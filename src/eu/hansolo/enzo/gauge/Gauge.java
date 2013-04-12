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

package eu.hansolo.enzo.gauge;

import eu.hansolo.enzo.gauge.skin.GaugeSkin;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Control;

import java.text.DecimalFormat;
import java.util.Locale;


/**
 * Created by
 * User: hansolo
 * Date: 01.04.13
 * Time: 17:10
 */
public class Gauge extends Control {
    public static enum NeedleType {
        STANDARD
    }
    public static enum TickLabelOrientation {
        NORMAL,
        HORIZONTAL,
        TANGENT
    }
    public static enum NumberFormat {
        AUTO("0"),
        STANDARD("0"),
        FRACTIONAL("0.0#"),
        SCIENTIFIC("0.##E0"),
        PERCENTAGE("##0.0%");

        private final DecimalFormat DF;

        private NumberFormat(final String FORMAT_STRING) {
            Locale.setDefault(new Locale("en", "US"));

            DF = new DecimalFormat(FORMAT_STRING);
        }

        public String format(final Number NUMBER) {
            return DF.format(NUMBER);
        }
    }
    public static final String STYLE_CLASS_NEEDLE_STANDARD   = "needle-standard";
    private ObjectProperty<GaugeModel> gaugeModel;


    // ******************** Constructors **************************************
    public Gauge() {
        this(new GaugeModel());
    }
    public Gauge(final GaugeModel GAUGE_MODEL) {
        getStyleClass().setAll("gauge");
        gaugeModel = new SimpleObjectProperty<>(GAUGE_MODEL);
    }


    // ******************** Methods *******************************************
    public GaugeModel getGaugeModel() {
        return gaugeModel.get();
    }
    public void setGaugeModel(final GaugeModel GAUGE_MODEL) {
        gaugeModel.set(GAUGE_MODEL);
    }
    public ObjectProperty<GaugeModel> gaugeModelProperty() {
        return gaugeModel;
    }


    // ******************** Style related *************************************
    @Override public GaugeSkin createDefaultSkin() {
        return new GaugeSkin(this);
    }

    @Override protected String getUserAgentStylesheet() {
        return getClass().getResource("gauge.css").toExternalForm();
    }
}
