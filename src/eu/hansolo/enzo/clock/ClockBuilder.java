/*
 * Copyright (c) 2013, Gerrit Grunwald
 * All right reserved
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * The names of its contributors may not be used to endorse or promote
 * products derived from this software without specific prior written
 * permission.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package eu.hansolo.enzo.clock;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.ControlBuilder;

import java.util.HashMap;


/**
 * Created by
 * User: hansolo
 * Date: 09.01.13
 * Time: 09:02
 */
public class ClockBuilder<B extends ClockBuilder<B>> extends ControlBuilder<B> {
    private HashMap<String, Property> properties = new HashMap<>();


    // ******************** Constructors **************************************
    protected ClockBuilder() {
    }


    // ******************** Methods *******************************************
    public final static ClockBuilder create() {
        return new ClockBuilder();
    }

    public final ClockBuilder nightMode(final boolean NIGHT_MODE) {
        properties.put("nightMode", new SimpleBooleanProperty(NIGHT_MODE));
        return this;
    }

    public final ClockBuilder design(final Clock.Design DESIGN) {
        properties.put("design", new SimpleObjectProperty<Clock.Design>(DESIGN));
        return this;
    }

    public final ClockBuilder discreteSecond(final boolean DISCRETE_SECOND) {
        properties.put("discreteSecond", new SimpleBooleanProperty(DISCRETE_SECOND));
        return this;
    }

    public final ClockBuilder secondPointerVisible(final boolean SECOND_POINTER_VISIBLE) {
        properties.put("secondPointerVisible", new SimpleBooleanProperty(SECOND_POINTER_VISIBLE));
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

    @Override public final Clock build() {
        final Clock CONTROL = new Clock();
        for (String key : properties.keySet()) {
            if ("prefWidth".equals(key)) {
                CONTROL.setPrefWidth(((DoubleProperty) properties.get(key)).get());
            } else if ("prefHeight".equals(key)) {
                CONTROL.setPrefHeight(((DoubleProperty) properties.get(key)).get());
            } else if ("layoutX".equals(key)) {
                CONTROL.setLayoutX(((DoubleProperty) properties.get(key)).get());
            } else if ("layoutY".equals(key)) {
                CONTROL.setLayoutY(((DoubleProperty) properties.get(key)).get());
            } else if ("nightMode".equals(key)) {
                CONTROL.setNightMode(((BooleanProperty) properties.get(key)).get());
            } else if ("design".equals(key)) {
                CONTROL.setDesign(((ObjectProperty<Clock.Design>) properties.get(key)).get());
            } else if ("discreteSecond".equals(key)) {
                CONTROL.setDiscrecteSecond(((BooleanProperty) properties.get(key)).get());
            } else if ("secondPointerVisible".equals(key)) {
                CONTROL.setSecondPointerVisible(((BooleanProperty) properties.get(key)).get());
            }
        }
        return CONTROL;
    }
}