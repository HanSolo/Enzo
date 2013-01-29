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

package eu.hansolo.enzo.sevensegment.skin;

import com.sun.javafx.scene.control.skin.BehaviorSkinBase;
import eu.hansolo.enzo.sevensegment.SevenSegment;
import eu.hansolo.enzo.sevensegment.behavior.SevenSegmentBehavior;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;


public class SevenSegmentSkin extends BehaviorSkinBase<SevenSegment, SevenSegmentBehavior> {
    private static final double DEFAULT_WIDTH  = 268;
    private static final double DEFAULT_HEIGHT = 357;
    private static final double MINIMUM_WIDTH  = 5;
    private static final double MINIMUM_HEIGHT = 5;
    private static final double MAXIMUM_WIDTH  = 1024;
    private static final double MAXIMUM_HEIGHT = 1024;
    private SevenSegment control;
    private static double aspectRatio;
    private double        size;
    private double        width;
    private double        height;
    private Pane          pane;
    private Region        segmentA;
    private Region        segmentB;
    private Region        segmentC;
    private Region        segmentD;
    private Region        segmentE;
    private Region        segmentF;
    private Region        segmentG;
    private Region        segmentDot;
    private Map<SevenSegment.Segment, Region> segmentMap;


    // ******************** Constructors **************************************
    public SevenSegmentSkin(final SevenSegment CONTROL) {
        super(CONTROL, new SevenSegmentBehavior(CONTROL));
        control     = CONTROL;
        aspectRatio = DEFAULT_HEIGHT / DEFAULT_WIDTH;
        pane        = new Pane();
        segmentMap  = new HashMap<SevenSegment.Segment, Region>(17);
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

        segmentA = new Region();
        segmentA.getStyleClass().setAll("a");
        segmentMap.put(SevenSegment.Segment.A, segmentA);

        segmentB = new Region();
        segmentB.getStyleClass().setAll("b");
        segmentMap.put(SevenSegment.Segment.B, segmentB);

        segmentC = new Region();
        segmentC.getStyleClass().setAll("c");
        segmentMap.put(SevenSegment.Segment.C, segmentC);

        segmentD = new Region();
        segmentD.getStyleClass().setAll("d");
        segmentMap.put(SevenSegment.Segment.D, segmentD);

        segmentE = new Region();
        segmentE.getStyleClass().setAll("e");
        segmentMap.put(SevenSegment.Segment.E, segmentE);

        segmentF = new Region();
        segmentF.getStyleClass().setAll("f");
        segmentMap.put(SevenSegment.Segment.F, segmentF);

        segmentG = new Region();
        segmentG.getStyleClass().setAll("g");
        segmentMap.put(SevenSegment.Segment.G, segmentG);

        segmentDot = new Region();
        segmentDot.getStyleClass().setAll("dot");
        segmentMap.put(SevenSegment.Segment.DOT, segmentDot);

        pane.getChildren().setAll(segmentA,
            segmentB,
            segmentC,
            segmentD,
            segmentE,
            segmentF,
            segmentG,
            segmentDot);

        getChildren().setAll(pane);
        resize();
        update();
    }

    private void registerListeners() {
        registerChangeListener(control.widthProperty(), "RESIZE");
        registerChangeListener(control.heightProperty(), "RESIZE");
        registerChangeListener(control.prefWidthProperty(), "PREF_SIZE");
        registerChangeListener(control.prefHeightProperty(), "PREF_SIZE");
        registerChangeListener(control.characterProperty(), "CHARACTER");
        registerChangeListener(control.dotOnProperty(), "DOT_ON");
        registerChangeListener(control.segmentStyleProperty(), "UPDATE");

        control.getStyleClass().addListener(new ListChangeListener<String>() {
            @Override public void onChanged(Change<? extends String> change) {
                resize();
                update();
            }
        });
    }


