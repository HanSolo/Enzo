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

package eu.langhammer.enzo2.gauge;

import com.sun.javafx.css.converters.PaintConverter;
import eu.langhammer.enzo2.common.Marker;
import eu.langhammer.enzo2.common.Section;
import eu.langhammer.enzo2.gauge.skin.GaugeSkin;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.BooleanPropertyBase;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.DoublePropertyBase;
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
import javafx.collections.ObservableMap;
import javafx.css.CssMetaData;
import javafx.css.PseudoClass;
import javafx.css.Styleable;
import javafx.css.StyleableObjectProperty;
import javafx.css.StyleableProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.transform.Rotate;
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
    public static enum NeedleType {
        STANDARD("needle-standard");

        public final String STYLE_CLASS;

        private NeedleType(final String STYLE_CLASS) {
            this.STYLE_CLASS = STYLE_CLASS;
        }
    }
    public static enum TickLabelOrientation {
        ORTHOGONAL,
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

    public static final String       STYLE_CLASS_NEEDLE_STANDARD = NeedleType.STANDARD.STYLE_CLASS;

    // Default section colors
    private static final Color       DEFAULT_SECTION_FILL_0      = Color.rgb(0, 0, 178, 0.5);
    private static final Color       DEFAULT_SECTION_FILL_1      = Color.rgb(0, 128, 255, 0.5);
    private static final Color       DEFAULT_SECTION_FILL_2      = Color.rgb(  0, 255, 255, 0.5);
    private static final Color       DEFAULT_SECTION_FILL_3      = Color.rgb(  0, 255,  64, 0.5);
    private static final Color       DEFAULT_SECTION_FILL_4      = Color.rgb(128, 255,   0, 0.5);
    private static final Color       DEFAULT_SECTION_FILL_5      = Color.rgb(255, 255,   0, 0.5);
    private static final Color       DEFAULT_SECTION_FILL_6      = Color.rgb(255, 191,   0, 0.5);
    private static final Color       DEFAULT_SECTION_FILL_7      = Color.rgb(255, 128,   0, 0.5);
    private static final Color       DEFAULT_SECTION_FILL_8      = Color.rgb(255,  64,   0, 0.5);
    private static final Color       DEFAULT_SECTION_FILL_9      = Color.rgb(255,   0,   0, 0.5);
    private static final Color       DEFAULT_HISTOGRAM_FILL      = Color.rgb(  0, 200,   0, 0.3);

    // Default marker colors
    private static final Color       DEFAULT_MARKER_FILL_0       = Color.rgb(  0, 200,   0, 0.5);
    private static final Color       DEFAULT_MARKER_FILL_1       = Color.rgb(200, 200,   0, 0.5);
    private static final Color       DEFAULT_MARKER_FILL_2       = Color.rgb(200,   0,   0, 0.5);
    private static final Color       DEFAULT_MARKER_FILL_3       = Color.rgb(  0,   0, 200, 0.5);
    private static final Color       DEFAULT_MARKER_FILL_4       = Color.rgb(  0, 200, 200, 0.5);

    // CSS Pseudo classes
    private static final PseudoClass INTERACTIVE_PSEUDO_CLASS    = PseudoClass.getPseudoClass("interactive");

    private BooleanProperty                      interactive;

    private DoubleProperty                       value;
    private double                               oldValue;
    private DoubleProperty                       minValue;
    private double                               exactMinValue;
    private DoubleProperty                       maxValue;
    private double                               exactMaxValue;
    private double                               _threshold;
    private DoubleProperty                       threshold;
    private boolean                              _thresholdVisible;
    private BooleanProperty                      thresholdVisible;
    private double                               _minMeasuredValue;
    private DoubleProperty                       minMeasuredValue;
    private boolean                              _minMeasuredValueVisible;
    private BooleanProperty                      minMeasuredValueVisible;
    private double                               _maxMeasuredValue;
    private DoubleProperty                       maxMeasuredValue;
    private boolean                              _maxMeasuredValueVisible;
    private BooleanProperty                      maxMeasuredValueVisible;
    private int                                  _decimals;
    private IntegerProperty                      decimals;
    private String                               _title;
    private StringProperty                       title;
    private String                               _unit;
    private StringProperty                       unit;
    private boolean                              _animated;
    private BooleanProperty                      animated;
    private double                               animationDuration;
    private double                               _startAngle;
    private DoubleProperty                       startAngle;
    private double                               _angleRange;
    private DoubleProperty                       angleRange;
    private boolean                              _clockwise;
    private BooleanProperty                      clockwise;
    private boolean                              _autoScale;
    private BooleanProperty                      autoScale;
    private Gauge.NeedleType                     _needleType;
    private ObjectProperty<NeedleType>           needleType;
    private Color                                _needleColor;
    private ObjectProperty<Color>                needleColor;
    private Gauge.TickLabelOrientation           _tickLabelOrientation;
    private ObjectProperty<TickLabelOrientation> tickLabelOrientation;
    private Gauge.NumberFormat                   _numberFormat;
    private ObjectProperty<NumberFormat>         numberFormat;
    private ObservableList<Section>              sections;
    private boolean                              _sectionsVisible;
    private BooleanProperty                      sectionsVisible;
    private ObservableMap<Marker, Rotate>        markers;
    private boolean                              _markersVisible;
    private BooleanProperty                      markersVisible;
    private double                               _majorTickSpace;
    private DoubleProperty                       majorTickSpace;
    private double                               _minorTickSpace;
    private DoubleProperty                       minorTickSpace;
    private boolean                              _plainValue;
    private BooleanProperty                      plainValue;
    private boolean                              _histogramEnabled;
    private BooleanProperty                      histogramEnabled;
    private boolean                              _dropShadowEnabled;
    private BooleanProperty                      dropShadowEnabled;

    // CSS styleable properties
    private ObjectProperty<Paint>                tickMarkFill;
    private ObjectProperty<Paint>                tickLabelFill;
    private ObjectProperty<Paint>                sectionFill0;
    private ObjectProperty<Paint>                sectionFill1;
    private ObjectProperty<Paint>                sectionFill2;
    private ObjectProperty<Paint>                sectionFill3;
    private ObjectProperty<Paint>                sectionFill4;
    private ObjectProperty<Paint>                sectionFill5;
    private ObjectProperty<Paint>                sectionFill6;
    private ObjectProperty<Paint>                sectionFill7;
    private ObjectProperty<Paint>                sectionFill8;
    private ObjectProperty<Paint>                sectionFill9;
    private ObjectProperty<Paint>                histogramFill;
    private ObjectProperty<Paint>                markerFill0;
    private ObjectProperty<Paint>                markerFill1;
    private ObjectProperty<Paint>                markerFill2;
    private ObjectProperty<Paint>                markerFill3;
    private ObjectProperty<Paint>                markerFill4;


    // ******************** Constructors **************************************
    public Gauge() {
        getStyleClass().add("gauge");
        value                    = new DoublePropertyBase(0) {
            @Override protected void invalidated() {
                set(clamp(getMinValue(), getMaxValue(), get()));
            }
            @Override public Object getBean() { return this; }
            @Override public String getName() { return "value"; }
        };
        minValue                 = new DoublePropertyBase(0) {
            @Override protected void invalidated() {
                //set(clamp(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, get()));
                if (getValue() < get()) setValue(get());
                if (getThreshold() < get()) setThreshold(get());
                for (Marker marker : markers.keySet()) {
                    if (marker.getValue() < get()) marker.setValue(get());                 
                }
                for (Section section : sections) {
                    if (section.getStart() < get()) section.setStart(get());                    
                    if (section.getStop() < get()) section.setStop(get());                    
                }
            }
            @Override public Object getBean() { return this; }
            @Override public String getName() { return "minValue"; }
        };
        maxValue                 = new DoublePropertyBase(100) {
            @Override protected void invalidated() {
                //set(clamp(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, get()));
                if (getValue() > get()) setValue(get());
                if (getThreshold() > get()) setThreshold(get());
                for (Marker marker : markers.keySet()) {                    
                    if (marker.getValue() > get()) marker.setValue(get());
                }
                for (Section section : sections) {                    
                    if (section.getStart() > get()) section.setStart(get());                    
                    if (section.getStop() > get()) section.setStop(get());
                }
            }
            @Override public Object getBean() { return this; }
            @Override public String getName() { return "maxValue"; }
        };
        oldValue                 = 0;
        _threshold               = 50;
        _thresholdVisible        = false;
        _minMeasuredValue        = 100;
        _minMeasuredValueVisible = false;
        _maxMeasuredValue        = 0;
        _maxMeasuredValueVisible = false;
        _decimals                = 1;
        _title                   = "";
        _unit                    = "";
        _animated                = true;
        _startAngle              = 320;
        _angleRange              = 280;
        _clockwise               = true;
        _autoScale               = false;
        _needleType              = NeedleType.STANDARD;
        _needleColor             = Color.RED;
        _tickLabelOrientation    = TickLabelOrientation.HORIZONTAL;
        _numberFormat            = NumberFormat.STANDARD;
        sections                 = FXCollections.observableArrayList();
        _sectionsVisible         = true;
        markers                  = FXCollections.observableHashMap();
        _markersVisible          = true;
        _majorTickSpace          = 10;
        _minorTickSpace          = 1;
        animationDuration        = 800;
        _plainValue              = true;
        _histogramEnabled        = false;
        _dropShadowEnabled       = true;
    }


    // ******************** Methods *******************************************
    public final double getValue() {
        return value.get();
    }
    public final void setValue(final double VALUE) {
        if (isInteractive()) return;
        oldValue = value.get();
        value.set(VALUE);
    }
    public final DoubleProperty valueProperty() {
        return value;
    }

    public final double getOldValue() {
        return oldValue;
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

    public final double getThreshold() {
        return null == threshold ? _threshold : threshold.get();
    }
    public final void setThreshold(final double THRESHOLD) {
        if (null == threshold) {
            _threshold = clamp(getMinValue(), getMaxValue(), THRESHOLD);
        } else {
            threshold.set(THRESHOLD);
        }
    }
    public final DoubleProperty thresholdProperty() {
        if (null == threshold) {            
            threshold = new DoublePropertyBase(_threshold) {
                @Override protected void invalidated() { set(clamp(getMinValue(), getMaxValue(), get())); }
                @Override public Object getBean() { return this; }
                @Override public String getName() { return "threshold"; }
            };
        }
        return threshold;
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
    public final ReadOnlyDoubleProperty minMeasuredValueProperty() {
        if (null == minMeasuredValue) {
            minMeasuredValue = new SimpleDoubleProperty(this, "minMeasuredValue", _minMeasuredValue);
        }
        return minMeasuredValue;
    }

    public final double getMaxMeasuredValue() {
        return null == maxMeasuredValue ? _maxMeasuredValue : maxMeasuredValue.get();
    }
    public final void setMaxMeasuredValue(final double MAX_MEASURED_VALUE) {
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
        setMinMeasuredValue(getValue());
    }
    public void resetMaxMeasuredValue() {
        setMaxMeasuredValue(getValue());
    }
    public void resetMinAndMaxMeasuredValue() {
        setMinMeasuredValue(getValue());
        setMaxMeasuredValue(getValue());
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

    public double getStartAngle() {
        return null == startAngle ? _startAngle : startAngle.get();
    }
    public final void setStartAngle(final double START_ANGLE) {
        if (null == startAngle) {
            _startAngle = clamp(0, 360, START_ANGLE);
        } else {
            startAngle.set(START_ANGLE);
        }
    }
    public final DoubleProperty startAngleProperty() {
        if (null == startAngle) {            
            startAngle = new DoublePropertyBase(_startAngle) {
                @Override protected void invalidated() { set(clamp(0d, 360d, get())); }
                @Override public Object getBean() { return this; }
                @Override public String getName() { return "startAngle"; }
            };
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
            angleRange.set(ANGLE_RANGE);
        }
    }
    public final DoubleProperty angleRangeProperty() {
        if (null == angleRange) {            
            angleRange = new DoublePropertyBase(_angleRange) {
                @Override protected void invalidated() { set(clamp(0d, 360d, get())); }
                @Override public Object getBean() { return this; }
                @Override public String getName() { return "angleRange"; }
            };
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
        if (null == autoScale) {
            _autoScale = AUTO_SCALE;
        } else {
            autoScale.set(AUTO_SCALE);
        }
    }
    public final BooleanProperty autoScaleProperty() {
        if (null == autoScale) {            
            autoScale = new BooleanPropertyBase(_autoScale) {
                @Override protected void invalidated() {
                    if (get()) {
                        exactMinValue = getMinValue();
                        exactMaxValue = getMaxValue();
                    } else {
                        setMinValue(exactMinValue);
                        setMaxValue(exactMaxValue);
                    }                    
                }
                @Override public Object getBean() { return this; }
                @Override public String getName() { return "autoScale"; }
            };
        }
        return autoScale;
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

    public final ObservableMap<Marker, Rotate> getMarkers() {
        return markers;
    }
    public final void setMarkers(final List<Marker> MARKERS) {
        int markerCounter = 0;
        for (Marker marker : MARKERS) {
            Rotate markerRotate = new Rotate(180 - getStartAngle());
            marker.getTransforms().setAll(markerRotate);
            marker.getStyleClass().add("marker" + markerCounter);
            markers.put(marker, markerRotate);
            markerCounter++;
        }
    }
    public final void setMarkers(final Marker... MARKERS) {
        setMarkers(Arrays.asList(MARKERS));
    }
    public final void addMarker(final Marker MARKER) {
        if (!markers.keySet().contains(MARKER)) {
            Rotate markerRotate = new Rotate(180 - getStartAngle());
            MARKER.getTransforms().setAll(markerRotate);
            MARKER.getStyleClass().add("marker" + markers.size());
            markers.put(MARKER, markerRotate);
        }
    }
    public final void removeMarker(final Marker MARKER) {
        if (markers.keySet().contains(MARKER)) markers.remove(MARKER);
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

    /**
     * @return true if the value of the gauge will be drawn without a blend effect
     */
    public final boolean isPlainValue() {
        return null == plainValue ? _plainValue : plainValue.get();
    }
    /**
     * If set to true the value will be visualized without a blend effect
     * @param PLAIN_VALUE
     */
    public final void setPlainValue(final boolean PLAIN_VALUE) {
        if (null == plainValue) {
            _plainValue = PLAIN_VALUE;
        } else {
            plainValue.set(PLAIN_VALUE);
        }
    }
    public final BooleanProperty plainValueProperty() {
        if (null == plainValue) {
            plainValue = new SimpleBooleanProperty(this, "plainValue", _plainValue);
        }
        return plainValue;
    }

    public final boolean isHistogramEnabled() {
        return null == histogramEnabled ? _histogramEnabled : histogramEnabled.get();
    }
    public final void setHistogramEnabled(final boolean HISTOGRAM_ENABLED) {
        if (null == histogramEnabled) {
            _histogramEnabled = HISTOGRAM_ENABLED;
        } else {
            histogramEnabled.set(HISTOGRAM_ENABLED);
        }
    }
    public final BooleanProperty histogramEnabledProperty() {
        if (null == histogramEnabled) {
            histogramEnabled = new SimpleBooleanProperty(this, "histogramEnabled", _histogramEnabled);
        }
        return histogramEnabled;
    }

    public final boolean isDropShadowEnabled() {
        return null == dropShadowEnabled ? _dropShadowEnabled : dropShadowEnabled.get();
    }
    public final void setDropShadowEnabled(final boolean DROP_SHADOW_ENABLED) {
        if (null == dropShadowEnabled) {
            _dropShadowEnabled = DROP_SHADOW_ENABLED;
        } else {
            dropShadowEnabled.set(DROP_SHADOW_ENABLED);
        }
    }
    public final BooleanProperty dropShadowEnabledProperty() {
        if (null == dropShadowEnabled) {
            dropShadowEnabled = new SimpleBooleanProperty(this, "dropShadowEnabled", _dropShadowEnabled);
        }
        return dropShadowEnabled;
    }

    public final boolean isSectionsVisible() {
        return null == sectionsVisible ? _sectionsVisible : sectionsVisible.get();
    }
    public final void setSectionsVisible(final boolean SECTIONS_VISIBLE) {
        if (null == sectionsVisible) {
            _sectionsVisible = SECTIONS_VISIBLE;
        } else {
            sectionsVisible.set(SECTIONS_VISIBLE);
        }
    }
    public final BooleanProperty sectionsVisibleProperty() {
        if (null == sectionsVisible) {
            sectionsVisible = new SimpleBooleanProperty(this, "sectionsVisible", _sectionsVisible);
        }
        return sectionsVisible;
    }

    public final boolean isMarkersVisible() {
        return null == markersVisible ? _markersVisible : markersVisible.get();
    }
    public final void setMarkersVisible(final boolean MARKERS_VISIBLE) {
        if (null == markersVisible) {
            _markersVisible = MARKERS_VISIBLE;
        } else {
            markersVisible.set(MARKERS_VISIBLE);
        }
    }
    public final BooleanProperty markersVisibleProperty() {
        if (null == markersVisible) {
            markersVisible = new SimpleBooleanProperty(this, "markersVisible", _markersVisible);
        }
        return markersVisible;
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
            maxMeasuredValueVisible = new SimpleBooleanProperty(this, "maxMeasuredValueVisible", _maxMeasuredValueVisible);
        }
        return maxMeasuredValueVisible;
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
                @Override public Object getBean() { return Gauge.this; }
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
                @Override public Object getBean() { return Gauge.this; }
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
                @Override public Object getBean() { return Gauge.this; }
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
                @Override public Object getBean() { return Gauge.this; }
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
                @Override public Object getBean() { return Gauge.this; }
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
                @Override public Object getBean() { return Gauge.this; }
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
                @Override public Object getBean() { return Gauge.this; }
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
                @Override public Object getBean() { return Gauge.this; }
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
                @Override public Object getBean() { return Gauge.this; }
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
                @Override public Object getBean() { return Gauge.this; }
                @Override public String getName() { return "sectionFill9"; }
            };
        }
        return sectionFill9;
    }

    public final Paint getHistogramFill() {
        return null == histogramFill ? DEFAULT_HISTOGRAM_FILL : histogramFill.get();
    }
    public final void setHistogramFill(Paint value) {
        histogramFillProperty().set(value);
    }
    public final ObjectProperty<Paint> histogramFillProperty() {
        if (null == histogramFill) {
            histogramFill = new StyleableObjectProperty<Paint>(DEFAULT_HISTOGRAM_FILL) {
                @Override public CssMetaData getCssMetaData() { return StyleableProperties.HISTOGRAM_FILL; }
                @Override public Object getBean() { return Gauge.this; }
                @Override public String getName() { return "histogramFill"; }
            };
        }
        return histogramFill;
    }

    public final Paint getMarkerFill0() {
        return null == markerFill0 ? DEFAULT_MARKER_FILL_0 : markerFill0.get();
    }
    public final void setMarkerFill0(Paint value) {
        markerFill0Property().set(value);
    }
    public final ObjectProperty<Paint> markerFill0Property() {
        if (null == markerFill0) {
            markerFill0 = new StyleableObjectProperty<Paint>(DEFAULT_MARKER_FILL_0) {
                @Override public CssMetaData getCssMetaData() { return StyleableProperties.MARKER_FILL_0; }
                @Override public Object getBean() { return Gauge.this; }
                @Override public String getName() { return "markerFill0"; }
            };
        }
        return markerFill0;
    }

    public final Paint getMarkerFill1() {
        return null == markerFill1 ? DEFAULT_MARKER_FILL_1 : markerFill1.get();
    }
    public final void setMarkerFill1(Paint value) {
        markerFill1Property().set(value);
    }
    public final ObjectProperty<Paint> markerFill1Property() {
        if (null == markerFill1) {
            markerFill1 = new StyleableObjectProperty<Paint>(DEFAULT_MARKER_FILL_1) {
                @Override public CssMetaData getCssMetaData() { return StyleableProperties.MARKER_FILL_1; }
                @Override public Object getBean() { return Gauge.this; }
                @Override public String getName() { return "markerFill1"; }
            };
        }
        return markerFill1;
    }

    public final Paint getMarkerFill2() {
        return null == markerFill2 ? DEFAULT_MARKER_FILL_2 : markerFill2.get();
    }
    public final void setMarkerFill2(Paint value) {
        markerFill2Property().set(value);
    }
    public final ObjectProperty<Paint> markerFill2Property() {
        if (null == markerFill2) {
            markerFill2 = new StyleableObjectProperty<Paint>(DEFAULT_MARKER_FILL_2) {
                @Override public CssMetaData getCssMetaData() { return StyleableProperties.MARKER_FILL_2; }
                @Override public Object getBean() { return Gauge.this; }
                @Override public String getName() { return "markerFill2"; }
            };
        }
        return markerFill2;
    }

    public final Paint getMarkerFill3() {
        return null == markerFill3 ? DEFAULT_MARKER_FILL_3 : markerFill3.get();
    }
    public final void setMarkerFill3(Paint value) {
        markerFill3Property().set(value);
    }
    public final ObjectProperty<Paint> markerFill3Property() {
        if (null == markerFill3) {
            markerFill3 = new StyleableObjectProperty<Paint>(DEFAULT_MARKER_FILL_3) {
                @Override public CssMetaData getCssMetaData() { return StyleableProperties.MARKER_FILL_3; }
                @Override public Object getBean() { return Gauge.this; }
                @Override public String getName() { return "markerFill3"; }
            };
        }
        return markerFill3;
    }

    public final Paint getMarkerFill4() {
        return null == markerFill4 ? DEFAULT_MARKER_FILL_4 : markerFill4.get();
    }
    public final void setMarkerFill4(Paint value) {
        markerFill4Property().set(value);
    }
    public final ObjectProperty<Paint> markerFill4Property() {
        if (null == markerFill4) {
            markerFill4 = new StyleableObjectProperty<Paint>(DEFAULT_MARKER_FILL_4) {
                @Override public CssMetaData getCssMetaData() { return StyleableProperties.MARKER_FILL_4; }
                @Override public Object getBean() { return Gauge.this; }
                @Override public String getName() { return "markerFill4"; }
            };
        }
        return markerFill4;
    }


    // ******************** CSS Pseudo Classes ********************************
    public final boolean isInteractive() {
        return null == interactive ? false : interactive.get();
    }
    public final void setInteractive(final boolean INTERACTIVE) {
        interactiveProperty().set(INTERACTIVE);
    }
    public final BooleanProperty interactiveProperty() {
        if (null == interactive) {
            interactive = new BooleanPropertyBase(false) {
                @Override protected void invalidated() { pseudoClassStateChanged(INTERACTIVE_PSEUDO_CLASS, get()); }
                @Override public Object getBean() { return this; }
                @Override public String getName() { return "interactive"; }
            };
        }
        return interactive;
    }


    // ******************** Style related *************************************
    @Override protected Skin createDefaultSkin() {
        return new GaugeSkin(this);
    }

    @Override public String getUserAgentStylesheet() {
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

        private static final CssMetaData<Gauge, Paint> SECTION_FILL_0 =
            new CssMetaData<Gauge, Paint>("-section-fill-0", PaintConverter.getInstance(), DEFAULT_SECTION_FILL_0) {

                @Override public boolean isSettable(Gauge gauge) {
                    return null == gauge.sectionFill0 || !gauge.sectionFill0.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(Gauge gauge) {
                    return (StyleableProperty) gauge.sectionFill0Property();
                }

                @Override public Paint getInitialValue(Gauge gauge) {
                    return gauge.getSectionFill0();
                }
            };

        private static final CssMetaData<Gauge, Paint> SECTION_FILL_1 =
            new CssMetaData<Gauge, Paint>("-section-fill-1", PaintConverter.getInstance(), DEFAULT_SECTION_FILL_1) {

                @Override public boolean isSettable(Gauge gauge) {
                    return null == gauge.sectionFill1 || !gauge.sectionFill1.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(Gauge gauge) {
                    return (StyleableProperty) gauge.sectionFill1Property();
                }

                @Override public Paint getInitialValue(Gauge gauge) {
                    return gauge.getSectionFill1();
                }
            };

        private static final CssMetaData<Gauge, Paint> SECTION_FILL_2 =
            new CssMetaData<Gauge, Paint>("-section-fill-2", PaintConverter.getInstance(), DEFAULT_SECTION_FILL_2) {

                @Override public boolean isSettable(Gauge gauge) {
                    return null == gauge.sectionFill2 || !gauge.sectionFill2.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(Gauge gauge) {
                    return (StyleableProperty) gauge.sectionFill2Property();
                }

                @Override public Paint getInitialValue(Gauge gauge) {
                    return gauge.getSectionFill2();
                }
            };

        private static final CssMetaData<Gauge, Paint> SECTION_FILL_3 =
            new CssMetaData<Gauge, Paint>("-section-fill-3", PaintConverter.getInstance(), DEFAULT_SECTION_FILL_3) {

                @Override public boolean isSettable(Gauge gauge) {
                    return null == gauge.sectionFill3 || !gauge.sectionFill3.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(Gauge gauge) {
                    return (StyleableProperty) gauge.sectionFill3Property();
                }

                @Override public Paint getInitialValue(Gauge gauge) {
                    return gauge.getSectionFill3();
                }
            };

        private static final CssMetaData<Gauge, Paint> SECTION_FILL_4 =
            new CssMetaData<Gauge, Paint>("-section-fill-4", PaintConverter.getInstance(), DEFAULT_SECTION_FILL_4) {

                @Override public boolean isSettable(Gauge gauge) {
                    return null == gauge.sectionFill4 || !gauge.sectionFill4.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(Gauge gauge) {
                    return (StyleableProperty) gauge.sectionFill4Property();
                }

                @Override public Paint getInitialValue(Gauge gauge) {
                    return gauge.getSectionFill4();
                }
            };

        private static final CssMetaData<Gauge, Paint> SECTION_FILL_5 =
            new CssMetaData<Gauge, Paint>("-section-fill-5", PaintConverter.getInstance(), DEFAULT_SECTION_FILL_5) {

                @Override public boolean isSettable(Gauge gauge) {
                    return null == gauge.sectionFill5 || !gauge.sectionFill5.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(Gauge gauge) {
                    return (StyleableProperty) gauge.sectionFill5Property();
                }

                @Override public Paint getInitialValue(Gauge gauge) {
                    return gauge.getSectionFill5();
                }
            };

        private static final CssMetaData<Gauge, Paint> SECTION_FILL_6 =
            new CssMetaData<Gauge, Paint>("-section-fill-6", PaintConverter.getInstance(), DEFAULT_SECTION_FILL_6) {

                @Override public boolean isSettable(Gauge gauge) {
                    return null == gauge.sectionFill6 || !gauge.sectionFill6.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(Gauge gauge) {
                    return (StyleableProperty) gauge.sectionFill6Property();
                }

                @Override public Paint getInitialValue(Gauge gauge) {
                    return gauge.getSectionFill6();
                }
            };

        private static final CssMetaData<Gauge, Paint> SECTION_FILL_7 =
            new CssMetaData<Gauge, Paint>("-section-fill-7", PaintConverter.getInstance(), DEFAULT_SECTION_FILL_7) {

                @Override public boolean isSettable(Gauge gauge) {
                    return null == gauge.sectionFill7 || !gauge.sectionFill7.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(Gauge gauge) {
                    return (StyleableProperty) gauge.sectionFill7Property();
                }

                @Override public Paint getInitialValue(Gauge gauge) {
                    return gauge.getSectionFill7();
                }
            };

        private static final CssMetaData<Gauge, Paint> SECTION_FILL_8 =
            new CssMetaData<Gauge, Paint>("-section-fill-8", PaintConverter.getInstance(), DEFAULT_SECTION_FILL_8) {

                @Override public boolean isSettable(Gauge gauge) {
                    return null == gauge.sectionFill8 || !gauge.sectionFill8.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(Gauge gauge) {
                    return (StyleableProperty) gauge.sectionFill8Property();
                }

                @Override public Paint getInitialValue(Gauge gauge) {
                    return gauge.getSectionFill8();
                }
            };

        private static final CssMetaData<Gauge, Paint> SECTION_FILL_9 =
            new CssMetaData<Gauge, Paint>("-section-fill-9", PaintConverter.getInstance(), DEFAULT_SECTION_FILL_9) {

                @Override public boolean isSettable(Gauge gauge) {
                    return null == gauge.sectionFill9 || !gauge.sectionFill9.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(Gauge gauge) {
                    return (StyleableProperty) gauge.sectionFill9Property();
                }

                @Override public Paint getInitialValue(Gauge gauge) {
                    return gauge.getSectionFill9();
                }
            };

        private static final CssMetaData<Gauge, Paint> HISTOGRAM_FILL =
            new CssMetaData<Gauge, Paint>("-histogram-fill", PaintConverter.getInstance(), DEFAULT_HISTOGRAM_FILL) {

                @Override public boolean isSettable(Gauge gauge) {
                    return null == gauge.histogramFill || !gauge.histogramFill.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(Gauge gauge) {
                    return (StyleableProperty) gauge.histogramFillProperty();
                }

                @Override public Paint getInitialValue(Gauge gauge) {
                    return gauge.getHistogramFill();
                }
            };

        private static final CssMetaData<Gauge, Paint> MARKER_FILL_0 =
            new CssMetaData<Gauge, Paint>("-marker-fill-0", PaintConverter.getInstance(), DEFAULT_MARKER_FILL_0) {

                @Override public boolean isSettable(Gauge gauge) {
                    return null == gauge.markerFill0 || !gauge.markerFill0.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(Gauge gauge) {
                    return (StyleableProperty) gauge.markerFill0Property();
                }

                @Override public Paint getInitialValue(Gauge gauge) {
                    return gauge.getMarkerFill0();
                }
            };

        private static final CssMetaData<Gauge, Paint> MARKER_FILL_1 =
            new CssMetaData<Gauge, Paint>("-marker-fill-1", PaintConverter.getInstance(), DEFAULT_MARKER_FILL_1) {

                @Override public boolean isSettable(Gauge gauge) {
                    return null == gauge.markerFill1 || !gauge.markerFill1.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(Gauge gauge) {
                    return (StyleableProperty) gauge.markerFill1Property();
                }

                @Override public Paint getInitialValue(Gauge gauge) {
                    return gauge.getMarkerFill1();
                }
            };

        private static final CssMetaData<Gauge, Paint> MARKER_FILL_2 =
            new CssMetaData<Gauge, Paint>("-marker-fill-2", PaintConverter.getInstance(), DEFAULT_MARKER_FILL_2) {

                @Override public boolean isSettable(Gauge gauge) {
                    return null == gauge.markerFill2 || !gauge.markerFill2.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(Gauge gauge) {
                    return (StyleableProperty) gauge.markerFill2Property();
                }

                @Override public Paint getInitialValue(Gauge gauge) {
                    return gauge.getMarkerFill2();
                }
            };

        private static final CssMetaData<Gauge, Paint> MARKER_FILL_3 =
            new CssMetaData<Gauge, Paint>("-marker-fill-3", PaintConverter.getInstance(), DEFAULT_MARKER_FILL_3) {

                @Override public boolean isSettable(Gauge gauge) {
                    return null == gauge.markerFill3 || !gauge.markerFill3.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(Gauge gauge) {
                    return (StyleableProperty) gauge.markerFill3Property();
                }

                @Override public Paint getInitialValue(Gauge gauge) {
                    return gauge.getMarkerFill3();
                }
            };

        private static final CssMetaData<Gauge, Paint> MARKER_FILL_4 =
            new CssMetaData<Gauge, Paint>("-marker-fill-4", PaintConverter.getInstance(), DEFAULT_MARKER_FILL_4) {

                @Override public boolean isSettable(Gauge gauge) {
                    return null == gauge.markerFill4 || !gauge.markerFill4.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(Gauge gauge) {
                    return (StyleableProperty) gauge.markerFill4Property();
                }

                @Override public Paint getInitialValue(Gauge gauge) {
                    return gauge.getMarkerFill4();
                }
            };

        private static final List<CssMetaData<? extends Styleable, ?>> STYLEABLES;
        static {
            final List<CssMetaData<? extends Styleable, ?>> styleables = new ArrayList<>(Control.getClassCssMetaData());
            Collections.addAll(styleables,
                               TICK_MARK_FILL,
                               TICK_LABEL_FILL,
                               SECTION_FILL_0,
                               SECTION_FILL_1,
                               SECTION_FILL_2,
                               SECTION_FILL_3,
                               SECTION_FILL_4,
                               SECTION_FILL_5,
                               SECTION_FILL_6,
                               SECTION_FILL_7,
                               SECTION_FILL_8,
                               SECTION_FILL_9,
                               HISTOGRAM_FILL,
                               MARKER_FILL_0,
                               MARKER_FILL_1,
                               MARKER_FILL_2,
                               MARKER_FILL_3,
                               MARKER_FILL_4
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
