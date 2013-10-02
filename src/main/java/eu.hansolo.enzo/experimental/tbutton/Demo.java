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

package eu.hansolo.enzo.experimental.tbutton;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Demo extends Application {
    private TButton control;

    @Override public void init() {
        control = TButtonBuilder.create()
                                .prefWidth(144)
                                .prefHeight(144)
                                .text("Pi 1")
                                .build();
        control.setOnSelect(new EventHandler<TButton.SelectEvent>() {
            @Override public void handle(TButton.SelectEvent selectEvent) {
                System.out.println("Button selected");
            }
        });
        control.setOnDeselect(new EventHandler<TButton.SelectEvent>() {
            @Override public void handle(TButton.SelectEvent selectEvent) {
                System.out.println("Button deselected");
            }
        });
    }

    @Override public void start(Stage stage) {
        StackPane pane = new StackPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.getChildren().setAll(control);

        Scene scene = new Scene(pane, 200, 200);

        stage.setTitle("JavaFX TButton");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}


