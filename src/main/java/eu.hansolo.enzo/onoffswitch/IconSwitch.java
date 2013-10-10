package eu.hansolo.enzo.onoffswitch;

import com.sun.javafx.css.converters.PaintConverter;
import eu.hansolo.enzo.common.Symbol;
import eu.hansolo.enzo.common.SymbolType;
import eu.hansolo.enzo.onoffswitch.skin.IconSwitchSkin;
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
public class IconSwitch extends Control {
    public static final  Color         DEFAULT_SWITCH_COLOR = Color.WHITE;
    public static final  Color         DEFAULT_THUMB_COLOR  = Color.WHITE;
    public static final  Color         DEFAULT_SYMBOL_COLOR = Color.DARKGRAY;
    private static final PseudoClass   ON_PSEUDO_CLASS      = PseudoClass.getPseudoClass("on");
    private final Symbol               symbol;

    // CSS styleable properties
    private ObjectProperty<Paint>      switchColor;
    private ObjectProperty<Paint>      thumbColor;
    private StringProperty             text;

    // CSS pseudo classes
    private BooleanProperty            on;


    // ******************** Constructors **************************************
    public IconSwitch() {
        getStyleClass().setAll("icon-switch");
        symbol = new Symbol(SymbolType.NONE, 19, DEFAULT_SYMBOL_COLOR, Symbol.RESIZEABLE);
    }


    // ******************** Methods *******************************************
    public final boolean isOn() {
        return null == on ? false : on.get();
    }
    public final void setOn(final boolean ON) {
        onProperty().set(ON);
    }
    public final BooleanProperty onProperty() {
        if (null == on) {
            on = new BooleanPropertyBase(false) {
                @Override protected void invalidated() {
                    pseudoClassStateChanged(ON_PSEUDO_CLASS, get());
                    if (on.get()) {
                        fireSwitchEvent(new SwitchEvent(this, null, SwitchEvent.ON));
                    } else {
                        fireSwitchEvent(new SwitchEvent(this, null, SwitchEvent.OFF));
                    }
                }
                @Override public Object getBean() { return this; }
                @Override public String getName() { return "on"; }
            };
        }
        return on;
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

    @Override protected String getUserAgentStylesheet() {
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
    public final ObjectProperty<EventHandler<SwitchEvent>> onSwitchedOnProperty() { return onSwitchedOn; }
    public final void setOnSwitchedOn(EventHandler<SwitchEvent> value) { onSwitchedOnProperty().set(value); }
    public final EventHandler<SwitchEvent> getOnSwitchedOn() { return onSwitchedOnProperty().get(); }
    private ObjectProperty<EventHandler<SwitchEvent>> onSwitchedOn = new ObjectPropertyBase<EventHandler<SwitchEvent>>() {
        @Override public Object getBean() { return this; }

        @Override public String getName() { return "onSwitchedOn";}
    };

    public final ObjectProperty<EventHandler<SwitchEvent>> onSwitchedOffProperty() { return onSwitchedOff; }
    public final void setOnSwitchedOff(EventHandler<SwitchEvent> value) { onSwitchedOffProperty().set(value); }
    public final EventHandler<SwitchEvent> getOnSwitchedOff() { return onSwitchedOffProperty().get(); }
    private ObjectProperty<EventHandler<SwitchEvent>> onSwitchedOff = new ObjectPropertyBase<EventHandler<SwitchEvent>>() {
        @Override public Object getBean() { return this; }

        @Override public String getName() { return "onSwitchedOff";}
    };

    public void fireSwitchEvent(final SwitchEvent EVENT) {
        fireEvent(EVENT);
        final EventType TYPE = EVENT.getEventType();
        final EventHandler<SwitchEvent> HANDLER;
        if (SwitchEvent.ON == TYPE) {
            HANDLER = getOnSwitchedOn();
        } else if (SwitchEvent.OFF == TYPE) {
            HANDLER = getOnSwitchedOff();
        } else {
            HANDLER = null;
        }
        if (null == HANDLER) return;
        HANDLER.handle(EVENT);
    }
}
