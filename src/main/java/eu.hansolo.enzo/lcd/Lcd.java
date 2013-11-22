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

package eu.hansolo.enzo.lcd;

import eu.hansolo.enzo.common.ValueEvent;
import eu.hansolo.enzo.lcd.skin.LcdSkin;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.BooleanPropertyBase;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.DoublePropertyBase;
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
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.util.Duration;


public class Lcd extends Control {
    public static final String STYLE_CLASS_BEIGE              = "lcd-beige";
    public static final String STYLE_CLASS_BLUE               = "lcd-blue";
    public static final String STYLE_CLASS_ORANGE             = "lcd-orange";
    public static final String STYLE_CLASS_RED                = "lcd-red";
    public static final String STYLE_CLASS_YELLOW             = "lcd-yellow";
    public static final String STYLE_CLASS_WHITE              = "lcd-white";
    public static final String STYLE_CLASS_GRAY               = "lcd-gray";
    public static final String STYLE_CLASS_BLACK              = "lcd-black";
    public static final String STYLE_CLASS_GREEN              = "lcd-green";
    public static final String STYLE_CLASS_GREEN_DARKGREEN    = "lcd-green-darkgreen";
    public static final String STYLE_CLASS_BLUE2              = "lcd-blue2";
    public static final String STYLE_CLASS_BLUE_BLACK         = "lcd-blue-black";
    public static final String STYLE_CLASS_BLUE_DARKBLUE      = "lcd-blue-darkblue";
    public static final String STYLE_CLASS_BLUE_LIGHTBLUE     = "lcd-blue-lightblue";
    public static final String STYLE_CLASS_BLUE_GRAY          = "lcd-blue-gray";
    public static final String STYLE_CLASS_STANDARD           = "lcd-standard";
    public static final String STYLE_CLASS_LIGHTGREEN         = "lcd-lightgreen";
    public static final String STYLE_CLASS_STANDARD_GREEN     = "lcd-standard-green";
    public static final String STYLE_CLASS_BLUE_BLUE          = "lcd-blue-blue";
    public static final String STYLE_CLASS_RED_DARKRED        = "lcd-red-darkred";
    public static final String STYLE_CLASS_DARKBLUE           = "lcd-darkblue";
    public static final String STYLE_CLASS_PURPLE             = "lcd-purple";
    public static final String STYLE_CLASS_BLACK_RED          = "lcd-black-red";
    public static final String STYLE_CLASS_DARKGREEN          = "lcd-darkgreen";
    public static final String STYLE_CLASS_AMBER              = "lcd-amber";
    public static final String STYLE_CLASS_LIGHTBLUE          = "lcd-lightblue";
    public static final String STYLE_CLASS_GREEN_BLACK        = "lcd-green-black";
    public static final String STYLE_CLASS_YELLOW_BLACK       = "lcd-yellow-black";
    public static final String STYLE_CLASS_BLACK_YELLOW       = "lcd-black-yellow";
    public static final String STYLE_CLASS_LIGHTGREEN_BLACK   = "lcd-lightgreen-black";
    public static final String STYLE_CLASS_DARKPURPLE         = "lcd-darkpurple";
    public static final String STYLE_CLASS_DARKAMBER          = "lcd-darkamber";
    public static final String STYLE_CLASS_BLUE_LIGHTBLUE2    = "lcd-blue-lightblue2";
    public static final String STYLE_CLASS_GRAY_PURPLE        = "lcd-gray-purple";
    public static final String STYLE_CLASS_SECTIONS           = "lcd-sections";
    public static final String STYLE_CLASS_YOCTOPUCE          = "lcd-yoctopuce";

    public static final String STYLE_CLASS_FLAT_TURQOISE      = "lcd-flat-turqoise";
    public static final String STYLE_CLASS_FLAT_GREEN_SEA     = "lcd-flat-green-sea";
    public static final String STYLE_CLASS_FLAT_EMERLAND      = "lcd-flat-emerland";
    public static final String STYLE_CLASS_FLAT_NEPHRITIS     = "lcd-flat-nephritis";
    public static final String STYLE_CLASS_FLAT_PETER_RIVER   = "lcd-flat-peter-river";
    public static final String STYLE_CLASS_FLAT_BELIZE_HOLE   = "lcd-flat-belize-hole";
    public static final String STYLE_CLASS_FLAT_AMETHYST      = "lcd-flat-amethyst";
    public static final String STYLE_CLASS_FLAT_WISTERIA      = "lcd-flat-wisteria";
    public static final String STYLE_CLASS_FLAT_SUNFLOWER     = "lcd-flat-sunflower";
    public static final String STYLE_CLASS_FLAT_ORANGE        = "lcd-flat-orange";
    public static final String STYLE_CLASS_FLAT_CARROT        = "lcd-flat-carrot";
    public static final String STYLE_CLASS_FLAT_PUMPKIN       = "lcd-flat-pumpkin";
    public static final String STYLE_CLASS_FLAT_ALIZARIN      = "lcd-flat-alizarin";
    public static final String STYLE_CLASS_FLAT_POMEGRANATE   = "lcd-flat-pomegranate";
    public static final String STYLE_CLASS_FLAT_CLOUDS        = "lcd-flat-clouds";
    public static final String STYLE_CLASS_FLAT_SILVER        = "lcd-flat-silver";
    public static final String STYLE_CLASS_FLAT_CONCRETE      = "lcd-flat-concrete";
    public static final String STYLE_CLASS_FLAT_ASBESTOS      = "lcd-flat-asbestos";
    public static final String STYLE_CLASS_FLAT_WET_ASPHALT   = "lcd-flat-wet-asphalt";
    public static final String STYLE_CLASS_FLAT_MIDNIGHT_BLUE = "lcd-flat-midnight-blue";
    public static enum LcdFont {
        STANDARD,
        LCD,
        DIGITAL,
        DIGITAL_BOLD,
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

