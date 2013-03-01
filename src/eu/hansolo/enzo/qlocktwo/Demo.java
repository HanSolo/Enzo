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

package eu.hansolo.enzo.qlocktwo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Demo extends Application {
    private QlockTwo german;
    private QlockTwo english;
    private QlockTwo french;
    private QlockTwo spanish;
    private QlockTwo dutch;
    private QlockTwo german1;


    @Override public void init() {
        german  = QlockTwoBuilder.create().prefWidth(300).prefHeight(300).language(QlockTwo.Language.GERMAN).color(QlockTwo.QlockColor.BLACK_ICE_TEA).build();
        english = QlockTwoBuilder.create().prefWidth(300).prefHeight(300).language(QlockTwo.Language.ENGLISH).color(QlockTwo.QlockColor.BLUE_CANDY).build();
        french  = QlockTwoBuilder.create().prefWidth(300).prefHeight(300).language(QlockTwo.Language.FRENCH).color(QlockTwo.QlockColor.CHERRY_CAKE).build();
        spanish = QlockTwoBuilder.create().prefWidth(300).prefHeight(300).language(QlockTwo.Language.SPANISH).color(QlockTwo.QlockColor.FROZEN_BLACKBERRY).build();
        dutch   = QlockTwoBuilder.create().prefWidth(300).prefHeight(300).language(QlockTwo.Language.DUTCH).color(QlockTwo.QlockColor.LIME_JUICE).build();
        german1 = QlockTwoBuilder.create().secondsMode(true).prefWidth(300).prefHeight(300).language(QlockTwo.Language.GERMAN).color(QlockTwo.QlockColor.STAINLESS_STEEL).build();
    }

    @Override public void start(Stage stage) {
        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);
        pane.add(german, 0, 0);
        pane.add(english, 1, 0);
        pane.add(french, 2, 0);
        pane.add(spanish, 0, 1);
        pane.add(dutch, 1, 1);
        pane.add(german1, 2, 1);

        Scene scene = new Scene(pane, Color.DARKGRAY);

        stage.setTitle("JavaFX QlockTwo");
        stage.setScene(scene);
        stage.show();
    }

    @Override public void stop() {
        Platform.exit();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}


