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
package eu.langhammer.enzo2.common;

import java.util.Objects;
import javafx.beans.property.*;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.scene.image.Image;
import javafx.util.Builder;

/**
 * This class is used for line charts as a <i>section</i> in the chart. This
 * section have a start and end point and optional text, icon and style.
 *
 * @author gerrit grunwald (creator)
 * @author tim langhammer (editor)
 */
public class Section {

    // the start of the section
    private double start;
    // the start Property
    private DoubleProperty fx_startProp;
    // the end of the section
    private double stop;
    // the end Property
    private DoubleProperty fx_stopProp;
    // optional text
    private String text;
    // text Property
    private StringProperty fx_textProp;
    // optional icon Property
    private ObjectProperty<Image> fx_iconProp;
    // optional style class
    private String styleClass;
    // style class
    private StringProperty fx_styleClassProp;

    // ******************** Constructors **************************************
    public Section() {
        this( -1D, -1D, "" );
    }

    public Section( final double START,
                    final double STOP ) {
        this( START, STOP, "" );
    }

    public Section( final double START,
                    final double STOP,
                    final String TEXT ) {
        this( START, STOP, TEXT, null );
    }

    public Section( final double START,
                    final double STOP,
                    final String TEXT,
                    final Image ICON ) {
        this( START, STOP, TEXT, ICON, "" );
    }

    public Section( final double START,
                    final double STOP,
                    final String TEXT,
                    final Image ICON,
                    final String STYLE_CLASS ) {
        start = START;
        stop = STOP;
        text = TEXT;
        fx_iconProp = new SimpleObjectProperty<>( this, "icon", ICON );
        styleClass = STYLE_CLASS;
    }

    // ******************** Methods *******************************************
    public final double getStart() {
        return null == fx_startProp ? start : fx_startProp.get();
    }

    public final void setStart( final double START ) {
        if ( null == fx_startProp ) {
            start = START;
        }
        else {
            fx_startProp.set( START );
        }
    }

    public final DoubleProperty startProperty() {
        if ( null == fx_startProp ) {
            fx_startProp = new SimpleDoubleProperty( this, "start", start );
        }
        return fx_startProp;
    }

    public final double getStop() {
        return null == fx_stopProp ? stop : fx_stopProp.get();
    }

    public final void setStop( final double STOP ) {
        if ( null == fx_stopProp ) {
            stop = STOP;
        }
        else {
            fx_stopProp.set( STOP );
        }
    }

    public final DoubleProperty stopProperty() {
        if ( null == fx_stopProp ) {
            fx_stopProp = new SimpleDoubleProperty( this, "stop", stop );
        }
        return fx_stopProp;
    }

    public final String getText() {
        return null == fx_textProp ? text : fx_textProp.get();
    }

    public final void setText( final String TEXT ) {
        if ( null == fx_textProp ) {
            text = TEXT;
        }
        else {
            fx_textProp.set( TEXT );
        }
    }

    public final StringProperty textProperty() {
        if ( null == fx_textProp ) {
            fx_textProp = new SimpleStringProperty( this, "text", text );
        }
        return fx_textProp;
    }

    public final Image getImage() {
        return fx_iconProp.get();
    }

    public final void setIcon( final Image IMAGE ) {
        fx_iconProp.set( IMAGE );
    }

    public final ObjectProperty<Image> iconProperty() {
        return fx_iconProp;
    }

    public final String getStyleClass() {
        return null == fx_styleClassProp ? styleClass : fx_styleClassProp.get();
    }

    public final void setStyleClass( final String STYLE_CLASS ) {
        if ( null == fx_styleClassProp ) {
            styleClass = STYLE_CLASS;
        }
        else {
            fx_styleClassProp.set( STYLE_CLASS );
        }
    }

    public final StringProperty styleClassProperty() {
        if ( null == fx_styleClassProp ) {
            fx_styleClassProp = new SimpleStringProperty( this, "styleClass", styleClass );
        }
        return fx_styleClassProp;
    }

    public boolean contains( final double VALUE ) {
        return ( ( Double.compare( VALUE, getStart() ) >= 0 && Double.compare( VALUE, getStop() ) <= 0 ) );
    }

    // ******************** Event handling ************************************
    public final ObjectProperty<EventHandler<SectionEvent>> onEnteringSectionProperty() {
        return onEnteringSection;
    }

    public final void setOnEnteringSection( EventHandler<SectionEvent> value ) {
        onEnteringSectionProperty().set( value );
    }

