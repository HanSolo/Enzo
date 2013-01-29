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

import com.sun.javafx.scene.control.skin.BehaviorSkinBase;
import eu.hansolo.enzo.led.Led;
import eu.hansolo.enzo.led.behavior.LedBehavior;
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
 * Date: 16.11.12
 * Time: 09:20
 */
public class LedSkin extends BehaviorSkinBase<Led, LedBehavior> {
    private static final double PREFERRED_SIZE = 16;
    private static final double MINIMUM_SIZE   = 8;
    private static final double MAXIMUM_SIZE   = 1024;
    private Led                 control;
    private double              size;
    private Pane                pane;
    private Region              frame;
    private Region              off;
    private Region              onBack;
    private Region              on;
    private Region              highlight;
    private InnerShadow         innerShadow;
    private DropShadow          glow;


    // ******************** Constructors **************************************
    public LedSkin(final Led CONTROL) {
        super(CONTROL, new LedBehavior(CONTROL));
        control = CONTROL;
        pane    = new Pane();

        init();
        initGraphics();
        registerListeners();
    }


    // ******************** Initialization ************************************
    private void init() {
        if (Double.compare(control.getPrefWidth(), 0.0) <= 0 || Double.compare(control.getPrefHeight(), 0.0) <= 0 ||
            control.getWidth() <= 0 || control.getHeight() <= 0) {
            control.setPrefSize(PREFERRED_SIZE, PREFERRED_SIZE);
        }

        if (Double.compare(control.getMinWidth(), 0.0) <= 0 || Double.compare(control.getMinHeight(), 0.0) <= 0) {
            control.setMinSize(MINIMUM_SIZE, MINIMUM_SIZE);
        }

        if (Double.compare(control.getMaxWidth(), 0.0) <= 0 || Double.compare(control.getMaxHeight(), 0.0) <= 0) {
            control.setMaxSize(MAXIMUM_SIZE, MAXIMUM_SIZE);
        }
    }

    private void initGraphics() {
        frame = new Region();
        frame.setVisible(control.isFrameVisible());

        off = new Region();

        innerShadow = InnerShadowBuilder.create()
                                        .color(Color.rgb(0, 0, 0, 0.65))
                                        .radius(8)
                                        .blurType(BlurType.GAUSSIAN)
                                        .build();

        glow = DropShadowBuilder.create()
                                .input(innerShadow)
                                .color(control.getColor())
                                .radius(20)
                                .blurType(BlurType.GAUSSIAN)
                                .build();

        on = new Region();
        on.setEffect(glow);
        on.setVisible(control.isOn());

        highlight = new Region();

        changeStyle();

        // Add all nodes
        pane.getChildren().setAll(frame, off, on, highlight);

        getChildren().setAll(pane);
    }

    private void registerListeners() {
        registerChangeListener(control.widthProperty(), "RESIZE");
        registerChangeListener(control.heightProperty(), "RESIZE");
        registerChangeListener(control.colorProperty(), "COLOR");
        registerChangeListener(control.typeProperty(), "TYPE");
        registerChangeListener(control.onProperty(), "ON");
        registerChangeListener(control.frameVisibleProperty(), "FRAME_VISIBLE");
    }


    // ******************** Methods *******************************************
    @Override protected void handleControlPropertyChanged(final String PROPERTY) {
        super.handleControlPropertyChanged(PROPERTY);
        if ("RESIZE".equals(PROPERTY)) {
            resize();
        } else if ("COLOR".equals(PROPERTY)) {
            control.setStyle("-fx-led-color: " + colorToCss(control.getColor()) + ";");
            glow.setColor(control.getColor());
        } else if ("ON".equals(PROPERTY)) {
            on.setVisible(control.isOn());
        } else if ("TYPE".equals(PROPERTY)) {
            changeStyle();
            resize();
        } else if ("FRAME_VISIBLE".equals(PROPERTY)) {
            frame.setVisible(control.isFrameVisible());
        }
    }

    @Override public final void dispose() {
        control = null;
    }

    @Override protected double computePrefWidth(final double PREF_HEIGHT) {
        double prefHeight = PREFERRED_SIZE;
        if (PREF_HEIGHT != -1) {
            prefHeight = Math.max(0, PREF_HEIGHT - control.getInsets().getTop() - control.getInsets().getBottom());
        }
        return super.computePrefWidth(prefHeight);
    }

