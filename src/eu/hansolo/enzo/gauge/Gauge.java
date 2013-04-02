package eu.hansolo.enzo.gauge;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Control;


/**
 * Created by
 * User: hansolo
 * Date: 01.04.13
 * Time: 17:10
 */
public class Gauge extends Control {
    public static enum NeedleType {
        STANDARD,
        SCIENTIFIC,
        ARROW,
        BIG
    }
    private ObjectProperty<GaugeModel> gaugeModel;


    // ******************** Constructors **************************************
    public Gauge() {
        this(new GaugeModel());
    }
    public Gauge(final GaugeModel GAUGE_MODEL) {
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
    @Override protected String getUserAgentStylesheet() {
        return getClass().getResource(getClass().getSimpleName().toLowerCase() + ".css").toExternalForm();
    }
}
