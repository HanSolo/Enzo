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

import java.util.Objects;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.layout.Region;

/**
 * Created by
 * User: hansolo
 * Date: 03.07.13
 * Time: 08:12
 */
public class Marker extends Region {

    private double _value;
    private DoubleProperty valueProp;
    private String _text;
    private StringProperty textProp;
    private boolean exceeded;

    // ******************** Constructors **************************************
    public Marker() {
        this( 0D, "Marker" );
    }

    public Marker( final double VALUE ) {
        this( VALUE, "Marker" );
    }

    public Marker( final double VALUE, final String TEXT ) {
        this._value = VALUE;
        this._text = TEXT;
        this.exceeded = false;
    }

    // ******************** Methods *******************************************
    public final double getValue() {
        return null == valueProp ? _value : valueProp.get();
    }

    public final void setValue( final double START ) {
        if ( null == valueProp ) {
            this._value = START;
        }
        else {
            valueProp.set( START );
        }
    }

    public final DoubleProperty startProperty() {
        if ( null == valueProp ) {
            valueProp = new SimpleDoubleProperty( this, "value", _value );
        }
        return valueProp;
    }

    public final String getText() {
        return null == textProp ? _text : textProp.get();
    }

    public final void setText( final String TEXT ) {
        if ( null == textProp ) {
            this._text = TEXT;
        }
        else {
            textProp.set( TEXT );
        }
    }

    public final StringProperty textProperty() {
        if ( null == textProp ) {
            textProp = new SimpleStringProperty( this, "text", _text );
        }
        return textProp;
    }

    public final boolean isExceeded() {
        return exceeded;
    }

    public final void setExceeded( final boolean EXCEEDED ) {
        this.exceeded = EXCEEDED;
    }

    // ******************** Event handling ************************************
    public final ObjectProperty<EventHandler<ValueEvent>> onMarkerExceededProperty() {
        return onMarkerExceeded;
    }

    public final void setOnMarkerExceeded( EventHandler<ValueEvent> value ) {
        onMarkerExceededProperty().set( value );
    }

    public final EventHandler<ValueEvent> getOnMarkerExceeded() {
        return onMarkerExceededProperty().get();
    }
    
    private final ObjectProperty<EventHandler<ValueEvent>> onMarkerExceeded = new ObjectPropertyBase<EventHandler<ValueEvent>>() {
        @Override
        public Object getBean() {
            return this;
        }

        @Override
        public String getName() {
            return "onMarkerExceeded";
        }
    };

    public final ObjectProperty<EventHandler<ValueEvent>> onMarkerUnderrunProperty() {
        return onMarkerUnderrun;
    }

    public final void setOnMarkerUnderrun( EventHandler<ValueEvent> value ) {
        onMarkerUnderrunProperty().set( value );
    }

    public final EventHandler<ValueEvent> getOnMarkerUnderrun() {
        return onMarkerUnderrunProperty().get();
    }
    
    private final ObjectProperty<EventHandler<ValueEvent>> onMarkerUnderrun = new ObjectPropertyBase<EventHandler<ValueEvent>>() {
        @Override
        public Object getBean() {
            return this;
        }

        @Override
        public String getName() {
            return "onMarkerUnderrun";
        }
    };

    public void fireMarkerEvent( final ValueEvent EVENT ) {
        final EventHandler<ValueEvent> HANDLER;
        final EventType<?> TYPE = EVENT.getEventType();
        
        if ( ValueEvent.VALUE_EXCEEDED == TYPE ) {
            HANDLER = getOnMarkerExceeded();
        }
        else if ( ValueEvent.VALUE_UNDERRUN == TYPE ) {
            HANDLER = getOnMarkerUnderrun();
        }
        else {
            HANDLER = null;
        }

        if ( null == HANDLER ) {
            return;
        }

        HANDLER.handle( EVENT );
    }

    @Override
    public boolean equals( Object obj ) {
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final Marker other = ( Marker ) obj;
        if ( Double.doubleToLongBits( this._value ) != Double.doubleToLongBits( other._value ) ) {
            return false;
        }
        if ( !Objects.equals( this._text, other._text ) ) {
            return false;
        }
        return this.exceeded == other.exceeded;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + ( int ) ( Double.doubleToLongBits( this._value ) ^ ( Double.doubleToLongBits( this._value ) >>> 32 ) );
        hash = 83 * hash + Objects.hashCode( this._text );
        hash = 83 * hash + ( this.exceeded ? 1 : 0 );
        return hash;
    }

    @Override
    public String toString() {
        final StringBuilder nameSB = new StringBuilder();
        nameSB.append( "Section: " ).append( "\n" );
        nameSB.append( "text   : " ).append( textProp.get() ).append( "\n" );
        nameSB.append( "value  : " ).append( valueProp.get() ).append( "\n" );
        return nameSB.toString();
    }

    // ******************** Inner Classes *************************************
    // TODO: kann durch ValueEvent ersetzt werden
    
    /*
    public static class ValueEvent extends Event {
        
        
        public static final EventType<ValueEvent> MARKER_EXCEEDED = new EventType<>( ANY, "markerExceeded" );
        public static final EventType<ValueEvent> MARKER_UNDERRUN = new EventType<>( ANY, "markerUnderrun" );
        private static final long serialVersionUID = 200002009L;

        // ******************** Constructors **************************************
        public ValueEvent( final Object SOURCE, final EventTarget TARGET, EventType<ValueEvent> TYPE ) {
            super( SOURCE, TARGET, TYPE );
        }
    }  */
}
