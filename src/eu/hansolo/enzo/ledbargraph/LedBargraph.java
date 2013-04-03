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
    private Led.Type                    defaultLedType = Led.Type.ROUND;
    private ObjectProperty<Led.Type>    ledType;
    private boolean                     defaultFrameVisible = false;
    private BooleanProperty             frameVisible;
    private double                      defaultLedSize = 16;
    private DoubleProperty              ledSize;
    private Orientation                 defaultOrientation = Orientation.HORIZONTAL;
    private ObjectProperty<Orientation> orientation;
    private int                         defaultNoOfLeds = 16;
    private IntegerProperty             noOfLeds;
    private ListProperty<Color>         ledColors;
    private boolean                     defaultPeakValueVisible = false;
    private BooleanProperty             peakValueVisible;
    private DoubleProperty              value;


    // ******************** Constructors **************************************
    public LedBargraph() {
        getStyleClass().add("bargraph");
        ledColors        = new SimpleListProperty(this, "ledColors", FXCollections.<Color>observableArrayList());
        value            = new SimpleDoubleProperty(this, "value", 0);

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
    public final Led.Type getLedType() {
        return null == ledType ? defaultLedType : ledType.get();
    }
    public final void setLedType(final Led.Type LED_TYPE) {
        if (null == ledType) {
            defaultLedType = LED_TYPE;
        } else {
            ledType.set(LED_TYPE);
        }
    }
    public final ObjectProperty<Led.Type> ledTypeProperty() {
        if (null == ledType) {
            ledType = new SimpleObjectProperty<>(this, "ledType", defaultLedType);
        }
        return ledType;
    }

    public final boolean isFrameVisible() {
        return null == frameVisible ? defaultFrameVisible : frameVisible.get();
    }
    public final void setFrameVisible(final boolean FRAME_VISIBLE) {
        if (null == frameVisible) {
            defaultFrameVisible = FRAME_VISIBLE;
        } else {
            frameVisible.set(FRAME_VISIBLE);
        }
    }
    public final BooleanProperty frameVisibleProperty() {
        if (null == frameVisible) {
            frameVisible = new SimpleBooleanProperty(this, "frameVisible", defaultFrameVisible);
        }
        return frameVisible;
    }

    public final double getLedSize() {
        return null == ledSize ? defaultLedSize : ledSize.get();
    }
    public final void setLedSize(final double LED_SIZE) {
        double size = LED_SIZE < 10 ? 10 : (LED_SIZE > 50 ? 50 : LED_SIZE);
        if (null == ledSize) {
            defaultLedSize = size;
        } else {
            ledSize.set(size);
        }
    }
    public final DoubleProperty ledSizeProperty() {
        if (null == ledSize) {
            ledSize = new SimpleDoubleProperty(this, "ledSize", defaultLedSize);
        }
        return ledSize;
    }

    public final Orientation getOrientation() {
        return null == orientation ? defaultOrientation : orientation.get();
    }
    public final void setOrientation(final Orientation ORIENTATION) {
        if (null == orientation) {
            defaultOrientation = ORIENTATION;
        } else {
            orientation.set(ORIENTATION);
        }
    }
    public final ObjectProperty<Orientation> orientationProperty() {
        if (null == orientation) {
            orientation = new SimpleObjectProperty<>(this, "orientation", defaultOrientation);
        }
        return orientation;
    }

    public final int getNoOfLeds() {
        return null == noOfLeds ? defaultNoOfLeds : noOfLeds.get();
    }
    public final void setNoOfLeds(final int NO_OF_LEDS) {
        int amount = NO_OF_LEDS < 5 ? 5 : NO_OF_LEDS;
        if (amount > noOfLeds.get()) {
            for (int i = 0 ; i < (amount - noOfLeds.get()) ; i++) {
                ledColors.get().add(Color.RED);
            }
        }
        if (null == noOfLeds) {
            defaultNoOfLeds = amount;
        } else {
            noOfLeds.set(amount);
        }
    }
    public final IntegerProperty noOfLedsProperty() {
        if (null == noOfLeds) {
            noOfLeds = new SimpleIntegerProperty(this, "noOfLeds", defaultNoOfLeds);
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
        return null == peakValueVisible ? defaultPeakValueVisible : peakValueVisible.get();
    }
    public final void setPeakValueVisible(final boolean PEAK_VALUE_VISIBLE) {
        if (null == peakValueVisible) {
            defaultPeakValueVisible = PEAK_VALUE_VISIBLE;
        } else {
            peakValueVisible.set(PEAK_VALUE_VISIBLE);
        }
    }
    public final BooleanProperty peakValueVisibleProperty() {
        if (null == peakValueVisible) {
            peakValueVisible = new SimpleBooleanProperty(this, "peakValueVisible", defaultPeakValueVisible);
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
