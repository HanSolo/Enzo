/*
 * Copyright (c) 2013 by Gerrit Grunwald
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.langhammer.enzo.gauge.skin;

import eu.langhammer.enzo.common.Section;
import eu.langhammer.enzo.gauge.SimpleGauge;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.geometry.Point2D;
import javafx.geometry.VPos;
import javafx.scene.CacheHint;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.FillRule;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.Locale;


/**
 * Created by
 * User: hansolo
 * Date: 01.04.13
 * Time: 17:18
 */
public class SimpleGaugeSkin extends SkinBase<SimpleGauge> implements Skin<SimpleGauge> {
    private static final double PREFERRED_WIDTH  = 200;
    private static final double PREFERRED_HEIGHT = 200;
    private static final double MINIMUM_WIDTH    = 50;
    private static final double MINIMUM_HEIGHT   = 50;
    private static final double MAXIMUM_WIDTH    = 1024;
    private static final double MAXIMUM_HEIGHT   = 1024;
    private double              size;
    private Pane                pane;
    private Canvas              sectionsCanvas;    
    private GraphicsContext     sectionsCtx;
    private Canvas              measuredRangeCanvas;
    private GraphicsContext     measuredRangeCtx;
    private Path                needle;
    private Rotate              needleRotate;
    private Text                value;
    private Text                title;
    private double              angleStep;
    private Timeline            timeline;


    // ******************** Constructors **************************************
    public SimpleGaugeSkin(SimpleGauge gauge) {
        super(gauge);
        angleStep = gauge.getAngleRange() / (gauge.getMaxValue() - gauge.getMinValue());
        timeline  = new Timeline();

        init();
        initGraphics();
        registerListeners();
    }


    // ******************** Initialization ************************************
    private void init() {
        if (Double.compare(getSkinnable().getPrefWidth(), 0.0) <= 0 || Double.compare(getSkinnable().getPrefHeight(), 0.0) <= 0 ||
            Double.compare(getSkinnable().getWidth(), 0.0) <= 0 || Double.compare(getSkinnable().getHeight(), 0.0) <= 0) {
            if (getSkinnable().getPrefWidth() > 0 && getSkinnable().getPrefHeight() > 0) {
                getSkinnable().setPrefSize(getSkinnable().getPrefWidth(), getSkinnable().getPrefHeight());
            } else {
                getSkinnable().setPrefSize(PREFERRED_WIDTH, PREFERRED_HEIGHT);
            }
        }

        if (Double.compare(getSkinnable().getMinWidth(), 0.0) <= 0 || Double.compare(getSkinnable().getMinHeight(), 0.0) <= 0) {
            getSkinnable().setMinSize(MINIMUM_WIDTH, MINIMUM_HEIGHT);
        }

        if (Double.compare(getSkinnable().getMaxWidth(), 0.0) <= 0 || Double.compare(getSkinnable().getMaxHeight(), 0.0) <= 0) {
            getSkinnable().setMaxSize(MAXIMUM_WIDTH, MAXIMUM_HEIGHT);
        }
    }

