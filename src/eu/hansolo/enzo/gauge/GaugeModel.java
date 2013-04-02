package eu.hansolo.enzo.gauge;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.event.EventType;


/**
 * Created by
 * User: hansolo
 * Date: 01.04.13
 * Time: 16:29
 */
public class GaugeModel {

    private double           value;
    private double           minValue;
    private double           maxValue;
    private double           threshold;
    private double           minMeasuredValue;
    private double           maxMeasuredValue;
    private int              noOfDecimals;
    private String           title;
    private String           unit;
    private boolean          animated;
    private double           angleRange;
    private double           rotationOffset;
    private Gauge.NeedleType needleType;



    // ******************** Constructors **************************************
    public GaugeModel() {
        value            = 0;
        minValue         = 0;
        maxValue         = 100;
        threshold        = 50;
        minMeasuredValue = 100;
        maxMeasuredValue = 0;
        noOfDecimals     = 2;
        title            = "";
        unit             = "";
        animated         = true;
        angleRange       = 300;
        rotationOffset   = 150;
        needleType       = Gauge.NeedleType.STANDARD;
    }


    // ******************** Public Methods ************************************
    public double getValue() {
        return value;
    }
    public void setValue(final double VALUE) {
        double oldValue = value;
        value = clamp(minValue, maxValue, VALUE);
        fireGaugeModelEvent(new GaugeModelEvent(this, null, GaugeModelEvent.VALUE_CHANGED));
        if (oldValue < threshold && value > threshold) {
            fireGaugeModelEvent(new GaugeModelEvent(this, null, GaugeModelEvent.THRESHOLD_EXCEEDED));
        }
        if (oldValue > threshold && value < threshold) {
            fireGaugeModelEvent(new GaugeModelEvent(this, null, GaugeModelEvent.THRESHOLD_UNDERRUN));
        }
        if (value < minMeasuredValue) {
            setMinMeasuredValue(value);
        }
        if (value > maxMeasuredValue) {
            setMaxMeasuredValue(value);
        }
    }

    public double getMinValue() {
        return minValue;
    }
    public void setMinValue(final double MIN_VALUE) {
        minValue = clamp(Double.MIN_VALUE, maxValue, MIN_VALUE);
        fireGaugeModelEvent(new GaugeModelEvent(this, null, GaugeModelEvent.MIN_VALUE_CHANGED));
        validate();
    }

    public double getMaxValue() {
        return maxValue;
    }
    public void setMaxValue(final double MAX_VALUE) {
        maxValue = clamp(minValue, Double.MAX_VALUE, MAX_VALUE);
        fireGaugeModelEvent(new GaugeModelEvent(this, null, GaugeModelEvent.MAX_VALUE_CHANGED));
        validate();
    }

    public double getThreshold() {
        return threshold;
    }
    public void setThreshold(final double THRESHOLD) {
        threshold = clamp(minValue, maxValue, THRESHOLD);
        fireGaugeModelEvent(new GaugeModelEvent(this, null, GaugeModelEvent.THRESHOLD_CHANGED));
    }

    public double getMinMeasuredValue() {
        return minMeasuredValue;
    }
    private void setMinMeasuredValue(final double MIN_MEASURED_VALUE) {
        minMeasuredValue = MIN_MEASURED_VALUE;
        fireGaugeModelEvent(new GaugeModelEvent(this, null, GaugeModelEvent.MIN_MEASURED_VALUE_CHANGE));
    }

    public double getMaxMeasuredValue() {
        return maxMeasuredValue;
    }
    private void setMaxMeasuredValue(final double MAX_MEASURED_VALUE) {
        maxMeasuredValue = MAX_MEASURED_VALUE;
        fireGaugeModelEvent(new GaugeModelEvent(this, null, GaugeModelEvent.MAX_MEASURED_VALUE_CHANGED));
    }

    public void resetMinMeasuredValue() {
        setMinMeasuredValue(value);
    }

    public void resetMaxMeasuredValue() {
        setMaxMeasuredValue(value);
    }

