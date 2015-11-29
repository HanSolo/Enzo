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

package eu.langhammer.enzo2.clock.skin;

import eu.langhammer.enzo2.clock.Clock;
import eu.langhammer.enzo2.common.Fonts;
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;


/**
 * User: hansolo
 * Date: 31.10.12
 * Time: 14:18
 */
public class ClockSkin extends SkinBase<Clock> implements Skin<Clock> {
    private static final long   INTERVAL         = 20_000_000l;
    private static final double PREFERRED_WIDTH  = 200;
    private static final double PREFERRED_HEIGHT = 200;
    private static final double MINIMUM_WIDTH    = 50;
    private static final double MINIMUM_HEIGHT   = 50;
    private static final double MAXIMUM_WIDTH    = 1024;
    private static final double MAXIMUM_HEIGHT   = 1024;
    private Pane                pane;
    private String              nightDayStyleClass;
    private Region              background;
    private Canvas              logoLayer;
    private GraphicsContext     ctx;
    private Region              hourPointer;
    private Region              hourPointerFlour;
    private Region              minutePointer;
    private Region              minutePointerFlour;
    private Region              secondPointer;
    private Region              centerKnob;
    private Region              foreground;
    private double              size;
    private double              hourPointerWidthFactor;
    private double              hourPointerHeightFactor;
    private double              minutePointerWidthFactor;
    private double              minutePointerHeightFactor;
    private double              secondPointerWidthFactor;
    private double              secondPointerHeightFactor;
    private double              majorTickWidthFactor;
    private double              majorTickHeightFactor;
    private double              minorTickWidthFactor;
    private double              minorTickHeightFactor;
    private double              majorTickOffset;
    private double              minorTickOffset;
    private Rotate              hourAngle;
    private Rotate              minuteAngle;
    private Rotate              secondAngle;
    private List<Region>        ticks;
    private List<Text>          tickLabels;
    private Group               tickMarkGroup;
    private Group               tickLabelGroup;
    private Group               pointerGroup;
    private Group               secondPointerGroup;
    private Font                tickLabelFont;
    private DoubleProperty      currentMinuteAngle;
    private DoubleProperty      minute;
    private Timeline            timeline;
    private long                lastTimerCall;
    private AnimationTimer      timer;


    // ******************** Constructors **************************************
    public ClockSkin(final Clock CONTROL) {
        super(CONTROL);
        nightDayStyleClass        = getSkinnable().isNightMode() ? "night-mode" : "day-mode";

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

        //tickLabelFont      = Font.loadFont(getClass().getResourceAsStream("/eu/hansolo/enzo/fonts/helvetica.ttf"), 12);
        tickLabelFont      = Font.loadFont(getClass().getResourceAsStream("/eu/hansolo/enzo/fonts/bebasneue.ttf"), 12);
        minute             = new SimpleDoubleProperty(0);
        currentMinuteAngle = new SimpleDoubleProperty(0);

        hourAngle = new Rotate();
        hourAngle.angleProperty().bind(currentMinuteAngle);
        minuteAngle = new Rotate();
        secondAngle = new Rotate();

        ticks      = new ArrayList<>(60);
        tickLabels = new ArrayList<>(12);

        timeline = new Timeline();
        timer = new AnimationTimer() {
            @Override public void handle(final long NOW) {
                if (NOW >= lastTimerCall + INTERVAL) {
                    updateTime(LocalDateTime.now().plus(getSkinnable().getOffset()));                    
                    lastTimerCall = NOW;
                }
            }
        };
        minute.addListener(observable -> moveMinutePointer(minute.get()) );

        init();
        initGraphics();
        registerListeners();
        if (getSkinnable().isRunning()) {
            timer.start();
        } else {
            updateTime(LocalDateTime.now().plus(getSkinnable().getOffset()));
        }
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
        pane    = new Pane();

        background = new Region();
        if (Clock.Design.IOS6 == getSkinnable().getDesign()) {
            background.getStyleClass().setAll("background-ios6");
        } else if (Clock.Design.DB == getSkinnable().getDesign()) {
            background.getStyleClass().setAll("background-db");
        } else if (Clock.Design.BRAUN == getSkinnable().getDesign()) {
            background.getStyleClass().setAll("background-braun");
        } else if (Clock.Design.BOSCH == getSkinnable().getDesign()) {
            background.getStyleClass().setAll("background-bosch");
        }

        logoLayer = new Canvas(PREFERRED_WIDTH, PREFERRED_HEIGHT);
        ctx       = logoLayer.getGraphicsContext2D();

        String majorTickStyleClass;
        String minorTickStyleClass;
        if (Clock.Design.IOS6 == getSkinnable().getDesign()) {
            majorTickStyleClass = "major-tick-ios6";
            minorTickStyleClass = "minor-tick-ios6";
        } else if (Clock.Design.DB == getSkinnable().getDesign()) {
            majorTickStyleClass = "major-tick-db";
            minorTickStyleClass = "minor-tick-db";
        } else if (Clock.Design.BOSCH == getSkinnable().getDesign()) {
            majorTickStyleClass = "major-tick-bosch";
            minorTickStyleClass = "minor-tick-bosch";
        } else {
            majorTickStyleClass = "major-tick-braun";
            minorTickStyleClass = "minor-tick-braun";
        }

        int tickLabelCounter = 1;
        for (double angle = 0 ; angle < 360 ; angle += 6) {
            Region tick = new Region();
            if (angle % 30 == 0) {
                tick.getStyleClass().setAll(majorTickStyleClass);
                Text tickLabel = new Text(Integer.toString(tickLabelCounter));
                tickLabel.getStyleClass().setAll("tick-label-braun");
                tickLabels.add(tickLabel);
                tickLabelCounter++;
            } else {
                tick.getStyleClass().setAll(minorTickStyleClass);
            }
            ticks.add(tick);
        }

        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.rgb(0, 0, 0, 0.65));
        dropShadow.setRadius(1.5);
        dropShadow.setBlurType(BlurType.TWO_PASS_BOX);
        dropShadow.setOffsetY(1);

