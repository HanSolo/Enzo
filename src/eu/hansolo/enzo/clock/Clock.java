/*
 * Copyright (c) 2013. Gerrit Grunwald
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package eu.hansolo.enzo.clock;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Control;


/**
 * User: hansolo
 * Date: 31.10.12
 * Time: 14:17
 */
public class Clock extends Control {
    public enum Design {
        IOS6,
        DB,
        BRAUN
    }
    private boolean                defaultDiscreteSecond;
    private BooleanProperty        discreteSecond;
    private boolean                defaultSecondPointerVisible;
    private BooleanProperty        secondPointerVisible;
    private boolean                defaultNightMode;
    private BooleanProperty        nightMode;
    private Design                 defaultDesign;
    private ObjectProperty<Design> design;
    private boolean                defaultHighlightVisible;
    private BooleanProperty        highlightVisible;


    // ******************** Constructors **************************************
    public Clock() {
        getStyleClass().add("clock");
        defaultDiscreteSecond       = false;
        defaultSecondPointerVisible = true;
        defaultNightMode            = false;
        defaultDesign               = Design.IOS6;
        defaultHighlightVisible     = true;
    }


    // ******************** Methods *******************************************
    public final boolean isDiscreteSecond() {
        return null == discreteSecond ? defaultDiscreteSecond : discreteSecond.get();
    }
    public final void setDiscreteSecond(final boolean DISCRETE_SECOND) {
        if (null == discreteSecond) {
            defaultDiscreteSecond = DISCRETE_SECOND;
        } else {
            discreteSecond.set(DISCRETE_SECOND);
        }
    }
    public final BooleanProperty discreteSecondProperty() {
        if (null == discreteSecond) {
            discreteSecond = new SimpleBooleanProperty(this, "discreteSecond", defaultDiscreteSecond);
        }
        return discreteSecond;
    }

    public final boolean isSecondPointerVisible() {
        return null == secondPointerVisible ? defaultSecondPointerVisible : secondPointerVisible.get();
    }
    public final void setSecondPointerVisible(final boolean SECOND_POINTER_VISIBLE) {
        if (null == secondPointerVisible) {
            defaultSecondPointerVisible = SECOND_POINTER_VISIBLE;
        } else {
            secondPointerVisible.set(SECOND_POINTER_VISIBLE);
        }
    }
    public final BooleanProperty secondPointerVisibleProperty() {
        if (null == secondPointerVisible) {
            secondPointerVisible = new SimpleBooleanProperty(this, "secondPointerVisible", defaultSecondPointerVisible);
        }
        return secondPointerVisible;
    }

    public final boolean isNightMode() {
        return null == nightMode ? defaultNightMode : nightMode.get();
    }
    public final void setNightMode(final boolean NIGHT_MODE) {
        if (null == nightMode) {
            defaultNightMode = NIGHT_MODE;
        } else {
            nightMode.set(NIGHT_MODE);
        }
    }
    public final BooleanProperty nightModeProperty() {
        if (null == nightMode) {
            nightMode = new SimpleBooleanProperty(this, "nightMode", defaultNightMode);
        }
        return nightMode;
    }

    public final Design getDesign() {
        return null == design ? defaultDesign : design.get();
    }
    public final void setDesign(final Design DESIGN) {
        if (null == design) {
            defaultDesign = DESIGN;
        } else {
            design.set(DESIGN);
        }
    }
    public final ObjectProperty<Design> designProperty() {
        if (null == design) {
            design = new SimpleObjectProperty<>(this, "design", defaultDesign);
        }
        return design;
    }

    public final boolean isHighlightVisible() {
        return null == highlightVisible ? defaultHighlightVisible : highlightVisible.get();
    }
    public final void setHighlightVisible(final boolean HIGHLIGHT_VISIBLE) {
        if (null == highlightVisible) {
            defaultHighlightVisible = HIGHLIGHT_VISIBLE;
        } else {
            highlightVisible.set(HIGHLIGHT_VISIBLE);
        }
    }
    public final BooleanProperty highlightVisibleProperty() {
        if (null == highlightVisible) {
            highlightVisible = new SimpleBooleanProperty(this, "highlightVisible", defaultHighlightVisible);
        }
        return highlightVisible;
    }


    // ******************** Style related *************************************
    @Override protected String getUserAgentStylesheet() {
        return getClass().getResource("clock.css").toExternalForm();
    }
}

