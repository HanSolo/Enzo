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

package eu.hansolo.enzo.led.skin;

import eu.hansolo.enzo.common.Util;
import eu.hansolo.enzo.led.Led;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;


/**
 * Created by
 * User: hansolo
 * Date: 17.04.13
 * Time: 09:01
 */
public class LedSkin extends SkinBase<Led> implements Skin<Led> {
    private static final double PREFERRED_SIZE = 16;
    private static final double MINIMUM_SIZE   = 8;
    private static final double MAXIMUM_SIZE   = 1024;
    private double              size;
    private Pane                pane;
    private Region              frame;
    private Region              led;
    private Region              highlight;
    private InnerShadow         innerShadow;
    private DropShadow          glow;


    // ******************** Constructors **************************************
    public LedSkin(final Led CONTROL) {
        super(CONTROL);
        pane    = new Pane();
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
                getSkinnable().setPrefSize(PREFERRED_SIZE, PREFERRED_SIZE);
            }
        }

        if (Double.compare(getSkinnable().getMinWidth(), 0.0) <= 0 || Double.compare(getSkinnable().getMinHeight(), 0.0) <= 0) {
            getSkinnable().setMinSize(MINIMUM_SIZE, MINIMUM_SIZE);
        }

        if (Double.compare(getSkinnable().getMaxWidth(), 0.0) <= 0 || Double.compare(getSkinnable().getMaxHeight(), 0.0) <= 0) {
            getSkinnable().setMaxSize(MAXIMUM_SIZE, MAXIMUM_SIZE);
        }
    }

    private void initGraphics() {
        frame = new Region();
        frame.setOpacity(getSkinnable().isFrameVisible() ? 1 : 0);

        led = new Region();
        led.setStyle("-led-color: " + Util.colorToCss((Color) getSkinnable().getLedColor()) + ";");

        innerShadow = new InnerShadow();
        innerShadow.setColor(Color.rgb(0, 0, 0, 0.65));
        innerShadow.setRadius(8);
        innerShadow.setBlurType(BlurType.TWO_PASS_BOX);

        glow = new DropShadow();
        glow.setInput(innerShadow);
        glow.setColor((Color) getSkinnable().getLedColor());
        glow.setRadius(20);
        glow.setBlurType(BlurType.TWO_PASS_BOX);

        highlight = new Region();

        // Set the appropriate style classes
        changeStyle();

        // Add all nodes
        pane.getChildren().setAll(frame, led, highlight);

        getChildren().setAll(pane);
    }

    private void registerListeners() {
        getSkinnable().widthProperty().addListener(observable -> handleControlPropertyChanged("RESIZE") );
        getSkinnable().heightProperty().addListener(observable -> handleControlPropertyChanged("RESIZE") );
        getSkinnable().ledColorProperty().addListener(observable -> handleControlPropertyChanged("COLOR") );
        getSkinnable().ledTypeProperty().addListener(observable -> handleControlPropertyChanged("STYLE") );
        getSkinnable().onProperty().addListener(observable -> handleControlPropertyChanged("ON") );
        getSkinnable().frameVisibleProperty().addListener(observable -> handleControlPropertyChanged("FRAME_VISIBLE") );
    }


    // ******************** Methods *******************************************
    protected void handleControlPropertyChanged(final String PROPERTY) {
        if ("RESIZE".equals(PROPERTY)) {
            resize();
        } else if ("COLOR".equals(PROPERTY)) {
            led.setStyle("-led-color: " + Util.colorToCss((Color) getSkinnable().getLedColor()) + ";");
            changeStyle();
        } else if ("STYLE".equals(PROPERTY)) {
            changeStyle();
        } else if ("ON".equals(PROPERTY)) {
            led.setEffect(getSkinnable().isOn() ? glow : innerShadow);
        } else if ("FRAME_VISIBLE".equals(PROPERTY)) {
            frame.setOpacity(getSkinnable().isFrameVisible() ? 1.0 : 0.0);
        }
    }

    @Override protected double computeMinWidth(final double HEIGHT, double TOP_INSET, double RIGHT_INSET, double BOTTOM_INSET, double LEFT_INSET) {
        return super.computeMinWidth(Math.max(MINIMUM_SIZE, HEIGHT - TOP_INSET - BOTTOM_INSET), TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET);
    }
    @Override protected double computeMinHeight(final double WIDTH, double TOP_INSET, double RIGHT_INSET, double BOTTOM_INSET, double LEFT_INSET) {
        return super.computeMinHeight(Math.max(MINIMUM_SIZE, WIDTH - LEFT_INSET - RIGHT_INSET), TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET);
    }

    @Override protected double computeMaxWidth(final double HEIGHT, double TOP_INSET, double RIGHT_INSET, double BOTTOM_INSET, double LEFT_INSET) {
        return super.computeMaxWidth(Math.min(MAXIMUM_SIZE, HEIGHT - TOP_INSET - BOTTOM_INSET), TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET);
    }
    @Override protected double computeMaxHeight(final double WIDTH, double TOP_INSET, double RIGHT_INSET, double BOTTOM_INSET, double LEFT_INSET) {
        return super.computeMaxHeight(Math.min(MAXIMUM_SIZE, WIDTH - LEFT_INSET - RIGHT_INSET), TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET);
    }

    @Override protected double computePrefWidth(final double HEIGHT, double TOP_INSET, double RIGHT_INSET, double BOTTOM_INSET, double LEFT_INSET) {
        double prefHeight = PREFERRED_SIZE;
        if (HEIGHT != -1) {
            prefHeight = Math.max(0, HEIGHT - TOP_INSET - BOTTOM_INSET);
        }
        return super.computePrefWidth(prefHeight, TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET);
    }
    @Override protected double computePrefHeight(final double WIDTH, double TOP_INSET, double RIGHT_INSET, double BOTTOM_INSET, double LEFT_INSET) {
        double prefWidth = PREFERRED_SIZE;
        if (WIDTH != -1) {
            prefWidth = Math.max(0, WIDTH - LEFT_INSET - RIGHT_INSET);
        }
        return super.computePrefHeight(prefWidth, TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET);
    }


    // ******************** Private Methods ***********************************
    private void changeStyle() {
        switch(getSkinnable().getLedType()) {
            case HORIZONTAL:
                frame.getStyleClass().setAll("horizontal-frame");
                led.getStyleClass().setAll("horizontal");
                highlight.getStyleClass().setAll("horizontal-highlight");
                break;
            case VERTICAL:
                frame.getStyleClass().setAll("vertical-frame");
                led.getStyleClass().setAll("vertical");
                highlight.getStyleClass().setAll("vertical-highlight");
                break;
            case SQUARE:
                frame.getStyleClass().setAll("square-frame");
                led.getStyleClass().setAll("square");
                highlight.getStyleClass().setAll("square-highlight");
                break;
            case TRIANGLE_UP:
                frame.getStyleClass().setAll("triangle-up-frame");
                led.getStyleClass().setAll("triangle-up");
                highlight.getStyleClass().setAll("triangle-up-highlight");
                break;
            case TRIANGLE_RIGHT:
                frame.getStyleClass().setAll("triangle-right-frame");
                led.getStyleClass().setAll("triangle-right");
                highlight.getStyleClass().setAll("triangle-right-highlight");
                break;
            case TRIANGLE_DOWN:
                frame.getStyleClass().setAll("triangle-down-frame");
                led.getStyleClass().setAll("triangle-down");
                highlight.getStyleClass().setAll("triangle-down-highlight");
                break;
            case TRIANGLE_LEFT:
                frame.getStyleClass().setAll("triangle-left-frame");
                led.getStyleClass().setAll("triangle-left");
                highlight.getStyleClass().setAll("triangle-left-highlight");
                break;
            case ROUND:
            default:
                frame.getStyleClass().setAll("round-frame");
                led.getStyleClass().setAll("round");
                highlight.getStyleClass().setAll("round-highlight");
                break;
        }

        glow.setColor((Color) getSkinnable().getLedColor());
        led.setEffect(getSkinnable().isOn() ? glow : innerShadow);

        resize();
    }

    private void resize() {
        size = getSkinnable().getWidth() < getSkinnable().getHeight() ? getSkinnable().getWidth() : getSkinnable().getHeight();
        if (size > 0) {
            innerShadow.setRadius(0.07 * size);
            glow.setRadius(0.36 * size);

            switch(getSkinnable().getLedType()) {
                case HORIZONTAL:
                    frame.setPrefSize(size, 0.56 * size);
                    frame.setTranslateY(0.22 * size);

                    led.setPrefSize(0.72 * size, 0.28 * size);
                    led.setTranslateX(0.14 * size);
                    led.setTranslateY(0.36 * size);

                    highlight.setPrefSize(0.68 * size, 0.12 * size);
                    highlight.setTranslateX(0.16 * size);
                    highlight.setTranslateY(0.38 * size);
                    break;
                case VERTICAL:
                    frame.setPrefSize(0.56 * size, size);
                    frame.setTranslateX(0.22 * size);

                    led.setPrefSize(0.28 * size, 0.72 * size);
                    led.setTranslateX(0.36 * size);
                    led.setTranslateY(0.14 * size);

                    highlight.setPrefSize(0.22 * size, 0.23 * size);
                    highlight.setTranslateX(0.39 * size);
                    highlight.setTranslateY(0.17 * size);
                    break;
                case SQUARE:
                    frame.setPrefSize(size, size);

                    led.setPrefSize(0.72 * size, 0.72 * size);
                    led.setTranslateX(0.14 * size);
                    led.setTranslateY(0.14 * size);

                    highlight.setPrefSize(0.66 * size, 0.23 * size);
                    highlight.setTranslateX(0.17 * size);
                    highlight.setTranslateY(0.17 * size);
                    break;
                case TRIANGLE_UP:
                    frame.setPrefSize(1.0035383605957031 * size, 0.9975 * size);
                    frame.setTranslateX(-0.0017691457271575928 * size);
                    frame.setTranslateY(0.0025 * size);

                    led.setPrefSize(0.6956922149658203 * size, 0.7 * size);
                    led.setTranslateX(0.15215388298034668 * size);
                    led.setTranslateY(0.2 * size);

                    highlight.setPrefSize(0.30249504089355467 * size, 0.635 * size);
                    highlight.setTranslateX(0.1975049591064453 * size);
                    highlight.setTranslateY(0.235 * size);
                    break;
                case TRIANGLE_RIGHT:
                    frame.setPrefSize(size, size);

                    led.setPrefSize(0.7 * size, 0.6956920623779297 * size);
                    led.setTranslateX(0.1 * size);
                    led.setTranslateY(0.152153902053833 * size);

                    highlight.setPrefSize(0.635 * size, 0.3 * size);
                    highlight.setTranslateX(0.13 * size);
                    highlight.setTranslateY(0.19 * size);
                    break;
                case TRIANGLE_DOWN:
                    frame.setPrefSize(1.0035383605957031 * size, 0.9975 * size);
                    frame.setTranslateX(-0.0017691457271575928 * size);


                    led.setPrefSize(0.6956922149658203 * size, 0.7 * size);
                    led.setTranslateX(0.15215388298034668 * size);
                    led.setTranslateY(0.1 * size);

                    highlight.setPrefSize(0.6024949645996094 * size, 0.63 * size);
                    highlight.setTranslateX(0.1975049591064453 * size);
                    highlight.setTranslateY(0.13 * size);
                    break;
                case TRIANGLE_LEFT:
                    frame.setPrefSize(0.9975 * size, 1.0035382843017577 * size);
                    frame.setTranslateX(0.0025 * size);
                    frame.setTranslateY(-0.0017691445350646972 * size);

                    led.setPrefSize(0.7 * size, 0.6956920623779297 * size);
                    led.setTranslateX(0.2 * size);
                    led.setTranslateY(0.152153902053833 * size);

                    highlight.setPrefSize(0.635 * size, 0.3 * size);
                    highlight.setTranslateX(0.235 * size);
                    highlight.setTranslateY(0.19 * size);
                    break;
                case ROUND:
                default:
                    frame.setPrefSize(size, size);

                    led.setPrefSize(0.72 * size, 0.72 * size);
                    led.setTranslateX(0.14 * size);
                    led.setTranslateY(0.14 * size);

                    highlight.setPrefSize(0.58 * size, 0.58 * size);
                    highlight.setTranslateX(0.21 * size);
                    highlight.setTranslateY(0.21 * size);
                    break;
            }
        }
    }
}
