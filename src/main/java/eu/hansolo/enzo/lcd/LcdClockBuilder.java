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

package eu.hansolo.enzo.lcd;

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

import java.time.Clock;
import java.util.HashMap;
import java.util.List;


/**
 * Created by
 * User: hansolo
 * Date: 19.08.13
 * Time: 08:15
 */
public class LcdClockBuilder<B extends LcdClockBuilder<B>> {
    private HashMap<String, Property> properties = new HashMap<>();


    // ******************** Constructors **************************************
    protected LcdClockBuilder() {}


    // ******************** Methods *******************************************
    public static final LcdClockBuilder create() {
        return new LcdClockBuilder();
    }

    public final LcdClockBuilder styleClass(final String STYLE_CLASS) {
        properties.put("styleClass", new SimpleStringProperty(STYLE_CLASS));
        return this;
    }

    public final LcdClockBuilder clock(final Clock CLOCK) {
        properties.put("clock", new SimpleObjectProperty<>(CLOCK));
        return this;
    }

    public final LcdClockBuilder keepAspect(final boolean KEEP_ASPECT) {
        properties.put("keepAspect", new SimpleBooleanProperty(KEEP_ASPECT));
        return this;
    }

    public final LcdClockBuilder noFrame(final boolean NO_FRAME) {
        properties.put("noFrame", new SimpleBooleanProperty(NO_FRAME));
        return this;
    }

    public final LcdClockBuilder backgroundVisible(final boolean BACKGROUND_VISIBLE) {
        properties.put("backgroundVisible", new SimpleBooleanProperty(BACKGROUND_VISIBLE));
        return this;
    }

    public final LcdClockBuilder crystalOverlayVisible(final boolean CRYSTAL_OVERLAY_VISIBLE) {
        properties.put("crystalOverlayVisible", new SimpleBooleanProperty(CRYSTAL_OVERLAY_VISIBLE));
        return this;
    }

    public final LcdClockBuilder mainInnerShadowVisible(final boolean MAIN_INNER_SHADOW_VISIBLE) {
        properties.put("mainInnerShadowVisible", new SimpleBooleanProperty(MAIN_INNER_SHADOW_VISIBLE));
        return this;
    }

    public final LcdClockBuilder foregroundShadowVisible(final boolean FOREGROUND_SHADOW_VISIBLE) {
        properties.put("foregroundShadowVisible", new SimpleBooleanProperty(FOREGROUND_SHADOW_VISIBLE));
        return this;
    }

    public final LcdClockBuilder title(final String TITLE) {
        properties.put("title", new SimpleStringProperty(TITLE));
        return this;
    }

    public final LcdClockBuilder alarms(final Alarm... ALARMS) {
        properties.put("alarmsArray", new SimpleObjectProperty<>(ALARMS));
        return this;
    }

    public final LcdClockBuilder alarms(final List<Alarm> ALARMS) {
        properties.put("alarmsList", new SimpleObjectProperty<>(ALARMS));
        return this;
    }

    public final LcdClockBuilder titleFont(final String TITLE_FONT) {
        properties.put("titleFont", new SimpleStringProperty(TITLE_FONT));
        return this;
    }

    public final LcdClockBuilder secondFont(final String SECOND_FONT) {
        properties.put("secondFont", new SimpleStringProperty(SECOND_FONT));
        return this;
    }

    public final LcdClockBuilder timeFont(final LcdClock.LcdFont TIME_FONT) {
        properties.put("timeFont", new SimpleObjectProperty<LcdClock.LcdFont>(TIME_FONT));
        return this;
    }

    public final LcdClockBuilder smallFont(final String SMALL_FONT) {
        properties.put("smallFont", new SimpleStringProperty(SMALL_FONT));
        return this;
    }

    public final B prefSize(final double WIDTH, final double HEIGHT) {
        properties.put("prefSize", new SimpleObjectProperty<>(new Dimension2D(WIDTH, HEIGHT)));
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

    public final LcdClock build() {
        final LcdClock CONTROL = new LcdClock();
        for (String key : properties.keySet()) {
            if ("prefSize".equals(key)) {
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
                CONTROL.getStyleClass().setAll("lcd-clock", ((StringProperty) properties.get(key)).get());
            } else if ("keepAspect".equals(key)) {
                CONTROL.setKeepAspect(((BooleanProperty) properties.get(key)).get());
            } else if ("noFrame".equals(key)) {
                CONTROL.setNoFrame(((BooleanProperty) properties.get(key)).get());
            } else if ("backgroundVisible".equals(key)) {
                CONTROL.setBackgroundVisible(((BooleanProperty) properties.get(key)).get());
            } else if ("crystalOverlayVisible".equals(key)) {
                CONTROL.setCrystalOverlayVisible(((BooleanProperty) properties.get(key)).get());
            } else if ("mainInnerShadowVisible".equals(key)) {
                CONTROL.setMainInnerShadowVisible(((BooleanProperty) properties.get(key)).get());
            } else if ("foregroundShadowVisible".equals(key)) {
                CONTROL.setForegroundShadowVisible(((BooleanProperty) properties.get(key)).get());
            } else if("title".equals(key)) {
                CONTROL.setTitle(((StringProperty) properties.get(key)).get());
            } else if ("titleFont".equals(key)) {
                CONTROL.setTitleFont(((StringProperty) properties.get(key)).get());
            } else if ("timeFont".equals(key)) {
                CONTROL.setTimeFont(((ObjectProperty<LcdClock.LcdFont>) properties.get(key)).get());
            } else if ("smallFont".equals(key)) {
                CONTROL.setSmallFont(((StringProperty) properties.get(key)).get());
            } else if("alarmsArray".equals(key)) {
                CONTROL.setAlarms(((ObjectProperty<Alarm[]>) properties.get(key)).get());
            } else if("alarmsList".equals(key)) {
                CONTROL.setAlarms(((ObjectProperty<List<Alarm>>) properties.get(key)).get());
            }
        }
        return CONTROL;
    }
}
