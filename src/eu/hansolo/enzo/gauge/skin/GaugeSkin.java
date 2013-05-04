/*
 * Copyright (c) 2013. Gerrit Grunwald
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package eu.hansolo.enzo.gauge.skin;

import eu.hansolo.enzo.gauge.Gauge;
import eu.hansolo.enzo.gauge.Section;
import eu.hansolo.enzo.tools.ShapeConverter;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadowBuilder;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;

import java.util.Locale;


/**
 * Created by
 * User: hansolo
 * Date: 01.04.13
 * Time: 17:18
 */
public class GaugeSkin extends SkinBase<Gauge> implements Skin<Gauge> {
    private static final double            PREFERRED_WIDTH  = 200;
    private static final double            PREFERRED_HEIGHT = 200;
    private static final double            MINIMUM_WIDTH    = 50;
    private static final double            MINIMUM_HEIGHT   = 50;
    private static final double            MAXIMUM_WIDTH    = 1024;
    private static final double            MAXIMUM_HEIGHT   = 1024;
    private double                         size;
    private Pane                           pane;
    private Region                         background;
    private Canvas                         ticksAndSectionsCanvas;
    private GraphicsContext                ticksAndSections;
    private Region                         needle;
    private Region                         needleHighlight;
    private Rotate                         needleRotate;
    private Region                         knob;
    private Text                           title;
    private Text                           unit;
    private double                         angleStep;
    private Timeline                       timeline;


    // ******************** Constructors **************************************
    public GaugeSkin(Gauge gauge) {
        super(gauge);
        pane          = new Pane();
        angleStep     = gauge.getAngleRange() / (gauge.getMaxValue() - gauge.getMinValue());
        timeline      = new Timeline();
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
        background = new Region();
        background.getStyleClass().setAll("background");

        ticksAndSectionsCanvas = new Canvas(PREFERRED_WIDTH, PREFERRED_HEIGHT);
        ticksAndSections = ticksAndSectionsCanvas.getGraphicsContext2D();

        needle = new Region();
        needle.getStyleClass().setAll(Gauge.STYLE_CLASS_NEEDLE_STANDARD);
        needleRotate = new Rotate(180 - getSkinnable().getStartAngle());
        needle.getTransforms().setAll(needleRotate);

        needleHighlight = new Region();
        needleHighlight.getStyleClass().setAll("needle-highlight");
        needleHighlight.getTransforms().setAll(needleRotate);

        knob = new Region();
        knob.getStyleClass().setAll("knob");

        Group shadowGroup = new Group(needle, needleHighlight, knob);
        shadowGroup.setEffect(DropShadowBuilder.create()
                                               .color(Color.rgb(0, 0, 0, 0.25))
                                               .radius(3)
                                               .blurType(BlurType.GAUSSIAN)
                                               .offsetY(3)
                                               .build());

        title = new Text(getSkinnable().getTitle());
        title.setTextOrigin(VPos.CENTER);
        title.getStyleClass().setAll("title");

        unit = new Text(getSkinnable().getUnit());
        unit.setTextOrigin(VPos.CENTER);
        unit.getStyleClass().setAll("unit");

        // Add all nodes
        pane.getChildren().setAll(background, ticksAndSectionsCanvas, title, unit, shadowGroup);

        getChildren().setAll(pane);
    }

    private void registerListeners() {
        getSkinnable().widthProperty().addListener(observable -> { handleControlPropertyChanged("RESIZE"); });
        getSkinnable().heightProperty().addListener(observable -> { handleControlPropertyChanged("RESIZE"); });
        getSkinnable().valueProperty().addListener(observable -> { handleControlPropertyChanged("VALUE"); });
        getSkinnable().minValueProperty().addListener(observable -> { handleControlPropertyChanged("RECALC"); });
        getSkinnable().maxValueProperty().addListener(observable -> { handleControlPropertyChanged("RECALC"); });
        getSkinnable().minMeasuredValueProperty().addListener(observable -> { handleControlPropertyChanged("MIN_MEASURED_VALUE"); });
        getSkinnable().maxMeasuredValueProperty().addListener(observable -> { handleControlPropertyChanged("MAX_MEASURED_VALUE"); });
        getSkinnable().tickLabelOrientationProperty().addListener(observable -> { handleControlPropertyChanged("RESIZE"); });
        getSkinnable().needleTypeProperty().addListener(observable -> { handleControlPropertyChanged("NEEDLE_TYPE"); });
        getSkinnable().needleColorProperty().addListener(observable -> { handleControlPropertyChanged("NEEDLE_COLOR"); });
        getSkinnable().animatedProperty().addListener(observable -> { handleControlPropertyChanged("ANIMATED"); });
        getSkinnable().angleRangeProperty().addListener(observable -> { handleControlPropertyChanged("ANGLE_RANGE"); });
        getSkinnable().numberFormatProperty().addListener(observable -> { handleControlPropertyChanged("RECALC"); });
        needleRotate.angleProperty().addListener(observable -> { handleControlPropertyChanged("LCD"); });
    }


