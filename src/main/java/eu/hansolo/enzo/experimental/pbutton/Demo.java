package eu.hansolo.enzo.experimental.pbutton;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Demo extends Application {
    @Override public void start(Stage stage) {
        PushButton control = PushButtonBuilder.create()
                                              .prefWidth(81)
                                              .prefHeight(43)
                                              .build();

        ToggleButton button = new ToggleButton("Push");
        button.setPrefSize(81, 43);
        button.getStylesheets().add(getClass().getResource("demo.css").toExternalForm());

        VBox pane = new VBox();
        pane.setSpacing(5);
        pane.getChildren().setAll(control, button);

        Scene scene = new Scene(pane);
        scene.setFill(Color.rgb(208,69,28));

        stage.setTitle("JavaFX Custom Control");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}


