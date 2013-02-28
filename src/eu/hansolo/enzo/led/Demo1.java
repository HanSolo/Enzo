package eu.hansolo.enzo.led;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * Created by
 * User: hansolo
 * Date: 28.02.13
 * Time: 16:34
 */
public class Demo1 extends Application {
    private Led            led;
    private boolean        toggle;
    private long           lastTimerCall;
    private AnimationTimer timer;


    @Override public void init() {
        led           = LedBuilder.create()
                                  .type(Led.Type.ROUND)
                                  .prefWidth(64)
                                  .prefHeight(64)
                                  .build();
        toggle        = false;
        lastTimerCall = System.nanoTime();
        timer = new AnimationTimer() {
            @Override public void handle(long now) {
                if (now > lastTimerCall + 500_000_000l) {
                    toggle ^= true;
                    led.setOn(toggle);
                    lastTimerCall = now;
                }
            }
        };
    }

    @Override public void start(Stage stage) {
        StackPane pane = new StackPane();
        pane.getChildren().add(led);

        Scene scene = new Scene(pane);

        stage.setScene(scene);
        stage.show();

        timer.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
