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
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
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
    public static final String STYLE_CLASS_LIGHTGREEN       = "lcd-lightgreen";
    public static final String STYLE_CLASS_STANDARD_GREEN   = "lcd-standard-green";
    public static final String STYLE_CLASS_BLUE_BLUE        = "lcd-blue-blue";
    public static final String STYLE_CLASS_RED_DARKRED      = "lcd-red-darkred";
    public static final String STYLE_CLASS_DARKBLUE         = "lcd-darkblue";
    public static final String STYLE_CLASS_PURPLE           = "lcd-purple";
    public static final String STYLE_CLASS_BLACK_RED        = "lcd-black-red";
    public static final String STYLE_CLASS_DARKGREEN        = "lcd-darkgreen";
    public static final String STYLE_CLASS_AMBER            = "lcd-amber";
    public static final String STYLE_CLASS_LIGHTBLUE        = "lcd-lightblue";
    public static final String STYLE_CLASS_GREEN_BLACK      = "lcd-green-black";
    public static final String STYLE_CLASS_YELLOW_BLACK     = "lcd-yellow-black";
    public static final String STYLE_CLASS_BLACK_YELLOW     = "lcd-black-yellow";
    public static final String STYLE_CLASS_LIGHTGREEN_BLACK = "lcd-lightgreen-black";
    public static final String STYLE_CLASS_DARKPURPLE       = "lcd-darkpurple";
    public static final String STYLE_CLASS_DARKAMBER        = "lcd-darkamber";
    public static final String STYLE_CLASS_BLUE_LIGHTBLUE2  = "lcd-blue-lightblue2";
    public static final String STYLE_CLASS_GRAY_PURPLE      = "lcd-gray-purple";
    public static final String STYLE_CLASS_SECTIONS         = "lcd-sections";
    public static enum LcdFont {
        STANDARD,
        LCD,
        BUS,
        PIXEL,
        PHONE_LCD,
        ELEKTRA
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
    private boolean                      keepAspect;
    private boolean                      defaultTextMode = false;
    private BooleanProperty              textMode;
    private String                       defaultText = "";
    private StringProperty               text;
    private DoubleProperty               value;
    private DoubleProperty               currentValue;
    private DoubleProperty               formerValue;
    private double                       defaultMinValue = 0.0;
    private DoubleProperty               minValue;
    private double                       defaultMaxValue = 100.0;
    private DoubleProperty               maxValue;
    private boolean                      defaultValueAnimationEnabled = true;
    private BooleanProperty              valueAnimationEnabled;
    private double                       defaultAnimationDuration = 800;
    private DoubleProperty               animationDuration;
    private double                       defaultMinMeasuredValue = 100;
    private DoubleProperty               minMeasuredValue;
    private double                       defaultMaxMeasuredValue = 0;
    private DoubleProperty               maxMeasuredValue;
    private int                          defaultMinMeasuredValueDecimals = 2;
    private IntegerProperty              minMeasuredValueDecimals;
    private int                          defaultMaxMeasuredValueDecimals = 2;
    private IntegerProperty              maxMeasuredValueDecimals;
    private double                       defaultThreshold = 50.0;
    private DoubleProperty               threshold;
    private boolean                      defaultThresholdBehaviorInverted = false;
    private BooleanProperty              thresholdBehaviorInverted;
    private boolean                      defaultThresholdExceeded = false;
    private BooleanProperty              thresholdExceeded;
    private String                       defaultTitle = "";
    private StringProperty               title;
    private String                       defaultUnit = "";
    private StringProperty               unit;
    private String                       defaultLowerCenterText = "";
    private StringProperty               lowerCenterText;
    private boolean                      defaultLowerCenterCenterTextVisible = false;
    private BooleanProperty              lowerCenterTextVisible;
    private String                       defaultLowerRightText = "";
    private StringProperty               lowerRightText;
    private boolean                      defaultLowerRightTextVisible = false;
    private BooleanProperty              lowerRightTextVisible;
    private String                       defaultUpperLeftText = "";
    private StringProperty               upperLeftText;
    private boolean                      defaultUpperLeftTextVisible = false;
    private BooleanProperty              upperLeftTextVisible;
    private String                       defaultUpperRightText = "";
    private StringProperty               upperRightText;
    private boolean                      defaultUpperRightTextVisible = false;
    private BooleanProperty              upperRightTextVisible;
    private NumberSystem                 defaultNumberSystem = NumberSystem.DECIMAL;
    private ObjectProperty<NumberSystem> numberSystem;
    private Trend                        defaultTrend = Trend.UNKNOWN;
    private ObjectProperty<Trend>        trend;
    private double                       defaultBatteryCharge = 0.0;
    private DoubleProperty               batteryCharge;
    private boolean                      defaultValueVisible = true;
    private BooleanProperty              valueVisible;
    private boolean                      defaultMinMeasuredValueVisible = false;
    private BooleanProperty              minMeasuredValueVisible;
    private boolean                      defaultMaxMeasuredValueVisible = false;
    private BooleanProperty              maxMeasuredValueVisible;
    private boolean                      defaultFormerValueVisible = false;
    private BooleanProperty              formerValueVisible;
    private boolean                      defaultThresholdVisible = false;
    private BooleanProperty              thresholdVisible;
    private String                       defaultUnitFont = "Verdana";
    private StringProperty               unitFont;
    private String                       defaultTitleFont = "Verdana";
    private StringProperty               titleFont;
    private LcdFont                      defaultValueFont = LcdFont.LCD;
    private ObjectProperty<LcdFont>      valueFont;
    private int                          defaultDecimals = 0;
    private IntegerProperty              decimals;
    private boolean                      defaultNumberSystemVisible = false;
    private BooleanProperty              numberSystemVisible;
    private boolean                      defaultBlinking = false;
    private BooleanProperty              blinking;
    private boolean                      defaultBackgroundVisible = true;
    private BooleanProperty              backgroundVisible;
    private boolean                      defaultCrystalOverlayVisible = false;
    private BooleanProperty              crystalOverlayVisible;
    private boolean                      defaultForegroundShadowVisible = false;
    private BooleanProperty              foregroundShadowVisible;
    private boolean                      defaultTitleVisible = true;
    private BooleanProperty              titleVisible;
    private boolean                      defaultUnitVisible = true;
    private BooleanProperty              unitVisible;
    private boolean                      defaultTrendVisible = false;
    private BooleanProperty              trendVisible;
    private boolean                      defaultBatteryVisible = false;
    private BooleanProperty              batteryVisible;
    private boolean                      defaultAlarmVisible = false;
    private BooleanProperty              alarmVisible;
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
        value                     = new SimpleDoubleProperty(0);
        currentValue              = new SimpleDoubleProperty(0);
        formerValue               = new SimpleDoubleProperty(0);
        initialized               = false;
        firstTime                 = true;
        keepAspect                = true;
        toggleValue               = false;
        toggleThreshold           = false;
        interval                  = 500_000_000l;
        lastBlinkTimerCall        = System.nanoTime();
        lastThresholdTimerCall    = System.nanoTime();
        timer                     = new AnimationTimer() {
            @Override public void handle(final long NOW) {
                if (isBlinking() && NOW > lastBlinkTimerCall + interval) {
                    toggleValue ^= true;
                    setValueVisible(toggleValue);
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
        currentValueProperty().addListener(new InvalidationListener() {
            @Override public void invalidated(Observable observable) {
                if (Double.compare(currentValue.get(), getMinMeasuredValue()) < 0 && !firstTime) {
                    setMinMeasuredValue(currentValue.get());
                }
                if (Double.compare(currentValue.get(), getMaxMeasuredValue()) > 0 && !firstTime) {
                    setMaxMeasuredValue(currentValue.get());
                }
                validateThreshold();
            }
        });
    }


    // ******************** Methods *******************************************
    public final boolean isKeepAspect() {
        return keepAspect;
    }
    public final void setKeepAspect(final boolean KEEP_ASPECT) {
        keepAspect = KEEP_ASPECT;
    }

    @Override public boolean isResizable() {
        return true;
    }

    public final boolean isTextMode() {
        return null == textMode ? defaultTextMode : textMode.get();
    }
    public final void setTextMode(final boolean TEXT_MODE) {
        if (null == textMode) {
            defaultTextMode = TEXT_MODE;
        } else {
            textMode.set(TEXT_MODE);
        }
    }
    public final BooleanProperty textModeProperty() {
        if (null == textMode) {
            textMode = new SimpleBooleanProperty(this, "textMode", defaultTextMode);
        }
        return textMode;
    }

    public final String getText() {
        return null == text ? defaultText : text.get();
    }
    public final void setText(final String TEXT) {
        if (null == text) {
            defaultText = TEXT;
        } else {
            text.set(TEXT);
        }
    }
    public final StringProperty textProperty() {
        if (null == text) {
            text = new SimpleStringProperty(this, "text", defaultText);
        }
        return text;
    }

    public final double getValue() {
        return value.get();
    }
    public final void setValue(final double VALUE) {
        formerValue.set(value.get());
        value.set(clamp(getMinValue(), getMaxValue(), VALUE));
    }
    public final ReadOnlyDoubleProperty valueProperty() {
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
        return null == valueAnimationEnabled ? defaultValueAnimationEnabled : valueAnimationEnabled.get();
    }
    public final void setValueAnimationEnabled(final boolean VALUE_ANIMATION_ENABLED) {
        if (null == valueAnimationEnabled) {
            defaultValueAnimationEnabled = VALUE_ANIMATION_ENABLED;
        } else {
            valueAnimationEnabled.set(VALUE_ANIMATION_ENABLED);
        }
    }
    public final BooleanProperty valueAnimationEnabledProperty() {
        if (null == valueAnimationEnabled) {
            valueAnimationEnabled = new SimpleBooleanProperty(this, "valueAnimationEnabled", defaultValueAnimationEnabled);
        }
        return valueAnimationEnabled;
    }

    public final double getAnimationDuration() {
        return null == animationDuration ? defaultAnimationDuration : animationDuration.get();
    }
    public final void setAnimationDuration(final double ANIMATION_DURATION) {
        if (null == animationDuration) {
            defaultAnimationDuration = ANIMATION_DURATION;
        } else {
            animationDuration.set(ANIMATION_DURATION);
        }
    }
    public final DoubleProperty animationDurationProperty() {
        if (null == animationDuration) {
            animationDuration = new SimpleDoubleProperty(this, "animationDuration", defaultAnimationDuration);
        }
        return animationDuration;
    }

    public final double getMinValue() {
        return null == minValue ? defaultMinValue : minValue.get();
    }
    public final void setMinValue(final double MIN_VALUE) {
        if (null == minValue) {
            defaultMinValue = MIN_VALUE;
        } else {
            minValue.set(MIN_VALUE);
        }
    }
    public final DoubleProperty minValueProperty() {
        if (null == minValue) {
            minValue = new SimpleDoubleProperty(this, "minValue", defaultMinValue);
        }
        return minValue;
    }

    public final double getMaxValue() {
        return null == maxValue ? defaultMaxValue : maxValue.get();
    }
    public final void setMaxValue(final double MAX_VALUE) {
        if (null == maxValue) {
            defaultMaxValue = MAX_VALUE;
        } else {
            maxValue.set(MAX_VALUE);
        }
    }
    public final DoubleProperty maxValueProperty() {
        if (null == maxValue) {
            maxValue = new SimpleDoubleProperty(this, "maxValue", defaultMaxValue);
        }
        return maxValue;
    }

    public final double getRange() {
        if (null == maxValue && null != minValue) {
            return defaultMaxValue - minValue.get();
        } else if (null != maxValue && null == minValue) {
            return maxValue.get() - defaultMinValue;
        } else if (null == maxValue && null == minValue) {
            return defaultMaxValue - defaultMinValue;
        } else {
            return maxValue.subtract(minValue).get();
        }
    }

    public final double getMinMeasuredValue() {
        return null == minMeasuredValue ? defaultMinMeasuredValue : minMeasuredValue.get();
    }
    public final void setMinMeasuredValue(final double MIN_MEASURED_VALUE) {
        if (null == minMeasuredValue) {
            defaultMinMeasuredValue = MIN_MEASURED_VALUE;
        } else {
            minMeasuredValue.set(MIN_MEASURED_VALUE);
        }
    }
    public final DoubleProperty minMeasuredValueProperty() {
        if (null == minMeasuredValue) {
            minMeasuredValue = new SimpleDoubleProperty(this, "minMeasuredValue", defaultMinMeasuredValue);
        }
        return minMeasuredValue;
    }
    public final void resetMinMeasuredValue() {
        setMinMeasuredValue(getValue());
    }

    public final double getMaxMeasuredValue() {
        return null == maxMeasuredValue ? defaultMinMeasuredValue : maxMeasuredValue.get();
    }
    public final void setMaxMeasuredValue(final double MAX_MEASURED_VALUE) {
        if (null == maxMeasuredValue) {
            defaultMaxMeasuredValue = MAX_MEASURED_VALUE;
        } else {
            maxMeasuredValue.set(MAX_MEASURED_VALUE);
        }
    }
    public final DoubleProperty maxMeasuredValueProperty() {
        if (null == maxMeasuredValue) {
            maxMeasuredValue = new SimpleDoubleProperty(this, "maxMeasuredValue", defaultMaxMeasuredValue);
        }
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
        return null == threshold ? defaultThreshold : threshold.get();
    }
    public final void setThreshold(final double THRESHOLD) {
        if (null == threshold) {
            defaultThreshold = clamp(getMinValue(), getMaxValue(), THRESHOLD);
        } else {
            threshold.set(clamp(getMinValue(), getMaxValue(), THRESHOLD));
        }
        validateThreshold();
    }
    public final ReadOnlyDoubleProperty thresholdProperty() {
        if (null == threshold) {
            threshold = new SimpleDoubleProperty(this, "threshold", defaultThreshold);
        }
        return threshold;
    }

    public final boolean isThresholdBehaviorInverted() {
        return null == thresholdBehaviorInverted ? defaultThresholdBehaviorInverted : thresholdBehaviorInverted.get();
    }
    public final void setThresholdBehaviorInverted(final boolean THRESHOLD_BEHAVIOR_INVERTED) {
        if (null == thresholdBehaviorInverted) {
            defaultThresholdBehaviorInverted = THRESHOLD_BEHAVIOR_INVERTED;
        } else {
            thresholdBehaviorInverted.set(THRESHOLD_BEHAVIOR_INVERTED);
        }
    }
    public final BooleanProperty thresholdBehaviorInvertedProperty() {
        if (null == thresholdBehaviorInverted) {
            thresholdBehaviorInverted = new SimpleBooleanProperty(this, "thresholdBehaviorInverted", defaultThresholdBehaviorInverted);
        }
        return thresholdBehaviorInverted;
    }

    public final boolean isThresholdExceeded() {
        return null == thresholdExceeded ? defaultThresholdExceeded : thresholdExceeded.get();
    }
    public final void setThresholdExceeded(final boolean THRESHOLD_EXCEEDED) {
        if (null == thresholdExceeded) {
            defaultThresholdExceeded = THRESHOLD_EXCEEDED;
        } else {
            thresholdExceeded.set(THRESHOLD_EXCEEDED);
        }
    }
    public final BooleanProperty thresholdExceededProperty() {
        if (null == thresholdExceeded) {
            thresholdExceeded = new SimpleBooleanProperty(this, "thresholdExceeded", defaultThresholdExceeded);
        }
        return thresholdExceeded;
    }

    public final String getTitle() {
        return null == title ? defaultTitle : title.get();
    }
    public final void setTitle(final String TITLE) {
        if (null == title) {
            defaultTitle = TITLE;
        } else {
            title.set(TITLE);
        }
    }
    public final StringProperty titleProperty() {
        if (null == title) {
            title = new SimpleStringProperty(this, "title", defaultTitle);
        }
        return title;
    }

    public final String getUnit() {
        return null == unit ? defaultUnit : unit.get();
    }
    public final void setUnit(final String UNIT) {
        if (null == unit) {
            defaultUnit = UNIT;
        } else {
            unit.set(UNIT);
        }
    }
    public final StringProperty unitProperty() {
        if (null == unit) {
            unit = new SimpleStringProperty(this, "unit", defaultUnit);
        }
        return unit;
    }

    public final String getLowerCenterText() {
        return null == lowerCenterText ? defaultLowerCenterText : lowerCenterText.get();
    }
    public final void setLowerCenterText(final String LOWER_CENTER_TEXT) {
        if (null == lowerCenterText) {
            defaultLowerCenterText = LOWER_CENTER_TEXT;
        } else {
            lowerCenterText.set(LOWER_CENTER_TEXT);
        }
    }
    public final StringProperty lowerCenterTextProperty() {
        if (null == lowerCenterText) {
            lowerCenterText = new SimpleStringProperty(this, "lowerCenterText", defaultLowerCenterText);
        }
        return lowerCenterText;
    }

    public final boolean isLowerCenterTextVisible() {
        return null == lowerCenterTextVisible ? defaultLowerCenterCenterTextVisible : lowerCenterTextVisible.get();
    }
    public final void setLowerCenterTextVisible(final boolean LOWER_CENTER_TEXT_VISIBLE) {
        if (null == lowerCenterTextVisible) {
            defaultLowerCenterCenterTextVisible = LOWER_CENTER_TEXT_VISIBLE;
        } else {
            lowerCenterTextVisible.set(LOWER_CENTER_TEXT_VISIBLE);
        }
    }
    public final BooleanProperty lowerCenterTextVisibleProperty() {
        if (null == lowerCenterTextVisible) {
            lowerCenterTextVisible = new SimpleBooleanProperty(this, "lowerCenterTextVisible", defaultLowerCenterCenterTextVisible);
        }
        return lowerCenterTextVisible;
    }

    public final String getLowerRightText() {
        return null == lowerRightText ? defaultLowerRightText : lowerRightText.get();
    }
    public final void setLowerRightText(final String LOWER_RIGHT_TEXT) {
        if (null == lowerRightText) {
            defaultLowerRightText = LOWER_RIGHT_TEXT;
        } else {
            lowerRightText.set(LOWER_RIGHT_TEXT);
        }
    }
    public final StringProperty lowerRightTextProperty() {
        if (null == lowerRightText) {
            lowerRightText = new SimpleStringProperty(this, "lowerRightText", defaultLowerRightText);
        }
        return lowerRightText;
    }

    public final boolean isLowerRightTextVisible() {
        return null == lowerRightTextVisible ? defaultLowerRightTextVisible : lowerRightTextVisible.get();
    }
    public final void setLowerRightTextVisible(final boolean LOWER_RIGHT_TEXT_VISIBLE) {
        if (null == lowerRightTextVisible) {
            defaultLowerRightTextVisible = LOWER_RIGHT_TEXT_VISIBLE;
        } else {
            lowerRightTextVisible.set(LOWER_RIGHT_TEXT_VISIBLE);
        }
    }
    public final BooleanProperty lowerRightTextVisibleProperty() {
        if (null == lowerRightTextVisible) {
            lowerRightTextVisible = new SimpleBooleanProperty(this, "lowerRightTextVisible", defaultLowerRightTextVisible);
        }
        return lowerRightTextVisible;
    }

    public final String getUpperLeftText() {
        return null == upperLeftText ? defaultUpperLeftText : upperLeftText.get();
    }
    public final void setUpperLeftText(final String UPPER_LEFT_TEXT) {
        if (null == upperLeftText) {
            defaultUpperLeftText = UPPER_LEFT_TEXT;
        } else {
            upperLeftText.set(UPPER_LEFT_TEXT);
        }
    }
    public final StringProperty upperLeftTextProperty() {
        if (null == upperLeftText) {
            upperLeftText = new SimpleStringProperty(this, "upperLeftText", defaultUpperLeftText);
        }
        return upperLeftText;
    }

    public final boolean isUpperLeftTextVisible() {
        return null == upperLeftTextVisible ? defaultUpperLeftTextVisible : upperLeftTextVisible.get();
    }
    public final void setUpperLeftTextVisible(final boolean UPPER_LEFT_TEXT_VISIBLE) {
        if (null == upperLeftTextVisible) {
            defaultUpperLeftTextVisible = UPPER_LEFT_TEXT_VISIBLE;
        } else {
            upperLeftTextVisible.set(UPPER_LEFT_TEXT_VISIBLE);
        }
    }
    public final BooleanProperty upperLeftTextVisibleProperty() {
        if (null == upperLeftTextVisible) {
            upperLeftTextVisible = new SimpleBooleanProperty(this, "upperLeftTextVisible", defaultUpperLeftTextVisible);
        }
        return upperLeftTextVisible;
    }

    public final String getUpperRightText() {
        return null == upperRightText ? defaultUpperRightText : upperRightText.get();
    }
    public final void setUpperRightText(final String UPPER_RIGHT_TEXT) {
        if (null == upperRightText) {
            defaultUpperRightText = UPPER_RIGHT_TEXT;
        } else {
            upperRightText.set(UPPER_RIGHT_TEXT);
        }
    }
    public final StringProperty upperRightTextProperty() {
        if (null == upperRightText) {
            upperRightText = new SimpleStringProperty(this, "upperRightText", defaultUpperRightText);
        }
        return upperRightText;
    }

    public final boolean isUpperRightTextVisible() {
        return null == upperRightTextVisible ? defaultUpperRightTextVisible : upperRightTextVisible.get();
    }
    public final void setUpperRightTextVisible(final boolean UPPER_RIGHT_TEXT_VISIBLE) {
        if (null == upperRightTextVisible) {
            defaultUpperRightTextVisible = UPPER_RIGHT_TEXT_VISIBLE;
        } else {
            upperRightTextVisible.set(UPPER_RIGHT_TEXT_VISIBLE);
        }
    }
    public final BooleanProperty upperRightTextVisibleProperty() {
        if (null == upperRightTextVisible) {
            upperRightTextVisible = new SimpleBooleanProperty(this, "upperRightTextVisible", defaultUpperRightTextVisible);
        }
        return upperRightTextVisible;
    }

    public final NumberSystem getNumberSystem() {
        return null == numberSystem ? defaultNumberSystem : numberSystem.get();
    }
    public final void setNumberSystem(final NumberSystem NUMBER_SYSTEM) {
        if (null == numberSystem) {
            defaultNumberSystem = NUMBER_SYSTEM;
        } else {
            numberSystem.set(NUMBER_SYSTEM);
        }
    }
    public final ObjectProperty<NumberSystem> numberSystemProperty() {
        if (null == numberSystem) {
            numberSystem = new SimpleObjectProperty<>(this, "numberSystem", defaultNumberSystem);
        }
        return numberSystem;
    }

    public final Trend getTrend() {
        return null == trend ? defaultTrend : trend.get();
    }
    public final void setTrend(final Trend TREND) {
        if (null == trend ) {
            defaultTrend = TREND;
        } else {
            trend.set(TREND);
        }
    }
    public final ObjectProperty<Trend> trendProperty() {
        if (null == trend) {
            trend = new SimpleObjectProperty<>(this, "trend", defaultTrend);
        }
        return trend;
    }

    public final double getBatteryCharge() {
        return null == batteryCharge ? defaultBatteryCharge : batteryCharge.get();
    }
    public final void setBatteryCharge(final double BATTERY_CHARGE) {
        if (null == batteryCharge) {
            defaultBatteryCharge = clamp(0.0, 1.0, BATTERY_CHARGE);
        } else {
            batteryCharge.set(clamp(0.0, 1.0, BATTERY_CHARGE));
        }
    }
    public final DoubleProperty batteryChargeProperty() {
        if (null == batteryCharge) {
            batteryCharge = new SimpleDoubleProperty(this, "batteryCharge", defaultBatteryCharge);
        }
        return batteryCharge;
    }

    public final boolean isValueVisible() {
        return null == valueVisible ? defaultValueVisible : valueVisible.get();
    }
    private final void setValueVisible(final boolean VALUE_VISIBLE) {
        if (null == valueVisible) {
            defaultValueVisible = VALUE_VISIBLE;
        } else {
            valueVisible.set(VALUE_VISIBLE);
        }
    }
    public final ReadOnlyBooleanProperty valueVisibleProperty() {
        if (null == valueVisible) {
            valueVisible = new SimpleBooleanProperty(this, "valueVisible", defaultValueVisible);
        }
        return valueVisible;
    }

    public final boolean isMinMeasuredValueVisible() {
        return null == minMeasuredValueVisible ? defaultMinMeasuredValueVisible : minMeasuredValueVisible.get();
    }
    public final void setMinMeasuredValueVisible(final boolean MIN_MEASURED_VALUE_VISIBLE) {
        if (null == minMeasuredValueVisible) {
            defaultMinMeasuredValueVisible = MIN_MEASURED_VALUE_VISIBLE;
        } else {
            minMeasuredValueVisible.set(MIN_MEASURED_VALUE_VISIBLE);
        }
    }
    public final BooleanProperty minMeasuredValueVisibleProperty() {
        if (null == minMeasuredValueVisible) {
            minMeasuredValueVisible = new SimpleBooleanProperty(this, "minMeasuredValueVisible", defaultMinMeasuredValueVisible);
        }
        return minMeasuredValueVisible;
    }

    public final boolean isMaxMeasuredValueVisible() {
        return null == maxMeasuredValueVisible ? defaultMaxMeasuredValueVisible : maxMeasuredValueVisible.get();
    }
    public final void setMaxMeasuredValueVisible(final boolean MAX_MEASURED_VALUE_VISIBLE) {
        if (null == maxMeasuredValueVisible) {
            defaultMaxMeasuredValueVisible = MAX_MEASURED_VALUE_VISIBLE;
        } else {
            maxMeasuredValueVisible.set(MAX_MEASURED_VALUE_VISIBLE);
        }
    }
    public final BooleanProperty maxMeasuredValueVisibleProperty() {
        if (null == maxMeasuredValueVisible) {
            maxMeasuredValueVisible = new SimpleBooleanProperty(this, "maxMeasureValueVisible", defaultMaxMeasuredValueVisible);
        }
        return maxMeasuredValueVisible;
    }

    public final boolean isThresholdVisible() {
        return null == thresholdVisible ? defaultThresholdVisible : thresholdVisible.get();
    }
    public final void setThresholdVisible(final boolean THRESHOLD_VISIBLE) {
        if (null == thresholdVisible) {
            defaultThresholdVisible = THRESHOLD_VISIBLE;
        } else {
            thresholdVisible.set(THRESHOLD_VISIBLE);
        }
    }
    public final BooleanProperty thresholdVisibleProperty() {
        if (null == thresholdVisible) {
            thresholdVisible = new SimpleBooleanProperty(this, "thresholdVisible", defaultThresholdVisible);
        }
        return thresholdVisible;
    }

    public final boolean isBackgroundVisible() {
        return null == backgroundVisible ? defaultBackgroundVisible : backgroundVisible.get();
    }
    public final void setBackgroundVisible(final boolean BACKGROUND_VISIBLE) {
        if (null == backgroundVisible) {
            defaultBackgroundVisible = BACKGROUND_VISIBLE;
        } else {
            backgroundVisible.set(BACKGROUND_VISIBLE);
        }
    }
    public final BooleanProperty backgroundVisibleProperty() {
        if (null == backgroundVisible) {
            backgroundVisible = new SimpleBooleanProperty(this, "backgroundVisible", defaultBackgroundVisible);
        }
        return backgroundVisible;
    }

    public final boolean isCrystalOverlayVisible() {
        return null == crystalOverlayVisible ? defaultCrystalOverlayVisible : crystalOverlayVisible.get();
    }
    public final void setCrystalOverlayVisible(final boolean CRYSTAL_OVERLAY_VISIBLE) {
        if (null == crystalOverlayVisible) {
            defaultCrystalOverlayVisible = CRYSTAL_OVERLAY_VISIBLE;
        } else {
            crystalOverlayVisible.set(CRYSTAL_OVERLAY_VISIBLE);
        }
    }
    public final BooleanProperty crystalOverlayVisibleProperty() {
        if (null == crystalOverlayVisible) {
            crystalOverlayVisible = new SimpleBooleanProperty(this, "crystalOverlayVisible", defaultCrystalOverlayVisible);
        }
        return crystalOverlayVisible;
    }

    public final boolean isForegroundShadowVisible() {
        return null == foregroundShadowVisible ? defaultForegroundShadowVisible : foregroundShadowVisible.get();
    }
    public final void setForegroundShadowVisible(final boolean FOREGROUND_SHADOW_VISIBLE) {
        if (null == foregroundShadowVisible) {
            defaultForegroundShadowVisible = FOREGROUND_SHADOW_VISIBLE;
        } else {
            foregroundShadowVisible.set(FOREGROUND_SHADOW_VISIBLE);
        }
    }
    public final BooleanProperty foregroundShadowVisibleProperty() {
        if (null == foregroundShadowVisible) {
            foregroundShadowVisible = new SimpleBooleanProperty(this, "foregroundShadowVisible", defaultForegroundShadowVisible);
        }
        return foregroundShadowVisible;
    }

    public final String getTitleFont() {
        return null == titleFont ? defaultTitleFont : titleFont.get();
    }
    public final void setTitleFont(final String TITLE_FONT) {
        if (null == titleFont) {
            defaultTitleFont = TITLE_FONT;
        } else {
            titleFont.set(TITLE_FONT);
        }
    }
    public final StringProperty titleFontProperty() {
        if (null == titleFont) {
            titleFont = new SimpleStringProperty(this, "titleFont", defaultTitleFont);
        }
        return titleFont;
    }

    public final String getUnitFont() {
        return null == unitFont ? defaultUnitFont : unitFont.get();
    }
    public final void setUnitFont(final String UNIT_FONT) {
        if (null == unitFont) {
            defaultUnitFont = UNIT_FONT;
        } else {
            unitFont.set(UNIT_FONT);
        }
    }
    public final StringProperty unitFontProperty() {
        if (null == unitFont) {
            unitFont = new SimpleStringProperty(this, "unitFont", defaultUnitFont);
        }
        return unitFont;
    }
    
    public final LcdFont getValueFont() {
        return null == valueFont ? defaultValueFont : valueFont.get();
    }
    public final void setValueFont(final LcdFont VALUE_FONT) {
        if (null == valueFont) {
            defaultValueFont = VALUE_FONT;
        } else {
            valueFont.set(VALUE_FONT);
        }
    }
    public final ObjectProperty<LcdFont> valueFontProperty() {
        if (null == valueFont) {
            valueFont = new SimpleObjectProperty<>(this, "valueFont", defaultValueFont);
        }
        return valueFont;
    }

    public final int getDecimals() {
        return null == decimals ? defaultDecimals : decimals.get();
    }
    public final void setDecimals(final int DECIMALS) {
        final int dec = DECIMALS > 5 ? 5 : (DECIMALS < 0 ? 0 : DECIMALS);
        if (null == decimals) {
            defaultDecimals = dec;
        } else {
            decimals.set(dec);
        }
    }
    public final IntegerProperty decimalsProperty() {
        if (null == decimals) {
            decimals = new SimpleIntegerProperty(this, "decimals", defaultDecimals);
        }
        return decimals;
    }

    public final boolean isNumberSystemVisible() {
        return null == numberSystemVisible ? defaultNumberSystemVisible : numberSystemVisible.get();
    }
    public final void setNumberSystemVisible(final boolean NUMBER_SYSTEM_VISIBLE) {
        if (null == numberSystemVisible) {
            defaultNumberSystemVisible = NUMBER_SYSTEM_VISIBLE;
        } else {
            numberSystemVisible.set(NUMBER_SYSTEM_VISIBLE);
        }
    }
    public final BooleanProperty numberSystemVisibleProperty() {
        if (null == numberSystemVisible) {
            numberSystemVisible = new SimpleBooleanProperty(this, "numberSystemVisible", defaultNumberSystemVisible);
        }
        return numberSystemVisible;
    }

    public final boolean isBlinking() {
        return null == blinking ? defaultBlinking : blinking.get();
    }
    public final void setBlinking(final boolean BLINKING) {
        if (null == blinking) {
            defaultBlinking = BLINKING;
        } else {
            blinking.set(BLINKING);
        }
        if (BLINKING) {
            timer.start();
        } else {
            timer.stop();
            setValueVisible(true);
        }
    }
    public final BooleanProperty blinkingProperty() {
        if (null == blinking) {
            blinking = new SimpleBooleanProperty(this, "blinking", defaultBlinking);
        }
        return blinking;
    }

    public final boolean isTitleVisible() {
        return null == titleVisible ? defaultTitleVisible : titleVisible.get();
    }
    public final void setTitleVisible(final boolean TITLE_VISIBLE) {
        if (null == titleVisible) {
            defaultTitleVisible = TITLE_VISIBLE;
        } else {
            titleVisible.set(TITLE_VISIBLE);
        }
    }
    public final BooleanProperty titleVisibleProperty() {
        if (null == titleVisible) {
            titleVisible = new SimpleBooleanProperty(this, "titleVisible", defaultTitleVisible);
        }
        return titleVisible;
    }

    public final boolean isUnitVisible() {
        return null == unitVisible ? defaultUnitVisible : unitVisible.get();
    }
    public final void setUnitVisible(final boolean UNIT_VISIBLE) {
        if (null == unitVisible) {
            defaultUnitVisible = UNIT_VISIBLE;
        } else {
            unitVisible.set(UNIT_VISIBLE);
        }
    }
    public final BooleanProperty unitVisibleProperty() {
        if (null == unitVisible) {
            unitVisible = new SimpleBooleanProperty(this, "unitVisible", defaultUnitVisible);
        }
        return unitVisible;
    }

    public final boolean isTrendVisible() {
        return null == trendVisible ? defaultTrendVisible : trendVisible.get();
    }
    public final void setTrendVisible(final boolean TREND_VISIBLE) {
        if (null == trendVisible) {
            defaultTrendVisible = TREND_VISIBLE;
        } else {
            trendVisible.set(TREND_VISIBLE);
        }
    }
    public final BooleanProperty trendVisibleProperty() {
        if (null == trendVisible) {
            trendVisible = new SimpleBooleanProperty(this, "trendVisible", defaultTrendVisible);
        }
        return trendVisible;
    }

    public final boolean isBatteryVisible() {
        return null == batteryVisible ? defaultBatteryVisible : batteryVisible.get();
    }
    public final void setBatteryVisible(final boolean BATTERY_VISIBLE) {
        if (null == batteryVisible) {
            defaultBatteryVisible = BATTERY_VISIBLE;
        } else {
            batteryVisible.set(BATTERY_VISIBLE);
        }
    }
    public final BooleanProperty batteryVisibleProperty() {
        if (null == batteryVisible) {
            batteryVisible = new SimpleBooleanProperty(this, "batteryVisible", defaultBatteryVisible);
        }
        return batteryVisible;
    }

    public final boolean isAlarmVisible() {
        return null == alarmVisible ? defaultAlarmVisible : alarmVisible.get();
    }
    public final void setAlarmVisible(final boolean ALARM_VISIBLE) {
        if (null == alarmVisible) {
            defaultAlarmVisible = ALARM_VISIBLE;
        } else {
            alarmVisible.set(ALARM_VISIBLE);
        }
    }
    public final BooleanProperty alarmVisibleProperty() {
        if (null == alarmVisible) {
            alarmVisible = new SimpleBooleanProperty(this, "alarmVisible", defaultAlarmVisible);
        }
        return alarmVisible;
    }

    public final boolean isFormerValueVisible() {
        return null == formerValueVisible ? defaultFormerValueVisible : formerValueVisible.get();
    }
    public final void setFormerValueVisible(final boolean FORMER_VALUE_VISIBLE) {
        if (null == formerValueVisible) {
            defaultFormerValueVisible = FORMER_VALUE_VISIBLE;
        } else {
            formerValueVisible.set(FORMER_VALUE_VISIBLE);
        }
    }
    public final BooleanProperty formerValueVisibleProperty() {
        if (null == formerValueVisible) {
            formerValueVisible = new SimpleBooleanProperty(this, "formerValueVisible", defaultFormerValueVisible);
        }
        return formerValueVisible;
    }

    public final int getMinMeasuredValueDecimals() {
        return null == minMeasuredValueDecimals ? defaultMinMeasuredValueDecimals : minMeasuredValueDecimals.get();
    }
    public final void setMinMeasuredValueDecimals(final int MIN_MEASURED_VALUE_DECIMALS) {
        final int DECIMALS = MIN_MEASURED_VALUE_DECIMALS > 5 ? 5 : (MIN_MEASURED_VALUE_DECIMALS < 0 ? 0 : MIN_MEASURED_VALUE_DECIMALS);
        if (null == minMeasuredValueDecimals) {
            defaultMinMeasuredValueDecimals = MIN_MEASURED_VALUE_DECIMALS;
        } else {
            minMeasuredValueDecimals.set(DECIMALS);
        }
    }
    public final IntegerProperty minMeasuredValueDecimalsProperty() {
        if (null == minMeasuredValueDecimals) {
            minMeasuredValueDecimals = new SimpleIntegerProperty(this, "minMeasuredValueDecimals", defaultMinMeasuredValueDecimals);
        }
        return maxMeasuredValueDecimals;
    }

    public final int getMaxMeasuredValueDecimals() {
        return null == maxMeasuredValueDecimals ? defaultMaxMeasuredValueDecimals : maxMeasuredValueDecimals.get();
    }
    public final void setMaxMeasuredValueDecimals(final int MAX_MEASURED_VALUE_DECIMALS) {
        final int DECIMALS = MAX_MEASURED_VALUE_DECIMALS > 5 ? 5 : (MAX_MEASURED_VALUE_DECIMALS < 0 ? 0 : MAX_MEASURED_VALUE_DECIMALS);
        if (null == maxMeasuredValueDecimals) {
            defaultMaxMeasuredValueDecimals = MAX_MEASURED_VALUE_DECIMALS;
        } else {
            maxMeasuredValueDecimals.set(DECIMALS);
        }
    }
    public final IntegerProperty maxMeasuredValueDecimalsProperty() {
        if (null == maxMeasuredValueDecimals) {
            maxMeasuredValueDecimals = new SimpleIntegerProperty(this, "maxMeasuredValueDecimals", defaultMaxMeasuredValueDecimals);
        }
        return maxMeasuredValueDecimals;
    }


    // ******************** Utility Methods ***********************************
    public static double clamp(final double MIN, final double MAX, final double VALUE) {
        if (VALUE < MIN) return MIN;
        if (VALUE > MAX) return MAX;
        return VALUE;
    }

    private void validateThreshold() {
        if (initialized) {
            if (isThresholdBehaviorInverted()) {
                setThresholdExceeded(currentValue.get() < getThreshold());
            } else {
                setThresholdExceeded(currentValue.get() > getThreshold());
            }
        }
    }


    // ******************** Style related *************************************
    @Override protected String getUserAgentStylesheet() {
        return getClass().getResource(getClass().getSimpleName().toLowerCase() + ".css").toExternalForm();
    }
}
