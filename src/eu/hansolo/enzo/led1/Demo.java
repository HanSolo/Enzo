package eu.hansolo.enzo.led1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;


/**
 * Created by
 * User: hansolo
 * Date: 11.02.13
 * Time: 16:28
 */
public class Demo extends Application {
    private static final Random RND = new Random();

    @Override public void init() {
    }

    @Override public void start(Stage stage) {
        GridPane pane = new GridPane();
        for (int y = 0 ; y < 20 ; y++) {
            for (int x = 0 ; x < 40 ; x++) {
                pane.add(LedBuilder.create()
                                    .color(Led.LedColor.values()[RND.nextInt(Led.LedColor.values().length)])
                                    .frameVisible(false)
                                    .interval(50_000_000l)
                                    .blinking(true)
                                    .build(), x, y);
            }
        }

        Scene scene = new Scene(pane, Color.rgb(50, 50, 50));

        stage.setTitle("Led demo");
        stage.setScene(scene);
        stage.show();
    }

    @Override public void stop() {

    }

    public static void main(String[] args) {
        launch(args);
    }
}


