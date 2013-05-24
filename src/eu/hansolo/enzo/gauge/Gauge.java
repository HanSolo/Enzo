/*
 * Copyright (c) 2013. Gerrit Grunwald
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package eu.hansolo.enzo.gauge;

import com.sun.javafx.css.converters.PaintConverter;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.BooleanPropertyBase;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.css.CssMetaData;
import javafx.css.PseudoClass;
import javafx.css.Styleable;
import javafx.css.StyleableObjectProperty;
import javafx.css.StyleableProperty;
import javafx.scene.control.Control;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;


/**
 * Created by
 * User: hansolo
 * Date: 01.04.13
 * Time: 17:10
 */
public class Gauge extends Control {
    // CSS Pseudo classes
    private static final PseudoClass             TOUCH_MODE_PSEUDO_CLASS     = PseudoClass.getPseudoClass("touch-mode");

    public static enum NeedleType {
        STANDARD("needle-standard");

        public final String STYLE_CLASS;

        private NeedleType(final String STYLE_CLASS) {
            this.STYLE_CLASS = STYLE_CLASS;
        }
    }
    public static enum TickLabelOrientation {
        NORMAL,
        HORIZONTAL,
        TANGENT
    }
    public static enum NumberFormat {
        AUTO("0"),
        STANDARD("0"),
        FRACTIONAL("0.0#"),
        SCIENTIFIC("0.##E0"),
        PERCENTAGE("##0.0%");

        private final DecimalFormat DF;

        private NumberFormat(final String FORMAT_STRING) {
            Locale.setDefault(new Locale("en", "US"));

            DF = new DecimalFormat(FORMAT_STRING);
        }

        public String format(final Number NUMBER) {
            return DF.format(NUMBER);
        }
    }
    public static final String                   STYLE_CLASS_NEEDLE_STANDARD = NeedleType.STANDARD.STYLE_CLASS;
    private double                               _value;
    private DoubleProperty                       value;
    private double                               _oldValue;
    private double                               _minValue;
    private DoubleProperty                       minValue;
    private double                               _maxValue;
    private DoubleProperty                       maxValue;
    private double                               _threshold;
    private DoubleProperty                       threshold;
    private double                               _minMeasuredValue;
    private DoubleProperty                       minMeasuredValue;
    private double                               _maxMeasuredValue;
    private DoubleProperty                       maxMeasuredValue;
    private int                                  _noOfDecimals;
    private IntegerProperty                      noOfDecimals;
    private String                               _title;
    private StringProperty                       title;
    private String                               _unit;
    private StringProperty                       unit;
    private boolean                              _animated;
    private BooleanProperty                      animated;
    private boolean                              _alwaysRound;
    private BooleanProperty                      alwaysRound;
    private double                               _startAngle;
    private DoubleProperty                       startAngle;
    private double                               _angleRange;
    private DoubleProperty                       angleRange;
    private Gauge.NeedleType                     _needleType;
    private ObjectProperty<NeedleType>           needleType;
    private Color                                _needleColor;
    private ObjectProperty<Color>                needleColor;
    private Gauge.TickLabelOrientation           _tickLabelOrientation;
    private ObjectProperty<TickLabelOrientation> tickLabelOrientation;
    private Gauge.NumberFormat                   _numberFormat;
    private ObjectProperty<NumberFormat>         numberFormat;
    private List<Section>                        _sections;
    private ListProperty<Section>                sections;
    private double                               _majorTickSpace;
    private DoubleProperty                       majorTickSpace;
    private double                               _minorTickSpace;
    private DoubleProperty                       minorTickSpace;
    private boolean                              _lcdEnabled;
    private BooleanProperty                      lcdEnabled;
    private Duration                             animationTime;

    // CSS styleable properties
    private ObjectProperty<Paint>                tickMarkFill;
    private ObjectProperty<Paint>                tickLabelFill;

    // CSS pseudo classes
    private BooleanProperty                      touchMode;