    // CSS pseudo classes
    private static final PseudoClass     NO_FRAME_PSEUDO_CLASS = PseudoClass.getPseudoClass("no-frame");
    private BooleanProperty              noFrame;

    private boolean                      initialized;
    private boolean                      firstTime;
    private boolean                      keepAspect;
    private boolean                      _textMode = false;
    private BooleanProperty              textMode;
    private String                       _text = "";
    private StringProperty               text;
    private DoubleProperty               value;
    private DoubleProperty               currentValue;
    private DoubleProperty               formerValue;
    private double                       _minValue = 0.0;
    private DoubleProperty               minValue;
    private double                       _maxValue = 100.0;
    private DoubleProperty               maxValue;
    private boolean                      _animated = true;
    private BooleanProperty              animated;
    private double                       _animationDuration = 800;
    private DoubleProperty               animationDuration;
    private double                       _minMeasuredValue = 100;
    private DoubleProperty               minMeasuredValue;
    private double                       _maxMeasuredValue = 0;
    private DoubleProperty               maxMeasuredValue;
    private int                          _minMeasuredValueDecimals = 2;
    private IntegerProperty              minMeasuredValueDecimals;
    private int                          _maxMeasuredValueDecimals = 2;
    private IntegerProperty              maxMeasuredValueDecimals;
    private double                       _threshold = 50.0;
    private DoubleProperty               threshold;
    private boolean                      _thresholdBehaviorInverted = false;
    private BooleanProperty              thresholdBehaviorInverted;
    private boolean                      thresholdExceeded = false;
    private String                       _title = "";
    private StringProperty               title;
    private String                       _unit = "";
    private StringProperty               unit;
    private String                       _lowerCenterText = "";
    private StringProperty               lowerCenterText;
    private boolean                      _lowerCenterCenterTextVisible = false;
    private BooleanProperty              lowerCenterTextVisible;
    private String                       _lowerRightText = "";
    private StringProperty               lowerRightText;
    private boolean                      _lowerRightTextVisible = false;
    private BooleanProperty              lowerRightTextVisible;
    private String                       _upperLeftText = "";
    private StringProperty               upperLeftText;
    private boolean                      _upperLeftTextVisible = false;
    private BooleanProperty              upperLeftTextVisible;
    private String                       _upperRightText = "";
    private StringProperty               upperRightText;
    private boolean                      _upperRightTextVisible = false;
    private BooleanProperty              upperRightTextVisible;
    private NumberSystem                 _numberSystem = NumberSystem.DECIMAL;
    private ObjectProperty<NumberSystem> numberSystem;
    private Trend                        _trend = Trend.UNKNOWN;
    private ObjectProperty<Trend>        trend;
    private double                       _batteryCharge = 0.0;
    private DoubleProperty               batteryCharge;
    private double                       _signalStrength = 0.0;
    private DoubleProperty               signalStrength;
    private boolean                      _valueVisible = true;
    private BooleanProperty              valueVisible;
    private boolean                      _minMeasuredValueVisible = false;
    private BooleanProperty              minMeasuredValueVisible;
    private boolean                      _maxMeasuredValueVisible = false;
    private BooleanProperty              maxMeasuredValueVisible;
    private boolean                      _formerValueVisible = false;
    private BooleanProperty              formerValueVisible;
    private boolean                      _thresholdVisible = false;
    private BooleanProperty              thresholdVisible;
    private String                       _unitFont  = "Open Sans";
    private StringProperty               unitFont;
    private String                       _titleFont = "Open Sans";
    private StringProperty               titleFont;
    private LcdFont                      _valueFont = LcdFont.LCD;
    private ObjectProperty<LcdFont>      valueFont;
    private String                       _smallFont = "Open Sans";
    private StringProperty               smallFont;
    private int                          _decimals = 0;
    private IntegerProperty              decimals;
    private boolean                      _numberSystemVisible = false;
    private BooleanProperty              numberSystemVisible;
    private boolean                      _blinking = false;
    private BooleanProperty              blinking;
    private boolean                      _backgroundVisible = true;
    private BooleanProperty              backgroundVisible;
    private boolean                      _crystalOverlayVisible = false;
    private BooleanProperty              crystalOverlayVisible;
    private boolean                      _mainInnerShadowVisible = false;
    private BooleanProperty              mainInnerShadowVisible;
    private boolean                      _foregroundShadowVisible = false;
    private BooleanProperty              foregroundShadowVisible;
    private boolean                      _titleVisible = true;
    private BooleanProperty              titleVisible;
    private boolean                      _unitVisible = true;
    private BooleanProperty              unitVisible;
    private boolean                      _trendVisible = false;
    private BooleanProperty              trendVisible;
    private boolean                      _batteryVisible = false;
    private BooleanProperty              batteryVisible;
    private boolean                      _signalVisible = false;
    private BooleanProperty              signalVisible;
    private boolean                      _alarmVisible = false;
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
        value                     = new DoublePropertyBase(0) {
            @Override protected void invalidated() {
                set(clamp(getMinValue(), getMaxValue(), get()));
            }
            @Override public Object getBean() { return this; }
            @Override public String getName() { return "value"; }
        };
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
                if (thresholdExceeded && NOW > lastThresholdTimerCall + interval) {
                    toggleThreshold ^= true;
                    thresholdVisible.set(toggleThreshold);
                    lastThresholdTimerCall = NOW;
                }
            }
        };
        toValueAnimation = new Transition() {
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
        valueProperty().addListener((ov, oldValue, newValue) -> {
            formerValue.set(oldValue.doubleValue());
            if (toValueAnimation.getStatus() != Animation.Status.STOPPED) {
                toValueAnimation.stop();
            }
            if (getAnimated()) {
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
        });
        currentValueProperty().addListener(observable -> {
            if (Double.compare(currentValue.get(), getMinMeasuredValue()) < 0 && !firstTime) {
                setMinMeasuredValue(currentValue.get());
            }
            if (Double.compare(currentValue.get(), getMaxMeasuredValue()) > 0 && !firstTime) {
                setMaxMeasuredValue(currentValue.get());
            }
            // Check threshold
            if (thresholdExceeded) {
                if (currentValue.get() < getThreshold()) {
                    fireEvent(new ValueEvent(this, this, ValueEvent.VALUE_UNDERRUN));
                    thresholdExceeded = false;
                }
            } else {
                if (currentValue.get() > getThreshold()) {
                    fireEvent(new ValueEvent(this, this, ValueEvent.VALUE_EXCEEDED));
                    thresholdExceeded = true;
                }
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
        return null == textMode ? _textMode : textMode.get();
    }
    public final void setTextMode(final boolean TEXT_MODE) {
        if (null == textMode) {
            _textMode = TEXT_MODE;
        } else {
            textMode.set(TEXT_MODE);
        }
    }
    public final BooleanProperty textModeProperty() {
        if (null == textMode) {
            textMode = new SimpleBooleanProperty(this, "textMode", _textMode);
        }
        return textMode;
    }

    public final String getText() {
        return null == text ? _text : text.get();
    }
    public final void setText(final String TEXT) {
        if (null == text) {
            _text = TEXT;
        } else {
            text.set(TEXT);
        }
    }
    public final StringProperty textProperty() {
        if (null == text) {
            text = new SimpleStringProperty(this, "text", _text);
        }
        return text;
    }

    public final double getValue() {
        return value.get();
    }
    public final void setValue(final double VALUE) {
        formerValue.set(value.get());
        value.set(VALUE);
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

    public final boolean getAnimated() {
        return null == animated ? _animated : animated.get();
    }
    public final void setAnimated(final boolean ANIMATED) {
        if (null == animated) {
            _animated = ANIMATED;
        } else {
            animated.set(ANIMATED);
        }
    }
    public final BooleanProperty animatedProperty() {
        if (null == animated) {
            animated = new SimpleBooleanProperty(this, "animated", _animated);
        }
        return animated;
    }

    public final double getAnimationDuration() {
        return null == animationDuration ? _animationDuration : animationDuration.get();
    }
    public final void setAnimationDuration(final double ANIMATION_DURATION) {
        if (null == animationDuration) {
            _animationDuration = ANIMATION_DURATION;
        } else {
            animationDuration.set(ANIMATION_DURATION);
        }
    }
    public final DoubleProperty animationDurationProperty() {
        if (null == animationDuration) {
            animationDuration = new SimpleDoubleProperty(this, "animationDuration", _animationDuration);
        }
        return animationDuration;
    }

    public final double getMinValue() {
        return null == minValue ? _minValue : minValue.get();
    }
    public final void setMinValue(final double MIN_VALUE) {
        if (null == minValue) {
            _minValue = clamp(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, MIN_VALUE);
        } else {
            minValue.set(MIN_VALUE);
        }
    }
    public final DoubleProperty minValueProperty() {
        if (null == minValue) {            
            minValue = new DoublePropertyBase(_minValue) {
                @Override protected void invalidated() {
                    set(clamp(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, get()));
                }
                @Override public Object getBean() { return this; }
                @Override public String getName() { return "minValue"; }
            };
        }
        return minValue;
    }

    public final double getMaxValue() {
        return null == maxValue ? _maxValue : maxValue.get();
    }
    public final void setMaxValue(final double MAX_VALUE) {
        if (null == maxValue) {
            _maxValue = clamp(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, MAX_VALUE);
        } else {
            maxValue.set(MAX_VALUE);
        }
    }
    public final DoubleProperty maxValueProperty() {
        if (null == maxValue) {            
            maxValue = new DoublePropertyBase(_maxValue) {
                @Override protected void invalidated() {
                    set(clamp(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, get()));
                }
                @Override public Object getBean() { return this; }
                @Override public String getName() { return "maxValue"; }
            };
        }
        return maxValue;
    }

    public final double getRange() {
        if (null == maxValue && null != minValue) {
            return _maxValue - minValue.get();
        } else if (null != maxValue && null == minValue) {
            return maxValue.get() - _minValue;
        } else if (null == maxValue && null == minValue) {
            return _maxValue - _minValue;
        } else {
            return maxValue.subtract(minValue).get();
        }
    }

    public final double getMinMeasuredValue() {
        return null == minMeasuredValue ? _minMeasuredValue : minMeasuredValue.get();
    }
    public final void setMinMeasuredValue(final double MIN_MEASURED_VALUE) {
        if (null == minMeasuredValue) {
            _minMeasuredValue = MIN_MEASURED_VALUE;
        } else {
            minMeasuredValue.set(MIN_MEASURED_VALUE);
        }
    }
    public final DoubleProperty minMeasuredValueProperty() {
        if (null == minMeasuredValue) {
            minMeasuredValue = new SimpleDoubleProperty(this, "minMeasuredValue", _minMeasuredValue);
        }
        return minMeasuredValue;
    }
    public final void resetMinMeasuredValue() {
        setMinMeasuredValue(getValue());
    }

    public final double getMaxMeasuredValue() {
        return null == maxMeasuredValue ? _minMeasuredValue : maxMeasuredValue.get();
    }
    public final void setMaxMeasuredValue(final double MAX_MEASURED_VALUE) {
        if (null == maxMeasuredValue) {
            _maxMeasuredValue = MAX_MEASURED_VALUE;
        } else {
            maxMeasuredValue.set(MAX_MEASURED_VALUE);
        }
    }
    public final DoubleProperty maxMeasuredValueProperty() {
        if (null == maxMeasuredValue) {
            maxMeasuredValue = new SimpleDoubleProperty(this, "maxMeasuredValue", _maxMeasuredValue);
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
        return null == threshold ? _threshold : threshold.get();
    }
    public final void setThreshold(final double THRESHOLD) {
        if (null == threshold) {
            _threshold = clamp(getMinValue(), getMaxValue(), THRESHOLD);
        } else {
            threshold.set(clamp(getMinValue(), getMaxValue(), THRESHOLD));
        }
        //validateThreshold();
    }
    public final ReadOnlyDoubleProperty thresholdProperty() {
        if (null == threshold) {
            threshold = new SimpleDoubleProperty(this, "threshold", _threshold);
        }
        return threshold;
    }

    public final boolean isThresholdBehaviorInverted() {
        return null == thresholdBehaviorInverted ? _thresholdBehaviorInverted : thresholdBehaviorInverted.get();
    }
    public final void setThresholdBehaviorInverted(final boolean THRESHOLD_BEHAVIOR_INVERTED) {
        if (null == thresholdBehaviorInverted) {
            _thresholdBehaviorInverted = THRESHOLD_BEHAVIOR_INVERTED;
        } else {
            thresholdBehaviorInverted.set(THRESHOLD_BEHAVIOR_INVERTED);
        }
    }
    public final BooleanProperty thresholdBehaviorInvertedProperty() {
        if (null == thresholdBehaviorInverted) {
            thresholdBehaviorInverted = new SimpleBooleanProperty(this, "thresholdBehaviorInverted", _thresholdBehaviorInverted);
        }
        return thresholdBehaviorInverted;
    }

    public final String getTitle() {
        return null == title ? _title : title.get();
    }
    public final void setTitle(final String TITLE) {
        if (null == title) {
            _title = TITLE;
        } else {
            title.set(TITLE);
        }
    }
    public final StringProperty titleProperty() {
        if (null == title) {
            title = new SimpleStringProperty(this, "title", _title);
        }
        return title;
    }

    public final String getUnit() {
        return null == unit ? _unit : unit.get();
    }
    public final void setUnit(final String UNIT) {
        if (null == unit) {
            _unit = UNIT;
        } else {
            unit.set(UNIT);
        }
    }
    public final StringProperty unitProperty() {
        if (null == unit) {
            unit = new SimpleStringProperty(this, "unit", _unit);
        }
        return unit;
    }

    public final String getLowerCenterText() {
        return null == lowerCenterText ? _lowerCenterText : lowerCenterText.get();
    }
    public final void setLowerCenterText(final String LOWER_CENTER_TEXT) {
        if (null == lowerCenterText) {
            _lowerCenterText = LOWER_CENTER_TEXT;
        } else {
            lowerCenterText.set(LOWER_CENTER_TEXT);
        }
    }
    public final StringProperty lowerCenterTextProperty() {
        if (null == lowerCenterText) {
            lowerCenterText = new SimpleStringProperty(this, "lowerCenterText", _lowerCenterText);
        }
        return lowerCenterText;
    }

    public final boolean isLowerCenterTextVisible() {
        return null == lowerCenterTextVisible ? _lowerCenterCenterTextVisible : lowerCenterTextVisible.get();
    }
    public final void setLowerCenterTextVisible(final boolean LOWER_CENTER_TEXT_VISIBLE) {
        if (null == lowerCenterTextVisible) {
            _lowerCenterCenterTextVisible = LOWER_CENTER_TEXT_VISIBLE;
        } else {
            lowerCenterTextVisible.set(LOWER_CENTER_TEXT_VISIBLE);
        }
    }
    public final BooleanProperty lowerCenterTextVisibleProperty() {
        if (null == lowerCenterTextVisible) {
            lowerCenterTextVisible = new SimpleBooleanProperty(this, "lowerCenterTextVisible", _lowerCenterCenterTextVisible);
        }
        return lowerCenterTextVisible;
    }

    public final String getLowerRightText() {
        return null == lowerRightText ? _lowerRightText : lowerRightText.get();
    }
    public final void setLowerRightText(final String LOWER_RIGHT_TEXT) {
        if (null == lowerRightText) {
            _lowerRightText = LOWER_RIGHT_TEXT;
        } else {
            lowerRightText.set(LOWER_RIGHT_TEXT);
        }
    }
    public final StringProperty lowerRightTextProperty() {
        if (null == lowerRightText) {
            lowerRightText = new SimpleStringProperty(this, "lowerRightText", _lowerRightText);
        }
        return lowerRightText;
    }

    public final boolean isLowerRightTextVisible() {
        return null == lowerRightTextVisible ? _lowerRightTextVisible : lowerRightTextVisible.get();
    }
    public final void setLowerRightTextVisible(final boolean LOWER_RIGHT_TEXT_VISIBLE) {
        if (null == lowerRightTextVisible) {
            _lowerRightTextVisible = LOWER_RIGHT_TEXT_VISIBLE;
        } else {
            lowerRightTextVisible.set(LOWER_RIGHT_TEXT_VISIBLE);
        }
    }
    public final BooleanProperty lowerRightTextVisibleProperty() {
        if (null == lowerRightTextVisible) {
            lowerRightTextVisible = new SimpleBooleanProperty(this, "lowerRightTextVisible", _lowerRightTextVisible);
        }
        return lowerRightTextVisible;
    }

    public final String getUpperLeftText() {
        return null == upperLeftText ? _upperLeftText : upperLeftText.get();
    }
    public final void setUpperLeftText(final String UPPER_LEFT_TEXT) {
        if (null == upperLeftText) {
            _upperLeftText = UPPER_LEFT_TEXT;
        } else {
            upperLeftText.set(UPPER_LEFT_TEXT);
        }
    }
    public final StringProperty upperLeftTextProperty() {
        if (null == upperLeftText) {
            upperLeftText = new SimpleStringProperty(this, "upperLeftText", _upperLeftText);
        }
        return upperLeftText;
    }

    public final boolean isUpperLeftTextVisible() {
        return null == upperLeftTextVisible ? _upperLeftTextVisible : upperLeftTextVisible.get();
    }
    public final void setUpperLeftTextVisible(final boolean UPPER_LEFT_TEXT_VISIBLE) {
        if (null == upperLeftTextVisible) {
            _upperLeftTextVisible = UPPER_LEFT_TEXT_VISIBLE;
        } else {
            upperLeftTextVisible.set(UPPER_LEFT_TEXT_VISIBLE);
        }
    }
    public final BooleanProperty upperLeftTextVisibleProperty() {
        if (null == upperLeftTextVisible) {
            upperLeftTextVisible = new SimpleBooleanProperty(this, "upperLeftTextVisible", _upperLeftTextVisible);
        }
        return upperLeftTextVisible;
    }

    public final String getUpperRightText() {
        return null == upperRightText ? _upperRightText : upperRightText.get();
    }
    public final void setUpperRightText(final String UPPER_RIGHT_TEXT) {
        if (null == upperRightText) {
            _upperRightText = UPPER_RIGHT_TEXT;
        } else {
            upperRightText.set(UPPER_RIGHT_TEXT);
        }
    }
    public final StringProperty upperRightTextProperty() {
        if (null == upperRightText) {
            upperRightText = new SimpleStringProperty(this, "upperRightText", _upperRightText);
        }
        return upperRightText;
    }

    public final boolean isUpperRightTextVisible() {
        return null == upperRightTextVisible ? _upperRightTextVisible : upperRightTextVisible.get();
    }
    public final void setUpperRightTextVisible(final boolean UPPER_RIGHT_TEXT_VISIBLE) {
        if (null == upperRightTextVisible) {
            _upperRightTextVisible = UPPER_RIGHT_TEXT_VISIBLE;
        } else {
            upperRightTextVisible.set(UPPER_RIGHT_TEXT_VISIBLE);
        }
    }
    public final BooleanProperty upperRightTextVisibleProperty() {
        if (null == upperRightTextVisible) {
            upperRightTextVisible = new SimpleBooleanProperty(this, "upperRightTextVisible", _upperRightTextVisible);
        }
        return upperRightTextVisible;
    }

    public final NumberSystem getNumberSystem() {
        return null == numberSystem ? _numberSystem : numberSystem.get();
    }
    public final void setNumberSystem(final NumberSystem NUMBER_SYSTEM) {
        if (null == numberSystem) {
            _numberSystem = NUMBER_SYSTEM;
        } else {
            numberSystem.set(NUMBER_SYSTEM);
        }
    }
    public final ObjectProperty<NumberSystem> numberSystemProperty() {
        if (null == numberSystem) {
            numberSystem = new SimpleObjectProperty<>(this, "numberSystem", _numberSystem);
        }
        return numberSystem;
    }

    public final Trend getTrend() {
        return null == trend ? _trend : trend.get();
    }
    public final void setTrend(final Trend TREND) {
        if (null == trend ) {
            _trend = TREND;
        } else {
            trend.set(TREND);
        }
    }
    public final ObjectProperty<Trend> trendProperty() {
        if (null == trend) {
            trend = new SimpleObjectProperty<>(this, "trend", _trend);
        }
        return trend;
    }

    public final double getBatteryCharge() {
        return null == batteryCharge ? _batteryCharge : batteryCharge.get();
    }
    public final void setBatteryCharge(final double BATTERY_CHARGE) {
        if (null == batteryCharge) {
            _batteryCharge = clamp(0.0, 1.0, BATTERY_CHARGE);
        } else {
            batteryCharge.set(clamp(0.0, 1.0, BATTERY_CHARGE));
        }
    }
    public final DoubleProperty batteryChargeProperty() {
        if (null == batteryCharge) {
            batteryCharge = new SimpleDoubleProperty(this, "batteryCharge", _batteryCharge);
        }
        return batteryCharge;
    }

    public final double getSignalStrength() {
        return null == signalStrength ? _signalStrength : signalStrength.get();
    }
    public final void setSignalStrength(final double SIGNAL_STRENGTH) {
        if (null == signalStrength) {
            _signalStrength = clamp(0.0, 1.0, SIGNAL_STRENGTH);
        } else {
            signalStrength.set(clamp(0.0, 1.0, SIGNAL_STRENGTH));
        }
    }
    public final DoubleProperty signalStrengthProperty() {
        if (null == signalStrength) {
            signalStrength = new SimpleDoubleProperty(this, "signalStrength", _signalStrength);
        }
        return signalStrength;
    }

    public final boolean isValueVisible() {
        return null == valueVisible ? _valueVisible : valueVisible.get();
    }
    private final void setValueVisible(final boolean VALUE_VISIBLE) {
        if (null == valueVisible) {
            _valueVisible = VALUE_VISIBLE;
        } else {
            valueVisible.set(VALUE_VISIBLE);
        }
    }
    public final ReadOnlyBooleanProperty valueVisibleProperty() {
        if (null == valueVisible) {
            valueVisible = new SimpleBooleanProperty(this, "valueVisible", _valueVisible);
        }
        return valueVisible;
    }

    public final boolean isMinMeasuredValueVisible() {
        return null == minMeasuredValueVisible ? _minMeasuredValueVisible : minMeasuredValueVisible.get();
    }
    public final void setMinMeasuredValueVisible(final boolean MIN_MEASURED_VALUE_VISIBLE) {
        if (null == minMeasuredValueVisible) {
            _minMeasuredValueVisible = MIN_MEASURED_VALUE_VISIBLE;
        } else {
            minMeasuredValueVisible.set(MIN_MEASURED_VALUE_VISIBLE);
        }
    }
    public final BooleanProperty minMeasuredValueVisibleProperty() {
        if (null == minMeasuredValueVisible) {
            minMeasuredValueVisible = new SimpleBooleanProperty(this, "minMeasuredValueVisible", _minMeasuredValueVisible);
        }
        return minMeasuredValueVisible;
    }

    public final boolean isMaxMeasuredValueVisible() {
        return null == maxMeasuredValueVisible ? _maxMeasuredValueVisible : maxMeasuredValueVisible.get();
    }
    public final void setMaxMeasuredValueVisible(final boolean MAX_MEASURED_VALUE_VISIBLE) {
        if (null == maxMeasuredValueVisible) {
            _maxMeasuredValueVisible = MAX_MEASURED_VALUE_VISIBLE;
        } else {
            maxMeasuredValueVisible.set(MAX_MEASURED_VALUE_VISIBLE);
        }
    }
    public final BooleanProperty maxMeasuredValueVisibleProperty() {
        if (null == maxMeasuredValueVisible) {
            maxMeasuredValueVisible = new SimpleBooleanProperty(this, "maxMeasureValueVisible", _maxMeasuredValueVisible);
        }
        return maxMeasuredValueVisible;
    }

    public final boolean isThresholdVisible() {
        return null == thresholdVisible ? _thresholdVisible : thresholdVisible.get();
    }
    public final void setThresholdVisible(final boolean THRESHOLD_VISIBLE) {
        if (null == thresholdVisible) {
            _thresholdVisible = THRESHOLD_VISIBLE;
        } else {
            thresholdVisible.set(THRESHOLD_VISIBLE);
        }
    }
    public final BooleanProperty thresholdVisibleProperty() {
        if (null == thresholdVisible) {
            thresholdVisible = new SimpleBooleanProperty(this, "thresholdVisible", _thresholdVisible);
        }
        return thresholdVisible;
    }

    public final boolean isNoFrame() {
        return null == noFrame ? true : noFrame.get();
    }
    public final void setNoFrame(final boolean NO_FRAME) {
        noFrameProperty().set(NO_FRAME);
    }
    public final BooleanProperty noFrameProperty() {
        if (null == noFrame) {
            noFrame = new BooleanPropertyBase(false) {
                @Override protected void invalidated() {
                    pseudoClassStateChanged(NO_FRAME_PSEUDO_CLASS, get());
                }
                @Override public Object getBean() { return this; }
                @Override public String getName() { return "noFrame"; }
            };
        }
        return noFrame;
    }

    public final boolean isBackgroundVisible() {
        return null == backgroundVisible ? _backgroundVisible : backgroundVisible.get();
    }
    public final void setBackgroundVisible(final boolean BACKGROUND_VISIBLE) {
        if (null == backgroundVisible) {
            _backgroundVisible = BACKGROUND_VISIBLE;
        } else {
            backgroundVisible.set(BACKGROUND_VISIBLE);
        }
    }
    public final BooleanProperty backgroundVisibleProperty() {
        if (null == backgroundVisible) {
            backgroundVisible = new SimpleBooleanProperty(this, "backgroundVisible", _backgroundVisible);
        }
        return backgroundVisible;
    }

    public final boolean isCrystalOverlayVisible() {
        return null == crystalOverlayVisible ? _crystalOverlayVisible : crystalOverlayVisible.get();
    }
    public final void setCrystalOverlayVisible(final boolean CRYSTAL_OVERLAY_VISIBLE) {
        if (null == crystalOverlayVisible) {
            _crystalOverlayVisible = CRYSTAL_OVERLAY_VISIBLE;
        } else {
            crystalOverlayVisible.set(CRYSTAL_OVERLAY_VISIBLE);
        }
    }
    public final BooleanProperty crystalOverlayVisibleProperty() {
        if (null == crystalOverlayVisible) {
            crystalOverlayVisible = new SimpleBooleanProperty(this, "crystalOverlayVisible", _crystalOverlayVisible);
        }
        return crystalOverlayVisible;
    }

    public final boolean isMainInnerShadowVisible() {
        return null == mainInnerShadowVisible ? _mainInnerShadowVisible : mainInnerShadowVisible.get();
    }
    public final void setMainInnerShadowVisible(final boolean MAIN_INNER_SHADOW_VISIBLE) {
        if (null == mainInnerShadowVisible) {
            _mainInnerShadowVisible = MAIN_INNER_SHADOW_VISIBLE;
        } else {
            mainInnerShadowVisible.set(MAIN_INNER_SHADOW_VISIBLE);
        }
    }
    public final BooleanProperty mainInnerShadowVisibleProperty() {
        if (null == mainInnerShadowVisible) {
            mainInnerShadowVisible = new SimpleBooleanProperty(this, "mainInnerShadowVisible", _mainInnerShadowVisible);
        }
        return mainInnerShadowVisible;
    }

    public final boolean isForegroundShadowVisible() {
        return null == foregroundShadowVisible ? _foregroundShadowVisible : foregroundShadowVisible.get();
    }
    public final void setForegroundShadowVisible(final boolean FOREGROUND_SHADOW_VISIBLE) {
        if (null == foregroundShadowVisible) {
            _foregroundShadowVisible = FOREGROUND_SHADOW_VISIBLE;
        } else {
            foregroundShadowVisible.set(FOREGROUND_SHADOW_VISIBLE);
        }
    }
    public final BooleanProperty foregroundShadowVisibleProperty() {
        if (null == foregroundShadowVisible) {
            foregroundShadowVisible = new SimpleBooleanProperty(this, "foregroundShadowVisible", _foregroundShadowVisible);
        }
        return foregroundShadowVisible;
    }

    public final String getTitleFont() {
        return null == titleFont ? _titleFont : titleFont.get();
    }
    public final void setTitleFont(final String TITLE_FONT) {
        if (null == titleFont) {
            _titleFont = TITLE_FONT;
        } else {
            titleFont.set(TITLE_FONT);
        }
    }
    public final StringProperty titleFontProperty() {
        if (null == titleFont) {
            titleFont = new SimpleStringProperty(this, "titleFont", _titleFont);
        }
        return titleFont;
    }

    public final String getUnitFont() {
        return null == unitFont ? _unitFont : unitFont.get();
    }
    public final void setUnitFont(final String UNIT_FONT) {
        if (null == unitFont) {
            _unitFont = UNIT_FONT;
        } else {
            unitFont.set(UNIT_FONT);
        }
    }
    public final StringProperty unitFontProperty() {
        if (null == unitFont) {
            unitFont = new SimpleStringProperty(this, "unitFont", _unitFont);
        }
        return unitFont;
    }
    
    public final LcdFont getValueFont() {
        return null == valueFont ? _valueFont : valueFont.get();
    }
    public final void setValueFont(final LcdFont VALUE_FONT) {
        if (null == valueFont) {
            _valueFont = VALUE_FONT;
        } else {
            valueFont.set(VALUE_FONT);
        }
    }
    public final ObjectProperty<LcdFont> valueFontProperty() {
        if (null == valueFont) {
            valueFont = new SimpleObjectProperty<>(this, "valueFont", _valueFont);
        }
        return valueFont;
    }

    public final String getSmallFont() {
        return null == smallFont ? _smallFont : smallFont.get();
    }
    public final void setSmallFont(final String SMALL_FONT) {
        if (null == smallFont) {
            _smallFont = SMALL_FONT;
        } else {
            smallFont.set(SMALL_FONT);
        }
    }
    public final StringProperty smallFontProperty() {
        if (null == smallFont) {
            smallFont = new SimpleStringProperty(this, "smallFont", _smallFont);
        }
        return smallFont;
    }

    public final int getDecimals() {
        return null == decimals ? _decimals : decimals.get();
    }
    public final void setDecimals(final int DECIMALS) {
        final int dec = DECIMALS > 5 ? 5 : (DECIMALS < 0 ? 0 : DECIMALS);
        if (null == decimals) {
            _decimals = dec;
        } else {
            decimals.set(dec);
        }
    }
    public final IntegerProperty decimalsProperty() {
        if (null == decimals) {
            decimals = new SimpleIntegerProperty(this, "decimals", _decimals);
        }
        return decimals;
    }

    public final boolean isNumberSystemVisible() {
        return null == numberSystemVisible ? _numberSystemVisible : numberSystemVisible.get();
    }
    public final void setNumberSystemVisible(final boolean NUMBER_SYSTEM_VISIBLE) {
        if (null == numberSystemVisible) {
            _numberSystemVisible = NUMBER_SYSTEM_VISIBLE;
        } else {
            numberSystemVisible.set(NUMBER_SYSTEM_VISIBLE);
        }
    }
    public final BooleanProperty numberSystemVisibleProperty() {
        if (null == numberSystemVisible) {
            numberSystemVisible = new SimpleBooleanProperty(this, "numberSystemVisible", _numberSystemVisible);
        }
        return numberSystemVisible;
    }

    public final boolean isBlinking() {
        return null == blinking ? _blinking : blinking.get();
    }
    public final void setBlinking(final boolean BLINKING) {
        if (null == blinking) {
            _blinking = BLINKING;
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
            blinking = new SimpleBooleanProperty(this, "blinking", _blinking);
        }
        return blinking;
    }

    public final boolean isTitleVisible() {
        return null == titleVisible ? _titleVisible : titleVisible.get();
    }
    public final void setTitleVisible(final boolean TITLE_VISIBLE) {
        if (null == titleVisible) {
            _titleVisible = TITLE_VISIBLE;
        } else {
            titleVisible.set(TITLE_VISIBLE);
        }
    }
    public final BooleanProperty titleVisibleProperty() {
        if (null == titleVisible) {
            titleVisible = new SimpleBooleanProperty(this, "titleVisible", _titleVisible);
        }
        return titleVisible;
    }

    public final boolean isUnitVisible() {
        return null == unitVisible ? _unitVisible : unitVisible.get();
    }
    public final void setUnitVisible(final boolean UNIT_VISIBLE) {
        if (null == unitVisible) {
            _unitVisible = UNIT_VISIBLE;
        } else {
            unitVisible.set(UNIT_VISIBLE);
        }
    }
    public final BooleanProperty unitVisibleProperty() {
        if (null == unitVisible) {
            unitVisible = new SimpleBooleanProperty(this, "unitVisible", _unitVisible);
        }
        return unitVisible;
    }

    public final boolean isTrendVisible() {
        return null == trendVisible ? _trendVisible : trendVisible.get();
    }
    public final void setTrendVisible(final boolean TREND_VISIBLE) {
        if (null == trendVisible) {
            _trendVisible = TREND_VISIBLE;
        } else {
            trendVisible.set(TREND_VISIBLE);
        }
    }
    public final BooleanProperty trendVisibleProperty() {
        if (null == trendVisible) {
            trendVisible = new SimpleBooleanProperty(this, "trendVisible", _trendVisible);
        }
        return trendVisible;
    }

    public final boolean isBatteryVisible() {
        return null == batteryVisible ? _batteryVisible : batteryVisible.get();
    }
    public final void setBatteryVisible(final boolean BATTERY_VISIBLE) {
        if (null == batteryVisible) {
            _batteryVisible = BATTERY_VISIBLE;
        } else {
            batteryVisible.set(BATTERY_VISIBLE);
        }
    }
    public final BooleanProperty batteryVisibleProperty() {
        if (null == batteryVisible) {
            batteryVisible = new SimpleBooleanProperty(this, "batteryVisible", _batteryVisible);
        }
        return batteryVisible;
    }

    public final boolean isSignalVisible() {
        return null == signalVisible ? _signalVisible : signalVisible.get();
    }
    public final void setSignalVisible(final boolean SIGNAL_VISIBLE) {
        if (null == signalVisible) {
            _signalVisible = SIGNAL_VISIBLE;
        } else {
            signalVisible.set(SIGNAL_VISIBLE);
        }
    }
    public final BooleanProperty signalVisibleProperty() {
        if (null == signalVisible) {
            signalVisible = new SimpleBooleanProperty(this, "signalVisible", _signalVisible);
        }
        return signalVisible;
    }

    public final boolean isAlarmVisible() {
        return null == alarmVisible ? _alarmVisible : alarmVisible.get();
    }
    public final void setAlarmVisible(final boolean ALARM_VISIBLE) {
        if (null == alarmVisible) {
            _alarmVisible = ALARM_VISIBLE;
        } else {
            alarmVisible.set(ALARM_VISIBLE);
        }
    }
    public final BooleanProperty alarmVisibleProperty() {
        if (null == alarmVisible) {
            alarmVisible = new SimpleBooleanProperty(this, "alarmVisible", _alarmVisible);
        }
        return alarmVisible;
    }

    public final boolean isFormerValueVisible() {
        return null == formerValueVisible ? _formerValueVisible : formerValueVisible.get();
    }
    public final void setFormerValueVisible(final boolean FORMER_VALUE_VISIBLE) {
        if (null == formerValueVisible) {
            _formerValueVisible = FORMER_VALUE_VISIBLE;
        } else {
            formerValueVisible.set(FORMER_VALUE_VISIBLE);
        }
    }
    public final BooleanProperty formerValueVisibleProperty() {
        if (null == formerValueVisible) {
            formerValueVisible = new SimpleBooleanProperty(this, "formerValueVisible", _formerValueVisible);
        }
        return formerValueVisible;
    }

    public final int getMinMeasuredValueDecimals() {
        return null == minMeasuredValueDecimals ? _minMeasuredValueDecimals : minMeasuredValueDecimals.get();
    }
    public final void setMinMeasuredValueDecimals(final int MIN_MEASURED_VALUE_DECIMALS) {
        final int DECIMALS = MIN_MEASURED_VALUE_DECIMALS > 5 ? 5 : (MIN_MEASURED_VALUE_DECIMALS < 0 ? 0 : MIN_MEASURED_VALUE_DECIMALS);
        if (null == minMeasuredValueDecimals) {
            _minMeasuredValueDecimals = MIN_MEASURED_VALUE_DECIMALS;
        } else {
            minMeasuredValueDecimals.set(DECIMALS);
        }
    }
    public final IntegerProperty minMeasuredValueDecimalsProperty() {
        if (null == minMeasuredValueDecimals) {
            minMeasuredValueDecimals = new SimpleIntegerProperty(this, "minMeasuredValueDecimals", _minMeasuredValueDecimals);
        }
        return maxMeasuredValueDecimals;
    }

    public final int getMaxMeasuredValueDecimals() {
        return null == maxMeasuredValueDecimals ? _maxMeasuredValueDecimals : maxMeasuredValueDecimals.get();
    }
    public final void setMaxMeasuredValueDecimals(final int MAX_MEASURED_VALUE_DECIMALS) {
        final int DECIMALS = MAX_MEASURED_VALUE_DECIMALS > 5 ? 5 : (MAX_MEASURED_VALUE_DECIMALS < 0 ? 0 : MAX_MEASURED_VALUE_DECIMALS);
        if (null == maxMeasuredValueDecimals) {
            _maxMeasuredValueDecimals = MAX_MEASURED_VALUE_DECIMALS;
        } else {
            maxMeasuredValueDecimals.set(DECIMALS);
        }
    }
    public final IntegerProperty maxMeasuredValueDecimalsProperty() {
        if (null == maxMeasuredValueDecimals) {
            maxMeasuredValueDecimals = new SimpleIntegerProperty(this, "maxMeasuredValueDecimals", _maxMeasuredValueDecimals);
        }
        return maxMeasuredValueDecimals;
    }


    // ******************** Utility Methods ***********************************
    public static double clamp(final double MIN, final double MAX, final double VALUE) {
        if (VALUE < MIN) return MIN;
        if (VALUE > MAX) return MAX;
        return VALUE;
    }


    // ******************** Style related *************************************
    @Override protected Skin createDefaultSkin() {
        return new LcdSkin(this);
    }

    @Override protected String getUserAgentStylesheet() {
        return getClass().getResource(getClass().getSimpleName().toLowerCase() + ".css").toExternalForm();
    }
}
