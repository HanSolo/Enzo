/*
 * Copyright (c) 2013 by Gerrit Grunwald
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.langhammer.enzo2.canvasled;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * Created by
 * User: hansolo
 * Date: 31.08.13
 * Time: 08:37
 */
public class Demo1 extends Application {
    private Led            led;
    private boolean        toggle;
    private long           lastTimerCall;
    private AnimationTimer timer;


    @Override public void init() {
        led                = LedBuilder.create()
                                       .ledColor(Color.MAGENTA)
                                       .prefWidth(64)
                                       .prefHeight(64)
                                       .build();
        toggle             = false;
        lastTimerCall      = System.nanoTime();
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

        led.setStyle("-led-color: lime;");

        Scene scene = new Scene(pane, 128, 128);

        stage.setScene(scene);
        stage.show();

        timer.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
