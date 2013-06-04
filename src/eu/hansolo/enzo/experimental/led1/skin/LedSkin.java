/*
 * Copyright (c) 2013. Gerrit Grunwald
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package eu.hansolo.enzo.experimental.led1.skin;

import eu.hansolo.enzo.experimental.led1.Led;

import javafx.collections.ListChangeListener;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
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
        frame.setOpacity(getSkinnable().isFrameVisible() ? 1 : 0);

        ledOff = new Region();
        ledOff.getStyleClass().setAll("led", "led-off", getSkinnable().getColor().STYLE_CLASS);

        ledOn = new Region();
        ledOn.getStyleClass().setAll("led", "led-on", getSkinnable().getColor().STYLE_CLASS);
        ledOn.setOpacity(getSkinnable().isOn() ? 1 : 0);

        innerShadow = new InnerShadow();
        innerShadow.setOffsetX(0.0);
        innerShadow.setOffsetY(0.0);
        innerShadow.setRadius(0.1 * PREFERRED_WIDTH);
        innerShadow.setColor(Color.BLACK);
        innerShadow.setBlurType(BlurType.TWO_PASS_BOX);

        dropShadow = new DropShadow();
        dropShadow.setOffsetX(0.0);
        dropShadow.setOffsetY(0.0);
        dropShadow.setRadius(25.0 / 100.0 * PREFERRED_WIDTH);
        dropShadow.setColor(getSkinnable().getColor().COLOR);
        dropShadow.setBlurType(BlurType.TWO_PASS_BOX);
        dropShadow.setInput(innerShadow);

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
            ledOn.setOpacity(getSkinnable().isOn() ? 1 : 0);
            ledOff.setOpacity(getSkinnable().isOn() ? 0 : 1);
        } else if ("FRAME_VISIBLE".equals(PROPERTY)) {
            frame.setOpacity(getSkinnable().isFrameVisible() ? 1 : 0);
        } else if ("COLOR".equals(PROPERTY)) {
            ledOff.getStyleClass().setAll("led", "led-off", getSkinnable().getColor().STYLE_CLASS);
            ledOn.getStyleClass().setAll("led", "led-on", getSkinnable().getColor().STYLE_CLASS);
            dropShadow.setColor(getSkinnable().getColor().COLOR);
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
