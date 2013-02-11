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

package eu.hansolo.enzo.led;

import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Control;
import javafx.scene.paint.Color;


/**
 * Created by
 * User: hansolo
 * Date: 16.11.12
 * Time: 09:19
 */
public class Led extends Control {
    public enum Type {
        ROUND,
        SQUARE,
        VERTICAL,
        HORIZONTAL
    }
    private static final String STYLE_CLASS_ROUND  = "led-round";
    private static final String STYLE_CLASS_SQUARE = "led-square";
    private static final String STYLE_CLASS_VER    = "led-ver";
    private static final String STYLE_CLASS_HOR    = "led-hor";

    public static final String STYLE_CLASS_RED     = "led-red";
    public static final String STYLE_CLASS_GREEN   = "led-green";
    public static final String STYLE_CLASS_BLUE    = "led-blue";
    public static final String STYLE_CLASS_YELLOW  = "led-yellow";
    public static final String STYLE_CLASS_ORANGE  = "led-orange";
    public static final String STYLE_CLASS_CYAN    = "led-cyan";
    public static final String STYLE_CLASS_MAGENTA = "led-magenta";
    public static final String STYLE_CLASS_PURPLE  = "led-purple";
    public static final String STYLE_CLASS_GRAY    = "led-gray";

    private ObjectProperty<Color> color;
    private ObjectProperty<Type>  type;
    private BooleanProperty       on;
    private BooleanProperty       blink;
    private BooleanProperty       frameVisible;
    private boolean               toggle;
    private long                  lastTimerCall;
    private LongProperty          interval;
    private AnimationTimer        timer;


    // ******************** Constructors **************************************
    public Led() {
        getStyleClass().add("led");
        color         = new SimpleObjectProperty<>(Color.RED);
        type          = new SimpleObjectProperty<>(Type.ROUND);
        on            = new SimpleBooleanProperty(false);
        blink         = new SimpleBooleanProperty(false);
        frameVisible  = new SimpleBooleanProperty(true);
        interval      = new SimpleLongProperty(500_000_000);
        toggle        = false;
        lastTimerCall = System.nanoTime();
        timer         = new AnimationTimer() {
            @Override public void handle(final long NOW) {
                if (NOW > lastTimerCall + getInterval()) {
                    toggle ^= true;
                    setOn(toggle);
                    lastTimerCall = NOW;
                }
            }
        };
    }


    // ******************** Methods *******************************************
    public final Color getColor() {
        return color.get();
    }
    public final void setColor(final Color COLOR) {
        color.set(COLOR);
    }
    public final ObjectProperty<Color> colorProperty() {
        return color;
    }

    public final Type getType() {
        return type.get();
    }
    public final void setType(final Type TYPE) {
        type.set(TYPE);
    }
    public final ObjectProperty<Type> typeProperty() {
        return type;
    }

    public final boolean isOn() {
        return on.get();
    }
    public final void setOn(final boolean ON) {
        on.set(ON);
    }
    public final BooleanProperty onProperty() {
        return on;
    }

    public final boolean isBlinking() {
        return blink.get();
    }
    public final void setBlink(final boolean BLINK) {
        blink.set(BLINK);
        if (BLINK) {
            timer.start();
        } else {
            timer.stop();
            setOn(false);
        }
    }
    public final BooleanProperty blinkProperty() {
        return blink;
    }

    public final long getInterval() {
        return interval.get();
    }
    public final void setInterval(final long INTERVAL) {
        interval.set(clamp(50_000_000l, 5_000_000_000l, INTERVAL));
    }
    public final LongProperty intervalProperty() {
        return interval;
    }

    public final boolean isFrameVisible() {
        return frameVisible.get();
    }
    public final void setFrameVisible(final boolean FRAME_VISIBLE) {
        frameVisible.set(FRAME_VISIBLE);
    }
    public final BooleanProperty frameVisibleProperty() {
        return frameVisible;
    }


    // ******************** Utility Methods ***********************************
    public static long clamp(final long MIN, final long MAX, final long VALUE) {
        if (VALUE < MIN) return MIN;
        if (VALUE > MAX) return MAX;
        return VALUE;
    }


    // ******************** Style related *************************************
    @Override protected String getUserAgentStylesheet() {
        return getClass().getResource("led.css").toExternalForm();
    }
}

