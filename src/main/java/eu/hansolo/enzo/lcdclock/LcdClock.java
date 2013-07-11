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

package eu.hansolo.enzo.lcdclock;/**
 * Created by
 * User: hansolo
 * Date: 11.07.13
 * Time: 06:17
 */

import javafx.animation.AnimationTimer;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.Calendar;


public class LcdClock extends Region {
    private static final double   PREFERRED_WIDTH  = 200;
    private static final double   PREFERRED_HEIGHT = 200;
    private static final double   MINIMUM_WIDTH    = 25;
    private static final double   MINIMUM_HEIGHT   = 25;
    private static final double   MAXIMUM_WIDTH    = 1024;
    private static final double   MAXIMUM_HEIGHT   = 1024;
    private double                size;
    private double                width;
    private double                height;
    private ObjectProperty<Color> hColor;
    private ObjectProperty<Color> mColor;
    private ObjectProperty<Color> sColor;
    private ObjectProperty<Color> textColor;
    private Pane                  pane;
    private Canvas                canvasBkg;
    private GraphicsContext       ctxBkg;
    private Canvas                canvasFg;
    private GraphicsContext       ctxFg;
    private Font                  font;
    private int                   hours;
    private int                   minutes;
    private int                   seconds;
    private boolean               pm;
    private StringBuilder         time;
    private long                  lastTimerCall;
    private AnimationTimer        timer;


    // ******************** Constructors **************************************
    public LcdClock() {
        hColor        = new SimpleObjectProperty<>(this, "hourColor", Color.BLACK);
        mColor        = new SimpleObjectProperty<>(this, "minuteColor", Color.BLACK);
        sColor        = new SimpleObjectProperty<>(this, "secondColor", Color.BLACK);
        textColor     = new SimpleObjectProperty<>(this, "textColor", Color.BLACK);
        time          = new StringBuilder();
        lastTimerCall = System.nanoTime();
        timer = new AnimationTimer() {
            @Override public void handle(long now) {
                if (now > lastTimerCall + 100_000_000l) {
                    time.setLength(0);
                    pm    = Calendar.getInstance().get(Calendar.AM_PM) == 1;
                    hours = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
                    String hourString = Integer.toString(hours);
                    if (hours < 10) {
                        time.append("0");
                        time.append(hourString.substring(0, 1));
                    } else {
                        time.append(hourString.substring(0, 1));
                        time.append(hourString.substring(1));
                    }

                    time.append(":");

                    minutes = Calendar.getInstance().get(Calendar.MINUTE);
                    String minutesString = Integer.toString(minutes);
                    if (minutes < 10) {
                        time.append("0");
                        time.append(minutesString.substring(0, 1));
                    } else {
                        time.append(minutesString.substring(0, 1));
                        time.append(minutesString.substring(1));
                    }

                    seconds = Calendar.getInstance().get(Calendar.SECOND);
                    /*
                    String secondsString = Integer.toString(seconds);
                    if (seconds < 10) {
                        time.append("0");
                        time.append(secondsString.substring(0, 1));
                    } else {
                        time.append(secondsString.substring(0, 1));
                        time.append(secondsString.substring(1));
                    }
                    */
                    drawForeground();
                    lastTimerCall = now;
                }
            }
        };

        init();
        initGraphics();
        registerListeners();
        timer.start();
    }


    // ******************** Initialization ************************************
    private void init() {
        if (Double.compare(getPrefWidth(), 0.0) <= 0 || Double.compare(getPrefHeight(), 0.0) <= 0 ||
            Double.compare(getWidth(), 0.0) <= 0 || Double.compare(getHeight(), 0.0) <= 0) {
            if (getPrefWidth() > 0 && getPrefHeight() > 0) {
                setPrefSize(getPrefWidth(), getPrefHeight());
            } else {
                setPrefSize(PREFERRED_WIDTH, PREFERRED_HEIGHT);
            }
        }

        if (Double.compare(getMinWidth(), 0.0) <= 0 || Double.compare(getMinHeight(), 0.0) <= 0) {
            setMinSize(MINIMUM_WIDTH, MINIMUM_HEIGHT);
        }

        if (Double.compare(getMaxWidth(), 0.0) <= 0 || Double.compare(getMaxHeight(), 0.0) <= 0) {
            setMaxSize(MAXIMUM_WIDTH, MAXIMUM_HEIGHT);
        }
    }

    private void initGraphics() {
        Font.loadFont(getClass().getResourceAsStream("/eu/hansolo/enzo/fonts/digital.ttf"), (0.5833333333 * PREFERRED_HEIGHT));         // "Digital-7"
        //Font.loadFont(getClass().getResourceAsStream("/eu/hansolo/enzo/fonts/digitalreadout.ttf"), (0.5833333333 * PREFERRED_HEIGHT));  // "Digital Readout Upright"
        //Font.loadFont(getClass().getResourceAsStream("/eu/hansolo/enzo/fonts/digitalreadoutb.ttf"), (0.5833333333 * PREFERRED_HEIGHT)); // "Digital Readout Thick Upright"

        canvasBkg = new Canvas(PREFERRED_WIDTH, PREFERRED_HEIGHT);
        ctxBkg    = canvasBkg.getGraphicsContext2D();

        canvasFg  = new Canvas(PREFERRED_WIDTH, PREFERRED_HEIGHT);
        ctxFg     = canvasFg.getGraphicsContext2D();

        pane      = new Pane();
        pane.getChildren().setAll(canvasBkg, canvasFg);

        getChildren().setAll(pane);
        resize();
    }