        tickMarkGroup = new Group();
        tickMarkGroup.setEffect(dropShadow);
        tickMarkGroup.getChildren().setAll(ticks);

        tickLabelGroup = new Group();
        tickLabelGroup.setEffect(dropShadow);
        tickLabelGroup.getChildren().setAll(tickLabels);
        tickLabelGroup.setOpacity(Clock.Design.BRAUN == getSkinnable().getDesign() ? 1 : 0);

        hourPointer = new Region();
        if (Clock.Design.IOS6 == getSkinnable().getDesign()) {
            hourPointer.getStyleClass().setAll("hour-pointer-ios6");
        } else if (Clock.Design.DB == getSkinnable().getDesign()) {
            hourPointer.getStyleClass().setAll("hour-pointer-db");
        } else if (Clock.Design.BRAUN == getSkinnable().getDesign()) {
            hourPointer.getStyleClass().setAll("hour-pointer-braun");
        } else if (Clock.Design.BOSCH == getSkinnable().getDesign()) {
            hourPointer.getStyleClass().setAll("hour-pointer-bosch");
        }
        hourPointer.getTransforms().setAll(hourAngle);

        hourPointerFlour = new Region();
        hourPointerFlour.getStyleClass().setAll("hour-pointer-braun-flour");
        if (Clock.Design.BRAUN == getSkinnable().getDesign()) {
            hourPointerFlour.setOpacity(1);
        } else {
            hourPointerFlour.setOpacity(0);
        }
        hourPointerFlour.getTransforms().setAll(hourAngle);

        minutePointer = new Region();
        if (Clock.Design.IOS6 == getSkinnable().getDesign()) {
            minutePointer.getStyleClass().setAll("minute-pointer-ios6");
        } else if (Clock.Design.DB == getSkinnable().getDesign()) {
            minutePointer.getStyleClass().setAll("minute-pointer-db");
        } else if (Clock.Design.BRAUN == getSkinnable().getDesign()) {
            minutePointer.getStyleClass().setAll("minute-pointer-braun");
        } else if (Clock.Design.BOSCH == getSkinnable().getDesign()) {
            minutePointer.getStyleClass().setAll("minute-pointer-bosch");
        }
        minutePointer.getTransforms().setAll(minuteAngle);

        minutePointerFlour = new Region();
        minutePointerFlour.getStyleClass().setAll("minute-pointer-braun-flour");
        if (Clock.Design.BRAUN == getSkinnable().getDesign()) {
            minutePointerFlour.setOpacity(1);
        } else {
            minutePointerFlour.setOpacity(0);
        }
        minutePointerFlour.getTransforms().setAll(minuteAngle);

