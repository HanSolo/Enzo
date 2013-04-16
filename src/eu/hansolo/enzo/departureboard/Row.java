package eu.hansolo.enzo.departureboard;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;


/**
 * Created by
 * User: hansolo
 * Date: 15.04.13
 * Time: 08:13
 */
public class Row extends HBox {
    private LedSegment       leds;
    private TimeSegment      time;
    private WordSegment      destination;
    private CharacterSegment flightNo;
    private CharacterSegment gate;
    private String[]         destinations;
    private boolean          isEmpty;


    // ******************** Constructors **************************************
    public Row() {
        this(false, "  ", "  ", "", "", "", new String[]{""});
    }
    public Row(final String[] DESTINATIONS) {
        this(false, "  ", "  ", "", "", "", DESTINATIONS);
    }
    public Row(final String HOURS, final String MINUTES, final String DESTINATION, final String FLIGHT_NO, final String GATE) {
        this(false, HOURS, MINUTES, DESTINATION, FLIGHT_NO, GATE, new String[]{"0", "1", "2"});
    }
    public Row(final String HOURS, final String MINUTES, final String DESTINATION, final String FLIGHT_NO, final String GATE, final String[] DESTINATIONS) {
        this(false, HOURS, MINUTES, DESTINATION, FLIGHT_NO, GATE, DESTINATIONS);
    }
    public Row(final boolean BLINKING, final String HOURS, final String MINUTES, final String DESTINATION, final String FLIGHT_NO, final String GATE, final String[] DESTINATIONS) {
        leds        = new LedSegment();
        time        = new TimeSegment();
        destination = new WordSegment(14, DESTINATIONS);
        flightNo    = new CharacterSegment(6, Color.rgb(255, 240, 100));
        gate        = new CharacterSegment(3);
        isEmpty     = DESTINATION.isEmpty() ? true : false;
        leds.setBlinking(BLINKING);
        time.setTime(HOURS, MINUTES);
        destination.setText(DESTINATION);
        flightNo.setText(FLIGHT_NO);
        gate.setText(GATE);

        setSpacing(11);
        setAlignment(Pos.CENTER_LEFT);
        setCenterShape(true);
        getChildren().addAll(leds,
                             flightNo,
                             destination,
                             time,
                             gate);
    }


    // ******************** Methods *******************************************
    public final String getRowString() {
        //return time.getTime() + " " + flightNo.getText();
        return time.getTime() + " " + destination.getText() + " " + flightNo.getText();
    }

    public final void setRow(final Row ROW) {
        setRow(ROW.isBlinking(), ROW.getHours(), ROW.getMinutes(), ROW.getDestination(), ROW.getFlightNo(), ROW.getGate());
    }
    public final void setRow(final String HOURS, final String MINUTES, final String DESTINATION, final String FLIGHT_NO, final String GATE) {
        setRow(false, HOURS, MINUTES, DESTINATION, FLIGHT_NO, GATE);
    }
    public final void setRow(final boolean BLINKING, final String HOURS, final String MINUTES, final String DESTINATION, final String FLIGHT_NO, final String GATE) {
        leds.setBlinking(BLINKING);
        time.setTime(HOURS, MINUTES);
        destination.setText(DESTINATION);
        flightNo.setText(FLIGHT_NO);
        gate.setText(GATE);
        isEmpty = DESTINATION.isEmpty() ? true : false;
    }

    public final boolean isBlinking() {
        return leds.isBlinking();
    }
    public final void setBlinking(final boolean BLINKING) {
        leds.setBlinking(BLINKING);
    }

    public final String getTime() {
        return time.getTime();
    }
    public final void setTime(final String HOURS, final String MINUTES) {
        time.setTime(HOURS, MINUTES);
    }

    public final String getHours() {
        return time.getHours();
    }
    public final void setHours(final String HOURS) {
        time.setHours(HOURS);
    }

    public final String getMinutes() {
        return time.getMinutes();
    }
    public final void setMinutes(final String MINUTES) {
        time.setMinutes(MINUTES);
    }

    public final String getDestination() {
        return destination.getText();
    }
    public final void setDestination(final String DESTINATION) {
        destination.setText(DESTINATION);
    }

    public final String getFlightNo() {
        return flightNo.getText();
    }
    public final void setFlightNo(final String FLIGHT_NO) {
        flightNo.setText(FLIGHT_NO);
    }

    public final String getGate() {
        return gate.getText();
    }
    public final void setGate(final String GATE) {
        gate.setText(GATE);
    }

    public final String[] getDestinations() {
        return destinations;
    }
    public final void setDestinations(final String[] DESTINATIONS) {
        destinations = DESTINATIONS;

    }

    public final void setLedColor(final Color COLOR) {
        leds.setColor(COLOR);
    }

    public final void reset() {
        setRow(false, "", "", "", "", "");
        isEmpty = true;
        setBlinking(false);
    }

    public final boolean isEmpty() {
        return isEmpty;
    }

    public final void toggleLeds() {
        leds.toggle();
    }

    public final void setLedsOff() {
        leds.reset();
    }

    public String toString() {
        return getRowString();
    }
}

