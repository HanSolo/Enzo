/*
 * Copyright (c) 2013 by Gerrit Grunwald
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.hansolo.enzo.roundlcdclock;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Dimension2D;
import javafx.scene.paint.Color;

import java.time.LocalTime;
import java.util.HashMap;


/**
 * Created by
 * User: hansolo
 * Date: 11.07.13
 * Time: 09:21
 */
public class RoundLcdClockBuilder<B extends RoundLcdClockBuilder<B>> {
    private HashMap<String, Property> properties = new HashMap<>();


    // ******************** Constructors **************************************
    protected RoundLcdClockBuilder() {
    }


    // ******************** Methods *******************************************
    public final static RoundLcdClockBuilder create() {
        return new RoundLcdClockBuilder();
    }

    public final RoundLcdClockBuilder color(final Color COLOR) {
        properties.put("color", new SimpleObjectProperty<>(COLOR));
        return this;
    }

    public final RoundLcdClockBuilder hourColor(final Color HOUR_COLOR) {
        properties.put("hourColor", new SimpleObjectProperty<>(HOUR_COLOR));
        return this;
    }

    public final RoundLcdClockBuilder minuteColor(final Color MINUTE_COLOR) {
        properties.put("minuteColor", new SimpleObjectProperty<>(MINUTE_COLOR));
        return this;
    }

    public final RoundLcdClockBuilder secondColor(final Color SECOND_COLOR) {
        properties.put("secondColor", new SimpleObjectProperty<>(SECOND_COLOR));
        return this;
    }

    public final RoundLcdClockBuilder timeColor(final Color TIME_COLOR) {
        properties.put("timeColor", new SimpleObjectProperty<>(TIME_COLOR));
        return this;
    }

    public final RoundLcdClockBuilder dateColor(final Color DATE_COLOR) {
        properties.put("dateColor", new SimpleObjectProperty<>(DATE_COLOR));
        return this;
    }

    public final RoundLcdClockBuilder alarmOn(final boolean ALARM_ON) {
        properties.put("alarmOn", new SimpleBooleanProperty(ALARM_ON));
        return this;
    }

    public final RoundLcdClockBuilder alarm(final LocalTime ALARM) {
        properties.put("alarm", new SimpleObjectProperty<>(ALARM));
        return this;
    }

    public final RoundLcdClockBuilder alarmVisible(final boolean ALARM_VISIBLE) {
        properties.put("alarmVisible", new SimpleBooleanProperty(ALARM_VISIBLE));
        return this;
    }

    public final RoundLcdClockBuilder dateVisible(final boolean DATE_VISIBLE) {
        properties.put("dateVisible", new SimpleBooleanProperty(DATE_VISIBLE));
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


    public final RoundLcdClock build() {
        final RoundLcdClock CONTROL = new RoundLcdClock();
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
            } else if ("color".equals(key)) {
                CONTROL.setColor(((ObjectProperty<Color>) properties.get(key)).get());
            } else if ("hourColor".equals(key)) {
                CONTROL.setHourColor(((ObjectProperty<Color>) properties.get(key)).get());
            } else if ("minuteColor".equals(key)) {
                CONTROL.setMinuteColor(((ObjectProperty<Color>) properties.get(key)).get());
            } else if ("secondColor".equals(key)) {
                CONTROL.setSecondColor(((ObjectProperty<Color>) properties.get(key)).get());
            } else if ("timeColor".equals(key)) {
                CONTROL.setTimeColor(((ObjectProperty<Color>) properties.get(key)).get());
            } else if ("dateColor".equals(key)) {
                CONTROL.setDateColor(((ObjectProperty<Color>) properties.get(key)).get());
            } else if ("alarmOn".equals(key)) {
                CONTROL.setAlarmOn(((BooleanProperty) properties.get(key)).get());
            } else if ("alarm".equals(key)) {
                CONTROL.setAlarm(((ObjectProperty<LocalTime>) properties.get(key)).get());
            } else if ("dateVisible".equals(key)) {
                CONTROL.setDateVisible(((BooleanProperty) properties.get(key)).get());
            } else if ("alarmVisible".equals(key)) {
                CONTROL.setAlarmVisible(((BooleanProperty) properties.get(key)).get());
            }
        }
        return CONTROL;
    }
}
