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

package eu.langhammer.enzo2.onoffswitch;/**
 * User: hansolo
 * Date: 08.10.13
 * Time: 08:47
 */

import eu.langhammer.enzo2.common.SymbolType;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Demo extends Application {
    private OnOffSwitch onOffSwitch;
    private IconSwitch  iconSwitchSymbol;
    private IconSwitch  iconSwitchText;
    private IconSwitch  iconSwitchSymbol1;

    @Override public void init() {
        onOffSwitch = new OnOffSwitch();

        ToggleGroup iconSwitchToggleGroup = new ToggleGroup();

        iconSwitchSymbol = new IconSwitch();
        iconSwitchSymbol.setToggleGroup(iconSwitchToggleGroup);
        iconSwitchSymbol.setSelected(true);
        iconSwitchSymbol.setSymbolType(SymbolType.POWER);
        iconSwitchSymbol.setSymbolColor(Color.web("#34495e"));

        iconSwitchText = new IconSwitch();
        iconSwitchText.setToggleGroup(iconSwitchToggleGroup);
        iconSwitchText.setText("A");
        iconSwitchText.setSymbolColor(Color.web("#34495e"));

        iconSwitchSymbol1 = new IconSwitch();
        iconSwitchSymbol1.setSymbolType(SymbolType.ALARM);
        iconSwitchSymbol1.setSymbolColor(Color.web("#34495e"));

        onOffSwitch.setOnSelect(switchEvent -> System.out.println("OnOff Switch switched on"));
        iconSwitchSymbol.setOnSelect(switchEvent -> System.out.println("Icon Switch Symbol switched on"));
        iconSwitchText.setOnSelect(switchEvent -> System.out.println("Icon Switch Text switched on"));
        iconSwitchSymbol1.setOnSelect(switchEvent -> System.out.println("Icon Switch Symbol 1 switched on"));
    }

    @Override public void start(Stage stage) {
        VBox pane = new VBox();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setBackground(new Background(new BackgroundFill(Color.web("#34495e"), CornerRadii.EMPTY, Insets.EMPTY)));
        pane.setSpacing(20);
        pane.setAlignment(Pos.CENTER);
        pane.getChildren().addAll(onOffSwitch, iconSwitchSymbol, iconSwitchText, iconSwitchSymbol1);

        Scene scene = new Scene(pane, 100, 150);

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
