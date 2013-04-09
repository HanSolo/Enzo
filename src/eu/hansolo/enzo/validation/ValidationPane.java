package eu.hansolo.enzo.validation;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.util.Duration;


/**
 * Created by
 * User: hansolo
 * Date: 08.04.13
 * Time: 07:24
 */
public class ValidationPane extends Region {
    private static final int               IMG_SIZE = 12;
    private static final int               OFFSET   = 6;
    private static final Image             VALID    = new Image(ValidationPane.class.getResource("valid.png").toExternalForm(), IMG_SIZE, IMG_SIZE, true, false);
    private static final Image             INVALID  = new Image(ValidationPane.class.getResource("invalid.png").toExternalForm(), IMG_SIZE, IMG_SIZE, true, false);
    private Canvas                         canvas;
    private GraphicsContext                ctx;
    private ObservableMap<Node, Validator> validatorMap;
    private Timeline                       timeline;


    // ******************** Constructors **************************************
    public ValidationPane() {
        init();
        initGraphics();
        registerListeners();
    }

    private void init() {
        validatorMap = FXCollections.observableHashMap();
        timeline     = new Timeline();
    }

    private void initGraphics() {
        setMouseTransparent(true);
        canvas            = new Canvas(getPrefWidth(), getPrefHeight());
        ctx               = canvas.getGraphicsContext2D();
        DropShadow shadow = new DropShadow();
        shadow.setRadius(3);
        shadow.setColor(Color.rgb(0, 0, 0, 0.3));
        shadow.setBlurType(BlurType.TWO_PASS_BOX);
        shadow.setOffsetY(1);
        canvas.setEffect(shadow);

        getChildren().setAll(canvas);
    }

    private void registerListeners() {
        widthProperty().addListener(observable -> {
            canvas.setWidth(getWidth());
            draw();
        });
        heightProperty().addListener(observable -> {
            canvas.setHeight(getHeight());
            draw();
        });
        validatorMap.addListener((Observable observable) -> { draw(); });
    }


    // ******************** Methods *******************************************
    public void add(final Node NODE) {
        add(Pos.TOP_LEFT, NODE);
    }
    public void add(final Pos POSITION, final Node NODE) {
        if (validatorMap.keySet().contains(NODE)) return;
        validatorMap.put(NODE, new Validator(false, POSITION));
    }
    public void addAll(final Node... NODES) {
        addAll(Pos.TOP_LEFT, NODES);
    }
    public void addAll(final Pos POS, final Node... NODES) {
        for (Node node : NODES) {
            add(POS, node);
        }
    }

    public void remove(final Node NODE) {
        if (validatorMap.containsKey(NODE)) validatorMap.remove(NODE);
    }
    public void clear() {
        validatorMap.clear();
    }

    public boolean isValid(final Node NODE) {
        return (validatorMap.keySet().contains(NODE)) ? validatorMap.get(NODE).isValid() : false;
    }
    public void setValid(final Node NODE, final boolean VALID) {
        if (validatorMap.keySet().contains(NODE)) {
            validatorMap.get(NODE).setValid(VALID);
            fireValidationEvent(new ValidationEvent(NODE, this, null, VALID ? ValidationEvent.VALID : ValidationEvent.INVALID));
            validatorMap.get(NODE).setAlpha(1.0);
            draw();
            if (VALID) {
                fadeOut(NODE);
            } else {
                timeline.stop();
            }
        }
    }

    private void fadeOut(final Node NODE) {
        validatorMap.get(NODE).alphaProperty().addListener(observable -> { draw(); });

        KeyValue keyValueVisible   = new KeyValue(validatorMap.get(NODE).alphaProperty(), 1);
        KeyValue keyValueInvisible = new KeyValue(validatorMap.get(NODE).alphaProperty(), 0, Interpolator.EASE_IN);

        KeyFrame kf1 = new KeyFrame(Duration.millis(0), keyValueVisible);
        KeyFrame kf2 = new KeyFrame(Duration.millis(1000), keyValueVisible);
        KeyFrame kf3 = new KeyFrame(Duration.millis(1500), keyValueInvisible);

        timeline.getKeyFrames().setAll(kf1, kf2, kf3);
        timeline.play();
    }