    public int getNoOfDecimals() {
        return noOfDecimals;
    }
    public void setNoOfDecimals(final int NO_OF_DECIMALS) {
        noOfDecimals = clamp(0, 10, NO_OF_DECIMALS);
        fireGaugeModelEvent(new GaugeModelEvent(this, null, GaugeModelEvent.NO_OF_DECIMALS_CHANGED));
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(final String TITLE) {
        title = TITLE;
        fireGaugeModelEvent(new GaugeModelEvent(this, null, GaugeModelEvent.TITLE_CHANGED));
    }

    public String getUnit() {
        return unit;
    }
    public void setUnit(final String UNIT) {
        unit = UNIT;
        fireGaugeModelEvent(new GaugeModelEvent(this, null, GaugeModelEvent.UNIT_CHANGED));
    }

    public boolean isAnimated() {
        return animated;
    }
    public void setAnimated(final boolean ANIMATED) {
        animated = ANIMATED;
        fireGaugeModelEvent(new GaugeModelEvent(this, null, GaugeModelEvent.ANIMATED));
    }

    public double getAngleRange() {
        return angleRange;
    }
    public void setAngleRange(final double ANGLE_RANGE) {
        angleRange = clamp(0, 360, ANGLE_RANGE);
        fireGaugeModelEvent(new GaugeModelEvent(this, null, GaugeModelEvent.ANGLE_RANGE_CHANGED));
    }

    public double getRotationOffset() {
        return rotationOffset;
    }
    public void setRotationOffset(final double ROTATION_OFFSET) {
        rotationOffset = clamp(0, 360, ROTATION_OFFSET);
        fireGaugeModelEvent(new GaugeModelEvent(this, null, GaugeModelEvent.ROTATION_OFFSET_CHANGED));
    }

    // Properties related to visualization
    public Gauge.NeedleType getNeedleType() {
        return needleType;
    }
    public void setNeedleType(final Gauge.NeedleType NEEDLE_TYPE) {
        needleType = NEEDLE_TYPE;
        fireGaugeModelEvent(new GaugeModelEvent(this, null, GaugeModelEvent.NEEDLE_TYPE_CHANGE));
    }


    // ******************** Private Methods ***********************************
    private double clamp(final double MIN_VALUE, final double MAX_VALUE, final double VALUE) {
        if (VALUE < MIN_VALUE) return MIN_VALUE;
        if (VALUE > MAX_VALUE) return MAX_VALUE;
        return VALUE;
    }

    private int clamp(final int MIN_VALUE, final int MAX_VALUE, final int VALUE) {
        if (VALUE < MIN_VALUE) return MIN_VALUE;
        if (VALUE > MAX_VALUE) return MAX_VALUE;
        return VALUE;
    }

    private void validate() {
        if (threshold < minValue) setThreshold(minValue);
        if (threshold > maxValue) setThreshold(maxValue);
        if (value < minValue) setValue(minValue);
        if (value > maxValue) setValue(maxValue);
    }


    // ******************** Event Handling ************************************
    public final ObjectProperty<EventHandler<GaugeModelEvent>> onGaugeModelChangedProperty() { return onGaugeModelChanged; }
    public final void setOnGaugeModelChanged(EventHandler<GaugeModelEvent> value) { onGaugeModelChangedProperty().set(value); }
    public final EventHandler<GaugeModelEvent> getOnGaugeModelChanged() { return onGaugeModelChangedProperty().get(); }
    private ObjectProperty<EventHandler<GaugeModelEvent>> onGaugeModelChanged = new ObjectPropertyBase<EventHandler<GaugeModelEvent>>() {
        @Override public Object getBean() { return this; }
        @Override public String getName() { return "onGaugeModelChanged";}
    };
    public void fireGaugeModelEvent(final GaugeModelEvent EVENT) {
        final EventHandler<GaugeModelEvent> HANDLER = getOnGaugeModelChanged();
        if (HANDLER != null) {
            HANDLER.handle(EVENT);
        }
    }


    // ******************** Inner Classes *************************************
    public static class GaugeModelEvent extends Event {
        public static final EventType<GaugeModelEvent> VALUE_CHANGED              = new EventType(ANY, "VALUE_CHANGED");
        public static final EventType<GaugeModelEvent> MIN_VALUE_CHANGED          = new EventType(ANY, "MIN_VALUE_CHANGED");
        public static final EventType<GaugeModelEvent> MAX_VALUE_CHANGED          = new EventType(ANY, "MAX_VALUE_CHANGED");
        public static final EventType<GaugeModelEvent> THRESHOLD_CHANGED          = new EventType(ANY, "THRESHOLD_CHANGED");
        public static final EventType<GaugeModelEvent> THRESHOLD_EXCEEDED         = new EventType(ANY, "THRESHOLD_EXCEEDED");
        public static final EventType<GaugeModelEvent> THRESHOLD_UNDERRUN         = new EventType(ANY, "THRESHOLD_UNDERRUN");
        public static final EventType<GaugeModelEvent> MIN_MEASURED_VALUE_CHANGE  = new EventType(ANY, "MIN_MEASURED_VALUE_CHANGED");
        public static final EventType<GaugeModelEvent> MAX_MEASURED_VALUE_CHANGED = new EventType(ANY, "MAX_MEASURED_VALUE_CHANGED");
        public static final EventType<GaugeModelEvent> NO_OF_DECIMALS_CHANGED     = new EventType(ANY, "NO_OF_DECIMALS_CHANGED");
        public static final EventType<GaugeModelEvent> TITLE_CHANGED              = new EventType(ANY, "TITLE_CHANGED");
        public static final EventType<GaugeModelEvent> UNIT_CHANGED               = new EventType(ANY, "UNIT_CHANGED");
        public static final EventType<GaugeModelEvent> ANIMATED                   = new EventType(ANY, "ANIMATED");
        public static final EventType<GaugeModelEvent> ANGLE_RANGE_CHANGED        = new EventType(ANY, "ANGLE_RANGE_CHANGED");
        public static final EventType<GaugeModelEvent> ROTATION_OFFSET_CHANGED    = new EventType(ANY, "ROTATION_OFFSET_CHANGED");
        public static final EventType<GaugeModelEvent> NEEDLE_TYPE_CHANGE         = new EventType(ANY, "NEEDLE_TYPE_CHANGED");


        // ******************* Constructors ***************************************
        public GaugeModelEvent(final Object SOURCE, final EventTarget TARGET, final EventType EVENT_TYPE) {
            super(SOURCE, TARGET, EVENT_TYPE);
        }
    }
}
