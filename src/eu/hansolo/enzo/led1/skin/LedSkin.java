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

package eu.hansolo.enzo.led1.skin;

import eu.hansolo.enzo.led1.Led;
import javafx.collections.ListChangeListener;
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


public class LedSkin extends SkinBase<Led> implements Skin<Led> {
    private static final double PREFERRED_WIDTH  = 16;
    private static final double PREFERRED_HEIGHT = 16;
    private static final double MINIMUM_WIDTH    = 5;
    private static final double MINIMUM_HEIGHT   = 5;
    private static final double MAXIMUM_WIDTH    = 1024;
    private static final double MAXIMUM_HEIGHT   = 1024;
    private double              aspectRatio;
    private double              size;
    private double              width;
    private double              height;
    private Pane                pane;
    private Region              frame;
    private Region              ledOff;
    private Region              ledOn;
    private InnerShadow         innerShadow;
    private DropShadow          dropShadow;
    private Region              highlight;


    // ******************** Constructors **************************************
    public LedSkin(final Led CONTROL) {
        super(CONTROL);
        aspectRatio = PREFERRED_HEIGHT / PREFERRED_WIDTH;
        pane        = new Pane();
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
        frame = new Region();
        frame.getStyleClass().setAll("frame");
        frame.setVisible(getSkinnable().isFrameVisible());

        ledOff = new Region();
        ledOff.getStyleClass().setAll("led", "led-off", getSkinnable().getColor().STYLE_CLASS);

        ledOn = new Region();
        ledOn.getStyleClass().setAll("led", "led-on", getSkinnable().getColor().STYLE_CLASS);
        ledOn.setVisible(getSkinnable().isOn());

        innerShadow = InnerShadowBuilder.create()
                                        .offsetX(0.0)
                                        .offsetY(0.0)
                                        .radius(0.1 * PREFERRED_WIDTH)
                                        .color(Color.BLACK)
                                        .blurType(BlurType.GAUSSIAN)
                                        .build();
        dropShadow = DropShadowBuilder.create()
                                      .offsetX(0.0)
                                      .offsetY(0.0)
                                      .radius(25.0 / 100.0 * PREFERRED_WIDTH)
                                      .color(getSkinnable().getColor().COLOR)
                                      .blurType(BlurType.GAUSSIAN)
                                      .input(innerShadow)
                                      .build();
        ledOn.setEffect(dropShadow);

        highlight = new Region();
        highlight.getStyleClass().setAll("highlight");

        pane.getChildren().setAll(frame,
                                  ledOff,
                                  ledOn,
                                  highlight);

        getChildren().setAll(pane);
        resize();
    }

