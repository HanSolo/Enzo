package eu.hansolo.enzo.gauge.skin;

import com.sun.javafx.scene.control.skin.BehaviorSkinBase;
import eu.hansolo.enzo.gauge.Gauge;
import eu.hansolo.enzo.gauge.GaugeModel;
import eu.hansolo.enzo.gauge.Radial;
import eu.hansolo.enzo.gauge.behavior.GaugeBehavior;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;


/**
 * Created by
 * User: hansolo
 * Date: 01.04.13
 * Time: 17:18
 */
public class GaugeSkin extends BehaviorSkinBase<Gauge, GaugeBehavior> {
    private static final double                      DEFAULT_WIDTH  = 200;
    private static final double                      DEFAULT_HEIGHT = 200;
    private static final double                      MINIMUM_WIDTH  = 50;
    private static final double                      MINIMUM_HEIGHT = 50;
    private static final double                      MAXIMUM_WIDTH  = 1024;
    private static final double                      MAXIMUM_HEIGHT = 1024;
    private Gauge                                    control;
    private double                                   size;
    private Pane                                     pane;
    private Region                                   background;
    private Region                                   ticks;
    private Region                                   needle;
    private Rotate                                   needleRotate;
    private double                                   angleStep;
    private Region                                   knob;
    private Region                                   knobHighlight;
    private Text                                     title;
    private Timeline                                 timeline;
    private EventHandler<GaugeModel.GaugeModelEvent> gaugeModelHandler;


    // ******************** Constructors **************************************
    protected GaugeSkin(Gauge gauge, GaugeBehavior gaugeBehavior) {
        super(gauge, gaugeBehavior);
        control = gauge;
        control.gaugeModelProperty().addListener(new InvalidationListener() {
            @Override public void invalidated(Observable observable) {
                registerListeners();
            }
        });
        gaugeModelHandler = new EventHandler<GaugeModel.GaugeModelEvent>() {
            @Override public void handle(final GaugeModel.GaugeModelEvent EVENT) {
                final EventType TYPE = EVENT.getEventType();
                if (GaugeModel.GaugeModelEvent.VALUE_CHANGED == TYPE) {

                } else if (GaugeModel.GaugeModelEvent.MIN_VALUE_CHANGED == TYPE) {

                } else if (GaugeModel.GaugeModelEvent.MAX_VALUE_CHANGED == TYPE) {

                } else if (GaugeModel.GaugeModelEvent.THRESHOLD_CHANGED == TYPE) {

                } else if (GaugeModel.GaugeModelEvent.THRESHOLD_EXCEEDED == TYPE) {

                } else if (GaugeModel.GaugeModelEvent.THRESHOLD_UNDERRUN == TYPE) {

                } else if (GaugeModel.GaugeModelEvent.MIN_MEASURED_VALUE_CHANGE == TYPE) {

                } else if (GaugeModel.GaugeModelEvent.MAX_MEASURED_VALUE_CHANGED == TYPE) {

                } else if (GaugeModel.GaugeModelEvent.NO_OF_DECIMALS_CHANGED == TYPE) {

                } else if (GaugeModel.GaugeModelEvent.TITLE_CHANGED == TYPE) {

                } else if (GaugeModel.GaugeModelEvent.UNIT_CHANGED == TYPE) {

                } else if (GaugeModel.GaugeModelEvent.ANIMATED == TYPE) {

                } else if (GaugeModel.GaugeModelEvent.NEEDLE_TYPE_CHANGE == TYPE) {
                    changeNeedle();
                }
            }
        };
        init();
        initGraphics();
        registerListeners();
    }


    // ******************** Initialization ************************************
    private void init() {
        if (Double.compare(control.getPrefWidth(), 0.0) <= 0 || Double.compare(control.getPrefHeight(), 0.0) <= 0 ||
            Double.compare(control.getWidth(), 0.0) <= 0 || Double.compare(control.getHeight(), 0.0) <= 0) {
            if (control.getPrefWidth() > 0 && control.getPrefHeight() > 0) {
                control.setPrefSize(control.getPrefWidth(), control.getPrefHeight());
            } else {
                control.setPrefSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
            }
        }

        if (Double.compare(control.getMinWidth(), 0.0) <= 0 || Double.compare(control.getMinHeight(), 0.0) <= 0) {
            control.setMinSize(MINIMUM_WIDTH, MINIMUM_HEIGHT);
        }

        if (Double.compare(control.getMaxWidth(), 0.0) <= 0 || Double.compare(control.getMaxHeight(), 0.0) <= 0) {
            control.setMaxSize(MAXIMUM_WIDTH, MAXIMUM_HEIGHT);
        }
    }

    private void initGraphics() {

    }

    private void registerListeners() {
        registerChangeListener(control.widthProperty(), "RESIZE");
        registerChangeListener(control.heightProperty(), "RESIZE");
        control.getGaugeModel().setOnGaugeModelChanged(gaugeModelHandler);
    }


    // ******************** Methods *******************************************
    @Override protected void handleControlPropertyChanged(final String PROPERTY) {
        super.handleControlPropertyChanged(PROPERTY);
        if ("RESIZE".equals(PROPERTY)) {
            resize();
        }
    }

