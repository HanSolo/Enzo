package eu.hansolo.enzo.touchbutton;

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


public class TouchButton extends Control {
    public static enum Status {
        OFF,
        UNSELECTED,
        SELECTED
    }
    private Status                 _status;
    private ObjectProperty<Status> status;
    private boolean                _toggleEnabled;
    private BooleanProperty        toggleEnabled;
    private Color                  _color;
    private ObjectProperty<Color>  color;

    // ******************** Constructors **************************************
    public TouchButton() {
        getStyleClass().add("touch-button");
        _status        = Status.OFF;
        _toggleEnabled = true;
        _color         = Color.RED;
        registerListeners();
    }

    private void registerListeners() {
        setOnMousePressed(mouseEvent -> { toggle(); });
        setOnTouchPressed(touchEvent -> { toggle(); });
    }


    // ******************** Methods *******************************************
    public final Status getStatus() {
        return null == status ? _status : status.get();
    }
    public final void setStatus(final Status STATUS) {
        if (null == status) {
            _status = STATUS;
        } else {
            status.set(STATUS);
        }
        if (Status.UNSELECTED == STATUS) {
            fireSelectionEvent(new SelectionEvent(getStatus(), this, null, SelectionEvent.UNSELECTED));
        } else if (Status.SELECTED == STATUS) {
            fireSelectionEvent(new SelectionEvent(getStatus(), this, null, SelectionEvent.SELECTED));
        } else if (Status.OFF == STATUS) {
            fireSelectionEvent(new SelectionEvent(getStatus(), this, null, SelectionEvent.OFF));
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
            color = new SimpleObjectProperty<>(this, "color", _color);
        }
        return color;
    }

    @Override public boolean isResizable() {
        return true;
    }

    private void toggle() {
        if (Status.UNSELECTED == getStatus()) {
            setStatus(Status.SELECTED);
        } else if (isToggleEnabled() && Status.SELECTED == getStatus()) {
            setStatus(Status.UNSELECTED);
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

    public final ObjectProperty<EventHandler<SelectionEvent>> onUnselectProperty() { return onUnselect; }
    public final void setOnUnselect(EventHandler<SelectionEvent> value) { onUnselectProperty().set(value); }
    public final EventHandler<SelectionEvent> getOnUnselect() { return onUnselectProperty().get(); }
    private ObjectProperty<EventHandler<SelectionEvent>> onUnselect = new ObjectPropertyBase<EventHandler<SelectionEvent>>() {
        @Override public Object getBean() { return this; }
        @Override public String getName() { return "onUnselect";}
    };

    public final ObjectProperty<EventHandler<SelectionEvent>> onOffProperty() { return onOff; }
    public final void setOnOff(EventHandler<SelectionEvent> value) { onOffProperty().set(value); }
    public final EventHandler<SelectionEvent> getOnOff() { return onOffProperty().get(); }
    private ObjectProperty<EventHandler<SelectionEvent>> onOff = new ObjectPropertyBase<EventHandler<SelectionEvent>>() {
        @Override public Object getBean() { return this; }
        @Override public String getName() { return "onOff";}
    };

    public void fireSelectionEvent(final SelectionEvent EVENT) {
        final EventType                    TYPE    = EVENT.getEventType();
        final EventHandler<SelectionEvent> HANDLER;

        if (SelectionEvent.SELECTED == TYPE) {
            HANDLER = getOnSelect();
        } else if (SelectionEvent.UNSELECTED == TYPE) {
            HANDLER = getOnUnselect();
        } else if (SelectionEvent.OFF == TYPE) {
            HANDLER = getOnOff();
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
        public static final EventType<SelectionEvent> UNSELECTED = new EventType(ANY, "unselected");
        public static final EventType<SelectionEvent> OFF        = new EventType(ANY, "off");

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
