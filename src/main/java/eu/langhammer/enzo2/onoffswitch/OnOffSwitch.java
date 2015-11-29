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

package eu.langhammer.enzo2.onoffswitch;

import com.sun.javafx.css.converters.PaintConverter;
import eu.langhammer.enzo2.onoffswitch.skin.OnOffSwitchSkin;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.BooleanPropertyBase;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
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
 * Date: 08.10.13
 * Time: 07:49
 */
public class OnOffSwitch extends Control implements Toggle {
    public static final  Color          DEFAULT_SWITCH_COLOR   = Color.WHITE;
    public static final  Color          DEFAULT_TEXT_COLOR_ON  = Color.WHITE;
    public static final  Color          DEFAULT_TEXT_COLOR_OFF = Color.WHITE;
    public static final  Color          DEFAULT_THUMB_COLOR    = Color.WHITE;
    private static final PseudoClass    PSEUDO_CLASS_SELECTED  = PseudoClass.getPseudoClass("selected");

    // CSS styleable properties
    private ObjectProperty<Paint>       switchColor;
    private ObjectProperty<Paint>       textColorOn;
    private ObjectProperty<Paint>       textColorOff;
    private ObjectProperty<Paint>       thumbColor;
    private ObjectProperty<ToggleGroup> toggleGroup;

    // CSS pseudo classes
    private BooleanProperty             selected;


    // ******************** Constructors **************************************
    public OnOffSwitch() {
        getStyleClass().setAll("on-off-switch");
    }


    // ******************** Methods *******************************************
    public final boolean isSelected() {
        return null == selected ? false : selected.get();
    }
    public final void setSelected(final boolean SELECTED) {
        selectedProperty().set(SELECTED);
    }
    public final BooleanProperty selectedProperty() {
        if (null == selected) {
            selected = new BooleanPropertyBase() {
                @Override protected void invalidated() {
                    if (null != getToggleGroup()) {
                        if (get()) {
                            getToggleGroup().selectToggle(OnOffSwitch.this);
                        } else if (getToggleGroup().getSelectedToggle() == OnOffSwitch.this) {
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
                @Override public String getName() { return "on"; }
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
                    if (null != toggleGroup && !toggleGroup.getToggles().contains(OnOffSwitch.this)) {
                        if (oldToggleGroup != null) {
                            oldToggleGroup.getToggles().remove(OnOffSwitch.this);
                        }
                        toggleGroup.getToggles().add(OnOffSwitch.this);
                    } else if (null == toggleGroup) {
                        oldToggleGroup.getToggles().remove(OnOffSwitch.this);
                    }
                    oldToggleGroup = toggleGroup;
                }
                @Override public Object getBean() { return OnOffSwitch.this; }
                @Override public String getName() { return "toggleGroup"; }
            };
        }
        return toggleGroup;
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
                @Override public Object getBean() { return OnOffSwitch.this; }
                @Override public String getName() { return "switchColor"; }
            };
        }
        return switchColor;
    }

    public final Paint getTextColorOn() {
        return null == textColorOn ? DEFAULT_TEXT_COLOR_ON : textColorOn.get();
    }
    public final void setTextColorOn(Paint value) {
        textColorOnProperty().set(value);
    }
    public final ObjectProperty<Paint> textColorOnProperty() {
        if (null == textColorOn) {
            textColorOn = new StyleableObjectProperty<Paint>(DEFAULT_TEXT_COLOR_ON) {
                @Override public CssMetaData getCssMetaData() { return StyleableProperties.TEXT_COLOR_ON; }
                @Override public Object getBean() { return OnOffSwitch.this; }
                @Override public String getName() { return "switchColor"; }
            };
        }
        return textColorOn;
    }

