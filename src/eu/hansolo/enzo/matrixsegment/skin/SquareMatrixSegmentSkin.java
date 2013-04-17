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

import eu.hansolo.enzo.matrixsegment.SquareMatrixSegment;
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
import javafx.scene.layout.RegionBuilder;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static eu.hansolo.enzo.matrixsegment.SquareMatrixSegment.Dot;


public class SquareMatrixSegmentSkin extends SkinBase<SquareMatrixSegment> implements Skin<SquareMatrixSegment> {
    private static final double DEFAULT_WIDTH  = 100;
    private static final double DEFAULT_HEIGHT = 100;
    private static final double MINIMUM_WIDTH  = 5;
    private static final double MINIMUM_HEIGHT = 5;
    private static final double MAXIMUM_WIDTH  = 1024;
    private static final double MAXIMUM_HEIGHT = 1024;
    private static double aspectRatio;
    private Map<SquareMatrixSegment.Dot, Region> dotMap;
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
    private Region        d67;
    private Region        d57;
    private Region        d47;
    private Region        d37;
    private Region        d27;
    private Region        d17;
    private Region        d07;
    private Region        d66;
    private Region        d56;
    private Region        d46;
    private Region        d36;
    private Region        d26;
    private Region        d16;
    private Region        d06;
    private Region        d65;
    private Region        d55;
    private Region        d45;
    private Region        d35;
    private Region        d25;
    private Region        d15;
    private Region        d05;
    private Region        d64;
    private Region        d54;
    private Region        d44;
    private Region        d34;
    private Region        d24;
    private Region        d14;
    private Region        d04;
    private Region        d63;
    private Region        d53;
    private Region        d43;
    private Region        d33;
    private Region        d23;
    private Region        d13;
    private Region        d03;
    private Region        d62;
    private Region        d52;
    private Region        d42;
    private Region        d32;
    private Region        d22;
    private Region        d12;
    private Region        d02;
    private Region        d61;
    private Region        d51;
    private Region        d41;
    private Region        d31;
    private Region        d21;
    private Region        d11;
    private Region        d01;
    private Region        d67h;
    private Region        d57h;
    private Region        d47h;
    private Region        d37h;
    private Region        d27h;
    private Region        d17h;
    private Region        d07h;
    private Region        d66h;
    private Region        d56h;
    private Region        d46h;
    private Region        d36h;
    private Region        d26h;
    private Region        d16h;
    private Region        d06h;
    private Region        d65h;
    private Region        d55h;
    private Region        d45h;
    private Region        d35h;
    private Region        d25h;
    private Region        d15h;
    private Region        d05h;
    private Region        d64h;
    private Region        d54h;
    private Region        d44h;
    private Region        d34h;
    private Region        d24h;
    private Region        d14h;
    private Region        d04h;
    private Region        d63h;
    private Region        d53h;
    private Region        d43h;
    private Region        d33h;
    private Region        d23h;
    private Region        d13h;
    private Region        d03h;
    private Region        d62h;
    private Region        d52h;
    private Region        d42h;
    private Region        d32h;
    private Region        d22h;
    private Region        d12h;
    private Region        d02h;
    private Region        d61h;
    private Region        d51h;
    private Region        d41h;
    private Region        d31h;
    private Region        d21h;
    private Region        d11h;
    private Region        d01h;


