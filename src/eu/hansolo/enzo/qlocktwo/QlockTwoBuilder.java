package eu.hansolo.enzo.qlocktwo;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.ControlBuilder;

import java.util.HashMap;


public class QlockTwoBuilder<B extends QlockTwoBuilder<B>> extends ControlBuilder<B> { //implements Builder<QlockTwo> {
    private HashMap<String, Property> properties = new HashMap<>();


    // ******************** Constructors **************************************
    protected QlockTwoBuilder() {}


    // ******************** Methods *******************************************
    public static final QlockTwoBuilder create() {
        return new QlockTwoBuilder();
    }

    public final QlockTwoBuilder color(final QlockTwo.QlockColor COLOR) {
        properties.put("color", new SimpleObjectProperty<QlockTwo.QlockColor>(COLOR));
        return this;
    }

    public final QlockTwoBuilder language(final QlockTwo.Language LANGUAGE) {
        properties.put("language", new SimpleObjectProperty<QlockTwo.Language>(LANGUAGE));
        return this;
    }

    public final QlockTwoBuilder highlightVisible(final boolean HIGHLIGHT_VISIBLE) {
        properties.put("highlightVisible", new SimpleBooleanProperty(HIGHLIGHT_VISIBLE));
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

    @Override public final QlockTwo build() {
        final QlockTwo CONTROL = new QlockTwo();
        for (String key : properties.keySet()) {
            if ("color".equals(key)) {
                CONTROL.setColor(((ObjectProperty<QlockTwo.QlockColor>) properties.get(key)).get());
            } else if ("language".equals(key)) {
                CONTROL.setLanguage(((ObjectProperty<QlockTwo.Language>) properties.get(key)).get());
            } else if ("highlightVisible".equals(key)) {
                CONTROL.setHighlightVisible(((BooleanProperty) properties.get(key)).get());
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

