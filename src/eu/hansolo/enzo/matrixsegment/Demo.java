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

package eu.hansolo.enzo.matrixsegment;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;


public class Demo extends Application {
    private static final Random RND = new Random();
    private Color[] colors = {
        Color.RED,
        Color.LIME,
        Color.LIGHTBLUE,
        Color.CYAN,
        Color.MAGENTA
    };

    private MatrixSegment seg1;
    private MatrixSegment seg2;
    private MatrixSegment seg3;
    private MatrixSegment seg4;
    private MatrixSegment seg5;
    private MatrixSegment seg6;

    private SquareMatrixSegment seg7;
    private SquareMatrixSegment seg8;
    private SquareMatrixSegment seg9;
    private SquareMatrixSegment seg10;
    private SquareMatrixSegment seg11;
    private SquareMatrixSegment seg12;

    private long           interval;
    private long           lastTimerCall;
    private AnimationTimer timer;


    @Override public void init() {
        seg1  = MatrixSegmentBuilder.create().character("J").build();
        seg2  = MatrixSegmentBuilder.create().character("a").build();
        seg3  = MatrixSegmentBuilder.create().character("v").build();
        seg4  = MatrixSegmentBuilder.create().character("a").build();
        seg5  = MatrixSegmentBuilder.create().character("F").build();
        seg6  = MatrixSegmentBuilder.create().character("X").build();

        seg7  = SquareMatrixSegmentBuilder.create().character("J").build();
        seg8  = SquareMatrixSegmentBuilder.create().character("a").build();
        seg9  = SquareMatrixSegmentBuilder.create().character("v").build();
        seg10 = SquareMatrixSegmentBuilder.create().character("a").build();
        seg11 = SquareMatrixSegmentBuilder.create().character("F").build();
        seg12 = SquareMatrixSegmentBuilder.create().character("X").build();

        interval      = 2_000_000_000l;
        lastTimerCall = System.nanoTime();
        timer         = new AnimationTimer() {
            @Override public void handle(long now) {
                if (now > lastTimerCall + interval) {
                    Color color = colors[RND.nextInt(5)];
                    seg1.setColor(color);
                    seg2.setColor(color);
                    seg3.setColor(color);
                    seg4.setColor(color);
                    seg5.setColor(color);
                    seg6.setColor(color);
                    seg7.setColor(color);
                    seg8.setColor(color);
                    seg9.setColor(color);
                    seg10.setColor(color);
                    seg11.setColor(color);
                    seg12.setColor(color);
                    lastTimerCall = now;
                }
            }
        };
    }

    @Override public void start(Stage stage) {
        HBox matrixSegmentPane = new HBox();
        matrixSegmentPane.setSpacing(5);
        matrixSegmentPane.setPadding(new Insets(10, 10, 10, 10));
        matrixSegmentPane.getChildren().setAll(seg1, seg2, seg3, seg4, seg5, seg6);

        HBox squareMatrixSegmentPane = new HBox();
        squareMatrixSegmentPane.setSpacing(0);
        squareMatrixSegmentPane.setPadding(new Insets(10, 10, 10, 10));
        squareMatrixSegmentPane.getChildren().setAll(seg7, seg8, seg9, seg10, seg11, seg12);

        VBox pane = new VBox();
        pane.setSpacing(10);
        pane.getChildren().addAll(matrixSegmentPane, squareMatrixSegmentPane);

        Scene scene = new Scene(pane, Color.DARKGRAY);

        stage.setTitle("Enzo MatrixSegment");
        stage.setScene(scene);
        stage.show();

        timer.start();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}


