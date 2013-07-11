package eu.hansolo.enzo.lcdclock;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * Created by
 * User: hansolo
 * Date: 11.07.13
 * Time: 06:16
 */

public class Demo extends Application {
    private LcdClock clock;

    @Override public void init() {
        clock = LcdClockBuilder.create()
                               //.color(Color.CYAN)
                               //.hourColor(Color.LIME)
                               //.minuteColor(Color.AQUA)
                               //.secondColor(Color.MAGENTA)
                               //.textColor(Color.DARKOLIVEGREEN)
                               .build();
    }

    @Override public void start(Stage stage) {
        StackPane pane = new StackPane();
        pane.setPadding(new Insets(5, 5, 5, 5));
        pane.getChildren().addAll(clock);

        Scene scene = new Scene(pane);
        //scene.setFullScreen(true);

        stage.setTitle("LcdClock");
        stage.setScene(scene);
        stage.show();
    }

    @Override public void stop() {

    }

    public static void main(final String[] args) {
        Application.launch(args);
    }
}