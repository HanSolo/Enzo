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

package eu.hansolo.enzo.lcd;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;


public class Demo extends Application {
    private static final Random   RND = new Random();
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
    "lcd-sections"
    };
    private Lcd                 control;
    private long                lastTimerCall;
    private double              charge;
    private int                 styleClassCounter;
    private AnimationTimer      timer;

    @Override public void init() {
        control = LcdBuilder.create()
                            .prefWidth(528)
                            .prefHeight(192)
                            .styleClass(Lcd.STYLE_CLASS_DARKBLUE)
                            .foregroundShadowVisible(true)
                            .crystalOverlayVisible(true)
                            .title("Room Temp")
                            .batteryVisible(true)
                            .alarmVisible(true)
                            .unit("°C")
                            .unitVisible(true)
                            .decimals(2)
                            .minMeasuredValueDecimals(2)
                            .minMeasuredValueVisible(true)
                            .maxMeasuredValueDecimals(2)
                            .maxMeasuredValueVisible(true)
                            .formerValueVisible(true)
                            .threshold(26)
                            .thresholdVisible(true)
                            .trendVisible(true)
                            //.numberSystemVisible(false)
                            .lowerRightTextVisible(true)
                            .lowerRightText("Info")
                            .valueFont(Lcd.LcdFont.ELEKTRA)
                            .valueAnimationEnabled(true)
                            .build();
        charge = 0.0;
        styleClassCounter = 0;
        lastTimerCall = System.nanoTime();
        timer = new AnimationTimer() {
            @Override public void handle(long now) {
                if (now > lastTimerCall + 3_000_000_000l) {
                    styleClassCounter ++;
                    if (styleClassCounter > 34) styleClassCounter = 0;
                    control.getStyleClass().setAll("lcd", STYLE_CLASSES[styleClassCounter]);
                    control.setValue(RND.nextDouble() * 100);
                    control.setTrend(Lcd.Trend.values()[RND.nextInt(5)]);
                    charge += 0.02;
                    if (charge > 1.0) charge = 0.0;
                    control.setBatteryCharge(charge);
                    lastTimerCall = now;
                }
            }
        };
    }

    @Override public void start(Stage stage) {
        StackPane pane = new StackPane();
        pane.getChildren().setAll(control);

        Scene scene = new Scene(pane, Color.TRANSPARENT);

        stage.setTitle("Lcd demo");
        stage.centerOnScreen();
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();

        timer.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


