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

package eu.hansolo.enzo.onoffswitch;/**
 * User: hansolo
 * Date: 08.10.13
 * Time: 08:47
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Demo extends Application {
    private OnOffSwitch onOffSwitch;

    @Override public void init() {
        onOffSwitch = new OnOffSwitch();

        onOffSwitch.setOnSwitchedOn(switchEvent -> System.out.println("switched on"));
    }

    @Override public void start(Stage stage) {
        StackPane pane = new StackPane();
        pane.getChildren().add(onOffSwitch);

        Scene scene = new Scene(pane, 200, 100, Color.BLACK);

        stage.setTitle("OnOffSwitch");
        stage.setScene(scene);
        stage.show();
    }

    @Override public void stop() {

    }

    public static void main(String[] args) {
        launch(args);
    }
}