    private void initGraphics() {
        Font.loadFont(getClass().getResourceAsStream("/eu/hansolo/enzo/fonts/opensans-semibold.ttf"), (0.06 * PREFERRED_HEIGHT)); // "OpenSans"

        sectionsCanvas = new Canvas(PREFERRED_WIDTH, PREFERRED_HEIGHT);
        sectionsCtx    = sectionsCanvas.getGraphicsContext2D();

        measuredRangeCanvas = new Canvas(PREFERRED_WIDTH, PREFERRED_HEIGHT);
        measuredRangeCanvas.setManaged(getSkinnable().isMeasuredRangeVisible());
        measuredRangeCanvas.setVisible(getSkinnable().isMeasuredRangeVisible());
        measuredRangeCtx    = measuredRangeCanvas.getGraphicsContext2D();        
        
        if (getSkinnable().getValue() < getSkinnable().getMinValue()) getSkinnable().setValue(getSkinnable().getMinValue());
        if (getSkinnable().getValue() > getSkinnable().getMaxValue()) getSkinnable().setValue(getSkinnable().getMaxValue());

        needleRotate = new Rotate(180 - getSkinnable().getStartAngle());
        if (getSkinnable().getMinValue() < 0) {
            needleRotate.setAngle(needleRotate.getAngle() + (getSkinnable().getValue() - getSkinnable().getOldValue() - getSkinnable().getMinValue()) * angleStep);
        } else {
            //needleRotate.setAngle(needleRotate.getAngle() + (getSkinnable().getValue() - getSkinnable().getOldValue() + getSkinnable().getMinValue()) * angleStep);
        }

        angleStep = getSkinnable().getAngleRange() / (getSkinnable().getMaxValue() - getSkinnable().getMinValue());
        needleRotate.setAngle(needleRotate.getAngle() + (getSkinnable().getValue() - getSkinnable().getOldValue()) * angleStep);

        needle = new Path();
        needle.setFillRule(FillRule.EVEN_ODD);
        needle.getStyleClass().setAll("needle");
        needle.getTransforms().setAll(needleRotate);       

        value = new Text(String.format(Locale.US, "%." + getSkinnable().getDecimals() + "f", getSkinnable().getMinValue()) + getSkinnable().getUnit());
        value.setMouseTransparent(true);
        value.setTextOrigin(VPos.CENTER);
        value.getStyleClass().setAll("value");

        title = new Text(getSkinnable().getTitle());
        title.setTextOrigin(VPos.CENTER);
        title.getStyleClass().setAll("title");

        // Add all nodes
        pane = new Pane();
        pane.getStyleClass().add("simple-gauge");
        pane.getChildren().setAll(sectionsCanvas,
                                  measuredRangeCanvas,
                                  needle,
                                  value,
                                  title);

        getChildren().setAll(pane);
        resize();
    }

    private void registerListeners() {
        getSkinnable().widthProperty().addListener(observable -> handleControlPropertyChanged("RESIZE"));
        getSkinnable().heightProperty().addListener(observable -> handleControlPropertyChanged("RESIZE"));
        getSkinnable().valueProperty().addListener(observable -> handleControlPropertyChanged("VALUE"));
        getSkinnable().minValueProperty().addListener(observable -> handleControlPropertyChanged("RECALC"));
        getSkinnable().maxValueProperty().addListener(observable -> handleControlPropertyChanged("RECALC"));
        getSkinnable().titleProperty().addListener(observable -> handleControlPropertyChanged("RESIZE"));
        getSkinnable().needleColorProperty().addListener(observable -> handleControlPropertyChanged("NEEDLE_COLOR"));
        getSkinnable().animatedProperty().addListener(observable -> handleControlPropertyChanged("ANIMATED"));
        getSkinnable().angleRangeProperty().addListener(observable -> handleControlPropertyChanged("ANGLE_RANGE"));
        getSkinnable().sectionTextVisibleProperty().addListener(observable -> handleControlPropertyChanged("RESIZE"));
        getSkinnable().sectionIconVisibleProperty().addListener(observable -> handleControlPropertyChanged("RESIZE"));
        getSkinnable().valueTextColorProperty().addListener(observable -> handleControlPropertyChanged("RESIZE"));
        getSkinnable().titleTextColorProperty().addListener(observable -> handleControlPropertyChanged("RESIZE"));
        getSkinnable().sectionTextColorProperty().addListener(observable -> handleControlPropertyChanged("RESIZE"));
        getSkinnable().measuredRangeVisibleProperty().addListener(observable -> handleControlPropertyChanged("MEASURED_RANGE_VISIBLE"));
        getSkinnable().getSections().addListener((ListChangeListener<Section>) change -> handleControlPropertyChanged("RESIZE"));        

        needleRotate.angleProperty().addListener(observable -> handleControlPropertyChanged("ANGLE"));
    }


