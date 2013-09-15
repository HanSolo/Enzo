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
    private double                _degrees;
    private DoubleProperty        degrees;
    private double                _offset;
    private DoubleProperty        offset;
    private double                _radius;
    private DoubleProperty        radius;
    private double                _buttonSize;
    private DoubleProperty        buttonSize;
    private Color                 _buttonInnerColor;
    private ObjectProperty<Color> buttonInnerColor;
    private Color                 _buttonFrameColor;
    private ObjectProperty<Color> buttonFrameColor;
    private Color                 _buttonForegroundColor;
    private ObjectProperty<Color> buttonForegroundColor;
    private double                _buttonAlpha;
    private DoubleProperty        buttonAlpha;
    private boolean               _buttonVisible;
    private BooleanProperty       buttonVisible;
    private boolean               _buttonHideOnSelect;
    private BooleanProperty       buttonHideOnSelect;
    private boolean               _hideOnClose;
    private BooleanProperty       hideOnClose;
    private boolean               _tooltipsEnabled;
    private BooleanProperty       tooltipsEnabled;


    // ******************** Constructors **************************************
    public Options() {
        this(360, -90, 100);
    }
    public Options(final double DEGREES, final double OFFSET, final double RADIUS) {
        this(DEGREES, OFFSET, RADIUS, 44, Color.hsb(0, 0.1, 0.1), Color.WHITE, Color.WHITE, true, false, false, 0.5, true);
    }
    public Options(final double DEGREES, final double OFFSET, final double RADIUS, final double BUTTON_SIZE, final Color BUTTON_INNER_COLOR, final Color BUTTON_FRAME_COLOR, final Color BUTTON_FOREGROUND_COLOR, final boolean BUTTON_HIDE_ON_SELECT, final boolean HIDE_ON_CLOSE, final boolean TOOLTIPS_ENABLED, final double BUTTON_ALPHA, final boolean BUTTON_VISIBLE) {
        _degrees               = DEGREES;
        _offset                = OFFSET;
        _radius                = RADIUS;
        _buttonSize            = BUTTON_SIZE;
        _buttonInnerColor      = BUTTON_INNER_COLOR;
        _buttonFrameColor      = BUTTON_FRAME_COLOR;
        _buttonForegroundColor = BUTTON_FOREGROUND_COLOR;
        _buttonAlpha           = BUTTON_ALPHA;
        _buttonHideOnSelect    = BUTTON_HIDE_ON_SELECT;
        _hideOnClose           = HIDE_ON_CLOSE;
        _tooltipsEnabled       = TOOLTIPS_ENABLED;
        _buttonVisible         = BUTTON_VISIBLE;
    }


    // ******************** Methods *******************************************
    public double getDegrees() {
        return null == degrees ? _degrees : degrees.get();
    }
    public void setDegrees(final double DEGREES) {
        if (null == degrees) {
            _degrees = DEGREES;
        } else {
            degrees.set(DEGREES);
        }
    }
    public DoubleProperty degreesProperty() {
        if (null == degrees) {
            degrees = new SimpleDoubleProperty(this, "degrees", _degrees);
        }
        return degrees;
    }

    public double getOffset() {
        return null == offset ? _offset : offset.get();
    }
    public void setOffset(final double OFFSET) {
        if (null == offset) {
            _offset = OFFSET;
        } else {
            offset.set(OFFSET);
        }
    }
    public DoubleProperty offsetProperty() {
        if (null == offset) {
            offset = new SimpleDoubleProperty(this, "offset", _offset);
        }
        return offset;
    }

    public double getRadius() {
        return null == radius ? _radius : radius.get();
    }
    public void setRadius(final double RADIUS) {
        if (null == radius) {
            _radius = RADIUS;
        } else {
            radius.set(RADIUS);
        }
    }
    public DoubleProperty radiusProperty() {
        if (null == radius) {
            radius = new SimpleDoubleProperty(this, "radius", _radius);
        }
        return radius;
    }

    public double getButtonSize() {
        return null == buttonSize ? _buttonSize : buttonSize.get();
    }
    public void setButtonSize(final double BUTTON_SIZE) {
        if (null == buttonSize) {
            _buttonSize = BUTTON_SIZE;
        } else {
            buttonSize.set(BUTTON_SIZE);
        }
    }
    public DoubleProperty buttonSizeProperty() {
        if (null == buttonSize) {
            buttonSize = new SimpleDoubleProperty(this, "buttonSize", _buttonSize);
        }
        return buttonSize;
    }

    public Color getButtonInnerColor() {
        return null == buttonInnerColor ? _buttonInnerColor : buttonInnerColor.get();
    }
    public void setButtonInnerColor(final Color BUTTON_INNER_COLOR) {
        if (null == buttonInnerColor) {
            _buttonInnerColor = BUTTON_INNER_COLOR;
        } else {
            buttonInnerColor.set(BUTTON_INNER_COLOR);
        }
    }
    public ObjectProperty<Color> buttonInnerColorProperty() {
        if (null == buttonInnerColor) {
            buttonInnerColor = new SimpleObjectProperty<>(this, "buttonInnerColor", _buttonInnerColor);
        }
        return buttonInnerColor;
    }

    public Color getButtonFrameColor() {
        return null == buttonFrameColor ? _buttonFrameColor : buttonFrameColor.get();
    }
    public void setButtonFrameColor(final Color BUTTON_FRAME_COLOR) {
        if (null == buttonFrameColor) {
            _buttonFrameColor = BUTTON_FRAME_COLOR;
        } else {
            buttonFrameColor.set(BUTTON_FRAME_COLOR);
        }
    }
    public ObjectProperty<Color> buttonFrameColorProperty() {
        if (null == buttonFrameColor) {
            buttonFrameColor = new SimpleObjectProperty<>(this, "buttonFrameColor", _buttonFrameColor);
        }
        return buttonForegroundColor;
    }

    public Color getButtonForegroundColor() {
        return null == buttonForegroundColor ? _buttonForegroundColor : buttonForegroundColor.get();
    }
    public void setButtonForegroundColor(final Color BUTTON_FOREGROUND_COLOR) {
        if (null == buttonForegroundColor) {
            _buttonForegroundColor = BUTTON_FOREGROUND_COLOR;
        } else {
            buttonForegroundColor.set(BUTTON_FOREGROUND_COLOR);
        }
    }
    public ObjectProperty<Color> buttonForegroundColorProperty() {
        if (null == buttonForegroundColor) {
            buttonForegroundColor = new SimpleObjectProperty<>(this, "buttonForegroundColor", _buttonForegroundColor);
        }
        return buttonForegroundColor;
    }

    public double getButtonAlpha() {
        return null == buttonAlpha ? _buttonAlpha : buttonAlpha.get();
    }
    public void setButtonAlpha(final double BUTTON_ALPHA) {
        double alpha = BUTTON_ALPHA < 0 ? 0 : (BUTTON_ALPHA > 1 ? 1.0 : BUTTON_ALPHA);
        if (null == buttonAlpha) {
            _buttonAlpha = alpha;
        } else {
            buttonAlpha.set(alpha);
        }
    }
    public DoubleProperty buttonAlphaProperty() {
        if (null == buttonAlpha) {
            buttonAlpha = new SimpleDoubleProperty(this, "buttonAlpha", _buttonAlpha);
        }
        return buttonAlpha;
    }

    public boolean isButtonHideOnSelect() {
        return null == buttonHideOnSelect ? _buttonHideOnSelect : buttonHideOnSelect.get();
    }
    public void setButtonHideOnSelect(final boolean BUTTON_HIDE_ON_SELECT) {
        if (null == buttonHideOnSelect) {
            _buttonHideOnSelect = BUTTON_HIDE_ON_SELECT;
        } else {
            buttonHideOnSelect.set(BUTTON_HIDE_ON_SELECT);
        }
    }
    public BooleanProperty buttonHideOnSelectProperty() {
        if (null == buttonHideOnSelect) {
            buttonHideOnSelect = new SimpleBooleanProperty(this, "buttonHideOnSelect", _buttonHideOnSelect);
        }
        return buttonHideOnSelect;
    }

    public boolean isHideOnClose() {
        return null == hideOnClose ? _hideOnClose : hideOnClose.get();
    }
    public void setHideOnClose(final boolean HIDE_ON_CLOSE) {
        if (null == hideOnClose) {
            _hideOnClose = HIDE_ON_CLOSE;
        } else {
            hideOnClose.set(HIDE_ON_CLOSE);
        }
    }
    public BooleanProperty hideOnCloseProperty() {
        if (null == hideOnClose) {
            hideOnClose = new SimpleBooleanProperty(this, "hideOnClose", _hideOnClose);
        }
        return hideOnClose;
    }

    public boolean isTooltipsEnabled() {
        return null == tooltipsEnabled ? _tooltipsEnabled : tooltipsEnabled.get();
    }
    public void setTooltipsEnabled(final boolean TOOLTIPS_ENABLED) {
        if (null == tooltipsEnabled) {
            _tooltipsEnabled = TOOLTIPS_ENABLED;
        } else {
            tooltipsEnabled.set(TOOLTIPS_ENABLED);
        }
    }
    public BooleanProperty tooltipsEnabledProperty() {
        if (null == tooltipsEnabled) {
            tooltipsEnabled = new SimpleBooleanProperty(this, "tooltipsEnabled", _tooltipsEnabled);
        }
        return tooltipsEnabled;
    }

    public boolean isButtonVisible() {
        return null == buttonVisible ? _buttonVisible : buttonVisible.get();
    }
    public void setButtonVisible(final boolean BUTTON_VISIBLE) {
        if (null == buttonVisible) {
            _buttonVisible = BUTTON_VISIBLE;
        } else {
            buttonVisible.set(BUTTON_VISIBLE);
        }
    }
    public BooleanProperty buttonVisibleProperty() {
        if (null == buttonVisible) {
            buttonVisible = new SimpleBooleanProperty(this, "buttonVisible", _buttonVisible);
        }
        return buttonVisible;
    }
}
