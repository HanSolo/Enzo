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

package eu.hansolo.enzo.radialmenu;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;


/**
 * Created with IntelliJ IDEA.
 * User: hansolo
 * Date: 21.09.12
 * Time: 13:26
 * To change this template use File | Settings | File Templates.
 */
public class MenuItem {
    private String                      defaultTooltip;
    private StringProperty              tooltip;
    private double                      defaultSize;
    private DoubleProperty              size;
    private Color                       defaultInnerColor;
    private ObjectProperty<Color>       innerColor;
    private Color                       defaultFrameColor;
    private ObjectProperty<Color>       frameColor;
    private Color                       defaultForegroundColor;
    private ObjectProperty<Color>       foregroundColor;
    private Symbol.Type                 defaultSymbol;
    private ObjectProperty<Symbol.Type> symbol;
    private StringProperty              thumbnailImageName;


    // ******************** Constructors **************************************
    public MenuItem() {
        this("", 32, Color.rgb(68, 64, 60), Color.WHITE, Color.WHITE, Symbol.Type.NONE, "");
    }
    public MenuItem(final Symbol.Type SYMBOL_TYPE) {
        this("", 32, Color.rgb(68, 64, 60), Color.WHITE, Color.WHITE, SYMBOL_TYPE, "");
    }
    public MenuItem(final String THUMBNAIL_IMAGE_NAME) {
        this("", 32, Color.rgb(68, 64, 60), Color.WHITE, Color.WHITE, Symbol.Type.NONE, THUMBNAIL_IMAGE_NAME);
    }
    public MenuItem(final Symbol.Type SYMBOL_TYPE, final String TOOLTIP) {
        this(TOOLTIP, 32, Color.rgb(68, 64, 60), Color.WHITE, Color.WHITE, SYMBOL_TYPE, "");
    }
    public MenuItem(final Symbol.Type SYMBOL_TYPE, final String TOOLTIP, final Color INNER_COLOR) {
        this(TOOLTIP, 32, INNER_COLOR, Color.WHITE, Color.WHITE, SYMBOL_TYPE, "");
    }
    public MenuItem(final String TOOLTIP, final double SIZE, final Color INNER_COLOR, final Color FRAME_COLOR, final Color FOREGROUND_COLOR, final Symbol.Type SYMBOL_TYPE, final String THUMBNAIL_IMAGE_NAME) {
        defaultTooltip         = TOOLTIP;
        defaultSize            = SIZE;
        defaultInnerColor      = INNER_COLOR;
        defaultFrameColor      = FRAME_COLOR;
        defaultForegroundColor = FOREGROUND_COLOR;
        defaultSymbol          = SYMBOL_TYPE;
        thumbnailImageName     = new SimpleStringProperty(this, "thumbnailImageName", THUMBNAIL_IMAGE_NAME);
    }


    // ******************** Methods *******************************************
    public String getTooltip() {
        return null == tooltip ? defaultTooltip : tooltip.get();
    }
    public void setTooltip(final String TOOLTIP) {
        if (null == tooltip) {
            defaultTooltip = TOOLTIP;
        } else {
            tooltip.set(TOOLTIP);
        }
    }
    public StringProperty tooltipProperty() {
        if (null == tooltip) {
            tooltip = new SimpleStringProperty(this, "tooltip", defaultTooltip);
        }
        return tooltip;
    }

    public double getSize() {
        return null == size ? defaultSize : size.get();
    }
    public void setSize(final double SIZE) {
        if (null == size) {
            defaultSize = SIZE;
        } else {
            size.set(SIZE);
        }
    }
    public DoubleProperty sizeProperty() {
        if (null == size) {
            size = new SimpleDoubleProperty(this, "size", defaultSize);
        }
        return size;
    }

    public Color getInnerColor() {
        return null == innerColor ? defaultInnerColor : innerColor.get();
    }
    public void setInnerColor(final Color INNER_COLOR) {
        if (null == innerColor) {
            defaultInnerColor = INNER_COLOR;
        } else {
            innerColor.set(INNER_COLOR);
        }
    }
    public ObjectProperty<Color>innerColorProperty() {
        if (null == innerColor) {
            innerColor = new SimpleObjectProperty<>(this, "innerColor", defaultInnerColor);
        }
        return innerColor;
    }

    public Color getFrameColor() {
        return null == frameColor ? defaultFrameColor : frameColor.get();
    }
    public void setFrameColor(final Color FRAME_COLOR) {
        if (null == frameColor) {
            defaultFrameColor = FRAME_COLOR;
        } else {
            frameColor.set(FRAME_COLOR);
        }
    }
    public ObjectProperty<Color> frameColorProperty() {
        if (null == frameColor) {
            frameColor = new SimpleObjectProperty<>(this, "frameColor", defaultFrameColor);
        }
        return frameColor;
    }

    public Color getForegroundColor() {
        return null == foregroundColor ? defaultForegroundColor : foregroundColor.get();
    }
    public void setForegroundColor(final Color FOREGROUND_COLOR) {
        if (null == foregroundColor) {
            defaultForegroundColor = FOREGROUND_COLOR;
        } else {
            foregroundColor.set(FOREGROUND_COLOR);
        }
    }
    public ObjectProperty<Color> foregroundColorProperty() {
        if (null == foregroundColor) {
            foregroundColor = new SimpleObjectProperty<>(this, "foregroundColor", defaultForegroundColor);
        }
        return foregroundColor;
    }

    public Symbol.Type getSymbol() {
        return null == symbol ? defaultSymbol : symbol.get();
    }
    public void setSymbol(final Symbol.Type SYMBOL_TYPE) {
        if (null == symbol) {
            defaultSymbol = SYMBOL_TYPE;
        } else {
            symbol.set(SYMBOL_TYPE);
        }
    }
    public ObjectProperty<Symbol.Type> symbolProperty() {
        if (null == symbol) {
            symbol = new SimpleObjectProperty<>(this, "symbol", defaultSymbol);
        }
        return symbol;
   }

    public String getThumbnailImageName() {
        return thumbnailImageName.get();
    }
    public void setThumbnailImageName(final String THUMBNAIL_IMAGE_NAME) {
        thumbnailImageName.set(THUMBNAIL_IMAGE_NAME);
    }
    public StringProperty thumbnailImageNameProperty() {
        return thumbnailImageName;
    }
}
