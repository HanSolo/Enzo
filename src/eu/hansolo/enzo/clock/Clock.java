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

