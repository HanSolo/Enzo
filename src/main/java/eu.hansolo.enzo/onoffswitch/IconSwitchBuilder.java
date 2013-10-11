package eu.hansolo.enzo.onoffswitch;

import eu.hansolo.enzo.common.SymbolType;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Dimension2D;
import javafx.scene.paint.Color;

import java.util.HashMap;


/**
 * User: hansolo
 * Date: 12.10.13
 * Time: 01:49
 */
public class IconSwitchBuilder  <B extends IconSwitchBuilder<B>> {
    private HashMap<String, Property> properties = new HashMap<>();


    // ******************** Constructors **************************************
    protected IconSwitchBuilder() {
    }


    // ******************** Methods *******************************************
    public final static IconSwitchBuilder create() {
        return new IconSwitchBuilder();
    }

    public final IconSwitchBuilder styleClass(final String STYLE_CLASS) {
        properties.put("styleClass", new SimpleStringProperty(STYLE_CLASS));
        return this;
    }

    public final IconSwitchBuilder switchColor(final Color SWITCH_COLOR) {
        properties.put("switchColor", new SimpleObjectProperty<>(SWITCH_COLOR));
        return this;
    }

    public final IconSwitchBuilder thumbColor(final Color THUMB_COLOR) {
        properties.put("thumbColor", new SimpleObjectProperty<>(THUMB_COLOR));
        return this;
    }

    public final IconSwitchBuilder symbolColor(final Color SYMBOL_COLOR) {
        properties.put("symbolColor", new SimpleObjectProperty<>(SYMBOL_COLOR));
        return this;
    }

    public final IconSwitchBuilder symbolType(final SymbolType SYMBOL_TYPE) {
        properties.put("symbolType", new SimpleObjectProperty<>(SYMBOL_TYPE));
        return this;
    }

    public final IconSwitchBuilder text(final String TEXT) {
        properties.put("text", new SimpleStringProperty(TEXT));
        return this;
    }

    public final IconSwitchBuilder on(final boolean ON) {
        properties.put("on", new SimpleBooleanProperty(ON));
        return this;
    }

    public final B prefSize(final double WIDTH, final double HEIGHT) {
        properties.put("prefSize", new SimpleObjectProperty<>(new Dimension2D(WIDTH, HEIGHT)));
        return (B)this;
    }
    public final B minSize(final double WIDTH, final double HEIGHT) {
        properties.put("minSize", new SimpleObjectProperty<>(new Dimension2D(WIDTH, HEIGHT)));
        return (B)this;
    }
    public final B maxSize(final double WIDTH, final double HEIGHT) {
        properties.put("maxSize", new SimpleObjectProperty<>(new Dimension2D(WIDTH, HEIGHT)));
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

    public final IconSwitch build() {
        final IconSwitch CONTROL = new IconSwitch();
        for (String key : properties.keySet()) {
            if ("prefSize".equals(key)) {
                Dimension2D dim = ((ObjectProperty<Dimension2D>) properties.get(key)).get();
                CONTROL.setPrefSize(dim.getWidth(), dim.getHeight());
            } else if("minSize".equals(key)) {
                Dimension2D dim = ((ObjectProperty<Dimension2D>) properties.get(key)).get();
                CONTROL.setPrefSize(dim.getWidth(), dim.getHeight());
            } else if("maxSize".equals(key)) {
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
                CONTROL.getStyleClass().setAll("on-off-switch", ((StringProperty) properties.get(key)).get());
            } else if ("switchColor".equals(key)) {
                CONTROL.setSwitchColor(((ObjectProperty<Color>) properties.get(key)).get());
            } else if ("thumbColorOn".equals(key)) {
                CONTROL.setThumbColor(((ObjectProperty<Color>) properties.get(key)).get());
            } else if ("symbolColor".equals(key)) {
                CONTROL.setSymbolColor(((ObjectProperty<Color>) properties.get(key)).get());
            } else if ("symbolType".equals(key)) {
                CONTROL.setSymbolType(((ObjectProperty<SymbolType>) properties.get(key)).get());
            } else if ("text".equals(key)) {
                CONTROL.setText(((StringProperty) properties.get(key)).get());
            } else if ("on".equals(key)) {
                CONTROL.setOn(((BooleanProperty) properties.get(key)).get());
            }
        }
        return CONTROL;
    }
}
