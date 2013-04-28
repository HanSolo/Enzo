package eu.hansolo.enzo.touchbutton.skin;

import eu.hansolo.enzo.touchbutton.TouchButton;
import javafx.collections.ListChangeListener;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;


public class TouchButtonSkin extends SkinBase<TouchButton> implements Skin<TouchButton> {
    private static final double MINIMUM_WIDTH    = 25;
    private static final double MINIMUM_HEIGHT   = 25;
    private static final double MAXIMUM_WIDTH    = 1024;
    private static final double MAXIMUM_HEIGHT   = 1024;
    private static final double PREFERRED_WIDTH  = 380;
    private static final double PREFERRED_HEIGHT = 390;
    private double              size;
    private double              width;
    private double              height;
    private Pane                pane;

    private Region              background;
    private InnerShadow         backgroundInnerShadow0;
    private InnerShadow         backgroundInnerShadow1;
    private InnerShadow         backgroundInnerShadow2;

    private Region              offBody;
    private DropShadow          offBodyDropShadow0;
    private InnerShadow         offBodyInnerShadow1;

    private Region              onBody;
    private InnerShadow         onBodyInnerShadow0;
    private InnerShadow         onBodyInnerShadow1;

    private Region              icon;
    private InnerShadow         iconInnerShadow;
    private DropShadow          iconDropShadow;


