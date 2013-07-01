package eu.hansolo.enzo.notification;/**
 * Created by
 * User: hansolo
 * Date: 01.07.13
 * Time: 07:10
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Demo extends Application {


    // ******************** Initialization ************************************
    @Override public void init() {

    }

    // ******************** Methods *******************************************


    // ******************** Application start *********************************
    @Override public void start(Stage stage) {
        StackPane pane = new StackPane();
        pane.getChildren().addAll();

        Scene scene = new Scene(pane);

        stage.setScene(scene);
        stage.show();
    }

    @Override public void stop() {

    }

    public static void main(String[] args) {
        launch(args);
    }
}
