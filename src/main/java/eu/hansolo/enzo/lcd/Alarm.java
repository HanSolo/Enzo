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

package eu.hansolo.enzo.lcd;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


/**
 * Created by
 * User: hansolo
 * Date: 19.08.13
 * Time: 10:27
 */
public class Alarm {
    public static enum Repetition {
        ONCE,
        HOURLY,
        DAILY,
        WEEKLY
    }

    private Repetition    repetition;
    private LocalDateTime time;
    private boolean       active;
    private String        text;
    private Command       command;


    // ******************** Constructors **************************************
    public Alarm() {
        this(Repetition.ONCE, LocalDateTime.now().plus(5, ChronoUnit.MINUTES));
    }
    public Alarm(final Repetition REPETITION, final LocalDateTime TIME) {
        this(REPETITION, TIME, true);
    }
    public Alarm(final Repetition REPETITION, final LocalDateTime TIME, final boolean ACTIVE) {
        this(REPETITION, TIME, ACTIVE, "");
    }
    public Alarm(final Repetition REPETITION, final LocalDateTime TIME, final boolean ACTIVE, final String TEXT) {
        this(REPETITION, TIME, ACTIVE, TEXT, null);
    }
    public Alarm(final Repetition REPETITION, final LocalDateTime TIME, final boolean ACTIVE, final String TEXT, final Command COMMAND) {
        repetition = REPETITION;
        time           = TIME;
        active         = ACTIVE;
        text           = TEXT;
        command        = COMMAND;
    }


    // ******************** Methods *******************************************
    public Repetition getRepetition() {
        return repetition;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public boolean isActive() {
        return active;
    }
    public void setActive(final boolean ACTIVE) {
        active = ACTIVE;
    }

    public String getText() {
        return text;
    }
    public void setText(final String TEXT) {
        text = TEXT;
    }

    public Command getCommand() {
        return command;
    }
    public void setCommand(final Command COMMAND) {
        command = COMMAND;
    }
    public void executeCommand() {
        if (null != command) command.execute();
    }


    // ******************** Internal Classes **********************************
    public static class AlarmEvent extends Event {
        public static final EventType<AlarmEvent> ALARM = new EventType(ANY, "ALARM");
        private Alarm alarm;


        // ******************** Constructors **************************************
        public AlarmEvent(final Alarm ALARM, final Object SOURCE, final EventTarget TARGET, EventType<AlarmEvent> TYPE) {
            super(SOURCE, TARGET, TYPE);
            alarm = ALARM;
        }

        public Alarm getAlarm() {
            return alarm;
        }
    }

    public interface Command {
        void execute();
    }
}
