package eu.langhammer.enzo2.onoffswitch;

import com.sun.javafx.css.converters.PaintConverter;
import eu.langhammer.enzo2.common.Symbol;
import eu.langhammer.enzo2.common.SymbolType;
import eu.langhammer.enzo2.onoffswitch.skin.IconSwitchSkin;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.BooleanPropertyBase;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.css.CssMetaData;
import javafx.css.PseudoClass;
import javafx.css.Styleable;
import javafx.css.StyleableObjectProperty;
import javafx.css.StyleableProperty;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * User: hansolo
 * Date: 10.10.13
 * Time: 08:41
 */
public class IconSwitch extends Control implements Toggle {
    public static final  Color          DEFAULT_SWITCH_COLOR  = Color.WHITE;
    public static final  Color          DEFAULT_THUMB_COLOR   = Color.WHITE;
    public static final  Color          DEFAULT_SYMBOL_COLOR  = Color.DARKGRAY;
    private static final PseudoClass    PSEUDO_CLASS_SELECTED = PseudoClass.getPseudoClass("selected");
    private final Symbol                symbol;

    // CSS styleable properties
    private ObjectProperty<Paint>       switchColor;
    private ObjectProperty<Paint>       thumbColor;
    private StringProperty              text;
    private ObjectProperty<ToggleGroup> toggleGroup;

    // CSS pseudo classes
    private BooleanProperty             selected;


    // ******************** Constructors **************************************
    public IconSwitch() {
        getStyleClass().setAll("icon-switch");
        symbol = new Symbol(SymbolType.NONE, 19, DEFAULT_SYMBOL_COLOR, Symbol.RESIZEABLE);
    }


    // ******************** Methods *******************************************
    public final boolean isSelected() {
        return null == selected ? false : selected.get();
    }
    public final void setSelected(final boolean ON) {
        selectedProperty().set(ON);
    }
    public final BooleanProperty selectedProperty() {
        if (null == selected) {
            selected = new BooleanPropertyBase() {
                @Override protected void invalidated() {
                    if (null != getToggleGroup()) {
                        if (get()) {
                            getToggleGroup().selectToggle(IconSwitch.this);
                        } else if (getToggleGroup().getSelectedToggle() == IconSwitch.this) {
                            getToggleGroup().selectToggle(null);
                        }
                    }
                    if (selected.get()) {
                        fireSelectionEvent(new SelectionEvent(this, null, SelectionEvent.SELECT));
                    } else {
                        fireSelectionEvent(new SelectionEvent(this, null, SelectionEvent.DESELECT));
                    }
                    pseudoClassStateChanged(PSEUDO_CLASS_SELECTED, get());
                }
                @Override public Object getBean() { return this; }
                @Override public String getName() { return "selected"; }
            };
        }
        return selected;
    }

    public final ToggleGroup getToggleGroup() {
        return null == toggleGroup ? null : toggleGroup.get();
    }
    public final void setToggleGroup(ToggleGroup value) {
        toggleGroupProperty().set(value);
    }
    public final ObjectProperty<ToggleGroup> toggleGroupProperty() {
        if (null == toggleGroup) {
            toggleGroup = new ObjectPropertyBase<ToggleGroup>() {
                private ToggleGroup oldToggleGroup;
                @Override protected void invalidated() {
                    final ToggleGroup toggleGroup = get();
                    if (null != toggleGroup && !toggleGroup.getToggles().contains(IconSwitch.this)) {
                        if (oldToggleGroup != null) {
                            oldToggleGroup.getToggles().remove(IconSwitch.this);
                        }
                        toggleGroup.getToggles().add(IconSwitch.this);
                    } else if (null == toggleGroup) {
                        oldToggleGroup.getToggles().remove(IconSwitch.this);
                    }
                    oldToggleGroup = toggleGroup;
                }
                @Override public Object getBean() { return IconSwitch.this; }
                @Override public String getName() { return "toggleGroup"; }
            };
        }
        return toggleGroup;
    }

    public final SymbolType getSymbolType() {
        return symbol.getSymbolType();
    }
    public final void setSymbolType(final SymbolType SYMBOL_TYPE) {
        symbol.setSymbolType(SYMBOL_TYPE);
    }
    public final ObjectProperty<SymbolType> symbolTypeProperty() {
        return symbol.symbolTypeProperty();
    }

    public final Color getSymbolColor() {
        return symbol.getColor();
    }
    public final void setSymbolColor(final Color SYMBOL_COLOR) {
        symbol.setColor(SYMBOL_COLOR);
    }
    public final ObjectProperty<Color> symbolColorProperty() {
        return symbol.colorProperty();
    }

    public final String getText() {
        return (null == text) ? "" : text.get();
    }
    public final void setText(final String TEXT) {
        textProperty().set(TEXT);
    }
    public final StringProperty textProperty() {
        if (null == text) {
            text = new SimpleStringProperty(this, "text", "");
        }
        return text;
    }

    public final Symbol getSymbol() {
        return symbol;
    }


