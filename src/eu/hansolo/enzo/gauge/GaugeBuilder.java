package eu.hansolo.enzo.gauge;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ControlBuilder;

import java.util.HashMap;


/**
 * Created by
 * User: hansolo
 * Date: 02.04.13
 * Time: 09:52
 */
public class GaugeBuilder<B extends GaugeBuilder<B>> extends ControlBuilder<B> {
    private HashMap<String, Property> properties = new HashMap<>();


    // ******************** Constructors **************************************
    protected GaugeBuilder() {
    }


    // ******************** Methods *******************************************
    public final static GaugeBuilder create() {
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

    public final GaugeBuilder title(final String TITLE) {
        properties.put("title", new SimpleStringProperty(TITLE));
        return this;
    }

    public final GaugeBuilder unit(final String UNIT) {
        properties.put("unit", new SimpleStringProperty(UNIT));
        return this;
    }

    public final GaugeBuilder needleType(final Gauge.NeedleType NEEDLE_TYPE) {
        properties.put("needleType", new SimpleObjectProperty<>(NEEDLE_TYPE));
        return this;
    }

    public final GaugeBuilder animated(final boolean ANIMATED) {
        properties.put("animated", new SimpleBooleanProperty(ANIMATED));
        return this;
    }

    @Override public final B prefWidth(final double PREF_WIDTH) {
        properties.put("prefWidth", new SimpleDoubleProperty(PREF_WIDTH));
        return (B) this;
    }

    @Override public final B prefHeight(final double PREF_HEIGHT) {
        properties.put("prefHeight", new SimpleDoubleProperty(PREF_HEIGHT));
        return (B) this;
    }

    @Override public final B layoutX(final double LAYOUT_X) {
        properties.put("layoutX", new SimpleDoubleProperty(LAYOUT_X));
        return (B) this;
    }

    @Override public final B layoutY(final double LAYOUT_Y) {
        properties.put("layoutY", new SimpleDoubleProperty(LAYOUT_Y));
        return (B) this;
    }

    @Override public final B translateX(final double TRANSLATE_X) {
        properties.put("translateX", new SimpleDoubleProperty(TRANSLATE_X));
        return (B) this;
    }

    @Override public final B translateY(final double TRANSLATE_Y) {
        properties.put("translateY", new SimpleDoubleProperty(TRANSLATE_Y));
        return (B) this;
    }

    @Override public final Gauge build() {
        final GaugeModel MODEL = new GaugeModel();

        for (String key : properties.keySet()) {
            if ("value".equals(key)) {
                MODEL.setValue(((DoubleProperty) properties.get(key)).get());
            } else if ("minValue".equals(key)) {
                MODEL.setMinValue(((DoubleProperty) properties.get(key)).get());
            } else if ("maxValue".equals(key)) {
                MODEL.setMaxValue(((DoubleProperty) properties.get(key)).get());
            } else if ("title".equals(key)) {
                MODEL.setTitle(((StringProperty) properties.get(key)).get());
            } else if ("unit".equals(key)) {
                MODEL.setUnit(((StringProperty) properties.get(key)).get());
            } else if ("needleType".equals(key)) {
                MODEL.setNeedleType(((ObjectProperty<Gauge.NeedleType>) properties.get(key)).get());
            } else if ("animated".equals(key)) {
                MODEL.setAnimated(((BooleanProperty) properties.get(key)).get());
            }
        }

        // Create control from model
        final Gauge CONTROL = new Gauge(MODEL);

        for (String key : properties.keySet()) {
            if ("prefWidth".equals(key)) {
                CONTROL.setPrefWidth(((DoubleProperty) properties.get(key)).get());
            } else if ("prefHeight".equals(key)) {
                CONTROL.setPrefHeight(((DoubleProperty) properties.get(key)).get());
            } else if ("layoutX".equals(key)) {
                CONTROL.setLayoutX(((DoubleProperty) properties.get(key)).get());
            } else if ("layoutY".equals(key)) {
                CONTROL.setLayoutY(((DoubleProperty) properties.get(key)).get());
            } else if ("translateX".equals(key)) {
                CONTROL.setTranslateX(((DoubleProperty) properties.get(key)).get());
            } else if ("translateY".equals(key)) {
                CONTROL.setTranslateY(((DoubleProperty) properties.get(key)).get());
            }
        }

        return CONTROL;
    }
}