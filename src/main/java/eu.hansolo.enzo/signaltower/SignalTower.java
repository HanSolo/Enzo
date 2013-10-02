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

package eu.hansolo.enzo.signaltower;

import eu.hansolo.enzo.signaltower.skin.SignalTowerSkin;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.BooleanPropertyBase;
import javafx.css.PseudoClass;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;


public class SignalTower extends Control {
    private static final PseudoClass GREEN_ON_PSEUDO_CLASS  = PseudoClass.getPseudoClass("green-on");
    private static final PseudoClass YELLOW_ON_PSEUDO_CLASS = PseudoClass.getPseudoClass("yellow-on");
    private static final PseudoClass RED_ON_PSEUDO_CLASS    = PseudoClass.getPseudoClass("red-on");
    private BooleanProperty          greenOn;
    private BooleanProperty          yellowOn;
    private BooleanProperty          redOn;
    private boolean                  keepAspect;


    // ******************** Constructors **************************************
    public SignalTower() {
        getStyleClass().add("signal-tower");
        keepAspect    = true;
    }


    // ******************** Methods *******************************************
    public final boolean isGreenOn() {
        return null == greenOn ? false : greenOn.get();
    }
    public final void setGreenOn(final boolean GREEN_ON) {
        greenOnProperty().set(GREEN_ON);
    }
    public final BooleanProperty greenOnProperty() {
        if (null == greenOn) {
            greenOn = new BooleanPropertyBase(false) {
                @Override protected void invalidated() { pseudoClassStateChanged(GREEN_ON_PSEUDO_CLASS, get()); }
                @Override public Object getBean() { return this; }
                @Override public String getName() { return "greenOn"; }
            };
        }
        return greenOn;
    }

    public final boolean isYellowOn() {
        return null == yellowOn ? false : yellowOn.get();
    }
    public final void setYellowOn(final boolean YELLOW_ON) {
        yellowOnProperty().set(YELLOW_ON);
    }
    public final BooleanProperty yellowOnProperty() {
        if (null == yellowOn) {
            yellowOn = new BooleanPropertyBase(false) {
                @Override protected void invalidated() { pseudoClassStateChanged(YELLOW_ON_PSEUDO_CLASS, get()); }
                @Override public Object getBean() { return this; }
                @Override public String getName() { return "yellowOn"; }
            };
        }
        return yellowOn;
    }

    public final boolean isRedOn() {
        return null == redOn ? false : redOn.get();
    }
    public final void setRedOn(final boolean RED_ON) {
        redOnProperty().set(RED_ON);
    }
    public final BooleanProperty redOnProperty() {
        if (null == redOn) {
            redOn = new BooleanPropertyBase(false) {
                @Override protected void invalidated() { pseudoClassStateChanged(RED_ON_PSEUDO_CLASS, get()); }
                @Override public Object getBean() { return this; }
                @Override public String getName() { return "redOn"; }
            };
        }
        return redOn;
    }

    public final void setColors(final boolean GREEN_ON, final boolean YELLOW_ON, final boolean RED_ON) {
        setGreenOn(GREEN_ON);
        setYellowOn(YELLOW_ON);
        setRedOn(RED_ON);
    }

    public final boolean isKeepAspect() {
        return keepAspect;
    }
    public final void setKeepAspect(final boolean KEEP_ASPECT) {
        keepAspect = KEEP_ASPECT;
    }


    // ******************** Style related *************************************
    @Override protected Skin createDefaultSkin() {
        return new SignalTowerSkin(this);
    }
    @Override protected String getUserAgentStylesheet() {
        return getClass().getResource("signaltower.css").toExternalForm();
    }
}
