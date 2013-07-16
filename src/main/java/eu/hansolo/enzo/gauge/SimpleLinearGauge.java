package eu.hansolo.enzo.gauge;

import com.sun.javafx.css.converters.PaintConverter;
import eu.hansolo.enzo.gauge.skin.SimpleLinearGaugeSkin;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.BooleanPropertyBase;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.CssMetaData;
import javafx.css.PseudoClass;
import javafx.css.StyleConverter;
import javafx.css.Styleable;
import javafx.css.StyleableDoubleProperty;
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
 * Date: 16.07.13
 * Time: 13:04
 */
public class SimpleLinearGauge extends Control {
    public static enum FlatUiColor {
        TURQOISE("turqoise"),
        GREEN_SEA("green-sea"),
        EMERLAND("emerland"),
        NEPHRITIS("nephritis"),
        PETER_RIVER("peter-river"),
        BELIZE_HOLE("belize-hole"),
        AMNETHYST("amnethyst"),
        WISTERIA("wisteria"),
        SUNFLOWER("sunflower"),
        ORANGE("orange"),
        CARROT("carrot"),
        PUMPKIN("pumpkin"),
        ALIZARIN("alizarin"),
        POMEGRANATE("pomegranate"),
        CLOUDS("clouds"),
        SILVER("silver"),
        CONCRETE("concrete"),
        ASBESTOS("asbestos"),
        WET_ASPHALT("wet-asphalt"),
        MIDNIGHT_BLUE("midnight-blue");

        private final String STYLE_CLASS;

        private FlatUiColor(final String STYLE_CLASS) {
            this.STYLE_CLASS = STYLE_CLASS;
        }
    }

    public static final String       STYLE_CLASS_TURQOISE      = FlatUiColor.TURQOISE.STYLE_CLASS;
    public static final String       STYLE_CLASS_GREEN_SEA     = FlatUiColor.GREEN_SEA.STYLE_CLASS;
    public static final String       STYLE_CLASS_EMERLAND      = FlatUiColor.EMERLAND.STYLE_CLASS;
    public static final String       STYLE_CLASS_NEPHRITIS     = FlatUiColor.NEPHRITIS.STYLE_CLASS;
    public static final String       STYLE_CLASS_PETER_RIVER   = FlatUiColor.PETER_RIVER.STYLE_CLASS;
    public static final String       STYLE_CLASS_BELIZE_HOLE   = FlatUiColor.BELIZE_HOLE.STYLE_CLASS;
    public static final String       STYLE_CLASS_AMNETHYST     = FlatUiColor.AMNETHYST.STYLE_CLASS;
    public static final String       STYLE_CLASS_WISTERIA      = FlatUiColor.WISTERIA.STYLE_CLASS;
    public static final String       STYLE_CLASS_SUNFLOWER     = FlatUiColor.SUNFLOWER.STYLE_CLASS;
    public static final String       STYLE_CLASS_ORANGE        = FlatUiColor.ORANGE.STYLE_CLASS;
    public static final String       STYLE_CLASS_CARROT        = FlatUiColor.CARROT.STYLE_CLASS;
    public static final String       STYLE_CLASS_PUMPKIN       = FlatUiColor.PUMPKIN.STYLE_CLASS;
    public static final String       STYLE_CLASS_ALIZARIN      = FlatUiColor.ALIZARIN.STYLE_CLASS;
    public static final String       STYLE_CLASS_POMEGRANATE   = FlatUiColor.POMEGRANATE.STYLE_CLASS;
    public static final String       STYLE_CLASS_CLOUDS        = FlatUiColor.CLOUDS.STYLE_CLASS;
    public static final String       STYLE_CLASS_SILVER        = FlatUiColor.SILVER.STYLE_CLASS;
    public static final String       STYLE_CLASS_CONCRETE      = FlatUiColor.CONCRETE.STYLE_CLASS;
    public static final String       STYLE_CLASS_ASBESTOS      = FlatUiColor.ASBESTOS.STYLE_CLASS;
    public static final String       STYLE_CLASS_WET_ASPHALT   = FlatUiColor.WET_ASPHALT.STYLE_CLASS;
    public static final String       STYLE_CLASS_MIDNIGHT_BLUE = FlatUiColor.MIDNIGHT_BLUE.STYLE_CLASS;

