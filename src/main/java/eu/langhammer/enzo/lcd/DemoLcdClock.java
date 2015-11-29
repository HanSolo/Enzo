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

package eu.langhammer.enzo.lcd;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.util.Locale;


/**
 * Created by
 * User: hansolo
 * Date: 19.08.13
 * Time: 08:21
 */
public class DemoLcdClock extends Application {
    private static       int      noOfNodes     = 0;
    private static final String[] STYLE_CLASSES = {
        "lcd-beige",
        "lcd-blue",
        "lcd-orange",
        "lcd-red",
        "lcd-yellow",
        "lcd-white",
        "lcd-gray",
        "lcd-black",
        "lcd-green",
        "lcd-green-darkgreen",
        "lcd-blue2",
        "lcd-blue-black",
        "lcd-blue-darkblue",
        "lcd-blue-lightblue",
        "lcd-blue-gray",
        "lcd-standard",
        "lcd-lightgreen",
        "lcd-standard-green",
        "lcd-blue-blue",
        "lcd-red-darkred",
        "lcd-darkblue",
        "lcd-purple",
        "lcd-black-red",
        "lcd-darkgreen",
        "lcd-amber",
        "lcd-lightblue",
        "lcd-green-black",
        "lcd-yellow-black",
        "lcd-black-yellow",
        "lcd-lightgreen-black",
        "lcd-darkpurple",
        "lcd-darkamber",
        "lcd-blue-lightblue2",
        "lcd-gray-purple",
        "lcd-sections",
        "lcd-flat-turqoise",
        "lcd-flat-gree-sea",
        "lcd-flat-emerland",
        "lcd-flat-nephritis",
        "lcd-flat-peter-river",
        "lcd-flat-belize-hole",
        "lcd-flat-amethyst",
        "lcd-flat-wisteria",
        "lcd-flat-sunflower",
        "lcd-flat-orange",
        "lcd-flat-carrot",
        "lcd-flat-pumpkin",
        "lcd-flat-alizarin",
        "lcd-flat-pomegranate",
        "lcd-flat-clouds",
        "lcd-flat-silver",
        "lcd-flat-concrete",
        "lcd-flat-asbestos",
        "lcd-flat-wet-asphalt",
        "lcd-flat-midnight-blue"
    };
    private LcdClock       control;
    private long           lastTimerCall;
    private int            styleClassCounter;
    private AnimationTimer timer;
    private Commando1      commando1 = new Commando1();
    private Commando2      commando2 = new Commando2();

    @Override public void init() {
        control = LcdClockBuilder.create()
                                 .prefWidth(760)
                                 .prefHeight(400)
                                 .title("Münster")
                                 .styleClass(LcdClock.STYLE_CLASS_YOCTOPUCE)
                                 .foregroundShadowVisible(true)
                                 .crystalOverlayVisible(true)
                                 .timeFont(LcdClock.LcdFont.LCD)
                                 .locale(Locale.GERMAN)
                                 .dateFormat(LcdClock.DateFormat.DAY_MONTH_YEAR)
                                 .dateSeparator(LcdClock.DateSeparator.DOT)
                                 .alarms(new Alarm[]{
                                     new Alarm(Alarm.Repetition.ONCE, LocalDateTime.now().plusSeconds(20), Alarm.ARMED, "20s after Start"),
                                     new Alarm(Alarm.Repetition.ONCE, LocalDateTime.now().plusSeconds(30), Alarm.ARMED, "30s after Start", commando2)
                                 })
                                 .build();

        control.addEventHandler(Alarm.AlarmEvent.ALARM, new EventHandler<Alarm.AlarmEvent>() {
            @Override public void handle(Alarm.AlarmEvent alarmEvent) {
                System.out.println("Alarm: " + alarmEvent.getAlarm().getText());
            }
        });

        styleClassCounter = 34;
        lastTimerCall = System.nanoTime();
        timer = new AnimationTimer() {
            @Override public void handle(long now) {
                if (now > lastTimerCall + 5_000_000_000l) {
                    styleClassCounter ++;
                    if (styleClassCounter >= STYLE_CLASSES.length) {
                        styleClassCounter = 0;
                        control.setMainInnerShadowVisible(true);
                        control.setForegroundShadowVisible(true);
                        control.setCrystalOverlayVisible(true);
                    }
                    control.getStyleClass().setAll("lcd-clock", STYLE_CLASSES[styleClassCounter]);
                    if (styleClassCounter > 34) {
                        control.setMainInnerShadowVisible(false);
                        control.setForegroundShadowVisible(false);
                        control.setCrystalOverlayVisible(false);
                    }
                    lastTimerCall = now;
                }
            }
        };
    }

    @Override public void start(Stage stage) {
        StackPane pane = new StackPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.getChildren().setAll(control);

        Scene scene = new Scene(pane, Color.TRANSPARENT);

        stage.setTitle("Lcd demo");
        stage.centerOnScreen();
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();

        //timer.start();

        calcNoOfNodes(scene.getRoot());
        System.out.println(noOfNodes + " Nodes in SceneGraph");
    }

    public static void main(String[] args) {
        launch(args);
    }


    // ******************** Misc **********************************************
    private static void calcNoOfNodes(Node node) {
        if (node instanceof Parent) {
            if (((Parent) node).getChildrenUnmodifiable().size() != 0) {
                ObservableList<Node> tempChildren = ((Parent) node).getChildrenUnmodifiable();
                noOfNodes += tempChildren.size();
                for (Node n : tempChildren) {
                    calcNoOfNodes(n);
                    //System.out.println(n.getStyleClass().toString());
                }
            }
        }
    }


    public class Commando1 implements Alarm.Command {
        @Override public void execute() {
            System.out.println("Now execute something triggered by the Alarm");
        }
    }
    
    public class Commando2 implements Alarm.Command {
        @Override public void execute() {
            System.out.println("Another class that will be executed by an Alarm");
        }
    }
}

