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

package eu.hansolo.enzo.matrixsegment.skin;

import com.sun.javafx.scene.control.skin.BehaviorSkinBase;
import eu.hansolo.enzo.matrixsegment.MatrixSegment;
import eu.hansolo.enzo.matrixsegment.behavior.MatrixSegmentBehavior;
import javafx.collections.ListChangeListener;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.DropShadowBuilder;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.InnerShadowBuilder;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RegionBuilder;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static eu.hansolo.enzo.matrixsegment.MatrixSegment.Dot;


public class MatrixSegmentSkin extends BehaviorSkinBase<MatrixSegment, MatrixSegmentBehavior> {
    private static final double DEFAULT_WIDTH  = 71;
    private static final double DEFAULT_HEIGHT = 100;
    private static final double MINIMUM_WIDTH  = 5;
    private static final double MINIMUM_HEIGHT = 5;
    private static final double MAXIMUM_WIDTH  = 1024;
    private static final double MAXIMUM_HEIGHT = 1024;
    private MatrixSegment control;
    private static double aspectRatio;
    private Map<MatrixSegment.Dot, Region> dotMap;
    private List<Region>  highlights;
    private double        size;
    private double        width;
    private double        height;
    private Pane          pane;
    private Region        background;
    private InnerShadow   backgroundInnerShadow;
    private InnerShadow   backgroundInnerHighlight;
    private InnerShadow   dotInnerShadow;
    private DropShadow    glow;
    private Region        d57;
    private Region        d47;
    private Region        d37;
    private Region        d27;
    private Region        d17;
    private Region        d56;
    private Region        d46;
    private Region        d36;
    private Region        d26;
    private Region        d16;
    private Region        d55;
    private Region        d45;
    private Region        d35;
    private Region        d25;
    private Region        d15;
    private Region        d54;
    private Region        d44;
    private Region        d34;
    private Region        d24;
    private Region        d14;
    private Region        d53;
    private Region        d43;
    private Region        d33;
    private Region        d23;
    private Region        d13;
    private Region        d52;
    private Region        d42;
    private Region        d32;
    private Region        d22;
    private Region        d12;
    private Region        d51;
    private Region        d41;
    private Region        d31;
    private Region        d21;
    private Region        d11;
    private Region        d57h;
    private Region        d47h;
    private Region        d37h;
    private Region        d27h;
    private Region        d17h;
    private Region        d56h;
    private Region        d46h;
    private Region        d36h;
    private Region        d26h;
    private Region        d16h;
    private Region        d55h;
    private Region        d45h;
    private Region        d35h;
    private Region        d25h;
    private Region        d15h;
    private Region        d54h;
    private Region        d44h;
    private Region        d34h;
    private Region        d24h;
    private Region        d14h;
    private Region        d53h;
    private Region        d43h;
    private Region        d33h;
    private Region        d23h;
    private Region        d13h;
    private Region        d52h;
    private Region        d42h;
    private Region        d32h;
    private Region        d22h;
    private Region        d12h;
    private Region        d51h;
    private Region        d41h;
    private Region        d31h;
    private Region        d21h;
    private Region        d11h;


    // ******************** Constructors **************************************
    public MatrixSegmentSkin(final MatrixSegment CONTROL) {
        super(CONTROL, new MatrixSegmentBehavior(CONTROL));
        control     = CONTROL;
        aspectRatio = DEFAULT_HEIGHT / DEFAULT_WIDTH;
        dotMap      = new HashMap<>(35);
        highlights  = new ArrayList<>(35);
        pane        = new Pane();
        init();
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

        if (control.getPrefWidth() != DEFAULT_WIDTH || control.getPrefHeight() != DEFAULT_HEIGHT) {
            aspectRatio = control.getPrefHeight() / control.getPrefWidth();
        }
    }

