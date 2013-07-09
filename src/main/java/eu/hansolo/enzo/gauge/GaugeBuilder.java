package eu.hansolo.enzo.gauge;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Dimension2D;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.List;


/**
 * Created by
 * User: hansolo
 * Date: 09.07.13
 * Time: 13:19
 */
public class GaugeBuilder<B extends GaugeBuilder<B>> {
    private HashMap<String, Property> properties = new HashMap<>();


    // ******************** Constructors **************************************
    protected GaugeBuilder() {}


    // ******************** Methods *******************************************
    public static final GaugeBuilder create() {
        return new GaugeBuilder();
    }

    public final GaugeBuilder value(final double VALUE) {
        properties.put("value", new SimpleDoubleProperty(VALUE));
        return this;
    }

    public final GaugeBuilder minValue(final double MIN_VALUE) {
        properties.put("minValue", new SimpleDoubleProperty(MIN_VALUE));
        return this;
    }

    public final GaugeBuilder maxValue(final double MAX_VALUE) {
        properties.put("maxValue", new SimpleDoubleProperty(MAX_VALUE));
        return this;
    }

    public final GaugeBuilder threshold(final double THRESHOLD) {
        properties.put("threshold", new SimpleDoubleProperty(THRESHOLD));
        return this;
    }

    public final GaugeBuilder sections(final Section... SECTIONS) {
        properties.put("sectionsArray", new SimpleObjectProperty<>(SECTIONS));
        return this;
    }

    public final GaugeBuilder sections(final List<Section> SECTIONS) {
        properties.put("sectionsList", new SimpleObjectProperty<>(SECTIONS));
        return this;
    }

    public final GaugeBuilder markers(final Marker... MARKERS) {
        properties.put("markersArray", new SimpleObjectProperty<>(MARKERS));
        return this;
    }

    public final GaugeBuilder markers(final List<Marker> MARKERS) {
        properties.put("markersList", new SimpleObjectProperty<>(MARKERS));
        return this;
    }

    public final GaugeBuilder decimals(final int DECIMALS) {
        properties.put("decimals", new SimpleIntegerProperty(DECIMALS));
        return this;
    }

    public final GaugeBuilder title(final String TITLE) {
        properties.put("title", new SimpleStringProperty(TITLE));
        return this;
    }

    public final GaugeBuilder unit(final String UNIT) {
        properties.put("unit", new SimpleStringProperty(UNIT));
        return this;
    }

    public final GaugeBuilder animated(final boolean ANIMATED) {
        properties.put("animated", new SimpleBooleanProperty(ANIMATED));
        return this;
    }

    public final GaugeBuilder animationDuration(final double ANIMATION_DURATION) {
        properties.put("animationDuration", new SimpleDoubleProperty(ANIMATION_DURATION));
        return this;
    }

    public final GaugeBuilder minMeasuredValueVisible(final boolean MIN_MEASURED_VALUE_VISIBLE) {
        properties.put("minMeasuredValueVisible", new SimpleBooleanProperty(MIN_MEASURED_VALUE_VISIBLE));
        return this;
    }

    public final GaugeBuilder maxMeasuredValueVisible(final boolean MAX_MEASURED_VALUE_VISIBLE) {
        properties.put("maxMeasuredValueVisible", new SimpleBooleanProperty(MAX_MEASURED_VALUE_VISIBLE));
        return this;
    }

    public final GaugeBuilder thresholdVisible(final boolean THRESHOLD_VISIBLE) {
        properties.put("thresholdVisible", new SimpleBooleanProperty(THRESHOLD_VISIBLE));
        return this;
    }

    public final GaugeBuilder startAngle(final double START_ANGLE) {
        properties.put("startAngle", new SimpleDoubleProperty(START_ANGLE));
        return this;
    }

    public final GaugeBuilder angleRange(final double ANGLE_RANGE) {
        properties.put("angleRange", new SimpleDoubleProperty(ANGLE_RANGE));
        return this;
    }

    public final GaugeBuilder needleColor(final Color NEEDLE_COLOR) {
        properties.put("needleColor", new SimpleObjectProperty<>(NEEDLE_COLOR));
        return this;
    }

