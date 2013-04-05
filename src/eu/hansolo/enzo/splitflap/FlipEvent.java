package eu.hansolo.enzo.splitflap;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;


/**
 * Created by
 * User: hansolo
 * Date: 05.02.13
 * Time: 09:30
 */
public class FlipEvent extends Event {
    public static final EventType<FlipEvent> FLIP_FORWARD  = new EventType(ANY, "FLIP_FORWARD");
    public static final EventType<FlipEvent> FLIP_BACKWARD = new EventType(ANY, "FLIP_BACKWARD");
    public static final EventType<FlipEvent> FLIP_FINISHED = new EventType(ANY, "FLIP_FINISHED");


    // ******************** Constructors **************************************
    public FlipEvent(final Object SOURCE, final EventTarget TARGET, EventType<FlipEvent> TYPE) {
        super(SOURCE, TARGET, TYPE);
    }
}