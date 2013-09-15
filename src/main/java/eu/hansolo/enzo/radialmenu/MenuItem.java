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
import javafx.beans.property.SimpleBooleanProperty;
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
    private String                     _tooltip;
    private StringProperty             tooltip;
    private double                     _size;
    private DoubleProperty             size;
    private Color                      _innerColor;
    private ObjectProperty<Color>      innerColor;
    private Color                      _frameColor;
    private ObjectProperty<Color>      frameColor;
    private Color                      _foregroundColor;
    private ObjectProperty<Color>      foregroundColor;
    private Color                      _selectedColor;
    private ObjectProperty<Color>      selectedColor;
    private SymbolType                 _symbolType;
    private ObjectProperty<SymbolType> symbolType;
    private StringProperty             thumbnailImageName;
    private boolean                    _selectable;
    private BooleanProperty            selectable;
    private boolean                    _selected;
    private BooleanProperty            selected;


    // ******************** Constructors **************************************
    public MenuItem() {
        this("", 32, Color.rgb(68, 64, 60), Color.WHITE, Color.WHITE, Color.rgb(255,153,0), SymbolType.NONE, "");
    }
    public MenuItem(final SymbolType SYMBOL_TYPE) {
        this("", 32, Color.rgb(68, 64, 60), Color.WHITE, Color.WHITE, Color.rgb(255,153,0), SYMBOL_TYPE, "");
    }
    public MenuItem(final String THUMBNAIL_IMAGE_NAME) {
        this("", 32, Color.rgb(68, 64, 60), Color.WHITE, Color.WHITE, Color.rgb(255,153,0), SymbolType.NONE, THUMBNAIL_IMAGE_NAME);
    }
    public MenuItem(final SymbolType SYMBOL_TYPE, final String TOOLTIP) {
        this(TOOLTIP, 32, Color.rgb(68, 64, 60), Color.WHITE, Color.WHITE, Color.rgb(255,153,0), SYMBOL_TYPE, "");
    }
    public MenuItem(final SymbolType SYMBOL_TYPE, final String TOOLTIP, final Color INNER_COLOR, final Color SELECTED_COLOR) {
        this(TOOLTIP, 32, INNER_COLOR, Color.WHITE, Color.WHITE, SELECTED_COLOR, SYMBOL_TYPE, "");
    }
    public MenuItem(final String TOOLTIP, final double SIZE, final Color INNER_COLOR, final Color FRAME_COLOR, final Color FOREGROUND_COLOR, final Color SELECTED_COLOR, final SymbolType SYMBOL_TYPE, final String THUMBNAIL_IMAGE_NAME) {
        _tooltip           = TOOLTIP;
        _size              = SIZE;
        _innerColor        = INNER_COLOR;
        _frameColor        = FRAME_COLOR;
        _foregroundColor   = FOREGROUND_COLOR;
        _selectedColor     = SELECTED_COLOR;
        _symbolType        = SYMBOL_TYPE;
        _selectable        = false;
        _selected          = false;
        thumbnailImageName = new SimpleStringProperty(this, "thumbnailImageName", THUMBNAIL_IMAGE_NAME);
    }


    // ******************** Methods *******************************************
    public String getTooltip() {
        return null == tooltip ? _tooltip : tooltip.get();
    }
    public void setTooltip(final String TOOLTIP) {
        if (null == tooltip) {
            _tooltip = TOOLTIP;
        } else {
            tooltip.set(TOOLTIP);
        }
    }
    public StringProperty tooltipProperty() {
        if (null == tooltip) {
            tooltip = new SimpleStringProperty(this, "tooltip", _tooltip);
        }
        return tooltip;
    }

    public double getSize() {
        return null == size ? _size : size.get();
    }
    public void setSize(final double SIZE) {
        if (null == size) {
            _size = SIZE;
        } else {
            size.set(SIZE);
        }
    }
    public DoubleProperty sizeProperty() {
        if (null == size) {
            size = new SimpleDoubleProperty(this, "size", _size);
        }
        return size;
    }

    public Color getInnerColor() {
        return null == innerColor ? _innerColor : innerColor.get();
    }
    public void setInnerColor(final Color INNER_COLOR) {
        if (null == innerColor) {
            _innerColor = INNER_COLOR;
        } else {
            innerColor.set(INNER_COLOR);
        }
    }
    public ObjectProperty<Color>innerColorProperty() {
        if (null == innerColor) {
            innerColor = new SimpleObjectProperty<>(this, "innerColor", _innerColor);
        }
        return innerColor;
    }

    public Color getFrameColor() {
        return null == frameColor ? _frameColor : frameColor.get();
    }
    public void setFrameColor(final Color FRAME_COLOR) {
        if (null == frameColor) {
            _frameColor = FRAME_COLOR;
        } else {
            frameColor.set(FRAME_COLOR);
        }
    }
    public ObjectProperty<Color> frameColorProperty() {
        if (null == frameColor) {
            frameColor = new SimpleObjectProperty<>(this, "frameColor", _frameColor);
        }
        return frameColor;
    }

    public Color getForegroundColor() {
        return null == foregroundColor ? _foregroundColor : foregroundColor.get();
    }
    public void setForegroundColor(final Color FOREGROUND_COLOR) {
        if (null == foregroundColor) {
            _foregroundColor = FOREGROUND_COLOR;
        } else {
            foregroundColor.set(FOREGROUND_COLOR);
        }
    }
    public ObjectProperty<Color> foregroundColorProperty() {
        if (null == foregroundColor) {
            foregroundColor = new SimpleObjectProperty<>(this, "foregroundColor", _foregroundColor);
        }
        return foregroundColor;
    }

    public Color getSelectedColor() {
        return null == selectedColor ? _selectedColor : selectedColor.get();
    }
    public void setSelectedColor(final Color SELECTED_COLOR) {
        if (null == selectedColor) {
            _selectedColor = SELECTED_COLOR;
        } else {
            selectedColor.set(SELECTED_COLOR);
        }
    }
    public ObjectProperty<Color> selectedColorProperty() {
        if (null == selectedColor) {
            selectedColor = new SimpleObjectProperty<>(this, "selectedColor", _selectedColor);
        }
        return selectedColor;
    }

    public SymbolType getSymbolType() {
        return null == symbolType ? _symbolType : symbolType.get();
    }
    public void setSymbolType(final SymbolType SYMBOL_TYPE) {
        if (null == symbolType) {
            _symbolType = SYMBOL_TYPE;
        } else {
            symbolType.set(SYMBOL_TYPE);
        }
    }
    public ObjectProperty<SymbolType> symbolTypeProperty() {
        if (null == symbolType) {
            symbolType = new SimpleObjectProperty<>(this, "symbolType", _symbolType);
        }
        return symbolType;
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

    public boolean isSelectable() {
        return null == selectable ? _selectable : selectable.get();
    }
    public void setSelectable(final boolean SELECTABLE) {
        if (null == selectable) {
            _selectable = SELECTABLE;
        } else {
            selectable.set(SELECTABLE);
        }
    }
    public BooleanProperty selectableProperty() {
        if (null == selectable) {
            selectable = new SimpleBooleanProperty(this, "selectable", _selectable);
        }
        return selectable;
    }

    public boolean isSelected() {
        return null == selected ? _selected : selected.get();
    }
    public void setSelected(final boolean SELECTED) {
        if (null == selected) {
            _selected = SELECTED;
        } else {
            selected.set(SELECTED);
        }
    }
    public BooleanProperty selectedProperty() {
        if (null == selected) {
            selected = new SimpleBooleanProperty(this, "selected", _selected);
        }
        return selected;
    }
}