    @Override protected double computePrefHeight(final double PREF_WIDTH) {
        double prefWidth = PREFERRED_SIZE;
        if (PREF_WIDTH != -1) {
            prefWidth = Math.max(0, PREF_WIDTH - control.getInsets().getLeft() - control.getInsets().getRight());
        }
        return super.computePrefWidth(prefWidth);
    }

    @Override protected double computeMinWidth(final double MIN_HEIGHT) {
        return super.computeMinWidth(Math.max(5, MIN_HEIGHT - control.getInsets().getTop() - control.getInsets().getBottom()));
    }

    @Override protected double computeMinHeight(final double MIN_WIDTH) {
        return super.computeMinHeight(Math.max(5, MIN_WIDTH - control.getInsets().getLeft() - control.getInsets().getRight()));
    }

    @Override protected double computeMaxWidth(final double MAX_HEIGHT) {
        return super.computeMaxWidth(Math.min(1024, MAX_HEIGHT - control.getInsets().getTop() - control.getInsets().getBottom()));
    }

    @Override protected double computeMaxHeight(final double MAX_WIDTH) {
        return super.computeMaxHeight(Math.min(1024, MAX_WIDTH - control.getInsets().getLeft() - control.getInsets().getRight()));
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
        switch(control.getType()) {
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

        control.setStyle("-fx-led-color: " + colorToCss(control.getColor()) + ";");
    }

    private void resize() {
        size = control.getWidth() < control.getHeight() ? control.getWidth() : control.getHeight();

        innerShadow.setRadius(size * 0.08);
        glow.setRadius(size * 0.25);

        switch(control.getType()) {
            case HORIZONTAL:
                frame.setPrefSize(size, size * 0.56);
                frame.setTranslateY((size - frame.getPrefHeight()) * 0.5);

                off.setPrefSize(size * 0.71, size * 0.28);
                off.setTranslateX(size * 0.14);
                off.setTranslateY((size - off.getPrefHeight()) * 0.5);

                on.setPrefSize(size * 0.71, size * 0.28);
                on.setTranslateX(size * 0.14);
                on.setTranslateY((size - on.getPrefHeight()) * 0.5);

                highlight.setPrefSize(size * 0.67, size * 0.12);
                highlight.setTranslateX((size - highlight.getPrefWidth()) * 0.5);
                highlight.setTranslateY(size * 0.38);
                break;
            case VERTICAL:
                frame.setPrefSize(size * 0.56, size);
                frame.setTranslateX((size - frame.getPrefWidth()) * 0.5);

                off.setPrefSize(size * 0.28, size * 0.71);
                off.setTranslateX((size - off.getPrefWidth()) * 0.5);
                off.setTranslateY(size * 0.14);

                on.setPrefSize(size * 0.28, size * 0.71);
                on.setTranslateX((size - on.getPrefWidth()) * 0.5);
                on.setTranslateY(size * 0.14);

                highlight.setPrefSize(size * 0.22, size * 0.23);
                highlight.setTranslateX((size - highlight.getPrefWidth()) * 0.5);
                highlight.setTranslateY(size * 0.17);
                break;
            case SQUARE:
                frame.setPrefSize(size, size);

                off.setPrefSize(size * 0.71, size * 0.71);
                off.setTranslateX(size * 0.14);
                off.setTranslateY(size * 0.14);

                on.setPrefSize(size * 0.71, size * 0.71);
                on.setTranslateX(size * 0.14);
                on.setTranslateY(size * 0.14);

                highlight.setPrefSize(size * 0.66, size * 0.23);
                highlight.setTranslateX((size - highlight.getPrefWidth()) * 0.5);
                highlight.setTranslateY(size * 0.17);
                break;
            case ROUND:
            default:
                frame.setPrefSize(size, size);

                off.setPrefSize(size * 0.71, size * 0.71);
                off.setTranslateX(size * 0.14);
                off.setTranslateY(size * 0.14);

                on.setPrefSize(size * 0.71, size * 0.71);
                on.setTranslateX(size * 0.14);
                on.setTranslateY(size * 0.14);

                highlight.setPrefSize(size * 0.6, size * 0.6);
                highlight.setTranslateX((size - highlight.getPrefWidth()) * 0.5);
                highlight.setTranslateY((size - highlight.getPrefHeight()) * 0.5);
                break;
        }
    }
}