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
package eu.langhammer.enzo.common;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

/**
 * Common <i>event</i> class for all kind of 1D - values.
 * That is values from a minimum to a maximum value like physical units.
 * ~~ Original: ~~
 * Created by
 * User: hansolo
 * Date: 03.07.13
 * Time: 08:58
 * ~~ END ~~
 * 
 * @author hansolo (creator)
 * @author langhammer (editor)
 */
public class ValueEvent extends Event {

    public static final long serialVersionUID = 20152111L;

    public static final EventType<ValueEvent> VALUE_EXCEEDED = new EventType<>( ANY, "VALUE_EXCEEDED" );
    public static final EventType<ValueEvent> VALUE_UNDERRUN = new EventType<>( ANY, "VALUE_UNDERRUN" );

    // ******************** Constructors **************************************
    public ValueEvent( final Object SOURCE,
                       final EventTarget TARGET,
                       EventType<ValueEvent> TYPE ) {
        super( SOURCE, TARGET, TYPE );
    }
}
