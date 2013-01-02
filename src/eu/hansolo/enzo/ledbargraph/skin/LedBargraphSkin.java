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

package eu.hansolo.enzo.ledbargraph.skin;

import eu.hansolo.enzo.led.Led;
import eu.hansolo.enzo.led.LedBuilder;
import eu.hansolo.enzo.ledbargraph.LedBargraph;
import javafx.animation.AnimationTimer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Orientation;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by
 * User: hansolo
 * Date: 16.02.12
 * Time: 11:30
 */
public class LedBargraphSkin extends SkinBase<LedBargraph> {
    private static final double PREFERRED_SIZE = 16;
    public static final long    PEAK_TIMEOUT   = 1_500_000_000l;
    private LedBargraph         control;
    private Pane                bargraph;
    private List <Led>          ledList;
    private long                lastTimerCall;
    private DoubleProperty      stepSize;
    private int                 peakLedIndex;
    private AnimationTimer      timer;


    // ******************** Constructors **************************************
    public LedBargraphSkin(final LedBargraph CONTROL) {
        super(CONTROL);
        control         = CONTROL;
        ledList         = new ArrayList<>(control.getNoOfLeds());
        stepSize        = new SimpleDoubleProperty(1.0 / control.getNoOfLeds());
        lastTimerCall   = 0l;
        peakLedIndex    = 0;
        timer           = new AnimationTimer() {
            @Override public void handle(final long NOW) {
                if (NOW > lastTimerCall + PEAK_TIMEOUT) {
                    ledList.get(peakLedIndex).setOn(false);
                    peakLedIndex = 0;
                    timer.stop();
                }
            }
        };

        init();
        initGraphics();
        registerListeners();
    }


    // ******************** Initialization ************************************
    private void init() {
        if (control.getPrefWidth() <= 0 || control.getPrefHeight() <= 0 ||
            control.getPrefWidth() <= 0 || control.getHeight() <= 0) {
            control.setPrefSize(PREFERRED_SIZE, PREFERRED_SIZE);
        }

        if (control.getMinWidth() <= 0 || control.getMinHeight() <= 0 ||
            control.getPrefWidth() <= 0 || getHeight() <= 0) {
            control.setMinSize(8, 8);
        }

        if (control.getMaxWidth() <= 0 || control.getMaxHeight() <= 0 ||
            control.getPrefWidth() <= 0 || getHeight() <= 0) {
            control.setMaxSize(1024, 1024);
        }

        for(int i = 0 ; i < control.getNoOfLeds() ; i++) {
            Led led = LedBuilder.create()
                                .frameVisible(control.isFrameVisible())
                                .prefWidth(control.getLedSize())
                                .prefHeight(control.getLedSize())
                                .build();
            ledList.add(led);
            if (control.getValue() > 0) {
                if (Double.compare(i * stepSize.doubleValue(), control.getValue()) <= 0) {
                    ledList.get(i).setOn(true);
                } else {
                    ledList.get(i).setOn(false);
                }
            }
        }

        setLedColors();
        setLedTypes();
    }

    private void initGraphics() {
        final int NO_OF_LEDS = control.getNoOfLeds();
        if (control.getOrientation() == Orientation.VERTICAL) {
            bargraph = new VBox();
            ((VBox) bargraph).setSpacing(0);
            //bargraph.setPadding(new Insets(0, 0, 0, 0));
            for (int i = 0 ; i < NO_OF_LEDS ; i++) {
                bargraph.getChildren().add(i, ledList.get(NO_OF_LEDS - 1 - i));
            }
        } else {
            bargraph = new HBox();
            ((HBox) bargraph).setSpacing(0);
            //bargraph.setPadding(new Insets(0, 0, 0, 0));
            for (int i = 0 ; i < NO_OF_LEDS ; i++) {
                bargraph.getChildren().add(i, ledList.get(i));
            }
        }
        getChildren().setAll(bargraph);
    }