    // ******************** Methods *******************************************
    @Override protected void handleControlPropertyChanged(final String PROPERTY) {
        super.handleControlPropertyChanged(PROPERTY);
        if ("UPDATE".equals(PROPERTY)) {
            update();
        } else if ("RESIZE".equals(PROPERTY)) {
            resize();
            update();
        } else if ("PREF_SIZE".equals(PROPERTY)) {
            aspectRatio = control.getPrefHeight() / control.getPrefWidth();
        } else if ("CHARACTER".equals(PROPERTY)) {
            update();
        } else if ("DOT_ON".equals(PROPERTY)) {
            update();
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
    private void update() {
        final int    ASCII    = control.getCharacter().isEmpty() ? 20 : control.getCharacter().toUpperCase().charAt(0);
        final String ON_STYLE = control.getSegmentStyle().CLASS;

        for (SevenSegment.Segment segment : segmentMap.keySet()) {
            if (control.getSegmentMapping().containsKey(ASCII)) {
                if (control.getSegmentMapping().get(ASCII).contains(segment)) {
                    segmentMap.get(segment).getStyleClass().setAll(segment.name().toLowerCase(), ON_STYLE);
                } else {
                    segmentMap.get(segment).getStyleClass().setAll(segment.name().toLowerCase(), SevenSegment.STYLE_CLASS_OFF);
                }
            } else {
                segmentMap.get(segment).getStyleClass().setAll(segment.name().toLowerCase(), SevenSegment.STYLE_CLASS_OFF);
            }
        }

        if (control.isDotOn()) {
            segmentMap.get(SevenSegment.Segment.DOT).getStyleClass().setAll("dot", ON_STYLE);
        } else {
            segmentMap.get(SevenSegment.Segment.DOT).getStyleClass().setAll("dot", SevenSegment.STYLE_CLASS_OFF);
        }
    }


    // ******************** Resizing ******************************************
    private void resize() {
        size   = control.getWidth() < control.getHeight() ? control.getWidth() : control.getHeight();
        width  = control.getWidth();
        height = control.getHeight();
        if (control.isKeepAspect()) {
            if (aspectRatio * width > height) {
                width  = 1 / (aspectRatio / height);
            } else if (1 / (aspectRatio / height) > width) {
                height = aspectRatio * width;
            }
        }

        segmentA.setPrefSize(0.7078622846461055 * width, 0.12605043085349374 * height);
        segmentA.setTranslateX(0.11621808294040054 * width);
        segmentA.setTranslateY(0.0 * height);

        segmentB.setPrefSize(0.19782541758978545 * width, 0.4623330167027749 * height);
        segmentB.setTranslateX(0.6715775674848414 * width);
        segmentB.setTranslateY(0.03402365890203738 * height);

        segmentC.setPrefSize(0.20474943474157534 * width, 0.46794295110622375 * height);
        segmentC.setTranslateX(0.6267936478799848 * width);
        segmentC.setTranslateY(0.5036215902376575 * height);

        segmentD.setPrefSize(0.7041493885552705 * width, 0.12605042016806722 * height);
        segmentD.setTranslateX(0.04532256055234084 * width);
        segmentD.setTranslateY(0.8739495798319328 * height);

        segmentE.setPrefSize(0.20156638302020172 * width, 0.4595308704536502 * height);
        segmentE.setTranslateX(0.0 * width);
        segmentE.setTranslateY(0.5036443288252801 * height);

        segmentF.setPrefSize(0.20103195532044368 * width, 0.47076633998325895 * height);
        segmentF.setTranslateX(0.03783089367311392 * width);
        segmentF.setTranslateY(0.028435463998831956 * height);

        segmentG.setPrefSize(0.6134279165694962 * width, 0.12605042016806722 * height);
        segmentG.setTranslateX(0.12985323436224638 * width);
        segmentG.setTranslateY(0.4369747899159664 * height);

        segmentDot.setPrefSize(0.16791044776119404 * width, 0.12605042016806722 * height);
        segmentDot.setTranslateX(0.832089552238806 * width);
        segmentDot.setTranslateY(0.8739495798319328 * height);

    }


}

