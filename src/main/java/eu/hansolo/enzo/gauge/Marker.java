package eu.hansolo.enzo.gauge;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 * Created by
 * User: hansolo
 * Date: 03.07.13
 * Time: 08:12
 */
public class Marker {
    private double         _start;
    private DoubleProperty value;
    private String         _text;
    private StringProperty text;


    // ******************** Constructors **************************************
    public Marker() {
        this(0, "");
    }
    public Marker(final double VALUE) {
        this(VALUE, "");
    }
    public Marker(final double VALUE, final String TEXT) {
        _start = VALUE;
        _text  = TEXT;
    }


    // ******************** Methods *******************************************
    public final double getValue() {
        return null == value ? _start : value.get();
    }
    public final void setValue(final double START) {
        if (null == value) {
            _start = START;
        } else {
            value.set(START);
        }
    }
    public final DoubleProperty startProperty() {
        if (null == value) {
            value = new SimpleDoubleProperty(this, "value", _start);
        }
        return value;
    }

    public final String getText() {
        return null == text ? _text : text.get();
    }
    public final void setText(final String TEXT) {
        if (null == text) {
            _text = TEXT;
        } else {
            text.set(TEXT);
        }
    }
    public final StringProperty textProperty() {
        if (null == text) {
            text = new SimpleStringProperty(this, "text", _text);
        }
        return text;
    }

    public boolean equals(final Marker MARKER) {
        return (Double.compare(MARKER.getValue(), getValue()) == 0 &&
            MARKER.getText().equals(getText()));
    }

    @Override public String toString() {
        final StringBuilder NAME = new StringBuilder();
        NAME.append("Section: ").append("\n");
        NAME.append("text   : ").append(text.get()).append("\n");
        NAME.append("value  : ").append(value.get()).append("\n");
        return NAME.toString();
    }
}
