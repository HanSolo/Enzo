package eu.hansolo.enzo.vumeter;

import com.sun.javafx.css.converters.PaintConverter;
import eu.hansolo.enzo.common.Section;
import eu.hansolo.enzo.vumeter.skin.VuMeterSkin;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.css.StyleableObjectProperty;
import javafx.css.StyleableProperty;
import javafx.geometry.Orientation;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * User: hansolo
 * Date: 04.11.13
 * Time: 10:29
 */
public class VuMeter extends Control {        
    // CSS styleable properties    
    private static final Color      DEFAULT_SECTION_FILL_0 = Color.web("#f3622d");
    private static final Color      DEFAULT_SECTION_FILL_1 = Color.web("#fba71b");
    private static final Color      DEFAULT_SECTION_FILL_2 = Color.web("#57b757");
    private static final Color      DEFAULT_SECTION_FILL_3 = Color.web("#f5982b");
    private static final Color      DEFAULT_SECTION_FILL_4 = Color.web("#41a9c9");
    private static final Color      DEFAULT_SECTION_FILL_5 = Color.web("#4258c9");
    private static final Color      DEFAULT_SECTION_FILL_6 = Color.web("#9a42c8");
    private static final Color      DEFAULT_SECTION_FILL_7 = Color.web("#c84164");
    private static final Color      DEFAULT_SECTION_FILL_8 = Color.web("#888888");
    private static final Color      DEFAULT_SECTION_FILL_9 = Color.web("#aaaaaa");
    
    private ObjectProperty<Paint>   sectionFill0;
    private ObjectProperty<Paint>   sectionFill1;
    private ObjectProperty<Paint>   sectionFill2;
    private ObjectProperty<Paint>   sectionFill3;
    private ObjectProperty<Paint>   sectionFill4;        

    // Properties    
    private double                      _value;
    private DoubleProperty              value;
    private double                      _minValue;
    private DoubleProperty              minValue;
    private double                      _maxValue;
    private DoubleProperty              maxValue;
    private Orientation                 _orientation;
    private ObjectProperty<Orientation> orientation;
    private int                         _noOfLeds;
    private IntegerProperty             noOfLeds;
    private double                      _ledSpacing;
    private DoubleProperty              ledSpacing;
    private boolean                     _peakValueVisible = false;
    private BooleanProperty             peakValueVisible;
    private ObservableList<Section>     sections;
    private boolean                     _interactive;
    private BooleanProperty             interactive;


    // ******************** Constructors **************************************
    public VuMeter() {
        getStyleClass().add("vu-meter");
        _value       = 0.0;
        _minValue    = 0.0;
        _maxValue    = 1.0;
        _orientation = Orientation.HORIZONTAL;
        _noOfLeds    = 15;
        _ledSpacing  = 3;
        sections     = FXCollections.observableArrayList();
        _interactive = false;
    }


    // ******************** Methods *******************************************            
    public final double getValue() {
        return null == value ? _value : value.get();
    }
    public final void setValue(final double VALUE) {
        if (isInteractive()) return;
        if (null == value) {
            _value = clamp(getMinValue(), getMaxValue(), VALUE);
        } else {
            value.set(clamp(getMinValue(), getMaxValue(), VALUE));
        }
    }
    public final ReadOnlyDoubleProperty valueProperty() {
        if (null == value) {
            value = new SimpleDoubleProperty(this, "value", _value);
        }
        return value;
    }

    public final double getMinValue() {
        return null == minValue ? _minValue : minValue.get();
    }
    public final void setMinValue(final double MIN_VALUE) {
        if (null == minValue) {
            _minValue = clamp(-Double.MAX_VALUE, getMaxValue(), MIN_VALUE);
        } else {
            minValue.set(clamp(-Double.MAX_VALUE, getMaxValue(), MIN_VALUE));
        }
    }
    public final ReadOnlyDoubleProperty minValueProperty() {
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
    }
    public final ReadOnlyDoubleProperty maxValueProperty() {
        if (null == maxValue) {
            maxValue = new SimpleDoubleProperty(this, "maxValue", _maxValue);
        }
        return maxValue;
    }
    
    public final Orientation getOrientation() {
        return null == orientation ? _orientation : orientation.get();
    }
    public final void setOrientation(final Orientation ORIENTATION) {
        if (null == orientation) {
            _orientation = ORIENTATION;
        } else {
            orientation.set(ORIENTATION);
        }
    }
    public final ObjectProperty<Orientation> orientationProperty() {
        if (null == orientation) {
            orientation = new SimpleObjectProperty<>(this, "orientation", _orientation);
        }
        return orientation;
    }
    
    public final int getNoOfLeds() {
        return null == noOfLeds ? _noOfLeds : noOfLeds.get();
    }
    public final void setNoOfLeds(final int NO_OF_LEDS) {
        if (null == noOfLeds) {
            _noOfLeds = clamp(2, Integer.MAX_VALUE, NO_OF_LEDS);
        } else {
            noOfLeds.set(clamp(2, Integer.MAX_VALUE, NO_OF_LEDS));
        }         
    }
    public final ReadOnlyIntegerProperty noOfLedsProperty() {
        if (null == noOfLeds) {
            noOfLeds = new SimpleIntegerProperty(this, "noOfLeds", _noOfLeds);
        }
        return noOfLeds;
    }
    
