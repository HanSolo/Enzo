package eu.hansolo.enzo.lcd;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;


/**
 * Created by
 * User: hansolo
 * Date: 03.07.13
 * Time: 08:58
 */
public class LcdEvent extends Event {
    public static final EventType<LcdEvent> THRESHOLD_EXCEEDED = new EventType(ANY, "thresholdExceeded");
    public static final EventType<LcdEvent> THRESHOLD_UNDERRUN = new EventType(ANY, "thresholdUnderrun");


    // ******************** Constructors **************************************
    public LcdEvent(final Object SOURCE, final EventTarget TARGET, EventType<LcdEvent> TYPE) {
        super(SOURCE, TARGET, TYPE);
    }
}