    private void registerListeners() {
        widthProperty().addListener(observable -> handleControlPropertyChanged("RESIZE"));
        heightProperty().addListener(observable -> handleControlPropertyChanged("RESIZE"));
        hColor.addListener(observable -> handleControlPropertyChanged("REDRAW"));
        mColor.addListener(observable -> handleControlPropertyChanged("REDRAW"));
        sColor.addListener(observable -> handleControlPropertyChanged("REDRAW"));
        textColor.addListener(observable -> handleControlPropertyChanged("REDRAW"));
    }


    // ******************** Methods *******************************************
    protected void handleControlPropertyChanged(final String PROPERTY) {
        if ("RESIZE".equals(PROPERTY)) {
            resize();
        } else if ("REDRAW".equals(PROPERTY)) {
            timer.stop();
            drawBackground();
            drawForeground();
            timer.start();
        }
    }

    @Override protected double computePrefWidth(final double PREF_HEIGHT) {
        double prefHeight = PREFERRED_HEIGHT;
        if (PREF_HEIGHT != -1) {
            prefHeight = Math.max(0, PREF_HEIGHT - getInsets().getTop() - getInsets().getBottom());
        }
        return super.computePrefWidth(prefHeight);
    }
    @Override protected double computePrefHeight(final double PREF_WIDTH) {
        double prefWidth = PREFERRED_WIDTH;
        if (PREF_WIDTH != -1) {
            prefWidth = Math.max(0, PREF_WIDTH - getInsets().getLeft() - getInsets().getRight());
        }
        return super.computePrefWidth(prefWidth);
    }

    @Override protected double computeMinWidth(final double MIN_HEIGHT) {
        return super.computeMinWidth(Math.max(MINIMUM_HEIGHT, MIN_HEIGHT - getInsets().getTop() - getInsets().getBottom()));
    }
    @Override protected double computeMinHeight(final double MIN_WIDTH) {
        return super.computeMinHeight(Math.max(MINIMUM_WIDTH, MIN_WIDTH - getInsets().getLeft() - getInsets().getRight()));
    }

    @Override protected double computeMaxWidth(final double MAX_HEIGHT) {
        return super.computeMaxWidth(Math.min(MAXIMUM_HEIGHT, MAX_HEIGHT - getInsets().getTop() - getInsets().getBottom()));
    }
    @Override protected double computeMaxHeight(final double MAX_WIDTH) {
        return super.computeMaxHeight(Math.min(MAXIMUM_WIDTH, MAX_WIDTH - getInsets().getLeft() - getInsets().getRight()));
    }

    public final Color getHourColor() {
        return hColor.get();
    }
    public final void setHourColor(final Color HOUR_COLOR) {
        hColor.set(HOUR_COLOR);
    }
    public final ObjectProperty<Color> hourColorProperty() {
        return hColor;
    }

    public final Color getMinuteColor() {
        return mColor.get();
    }
    public final void setMinuteColor(final Color MINUTE_COLOR) {
        mColor.set(MINUTE_COLOR);
    }
    public final ObjectProperty<Color> minuteColorProperty() {
        return mColor;
    }

    public final Color getSecondColor() {
        return sColor.get();
    }
    public final void setSecondColor(final Color SECOND_COLOR) {
        sColor.set(SECOND_COLOR);
    }
    public final ObjectProperty<Color> secondColorProperty() {
        return sColor;
    }

    public final Color getTextColor() {
        return textColor.get();
    }
    public final void setTextColor(final Color TEXT_COLOR) {
        textColor.set(TEXT_COLOR);
    }
    public final ObjectProperty<Color> textColorProperty() {
        return textColor;
    }

    public final void setColor(final Color COLOR) {
        setHourColor(COLOR);
        setMinuteColor(COLOR);
        setSecondColor(COLOR);
        setTextColor(COLOR);
    }

