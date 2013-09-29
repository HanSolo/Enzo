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

import com.sun.javafx.css.converters.ColorConverter;
import com.sun.javafx.css.converters.PaintConverter;
import javafx.beans.property.*;
import javafx.css.*;
import javafx.scene.control.Control;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: hansolo
 * Date: 21.09.12
 * Time: 13:26
 * To change this template use File | Settings | File Templates.
 */
public class MenuItem extends Region {
    private static final double        PREFERRED_SIZE                   = 35;
    private static final double        MINIMUM_SIZE                     = 15;
    private static final double        MAXIMUM_SIZE                     = 1024;

    public static final Paint          DEFAULT_BACKGROUND_FILL          = Color.YELLOW;
    public static final Color          DEFAULT_BORDER_COLOR             = Color.YELLOW;
    public static final Paint          DEFAULT_FOREGROUND_FILL          = Color.WHITE;
    public static final Paint          DEFAULT_SELECTED_BACKGROUND_FILL = Color.ORANGE;
    public static final Paint          DEFAULT_SELECTED_FOREGROUND_FILL = Color.WHITE;

    private static final PseudoClass   SELECT_PSEUDO_CLASS              = PseudoClass.getPseudoClass("select");
    private BooleanProperty            selected;
    private boolean                    _selectable;
    private BooleanProperty            selectable;
    private String                     _tooltip;
    private StringProperty             tooltip;
    private double                     _size;
    private DoubleProperty             size;
    private Paint                      _backgroundFill;
    private ObjectProperty<Paint>      backgroundFill;
    private Color                      _borderColor;
    private ObjectProperty<Color>      borderColor;
    private Paint                      _foregroundFill;
    private ObjectProperty<Paint>      foregroundFill;
    private Paint                      _selectedBackgroundFill;
    private ObjectProperty<Paint>      selectedBackgroundFill;
    private Paint                      _selectedForegroundFill;
    private ObjectProperty<Paint>      selectedForegroundFill;
    private SymbolType                 _symbolType;
    private ObjectProperty<SymbolType> symbolType;
    private StringProperty             thumbnailImageName;
    private StringProperty             text;


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
    public MenuItem(final String TOOLTIP, final double SIZE, final Color BACKGROUND_FILL, final Color STROKE_COLOR, final Color FOREGROUND_FILL, final Color SELECTED_BACKGROUND_FILL, final SymbolType SYMBOL_TYPE, final String THUMBNAIL_IMAGE_NAME) {
        getStyleClass().setAll("menu-item");

        _tooltip                = TOOLTIP;
        _size                   = SIZE;
        _backgroundFill         = BACKGROUND_FILL;
        _borderColor = STROKE_COLOR;
        _foregroundFill         = FOREGROUND_FILL;
        _selectedBackgroundFill = SELECTED_BACKGROUND_FILL;
        _selectedForegroundFill = Color.WHITE;
        _symbolType             = SYMBOL_TYPE;
        _selectable             = false;
        thumbnailImageName      = new SimpleStringProperty(this, "thumbnailImageName", THUMBNAIL_IMAGE_NAME);

        init();
        initGraphics();
        registerListeners();
    }


    // ******************** Initialization ************************************
    private void init() {
        if (Double.compare(getWidth(), 0) <= 0 ||
            Double.compare(getHeight(), 0) <= 0 ||
            Double.compare(getPrefWidth(), 0) <= 0 ||
            Double.compare(getPrefHeight(), 0) <= 0) {
            setPrefSize(PREFERRED_SIZE, PREFERRED_SIZE);
        }
        if (Double.compare(getMinWidth(), 0) <= 0 ||
            Double.compare(getMinHeight(), 0) <= 0) {
            setMinSize(MINIMUM_SIZE, MINIMUM_SIZE);
        }
        if (Double.compare(getMaxWidth(), 0) <= 0 ||
            Double.compare(getMaxHeight(), 0) <= 0) {
            setMaxSize(MAXIMUM_SIZE, MAXIMUM_SIZE);
        }
    }

    private void initGraphics() {
        setPickOnBounds(false);
    }

    private void registerListeners() {}


    // ******************** Methods *******************************************
    @Override protected double computePrefWidth(final double PREF_HEIGHT) {
        double prefHeight = PREFERRED_SIZE;
        if (PREF_HEIGHT != -1) {
            prefHeight = Math.max(0, PREF_HEIGHT - getInsets().getTop() - getInsets().getBottom());
        }
        return super.computePrefWidth(prefHeight);
    }
    @Override protected double computePrefHeight(final double PREF_WIDTH) {
        double prefWidth = PREFERRED_SIZE;
        if (PREF_WIDTH != -1) {
            prefWidth = Math.max(0, PREF_WIDTH - getInsets().getLeft() - getInsets().getRight());
        }
        return super.computePrefWidth(prefWidth);
    }

