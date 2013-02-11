package eu.hansolo.enzo.led1;

import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Control;
import javafx.scene.paint.Color;


public class Led extends Control {
    public static enum LedColor {
        RED(Color.RED, "led-red"),
        GREEN(Color.GREEN, "led-green"),
        BLUE(Color.BLUE, "led-blue"),
        LIGHT_BLUE(Color.LIGHTBLUE, "led-lightblue"),
        YELLOW(Color.YELLOW, "led-yellow"),
        ORANGE(Color.ORANGE, "led-orange"),
        CYAN(Color.CYAN, "led-cyan"),
        MAGENTA(Color.MAGENTA, "led-magenta"),
        PURPLE(Color.PURPLE, "led-purple"),
        WHITE(Color.WHITE, "led-white"),
        GRAY(Color.LIGHTGRAY, "led-gray");

        public final Color  COLOR;
        public final String STYLE_CLASS;

        private LedColor(final Color COLOR, final String STYLE_CLASS) {
            this.COLOR       = COLOR;
            this.STYLE_CLASS = STYLE_CLASS;
        }
    }

    private BooleanProperty          on;
    private BooleanProperty          blinking;
    private BooleanProperty          frameVisible;
    private ObjectProperty<LedColor> color;
    private BooleanProperty          keepAspect;
    private LongProperty             interval;
    private long                     lastTimerCall;
    private boolean                  toggle;
    private AnimationTimer           timer;


    // ******************** Constructors **************************************
    public Led() {
        getStyleClass().add("led");
        on            = new SimpleBooleanProperty(false);
        blinking      = new SimpleBooleanProperty(false);
        frameVisible  = new SimpleBooleanProperty(true);
        color         = new SimpleObjectProperty<>(LedColor.RED);
        keepAspect    = new SimpleBooleanProperty(true);
        interval      = new SimpleLongProperty(500_000_000);
        lastTimerCall = System.nanoTime();
        toggle        = false;
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
    public final boolean isKeepAspect() {
        return keepAspect.get();
    }
    public final void setKeepAspect(final boolean KEEP_ASPECT) {
        keepAspect.set(KEEP_ASPECT);
    }
    public final BooleanProperty keepAspectProperty() {
        return keepAspect;
    }

    @Override public boolean isResizable() {
        return true;
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
        return blinking.get();
    }
    public final void setBlinking(final boolean BLINKING) {
        blinking.set(BLINKING);
        if (BLINKING) {
            timer.start();
        } else {
            timer.stop();
        }
    }
    public final BooleanProperty blinkingProperty() {
        return blinking;
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
    public final void setFrameVisible(final boolean FRAMEVISIBLE) {
        frameVisible.set(FRAMEVISIBLE);
    }
    public final BooleanProperty frameVisibleProperty() {
        return frameVisible;
    }

    public final LedColor getColor() {
        return color.get();
    }
    public final void setColor(final LedColor LED_COLOR) {
        color.set(LED_COLOR);
    }
    public final ObjectProperty<LedColor> colorProperty() {
        return color;
    }


    // ******************** Utility Methods ***********************************
    public static long clamp(final long MIN, final long MAX, final long VALUE) {
        if (VALUE < MIN) return MIN;
        if (VALUE > MAX) return MAX;
        return VALUE;
    }


    // ******************** Style related *************************************
    @Override protected String getUserAgentStylesheet() {
        return getClass().getResource(getClass().getSimpleName().toLowerCase() + ".css").toExternalForm();
    }
}

