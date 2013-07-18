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

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;
import javafx.util.Builder;

import java.util.HashMap;


/**
 * Created with IntelliJ IDEA.
 * User: hansolo
 * Date: 24.09.12
 * Time: 15:45
 * To change this template use File | Settings | File Templates.
 */
public class MenuItemBuilder implements Builder<MenuItem> {
    private HashMap<String, Property> properties = new HashMap<String, Property>();


    // ******************** Constructors **************************************
    protected MenuItemBuilder() {}


    // ******************** Methods *******************************************
    public static final MenuItemBuilder create() {
        return new MenuItemBuilder();
    }

    public final MenuItemBuilder tooltip(final String TOOLTIP) {
        properties.put("TOOLTIP", new SimpleStringProperty(TOOLTIP));
        return this;
    }

    public final MenuItemBuilder size(final double SIZE) {
        properties.put("SIZE", new SimpleDoubleProperty(SIZE));
        return this;
    }

    public final MenuItemBuilder innerColor(final Color INNER_COLOR) {
        properties.put("INNER_COLOR", new SimpleObjectProperty<Color>(INNER_COLOR));
        return this;
    }

    public final MenuItemBuilder frameColor(final Color FRAME_COLOR) {
        properties.put("FRAME_COLOR", new SimpleObjectProperty<Color>(FRAME_COLOR));
        return this;
    }

    public final MenuItemBuilder foregroundColor(final Color FOREGROUND_COLOR) {
        properties.put("FOREGROUND_COLOR", new SimpleObjectProperty<Color>(FOREGROUND_COLOR));
        return this;
    }

    public final MenuItemBuilder symbol(final Symbol.Type SYMBOL_TYPE) {
        properties.put("SYMBOL", new SimpleObjectProperty<Symbol.Type>(SYMBOL_TYPE));
        return this;
    }

    public final MenuItemBuilder thumbnailImageName(final String THUMBNAIL_IMAGE_NAME) {
        properties.put("THUMBNAIL_IMAGE_NAME", new SimpleStringProperty(THUMBNAIL_IMAGE_NAME));
        return this;
    }

    @Override public final MenuItem build() {
        final MenuItem CONTROL = new MenuItem();

        properties.forEach((key, property) -> {
            if ("TOOLTIP".equals(key)) {
                CONTROL.setTooltip(((StringProperty) property).get());
            } else if("SIZE".equals(key)) {
                CONTROL.setSize(((DoubleProperty) property).get());
            } else if ("INNER_COLOR".equals(key)) {
                CONTROL.setInnerColor(((ObjectProperty<Color>) property).get());
            } else if ("FRAME_COLOR".equals(key)) {
                CONTROL.setFrameColor(((ObjectProperty<Color>) property).get());
            } else if ("FOREGROUND_COLOR".equals(key)) {
                CONTROL.setForegroundColor(((ObjectProperty<Color>) property).get());
            } else if ("SYMBOL".equals(key)) {
                CONTROL.setSymbol(((ObjectProperty<Symbol.Type>) property).get());
            } else if ("THUMBNAIL_IMAGE_NAME".equals(key)) {
                CONTROL.setThumbnailImageName(((StringProperty) property).get());
            }
        });

        return CONTROL;
    }
}