        DropShadow pointerShadow = new DropShadow();
        pointerShadow.setColor(Color.rgb(0, 0, 0, 0.45));
        pointerShadow.setRadius(12);
        pointerShadow.setBlurType(BlurType.TWO_PASS_BOX);
        pointerShadow.setOffsetY(6);

        pointerGroup = new Group();
        pointerGroup.setEffect(pointerShadow);
        pointerGroup.getChildren().setAll(minutePointerFlour, minutePointer, hourPointerFlour, hourPointer);

        secondPointer = new Region();
        secondPointer.setOpacity(1);
        if (Clock.Design.IOS6 == getSkinnable().getDesign()) {
            secondPointer.getStyleClass().setAll("second-pointer-ios6");
        } else if (Clock.Design.DB == getSkinnable().getDesign()) {
            secondPointer.getStyleClass().setAll("second-pointer-db");
        } else if (Clock.Design.BRAUN == getSkinnable().getDesign()) {
            secondPointer.getStyleClass().setAll("second-pointer-braun");
        } else if (Clock.Design.BOSCH == getSkinnable().getDesign()) {
            secondPointer.setOpacity(0);
        }
        secondPointer.getTransforms().setAll(secondAngle);

        InnerShadow secondPointerInnerShadow = new InnerShadow();
        secondPointerInnerShadow.setColor(Color.rgb(0, 0, 0, 0.3));
        secondPointerInnerShadow.setRadius(1);
        secondPointerInnerShadow.setBlurType(BlurType.TWO_PASS_BOX);
        secondPointerInnerShadow.setOffsetY(-1);

        InnerShadow secondPointerInnerHighlight = new InnerShadow();
        secondPointerInnerHighlight.setColor(Color.rgb(255, 255, 255, 0.3));
        secondPointerInnerHighlight.setRadius(1);
        secondPointerInnerHighlight.setBlurType(BlurType.TWO_PASS_BOX);
        secondPointerInnerHighlight.setOffsetY(1);
        secondPointerInnerHighlight.setInput(secondPointerInnerShadow);

        DropShadow secondPointerShadow = new DropShadow();
        secondPointerShadow.setColor(Color.rgb(0, 0, 0, 0.45));
        secondPointerShadow.setRadius(12);
        secondPointerShadow.setBlurType(BlurType.TWO_PASS_BOX);
        secondPointerShadow.setOffsetY(6);
        secondPointerShadow.setInput(secondPointerInnerHighlight);

        secondPointerGroup = new Group();
        secondPointerGroup.setEffect(secondPointerShadow);
        secondPointerGroup.getChildren().setAll(secondPointer);
        secondPointerGroup.setOpacity(getSkinnable().isSecondPointerVisible() ? 1 : 0);

        centerKnob = new Region();
        if (Clock.Design.IOS6 == getSkinnable().getDesign()) {
            centerKnob.getStyleClass().setAll("center-knob-ios6");
        } else if (Clock.Design.DB == getSkinnable().getDesign()) {
            centerKnob.getStyleClass().setAll("center-knob-db");
        } else if (Clock.Design.BRAUN == getSkinnable().getDesign()) {
            centerKnob.getStyleClass().setAll("center-knob-braun");
        } else if (Clock.Design.BOSCH == getSkinnable().getDesign()) {
            centerKnob.getStyleClass().setAll("center-knob-bosch");
        }

        foreground = new Region();
        if (Clock.Design.IOS6 == getSkinnable().getDesign()) {
            foreground.getStyleClass().setAll("foreground-ios6");
        } else if (Clock.Design.DB == getSkinnable().getDesign()) {
            foreground.getStyleClass().setAll("foreground-db");
        } else if (Clock.Design.BRAUN == getSkinnable().getDesign()) {
            foreground.getStyleClass().setAll("foreground-braun");
        } else if (Clock.Design.BOSCH == getSkinnable().getDesign()) {
            foreground.getStyleClass().setAll("foreground-bosch");
        }
        foreground.setOpacity(getSkinnable().isHighlightVisible() ? 1 : 0);

        pane.getChildren().setAll(background, logoLayer, tickMarkGroup, tickLabelGroup, pointerGroup, secondPointerGroup, centerKnob, foreground);

        getChildren().setAll(pane);

