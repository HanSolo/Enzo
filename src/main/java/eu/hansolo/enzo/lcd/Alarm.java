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

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;


/**
 * Created by
 * User: hansolo
 * Date: 19.08.13
 * Time: 10:27
 */
public class Alarm {
    public static enum Interval {
        ONCE,
        HOURLY,
        DAILY,
        MO_TO_FR,
        SA_SU,
        WEEKLY,
        MONTHLY,
        CUSTOM
    }

    private Interval  interval;
    private LocalTime time;
    private Duration  duration;
    private boolean   active;
    private String    text;
    private Command   command;


    // ******************** Constructors **************************************
    public Alarm() {
        this(Interval.ONCE, LocalTime.now().plus(5, ChronoUnit.MINUTES));
    }
    public Alarm(final Interval INTERVAL, final LocalTime TIME) {
        this(INTERVAL, TIME, null);
    }
    public Alarm(final Interval INTERVAL, final LocalTime TIME, final Command COMMAND) {
        this(INTERVAL, TIME, COMMAND, false);
    }
    public Alarm(final Interval INTERVAL, final LocalTime TIME, final Command COMMAND, final boolean ACTIVE) {
        this(INTERVAL, TIME, COMMAND, ACTIVE, "");
    }
    public Alarm(final Interval INTERVAL, final LocalTime TIME, final Command COMMAND, final boolean ACTIVE, final String TEXT) {
        interval = INTERVAL;
        time     = TIME;
        command  = COMMAND;
        active   = ACTIVE;
        text     = TEXT;
        init();
    }


    // ******************** Initialization ************************************
    private void init() {
        switch(interval) {
            case HOURLY:
                duration = Duration.ofHours(1l);
                break;
            case DAILY:
                duration = Duration.ofDays(1l);
                break;
            case WEEKLY:
                duration = Duration.ofDays(7l);
                break;
            default:
            case ONCE:
                duration = Duration.ofSeconds(0l);
                break;
        }
    }


    // ******************** Methods *******************************************
    public Interval getInterval() {
        return interval;
    }

    public LocalTime getTime() {
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
    public interface Command {
        void execute();
    }
}
