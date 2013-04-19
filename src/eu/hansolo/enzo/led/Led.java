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

    private Color                 _color = Color.RED;
    private ObjectProperty<Color> color;
    private Type                  _type = Type.ROUND;
    private ObjectProperty<Type>  type;
    private boolean               _on = false;
    private BooleanProperty       on;
    private boolean               _blink = false;
    private BooleanProperty       blink;
    private boolean               _frameVisible = true;
    private BooleanProperty       frameVisible;
    private boolean               toggle;
    private long                  lastTimerCall;
    private long                  _interval = 500_000_000l;
    private LongProperty          interval;
    private AnimationTimer        timer;


    // ******************** Constructors **************************************
    public Led() {
        getStyleClass().add("led");
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
    public Color getColor() {
        return (null == color) ? _color : color.get();
    }
    public void setColor(final Color COLOR) {
        if (null == color) {
            _color = COLOR;
        } else {
            color.set(COLOR);
        }
    }
    public ObjectProperty colorProperty() {
        if (null == color) {
            color = new SimpleObjectProperty<>(this, "color", _color);
        }
        return color;
    }

    public final Type getType() {
        return (null == type) ? _type : type.get();
    }
    public final void setType(final Type TYPE) {
        if (null == type) {
            _type = TYPE;
        } else {
            type.set(TYPE);
        }
    }
    public final ObjectProperty<Type> typeProperty() {
        if (null == type) {
            type = new SimpleObjectProperty<>(this, "type", _type);
        }
        return type;
    }

    public final boolean isOn() {
        return (null == on) ? _on : on.get();
    }
    public final void setOn(final boolean ON) {
        if (null == on) {
            _on = ON;
        } else {
            on.set(ON);
        }
    }
    public final BooleanProperty onProperty() {
        if (null == on) {
            on = new SimpleBooleanProperty(this, "on", _on);
        }
        return on;
    }

    public final boolean isBlinking() {
        return (null == blink) ? _blink : blink.get();
    }
    public final void setBlink(final boolean BLINK) {
        _blink = BLINK;
        if (null == blink) {
            _blink = BLINK;
        } else {
            blink.set(BLINK);
        }
        if (BLINK) {
            timer.start();
        } else {
            timer.stop();
            setOn(false);
        }
    }
    public final BooleanProperty blinkProperty() {
        if (null == blink) {
            blink = new SimpleBooleanProperty(this, "blink", _blink);
        }
        return blink;
    }

    public final long getInterval() {
        return (null == interval) ? _interval : interval.get();
    }
    public final void setInterval(final long INTERVAL) {
        if (null == interval) {
            _interval = clamp(50_000_000l, 5_000_000_000l, INTERVAL);
        } else {
            interval.set(clamp(50_000_000l, 5_000_000_000l, INTERVAL));
        }
    }
    public final LongProperty intervalProperty() {
        if (null == interval) {
            interval = new SimpleLongProperty(this, "interval", _interval);
        }
        return interval;
    }

    public final boolean isFrameVisible() {
        return (null == frameVisible) ? _frameVisible : frameVisible.get();
    }
    public final void setFrameVisible(final boolean FRAME_VISIBLE) {
        if (null == frameVisible) {
            _frameVisible = FRAME_VISIBLE;
        } else {
            frameVisible.set(FRAME_VISIBLE);
        }
    }
    public final BooleanProperty frameVisibleProperty() {
        if (null == frameVisible) {
            frameVisible = new SimpleBooleanProperty(this, "frameVisible", _frameVisible);
        }
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