    private void drawForeground() {
        int hourCounter = 1;
        int minCounter  = 1;
        int secCounter  = 1;

        double strokeWidth = size * 0.06;
        ctxFg.setLineCap(StrokeLineCap.BUTT);
        ctxFg.clearRect(0, 0, size, size);
        for (int i = 450 ; i >= 90 ; i--) {
            ctxFg.save();
            if (i % 6 == 0) {
                // draw minutes
                if (minCounter <= minutes) {
                    ctxFg.setStroke(getMinuteColor());
                    ctxFg.setLineWidth(strokeWidth);
                    ctxFg.strokeArc(strokeWidth * 0.5 + strokeWidth * 1.1, strokeWidth * 0.5 + strokeWidth * 1.1, size - strokeWidth - strokeWidth * 2.2, size - strokeWidth - strokeWidth * 2.2, i + 1 - 6, 4, ArcType.OPEN);
                    minCounter++;
                }
                // draw seconds
                if (secCounter <= seconds + 1) {
                    ctxFg.setStroke(getSecondColor());
                    ctxFg.setLineWidth(strokeWidth * 0.25);
                    ctxFg.strokeArc(strokeWidth * 0.5 + strokeWidth * 1.8, strokeWidth * 0.5 + strokeWidth * 1.8, size - strokeWidth - strokeWidth * 3.6, size - strokeWidth - strokeWidth * 3.6, i + 1 - 6, 4, ArcType.OPEN);
                    secCounter++;
                }
            }
            if (i % 30 == 0) {
                //draw hours
                ctxFg.setStroke(getHourColor());
                ctxFg.setLineWidth(strokeWidth);
                if (hours == 0 || hours == 12) {
                    ctxFg.strokeArc(strokeWidth * 0.5, strokeWidth * 0.5, size - strokeWidth, size - strokeWidth, i + 1 - 30, 28, ArcType.OPEN);
                } else if (hourCounter <= (pm ? hours - 12 : hours)) {
                    ctxFg.strokeArc(strokeWidth * 0.5, strokeWidth * 0.5, size - strokeWidth, size - strokeWidth, i + 1 - 30, 28, ArcType.OPEN);
                    hourCounter++;
                }
            }
            ctxFg.restore();
        }

        // draw the text
        ctxFg.setFill(getTextColor());
        font = Font.font("Digital-7", (0.2 * size));
        //font = Font.font("Digital Readout Upright", (0.2 * size));
        //font = Font.font("Digital Readout Thick Upright", (0.2 * size));
        ctxFg.setTextBaseline(VPos.CENTER);
        ctxFg.setTextAlign(TextAlignment.CENTER);
        ctxFg.setFont(font);
        ctxFg.fillText(time.toString(), size * 0.5, size * 0.5);
    }

    private void drawBackground() {
        double strokeWidth = size * 0.06;
        ctxBkg.setLineCap(StrokeLineCap.BUTT);
        ctxBkg.clearRect(0, 0, size, size);
        // draw translucent background
        ctxBkg.setStroke(Color.rgb(0, 12, 6, 0.1));
        for (int i = 0 ; i < 360 ; i++) {
            ctxBkg.save();
            if (i % 6 == 0) {
                // draw minutes
                ctxBkg.setStroke(Color.color(mColor.get().getRed(), mColor.get().getGreen(), mColor.get().getBlue(), 0.1));
                ctxBkg.setLineWidth(strokeWidth);
                ctxBkg.strokeArc(strokeWidth * 0.5 + strokeWidth * 1.1, strokeWidth * 0.5 + strokeWidth * 1.1, size - strokeWidth - strokeWidth * 2.2, size - strokeWidth - strokeWidth * 2.2, i + 1, 4, ArcType.OPEN);

                // draw seconds
                ctxBkg.setStroke(Color.color(sColor.get().getRed(), sColor.get().getGreen(), sColor.get().getBlue(), 0.1));
                ctxBkg.setLineWidth(strokeWidth * 0.25);
                ctxBkg.strokeArc(strokeWidth * 0.5 + strokeWidth * 1.8, strokeWidth * 0.5 + strokeWidth * 1.8, size - strokeWidth - strokeWidth * 3.6, size - strokeWidth - strokeWidth * 3.6, i + 1, 4, ArcType.OPEN);
            }
            if (i % 30 == 0) {
                //draw hours
                ctxBkg.setStroke(Color.color(hColor.get().getRed(), hColor.get().getGreen(), hColor.get().getBlue(), 0.1));
                ctxBkg.setLineWidth(strokeWidth);
                ctxBkg.strokeArc(strokeWidth * 0.5, strokeWidth * 0.5, size - strokeWidth, size - strokeWidth, i + 1, 28, ArcType.OPEN);
            }
            ctxBkg.restore();
        }

        // draw the text
        ctxBkg.setFill(Color.color(textColor.get().getRed(), textColor.get().getGreen(), textColor.get().getBlue(), 0.1));
        font = Font.font("Digital-7", (0.2 * size));
        //font = Font.font("Digital Readout Upright", (0.2 * size));
        //font = Font.font("Digital Readout Thick Upright", (0.2 * size));
        ctxBkg.setTextBaseline(VPos.CENTER);
        ctxBkg.setTextAlign(TextAlignment.CENTER);
        ctxBkg.setFont(font);
        ctxBkg.fillText("88:88", size * 0.5, size * 0.5);
    }

    // ******************** Resizing ******************************************
    private void resize() {
        size   = getWidth() < getHeight() ? getWidth() : getHeight();
        width  = getWidth();
        height = getHeight();

        if (width > 0 && height > 0) {
            canvasBkg.setWidth(size);
            canvasBkg.setHeight(size);
            canvasFg.setWidth(size);
            canvasFg.setHeight(size);
            drawBackground();
            drawForeground();
        }
    }
}