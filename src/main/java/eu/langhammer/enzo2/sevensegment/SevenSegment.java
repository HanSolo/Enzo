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

package eu.langhammer.enzo2.sevensegment;

import eu.langhammer.enzo2.sevensegment.skin.SevenSegmentSkin;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SevenSegment extends Control {
    public static final String           STYLE_CLASS_RED     = "red";
    public static final String           STYLE_CLASS_GREEN   = "green";
    public static final String           STYLE_CLASS_BLUE    = "blue";
    public static final String           STYLE_CLASS_YELLOW  = "yellow";
    public static final String           STYLE_CLASS_ORANGE  = "orange";
    public static final String           STYLE_CLASS_CYAN    = "cyan";
    public static final String           STYLE_CLASS_MAGENTA = "magenta";
    public static final String           STYLE_CLASS_WHITE   = "white";
    public static final String           STYLE_CLASS_BLACK   = "black";
    public static enum                   Segment { A, B, C, D, E, F, G, DOT }
    public static enum                   SegmentStyle {
        RED(STYLE_CLASS_RED),
        GREEN(STYLE_CLASS_GREEN),
        BLUE(STYLE_CLASS_BLUE),
        YELLOW(STYLE_CLASS_YELLOW),
        ORANGE(STYLE_CLASS_ORANGE),
        CYAN(STYLE_CLASS_CYAN),
        MAGENTA(STYLE_CLASS_MAGENTA),
        WHITE(STYLE_CLASS_WHITE),
        BLACK(STYLE_CLASS_BLACK);

        public final String ON_CLASS;
        public final String OFF_CLASS;

        private SegmentStyle(final String CLASS_NAME) {
            ON_CLASS  = CLASS_NAME;
            OFF_CLASS = CLASS_NAME + "-off";
        }
    }
    private boolean                      keepAspect;
    private String                       _character = " ";
    private StringProperty               character;
    private boolean                      _dotOn = false;
    private BooleanProperty              dotOn;
    private SegmentStyle                 _segmentStyle;
    private ObjectProperty<SegmentStyle> segmentStyle;
    private Map<Integer, List<Segment>>  mapping;


    // ******************** Constructors **************************************
    public SevenSegment() {
        this(" ", SegmentStyle.RED);
    }
    public SevenSegment(final String CHARACTER) {
        this(CHARACTER, SegmentStyle.RED);
    }
    public SevenSegment(final Character CHARACTER) {
        this(CHARACTER, SegmentStyle.RED);
    }
    public SevenSegment(final int CHARACTER) {
        this(Integer.toString(CHARACTER < 0 ? 0 : (CHARACTER > 9 ? 9 : CHARACTER)), SegmentStyle.RED);
    }
    public SevenSegment(final Character CHARACTER, final SegmentStyle SEGMENT_STYLE) {
        this(String.valueOf(CHARACTER), SEGMENT_STYLE);
    }
    public SevenSegment(final int CHARACTER, final SegmentStyle SEGMENT_STYLE) {
        this(Integer.toString(CHARACTER < 0 ? 0 : (CHARACTER > 9 ? 9 : CHARACTER)), SEGMENT_STYLE);
    }
    public SevenSegment(final String CHARACTER, final SegmentStyle SEGMENT_STYLE) {
        getStyleClass().add("seven-segment");
        keepAspect    = true;
        _character    = CHARACTER.substring(0, 1);
        _segmentStyle = SEGMENT_STYLE;
        mapping       = new HashMap<>(48);
        initMapping();
    }


    // ******************** Initialization ************************************
    private void initMapping() {
        // Space
        mapping.put(20, Arrays.asList(new Segment[] {}));
        // .
        mapping.put(46, Arrays.asList(new Segment[]{Segment.DOT}));
        // 0 - 9
        mapping.put(48, Arrays.asList(new Segment[]{Segment.A, Segment.B, Segment.C, Segment.D, Segment.E, Segment.F}));
        mapping.put(49, Arrays.asList(new Segment[]{Segment.B, Segment.C}));
        mapping.put(50, Arrays.asList(new Segment[]{Segment.A, Segment.B, Segment.D, Segment.E, Segment.G}));
        mapping.put(51, Arrays.asList(new Segment[]{Segment.A, Segment.B, Segment.C, Segment.D, Segment.G}));
        mapping.put(52, Arrays.asList(new Segment[]{Segment.B, Segment.C, Segment.F, Segment.G}));
        mapping.put(53, Arrays.asList(new Segment[]{Segment.A, Segment.C, Segment.D, Segment.F, Segment.G}));
        mapping.put(54, Arrays.asList(new Segment[]{Segment.A, Segment.C, Segment.D, Segment.E, Segment.F, Segment.G}));
        mapping.put(55, Arrays.asList(new Segment[]{Segment.A, Segment.B, Segment.C}));
        mapping.put(56, Arrays.asList(new Segment[]{Segment.A, Segment.B, Segment.C, Segment.D, Segment.E, Segment.F, Segment.G}));
        mapping.put(57, Arrays.asList(new Segment[] {
            Segment.A,
            Segment.B,
            Segment.C,
            Segment.D,
            Segment.F,
            Segment.G
        }));
    }


    // ******************** Methods *******************************************
    public final boolean isKeepAspect() {
        return keepAspect;
    }
    public final void setKeepAspect(final boolean KEEP_ASPECT) {
        keepAspect = KEEP_ASPECT;
    }

    public final String getCharacter() {
        return null == character ? _character : character.get();
    }
    public final void setCharacter(final String CHARACTER) {
        if (null == character) {
            _character = CHARACTER.substring(0, 1);
        } else {
            character.set(CHARACTER.substring(0, 1));
        }
    }
    public final void setCharacter(final Character CHARACTER) {
        if (null == character) {
            _character = String.valueOf(CHARACTER);
        } else {
            character.set(String.valueOf(CHARACTER));
        }
    }
    public final void setCharacter(final int CHARACTER) {
        if (null == character) {
            _character = Integer.toString(CHARACTER < 0 ? 0 : (CHARACTER > 9 ? 9 : CHARACTER));
        } else {
            character.set(Integer.toString(CHARACTER < 0 ? 0 : (CHARACTER > 9 ? 9 : CHARACTER)));
        }
    }
    public final ReadOnlyStringProperty characterProperty() {
        if (null == character) {
            character = new SimpleStringProperty(this, "character", _character);
        }
        return character;
    }

    public final boolean isDotOn() {
        return null == dotOn ? _dotOn : dotOn.get();
    }
    public final void setDotOn(final boolean DOT_ON) {
        if (null == dotOn) {
            _dotOn = DOT_ON;
        } else {
            dotOn.set(DOT_ON);
        }
    }
    public final BooleanProperty dotOnProperty() {
        if (null == dotOn) {
            dotOn = new SimpleBooleanProperty(this, "dotOn", _dotOn);
        }
        return dotOn;
    }

    public final SegmentStyle getSegmentStyle() {
        return null == segmentStyle ? _segmentStyle : segmentStyle.get();
    }
    public final void setSegmentStyle(final SegmentStyle SEGMENT_STYLE) {
        if (null == segmentStyle) {
            _segmentStyle = SEGMENT_STYLE;
        } else {
            segmentStyle.set(SEGMENT_STYLE);
        }
    }
    public final ObjectProperty<SegmentStyle> segmentStyleProperty() {
        if (null == segmentStyle) {
            segmentStyle = new SimpleObjectProperty<>(this, "segmentStyle", _segmentStyle);
        }
        return segmentStyle;
    }

    /**
     * Returns a Map that contains the default mapping from ascii integers to lcd segments.
     * The segments are defined as follows:
     *
     *         AAAAAAAAAA
     *        F          B
     *        F          B
     *        F          B
     *        F          B
     *         GGGGGGGGGG
     *        E          C
     *        E          C
     *        E          C
     *        E          C
     *         DDDDDDDDDD
     *
     * @return a Map that contains the default mapping from ascii integers to segments
     */
    public final Map<Integer, List<Segment>> getSegmentMapping() {
        Map<Integer, List<Segment>> segmentMapping = new HashMap<>(48);
        for (int key : mapping.keySet()) {
            segmentMapping.put(key, mapping.get(key));
        }
        return segmentMapping;
    }

    @Override public boolean isResizable() {
        return true;
    }
    

    // ******************** Style related *************************************
    @Override protected Skin createDefaultSkin() {
        return new SevenSegmentSkin(this);
    }

    @Override public String getUserAgentStylesheet() {
        return getClass().getResource(getClass().getSimpleName().toLowerCase() + ".css").toExternalForm();
    }
}

