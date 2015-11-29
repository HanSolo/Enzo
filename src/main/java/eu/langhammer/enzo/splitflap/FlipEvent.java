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

package eu.langhammer.enzo.splitflap;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;


/**
 * Created by
 * User: hansolo
 * Date: 05.02.13
 * Time: 09:30
 */
public class FlipEvent extends Event {
    public static final EventType<FlipEvent> FLIP_FORWARD  = new EventType(ANY, "flipForward");
    public static final EventType<FlipEvent> FLIP_BACKWARD = new EventType(ANY, "flipBackward");
    public static final EventType<FlipEvent> FLIP_FINISHED = new EventType(ANY, "flipFinished");


    // ******************** Constructors **************************************
    public FlipEvent(final Object SOURCE, final EventTarget TARGET, EventType<FlipEvent> TYPE) {
        super(SOURCE, TARGET, TYPE);
    }
}
