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

package eu.hansolo.enzo.simpleindicator.skin;

import eu.hansolo.enzo.simpleindicator.SimpleIndicator;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;


/**
 * Created by
 * User: hansolo
 * Date: 06.03.12
 * Time: 13:53
 */
public class SimpleIndicatorSkin extends SkinBase<SimpleIndicator> implements Skin<SimpleIndicator> {
    private static final double PREFERRED_SIZE = 48;
    private static final double MINIMUM_SIZE   = 16;
    private static final double MAXIMUM_SIZE   = 1024;
    private double              size;
    private Pane                pane;
    private Region              outerFrame;
    private Region              innerFrame;
    private Region              mainBack;
    private Region              main;
    private Region              highlight;


    // ******************** Constructors **************************************
    public SimpleIndicatorSkin(final SimpleIndicator CONTROL) {
        super(CONTROL);
        pane    = new Pane();

        init();
        initGraphics();
        registerListeners();
    }


    // ******************** Initialization ************************************
    private void init() {
        if (Double.compare(getSkinnable().getPrefWidth(), 0.0) <= 0 || Double.compare(getSkinnable().getPrefHeight(), 0.0) <= 0 ||
            getSkinnable().getWidth() <= 0 || getSkinnable().getHeight() <= 0) {
            getSkinnable().setPrefSize(PREFERRED_SIZE, PREFERRED_SIZE);
        }

        if (Double.compare(getSkinnable().getMinWidth(), 0.0) <= 0 || Double.compare(getSkinnable().getMinHeight(), 0.0) <= 0) {
            getSkinnable().setMinSize(MINIMUM_SIZE, MINIMUM_SIZE);
        }

        if (Double.compare(getSkinnable().getMaxWidth(), 0.0) <= 0 || Double.compare(getSkinnable().getMaxHeight(), 0.0) <= 0) {
            getSkinnable().setMaxSize(MAXIMUM_SIZE, MAXIMUM_SIZE);
        }
    }

    private void initGraphics() {
        outerFrame = new Region();
        outerFrame.getStyleClass().setAll("outer-frame");

        innerFrame = new Region();
        innerFrame.getStyleClass().setAll("inner-frame");

        mainBack = new Region();
        mainBack.getStyleClass().setAll("main-back");

        main = new Region();
        main.getStyleClass().setAll("main");

        highlight = new Region();
        highlight.getStyleClass().setAll("highlight");

        pane.getChildren().setAll(outerFrame, innerFrame, mainBack, main, highlight);

        getChildren().setAll(pane);
    }

    private void registerListeners() {
        getSkinnable().widthProperty().addListener(observable -> { handleControlPropertyChanged("RESIZE"); });
        getSkinnable().heightProperty().addListener(observable -> { handleControlPropertyChanged("RESIZE"); });
        getSkinnable().indicatorStyleProperty().addListener(observable -> { handleControlPropertyChanged("UPDATE"); });
    }


    // ******************** Methods *******************************************
    protected void handleControlPropertyChanged(final String PROPERTY) {
        if ("RESIZE".equals(PROPERTY)) {
            resize();
        } else if ("UPDATE".equals(PROPERTY)) {
            update();
        }
    }

    @Override protected double computePrefWidth(final double PREF_HEIGHT) {
        double prefHeight = PREFERRED_SIZE;
        if (PREF_HEIGHT != -1) {
            prefHeight = Math.max(0, PREF_HEIGHT - getSkinnable().getInsets().getTop() - getSkinnable().getInsets().getBottom());
        }
        return super.computePrefWidth(prefHeight);
    }
    @Override protected double computePrefHeight(final double PREF_WIDTH) {
        double prefWidth = PREFERRED_SIZE;
        if (PREF_WIDTH != -1) {
            prefWidth = Math.max(0, PREF_WIDTH - getSkinnable().getInsets().getLeft() - getSkinnable().getInsets().getRight());
        }
        return super.computePrefWidth(prefWidth);
    }

    @Override protected double computeMinWidth(final double MIN_HEIGHT) {
        return super.computeMinWidth(Math.max(5, MIN_HEIGHT - getSkinnable().getInsets().getTop() - getSkinnable().getInsets().getBottom()));
    }
    @Override protected double computeMinHeight(final double MIN_WIDTH) {
        return super.computeMinHeight(Math.max(5, MIN_WIDTH - getSkinnable().getInsets().getLeft() - getSkinnable().getInsets().getRight()));
    }

    @Override protected double computeMaxWidth(final double MAX_HEIGHT) {
        return super.computeMaxWidth(Math.min(1024, MAX_HEIGHT - getSkinnable().getInsets().getTop() - getSkinnable().getInsets().getBottom()));
    }
    @Override protected double computeMaxHeight(final double MAX_WIDTH) {
        return super.computeMaxHeight(Math.min(1024, MAX_WIDTH - getSkinnable().getInsets().getLeft() - getSkinnable().getInsets().getRight()));
    }


    // ******************** Private Methods ***********************************
    private void update() {
        getSkinnable().getStyleClass().setAll("indicator", getSkinnable().getIndicatorStyle().CLASS);
        System.out.println(getSkinnable().getIndicatorStyle());
    }

    private void resize() {
        size = getSkinnable().getWidth() < getSkinnable().getHeight() ? getSkinnable().getWidth() : getSkinnable().getHeight();

        if (size > 0) {
            outerFrame.setPrefSize(size, size);

            innerFrame.setPrefSize(size * 0.8, size * 0.8);
            innerFrame.setTranslateX((size - innerFrame.getPrefWidth()) * 0.5);
            innerFrame.setTranslateY((size - innerFrame.getPrefHeight()) * 0.5);

            mainBack.setPrefSize(size * 0.76, size * 0.76);
            mainBack.setTranslateX((size - mainBack.getPrefWidth()) * 0.5);
            mainBack.setTranslateY((size - mainBack.getPrefHeight()) * 0.5);

            main.setPrefSize(size * 0.76, size * 0.76);
            main.setTranslateX((size - main.getPrefWidth()) * 0.5);
            main.setTranslateY((size - main.getPrefHeight()) * 0.5);

            highlight.setPrefSize(size * 0.52, size * 0.30);
            highlight.setTranslateX((size - highlight.getPrefWidth()) * 0.5);
            highlight.setTranslateY((size - highlight.getPrefHeight()) * 0.2);
        }
    }
}
