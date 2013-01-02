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

package eu.hansolo.enzo.ledbargraph;

import eu.hansolo.enzo.led.Led;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;


/**
 * Created by
 * User: hansolo
 * Date: 19.11.12
 * Time: 04:02
 */
public class Demo extends Application {
    private static final Random RND = new Random();
    private LedBargraph         control;
    private long                lastTimerCall;
    private AnimationTimer      timer;

    @Override public void init() {
        control = LedBargraphBuilder.create()
                                    .ledType(Led.Type.ROUND)
                                    .ledSize(32)
                                    .build();
        lastTimerCall = System.nanoTime();
        timer = new AnimationTimer() {
            @Override public void handle(final long NOW) {
                if (NOW > lastTimerCall + 100_000_000l) {
                    control.setValue(RND.nextDouble());
                    lastTimerCall = NOW;
                }
            }
        };
    }

    @Override public void start(Stage stage) {
        StackPane pane = new StackPane();

        pane.getChildren().setAll(control);

        Scene scene = new Scene(pane, 400, 400, Color.rgb(0, 0, 0, 0.8));

        stage.setTitle("Led Bargraph demo");
        stage.setScene(scene);
        stage.show();

        timer.start();
    }

    @Override public void stop() {

    }

    public static void main(String[] args) {
        launch(args);
    }
}