    private void initGraphics() {

        background = new Region();
        background.getStyleClass().setAll("background");

        backgroundInnerShadow = InnerShadowBuilder.create()
                                                  .offsetY(-1.0)
                                                  .radius(1.0 / 310.0 * DEFAULT_WIDTH)
                                                  .color(Color.rgb(0, 0, 0, 0.65))
                                                  .blurType(BlurType.TWO_PASS_BOX)
                                                  .build();
        backgroundInnerHighlight = InnerShadowBuilder.create()
                                                     .offsetY(1.0)
                                                     .radius(1.0 / 310.0 * DEFAULT_WIDTH)
                                                     .color(Color.rgb(200, 200, 200, 0.65))
                                                     .blurType(BlurType.TWO_PASS_BOX)
                                                     .input(backgroundInnerShadow)
                                                     .build();
        background.setEffect(backgroundInnerHighlight);

        dotInnerShadow = InnerShadowBuilder.create()
                                           .radius(0.025 * DEFAULT_WIDTH)
                                           .color(Color.rgb(0, 0, 0, 0.65))
                                           .blurType(BlurType.TWO_PASS_BOX)
                                           .build();

        glow = DropShadowBuilder.create()
                                .input(dotInnerShadow)
                                .radius(0.032 * DEFAULT_WIDTH)
                                .color(control.getColor())
                                .blurType(BlurType.TWO_PASS_BOX)
                                .build();

        // dot definitions
        d57 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D57, d57);

