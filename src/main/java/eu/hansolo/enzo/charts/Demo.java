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

package eu.hansolo.enzo.charts;

import eu.hansolo.enzo.common.Section;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * Created by
 * User: hansolo
 * Date: 19.08.13
 * Time: 15:07
 */

public class Demo extends Application {
    private static int      noOfNodes = 0;
    private SimpleLineChart chart;

    @Override public void init() {
        XYChart.Series series = new XYChart.Series();
        series.setName("temperature");
        series.getData().add(new XYChart.Data(1, 23d));
        series.getData().add(new XYChart.Data(2, 18d));
        series.getData().add(new XYChart.Data(3, 15d));
        series.getData().add(new XYChart.Data(4, 17d));
        series.getData().add(new XYChart.Data(5, 20d));
        series.getData().add(new XYChart.Data(6, 25d));
        series.getData().add(new XYChart.Data(7, 28d));
        series.getData().add(new XYChart.Data(8, 32d));
        series.getData().add(new XYChart.Data(9, 30d));
        series.getData().add(new XYChart.Data(10, 26d));
        series.getData().add(new XYChart.Data(11, 24d));
        series.getData().add(new XYChart.Data(12, 22d));

        chart = new SimpleLineChart();
        chart.setSections(new Section[]{
            new Section(-40, -20),
            new Section(-20, 0),
            new Section(0, 20),
            new Section(20, 30),
            new Section(30, 40)
        });
        chart.getStyleClass().addAll(SimpleLineChart.STYLE_CLASS_BLUE_TO_RED_5);
        chart.setSectionRangeVisible(true);
        chart.setUnit("°C");
        chart.setSeries(series);
    }

    @Override public void start(Stage stage) throws Exception {
        StackPane pane = new StackPane();
        pane.setPadding(new Insets(5, 5, 5, 5));
        pane.getChildren().addAll(chart);

        Scene scene = new Scene(pane);
        //scene.setFullScreen(true);

        stage.setTitle("Demo SimpleLineChart");
        stage.setScene(scene);
        stage.show();

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