    // Default section colors
    private static final Color       DEFAULT_SECTION_0_FILL    = Color.rgb(0, 0, 178, 0.5);
    private static final Color       DEFAULT_SECTION_1_FILL    = Color.rgb(0, 128, 255, 0.5);
    private static final Color       DEFAULT_SECTION_2_FILL    = Color.rgb(  0, 255, 255, 0.5);
    private static final Color       DEFAULT_SECTION_3_FILL    = Color.rgb(  0, 255,  64, 0.5);
    private static final Color       DEFAULT_SECTION_4_FILL    = Color.rgb(128, 255,   0, 0.5);
    private static final Color       DEFAULT_SECTION_5_FILL    = Color.rgb(255, 255,   0, 0.5);
    private static final Color       DEFAULT_SECTION_6_FILL    = Color.rgb(255, 191,   0, 0.5);
    private static final Color       DEFAULT_SECTION_7_FILL    = Color.rgb(255, 128,   0, 0.5);
    private static final Color       DEFAULT_SECTION_8_FILL    = Color.rgb(255,  64,   0, 0.5);
    private static final Color       DEFAULT_SECTION_9_FILL    = Color.rgb(255,   0,   0, 0.5);

    // CSS Pseudo classes
    private static final PseudoClass ROUND_PSEUDO_CLASS        = PseudoClass.getPseudoClass("round");
    private static final PseudoClass SEGMENTED_PSEUDO_CLASS    = PseudoClass.getPseudoClass("segmented");

    private BooleanProperty          round;
    private BooleanProperty          segmented;

    private double                   _value;
    private DoubleProperty           value;
    private double                   _oldValue;
    private double                   _minValue;
    private DoubleProperty           minValue;
    private double                   _maxValue;
    private DoubleProperty           maxValue;
    private double                   _threshold;
    private DoubleProperty           threshold;
    private int                      _decimals;
    private IntegerProperty          decimals;
    private String                   _title;
    private StringProperty           title;
    private String                   _unit;
    private StringProperty           unit;
    private boolean                  _animated;
    private BooleanProperty          animated;
    private double                   animationDuration;
    private ObservableList<Section>  sections;

    // CSS styleable properties
    private DoubleProperty           barWidth;
    private ObjectProperty<Paint>    barBackgroundFill;
    private ObjectProperty<Paint>    barFill;
    private ObjectProperty<Paint>    valueColor;
    private ObjectProperty<Paint>    labelColor;
    private ObjectProperty<Paint>    section0Fill;
    private ObjectProperty<Paint>    section1Fill;
    private ObjectProperty<Paint>    section2Fill;
    private ObjectProperty<Paint>    section3Fill;
    private ObjectProperty<Paint>    section4Fill;
    private ObjectProperty<Paint>    section5Fill;
    private ObjectProperty<Paint>    section6Fill;
    private ObjectProperty<Paint>    section7Fill;
    private ObjectProperty<Paint>    section8Fill;
    private ObjectProperty<Paint>    section9Fill;


    // ******************** Constructors **************************************
    public SimpleLinearGauge() {
        getStyleClass().add("simple-linear-gauge");
        _value                   = 0;
        _oldValue                = 0;
        _minValue                = 0;
        _maxValue                = 100;
        _threshold               = 50;
        _decimals                = 1;
        _title                   = "";
        _unit                    = "";
        _animated                = true;
        sections                 = FXCollections.observableArrayList();
        animationDuration        = 800;
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

    public final double getAnimationDuration() {
        return animationDuration;
    }
    public final void setAnimationDuration(final double ANIMATION_DURATION) {
        animationDuration = clamp(20, 5000, ANIMATION_DURATION);
    }

    public final double getRange() {
        return getMaxValue() - getMinValue();
    }

    // Properties related to visualization
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
        for (Section section : sections) {
            if (section.getStart() < getMinValue()) section.setStart(getMinValue());
            if (section.getStart() > getMaxValue()) section.setStart(getMaxValue());
            if (section.getStop() < getMinValue()) section.setStop(getMinValue());
            if (section.getStop() > getMaxValue()) section.setStop(getMaxValue());
        }
    }


