package eu.hansolo.enzo.signaltower;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Dimension2D;

import java.util.HashMap;


public class SignalTowerBuilder<B extends SignalTowerBuilder<B>> {
    private HashMap<String, Property> properties = new HashMap<>();


    // ******************** Constructors **************************************
    protected SignalTowerBuilder() {}


    // ******************** Methods *******************************************
    public static final SignalTowerBuilder create() {
        return new SignalTowerBuilder();
    }

    public final SignalTowerBuilder greenOn(final boolean GREEN_ON) {
        properties.put("greenOn", new SimpleBooleanProperty(GREEN_ON));
        return this;
    }

    public final SignalTowerBuilder yellowOn(final boolean YELLOW_ON) {
        properties.put("yellowOn", new SimpleBooleanProperty(YELLOW_ON));
        return this;
    }

    public final SignalTowerBuilder redOn(final boolean RED_ON) {
        properties.put("redOn", new SimpleBooleanProperty(RED_ON));
        return this;
    }

    public final B minSize(final double WIDTH, final double HEIGHT) {
        properties.put("minSize", new SimpleObjectProperty<>(new Dimension2D(WIDTH, HEIGHT)));
        return (B)this;
    }
    public final B prefSize(final double WIDTH, final double HEIGHT) {
            properties.put("prefSize", new SimpleObjectProperty<>(new Dimension2D(WIDTH, HEIGHT)));
            return (B)this;
        }
    public final B maxSize(final double WIDTH, final double HEIGHT) {
        properties.put("maxSize", new SimpleObjectProperty<>(new Dimension2D(WIDTH, HEIGHT)));
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

    public final B prefWidth(final double PREF_WIDTH) {
        properties.put("prefWidth", new SimpleDoubleProperty(PREF_WIDTH));
        return (B)this;
    }
    public final B prefHeight(final double PREF_HEIGHT) {
        properties.put("prefHeight", new SimpleDoubleProperty(PREF_HEIGHT));
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

    public final B scaleX(final double SCALE_X) {
        properties.put("scaleX", new SimpleDoubleProperty(SCALE_X));
        return (B)this;
    }
    public final B scaleY(final double SCALE_Y) {
        properties.put("scaleY", new SimpleDoubleProperty(SCALE_Y));
        return (B)this;
    }


    public final SignalTower build() {
        final SignalTower CONTROL = new SignalTower();
        properties.forEach((key, property) -> {
            if ("minSize".equals(key)) {
                Dimension2D dim = ((ObjectProperty<Dimension2D>) property).get();
                CONTROL.setMinSize(dim.getWidth(), dim.getHeight());
            } else if ("prefSize".equals(key)) {
                Dimension2D dim = ((ObjectProperty<Dimension2D>) property).get();
                CONTROL.setPrefSize(dim.getWidth(), dim.getHeight());
            } else if ("maxSize".equals(key)) {
                Dimension2D dim = ((ObjectProperty<Dimension2D>) property).get();
                CONTROL.setMaxSize(dim.getWidth(), dim.getHeight());
            } else if("minWidth".equals(key)) {
                CONTROL.setMinWidth(((DoubleProperty) property).get());
            } else if("minHeight".equals(key)) {
                CONTROL.setMinHeight(((DoubleProperty) property).get());
            } else if("prefWidth".equals(key)) {
                CONTROL.setPrefWidth(((DoubleProperty) property).get());
            } else if("prefHeight".equals(key)) {
                CONTROL.setPrefHeight(((DoubleProperty) property).get());
            } else if("maxWidth".equals(key)) {
                CONTROL.setMaxWidth(((DoubleProperty) property).get());
            } else if("maxHeight".equals(key)) {
                CONTROL.setMaxHeight(((DoubleProperty) property).get());
            } else if ("layoutX".equals(key)) {
                CONTROL.setLayoutX(((DoubleProperty) property).get());
            } else if ("layoutY".equals(key)) {
                CONTROL.setLayoutY(((DoubleProperty) property).get());
            } else if ("translateX".equals(key)) {
                CONTROL.setTranslateX(((DoubleProperty) property).get());
            } else if ("translateY".equals(key)) {
                CONTROL.setTranslateY(((DoubleProperty) property).get());
            } else if ("scaleX".equals(key)) {
                CONTROL.setScaleX(((DoubleProperty) property).get());
            } else if ("scaleY".equals(key)) {
                CONTROL.setScaleY(((DoubleProperty) property).get());
            } else if ("greenOn".equals(key)) {
                CONTROL.setGreenOn(((BooleanProperty) property).get());
            } else if ("yellowOn".equals(key)) {
                CONTROL.setYellowOn(((BooleanProperty) property).get());
            } else if ("redOn".equals(key)) {
                CONTROL.setRedOn(((BooleanProperty) property).get());
            }
        });

        return CONTROL;
    }
}