    public final GaugeBuilder tickLabelOrientation(final Gauge.TickLabelOrientation TICK_LABEL_ORIENTATION) {
        properties.put("tickLabelOrientation", new SimpleObjectProperty<>(TICK_LABEL_ORIENTATION));
        return this;
    }

    public final GaugeBuilder numberFormat(final Gauge.NumberFormat NUMBER_FORMAT) {
        properties.put("numberFormat", new SimpleObjectProperty<>(NUMBER_FORMAT));
        return this;
    }

    public final GaugeBuilder majorTickSpace(final double MAJOR_TICK_SPACE) {
        properties.put("majorTickSpace", new SimpleDoubleProperty(MAJOR_TICK_SPACE));
        return this;
    }

    public final GaugeBuilder minorTickSpace(final double MINOR_TICK_SPACE) {
        properties.put("minorTickSpace", new SimpleDoubleProperty(MINOR_TICK_SPACE));
        return this;
    }

    public final GaugeBuilder plainValue(final boolean PLAIN_VALUE) {
        properties.put("plainValue", new SimpleBooleanProperty(PLAIN_VALUE));
        return this;
    }

    public final GaugeBuilder histogramEnabled(final boolean HISTOGRAM_ENABLED) {
        properties.put("histogramEnabled", new SimpleBooleanProperty(HISTOGRAM_ENABLED));
        return this;
    }

    public final GaugeBuilder dropShadowEnabled(final boolean DROP_SHADOW_ENABLED) {
        properties.put("dropShadowEnabled", new SimpleBooleanProperty(DROP_SHADOW_ENABLED));
        return this;
    }

    public final GaugeBuilder tickLabelFill(final Color TICK_LABEL_FILL) {
        properties.put("tickLabelFill", new SimpleObjectProperty<>(TICK_LABEL_FILL));
        return this;
    }

    public final GaugeBuilder section0Fill(final Color SECTION_0_FILL) {
        properties.put("section0Fill", new SimpleObjectProperty<>(SECTION_0_FILL));
        return this;
    }

    public final GaugeBuilder section1Fill(final Color SECTION_1_FILL) {
        properties.put("section1Fill", new SimpleObjectProperty<>(SECTION_1_FILL));
        return this;
    }

    public final GaugeBuilder section2Fill(final Color SECTION_2_FILL) {
        properties.put("section2Fill", new SimpleObjectProperty<>(SECTION_2_FILL));
        return this;
    }

    public final GaugeBuilder section3Fill(final Color SECTION_3_FILL) {
        properties.put("section3Fill", new SimpleObjectProperty<>(SECTION_3_FILL));
        return this;
    }

    public final GaugeBuilder section4Fill(final Color SECTION_4_FILL) {
        properties.put("section4Fill", new SimpleObjectProperty<>(SECTION_4_FILL));
        return this;
    }

    public final GaugeBuilder section5Fill(final Color SECTION_5_FILL) {
        properties.put("section5Fill", new SimpleObjectProperty<>(SECTION_5_FILL));
        return this;
    }

    public final GaugeBuilder section6Fill(final Color SECTION_6_FILL) {
        properties.put("section6Fill", new SimpleObjectProperty<>(SECTION_6_FILL));
        return this;
    }

    public final GaugeBuilder section7Fill(final Color SECTION_7_FILL) {
        properties.put("section7Fill", new SimpleObjectProperty<>(SECTION_7_FILL));
        return this;
    }

    public final GaugeBuilder section8Fill(final Color SECTION_8_FILL) {
        properties.put("section8Fill", new SimpleObjectProperty<>(SECTION_8_FILL));
        return this;
    }

    public final GaugeBuilder section9Fill(final Color SECTION_9_FILL) {
        properties.put("section9Fill", new SimpleObjectProperty<>(SECTION_9_FILL));
        return this;
    }

    public final GaugeBuilder marker0Fill(final Color MARKER_0_FILL) {
        properties.put("marker0Fill", new SimpleObjectProperty<>(MARKER_0_FILL));
        return this;
    }

