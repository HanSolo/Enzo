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

package eu.hansolo.enzo.clock;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * Created with IntelliJ IDEA.
 * User: hansolo
 * Date: 31.10.12
 * Time: 16:14
 * To change this template use File | Settings | File Templates.
 */
public class Demo extends Application {

    private Clock      clock1;
    private Clock      clock2;
    private Clock      clock3;
    private Clock      clock4;
    private Clock      clock5;
    private Clock      clock6;
    private static int noOfNodes;

    @Override public void init() {
        clock1 = ClockBuilder.create()
                             .design(Clock.Design.IOS6)
                             .build();
        clock2 = ClockBuilder.create()
                             .design(Clock.Design.IOS6)
                             .nightMode(true)
                             .build();
        clock3 = ClockBuilder.create()
                             .design(Clock.Design.DB)
                             .build();
        clock4 = ClockBuilder.create()
                             .design(Clock.Design.DB)
                             .nightMode(true)
                             .build();
        clock5 = ClockBuilder.create()
                             .design(Clock.Design.BRAUN)
                             .discreteSecond(true)
                             .build();
        clock6 = ClockBuilder.create()
                             .design(Clock.Design.BRAUN)
                             .nightMode(true)
                             .discreteSecond(true)
                             .build();
    }

    @Override public void start(Stage stage) {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.add(clock1, 0, 0);
        pane.add(clock2, 0, 1);
        pane.add(clock3, 1, 0);
        pane.add(clock4, 1, 1);
        pane.add(clock5, 2, 0);
        pane.add(clock6, 2, 1);

        //Scene scene = new Scene(pane, 400, 400, Color.rgb(195, 195, 195));
        Scene scene = new Scene(pane, Color.BLACK);

        stage.setTitle("Clock Demo");
        stage.setScene(scene);
        stage.show();

        //clock.setMajorTickHeight(30);

        calcNoOfNodes(scene.getRoot());
        System.out.println("No. of nodes in scene: " + noOfNodes);
    }

    @Override public void stop() {

    }

    private static void calcNoOfNodes(Node node) {
        if (node instanceof Parent) {
            if (((Parent) node).getChildrenUnmodifiable().size() != 0) {
                ObservableList<Node> tempChildren = ((Parent) node).getChildrenUnmodifiable();
                noOfNodes += tempChildren.size();
                for (Node n : tempChildren) {
                    calcNoOfNodes(n);
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