    // ******************** Methods *******************************************
    protected void handleControlPropertyChanged(final String PROPERTY) {
        if ("RESIZE".equals(PROPERTY)) {
            resize();
        } else if ("VALUE".equals(PROPERTY)) {
            rotateNeedle();
        } else if ("RECALC".equals(PROPERTY)) {
            angleStep = getSkinnable().getAngleRange() / (getSkinnable().getMaxValue() + getSkinnable().getMinValue());
            if (getSkinnable().getMinValue() < 0) {                
                needleRotate.setAngle(180 - getSkinnable().getStartAngle() - (getSkinnable().getMinValue()) * angleStep);
            } else {                                
                needleRotate.setAngle(needleRotate.getAngle() + (getSkinnable().getValue() * angleStep));
            }
            resize();
        } else if ("ANGLE".equals(PROPERTY)) {
            double currentValue = (needleRotate.getAngle() + getSkinnable().getStartAngle() - 180) / angleStep + getSkinnable().getMinValue();
            value.setText(String.format(Locale.US, "%." + getSkinnable().getDecimals() + "f", currentValue) + getSkinnable().getUnit());
            value.setTranslateX((size - value.getLayoutBounds().getWidth()) * 0.5);
            if (value.getLayoutBounds().getWidth() > 0.45 * size) {
                resizeText();
            }
            // Check sections
            for (Section section : getSkinnable().getSections()) {
                if (section.contains(currentValue)) {
                    section.fireSectionEvent(new Section.SectionEvent(section, null, Section.SectionEvent.ENTERING_SECTION));
                    break;
                }
            }
            // Adjust minMeasured and maxMeasured values
            if (currentValue < getSkinnable().getMinMeasuredValue()) {
                getSkinnable().setMinMeasuredValue(currentValue);                
            }
            if (currentValue > getSkinnable().getMaxMeasuredValue()) {
                getSkinnable().setMaxMeasuredValue(currentValue);                
            }
            if (getSkinnable().isMeasuredRangeVisible()) drawMeasuredRange();
        } else if ("MEASURED_RANGE_VISIBLE".equals(PROPERTY)) {
            measuredRangeCanvas.setManaged(getSkinnable().isMeasuredRangeVisible());
            measuredRangeCanvas.setVisible(getSkinnable().isMeasuredRangeVisible());            
        }
    }
        
    public void resetNeedle() {
        timeline.stop();
        boolean wasAnimated = getSkinnable().isAnimated();                
        getSkinnable().setAnimated(false);            
        getSkinnable().setValue(getSkinnable().getMinValue());
        if (wasAnimated) {
            getSkinnable().setAnimated(true);
        }
    }    
    
    @Override protected double computeMinWidth(final double HEIGHT, double TOP_INSET, double RIGHT_INSET, double BOTTOM_INSET, double LEFT_INSET) {
        return super.computeMinWidth(Math.max(MINIMUM_HEIGHT, HEIGHT - TOP_INSET - BOTTOM_INSET), TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET);
    }
    @Override protected double computeMinHeight(final double WIDTH, double TOP_INSET, double RIGHT_INSET, double BOTTOM_INSET, double LEFT_INSET) {
        return super.computeMinHeight(Math.max(MINIMUM_WIDTH, WIDTH - LEFT_INSET - RIGHT_INSET), TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET);
    }

    @Override protected double computeMaxWidth(final double HEIGHT, double TOP_INSET, double RIGHT_INSET, double BOTTOM_INSET, double LEFT_INSET) {
        return super.computeMaxWidth(Math.min(MAXIMUM_HEIGHT, HEIGHT - TOP_INSET - BOTTOM_INSET), TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET);
    }
    @Override protected double computeMaxHeight(final double WIDTH, double TOP_INSET, double RIGHT_INSET, double BOTTOM_INSET, double LEFT_INSET) {
        return super.computeMaxHeight(Math.min(MAXIMUM_WIDTH, WIDTH - LEFT_INSET - RIGHT_INSET), TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET);
    }

