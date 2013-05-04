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
 * Date: 15.11.12
 * Time: 00:53
 */

public class RadialBuilder<B extends RadialBuilder<B>> extends ControlBuilder<B> {
    private HashMap<String, Property> properties = new HashMap<>();


    // ******************** Constructors **************************************
    protected RadialBuilder() {
    }


    // ******************** Methods *******************************************
    public final static RadialBuilder create() {
        return new RadialBuilder();
    }

    public final RadialBuilder value(final double VALUE) {
        properties.put("value", new SimpleDoubleProperty(VALUE));
        return this;
    }

    public final RadialBuilder minValue(final double MIN_VALUE) {
        properties.put("minValue", new SimpleDoubleProperty(MIN_VALUE));
        return this;
    }

    public final RadialBuilder maxValue(final double MAX_VALUE) {
        properties.put("maxValue", new SimpleDoubleProperty(MAX_VALUE));
        return this;
    }

    public final RadialBuilder title(final String TITLE) {
        properties.put("title", new SimpleStringProperty(TITLE));
        return this;
    }

    public final RadialBuilder needleType(final Radial.NeedleType NEEDLE_TYPE) {
        properties.put("needleType", new SimpleObjectProperty<Radial.NeedleType>(NEEDLE_TYPE));
        return this;
    }

    public final RadialBuilder animated(final boolean ANIMATED) {
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

    @Override public final Radial build() {
        final Radial CONTROL = new Radial();
        for (String key : properties.keySet()) {
            if ("prefWidth".equals(key)) {
                CONTROL.setPrefWidth(((DoubleProperty) properties.get(key)).get());
            } else if ("prefHeight".equals(key)) {
                CONTROL.setPrefHeight(((DoubleProperty) properties.get(key)).get());
            } else if ("layoutX".equals(key)) {
                CONTROL.setLayoutX(((DoubleProperty) properties.get(key)).get());
            } else if ("layoutY".equals(key)) {
                CONTROL.setLayoutY(((DoubleProperty) properties.get(key)).get());
            } else if ("value".equals(key)) {
                CONTROL.setValue(((DoubleProperty) properties.get(key)).get());
            } else if ("minValue".equals(key)) {
                CONTROL.setMinValue(((DoubleProperty) properties.get(key)).get());
            } else if ("maxValue".equals(key)) {
                CONTROL.setMaxValue(((DoubleProperty) properties.get(key)).get());
            } else if ("title".equals(key)) {
                CONTROL.setTitle(((StringProperty) properties.get(key)).get());
            } else if ("needleType".equals(key)) {
                CONTROL.setNeedleType(((ObjectProperty<Radial.NeedleType>) properties.get(key)).get());
            } else if ("animated".equals(key)) {
                CONTROL.setAnimated(((BooleanProperty) properties.get(key)).get());
            }
        }
        return CONTROL;
    }
}