        updateDesign();
    }

    private void registerListeners() {
        getSkinnable().widthProperty().addListener(observable -> handleControlPropertyChanged("RESIZE") );
        getSkinnable().heightProperty().addListener(observable -> handleControlPropertyChanged("RESIZE") );
        getSkinnable().secondPointerVisibleProperty().addListener(observable -> handleControlPropertyChanged("SECOND_POINTER_VISIBLE") );
        getSkinnable().nightModeProperty().addListener(observable -> handleControlPropertyChanged("DESIGN") );
        getSkinnable().designProperty().addListener(observable -> handleControlPropertyChanged("DESIGN") );
        getSkinnable().highlightVisibleProperty().addListener(observable -> handleControlPropertyChanged("DESIGN") );
        getSkinnable().dateTimeProperty().addListener(observable -> handleControlPropertyChanged("DATE_TIME"));
        getSkinnable().runningProperty().addListener(observable -> handleControlPropertyChanged("RUNNING"));
    }


    // ******************** Methods *******************************************
    protected void handleControlPropertyChanged(final String PROPERTY) {
        if ("RESIZE".equals(PROPERTY)) {
            resize();
        } else if ("DESIGN".equals(PROPERTY)) {
            updateDesign();
        } else if ("SECOND_POINTER_VISIBLE".equals(PROPERTY)) {
            secondPointerGroup.setOpacity(getSkinnable().isSecondPointerVisible() ? 1 : 0);
        } else if ("DATE_TIME".equals(PROPERTY)) {
            if (getSkinnable().isRunning()) updateTime(getSkinnable().getDateTime().plus(getSkinnable().getOffset()));    
        } else if ("RUNNING".equals(PROPERTY)) {
            if (getSkinnable().isRunning()) {
                timer.start();
            } else {
                timer.stop();                
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

    private void updateTime(final LocalDateTime DATE_TIME) {                
        // Seconds
        if (getSkinnable().isDiscreteSecond()) {
            secondAngle.setAngle(DATE_TIME.getSecond() * 6);
        } else {
            secondAngle.setAngle(DATE_TIME.getSecond() * 6 + DATE_TIME.get(ChronoField.MILLI_OF_SECOND) * 0.006);
        }
        // Minutes
        minute.set(DATE_TIME.getMinute() * 6);
        // Hours
        minuteAngle.setAngle(DATE_TIME.getHour() * 30 + 0.5 * DATE_TIME.getMinute());
        
        if (getSkinnable().isAutoNightMode()) checkForNight(DATE_TIME);
    }
    
    private void checkForNight(final LocalDateTime DATE_TIME) {
        int hour   = DATE_TIME.getHour();
        int minute = DATE_TIME.getMinute();
        
        if (0 <= hour && minute >= 0 && hour <= 5 && minute <= 59|| 17 <= hour && minute <= 59 && hour <= 23 && minute <= 59) {
            getSkinnable().setNightMode(true);
        } else {
            getSkinnable().setNightMode(false);
        }
    }
    
    private void drawLogoLayer() {
        ctx.clearRect(0, 0, size, size);
        if (Clock.Design.BOSCH == getSkinnable().getDesign()) {
            ctx.setFill(getSkinnable().isNightMode() ? Color.rgb(240, 240, 240) : Color.rgb(10, 10, 10));
            ctx.fillRect(size * 0.5 - 1, size * 0.18, 2, size * 0.27);
            ctx.fillRect(size * 0.5 - 1, size * 0.55, 2, size * 0.27);
            ctx.fillRect(size * 0.18, size * 0.5 - 1, size * 0.27, 2);
            ctx.fillRect(size * 0.55, size * 0.5 - 1, size * 0.27, 2);
        }
        if (getSkinnable().getText().isEmpty()) return;
        ctx.setFill(getSkinnable().isNightMode() ? Color.WHITE : Color.BLACK);
        ctx.setFont(Fonts.opensansSemiBold(size * 0.05));
        ctx.setTextBaseline(VPos.CENTER);
        ctx.setTextAlign(TextAlignment.CENTER);        
        ctx.fillText(getSkinnable().getText(), size * 0.5, size * 0.675, size * 0.8);
    }

    private void updateDesign() {
        // Set day or night mode
        nightDayStyleClass = getSkinnable().isNightMode() ? "night-mode" : "day-mode";
        // Set Styles for each component
        if (Clock.Design.IOS6 == getSkinnable().getDesign()) {
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
        } else if (Clock.Design.BRAUN == getSkinnable().getDesign()) {
            nightDayStyleClass = getSkinnable().isNightMode() ? "night-mode-braun" : "day-mode-braun";
            background.getStyleClass().setAll(nightDayStyleClass, "background-braun");
            int index = 0;
            for (double angle = 0 ; angle < 360 ; angle += 6) {
                if (angle % 30 == 0) {
                    ticks.get(index).getStyleClass().setAll(nightDayStyleClass, "major-tick-braun");
                } else {
                    ticks.get(index).getStyleClass().setAll(nightDayStyleClass, "minor-tick-braun");
                }
                index++;
            }
            for (index = 0 ; index < 12 ; index++) {
                tickLabels.get(index).getStyleClass().setAll(nightDayStyleClass, "tick-label-braun");
            }
            hourPointer.getStyleClass().setAll(nightDayStyleClass, "hour-pointer-braun");
            minutePointer.getStyleClass().setAll(nightDayStyleClass, "minute-pointer-braun");
            secondPointer.getStyleClass().setAll(nightDayStyleClass, "second-pointer-braun");
            centerKnob.getStyleClass().setAll(nightDayStyleClass, "center-knob-braun");
            foreground.getStyleClass().setAll(nightDayStyleClass, "foreground-braun");
        } else if (Clock.Design.BOSCH == getSkinnable().getDesign()) {
            nightDayStyleClass = getSkinnable().isNightMode() ? "night-mode-bosch" : "day-mode-bosch";
            background.getStyleClass().setAll(nightDayStyleClass, "background-bosch");
            int index = 0;
            for (double angle = 0 ; angle < 360 ; angle += 6) {
                Region tick = ticks.get(index);
                if (angle % 30 == 0) {
                    tick.getStyleClass().setAll(nightDayStyleClass, "major-tick-bosch");
                } else {
                    tick.getStyleClass().setAll(nightDayStyleClass, "minor-tick-bosch");
                }
                ticks.add(tick);
                index++;
            }
            hourPointer.getStyleClass().setAll(nightDayStyleClass, "hour-pointer-bosch");
            minutePointer.getStyleClass().setAll(nightDayStyleClass, "minute-pointer-bosch");
            secondPointer.getStyleClass().setAll(nightDayStyleClass, "second-pointer-bosch");
            centerKnob.getStyleClass().setAll(nightDayStyleClass, "center-knob-bosch");
            foreground.getStyleClass().setAll(nightDayStyleClass, "foreground-bosch");
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
        tickLabelGroup.setOpacity(Clock.Design.BRAUN == getSkinnable().getDesign() ? 1 : 0);
        foreground.setOpacity(getSkinnable().isHighlightVisible() ? 1 : 0);
        resize();
    }

    private void resize() {
        size = getSkinnable().getWidth() < getSkinnable().getHeight() ? getSkinnable().getWidth() : getSkinnable().getHeight();

        logoLayer.setWidth(size);
        logoLayer.setHeight(size);

        if (size > 0) {
            background.setPrefSize(size, size);

            // TODO: hourPointer and minutePointer have to be vice versa...wrong scaling here !!!

            if (Clock.Design.IOS6 == getSkinnable().getDesign()) {
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
            } else if (Clock.Design.BRAUN == getSkinnable().getDesign()) {
                hourPointerWidthFactor    = 0.105;
                hourPointerHeightFactor   = 0.485;
                minutePointerWidthFactor  = 0.105;
                minutePointerHeightFactor = 0.4;
                secondPointerWidthFactor  = 0.09;
                secondPointerHeightFactor = 0.53;
                majorTickWidthFactor      = 0.015;
                majorTickHeightFactor     = 0.045;
                minorTickWidthFactor      = 0.0075;
                minorTickHeightFactor     = 0.0225;
                majorTickOffset           = 0.012;
                minorTickOffset           = 0.02;
                hourAngle.setPivotX(size * 0.5 * hourPointerWidthFactor);
                hourAngle.setPivotY(size * 0.895 * hourPointerHeightFactor);
                minuteAngle.setPivotX(size * 0.5 * minutePointerWidthFactor);
                minuteAngle.setPivotY(size * 0.87 * minutePointerHeightFactor);
                secondAngle.setPivotX(size * 0.5 * secondPointerWidthFactor);
                secondAngle.setPivotY(size * 0.8125 * secondPointerHeightFactor);
            } else if (Clock.Design.BOSCH == getSkinnable().getDesign()) {
                hourPointerWidthFactor    = 0.04;
                hourPointerHeightFactor   = 0.54;  // ToDo: switch with minutePointerHeightFactor
                minutePointerWidthFactor  = 0.04;
                minutePointerHeightFactor = 0.38;  // ToDo: switch with hourPointerHeightFactor
                secondPointerWidthFactor  = 0.09;
                secondPointerHeightFactor = 0.53;
                majorTickWidthFactor      = 0.02;
                majorTickHeightFactor     = 0.145;
                minorTickWidthFactor      = 0.006;
                minorTickHeightFactor     = 0.07;
                majorTickOffset           = 0.005;
                minorTickOffset           = 0.04;
                hourAngle.setPivotX(size * 0.5 * hourPointerWidthFactor);
                hourAngle.setPivotY(size * 0.8240740741 * hourPointerHeightFactor);
                minuteAngle.setPivotX(size * 0.5 * minutePointerWidthFactor);
                minuteAngle.setPivotY(size * 0.75 * minutePointerHeightFactor);
                secondAngle.setPivotX(size * 0.5 * secondPointerWidthFactor);
                secondAngle.setPivotY(size * 0.8125 * secondPointerHeightFactor);
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

            drawLogoLayer();

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

            if (Clock.Design.BRAUN == getSkinnable().getDesign()) {
                int tickLabelCounter = 0;
                //tickLabelFont = Font.loadFont(getClass().getResourceAsStream("/eu/hansolo/enzo/fonts/helvetica.ttf"), (0.075 * size));
                tickLabelFont = Font.font("Bebas Neue", FontWeight.THIN, FontPosture.REGULAR, 0.09 * size);
                for (double angle = 0 ; angle < 360 ; angle += 30.0) {
                    double x = 0.34 * size * Math.sin(Math.toRadians(150 - angle));
                    double y = 0.34 * size * Math.cos(Math.toRadians(150 - angle));
                    tickLabels.get(tickLabelCounter).setFont(tickLabelFont);
                    tickLabels.get(tickLabelCounter).setX(size * 0.5 + x - tickLabels.get(tickLabelCounter).getLayoutBounds().getWidth() * 0.5);
                    tickLabels.get(tickLabelCounter).setY(size * 0.5 + y);
                    tickLabels.get(tickLabelCounter).setTextOrigin(VPos.CENTER);
                    tickLabels.get(tickLabelCounter).setTextAlignment(TextAlignment.CENTER);
                    tickLabelCounter++;
                }
            }

            hourPointer.setPrefSize(size * hourPointerWidthFactor, size * hourPointerHeightFactor);
            if (Clock.Design.IOS6 == getSkinnable().getDesign()) {
                hourPointer.setTranslateX(size * 0.5 - (hourPointer.getPrefWidth() * 0.5));
                hourPointer.setTranslateY(size * 0.5 - (hourPointer.getPrefHeight()) + (hourPointer.getPrefHeight() * 0.24));
            } else if (Clock.Design.BRAUN == getSkinnable().getDesign()) {
                hourPointer.setTranslateX(size * 0.5 - (hourPointer.getPrefWidth() * 0.5));
                hourPointer.setTranslateY(size * 0.5 - (hourPointer.getPrefHeight()) + (hourPointer.getPrefHeight() * 0.108));
                hourPointerFlour.setPrefSize(size * hourPointerWidthFactor, size * hourPointerHeightFactor);
                hourPointerFlour.setTranslateX(size * 0.5 - (hourPointer.getPrefWidth() * 0.5));
                hourPointerFlour.setTranslateY(size * 0.5 - (hourPointer.getPrefHeight()) + (hourPointer.getPrefHeight() * 0.108));
            } else if (Clock.Design.BOSCH == getSkinnable().getDesign()) {
                hourPointer.setTranslateX(size * 0.5 - (hourPointer.getPrefWidth() * 0.5));
                hourPointer.setTranslateY(size * 0.5 - (hourPointer.getPrefHeight()) + (hourPointer.getPrefHeight() * 0.1759259259));
            } else {
                hourPointer.setTranslateX(size * 0.5 - (hourPointer.getPrefWidth() * 0.5));
                hourPointer.setTranslateY(size * 0.5 - hourPointer.getPrefHeight());
            }

            minutePointer.setPrefSize(size * minutePointerWidthFactor, size * minutePointerHeightFactor);
            if (Clock.Design.IOS6 == getSkinnable().getDesign()) {
                minutePointer.setTranslateX(size * 0.5 - (minutePointer.getPrefWidth() * 0.5));
                minutePointer.setTranslateY(size * 0.5 - (minutePointer.getPrefHeight()) + (minutePointer.getPrefHeight() * 0.34));
            } else if (Clock.Design.BRAUN == getSkinnable().getDesign()) {
                minutePointer.setTranslateX(size * 0.5 - (minutePointer.getPrefWidth() * 0.5));
                minutePointer.setTranslateY(size * 0.5 - (minutePointer.getPrefHeight()) + (minutePointer.getPrefHeight() * 0.128));
                minutePointerFlour.setPrefSize(size * minutePointerWidthFactor, size * minutePointerHeightFactor);
                minutePointerFlour.setTranslateX(size * 0.5 - (minutePointer.getPrefWidth() * 0.5));
                minutePointerFlour.setTranslateY(size * 0.5 - (minutePointer.getPrefHeight()) + (minutePointer.getPrefHeight() * 0.128));
            } else if (Clock.Design.BOSCH == getSkinnable().getDesign()) {
                minutePointer.setTranslateX(size * 0.5 - (minutePointer.getPrefWidth() * 0.5));
                minutePointer.setTranslateY(size * 0.5 - (minutePointer.getPrefHeight()) + (minutePointer.getPrefHeight() * 0.25));
            } else {
                minutePointer.setTranslateX(size * 0.5 - (minutePointer.getPrefWidth() * 0.5));
                minutePointer.setTranslateY(size * 0.5 - minutePointer.getPrefHeight());
            }

            secondPointer.setPrefSize(size * secondPointerWidthFactor, size * secondPointerHeightFactor);
            if (Clock.Design.IOS6 == getSkinnable().getDesign()) {
                secondPointer.setTranslateX(size * 0.5 - (secondPointer.getPrefWidth() * 0.5));
                secondPointer.setTranslateY(size * 0.5 - (secondPointer.getPrefHeight()) + (secondPointer.getPrefHeight() * 0.2658959538));
            } else if (Clock.Design.BRAUN == getSkinnable().getDesign()) {
                secondPointer.setTranslateX(size * 0.5 - (secondPointer.getPrefWidth() * 0.5));
                secondPointer.setTranslateY(size * 0.5 - secondPointer.getPrefHeight() + (secondPointer.getPrefHeight() * 0.189));
            } else {
                secondPointer.setTranslateX(size * 0.5 - (secondPointer.getPrefWidth() * 0.5));
                secondPointer.setTranslateY(size * 0.5 - secondPointer.getPrefHeight());
            }

            if (Clock.Design.IOS6 == getSkinnable().getDesign()) {
                centerKnob.setPrefSize(size * 0.015, size * 0.015);
            } else if (Clock.Design.BRAUN == getSkinnable().getDesign()) {
                centerKnob.setPrefSize(size * 0.085, size * 0.085);
            } else if (Clock.Design.BOSCH == getSkinnable().getDesign()) {
                centerKnob.setPrefSize(size * 0.035, size * 0.035);
            } else {
                centerKnob.setPrefSize(size * 0.1, size * 0.1);
            }
            centerKnob.setTranslateX(size * 0.5 - (centerKnob.getPrefWidth() * 0.5));
            centerKnob.setTranslateY(size * 0.5 - (centerKnob.getPrefHeight() * 0.5));

            foreground.setPrefSize(size * 0.955, size * 0.495);
            foreground.setTranslateX(size * 0.5 - (foreground.getPrefWidth() * 0.5));
            foreground.setTranslateY(size * 0.01);
        }
    }


    // ******************** Drawing related ***********************************
    private void moveMinutePointer(double newAngle) {
        final KeyValue kv = new KeyValue(currentMinuteAngle, newAngle, Interpolator.SPLINE(0.5, 0.4, 0.4, 1.0));
        final KeyFrame kf = new KeyFrame(Duration.millis(200), kv);
        timeline = new Timeline();
        timeline.getKeyFrames().add(kf);
        timeline.play();
    }
}