    // ******************** CSS Stylable Properties ***************************
    public final Paint getSwitchColor() {
        return null == switchColor ? DEFAULT_SWITCH_COLOR : switchColor.get();
    }
    public final void setSwitchColor(Paint value) {
        switchColorProperty().set(value);
    }
    public final ObjectProperty<Paint> switchColorProperty() {
        if (null == switchColor) {
            switchColor = new StyleableObjectProperty<Paint>(DEFAULT_SWITCH_COLOR) {
                @Override public CssMetaData getCssMetaData() { return StyleableProperties.SWITCH_COLOR; }

                @Override public Object getBean() { return IconSwitch.this; }

                @Override public String getName() { return "switchColor"; }
            };
        }
        return switchColor;
    }

    public final Paint getThumbColor() {
        return null == thumbColor ? DEFAULT_THUMB_COLOR : thumbColor.get();
    }
    public final void setThumbColor(Paint value) {
        thumbColorProperty().set(value);
    }
    public final ObjectProperty<Paint> thumbColorProperty() {
        if (null == thumbColor) {
            thumbColor = new StyleableObjectProperty<Paint>(DEFAULT_THUMB_COLOR) {
                @Override public CssMetaData getCssMetaData() { return StyleableProperties.THUMB_COLOR; }

                @Override public Object getBean() { return IconSwitch.this; }

                @Override public String getName() { return "thumbColor"; }
            };
        }
        return thumbColor;
    }


    // ******************** Style related *************************************
    @Override protected Skin createDefaultSkin() {
        return new IconSwitchSkin(this);
    }

    @Override public String getUserAgentStylesheet() {
        return getClass().getResource("iconswitch.css").toExternalForm();
    }

    private static class StyleableProperties {
        private static final CssMetaData<IconSwitch, Paint> SWITCH_COLOR =
            new CssMetaData<IconSwitch, Paint>("-switch-color", PaintConverter.getInstance(), DEFAULT_SWITCH_COLOR) {
                @Override public boolean isSettable(IconSwitch iconSwitch) {
                    return null == iconSwitch.switchColor || !iconSwitch.switchColor.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(IconSwitch iconSwitch) {
                    return (StyleableProperty) iconSwitch.switchColorProperty();
                }

                @Override public Color getInitialValue(IconSwitch iconSwitch) {
                    return (Color) iconSwitch.getSwitchColor();
                }
            };

        private static final CssMetaData<IconSwitch, Paint> THUMB_COLOR =
            new CssMetaData<IconSwitch, Paint>("-thumb-color", PaintConverter.getInstance(), DEFAULT_THUMB_COLOR) {
                @Override public boolean isSettable(IconSwitch iconSwitch) {
                    return null == iconSwitch.thumbColor || !iconSwitch.thumbColor.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(IconSwitch iconSwitch) {
                    return (StyleableProperty) iconSwitch.thumbColorProperty();
                }

                @Override public Color getInitialValue(IconSwitch iconSwitch) {
                    return (Color) iconSwitch.getThumbColor();
                }
            };

        private static final List<CssMetaData<? extends Styleable, ?>> STYLEABLES;
        static {
            final List<CssMetaData<? extends Styleable, ?>> styleables = new ArrayList<>(Control.getClassCssMetaData());
            Collections.addAll(styleables,
                               SWITCH_COLOR,
                               THUMB_COLOR
            );
            STYLEABLES = Collections.unmodifiableList(styleables);
        }
    }

    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {
        return StyleableProperties.STYLEABLES;
    }

    @Override public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {
        return getClassCssMetaData();
    }


    // ******************** Event handling*************************************
    public final ObjectProperty<EventHandler<SelectionEvent>> onSelectProperty() { return onSelect; }
    public final void setOnSelect(EventHandler<SelectionEvent> value) { onSelectProperty().set(value); }
    public final EventHandler<SelectionEvent> getOnSelect() { return onSelectProperty().get(); }
    private ObjectProperty<EventHandler<SelectionEvent>> onSelect = new ObjectPropertyBase<EventHandler<SelectionEvent>>() {
        @Override public Object getBean() { return this; }
        @Override public String getName() { return "onSelect";}
    };

    public final ObjectProperty<EventHandler<SelectionEvent>> onDeselectProperty() { return onDeselect; }
    public final void setOnDeselect(EventHandler<SelectionEvent> value) { onDeselectProperty().set(value); }
    public final EventHandler<SelectionEvent> getOnDeselect() { return onDeselectProperty().get(); }
    private ObjectProperty<EventHandler<SelectionEvent>> onDeselect = new ObjectPropertyBase<EventHandler<SelectionEvent>>() {
        @Override public Object getBean() { return this; }
        @Override public String getName() { return "onDeselect";}
    };

    public void fireSelectionEvent(final SelectionEvent EVENT) {
        fireEvent(EVENT);
        final EventType TYPE = EVENT.getEventType();
        final EventHandler<SelectionEvent> HANDLER;
        if (SelectionEvent.SELECT == TYPE) {
            HANDLER = getOnSelect();
        } else if (SelectionEvent.DESELECT == TYPE) {
            HANDLER = getOnDeselect();
        } else {
            HANDLER = null;
        }
        if (null == HANDLER) return;
        HANDLER.handle(EVENT);
    }
}