    public final GaugeBuilder marker1Fill(final Color MARKER_1_FILL) {
        properties.put("marker1Fill", new SimpleObjectProperty<>(MARKER_1_FILL));
        return this;
    }

    public final GaugeBuilder marker2Fill(final Color MARKER_2_FILL) {
        properties.put("marker2Fill", new SimpleObjectProperty<>(MARKER_2_FILL));
        return this;
    }

    public final GaugeBuilder marker3Fill(final Color MARKER_3_FILL) {
        properties.put("marker3Fill", new SimpleObjectProperty<>(MARKER_3_FILL));
        return this;
    }

    public final GaugeBuilder marker4Fill(final Color MARKER_4_FILL) {
        properties.put("marker4Fill", new SimpleObjectProperty<>(MARKER_4_FILL));
        return this;
    }

    public final GaugeBuilder sectionsVisible(final boolean SECTIONS_VISIBLE) {
        properties.put("sectionsVisible", new SimpleBooleanProperty(SECTIONS_VISIBLE));
        return this;
    }

    public final GaugeBuilder markersVisible(final boolean MARKERS_VISIBLE) {
        properties.put("markersVisible", new SimpleBooleanProperty(MARKERS_VISIBLE));
        return this;
    }

    public final B prefSize(final double WIDTH, final double HEIGHT) {
        properties.put("prefSize", new SimpleObjectProperty<>(new Dimension2D(WIDTH, HEIGHT)));
        return (B)this;
    }

    public final B prefWidth(final double PREF_WIDTH) {
        properties.put("prefWidth", new SimpleDoubleProperty(PREF_WIDTH));
        return (B)this;
    }
    public final B prefHeight(final double PREF_HEIGHT) {
        properties.put("prefHeight", new SimpleDoubleProperty(PREF_HEIGHT));
        return (B)this;
    }

    public final B minWidth(final double MIN_WIDTH) {
        properties.put("minWidth", new SimpleDoubleProperty(MIN_WIDTH));
        return (B)this;
    }
    public final B minHeight(final double MIN_HEIGHT) {
        properties.put("minHeight", new SimpleDoubleProperty(MIN_HEIGHT));
        return (B)this;
    }

    public final B maxWidth(final double MAX_WIDTH) {
        properties.put("maxWidth", new SimpleDoubleProperty(MAX_WIDTH));
        return (B)this;
    }
    public final B maxHeight(final double MAX_HEIGHT) {
        properties.put("maxHeight", new SimpleDoubleProperty(MAX_HEIGHT));
        return (B)this;
    }

    public final B scaleX(final double SCALE_X) {
        properties.put("scaleX", new SimpleDoubleProperty(SCALE_X));
        return (B)this;
    }
    public final B scaleY(final double SCALE_Y) {
        properties.put("scaleY", new SimpleDoubleProperty(SCALE_Y));
        return (B)this;
    }

    public final B layoutX(final double LAYOUT_X) {
        properties.put("layoutX", new SimpleDoubleProperty(LAYOUT_X));
        return (B)this;
    }
    public final B layoutY(final double LAYOUT_Y) {
        properties.put("layoutY", new SimpleDoubleProperty(LAYOUT_Y));
        return (B)this;
    }

    public final B translateX(final double TRANSLATE_X) {
        properties.put("translateX", new SimpleDoubleProperty(TRANSLATE_X));
        return (B)this;
    }
    public final B translateY(final double TRANSLATE_Y) {
        properties.put("translateY", new SimpleDoubleProperty(TRANSLATE_Y));
        return (B)this;
    }