    @Override protected double computePrefWidth(final double HEIGHT, double TOP_INSET, double RIGHT_INSET, double BOTTOM_INSET, double LEFT_INSET) {
        double prefHeight = PREFERRED_HEIGHT;
        if (HEIGHT != -1) {
            prefHeight = Math.max(0, HEIGHT - TOP_INSET - BOTTOM_INSET);
        }
        return super.computePrefWidth(prefHeight, TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET);
    }
    @Override protected double computePrefHeight(final double WIDTH, double TOP_INSET, double RIGHT_INSET, double BOTTOM_INSET, double LEFT_INSET) {
        double prefWidth = PREFERRED_WIDTH;
        if (WIDTH != -1) {
            prefWidth = Math.max(0, WIDTH - LEFT_INSET - RIGHT_INSET);
        }
        return super.computePrefHeight(prefWidth, TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET);
    }


    // ******************** Private Methods ***********************************
    private void rotateNeedle() {
        angleStep          = getSkinnable().getAngleRange() / (getSkinnable().getMaxValue() - getSkinnable().getMinValue());
        double targetAngle = needleRotate.getAngle() + (getSkinnable().getValue() - getSkinnable().getOldValue()) * angleStep;
        targetAngle        = clamp(180 - getSkinnable().getStartAngle(), 180 - getSkinnable().getStartAngle() + getSkinnable().getAngleRange(), targetAngle);
        
        if (getSkinnable().isAnimated()) {
            timeline.stop();            
            final KeyValue KEY_VALUE = new KeyValue(needleRotate.angleProperty(), targetAngle, Interpolator.SPLINE(0.5, 0.4, 0.4, 1.0));
            final KeyFrame KEY_FRAME = new KeyFrame(Duration.millis(getSkinnable().getAnimationDuration()), KEY_VALUE);
            timeline.getKeyFrames().setAll(KEY_FRAME);
            timeline.play();
        } else {
            needleRotate.setAngle(targetAngle);
        }
    }

