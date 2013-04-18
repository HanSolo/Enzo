package eu.hansolo.enzo.departureboard;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

import java.util.Random;


/**
 * Created by
 * User: hansolo
 * Date: 17.04.13
 * Time: 15:16
 */
public class SimpleDemo extends Application {
    private static final Random      RND = new Random();
    private final SimpleRow[] ROWS = {
        new SimpleRow("12", "20", "ST. GALLEN", "01"),
        new SimpleRow("05", "31", "INTERLAKEN OST", "02"),
        new SimpleRow("08", "15", "KOELN", "03"),
        new SimpleRow("19", "20", "MUENCHEN", "04"),
        new SimpleRow("17", "05", "BERN", "05"),
        new SimpleRow("15", "00", "GENF", "06"),
        new SimpleRow("07", "30", "STUTTGART", "07"),
        new SimpleRow("16", "44", "FREIBURG", "08"),
        new SimpleRow("22", "10", "MUENSTER", "09"),
        new SimpleRow("13", "17", "HAMBURG", "10")
    };

    private SimpleDepartureBoard departureBoard;
    private long                 lastTimerCall;
    private AnimationTimer       randomTimer;

    @Override public void init() {
        departureBoard = new SimpleDepartureBoard();
        lastTimerCall = System.nanoTime();
        randomTimer = new AnimationTimer() {
            @Override public void handle(long now) {
                if (now > lastTimerCall + 7_000_000_000l) {
                    int rnd = RND.nextInt(10);
                    departureBoard.setRow(RND.nextInt(5), ROWS[rnd].getHours(), ROWS[rnd].getMinutes(), ROWS[rnd].getDestination(), ROWS[rnd].getTrack());
                    departureBoard.setRowBlink(RND.nextInt(5), RND.nextBoolean());
                    lastTimerCall = now;
                }
            }
        };
    }

    @Override public void start(Stage stage) throws Exception {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));

        grid.add(new SimpleHeaderRow(), 0, 0);

        grid.add(departureBoard, 0, 1);
        GridPane.setHalignment(departureBoard, HPos.CENTER);

        LinearGradient gradient = new LinearGradient(0, 0, 0, 600, false, CycleMethod.NO_CYCLE,
                                                     new Stop(0.0, Color.rgb(28, 27, 22)),
                                                     new Stop(0.25, Color.rgb(38, 37, 32)),
                                                     new Stop(1.0, Color.rgb(28, 27, 22)));
        final Scene scene = new Scene(grid, gradient);
        scene.setCamera(new PerspectiveCamera());

        stage.setTitle("DepartureBoard");
        stage.setScene(scene);
        stage.show();
        departureBoard.start();
        randomTimer.start();
    }

    private void initialSetup() {
        departureBoard.setRow(0, "12", "20", "ST. GALLEN", "07");
        departureBoard.setRow(1, "12", "29", "INTERLAKEN OST", "");
        departureBoard.setRow(2, "12", "30", "BASEL SBB", "");
        departureBoard.setRow(3, "12", "32", "BASEL SBB", "07");
        departureBoard.setRow(4, "12", "40", "GENF-FLUGHAFEN", "08");
    }

    public static void main(final String[] args) {
        Application.launch(args);
    }
}