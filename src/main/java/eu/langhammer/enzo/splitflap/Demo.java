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

package eu.langhammer.enzo.splitflap;

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
                                  .flipTime(1000)
                                  //.flapColor(Color.rgb(0, 0, 150))
                                  //.textColor(Color.WHITESMOKE)
                                  //.withFixture(false)
                                  //.darkFixture(false)
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
