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

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;


/**
 * Created with IntelliJ IDEA.
 * User: hansolo
 * Date: 21.09.12
 * Time: 13:40
 * To change this template use File | Settings | File Templates.
 */
public class Options {
    private double                defaultDegrees;
    private DoubleProperty        degrees;
    private double                defaultOffset;
    private DoubleProperty        offset;
    private double                defaultRadius;
    private DoubleProperty        radius;
    private double                defaultButtonSize;
    private DoubleProperty        buttonSize;
    private Color                 defaultButtonInnerColor;
    private ObjectProperty<Color> buttonInnerColor;
    private Color                 defaultButtonFrameColor;
    private ObjectProperty<Color> buttonFrameColor;
    private Color                 defaultButtonForegroundColor;
    private ObjectProperty<Color> buttonForegroundColor;
    private double                defaultButtonAlpha;
    private DoubleProperty        buttonAlpha;
    private boolean               defaultButtonVisible;
    private BooleanProperty       buttonVisible;
    private boolean               defaultButtonHideOnSelect;
    private BooleanProperty       buttonHideOnSelect;
    private boolean               defaultTooltipsEnabled;
    private BooleanProperty       tooltipsEnabled;


    // ******************** Constructors **************************************
    public Options() {
        this(360, -90, 100);
    }
    public Options(final double DEGREES, final double OFFSET, final double RADIUS) {
        this(DEGREES, OFFSET, RADIUS, 44, Color.hsb(0, 0.1, 0.1), Color.WHITE, Color.WHITE, true, false, 0.5, true);
    }
    public Options(final double DEGREES, final double OFFSET, final double RADIUS, final double BUTTON_SIZE, final Color BUTTON_INNER_COLOR, final Color BUTTON_FRAME_COLOR, final Color BUTTON_FOREGROUND_COLOR, final boolean BUTTON_HIDE_ON_SELECT, final boolean TOOLTIPS_ENABLED, final double BUTTON_ALPHA, final boolean BUTTON_VISIBLE) {
        defaultDegrees               = DEGREES;
        defaultOffset                = OFFSET;
        defaultRadius                = RADIUS;
        defaultButtonSize            = BUTTON_SIZE;
        defaultButtonInnerColor      = BUTTON_INNER_COLOR;
        defaultButtonFrameColor      = BUTTON_FRAME_COLOR;
        defaultButtonForegroundColor = BUTTON_FOREGROUND_COLOR;
        defaultButtonAlpha           = BUTTON_ALPHA;
        defaultButtonHideOnSelect    = BUTTON_HIDE_ON_SELECT;
        defaultTooltipsEnabled       = TOOLTIPS_ENABLED;
        defaultButtonVisible         = BUTTON_VISIBLE;
    }


    // ******************** Methods *******************************************
    public double getDegrees() {
        return null == degrees ? defaultDegrees : degrees.get();
    }
    public void setDegrees(final double DEGREES) {
        if (null == degrees) {
            defaultDegrees = DEGREES;
        } else {
            degrees.set(DEGREES);
        }
    }
    public DoubleProperty degreesProperty() {
        if (null == degrees) {
            degrees = new SimpleDoubleProperty(this, "degrees", defaultDegrees);
        }
        return degrees;
    }

    public double getOffset() {
        return null == offset ? defaultOffset : offset.get();
    }
    public void setOffset(final double OFFSET) {
        if (null == offset) {
            defaultOffset = OFFSET;
        } else {
            offset.set(OFFSET);
        }
    }
    public DoubleProperty offsetProperty() {
        if (null == offset) {
            offset = new SimpleDoubleProperty(this, "offset", defaultOffset);
        }
        return offset;
    }

    public double getRadius() {
        return null == radius ? defaultRadius : radius.get();
    }
    public void setRadius(final double RADIUS) {
        if (null == radius) {
            defaultRadius = RADIUS;
        } else {
            radius.set(RADIUS);
        }
    }
    public DoubleProperty radiusProperty() {
        if (null == radius) {
            radius = new SimpleDoubleProperty(this, "radius", defaultRadius);
        }
        return radius;
    }

    public double getButtonSize() {
        return null == buttonSize ? defaultButtonSize : buttonSize.get();
    }
    public void setButtonSize(final double BUTTON_SIZE) {
        if (null == buttonSize) {
            defaultButtonSize = BUTTON_SIZE;
        } else {
            buttonSize.set(BUTTON_SIZE);
        }
    }
    public DoubleProperty buttonSizeProperty() {
        if (null == buttonSize) {
            buttonSize = new SimpleDoubleProperty(this, "buttonSize", defaultButtonSize);
        }
        return buttonSize;
    }

