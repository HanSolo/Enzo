package eu.hansolo.enzo.departureboard;

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


/**
 * Created by
 * User: hansolo
 * Date: 17.04.13
 * Time: 15:16
 */
public class SimpleDemo extends Application {
    private SimpleDepartureBoard departureBoard;


    @Override public void init() {
        departureBoard = new SimpleDepartureBoard();
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
        initialSetup();
        departureBoard.start();
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