    private void registerListeners() {
        getSkinnable().widthProperty().addListener(observable -> { handleControlPropertyChanged("RESIZE"); });
        getSkinnable().heightProperty().addListener(observable -> { handleControlPropertyChanged("RESIZE"); });
        getSkinnable().prefWidthProperty().addListener(observable -> { handleControlPropertyChanged("PREF_SIZE"); });
        getSkinnable().prefHeightProperty().addListener(observable -> { handleControlPropertyChanged("PREF_SIZE"); });
        getSkinnable().colorProperty().addListener(observable -> { handleControlPropertyChanged("COLOR"); });
        getSkinnable().onProperty().addListener(observable -> { handleControlPropertyChanged("GLOWING"); });
        getSkinnable().frameVisibleProperty().addListener(observable -> { handleControlPropertyChanged("FRAME_VISIBLE"); });

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
        } else if ("PREF_SIZE".equals(PROPERTY)) {
            aspectRatio = getSkinnable().getPrefHeight() / getSkinnable().getPrefWidth();
        } else if ("GLOWING".equals(PROPERTY)) {
            ledOn.setVisible(getSkinnable().isOn());
            ledOff.setVisible(!getSkinnable().isOn());
        } else if ("FRAME_VISIBLE".equals(PROPERTY)) {
            frame.setVisible(getSkinnable().isFrameVisible());
        } else if ("COLOR".equals(PROPERTY)) {
            ledOff.getStyleClass().setAll("led", "led-off", getSkinnable().getColor().STYLE_CLASS);
            ledOn.getStyleClass().setAll("led", "led-on", getSkinnable().getColor().STYLE_CLASS);
            dropShadow.setColor(getSkinnable().getColor().COLOR);
        }
    }

    @Override protected double computeMinWidth(final double HEIGHT, int TOP_INSET, int RIGHT_INSET, int BOTTOM_INSET, int LEFT_INSET) {
        return super.computeMinWidth(Math.max(MINIMUM_HEIGHT, HEIGHT - TOP_INSET - BOTTOM_INSET), TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET);
    }
    @Override protected double computeMinHeight(final double WIDTH, int TOP_INSET, int RIGHT_INSET, int BOTTOM_INSET, int LEFT_INSET) {
        return super.computeMinHeight(Math.max(MINIMUM_WIDTH, WIDTH - LEFT_INSET - RIGHT_INSET), TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET);
    }

    @Override protected double computeMaxWidth(final double HEIGHT, int TOP_INSET, int RIGHT_INSET, int BOTTOM_INSET, int LEFT_INSET) {
        return super.computeMaxWidth(Math.min(MAXIMUM_HEIGHT, HEIGHT - TOP_INSET - BOTTOM_INSET), TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET);
    }
    @Override protected double computeMaxHeight(final double WIDTH, int TOP_INSET, int RIGHT_INSET, int BOTTOM_INSET, int LEFT_INSET) {
        return super.computeMaxHeight(Math.min(MAXIMUM_WIDTH, WIDTH - LEFT_INSET - RIGHT_INSET), TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET);
    }

    @Override protected double computePrefWidth(final double HEIGHT, int TOP_INSET, int RIGHT_INSET, int BOTTOM_INSET, int LEFT_INSET) {
        double prefHeight = PREFERRED_HEIGHT;
        if (HEIGHT != -1) {
            prefHeight = Math.max(0, HEIGHT - TOP_INSET - BOTTOM_INSET);
        }
        return super.computePrefWidth(prefHeight, TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET);
    }
    @Override protected double computePrefHeight(final double WIDTH, int TOP_INSET, int RIGHT_INSET, int BOTTOM_INSET, int LEFT_INSET) {
        double prefWidth = PREFERRED_WIDTH;
        if (WIDTH != -1) {
            prefWidth = Math.max(0, WIDTH - LEFT_INSET - RIGHT_INSET);
        }
        return super.computePrefHeight(prefWidth, TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET);
    }


    // ******************** Resizing ******************************************
    private void resize() {
        boolean wasBlinking = getSkinnable().isBlinking();
        if (wasBlinking) {
            getSkinnable().setBlinking(false);
        }

        size    = getSkinnable().getWidth() < getSkinnable().getHeight() ? getSkinnable().getWidth() : getSkinnable().getHeight();
        width   = getSkinnable().getWidth();
        height  = getSkinnable().getHeight();
        if (getSkinnable().isKeepAspect()) {
            if (aspectRatio * width > height) {
                width  = 1 / (aspectRatio / height);
            } else if (1 / (aspectRatio / height) > width) {
                height = aspectRatio * width;
            }
        }

        if (width > 0 && height > 0) {
            frame.setPrefSize(0.75 * width, 0.75 * height);
            frame.setTranslateX(0.125 * width);
            frame.setTranslateY(0.125 * height);

            ledOff.setPrefSize(0.625 * width, 0.625 * height);
            ledOff.setTranslateX(0.1875 * width);
            ledOff.setTranslateY(0.1875 * height);

            ledOn.setPrefSize(0.625 * width, 0.625 * height);
            ledOn.setTranslateX(0.1875 * width);
            ledOn.setTranslateY(0.1875 * height);
            innerShadow.setRadius(0.1 / 80.0 * size);
            dropShadow.setRadius(0.25 * size);

            highlight.setPrefSize(0.5 * width, 0.5 * height);
            highlight.setTranslateX(0.25 * width);
            highlight.setTranslateY(0.25 * height);
        }
        getSkinnable().setBlinking(wasBlinking);
    }
}
