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

import eu.hansolo.enzo.gauge.SimpleRadialGauge;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ListChangeListener;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.util.Locale;


/**
 * Created by
 * User: hansolo
 * Date: 15.07.13
 * Time: 16:45
 */
public class SimpleRadialGaugeSkin extends SkinBase<SimpleRadialGauge> implements Skin<SimpleRadialGauge> {
    private static final double PREFERRED_WIDTH  = 200;
    private static final double PREFERRED_HEIGHT = 200;
    private static final double MINIMUM_WIDTH    = 50;
    private static final double MINIMUM_HEIGHT   = 50;
    private static final double MAXIMUM_WIDTH    = 1024;
    private static final double MAXIMUM_HEIGHT   = 1024;
    private double              size;
    private double              centerX;
    private double              centerY;
    private Pane                pane;
    private DoubleProperty      angle;
    private Arc                 barBackground;
    private Arc                 bar;
    private Canvas              titleAndUnit;
    private GraphicsContext     ctx;
    private Label               value;
    private double              angleStep;
    private InnerShadow         innerShadow;
    private Timeline            timeline;


    // ******************** Constructors **************************************
    public SimpleRadialGaugeSkin(SimpleRadialGauge gauge) {
        super(gauge);
        angleStep = gauge.getAngleRange() / (gauge.getMaxValue() - gauge.getMinValue());
        angle     = new SimpleDoubleProperty(this, "angle", getSkinnable().getValue() * angleStep);
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

        innerShadow = new InnerShadow();
        innerShadow.setRadius(0.01 * size);
        innerShadow.setBlurType(BlurType.TWO_PASS_BOX);
        innerShadow.setColor(Color.rgb(0, 0, 0, 0.65));

        barBackground = new Arc();
        barBackground.setCenterX(PREFERRED_WIDTH * 0.5);
        barBackground.setCenterY(PREFERRED_HEIGHT * 0.5);
        barBackground.setRadiusX(PREFERRED_WIDTH * 0.5 - 4);
        barBackground.setRadiusY(PREFERRED_HEIGHT * 0.5 - 4);
        barBackground.setStartAngle(getSkinnable().getStartAngle() - 90);
        barBackground.setLength(-getSkinnable().getAngleRange());
        barBackground.setStrokeType(StrokeType.CENTERED);
        barBackground.getStyleClass().addAll("bar-background");
        barBackground.setEffect(innerShadow);

        bar = new Arc();
        bar.setCenterX(PREFERRED_WIDTH * 0.5);
        bar.setCenterY(PREFERRED_HEIGHT * 0.5);
        bar.setRadiusX(PREFERRED_WIDTH * 0.5 - 4);
        bar.setRadiusY(PREFERRED_HEIGHT * 0.5 - 4);
        bar.setStartAngle(getSkinnable().getStartAngle() - 90);
        bar.setLength(0);
        bar.setStrokeType(StrokeType.CENTERED);
        bar.getStyleClass().addAll("bar");
        bar.setEffect(innerShadow);

        titleAndUnit = new Canvas(PREFERRED_WIDTH, PREFERRED_HEIGHT);
        ctx          = titleAndUnit.getGraphicsContext2D();

        value = new Label(String.format(Locale.US, "%." + getSkinnable().getDecimals() + "f", (angle.get() + getSkinnable().getStartAngle() - 180) / angleStep));
        value.setMouseTransparent(true);
        value.setAlignment(Pos.CENTER);
        value.getStyleClass().addAll("value");

        // Add all nodes
        pane = new Pane();
        pane.getChildren().setAll(titleAndUnit,
                                  barBackground,
                                  bar,
                                  value);

        getChildren().setAll(pane);
    }

    private void registerListeners() {
        getSkinnable().widthProperty().addListener(observable -> handleControlPropertyChanged("RESIZE"));
        getSkinnable().heightProperty().addListener(observable -> handleControlPropertyChanged("RESIZE"));
        getSkinnable().valueProperty().addListener(observable -> handleControlPropertyChanged("VALUE"));
        getSkinnable().minValueProperty().addListener(observable -> handleControlPropertyChanged("RESIZE"));
        getSkinnable().maxValueProperty().addListener(observable -> handleControlPropertyChanged("RESIZE"));

        getSkinnable().animatedProperty().addListener(observable -> handleControlPropertyChanged("ANIMATED"));
        getSkinnable().angleRangeProperty().addListener(observable -> handleControlPropertyChanged("ANGLE_RANGE"));
        getSkinnable().decimalsProperty().addListener(observable -> handleControlPropertyChanged("RESIZE"));

        getSkinnable().valueColorProperty().addListener(observable -> handleControlPropertyChanged("REDRAW_CANVAS"));
        getSkinnable().labelColorProperty().addListener(observable -> handleControlPropertyChanged("REDRAW_CANVAS"));

        getSkinnable().getStyleClass().addListener((ListChangeListener<String>) change -> handleControlPropertyChanged("STYLE"));

        angle.addListener(observable -> handleControlPropertyChanged("ANGLE"));
    }