    public final Gauge build() {
        final Gauge CONTROL = new Gauge();
        for (String key : properties.keySet()) {
            if ("prefSize".equals(key)) {
                Dimension2D dim = ((ObjectProperty<Dimension2D>) properties.get(key)).get();
                CONTROL.setPrefSize(dim.getWidth(), dim.getHeight());
            } else if("prefWidth".equals(key)) {
                CONTROL.setPrefWidth(((DoubleProperty) properties.get(key)).get());
            } else if("prefHeight".equals(key)) {
                CONTROL.setPrefHeight(((DoubleProperty) properties.get(key)).get());
            } else if("minWidth".equals(key)) {
                CONTROL.setMinWidth(((DoubleProperty) properties.get(key)).get());
            } else if("minHeight".equals(key)) {
                CONTROL.setMinHeight(((DoubleProperty) properties.get(key)).get());
            } else if("maxWidth".equals(key)) {
                CONTROL.setMaxWidth(((DoubleProperty) properties.get(key)).get());
            } else if("maxHeight".equals(key)) {
                CONTROL.setMaxHeight(((DoubleProperty) properties.get(key)).get());
            } else if("scaleX".equals(key)) {
                CONTROL.setScaleX(((DoubleProperty) properties.get(key)).get());
            } else if("scaleY".equals(key)) {
                CONTROL.setScaleY(((DoubleProperty) properties.get(key)).get());
            } else if ("layoutX".equals(key)) {
                CONTROL.setLayoutX(((DoubleProperty) properties.get(key)).get());
            } else if ("layoutY".equals(key)) {
                CONTROL.setLayoutY(((DoubleProperty) properties.get(key)).get());
            } else if ("translateX".equals(key)) {
                CONTROL.setTranslateX(((DoubleProperty) properties.get(key)).get());
            } else if ("translateY".equals(key)) {
                CONTROL.setTranslateY(((DoubleProperty) properties.get(key)).get());
            } else if("styleClass".equals(key)) {
                CONTROL.getStyleClass().setAll("gauge", ((StringProperty) properties.get(key)).get());
            } else if("value".equals(key)) {
                CONTROL.setValue(((DoubleProperty) properties.get(key)).get());
            } else if("minValue".equals(key)) {
                CONTROL.setMinValue(((DoubleProperty) properties.get(key)).get());
            } else if("maxValue".equals(key)) {
                CONTROL.setMaxValue(((DoubleProperty) properties.get(key)).get());
            } else if("threshold".equals(key)) {
                CONTROL.setThreshold(((DoubleProperty) properties.get(key)).get());
            } else if("sectionsArray".equals(key)) {
                CONTROL.setSections(((ObjectProperty<Section[]>) properties.get(key)).get());
            } else if("sectionsList".equals(key)) {
                CONTROL.setSections(((ObjectProperty<List<Section>>) properties.get(key)).get());
            } else if("markersArray".equals(key)) {
                CONTROL.setMarkers(((ObjectProperty<Marker[]>) properties.get(key)).get());
            } else if("markersList".equals(key)) {
                CONTROL.setMarkers(((ObjectProperty<List<Marker>>) properties.get(key)).get());
            } else if("decimals".equals(key)) {
                CONTROL.setDecimals(((IntegerProperty) properties.get(key)).get());
            } else if("title".equals(key)) {
                CONTROL.setTitle(((StringProperty) properties.get(key)).get());
            } else if("unit".equals(key)) {
                CONTROL.setUnit(((StringProperty) properties.get(key)).get());
            } else if("animated".equals(key)) {
                CONTROL.setAnimated(((BooleanProperty) properties.get(key)).get());
            } else if("animationDuration".equals(key)) {
                CONTROL.setAnimationDuration(((DoubleProperty) properties.get(key)).get());
            } else if("minMeasuredValueVisible".equals(key)) {
                CONTROL.setMinMeasuredValueVisible(((BooleanProperty) properties.get(key)).get());
            } else if("maxMeasuredValueVisible".equals(key)) {
                CONTROL.setMaxMeasuredValueVisible(((BooleanProperty) properties.get(key)).get());
            } else if("thresholdVisible".equals(key)) {
                CONTROL.setThresholdVisible(((BooleanProperty) properties.get(key)).get());
            } else if("startAngle".equals(key)) {
                CONTROL.setStartAngle(((DoubleProperty) properties.get(key)).get());
            } else if("angleRange".equals(key)) {
                CONTROL.setAngleRange(((DoubleProperty) properties.get(key)).get());
            } else if("needleColor".equals(key)) {
                CONTROL.setNeedleColor(((ObjectProperty<Color>) properties.get(key)).get());
            } else if("tickLabelOrientation".equals(key)) {
                CONTROL.setTickLabelOrientation(((ObjectProperty<Gauge.TickLabelOrientation>) properties.get(key)).get());
            } else if("numberFormat".equals(key)) {
                CONTROL.setNumberFormat(((ObjectProperty<Gauge.NumberFormat>) properties.get(key)).get());
            } else if("majorTickSpace".equals(key)) {
                CONTROL.setMajorTickSpace(((DoubleProperty) properties.get(key)).get());
            } else if("minorTickSpace".equals(key)) {
                CONTROL.setMinorTickSpace(((DoubleProperty) properties.get(key)).get());
            } else if("plainValue".equals(key)) {
                CONTROL.setPlainValue(((BooleanProperty) properties.get(key)).get());
            } else if("histogramEnabled".equals(key)) {
                CONTROL.setHistogramEnabled(((BooleanProperty) properties.get(key)).get());
            } else if("dropShadowEnabled".equals(key)) {
                CONTROL.setDropShadowEnabled(((BooleanProperty) properties.get(key)).get());
            } else if("tickLabelFill".equals(key)) {
                CONTROL.setTickLabelFill(((ObjectProperty<Color>) properties.get(key)).get());
            } else if("section0Fill".equals(key)) {
                CONTROL.setSection0Fill(((ObjectProperty<Color>) properties.get(key)).get());
            } else if("section1Fill".equals(key)) {
                CONTROL.setSection1Fill(((ObjectProperty<Color>) properties.get(key)).get());
            } else if("section2Fill".equals(key)) {
                CONTROL.setSection2Fill(((ObjectProperty<Color>) properties.get(key)).get());
            } else if("section3Fill".equals(key)) {
                CONTROL.setSection3Fill(((ObjectProperty<Color>) properties.get(key)).get());
            } else if("section4Fill".equals(key)) {
                CONTROL.setSection4Fill(((ObjectProperty<Color>) properties.get(key)).get());
            } else if("section5Fill".equals(key)) {
                CONTROL.setSection5Fill(((ObjectProperty<Color>) properties.get(key)).get());
            } else if("section6Fill".equals(key)) {
                CONTROL.setSection6Fill(((ObjectProperty<Color>) properties.get(key)).get());
            } else if("section7Fill".equals(key)) {
                CONTROL.setSection7Fill(((ObjectProperty<Color>) properties.get(key)).get());
            } else if("section8Fill".equals(key)) {
                CONTROL.setSection8Fill(((ObjectProperty<Color>) properties.get(key)).get());
            } else if("section9Fill".equals(key)) {
                CONTROL.setSection9Fill(((ObjectProperty<Color>) properties.get(key)).get());
            } else if("histogramFill".equals(key)) {
                CONTROL.setHistogramFill(((ObjectProperty<Color>) properties.get(key)).get());
            } else if("marker0Fill".equals(key)) {
                CONTROL.setMarker0Fill(((ObjectProperty<Color>) properties.get(key)).get());
            } else if("marker1Fill".equals(key)) {
                CONTROL.setMarker1Fill(((ObjectProperty<Color>) properties.get(key)).get());
            } else if("marker2Fill".equals(key)) {
                CONTROL.setMarker2Fill(((ObjectProperty<Color>) properties.get(key)).get());
            } else if("marker3Fill".equals(key)) {
                CONTROL.setMarker3Fill(((ObjectProperty<Color>) properties.get(key)).get());
            } else if("marker4Fill".equals(key)) {
                CONTROL.setMarker4Fill(((ObjectProperty<Color>) properties.get(key)).get());
            } else if("sectionsVisible".equals(key)) {
                CONTROL.setSectionsVisible(((BooleanProperty) properties.get(key)).get());
            } else if("markersVisible".equals(key)) {
                CONTROL.setMarkersVisible(((BooleanProperty) properties.get(key)).get());
            }
        }
        return CONTROL;
    }
}
