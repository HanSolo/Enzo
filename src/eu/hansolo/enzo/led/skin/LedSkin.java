/*
 * Copyright (c) 2013, Gerrit Grunwald
 * All right reserved
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * The names of its contributors may not be used to endorse or promote
 * products derived from this software without specific prior written
 * permission.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package eu.hansolo.enzo.led.skin;

import eu.hansolo.enzo.led.Led;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.DropShadowBuilder;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.InnerShadowBuilder;
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
    private Region              off;
    private Region              on;
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
        frame.setVisible(getSkinnable().isFrameVisible());

        off = new Region();

        innerShadow = InnerShadowBuilder.create()
                                        .color(Color.rgb(0, 0, 0, 0.65))
                                        .radius(8)
                                        .blurType(BlurType.GAUSSIAN)
                                        .build();

        glow = DropShadowBuilder.create()
                                .input(innerShadow)
                                .color(getSkinnable().getColor())
                                .radius(20)
                                .blurType(BlurType.GAUSSIAN)
                                .build();

        on = new Region();
        on.setEffect(glow);
        on.setVisible(getSkinnable().isOn());

        highlight = new Region();

        // Set the appropriate style classes
        changeStyle();

        // Add all nodes
        pane.getChildren().setAll(frame, off, on, highlight);

        getChildren().setAll(pane);
    }

    private void registerListeners() {
        getSkinnable().widthProperty().addListener(observable -> { handleControlPropertyChanged("RESIZE"); });
        getSkinnable().heightProperty().addListener(observable -> { handleControlPropertyChanged("RESIZE"); });
        getSkinnable().colorProperty().addListener(observable -> { handleControlPropertyChanged("COLOR"); });
        getSkinnable().typeProperty().addListener(observable -> { handleControlPropertyChanged("TYPE"); });
        getSkinnable().onProperty().addListener(observable -> { handleControlPropertyChanged("SELECTED"); });
        getSkinnable().frameVisibleProperty().addListener(observable -> { handleControlPropertyChanged("FRAME_VISIBLE"); });
    }


    // ******************** Methods *******************************************
    protected void handleControlPropertyChanged(final String PROPERTY) {
        if ("RESIZE".equals(PROPERTY)) {
            resize();
        } else if ("COLOR".equals(PROPERTY)) {
            getSkinnable().setStyle("-fx-led-color: " + colorToCss(getSkinnable().getColor()) + ";");
            glow.setColor(getSkinnable().getColor());
        } else if ("SELECTED".equals(PROPERTY)) {
            on.setVisible(getSkinnable().isOn());
            off.setVisible(!getSkinnable().isOn());
        } else if ("TYPE".equals(PROPERTY)) {
            changeStyle();
            resize();
        } else if ("FRAME_VISIBLE".equals(PROPERTY)) {
            frame.setVisible(getSkinnable().isFrameVisible());
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
    private String colorToCss(final Color COLOR) {
        StringBuilder cssColor = new StringBuilder();
        cssColor.append("rgba(")
                .append((int) (COLOR.getRed() * 255)).append(", ")
                .append((int) (COLOR.getGreen() * 255)).append(", ")
                .append((int) (COLOR.getBlue() * 255)).append(", ")
                .append(COLOR.getOpacity()).append(");");
        return cssColor.toString();
    }

    private void changeStyle() {
        switch(getSkinnable().getType()) {
            case HORIZONTAL:
                frame.getStyleClass().setAll("frame-horizontal");
                off.getStyleClass().setAll("off-horizontal");
                on.getStyleClass().setAll("on-horizontal");
                highlight.getStyleClass().setAll("highlight-horizontal");
                break;
            case VERTICAL:
                frame.getStyleClass().setAll("frame-vertical");
                off.getStyleClass().setAll("off-vertical");
                on.getStyleClass().setAll("on-vertical");
                highlight.getStyleClass().setAll("highlight-vertical");
                break;
            case SQUARE:
                frame.getStyleClass().setAll("frame-square");
                off.getStyleClass().setAll("off-square");
                on.getStyleClass().setAll("on-square");
                highlight.getStyleClass().setAll("highlight-square");
                break;
            case ROUND:
            default:
                frame.getStyleClass().setAll("frame-round");
                off.getStyleClass().setAll("off-round");
                on.getStyleClass().setAll("on-round");
                highlight.getStyleClass().setAll("highlight-round");
                break;
        }

        getSkinnable().setStyle("-fx-led-color: " + colorToCss(getSkinnable().getColor()) + ";");
    }

    private void resize() {
        size = getSkinnable().getWidth() < getSkinnable().getHeight() ? getSkinnable().getWidth() : getSkinnable().getHeight();
        if (size > 0) {
            innerShadow.setRadius(0.07 * size);
            glow.setRadius(0.36 * size);

            switch(getSkinnable().getType()) {
                case HORIZONTAL:
                    frame.setPrefSize(size, 0.56 * size);
                    frame.setTranslateY(0.22 * size);

                    off.setPrefSize(0.72 * size, 0.28 * size);
                    off.setTranslateX(0.14 * size);
                    off.setTranslateY(0.36 * size);

                    on.setPrefSize(0.72 * size, 0.28 * size);
                    on.setTranslateX(0.14 * size);
                    on.setTranslateY(0.36 * size);

                    highlight.setPrefSize(0.68 * size, 0.12 * size);
                    highlight.setTranslateX(0.16 * size);
                    highlight.setTranslateY(0.38 * size);
                    break;
                case VERTICAL:
                    frame.setPrefSize(0.56 * size, size);
                    frame.setTranslateX(0.22 * size);

                    off.setPrefSize(0.28 * size, 0.72 * size);
                    off.setTranslateX(0.36 * size);
                    off.setTranslateY(0.14 * size);

                    on.setPrefSize(0.28 * size, 0.72 * size);
                    on.setTranslateX(0.36 * size);
                    on.setTranslateY(0.14 * size);

                    highlight.setPrefSize(0.22 * size, 0.23 * size);
                    highlight.setTranslateX(0.39 * size);
                    highlight.setTranslateY(0.17 * size);
                    break;
                case SQUARE:
                    frame.setPrefSize(size, size);

                    off.setPrefSize(0.72 * size, 0.72 * size);
                    off.setTranslateX(0.14 * size);
                    off.setTranslateY(0.14 * size);

                    on.setPrefSize(0.72 * size, 0.72 * size);
                    on.setTranslateX(0.14 * size);
                    on.setTranslateY(0.14 * size);

                    highlight.setPrefSize(0.66 * size, 0.23 * size);
                    highlight.setTranslateX(0.17 * size);
                    highlight.setTranslateY(0.17 * size);
                    break;
                case ROUND:
                default:
                    frame.setPrefSize(size, size);

                    off.setPrefSize(0.72 * size, 0.72 * size);
                    off.setTranslateX(0.14 * size);
                    off.setTranslateY(0.14 * size);

                    on.setPrefSize(0.72 * size, 0.72 * size);
                    on.setTranslateX(0.14 * size);
                    on.setTranslateY(0.14 * size);

                    highlight.setPrefSize(0.58 * size, 0.58 * size);
                    highlight.setTranslateX(0.21 * size);
                    highlight.setTranslateY(0.21 * size);
                    break;
            }
        }
    }
}
