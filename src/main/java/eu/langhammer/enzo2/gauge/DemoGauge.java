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

package eu.langhammer.enzo2.gauge;

import eu.langhammer.enzo2.common.Marker;
import eu.langhammer.enzo2.common.Section;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;


/**
 * Created by
 * User: hansolo
 * Date: 15.11.12
 * Time: 00:54
 */

public class DemoGauge extends Application {
    private static final Random RND       = new Random();
    private static int          noOfNodes = 0;
    private Gauge               control;
    private long                lastTimerCall;
    private AnimationTimer      timer;
    private Marker              marker0;


    @Override public void init() {
        control = GaugeBuilder.create()
                              .prefSize(400, 400)
                              .startAngle(330)
                              .angleRange(300)
                              .minValue(0)
                              .maxValue(100)
                              .sections(new Section(0, 60),
                                        new Section(60, 80),
                                        new Section(80, 100))
                              .majorTickSpace(20)
                              .plainValue(false)
                              .tickLabelOrientation(Gauge.TickLabelOrientation.HORIZONTAL)
                              .threshold(70)
                              .thresholdVisible(true)
                              .minMeasuredValueVisible(true)
                              .maxMeasuredValueVisible(true)
                              .title("Title")
                              .unit("Unit")
                              .build();


        //control.setStyle("-tick-label-fill: blue;");
        //control.setMinorTickSpace(2);
        //control.setHistogramEnabled(true);


        //control.setOnThresholdExceeded(observable -> System.out.println("Threshold exceeded") );
        //control.setOnThresholdUnderrun(observable -> System.out.println("Threshold underrun"));

        marker0 = new Marker(25);
        //marker0.setOnMarkerExceeded(observable -> System.out.println("Marker exceeded"));
        //marker0.setOnMarkerUnderrun(observable -> System.out.println("Marker underrun"));
        control.addMarker(marker0);


        lastTimerCall = System.nanoTime() + 500_000_000l;
        timer = new AnimationTimer() {
            @Override public void handle(long now) {
                if (now > lastTimerCall + 2_000_000_000l) {
                    control.setValue(RND.nextDouble() * 100);
                    System.out.println(control.getValue());
                    lastTimerCall = now;
                }
            }
        };
    }

    @Override public void start(Stage stage) throws Exception {
        StackPane pane = new StackPane();
        pane.setPadding(new Insets(5, 5, 5, 5));
        pane.getChildren().add(control);

        final Scene scene = new Scene(pane, Color.BLACK);
        scene.getStylesheets().add(getClass().getResource("demo.css").toExternalForm());
        //scene.setFullScreen(true);

        stage.setTitle("Gauge");
        stage.setScene(scene);
        stage.show();

        //timer.start();

        calcNoOfNodes(scene.getRoot());
        System.out.println(noOfNodes + " Nodes in SceneGraph");
    }

    @Override public void stop() {

    }

    public static void main(final String[] args) {
        Application.launch(args);
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
}
