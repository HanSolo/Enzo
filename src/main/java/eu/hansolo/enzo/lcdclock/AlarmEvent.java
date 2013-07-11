package eu.hansolo.enzo.lcdclock;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;


/**
 * Created by
 * User: hansolo
 * Date: 11.07.13
 * Time: 20:42
 */
public class AlarmEvent extends Event {
    public static final EventType<AlarmEvent> ALARM = new EventType(ANY, "ALARM");


    // ******************** Constructors **************************************
    public AlarmEvent(final Object SOURCE, final EventTarget TARGET, EventType<AlarmEvent> TYPE) {
        super(SOURCE, TARGET, TYPE);
    }
}
