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

package eu.hansolo.enzo.radialmenu;

import eu.hansolo.enzo.common.Util;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;


/**
 * Created by
 * User: hansolo
 * Date: 11.09.13
 * Time: 22:15
 */
public class Symbol extends Region {
    private static final double PREFERRED_WIDTH  = 28;
    private static final double PREFERRED_HEIGHT = 28;
    private static final double MINIMUM_WIDTH    = 5;
    private static final double MINIMUM_HEIGHT   = 5;
    private static final double MAXIMUM_WIDTH    = 1024;
    private static final double MAXIMUM_HEIGHT   = 1024;
    private SymbolType          symbolType;
    private Color               color;


    // ******************** Constructor ***************************************
    public Symbol(final SymbolType SYMBOL_TYPE, final double SIZE, final Color COLOR) {
        symbolType = SYMBOL_TYPE;
        color      = COLOR;
        setMinSize(SIZE * SYMBOL_TYPE.WIDTH_FACTOR, SIZE * SYMBOL_TYPE.HEIGHT_FACTOR);
        setPrefSize(SIZE * SYMBOL_TYPE.WIDTH_FACTOR, SIZE * SYMBOL_TYPE.HEIGHT_FACTOR);
        setMaxSize(SIZE * SYMBOL_TYPE.WIDTH_FACTOR, SIZE * SYMBOL_TYPE.HEIGHT_FACTOR);
        getStylesheets().add(getClass().getResource("symbols.css").toExternalForm());
        getStyleClass().setAll("symbol");
        init();
        initGraphics();
        registerListeners();
    }


    // ******************** Initialization ************************************
    private void init() {
        if (Double.compare(getPrefWidth(), 0.0) <= 0 || Double.compare(getPrefHeight(), 0.0) <= 0 ||
            Double.compare(getWidth(), 0.0) <= 0 || Double.compare(getHeight(), 0.0) <= 0) {
            if (getPrefWidth() > 0 && getPrefHeight() > 0) {
                setPrefSize(getPrefWidth(), getPrefHeight());
            } else {
                setPrefSize(PREFERRED_WIDTH, PREFERRED_HEIGHT);
            }
        }

        if (Double.compare(getMinWidth(), 0.0) <= 0 || Double.compare(getMinHeight(), 0.0) <= 0) {
            setMinSize(MINIMUM_WIDTH, MINIMUM_HEIGHT);
        }

        if (Double.compare(getMaxWidth(), 0.0) <= 0 || Double.compare(getMaxHeight(), 0.0) <= 0) {
            setMaxSize(MAXIMUM_WIDTH, MAXIMUM_HEIGHT);
        }
    }

    private void initGraphics() {
        setId(symbolType.STYLE_CLASS);
        setStyle("-symbol-color: " + Util.colorToCss(color) + ";");
    }

    private void registerListeners() {}


    // ******************** Methods *******************************************
    @Override protected double computePrefWidth(final double PREF_HEIGHT) {
        double prefHeight = PREFERRED_HEIGHT;
        if (PREF_HEIGHT != -1) {
            prefHeight = Math.max(0, PREF_HEIGHT - getInsets().getTop() - getInsets().getBottom());
        }
        return super.computePrefWidth(prefHeight);
    }
    @Override protected double computePrefHeight(final double PREF_WIDTH) {
        double prefWidth = PREFERRED_WIDTH;
        if (PREF_WIDTH != -1) {
            prefWidth = Math.max(0, PREF_WIDTH - getInsets().getLeft() - getInsets().getRight());
        }
        return super.computePrefWidth(prefWidth);
    }

    @Override protected double computeMinWidth(final double MIN_HEIGHT) {
        return super.computeMinWidth(Math.max(MINIMUM_HEIGHT, MIN_HEIGHT - getInsets().getTop() - getInsets().getBottom()));
    }
    @Override protected double computeMinHeight(final double MIN_WIDTH) {
        return super.computeMinHeight(Math.max(MINIMUM_WIDTH, MIN_WIDTH - getInsets().getLeft() - getInsets().getRight()));
    }

    @Override protected double computeMaxWidth(final double MAX_HEIGHT) {
        return super.computeMaxWidth(Math.min(MAXIMUM_HEIGHT, MAX_HEIGHT - getInsets().getTop() - getInsets().getBottom()));
    }
    @Override protected double computeMaxHeight(final double MAX_WIDTH) {
        return super.computeMaxHeight(Math.min(MAXIMUM_WIDTH, MAX_WIDTH - getInsets().getLeft() - getInsets().getRight()));
    }

    public SymbolType getSymbolType() {
        return symbolType;
    }
    public void setSymbolType(final SymbolType SYMBOL_TYPE) {
        symbolType = SYMBOL_TYPE;
        setId(symbolType.STYLE_CLASS);
    }

    public Color getColor() {
        return color;
    }
    public void setColor(final Color COLOR) {
        color = COLOR;
        setStyle("-symbol-color: " + Util.colorToCss(color) + ";");
    }
}
