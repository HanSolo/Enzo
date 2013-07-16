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

package eu.hansolo.enzo.gauge;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import java.util.Random;


/**
 * Created by
 * User: hansolo
 * Date: 15.07.13
 * Time: 17:50
 */

public class DemoSimpleGauge extends Application {
    private static final Random RND       = new Random();
    private static int          noOfNodes = 0;
    private SimpleRadialGauge   simpleRadial;
    private SimpleLinearGauge   simpleLinear;
    private long                lastTimerCall;
    private AnimationTimer      timer;

    @Override public void init() {
        simpleRadial = SimpleRadialGaugeBuilder.create()
                                               .round(true)
                                               //.segmented(true)
                                               .sections(new Section(20, 40))
                                               .title("title")
                                               .unit("unit")
                                               .build();

        simpleLinear = SimpleLinearGaugeBuilder.create()
                                               .round(true)
                                               //.segmented(true)
                                               .sections(new Section(20, 40))
                                               .title("title")
                                               .unit("unit")
                                               .build();

        lastTimerCall = System.nanoTime() + 2_000_000_000l;
        timer = new AnimationTimer() {
            @Override public void handle(long now) {
                if (now > lastTimerCall + 5_000_000_000l) {
                    simpleRadial.setValue(RND.nextDouble() * 100);
                    simpleLinear.setValue(RND.nextDouble() * 100);
                    lastTimerCall = now;
                }
            }
        };
    }

    @Override public void start(Stage stage) throws Exception {
        HBox pane = new HBox();
        HBox.setHgrow(simpleLinear, Priority.ALWAYS);
        pane.setPadding(new Insets(5, 5, 5, 5));
        pane.getChildren().addAll(simpleRadial, simpleLinear);

        final Scene scene = new Scene(pane);
        //scene.setFullScreen(true);

        stage.setTitle("test");
        stage.setScene(scene);
        stage.show();

        timer.start();

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