    @Override protected double computeMinWidth(final double MIN_HEIGHT) {
        return super.computeMinWidth(Math.max(MINIMUM_SIZE, MIN_HEIGHT - getInsets().getTop() - getInsets().getBottom()));
    }
    @Override protected double computeMinHeight(final double MIN_WIDTH) {
        return super.computeMinHeight(Math.max(MINIMUM_SIZE, MIN_WIDTH - getInsets().getLeft() - getInsets().getRight()));
    }

    @Override protected double computeMaxWidth(final double MAX_HEIGHT) {
        return super.computeMaxWidth(Math.min(MAXIMUM_SIZE, MAX_HEIGHT - getInsets().getTop() - getInsets().getBottom()));
    }
    @Override protected double computeMaxHeight(final double MAX_WIDTH) {
        return super.computeMaxHeight(Math.min(MAXIMUM_SIZE, MAX_WIDTH - getInsets().getLeft() - getInsets().getRight()));
    }

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

    public Paint getBackgroundFill() {
        return null == backgroundFill ? _backgroundFill : backgroundFill.get();
    }
    public void setBackgroundFill(final Paint BACKGROUND_FILL) {
        if (null == backgroundFill) {
            _backgroundFill = BACKGROUND_FILL;
        } else {
            backgroundFill.set(BACKGROUND_FILL);
        }
    }
    public ObjectProperty<Paint> backgroundFillProperty() {
        if (null == backgroundFill) {
            backgroundFill = new StyleableObjectProperty<Paint>(DEFAULT_BACKGROUND_FILL) {
                @Override public CssMetaData getCssMetaData() { return StyleableProperties.BACKGROUND_FILL; }
                @Override public Object getBean() { return MenuItem.this; }
                @Override public String getName() { return "backgroundFill"; }
            };
        }
        return backgroundFill;
    }

    public Color getBorderColor() {
        return null == borderColor ? _borderColor : borderColor.get();
    }
    public void setBorderColor(final Color BORDER_COLOR) {
        if (null == borderColor) {
            _borderColor = BORDER_COLOR;
        } else {
            borderColor.set(BORDER_COLOR);
        }
    }
    public ObjectProperty<Color> borderColorProperty() {
        if (null == borderColor) {
            borderColor = new StyleableObjectProperty<Color>(DEFAULT_BORDER_COLOR) {
                @Override public CssMetaData getCssMetaData() { return StyleableProperties.BORDER_COLOR; }
                @Override public Object getBean() { return MenuItem.this; }
                @Override public String getName() { return "borderColor"; }
            };
        }
        return borderColor;
    }

    public Paint getForegroundFill() {
        return null == foregroundFill ? _foregroundFill : foregroundFill.get();
    }
    public void setForegroundFill(final Paint FOREGROUND_FILL) {
        if (null == foregroundFill) {
            _foregroundFill = FOREGROUND_FILL;
        } else {
            foregroundFill.set(FOREGROUND_FILL);
        }
    }
    public ObjectProperty<Paint> foregroundFillProperty() {
        if (null == foregroundFill) {
            foregroundFill = new StyleableObjectProperty<Paint>(DEFAULT_FOREGROUND_FILL) {
                @Override public CssMetaData getCssMetaData() { return StyleableProperties.FOREGROUND_FILL; }
                @Override public Object getBean() { return MenuItem.this; }
                @Override public String getName() { return "foregroundFill"; }
            };
        }
        return foregroundFill;
    }

    public Paint getSelectedBackgroundFill() {
        return null == selectedBackgroundFill ? _selectedBackgroundFill : selectedBackgroundFill.get();
    }
    public void setSelectedBackgroundFill(final Paint SELECTED_BACKGROUND_FILL) {
        if (null == selectedBackgroundFill) {
            _selectedBackgroundFill = SELECTED_BACKGROUND_FILL;
        } else {
            selectedBackgroundFill.set(SELECTED_BACKGROUND_FILL);
        }
    }
    public ObjectProperty<Paint> selectedBackgroundFillProperty() {
        if (null == selectedBackgroundFill) {
            selectedBackgroundFill = new StyleableObjectProperty<Paint>(DEFAULT_SELECTED_BACKGROUND_FILL) {
                @Override public CssMetaData getCssMetaData() { return StyleableProperties.SELECTED_BACKGROUND_FILL; }
                @Override public Object getBean() { return MenuItem.this; }
                @Override public String getName() { return "selectedBackgroundFill"; }
            };
        }
        return selectedBackgroundFill;
    }

