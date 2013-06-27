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

package eu.hansolo.enzo.sevensegment;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.Random;


public class Demo extends Application {
    private static final Random RND = new Random();
    private long           lastTimerCall;
    private AnimationTimer timer;

    private SevenSegment seg0;
    private SevenSegment seg1;
    private SevenSegment seg2;
    private SevenSegment seg3;


    @Override public void init() {
        seg0 = SevenSegmentBuilder.create()
                                    .prefWidth(134)
                                    .prefHeight(179)
                                    .character(0)
                                    .segmentStyle(SevenSegment.SegmentStyle.RED)
                                    .build();
        seg1 = SevenSegmentBuilder.create()
                                    .prefWidth(134)
                                    .prefHeight(179)
                                    .character(0)
                                    .dotOn(true)
                                    .segmentStyle(SevenSegment.SegmentStyle.RED)
                                    .build();
        seg2 = SevenSegmentBuilder.create()
                                    .prefWidth(134)
                                    .prefHeight(179)
                                    .character(0)
                                    .segmentStyle(SevenSegment.SegmentStyle.RED)
                                    .build();
        seg3 = SevenSegmentBuilder.create()
                                    .prefWidth(134)
                                    .prefHeight(179)
                                    .character(0)
                                    .segmentStyle(SevenSegment.SegmentStyle.RED)
                                    .build();

        lastTimerCall = System.nanoTime();
        timer = new AnimationTimer() {
            @Override public void handle(long now) {
                if (now > lastTimerCall + 5_000_000_000l) {
                    double v = RND.nextDouble() * 20.0 + 10.0;

                    if (Double.compare(v, 10) > 0 && Double.compare(v, 16) <= 0) {
                        setSegmentColor(SevenSegment.SegmentStyle.BLUE);
                    } else if (Double.compare(v, 16) > 0 && Double.compare(v, 20) <= 0) {
                        setSegmentColor(SevenSegment.SegmentStyle.CYAN);
                    } else if (Double.compare(v, 20) > 0 && Double.compare(v, 24) <= 0) {
                        setSegmentColor(SevenSegment.SegmentStyle.GREEN);
                    } else if (Double.compare(v, 24) > 0 && Double.compare(v, 28) <= 0) {
                        setSegmentColor(SevenSegment.SegmentStyle.YELLOW);
                    } else {
                        setSegmentColor(SevenSegment.SegmentStyle.RED);
                    }

                    String value = String.format(Locale.US, "%.2f", v);
                    int offset = value.length() == 5 ? 0 : 1;
                    seg0.setCharacter(offset == 0 ? value.substring(0, 1) : "0");
                    seg1.setCharacter(value.substring(1 - offset, 2 - offset));
                    seg2.setCharacter(value.substring(3 - offset, 4 - offset));
                    seg3.setCharacter(value.substring(4 - offset, 5 - offset));
                    lastTimerCall = now;
                }
            }
        };
    }

    private void setSegmentColor(final SevenSegment.SegmentStyle STYLE) {
        seg0.setSegmentStyle(STYLE);
        seg1.setSegmentStyle(STYLE);
        seg2.setSegmentStyle(STYLE);
        seg3.setSegmentStyle(STYLE);
    }

    @Override public void start(Stage stage) {
        HBox lcd = new HBox();
        lcd.setPadding(new Insets(15, 15, 15, 15));
        lcd.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        lcd.setSpacing(10);
        lcd.setAlignment(Pos.CENTER);
        lcd.setFillHeight(false);
        HBox.setMargin(seg3, new Insets(0, 20, 0, 0));
        lcd.getChildren().addAll(seg0, seg1, seg2, seg3);
        /*
        for (int i = 0 ; i < 256 ; i++) {
            System.out.println(i + "   :   " + Character.toString((char) i));
        }
        */
        StackPane pane = new StackPane();
        pane.getChildren().setAll(lcd);

        Scene scene = new Scene(pane, Color.BLACK);

        stage.setTitle("SevenSegment Demo");
        stage.setScene(scene);
        stage.show();

        timer.start();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