    public final Paint getTextColorOff() {
        return null == textColorOff ? DEFAULT_TEXT_COLOR_OFF : textColorOff.get();
    }
    public final void setTextColorOff(Paint value) {
        textColorOffProperty().set(value);
    }
    public final ObjectProperty<Paint> textColorOffProperty() {
        if (null == textColorOff) {
            textColorOff = new StyleableObjectProperty<Paint>(DEFAULT_TEXT_COLOR_OFF) {
                @Override public CssMetaData getCssMetaData() { return StyleableProperties.TEXT_COLOR_OFF; }
                @Override public Object getBean() { return OnOffSwitch.this; }
                @Override public String getName() { return "textColorOff"; }
            };
        }
        return textColorOff;
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
                @Override public Object getBean() { return OnOffSwitch.this; }
                @Override public String getName() { return "thumbColor"; }
            };
        }
        return thumbColor;
    }


    // ******************** Style related *************************************
    @Override protected Skin createDefaultSkin() {
        return new OnOffSwitchSkin(this);
    }

    @Override public String getUserAgentStylesheet() {
        return getClass().getResource("onoffswitch.css").toExternalForm();
    }

    private static class StyleableProperties {
        private static final CssMetaData<OnOffSwitch, Paint> SWITCH_COLOR =
            new CssMetaData<OnOffSwitch, Paint>("-switch-color", PaintConverter.getInstance(), DEFAULT_SWITCH_COLOR) {
                @Override public boolean isSettable(OnOffSwitch onOffSwitch) {
                    return null == onOffSwitch.switchColor || !onOffSwitch.switchColor.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(OnOffSwitch onOffSwitch) {
                    return (StyleableProperty) onOffSwitch.switchColorProperty();
                }

                @Override public Color getInitialValue(OnOffSwitch onOffSwitch) {
                    return (Color) onOffSwitch.getSwitchColor();
                }
            };

        private static final CssMetaData<OnOffSwitch, Paint> TEXT_COLOR_ON =
            new CssMetaData<OnOffSwitch, Paint>("-text-color-on", PaintConverter.getInstance(), DEFAULT_TEXT_COLOR_ON) {
                @Override public boolean isSettable(OnOffSwitch onOffSwitch) {
                    return null == onOffSwitch.textColorOn || !onOffSwitch.textColorOn.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(OnOffSwitch onOffSwitch) {
                    return (StyleableProperty) onOffSwitch.textColorOnProperty();
                }

                @Override public Color getInitialValue(OnOffSwitch onOffSwitch) {
                    return (Color) onOffSwitch.getTextColorOn();
                }
            };

        private static final CssMetaData<OnOffSwitch, Paint> TEXT_COLOR_OFF =
            new CssMetaData<OnOffSwitch, Paint>("-text-color-off", PaintConverter.getInstance(), DEFAULT_TEXT_COLOR_OFF) {
                @Override public boolean isSettable(OnOffSwitch onOffSwitch) {
                    return null == onOffSwitch.textColorOff || !onOffSwitch.textColorOff.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(OnOffSwitch onOffSwitch) {
                    return (StyleableProperty) onOffSwitch.textColorOffProperty();
                }

                @Override public Color getInitialValue(OnOffSwitch onOffSwitch) {
                    return (Color) onOffSwitch.getTextColorOff();
                }
            };

        private static final CssMetaData<OnOffSwitch, Paint> THUMB_COLOR =
            new CssMetaData<OnOffSwitch, Paint>("-thumb-color", PaintConverter.getInstance(), DEFAULT_THUMB_COLOR) {
                @Override public boolean isSettable(OnOffSwitch onOffSwitch) {
                    return null == onOffSwitch.thumbColor || !onOffSwitch.thumbColor.isBound();
                }

                @Override public StyleableProperty<Paint> getStyleableProperty(OnOffSwitch onOffSwitch) {
                    return (StyleableProperty) onOffSwitch.thumbColorProperty();
                }

                @Override public Color getInitialValue(OnOffSwitch onOffSwitch) {
                    return (Color) onOffSwitch.getThumbColor();
                }
            };

        private static final List<CssMetaData<? extends Styleable, ?>> STYLEABLES;

        static {
            final List<CssMetaData<? extends Styleable, ?>> styleables = new ArrayList<>(Control.getClassCssMetaData());
            Collections.addAll(styleables,
                               SWITCH_COLOR,
                               TEXT_COLOR_ON,
                               TEXT_COLOR_OFF,
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
