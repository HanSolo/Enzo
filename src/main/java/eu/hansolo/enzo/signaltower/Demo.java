package eu.hansolo.enzo.signaltower;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Demo extends Application {
    private SignalTower    signalTower;
    private long           lastTimerCall;
    private AnimationTimer timer;
    private int            count;

    @Override public void init() {
        signalTower   = SignalTowerBuilder.create().build();
        count         = 0;
        lastTimerCall = System.nanoTime();
        timer = new AnimationTimer() {
            @Override public void handle(long now) {
                if (now > lastTimerCall + 2_000_000_000l) {
                    if (count == 3) count = 0;
                    switch(count) {
                        case 0:
                            signalTower.setColors(true, false, false);
                            break;
                        case 1:
                            signalTower.setColors(false, true, false);
                            break;
                        case 2:
                            signalTower.setColors(false, false, true);
                            break;
                    }
                    count++;
                    lastTimerCall = now;
                }
            }
        };
    }

    @Override public void start(Stage stage) {
        StackPane pane = new StackPane();
        pane.setPadding(new Insets(5, 5, 5, 5));
        pane.setBackground(new Background(new BackgroundFill(Color.rgb(70, 70, 70), CornerRadii.EMPTY, Insets.EMPTY)));
        pane.getChildren().setAll(signalTower);

        Scene scene = new Scene(pane);

        stage.setTitle("SignalTower");
        stage.setScene(scene);
        stage.show();

        timer.start();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}


