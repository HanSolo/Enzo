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

package eu.hansolo.enzo.experimental.tbutton;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.scene.control.Control;
import javafx.scene.paint.Color;


public class TButton extends Control {
    private BooleanProperty       selected;
    private StringProperty        text;
    private ObjectProperty<Color> ledColor;


    // ******************** Constructors **************************************
    public TButton() {
        this("");
    }
    public TButton(final String TEXT) {
        getStyleClass().add("tbutton");
        selected  = new SimpleBooleanProperty(this, "selected", false);
        text      = new SimpleStringProperty(this, "text", TEXT);
        ledColor  = new SimpleObjectProperty<>(Color.YELLOW);
    }


    // ******************** Methods *******************************************
    public final boolean isSelected() {
        return selected.get();
    }
    public final void setSelected(final boolean SELECTED) {
        selected.set(SELECTED);
    }
    public final BooleanProperty selectedProperty() {
        return selected;
    }

    public final String getText() {
        return text.get();
    }
    public final void setText(final String TEXT) {
        text.set(TEXT);
    }
    public final StringProperty textProperty() {
        return text;
    }
    
    public final Color getLedColor() {
        return ledColor.get();
    }
    public final void setLedColor(final Color LED_COLOR) {
        ledColor.set(LED_COLOR);
    }
    public final ObjectProperty<Color> ledColorProperty() {
        return ledColor;
    }
    
    
    // ******************** Event handling ************************************
    public final ObjectProperty<EventHandler<SelectEvent>> onSelectProperty() { return onSelect; }
    public final void setOnSelect(EventHandler<SelectEvent> value) { onSelectProperty().set(value); }
    public final EventHandler<SelectEvent> getOnSelect() { return onSelectProperty().get(); }
    private ObjectProperty<EventHandler<SelectEvent>> onSelect = new ObjectPropertyBase<EventHandler<SelectEvent>>() {
        @Override public Object getBean() { return this; }
        @Override public String getName() { return "onSelect";}
    };

    public final ObjectProperty<EventHandler<SelectEvent>> onDeselectProperty() { return onDeselect; }
    public final void setOnDeselect(EventHandler<SelectEvent> value) { onDeselectProperty().set(value); }
    public final EventHandler<SelectEvent> getOnDeselect() { return onDeselectProperty().get(); }
    private ObjectProperty<EventHandler<SelectEvent>> onDeselect = new ObjectPropertyBase<EventHandler<SelectEvent>>() {
        @Override public Object getBean() { return this; }
        @Override public String getName() { return "onDeselect";}
    };

    public void fireSelectEvent(final SelectEvent EVENT) {
        final EventHandler<SelectEvent> HANDLER;
        final EventType                    TYPE    = EVENT.getEventType();
        if (SelectEvent.SELECT == TYPE) {
            HANDLER = getOnSelect();
        } else if (SelectEvent.DESELECT == TYPE) {
            HANDLER = getOnDeselect();
        } else {
            HANDLER = null;
        }

        if (HANDLER != null) {
            HANDLER.handle(EVENT);
        }
    }


    // ******************** Style related *************************************
    @Override protected String getUserAgentStylesheet() {
        return getClass().getResource(getClass().getSimpleName().toLowerCase() + ".css").toExternalForm();
    }
    
    
    // ******************** Inner classes *************************************        
    public static class SelectEvent extends Event {
        public static final EventType<SelectEvent> SELECT   = new EventType(ANY, "select");
        public static final EventType<SelectEvent> DESELECT = new EventType(ANY, "deselect");

        // ******************** Constructors **************************************
        public SelectEvent(final Object SOURCE, final EventTarget TARGET, EventType<SelectEvent> TYPE) {
            super(SOURCE, TARGET, TYPE);
        }                
    }            
}