        d47 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D47, d47);

        d37 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D37, d37);

        d27 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D27, d27);

        d17 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D17, d17);

        d56 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D56, d56);

        d46 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D46, d46);

        d36 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D36, d36);

        d26 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D26, d26);

        d16 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D16, d16);

        d55 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D55, d55);

        d45 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D45, d45);

        d35 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D35, d35);

        d25 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D25, d25);

        d15 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D15, d15);

        d54 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D54, d54);

        d44 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D44, d44);

        d34 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D34, d34);

        d24 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D24, d24);

        d14 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D14, d14);

        d53 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D53, d53);

        d43 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D43, d43);

        d33 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D33, d33);

        d23 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D23, d23);

        d13 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D13, d13);

        d52 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D52, d52);

        d42 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D42, d42);

        d32 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D32, d32);

        d22 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D22, d22);

        d12 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D12, d12);

        d51 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D51, d51);

        d41 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D41, d41);

        d31 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D31, d31);

        d21 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D21, d21);

        d11 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D11, d11);

        // highlight definitions
        d57h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d57h);

        d47h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d47h);

        d37h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d37h);

        d27h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d27h);

        d17h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d17h);

        d56h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d56h);

        d46h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d46h);

        d36h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d36h);

        d26h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d26h);

        d16h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d16h);

        d55h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d55h);

        d45h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d45h);

        d35h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d35h);

        d25h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d25h);

        d15h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d15h);

        d54h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d54h);

        d44h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d44h);

        d34h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d34h);

        d24h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d24h);

        d14h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d14h);

        d53h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d53h);

        d43h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d43h);

        d33h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d33h);

        d23h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d23h);

        d13h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d13h);

        d52h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d52h);

        d42h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d42h);

        d32h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d32h);

        d22h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d22h);

        d12h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d12h);

        d51h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d51h);

        d41h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d41h);

        d31h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d31h);

        d21h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d21h);

        d11h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d11h);

        pane.getChildren().setAll(background,
                                  d57, d47, d37, d27, d17,
                                  d56, d46, d36, d26, d16,
                                  d55, d45, d35, d25, d15,
                                  d54, d44, d34, d24, d14,
                                  d53, d43, d33, d23, d13,
                                  d52, d42, d32, d22, d12,
                                  d51, d41, d31, d21, d11,
                                  d57h, d47h, d37h, d27h, d17h,
                                  d56h, d46h, d36h, d26h, d16h,
                                  d55h, d45h, d35h, d25h, d15h,
                                  d54h, d44h, d34h, d24h, d14h,
                                  d53h, d43h, d33h, d23h, d13h,
                                  d52h, d42h, d32h, d22h, d12h,
                                  d51h, d41h, d31h, d21h, d11h);

        getChildren().setAll(pane);

        resize();
        updateMatrix();
        updateMatrixColor();
        for (Region highlight : highlights) {
            highlight.setVisible(control.isHighlightsVisible());
        }
    }

    private void registerListeners() {
        registerChangeListener(control.widthProperty(), "RESIZE");
        registerChangeListener(control.heightProperty(), "RESIZE");
        registerChangeListener(control.prefWidthProperty(), "PREF_SIZE");
        registerChangeListener(control.prefHeightProperty(), "PREF_SIZE");
        registerChangeListener(control.colorProperty(), "COLOR");
        registerChangeListener(control.backgroundVisibleProperty(), "BACKGROUND");
        registerChangeListener(control.highlightsVisibleProperty(), "HIGHLIGHTS");
        registerChangeListener(control.characterProperty(), "CHARACTER");
        registerChangeListener(control.glowEnabledProperty(), "GLOW");

        control.getStyleClass().addListener(new ListChangeListener<String>() {
            @Override public void onChanged(Change<? extends String> change) {
                resize();
            }
        });
    }


    // ******************** Methods *******************************************
    @Override protected void handleControlPropertyChanged(final String PROPERTY) {
        super.handleControlPropertyChanged(PROPERTY);
        if ("RESIZE".equals(PROPERTY)) {
            resize();
        } else if ("PREF_SIZE".equals(PROPERTY)) {
            aspectRatio = control.getPrefHeight() / control.getPrefWidth();
        } else if ("COLOR".equals(PROPERTY)) {
            updateMatrixColor();
        } else if ("BACKGROUND".equals(PROPERTY)) {
            background.setVisible(control.isBackgroundVisible());
        } else if ("HIGHLIGHTS".equals(PROPERTY)) {
            for (Region highlight : highlights) {
                highlight.setVisible(control.isHighlightsVisible());
            }
        } else if ("CHARACTER".equals(PROPERTY)) {
            updateMatrix();
        } else if ("GLOW".equals(PROPERTY)) {
            updateMatrix();
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


    // ******************** Update ********************************************
    public void updateMatrixColor() {
        control.setStyle("-dot-on-color: " + colorToCss(control.getColor()) + ";");
        glow.setColor(control.getColor());
    }

    public void updateMatrix() {
        final int ASCII = control.getCharacter().isEmpty() ? 20 : control.getCharacter().toUpperCase().charAt(0);

        if (control.getCustomDotMapping().isEmpty()) {
            for (Dot dot : dotMap.keySet()) {
                if (control.getDotMapping().containsKey(ASCII)) {
                    if (control.getDotMapping().get(ASCII).contains(dot)) {
                        dotMap.get(dot).getStyleClass().setAll("dot-on");
                        dotMap.get(dot).setEffect(control.isGlowEnabled() ? glow : dotInnerShadow);
                    } else {
                        dotMap.get(dot).getStyleClass().setAll("dot-off");
                        dotMap.get(dot).setEffect(dotInnerShadow);
                    }
                } else {
                    dotMap.get(dot).getStyleClass().setAll("dot-off");
                    dotMap.get(dot).setEffect(dotInnerShadow);
                }
            }
        } else {
            for (Dot dot : dotMap.keySet()) {
                if (control.getCustomDotMapping().containsKey(ASCII)) {
                    if (control.getCustomDotMapping().get(ASCII).contains(dot)) {
                        dotMap.get(dot).getStyleClass().setAll("dot-on");
                        dotMap.get(dot).setEffect(control.isGlowEnabled() ? glow : dotInnerShadow);
                    } else {
                        dotMap.get(dot).getStyleClass().setAll("dot-off");
                        dotMap.get(dot).setEffect(dotInnerShadow);
                    }
                } else {
                    dotMap.get(dot).getStyleClass().setAll("dot-off");
                    dotMap.get(dot).setEffect(dotInnerShadow);
                }
            }
        }
    }


    // ******************** Resizing ******************************************
    private void resize() {
        size   = control.getWidth() < control.getHeight() ? control.getWidth() : control.getHeight();
        width  = control.getWidth();
        height = control.getHeight();

        if (aspectRatio * width > height) {
            width  = 1 / (aspectRatio / height);
        } else if (1 / (aspectRatio / height) > width) {
            height = aspectRatio * width;
        }

        // resize the background
        background.setPrefSize(width, height);
        backgroundInnerShadow.setRadius(0.0025 * size);
        backgroundInnerHighlight.setRadius(0.0025 * size);

        dotInnerShadow.setRadius(0.025 * width);
        glow.setRadius(0.032 * width);

        // resize the dots
        double dotWidth  = 0.13548387096774195 * width;
        double dotHeight = 0.0967741935483871 * height;

        for (Region dot : dotMap.values()) {
            dot.setPrefSize(dotWidth, dotHeight);
        }

        d57.setTranslateX(0.832258064516129 * width);
        d57.setTranslateY(0.880184331797235 * height);

        d47.setTranslateX(0.632258064516129 * width);
        d47.setTranslateY(0.880184331797235 * height);

        d37.setTranslateX(0.432258064516129 * width);
        d37.setTranslateY(0.880184331797235 * height);

        d27.setTranslateX(0.23225806451612904 * width);
        d27.setTranslateY(0.880184331797235 * height);

        d17.setTranslateX(0.03225806451612903 * width);
        d17.setTranslateY(0.880184331797235 * height);

        d56.setTranslateX(0.832258064516129 * width);
        d56.setTranslateY(0.7373271889400922 * height);

        d46.setTranslateX(0.632258064516129 * width);
        d46.setTranslateY(0.7373271889400922 * height);

        d36.setTranslateX(0.432258064516129 * width);
        d36.setTranslateY(0.7373271889400922 * height);

        d26.setTranslateX(0.23225806451612904 * width);
        d26.setTranslateY(0.7373271889400922 * height);

        d16.setTranslateX(0.03225806451612903 * width);
        d16.setTranslateY(0.7373271889400922 * height);

        d55.setTranslateX(0.832258064516129 * width);
        d55.setTranslateY(0.5944700460829493 * height);

        d45.setTranslateX(0.632258064516129 * width);
        d45.setTranslateY(0.5944700460829493 * height);

        d35.setTranslateX(0.432258064516129 * width);
        d35.setTranslateY(0.5944700460829493 * height);

        d25.setTranslateX(0.23225806451612904 * width);
        d25.setTranslateY(0.5944700460829493 * height);

        d15.setTranslateX(0.03225806451612903 * width);
        d15.setTranslateY(0.5944700460829493 * height);

        d54.setTranslateX(0.832258064516129 * width);
        d54.setTranslateY(0.45161290322580644 * height);

        d44.setTranslateX(0.632258064516129 * width);
        d44.setTranslateY(0.45161290322580644 * height);

        d34.setTranslateX(0.432258064516129 * width);
        d34.setTranslateY(0.45161290322580644 * height);

        d24.setTranslateX(0.23225806451612904 * width);
        d24.setTranslateY(0.45161290322580644 * height);

        d14.setTranslateX(0.03225806451612903 * width);
        d14.setTranslateY(0.45161290322580644 * height);

        d53.setTranslateX(0.832258064516129 * width);
        d53.setTranslateY(0.3087557603686636 * height);

        d43.setTranslateX(0.632258064516129 * width);
        d43.setTranslateY(0.3087557603686636 * height);

        d33.setTranslateX(0.432258064516129 * width);
        d33.setTranslateY(0.3087557603686636 * height);

        d23.setTranslateX(0.23225806451612904 * width);
        d23.setTranslateY(0.3087557603686636 * height);

        d13.setTranslateX(0.03225806451612903 * width);
        d13.setTranslateY(0.3087557603686636 * height);

        d52.setTranslateX(0.832258064516129 * width);
        d52.setTranslateY(0.16589861751152074 * height);

        d42.setTranslateX(0.632258064516129 * width);
        d42.setTranslateY(0.16589861751152074 * height);

        d32.setTranslateX(0.432258064516129 * width);
        d32.setTranslateY(0.16589861751152074 * height);

        d22.setTranslateX(0.23225806451612904 * width);
        d22.setTranslateY(0.16589861751152074 * height);

        d12.setTranslateX(0.03225806451612903 * width);
        d12.setTranslateY(0.16589861751152074 * height);

        d51.setTranslateX(0.832258064516129 * width);
        d51.setTranslateY(0.02304147465437788 * height);

        d41.setTranslateX(0.632258064516129 * width);
        d41.setTranslateY(0.02304147465437788 * height);

        d31.setTranslateX(0.432258064516129 * width);
        d31.setTranslateY(0.02304147465437788 * height);

        d21.setTranslateX(0.23225806451612904 * width);
        d21.setTranslateY(0.02304147465437788 * height);

        d11.setTranslateX(0.03225806451612903 * width);
        d11.setTranslateY(0.02304147465437788 * height);


        // resize the highlights
        double highlightWidth  = 0.07741935483870968 * width;
        double highlightHeight = 0.03456221198156682 * height;

        for (Region highlight : highlights) {
            highlight.setPrefSize(highlightWidth, highlightHeight);
        }

        d57h.setTranslateX(0.8612903225806452 * width);
        d57h.setTranslateY(0.8894009216589862 * height);

        d47h.setTranslateX(0.6612903225806451 * width);
        d47h.setTranslateY(0.8894009216589862 * height);

        d37h.setTranslateX(0.4612903225806452 * width);
        d37h.setTranslateY(0.8894009216589862 * height);

        d27h.setTranslateX(0.26129032258064516 * width);
        d27h.setTranslateY(0.8894009216589862 * height);

        d17h.setTranslateX(0.06129032258064516 * width);
        d17h.setTranslateY(0.8894009216589862 * height);

        d56h.setTranslateX(0.8612903225806452 * width);
        d56h.setTranslateY(0.7465437788018433 * height);

        d46h.setTranslateX(0.6612903225806451 * width);
        d46h.setTranslateY(0.7465437788018433 * height);

        d36h.setTranslateX(0.4612903225806452 * width);
        d36h.setTranslateY(0.7465437788018433 * height);

        d26h.setTranslateX(0.26129032258064516 * width);
        d26h.setTranslateY(0.7465437788018433 * height);

        d16h.setTranslateX(0.06129032258064516 * width);
        d16h.setTranslateY(0.7465437788018433 * height);

        d55h.setTranslateX(0.8612903225806452 * width);
        d55h.setTranslateY(0.6036866359447005 * height);

        d45h.setTranslateX(0.6612903225806451 * width);
        d45h.setTranslateY(0.6036866359447005 * height);

        d35h.setTranslateX(0.4612903225806452 * width);
        d35h.setTranslateY(0.6036866359447005 * height);

        d25h.setTranslateX(0.26129032258064516 * width);
        d25h.setTranslateY(0.6036866359447005 * height);

        d15h.setTranslateX(0.06129032258064516 * width);
        d15h.setTranslateY(0.6036866359447005 * height);

        d54h.setTranslateX(0.8612903225806452 * width);
        d54h.setTranslateY(0.4608294930875576 * height);

        d44h.setTranslateX(0.6612903225806451 * width);
        d44h.setTranslateY(0.4608294930875576 * height);

        d34h.setTranslateX(0.4612903225806452 * width);
        d34h.setTranslateY(0.4608294930875576 * height);

        d24h.setTranslateX(0.26129032258064516 * width);
        d24h.setTranslateY(0.4608294930875576 * height);

        d14h.setTranslateX(0.06129032258064516 * width);
        d14h.setTranslateY(0.4608294930875576 * height);

        d53h.setTranslateX(0.8612903225806452 * width);
        d53h.setTranslateY(0.31797235023041476 * height);

        d43h.setTranslateX(0.6612903225806451 * width);
        d43h.setTranslateY(0.31797235023041476 * height);

        d33h.setTranslateX(0.4612903225806452 * width);
        d33h.setTranslateY(0.31797235023041476 * height);

        d23h.setTranslateX(0.26129032258064516 * width);
        d23h.setTranslateY(0.31797235023041476 * height);

        d13h.setTranslateX(0.06129032258064516 * width);
        d13h.setTranslateY(0.31797235023041476 * height);

        d52h.setTranslateX(0.8612903225806452 * width);
        d52h.setTranslateY(0.17511520737327188 * height);

        d42h.setTranslateX(0.6612903225806451 * width);
        d42h.setTranslateY(0.17511520737327188 * height);

        d32h.setTranslateX(0.4612903225806452 * width);
        d32h.setTranslateY(0.17511520737327188 * height);

        d22h.setTranslateX(0.26129032258064516 * width);
        d22h.setTranslateY(0.17511520737327188 * height);

        d12h.setTranslateX(0.06129032258064516 * width);
        d12h.setTranslateY(0.17511520737327188 * height);

        d51h.setTranslateX(0.8612903225806452 * width);
        d51h.setTranslateY(0.03225806451612903 * height);

        d41h.setTranslateX(0.6612903225806451 * width);
        d41h.setTranslateY(0.03225806451612903 * height);

        d31h.setTranslateX(0.4612903225806452 * width);
        d31h.setTranslateY(0.03225806451612903 * height);

        d21h.setTranslateX(0.26129032258064516 * width);
        d21h.setTranslateY(0.03225806451612903 * height);

        d11h.setTranslateX(0.06129032258064516 * width);
        d11h.setTranslateY(0.03225806451612903 * height);
    }
}
