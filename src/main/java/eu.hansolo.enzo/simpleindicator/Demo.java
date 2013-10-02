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

package eu.hansolo.enzo.simpleindicator;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * Created by
 * User: hansolo
 * Date: 19.11.12
 * Time: 03:33
 */
public class Demo extends Application {
    private SimpleIndicator control;
    private boolean         toggle;
    private long            lastTimerCall;
    private AnimationTimer  timer;


    @Override public void init() {
        control       = SimpleIndicatorBuilder.create().build();
        toggle        = false;
        lastTimerCall = System.nanoTime();
        timer = new AnimationTimer() {
            @Override public void handle(final long NOW) {
                if (NOW > lastTimerCall + 1_000_000_000l) {
                    toggle ^= true;
                    if (toggle) {
                        //control.getStyleClass().setAll("indicator", SimpleIndicator.STYLE_CLASS_PURPLE);
                        control.setIndicatorStyle(SimpleIndicator.IndicatorStyle.PURPLE);
                    } else {
                        //control.getStyleClass().setAll("indicator", SimpleIndicator.STYLE_CLASS_MAGENTA);
                        control.setIndicatorStyle(SimpleIndicator.IndicatorStyle.MAGENTA);
                    }
                    lastTimerCall = NOW;
                }
            }
        };
    }

    @Override public void start(Stage stage) {
        StackPane pane = new StackPane();
        pane.getChildren().setAll(control);

        Scene scene = new Scene(pane, 400, 400, Color.DARKGRAY);

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
