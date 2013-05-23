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

package eu.hansolo.enzo.ledbargraph;

import eu.hansolo.enzo.led.Led;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.scene.control.Control;
import javafx.scene.paint.Color;

import java.util.List;


/**
 * Created by
 * User: hansolo
 * Date: 16.02.12
 * Time: 11:29
 */
public class LedBargraph extends Control {
    private Led.LedType _ledType = Led.LedType.ROUND;
    private ObjectProperty<Led.LedType>    ledType;
    private boolean                     _frameVisible = false;
    private BooleanProperty             frameVisible;
    private double                      _ledSize = 16;
    private DoubleProperty              ledSize;
    private Orientation                 _orientation = Orientation.HORIZONTAL;
    private ObjectProperty<Orientation> orientation;
    private int                         _noOfLeds = 16;
    private IntegerProperty             noOfLeds;
    private ListProperty<Color>         ledColors;
    private boolean                     _peakValueVisible = false;
    private BooleanProperty             peakValueVisible;
    private DoubleProperty              value;


    // ******************** Constructors **************************************
    public LedBargraph() {
        getStyleClass().add("bargraph");
        ledColors = new SimpleListProperty(this, "ledColors", FXCollections.<Color>observableArrayList());
        value     = new SimpleDoubleProperty(this, "value", 0);

        for (int i = 0 ; i < getNoOfLeds() ; i++) {
            if (i < 11) {
                ledColors.get().add(Color.LIME);
            } else if (i > 10 && i < 13) {
                ledColors.get().add(Color.YELLOW);
            } else {
                ledColors.get().add(Color.RED);
            }
        }
    }


    // ******************** Methods *******************************************
    public final Led.LedType getLedType() {
        return null == ledType ? _ledType : ledType.get();
    }
    public final void setLedType(final Led.LedType LED_TYPE) {
        if (null == ledType) {
            _ledType = LED_TYPE;
        } else {
            ledType.set(LED_TYPE);
        }
    }
    public final ObjectProperty<Led.LedType> ledTypeProperty() {
        if (null == ledType) {
            ledType = new SimpleObjectProperty<>(this, "ledType", _ledType);
        }
        return ledType;
    }

    public final boolean isFrameVisible() {
        return null == frameVisible ? _frameVisible : frameVisible.get();
    }
    public final void setFrameVisible(final boolean FRAME_VISIBLE) {
        if (null == frameVisible) {
            _frameVisible = FRAME_VISIBLE;
        } else {
            frameVisible.set(FRAME_VISIBLE);
        }
    }
    public final BooleanProperty frameVisibleProperty() {
        if (null == frameVisible) {
            frameVisible = new SimpleBooleanProperty(this, "frameVisible", _frameVisible);
        }
        return frameVisible;
    }

    public final double getLedSize() {
        return null == ledSize ? _ledSize : ledSize.get();
    }
    public final void setLedSize(final double LED_SIZE) {
        double size = LED_SIZE < 10 ? 10 : (LED_SIZE > 50 ? 50 : LED_SIZE);
        if (null == ledSize) {
            _ledSize = size;
        } else {
            ledSize.set(size);
        }
    }
    public final DoubleProperty ledSizeProperty() {
        if (null == ledSize) {
            ledSize = new SimpleDoubleProperty(this, "ledSize", _ledSize);
        }
        return ledSize;
    }

    public final Orientation getOrientation() {
        return null == orientation ? _orientation : orientation.get();
    }
    public final void setOrientation(final Orientation ORIENTATION) {
        if (null == orientation) {
            _orientation = ORIENTATION;
        } else {
            orientation.set(ORIENTATION);
        }
    }
    public final ObjectProperty<Orientation> orientationProperty() {
        if (null == orientation) {
            orientation = new SimpleObjectProperty<>(this, "orientation", _orientation);
        }
        return orientation;
    }

    public final int getNoOfLeds() {
        return null == noOfLeds ? _noOfLeds : noOfLeds.get();
    }
    public final void setNoOfLeds(final int NO_OF_LEDS) {
        int amount = NO_OF_LEDS < 5 ? 5 : NO_OF_LEDS;
        if (amount > noOfLeds.get()) {
            for (int i = 0 ; i < (amount - noOfLeds.get()) ; i++) {
                ledColors.get().add(Color.RED);
            }
        }
        if (null == noOfLeds) {
            _noOfLeds = amount;
        } else {
            noOfLeds.set(amount);
        }
    }
    public final IntegerProperty noOfLedsProperty() {
        if (null == noOfLeds) {
            noOfLeds = new SimpleIntegerProperty(this, "noOfLeds", _noOfLeds);
        }
        return noOfLeds;
    }

    public final List<Color> getLedColors() {
        return ledColors.get();
    }
    public final void setLedColors(final List<Color> LED_COLORS) {
        ledColors.get().setAll(LED_COLORS);
    }
    public final ListProperty<Color> ledColorsProperty() {
        return ledColors;
    }

    public final Color getLedColor(final int INDEX) {
        Color ledColor;
        if (INDEX < 0) {
            ledColor = ledColors.get().get(0);
        } else if (INDEX > getNoOfLeds() - 1) {
            ledColor = ledColors.get().get(getNoOfLeds() - 1);
        } else {
            ledColor = ledColors.get().get(INDEX);
        }
        return ledColor;
    }
    public final void setLedColor(final int INDEX, final Color COLOR) {
        int realIndex = INDEX - 1;
        if (realIndex < 0) {
            ledColors.get().set(0, COLOR);
        } else if (realIndex > noOfLeds.get() - 1) {
            ledColors.get().set(noOfLeds.get() - 1, COLOR);
        } else {
            ledColors.get().set(realIndex, COLOR);
        }
    }

    public final boolean isPeakValueVisible() {
        return null == peakValueVisible ? _peakValueVisible : peakValueVisible.get();
    }
    public final void setPeakValueVisible(final boolean PEAK_VALUE_VISIBLE) {
        if (null == peakValueVisible) {
            _peakValueVisible = PEAK_VALUE_VISIBLE;
        } else {
            peakValueVisible.set(PEAK_VALUE_VISIBLE);
        }
    }
    public final BooleanProperty peakValueVisibleProperty() {
        if (null == peakValueVisible) {
            peakValueVisible = new SimpleBooleanProperty(this, "peakValueVisible", _peakValueVisible);
        }
        return peakValueVisible;
    }

    public final double getValue() {
        return value.get();
    }
    public final void setValue(final double VALUE) {
        double val = VALUE < 0 ? 0 : (VALUE > 1 ? 1 : VALUE);
        value.set(val);
    }
    public final DoubleProperty valueProperty() {
        return value;
    }


    // ******************** Stylesheet handling *******************************
    @Override public String getUserAgentStylesheet() {
        return getClass().getResource("ledbargraph.css").toExternalForm();
    }
}