    // ******************** Constructors **************************************
    public TouchButtonSkin(final TouchButton CONTROL) {
        super(CONTROL);
        pane         = new Pane();
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

        background = new Region();
        background.getStyleClass().setAll("background");

        backgroundInnerShadow0 = new InnerShadow();
        backgroundInnerShadow0.setOffsetX(0.0);
        backgroundInnerShadow0.setOffsetY(0.0);
        backgroundInnerShadow0.setRadius(20.0 / 380.0 * PREFERRED_WIDTH);
        backgroundInnerShadow0.setColor(Color.web("0x000000a6"));
        backgroundInnerShadow0.setBlurType(BlurType.GAUSSIAN);
        backgroundInnerShadow1 = new InnerShadow();
        backgroundInnerShadow1.setOffsetX(0);
        backgroundInnerShadow1.setOffsetY(-10.0 / 380.0 * PREFERRED_WIDTH);
        backgroundInnerShadow1.setRadius(10.0 / 380.0 * PREFERRED_WIDTH);
        backgroundInnerShadow1.setColor(Color.web("0xffffffff"));
        backgroundInnerShadow1.setBlurType(BlurType.GAUSSIAN);
        backgroundInnerShadow1.setInput(backgroundInnerShadow0);
        backgroundInnerShadow2 = new InnerShadow();
        backgroundInnerShadow2.setOffsetX(0);
        backgroundInnerShadow2.setOffsetY(15.0 / 380.0 * PREFERRED_WIDTH);
        backgroundInnerShadow2.setRadius(15.0 / 380.0 * PREFERRED_WIDTH);
        backgroundInnerShadow2.setColor(Color.web("0x00000026"));
        backgroundInnerShadow2.setBlurType(BlurType.GAUSSIAN);
        backgroundInnerShadow2.setInput(backgroundInnerShadow1);
        background.setEffect(backgroundInnerShadow2);
        background.setVisible(TouchButton.Status.SELECTED == getSkinnable().getStatus() ||
                              TouchButton.Status.OFF == getSkinnable().getStatus());

        offBody = new Region();
        offBody.getStyleClass().setAll("off-body");

        offBodyDropShadow0 = new DropShadow();
        offBodyDropShadow0.setOffsetX(0);
        offBodyDropShadow0.setOffsetY(20.0 / 380.0 * PREFERRED_WIDTH);
        offBodyDropShadow0.setRadius(20.0 / 380.0 * PREFERRED_WIDTH);
        offBodyDropShadow0.setColor(Color.web("0x000000a6"));
        offBodyDropShadow0.setBlurType(BlurType.GAUSSIAN);
        offBodyInnerShadow1 = new InnerShadow();
        offBodyInnerShadow1.setOffsetX(0.0);
        offBodyInnerShadow1.setOffsetY(0.0);
        offBodyInnerShadow1.setRadius(10.0 / 380.0 * PREFERRED_WIDTH);
        offBodyInnerShadow1.setColor(Color.web("0x000000a6"));
        offBodyInnerShadow1.setBlurType(BlurType.GAUSSIAN);
        offBodyInnerShadow1.setInput(offBodyDropShadow0);
        offBody.setEffect(offBodyInnerShadow1);
        offBody.setVisible(TouchButton.Status.UNSELECTED == getSkinnable().getStatus());

        onBody = new Region();
        onBody.getStyleClass().setAll("on-body");

        onBodyInnerShadow0 = new InnerShadow();
        onBodyInnerShadow0.setOffsetX(0.0);
        onBodyInnerShadow0.setOffsetY(0.0);
        onBodyInnerShadow0.setRadius(10.0 / 380.0 * PREFERRED_WIDTH);
        onBodyInnerShadow0.setColor(Color.web("0x000000a6"));
        onBodyInnerShadow0.setBlurType(BlurType.GAUSSIAN);
        onBodyInnerShadow1 = new InnerShadow();
        onBodyInnerShadow1.setOffsetX(0);
        onBodyInnerShadow1.setOffsetY(10.0 / 380.0 * PREFERRED_WIDTH);
        onBodyInnerShadow1.setRadius(15.0 / 380.0 * PREFERRED_WIDTH);
        onBodyInnerShadow1.setColor(Color.web("0x000000a6"));
        onBodyInnerShadow1.setBlurType(BlurType.GAUSSIAN);
        onBodyInnerShadow1.setInput(onBodyInnerShadow0);
        onBody.setEffect(onBodyInnerShadow1);
        onBody.setVisible(TouchButton.Status.SELECTED == getSkinnable().getStatus());

        icon = new Region();
        icon.setStyle("-icon-on-color: " + colorToCss(getSkinnable().getColor()));
        icon.setMouseTransparent(true);
        icon.setVisible(TouchButton.Status.UNSELECTED == getSkinnable().getStatus() ||
                        TouchButton.Status.SELECTED == getSkinnable().getStatus());
        iconInnerShadow = new InnerShadow();
        iconInnerShadow.setOffsetX(0);
        iconInnerShadow.setOffsetY(5.0 / 380.0 * PREFERRED_WIDTH);
        iconInnerShadow.setRadius(5.0 / 380.0 * PREFERRED_WIDTH);
        iconInnerShadow.setColor(Color.web("0x000000a6"));
        iconInnerShadow.setBlurType(BlurType.GAUSSIAN);
        iconDropShadow = new DropShadow();
        iconDropShadow.setOffsetX(0.0);
        iconDropShadow.setOffsetY(0.0);
        iconDropShadow.setRadius(30.0 / 380.0 * PREFERRED_WIDTH);
        iconDropShadow.setColor(getSkinnable().getColor());
        iconDropShadow.setBlurType(BlurType.GAUSSIAN);
        iconDropShadow.setInput(iconInnerShadow);

        switch(getSkinnable().getStatus()) {
            case UNSELECTED:
                background.setVisible(false);
                icon.getStyleClass().setAll("icon");
                icon.setEffect(iconInnerShadow);
                icon.setVisible(true);
                break;
            case SELECTED:
                background.setVisible(true);
                icon.getStyleClass().setAll("icon", "icon-selected");
                icon.setEffect(iconDropShadow);
                icon.setVisible(true);
                break;
            case OFF:
            default:
                background.setVisible(true);
                icon.setVisible(false);
                break;
        }

        pane.getChildren().setAll(background,
                                  offBody,
                                  onBody,
                                  icon);

        getChildren().setAll(pane);
    }

    private void registerListeners() {
        getSkinnable().widthProperty().addListener(observable -> { handleControlPropertyChanged("RESIZE"); });
        getSkinnable().heightProperty().addListener(observable -> { handleControlPropertyChanged("RESIZE"); });
        getSkinnable().prefWidthProperty().addListener(observable -> { handleControlPropertyChanged("PREF_SIZE"); });
        getSkinnable().prefHeightProperty().addListener(observable -> { handleControlPropertyChanged("PREF_SIZE"); });
        getSkinnable().statusProperty().addListener(observable -> { handleControlPropertyChanged("STATUS"); });
        getSkinnable().colorProperty().addListener(observable -> { handleControlPropertyChanged("COLOR"); });
        getSkinnable().getStyleClass().addListener(new ListChangeListener<String>() {
            @Override public void onChanged(Change<? extends String> change) {
                resize();
            }
        });
    }


