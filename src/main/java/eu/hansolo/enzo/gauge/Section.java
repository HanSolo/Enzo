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

package eu.hansolo.enzo.gauge;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
    private String                _text;
    private StringProperty        text;


    // ******************** Constructors **************************************
    public Section() {
        this(-1, -1, "");
    }
    public Section(final double START, final double STOP) {
        this(START, STOP, "");
    }
    public Section(final double START, final double STOP, final String TEXT) {
        this(START, STOP, null, null, TEXT);
    }
    public Section(final double START, final double STOP, final Shape SECTION_AREA, final Shape FILLED_AREA, final String TEXT) {
        _start                     = START;
        _stop                      = STOP;
        _text                      = TEXT;
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
                SECTION.getText().equals(getText()));
    }

    @Override public String toString() {
        final StringBuilder NAME = new StringBuilder();
        NAME.append("Section: ").append("\n");
        NAME.append("text      : ").append(text.get()).append("\n");
        NAME.append("startValue: ").append(start.get()).append("\n");
        NAME.append("stopValue : ").append(stop.get()).append("\n");
        return NAME.toString();
    }
}