    // ******************** Constructors **************************************
    public Gauge() {
        getStyleClass().add("gauge");
        _value                = 0;
        _oldValue             = 0;
        _minValue             = 0;
        _maxValue             = 100;
        _threshold            = 50;
        _minMeasuredValue     = 100;
        _maxMeasuredValue     = 0;
        _noOfDecimals         = 2;
        _title                = "title";
        _unit                 = "unit";
        _animated             = true;
        _alwaysRound          = true;
        _startAngle           = -40;
        _angleRange           = 280;
        _needleType           = NeedleType.STANDARD;
        _needleColor          = Color.RED;
        _tickLabelOrientation = TickLabelOrientation.HORIZONTAL;
        _numberFormat         = NumberFormat.STANDARD;
        _sections             = new ArrayList<>();
        _majorTickSpace       = 10;
        _minorTickSpace       = 1;
        _lcdEnabled           = true;
        animationTime         = Duration.millis(800);
    }


    // ******************** Methods *******************************************
    public final double getValue() {
        return null == value ? _value : value.get();
    }
    public final void setValue(final double VALUE) {
        if (null == value) {
            _oldValue = _value;
            _value = clamp(_minValue, _maxValue, VALUE);
        } else {
            _oldValue = value.get();
            value.set(clamp(getMinValue(), getMaxValue(), VALUE));
        }

        if (getValue() < getMinMeasuredValue()) {
            setMinMeasuredValue(_value);
        }
        if (getValue() > getMaxMeasuredValue()) {
            setMaxMeasuredValue(_value);
        }
    }
    public final DoubleProperty valueProperty() {
        if (null == value) {
            value = new SimpleDoubleProperty(this, "value", _value);
        }
        return value;
    }

    public final double getOldValue() {
        return _oldValue;
    }

    public final double getMinValue() {
        return null == minValue ? _minValue : minValue.get();
    }
    public final void setMinValue(final double MIN_VALUE) {
        if (null == minValue) {
            _minValue = clamp(Double.MIN_VALUE, _maxValue, MIN_VALUE);
        } else {
            minValue.set(clamp(Double.MIN_VALUE, getMaxValue(), MIN_VALUE));
        }
        validate();
    }
    public final DoubleProperty minValueProperty() {
        if (null == minValue) {
            minValue = new SimpleDoubleProperty(this, "minValue", _minValue);
        }
        return minValue;
    }

    public final double getMaxValue() {
        return null == maxValue ? _maxValue : maxValue.get();
    }
    public final void setMaxValue(final double MAX_VALUE) {
        if (null == maxValue) {
            _maxValue = clamp(getMinValue(), Double.MAX_VALUE, MAX_VALUE);
        } else {
            maxValue.set(clamp(getMinValue(), Double.MAX_VALUE, MAX_VALUE));
        }
        validate();
    }
    public final DoubleProperty maxValueProperty() {
        if (null == maxValue) {
            maxValue = new SimpleDoubleProperty(this, "maxValue", _maxValue);
        }
        return maxValue;
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
    }
    public final DoubleProperty thresholdProperty() {
        if (null == threshold) {
            threshold = new SimpleDoubleProperty(this, "threshold", _threshold);
        }
        return threshold;
    }

    public final double getMinMeasuredValue() {
        return null == minMeasuredValue ? _minMeasuredValue : minMeasuredValue.get();
    }
    private final void setMinMeasuredValue(final double MIN_MEASURED_VALUE) {
        if (null == minMeasuredValue) {
            _minMeasuredValue = MIN_MEASURED_VALUE;
        } else {
            minMeasuredValue.set(MIN_MEASURED_VALUE);
        }
    }
    public final ReadOnlyDoubleProperty minMeasuredValueProperty() {
        if (null == minMeasuredValue) {
            minMeasuredValue = new SimpleDoubleProperty(this, "minMeasuredValue", _minMeasuredValue);
        }
        return minMeasuredValue;
    }

    public final double getMaxMeasuredValue() {
        return null == maxMeasuredValue ? _maxMeasuredValue : maxMeasuredValue.get();
    }
    private final void setMaxMeasuredValue(final double MAX_MEASURED_VALUE) {
        if (null == maxMeasuredValue) {
            _maxMeasuredValue = MAX_MEASURED_VALUE;
        } else {
            maxMeasuredValue.set(MAX_MEASURED_VALUE);
        }
    }
    public final ReadOnlyDoubleProperty maxMeasuredValueProperty() {
        if (null == maxMeasuredValue) {
            maxMeasuredValue = new SimpleDoubleProperty(this, "maxMeasuredValue", _maxMeasuredValue);
        }
        return maxMeasuredValue;
    }

