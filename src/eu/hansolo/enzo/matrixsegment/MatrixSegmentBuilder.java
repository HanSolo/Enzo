package eu.hansolo.enzo.matrixsegment;

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


public class MatrixSegmentBuilder<B extends MatrixSegmentBuilder<B>> extends ControlBuilder<B> { //implements Builder<SquareMatrixSegment> {
    private HashMap<String, Property> properties = new HashMap<>();


    // ******************** Constructors **************************************
    protected MatrixSegmentBuilder() {}


    // ******************** Methods *******************************************
    public static final MatrixSegmentBuilder create() {
        return new MatrixSegmentBuilder();
    }

    public final MatrixSegmentBuilder color(final Color COLOR) {
        properties.put("color", new SimpleObjectProperty<Color>(COLOR));
        return this;
    }

    public final MatrixSegmentBuilder character(final String CHARACTER) {
        properties.put("character", new SimpleStringProperty(CHARACTER));
        return this;
    }

    public final MatrixSegmentBuilder backgroundVisible(final boolean BACKGROUND_VISIBLE) {
        properties.put("backgroundVisible", new SimpleBooleanProperty(BACKGROUND_VISIBLE));
        return this;
    }

    public final MatrixSegmentBuilder highlightsVisible(final boolean HIGHLIGHTS_VISIBLE) {
        properties.put("highlightsVisible", new SimpleBooleanProperty(HIGHLIGHTS_VISIBLE));
        return this;
    }

    public final MatrixSegmentBuilder glowEnabled(final boolean GLOW_ENABLED) {
        properties.put("glowEnabled", new SimpleBooleanProperty(GLOW_ENABLED));
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

    @Override public final MatrixSegment build() {
        final MatrixSegment CONTROL = new MatrixSegment();
        for (String key : properties.keySet()) {
            if ("color".equals(key)) {
                CONTROL.setColor(((ObjectProperty<Color>) properties.get(key)).get());
            } else if ("character".equals(key)) {
                CONTROL.setCharacter(((StringProperty) properties.get(key)).get());
            } else if ("backgroundVisible".equals(key)) {
                CONTROL.setBackgroundVisible(((BooleanProperty) properties.get(key)).get());
            } else if ("highlightsVisible".equals(key)) {
                CONTROL.setHighlightsVisible(((BooleanProperty) properties.get(key)).get());
            } else if ("glowEnabled".equals(key)) {
                CONTROL.setGlowEnabled(((BooleanProperty) properties.get(key)).get());
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

