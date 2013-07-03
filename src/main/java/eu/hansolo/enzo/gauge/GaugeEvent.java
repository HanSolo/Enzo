package eu.hansolo.enzo.gauge;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;


/**
 * Created by
 * User: hansolo
 * Date: 03.07.13
 * Time: 08:58
 */
public class GaugeEvent extends Event {
    public static final EventType<GaugeEvent> THRESHOLD_EXCEEDED = new EventType(ANY, "thresholdExceeded");
    public static final EventType<GaugeEvent> THRESHOLD_UNDERRUN = new EventType(ANY, "thresholdUnderrun");


    // ******************** Constructors **************************************
    public GaugeEvent(final Object SOURCE, final EventTarget TARGET, EventType<GaugeEvent> TYPE) {
        super(SOURCE, TARGET, TYPE);
    }
}
