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

package eu.hansolo.enzo.clock.skin;

import eu.hansolo.enzo.clock.Clock;
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.control.SkinBase;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadowBuilder;
import javafx.scene.effect.InnerShadowBuilder;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * User: hansolo
 * Date: 31.10.12
 * Time: 14:18
 */
public class ClockSkin extends SkinBase<Clock> {
    private static final long           INTERVAL       = 20000000l;
    private static final int            PREFERRED_SIZE = 200;
    private Clock                       control;
    private Pane                        pane;
    private String                      nightDayStyleClass;
    private Region                      background;
    private Region                      hourPointer;
    private Region                      minutePointer;
    private Region                      secondPointer;
    private Region                      centerKnob;
    private Region                      foreground;
    private double                      size;
    private double                      hourPointerWidthFactor;
    private double                      hourPointerHeightFactor;
    private double                      minutePointerWidthFactor;
    private double                      minutePointerHeightFactor;
    private double                      secondPointerWidthFactor;
    private double                      secondPointerHeightFactor;
    private double                      majorTickWidthFactor;
    private double                      majorTickHeightFactor;
    private double                      minorTickWidthFactor;
    private double                      minorTickHeightFactor;
    private double                      majorTickOffset;
    private double                      minorTickOffset;
    private Rotate                      hourAngle;
    private Rotate                      minuteAngle;
    private Rotate                      secondAngle;
    private List<Region>                ticks;
    private Group                       tickMarkGroup;
    private Group                       pointerGroup;
    private Group                       secondPointerGroup;
    private DoubleProperty              currentMinuteAngle;
    private DoubleProperty              minute;
    private Timeline                    timeline;
    private long                        lastTimerCall;
    private AnimationTimer              timer;


