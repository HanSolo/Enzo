package eu.hansolo.enzo.signaltower.skin;

import eu.hansolo.enzo.common.Util;
import eu.hansolo.enzo.signaltower.SignalTower;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;


public class SignalTowerSkin extends SkinBase<SignalTower> implements Skin<SignalTower> {
    private static final double MINIMUM_WIDTH    = 5;
    private static final double MINIMUM_HEIGHT   = 5;
    private static final double MAXIMUM_WIDTH    = 1024;
    private static final double MAXIMUM_HEIGHT   = 1024;
    private static final double PREFERRED_WIDTH  = 150;
    private static final double PREFERRED_HEIGHT = 591;
    private double              aspectRatio;
    private double              size;
    private double              width;
    private double              height;
    private Pane                pane;

    private Region              green;
    private Region              yellow;
    private Region              red;
    private Region              rack;
    private Region              body;
    private Region              roof;
    private DropShadow          bodyDropShadow;
    private InnerShadow         bodyInnerShadow;


    // ******************** Constructors **************************************
    public SignalTowerSkin(final SignalTower CONTROL) {
        super(CONTROL);
        aspectRatio  = PREFERRED_HEIGHT / PREFERRED_WIDTH;
        init();
        initGraphics();
        registerListeners();
    }


    // ******************** Initialization ************************************
    private void init() {
        if (Double.compare(getSkinnable().getPrefWidth(), 0.0) <= 0 || Double.compare(getSkinnable().getPrefHeight(), 0.0) <= 0 ||
            Double.compare(getSkinnable().getWidth(), 0.0) <= 0 || Double.compare(getSkinnable().getHeight(), 0.0) <= 0) {
            if (getSkinnable().getPrefWidth() > 0 && getSkinnable().getPrefHeight() > 0) {
                getSkinnable().setPrefSize(getSkinnable().getPrefWidth(), getSkinnable().getPrefHeight());
            } else {
                getSkinnable().setPrefSize(PREFERRED_WIDTH, PREFERRED_HEIGHT);
            }
        }

        if (Double.compare(getSkinnable().getMinWidth(), 0.0) <= 0 || Double.compare(getSkinnable().getMinHeight(), 0.0) <= 0) {
            getSkinnable().setMinSize(MINIMUM_WIDTH, MINIMUM_HEIGHT);
        }

        if (Double.compare(getSkinnable().getMaxWidth(), 0.0) <= 0 || Double.compare(getSkinnable().getMaxHeight(), 0.0) <= 0) {
            getSkinnable().setMaxSize(MAXIMUM_WIDTH, MAXIMUM_HEIGHT);
        }
    }

    private void initGraphics() {
        green = new Region();
        green.getStyleClass().setAll("green");

        yellow = new Region();
        yellow.getStyleClass().setAll("yellow");

        red = new Region();
        red.getStyleClass().setAll("red");

        rack = new Region();
        rack.getStyleClass().setAll("rack");

        bodyDropShadow = new DropShadow(BlurType.TWO_PASS_BOX, Color.web("0x000000a6"), 0.0133333333 * PREFERRED_WIDTH, 1.0, 0d, 2d);

        bodyInnerShadow = new InnerShadow(BlurType.TWO_PASS_BOX, Color.web("0x000000a6"), 0.0133333333 * PREFERRED_WIDTH, 1.0, 1.4142135623730951, 1.4142135623730951);
        bodyInnerShadow.setInput(bodyDropShadow);

        body = new Region();
        body.getStyleClass().setAll("body");
        body.setEffect(bodyInnerShadow);

        roof = new Region();
        roof.getStyleClass().setAll("roof");

        pane = new Pane();
        pane.getChildren().setAll(green,
                                  yellow,
                                  red,
                                  rack,
                                  body,
                                  roof);

        getChildren().setAll(pane);
        resize();
    }

    private void registerListeners() {
        getSkinnable().widthProperty().addListener(observable -> handleControlPropertyChanged("RESIZE") );
        getSkinnable().heightProperty().addListener(observable -> handleControlPropertyChanged("RESIZE") );

    }


    // ******************** Methods *******************************************
    protected void handleControlPropertyChanged(final String PROPERTY) {
        if ("RESIZE".equals(PROPERTY)) {
            resize();
        }
    }

