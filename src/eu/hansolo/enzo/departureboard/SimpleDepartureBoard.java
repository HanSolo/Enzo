package eu.hansolo.enzo.departureboard;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * Created by
 * User: hansolo
 * Date: 17.04.13
 * Time: 14:54
 */
public class SimpleDepartureBoard extends VBox {
    private static final Calendar CAL = Calendar.getInstance();
    private List<SimpleRow>       rows;
    private AnimationTimer        timer;
    private long                  lastLedCall;


    // ******************** Constructors **************************************
    public SimpleDepartureBoard() {
        rows        = new ArrayList<>(5);
        lastLedCall = 750_000_000l;
        for (int i = 0 ; i < 5 ; i++) {
            rows.add(new SimpleRow());
        }
        setSpacing(5);
        for (SimpleRow row : rows) {
            getChildren().add(row);
        }
        timer = new AnimationTimer() {
            @Override public void handle(final long NOW) {
                if (NOW > lastLedCall + 750_000_000l) {
                    // set led's to blinking if needed
                    final int HH       = CAL.get(Calendar.HOUR_OF_DAY);
                    final int MM       = CAL.get(Calendar.MINUTE);
                    for (SimpleRow row : rows) {
                        if (!row.isEmpty()) {
                            int hour = Integer.parseInt(row.getHours());
                            int min  = Integer.parseInt(row.getMinutes());
                            if (HH == hour) {
                                if (MM >= min || MM + 10 >= min) {
                                    row.toggleLeds();
                                } else if (HH + 1 == hour && (MM + 10) % 60 >= min) {
                                    row.toggleLeds();
                                } else {
                                    row.setLedsOff();
                                }
                            }
                        }
                    }
                    lastLedCall = NOW;
                }
            }
        };
    }


    // ******************** Methods *******************************************
    public final void setRow(int index, final String HOURS, final String MINUTES, final String DESTINATION, final String TRACK) {
        if (index >= 0 && index < rows.size()) {
            rows.get(index).setRow(HOURS, MINUTES, DESTINATION, TRACK);
        }
    }
    public final void setRowBlink(int index, boolean blink) {
        if (index >= 0 && index < rows.size()) {
            rows.get(index).setBlinking(blink);
        }
    }
    public final void resetRow(int index) {
        setRow(index, "", "", "", "");
    }

    public final void start() {
        timer.start();
    }
    public final void stop() {
        timer.stop();
    }
}
