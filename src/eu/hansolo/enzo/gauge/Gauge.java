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
