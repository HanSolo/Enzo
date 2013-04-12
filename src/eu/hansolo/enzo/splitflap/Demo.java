package eu.hansolo.enzo.splitflap;

import javafx.animation.AnimationTimer;
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
    private SplitFlap      control;
    private AnimationTimer timer;
    private long           start;

    @Override public void init() {
        control = SplitFlapBuilder.create()
                                  .flipTime(100)
                                  .textColor(Color.YELLOW)
                                  .darkFixture(true)
                                  .build();
        start = System.nanoTime();
        timer = new AnimationTimer() {
            @Override public void handle(long now) {
                if (now > start + 2_000_000_000l) {
                    control.setText("X");
                }
            }
        };
    }

    @Override public void start(Stage stage) {
        StackPane pane = new StackPane();
        pane.getChildren().setAll(control);

        Scene scene = new Scene(pane, Color.DARKGRAY);

        stage.setScene(scene);
        stage.show();

        timer.start();
    }

    @Override public void stop() {

    }

    public static void main(String[] args) {
        launch(args);
    }
}