    @Override protected double computeMinWidth(final double HEIGHT, double TOP_INSET, double RIGHT_INSET, double BOTTOM_INSET, double LEFT_INSET) {
        return super.computeMinWidth(Math.max(MINIMUM_HEIGHT, HEIGHT - TOP_INSET - BOTTOM_INSET), TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET);
    }
    @Override protected double computeMinHeight(final double WIDTH, double TOP_INSET, double RIGHT_INSET, double BOTTOM_INSET, double LEFT_INSET) {
        return super.computeMinHeight(Math.max(MINIMUM_WIDTH, WIDTH - LEFT_INSET - RIGHT_INSET), TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET);
    }

    @Override protected double computeMaxWidth(final double HEIGHT, double TOP_INSET, double RIGHT_INSET, double BOTTOM_INSET, double LEFT_INSET) {
        return super.computeMaxWidth(Math.min(MAXIMUM_HEIGHT, HEIGHT - TOP_INSET - BOTTOM_INSET), TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET);
    }
    @Override protected double computeMaxHeight(final double WIDTH, double TOP_INSET, double RIGHT_INSET, double BOTTOM_INSET, double LEFT_INSET) {
        return super.computeMaxHeight(Math.min(MAXIMUM_WIDTH, WIDTH - LEFT_INSET - RIGHT_INSET), TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET);
    }

    @Override protected double computePrefWidth(final double HEIGHT, double TOP_INSET, double RIGHT_INSET, double BOTTOM_INSET, double LEFT_INSET) {
        double prefHeight = PREFERRED_HEIGHT;
        if (HEIGHT != -1) {
            prefHeight = Math.max(0, HEIGHT - TOP_INSET - BOTTOM_INSET);
        }
        return super.computePrefWidth(prefHeight, TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET);
    }
    @Override protected double computePrefHeight(final double WIDTH, double TOP_INSET, double RIGHT_INSET, double BOTTOM_INSET, double LEFT_INSET) {
        double prefWidth = PREFERRED_WIDTH;
        if (WIDTH != -1) {
            prefWidth = Math.max(0, WIDTH - LEFT_INSET - RIGHT_INSET);
        }
        return super.computePrefHeight(prefWidth, TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET);
    }


    // ******************** Resizing ******************************************
    private void resize() {
        size   = getSkinnable().getWidth() < getSkinnable().getHeight() ? getSkinnable().getWidth() : getSkinnable().getHeight();
        width  = getSkinnable().getWidth();
        height = getSkinnable().getHeight();
        if (getSkinnable().isKeepAspect()) {
            if (aspectRatio * width > height) {
                width  = 1 / (aspectRatio / height);
            } else if (1 / (aspectRatio / height) > width) {
                height = aspectRatio * width;
            }
        }
        if (width > 0 && height > 0) {
            green.setPrefSize(0.5333333333333333 * width, 0.11844331641285956 * height);
            green.setTranslateX(0.26 * width);
            green.setTranslateY(0.350253807106599 * height);

            yellow.setPrefSize(0.5333333333333333 * width, 0.11844331641285956 * height);
            yellow.setTranslateX(0.26 * width);
            yellow.setTranslateY(0.22504230118443316 * height);

            red.setPrefSize(0.5333333333333333 * width, 0.11844331641285956 * height);
            red.setTranslateX(0.26 * width);
            red.setTranslateY(0.09475465313028765 * height);

            rack.setPrefSize(0.02666666666666667 * width, 0.3824027072758037 * height);
            rack.setTranslateX(0.4266666666666667 * width);
            rack.setTranslateY(0.09475465313028765 * height);

            bodyDropShadow.setRadius(Util.clamp(1, 3, 0.0133333333 * size));
            bodyInnerShadow.setRadius(Util.clamp(1, 3, 0.0133333333 * size));

            body.setPrefSize(0.6133333333333333 * width, 0.9111675126903553 * height);
            body.setTranslateX(0.22 * width);
            body.setTranslateY(0.03976311336717428 * height);

            roof.setPrefSize(0.6133333333333333 * width, 0.018612521150592216 * height);
            roof.setTranslateX(0.22 * width);
            roof.setTranslateY(0.028764805414551606 * height);
        }
    }
}
