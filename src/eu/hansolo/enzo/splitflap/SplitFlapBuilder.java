package eu.hansolo.enzo.splitflap;

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
import javafx.scene.paint.Color;

import java.util.HashMap;


public class SplitFlapBuilder<B extends SplitFlapBuilder<B>> extends ControlBuilder<B> { //implements Builder<SplitFlap> {
    private HashMap<String, Property> properties = new HashMap<>();


    // ******************** Constructors **************************************
    protected SplitFlapBuilder() {}


    // ******************** Methods *******************************************
    public static final SplitFlapBuilder create() {
        return new SplitFlapBuilder();
    }

    public final SplitFlapBuilder keepAspect(final boolean KEEP_ASPECT) {
        properties.put("keepAspect", new SimpleBooleanProperty(KEEP_ASPECT));
        return this;
    }

    public final SplitFlapBuilder flipTime(final double FLIP_TIME) {
        properties.put("flipTime", new SimpleDoubleProperty(FLIP_TIME));
        return this;
    }

    public final SplitFlapBuilder textColor(final Color TEXT_COLOR) {
        properties.put("textColor", new SimpleObjectProperty<Color>(TEXT_COLOR));
        return this;
    }

    public final SplitFlapBuilder text(final String TEXT) {
        properties.put("text", new SimpleStringProperty(TEXT));
        return this;
    }

    public final SplitFlapBuilder characterSet(final SplitFlap.CharacterSet CHARACTER_SET) {
        properties.put("characterSet", new SimpleObjectProperty<SplitFlap.CharacterSet>(CHARACTER_SET));
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

    @Override public final SplitFlap build() {
        final SplitFlap CONTROL;
        if (properties.containsKey("characterSet")) {
            if (properties.containsKey("text")) {
                CONTROL = new SplitFlap(((ObjectProperty<SplitFlap.CharacterSet>) properties.get("characterSet")).get(),
                                        ((StringProperty) properties.get("text")).get());
            } else {
                CONTROL = new SplitFlap(((ObjectProperty<SplitFlap.CharacterSet>) properties.get("characterSet")).get(),
                                        ((ObjectProperty<SplitFlap.CharacterSet>) properties.get("characterSet")).get().selection[0]);
            }
        } else {
            CONTROL = new SplitFlap();
        }
        for (String key : properties.keySet()) {
            if ("keepAspect".equals(key)) {
                CONTROL.setKeepAspect(((BooleanProperty) properties.get(key)).get());
            } else if ("flipTime".equals(key)) {
                CONTROL.setFlipTime(((DoubleProperty) properties.get(key)).get());
            } else if ("textColor".equals(key)) {
                CONTROL.setTextColor(((ObjectProperty<Color>) properties.get(key)).get());
            } else if ("text".equals(key)) {
                CONTROL.setText(((StringProperty) properties.get(key)).get());
            } else if ("characterSet".equals(key)) {
                CONTROL.setCharacterSet(((ObjectProperty<SplitFlap.CharacterSet>) properties.get(key)).get());
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

