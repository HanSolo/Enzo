package eu.hansolo.enzo.onoffswitch.skin;

import eu.hansolo.enzo.common.Symbol;
import eu.hansolo.enzo.common.SymbolType;
import eu.hansolo.enzo.common.Util;
import eu.hansolo.enzo.onoffswitch.IconSwitch;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;



/**
 * User: hansolo
 * Date: 10.10.13
 * Time: 08:46
 */
public class IconSwitchSkin extends SkinBase<IconSwitch> implements Skin<IconSwitch> {
    private static final double PREFERRED_WIDTH  = 80;
    private static final double PREFERRED_HEIGHT = 32;
    private static final double MINIMUM_WIDTH    = 20;
    private static final double MINIMUM_HEIGHT   = 8;
    private static final double MAXIMUM_WIDTH    = 1024;
    private static final double MAXIMUM_HEIGHT   = 1024;
    private double              size;
    private double              width;
    private double              height;
    private Pane                pane;
    private double              aspectRatio;
    private Region              background;
    private Region              thumb;
    private Symbol              symbol;
    private Font                font;
    private Label               text;
    private TranslateTransition moveToSwitchedOff;
    private TranslateTransition moveToSwitchedOn;


    // ******************** Constructors **************************************
    public IconSwitchSkin(final IconSwitch CONTROL) {
        super(CONTROL);
        aspectRatio = PREFERRED_HEIGHT / PREFERRED_WIDTH;
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

        if (getSkinnable().getPrefWidth() != PREFERRED_WIDTH || getSkinnable().getPrefHeight() != PREFERRED_HEIGHT) {
            aspectRatio = getSkinnable().getPrefHeight() / getSkinnable().getPrefWidth();
        }
    }

    private void initGraphics() {
        Font.loadFont(getClass().getResourceAsStream("/eu/hansolo/enzo/fonts/opensans-semibold.ttf"), (0.5 * PREFERRED_HEIGHT)); // "OpenSans"
        font = Font.font("Open Sans", 0.5 * PREFERRED_HEIGHT);

        background = new Region();
        background.getStyleClass().setAll("background");
        background.setStyle("-switch-color: " + Util.colorToCss((Color) getSkinnable().getSwitchColor()) + ";");

        symbol = getSkinnable().getSymbol();
        symbol.setMouseTransparent(true);

        text = new Label(getSkinnable().getText());
        text.setTextAlignment(TextAlignment.CENTER);
        text.setAlignment(Pos.CENTER);
        text.setTextFill(getSkinnable().getSymbolColor());
        text.setFont(font);

        thumb = new Region();
        thumb.getStyleClass().setAll("thumb");
        thumb.setStyle("-thumb-color: " + Util.colorToCss((Color) getSkinnable().getThumbColor()) + ";");
        thumb.setMouseTransparent(true);

        pane = new Pane(background, symbol, text, thumb);
        pane.getStyleClass().setAll("icon-switch");

        moveToSwitchedOff = new TranslateTransition(Duration.millis(180), thumb);
        moveToSwitchedOn  = new TranslateTransition(Duration.millis(180), thumb);

        // Add all nodes
        getChildren().setAll(pane);
    }

    private void registerListeners() {
        getSkinnable().widthProperty().addListener(observable -> handleControlPropertyChanged("RESIZE") );
        getSkinnable().heightProperty().addListener(observable -> handleControlPropertyChanged("RESIZE") );
        getSkinnable().switchColorProperty().addListener(observable -> handleControlPropertyChanged("SWITCH_COLOR") );
        getSkinnable().thumbColorProperty().addListener(observable -> handleControlPropertyChanged("THUMB_COLOR"));
        getSkinnable().symbolColorProperty().addListener(observable -> handleControlPropertyChanged("SYMBOL_COLOR"));
        getSkinnable().textProperty().addListener(observable -> handleControlPropertyChanged("TEXT"));
        getSkinnable().onProperty().addListener(observable -> handleControlPropertyChanged("ON"));
        pane.setOnMouseClicked(mouseEvent -> getSkinnable().setOn(!getSkinnable().isOn()));
    }


    // ******************** Methods *******************************************
    protected void handleControlPropertyChanged(final String PROPERTY) {
        if ("RESIZE".equals(PROPERTY)) {
            resize();
        } else if ("SWITCH_COLOR".equals(PROPERTY)) {
            background.setStyle("-switch-color: " + Util.colorToCss((Color) getSkinnable().getSwitchColor()) + ";");
        } else if ("THUMB_COLOR".equals(PROPERTY)) {
            thumb.setStyle("-thumb-color: " + Util.colorToCss((Color) getSkinnable().getThumbColor()) + ";");
        } else if ("SYMBOL_COLOR".equals(PROPERTY)) {
            text.setTextFill(getSkinnable().getSymbolColor());
            resize();
        } else if ("TEXT".equals(PROPERTY)) {
            text.setText(getSkinnable().getText());
            resize();
        } else if ("ON".equals(PROPERTY)) {
            if (getSkinnable().isOn()) {
                moveToSwitchedOn.play();
            } else {
                moveToSwitchedOff.play();
            }
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


    // ******************** Private Methods ***********************************
    private void resize() {
        width  = getSkinnable().getWidth();
        height = getSkinnable().getHeight();
        size   = width < height ? width : height;

        if (width > 0 && height > 0) {
            if (aspectRatio * width > height) {
                width = 1 / (aspectRatio / height);
            } else if (1 / (aspectRatio / height) > width) {
                height = aspectRatio * width;
            }

            font = Font.font("Open Sans", FontWeight.BOLD, FontPosture.REGULAR, height * 0.5);

            background.setPrefSize(width, height);

            symbol.setPrefSize(height * 0.59375 * getSkinnable().getSymbolType().WIDTH_FACTOR, height * 0.59375 * getSkinnable().getSymbolType().HEIGHT_FACTOR);
            symbol.relocate(height * 0.15 + (height * 0.59375 - height * 0.59375 * getSkinnable().getSymbolType().WIDTH_FACTOR) * 0.5,
                            height * 0.18 + (height * 0.59375 - height * 0.59375 * getSkinnable().getSymbolType().HEIGHT_FACTOR) * 0.5);

            text.setFont(font);
            text.setVisible(!getSkinnable().getText().isEmpty() && SymbolType.NONE == getSkinnable().getSymbolType());
            text.setPrefSize(height * 0.59375, height * 0.59375);
            text.relocate(height * 0.125, height * 0.15);

            thumb.setPrefSize((height * 0.75), (height * 0.75));
            thumb.setTranslateX(getSkinnable().isOn() ? height * 1.625 : height * 0.875);
            thumb.setTranslateY(height * 0.125);

            moveToSwitchedOff.setFromX(height * 1.625);
            moveToSwitchedOff.setToX(height * 0.875);

            moveToSwitchedOn.setFromX(height * 0.875);
            moveToSwitchedOn.setToX(height * 1.625);
        }
    }
}