    public final double getLedSpacing() {
        return null == ledSpacing ? _ledSpacing : ledSpacing.get();
    }
    public final void setLedSpacing(final double LED_SPACING) {
        if (null == ledSpacing) {
            _ledSpacing = clamp(0, Double.MAX_VALUE, LED_SPACING);
        } else {
            ledSpacing.set(clamp(0, Double.MAX_VALUE, LED_SPACING));
        }
    }
    public final ReadOnlyDoubleProperty ledSpacingProperty() {
        if (null == ledSpacing) {
            ledSpacing = new SimpleDoubleProperty(this, "ledSpacing", _ledSpacing);
        }
        return ledSpacing;
    }

    public final boolean isPeakValueVisible() {
        return null == peakValueVisible ? _peakValueVisible : peakValueVisible.get();
    }
    public final void setPeakValueVisible(final boolean PEAK_VALUE_VISIBLE) {
        if (null == peakValueVisible) {
            _peakValueVisible = PEAK_VALUE_VISIBLE;
        } else {
            peakValueVisible.set(PEAK_VALUE_VISIBLE);
        }
    }
    public final BooleanProperty peakValueVisibleProperty() {
        if (null == peakValueVisible) {
            peakValueVisible = new SimpleBooleanProperty(this, "peakValueVisible", _peakValueVisible);
        }
        return peakValueVisible;
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
    
    public final boolean isInteractive() {
        return null == interactive ? _interactive : interactive.get();
    }
    public final void setInteractive(final boolean INTERACTIVE) {
        if (null == interactive) {
            _interactive = INTERACTIVE;
        } else {
            interactive.set(INTERACTIVE);
        }
    }
    public final BooleanProperty interactiveProperty() {
        if (null == interactive) {
            interactive = new SimpleBooleanProperty(this, "interactive", _interactive);
        }
        return interactive;
    }
    

    // ******************** CSS Stylable Properties ***************************            
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
    

    // ******************** Utility Methods ***********************************
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


    // ******************** Style related *************************************
    @Override protected Skin createDefaultSkin() {
        return new VuMeterSkin(this);
    }

    @Override protected String getUserAgentStylesheet() {
        return getClass().getResource("vumeter.css").toExternalForm();
    }

    private static class StyleableProperties {        
        private static final CssMetaData<VuMeter, Paint> SECTION_FILL_0 =
            new CssMetaData<VuMeter, Paint>("-section-fill-0",
                                                PaintConverter.getInstance(),
                                                DEFAULT_SECTION_FILL_0) {

                @Override public boolean isSettable(VuMeter gauge) {
                    return null == gauge.sectionFill0 || !gauge.sectionFill0.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(VuMeter gauge) {
                    return (StyleableProperty) gauge.sectionFill0Property();
                }

                @Override public Paint getInitialValue(VuMeter gauge) {
                    return gauge.getSectionFill0();
                }
            };

        private static final CssMetaData<VuMeter, Paint> SECTION_FILL_1 =
            new CssMetaData<VuMeter, Paint>("-section-fill-1",
                                                PaintConverter.getInstance(),
                                                DEFAULT_SECTION_FILL_1) {

                @Override public boolean isSettable(VuMeter gauge) {
                    return null == gauge.sectionFill1 || !gauge.sectionFill1.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(VuMeter gauge) {
                    return (StyleableProperty) gauge.sectionFill1Property();
                }

                @Override public Paint getInitialValue(VuMeter gauge) {
                    return gauge.getSectionFill1();
                }
            };

        private static final CssMetaData<VuMeter, Paint> SECTION_FILL_2 =
            new CssMetaData<VuMeter, Paint>("-section-fill-2",
                                                PaintConverter.getInstance(),
                                                DEFAULT_SECTION_FILL_2) {

                @Override public boolean isSettable(VuMeter gauge) {
                    return null == gauge.sectionFill2 || !gauge.sectionFill2.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(VuMeter gauge) {
                    return (StyleableProperty) gauge.sectionFill2Property();
                }

                @Override public Paint getInitialValue(VuMeter gauge) {
                    return gauge.getSectionFill2();
                }
            };

        private static final CssMetaData<VuMeter, Paint> SECTION_FILL_3 =
            new CssMetaData<VuMeter, Paint>("-section-fill-3",
                                                PaintConverter.getInstance(),
                                                DEFAULT_SECTION_FILL_3) {

                @Override public boolean isSettable(VuMeter gauge) {
                    return null == gauge.sectionFill3 || !gauge.sectionFill3.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(VuMeter gauge) {
                    return (StyleableProperty) gauge.sectionFill3Property();
                }

                @Override public Paint getInitialValue(VuMeter gauge) {
                    return gauge.getSectionFill3();
                }
            };

        private static final CssMetaData<VuMeter, Paint> SECTION_FILL_4 =
            new CssMetaData<VuMeter, Paint>("-section-fill-4",
                                                PaintConverter.getInstance(),
                                                DEFAULT_SECTION_FILL_4) {

                @Override public boolean isSettable(VuMeter gauge) {
                    return null == gauge.sectionFill4 || !gauge.sectionFill4.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(VuMeter gauge) {
                    return (StyleableProperty) gauge.sectionFill4Property();
                }

                @Override public Paint getInitialValue(VuMeter gauge) {
                    return gauge.getSectionFill4();
                }
            };
        
        private static final List<CssMetaData<? extends Styleable, ?>> STYLEABLES;
        static {
            final List<CssMetaData<? extends Styleable, ?>> styleables = new ArrayList<>(Control.getClassCssMetaData());
            Collections.addAll(styleables,
                               SECTION_FILL_0,
                               SECTION_FILL_1,
                               SECTION_FILL_2,
                               SECTION_FILL_3,
                               SECTION_FILL_4);
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

