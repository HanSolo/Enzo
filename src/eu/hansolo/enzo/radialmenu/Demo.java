/*
 * Copyright (c) 2013, Gerrit Grunwald
 * All right reserved
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * The names of its contributors may not be used to endorse or promote
 * products derived from this software without specific prior written
 * permission.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
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
