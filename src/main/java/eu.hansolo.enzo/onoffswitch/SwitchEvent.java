package eu.hansolo.enzo.onoffswitch;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;


/**
 * User: hansolo
 * Date: 10.10.13
 * Time: 09:48
 */
public class SwitchEvent extends Event {
    public static final EventType<SwitchEvent> ON  = new EventType(ANY, "on");
    public static final EventType<SwitchEvent> OFF = new EventType(ANY, "off");


    // ******************** Constructors **********************************
    public SwitchEvent(final Object SOURCE, final EventTarget TARGET, final EventType<SwitchEvent> EVENT_TYPE) {
        super(SOURCE, TARGET, EVENT_TYPE);
    }
}
