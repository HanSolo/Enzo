/*
 * Copyright (c) 2013. Gerrit Grunwald
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package eu.hansolo.enzo.led;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
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
        Pane pane = new Pane();
        pane.getChildren().add(led);

        Scene scene = new Scene(pane, 128, 128);

        stage.setScene(scene);
        stage.show();

        timer.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
