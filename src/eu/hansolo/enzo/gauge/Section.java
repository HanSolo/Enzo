package eu.hansolo.enzo.gauge;

import eu.hansolo.enzo.tools.Util;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;


/**
 *
 * @author hansolo
 */
public class Section {
    private double                _start;
    private DoubleProperty        start;
    private double                _stop;
    private DoubleProperty        stop;
    private Color                 _color;
    private ObjectProperty<Color> color;
    private String                _cssColor;
    private StringProperty        cssColor;
    private String                _text;
    private StringProperty        text;


    // ******************** Constructors **************************************
    public Section() {
        this(-1, -1, Color.RED, "");
    }
    public Section(final double START, final double STOP, final Color COLOR) {
        this(START, STOP, COLOR, "");
    }
    public Section(final double START, final double STOP, final Color COLOR, final String TEXT) {
        this(START, STOP, COLOR, COLOR.brighter().brighter(), null, null, TEXT);
    }
    public Section(final double START, final double STOP, final Color COLOR, final Color HIGHLIGHT_COLOR, final Shape SECTION_AREA, final Shape FILLED_AREA, final String TEXT) {
        _start                     = START;
        _stop                      = STOP;
        _color                     = COLOR;
        _text                      = TEXT;
        _cssColor                  = Util.colorToCss(COLOR);
    }


    // ******************** Methods *******************************************
    public final double getStart() {
        return null == start ? _start : start.get();
    }
    public final void setStart(final double START) {
        if (null == start) {
            _start = START;
        } else {
            start.set(START);
        }
    }
    public final DoubleProperty startProperty() {
        if (null == start) {
            start = new SimpleDoubleProperty(this, "start", _start);
        }
        return start;
    }

    public final double getStop() {
        return null == stop ? _stop : stop.get();
    }
    public final void setStop(final double STOP) {
        if (null == stop) {
            _stop = STOP;
        } else {
            stop.set(STOP);
        }
    }
    public final DoubleProperty stopProperty() {
        if (null == stop) {
            stop = new SimpleDoubleProperty(this, "stop", _stop);
        }
        return stop;
    }

    public final Color getColor() {
        return null == color ? _color : color.get();
    }
    public final void setColor(final Color COLOR) {
        if (null == color) {
            _color = COLOR;
        } else {
            color.set(COLOR);
        }
        setCssColor(COLOR);
    }
    public final ObjectProperty<Color> colorProperty() {
        if (null == color) {
            color = new SimpleObjectProperty<>(this, "color", _color);
        }
        return color;
    }

    public final String getCssColor() {
        return null == cssColor ? _cssColor : cssColor.get();
    }
    private void setCssColor(final Color COLOR) {
        if (null == cssColor) {
            _cssColor = Util.colorToCss(COLOR);
        } else {
            cssColor.set(Util.colorToCss(COLOR));
        }
    }
    public final StringProperty cssColorProperty() {
        if (null == cssColor) {
            cssColor = new SimpleStringProperty(this, "cssColor", _cssColor);
        }
        return cssColor;
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

    public boolean contains(final double VALUE) {
        return ((Double.compare(VALUE, start.get()) >= 0 && Double.compare(VALUE, stop.get()) <= 0));
    }

    public boolean equals(final Section SECTION) {
        return (Double.compare(SECTION.getStart(), getStart()) == 0 &&
                Double.compare(SECTION.getStop(), getStop()) == 0 &&
                SECTION.getColor().equals(getColor()) &&
                SECTION.getText().equals(getText()));
    }

    @Override public String toString() {
        final StringBuilder NAME = new StringBuilder();
        NAME.append("Section: ").append("\n");
        NAME.append("text      : ").append(text.get()).append("\n");
        NAME.append("startValue: ").append(start.get()).append("\n");
        NAME.append("stopValue : ").append(stop.get()).append("\n");
        NAME.append("color     : ").append(color.toString()).append("\n\n");
        return NAME.toString();
    }
}
