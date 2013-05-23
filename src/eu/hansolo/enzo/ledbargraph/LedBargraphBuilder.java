/*
 * Copyright (c) 2013. Gerrit Grunwald
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package eu.hansolo.enzo.ledbargraph;

import eu.hansolo.enzo.led.Led;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Orientation;
import javafx.scene.control.ControlBuilder;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.LinkedList;


/**
 * Created by
 * User: hansolo
 * Date: 09.03.12
 * Time: 16:37
 */
public class LedBargraphBuilder<B extends LedBargraphBuilder<B>> extends ControlBuilder<B> {
    private HashMap<String, Property> properties = new HashMap<String, Property>();


    // ******************** Constructors **************************************
    protected LedBargraphBuilder() {};


    // ******************** Methods *******************************************
    public static final LedBargraphBuilder create() {
        return new LedBargraphBuilder();
    }

    public final LedBargraphBuilder noOfLeds(final int NO_OF_LEDS) {
        properties.put("noOfLeds", new SimpleIntegerProperty(NO_OF_LEDS));
        return this;
    }

    public final LedBargraphBuilder ledType(final Led.LedType LED_TYPE) {
        properties.put("ledType", new SimpleObjectProperty<Led.LedType>(LED_TYPE));
        return this;
    }

    public final LedBargraphBuilder orientation(final Orientation ORIENTATION) {
        properties.put("orientation", new SimpleObjectProperty<Orientation>(ORIENTATION));
        return this;
    }

    public final LedBargraphBuilder peakValueVisible(final boolean PEAK_VALUE_VISIBLE) {
        properties.put("peakValueVisible", new SimpleBooleanProperty(PEAK_VALUE_VISIBLE));
        return this;
    }

    public final LedBargraphBuilder ledSize(final double LED_SIZE) {
        properties.put("ledSize", new SimpleDoubleProperty(LED_SIZE));
        return this;
    }

    public final LedBargraphBuilder ledColors(final LinkedList<Color> LED_COLORS) {
        properties.put("ledColors", new SimpleObjectProperty<LinkedList<Color>>(LED_COLORS));
        return this;
    }

    public final LedBargraphBuilder ledColor(final int INDEX, final Color COLOR) {
        properties.put("ledColorIndex", new SimpleIntegerProperty(INDEX));
        properties.put("ledColor", new SimpleObjectProperty<Color>(COLOR));
        return this;
    }

    public final LedBargraphBuilder value(final double VALUE) {
        properties.put("value", new SimpleDoubleProperty(VALUE));
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

    @Override public final B minWidth(final double MIN_WIDTH) {
        properties.put("minWidth", new SimpleDoubleProperty(MIN_WIDTH));
        return (B)this;
    }
    @Override public final B minHeight(final double MIN_HEIGHT) {
        properties.put("minHeight", new SimpleDoubleProperty(MIN_HEIGHT));
        return (B)this;
    }

    @Override public final B maxWidth(final double MAX_WIDTH) {
        properties.put("maxWidth", new SimpleDoubleProperty(MAX_WIDTH));
        return (B)this;
    }
    @Override public final B maxHeight(final double MAX_HEIGHT) {
        properties.put("maxHeight", new SimpleDoubleProperty(MAX_HEIGHT));
        return (B)this;
    }

    @Override public final B scaleX(final double SCALE_X) {
        properties.put("scaleX", new SimpleDoubleProperty(SCALE_X));
        return (B)this;
    }
    @Override public final B scaleY(final double SCALE_Y) {
        properties.put("scaleY", new SimpleDoubleProperty(SCALE_Y));
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

    @Override public final B translateX(final double TRANSLATE_X) {
        properties.put("translateX", new SimpleDoubleProperty(TRANSLATE_X));
        return (B)this;
    }
    @Override public final B translateY(final double TRANSLATE_Y) {
        properties.put("translateY", new SimpleDoubleProperty(TRANSLATE_Y));
        return (B)this;
    }

    @Override public final LedBargraph build() {
        final LedBargraph CONTROL = new LedBargraph();
        for (String key : properties.keySet()) {
            if("prefWidth".equals(key)) {
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
            } else if ("noOfLeds".equals(key)) {
                CONTROL.setNoOfLeds(((IntegerProperty) properties.get(key)).get());
            } else if ("ledType".equals(key)) {
                CONTROL.setLedType(((ObjectProperty<Led.LedType>) properties.get(key)).get());
            } else if ("orientation".equals(key)) {
                CONTROL.setOrientation(((ObjectProperty<Orientation>) properties.get(key)).get());
            } else if ("peakValueVisible".equals(key)) {
                CONTROL.setPeakValueVisible(((BooleanProperty) properties.get(key)).get());
            } else if ("ledSize".equals(key)) {
                CONTROL.setLedSize(((DoubleProperty) properties.get(key)).get());
            } else if ("ledColors".equals(key)) {
                CONTROL.setLedColors(((ObjectProperty<LinkedList<Color>>) properties.get(key)).get());
            } else if ("ledColor".equals(key)) {
                CONTROL.setLedColor(((IntegerProperty) properties.get("ledColorIndex")).get(), ((ObjectProperty<Color>) properties.get(key)).get());
            } else if ("value".equals(key)) {
                CONTROL.setValue(((DoubleProperty) properties.get(key)).get());
            }
        }
        return CONTROL;
    }
}
