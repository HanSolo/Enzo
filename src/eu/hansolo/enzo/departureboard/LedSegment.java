package eu.hansolo.enzo.departureboard;

import eu.hansolo.enzo.led.Led;
import eu.hansolo.enzo.led.LedBuilder;
import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
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
        setCenterShape(true);
        setSpacing(0);
        getChildren().setAll(leftLed, rightLed);

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
