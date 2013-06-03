package eu.hansolo.enzo.experimental.tbutton;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Demo extends Application {
    private TButton control;

    @Override public void init() {
        control = TButtonBuilder.create()
                                .prefWidth(144)
                                .prefHeight(144)
                                .touchable(false)
                                .text("Pi 1")
                                .build();
        control.setOnSelect(new EventHandler<TButton.SelectEvent>() {
            @Override public void handle(TButton.SelectEvent selectEvent) {
                System.out.println("Button selected");
            }
        });
        control.setOnDeselect(new EventHandler<TButton.SelectEvent>() {
            @Override public void handle(TButton.SelectEvent selectEvent) {
                System.out.println("Button deselected");
            }
        });
    }

    @Override public void start(Stage stage) {
        StackPane pane = new StackPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.getChildren().setAll(control);

        Scene scene = new Scene(pane, 200, 200);

        stage.setTitle("JavaFX TButton");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}


