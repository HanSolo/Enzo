package eu.hansolo.enzo.onoffswitch;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;


/**
 * User: hansolo
 * Date: 10.10.13
 * Time: 09:48
 */
public class SelectionEvent extends Event {
    public static final EventType<SelectionEvent> SELECT   = new EventType(ANY, "select");
    public static final EventType<SelectionEvent> DESELECT = new EventType(ANY, "deselect");


    // ******************** Constructors **********************************
    public SelectionEvent(final Object SOURCE, final EventTarget TARGET, final EventType<SelectionEvent> EVENT_TYPE) {
        super(SOURCE, TARGET, EVENT_TYPE);
    }
}
