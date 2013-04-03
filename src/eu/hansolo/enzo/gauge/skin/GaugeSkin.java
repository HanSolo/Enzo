package eu.hansolo.enzo.gauge.skin;

import com.sun.javafx.scene.control.skin.BehaviorSkinBase;
import eu.hansolo.enzo.gauge.Gauge;
import eu.hansolo.enzo.gauge.GaugeModel;
import eu.hansolo.enzo.gauge.behavior.GaugeBehavior;
import eu.hansolo.enzo.tools.ShapeConverter;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Group;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadowBuilder;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RegionBuilder;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
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
    private Region                                   needleHighlight;
    private Rotate                                   needleRotate;
    private double                                   angleStep;
    private Region                                   knob;
    private Text                                     title;
    private Text                                     unit;
    private Timeline                                 timeline;
    private EventHandler<GaugeModel.GaugeModelEvent> gaugeModelHandler;


    // ******************** Constructors **************************************
    public GaugeSkin(Gauge gauge) {
        super(gauge, new GaugeBehavior(gauge));
        control   = gauge;
        pane      = new Pane();
        angleStep = 0;
        timeline  = new Timeline();
        init();
        initGaugeModelHandler();
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

    private void initGaugeModelHandler() {
        gaugeModelHandler = new EventHandler<GaugeModel.GaugeModelEvent>() {
            @Override public void handle(final GaugeModel.GaugeModelEvent EVENT) {
                final EventType TYPE = EVENT.getEventType();
                if (GaugeModel.GaugeModelEvent.VALUE_CHANGED == TYPE) {
                    rotateNeedle();
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

                } else if (GaugeModel.GaugeModelEvent.ALWAYS_ROUND_CHANGED == TYPE) {
                    changeBackgroundShape();
                } else if (GaugeModel.GaugeModelEvent.START_ANGLE_CHANGED == TYPE) {
                    changeBackgroundShape();
                } else if (GaugeModel.GaugeModelEvent.ANGLE_RANGE_CHANGED == TYPE) {
                    changeBackgroundShape();
                } else if (GaugeModel.GaugeModelEvent.ANIMATED == TYPE) {

                } else if (GaugeModel.GaugeModelEvent.NEEDLE_TYPE_CHANGED == TYPE) {
                    changeNeedle();
                }
            }
        };
    }

    private void initGraphics() {
        background = new Region();
        background.getStyleClass().setAll("background");

        ticks = RegionBuilder.create().styleClass("ticks").build();

        needle = new Region();
        needle.getStyleClass().setAll(Gauge.STYLE_CLASS_NEEDLE_STANDARD);
        needleRotate = new Rotate(-control.getGaugeModel().getStartAngle());
        needle.getTransforms().setAll(needleRotate);

        needleHighlight = new Region();
        needleHighlight.getStyleClass().setAll("needle-highlight");
        needleHighlight.getTransforms().setAll(needleRotate);

        knob = new Region();
        knob.getStyleClass().setAll("knob");

        Group shadowGroup = new Group(needle, needleHighlight, knob);
        shadowGroup.setEffect(DropShadowBuilder.create()
                                               .color(Color.rgb(0, 0, 0, 0.25))
                                               .radius(3)
                                               .blurType(BlurType.GAUSSIAN)
                                               .offsetY(3)
                                               .build());

        title = new Text(control.getGaugeModel().getTitle());
        title.getStyleClass().setAll("title");

        unit = new Text(control.getGaugeModel().getUnit());
        unit.getStyleClass().setAll("unit");

        // Add all nodes
        pane.getChildren().setAll(background, ticks, title, unit, shadowGroup);

        getChildren().setAll(pane);
    }

    private void registerListeners() {
        registerChangeListener(control.widthProperty(), "RESIZE");
        registerChangeListener(control.heightProperty(), "RESIZE");
        control.getGaugeModel().setOnGaugeModelChanged(gaugeModelHandler);
        control.gaugeModelProperty().addListener(new InvalidationListener() {
            @Override public void invalidated(Observable observable) {
                registerListeners();
            }
        });
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
        double rotationOffset = control.getGaugeModel().getStartAngle();
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
            default:
                needle.getStyleClass().setAll(Gauge.STYLE_CLASS_NEEDLE_STANDARD);
        }
    }

    private void changeBackgroundShape() {
        if (control.getGaugeModel().isAlwaysRound()) {
            control.setStyle("-shape: \"M 0 100 C 0 44.7708 44.7708 0 100 0 C 155.2292 0 200 44.7708 200 100 C 200 155.2292 155.2292 200 100 200 C 44.7708 200 0 155.2292 0 100 Z\";");
        } else {
            final Arc ARC = new Arc();
            ARC.setCenterX(size * 0.5);
            ARC.setCenterY(size * 0.5);
            ARC.setRadiusX(size * 0.5);
            ARC.setRadiusY(size * 0.5);
            ARC.setStartAngle(control.getGaugeModel().getStartAngle());
            ARC.setLength(control.getGaugeModel().getAngleRange());
            ARC.setType(ArcType.ROUND);
            control.setStyle("-shape: \"" + ShapeConverter.convertArc(ARC) + "\";");
        }
    }

    private void resize() {
        size = control.getWidth() < control.getHeight() ? control.getWidth() : control.getHeight();

        double emptySegmentHeight = size * (1.0 - Math.cos(Math.toRadians((360 - control.getGaugeModel().getAngleRange()) * 0.5)));
        background.setPrefSize(size, size);

        switch (control.getGaugeModel().getNeedleType()) {
            default:
                needle.setPrefSize(size * 0.04, size * 0.45);
        }
        needle.setTranslateX((size - needle.getPrefWidth()) * 0.5);
        needle.setTranslateY(size * 0.5 - needle.getPrefHeight());
        needleRotate.setPivotX((needle.getPrefWidth()) * 0.5);
        needleRotate.setPivotY(needle.getPrefHeight());

        needleHighlight.setPrefSize(size * 0.04, size * 0.45);
        needleHighlight.setTranslateX((size - needle.getPrefWidth()) * 0.5);
        needleHighlight.setTranslateY(size * 0.5 - needle.getPrefHeight());

        knob.setPrefSize(size * 0.085, size * 0.085);
        knob.setTranslateX((size - knob.getPrefWidth()) * 0.5);
        knob.setTranslateY((size - knob.getPrefHeight()) * 0.5);

        title.setFont(Font.font("Arial", FontWeight.NORMAL, size * 0.06));
        title.setTranslateX((size - title.getLayoutBounds().getWidth()) * 0.5);
        title.setTranslateY(size * 0.35);

        unit.setFont(Font.font("Arial", FontWeight.NORMAL, size * 0.05));
        unit.setTranslateX((size - unit.getLayoutBounds().getWidth()) * 0.5);
        unit.setTranslateY(size * 0.65);
    }
}