    // ******************** Methods *******************************************
    protected void handleControlPropertyChanged(final String PROPERTY) {
        if ("RESIZE".equals(PROPERTY)) {
            resize();
        } else if ("STATUS".equals(PROPERTY)) {
            switch (getSkinnable().getStatus()) {
                case UNSELECTED:
                    background.setVisible(false);
                    onBody.setVisible(false);
                    offBody.setVisible(true);
                    icon.getStyleClass().setAll("icon");
                    icon.setEffect(iconInnerShadow);
                    icon.setVisible(true);
                    break;
                case SELECTED:
                    background.setVisible(true);
                    onBody.setVisible(true);
                    offBody.setVisible(false);
                    icon.getStyleClass().setAll("icon", "icon-selected");
                    icon.setEffect(iconDropShadow);
                    icon.setVisible(true);
                    break;
                case OFF:
                default:
                    background.setVisible(true);
                    onBody.setVisible(false);
                    offBody.setVisible(false);
                    icon.setVisible(false);
                    break;
            }
        } else if ("COLOR".equals(PROPERTY)) {
            iconDropShadow.setColor(getSkinnable().getColor());
            icon.setStyle("-icon-on-color: " + colorToCss(getSkinnable().getColor()));
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


    // ******************** Utility methods ***********************************
    private String colorToCss(final Color COLOR) {
        StringBuilder cssColor = new StringBuilder();
        cssColor.append("rgba(")
                .append((int) (COLOR.getRed() * 255)).append(", ")
                .append((int) (COLOR.getGreen() * 255)).append(", ")
                .append((int) (COLOR.getBlue() * 255)).append(", ")
                .append(COLOR.getOpacity()).append(")");
        return cssColor.toString();
    }


    // ******************** Resizing ******************************************
    private void resize() {
        size   = getSkinnable().getWidth() < getSkinnable().getHeight() ? getSkinnable().getWidth() : getSkinnable().getHeight();
        width  = size;
        height = size;

        if (size > 0) {
            background.setPrefSize(0.9473684210526315 * width, 0.9230769230769231 * height);
            background.setTranslateX(0.02631578947368421 * width);

            backgroundInnerShadow0.setRadius(20.0 / 380.0 * size);
            backgroundInnerShadow1.setRadius(10.0 / 380.0 * size);
            backgroundInnerShadow1.setOffsetY(-10.0 / 380.0 * size);
            backgroundInnerShadow2.setRadius(15.0 / 380.0 * size);
            backgroundInnerShadow2.setOffsetY(15.0 / 380.0 * size);

            offBody.setPrefSize(0.9210526315789473 * width, 0.8974358974358975 * height);
            offBody.setTranslateX(0.039473684210526314 * width);
            offBody.setTranslateY(0.01282051282051282 * height);
            offBodyDropShadow0.setRadius(15.0 / 380.0 * size);
            offBodyDropShadow0.setOffsetY(15.0 / 380.0 * size);
            offBodyInnerShadow1.setRadius(10.0 / 380.0 * size);

            onBody.setPrefSize(0.9210526315789473 * width, 0.8974358974358975 * height);
            onBody.setTranslateX(0.039473684210526314 * width);
            onBody.setTranslateY(0.010256410256410256 * height);
            onBodyInnerShadow0.setRadius(10.0 / 380.0 * size);
            onBodyInnerShadow1.setRadius(15.0 / 380.0 * size);
            onBodyInnerShadow1.setOffsetY(10.0 / 380.0 * size);

            icon.setPrefSize(0.4343404669510691 * width, 0.5487179487179488 * height);
            icon.setTranslateX(0.28421052631578947 * width);
            icon.setTranslateY(0.18717948717948718 * height);
            iconInnerShadow.setRadius(5.0 / 380.0 * size);
            iconInnerShadow.setOffsetY(5.0 / 380.0 * size);
            iconDropShadow.setRadius(30.0 / 380.0 * size);
        }
    }
}