    public final EventHandler<SectionEvent> getOnEnteringSection() {
        return onEnteringSectionProperty().get();
    }
    private ObjectProperty<EventHandler<SectionEvent>> onEnteringSection = new ObjectPropertyBase<EventHandler<SectionEvent>>() {
        @Override
        public Object getBean() {
            return this;
        }

        @Override
        public String getName() {
            return "onEnteringSection";
        }
    };

    public final ObjectProperty<EventHandler<SectionEvent>> onLeavingSectionProperty() {
        return onLeavingSection;
    }

    public final void setOnLeavingSection( EventHandler<SectionEvent> value ) {
        onLeavingSectionProperty().set( value );
    }

    public final EventHandler<SectionEvent> getOnLeavingSection() {
        return onLeavingSectionProperty().get();
    }

    private ObjectProperty<EventHandler<SectionEvent>> onLeavingSection = new ObjectPropertyBase<EventHandler<SectionEvent>>() {
        @Override
        public Object getBean() {
            return this;
        }

        @Override
        public String getName() {
            return "onLeavingSection";
        }
    };

    public void fireSectionEvent( final SectionEvent EVENT ) {
        final EventHandler<SectionEvent> HANDLER;
        final EventType<?> TYPE = EVENT.getEventType();
        if ( SectionEvent.ENTERING_SECTION == TYPE ) {
            HANDLER = getOnEnteringSection();
        }
        else if ( SectionEvent.LEAVING_SECTION == TYPE ) {
            HANDLER = getOnLeavingSection();
        }
        else {
            HANDLER = null;
        }

        if ( null == HANDLER ) {
            return;
        }

        HANDLER.handle( EVENT );
    }

    public static final SectionBuilder create( final double startVal,
                                               final double stopVal ) {
        return new SectionBuilder( startVal, stopVal );
    }

    /**
     *
     * @param obj
     *
     * @return
     */
    @Override
    public boolean equals( final Object obj ) {
        if ( null == obj ) {
            return false;
        }
        if ( obj.getClass() != getClass() ) {
            return false;
        }

        final Section otherSection = ( Section ) obj;

        return ( Double.compare( otherSection.getStart(), getStart() ) == 0
                && Double.compare( otherSection.getStop(), getStop() ) == 0
                && otherSection.getText().equals( getText() ) );
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + ( int ) ( Double.doubleToLongBits( this.start ) ^ ( Double.doubleToLongBits( this.start ) >>> 32 ) );
        hash = 43 * hash + ( int ) ( Double.doubleToLongBits( this.stop ) ^ ( Double.doubleToLongBits( this.stop ) >>> 32 ) );
        hash = 43 * hash + Objects.hashCode( this.text );
        hash = 43 * hash + Objects.hashCode( this.styleClass );
        return hash;
    }

    @Override
    public String toString() {
        final StringBuilder NAME = new StringBuilder();
        NAME.append( "Section: " ).append( "\n" );
        NAME.append( "text      : " ).append( getText() ).append( "\n" );
        NAME.append( "startValue: " ).append( getStart() ).append( "\n" );
        NAME.append( "stopValue : " ).append( getStop() ).append( "\n" );
        return NAME.toString();
    }

    // ******************** Inner Classes *************************************
    public static class SectionEvent extends Event {

        public static final EventType<SectionEvent> ENTERING_SECTION = new EventType<>( ANY, "enteringSection" );
        public static final EventType<SectionEvent> LEAVING_SECTION = new EventType<>( ANY, "leavingSection" );
        private static final long serialVersionUID = 20151123L;

        // ******************** Constructors **************************************
        public SectionEvent( final Object SOURCE,
                             final EventTarget TARGET,
                             EventType<SectionEvent> TYPE ) {
            super( SOURCE, TARGET, TYPE );
        }
    }

    public static final class SectionBuilder implements Builder<Section> {

        // mandatory
        private final double start;
        private final double stop;
        // optional
        private String text = "";
        private Image icon = null;
        private String style = "";

        private SectionBuilder( final double start, final double stop ) {
            this.start = start;
            this.stop = stop;
        }

        public SectionBuilder text( final String textStr ) {
            this.text = textStr;
            return this;
        }

        public SectionBuilder style( final String styleStr ) {
            this.style = styleStr;
            return this;
        }

        public SectionBuilder icon( final Image iconImage ) {
            this.icon = iconImage;
            return this;
        }

        @Override
        public Section build() {

            return new Section( start, stop, text, icon, style );

        }

    }
}
