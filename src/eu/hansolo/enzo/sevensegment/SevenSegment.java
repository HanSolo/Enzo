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
    public static final String           STYLE_CLASS_OFF     = "sevensegment-off";
    public static final String           STYLE_CLASS_RED     = "sevensegment-red";
    public static final String           STYLE_CLASS_GREEN   = "sevensegment-green";
    public static final String           STYLE_CLASS_BLUE    = "sevensegment-blue";
    public static final String           STYLE_CLASS_YELLOW  = "sevensegment-yellow";
    public static final String           STYLE_CLASS_ORANGE  = "sevensegment-orange";
    public static final String           STYLE_CLASS_CYAN    = "sevensegment-cyan";
    public static final String           STYLE_CLASS_MAGENTA = "sevensegment-magenta";
    public static final String           STYLE_CLASS_WHITE   = "sevensegment-white";
    public static final String           STYLE_CLASS_BLACK   = "sevensegment-black";
    public static enum                   Segment {
        A,
        B,
        C,
        D,
        E,
        F,
        G,
        DOT
    };
    public static enum                   SegmentStyle {
        OFF("sevensegment-off"),
        RED("sevensegment-red"),
        GREEN("sevensegment-green"),
        BLUE("sevensegment-blue"),
        YELLOW("sevensegment-yellow"),
        ORANGE("sevensegment-orange"),
        CYAN("sevensegment-cyan"),
        MAGENTA("sevensegment-magenta"),
        WHITE("sevensegment-white"),
        BLACK("sevensegment-black");

        public final String CLASS;

        private SegmentStyle(final String CLASS_NAME) {
            CLASS = CLASS_NAME;
        }
    }
    private BooleanProperty              keepAspect;
    private StringProperty               character;
    private BooleanProperty              dotOn;
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
        getStyleClass().add("sevensegment");
        keepAspect   = new SimpleBooleanProperty(true);
        character    = new SimpleStringProperty(CHARACTER.substring(0, 1));
        dotOn        = new SimpleBooleanProperty(false);
        segmentStyle = new SimpleObjectProperty<>(SEGMENT_STYLE);
        mapping      = new HashMap<>(48);
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
        return keepAspect.get();
    }
    public final void setKeepAspect(final boolean KEEP_ASPECT) {
        keepAspect.set(KEEP_ASPECT);
    }
    public final BooleanProperty keepAspectProperty() {
        return keepAspect;
    }

    public final String getCharacter() {
        return character.get();
    }
    public final void setCharacter(final String CHARACTER) {
        character.set(CHARACTER.substring(0, 1));
    }
    public final void setCharacter(final Character CHARACTER) {
        character.set(String.valueOf(CHARACTER));
    }
    public final void setCharacter(final int CHARACTER) {
        character.set(Integer.toString(CHARACTER < 0 ? 0 : (CHARACTER > 9 ? 9 : CHARACTER)));
    }
    public final ReadOnlyStringProperty characterProperty() {
        return character;
    }

    public final boolean isDotOn() {
        return dotOn.get();
    }
    public final void setDotOn(final boolean DOT_ON) {
        dotOn.set(DOT_ON);
    }
    public final BooleanProperty dotOnProperty() {
        return dotOn;
    }

    public final SegmentStyle getSegmentStyle() {
        return segmentStyle.get();
    }
    public final void setSegmentStyle(final SegmentStyle SEGMENT_STYLE) {
        segmentStyle.set(SEGMENT_STYLE);
    }
    public final ObjectProperty<SegmentStyle> segmentStyleProperty() {
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

