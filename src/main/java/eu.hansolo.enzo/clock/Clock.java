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

package eu.hansolo.enzo.clock;

import eu.hansolo.enzo.clock.skin.ClockSkin;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


/**
 * User: hansolo
 * Date: 31.10.12
 * Time: 14:17
 */
public class Clock extends Control {
    public enum Design {
        IOS6,
        DB,
        BRAUN,
        BOSCH
    }
    private String                        _text;
    private StringProperty                text;
    private boolean                       _discreteSecond;
    private BooleanProperty               discreteSecond;
    private boolean                       _secondPointerVisible;
    private BooleanProperty               secondPointerVisible;
    private boolean                       _nightMode;
    private BooleanProperty               nightMode;
    private Design                        _design;
    private ObjectProperty<Design>        design;
    private boolean                       _highlightVisible;
    private BooleanProperty               highlightVisible;    
    private LocalDateTime                 _dateTime;
    private ObjectProperty<LocalDateTime> dateTime;
    private Duration                      _offset;
    private ObjectProperty<Duration>      offset;
    private boolean                       _running;
    private BooleanProperty               running;


    // ******************** Constructors **************************************
    public Clock() {
        this(LocalDateTime.now());
    }
    public Clock(final LocalDateTime DATE_TIME) {
        getStyleClass().add("clock");
        _text                 = "";
        _discreteSecond       = false;
        _secondPointerVisible = true;
        _nightMode            = false;
        _design               = Design.IOS6;
        _highlightVisible     = true;
        _dateTime             = DATE_TIME;
        _offset               = Duration.of(0, ChronoUnit.MINUTES);
        _running              = false;
    }


    // ******************** Methods *******************************************
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
    
    public final boolean isDiscreteSecond() {
        return null == discreteSecond ? _discreteSecond : discreteSecond.get();
    }
    public final void setDiscreteSecond(final boolean DISCRETE_SECOND) {
        if (null == discreteSecond) {
            _discreteSecond = DISCRETE_SECOND;
        } else {
            discreteSecond.set(DISCRETE_SECOND);
        }
    }
    public final BooleanProperty discreteSecondProperty() {
        if (null == discreteSecond) {
            discreteSecond = new SimpleBooleanProperty(this, "discreteSecond", _discreteSecond);
        }
        return discreteSecond;
    }

    public final boolean isSecondPointerVisible() {
        return null == secondPointerVisible ? _secondPointerVisible : secondPointerVisible.get();
    }
    public final void setSecondPointerVisible(final boolean SECOND_POINTER_VISIBLE) {
        if (null == secondPointerVisible) {
            _secondPointerVisible = SECOND_POINTER_VISIBLE;
        } else {
            secondPointerVisible.set(SECOND_POINTER_VISIBLE);
        }
    }
    public final BooleanProperty secondPointerVisibleProperty() {
        if (null == secondPointerVisible) {
            secondPointerVisible = new SimpleBooleanProperty(this, "secondPointerVisible", _secondPointerVisible);
        }
        return secondPointerVisible;
    }

    public final boolean isNightMode() {
        return null == nightMode ? _nightMode : nightMode.get();
    }
    public final void setNightMode(final boolean NIGHT_MODE) {
        if (null == nightMode) {
            _nightMode = NIGHT_MODE;
        } else {
            nightMode.set(NIGHT_MODE);
        }
    }
    public final BooleanProperty nightModeProperty() {
        if (null == nightMode) {
            nightMode = new SimpleBooleanProperty(this, "nightMode", _nightMode);
        }
        return nightMode;
    }

    public final Design getDesign() {
        return null == design ? _design : design.get();
    }
    public final void setDesign(final Design DESIGN) {
        if (null == design) {
            _design = DESIGN;
        } else {
            design.set(DESIGN);
        }
    }
    public final ObjectProperty<Design> designProperty() {
        if (null == design) {
            design = new SimpleObjectProperty<>(this, "design", _design);
        }
        return design;
    }

    public final boolean isHighlightVisible() {
        return null == highlightVisible ? _highlightVisible : highlightVisible.get();
    }
    public final void setHighlightVisible(final boolean HIGHLIGHT_VISIBLE) {
        if (null == highlightVisible) {
            _highlightVisible = HIGHLIGHT_VISIBLE;
        } else {
            highlightVisible.set(HIGHLIGHT_VISIBLE);
        }
    }
    public final BooleanProperty highlightVisibleProperty() {
        if (null == highlightVisible) {
            highlightVisible = new SimpleBooleanProperty(this, "highlightVisible", _highlightVisible);
        }
        return highlightVisible;
    }
    
    public final LocalDateTime getDateTime() {
        return null == dateTime ? _dateTime : dateTime.get();
    }
    public final void setDateTime(final LocalDateTime DATE_TIME) {
        if (null == dateTime) {
            _dateTime = DATE_TIME;
        } else {
            dateTime.set(DATE_TIME);
        }
    }
    public final ObjectProperty<LocalDateTime> dateTimeProperty() {
        if (null == dateTime) {
            dateTime = new SimpleObjectProperty<>(this, "dateTime", _dateTime);
        }
        return dateTime;
    }

    public final Duration getOffset() {
        return null == offset ? _offset : offset.get();
    }
    public final void setOffset(final Duration OFFSET) {
        if (null == offset) {
            _offset = OFFSET;
        } else {
            offset.set(OFFSET);
        }
    }
    public final ObjectProperty<Duration> offsetProperty() {
        if (null == offset) {
            offset = new ObjectPropertyBase<Duration>() {
                @Override protected void invalidated() {
                    set(clamp(Duration.of(-12, ChronoUnit.HOURS), Duration.of(12, ChronoUnit.HOURS), get()));
                }
                @Override public Object getBean() {
                    return this;
                }
                @Override public String getName() {
                    return "offset";
                }
            };
            offset = new SimpleObjectProperty<>(this, "offset", _offset);
        }
        return offset;
    }
    
    public final boolean isRunning() {
        return null == running ? _running : running.get();
    }
    public final void setRunning(final boolean RUNNING) {
        if (null == running) {
            _running = RUNNING;
        } else {
            running.set(RUNNING);
        }
    }
    public final BooleanProperty runningProperty() {
        if (null == running) {
            running = new SimpleBooleanProperty(this, "running", _running);
        }
        return running;
    }

    private Duration clamp(Duration min, Duration max, Duration value) {
        if (getDateTime().plus(value).isBefore(getDateTime().plus(min))) return min;
        if (getDateTime().plus(value).isAfter(getDateTime().plus(max))) return max;
        return value;
    }
    

    // ******************** Style related *************************************
    @Override protected Skin createDefaultSkin() {
        return new ClockSkin(this);
    }

    @Override protected String getUserAgentStylesheet() {
        return getClass().getResource("clock.css").toExternalForm();
    }
}

