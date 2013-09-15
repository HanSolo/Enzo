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

package eu.hansolo.enzo.radialmenu;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;
import javafx.util.Builder;

import java.util.HashMap;


/**
 * Created with IntelliJ IDEA.
 * User: hansolo
 * Date: 24.09.12
 * Time: 15:56
 * To change this template use File | Settings | File Templates.
 */
public class OptionsBuilder implements Builder<Options> {
    private HashMap<String, Property> properties = new HashMap<String, Property>();


    // ******************** Constructors **************************************
    protected OptionsBuilder() {}


    // ******************** Methods *******************************************
    public static final OptionsBuilder create() {
        return new OptionsBuilder();
    }

    public final OptionsBuilder degrees(final double DEGREES) {
        properties.put("degrees", new SimpleDoubleProperty(DEGREES));
        return this;
    }

    public final OptionsBuilder offset(final double OFFSET) {
        properties.put("offset", new SimpleDoubleProperty(OFFSET));
        return this;
    }

    public final OptionsBuilder radius(final double RADIUS) {
        properties.put("radius", new SimpleDoubleProperty(RADIUS));
        return this;
    }

    public final OptionsBuilder buttonSize(final double BUTTON_SIZE) {
        properties.put("buttonSize", new SimpleDoubleProperty(BUTTON_SIZE));
        return this;
    }

    public final OptionsBuilder buttonInnerColor(final Color BUTTON_INNER_COLOR) {
        properties.put("buttonInnerColor", new SimpleObjectProperty<Color>(BUTTON_INNER_COLOR));
        return this;
    }

    public final OptionsBuilder buttonFrameColor(final Color BUTTON_FRAME_COLOR) {
        properties.put("buttonFrameColor", new SimpleObjectProperty<Color>(BUTTON_FRAME_COLOR));
        return this;
    }

    public final OptionsBuilder buttonForegroundColor(final Color BUTTON_FOREGROUND_COLOR) {
        properties.put("buttonForegroundColor", new SimpleObjectProperty<Color>(BUTTON_FOREGROUND_COLOR));
        return this;
    }

    public final OptionsBuilder buttonAlpha(final double BUTTON_ALPHA) {
        properties.put("buttonAlpha", new SimpleDoubleProperty(BUTTON_ALPHA));
        return this;
    }

    public final OptionsBuilder buttonHideOnSelect(final boolean BUTTON_HIDE_ON_SELECT) {
        properties.put("buttonHideOnSelect", new SimpleBooleanProperty(BUTTON_HIDE_ON_SELECT));
        return this;
    }

    public final OptionsBuilder hideOnClose(final boolean HIDE_ON_CLOSE) {
        properties.put("hideOnClose", new SimpleBooleanProperty(HIDE_ON_CLOSE));
        return this;
    }

    public final OptionsBuilder tooltipsEnabled(final boolean TOOLTIPS_ENABLED) {
        properties.put("tooltipsEnabled", new SimpleBooleanProperty(TOOLTIPS_ENABLED));
        return this;
    }

    public final OptionsBuilder buttonVisible(final boolean BUTTON_VISIBLE) {
        properties.put("buttonVisible", new SimpleBooleanProperty(BUTTON_VISIBLE));
        return this;
    }

    @Override public final Options build() {
        final Options CONTROL = new Options();

        properties.forEach((key, property) -> {
            if ("degrees".equals(key)) {
                CONTROL.setDegrees(((DoubleProperty) property).get());
            } else if("offset".equals(key)) {
                CONTROL.setOffset(((DoubleProperty) property).get());
            } else if ("radius".equals(key)) {
                CONTROL.setRadius(((DoubleProperty) property).get());
            } else if ("buttonSize".equals(key)) {
                CONTROL.setButtonSize(((DoubleProperty) property).get());
            } else if ("buttonInnerColor".equals(key)) {
                CONTROL.setButtonInnerColor(((ObjectProperty<Color>) property).get());
            } else if ("buttonFrameColor".equals(key)) {
                CONTROL.setButtonFrameColor(((ObjectProperty<Color>) property).get());
            } else if ("buttonForegroundColor".equals(key)) {
                CONTROL.setButtonForegroundColor(((ObjectProperty<Color>) property).get());
            } else if ("buttonAlpha".equals(key)) {
                CONTROL.setButtonAlpha(((DoubleProperty) property).get());
            } else if ("buttonHideOnSelect".equals(key)) {
                CONTROL.setButtonHideOnSelect(((BooleanProperty) property).get());
            } else if ("hideOnClose".equals(key)) {
                CONTROL.setHideOnClose(((BooleanProperty) property).get());
            } else if ("tooltipsEnabled".equals(key)) {
                CONTROL.setTooltipsEnabled(((BooleanProperty) property).get());
            } else if ("buttonVisible".equals(key)) {
                CONTROL.setButtonVisible(((BooleanProperty) property).get());
            }
        });

        return CONTROL;
    }
}

