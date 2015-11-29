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

import java.util.HashMap;
import javafx.beans.property.*;
import javafx.beans.value.WritableDoubleValue;
import javafx.scene.image.Image;

/**
 * Builder for {@linkplain Section}'s.
 * <p>
 * ~~ HAN SOLO ~~ Created by User: hansolo Date: 26.08.13 Time: 16:13 ~~ END ~~
 *
 * @author Gerrit Grunwald
 * @author Tim Langhammer (edited)
 * @deprecated use {@linkplain Section.SectionBuilder} instead
 */
public class SectionBuilder {

    private final HashMap<String, Property<?>> properties = new HashMap<>();

    // ******************** Constructors **************************************
    protected SectionBuilder() {
    }

    // ******************** Methods *******************************************
    public static final SectionBuilder create() {
        return new SectionBuilder();
    }

    public final SectionBuilder start( final double START ) {
        properties.put( "start", new SimpleDoubleProperty( START ) );
        return this;
    }

    public final SectionBuilder stop( final double STOP ) {
        properties.put( "stop", new SimpleDoubleProperty( STOP ) );
        return this;
    }

    public final SectionBuilder text( final String TEXT ) {
        properties.put( "text", new SimpleStringProperty( TEXT ) );
        return this;
    }

    public final SectionBuilder icon( final Image IMAGE ) {
        properties.put( "icon", new SimpleObjectProperty<>( IMAGE ) );
        return this;
    }

    public final SectionBuilder styleClass( final String STYLE_CLASS ) {
        properties.put( "styleClass", new SimpleStringProperty( STYLE_CLASS ) );
        return this;
    }

    public final Section build() {

        final Section SECTION = new Section();

        properties.keySet().stream().forEach( (key) -> {
            if ( null != key ) {
                switch ( key ) {
                    case "start":
                        SECTION.setStart( ( ( WritableDoubleValue ) properties.get( key ) ).get() );
                        break;
                    case "stop":
                        SECTION.setStop( ( ( WritableDoubleValue ) properties.get( key ) ).get() );
                        break;
                    case "text":
                        SECTION.setText( ( ( StringProperty ) properties.get( key ) ).get() );
                        break;
                    case "icon":
                        SECTION.setIcon( ( ( ObjectProperty<Image> ) properties.get( key ) ).get() );
                        break;
                    case "styleClass":
                        SECTION.setStyleClass( ( ( StringProperty ) properties.get( key ) ).get() );
                        break;
                }
            }
        } );
        return SECTION;
    }
}