    public Color getButtonInnerColor() {
        return null == buttonInnerColor ? defaultButtonInnerColor : buttonInnerColor.get();
    }
    public void setButtonInnerColor(final Color BUTTON_INNER_COLOR) {
        if (null == buttonInnerColor) {
            defaultButtonInnerColor = BUTTON_INNER_COLOR;
        } else {
            buttonInnerColor.set(BUTTON_INNER_COLOR);
        }
    }
    public ObjectProperty<Color> buttonInnerColorProperty() {
        if (null == buttonInnerColor) {
            buttonInnerColor = new SimpleObjectProperty<>(this, "buttonInnerColor", defaultButtonInnerColor);
        }
        return buttonInnerColor;
    }

    public Color getButtonFrameColor() {
        return null == buttonFrameColor ? defaultButtonFrameColor : buttonFrameColor.get();
    }
    public void setButtonFrameColor(final Color BUTTON_FRAME_COLOR) {
        if (null == buttonFrameColor) {
            defaultButtonFrameColor = BUTTON_FRAME_COLOR;
        } else {
            buttonFrameColor.set(BUTTON_FRAME_COLOR);
        }
    }
    public ObjectProperty<Color> buttonFrameColorProperty() {
        if (null == buttonFrameColor) {
            buttonFrameColor = new SimpleObjectProperty<>(this, "buttonFrameColor", defaultButtonFrameColor);
        }
        return buttonForegroundColor;
    }

    public Color getButtonForegroundColor() {
        return null == buttonForegroundColor ? defaultButtonForegroundColor : buttonForegroundColor.get();
    }
    public void setButtonForegroundColor(final Color BUTTON_FOREGROUND_COLOR) {
        if (null == buttonForegroundColor) {
            defaultButtonForegroundColor = BUTTON_FOREGROUND_COLOR;
        } else {
            buttonForegroundColor.set(BUTTON_FOREGROUND_COLOR);
        }
    }
    public ObjectProperty<Color> buttonForegroundColorProperty() {
        if (null == buttonForegroundColor) {
            buttonForegroundColor = new SimpleObjectProperty<>(this, "buttonForegroundColor", defaultButtonForegroundColor);
        }
        return buttonForegroundColor;
    }

    public double getButtonAlpha() {
        return null == buttonAlpha ? defaultButtonAlpha : buttonAlpha.get();
    }
    public void setButtonAlpha(final double BUTTON_ALPHA) {
        double alpha = BUTTON_ALPHA < 0 ? 0 : (BUTTON_ALPHA > 1 ? 1.0 : BUTTON_ALPHA);
        if (null == buttonAlpha) {
            defaultButtonAlpha = alpha;
        } else {
            buttonAlpha.set(alpha);
        }
    }
    public DoubleProperty buttonAlphaProperty() {
        if (null == buttonAlpha) {
            buttonAlpha = new SimpleDoubleProperty(this, "buttonAlpha", defaultButtonAlpha);
        }
        return buttonAlpha;
    }

    public boolean isButtonHideOnSelect() {
        return null == buttonHideOnSelect ? defaultButtonHideOnSelect : buttonHideOnSelect.get();
    }
    public void setButtonHideOnSelect(final boolean BUTTON_HIDE_ON_SELECT) {
        if (null == buttonHideOnSelect) {
            defaultButtonHideOnSelect = BUTTON_HIDE_ON_SELECT;
        } else {
            buttonHideOnSelect.set(BUTTON_HIDE_ON_SELECT);
        }
    }
    public BooleanProperty buttonHideOnSelectProperty() {
        if (null == buttonHideOnSelect) {
            buttonHideOnSelect = new SimpleBooleanProperty(this, "buttonHideOnSelect", defaultButtonHideOnSelect);
        }
        return buttonHideOnSelect;
    }

    public boolean isTooltipsEnabled() {
        return null == tooltipsEnabled ? defaultTooltipsEnabled : tooltipsEnabled.get();
    }
    public void setTooltipsEnabled(final boolean TOOLTIPS_ENABLED) {
        if (null == tooltipsEnabled) {
            defaultTooltipsEnabled = TOOLTIPS_ENABLED;
        } else {
            tooltipsEnabled.set(TOOLTIPS_ENABLED);
        }
    }
    public BooleanProperty tooltipsEnabledProperty() {
        if (null == tooltipsEnabled) {
            tooltipsEnabled = new SimpleBooleanProperty(this, "tooltipsEnabled", defaultTooltipsEnabled);
        }
        return tooltipsEnabled;
    }

    public boolean isButtonVisible() {
        return null == buttonVisible ? defaultButtonVisible : buttonVisible.get();
    }
    public void setButtonVisible(final boolean BUTTON_VISIBLE) {
        if (null == buttonVisible) {
            defaultButtonVisible = BUTTON_VISIBLE;
        } else {
            buttonVisible.set(BUTTON_VISIBLE);
        }
    }
    public BooleanProperty buttonVisibleProperty() {
        if (null == buttonVisible) {
            buttonVisible = new SimpleBooleanProperty(this, "buttonVisible", defaultButtonVisible);
        }
        return buttonVisible;
    }
}
