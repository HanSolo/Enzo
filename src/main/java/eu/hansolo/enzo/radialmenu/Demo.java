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

package eu.hansolo.enzo.radialmenu;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * Created with IntelliJ IDEA.
 * User: hansolo
 * Date: 21.09.12
 * Time: 16:18
 * To change this template use File | Settings | File Templates.
 */
public class Demo extends Application {

    @Override public void start(Stage stage) {
        final RadialMenu radialMenu = RadialMenuBuilder.create()
                                                       .options(OptionsBuilder.create()
                                                                              .degrees(360)
                                                                              .offset(-90)
                                                                              .radius(200)
                                                                              .buttonSize(72)
                                                                              .buttonAlpha(1.0)
                                                                              .build())
                                                       .items(
                                                           MenuItemBuilder.create().thumbnailImageName(getClass().getResource("star.png").toExternalForm()).size(64).build(),
                                                           MenuItemBuilder.create().symbol(Symbol.Type.LOCATION).tooltip("Location").size(64).build(),
                                                           MenuItemBuilder.create().symbol(Symbol.Type.MUSIC).tooltip("Music").size(64).build(),
                                                           MenuItemBuilder.create().symbol(Symbol.Type.SPEECH_BUBBLE).tooltip("Chat").size(64).build(),
                                                           MenuItemBuilder.create().symbol(Symbol.Type.BLUE_TOOTH).tooltip("Bluetooth").size(64).build(),
                                                           MenuItemBuilder.create().symbol(Symbol.Type.BULB).tooltip("Ideas").size(64).build(),
                                                           MenuItemBuilder.create().symbol(Symbol.Type.HEAD_PHONES).tooltip("Sound").size(64).build(),
                                                           MenuItemBuilder.create().symbol(Symbol.Type.TWITTER).tooltip("Twitter").size(64).build(),
                                                           MenuItemBuilder.create().symbol(Symbol.Type.TAGS).tooltip("Tags").size(64).build(),
                                                           MenuItemBuilder.create().symbol(Symbol.Type.CART).tooltip("Shop").size(64).build(),
                                                           MenuItemBuilder.create().symbol(Symbol.Type.ALARM).tooltip("Alarm").size(64).build(),
                                                           MenuItemBuilder.create().symbol(Symbol.Type.CLOCK).tooltip("Clock").size(64).build())
                                                       .build();
        radialMenu.setPrefSize(500, 500);
        radialMenu.setOnItemSelected(new EventHandler<RadialMenu.ItemEvent>() {
            @Override public void handle(RadialMenu.ItemEvent selectionEvent) {
                System.out.println("item " + selectionEvent.getItem().getTooltip() + " selected");
            }
        });
        radialMenu.setOnMenuOpenStarted(new EventHandler<RadialMenu.MenuEvent>() {
            @Override public void handle(RadialMenu.MenuEvent menuEvent) {
                System.out.println("Menu starts to open");
            }
        });
        radialMenu.setOnMenuOpenFinished(new EventHandler<RadialMenu.MenuEvent>() {
            @Override public void handle(RadialMenu.MenuEvent menuEvent) {
                System.out.println("Menu finished to open");
            }
        });
        radialMenu.setOnMenuCloseStarted(new EventHandler<RadialMenu.MenuEvent>() {
            @Override public void handle(RadialMenu.MenuEvent menuEvent) {
                System.out.println("Menu starts to close");
            }
        });
        radialMenu.setOnMenuCloseFinished(new EventHandler<RadialMenu.MenuEvent>() {
            @Override public void handle(RadialMenu.MenuEvent menuEvent) {
                System.out.println("Menu finished to close");
            }
        });


        HBox buttons = new HBox();
        buttons.setSpacing(10);
        buttons.setPadding(new Insets(10, 10, 10, 10));
        Button buttonShow = new Button("Show menu");
        buttonShow.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent actionEvent) {
                radialMenu.show();
            }
        });
        buttons.getChildren().add(buttonShow);

        Button buttonHide = new Button("Hide menu");
        buttonHide.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent actionEvent) {
                radialMenu.hide();
            }
        });
        buttons.getChildren().add(buttonHide);

        VBox pane = new VBox();
        pane.getChildren().add(radialMenu);
        pane.getChildren().add(buttons);

        Scene scene = new Scene(pane, Color.GRAY);      //, Color.rgb(159, 143, 110));

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