    // ******************** CSS Stylable Properties ***************************
    public final double getBarWidth() {
        return null == barWidth ? 20d : barWidth.get();
    }
    public final void setBarWidth(double value) {
        barWidthProperty().set(clamp(1, 100, value));
        setStyle("-bar-width: " + getBarWidth());
    }
    public final DoubleProperty barWidthProperty() {
        if (null == barWidth) {
            barWidth = new StyleableDoubleProperty(20d) {

                @Override public CssMetaData getCssMetaData() { return StyleableProperties.BAR_WIDTH; }

                @Override public Object getBean() { return SimpleLinearGauge.this; }

                @Override public String getName() { return "barWidth"; }
            };
        }
        return barWidth;
    }

    public final Paint getBarBackgroundFill() {
        return null == barBackgroundFill ? Color.rgb(234, 234, 234) : barBackgroundFill.get();
    }
    public final void setBarBackgroundFill(Paint value) {
        barBackgroundFillProperty().set(value);
    }
    public final ObjectProperty<Paint> barBackgroundFillProperty() {
        if (null == barBackgroundFill) {
            barBackgroundFill = new StyleableObjectProperty<Paint>(Color.rgb(234, 234, 234)) {

                @Override public CssMetaData getCssMetaData() { return StyleableProperties.BAR_BACKGROUND_FILL; }

                @Override public Object getBean() { return SimpleLinearGauge.this; }

                @Override public String getName() { return "barBackgroundFill"; }
            };
        }
        return barBackgroundFill;
    }

    public final Paint getBarFill() {
        return null == barFill ? Color.web("#f1c428") : barFill.get();
    }
    public final void setBarFill(Paint value) {
        barFillProperty().set(value);
    }
    public final ObjectProperty<Paint> barFillProperty() {
        if (null == barFill) {
            barFill = new StyleableObjectProperty<Paint>(Color.BLACK) {

                @Override public CssMetaData getCssMetaData() { return StyleableProperties.BAR_FILL; }

                @Override public Object getBean() { return SimpleLinearGauge.this; }

                @Override public String getName() { return "barFill"; }
            };
        }
        return barFill;
    }

    public final Paint getValueColor() {
        return null == valueColor ? Color.web("#888888") : valueColor.get();
    }
    public final void setValueColor(Paint value) {
        valueColorProperty().set(value);
    }
    public final ObjectProperty<Paint> valueColorProperty() {
        if (null == valueColor) {
            valueColor = new StyleableObjectProperty<Paint>(Color.web("#888888")) {

                @Override public CssMetaData getCssMetaData() { return StyleableProperties.VALUE_COLOR; }

                @Override public Object getBean() { return SimpleLinearGauge.this; }

                @Override public String getName() { return "valueColor"; }
            };
        }
        return valueColor;
    }

