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

package eu.hansolo.enzo.experimental.pbutton;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Demo extends Application {
    @Override public void start(Stage stage) {
        PushButton control = PushButtonBuilder.create()
                                              .prefWidth(81)
                                              .prefHeight(43)
                                              .build();

        ToggleButton button = new ToggleButton("Push");
        button.setPrefSize(81, 43);
        button.getStylesheets().add(getClass().getResource("demo.css").toExternalForm());

        VBox pane = new VBox();
        pane.setSpacing(5);
        pane.getChildren().setAll(control, button);

        Scene scene = new Scene(pane);
        scene.setFill(Color.rgb(208,69,28));

        stage.setTitle("JavaFX Custom Control");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}