    public Paint getSelectedForegroundFill() {
        return null == selectedForegroundFill ? _selectedForegroundFill : selectedForegroundFill.get();
    }
    public void setSelectedForegroundFill(final Paint SELECTED_FOREGROUND_FILL) {
        if (null == selectedForegroundFill) {
            _selectedForegroundFill = SELECTED_FOREGROUND_FILL;
        } else {
            selectedForegroundFill.set(SELECTED_FOREGROUND_FILL);
        }
    }
    public ObjectProperty<Paint> selectedForegroundFillProperty() {
        if (null == selectedForegroundFill) {
            selectedForegroundFill = new StyleableObjectProperty<Paint>(DEFAULT_SELECTED_FOREGROUND_FILL) {
                @Override public CssMetaData getCssMetaData() { return StyleableProperties.SELECTED_FOREGROUND_FILL; }
                @Override public Object getBean() { return MenuItem.this; }
                @Override public String getName() { return "selectedForegroundFill"; }
            };
        }
        return selectedForegroundFill;
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

    public String getText() {
        return null == text ? "" : text.get();
    }
    public void setText(final String TEXT) {
        textProperty().set(TEXT);
    }
    public StringProperty textProperty() {
        if (null == text) {
            text = new SimpleStringProperty(this, "text", "");
        }
        return text;
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

    public final boolean isSelected() {
        return null == selected ? false : selected.get();
    }
    public final void setSelected(final boolean SELECTED) {
        if (isSelectable()) {
            if (null == selected) initSelected();
            selected.set(SELECTED);
        }
    }
    public final ReadOnlyBooleanProperty selectedProperty() {
        if (null == selected) initSelected();
        return selected;
    }
    private void initSelected() {
        selected = new BooleanPropertyBase(false) {
            @Override protected void invalidated() { pseudoClassStateChanged(SELECT_PSEUDO_CLASS, get());}
            @Override public Object getBean() { return this; }
            @Override public String getName() { return "select"; }
        };
    }


    // ******************** CSS Meta Data *************************************
    private static class StyleableProperties {
        private static final CssMetaData<MenuItem, Paint> BACKGROUND_FILL =
            new CssMetaData<MenuItem, Paint>("-item-background", PaintConverter.getInstance(), DEFAULT_BACKGROUND_FILL) {

                @Override public boolean isSettable(MenuItem node) {
                    return null == node.backgroundFill || !node.backgroundFill.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(MenuItem node) {
                    return (StyleableProperty) node.backgroundFillProperty();
                }

                @Override public Paint getInitialValue(MenuItem node) {
                    return node.getBackgroundFill();
                }
            };

        private static final CssMetaData<MenuItem, Color> BORDER_COLOR =
            new CssMetaData<MenuItem, Color>("-item-border", ColorConverter.getInstance(), DEFAULT_BORDER_COLOR) {

                @Override public boolean isSettable(MenuItem node) {
                    return null == node.borderColor || !node.borderColor.isBound();
                }

                @Override public StyleableProperty<Color> getStyleableProperty(MenuItem node) {
                    return (StyleableProperty) node.borderColorProperty();
                }

                @Override public Color getInitialValue(MenuItem node) {
                    return node.getBorderColor();
                }
            };

        private static final CssMetaData<MenuItem, Paint> FOREGROUND_FILL =
            new CssMetaData<MenuItem, Paint>("-item-foreground", PaintConverter.getInstance(), DEFAULT_FOREGROUND_FILL) {

                @Override public boolean isSettable(MenuItem node) {
                    return null == node.foregroundFill || !node.foregroundFill.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(MenuItem node) {
                    return (StyleableProperty) node.foregroundFillProperty();
                }

                @Override public Paint getInitialValue(MenuItem node) {
                    return node.getForegroundFill();
                }
            };

        private static final CssMetaData<MenuItem, Paint> SELECTED_BACKGROUND_FILL =
            new CssMetaData<MenuItem, Paint>("-item-selected-background", PaintConverter.getInstance(), DEFAULT_SELECTED_BACKGROUND_FILL) {

                @Override public boolean isSettable(MenuItem node) {
                    return null == node.selectedBackgroundFill || !node.selectedBackgroundFill.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(MenuItem node) {
                    return (StyleableProperty) node.selectedBackgroundFillProperty();
                }

                @Override public Paint getInitialValue(MenuItem node) {
                    return node.getSelectedBackgroundFill();
                }
            };

        private static final CssMetaData<MenuItem, Paint> SELECTED_FOREGROUND_FILL =
            new CssMetaData<MenuItem, Paint>("-item-selected-foreground", PaintConverter.getInstance(), DEFAULT_SELECTED_FOREGROUND_FILL) {

                @Override public boolean isSettable(MenuItem node) {
                    return null == node.selectedForegroundFill || !node.selectedForegroundFill.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(MenuItem node) {
                    return (StyleableProperty) node.selectedForegroundFillProperty();
                }

                @Override public Paint getInitialValue(MenuItem node) {
                    return node.getSelectedForegroundFill();
                }
            };

        private static final List<CssMetaData<? extends Styleable, ?>> STYLEABLES;
        static {
            final List<CssMetaData<? extends Styleable, ?>> styleables = new ArrayList<>(Control.getClassCssMetaData());
            Collections.addAll(styleables,
                               BACKGROUND_FILL,
                               BORDER_COLOR,
                               FOREGROUND_FILL,
                               SELECTED_BACKGROUND_FILL,
                               SELECTED_FOREGROUND_FILL);
            STYLEABLES = Collections.unmodifiableList(styleables);
        }
    }

    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {
        return StyleableProperties.STYLEABLES;
    }
}
