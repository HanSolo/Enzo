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

package eu.hansolo.enzo.departureboard;

import eu.hansolo.enzo.led.Led;
import eu.hansolo.enzo.led.LedBuilder;
import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;


/**
 * Created by
 * User: hansolo
 * Date: 15.04.13
 * Time: 07:43
 */
public class LedSegment extends HBox {
    private Led             leftLed;
    private Led             rightLed;
    private Color           color;
    private boolean         defaultBlinking;
    private BooleanProperty blinking;
    private boolean         toggle;
    private long            lastTimerCall;
    private AnimationTimer  timer;


    // ******************** Constructors **************************************
    public LedSegment() {
        this(Color.LIME);
    }
    public LedSegment(final Color COLOR) {
        color    = COLOR;
        leftLed  = LedBuilder.create()
                             //.frameVisible(false)
                             .color(color)
                             .prefWidth(25)
                             .prefHeight(25)
                             .build();

        rightLed = LedBuilder.create()
                             //.frameVisible(false)
                             .color(color)
                             .prefWidth(25)
                             .prefHeight(25)
                             .build();
        setSpacing(2);
        getChildren().setAll(leftLed, rightLed);
        HBox.setMargin(leftLed, new Insets(15, 0, 0, 0));
        HBox.setMargin(rightLed, new Insets(15, 0, 0, 0));
        defaultBlinking = false;
        toggle          = false;
        timer = new AnimationTimer() {
            @Override public void handle(final long NOW) {
                if (NOW >  lastTimerCall + 750_000_000l) {
                    toggle ^= true;
                    leftLed.setOn(toggle);
                    rightLed.setOn(!toggle);
                    lastTimerCall = NOW;
                }
            }
        };
    }


    // ******************** Methods *******************************************
    public final boolean isBlinking() {
        return null == blinking ? defaultBlinking : blinking.get();
    }
    public final void setBlinking(final boolean BLINKING) {
        if (null == blinking) {
            defaultBlinking = BLINKING;
        } else {
            blinking.set(BLINKING);
        }
        if(BLINKING) {
            timer.start();
        } else {
            timer.stop();
            leftLed.setOn(false);
            rightLed.setOn(false);
        }
    }
    public final BooleanProperty blinkingProperty() {
        if (null == blinking) {
            blinking = new SimpleBooleanProperty(this, "blinking", defaultBlinking);
        }
        return blinking;
    }

    public final Color getColor() {
        return color;
    }
    public final void setColor(final Color COLOR) {
        color = COLOR;
        leftLed.setColor(COLOR);
        rightLed.setColor(COLOR);
    }

    public final void reset() {
        leftLed.setOn(false);
        rightLed.setOn(false);
    }

    public final void toggle() {
        toggle ^= true;
        leftLed.setOn(toggle);
        rightLed.setOn(!toggle);
    }
}
