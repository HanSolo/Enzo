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

package eu.hansolo.enzo.lcd;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.util.Duration;


public class Lcd extends Control {
    public static final String STYLE_CLASS_BEIGE            = "lcd-beige";
    public static final String STYLE_CLASS_BLUE             = "lcd-blue";
    public static final String STYLE_CLASS_ORANGE           = "lcd-orange";
    public static final String STYLE_CLASS_RED              = "lcd-red";
    public static final String STYLE_CLASS_YELLOW           = "lcd-yellow";
    public static final String STYLE_CLASS_WHITE            = "lcd-white";
    public static final String STYLE_CLASS_GRAY             = "lcd-gray";
    public static final String STYLE_CLASS_BLACK            = "lcd-black";
    public static final String STYLE_CLASS_GREEN            = "lcd-green";
    public static final String STYLE_CLASS_GREEN_DARKGREEN  = "lcd-green-darkgreen";
    public static final String STYLE_CLASS_BLUE2            = "lcd-blue2";
    public static final String STYLE_CLASS_BLUE_BLACK       = "lcd-blue-black";
    public static final String STYLE_CLASS_BLUE_DARKBLUE    = "lcd-blue-darkblue";
    public static final String STYLE_CLASS_BLUE_LIGHTBLUE   = "lcd-blue-lightblue";
    public static final String STYLE_CLASS_BLUE_GRAY        = "lcd-blue-gray";
    public static final String STYLE_CLASS_STANDARD         = "lcd-standard";
    public static final String STYLE_CLASS_STANDARD_GREEN   = "lcd-standard-green";
    public static final String STYLE_CLASS_BLUE_BLUE        = "lcd-blue-blue";
    public static final String STYLE_CLASS_RED_DARKRED      = "lcd-red-darkred";
    public static final String STYLE_CLASS_DARKBLUE         = "lcd-darkblue";
    public static final String STYLE_CLASS_LILA             = "lcd-lila";
    public static final String STYLE_CLASS_BLACK_RED        = "lcd-black-red";
    public static final String STYLE_CLASS_DARKGREEN        = "lcd-darkgreen";
    public static final String STYLE_CLASS_AMBER            = "lcd-amber";
    public static final String STYLE_CLASS_LIGHTBLUE        = "lcd-lightblue";
    public static final String STYLE_CLASS_GREEN_BLACK      = "lcd-green-black";
    public static final String STYLE_CLASS_YELLOW_BLACK     = "lcd-yellow-black";
    public static final String STYLE_CLASS_BLACK_YELLOW     = "lcd-black-yellow";
    public static final String STYLE_CLASS_LIGHTGREEN_BLACK = "lcd-lightgreen-black";
    public static final String STYLE_CLASS_DARKLILA         = "lcd-darklila";
    public static final String STYLE_CLASS_DARKAMBER        = "lcd-darkamber";
    public static final String STYLE_CLASS_BLUE_LIGHTBLUE2  = "lcd-blue-lightblue2";
    public static final String STYLE_CLASS_GRAY_LILA        = "lcd-gray-lila";
    public static final String STYLE_CLASS_SECTIONS         = "lcd-sections";
    public static enum LcdFont {
        STANDARD,
        LCD,
        BUS,
        PIXEL,
        PHONE_LCD
    }
    public static enum NumberSystem {
        DECIMAL("dec"),
        HEXADECIMAL("hex"),
        OCTAL("oct");

        private String text;

        private NumberSystem(final String TEXT) {
            text = TEXT;
        }

        @Override public String toString() {
            return text;
        }
    }
    public static enum Trend {
        UP,
        RISING,
        STEADY,
        FALLING,
        DOWN,
        UNKNOWN
    }

