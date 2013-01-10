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
import javafx.scene.control.SkinBase;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;


/**
 * Created by
 * User: hansolo
 * Date: 06.03.12
 * Time: 13:53
 */
public class SimpleIndicatorSkin extends SkinBase<SimpleIndicator> {
    private static final double PREFERRED_SIZE = 48;
    private static final double MINIMUM_SIZE   = 16;
    private static final double MAXIMUM_SIZE   = 1024;
    private SimpleIndicator     control;
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
        registerChangeListener(control.widthProperty(), "RESIZE");
        registerChangeListener(control.heightProperty(), "RESIZE");
    }


    // ******************** Methods *******************************************
    @Override protected void handleControlPropertyChanged(final String PROPERTY) {
        super.handleControlPropertyChanged(PROPERTY);
        if ("RESIZE".equals(PROPERTY)) {
            resize();
        }
    }

    @Override public final void dispose() {
        control = null;
    }

    @Override protected double computePrefWidth(final double PREF_HEIGHT) {
        double prefHeight = PREFERRED_SIZE;
        if (PREF_HEIGHT != -1) {
            prefHeight = Math.max(0, PREF_HEIGHT - getInsets().getTop() - getInsets().getBottom());
        }
        return super.computePrefWidth(prefHeight);
    }

    @Override protected double computePrefHeight(final double PREF_WIDTH) {
        double prefWidth = PREFERRED_SIZE;
        if (PREF_WIDTH != -1) {
            prefWidth = Math.max(0, PREF_WIDTH - getInsets().getLeft() - getInsets().getRight());
        }
        return super.computePrefWidth(prefWidth);
    }

    @Override protected double computeMinWidth(final double MIN_HEIGHT) {
        return super.computeMinWidth(Math.max(5, MIN_HEIGHT - getInsets().getTop() - getInsets().getBottom()));
    }

    @Override protected double computeMinHeight(final double MIN_WIDTH) {
        return super.computeMinHeight(Math.max(5, MIN_WIDTH - getInsets().getLeft() - getInsets().getRight()));
    }

    @Override protected double computeMaxWidth(final double MAX_HEIGHT) {
        return super.computeMaxWidth(Math.min(1024, MAX_HEIGHT - getInsets().getTop() - getInsets().getBottom()));
    }

    @Override protected double computeMaxHeight(final double MAX_WIDTH) {
        return super.computeMaxHeight(Math.min(1024, MAX_WIDTH - getInsets().getLeft() - getInsets().getRight()));
    }


    // ******************** Private Methods ***********************************
    private void resize() {
        size = control.getWidth() < control.getHeight() ? control.getWidth() : control.getHeight();

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
