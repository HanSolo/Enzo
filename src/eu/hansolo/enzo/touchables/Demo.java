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

package eu.hansolo.enzo.touchables;

import eu.hansolo.enzo.tools.Util;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Demo extends Application {
    private Image backgroundImage;

    @Override public void init() {
        backgroundImage = Util.createGrayNoise(256, 256, Color.rgb(145, 145, 145), Color.rgb(160, 160, 160));
    }

    @Override public void start(Stage stage) {
        ImageView imgView = new ImageView(backgroundImage);

        PushButton button1 = PushButtonBuilder.create()
                                              .status(PushButton.Status.DESELECTED)
                                              .color(Color.CYAN)
                                              .prefWidth(128)
                                              .prefHeight(128)
                                              .build();

        button1.setOnSelect(selectionEvent -> {
            System.out.println("Select");
        });
        button1.setOnDeselect(selectionEvent -> {
            System.out.println("Deselect");
        });

        StackPane pane = new StackPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.getChildren().setAll(imgView, button1);

        Scene scene = new Scene(pane, 256, 256, Color.rgb(153, 153, 153));

        stage.setTitle("JavaFX PushButton");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}


