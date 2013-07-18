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

package eu.hansolo.enzo.sixteensegment.skin;

import eu.hansolo.enzo.sixteensegment.SixteenSegment;
import javafx.collections.ListChangeListener;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

import java.util.HashMap;
import java.util.Map;


public class SixteenSegmentSkin extends SkinBase<SixteenSegment> implements Skin<SixteenSegment> {
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
    private Region              segmentA1;
    private Region              segmentA2;
    private Region              segmentB;
    private Region              segmentC;
    private Region              segmentD2;
    private Region              segmentD1;
    private Region              segmentE;
    private Region              segmentF;
    private Region              segmentG;
    private Region              segmentH;
    private Region              segmentI;
    private Region              segmentL;
    private Region              segmentM;
    private Region              segmentN;
    private Region              segmentP;
    private Region              segmentK;
    private Region              segmentDot;
    private Map<SixteenSegment.Segment, Region> segmentMap;


    // ******************** Constructors **************************************
    public SixteenSegmentSkin(final SixteenSegment CONTROL) {
        super(CONTROL);
        aspectRatio = PREFERRED_HEIGHT / PREFERRED_WIDTH;
        pane        = new Pane();
        segmentMap  = new HashMap<>(17);
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

        segmentA1 = new Region();
        segmentA1.getStyleClass().setAll("a1");
        segmentMap.put(SixteenSegment.Segment.A1, segmentA1);

        segmentA2 = new Region();
        segmentA2.getStyleClass().setAll("a2");
        segmentMap.put(SixteenSegment.Segment.A2, segmentA2);

        segmentB = new Region();
        segmentB.getStyleClass().setAll("b");
        segmentMap.put(SixteenSegment.Segment.B, segmentB);

        segmentC = new Region();
        segmentC.getStyleClass().setAll("c");
        segmentMap.put(SixteenSegment.Segment.C, segmentC);

        segmentD2 = new Region();
        segmentD2.getStyleClass().setAll("d2");
        segmentMap.put(SixteenSegment.Segment.D2, segmentD2);

        segmentD1 = new Region();
        segmentD1.getStyleClass().setAll("d1");
        segmentMap.put(SixteenSegment.Segment.D1, segmentD1);

        segmentE = new Region();
        segmentE.getStyleClass().setAll("e");
        segmentMap.put(SixteenSegment.Segment.E, segmentE);

        segmentF = new Region();
        segmentF.getStyleClass().setAll("f");
        segmentMap.put(SixteenSegment.Segment.F, segmentF);

        segmentG = new Region();
        segmentG.getStyleClass().setAll("g");
        segmentMap.put(SixteenSegment.Segment.G, segmentG);

        segmentH = new Region();
        segmentH.getStyleClass().setAll("h");
        segmentMap.put(SixteenSegment.Segment.H, segmentH);

        segmentI = new Region();
        segmentI.getStyleClass().setAll("i");
        segmentMap.put(SixteenSegment.Segment.I, segmentI);

        segmentL = new Region();
        segmentL.getStyleClass().setAll("l");
        segmentMap.put(SixteenSegment.Segment.L, segmentL);

        segmentM = new Region();
        segmentM.getStyleClass().setAll("m");
        segmentMap.put(SixteenSegment.Segment.M, segmentM);

        segmentN = new Region();
        segmentN.getStyleClass().setAll("n");
        segmentMap.put(SixteenSegment.Segment.N, segmentN);

        segmentP = new Region();
        segmentP.getStyleClass().setAll("p");
        segmentMap.put(SixteenSegment.Segment.P, segmentP);

        segmentK = new Region();
        segmentK.getStyleClass().setAll("k");
        segmentMap.put(SixteenSegment.Segment.K, segmentK);

        segmentDot = new Region();
        segmentDot.getStyleClass().setAll("dot");
        segmentMap.put(SixteenSegment.Segment.DOT, segmentDot);

        pane.getChildren().setAll(segmentA1,
                                  segmentA2,
                                  segmentB,
                                  segmentC,
                                  segmentD2,
                                  segmentD1,
                                  segmentE,
                                  segmentF,
                                  segmentG,
                                  segmentH,
                                  segmentI,
                                  segmentL,
                                  segmentM,
                                  segmentN,
                                  segmentP,
                                  segmentK,
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

        getSkinnable().getStyleClass().addListener((ListChangeListener<String>) change -> {
            resize();
            update();
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

        for (SixteenSegment.Segment segment : segmentMap.keySet()) {
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
            segmentMap.get(SixteenSegment.Segment.DOT).getStyleClass().setAll("dot", ON_STYLE);
        } else {
            segmentMap.get(SixteenSegment.Segment.DOT).getStyleClass().setAll("dot", OFF_STYLE);
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
            segmentA1.setPrefSize(0.373134328358209 * width, 0.07282913165266107 * height);
            segmentA1.setTranslateX(0.11567164179104478 * width);

            segmentA2.setPrefSize(0.376865671641791 * width, 0.07282913165266107 * height);
            segmentA2.setTranslateX(0.4962686567164179 * width);

            segmentB.setPrefSize(0.14185691947367654 * width, 0.4743288184414391 * height);
            segmentB.setTranslateX(0.7648594984367713 * width);
            segmentB.setTranslateY(0.02474666777111235 * height);

            segmentC.setPrefSize(0.14191687285010493 * width, 0.480032570889684 * height);
            segmentC.setTranslateX(0.7200879338961929 * width);
            segmentC.setTranslateY(0.5008913782798275 * height);

            segmentD2.setPrefSize(0.3656716417910448 * width, 0.07282913165266107 * height);
            segmentD2.setTranslateX(0.4216417910447761 * width);
            segmentD2.setTranslateY(0.927170868347339 * height);

            segmentD1.setPrefSize(0.3694029850746269 * width, 0.07282913165266107 * height);
            segmentD1.setTranslateX(0.03731343283582089 * width);
            segmentD1.setTranslateY(0.927170868347339 * height);

            segmentE.setPrefSize(0.1381160536808754 * width, 0.4744872192040879 * height);
            segmentE.setTranslateY(0.5008564583059787 * height);

            segmentF.setPrefSize(0.14191658817120453 * width, 0.4800337676574536 * height);
            segmentF.setTranslateX(0.04471146170772723 * width);
            segmentF.setTranslateY(0.019075994731999245 * height);

            segmentG.setPrefSize(0.26232508759000406 * width, 0.41364670868347336 * height);
            segmentG.setTranslateX(0.17906029544659516 * width);
            segmentG.setTranslateY(0.07340313139415923 * height);

            segmentH.setPrefSize(0.13067467532940766 * width, 0.4583017472125569 * height);
            segmentH.setTranslateX(0.41043147756092585 * width);
            segmentH.setTranslateY(0.038707604929178706 * height);

            segmentI.setPrefSize(0.33435482765311625 * width, 0.4141186198600534 * height);
            segmentI.setTranslateX(0.46448468450290054 * width);
            segmentI.setTranslateY(0.0736752731793401 * height);

            segmentK.setPrefSize(0.3523415238109987 * width, 0.07563025210084033 * height);
            segmentK.setTranslateX(0.45818246300540755 * width);
            segmentK.setTranslateY(0.46218487394957986 * height);

            segmentL.setPrefSize(0.25479484671977026 * width, 0.4144776074492297 * height);
            segmentL.setTranslateX(0.465354407011573 * width);
            segmentL.setTranslateY(0.5128619196702119 * height);

            segmentM.setPrefSize(0.13061295694379665 * width, 0.46119950665813203 * height);
            segmentM.setTranslateX(0.3656716417910448 * width);
            segmentM.setTranslateY(0.5000929204689688 * height);

            segmentN.setPrefSize(0.33414425067047576 * width, 0.4139740246684611 * height);
            segmentN.setTranslateX(0.10799379491094332 * width);
            segmentN.setTranslateY(0.5122721696100315 * height);

            segmentP.setPrefSize(0.34861020899530665 * width, 0.07563025210084033 * height);
            segmentP.setTranslateX(0.09992373879276105 * width);
            segmentP.setTranslateY(0.46218487394957986 * height);

            segmentDot.setPrefSize(0.13432835820895522 * width, 0.10084033613445378 * height);
            segmentDot.setTranslateX(0.8656716417910447 * width);
            segmentDot.setTranslateY(0.8991596638655462 * height);
        }
    }
}