    // ******************** Methods *******************************************
    protected void handleControlPropertyChanged(final String PROPERTY) {
        if ("RESIZE".equals(PROPERTY)) {
            resize();
        } else if ("VALUE".equals(PROPERTY)) {
            rotateNeedle();
        } else if ("RECALC".equals(PROPERTY)) {
            angleStep = getSkinnable().getAngleRange() / (getSkinnable().getMaxValue() - getSkinnable().getMinValue());
            resize();
        } else if ("LCD".equals(PROPERTY)) {
            if (getSkinnable().isLcdEnabled()) {
                drawLcd(ticksAndSections);
            }
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
        double range       = (getSkinnable().getMaxValue() - getSkinnable().getMinValue());
        double angleRange  = getSkinnable().getAngleRange();
        angleStep          = angleRange / range;
        double targetAngle = needleRotate.getAngle() + (getSkinnable().getValue() - getSkinnable().getOldValue()) * angleStep;

        if (getSkinnable().isAnimated()) {
            timeline.stop();
            final KeyValue KEY_VALUE = new KeyValue(needleRotate.angleProperty(), targetAngle, Interpolator.SPLINE(0.5, 0.4, 0.4, 1.0));
            final KeyFrame KEY_FRAME = new KeyFrame(getSkinnable().getAnimationTime(), KEY_VALUE);
            timeline.getKeyFrames().setAll(KEY_FRAME);
            timeline.getKeyFrames().add(KEY_FRAME);
            timeline.play();
        } else {
            needleRotate.setAngle(targetAngle);
        }
    }

    private void changeNeedle() {
        switch(getSkinnable().getNeedleType()) {
            default:
                needle.getStyleClass().setAll(Gauge.STYLE_CLASS_NEEDLE_STANDARD);
        }
    }

    private void changeBackgroundShape() {
        if (getSkinnable().isAlwaysRound()) {
            getSkinnable().setStyle("-shape: \"M 0 100 C 0 44.7708 44.7708 0 100 0 C 155.2292 0 200 44.7708 200 100 C 200 155.2292 155.2292 200 100 200 C 44.7708 200 0 155.2292 0 100 Z\";");
        } else {
            final Arc ARC = new Arc();
            ARC.setCenterX(size * 0.5);
            ARC.setCenterY(size * 0.5);
            ARC.setRadiusX(size * 0.5);
            ARC.setRadiusY(size * 0.5);
            ARC.setStartAngle(getSkinnable().getStartAngle());
            ARC.setLength(getSkinnable().getAngleRange());
            ARC.setType(ArcType.ROUND);
            getSkinnable().setStyle("-shape: \"" + ShapeConverter.convertArc(ARC) + "\";");
        }
    }

    private void drawTickMarks(final GraphicsContext CTX) {
        double sinValue;
        double cosValue;
        double offset = getSkinnable().getStartAngle();
        Point2D center = new Point2D(size * 0.5, size * 0.5);
        for (double angle = 0, counter = getSkinnable().getMinValue() ; Double.compare(counter, getSkinnable().getMaxValue()) <= 0 ; angle -= angleStep, counter++) {
            sinValue = Math.sin(Math.toRadians(angle + offset));
            cosValue = Math.cos(Math.toRadians(angle + offset));

            Point2D innerMainPoint   = new Point2D(center.getX() + size * 0.368 * sinValue, center.getY() + size * 0.368 * cosValue);
            Point2D innerMediumPoint = new Point2D(center.getX() + size * 0.388 * sinValue, center.getY() + size * 0.388 * cosValue);
            Point2D innerMinorPoint  = new Point2D(center.getX() + size * 0.3975 * sinValue, center.getY() + size * 0.3975 * cosValue);
            Point2D outerPoint       = new Point2D(center.getX() + size * 0.432 * sinValue, center.getY() + size * 0.432 * cosValue);
            Point2D textPoint        = new Point2D(center.getX() + size * 0.31 * sinValue, center.getY() + size * 0.31 * cosValue);

            CTX.setStroke(getSkinnable().getTickMarkFill());
            if (counter % getSkinnable().getMajorTickSpace() == 0) {
                // Draw major tickmark
                CTX.setLineWidth(size * 0.0055);
                CTX.strokeLine(innerMainPoint.getX(), innerMainPoint.getY(), outerPoint.getX(), outerPoint.getY());

                // Draw text
                CTX.save();
                CTX.setFont(Font.font("Verdana", FontWeight.NORMAL, 0.04 * size));
                CTX.setTextAlign(TextAlignment.CENTER);
                CTX.setTextBaseline(VPos.CENTER);
                CTX.setFill(getSkinnable().getTickMarkFill());
                CTX.fillText(Integer.toString((int) counter), textPoint.getX(), textPoint.getY());
                CTX.restore();
            } else if (getSkinnable().getMinorTickSpace() % 2 != 0 && counter % 5 == 0) {
                CTX.setLineWidth(size * 0.0035);
                CTX.strokeLine(innerMediumPoint.getX(), innerMediumPoint.getY(), outerPoint.getX(), outerPoint.getY());
            } else if (counter % getSkinnable().getMinorTickSpace() == 0) {
                CTX.setLineWidth(size * 0.00225);
                CTX.strokeLine(innerMinorPoint.getX(), innerMinorPoint.getY(), outerPoint.getX(), outerPoint.getY());
            }
        }
    }

    private final void drawSections(final GraphicsContext CTX) {
        final double xy     = (size - 0.8 * size) / 2;
        final double wh     = size * 0.8;
        final double OFFSET = 90 - getSkinnable().getStartAngle();
        for (final Section section : getSkinnable().getSections()) {
            final double ANGLE_START   = section.getStart() * angleStep;
            final double ANGLE_EXTEND  = (section.getStop() - section.getStart()) * angleStep;
            CTX.save();
            CTX.setStroke(section.getColor());
            CTX.setLineWidth(size * 0.07);
            CTX.setLineCap(StrokeLineCap.BUTT);
            CTX.strokeArc(xy, xy, wh, wh, -(OFFSET + ANGLE_START), -ANGLE_EXTEND, ArcType.OPEN);
            CTX.restore();
        }
    }

    private final void drawLcd(final GraphicsContext CTX) {
        CTX.clearRect(0.3026548673 * size, 0.7610619469 * size, 0.396460177 * size, 0.1256637168 * size);
        CTX.setFill(Color.rgb(98, 98, 98));
        CTX.fillRoundRect(0.3026548673 * size, 0.7699115044 * size, 0.396460177 * size, 0.1061946903 * size, 0.0212389381 * size, 0.0212389381 * size);
        CTX.setFill(Color.rgb(186, 186, 178));
        CTX.fillRoundRect(0.3061946903 * size, 0.7734513565 * size, 0.389380531 * size, 0.0991150442 * size, 0.0194690265 * size, 0.0194690265 * size);
        CTX.setFill(Color.rgb(73, 73, 73));
        CTX.setTextAlign(TextAlignment.RIGHT);
        CTX.setTextBaseline(VPos.CENTER);
        CTX.setFont(Font.loadFont(getClass().getResource("digital.ttf").toExternalForm(), (0.1 * size)));
        CTX.fillText(String.format(Locale.US, "%.2f", (needleRotate.getAngle() + getSkinnable().getStartAngle() - 180) / angleStep), 0.68 * size, 0.81 * size);
    }

    private void resize() {
        size = getSkinnable().getWidth() < getSkinnable().getHeight() ? getSkinnable().getWidth() : getSkinnable().getHeight();

        //double emptySegmentHeight = size * (1.0 - Math.cos(Math.toRadians((360 - getSkinnable().getAngleRange()) * 0.5)));
        background.setPrefSize(size, size);

        ticksAndSectionsCanvas.setWidth(size);
        ticksAndSectionsCanvas.setHeight(size);
        ticksAndSections.clearRect(0, 0, size, size);
        drawSections(ticksAndSections);
        drawTickMarks(ticksAndSections);
        if (getSkinnable().isLcdEnabled()) {
            drawLcd(ticksAndSections);
        }

        switch (getSkinnable().getNeedleType()) {
            default:
                needle.setPrefSize(size * 0.04, size * 0.425);
        }
        needle.setTranslateX((size - needle.getPrefWidth()) * 0.5);
        needle.setTranslateY(size * 0.5 - needle.getPrefHeight());
        needleRotate.setPivotX((needle.getPrefWidth()) * 0.5);
        needleRotate.setPivotY(needle.getPrefHeight());

        needleHighlight.setPrefSize(size * 0.04, size * 0.425);
        needleHighlight.setTranslateX((size - needle.getPrefWidth()) * 0.5);
        needleHighlight.setTranslateY(size * 0.5 - needle.getPrefHeight());

        knob.setPrefSize(size * 0.11, size * 0.11);
        knob.setTranslateX((size - knob.getPrefWidth()) * 0.5);
        knob.setTranslateY((size - knob.getPrefHeight()) * 0.5);

        title.setFont(Font.font("Arial", FontWeight.NORMAL, size * 0.06));
        title.setTranslateX((size - title.getLayoutBounds().getWidth()) * 0.5);
        title.setTranslateY(size * 0.35);

        unit.setFont(Font.font("Arial", FontWeight.NORMAL, size * 0.05));
        unit.setTranslateX((size - unit.getLayoutBounds().getWidth()) * 0.5);
        unit.setTranslateY(size * 0.65);
    }
}
