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

package eu.hansolo.enzo.experiments.pushbutton;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.scene.control.Control;
import javafx.scene.paint.Color;


public class PushButton extends Control {
    public static enum Status {
        EMPTY,
        DESELECTED,
        SELECTED
    }
    private Status                 _status;
    private ObjectProperty<Status> status;
    private boolean                _toggleEnabled;
    private BooleanProperty        toggleEnabled;
    private Color                  _color;
    private ObjectProperty<Color>  color;
    private boolean                keepAspect;


    // ******************** Constructors **************************************
    public PushButton() {
        getStyleClass().add("push-button");
        keepAspect     = true;
        _status        = Status.EMPTY;
        _toggleEnabled = true;
        _color         = Color.RED;
        registerListeners();
    }

    private void registerListeners() {
        setOnMousePressed(mouseEvent -> { toggle(); });
        setOnTouchPressed(touchEvent -> { toggle(); });
    }


    // ******************** Methods *******************************************
    public final boolean isKeepAspect() {
        return keepAspect;
    }

    public final Status getStatus() {
        return null == status ? _status : status.get();
    }
    public final void setStatus(final Status STATUS) {
        if (null == status) {
            _status = STATUS;
        } else {
            status.set(STATUS);
        }
        if (Status.DESELECTED == STATUS) {
            fireSelectionEvent(new SelectionEvent(getStatus(), this, null, SelectionEvent.DESELECTED));
        } else if (Status.SELECTED == STATUS) {
            fireSelectionEvent(new SelectionEvent(getStatus(), this, null, SelectionEvent.SELECTED));
        } else if (Status.EMPTY == STATUS) {
            fireSelectionEvent(new SelectionEvent(getStatus(), this, null, SelectionEvent.EMPTY));
        }
    }
    public final ObjectProperty<Status> statusProperty() {
        if (null == status) {
            status = new SimpleObjectProperty<>(this, "status", _status);
        }
        return status;
    }

    public final boolean isToggleEnabled() {
        return null == toggleEnabled ? _toggleEnabled : toggleEnabled.get();
    }
    public final void setToggleEnabled(final boolean TOGGLE_ENABLED) {
        if (null == toggleEnabled) {
            _toggleEnabled = TOGGLE_ENABLED;
        } else {
            toggleEnabled.set(TOGGLE_ENABLED);
        }
    }
    public final BooleanProperty toggleEnabledProperty() {
        if (null == toggleEnabled) {
            toggleEnabled = new SimpleBooleanProperty(this, "toggleEnabled", _toggleEnabled);
        }
        return toggleEnabled;
    }

    public final Color getColor() {
        return null == color ? _color : color.get();
    }
    public final void setColor(final Color COLOR) {
        if (null == color) {
            _color = COLOR;
        } else {
            color.set(COLOR);
        }
    }
    public final ObjectProperty<Color> colorProperty() {
        if (null == color) {
            color = new SimpleObjectProperty<>(this, "ledColor", _color);
        }
        return color;
    }

    @Override public boolean isResizable() {
        return true;
    }

    private void toggle() {
        if (Status.DESELECTED == getStatus()) {
            setStatus(Status.SELECTED);
        } else if (isToggleEnabled() && Status.SELECTED == getStatus()) {
            setStatus(Status.DESELECTED);
        }
    }


    // ******************** Event handling ************************************
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

    public final ObjectProperty<EventHandler<SelectionEvent>> onEmptyProperty() { return onEmpty; }
    public final void setOnEmpty(EventHandler<SelectionEvent> value) { onEmptyProperty().set(value); }
    public final EventHandler<SelectionEvent> getOnEmpty() { return onEmptyProperty().get(); }
    private ObjectProperty<EventHandler<SelectionEvent>> onEmpty = new ObjectPropertyBase<EventHandler<SelectionEvent>>() {
        @Override public Object getBean() { return this; }
        @Override public String getName() { return "onEmpty";}
    };

    public void fireSelectionEvent(final SelectionEvent EVENT) {
        final EventType TYPE    = EVENT.getEventType();
        final EventHandler<SelectionEvent> HANDLER;

        if (SelectionEvent.SELECTED == TYPE) {
            HANDLER = getOnSelect();
        } else if (SelectionEvent.DESELECTED == TYPE) {
            HANDLER = getOnDeselect();
        } else if (SelectionEvent.EMPTY == TYPE) {
            HANDLER = getOnEmpty();
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
    public static class SelectionEvent extends Event {
        public static final EventType<SelectionEvent> SELECTED   = new EventType(ANY, "selected");
        public static final EventType<SelectionEvent> DESELECTED = new EventType(ANY, "deselected");
        public static final EventType<SelectionEvent> EMPTY      = new EventType(ANY, "empty");

        private Status status;


        // ******************** Constructors **********************************
        public SelectionEvent(final Status STATUS, final Object SOURCE, final EventTarget TARGET, final EventType<SelectionEvent> EVENT_TYPE) {
            super(SOURCE, TARGET, EVENT_TYPE);
            status = STATUS;
        }


        // ******************** Methods ***************************************
        public final Status getStatus() {
            return status;
        }
    }
}

