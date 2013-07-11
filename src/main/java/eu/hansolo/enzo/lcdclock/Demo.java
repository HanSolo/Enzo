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

package eu.hansolo.enzo.lcdclock;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.time.LocalTime;


/**
 * Created by
 * User: hansolo
 * Date: 11.07.13
 * Time: 06:16
 */

public class Demo extends Application {
    private LcdClock clock;

    @Override public void init() {
        clock = LcdClockBuilder.create()
                               //.color(Color.CYAN)
                               //.hourColor(Color.LIME)
                               //.minuteColor(Color.AQUA)
                               //.secondColor(Color.MAGENTA)
                               //.textColor(Color.DARKOLIVEGREEN)
                               .alarmVisible(true)
                               .alarmOn(true)
                               .alarm(LocalTime.now().plusSeconds(20))
                               .dateVisible(true)
                               .build();
        clock.addEventHandler(AlarmEvent.ALARM, event -> {
            System.out.println("A L A R M");
        });
    }

    @Override public void start(Stage stage) {
        StackPane pane = new StackPane();
        pane.setPadding(new Insets(5, 5, 5, 5));
        pane.getChildren().addAll(clock);

        Scene scene = new Scene(pane);
        //scene.setFullScreen(true);

        stage.setTitle("LcdClock");
        stage.setScene(scene);
        stage.show();
    }

    @Override public void stop() {

    }

    public static void main(final String[] args) {
        Application.launch(args);
    }
}