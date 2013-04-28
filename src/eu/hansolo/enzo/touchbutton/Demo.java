package eu.hansolo.enzo.touchbutton;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Demo extends Application {
    @Override public void start(Stage stage) {
        TouchButton control = TouchButtonBuilder.create()
                                                .status(TouchButton.Status.UNSELECTED)
                                                .color(Color.CYAN)
                                                .prefWidth(380)
                                                .prefHeight(390)
                                                .build();

        control.setOnSelect(selectionEvent -> { System.out.println("Select"); });
        control.setOnUnselect(selectionEvent -> { System.out.println("Unselect"); });

        StackPane pane = new StackPane();
        pane.getChildren().setAll(control);

        Scene scene = new Scene(pane, 380, 390, Color.LIGHTGRAY);

        stage.setTitle("JavaFX Custom Control");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}


