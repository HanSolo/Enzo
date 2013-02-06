package eu.hansolo.enzo.splitflap;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * Created by
 * User: hansolo
 * Date: 06.02.13
 * Time: 20:26
 */
public class Demo extends Application {
    private SplitFlap control;


    @Override public void init() {
        control = SplitFlapBuilder.create()
                                  .flipTime(100)
                                  .build();
    }

    @Override public void start(Stage stage) {
        StackPane pane = new StackPane();
        pane.getChildren().setAll(control);

        Scene scene = new Scene(pane, 400, 400, Color.DARKGRAY);

        stage.setScene(scene);
        stage.show();

        control.setText("X");
    }

    @Override public void stop() {

    }

    public static void main(String[] args) {
        launch(args);
    }
}