    private void registerListeners() {
        registerChangeListener(control.widthProperty(), "RESIZE");
        registerChangeListener(control.heightProperty(), "RESIZE");
        registerChangeListener(control.valueProperty(), "VALUE");
        registerChangeListener(control.ledTypeProperty(), "LED_TYPE");
        registerChangeListener(control.frameVisibleProperty(), "FRAME_VISIBLE");
        registerChangeListener(control.ledSizeProperty(), "LED_SIZE");
        registerChangeListener(control.orientationProperty(), "ORIENTATION");
        registerChangeListener(control.noOfLedsProperty(), "LED_NUMBER");
        registerChangeListener(control.ledColorsProperty(), "LED_COLOR");
    }


    // ******************** Methods *******************************************
    @Override protected void handleControlPropertyChanged(final String PROPERTY) {
        super.handleControlPropertyChanged(PROPERTY);
        if ("VALUE".equals(PROPERTY)) {
            int currentLedPeakIndex = 0;
            for (int i = 0 ; i < control.getNoOfLeds() ; i++) {
                if (Double.compare(i * stepSize.doubleValue(), control.getValue()) <= 0) {
                    ledList.get(i).setOn(true);
                    currentLedPeakIndex = i;
                } else {
                    ledList.get(i).setOn(false);
                }
                ledList.get(peakLedIndex).setOn(true);
            }
            // PeakValue
            if (control.isPeakValueVisible()) {
                if (currentLedPeakIndex > peakLedIndex) {
                    peakLedIndex = currentLedPeakIndex;
                    timer.stop();
                    lastTimerCall = System.nanoTime();
                    timer.start();
                }
            }
        } else if ("FRAME_VISIBLE".equals(PROPERTY)) {
            for (Led led : ledList) {
                led.setFrameVisible(control.isFrameVisible());
            }
        } else if ("LED_SIZE".equals(PROPERTY)) {
            setLedSizes();
            setLedColors();
        } else if ("ORIENTATION".equals(PROPERTY)) {
            initGraphics();
        } else if ("LED_NUMBER".equals(PROPERTY)) {
            stepSize.set(1.0 / control.getNoOfLeds());
        } else if ("LED_COLOR".equals(PROPERTY)) {
            setLedColors();
        } else if ("LED_TYPE".equals(PROPERTY)) {
            setLedTypes();
        } else if ("RESIZE".equals(PROPERTY)) {
            setLedSizes();
        }
    }

    @Override public final void dispose() {
        control = null;
    }

    @Override protected double computePrefWidth(final double PREF_HEIGHT) {
        double prefHeight = 16;
        if (PREF_HEIGHT != -1) {
            prefHeight = Math.max(0, PREF_HEIGHT - getInsets().getTop() - getInsets().getBottom());
        }
        return super.computePrefWidth(prefHeight);
    }

    @Override protected double computePrefHeight(final double PREF_WIDTH) {
        double prefWidth = 16;
        if (PREF_WIDTH != -1) {
            prefWidth = Math.max(0, PREF_WIDTH - getInsets().getLeft() - getInsets().getRight());
        }
        return super.computePrefWidth(prefWidth);
    }

    @Override protected double computeMinWidth(final double MIN_HEIGHT) {
        return super.computeMinWidth(Math.max(5, MIN_HEIGHT - getInsets().getTop() - getInsets().getBottom()));
    }

    @Override protected double computeMinHeight(final double MIN_WIDTH) {
        return super.computeMinHeight(Math.max(5, MIN_WIDTH - getInsets().getLeft() - getInsets().getRight()));
    }

    @Override protected double computeMaxWidth(final double MAX_HEIGHT) {
        return super.computeMaxWidth(Math.min(1024, MAX_HEIGHT - getInsets().getTop() - getInsets().getBottom()));
    }

    @Override protected double computeMaxHeight(final double MAX_WIDTH) {
        return super.computeMaxHeight(Math.min(1024, MAX_WIDTH - getInsets().getLeft() - getInsets().getRight()));
    }


    // ******************** Private Methods ***********************************
    private final void setLedSizes() {
        for (Led led : ledList) {
            led.setPrefSize(control.getLedSize(), control.getLedSize());
        }
    }

    private final void setLedColors() {
        for (int i = 0 ; i < control.getNoOfLeds() ; i++) {
            ledList.get(i).setColor(control.getLedColor(i));
        }
    }

    private final void setLedTypes() {
        for (Led led : ledList) {
            led.setType(control.getLedType());
        }
    }
}
