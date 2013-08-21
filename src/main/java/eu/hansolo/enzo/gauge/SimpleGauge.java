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

package eu.hansolo.enzo.gauge;

import com.sun.javafx.css.converters.PaintConverter;
import eu.hansolo.enzo.common.Section;
import eu.hansolo.enzo.gauge.skin.SimpleGaugeSkin;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.css.StyleableObjectProperty;
import javafx.css.StyleableProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * Created by
 * User: hansolo
 * Date: 01.04.13
 * Time: 17:10
 */
public class SimpleGauge extends Control {
    public static final String      STYLE_CLASS_BLUE_TO_RED_5        = "blue-to-red-5";
    public static final String      STYLE_CLASS_GREEN_TO_DARKGREEN_6 = "green-to-darkgreen-6";
    public static final String      STYLE_CLASS_GREEN_TO_RED_6       = "green-to-red-6";
    public static final String      STYLE_CLASS_RED_TO_GREEN_6       = "red-to-green-6";
    public static final String      STYLE_CLASS_BLUE_TO_RED_6        = "blue-to-red-6";
    public static final String      STYLE_CLASS_PURPLE_TO_RED_6      = "purple-to-red-6";
    public static final String      STYLE_CLASS_GREEN_TO_RED_7       = "green-to-red-7";
    public static final String      STYLE_CLASS_RED_TO_GREEN_7       = "red-to-green-7";
    public static final String      STYLE_CLASS_GREEN_TO_RED_10      = "green-to-red-10";
    public static final String      STYLE_CLASS_RED_TO_GREEN_10      = "red-to-green-10";
    public static final String      STYLE_CLASS_PURPLE_TO_CYAN_10    = "purple-to-cyan-10";

    // Default section colors
    private static final Color      DEFAULT_SECTION_TEXT_COLOR       = Color.web("#ffffff");
    private static final Color      DEFAULT_SECTION_FILL_0           = Color.web("#f3622d");
    private static final Color      DEFAULT_SECTION_FILL_1           = Color.web("#fba71b");
    private static final Color      DEFAULT_SECTION_FILL_2           = Color.web("#57b757");
    private static final Color      DEFAULT_SECTION_FILL_3           = Color.web("#f5982b");
    private static final Color      DEFAULT_SECTION_FILL_4           = Color.web("#41a9c9");
    private static final Color      DEFAULT_SECTION_FILL_5           = Color.web("#4258c9");
    private static final Color      DEFAULT_SECTION_FILL_6           = Color.web("#9a42c8");
    private static final Color      DEFAULT_SECTION_FILL_7           = Color.web("#c84164");
    private static final Color      DEFAULT_SECTION_FILL_8           = Color.web("#888888");
    private static final Color      DEFAULT_SECTION_FILL_9           = Color.web("#aaaaaa");

    private DoubleProperty          value;
    private double                  oldValue;
    private DoubleProperty          minValue;
    private double                  exactMinValue;
    private DoubleProperty          maxValue;
    private double                  exactMaxValue;
    private int                     _decimals;
    private IntegerProperty         decimals;
    private String                  _unit;
    private StringProperty          unit;
    private boolean                 _animated;
    private BooleanProperty         animated;
    private double                  animationDuration;
    private double                  _startAngle;
    private DoubleProperty          startAngle;
    private double                  _angleRange;
    private DoubleProperty          angleRange;
    private boolean                 _clockwise;
    private BooleanProperty         clockwise;
    private boolean                 _autoScale;
    private BooleanProperty         autoScale;
    private boolean                 _sectionTextVisible;
    private BooleanProperty         sectionTextVisible;

    private Color                   _needleColor;
    private ObjectProperty<Color>   needleColor;
    private ObservableList<Section> sections;
    private double                  _majorTickSpace;
    private DoubleProperty          majorTickSpace;
    private double                  _minorTickSpace;
    private DoubleProperty          minorTickSpace;