    private void draw() {
        ctx.clearRect(0, 0, getWidth(), getHeight());

        if (validatorMap.isEmpty()) return;

        double[] indicatorPos = new double[2];
        for (Node node : validatorMap.keySet()) {
            Validator validator = validatorMap.get(node);
            ctx.save();
            ctx.setGlobalAlpha(validator.getAlpha());
            Point2D nodeMinPos = node.localToScene(node.getLayoutBounds().getMinX(), node.getLayoutBounds().getMinY());
            Point2D nodeMaxPos = node.localToScene(node.getLayoutBounds().getMaxX(), node.getLayoutBounds().getMaxY());

            if (nodeMinPos.getX() > 0 && nodeMinPos.getY() > 0) {
                if (Pos.CENTER_LEFT == validator.getValidatorPosition()) {
                    indicatorPos[0] = nodeMinPos.getX() - OFFSET;
                    indicatorPos[1] = nodeMinPos.getY() + (node.getLayoutBounds().getHeight() - IMG_SIZE) * 0.5;
                } else if (Pos.BOTTOM_LEFT == validator.getValidatorPosition()) {
                    indicatorPos[0] = nodeMinPos.getX() - OFFSET;
                    indicatorPos[1] = nodeMaxPos.getY() - OFFSET;
                } else if (Pos.TOP_RIGHT == validator.getValidatorPosition()) {
                    indicatorPos[0] = nodeMaxPos.getX() - OFFSET;
                    indicatorPos[1] = nodeMinPos.getY() - OFFSET;
                } else if (Pos.CENTER_RIGHT == validator.getValidatorPosition()) {
                    indicatorPos[0] = nodeMaxPos.getX() - OFFSET;
                    indicatorPos[1] = nodeMinPos.getY() + (node.getLayoutBounds().getHeight() - IMG_SIZE) * 0.5;
                } else if (Pos.BOTTOM_RIGHT == validator.getValidatorPosition()) {
                    indicatorPos[0] = nodeMaxPos.getX() - OFFSET;
                    indicatorPos[1] = nodeMaxPos.getY() - OFFSET;
                } else if (Pos.TOP_CENTER == validator.getValidatorPosition()) {
                    indicatorPos[0] = nodeMinPos.getX() + (node.getLayoutBounds().getWidth()) * 0.5 - OFFSET;
                    indicatorPos[1] = nodeMinPos.getY() - OFFSET;
                } else if (Pos.BOTTOM_CENTER == validator.getValidatorPosition()) {
                    indicatorPos[0] = nodeMinPos.getX() + (node.getLayoutBounds().getWidth()) * 0.5 - OFFSET;
                    indicatorPos[1] = nodeMaxPos.getY() - OFFSET;
                } else {
                    indicatorPos[0] = nodeMinPos.getX() - OFFSET;
                    indicatorPos[1] = nodeMinPos.getY() - OFFSET;
                }
                // @TODO optimize drawing by using ctx.clearRect(x, y, w, h) on each image instead of complete canvas
                ctx.drawImage(validatorMap.get(node).isValid() ? VALID : INVALID, indicatorPos[0], indicatorPos[1]);
                ctx.restore();
            }
        }
    }


    // ******************** Event Handling ************************************
    public final ObjectProperty<EventHandler<ValidationEvent>> onValidProperty() { return onValid; }
    public final void setOnValid(EventHandler<ValidationEvent> value) { onValidProperty().set(value); }
    public final EventHandler<ValidationEvent> getOnValid() { return onValidProperty().get(); }
    private ObjectProperty<EventHandler<ValidationEvent>> onValid = new ObjectPropertyBase<EventHandler<ValidationEvent>>() {
        @Override public Object getBean() { return this; }
        @Override public String getName() { return "onValid";}
    };

    public final ObjectProperty<EventHandler<ValidationEvent>> onInvalidProperty() { return onInvalid; }
    public final void setOnInvalid(EventHandler<ValidationEvent> value) { onInvalidProperty().set(value); }
    public final EventHandler<ValidationEvent> getOnInvalid() { return onInvalidProperty().get(); }
    private ObjectProperty<EventHandler<ValidationEvent>> onInvalid = new ObjectPropertyBase<EventHandler<ValidationEvent>>() {
        @Override public Object getBean() { return this; }
        @Override public String getName() { return "onInvalid";}
    };

    public void fireValidationEvent(final ValidationEvent EVENT) {
        final EventType             TYPE = EVENT.getEventType();
        final EventHandler<ValidationEvent> HANDLER;
        if (ValidationEvent.VALID == TYPE) {
            HANDLER = getOnValid();
        } else if (ValidationEvent.INVALID == TYPE) {
            HANDLER = getOnInvalid();
        } else {
            HANDLER = null;
        }

        if (HANDLER != null) {
            HANDLER.handle(EVENT);
        }
    }


    // ******************** Inner Classes *************************************
    public static class ValidationEvent extends Event {
        public static final EventType<ValidationEvent> VALID    = new EventType(ANY, "valid");
        public static final EventType<ValidationEvent> INVALID  = new EventType(ANY, "invalid");
        private Node node;


        // ******************* Constructors ***************************************
        public ValidationEvent(final Node NODE, final Object SOURCE, final EventTarget TARGET, final EventType<ValidationEvent> EVENT_TYPE) {
            super(SOURCE, TARGET, EVENT_TYPE);
            node = NODE;
        }


        // ******************* Methods ****************************************
        public Node getNode() {
            return node;
        }

    }

    public static class Validator {
        private DoubleProperty alpha;
        private boolean        valid;
        private Pos            validatorPosition;


        // ******************** Constructors **********************************
        public Validator(final boolean VALID) {
            this(VALID, Pos.TOP_LEFT);
        }
        public Validator(final boolean VALID, final Pos POSITION) {
            alpha = new SimpleDoubleProperty(1.0);
            valid             = VALID;
            validatorPosition = POSITION;
        }


        // ******************** Methods ***************************************
        public boolean isValid() {
            return valid;
        }
        public void setValid(final boolean VALID) {
            valid = VALID;
        }

        public Pos getValidatorPosition() {
            return validatorPosition;
        }
        public void setValidatorPosition(final Pos VALIDATOR_POSITION) {
            validatorPosition = VALIDATOR_POSITION;
        }

        public double getAlpha() {
            return alpha.get();
        }
        public void setAlpha(final double ALPHA) {
            alpha.set(ALPHA);
        }
        public DoubleProperty alphaProperty() {
            return alpha;
        }
    }
}
