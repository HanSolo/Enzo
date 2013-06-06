/*
 * Copyright (c) 2013. Gerrit Grunwald
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package eu.hansolo.enzo.departureboard;

import eu.hansolo.enzo.clock.Clock;
import eu.hansolo.enzo.clock.ClockBuilder;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.Calendar;


/**
 * Created by
 * User: hansolo
 * Date: 15.04.13
 * Time: 08:23
 */
public class Demo extends Application {
    private DepartureBoard departureBoard;


    @Override public void init() {
        departureBoard = new DepartureBoard();
    }

    @Override public void start(Stage stage) throws Exception {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));

        Clock clock = ClockBuilder.create()
                                  .secondPointerVisible(false)
                                  .nightMode(true)
                                  .design(Clock.Design.DB)
                                  .highlightVisible(false)
                                  .build();
        HBox.setMargin(clock, new Insets(0, 0, 20, 0));

        Text title = new Text("Departures");
        title.setFill(Color.WHITE);
        title.setTextAlignment(TextAlignment.LEFT);
        title.setTextOrigin(VPos.BOTTOM);
        title.setFont(Font.font("sans serif", 36));
        HBox.setMargin(title, new Insets(0, 0, 0, 50));

        Group airplaneImage = getDepartureImage(50, Color.WHITE);
        HBox.setMargin(airplaneImage, new Insets(0, 0, 0, 50));

        HBox header = new HBox();
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPrefHeight(140);
        header.getChildren().setAll(clock, airplaneImage, title);

        grid.add(header, 0, 0);

        HeaderRow labels = new HeaderRow();
        grid.add(labels, 0, 1);

        grid.add(departureBoard, 0, 2);
        GridPane.setHalignment(departureBoard, HPos.CENTER);

        LinearGradient gradient = new LinearGradient(0, 0, 0, 600, false, CycleMethod.NO_CYCLE,
                                                     new Stop(0.0, Color.rgb(28, 27, 22)),
                                                     new Stop(0.25, Color.rgb(38, 37, 32)),
                                                     new Stop(1.0, Color.rgb(28, 27, 22)));
        final Scene scene = new Scene(grid, gradient);
        scene.setCamera(new PerspectiveCamera());

        stage.setTitle("DepartureBoard");
        stage.setScene(scene);
        stage.show();
        initialSetup();
        departureBoard.start();
    }

    private void initialSetup() {
        final Calendar CAL = Calendar.getInstance();
        final long NOW = System.currentTimeMillis();
        CAL.setTimeInMillis(NOW + 60000);
        departureBoard.addRow(new Row(Integer.toString(CAL.get(Calendar.HOUR_OF_DAY)), Integer.toString(CAL.get(Calendar.MINUTE)), "BERLIN", "LH1969", "A01"));
        CAL.setTimeInMillis(NOW + 120000);
        departureBoard.addRow(new Row(Integer.toString(CAL.get(Calendar.HOUR_OF_DAY)), Integer.toString(CAL.get(Calendar.MINUTE)), "BASEL", "LH1203", "A03"));
        CAL.setTimeInMillis(NOW + 180000);
        departureBoard.addRow(new Row(Integer.toString(CAL.get(Calendar.HOUR_OF_DAY)), Integer.toString(CAL.get(Calendar.MINUTE)), "HELSINKI", "LH0502", "A08"));
        CAL.setTimeInMillis(NOW + 240000);
        departureBoard.addRow(new Row(Integer.toString(CAL.get(Calendar.HOUR_OF_DAY)), Integer.toString(CAL.get(Calendar.MINUTE)), "MOSCOW", "LH0610", "C13"));
        CAL.setTimeInMillis(NOW + 600000);
        departureBoard.addRow(new Row(Integer.toString(CAL.get(Calendar.HOUR_OF_DAY)), Integer.toString(CAL.get(Calendar.MINUTE)), "LONDON", "LH0610", "B02"));
        CAL.setTimeInMillis(NOW + 900000);
        departureBoard.addRow(new Row(Integer.toString(CAL.get(Calendar.HOUR_OF_DAY)), Integer.toString(CAL.get(Calendar.MINUTE)), "FRANKFURT", "LH1972", "A02"));
        CAL.setTimeInMillis(NOW + 1200000);
        departureBoard.addRow(new Row(Integer.toString(CAL.get(Calendar.HOUR_OF_DAY)), Integer.toString(CAL.get(Calendar.MINUTE)), "PARIS", "LH0502", "B01"));
        CAL.setTimeInMillis(NOW + 1500000);
        departureBoard.addRow(new Row(Integer.toString(CAL.get(Calendar.HOUR_OF_DAY)), Integer.toString(CAL.get(Calendar.MINUTE)), "SYDNEY", "LH0310", "A01"));
        CAL.setTimeInMillis(NOW + 1800000);
        departureBoard.addRow(new Row(Integer.toString(CAL.get(Calendar.HOUR_OF_DAY)), Integer.toString(CAL.get(Calendar.MINUTE)), "SAN FRANCISCO", "LH2310", "A12"));
        CAL.setTimeInMillis(NOW + 2100000);
        departureBoard.addRow(new Row(Integer.toString(CAL.get(Calendar.HOUR_OF_DAY)), Integer.toString(CAL.get(Calendar.MINUTE)), "BOSTON", "LH01942", "B05"));
        CAL.setTimeInMillis(NOW + 2400000);
        departureBoard.addRow(new Row(Integer.toString(CAL.get(Calendar.HOUR_OF_DAY)), Integer.toString(CAL.get(Calendar.MINUTE)), "NEW YORK", "LH4212", "C28"));
        CAL.setTimeInMillis(NOW + 2700000);
        departureBoard.addRow(new Row(Integer.toString(CAL.get(Calendar.HOUR_OF_DAY)), Integer.toString(CAL.get(Calendar.MINUTE)), "MUNICH", "LH2205", "B13"));
    }

    public final Group getDepartureImage(final double SIZE, final Color COLOR) {
        final double WIDTH = SIZE;
        final double HEIGHT = SIZE;

        final Group DEPARTURE_GROUP = new Group();

        DEPARTURE_GROUP.getChildren().clear();
        final Shape IBOUNDS = new Rectangle(0, 0, WIDTH, HEIGHT);
        IBOUNDS.setOpacity(0.0);
        DEPARTURE_GROUP.getChildren().add(IBOUNDS);

        final SVGPath plane = new SVGPath();
        plane.setContent("M7.187,29.915 L0,21.531 L2.71,19.027 L17.602,26.247 z M8.238,31.386 C11.338,30.356 28.419,24.403 37.745,21.179 L23.277,3.803 L26.845,0 L60.655,13.502 C70.396,10.262 80.136,7.022 89.875,3.783 C92.553,2.891 99.209,3.115 99.925,5.721 C100.702,8.549 95.275,12.872 92.607,13.759 C88.62,15.086 84.631,16.412 80.644,17.738 C63.796,23.323 46.924,28.834 30.051,34.342 C25.485,35.101 5.569,32.275 8.238,31.386 z");
        plane.setFill(COLOR);

        DEPARTURE_GROUP.getChildren().addAll(plane);

        return DEPARTURE_GROUP;
    }

    public static void main(final String[] args) {
        Application.launch(args);
    }
}