    // CSS styleable properties
    private ObjectProperty<Paint>   sectionTextColor;
    private ObjectProperty<Paint>   sectionFill0;
    private ObjectProperty<Paint>   sectionFill1;
    private ObjectProperty<Paint>   sectionFill2;
    private ObjectProperty<Paint>   sectionFill3;
    private ObjectProperty<Paint>   sectionFill4;
    private ObjectProperty<Paint>   sectionFill5;
    private ObjectProperty<Paint>   sectionFill6;
    private ObjectProperty<Paint>   sectionFill7;
    private ObjectProperty<Paint>   sectionFill8;
    private ObjectProperty<Paint>   sectionFill9;


    // ******************** Constructors **************************************
    public SimpleGauge() {
        getStyleClass().add("simple-gauge");
        value               = new SimpleDoubleProperty(this, "value", 0);
        minValue            = new SimpleDoubleProperty(this, "minValue", 0);
        maxValue            = new SimpleDoubleProperty(this, "maxValue", 100);
        oldValue            = 0;
        _decimals           = 0;
        _unit               = "";
        _animated           = true;
        _startAngle         = 315;
        _angleRange         = 270;
        _clockwise          = true;
        _autoScale          = false;
        _needleColor        = Color.web("#5a615f");
        _sectionTextVisible = false;
        sections            = FXCollections.observableArrayList();
        _majorTickSpace     = 10;
        _minorTickSpace     = 1;
        animationDuration   = 3000;
    }


    // ******************** Methods *******************************************
    public final double getValue() {
        return value.get();
    }
    public final void setValue(final double VALUE) {
        oldValue = value.get();
        value.set(clamp(getMinValue(), getMaxValue(), VALUE));
    }
    public final ReadOnlyDoubleProperty valueProperty() {
        return value;
    }

    public final double getOldValue() {
        return oldValue;
    }

    public final double getMinValue() {
        return minValue.get();
    }
    public final void setMinValue(final double MIN_VALUE) {
        minValue.set(clamp(Double.NEGATIVE_INFINITY, getMaxValue(), MIN_VALUE));
        validate();
    }
    public final DoubleProperty minValueProperty() {
        return minValue;
    }

    public final double getMaxValue() {
        return maxValue.get();
    }
    public final void setMaxValue(final double MAX_VALUE) {
        maxValue.set(clamp(getMinValue(), Double.POSITIVE_INFINITY, MAX_VALUE));
        validate();
    }
    public final DoubleProperty maxValueProperty() {
        return maxValue;
    }

