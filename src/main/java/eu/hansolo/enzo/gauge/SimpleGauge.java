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
    // Default section colors
    private static final Color      DEFAULT_SECTION_0_FILL = Color.web("#11632f");
    private static final Color      DEFAULT_SECTION_1_FILL = Color.web("#36843d");
    private static final Color      DEFAULT_SECTION_2_FILL = Color.web("#80b940");
    private static final Color      DEFAULT_SECTION_3_FILL = Color.web("#f5982b");
    private static final Color      DEFAULT_SECTION_4_FILL = Color.web("#f06129");
    private static final Color      DEFAULT_SECTION_5_FILL = Color.web("#b61f25");
    private static final Color      DEFAULT_SECTION_6_FILL = Color.rgb(255, 191,   0, 0.5);
    private static final Color      DEFAULT_SECTION_7_FILL = Color.rgb(255, 128,   0, 0.5);
    private static final Color      DEFAULT_SECTION_8_FILL = Color.rgb(255,  64,   0, 0.5);
    private static final Color      DEFAULT_SECTION_9_FILL = Color.rgb(255,   0,   0, 0.5);

    private double                  _value;
    private DoubleProperty          value;
    private double                  _oldValue;
    private double                  _minValue;
    private DoubleProperty          minValue;
    private double                  exactMinValue;
    private double                  _maxValue;
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

    private Color                   _needleColor;
    private ObjectProperty<Color>   needleColor;
    private ObservableList<Section> sections;
    private double                  _majorTickSpace;
    private DoubleProperty          majorTickSpace;
    private double                  _minorTickSpace;
    private DoubleProperty          minorTickSpace;

    // CSS styleable properties
    private ObjectProperty<Paint>   section0Fill;
    private ObjectProperty<Paint>   section1Fill;
    private ObjectProperty<Paint>   section2Fill;
    private ObjectProperty<Paint>   section3Fill;
    private ObjectProperty<Paint>   section4Fill;
    private ObjectProperty<Paint>   section5Fill;
    private ObjectProperty<Paint>   section6Fill;
    private ObjectProperty<Paint>   section7Fill;
    private ObjectProperty<Paint>   section8Fill;
    private ObjectProperty<Paint>   section9Fill;


    // ******************** Constructors **************************************
    public SimpleGauge() {
        getStyleClass().add("simple-gauge");
        _value            = 0;
        _oldValue         = 0;
        _minValue         = 0;
        _maxValue         = 100;
        _decimals         = 0;
        _unit             = "";
        _animated         = true;
        _startAngle       = 315;
        _angleRange       = 270;
        _clockwise        = true;
        _autoScale        = false;
        _needleColor      = Color.web("#5a615f");
        sections          = FXCollections.observableArrayList(new Section(0, 16.66666),
                                                              new Section(16.66666, 33.33333),
                                                              new Section(33.33333, 50.0),
                                                              new Section(50.0, 66.66666),
                                                              new Section(66.66666, 83.33333),
                                                              new Section(83.33333, 100.0));
        _majorTickSpace   = 10;
        _minorTickSpace   = 1;
        animationDuration = 3000;
    }


    // ******************** Methods *******************************************
    public final double getValue() {
        return null == value ? _value : value.get();
    }
    public final void setValue(final double VALUE) {
        if (null == value) {
            _oldValue = _value;
            _value    = clamp(getMinValue(), getMaxValue(), VALUE);
        } else {
            _oldValue = value.get();
            value.set(clamp(getMinValue(), getMaxValue(), VALUE));
        }
    }
    public final ReadOnlyDoubleProperty valueProperty() {
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
        for (Section section : sections) {
            if (section.getStart() < getMinValue()) section.setStart(getMinValue());
            if (section.getStart() > getMaxValue()) section.setStart(getMaxValue());
            if (section.getStop() < getMinValue()) section.setStop(getMinValue());
            if (section.getStop() > getMaxValue()) section.setStop(getMaxValue());
        }
    }


    // ******************** CSS Stylable Properties ***************************
    public final Paint getSection0Fill() {
        return null == section0Fill ? DEFAULT_SECTION_0_FILL : section0Fill.get();
    }
    public final void setSection0Fill(Paint value) {
        section0FillProperty().set(value);
    }
    public final ObjectProperty<Paint> section0FillProperty() {
        if (null == section0Fill) {
            section0Fill = new StyleableObjectProperty<Paint>(DEFAULT_SECTION_0_FILL) {
                @Override public CssMetaData getCssMetaData() { return StyleableProperties.SECTION_0_FILL; }
                @Override public Object getBean() { return this; }
                @Override public String getName() { return "section0Fill"; }
            };
        }
        return section0Fill;
    }

    public final Paint getSection1Fill() {
        return null == section1Fill ? DEFAULT_SECTION_1_FILL : section1Fill.get();
    }
    public final void setSection1Fill(Paint value) {
        section1FillProperty().set(value);
    }
    public final ObjectProperty<Paint> section1FillProperty() {
        if (null == section1Fill) {
            section1Fill = new StyleableObjectProperty<Paint>(DEFAULT_SECTION_1_FILL) {
                @Override public CssMetaData getCssMetaData() { return StyleableProperties.SECTION_1_FILL; }
                @Override public Object getBean() { return this; }
                @Override public String getName() { return "section1Fill"; }
            };
        }
        return section1Fill;
    }

    public final Paint getSection2Fill() {
        return null == section2Fill ? DEFAULT_SECTION_2_FILL : section2Fill.get();
    }
    public final void setSection2Fill(Paint value) {
        section2FillProperty().set(value);
    }
    public final ObjectProperty<Paint> section2FillProperty() {
        if (null == section2Fill) {
            section2Fill = new StyleableObjectProperty<Paint>(DEFAULT_SECTION_2_FILL) {
                @Override public CssMetaData getCssMetaData() { return StyleableProperties.SECTION_2_FILL; }
                @Override public Object getBean() { return this; }
                @Override public String getName() { return "section2Fill"; }
            };
        }
        return section2Fill;
    }

    public final Paint getSection3Fill() {
        return null == section3Fill ? DEFAULT_SECTION_3_FILL : section3Fill.get();
    }
    public final void setSection3Fill(Paint value) {
        section3FillProperty().set(value);
    }
    public final ObjectProperty<Paint> section3FillProperty() {
        if (null == section3Fill) {
            section3Fill = new StyleableObjectProperty<Paint>(DEFAULT_SECTION_3_FILL) {
                @Override public CssMetaData getCssMetaData() { return StyleableProperties.SECTION_3_FILL; }
                @Override public Object getBean() { return this; }
                @Override public String getName() { return "section3Fill"; }
            };
        }
        return section3Fill;
    }

    public final Paint getSection4Fill() {
        return null == section4Fill ? DEFAULT_SECTION_4_FILL : section4Fill.get();
    }
    public final void setSection4Fill(Paint value) {
        section4FillProperty().set(value);
    }
    public final ObjectProperty<Paint> section4FillProperty() {
        if (null == section4Fill) {
            section4Fill = new StyleableObjectProperty<Paint>(DEFAULT_SECTION_4_FILL) {
                @Override public CssMetaData getCssMetaData() { return StyleableProperties.SECTION_4_FILL; }
                @Override public Object getBean() { return this; }
                @Override public String getName() { return "section4Fill"; }
            };
        }
        return section4Fill;
    }

    public final Paint getSection5Fill() {
        return null == section5Fill ? DEFAULT_SECTION_5_FILL : section5Fill.get();
    }
    public final void setSection5Fill(Paint value) {
        section5FillProperty().set(value);
    }
    public final ObjectProperty<Paint> section5FillProperty() {
        if (null == section5Fill) {
            section5Fill = new StyleableObjectProperty<Paint>(DEFAULT_SECTION_5_FILL) {
                @Override public CssMetaData getCssMetaData() { return StyleableProperties.SECTION_5_FILL; }
                @Override public Object getBean() { return this; }
                @Override public String getName() { return "section5Fill"; }
            };
        }
        return section5Fill;
    }

    public final Paint getSection6Fill() {
        return null == section6Fill ? DEFAULT_SECTION_6_FILL : section6Fill.get();
    }
    public final void setSection6Fill(Paint value) {
        section6FillProperty().set(value);
    }
    public final ObjectProperty<Paint> section6FillProperty() {
        if (null == section6Fill) {
            section6Fill = new StyleableObjectProperty<Paint>(DEFAULT_SECTION_6_FILL) {
                @Override public CssMetaData getCssMetaData() { return StyleableProperties.SECTION_6_FILL; }
                @Override public Object getBean() { return this; }
                @Override public String getName() { return "section6Fill"; }
            };
        }
        return section6Fill;
    }

    public final Paint getSection7Fill() {
        return null == section7Fill ? DEFAULT_SECTION_7_FILL : section7Fill.get();
    }
    public final void setSection7Fill(Paint value) {
        section7FillProperty().set(value);
    }
    public final ObjectProperty<Paint> section7FillProperty() {
        if (null == section7Fill) {
            section7Fill = new StyleableObjectProperty<Paint>(DEFAULT_SECTION_7_FILL) {
                @Override public CssMetaData getCssMetaData() { return StyleableProperties.SECTION_7_FILL; }
                @Override public Object getBean() { return this; }
                @Override public String getName() { return "section7Fill"; }
            };
        }
        return section7Fill;
    }

    public final Paint getSection8Fill() {
        return null == section8Fill ? DEFAULT_SECTION_8_FILL : section8Fill.get();
    }
    public final void setSection8Fill(Paint value) {
        section8FillProperty().set(value);
    }
    public final ObjectProperty<Paint> section8FillProperty() {
        if (null == section8Fill) {
            section8Fill = new StyleableObjectProperty<Paint>(DEFAULT_SECTION_8_FILL) {
                @Override public CssMetaData getCssMetaData() { return StyleableProperties.SECTION_8_FILL; }
                @Override public Object getBean() { return this; }
                @Override public String getName() { return "section8Fill"; }
            };
        }
        return section8Fill;
    }

    public final Paint getSection9Fill() {
        return null == section9Fill ? DEFAULT_SECTION_9_FILL : section9Fill.get();
    }
    public final void setSection9Fill(Paint value) {
        section9FillProperty().set(value);
    }
    public final ObjectProperty<Paint> section9FillProperty() {
        if (null == section9Fill) {
            section9Fill = new StyleableObjectProperty<Paint>(DEFAULT_SECTION_9_FILL) {
                @Override public CssMetaData getCssMetaData() { return StyleableProperties.SECTION_9_FILL; }
                @Override public Object getBean() { return this; }
                @Override public String getName() { return "section9Fill"; }
            };
        }
        return section9Fill;
    }


    // ******************** Style related *************************************
    @Override protected Skin createDefaultSkin() {
        return new SimpleGaugeSkin(this);
    }

    @Override protected String getUserAgentStylesheet() {
        return getClass().getResource("simplegauge.css").toExternalForm();
    }

    private static class StyleableProperties {
        private static final CssMetaData<SimpleGauge, Paint> SECTION_0_FILL =
            new CssMetaData<SimpleGauge, Paint>("-section0-fill", PaintConverter.getInstance(), DEFAULT_SECTION_0_FILL) {

                @Override public boolean isSettable(SimpleGauge gauge) {
                    return null == gauge.section0Fill || !gauge.section0Fill.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(SimpleGauge gauge) {
                    return (StyleableProperty) gauge.section0FillProperty();
                }

                @Override public Paint getInitialValue(SimpleGauge gauge) {
                    return gauge.getSection0Fill();
                }
            };

        private static final CssMetaData<SimpleGauge, Paint> SECTION_1_FILL =
            new CssMetaData<SimpleGauge, Paint>("-section1-fill", PaintConverter.getInstance(), DEFAULT_SECTION_1_FILL) {

                @Override public boolean isSettable(SimpleGauge gauge) {
                    return null == gauge.section1Fill || !gauge.section1Fill.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(SimpleGauge gauge) {
                    return (StyleableProperty) gauge.section1FillProperty();
                }

                @Override public Paint getInitialValue(SimpleGauge gauge) {
                    return gauge.getSection1Fill();
                }
            };

        private static final CssMetaData<SimpleGauge, Paint> SECTION_2_FILL =
            new CssMetaData<SimpleGauge, Paint>("-section2-fill", PaintConverter.getInstance(), DEFAULT_SECTION_2_FILL) {

                @Override public boolean isSettable(SimpleGauge gauge) {
                    return null == gauge.section2Fill || !gauge.section2Fill.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(SimpleGauge gauge) {
                    return (StyleableProperty) gauge.section2FillProperty();
                }

                @Override public Paint getInitialValue(SimpleGauge gauge) {
                    return gauge.getSection2Fill();
                }
            };

        private static final CssMetaData<SimpleGauge, Paint> SECTION_3_FILL =
            new CssMetaData<SimpleGauge, Paint>("-section3-fill", PaintConverter.getInstance(), DEFAULT_SECTION_3_FILL) {

                @Override public boolean isSettable(SimpleGauge gauge) {
                    return null == gauge.section3Fill || !gauge.section3Fill.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(SimpleGauge gauge) {
                    return (StyleableProperty) gauge.section3FillProperty();
                }

                @Override public Paint getInitialValue(SimpleGauge gauge) {
                    return gauge.getSection3Fill();
                }
            };

        private static final CssMetaData<SimpleGauge, Paint> SECTION_4_FILL =
            new CssMetaData<SimpleGauge, Paint>("-section4-fill", PaintConverter.getInstance(), DEFAULT_SECTION_4_FILL) {

                @Override public boolean isSettable(SimpleGauge gauge) {
                    return null == gauge.section4Fill || !gauge.section4Fill.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(SimpleGauge gauge) {
                    return (StyleableProperty) gauge.section4FillProperty();
                }

                @Override public Paint getInitialValue(SimpleGauge gauge) {
                    return gauge.getSection4Fill();
                }
            };

        private static final CssMetaData<SimpleGauge, Paint> SECTION_5_FILL =
            new CssMetaData<SimpleGauge, Paint>("-section5-fill", PaintConverter.getInstance(), DEFAULT_SECTION_5_FILL) {

                @Override public boolean isSettable(SimpleGauge gauge) {
                    return null == gauge.section5Fill || !gauge.section5Fill.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(SimpleGauge gauge) {
                    return (StyleableProperty) gauge.section5FillProperty();
                }

                @Override public Paint getInitialValue(SimpleGauge gauge) {
                    return gauge.getSection5Fill();
                }
            };

        private static final CssMetaData<SimpleGauge, Paint> SECTION_6_FILL =
            new CssMetaData<SimpleGauge, Paint>("-section6-fill", PaintConverter.getInstance(), DEFAULT_SECTION_6_FILL) {

                @Override public boolean isSettable(SimpleGauge gauge) {
                    return null == gauge.section6Fill || !gauge.section6Fill.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(SimpleGauge gauge) {
                    return (StyleableProperty) gauge.section6FillProperty();
                }

                @Override public Paint getInitialValue(SimpleGauge gauge) {
                    return gauge.getSection6Fill();
                }
            };

        private static final CssMetaData<SimpleGauge, Paint> SECTION_7_FILL =
            new CssMetaData<SimpleGauge, Paint>("-section7-fill", PaintConverter.getInstance(), DEFAULT_SECTION_7_FILL) {

                @Override public boolean isSettable(SimpleGauge gauge) {
                    return null == gauge.section7Fill || !gauge.section7Fill.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(SimpleGauge gauge) {
                    return (StyleableProperty) gauge.section7FillProperty();
                }

                @Override public Paint getInitialValue(SimpleGauge gauge) {
                    return gauge.getSection7Fill();
                }
            };

        private static final CssMetaData<SimpleGauge, Paint> SECTION_8_FILL =
            new CssMetaData<SimpleGauge, Paint>("-section8-fill", PaintConverter.getInstance(), DEFAULT_SECTION_8_FILL) {

                @Override public boolean isSettable(SimpleGauge gauge) {
                    return null == gauge.section8Fill || !gauge.section8Fill.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(SimpleGauge gauge) {
                    return (StyleableProperty) gauge.section8FillProperty();
                }

                @Override public Paint getInitialValue(SimpleGauge gauge) {
                    return gauge.getSection8Fill();
                }
            };

        private static final CssMetaData<SimpleGauge, Paint> SECTION_9_FILL =
            new CssMetaData<SimpleGauge, Paint>("-section9-fill", PaintConverter.getInstance(), DEFAULT_SECTION_9_FILL) {

                @Override public boolean isSettable(SimpleGauge gauge) {
                    return null == gauge.section9Fill || !gauge.section9Fill.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(SimpleGauge gauge) {
                    return (StyleableProperty) gauge.section9FillProperty();
                }

                @Override public Paint getInitialValue(SimpleGauge gauge) {
                    return gauge.getSection9Fill();
                }
            };

        private static final List<CssMetaData<? extends Styleable, ?>> STYLEABLES;
        static {
            final List<CssMetaData<? extends Styleable, ?>> styleables = new ArrayList<>(Control.getClassCssMetaData());
            Collections.addAll(styleables,
                               SECTION_0_FILL,
                               SECTION_1_FILL,
                               SECTION_2_FILL,
                               SECTION_3_FILL,
                               SECTION_4_FILL,
                               SECTION_5_FILL,
                               SECTION_6_FILL,
                               SECTION_7_FILL,
                               SECTION_8_FILL,
                               SECTION_9_FILL);
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