    private boolean                      initialized;
    private boolean                      firstTime;
    private BooleanProperty              keepAspect;
    private DoubleProperty               value;
    private DoubleProperty               currentValue;
    private DoubleProperty               minValue;
    private DoubleProperty               maxValue;
    private DoubleProperty               formerValue;
    private BooleanProperty              valueAnimationEnabled;
    private DoubleProperty               animationDuration;
    private DoubleProperty               minMeasuredValue;
    private DoubleProperty               maxMeasuredValue;
    private IntegerProperty              minMeasuredValueDecimals;
    private IntegerProperty              maxMeasuredValueDecimals;
    private DoubleProperty               threshold;
    private BooleanProperty              thresholdBehaviorInverted;
    private BooleanProperty              thresholdExceeded;
    private StringProperty               title;
    private StringProperty               unit;
    private ObjectProperty<NumberSystem> numberSystem;
    private ObjectProperty<Trend>        trend;
    private BooleanProperty              valueVisible;
    private BooleanProperty              minMeasuredValueVisible;
    private BooleanProperty              maxMeasuredValueVisible;
    private BooleanProperty              formerValueVisible;
    private BooleanProperty              thresholdVisible;
    private StringProperty               unitFont;
    private StringProperty               titleFont;
    private ObjectProperty<LcdFont>      valueFont;
    private IntegerProperty              decimals;
    private BooleanProperty              numberSystemVisible;
    private BooleanProperty              blinking;
    private BooleanProperty              backgroundVisible;
    private BooleanProperty              titleVisible;
    private BooleanProperty              unitVisible;
    private BooleanProperty              trendVisible;
    private Transition                   toValueAnimation;
    private boolean                      toggleValue;
    private boolean                      toggleThreshold;
    private long                         interval;
    private long                         lastBlinkTimerCall;
    private long                         lastThresholdTimerCall;
    private AnimationTimer               timer;


    // ******************** Constructors **************************************
    public Lcd() {
        getStyleClass().add("lcd");
        initialized               = false;
        firstTime                 = true;
        keepAspect                = new SimpleBooleanProperty(true);
        value                     = new SimpleDoubleProperty(0);
        currentValue              = new SimpleDoubleProperty(0);
        minValue                  = new SimpleDoubleProperty(0);
        maxValue                  = new SimpleDoubleProperty(100);
        formerValue               = new SimpleDoubleProperty(0);
        valueAnimationEnabled     = new SimpleBooleanProperty(true);
        animationDuration         = new SimpleDoubleProperty(800);
        minMeasuredValue          = new SimpleDoubleProperty(100);
        maxMeasuredValue          = new SimpleDoubleProperty(0);
        minMeasuredValueDecimals  = new SimpleIntegerProperty(2);
        maxMeasuredValueDecimals  = new SimpleIntegerProperty(2);
        threshold                 = new SimpleDoubleProperty(50);
        thresholdBehaviorInverted = new SimpleBooleanProperty(false);
        thresholdExceeded         = new SimpleBooleanProperty(false);
        title                     = new SimpleStringProperty("");
        unit                      = new SimpleStringProperty("");
        numberSystem              = new SimpleObjectProperty<>(NumberSystem.DECIMAL);
        trend                     = new SimpleObjectProperty<>(Trend.UNKNOWN);
        valueVisible              = new SimpleBooleanProperty(true);
        minMeasuredValueVisible   = new SimpleBooleanProperty(false);
        maxMeasuredValueVisible   = new SimpleBooleanProperty(false);
        formerValueVisible        = new SimpleBooleanProperty(false);
        thresholdVisible          = new SimpleBooleanProperty(false);
        backgroundVisible         = new SimpleBooleanProperty(true);
        titleFont                 = new SimpleStringProperty("Verdana");
        unitFont                  = new SimpleStringProperty("Verdana");
        unitVisible               = new SimpleBooleanProperty(false);
        unitFont                  = new SimpleStringProperty("Verdana");
        titleFont                 = new SimpleStringProperty("Verdana");
        valueFont                 = new SimpleObjectProperty<>(LcdFont.LCD);
        decimals                  = new SimpleIntegerProperty(0);
        numberSystemVisible       = new SimpleBooleanProperty(false);
        blinking                  = new SimpleBooleanProperty(false);
        backgroundVisible         = new SimpleBooleanProperty(true);
        titleVisible              = new SimpleBooleanProperty(true);
        unitVisible               = new SimpleBooleanProperty(true);
        trendVisible              = new SimpleBooleanProperty(false);
        toggleValue               = false;
        toggleThreshold           = false;
        interval                  = 500_000_000l;
        lastBlinkTimerCall        = System.nanoTime();
        lastThresholdTimerCall    = System.nanoTime();
        timer                     = new AnimationTimer() {
            @Override public void handle(final long NOW) {
                if (isBlinking() && NOW > lastBlinkTimerCall + interval) {
                    toggleValue ^= true;
                    valueVisible.set(toggleValue);
                    lastBlinkTimerCall = NOW;
                }
                if (isThresholdExceeded() && NOW > lastThresholdTimerCall + interval) {
                    toggleThreshold ^= true;
                    thresholdVisible.set(toggleThreshold);
                    lastThresholdTimerCall = NOW;
                }
            }
        };
        toValueAnimation           = new Transition() {
            {
                setCycleDuration(Duration.millis(getAnimationDuration()));
            }
            protected void interpolate(double frac) {
                currentValue.set(getFormerValue() + (getValue() - getFormerValue()) * frac);
            }
        };
        init();
        initialized = true;
    }


