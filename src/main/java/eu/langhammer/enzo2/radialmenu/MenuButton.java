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

package eu.langhammer.enzo2.radialmenu;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.BooleanPropertyBase;
import javafx.css.PseudoClass;
import javafx.scene.layout.Region;
import javafx.util.Duration;


/**
 * Created with IntelliJ IDEA.
 * User: hansolo
 * Date: 28.09.13
 * Time: 02:54
 * To change this template use File | Settings | File Templates.
 */
public class MenuButton extends Region {
    private static final double      PREFERRED_SIZE    = 45;
    private static final double      MINIMUM_SIZE      = 20;
    private static final double      MAXIMUM_SIZE      = 1024;
    private static final PseudoClass OPEN_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");
    private RadialMenu               radialMenu;
    private BooleanProperty          open;
    private Region                   cross;
    private RotateTransition         crossRotate;


    // ******************** Constructors **************************************
    public MenuButton(final RadialMenu RADIAL_MENU) {
        radialMenu = RADIAL_MENU;
        getStyleClass().setAll("menu-button");

        init();
        initGraphics();
        registerListeners();
    }


    // ******************** Initialization ************************************
    private void init() {
        if (Double.compare(getWidth(), 0) <= 0 ||
                    Double.compare(getHeight(), 0) <= 0 ||
                    Double.compare(getPrefWidth(), 0) <= 0 ||
                    Double.compare(getPrefHeight(), 0) <= 0) {
            setPrefSize(PREFERRED_SIZE, PREFERRED_SIZE);
        }
        if (Double.compare(getMinWidth(), 0) <= 0 ||
                    Double.compare(getMinHeight(), 0) <= 0) {
            setMinSize(MINIMUM_SIZE, MINIMUM_SIZE);
        }
        if (Double.compare(getMaxWidth(), 0) <= 0 ||
                    Double.compare(getMaxHeight(), 0) <= 0) {
            setMaxSize(MAXIMUM_SIZE, MAXIMUM_SIZE);
        }
    }

    private void initGraphics() {
        setPickOnBounds(false);

        cross = new Region();
        cross.getStyleClass().add("cross");
        cross.setMouseTransparent(true);
        crossRotate = new RotateTransition(Duration.millis(200), cross);
        crossRotate.setInterpolator(Interpolator.EASE_BOTH);

        // Add all nodes
        getChildren().addAll(cross);
    }

    private void registerListeners() {
        widthProperty().addListener(observable -> resize());
        heightProperty().addListener(observable -> resize());
        setOnMouseClicked(actionEvent -> setOpen(!isOpen()));
    }


    // ******************** Methods *******************************************
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
        return super.computeMinWidth(Math.max(MINIMUM_SIZE, MIN_HEIGHT - getInsets().getTop() - getInsets().getBottom()));
    }
    @Override protected double computeMinHeight(final double MIN_WIDTH) {
        return super.computeMinHeight(Math.max(MINIMUM_SIZE, MIN_WIDTH - getInsets().getLeft() - getInsets().getRight()));
    }

    @Override protected double computeMaxWidth(final double MAX_HEIGHT) {
        return super.computeMaxWidth(Math.min(MAXIMUM_SIZE, MAX_HEIGHT - getInsets().getTop() - getInsets().getBottom()));
    }
    @Override protected double computeMaxHeight(final double MAX_WIDTH) {
        return super.computeMaxHeight(Math.min(MAXIMUM_SIZE, MAX_WIDTH - getInsets().getLeft() - getInsets().getRight()));
    }

    public final boolean isOpen() {
        return null == open ? false : open.get();
    }
    public final void setOpen(final boolean OPEN) {
        openProperty().set(OPEN);
        rotate();
    }
    public final BooleanProperty openProperty() {
        if (null == open) {
            open = new BooleanPropertyBase(false) {
                @Override protected void invalidated() { pseudoClassStateChanged(OPEN_PSEUDO_CLASS, get()); }
                @Override public Object getBean() { return this; }
                @Override public String getName() { return "open"; }
            };
        }
        return open;
    }

    private void rotate() {
        if (isOpen()) {
            crossRotate.setFromAngle(0);
            crossRotate.setToAngle(radialMenu.getOptions().isSimpleMode() ? -45 : -135);
        } else {
            crossRotate.setFromAngle(radialMenu.getOptions().isSimpleMode() ? -45 : -135);
            crossRotate.setToAngle(0);
        }
        crossRotate.play();
    }

    private void resize() {
        cross.setPrefSize(0.44444 * getPrefWidth(), 0.44444 * getPrefHeight());
        cross.relocate((getPrefWidth() - cross.getPrefWidth()) * 0.5, (getPrefHeight() - cross.getPrefHeight()) * 0.5);
    }
}