    public final Paint getLabelColor() {
        return null == labelColor ? Color.web("#888888") : labelColor.get();
    }
    public final void setLabelColor(Paint value) {
        labelColorProperty().set(value);
    }
    public final ObjectProperty<Paint> labelColorProperty() {
        if (null == labelColor) {
            labelColor = new StyleableObjectProperty<Paint>(Color.web("#888888")) {

                @Override public CssMetaData getCssMetaData() { return StyleableProperties.LABEL_COLOR; }

                @Override public Object getBean() { return SimpleLinearGauge.this; }

                @Override public String getName() { return "labelColor"; }
            };
        }
        return labelColor;
    }

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
                @Override public Object getBean() { return SimpleLinearGauge.this; }
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
                @Override public Object getBean() { return SimpleLinearGauge.this; }
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
                @Override public Object getBean() { return SimpleLinearGauge.this; }
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
                @Override public Object getBean() { return SimpleLinearGauge.this; }
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
                @Override public Object getBean() { return SimpleLinearGauge.this; }
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
                @Override public Object getBean() { return SimpleLinearGauge.this; }
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
                @Override public Object getBean() { return SimpleLinearGauge.this; }
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
                @Override public Object getBean() { return SimpleLinearGauge.this; }
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
                @Override public Object getBean() { return SimpleLinearGauge.this; }
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
                @Override public Object getBean() { return SimpleLinearGauge.this; }
                @Override public String getName() { return "section9Fill"; }
            };
        }
        return section9Fill;
    }


    // ******************** CSS Pseudo Classes ********************************
    public final boolean isRound() {
        return null == round ? false : round.get();
    }
    public final void setRound(final boolean ROUND) {
        roundProperty().set(ROUND);
    }
    public final BooleanProperty roundProperty() {
        if (null == round) {
            round = new BooleanPropertyBase(false) {
                @Override protected void invalidated() { pseudoClassStateChanged(ROUND_PSEUDO_CLASS, get()); }
                @Override public Object getBean() { return this; }
                @Override public String getName() { return "round"; }
            };
        }
        return round;
    }

    public final boolean isSegmented() {
        return null == segmented ? false : segmented.get();
    }
    public final void setSegmented(final boolean SEGMENTED) {
        segmentedProperty().set(SEGMENTED);
    }
    public final BooleanProperty segmentedProperty() {
        if (null == segmented) {
            segmented = new BooleanPropertyBase(false) {
                @Override protected void invalidated() { pseudoClassStateChanged(SEGMENTED_PSEUDO_CLASS, get()); }
                @Override public Object getBean() { return this; }
                @Override public String getName() { return "segmented"; }
            };
        }
        return segmented;
    }


    // ******************** Style related *************************************
    @Override protected Skin createDefaultSkin() {
        return new SimpleLinearGaugeSkin(this);
    }

    @Override protected String getUserAgentStylesheet() {
        return getClass().getResource("simple-linear-gauge.css").toExternalForm();
    }

    private static class StyleableProperties {
        private static final CssMetaData<SimpleLinearGauge, Number> BAR_WIDTH =
            new CssMetaData<SimpleLinearGauge, Number>("-bar-width", StyleConverter.getSizeConverter(), 20d) {

                @Override public boolean isSettable(SimpleLinearGauge gauge) {
                    return null == gauge.barWidth || !gauge.barWidth.isBound();
                }

                @Override public StyleableProperty<Number> getStyleableProperty(SimpleLinearGauge gauge) {
                    return (StyleableProperty) gauge.barWidthProperty();
                }
            };

        private static final CssMetaData<SimpleLinearGauge, Paint> BAR_BACKGROUND_FILL =
            new CssMetaData<SimpleLinearGauge, Paint>("-bar-background-fill", PaintConverter.getInstance(), Color.rgb(234, 234, 234)) {

                @Override public boolean isSettable(SimpleLinearGauge gauge) {
                    return null == gauge.barBackgroundFill || !gauge.barBackgroundFill.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(SimpleLinearGauge gauge) {
                    return (StyleableProperty) gauge.barBackgroundFillProperty();
                }
            };

        private static final CssMetaData<SimpleLinearGauge, Paint> BAR_FILL =
            new CssMetaData<SimpleLinearGauge, Paint>("-bar-fill", PaintConverter.getInstance(), Color.web("#f1c428")) {

                @Override public boolean isSettable(SimpleLinearGauge gauge) {
                    return null == gauge.barFill || !gauge.barFill.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(SimpleLinearGauge gauge) {
                    return (StyleableProperty) gauge.barFillProperty();
                }
            };

        private static final CssMetaData<SimpleLinearGauge, Paint> VALUE_COLOR =
            new CssMetaData<SimpleLinearGauge, Paint>("-value-color", PaintConverter.getInstance(), Color.web("#888888")) {

                @Override public boolean isSettable(SimpleLinearGauge gauge) {
                    return null == gauge.valueColor || !gauge.valueColor.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(SimpleLinearGauge gauge) {
                    return (StyleableProperty) gauge.valueColorProperty();
                }
            };

        private static final CssMetaData<SimpleLinearGauge, Paint> LABEL_COLOR =
            new CssMetaData<SimpleLinearGauge, Paint>("-label-color", PaintConverter.getInstance(), Color.web("#888888")) {

                @Override public boolean isSettable(SimpleLinearGauge gauge) {
                    return null == gauge.labelColor || !gauge.labelColor.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(SimpleLinearGauge gauge) {
                    return (StyleableProperty) gauge.labelColorProperty();
                }
            };

        private static final CssMetaData<SimpleLinearGauge, Paint> SECTION_0_FILL =
            new CssMetaData<SimpleLinearGauge, Paint>("-section0-fill", PaintConverter.getInstance(), DEFAULT_SECTION_0_FILL) {

                @Override public boolean isSettable(SimpleLinearGauge gauge) {
                    return null == gauge.section0Fill || !gauge.section0Fill.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(SimpleLinearGauge gauge) {
                    return (StyleableProperty) gauge.section0FillProperty();
                }

                @Override public Paint getInitialValue(SimpleLinearGauge gauge) {
                    return gauge.getSection0Fill();
                }
            };

        private static final CssMetaData<SimpleLinearGauge, Paint> SECTION_1_FILL =
            new CssMetaData<SimpleLinearGauge, Paint>("-section1-fill", PaintConverter.getInstance(), DEFAULT_SECTION_1_FILL) {

                @Override public boolean isSettable(SimpleLinearGauge gauge) {
                    return null == gauge.section1Fill || !gauge.section1Fill.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(SimpleLinearGauge gauge) {
                    return (StyleableProperty) gauge.section1FillProperty();
                }

                @Override public Paint getInitialValue(SimpleLinearGauge gauge) {
                    return gauge.getSection1Fill();
                }
            };

        private static final CssMetaData<SimpleLinearGauge, Paint> SECTION_2_FILL =
            new CssMetaData<SimpleLinearGauge, Paint>("-section2-fill", PaintConverter.getInstance(), DEFAULT_SECTION_2_FILL) {

                @Override public boolean isSettable(SimpleLinearGauge gauge) {
                    return null == gauge.section2Fill || !gauge.section2Fill.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(SimpleLinearGauge gauge) {
                    return (StyleableProperty) gauge.section2FillProperty();
                }

                @Override public Paint getInitialValue(SimpleLinearGauge gauge) {
                    return gauge.getSection2Fill();
                }
            };

        private static final CssMetaData<SimpleLinearGauge, Paint> SECTION_3_FILL =
            new CssMetaData<SimpleLinearGauge, Paint>("-section3-fill", PaintConverter.getInstance(), DEFAULT_SECTION_3_FILL) {

                @Override public boolean isSettable(SimpleLinearGauge gauge) {
                    return null == gauge.section3Fill || !gauge.section3Fill.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(SimpleLinearGauge gauge) {
                    return (StyleableProperty) gauge.section3FillProperty();
                }

                @Override public Paint getInitialValue(SimpleLinearGauge gauge) {
                    return gauge.getSection3Fill();
                }
            };

        private static final CssMetaData<SimpleLinearGauge, Paint> SECTION_4_FILL =
            new CssMetaData<SimpleLinearGauge, Paint>("-section4-fill", PaintConverter.getInstance(), DEFAULT_SECTION_4_FILL) {

                @Override public boolean isSettable(SimpleLinearGauge gauge) {
                    return null == gauge.section4Fill || !gauge.section4Fill.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(SimpleLinearGauge gauge) {
                    return (StyleableProperty) gauge.section4FillProperty();
                }

                @Override public Paint getInitialValue(SimpleLinearGauge gauge) {
                    return gauge.getSection4Fill();
                }
            };

        private static final CssMetaData<SimpleLinearGauge, Paint> SECTION_5_FILL =
            new CssMetaData<SimpleLinearGauge, Paint>("-section5-fill", PaintConverter.getInstance(), DEFAULT_SECTION_5_FILL) {

                @Override public boolean isSettable(SimpleLinearGauge gauge) {
                    return null == gauge.section5Fill || !gauge.section5Fill.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(SimpleLinearGauge gauge) {
                    return (StyleableProperty) gauge.section5FillProperty();
                }

                @Override public Paint getInitialValue(SimpleLinearGauge gauge) {
                    return gauge.getSection5Fill();
                }
            };

        private static final CssMetaData<SimpleLinearGauge, Paint> SECTION_6_FILL =
            new CssMetaData<SimpleLinearGauge, Paint>("-section6-fill", PaintConverter.getInstance(), DEFAULT_SECTION_6_FILL) {

                @Override public boolean isSettable(SimpleLinearGauge gauge) {
                    return null == gauge.section6Fill || !gauge.section6Fill.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(SimpleLinearGauge gauge) {
                    return (StyleableProperty) gauge.section6FillProperty();
                }

                @Override public Paint getInitialValue(SimpleLinearGauge gauge) {
                    return gauge.getSection6Fill();
                }
            };

        private static final CssMetaData<SimpleLinearGauge, Paint> SECTION_7_FILL =
            new CssMetaData<SimpleLinearGauge, Paint>("-section7-fill", PaintConverter.getInstance(), DEFAULT_SECTION_7_FILL) {

                @Override public boolean isSettable(SimpleLinearGauge gauge) {
                    return null == gauge.section7Fill || !gauge.section7Fill.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(SimpleLinearGauge gauge) {
                    return (StyleableProperty) gauge.section7FillProperty();
                }

                @Override public Paint getInitialValue(SimpleLinearGauge gauge) {
                    return gauge.getSection7Fill();
                }
            };

        private static final CssMetaData<SimpleLinearGauge, Paint> SECTION_8_FILL =
            new CssMetaData<SimpleLinearGauge, Paint>("-section8-fill", PaintConverter.getInstance(), DEFAULT_SECTION_8_FILL) {

                @Override public boolean isSettable(SimpleLinearGauge gauge) {
                    return null == gauge.section8Fill || !gauge.section8Fill.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(SimpleLinearGauge gauge) {
                    return (StyleableProperty) gauge.section8FillProperty();
                }

                @Override public Paint getInitialValue(SimpleLinearGauge gauge) {
                    return gauge.getSection8Fill();
                }
            };

        private static final CssMetaData<SimpleLinearGauge, Paint> SECTION_9_FILL =
            new CssMetaData<SimpleLinearGauge, Paint>("-section9-fill", PaintConverter.getInstance(), DEFAULT_SECTION_9_FILL) {

                @Override public boolean isSettable(SimpleLinearGauge gauge) {
                    return null == gauge.section9Fill || !gauge.section9Fill.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(SimpleLinearGauge gauge) {
                    return (StyleableProperty) gauge.section9FillProperty();
                }

                @Override public Paint getInitialValue(SimpleLinearGauge gauge) {
                    return gauge.getSection9Fill();
                }
            };

        private static final List<CssMetaData<? extends Styleable, ?>> STYLEABLES;
        static {
            final List<CssMetaData<? extends Styleable, ?>> styleables = new ArrayList<>(Control.getClassCssMetaData());
            Collections.addAll(styleables,
                BAR_WIDTH,
                BAR_BACKGROUND_FILL,
                BAR_FILL,
                VALUE_COLOR,
                LABEL_COLOR,
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