    public void resetMinMeasuredValue() {
        setMinMeasuredValue(_value);
    }
    public void resetMaxMeasuredValue() {
        setMaxMeasuredValue(_value);
    }

    public final int getNoOfDecimals() {
        return null == noOfDecimals ? _noOfDecimals : noOfDecimals.get();
    }
    public final void setNoOfDecimals(final int NO_OF_DECIMALS) {
        if (null == noOfDecimals) {
            _noOfDecimals = clamp(0, 10, NO_OF_DECIMALS);
        } else {
            noOfDecimals.set(clamp(0, 10, NO_OF_DECIMALS));
        }
    }
    public final IntegerProperty noOfDecimalsProperty() {
        if (null == noOfDecimals) {
            noOfDecimals = new SimpleIntegerProperty(this, "noOfDecimals", _noOfDecimals);
        }
        return noOfDecimals;
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

    public final boolean isAnimated() {
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

    public final boolean isAlwaysRound() {
        return null == alwaysRound ? _alwaysRound : alwaysRound.get();
    }
    public final void setAlwaysRound(final boolean ALWAYS_ROUND) {
        if (null == alwaysRound) {
            _alwaysRound = ALWAYS_ROUND;
        } else {
            alwaysRound.set(ALWAYS_ROUND);
        }
    }
    public final BooleanProperty alwaysRoundProperty() {
        if (null == alwaysRound) {
            alwaysRound = new SimpleBooleanProperty(this, "alwaysRound", _alwaysRound);
        }
        return alwaysRound;
    }

    public double getStartAngle() {
        return null == startAngle ? _startAngle : startAngle.get();
    }
    public final void setStartAngle(final double START_ANGLE) {
        if (null == startAngle) {
            _startAngle = clamp(0, 360, START_ANGLE);
        } else {
            startAngle.set(clamp(0, 360, START_ANGLE));
        }
    }
    public final DoubleProperty startAngleProperty() {
        if (null == startAngle) {
            startAngle = new SimpleDoubleProperty(this, "startAngle", _startAngle);
        }
        return startAngle;
    }

    public final Duration getAnimationTime() {
        return animationTime;
    }
    public final void setAnimationTime(final Duration ANIMATION_TIME) {
        animationTime = clamp(Duration.millis(20), Duration.millis(5000), ANIMATION_TIME);
    }

    public final double getAngleRange() {
        return null == angleRange ? _angleRange : angleRange.get();
    }
    public final void setAngleRange(final double ANGLE_RANGE) {
        if (null == angleRange) {
            _angleRange = clamp(0, 360, ANGLE_RANGE);
        } else {
            angleRange.set(clamp(0, 360, ANGLE_RANGE));
        }
    }
    public final DoubleProperty angleRangeProperty() {
        if (null == angleRange) {
            angleRange = new SimpleDoubleProperty(this, "angleRange", _angleRange);
        }
        return angleRange;
    }

    // Properties related to visualization
    public final Gauge.NeedleType getNeedleType() {
        return null == needleType ? _needleType : needleType.get();
    }
    public final void setNeedleType(final Gauge.NeedleType NEEDLE_TYPE) {
        if (null == needleType) {
            _needleType = NEEDLE_TYPE;
        } else {
            needleType.set(NEEDLE_TYPE);
        }
    }
    public final ObjectProperty<NeedleType> needleTypeProperty() {
        if (null == needleType) {
            needleType = new SimpleObjectProperty<>(this, "needleType", _needleType);
        }
        return needleType;
    }

    public final Color getNeedleColor() {
        return null == needleColor ? _needleColor : needleColor.get();
    }
    public final void setNeedleColor(final Color NEEDLE_COLOR) {
        if (null == needleColor) {
            _needleColor = NEEDLE_COLOR;
        } else {
            needleColor.set(NEEDLE_COLOR);
        }
    }
    public final ObjectProperty<Color> needleColorProperty() {
        if (null == needleColor) {
            needleColor = new SimpleObjectProperty<>(this, "needleColor", _needleColor);
        }
        return needleColor;
    }

    public final Gauge.TickLabelOrientation getTickLabelOrientation() {
        return null == tickLabelOrientation ? _tickLabelOrientation : tickLabelOrientation.get();
    }
    public final void setTickLabelOrientation(final Gauge.TickLabelOrientation TICK_LABEL_ORIENTATION) {
        if (null == tickLabelOrientation) {
            _tickLabelOrientation = TICK_LABEL_ORIENTATION;
        } else {
            tickLabelOrientation.set(TICK_LABEL_ORIENTATION);
        }
    }
    public final ObjectProperty<TickLabelOrientation> tickLabelOrientationProperty() {
        if (null == tickLabelOrientation) {
            tickLabelOrientation = new SimpleObjectProperty<>(this, "tickLabelOrientation", _tickLabelOrientation);
        }
        return tickLabelOrientation;
    }

    public final Gauge.NumberFormat getNumberFormat() {
        return null == numberFormat ? _numberFormat : numberFormat.get();
    }
    public final void setNumberFormat(final Gauge.NumberFormat NUMBER_FORMAT) {
        if (null == numberFormat) {
            _numberFormat = NUMBER_FORMAT;
        } else {
            numberFormat.set(NUMBER_FORMAT);
        }
    }
    public final ObjectProperty<NumberFormat> numberFormatProperty() {
        if (null == numberFormat) {
            numberFormat = new SimpleObjectProperty<>(this, "numberFormat", _numberFormat);
        }
        return numberFormat;
    }

    public final List<Section> getSections() {
        return null == sections ? _sections : sections.get();
    }
    public final void setSections(final List<Section> SECTIONS) {
        if (null == sections) {
            _sections.clear();
            _sections.addAll(SECTIONS);
        } else {
            sections.setAll(SECTIONS);
        }
    }
    public final void setSections(final Section... SECTIONS) {
        setSections(Arrays.asList(SECTIONS));
    }
    public final ListProperty<Section> sectionsProperty() {
        if (null == sections) {
            sections = new SimpleListProperty<>(this, "sections", FXCollections.observableArrayList(_sections));
        }
        return sections;
    }

    public final double getMajorTickSpace() {
        return null == majorTickSpace ? _majorTickSpace : majorTickSpace.get();
    }
    public final void setMajorTickSpace(final double MAJOR_TICK_SPACE) {
        if (null == majorTickSpace) {
            _majorTickSpace = MAJOR_TICK_SPACE;
        } else {
            majorTickSpace.set(MAJOR_TICK_SPACE);
        }
    }
    public final DoubleProperty majorTickSpaceProperty() {
        if (null == majorTickSpace) {
            majorTickSpace = new SimpleDoubleProperty(this, "majorTickSpace", _majorTickSpace);
        }
        return majorTickSpace;
    }

    public final double getMinorTickSpace() {
        return null == minorTickSpace ? _minorTickSpace : minorTickSpace.get();
    }
    public final void setMinorTickSpace(final double MINOR_TICK_SPACE) {
        if (null == minorTickSpace) {
            _minorTickSpace = MINOR_TICK_SPACE;
        } else {
            minorTickSpace.set(MINOR_TICK_SPACE);
        }
    }
    public final DoubleProperty minorTickSpaceProperty() {
        if (null == minorTickSpace) {
            minorTickSpace = new SimpleDoubleProperty(this, "minorTickSpace", _minorTickSpace);
        }
        return minorTickSpace;
    }

    public final boolean isLcdEnabled() {
        return null == lcdEnabled ? _lcdEnabled : lcdEnabled.get();
    }
    public final void setLcdEnabled(final boolean LCD_ENABLED) {
        if (null == lcdEnabled) {
            _lcdEnabled = LCD_ENABLED;
        } else {
            lcdEnabled.set(LCD_ENABLED);
        }
    }
    public final BooleanProperty lcdEnabledProperty() {
        if (null == lcdEnabled) {
            lcdEnabled = new SimpleBooleanProperty(this, "lcdEnabled", _lcdEnabled);
        }
        return lcdEnabled;
    }


    private double clamp(final double MIN_VALUE, final double MAX_VALUE, final double VALUE) {
        if (VALUE < MIN_VALUE) return MIN_VALUE;
        if (VALUE > MAX_VALUE) return MAX_VALUE;
        return VALUE;
    }

    private int clamp(final int MIN_VALUE, final int MAX_VALUE, final int VALUE) {
        if (VALUE < MIN_VALUE) return MIN_VALUE;
        if (VALUE > MAX_VALUE) return MAX_VALUE;
        return VALUE;
    }

    private Duration clamp(final Duration MIN_VALUE, final Duration MAX_VALUE, final Duration VALUE) {
        if (VALUE.lessThan(MIN_VALUE)) return MIN_VALUE;
        if (VALUE.greaterThan(MAX_VALUE)) return MAX_VALUE;
        return VALUE;
    }

    private void validate() {
        if (getThreshold() < getMinValue()) setThreshold(getMinValue());
        if (getThreshold() > getMaxValue()) setThreshold(getMaxValue());
        if (getValue() < getMinValue()) setValue(getMinValue());
        if (getValue() > getMaxValue()) setValue(getMaxValue());
    }


    // ******************** CSS Stylable Properties ***************************
    public final Paint getTickMarkFill() {
        return null == tickMarkFill ? Color.BLACK : tickMarkFill.get();
    }
    public final void setTickMarkFill(Paint value) {
        tickMarkFillProperty().set(value);
    }
    public final ObjectProperty<Paint> tickMarkFillProperty() {
        if (null == tickMarkFill) {
            tickMarkFill = new StyleableObjectProperty<Paint>(Color.BLACK) {

                @Override public CssMetaData getCssMetaData() { return StyleableProperties.TICK_MARK_FILL; }

                @Override public Object getBean() { return Gauge.this; }

                @Override public String getName() { return "tickMarkFill"; }
            };
        }
        return tickMarkFill;
    }

    public final Paint getTickLabelFill() {
        return null == tickLabelFill ? Color.BLACK : tickLabelFill.get();
    }
    public final void setTickLabelFill(Paint value) {
        tickLabelFillProperty().set(value);
    }
    public final ObjectProperty<Paint> tickLabelFillProperty() {
        if (null == tickLabelFill) {
            tickLabelFill = new StyleableObjectProperty<Paint>(Color.BLACK) {
                @Override public CssMetaData getCssMetaData() { return StyleableProperties.TICK_LABEL_FILL; }
                @Override public Object getBean() { return Gauge.this; }
                @Override public String getName() { return "tickLabelFill"; }
            };
        }
        return tickLabelFill;
    }


    // ******************** CSS Pseudo Classes ********************************
    public final boolean isTouchMode() {
        return null == touchMode ? false : touchMode.get();
    }
    public final void setTouchMode(final boolean TOUCH_MODE) {
        touchModeProperty().set(TOUCH_MODE);
    }
    public final BooleanProperty touchModeProperty() {
        if (null == touchMode) {
            touchMode = new BooleanPropertyBase(false) {
                @Override protected void invalidated() { pseudoClassStateChanged(TOUCH_MODE_PSEUDO_CLASS, get()); }
                @Override public Object getBean() { return this; }
                @Override public String getName() { return "touchMode"; }
            };
        }
        return touchMode;
    }


    // ******************** Style related *************************************
    @Override protected String getUserAgentStylesheet() {
        return getClass().getResource("gauge.css").toExternalForm();
    }
    
    private static class StyleableProperties {
        private static final CssMetaData<Gauge, Paint> TICK_MARK_FILL =
            new CssMetaData<Gauge, Paint>("-tick-mark-fill", PaintConverter.getInstance(), Color.BLACK) {

                @Override public boolean isSettable(Gauge gauge) {
                    return null == gauge.tickMarkFill || !gauge.tickMarkFill.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(Gauge gauge) {
                    return (StyleableProperty) gauge.tickMarkFillProperty();
                }
            };

        private static final CssMetaData<Gauge, Paint> TICK_LABEL_FILL =
            new CssMetaData<Gauge, Paint>("-tick-label-fill", PaintConverter.getInstance(), Color.BLACK) {

                @Override public boolean isSettable(Gauge gauge) {
                    return null == gauge.tickLabelFill || !gauge.tickLabelFill.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(Gauge gauge) {
                    return (StyleableProperty) gauge.tickLabelFillProperty();
                }
            };

        private static final List<CssMetaData<? extends Styleable, ?>> STYLEABLES;
        static {
            final List<CssMetaData<? extends Styleable, ?>> styleables = new ArrayList<>(Control.getClassCssMetaData());
            Collections.addAll(styleables,
                               TICK_MARK_FILL,
                               TICK_LABEL_FILL
            );
            STYLEABLES = Collections.unmodifiableList(styleables);
        }
    }

    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {
        return StyleableProperties.STYLEABLES;
    }

    @Override public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {
        return getClassCssMetaData();
    }
}
