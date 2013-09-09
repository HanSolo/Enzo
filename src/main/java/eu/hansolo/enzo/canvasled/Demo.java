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

package eu.hansolo.enzo.canvasled;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.time.LocalTime;
import java.util.Random;


/**
 * Created by
 * User: hansolo
 * Date: 31.08.13
 * Time: 08:32
 */
public class Demo  extends Application {
    private static final Random RND = new Random();
    private static int   noOfNodes  = 0;
    private LocalTime    start;
    private LocalTime    stop;

    @Override public void init() {
    }

    @Override public void start(Stage stage) {
        GridPane pane = new GridPane();
        for (int y = 0 ; y < 20 ; y++) {
            for (int x = 0 ; x < 20 ; x++) {
                pane.add(LedBuilder.create()
                                   .ledColor(Color.rgb(RND.nextInt(255), RND.nextInt(255), RND.nextInt(255)))
                                   .frameVisible(false)
                                   .interval(500_000_000l)
                                   .blinking(true)
                                   .build(), x, y);
            }
        }
        pane.sceneProperty().addListener(observable -> start = LocalTime.now());

        Scene scene = new Scene(pane);

        stage.setTitle("Led demo");
        stage.setScene(scene);
        stage.show();

        stop = LocalTime.now();
        System.out.println("Canvas Led: ");
        System.out.println("Start: " + start);
        System.out.println("Stop : " + stop);

        calcNoOfNodes(scene.getRoot());
        System.out.println(noOfNodes + " Nodes in SceneGraph");
        System.out.println(((stop.toNanoOfDay() - start.toNanoOfDay())) / noOfNodes / 1000000d + " [ms/node]");
    }

    @Override public void stop() {

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
}
