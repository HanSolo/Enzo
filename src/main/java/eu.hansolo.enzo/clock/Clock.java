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

package eu.hansolo.enzo.clock;

import eu.hansolo.enzo.clock.skin.ClockSkin;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;


/**
 * User: hansolo
 * Date: 31.10.12
 * Time: 14:17
 */
public class Clock extends Control {
    public enum Design {
        IOS6,
        DB,
        BRAUN,
        BOSCH
    }
    private boolean                _discreteSecond;
    private BooleanProperty        discreteSecond;
    private boolean                _secondPointerVisible;
    private BooleanProperty        secondPointerVisible;
    private boolean                _nightMode;
    private BooleanProperty        nightMode;
    private Design                 _design;
    private ObjectProperty<Design> design;
    private boolean                _highlightVisible;
    private BooleanProperty        highlightVisible;


    // ******************** Constructors **************************************
    public Clock() {
        getStyleClass().add("clock");
        _discreteSecond       = false;
        _secondPointerVisible = true;
        _nightMode            = false;
        _design               = Design.IOS6;
        _highlightVisible     = true;
    }


    // ******************** Methods *******************************************
    public final boolean isDiscreteSecond() {
        return null == discreteSecond ? _discreteSecond : discreteSecond.get();
    }
    public final void setDiscreteSecond(final boolean DISCRETE_SECOND) {
        if (null == discreteSecond) {
            _discreteSecond = DISCRETE_SECOND;
        } else {
            discreteSecond.set(DISCRETE_SECOND);
        }
    }
    public final BooleanProperty discreteSecondProperty() {
        if (null == discreteSecond) {
            discreteSecond = new SimpleBooleanProperty(this, "discreteSecond", _discreteSecond);
        }
        return discreteSecond;
    }

    public final boolean isSecondPointerVisible() {
        return null == secondPointerVisible ? _secondPointerVisible : secondPointerVisible.get();
    }
    public final void setSecondPointerVisible(final boolean SECOND_POINTER_VISIBLE) {
        if (null == secondPointerVisible) {
            _secondPointerVisible = SECOND_POINTER_VISIBLE;
        } else {
            secondPointerVisible.set(SECOND_POINTER_VISIBLE);
        }
    }
    public final BooleanProperty secondPointerVisibleProperty() {
        if (null == secondPointerVisible) {
            secondPointerVisible = new SimpleBooleanProperty(this, "secondPointerVisible", _secondPointerVisible);
        }
        return secondPointerVisible;
    }

    public final boolean isNightMode() {
        return null == nightMode ? _nightMode : nightMode.get();
    }
    public final void setNightMode(final boolean NIGHT_MODE) {
        if (null == nightMode) {
            _nightMode = NIGHT_MODE;
        } else {
            nightMode.set(NIGHT_MODE);
        }
    }
    public final BooleanProperty nightModeProperty() {
        if (null == nightMode) {
            nightMode = new SimpleBooleanProperty(this, "nightMode", _nightMode);
        }
        return nightMode;
    }

    public final Design getDesign() {
        return null == design ? _design : design.get();
    }
    public final void setDesign(final Design DESIGN) {
        if (null == design) {
            _design = DESIGN;
        } else {
            design.set(DESIGN);
        }
    }
    public final ObjectProperty<Design> designProperty() {
        if (null == design) {
            design = new SimpleObjectProperty<>(this, "design", _design);
        }
        return design;
    }

    public final boolean isHighlightVisible() {
        return null == highlightVisible ? _highlightVisible : highlightVisible.get();
    }
    public final void setHighlightVisible(final boolean HIGHLIGHT_VISIBLE) {
        if (null == highlightVisible) {
            _highlightVisible = HIGHLIGHT_VISIBLE;
        } else {
            highlightVisible.set(HIGHLIGHT_VISIBLE);
        }
    }
    public final BooleanProperty highlightVisibleProperty() {
        if (null == highlightVisible) {
            highlightVisible = new SimpleBooleanProperty(this, "highlightVisible", _highlightVisible);
        }
        return highlightVisible;
    }


    // ******************** Style related *************************************
    @Override protected Skin createDefaultSkin() {
        return new ClockSkin(this);
    }

    @Override protected String getUserAgentStylesheet() {
        return getClass().getResource("clock.css").toExternalForm();
    }
}

