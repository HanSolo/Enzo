package eu.hansolo.enzo.qlocktwo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Demo extends Application {
    @Override public void start(Stage stage) {
        QlockTwo control = QlockTwoBuilder.create()
            .prefWidth(500)
            .prefHeight(500)
            .build();

        StackPane pane = new StackPane();
        pane.getChildren().setAll(control);

        Scene scene = new Scene(pane, 500, 500, Color.DARKGRAY);

        stage.setTitle("JavaFX QlockTwo");
        stage.setScene(scene);
        stage.show();

        control.setLanguage(QlockTwo.Language.GERMAN);
        control.setColor(QlockTwo.QlockColor.BLUE_CANDY);
    }

    @Override public void stop() {
        Platform.exit();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}


