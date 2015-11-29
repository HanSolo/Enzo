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

package eu.langhammer.enzo.canvasled;

import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;


/**
 * Created by
 * User: hansolo
 * Date: 31.08.13
 * Time: 08:31
 */
public class Led extends Region {
    private static final double   PREFERRED_SIZE = 16;
    private static final double   MINIMUM_SIZE   = 8;
    private static final double   MAXIMUM_SIZE   = 1024;

    private ObjectProperty<Color> ledColor;
    private BooleanProperty       on;
    private boolean               _blinking = false;
    private BooleanProperty       blinking;
    private boolean               _frameVisible = true;
    private BooleanProperty       frameVisible;
    private boolean               toggle;
    private long                  lastTimerCall;
    private long                  _interval = 500_000_000l;
    private LongProperty          interval;
    private AnimationTimer        timer;

    private Canvas                canvas;
    private GraphicsContext       ctx;


    // ******************** Constructors **************************************
    public Led() {
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
        init();
        initGraphics();
        registerListeners();
    }


    // ******************** Initialization ************************************
    private void init() {
        if (Double.compare(getWidth(), 0) <= 0 ||
            Double.compare(getHeight(), 0) <= 0 ||
            Double.compare(getPrefWidth(), 0) <= 0 ||
            Double.compare(getPrefHeight(), 0) <= 0) {
            setPrefSize(PREFERRED_SIZE, PREFERRED_SIZE);
        }
        if (Double.compare(getMinWidth(), 0) <= 0 ||
            Double.compare(getMinHeight(), 0) <= 0) {
            setMinSize(MINIMUM_SIZE, MINIMUM_SIZE);
        }
        if (Double.compare(getMaxWidth(), 0) <= 0 ||
            Double.compare(getMaxHeight(), 0) <= 0) {
            setMaxSize(MAXIMUM_SIZE, MAXIMUM_SIZE);
        }
    }

    private void initGraphics() {
        canvas = new Canvas();
        ctx    = canvas.getGraphicsContext2D();
        getChildren().add(canvas);
    }

    private void registerListeners() {
        widthProperty().addListener(observable -> draw());
        heightProperty().addListener(observable -> draw());
        frameVisibleProperty().addListener(observable -> draw());
        onProperty().addListener(observable -> draw());
        ledColorProperty().addListener(observable -> draw());
    }


    // ******************** Methods *******************************************
    public final boolean isOn() {
        return (null == on) ? false : on.get();
    }
    public final void setOn(final boolean GLOWING) {
        onProperty().set(GLOWING);
    }
    public final BooleanProperty onProperty() {
        if (null == on) {
            on = new SimpleBooleanProperty(this, "on", false);
        }
        return on;
    }

    public final boolean isBlinking() {
        return (null == blinking) ? _blinking : blinking.get();
    }
    public final void setBlinking(final boolean BLINKING) {
        if (null == blinking) {
            _blinking = BLINKING;
        } else {
            blinking.set(BLINKING);
        }
        if (BLINKING) {
            timer.start();
        } else {
            timer.stop();
        }
    }
    public final BooleanProperty blinkingProperty() {
        if (null == blinking) {
            blinking = new SimpleBooleanProperty(this, "blinking", _blinking);
        }
        return blinking;
    }

    public final long getInterval() {
        return null == interval ? _interval : interval.get();
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
        draw();
    }
    public final BooleanProperty frameVisibleProperty() {
        if (null == frameVisible) {
            frameVisible = new SimpleBooleanProperty(this, "frameVisible", _frameVisible);
        }
        return frameVisible;
    }

    public final Color getLedColor() {
        return null == ledColor ? Color.RED : ledColor.get();
    }
    public final void setLedColor(final Color LED_COLOR) {
        ledColorProperty().set(LED_COLOR);
    }
    public final ObjectProperty<Color> ledColorProperty() {
        if (null == ledColor) {
            ledColor = new SimpleObjectProperty<>(this, "ledColor", Color.RED);
        }
        return ledColor;
    }


    // ******************** Utility Methods ***********************************
    public static long clamp(final long MIN, final long MAX, final long VALUE) {
        if (VALUE < MIN) return MIN;
        if (VALUE > MAX) return MAX;
        return VALUE;
    }


    // ******************** Resize/Redraw *************************************
    private void draw() {
        double size   = getWidth() < getHeight() ? getWidth() : getHeight();

        canvas.setWidth(size);
        canvas.setHeight(size);

        ctx.clearRect(0, 0, size, size);

        if (isFrameVisible()) { //frame
            Paint frame = new LinearGradient(0.14 * size, 0.14 * size,
                                             0.84 * size, 0.84 * size,
                                             false, CycleMethod.NO_CYCLE,
                                             new Stop(0.0, Color.rgb(20, 20, 20, 0.65)),
                                             new Stop(0.15, Color.rgb(20, 20, 20, 0.65)),
                                             new Stop(0.26, Color.rgb(41, 41, 41, 0.65)),
                                             new Stop(0.26, Color.rgb(41, 41, 41, 0.64)),
                                             new Stop(0.85, Color.rgb(200, 200, 200, 0.41)),
                                             new Stop(1.0, Color.rgb(200, 200, 200, 0.35)));
            ctx.setFill(frame);
            ctx.fillOval(0, 0, size, size);
        }

        InnerShadow innerShadow = new InnerShadow(BlurType.TWO_PASS_BOX, Color.rgb(0, 0, 0, 0.65), 0.07 * size, 0, 0, 0);
        if (isOn()) { //on
            ctx.save();
            Paint on = new LinearGradient(0.25 * size, 0.25 * size,
                                          0.74 * size, 0.74 * size,
                                          false, CycleMethod.NO_CYCLE,
                                          new Stop(0.0, ledColor.get().deriveColor(0d, 1d, 0.77, 1d)),
                                          new Stop(0.49, ledColor.get().deriveColor(0d, 1d, 0.5, 1d)),
                                          new Stop(1.0, ledColor.get()));
            innerShadow.setInput(new DropShadow(BlurType.TWO_PASS_BOX, ledColor.get(), 0.36 * size, 0, 0, 0));
            ctx.setEffect(innerShadow);
            ctx.setFill(on);
            ctx.fillOval(0.14 * size, 0.14 * size, 0.72 * size, 0.72 * size);
            ctx.restore();
        } else { // off
            ctx.save();
            Paint off = new LinearGradient(0.25 * size, 0.25 * size,
                                           0.74 * size, 0.74 * size,
                                           false, CycleMethod.NO_CYCLE,
                                           new Stop(0.0, ledColor.get().deriveColor(0d, 1d, 0.20, 1d)),
                                           new Stop(0.49, ledColor.get().deriveColor(0d, 1d, 0.13, 1d)),
                                           new Stop(1.0, ledColor.get().deriveColor(0d, 1d, 0.2, 1d)));
            ctx.setEffect(innerShadow);
            ctx.setFill(off);
            ctx.fillOval(0.14 * size, 0.14 * size, 0.72 * size, 0.72 * size);
            ctx.restore();
        }

        //highlight
        Paint highlight = new RadialGradient(0, 0,
                                             0.3 * size, 0.3 * size,
                                             0.29 * size,
                                             false, CycleMethod.NO_CYCLE,
                                             new Stop(0.0, Color.WHITE),
                                             new Stop(1.0, Color.TRANSPARENT));
        ctx.setFill(highlight);
        ctx.fillOval(0.21 * size, 0.21 * size, 0.58 * size, 0.58 * size);
    }
}