    @Override public final void dispose() {
        control = null;
    }

    @Override protected double computePrefWidth(final double PREF_HEIGHT) {
        double prefHeight = DEFAULT_HEIGHT;
        if (PREF_HEIGHT != -1) {
            prefHeight = Math.max(0, PREF_HEIGHT - control.getInsets().getTop() - control.getInsets().getBottom());
        }
        return super.computePrefWidth(prefHeight);
    }

    @Override protected double computePrefHeight(final double PREF_WIDTH) {
        double prefWidth = DEFAULT_WIDTH;
        if (PREF_WIDTH != -1) {
            prefWidth = Math.max(0, PREF_WIDTH - control.getInsets().getLeft() - control.getInsets().getRight());
        }
        return super.computePrefWidth(prefWidth);
    }

    @Override protected double computeMinWidth(final double MIN_HEIGHT) {
        return super.computeMinWidth(Math.max(MINIMUM_HEIGHT, MIN_HEIGHT - control.getInsets().getTop() - control.getInsets().getBottom()));
    }

    @Override protected double computeMinHeight(final double MIN_WIDTH) {
        return super.computeMinHeight(Math.max(MINIMUM_WIDTH, MIN_WIDTH - control.getInsets().getLeft() - control.getInsets().getRight()));
    }

    @Override protected double computeMaxWidth(final double MAX_HEIGHT) {
        return super.computeMaxWidth(Math.min(MAXIMUM_HEIGHT, MAX_HEIGHT - control.getInsets().getTop() - control.getInsets().getBottom()));
    }

    @Override protected double computeMaxHeight(final double MAX_WIDTH) {
        return super.computeMaxHeight(Math.min(MAXIMUM_WIDTH, MAX_WIDTH - control.getInsets().getLeft() - control.getInsets().getRight()));
    }


    // ******************** Private Methods ***********************************
    private void rotateNeedle() {
        double range          = (control.getGaugeModel().getMaxValue() - control.getGaugeModel().getMinValue());
        double angleRange     = control.getGaugeModel().getAngleRange();
        double rotationOffset = control.getGaugeModel().getRotationOffset();
        angleStep             = angleRange / range;
        double targetAngle    = control.getGaugeModel().getValue() * angleStep - rotationOffset;

        if (control.getGaugeModel().isAnimated()) {
            timeline.stop();
            final KeyValue KEY_VALUE = new KeyValue(needleRotate.angleProperty(), targetAngle, Interpolator.SPLINE(0.5, 0.4, 0.4, 1.0));
            final KeyFrame KEY_FRAME = new KeyFrame(Duration.millis(800), KEY_VALUE);
            timeline.getKeyFrames().setAll(KEY_FRAME);
            timeline.getKeyFrames().add(KEY_FRAME);
            timeline.play();
        } else {
            needleRotate.setAngle(targetAngle);
        }
    }

    private void changeNeedle() {
        switch(control.getGaugeModel().getNeedleType()) {
            case ARROW:
                needle.getStyleClass().setAll(Radial.STYLE_CLASS_NEEDLE_ARROW);
                break;
            case SCIENTIFIC:
                needle.getStyleClass().setAll(Radial.STYLE_CLASS_NEEDLE_SCIENTIFIC);
                break;
            case BIG:
                needle.getStyleClass().setAll(Radial.STYLE_CLASS_NEEDLE_BIG);
                break;
            default:
                needle.getStyleClass().setAll(Radial.STYLE_CLASS_NEEDLE_STANDARD);
        }
    }

    private void resize() {
        size = control.getWidth() < control.getHeight() ? control.getWidth() : control.getHeight();

        background.setPrefSize(size, size);

        switch (control.getGaugeModel().getNeedleType()) {
            case ARROW:
                needle.setPrefSize(size * 0.07, size * 0.45);
                break;
            case SCIENTIFIC:
                needle.setPrefSize(size * 0.025, size * 0.45);
                break;
            case BIG:
                needle.setPrefSize(size * 0.08, size * 0.45);
                break;
            default:
                needle.setPrefSize(size * 0.04, size * 0.45);
        }
        needle.setTranslateX((size - needle.getPrefWidth()) * 0.5);
        needle.setTranslateY(size * 0.5 - needle.getPrefHeight());
        needleRotate.setPivotX((needle.getPrefWidth()) * 0.5);
        needleRotate.setPivotY(needle.getPrefHeight());

        knob.setPrefSize(size * 0.085, size * 0.085);
        knob.setTranslateX((size - knob.getPrefWidth()) * 0.5);
        knob.setTranslateY((size - knob.getPrefHeight()) * 0.5);

        knobHighlight.setPrefSize(size * 0.08, size * 0.065);
        knobHighlight.setTranslateX((size - knobHighlight.getPrefWidth()) * 0.5);
        knobHighlight.setTranslateY(size * 0.462);

        title.setFont(Font.font("Verdana", FontWeight.NORMAL, size * 0.06));
        title.setTranslateX((size - title.getLayoutBounds().getWidth()) * 0.5);
        title.setTranslateY(size * 0.3);
    }
}