    // ******************** Constructors **************************************
    public SquareMatrixSegmentSkin(final SquareMatrixSegment CONTROL) {
        super(CONTROL);
        aspectRatio = 1.0;
        dotMap      = new HashMap<>(35);
        highlights  = new ArrayList<>(35);
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
                getSkinnable().setPrefSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
            }
        }

        if (Double.compare(getSkinnable().getMinWidth(), 0.0) <= 0 || Double.compare(getSkinnable().getMinHeight(), 0.0) <= 0) {
            getSkinnable().setMinSize(MINIMUM_WIDTH, MINIMUM_HEIGHT);
        }

        if (Double.compare(getSkinnable().getMaxWidth(), 0.0) <= 0 || Double.compare(getSkinnable().getMaxHeight(), 0.0) <= 0) {
            getSkinnable().setMaxSize(MAXIMUM_WIDTH, MAXIMUM_HEIGHT);
        }

        if (getSkinnable().getPrefWidth() != DEFAULT_WIDTH || getSkinnable().getPrefHeight() != DEFAULT_HEIGHT) {
            aspectRatio = getSkinnable().getPrefHeight() / getSkinnable().getPrefWidth();
        }
    }

    private void initGraphics() {

        background = new Region();
        background.getStyleClass().setAll("background");


        backgroundInnerShadow = InnerShadowBuilder.create()
                                                  .offsetY(-1.0)
                                                  .radius(1.0 / 434.0 * DEFAULT_WIDTH)
                                                  .color(Color.rgb(0, 0, 0, 0.65))
                                                  .blurType(BlurType.TWO_PASS_BOX)
                                                  .build();
        backgroundInnerHighlight = InnerShadowBuilder.create()
                                                     .offsetY(1.0)
                                                     .radius(1.0 / 434.0 * DEFAULT_WIDTH)
                                                     .color(Color.rgb(200, 200, 200, 0.65))
                                                     .blurType(BlurType.TWO_PASS_BOX)
                                                     .input(backgroundInnerShadow)
                                                     .build();
        background.setEffect(backgroundInnerHighlight);

        dotInnerShadow = InnerShadowBuilder.create()
                                           .radius(0.018 * DEFAULT_WIDTH)
                                           .color(Color.rgb(0, 0, 0, 0.65))
                                           .blurType(BlurType.TWO_PASS_BOX)
                                           .build();

        glow = DropShadowBuilder.create()
                                .input(dotInnerShadow)
                                .radius(0.023 * DEFAULT_WIDTH)
                                .color(getSkinnable().getColor())
                                .blurType(BlurType.TWO_PASS_BOX)
                                .build();

        // dot definitions
        d67 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D67, d67);

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

        d07 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D07, d07);

        d66 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D66, d66);

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

        d06 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D06, d06);

        d65 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D65, d65);

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

        d05 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D05, d05);

        d64 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D64, d64);

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

        d04 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D04, d04);

        d63 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D63, d63);

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

        d03 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D03, d03);

        d62 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D62, d62);

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

        d02 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D02, d02);

        d61 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D61, d61);

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

        d01 = RegionBuilder.create().styleClass("dot-off").effect(dotInnerShadow).build();
        dotMap.put(Dot.D01, d01);

        // define highlights
        d67h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d67h);

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

        d07h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d07h);

        d66h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d66h);

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

        d06h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d06h);

        d65h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d65h);

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

        d05h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d05h);

        d64h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d64h);

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

        d04h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d04h);

        d63h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d63h);

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

        d03h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d03h);

        d62h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d62h);

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

        d02h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d02h);

        d61h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d61h);

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

        d01h = RegionBuilder.create().styleClass("dot-highlight").build();
        highlights.add(d01h);

        pane.getChildren().setAll(background,
                                  d67, d57, d47, d37, d27, d17, d07,
                                  d66, d56, d46, d36, d26, d16, d06,
                                  d65, d55, d45, d35, d25, d15, d05,
                                  d64, d54, d44, d34, d24, d14, d04,
                                  d63, d53, d43, d33, d23, d13, d03,
                                  d62, d52, d42, d32, d22, d12, d02,
                                  d61, d51, d41, d31, d21, d11, d01,
                                  d67h, d57h, d47h, d37h, d27h, d17h, d07h,
                                  d66h, d56h, d46h, d36h, d26h, d16h, d06h,
                                  d65h, d55h, d45h, d35h, d25h, d15h, d05h,
                                  d64h, d54h, d44h, d34h, d24h, d14h, d04h,
                                  d63h, d53h, d43h, d33h, d23h, d13h, d03h,
                                  d62h, d52h, d42h, d32h, d22h, d12h, d02h,
                                  d61h, d51h, d41h, d31h, d21h, d11h, d01h);

        getChildren().setAll(pane);

        resize();
        updateMatrix();
        updateMatrixColor();
        for (Region highlight : highlights) {
            highlight.setVisible(getSkinnable().isHighlightsVisible());
        }
    }

    private void registerListeners() {
        getSkinnable().widthProperty().addListener(observable -> { handleControlPropertyChanged("RESIZE"); });
        getSkinnable().heightProperty().addListener(observable -> { handleControlPropertyChanged("RESIZE"); });
        getSkinnable().prefWidthProperty().addListener(observable -> { handleControlPropertyChanged("PREF_SIZE"); });
        getSkinnable().prefHeightProperty().addListener(observable -> { handleControlPropertyChanged("PREF_SIZE"); });
        getSkinnable().colorProperty().addListener(observable -> { handleControlPropertyChanged("COLOR"); });
        getSkinnable().backgroundVisibleProperty().addListener(observable -> { handleControlPropertyChanged("BACKGROUND"); });
        getSkinnable().highlightsVisibleProperty().addListener(observable -> { handleControlPropertyChanged("HIGHLIGHTS"); });
        getSkinnable().characterProperty().addListener(observable -> { handleControlPropertyChanged("CHARACTER"); });
        getSkinnable().glowEnabledProperty().addListener(observable -> { handleControlPropertyChanged("GLOW"); });

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
            aspectRatio = 1.0;
        } else if ("COLOR".equals(PROPERTY)) {
            updateMatrixColor();
        } else if ("BACKGROUND".equals(PROPERTY)) {
            background.setVisible(getSkinnable().isBackgroundVisible());
        } else if ("HIGHLIGHTS".equals(PROPERTY)) {
            for (Region highlight : highlights) {
                highlight.setVisible(getSkinnable().isHighlightsVisible());
            }
        } else if ("CHARACTER".equals(PROPERTY)) {
            updateMatrix();
        } else if ("GLOW".equals(PROPERTY)) {
            updateMatrix();
        }
    }

    @Override protected double computePrefWidth(final double PREF_HEIGHT) {
        double prefHeight = DEFAULT_HEIGHT;
        if (PREF_HEIGHT != -1) {
            prefHeight = Math.max(0, PREF_HEIGHT - getSkinnable().getInsets().getTop() - getSkinnable().getInsets().getBottom());
        }
        return super.computePrefWidth(prefHeight);
    }
    @Override protected double computePrefHeight(final double PREF_WIDTH) {
        double prefWidth = DEFAULT_WIDTH;
        if (PREF_WIDTH != -1) {
            prefWidth = Math.max(0, PREF_WIDTH - getSkinnable().getInsets().getLeft() - getSkinnable().getInsets().getRight());
        }
        return super.computePrefWidth(prefWidth);
    }

    @Override protected double computeMinWidth(final double MIN_HEIGHT) {
        return super.computeMinWidth(Math.max(MINIMUM_HEIGHT, MIN_HEIGHT - getSkinnable().getInsets().getTop() - getSkinnable().getInsets().getBottom()));
    }
    @Override protected double computeMinHeight(final double MIN_WIDTH) {
        return super.computeMinHeight(Math.max(MINIMUM_WIDTH, MIN_WIDTH - getSkinnable().getInsets().getLeft() - getSkinnable().getInsets().getRight()));
    }

    @Override protected double computeMaxWidth(final double MAX_HEIGHT) {
        return super.computeMaxWidth(Math.min(MAXIMUM_HEIGHT, MAX_HEIGHT - getSkinnable().getInsets().getTop() - getSkinnable().getInsets().getBottom()));
    }
    @Override protected double computeMaxHeight(final double MAX_WIDTH) {
        return super.computeMaxHeight(Math.min(MAXIMUM_WIDTH, MAX_WIDTH - getSkinnable().getInsets().getLeft() - getSkinnable().getInsets().getRight()));
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
        getSkinnable().setStyle("-dot-on-color: " + colorToCss(getSkinnable().getColor()) + ";");
        glow.setColor(getSkinnable().getColor());
    }

    public void updateMatrix() {
        final int ASCII = getSkinnable().getCharacter().isEmpty() ? 20 : getSkinnable().getCharacter().toUpperCase().charAt(0);

        if (getSkinnable().getCustomDotMapping().isEmpty()) {
            for (SquareMatrixSegment.Dot dot : dotMap.keySet()) {
                if (getSkinnable().getDotMapping().containsKey(ASCII)) {
                    if (getSkinnable().getDotMapping().get(ASCII).contains(dot)) {
                        dotMap.get(dot).getStyleClass().setAll("dot-on");
                        dotMap.get(dot).setEffect(getSkinnable().isGlowEnabled() ? glow : dotInnerShadow);
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
            for (SquareMatrixSegment.Dot dot : dotMap.keySet()) {
                if (getSkinnable().getCustomDotMapping().containsKey(ASCII)) {
                    if (getSkinnable().getCustomDotMapping().get(ASCII).contains(dot)) {
                        dotMap.get(dot).getStyleClass().setAll("dot-on");
                        dotMap.get(dot).setEffect(getSkinnable().isGlowEnabled() ? glow : dotInnerShadow);
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
        size   = getSkinnable().getWidth() < getSkinnable().getHeight() ? getSkinnable().getWidth() : getSkinnable().getHeight();
        width  = getSkinnable().getWidth();
        height = getSkinnable().getHeight();

        if (aspectRatio * width > height) {
            width  = 1 / (aspectRatio / height);
        } else if (1 / (aspectRatio / height) > width) {
            height = aspectRatio * width;
        }

        if (width > 0 && height > 0) {
            background.setPrefSize(width, height);
            backgroundInnerShadow.setRadius(0.0015 * size);
            backgroundInnerHighlight.setRadius(0.0015 * size);

            dotInnerShadow.setRadius(0.018 * width);
            glow.setRadius(0.023 * width);

            double dotWidth  = 0.0967741935483871 * width;
            double dotHeight = 0.0967741935483871 * height;

            for (Region dot : dotMap.values()) {
                dot.setPrefSize(dotWidth, dotHeight);
            }

            d67.setTranslateX(0.880184331797235 * width);
            d67.setTranslateY(0.880184331797235 * height);

            d57.setTranslateX(0.7373271889400922 * width);
            d57.setTranslateY(0.880184331797235 * height);

            d47.setTranslateX(0.5944700460829493 * width);
            d47.setTranslateY(0.880184331797235 * height);

            d37.setTranslateX(0.45161290322580644 * width);
            d37.setTranslateY(0.880184331797235 * height);

            d27.setTranslateX(0.3087557603686636 * width);
            d27.setTranslateY(0.880184331797235 * height);

            d17.setTranslateX(0.16589861751152074 * width);
            d17.setTranslateY(0.880184331797235 * height);

            d07.setTranslateX(0.02304147465437788 * width);
            d07.setTranslateY(0.880184331797235 * height);

            d66.setTranslateX(0.880184331797235 * width);
            d66.setTranslateY(0.7373271889400922 * height);

            d56.setTranslateX(0.7373271889400922 * width);
            d56.setTranslateY(0.7373271889400922 * height);

            d46.setTranslateX(0.5944700460829493 * width);
            d46.setTranslateY(0.7373271889400922 * height);

            d36.setTranslateX(0.45161290322580644 * width);
            d36.setTranslateY(0.7373271889400922 * height);

            d26.setTranslateX(0.3087557603686636 * width);
            d26.setTranslateY(0.7373271889400922 * height);

            d16.setTranslateX(0.16589861751152074 * width);
            d16.setTranslateY(0.7373271889400922 * height);

            d06.setTranslateX(0.02304147465437788 * width);
            d06.setTranslateY(0.7373271889400922 * height);

            d65.setTranslateX(0.880184331797235 * width);
            d65.setTranslateY(0.5944700460829493 * height);

            d55.setTranslateX(0.7373271889400922 * width);
            d55.setTranslateY(0.5944700460829493 * height);

            d45.setTranslateX(0.5944700460829493 * width);
            d45.setTranslateY(0.5944700460829493 * height);

            d35.setTranslateX(0.45161290322580644 * width);
            d35.setTranslateY(0.5944700460829493 * height);

            d25.setTranslateX(0.3087557603686636 * width);
            d25.setTranslateY(0.5944700460829493 * height);

            d15.setTranslateX(0.16589861751152074 * width);
            d15.setTranslateY(0.5944700460829493 * height);

            d05.setTranslateX(0.02304147465437788 * width);
            d05.setTranslateY(0.5944700460829493 * height);

            d64.setTranslateX(0.880184331797235 * width);
            d64.setTranslateY(0.45161290322580644 * height);

            d54.setTranslateX(0.7373271889400922 * width);
            d54.setTranslateY(0.45161290322580644 * height);

            d44.setTranslateX(0.5944700460829493 * width);
            d44.setTranslateY(0.45161290322580644 * height);

            d34.setTranslateX(0.45161290322580644 * width);
            d34.setTranslateY(0.45161290322580644 * height);

            d24.setTranslateX(0.3087557603686636 * width);
            d24.setTranslateY(0.45161290322580644 * height);

            d14.setTranslateX(0.16589861751152074 * width);
            d14.setTranslateY(0.45161290322580644 * height);

            d04.setTranslateX(0.02304147465437788 * width);
            d04.setTranslateY(0.45161290322580644 * height);

            d63.setTranslateX(0.880184331797235 * width);
            d63.setTranslateY(0.3087557603686636 * height);

            d53.setTranslateX(0.7373271889400922 * width);
            d53.setTranslateY(0.3087557603686636 * height);

            d43.setTranslateX(0.5944700460829493 * width);
            d43.setTranslateY(0.3087557603686636 * height);

            d33.setTranslateX(0.45161290322580644 * width);
            d33.setTranslateY(0.3087557603686636 * height);

            d23.setTranslateX(0.3087557603686636 * width);
            d23.setTranslateY(0.3087557603686636 * height);

            d13.setTranslateX(0.16589861751152074 * width);
            d13.setTranslateY(0.3087557603686636 * height);

            d03.setTranslateX(0.02304147465437788 * width);
            d03.setTranslateY(0.3087557603686636 * height);

            d62.setTranslateX(0.880184331797235 * width);
            d62.setTranslateY(0.16589861751152074 * height);

            d52.setTranslateX(0.7373271889400922 * width);
            d52.setTranslateY(0.16589861751152074 * height);

            d42.setTranslateX(0.5944700460829493 * width);
            d42.setTranslateY(0.16589861751152074 * height);

            d32.setTranslateX(0.45161290322580644 * width);
            d32.setTranslateY(0.16589861751152074 * height);

            d22.setTranslateX(0.3087557603686636 * width);
            d22.setTranslateY(0.16589861751152074 * height);

            d12.setTranslateX(0.16589861751152074 * width);
            d12.setTranslateY(0.16589861751152074 * height);

            d02.setTranslateX(0.02304147465437788 * width);
            d02.setTranslateY(0.16589861751152074 * height);

            d61.setTranslateX(0.880184331797235 * width);
            d61.setTranslateY(0.02304147465437788 * height);

            d51.setTranslateX(0.7373271889400922 * width);
            d51.setTranslateY(0.02304147465437788 * height);

            d41.setTranslateX(0.5944700460829493 * width);
            d41.setTranslateY(0.02304147465437788 * height);

            d31.setTranslateX(0.45161290322580644 * width);
            d31.setTranslateY(0.02304147465437788 * height);

            d21.setTranslateX(0.3087557603686636 * width);
            d21.setTranslateY(0.02304147465437788 * height);

            d11.setTranslateX(0.16589861751152074 * width);
            d11.setTranslateY(0.02304147465437788 * height);

            d01.setTranslateX(0.02304147465437788 * width);
            d01.setTranslateY(0.02304147465437788 * height);

            // resize the highlights
            double highlightWidth  = 0.055299539170506916 * width;
            double highlightHeight = 0.03456221198156682 * height;

            for (Region highlight : highlights) {
                highlight.setPrefSize(highlightWidth, highlightHeight);
            }

            d67h.setTranslateX(0.9009216589861752 * width);
            d67h.setTranslateY(0.8894009216589862 * height);

            d57h.setTranslateX(0.7580645161290323 * width);
            d57h.setTranslateY(0.8894009216589862 * height);

            d47h.setTranslateX(0.6152073732718893 * width);
            d47h.setTranslateY(0.8894009216589862 * height);

            d37h.setTranslateX(0.47235023041474655 * width);
            d37h.setTranslateY(0.8894009216589862 * height);

            d27h.setTranslateX(0.3294930875576037 * width);
            d27h.setTranslateY(0.8894009216589862 * height);

            d17h.setTranslateX(0.18663594470046083 * width);
            d17h.setTranslateY(0.8894009216589862 * height);

            d07h.setTranslateX(0.04377880184331797 * width);
            d07h.setTranslateY(0.8894009216589862 * height);

            d66h.setTranslateX(0.9009216589861752 * width);
            d66h.setTranslateY(0.7465437788018433 * height);

            d56h.setTranslateX(0.7580645161290323 * width);
            d56h.setTranslateY(0.7465437788018433 * height);

            d46h.setTranslateX(0.6152073732718893 * width);
            d46h.setTranslateY(0.7465437788018433 * height);

            d36h.setTranslateX(0.47235023041474655 * width);
            d36h.setTranslateY(0.7465437788018433 * height);

            d26h.setTranslateX(0.3294930875576037 * width);
            d26h.setTranslateY(0.7465437788018433 * height);

            d16h.setTranslateX(0.18663594470046083 * width);
            d16h.setTranslateY(0.7465437788018433 * height);

            d06h.setTranslateX(0.04377880184331797 * width);
            d06h.setTranslateY(0.7465437788018433 * height);

            d65h.setTranslateX(0.9009216589861752 * width);
            d65h.setTranslateY(0.6036866359447005 * height);

            d55h.setTranslateX(0.7580645161290323 * width);
            d55h.setTranslateY(0.6036866359447005 * height);

            d45h.setTranslateX(0.6152073732718893 * width);
            d45h.setTranslateY(0.6036866359447005 * height);

            d35h.setTranslateX(0.47235023041474655 * width);
            d35h.setTranslateY(0.6036866359447005 * height);

            d25h.setTranslateX(0.3294930875576037 * width);
            d25h.setTranslateY(0.6036866359447005 * height);

            d15h.setTranslateX(0.18663594470046083 * width);
            d15h.setTranslateY(0.6036866359447005 * height);

            d05h.setTranslateX(0.04377880184331797 * width);
            d05h.setTranslateY(0.6036866359447005 * height);

            d64h.setTranslateX(0.9009216589861752 * width);
            d64h.setTranslateY(0.4608294930875576 * height);

            d54h.setTranslateX(0.7580645161290323 * width);
            d54h.setTranslateY(0.4608294930875576 * height);

            d44h.setTranslateX(0.6152073732718893 * width);
            d44h.setTranslateY(0.4608294930875576 * height);

            d34h.setTranslateX(0.47235023041474655 * width);
            d34h.setTranslateY(0.4608294930875576 * height);

            d24h.setTranslateX(0.3294930875576037 * width);
            d24h.setTranslateY(0.4608294930875576 * height);

            d14h.setTranslateX(0.18663594470046083 * width);
            d14h.setTranslateY(0.4608294930875576 * height);

            d04h.setTranslateX(0.04377880184331797 * width);
            d04h.setTranslateY(0.4608294930875576 * height);

            d63h.setTranslateX(0.9009216589861752 * width);
            d63h.setTranslateY(0.31797235023041476 * height);

            d53h.setTranslateX(0.7580645161290323 * width);
            d53h.setTranslateY(0.31797235023041476 * height);

            d43h.setTranslateX(0.6152073732718893 * width);
            d43h.setTranslateY(0.31797235023041476 * height);

            d33h.setTranslateX(0.47235023041474655 * width);
            d33h.setTranslateY(0.31797235023041476 * height);

            d23h.setTranslateX(0.3294930875576037 * width);
            d23h.setTranslateY(0.31797235023041476 * height);

            d13h.setTranslateX(0.18663594470046083 * width);
            d13h.setTranslateY(0.31797235023041476 * height);

            d03h.setTranslateX(0.04377880184331797 * width);
            d03h.setTranslateY(0.31797235023041476 * height);

            d62h.setTranslateX(0.9009216589861752 * width);
            d62h.setTranslateY(0.17511520737327188 * height);

            d52h.setTranslateX(0.7580645161290323 * width);
            d52h.setTranslateY(0.17511520737327188 * height);

            d42h.setTranslateX(0.6152073732718893 * width);
            d42h.setTranslateY(0.17511520737327188 * height);

            d32h.setTranslateX(0.47235023041474655 * width);
            d32h.setTranslateY(0.17511520737327188 * height);

            d22h.setTranslateX(0.3294930875576037 * width);
            d22h.setTranslateY(0.17511520737327188 * height);

            d12h.setTranslateX(0.18663594470046083 * width);
            d12h.setTranslateY(0.17511520737327188 * height);

            d02h.setTranslateX(0.04377880184331797 * width);
            d02h.setTranslateY(0.17511520737327188 * height);

            d61h.setTranslateX(0.9009216589861752 * width);
            d61h.setTranslateY(0.03225806451612903 * height);

            d51h.setTranslateX(0.7580645161290323 * width);
            d51h.setTranslateY(0.03225806451612903 * height);

            d41h.setTranslateX(0.6152073732718893 * width);
            d41h.setTranslateY(0.03225806451612903 * height);

            d31h.setTranslateX(0.47235023041474655 * width);
            d31h.setTranslateY(0.03225806451612903 * height);

            d21h.setTranslateX(0.3294930875576037 * width);
            d21h.setTranslateY(0.03225806451612903 * height);

            d11h.setTranslateX(0.18663594470046083 * width);
            d11h.setTranslateY(0.03225806451612903 * height);

            d01h.setTranslateX(0.04377880184331797 * width);
            d01h.setTranslateY(0.03225806451612903 * height);
        }
    }
}