    private final void drawSections() {
        sectionsCtx.clearRect(0, 0, size, size);
        final double MIN_VALUE       = getSkinnable().getMinValue();
        final double MAX_VALUE       = getSkinnable().getMaxValue();
        final double OFFSET          = getSkinnable().getStartAngle() - 90;
        final int NO_OF_SECTIONS     = getSkinnable().getSections().size();
        final double SECTIONS_OFFSET = size * 0.015;
        final double SECTIONS_SIZE   = size - (size * 0.03);
        angleStep                    = getSkinnable().getAngleRange() / (getSkinnable().getMaxValue() + getSkinnable().getMinValue());
        double sinValue;
        double cosValue;
        for (int i = 0 ; i < NO_OF_SECTIONS ; i++) {
            final Section SECTION = getSkinnable().getSections().get(i);
            final double SECTION_START_ANGLE;
            if (SECTION.getStart() > MAX_VALUE || SECTION.getStop() < MIN_VALUE) continue;

            if (SECTION.getStart() < MIN_VALUE && SECTION.getStop() < MAX_VALUE) {
                SECTION_START_ANGLE = MIN_VALUE * angleStep;
            } else {
                SECTION_START_ANGLE = (SECTION.getStart() - MIN_VALUE) * angleStep;
            }
            final double SECTION_ANGLE_EXTEND;
            if (SECTION.getStop() > MAX_VALUE) {
                SECTION_ANGLE_EXTEND = MAX_VALUE * angleStep;
            } else {
                SECTION_ANGLE_EXTEND = (SECTION.getStop() - SECTION.getStart()) * angleStep;
            }
            sectionsCtx.save();
            switch(i) {
                case 0: sectionsCtx.setFill(getSkinnable().getSectionFill0()); break;
                case 1: sectionsCtx.setFill(getSkinnable().getSectionFill1()); break;
                case 2: sectionsCtx.setFill(getSkinnable().getSectionFill2()); break;
                case 3: sectionsCtx.setFill(getSkinnable().getSectionFill3()); break;
                case 4: sectionsCtx.setFill(getSkinnable().getSectionFill4()); break;
                case 5: sectionsCtx.setFill(getSkinnable().getSectionFill5()); break;
                case 6: sectionsCtx.setFill(getSkinnable().getSectionFill6()); break;
                case 7: sectionsCtx.setFill(getSkinnable().getSectionFill7()); break;
                case 8: sectionsCtx.setFill(getSkinnable().getSectionFill8()); break;
                case 9: sectionsCtx.setFill(getSkinnable().getSectionFill9()); break;
            }
            sectionsCtx.fillArc(SECTIONS_OFFSET, SECTIONS_OFFSET, SECTIONS_SIZE, SECTIONS_SIZE, (OFFSET - SECTION_START_ANGLE), -SECTION_ANGLE_EXTEND, ArcType.ROUND);

            // Draw Section Text
            if (getSkinnable().isSectionTextVisible()) {
                sinValue = -Math.sin(Math.toRadians(OFFSET - 90 - SECTION_START_ANGLE - SECTION_ANGLE_EXTEND * 0.5));
                cosValue = -Math.cos(Math.toRadians(OFFSET - 90 - SECTION_START_ANGLE - SECTION_ANGLE_EXTEND * 0.5));
                Point2D textPoint = new Point2D(size * 0.5 + size * 0.365 * sinValue, size * 0.5 + size * 0.365 * cosValue);
                sectionsCtx.setFont(Font.font("Open Sans", FontWeight.NORMAL, 0.08 * size));
                sectionsCtx.setTextAlign(TextAlignment.CENTER);
                sectionsCtx.setTextBaseline(VPos.CENTER);
                sectionsCtx.setFill(getSkinnable().getSectionTextColor());
                sectionsCtx.fillText(SECTION.getText(), textPoint.getX(), textPoint.getY());
            }

            // Draw Section Icon
            if (size > 0) {
                if (getSkinnable().isSectionIconVisible() && !getSkinnable().isSectionTextVisible()) {
                    if (null != SECTION.getImage()) {
                        Image icon = SECTION.getImage();
                        sinValue = -Math.sin(Math.toRadians(OFFSET - 90 - SECTION_START_ANGLE - SECTION_ANGLE_EXTEND * 0.5));
                        cosValue = -Math.cos(Math.toRadians(OFFSET - 90 - SECTION_START_ANGLE - SECTION_ANGLE_EXTEND * 0.5));
                        Point2D iconPoint = new Point2D(size * 0.5 + size * 0.365 * sinValue, size * 0.5 + size * 0.365 * cosValue);
                        sectionsCtx.drawImage(icon, iconPoint.getX() - size * 0.06, iconPoint.getY() - size * 0.06, size * 0.12, size * 0.12);
                    }
                }
            }
            sectionsCtx.restore();
            
            // Draw white border around area                        
            sectionsCtx.setStroke(Color.WHITE);
            sectionsCtx.setLineWidth(size * 0.032);            
            sectionsCtx.strokeArc(SECTIONS_OFFSET, SECTIONS_OFFSET, SECTIONS_SIZE, SECTIONS_SIZE, OFFSET + 90, 270, ArcType.ROUND);                        
        }
    }

    private final void drawMeasuredRange() {        
        final double MIN_VALUE    = getSkinnable().getMinValue();        
        final double OFFSET       = getSkinnable().getStartAngle() - 90;
        final double START_ANGLE  = (getSkinnable().getMinMeasuredValue() - MIN_VALUE) * angleStep;
        final double ANGLE_EXTEND = (getSkinnable().getMaxMeasuredValue() - getSkinnable().getMinMeasuredValue()) * angleStep;
        final double RANGE_OFFSET = size * 0.015;
        final double RANGE_SIZE   = size - (size * 0.03);        
        
        measuredRangeCtx.save();
        measuredRangeCtx.clearRect(0, 0, size, size);
        measuredRangeCtx.setFill(getSkinnable().getRangeFill());
        measuredRangeCtx.fillArc(RANGE_OFFSET, RANGE_OFFSET, RANGE_SIZE, RANGE_SIZE, (OFFSET - START_ANGLE), -ANGLE_EXTEND, ArcType.ROUND);
        measuredRangeCtx.setStroke(Color.WHITE);
        measuredRangeCtx.setLineWidth(size * 0.032);
        measuredRangeCtx.strokeArc(RANGE_OFFSET, RANGE_OFFSET, RANGE_SIZE, RANGE_SIZE, (OFFSET - START_ANGLE), -ANGLE_EXTEND, ArcType.ROUND);
        measuredRangeCtx.restore();
    }
    
