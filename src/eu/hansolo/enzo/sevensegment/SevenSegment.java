/*
 * Copyright (c) 2013, Gerrit Grunwald
 * All right reserved
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * The names of its contributors may not be used to endorse or promote
 * products derived from this software without specific prior written
 * permission.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package eu.hansolo.enzo.sevensegment;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Control;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SevenSegment extends Control {
    public static final String           STYLE_CLASS_OFF     = "seven-segment-off";
    public static final String           STYLE_CLASS_RED     = "seven-segment-red";
    public static final String           STYLE_CLASS_GREEN   = "seven-segment-green";
    public static final String           STYLE_CLASS_BLUE    = "seven-segment-blue";
    public static final String           STYLE_CLASS_YELLOW  = "seven-segment-yellow";
    public static final String           STYLE_CLASS_ORANGE  = "seven-segment-orange";
    public static final String           STYLE_CLASS_CYAN    = "seven-segment-cyan";
    public static final String           STYLE_CLASS_MAGENTA = "seven-segment-magenta";
    public static final String           STYLE_CLASS_WHITE   = "seven-segment-white";
    public static final String           STYLE_CLASS_BLACK   = "seven-segment-black";
    public static enum                   Segment {
        A,
        B,
        C,
        D,
        E,
        F,
        G,
        DOT
    }
    public static enum                   SegmentStyle {
        OFF(STYLE_CLASS_OFF),
        RED(STYLE_CLASS_RED),
        GREEN(STYLE_CLASS_GREEN),
        BLUE(STYLE_CLASS_BLUE),
        YELLOW(STYLE_CLASS_YELLOW),
        ORANGE(STYLE_CLASS_ORANGE),
        CYAN(STYLE_CLASS_CYAN),
        MAGENTA(STYLE_CLASS_MAGENTA),
        WHITE(STYLE_CLASS_WHITE),
        BLACK(STYLE_CLASS_BLACK);

        public final String CLASS;

        private SegmentStyle(final String CLASS_NAME) {
            CLASS = CLASS_NAME;
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
    @Override protected String getUserAgentStylesheet() {
        return getClass().getResource(getClass().getSimpleName().toLowerCase() + ".css").toExternalForm();
    }
}

