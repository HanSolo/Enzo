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

package eu.hansolo.enzo.led;

import com.sun.javafx.css.converters.EnumConverter;
import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.BooleanPropertyBase;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.css.CssMetaData;
import javafx.css.PseudoClass;
import javafx.css.StyleableObjectProperty;
import javafx.css.StyleableProperty;
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

    private static final PseudoClass ON_PSEUDO_CLASS = PseudoClass.getPseudoClass("on");

    public static final String STYLE_CLASS_RED     = "led-red";
    public static final String STYLE_CLASS_GREEN   = "led-green";
    public static final String STYLE_CLASS_BLUE    = "led-blue";
    public static final String STYLE_CLASS_YELLOW  = "led-yellow";
    public static final String STYLE_CLASS_ORANGE  = "led-orange";
    public static final String STYLE_CLASS_CYAN    = "led-cyan";
    public static final String STYLE_CLASS_MAGENTA = "led-magenta";
    public static final String STYLE_CLASS_PURPLE  = "led-purple";
    public static final String STYLE_CLASS_GRAY    = "led-gray";

    private BooleanProperty       on;
    private Color                 _color = Color.RED;
    private ObjectProperty<Color> color;
    private Type                  _type = Type.ROUND;
    private ObjectProperty<Type>  type;
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
        on = new BooleanPropertyBase(false) {
            @Override protected void invalidated() {
                pseudoClassStateChanged(ON_PSEUDO_CLASS, get());
            }
            @Override public Object getBean() {
                return this;
            }
            @Override public String getName() {
                return "on";
            }
        };
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
    public final boolean isOn() {
        return on.get();
    }
    public final void setOn(final boolean ON) {
        on.set(ON);
    }
    public final BooleanProperty onProperty() {
        return on;
    }

    public final Color getColor() {
        return (null == color) ? _color : color.get();
    }
    public final void setColor(final Color COLOR) {
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

    private final CssMetaData<Led, Type> TYPE = new CssMetaData<Led, Type>("-led-type", new EnumConverter<Type>(Type.class)) {
        @Override public boolean isSettable(Led led) {
            return null == led.type || !led.type.isBound();
        }

        @Override public StyleableProperty<Type> getStyleableProperty(Led led) {
            return (StyleableProperty) led.typeProperty();
        }
    };

    public final ObjectProperty<Type> typeProperty() {
        if (null == type) {
            type = new StyleableObjectProperty<Type>(Type.ROUND) {
                @Override public CssMetaData getCssMetaData() { return TYPE; }
                @Override public Object getBean() { return this; }
                @Override public String getName() { return "type";}
            };
        }
        return type;
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

    /* Problems
    final static List<CssMetaData> metaData = new ArrayList<CssMetaData> (Control.getClassCssMetaData());
    Collections.addAll(metaData, TYPE);

    private static final List<CssMetaData> CSS_META_DATA = Collections.unmodifiableList(metaData);
    public static List<CssMetaData> getClassCssMetaData() { return CSS_META_DATA; }
    @Override public List<CssMetaData> getCssMetaData() { return getClassCssMetaData(); }
    */

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

