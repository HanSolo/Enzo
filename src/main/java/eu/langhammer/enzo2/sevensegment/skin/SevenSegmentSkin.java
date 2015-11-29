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

package eu.langhammer.enzo2.sevensegment.skin;

import eu.langhammer.enzo2.sevensegment.SevenSegment;
import javafx.collections.ListChangeListener;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

import java.util.HashMap;
import java.util.Map;


public class SevenSegmentSkin extends SkinBase<SevenSegment> implements Skin<SevenSegment> {
    private static final double PREFERRED_WIDTH  = 268;
    private static final double PREFERRED_HEIGHT = 357;
    private static final double MINIMUM_WIDTH    = 5;
    private static final double MINIMUM_HEIGHT   = 5;
    private static final double MAXIMUM_WIDTH    = 1024;
    private static final double MAXIMUM_HEIGHT   = 1024;
    private static double       aspectRatio;
    private double              size;
    private double              width;
    private double              height;
    private Pane                pane;
    private Region              segmentA;
    private Region              segmentB;
    private Region              segmentC;
    private Region              segmentD;
    private Region              segmentE;
    private Region              segmentF;
    private Region              segmentG;
    private Region              segmentDot;
    private Map<SevenSegment.Segment, Region> segmentMap;


    // ******************** Constructors **************************************
    public SevenSegmentSkin(final SevenSegment CONTROL) {
        super(CONTROL);
        aspectRatio = PREFERRED_HEIGHT / PREFERRED_WIDTH;
        pane        = new Pane();
        segmentMap  = new HashMap<>(8);
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
        getSkinnable().widthProperty().addListener(observable -> handleControlPropertyChanged("RESIZE") );
        getSkinnable().heightProperty().addListener(observable -> handleControlPropertyChanged("RESIZE") );
        getSkinnable().prefWidthProperty().addListener(observable -> handleControlPropertyChanged("PREF_SIZE") );
        getSkinnable().prefHeightProperty().addListener(observable -> handleControlPropertyChanged("PREF_SIZE") );
        getSkinnable().characterProperty().addListener(observable -> handleControlPropertyChanged("CHARACTER") );
        getSkinnable().dotOnProperty().addListener(observable -> handleControlPropertyChanged("DOT_ON") );
        getSkinnable().segmentStyleProperty().addListener(observable -> handleControlPropertyChanged("UPDATE") );

        getSkinnable().getStyleClass().addListener(new ListChangeListener<String>() {
            @Override public void onChanged(Change<? extends String> change) {
                resize();
                update();
            }
        });
    }


    // ******************** Methods *******************************************
    protected void handleControlPropertyChanged(final String PROPERTY) {
        if ("UPDATE".equals(PROPERTY)) {
            update();
        } else if ("RESIZE".equals(PROPERTY)) {
            resize();
            update();
        } else if ("PREF_SIZE".equals(PROPERTY)) {
            aspectRatio = getSkinnable().getPrefHeight() / getSkinnable().getPrefWidth();
        } else if ("CHARACTER".equals(PROPERTY)) {
            update();
        } else if ("DOT_ON".equals(PROPERTY)) {
            update();
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


    // ******************** Update ********************************************
    private void update() {
        final int    ASCII     = getSkinnable().getCharacter().isEmpty() ? 20 : getSkinnable().getCharacter().toUpperCase().charAt(0);
        final String ON_STYLE  = getSkinnable().getSegmentStyle().ON_CLASS;
        final String OFF_STYLE = getSkinnable().getSegmentStyle().OFF_CLASS;

        for (SevenSegment.Segment segment : segmentMap.keySet()) {
            if (getSkinnable().getSegmentMapping().containsKey(ASCII)) {
                if (getSkinnable().getSegmentMapping().get(ASCII).contains(segment)) {
                    segmentMap.get(segment).getStyleClass().setAll(segment.name().toLowerCase(), ON_STYLE);
                } else {
                    segmentMap.get(segment).getStyleClass().setAll(segment.name().toLowerCase(), OFF_STYLE);
                }
            } else {
                segmentMap.get(segment).getStyleClass().setAll(segment.name().toLowerCase(), OFF_STYLE);
            }
        }

        if (getSkinnable().isDotOn()) {
            segmentMap.get(SevenSegment.Segment.DOT).getStyleClass().setAll("dot", ON_STYLE);
        } else {
            segmentMap.get(SevenSegment.Segment.DOT).getStyleClass().setAll("dot", OFF_STYLE);
        }
    }


    // ******************** Resizing ******************************************
    private void resize() {
        size   = getSkinnable().getWidth() < getSkinnable().getHeight() ? getSkinnable().getWidth() : getSkinnable().getHeight();
        width  = getSkinnable().getWidth();
        height = getSkinnable().getHeight();
        if (getSkinnable().isKeepAspect()) {
            if (aspectRatio * width > height) {
                width  = 1 / (aspectRatio / height);
            } else if (1 / (aspectRatio / height) > width) {
                height = aspectRatio * width;
            }
        }

        if (width > 0 && height > 0) {
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
}

