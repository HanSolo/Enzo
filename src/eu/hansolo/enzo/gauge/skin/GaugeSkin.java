/*
 * Copyright (c) 2013, Gerrit Grunwald
 * All right reserved
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * The names of its contributors may not be used to endorse or promote
 * products derived from this software without specific prior written
 * permission.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package eu.hansolo.enzo.gauge.skin;

import eu.hansolo.enzo.gauge.Gauge;
import eu.hansolo.enzo.tools.ShapeConverter;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadowBuilder;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RegionBuilder;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;


/**
 * Created by
 * User: hansolo
 * Date: 01.04.13
 * Time: 17:18
 */
public class GaugeSkin extends SkinBase<Gauge> implements Skin<Gauge> {
    private static final double PREFERRED_WIDTH  = 200;
    private static final double PREFERRED_HEIGHT = 200;
    private static final double MINIMUM_WIDTH    = 50;
    private static final double MINIMUM_HEIGHT   = 50;
    private static final double MAXIMUM_WIDTH    = 1024;
    private static final double MAXIMUM_HEIGHT   = 1024;
    private double              size;
    private Pane                pane;
    private Region              background;
    private Region              ticks;
    private Region              needle;
    private Region              needleHighlight;
    private Rotate              needleRotate;
    private double              angleStep;
    private Region              knob;
    private Text                title;
    private Text                unit;
    private Timeline            timeline;


    // ******************** Constructors **************************************
    public GaugeSkin(Gauge gauge) {
        super(gauge);
        pane      = new Pane();
        angleStep = 0;
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
        background = new Region();
        background.getStyleClass().setAll("background");

        ticks = RegionBuilder.create().styleClass("ticks").build();

        needle = new Region();
        needle.getStyleClass().setAll(Gauge.STYLE_CLASS_NEEDLE_STANDARD);
        needleRotate = new Rotate(-getSkinnable().getStartAngle());
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
        title.getStyleClass().setAll("title");

        unit = new Text(getSkinnable().getUnit());
        unit.getStyleClass().setAll("unit");

        // Add all nodes
        pane.getChildren().setAll(background, ticks, title, unit, shadowGroup);

        getChildren().setAll(pane);
    }

    private void registerListeners() {
        getSkinnable().widthProperty().addListener(observable -> { handleControlPropertyChanged("RESIZE"); });
        getSkinnable().heightProperty().addListener(observable -> { handleControlPropertyChanged("RESIZE"); });
        getSkinnable().valueProperty().addListener(observable -> { handleControlPropertyChanged("VALUE"); });
        getSkinnable().minValueProperty().addListener(observable -> { handleControlPropertyChanged("MIN_VALUE"); });
        getSkinnable().maxValueProperty().addListener(observable -> { handleControlPropertyChanged("MAX_VALUE"); });
        getSkinnable().minMeasuredValueProperty().addListener(observable -> { handleControlPropertyChanged("MIN_MEASURED_VALUE"); });
        getSkinnable().maxMeasuredValueProperty().addListener(observable -> { handleControlPropertyChanged("MAX_MEASURED_VALUE"); });
        getSkinnable().tickLabelOrientationProperty().addListener(observable -> { handleControlPropertyChanged("TICK_LABEL_ORIENTATION"); });
        getSkinnable().needleTypeProperty().addListener(observable -> { handleControlPropertyChanged("NEEDLE_TYPE"); });
        getSkinnable().needleColorProperty().addListener(observable -> { handleControlPropertyChanged("NEEDLE_COLOR"); });
        getSkinnable().animatedProperty().addListener(observable -> { handleControlPropertyChanged("ANIMATED"); });
        getSkinnable().angleRangeProperty().addListener(observable -> { handleControlPropertyChanged("ANGLE_RANGE"); });
        getSkinnable().numberFormatProperty().addListener(observable -> { handleControlPropertyChanged("NUMBER_FORMAT"); });
    }


    // ******************** Methods *******************************************
    protected void handleControlPropertyChanged(final String PROPERTY) {
        if ("RESIZE".equals(PROPERTY)) {
            resize();
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
        double range          = (getSkinnable().getMaxValue() - getSkinnable().getMinValue());
        double angleRange     = getSkinnable().getAngleRange();
        double rotationOffset = getSkinnable().getStartAngle();
        angleStep             = angleRange / range;
        double targetAngle    = getSkinnable().getValue() * angleStep - rotationOffset;

        if (getSkinnable().isAnimated()) {
            timeline.stop();
            final KeyValue KEY_VALUE = new KeyValue(needleRotate.angleProperty(), targetAngle, Interpolator.SPLINE(0.5, 0.4, 0.4, 1.0));
            final KeyFrame KEY_FRAME = new KeyFrame(Duration.millis(800), KEY_VALUE);
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

    private void resize() {
        size = getSkinnable().getWidth() < getSkinnable().getHeight() ? getSkinnable().getWidth() : getSkinnable().getHeight();

        double emptySegmentHeight = size * (1.0 - Math.cos(Math.toRadians((360 - getSkinnable().getAngleRange()) * 0.5)));
        background.setPrefSize(size, size);

        switch (getSkinnable().getNeedleType()) {
            default:
                needle.setPrefSize(size * 0.04, size * 0.45);
        }
        needle.setTranslateX((size - needle.getPrefWidth()) * 0.5);
        needle.setTranslateY(size * 0.5 - needle.getPrefHeight());
        needleRotate.setPivotX((needle.getPrefWidth()) * 0.5);
        needleRotate.setPivotY(needle.getPrefHeight());

        needleHighlight.setPrefSize(size * 0.04, size * 0.45);
        needleHighlight.setTranslateX((size - needle.getPrefWidth()) * 0.5);
        needleHighlight.setTranslateY(size * 0.5 - needle.getPrefHeight());

        knob.setPrefSize(size * 0.085, size * 0.085);
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
