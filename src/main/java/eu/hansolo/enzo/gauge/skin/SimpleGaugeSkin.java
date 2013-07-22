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

package eu.hansolo.enzo.gauge.skin;

import eu.hansolo.enzo.gauge.Section;
import eu.hansolo.enzo.gauge.SimpleGauge;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.geometry.VPos;
import javafx.scene.CacheHint;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.Pane;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.FillRule;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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
    private static final double      PREFERRED_WIDTH  = 200;
    private static final double      PREFERRED_HEIGHT = 200;
    private static final double      MINIMUM_WIDTH    = 50;
    private static final double      MINIMUM_HEIGHT   = 50;
    private static final double      MAXIMUM_WIDTH    = 1024;
    private static final double      MAXIMUM_HEIGHT   = 1024;
    private double                   size;
    private Pane                     pane;
    private Canvas                   sectionsCanvas;
    private GraphicsContext          sectionsCtx;
    private Path                     needle;
    private Rotate                   needleRotate;
    private Text                     value;
    private double                   angleStep;
    private Timeline                 timeline;


    // ******************** Constructors **************************************
    public SimpleGaugeSkin(SimpleGauge gauge) {
        super(gauge);
        angleStep         = gauge.getAngleRange() / (gauge.getMaxValue() - gauge.getMinValue());
        timeline          = new Timeline();

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

        needleRotate = new Rotate(180 - getSkinnable().getStartAngle());
        needleRotate.setAngle(needleRotate.getAngle() + (getSkinnable().getValue() - getSkinnable().getOldValue()) * angleStep);

        needle = new Path();
        needle.setFillRule(FillRule.EVEN_ODD);
        needle.getStyleClass().setAll("needle");
        needle.getTransforms().setAll(needleRotate);

        value = new Text(String.format(Locale.US, "%." + getSkinnable().getDecimals() + "f", getSkinnable().getValue()));
        value.setMouseTransparent(true);
        value.setTextOrigin(VPos.CENTER);
        value.getStyleClass().setAll("value");

        // Add all nodes
        pane = new Pane();
        pane.getChildren().setAll(sectionsCanvas,
                                  needle,
                                  value);

        getChildren().setAll(pane);
        resize();
    }

    private void registerListeners() {
        getSkinnable().widthProperty().addListener(observable -> handleControlPropertyChanged("RESIZE"));
        getSkinnable().heightProperty().addListener(observable -> handleControlPropertyChanged("RESIZE"));
        getSkinnable().valueProperty().addListener(observable -> handleControlPropertyChanged("VALUE"));
        getSkinnable().minValueProperty().addListener(observable -> handleControlPropertyChanged("RECALC"));
        getSkinnable().maxValueProperty().addListener(observable -> handleControlPropertyChanged("RECALC"));
        getSkinnable().needleColorProperty().addListener(observable -> handleControlPropertyChanged("NEEDLE_COLOR"));
        getSkinnable().animatedProperty().addListener(observable -> handleControlPropertyChanged("ANIMATED"));
        getSkinnable().angleRangeProperty().addListener(observable -> handleControlPropertyChanged("ANGLE_RANGE"));
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
            angleStep = getSkinnable().getAngleRange() / (getSkinnable().getMaxValue() - getSkinnable().getMinValue());
            resize();
        } else if ("ANGLE".equals(PROPERTY)) {
            double currentValue = (needleRotate.getAngle() + getSkinnable().getStartAngle() - 180) / angleStep;
            value.setText(String.format(Locale.US, "%." + getSkinnable().getDecimals() + "f", currentValue) + getSkinnable().getUnit());
            value.setTranslateX((size - value.getLayoutBounds().getWidth()) * 0.5);
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
        final double MIN_VALUE   = getSkinnable().getMinValue();
        final double OFFSET      = getSkinnable().getStartAngle();
        final int NO_OF_SECTIONS = getSkinnable().getSections().size();
        for (int i = 0 ; i < NO_OF_SECTIONS ; i++) {
            final Section SECTION      = getSkinnable().getSections().get(NO_OF_SECTIONS - i - 1);
            final double  ANGLE_START  = (SECTION.getStart() - MIN_VALUE) * angleStep;
            final double  ANGLE_EXTEND = (SECTION.getStop() - SECTION.getStart()) * angleStep;
            sectionsCtx.save();
            switch(i) {
                case 0: sectionsCtx.setFill(getSkinnable().getSection0Fill()); break;
                case 1: sectionsCtx.setFill(getSkinnable().getSection1Fill()); break;
                case 2: sectionsCtx.setFill(getSkinnable().getSection2Fill()); break;
                case 3: sectionsCtx.setFill(getSkinnable().getSection3Fill()); break;
                case 4: sectionsCtx.setFill(getSkinnable().getSection4Fill()); break;
                case 5: sectionsCtx.setFill(getSkinnable().getSection5Fill()); break;
                case 6: sectionsCtx.setFill(getSkinnable().getSection6Fill()); break;
                case 7: sectionsCtx.setFill(getSkinnable().getSection7Fill()); break;
                case 8: sectionsCtx.setFill(getSkinnable().getSection8Fill()); break;
                case 9: sectionsCtx.setFill(getSkinnable().getSection9Fill()); break;
            }
            sectionsCtx.fillArc(0, 0, size, size, (OFFSET + ANGLE_START), ANGLE_EXTEND, ArcType.ROUND);
            sectionsCtx.restore();
        }
    }

    private void resizeText() {
        value.setFont(Font.font("Open Sans", FontWeight.BOLD, size * 0.15));
        value.setTranslateX((size - value.getLayoutBounds().getWidth()) * 0.5);
        value.setTranslateY(size * 0.5);
    }

    private void resize() {
        size = getSkinnable().getWidth() < getSkinnable().getHeight() ? getSkinnable().getWidth() : getSkinnable().getHeight();

        sectionsCanvas.setWidth(size);
        sectionsCanvas.setHeight(size);
        drawSections();
        sectionsCanvas.setCache(true);
        sectionsCanvas.setCacheHint(CacheHint.QUALITY);

        value.setText(String.format(Locale.US, "%." + getSkinnable().getDecimals() + "f", (needleRotate.getAngle() + getSkinnable().getStartAngle() - 180) / angleStep) + getSkinnable().getUnit());

        needle.getElements().clear();
        needle.getElements().add(new MoveTo(0.24 * size, 0.5 * size));
        needle.getElements().add(new CubicCurveTo(0.24 * size, 0.6433333333333333 * size,
                                                  0.3566666666666667 * size, 0.76 * size,
                                                  0.5 * size, 0.76 * size));
        needle.getElements().add(new CubicCurveTo(0.6433333333333333 * size, 0.76 * size,
                                                  0.76 * size, 0.6433333333333333 * size,
                                                  0.76 * size, 0.5 * size));
        needle.getElements().add(new CubicCurveTo(0.76 * size, 0.37166666666666665 * size,
                                                  0.6666666666666666 * size, 0.265 * size,
                                                  0.545 * size, 0.24333333333333335 * size));
        needle.getElements().add(new CubicCurveTo(0.545 * size, 0.24333333333333335 * size,
                                                  0.5 * size, 0.0,
                                                  0.5 * size, 0.0));
        needle.getElements().add(new CubicCurveTo(0.5 * size, 0.0,
                                                  0.45666666666666667 * size, 0.24333333333333335 * size,
                                                  0.45666666666666667 * size, 0.24333333333333335 * size));
        needle.getElements().add(new CubicCurveTo(0.3333333333333333 * size, 0.265 * size,
                                                  0.24 * size, 0.37166666666666665 * size,
                                                  0.24 * size, 0.5 * size));
        needle.getElements().add(new ClosePath());
        needle.setStrokeWidth(size * 0.0283333333);

        needle.relocate(needle.getLayoutBounds().getMinX(), needle.getLayoutBounds().getMinY());
        needleRotate.setPivotX(size * 0.5);
        needleRotate.setPivotY(size * 0.5);

        resizeText();
    }
}