    private double clamp(final double MIN_VALUE, final double MAX_VALUE, final double VALUE) {
        if (VALUE < MIN_VALUE) return MIN_VALUE;
        if (VALUE > MAX_VALUE) return MAX_VALUE;
        return VALUE;
    }
    
    private void resizeText() {        
        value.setFont(Font.font("Open Sans", FontWeight.BOLD, size * 0.145));
        if (value.getLayoutBounds().getWidth() > 0.38 * size) {
            double decrement = 0d;
            while (value.getLayoutBounds().getWidth() > 0.38 * size && value.getFont().getSize() > 0) {
                value.setFont(Font.font("Open Sans", FontWeight.BOLD, size * (0.15 - decrement)));
                decrement += 0.01;
            }
        }
        value.setTranslateX((size - value.getLayoutBounds().getWidth()) * 0.5);
        value.setTranslateY(size * (title.getText().isEmpty() ? 0.5 : 0.48));

        title.setFont(Font.font("Open Sans", FontWeight.BOLD, size * 0.045));
        if (title.getLayoutBounds().getWidth() > 0.38 * size) {
            double decrement = 0d;
            while (title.getLayoutBounds().getWidth() > 0.38 * size && title.getFont().getSize() > 0) {
                title.setFont(Font.font("Open Sans", FontWeight.BOLD, size * (0.05 - decrement)));
                decrement += 0.01;
            }
        }
        title.setTranslateX((size - title.getLayoutBounds().getWidth()) * 0.5);
        title.setTranslateY(size * 0.5 + value.getFont().getSize() * 0.7);
    }

    private void resize() {
        size = getSkinnable().getWidth() < getSkinnable().getHeight() ? getSkinnable().getWidth() : getSkinnable().getHeight();

        sectionsCanvas.setWidth(size);
        sectionsCanvas.setHeight(size);
        drawSections();
        sectionsCanvas.setCache(true);
        sectionsCanvas.setCacheHint(CacheHint.QUALITY);

        measuredRangeCanvas.setWidth(size);
        measuredRangeCanvas.setHeight(size);
        drawMeasuredRange();        
        
        double currentValue = (needleRotate.getAngle() + getSkinnable().getStartAngle() - 180) / angleStep + getSkinnable().getMinValue();
        value.setText(String.format(Locale.US, "%." + getSkinnable().getDecimals() + "f", currentValue) + getSkinnable().getUnit());
        //value.setText(String.format(Locale.US, "%." + getSkinnable().getDecimals() + "f", (needleRotate.getAngle() + getSkinnable().getStartAngle() - 180) / angleStep) + getSkinnable().getUnit());

        title.setText(getSkinnable().getTitle());

        needle.getElements().clear();
        needle.getElements().add(new MoveTo(0.275 * size, 0.5 * size));
        needle.getElements().add(new CubicCurveTo(0.275 * size, 0.62426575 * size,
                                                  0.37573425 * size, 0.725 * size,
                                                  0.5 * size, 0.725 * size));
        needle.getElements().add(new CubicCurveTo(0.62426575 * size, 0.725 * size,
                                                  0.725 * size, 0.62426575 * size,
                                                  0.725 * size, 0.5 * size));
        needle.getElements().add(new CubicCurveTo(0.725 * size, 0.3891265 * size,
                                                  0.6448105 * size, 0.296985 * size,
                                                  0.5392625 * size, 0.2784125 * size));
        needle.getElements().add(new LineTo(0.5 * size, 0.0225));
        needle.getElements().add(new LineTo(0.4607375 * size, 0.2784125 * size));
        needle.getElements().add(new CubicCurveTo(0.3551895 * size, 0.296985 * size,
                                                  0.275 * size, 0.3891265 * size,
                                                  0.275 * size, 0.5 * size));
        needle.getElements().add(new ClosePath());
        needle.setStrokeWidth(size * 0.03);

        needle.relocate(needle.getLayoutBounds().getMinX(), needle.getLayoutBounds().getMinY());
        needleRotate.setPivotX(size * 0.5);
        needleRotate.setPivotY(size * 0.5);

        resizeText();
    }
}