    public final int getDecimals() {
        return null == decimals ? _decimals : decimals.get();
    }
    public final void setDecimals(final int DECIMALS) {
        if (null == decimals) {
            _decimals = clamp(0, 3, DECIMALS);
        } else {
            decimals.set(clamp(0, 3, DECIMALS));
        }
    }
    public final IntegerProperty decimalsProperty() {
        if (null == decimals) {
            decimals = new SimpleIntegerProperty(this, "decimals", _decimals);
        }
        return decimals;
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

    public final double getAnimationDuration() {
        return animationDuration;
    }
    public final void setAnimationDuration(final double ANIMATION_DURATION) {
        animationDuration = clamp(20, 5000, ANIMATION_DURATION);
    }

    public final double getAngleRange() {
        return null == angleRange ? _angleRange : angleRange.get();
    }
    public final void setAngleRange(final double ANGLE_RANGE) {
        if (null == angleRange) {
            _angleRange = clamp(0.0, 360.0, ANGLE_RANGE);
        } else {
            angleRange.set(clamp(0.0, 360.0, ANGLE_RANGE));
        }
    }
    public final DoubleProperty angleRangeProperty() {
        if (null == angleRange) {
            angleRange = new SimpleDoubleProperty(this, "angleRange", _angleRange);
        }
        return angleRange;
    }

    public final boolean isClockwise() {
        return null == clockwise ? _clockwise : clockwise.get();
    }
    public final void setClockwise(final boolean CLOCKWISE) {
        if (null == clockwise) {
            _clockwise = CLOCKWISE;
        } else {
            clockwise.set(CLOCKWISE);
        }
    }
    public final BooleanProperty clockwiseProperty() {
        if (null == clockwise) {
            clockwise = new SimpleBooleanProperty(this, "clockwise", _clockwise);
        }
        return clockwise;
    }

    public final boolean isAutoScale() {
        return null == autoScale ? _autoScale : autoScale.get();
    }
    public final void setAutoScale(final boolean AUTO_SCALE) {
        if (AUTO_SCALE) {
            exactMinValue = getMinValue();
            exactMaxValue = getMaxValue();
        } else {
            setMinValue(exactMinValue);
            setMaxValue(exactMaxValue);
        }
        if (null == autoScale) {
            _autoScale = AUTO_SCALE;
        } else {
            autoScale.set(AUTO_SCALE);
        }
    }
    public final BooleanProperty autoScaleProperty() {
        if (null == autoScale) {
            autoScale = new SimpleBooleanProperty(this, "autoScale", _autoScale);
        }
        return autoScale;
    }

    // Properties related to visualization
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

    public final ObservableList<Section> getSections() {
        return sections;
    }
    public final void setSections(final List<Section> SECTIONS) {
        sections.setAll(SECTIONS);
    }
    public final void setSections(final Section... SECTIONS) {
        setSections(Arrays.asList(SECTIONS));
    }
    public final void addSection(final Section SECTION) {
        if (!sections.contains(SECTION)) sections.add(SECTION);
    }
    public final void removeSection(final Section SECTION) {
        if (sections.contains(SECTION)) sections.remove(SECTION);
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

    public final boolean isSectionTextVisible() {
        return null == sectionTextVisible ? _sectionTextVisible : sectionTextVisible.get();
    }
    public final void setSectionTextVisible(final boolean SECTION_TEXT_VISIBLE) {
        if (null == sectionTextVisible) {
            _sectionTextVisible = SECTION_TEXT_VISIBLE;
        } else {
            sectionTextVisible.set(SECTION_TEXT_VISIBLE);
        }
    }
    public final BooleanProperty sectionTextVisibleProperty() {
        if (null == sectionTextVisible) {
            sectionTextVisible = new SimpleBooleanProperty(this, "sectionTextVisible", _sectionTextVisible);
        }
        return sectionTextVisible;
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

    public void calcAutoScale() {
        if (isAutoScale()) {
            double maxNoOfMajorTicks = 10;
            double maxNoOfMinorTicks = 10;
            double niceMinValue;
            double niceMaxValue;
            double niceRange;
            niceRange = (calcNiceNumber((getMaxValue() - getMinValue()), false));
            majorTickSpace.set(calcNiceNumber(niceRange / (maxNoOfMajorTicks - 1), true));
            niceMinValue = (Math.floor(getMinValue() / majorTickSpace.doubleValue()) * majorTickSpace.doubleValue());
            niceMaxValue = (Math.ceil(getMaxValue() / majorTickSpace.doubleValue()) * majorTickSpace.doubleValue());
            minorTickSpace.set(calcNiceNumber(majorTickSpace.doubleValue() / (maxNoOfMinorTicks - 1), true));
            setMinValue(niceMinValue);
            setMaxValue(niceMaxValue);
        }
    }

    /**
     * Returns a "niceScaling" number approximately equal to the range.
     * Rounds the number if ROUND == true.
     * Takes the ceiling if ROUND = false.
     *
     * @param RANGE the value range (maxValue - minValue)
     * @param ROUND whether to round the result or ceil
     * @return a "niceScaling" number to be used for the value range
     */
    private double calcNiceNumber(final double RANGE, final boolean ROUND) {
        final double EXPONENT = Math.floor(Math.log10(RANGE));   // exponent of range
        final double FRACTION = RANGE / Math.pow(10, EXPONENT);  // fractional part of range
        //final double MOD      = FRACTION % 0.5;                  // needed for large number scale
        double niceFraction;

        // niceScaling
        /*
        if (isLargeNumberScale()) {
            if (MOD != 0) {
                niceFraction = FRACTION - MOD;
                niceFraction += 0.5;
            } else {
                niceFraction = FRACTION;
            }
        } else {
        */

        if (ROUND) {
            if (FRACTION < 1.5) {
                niceFraction = 1;
            } else if (FRACTION < 3) {
                niceFraction = 2;
            } else if (FRACTION < 7) {
                niceFraction = 5;
            } else {
                niceFraction = 10;
            }
        } else {
            if (Double.compare(FRACTION, 1) <= 0) {
                niceFraction = 1;
            } else if (Double.compare(FRACTION, 2) <= 0) {
                niceFraction = 2;
            } else if (Double.compare(FRACTION, 5) <= 0) {
                niceFraction = 5;
            } else {
                niceFraction = 10;
            }
        }
        //}
        return niceFraction * Math.pow(10, EXPONENT);
    }

    private void validate() {
        if (getValue() < getMinValue()) setValue(getMinValue());
        if (getValue() > getMaxValue()) setValue(getMaxValue());
        /*
        for (Section section : sections) {
            if (section.getStart() < getMinValue()) section.setStart(getMinValue());
            if (section.getStart() > getMaxValue()) section.setStart(getMaxValue());
            if (section.getStop() < getMinValue()) section.setStop(getMinValue());
            if (section.getStop() > getMaxValue()) section.setStop(getMaxValue());
        }
        */
    }


    // ******************** CSS Stylable Properties ***************************
    public final Paint getSectionTextColor() {
        return null == sectionTextColor ? DEFAULT_SECTION_TEXT_COLOR : sectionTextColor.get();
    }
    public final void setSectionTextColor(Paint value) {
        sectionTextColorProperty().set(value);
    }
    public final ObjectProperty<Paint> sectionTextColorProperty() {
        if (null == sectionTextColor) {
            sectionTextColor = new StyleableObjectProperty<Paint>(DEFAULT_SECTION_TEXT_COLOR) {
                @Override public CssMetaData getCssMetaData() { return StyleableProperties.SECTION_TEXT_COLOR; }
                @Override public Object getBean() { return this; }
                @Override public String getName() { return "sectionTextColor"; }
            };
        }
        return sectionTextColor;
    }

    public final Paint getSectionFill0() {
        return null == sectionFill0 ? DEFAULT_SECTION_FILL_0 : sectionFill0.get();
    }
    public final void setSectionFill0(Paint value) {
        sectionFill0Property().set(value);
    }
    public final ObjectProperty<Paint> sectionFill0Property() {
        if (null == sectionFill0) {
            sectionFill0 = new StyleableObjectProperty<Paint>(DEFAULT_SECTION_FILL_0) {
                @Override public CssMetaData getCssMetaData() { return StyleableProperties.SECTION_FILL_0; }
                @Override public Object getBean() { return this; }
                @Override public String getName() { return "sectionFill0"; }
            };
        }
        return sectionFill0;
    }

    public final Paint getSectionFill1() {
        return null == sectionFill1 ? DEFAULT_SECTION_FILL_1 : sectionFill1.get();
    }
    public final void setSectionFill1(Paint value) {
        sectionFill1Property().set(value);
    }
    public final ObjectProperty<Paint> sectionFill1Property() {
        if (null == sectionFill1) {
            sectionFill1 = new StyleableObjectProperty<Paint>(DEFAULT_SECTION_FILL_1) {
                @Override public CssMetaData getCssMetaData() { return StyleableProperties.SECTION_FILL_1; }
                @Override public Object getBean() { return this; }
                @Override public String getName() { return "sectionFill1"; }
            };
        }
        return sectionFill1;
    }

    public final Paint getSectionFill2() {
        return null == sectionFill2 ? DEFAULT_SECTION_FILL_2 : sectionFill2.get();
    }
    public final void setSectionFill2(Paint value) {
        sectionFill2Property().set(value);
    }
    public final ObjectProperty<Paint> sectionFill2Property() {
        if (null == sectionFill2) {
            sectionFill2 = new StyleableObjectProperty<Paint>(DEFAULT_SECTION_FILL_2) {
                @Override public CssMetaData getCssMetaData() { return StyleableProperties.SECTION_FILL_2; }
                @Override public Object getBean() { return this; }
                @Override public String getName() { return "sectionFill2"; }
            };
        }
        return sectionFill2;
    }

    public final Paint getSectionFill3() {
        return null == sectionFill3 ? DEFAULT_SECTION_FILL_3 : sectionFill3.get();
    }
    public final void setSectionFill3(Paint value) {
        sectionFill3Property().set(value);
    }
    public final ObjectProperty<Paint> sectionFill3Property() {
        if (null == sectionFill3) {
            sectionFill3 = new StyleableObjectProperty<Paint>(DEFAULT_SECTION_FILL_3) {
                @Override public CssMetaData getCssMetaData() { return StyleableProperties.SECTION_FILL_3; }
                @Override public Object getBean() { return this; }
                @Override public String getName() { return "sectionFill3"; }
            };
        }
        return sectionFill3;
    }

    public final Paint getSectionFill4() {
        return null == sectionFill4 ? DEFAULT_SECTION_FILL_4 : sectionFill4.get();
    }
    public final void setSectionFill4(Paint value) {
        sectionFill4Property().set(value);
    }
    public final ObjectProperty<Paint> sectionFill4Property() {
        if (null == sectionFill4) {
            sectionFill4 = new StyleableObjectProperty<Paint>(DEFAULT_SECTION_FILL_4) {
                @Override public CssMetaData getCssMetaData() { return StyleableProperties.SECTION_FILL_4; }
                @Override public Object getBean() { return this; }
                @Override public String getName() { return "sectionFill4"; }
            };
        }
        return sectionFill4;
    }

    public final Paint getSectionFill5() {
        return null == sectionFill5 ? DEFAULT_SECTION_FILL_5 : sectionFill5.get();
    }
    public final void setSectionFill5(Paint value) {
        sectionFill5Property().set(value);
    }
    public final ObjectProperty<Paint> sectionFill5Property() {
        if (null == sectionFill5) {
            sectionFill5 = new StyleableObjectProperty<Paint>(DEFAULT_SECTION_FILL_5) {
                @Override public CssMetaData getCssMetaData() { return StyleableProperties.SECTION_FILL_5; }
                @Override public Object getBean() { return this; }
                @Override public String getName() { return "sectionFill5"; }
            };
        }
        return sectionFill5;
    }

    public final Paint getSectionFill6() {
        return null == sectionFill6 ? DEFAULT_SECTION_FILL_6 : sectionFill6.get();
    }
    public final void setSectionFill6(Paint value) {
        sectionFill6Property().set(value);
    }
    public final ObjectProperty<Paint> sectionFill6Property() {
        if (null == sectionFill6) {
            sectionFill6 = new StyleableObjectProperty<Paint>(DEFAULT_SECTION_FILL_6) {
                @Override public CssMetaData getCssMetaData() { return StyleableProperties.SECTION_FILL_6; }
                @Override public Object getBean() { return this; }
                @Override public String getName() { return "sectionFill6"; }
            };
        }
        return sectionFill6;
    }

    public final Paint getSectionFill7() {
        return null == sectionFill7 ? DEFAULT_SECTION_FILL_7 : sectionFill7.get();
    }
    public final void setSectionFill7(Paint value) {
        sectionFill7Property().set(value);
    }
    public final ObjectProperty<Paint> sectionFill7Property() {
        if (null == sectionFill7) {
            sectionFill7 = new StyleableObjectProperty<Paint>(DEFAULT_SECTION_FILL_7) {
                @Override public CssMetaData getCssMetaData() { return StyleableProperties.SECTION_FILL_7; }
                @Override public Object getBean() { return this; }
                @Override public String getName() { return "sectionFill7"; }
            };
        }
        return sectionFill7;
    }

    public final Paint getSectionFill8() {
        return null == sectionFill8 ? DEFAULT_SECTION_FILL_8 : sectionFill8.get();
    }
    public final void setSectionFill8(Paint value) {
        sectionFill8Property().set(value);
    }
    public final ObjectProperty<Paint> sectionFill8Property() {
        if (null == sectionFill8) {
            sectionFill8 = new StyleableObjectProperty<Paint>(DEFAULT_SECTION_FILL_8) {
                @Override public CssMetaData getCssMetaData() { return StyleableProperties.SECTION_FILL_8; }
                @Override public Object getBean() { return this; }
                @Override public String getName() { return "sectionFill8"; }
            };
        }
        return sectionFill8;
    }

    public final Paint getSectionFill9() {
        return null == sectionFill9 ? DEFAULT_SECTION_FILL_9 : sectionFill9.get();
    }
    public final void setSectionFill9(Paint value) {
        sectionFill9Property().set(value);
    }
    public final ObjectProperty<Paint> sectionFill9Property() {
        if (null == sectionFill9) {
            sectionFill9 = new StyleableObjectProperty<Paint>(DEFAULT_SECTION_FILL_9) {
                @Override public CssMetaData getCssMetaData() { return StyleableProperties.SECTION_FILL_9; }
                @Override public Object getBean() { return this; }
                @Override public String getName() { return "sectionFill9"; }
            };
        }
        return sectionFill9;
    }


    // ******************** Style related *************************************
    @Override protected Skin createDefaultSkin() {
        return new SimpleGaugeSkin(this);
    }

    @Override protected String getUserAgentStylesheet() {
        return getClass().getResource("simplegauge.css").toExternalForm();
    }

    private static class StyleableProperties {
        private static final CssMetaData<SimpleGauge, Paint> SECTION_TEXT_COLOR =
            new CssMetaData<SimpleGauge, Paint>("-section-text", PaintConverter.getInstance(), DEFAULT_SECTION_TEXT_COLOR) {

                @Override public boolean isSettable(SimpleGauge gauge) {
                    return null == gauge.sectionTextColor || !gauge.sectionTextColor.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(SimpleGauge gauge) {
                    return (StyleableProperty) gauge.sectionTextColorProperty();
                }

                @Override public Paint getInitialValue(SimpleGauge gauge) {
                    return gauge.getSectionTextColor();
                }
            };

        private static final CssMetaData<SimpleGauge, Paint> SECTION_FILL_0 =
            new CssMetaData<SimpleGauge, Paint>("-section-fill-0", PaintConverter.getInstance(), DEFAULT_SECTION_FILL_0) {

                @Override public boolean isSettable(SimpleGauge gauge) {
                    return null == gauge.sectionFill0 || !gauge.sectionFill0.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(SimpleGauge gauge) {
                    return (StyleableProperty) gauge.sectionFill0Property();
                }

                @Override public Paint getInitialValue(SimpleGauge gauge) {
                    return gauge.getSectionFill0();
                }
            };

        private static final CssMetaData<SimpleGauge, Paint> SECTION_FILL_1 =
            new CssMetaData<SimpleGauge, Paint>("-section-fill-1", PaintConverter.getInstance(), DEFAULT_SECTION_FILL_1) {

                @Override public boolean isSettable(SimpleGauge gauge) {
                    return null == gauge.sectionFill1 || !gauge.sectionFill1.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(SimpleGauge gauge) {
                    return (StyleableProperty) gauge.sectionFill1Property();
                }

                @Override public Paint getInitialValue(SimpleGauge gauge) {
                    return gauge.getSectionFill1();
                }
            };

        private static final CssMetaData<SimpleGauge, Paint> SECTION_FILL_2 =
            new CssMetaData<SimpleGauge, Paint>("-section-fill-2", PaintConverter.getInstance(), DEFAULT_SECTION_FILL_2) {

                @Override public boolean isSettable(SimpleGauge gauge) {
                    return null == gauge.sectionFill2 || !gauge.sectionFill2.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(SimpleGauge gauge) {
                    return (StyleableProperty) gauge.sectionFill2Property();
                }

                @Override public Paint getInitialValue(SimpleGauge gauge) {
                    return gauge.getSectionFill2();
                }
            };

        private static final CssMetaData<SimpleGauge, Paint> SECTION_FILL_3 =
            new CssMetaData<SimpleGauge, Paint>("-section-fill-3", PaintConverter.getInstance(), DEFAULT_SECTION_FILL_3) {

                @Override public boolean isSettable(SimpleGauge gauge) {
                    return null == gauge.sectionFill3 || !gauge.sectionFill3.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(SimpleGauge gauge) {
                    return (StyleableProperty) gauge.sectionFill3Property();
                }

                @Override public Paint getInitialValue(SimpleGauge gauge) {
                    return gauge.getSectionFill3();
                }
            };

        private static final CssMetaData<SimpleGauge, Paint> SECTION_FILL_4 =
            new CssMetaData<SimpleGauge, Paint>("-section-fill-4", PaintConverter.getInstance(), DEFAULT_SECTION_FILL_4) {

                @Override public boolean isSettable(SimpleGauge gauge) {
                    return null == gauge.sectionFill4 || !gauge.sectionFill4.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(SimpleGauge gauge) {
                    return (StyleableProperty) gauge.sectionFill4Property();
                }

                @Override public Paint getInitialValue(SimpleGauge gauge) {
                    return gauge.getSectionFill4();
                }
            };

        private static final CssMetaData<SimpleGauge, Paint> SECTION_FILL_5 =
            new CssMetaData<SimpleGauge, Paint>("-section-fill-5", PaintConverter.getInstance(), DEFAULT_SECTION_FILL_5) {

                @Override public boolean isSettable(SimpleGauge gauge) {
                    return null == gauge.sectionFill5 || !gauge.sectionFill5.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(SimpleGauge gauge) {
                    return (StyleableProperty) gauge.sectionFill5Property();
                }

                @Override public Paint getInitialValue(SimpleGauge gauge) {
                    return gauge.getSectionFill5();
                }
            };

        private static final CssMetaData<SimpleGauge, Paint> SECTION_FILL_6 =
            new CssMetaData<SimpleGauge, Paint>("-section-fill-6", PaintConverter.getInstance(), DEFAULT_SECTION_FILL_6) {

                @Override public boolean isSettable(SimpleGauge gauge) {
                    return null == gauge.sectionFill6 || !gauge.sectionFill6.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(SimpleGauge gauge) {
                    return (StyleableProperty) gauge.sectionFill6Property();
                }

                @Override public Paint getInitialValue(SimpleGauge gauge) {
                    return gauge.getSectionFill6();
                }
            };

        private static final CssMetaData<SimpleGauge, Paint> SECTION_FILL_7 =
            new CssMetaData<SimpleGauge, Paint>("-section-fill-7", PaintConverter.getInstance(), DEFAULT_SECTION_FILL_7) {

                @Override public boolean isSettable(SimpleGauge gauge) {
                    return null == gauge.sectionFill7 || !gauge.sectionFill7.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(SimpleGauge gauge) {
                    return (StyleableProperty) gauge.sectionFill7Property();
                }

                @Override public Paint getInitialValue(SimpleGauge gauge) {
                    return gauge.getSectionFill7();
                }
            };

        private static final CssMetaData<SimpleGauge, Paint> SECTION_FILL_8 =
            new CssMetaData<SimpleGauge, Paint>("-section-fill-8", PaintConverter.getInstance(), DEFAULT_SECTION_FILL_8) {

                @Override public boolean isSettable(SimpleGauge gauge) {
                    return null == gauge.sectionFill8 || !gauge.sectionFill8.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(SimpleGauge gauge) {
                    return (StyleableProperty) gauge.sectionFill8Property();
                }

                @Override public Paint getInitialValue(SimpleGauge gauge) {
                    return gauge.getSectionFill8();
                }
            };

        private static final CssMetaData<SimpleGauge, Paint> SECTION_FILL_9 =
            new CssMetaData<SimpleGauge, Paint>("-section-fill-9", PaintConverter.getInstance(), DEFAULT_SECTION_FILL_9) {

                @Override public boolean isSettable(SimpleGauge gauge) {
                    return null == gauge.sectionFill9 || !gauge.sectionFill9.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(SimpleGauge gauge) {
                    return (StyleableProperty) gauge.sectionFill9Property();
                }

                @Override public Paint getInitialValue(SimpleGauge gauge) {
                    return gauge.getSectionFill9();
                }
            };

        private static final List<CssMetaData<? extends Styleable, ?>> STYLEABLES;
        static {
            final List<CssMetaData<? extends Styleable, ?>> styleables = new ArrayList<>(Control.getClassCssMetaData());
            Collections.addAll(styleables,
                               SECTION_TEXT_COLOR,
                               SECTION_FILL_0,
                               SECTION_FILL_1,
                               SECTION_FILL_2,
                               SECTION_FILL_3,
                               SECTION_FILL_4,
                               SECTION_FILL_5,
                               SECTION_FILL_6,
                               SECTION_FILL_7,
                               SECTION_FILL_8,
                               SECTION_FILL_9);
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