    // ******************** Methods *******************************************
    protected void handleControlPropertyChanged(final String PROPERTY) {
        if ("RESIZE".equals(PROPERTY)) {
            resize();
        } else if ("VALUE".equals(PROPERTY)) {
            setBar();
        } else if ("RECALC".equals(PROPERTY)) {
            angleStep = getSkinnable().getAngleRange() / (getSkinnable().getMaxValue() - getSkinnable().getMinValue());
            resize();
        } else if ("ANGLE".equals(PROPERTY)) {
            double currentValue = angle.get() / angleStep;

            value.setText(String.format(Locale.US, "%." + getSkinnable().getDecimals() + "f", currentValue));
            value.setTranslateX((size - value.getLayoutBounds().getWidth()) * 0.5);
            for (int i = 0 ; i < getSkinnable().getSections().size() ; i++) {
                if (getSkinnable().getSections().get(i).contains(currentValue)) {
                    if (bar.getStyleClass().contains("section" + i)) continue;
                    bar.getStyleClass().add("section" + i);
                } else {
                    bar.getStyleClass().remove("section" + i);
                }
            }
            bar.setLength(-currentValue * angleStep);
        } else if ("STYLE".equals(PROPERTY)) {
            for (String style : getSkinnable().getStyleClass()) {
                if (bar.getStyleClass().contains(style)) continue;
                bar.getStyleClass().add(style);
            }
        } else if ("REDRAW_CANVAS".equals(PROPERTY)) {
            drawTitleAndUnit();
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
    private void drawTitleAndUnit() {
        ctx.clearRect(0, 0, size, size);
        ctx.setFill(getSkinnable().getLabelColor());
        ctx.setTextBaseline(VPos.CENTER);
        ctx.setTextAlign(TextAlignment.CENTER);
        ctx.setFont(Font.font("Open Sans", FontWeight.NORMAL, 20 / PREFERRED_HEIGHT * size));

        // title
        ctx.fillText(getSkinnable().getTitle(), size * 0.5, size * 0.3);
        // unit
        ctx.fillText(getSkinnable().getUnit(), size * 0.5, size * 0.7);
    }

    private void setBar() {
        double range       = (getSkinnable().getMaxValue() - getSkinnable().getMinValue());
        double angleRange  = getSkinnable().getAngleRange();
        angleStep          = angleRange / range;
        double targetAngle = getSkinnable().getValue() * angleStep;

        if (getSkinnable().isAnimated()) {
            timeline.stop();
            final KeyValue KEY_VALUE = new KeyValue(angle, targetAngle, Interpolator.SPLINE(0.5, 0.4, 0.4, 1.0));
            final KeyFrame KEY_FRAME = new KeyFrame(Duration.millis(getSkinnable().getAnimationDuration()), KEY_VALUE);
            timeline.getKeyFrames().setAll(KEY_FRAME);
            timeline.play();
        } else {
            angle.set(targetAngle);
        }
    }

    private void resize() {
        size = getSkinnable().getWidth() < getSkinnable().getHeight() ? getSkinnable().getWidth() : getSkinnable().getHeight();
        centerX = size * 0.5;
        centerY = size * 0.5;

        final double RADIUS     = size * 0.5 - 4;
        final double ARC_RADIUS = RADIUS - getSkinnable().getBarWidth() / 2;

        innerShadow.setRadius(0.01 * size);

        if (size > 100) {
            titleAndUnit.setWidth(size);
            titleAndUnit.setHeight(size);
            drawTitleAndUnit();
        }
        titleAndUnit.setVisible(size > 100);

        barBackground.setCenterX(centerX);
        barBackground.setCenterY(centerY);
        barBackground.setRadiusX(ARC_RADIUS);
        barBackground.setRadiusY(ARC_RADIUS);

        bar.setCenterX(centerX);
        bar.setCenterY(centerY);
        bar.setRadiusX(barBackground.getRadiusX());
        bar.setRadiusY(barBackground.getRadiusY());

        value.setFont(Font.font("Open Sans", FontWeight.BOLD, (size - (getSkinnable().getBarWidth() * 2)) * 0.3));
        value.setText(String.format(Locale.US, "%." + getSkinnable().getDecimals() + "f", getSkinnable().getValue()));
        value.setPrefSize((size - 8 - (2 * getSkinnable().getBarWidth())) * 0.8, (size - 8 - (2 * getSkinnable().getBarWidth())) * 0.8);
        value.setTranslateX((size - value.getPrefWidth()) * 0.5);
        value.setTranslateY((size - value.getPrefHeight()) * 0.5);
    }
}