    // ******************** Constructors **************************************
    public ClockSkin(final Clock CONTROL) {
        super(CONTROL);
        control = CONTROL;
        pane    = new Pane();

        nightDayStyleClass        = control.isNightMode() ? "night-mode" : "day-mode";

        hourPointerWidthFactor    = 0.04;
        hourPointerHeightFactor   = 0.55;
        minutePointerWidthFactor  = 0.04;
        minutePointerHeightFactor = 0.4;
        secondPointerWidthFactor  = 0.075;
        secondPointerHeightFactor = 0.46;

        majorTickWidthFactor  = 0.04;
        majorTickHeightFactor = 0.12;
        minorTickWidthFactor  = 0.01;
        minorTickHeightFactor = 0.05;

        majorTickOffset = 0.018;
        minorTickOffset = 0.05;

        minute             = new SimpleDoubleProperty(0);
        currentMinuteAngle = new SimpleDoubleProperty(0);

        hourAngle = new Rotate();
        hourAngle.angleProperty().bind(currentMinuteAngle);
        minuteAngle = new Rotate();
        secondAngle = new Rotate();

        ticks = new ArrayList<>(60);

        timeline = new Timeline();
        timer = new AnimationTimer() {
            @Override public void handle(final long NOW) {
                if (NOW >= lastTimerCall + INTERVAL) {
                    // Seconds
                    secondAngle.setAngle(Calendar.getInstance().get(Calendar.SECOND) * 6 + Calendar.getInstance().get(Calendar.MILLISECOND) * 0.006);
                    // Minutes
                    minute.set((Calendar.getInstance().get(Calendar.MINUTE)) * 6);
                    // Hours
                    minuteAngle.setAngle((Calendar.getInstance().get(Calendar.HOUR)) * 30 + 0.5 * Calendar.getInstance().get(Calendar.MINUTE));
                    lastTimerCall = NOW;
                }
            }
        };
        minute.addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
                moveMinutePointer(newValue.doubleValue());
            }
        });

        init();
        initGraphics();
        registerListeners();
        timer.start();
    }


    // ******************** Initialization ************************************
    private void init() {
        if (control.getPrefWidth() <= 0 || control.getPrefHeight() <= 0 ||
            control.getPrefWidth() <= 0 || control.getHeight() <= 0) {
            control.setPrefSize(PREFERRED_SIZE, PREFERRED_SIZE);
        }

        if (control.getMinWidth() <= 0 || control.getMinHeight() <= 0 ||
            control.getPrefWidth() <= 0 || getHeight() <= 0) {
            control.setMinSize(50, 50);
        }

        if (control.getMaxWidth() <= 0 || control.getMaxHeight() <= 0 ||
            control.getPrefWidth() <= 0 || getHeight() <= 0) {
            control.setMaxSize(1024, 1024);
        }
    }

    private void initGraphics() {
        background = new Region();
        background.getStyleClass().setAll("background-ios6");

        for (double angle = 0 ; angle < 360 ; angle += 6) {
            Region tick = new Region();
            if (angle % 30 == 0) {
                tick.getStyleClass().setAll("major-tick-ios6");
            } else {
                tick.getStyleClass().setAll("minor-tick-ios6");
            }
            ticks.add(tick);
        }

        tickMarkGroup = new Group();
        tickMarkGroup.setEffect(DropShadowBuilder.create()
                                             .color(Color.rgb(0, 0, 0, 0.65))
                                             .radius(1.5)
                                             .blurType(BlurType.GAUSSIAN)
                                             .offsetY(1)
                                             .build());
        tickMarkGroup.getChildren().setAll(ticks);

        hourPointer = new Region();
        hourPointer.getStyleClass().setAll("hour-pointer-ios6");
        hourPointer.getTransforms().setAll(hourAngle);

        minutePointer = new Region();
        minutePointer.getStyleClass().setAll("minute-pointer-ios6");
        minutePointer.getTransforms().setAll(minuteAngle);

        pointerGroup = new Group();
        pointerGroup.setEffect(DropShadowBuilder.create()
                                                .color(Color.rgb(0, 0, 0, 0.45))
                                                .radius(12)
                                                .blurType(BlurType.GAUSSIAN)
                                                .offsetY(6)
                                                .build());
        pointerGroup.getChildren().setAll(minutePointer, hourPointer);

        secondPointer = new Region();
        secondPointer.getStyleClass().setAll("second-pointer-ios6");
        secondPointer.getTransforms().setAll(secondAngle);

        secondPointerGroup = new Group();
        secondPointerGroup.setEffect(DropShadowBuilder.create()
                                                      .color(Color.rgb(0, 0, 0, 0.45))
                                                      .radius(12)
                                                      .blurType(BlurType.GAUSSIAN)
                                                      .offsetY(6)
                                                      .input(InnerShadowBuilder.create()
                                                                               .color(Color.rgb(255, 255, 255, 0.3))
                                                                               .radius(1)
                                                                               .blurType(BlurType.GAUSSIAN)
                                                                               .offsetY(1)
                                                                               .input(InnerShadowBuilder.create()
                                                                                                        .color(Color.rgb(0, 0, 0, 0.3))
                                                                                                        .radius(1)
                                                                                                        .blurType(BlurType.GAUSSIAN)
                                                                                                        .offsetY(-1)
                                                                                                        .build())
                                                                               .build())
                                                      .build());
        secondPointerGroup.getChildren().setAll(secondPointer);

        centerKnob = new Region();
        centerKnob.getStyleClass().setAll("center-knob-ios6");

        foreground = new Region();
        foreground.getStyleClass().setAll("foreground-ios6");

        pane.getChildren().setAll(background, tickMarkGroup, pointerGroup, secondPointerGroup, centerKnob, foreground);

        getChildren().setAll(pane);

        updateDesign();
    }

    private void registerListeners() {
        registerChangeListener(control.widthProperty(), "RESIZE");
        registerChangeListener(control.heightProperty(), "RESIZE");
        registerChangeListener(control.nightModeProperty(), "DESIGN");
        registerChangeListener(control.designProperty(), "DESIGN");
    }


    // ******************** Methods *******************************************
    @Override protected void handleControlPropertyChanged(final String PROPERTY) {
        super.handleControlPropertyChanged(PROPERTY);
        if ("RESIZE".equals(PROPERTY)) {
            resize();
        } else if ("DESIGN".equals(PROPERTY)) {
            updateDesign();
        }
    }

    @Override public final void dispose() {
        control = null;
    }

    @Override protected double computePrefWidth(final double PREF_HEIGHT) {
        double prefHeight = PREFERRED_SIZE;
        if (PREF_HEIGHT != -1) {
            prefHeight = Math.max(control.getMinHeight(), PREF_HEIGHT - getInsets().getTop() - getInsets().getBottom());
        }
        return super.computePrefWidth(prefHeight);
    }

    @Override protected double computePrefHeight(final double PREF_WIDTH) {
        double prefWidth = PREFERRED_SIZE;
        if (PREF_WIDTH != -1) {
            prefWidth = Math.max(control.getMinWidth(), PREF_WIDTH - getInsets().getRight() - getInsets().getLeft());
        }
        return super.computePrefWidth(prefWidth);
    }

    @Override protected double computeMinWidth(final double MIN_HEIGHT) {
        return super.computeMinWidth(Math.max(20, MIN_HEIGHT - getInsets().getTop() - getInsets().getBottom()));
    }

    @Override protected double computeMinHeight(final double MIN_WIDTH) {
        return super.computeMinHeight(Math.max(20, MIN_WIDTH - getInsets().getRight() - getInsets().getLeft()));
    }

    @Override protected double computeMaxWidth(final double MAX_HEIGHT) {
        return super.computeMaxWidth(Math.min(1024, MAX_HEIGHT - getInsets().getTop() - getInsets().getBottom()));
    }

    @Override protected double computeMaxHeight(final double MAX_WIDTH) {
        return super.computeMaxHeight(Math.min(1024, MAX_WIDTH - getInsets().getRight() - getInsets().getLeft()));
    }

    private void updateDesign() {
        // Set day or night mode
        nightDayStyleClass = control.isNightMode() ? "night-mode" : "day-mode";

        // Set Styles for each component
        if (Clock.Design.IOS6 == control.getDesign()) {
            background.getStyleClass().setAll(nightDayStyleClass, "background-ios6");
            int index = 0;
            for (double angle = 0 ; angle < 360 ; angle += 6) {
                Region tick = ticks.get(index);
                if (angle % 30 == 0) {
                    tick.getStyleClass().setAll(nightDayStyleClass, "major-tick-ios6");
                } else {
                    tick.getStyleClass().setAll(nightDayStyleClass, "minor-tick-ios6");
                }
                ticks.add(tick);
                index++;
            }
            hourPointer.getStyleClass().setAll(nightDayStyleClass, "hour-pointer-ios6");
            minutePointer.getStyleClass().setAll(nightDayStyleClass, "minute-pointer-ios6");
            secondPointer.getStyleClass().setAll(nightDayStyleClass, "second-pointer-ios6");
            centerKnob.getStyleClass().setAll(nightDayStyleClass, "center-knob-ios6");
            foreground.getStyleClass().setAll(nightDayStyleClass, "foreground-ios6");
        } else {
            background.getStyleClass().setAll(nightDayStyleClass, "background-db");
            int index = 0;
            for (double angle = 0 ; angle < 360 ; angle += 6) {
                Region tick = ticks.get(index);
                if (angle % 30 == 0) {
                    tick.getStyleClass().setAll(nightDayStyleClass, "major-tick-db");
                } else {
                    tick.getStyleClass().setAll(nightDayStyleClass, "minor-tick-db");
                }
                ticks.add(tick);
                index++;
            }
            hourPointer.getStyleClass().setAll(nightDayStyleClass, "hour-pointer-db");
            minutePointer.getStyleClass().setAll(nightDayStyleClass, "minute-pointer-db");
            secondPointer.getStyleClass().setAll(nightDayStyleClass, "second-pointer-db");
            centerKnob.getStyleClass().setAll(nightDayStyleClass, "center-knob-db");
            foreground.getStyleClass().setAll(nightDayStyleClass, "foreground-db");
        }
        resize();
    }

    private void resize() {
        size = control.getWidth() < control.getHeight() ? control.getWidth() : control.getHeight();

        background.setPrefSize(size, size);

        if (Clock.Design.IOS6 == control.getDesign()) {
            hourPointerWidthFactor    = 0.04;
            hourPointerHeightFactor   = 0.55;
            minutePointerWidthFactor  = 0.04;
            minutePointerHeightFactor = 0.4;
            secondPointerWidthFactor  = 0.075;
            secondPointerHeightFactor = 0.46;
            majorTickWidthFactor      = 0.04;
            majorTickHeightFactor     = 0.12;
            minorTickWidthFactor      = 0.01;
            minorTickHeightFactor     = 0.05;
            majorTickOffset           = 0.018;
            minorTickOffset           = 0.05;
            hourAngle.setPivotX(size * 0.5 * hourPointerWidthFactor);
            hourAngle.setPivotY(size * 0.76 * hourPointerHeightFactor);
            minuteAngle.setPivotX(size * 0.5 * minutePointerWidthFactor);
            minuteAngle.setPivotY(size * 0.66 * minutePointerHeightFactor);
            secondAngle.setPivotX(size * 0.5 * secondPointerWidthFactor);
            secondAngle.setPivotY(size * 0.7341040462 * secondPointerHeightFactor);
        } else {
            hourPointerWidthFactor    = 0.04;
            hourPointerHeightFactor   = 0.47;
            minutePointerWidthFactor  = 0.055;
            minutePointerHeightFactor = 0.33;
            secondPointerWidthFactor  = 0.1;
            secondPointerHeightFactor = 0.455;
            majorTickWidthFactor      = 0.04;
            majorTickHeightFactor     = 0.12;
            minorTickWidthFactor      = 0.025;
            minorTickHeightFactor     = 0.04;
            majorTickOffset           = 0.018;
            minorTickOffset           = 0.06;
            hourAngle.setPivotX(size * 0.5 * hourPointerWidthFactor);
            hourAngle.setPivotY(size * hourPointerHeightFactor);
            minuteAngle.setPivotX(size * 0.5 * minutePointerWidthFactor);
            minuteAngle.setPivotY(size * minutePointerHeightFactor);
            secondAngle.setPivotX(size * 0.5 * secondPointerWidthFactor);
            secondAngle.setPivotY(size * secondPointerHeightFactor);
        }

        double radius = 0.4;
        double sinValue;
        double cosValue;
        int index = 0;
        for (double angle = 0 ; angle < 360 ; angle += 6) {
            sinValue = Math.sin(Math.toRadians(angle));
            cosValue = Math.cos(Math.toRadians(angle));
            Region tick = ticks.get(index);
            if (angle % 30 == 0) {
                tick.setPrefWidth(size * majorTickWidthFactor);
                tick.setPrefHeight(size * majorTickHeightFactor);
                tick.setTranslateX(size * 0.5 + ((size * (radius + majorTickOffset) * sinValue) - (size * (majorTickWidthFactor) * 0.5)));
                tick.setTranslateY(size * 0.5 + ((size * (radius + majorTickOffset) * cosValue) - (size * (majorTickHeightFactor) * 0.5)));
            } else {
                tick.setPrefWidth(size * minorTickWidthFactor);
                tick.setPrefHeight(size * minorTickHeightFactor);
                tick.setTranslateX(size * 0.5 + ((size * (radius + minorTickOffset) * sinValue) - (size * (minorTickWidthFactor) * 0.5)));
                tick.setTranslateY(size * 0.5 + ((size * (radius + minorTickOffset) * cosValue) - (size * (minorTickHeightFactor) * 0.5)));
            }
            tick.setRotate(-angle);
            index++;
        }

        hourPointer.setPrefWidth(size * hourPointerWidthFactor);
        hourPointer.setPrefHeight(size * hourPointerHeightFactor);
        if (Clock.Design.IOS6 == control.getDesign()) {
            hourPointer.setTranslateX(size * 0.5 - (hourPointer.getPrefWidth() *0.5));
            hourPointer.setTranslateY(size * 0.5 - (hourPointer.getPrefHeight()) + (hourPointer.getPrefHeight() * 0.24));
        } else {
            hourPointer.setTranslateX(size * 0.5 - (hourPointer.getPrefWidth() * 0.5));
            hourPointer.setTranslateY(size * 0.5 - hourPointer.getPrefHeight());
        }

        minutePointer.setPrefWidth(size * minutePointerWidthFactor);
        minutePointer.setPrefHeight(size * minutePointerHeightFactor);
        if (Clock.Design.IOS6 == control.getDesign()) {
            minutePointer.setTranslateX(size * 0.5 - (minutePointer.getPrefWidth() * 0.5));
            minutePointer.setTranslateY(size * 0.5 - (minutePointer.getPrefHeight()) + (minutePointer.getPrefHeight() * 0.34));
        } else {
            minutePointer.setTranslateX(size * 0.5 - (minutePointer.getPrefWidth() * 0.5));
            minutePointer.setTranslateY(size * 0.5 - minutePointer.getPrefHeight());
        }

        secondPointer.setPrefWidth(size * secondPointerWidthFactor);
        secondPointer.setPrefHeight(size * secondPointerHeightFactor);
        if (Clock.Design.IOS6 == control.getDesign()) {
            secondPointer.setTranslateX(size * 0.5 - (secondPointer.getPrefWidth() * 0.5));
            secondPointer.setTranslateY(size * 0.5 - (secondPointer.getPrefHeight()) + (secondPointer.getPrefHeight() * 0.2658959538));
        } else {
            secondPointer.setTranslateX(size * 0.5 - (secondPointer.getPrefWidth() * 0.5));
            secondPointer.setTranslateY(size * 0.5 - secondPointer.getPrefHeight());
        }

        if (Clock.Design.IOS6 == control.getDesign()) {
            centerKnob.setPrefWidth(size * 0.015);
            centerKnob.setPrefHeight(size * 0.015);
        } else {
            centerKnob.setPrefWidth(size * 0.1);
            centerKnob.setPrefHeight(size * 0.1);
        }
        centerKnob.setTranslateX(size * 0.5 - (centerKnob.getPrefWidth() * 0.5));
        centerKnob.setTranslateY(size * 0.5 - (centerKnob.getPrefHeight() * 0.5));

        foreground.setPrefWidth(size * 0.955);
        foreground.setPrefHeight(size * 0.495);
        foreground.setTranslateX(size * 0.5 - (foreground.getPrefWidth() * 0.5));
        foreground.setTranslateY(size * 0.01);
    }


    // ******************** Drawing related ***********************************
    private void moveMinutePointer(double newAngle) {
        final KeyValue kv = new KeyValue(currentMinuteAngle, newAngle, Interpolator.SPLINE(0.5, 0.4, 0.4, 1.0));
        final KeyFrame kf = new KeyFrame(Duration.millis(200), kv);
        timeline  = new Timeline();
        timeline.getKeyFrames().add(kf);
        timeline.play();
    }
}
