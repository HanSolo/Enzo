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

package eu.hansolo.enzo.radialmenu;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;
import javafx.util.Builder;

import java.util.HashMap;


/**
 * Created with IntelliJ IDEA.
 * User: hansolo
 * Date: 24.09.12
 * Time: 15:56
 * To change this template use File | Settings | File Templates.
 */
public class OptionsBuilder implements Builder<Options> {
    private HashMap<String, Property> properties = new HashMap<String, Property>();


    // ******************** Constructors **************************************
    protected OptionsBuilder() {}


    // ******************** Methods *******************************************
    public static final OptionsBuilder create() {
        return new OptionsBuilder();
    }

    public final OptionsBuilder degrees(final double DEGREES) {
        properties.put("DEGREES", new SimpleDoubleProperty(DEGREES));
        return this;
    }

    public final OptionsBuilder offset(final double OFFSET) {
        properties.put("OFFSET", new SimpleDoubleProperty(OFFSET));
        return this;
    }

    public final OptionsBuilder radius(final double RADIUS) {
        properties.put("RADIUS", new SimpleDoubleProperty(RADIUS));
        return this;
    }

    public final OptionsBuilder buttonSize(final double BUTTON_SIZE) {
        properties.put("BUTTON_SIZE", new SimpleDoubleProperty(BUTTON_SIZE));
        return this;
    }

    public final OptionsBuilder buttonInnerColor(final Color BUTTON_INNER_COLOR) {
        properties.put("BUTTON_INNER_COLOR", new SimpleObjectProperty<Color>(BUTTON_INNER_COLOR));
        return this;
    }

    public final OptionsBuilder buttonFrameColor(final Color BUTTON_FRAME_COLOR) {
        properties.put("BUTTON_FRAME_COLOR", new SimpleObjectProperty<Color>(BUTTON_FRAME_COLOR));
        return this;
    }

    public final OptionsBuilder buttonForegroundColor(final Color BUTTON_FOREGROUND_COLOR) {
        properties.put("BUTTON_FOREGROUND_COLOR", new SimpleObjectProperty<Color>(BUTTON_FOREGROUND_COLOR));
        return this;
    }

    public final OptionsBuilder buttonAlpha(final double BUTTON_ALPHA) {
        properties.put("BUTTON_ALPHA", new SimpleDoubleProperty(BUTTON_ALPHA));
        return this;
    }

    public final OptionsBuilder buttonHideOnSelect(final boolean BUTTON_HIDE_ON_SELECT) {
        properties.put("BUTTON_HIDE_ON_SELECT", new SimpleBooleanProperty(BUTTON_HIDE_ON_SELECT));
        return this;
    }

    public final OptionsBuilder tooltipsEnabled(final boolean TOOLTIPS_ENABLED) {
        properties.put("TOOLTIPS_ENABLED", new SimpleBooleanProperty(TOOLTIPS_ENABLED));
        return this;
    }

    public final OptionsBuilder buttonVisible(final boolean BUTTON_VISIBLE) {
        properties.put("BUTTON_VISIBLE", new SimpleBooleanProperty(BUTTON_VISIBLE));
        return this;
    }

    @Override public final Options build() {
        final Options CONTROL = new Options();

        for (String key : properties.keySet()) {
            if ("DEGREES".equals(key)) {
                CONTROL.setDegrees(((DoubleProperty) properties.get(key)).get());
            } else if("OFFSET".equals(key)) {
                CONTROL.setOffset(((DoubleProperty) properties.get(key)).get());
            } else if ("RADIUS".equals(key)) {
                CONTROL.setRadius(((DoubleProperty) properties.get(key)).get());
            } else if ("BUTTON_SIZE".equals(key)) {
                CONTROL.setButtonSize(((DoubleProperty) properties.get(key)).get());
            } else if ("BUTTON_INNER_COLOR".equals(key)) {
                CONTROL.setButtonInnerColor(((ObjectProperty<Color>) properties.get(key)).get());
            } else if ("BUTTON_FRAME_COLOR".equals(key)) {
                CONTROL.setButtonFrameColor(((ObjectProperty<Color>) properties.get(key)).get());
            } else if ("BUTTON_FOREGROUND_COLOR".equals(key)) {
                CONTROL.setButtonForegroundColor(((ObjectProperty<Color>) properties.get(key)).get());
            } else if ("BUTTON_ALPHA".equals(key)) {
                CONTROL.setButtonAlpha(((DoubleProperty) properties.get(key)).get());
            } else if ("BUTTON_HIDE_ON_SELECT".equals(key)) {
                CONTROL.setButtonHideOnSelect(((BooleanProperty) properties.get(key)).get());
            } else if ("TOOLTIPS_ENABLED".equals(key)) {
                CONTROL.setTooltipsEnabled(((BooleanProperty) properties.get(key)).get());
            } else if ("BUTTON_VISIBLE".equals(key)) {
                CONTROL.setButtonVisible(((BooleanProperty) properties.get(key)).get());
            }
        }

        return CONTROL;
    }
}