    // ******************** Initialization ************************************
    private void init() {
        valueProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
                formerValue.set(oldValue.doubleValue());
                if (toValueAnimation.getStatus() != Animation.Status.STOPPED) {
                    toValueAnimation.stop();
                }
                if (isValueAnimationEnabled()) {
                    toValueAnimation.setInterpolator(Interpolator.SPLINE(0.5, 0.4, 0.4, 1.0));
                    toValueAnimation.play();
                    toValueAnimation.setOnFinished(new EventHandler<ActionEvent>() {
                        @Override public void handle(final ActionEvent EVENT) {
                            if (firstTime) {
                                resetMinMaxMeasuredValue();
                                firstTime = false;
                            }
                        }
                    });
                } else {
                    currentValue.set(newValue.doubleValue());
                }
            }
        });
        currentValue.addListener(new ChangeListener<Number>() {
            @Override public void changed(final ObservableValue<? extends Number> ov, final Number oldValue, final Number newValue) {
                if (Double.compare(currentValue.get(), getMinMeasuredValue()) < 0 && !firstTime) {
                    setMinMeasuredValue(currentValue.get());
                }
                if (Double.compare(currentValue.get(), getMaxMeasuredValue()) > 0 && !firstTime) {
                    setMaxMeasuredValue(currentValue.get());
                }
                if (initialized) {
                    if (isThresholdBehaviorInverted()) {
                        setThresholdExceeded(currentValue.get() < getThreshold());
                    } else {
                        setThresholdExceeded(currentValue.get() > getThreshold());
                    }
                }
            }
        });
    }


    // ******************** Methods *******************************************
    public final boolean isKeepAspect() {
        return keepAspect.get();
    }
    public final void setKeepAspect(final boolean KEEP_ASPECT) {
        keepAspect.set(KEEP_ASPECT);
    }
    public final BooleanProperty keepAspectProperty() {
        return keepAspect;
    }

    @Override public boolean isResizable() {
        return true;
    }

    public final double getValue() {
        return value.get();
    }
    public final void setValue(final double VALUE) {
        formerValue.set(value.get());
        value.set(clamp(getMinValue(), getMaxValue(), VALUE));
    }
    public final DoubleProperty valueProperty() {
        return value;
    }

    public final double getCurrentValue() {
        return currentValue.get();
    }
    public final ReadOnlyDoubleProperty currentValueProperty() {
        return currentValue;
    }

    public final double getFormerValue() {
        return formerValue.get();
    }
    public final ReadOnlyDoubleProperty formerValueProperty() {
        return formerValue;
    }

    public final boolean isValueAnimationEnabled() {
        return valueAnimationEnabled.get();
    }
    public final void setValueAnimationEnabled(final boolean VALUE_ANIMATION_ENABLED) {
        valueAnimationEnabled.set(VALUE_ANIMATION_ENABLED);
    }
    public final BooleanProperty valueAnimationEnabledProperty() {
        return valueAnimationEnabled;
    }

    public final double getAnimationDuration() {
        return animationDuration.get();
    }
    public final void setAnimationDuration(final double ANIMATION_DURATION) {
        animationDuration.set(ANIMATION_DURATION);
    }
    public final DoubleProperty animationDurationProperty() {
        return animationDuration;
    }

    public final double getMinValue() {
        return minValue.get();
    }
    public final void setMinValue(final double MIN_VALUE) {
        minValue.set(MIN_VALUE);
    }
    public final DoubleProperty minValueProperty() {
        return minValue;
    }

    public final double getMaxValue() {
        return maxValue.get();
    }
    public final void setMaxValue(final double MAX_VALUE) {
        maxValue.set(MAX_VALUE);
    }
    public final DoubleProperty maxValueProperty() {
        return maxValue;
    }

    public final double getRange() {
        return maxValue.subtract(minValue).get();
    }

    public final double getMinMeasuredValue() {
        return minMeasuredValue.get();
    }
    public final void setMinMeasuredValue(final double MIN_MEASURED_VALUE) {
        minMeasuredValue.set(MIN_MEASURED_VALUE);
    }
    public final DoubleProperty minMeasuredValueProperty() {
        return minMeasuredValue;
    }
    public final void resetMinMeasuredValue() {
        setMinMeasuredValue(getValue());
    }

    public final double getMaxMeasuredValue() {
        return maxMeasuredValue.get();
    }
    public final void setMaxMeasuredValue(final double MAX_MEASURED_VALUE) {
        maxMeasuredValue.set(MAX_MEASURED_VALUE);
    }
    public final DoubleProperty maxMeasuredValueProperty() {
        return maxMeasuredValue;
    }
    public final void resetMaxMeasuredValue() {
        setMaxMeasuredValue(getValue());
    }

    public final void resetMinMaxMeasuredValue() {
        setMinMeasuredValue(getValue());
        setMaxMeasuredValue(getValue());
    }

    public final double getThreshold() {
        return threshold.get();
    }
    public final void setThreshold(final double THRESHOLD) {
        threshold.set(clamp(getMinValue(), getMaxValue(), THRESHOLD));
    }
    public final DoubleProperty thresholdProperty() {
        return threshold;
    }

    public final boolean isThresholdBehaviorInverted() {
        return thresholdBehaviorInverted.get();
    }
    public final void setThresholdBehaviorInverted(final boolean THRESHOLD_BEHAVIOR_INVERTED) {
        thresholdBehaviorInverted.set(THRESHOLD_BEHAVIOR_INVERTED);
        
    }
    public final BooleanProperty thresholdBehaviorInvertedProperty() {
        return thresholdBehaviorInverted;
    }

    public final boolean isThresholdExceeded() {
        return thresholdExceeded.get();
    }
    public final void setThresholdExceeded(final boolean THRESHOLD_EXCEEDED) {
        thresholdExceeded.set(THRESHOLD_EXCEEDED);
        
    }
    public final BooleanProperty thresholdExceededProperty() {
        return thresholdExceeded;
    }

    public final String getTitle() {
        return title.get();
    }
    public final void setTitle(final String TITLE) {
        title.set(TITLE);
    }
    public final StringProperty titleProperty() {
        return title;
    }

    public final String getUnit() {
        return unit.get();
    }
    public final void setUnit(final String UNIT) {
        unit.set(UNIT);
        
    }
    public final StringProperty unitProperty() {
        return unit;
    }

    public final NumberSystem getNumberSystem() {
        return numberSystem.get();
    }
    public final void setNumberSystem(final NumberSystem NUMBER_SYSTEM) {
        numberSystem.set(NUMBER_SYSTEM);
        
    }
    public final ObjectProperty numberSystemProperty() {
        return numberSystem;
    }

    public final Trend getTrend() {
        return trend.get();
    }
    public final void setTrend(final Trend TREND) {
        trend.set(TREND);
        
    }
    public final ObjectProperty<Trend> trendProperty() {
        return trend;
    }

    public final boolean isValueVisible() {
        return valueVisible.get();
    }
    public final ReadOnlyBooleanProperty valueVisibleProperty() {
        return valueVisible;
    }

    public final boolean isMinMeasuredValueVisible() {
        return minMeasuredValueVisible.get();
    }
    public final void setMinMeasuredValueVisible(final boolean MIN_MEASURED_VALUE_VISIBLE) {
        minMeasuredValueVisible.set(MIN_MEASURED_VALUE_VISIBLE);
        
    }
    public final BooleanProperty minMeasuredValueVisibleProperty() {
        return minMeasuredValueVisible;
    }

    public final boolean isMaxMeasuredValueVisible() {
        return maxMeasuredValueVisible.get();
    }
    public final void setMaxMeasuredValueVisible(final boolean MAX_MEASURED_VALUE_VISIBLE) {
        maxMeasuredValueVisible.set(MAX_MEASURED_VALUE_VISIBLE);
        
    }
    public final BooleanProperty maxMeasuredValueVisibleProperty() {
        return maxMeasuredValueVisible;
    }

    public final boolean isThresholdVisible() {
        return thresholdVisible.get();
    }
    public final void setThresholdVisible(final boolean THRESHOLD_VISIBLE) {
        thresholdVisible.set(THRESHOLD_VISIBLE);
        
    }
    public final BooleanProperty thresholdVisibleProperty() {
        return thresholdVisible;
    }

    public final boolean isBackgroundVisible() {
        return backgroundVisible.get();
    }
    public final void setBackgroundVisible(final boolean BACKGROUND_VISIBLE) {
        backgroundVisible.set(BACKGROUND_VISIBLE);
        
    }
    public final BooleanProperty backgroundVisibleProperty() {
        return backgroundVisible;
    }

    public final String getTitleFont() {
        return titleFont.get();
    }
    public final void setTitleFont(final String TITLE_FONT) {
        titleFont.set(TITLE_FONT);
    }
    public final StringProperty titleFontProperty() {
        return titleFont;
    }

    public final String getUnitFont() {
        return unitFont.get();
    }
    public final void setUnitFont(final String UNIT_FONT) {
        unitFont.set(UNIT_FONT);
        
    }
    public final StringProperty unitFontProperty() {
        return unitFont;
    }
    
    public final LcdFont getValueFont() {
        return valueFont.get();
    }
    public final void setValueFont(final LcdFont VALUE_FONT) {
        valueFont.set(VALUE_FONT);
        
    }
    public final ObjectProperty<LcdFont> valueFontProperty() {
        return valueFont;
    }

    public final int getDecimals() {
        return decimals.get();
    }
    public final void setDecimals(final int DECIMALS) {
        final int dec = DECIMALS > 5 ? 5 : (DECIMALS < 0 ? 0 : DECIMALS);
        decimals.set(dec);
    }
    public final IntegerProperty decimalsProperty() {
        return decimals;
    }

    public final boolean isNumberSystemVisible() {
        return numberSystemVisible.get();
    }
    public final void setNumberSystemVisible(final boolean NUMBER_SYSTEM_VISIBLE) {
        numberSystemVisible.set(NUMBER_SYSTEM_VISIBLE);
        
    }
    public final BooleanProperty numberSystemVisibleProperty() {
        return numberSystemVisible;
    }

    public final boolean isBlinking() {
        return blinking.get();
    }
    public final void setBlinking(final boolean BLINKING) {
        blinking.set(BLINKING);
        if (BLINKING) {
            timer.start();
        } else {
            timer.stop();
            valueVisible.set(true);
        }
    }
    public final BooleanProperty blinkingProperty() {
        return blinking;
    }

    public final boolean isTitleVisible() {
        return titleVisible.get();
    }
    public final void setTitleVisible(final boolean TITLE_VISIBLE) {
        titleVisible.set(TITLE_VISIBLE);
    }
    public final BooleanProperty titleVisibleProperty() {
        return titleVisible;
    }

    public final boolean isUnitVisible() {
        return unitVisible.get();
    }
    public final void setUnitVisible(final boolean UNIT_VISIBLE) {
        unitVisible.set(UNIT_VISIBLE);
    }
    public final BooleanProperty unitVisibleProperty() {
        return unitVisible;
    }

    public final boolean isTrendVisible() {
        return trendVisible.get();
    }
    public final void setTrendVisible(final boolean TREND_VISIBLE) {
        trendVisible.set(TREND_VISIBLE);
    }
    public final BooleanProperty trendVisibleProperty() {
        return trendVisible;
    }

    public final boolean isFormerValueVisible() {
        return formerValueVisible.get();
    }
    public final void setFormerValueVisible(final boolean FORMER_VALUE_VISIBLE) {
        formerValueVisible.set(FORMER_VALUE_VISIBLE);
    }
    public final BooleanProperty formerValueVisibleProperty() {
        return formerValueVisible;
    }

    public final int getMinMeasuredValueDecimals() {
        return minMeasuredValueDecimals.get();
    }
    public final void setMinMeasuredValueDecimals(final int MIN_MEASURED_VALUE_DECIMALS) {
        final int DECIMALS = MIN_MEASURED_VALUE_DECIMALS > 5 ? 5 : (MIN_MEASURED_VALUE_DECIMALS < 0 ? 0 : MIN_MEASURED_VALUE_DECIMALS);
        minMeasuredValueDecimals.set(DECIMALS);
    }
    public final IntegerProperty minMeasuredValueDecimalsProperty() {
        return maxMeasuredValueDecimals;
    }

    public final int getMaxMeasuredValueDecimals() {
        return maxMeasuredValueDecimals.get();
    }
    public final void setMaxMeasuredValueDecimals(final int MAX_MEASURED_VALUE_DECIMALS) {
        final int DECIMALS = MAX_MEASURED_VALUE_DECIMALS > 5 ? 5 : (MAX_MEASURED_VALUE_DECIMALS < 0 ? 0 : MAX_MEASURED_VALUE_DECIMALS);
        maxMeasuredValueDecimals.set(DECIMALS);
    }
    public final IntegerProperty maxMeasuredValueDecimalsProperty() {
        return maxMeasuredValueDecimals;
    }


    // ******************** Private Methods ***********************************
    private double clamp(final double MIN, final double MAX, final double VALUE) {
        return VALUE < MIN ? MIN : (VALUE > MAX ? MAX : VALUE);
    }


    // ******************** Style related *************************************
    @Override protected String getUserAgentStylesheet() {
        return getClass().getResource(getClass().getSimpleName().toLowerCase() + ".css").toExternalForm();
    }
}
