package eu.hansolo.enzo.led1;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.ControlBuilder;

import java.util.HashMap;


public class LedBuilder<B extends LedBuilder<B>> extends ControlBuilder<B> { //implements Builder<Led> {
    private HashMap<String, Property> properties = new HashMap<>();


    // ******************** Constructors **************************************
    protected LedBuilder() {}


    // ******************** Methods *******************************************
    public static final LedBuilder create() {
        return new LedBuilder();
    }

    public final LedBuilder on(final boolean ON) {
        properties.put("on", new SimpleBooleanProperty(ON));
        return this;
    }

    public final LedBuilder blinking(final boolean BLINKING) {
        properties.put("blinking", new SimpleBooleanProperty(BLINKING));
        return this;
    }

    public final LedBuilder frameVisible(final boolean FRAME_VISIBLE) {
        properties.put("frameVisible", new SimpleBooleanProperty(FRAME_VISIBLE));
        return this;
    }

    public final LedBuilder color(final Led.LedColor COLOR) {
        properties.put("color", new SimpleObjectProperty<Led.LedColor>(COLOR));
        return this;
    }

    public final LedBuilder interval(final long INTERVAL) {
        properties.put("interval", new SimpleLongProperty(INTERVAL));
        return this;
    }


    @Override public final B prefWidth(final double PREF_WIDTH) {
        properties.put("prefWidth", new SimpleDoubleProperty(PREF_WIDTH));
        return (B)this;
    }

    @Override public final B prefHeight(final double PREF_HEIGHT) {
        properties.put("prefHeight", new SimpleDoubleProperty(PREF_HEIGHT));
        return (B)this;
    }

    @Override public final B layoutX(final double LAYOUT_X) {
        properties.put("layoutX", new SimpleDoubleProperty(LAYOUT_X));
        return (B)this;
    }

    @Override public final B layoutY(final double LAYOUT_Y) {
        properties.put("layoutY", new SimpleDoubleProperty(LAYOUT_Y));
        return (B)this;
    }

    @Override public final Led build() {
        final Led CONTROL = new Led();
        for (String key : properties.keySet()) {
            if ("on".equals(key)) {
                CONTROL.setOn(((BooleanProperty) properties.get(key)).get());
            } else if("blinking".equals(key)) {
                CONTROL.setBlinking(((BooleanProperty) properties.get(key)).get());
            } else if("frameVisible".equals(key)) {
                CONTROL.setFrameVisible(((BooleanProperty) properties.get(key)).get());
            } else if("color".equals(key)) {
                CONTROL.setColor(((ObjectProperty<Led.LedColor>) properties.get(key)).get());
            } else if ("interval".equals(key)) {
                CONTROL.setInterval(((LongProperty) properties.get(key)).get());
            } else if("prefWidth".equals(key)) {
                CONTROL.setPrefWidth(((DoubleProperty) properties.get(key)).get());
            } else if("prefHeight".equals(key)) {
                CONTROL.setPrefHeight(((DoubleProperty) properties.get(key)).get());
            } else if ("layoutX".equals(key)) {
                CONTROL.setLayoutX(((DoubleProperty) properties.get(key)).get());
            } else if ("layoutY".equals(key)) {
                CONTROL.setLayoutY(((DoubleProperty) properties.get(key)).get());
            }
        }

        return CONTROL;
    }
}

