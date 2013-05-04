package eu.hansolo.enzo.gauge;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Arrays;


/**
 * Created by
 * User: hansolo
 * Date: 29.04.13
 * Time: 10:15
 */
public class Test extends Application {

    @Override public void start(Stage stage) {

        ListView list = new ListView();
        list.getItems().setAll(Arrays.asList("test", "test", "test", "test", "test"));

        Pane pane = new Pane();
        pane.getChildren().add(list);

        Scene scene = new Scene(pane, 335, 125);
        scene.getStylesheets().add(getClass().getResource("test.css").toExternalForm());

        stage.setTitle("Animation");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
