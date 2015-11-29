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

package eu.langhammer.enzo2.sixteensegment;

import eu.langhammer.enzo2.sixteensegment.skin.SixteenSegmentSkin;
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


public class SixteenSegment extends Control {
    public static final String           STYLE_CLASS_RED     = "red";
    public static final String           STYLE_CLASS_GREEN   = "green";
    public static final String           STYLE_CLASS_BLUE    = "blue";
    public static final String           STYLE_CLASS_YELLOW  = "yellow";
    public static final String           STYLE_CLASS_ORANGE  = "orange";
    public static final String           STYLE_CLASS_CYAN    = "cyan";
    public static final String           STYLE_CLASS_MAGENTA = "magenta";
    public static final String           STYLE_CLASS_WHITE   = "white";
    public static final String           STYLE_CLASS_BLACK   = "black";
    public static enum                   Segment { A1, A2, B, C, D2, D1, E, F, G, H, I, K, L, M, N, P, DOT }
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
    public SixteenSegment() {
        this(" ", SegmentStyle.RED);
    }
    public SixteenSegment(final String CHARACTER) {
        this(CHARACTER, SegmentStyle.RED);
    }
    public SixteenSegment(final Character CHARACTER) {
        this(CHARACTER, SegmentStyle.RED);
    }
    public SixteenSegment(final int CHARACTER) {
        this(Integer.toString(CHARACTER < 0 ? 0 : (CHARACTER > 9 ? 9 : CHARACTER)), SegmentStyle.RED);
    }
    public SixteenSegment(final Character CHARACTER, final SegmentStyle SEGMENT_STYLE) {
        this(String.valueOf(CHARACTER), SEGMENT_STYLE);
    }
    public SixteenSegment(final int CHARACTER, final SegmentStyle SEGMENT_STYLE) {
        this(Integer.toString(CHARACTER < 0 ? 0 : (CHARACTER > 9 ? 9 : CHARACTER)), SEGMENT_STYLE);
    }
    public SixteenSegment(final String CHARACTER, final SegmentStyle SEGMENT_STYLE) {
        getStyleClass().add("sixteen-segment");
        keepAspect    = true;
        _character    = CHARACTER.substring(0, 1);
        _segmentStyle = SEGMENT_STYLE;
        mapping       = new HashMap<>(48);
        initMapping();
    }


    // ******************** Initialization ************************************
    private void initMapping() {
        /*
         * Sixteen Segments
         *
         *         A1A1A1 A2A2A2
         *        F G    H    I B
         *        F  G   H   I  B
         *        F   G  H  I   B
         *        F    G H I    B
         *         PPPPPP KKKKKK
         *        E    N M L    C
         *        E   N  M  L   C
         *        E  N   M   L  C
         *        E N    M    L C
         *         D1D1D1 D2D2D2
         *
         */

        // Space
        mapping.put(20, Arrays.asList(new Segment[] {}));
        // * + , - . /
        mapping.put(42, Arrays.asList(new Segment[]{Segment.G, Segment.H, Segment.I, Segment.L, Segment.M, Segment.N}));
        mapping.put(43, Arrays.asList(new Segment[]{Segment.H, Segment.K, Segment.M, Segment.P}));
        mapping.put(44, Arrays.asList(new Segment[]{Segment.N}));
        mapping.put(45, Arrays.asList(new Segment[]{Segment.P, Segment.K}));
        mapping.put(46, Arrays.asList(new Segment[]{Segment.DOT}));
        mapping.put(47, Arrays.asList(new Segment[]{Segment.I, Segment.N}));
        // 0 - 9
        mapping.put(48, Arrays.asList(new Segment[]{Segment.A1, Segment.A2, Segment.B, Segment.C, Segment.D2, Segment.D1, Segment.E, Segment.F, Segment.I, Segment.N}));
        mapping.put(49, Arrays.asList(new Segment[]{Segment.I, Segment.B, Segment.C}));
        mapping.put(50, Arrays.asList(new Segment[]{Segment.A1, Segment.A2, Segment.B, Segment.D2, Segment.D1, Segment.E, Segment.P, Segment.K}));
        mapping.put(51, Arrays.asList(new Segment[]{Segment.A1, Segment.A2, Segment.B, Segment.C, Segment.D2, Segment.D1, Segment.K}));
        mapping.put(52, Arrays.asList(new Segment[]{Segment.B, Segment.C, Segment.F, Segment.P, Segment.K}));
        mapping.put(53, Arrays.asList(new Segment[]{Segment.A1, Segment.A2, Segment.C, Segment.D2, Segment.D1, Segment.F, Segment.P, Segment.K}));
        mapping.put(54, Arrays.asList(new Segment[]{Segment.A1, Segment.A2, Segment.C, Segment.D2, Segment.D1, Segment.E, Segment.F, Segment.P, Segment.K}));
        mapping.put(55, Arrays.asList(new Segment[]{Segment.A1, Segment.A2, Segment.I, Segment.M}));
        mapping.put(56, Arrays.asList(new Segment[]{Segment.A1, Segment.A2, Segment.B, Segment.C, Segment.D2, Segment.D1, Segment.E, Segment.F, Segment.P, Segment.K}));
        mapping.put(57, Arrays.asList(new Segment[]{Segment.A1, Segment.A2, Segment.B, Segment.C, Segment.F, Segment.P, Segment.K}));
        // A - Z
        mapping.put(65, Arrays.asList(new Segment[]{Segment.A1, Segment.A2, Segment.B, Segment.C, Segment.E, Segment.F, Segment.P, Segment.K}));
        mapping.put(66, Arrays.asList(new Segment[]{Segment.A1, Segment.A2, Segment.B, Segment.C, Segment.D2, Segment.D1, Segment.H, Segment.M, Segment.K}));
        mapping.put(67, Arrays.asList(new Segment[]{Segment.A1, Segment.A2, Segment.D2, Segment.D1, Segment.E, Segment.F}));
        mapping.put(68, Arrays.asList(new Segment[]{Segment.A1, Segment.A2, Segment.B, Segment.C, Segment.D2, Segment.D1, Segment.H, Segment.M}));
        mapping.put(69, Arrays.asList(new Segment[]{Segment.A1, Segment.A2, Segment.D2, Segment.D1, Segment.E, Segment.F, Segment.P, Segment.K}));
        mapping.put(70, Arrays.asList(new Segment[]{Segment.A1, Segment.A2, Segment.E, Segment.F, Segment.P, Segment.K}));
        mapping.put(71, Arrays.asList(new Segment[]{Segment.A1, Segment.A2, Segment.C, Segment.D2, Segment.D1, Segment.E, Segment.F, Segment.K}));
        mapping.put(72, Arrays.asList(new Segment[]{Segment.B, Segment.C, Segment.E, Segment.F, Segment.P, Segment.K}));
        mapping.put(73, Arrays.asList(new Segment[]{Segment.A1, Segment.A2, Segment.D2, Segment.D1, Segment.M, Segment.H}));
        mapping.put(74, Arrays.asList(new Segment[]{Segment.B, Segment.C, Segment.D2, Segment.D1, Segment.E}));
        mapping.put(75, Arrays.asList(new Segment[]{Segment.E, Segment.F, Segment.I, Segment.L, Segment.P}));
        mapping.put(76, Arrays.asList(new Segment[]{Segment.D2, Segment.D1, Segment.E, Segment.F}));
        mapping.put(77, Arrays.asList(new Segment[]{Segment.B, Segment.C, Segment.E, Segment.F, Segment.G, Segment.I}));
        mapping.put(78, Arrays.asList(new Segment[]{Segment.B, Segment.C, Segment.E, Segment.F, Segment.G, Segment.L}));
        mapping.put(79, Arrays.asList(new Segment[]{Segment.A1, Segment.A2, Segment.B, Segment.C, Segment.D2, Segment.D1, Segment.E, Segment.F}));
        mapping.put(80, Arrays.asList(new Segment[]{Segment.A1, Segment.A2, Segment.B, Segment.E, Segment.F, Segment.P, Segment.K}));
        mapping.put(81, Arrays.asList(new Segment[]{Segment.A1, Segment.A2, Segment.B, Segment.C, Segment.D2, Segment.D1, Segment.E, Segment.F, Segment.L}));
        mapping.put(82, Arrays.asList(new Segment[]{Segment.A1, Segment.A2, Segment.B, Segment.E, Segment.F, Segment.P, Segment.K, Segment.L}));
        mapping.put(83, Arrays.asList(new Segment[]{Segment.A1, Segment.A2, Segment.C, Segment.D2, Segment.D1, Segment.G, Segment.K}));
        mapping.put(84, Arrays.asList(new Segment[]{Segment.A1, Segment.A2, Segment.H, Segment.M}));
        mapping.put(85, Arrays.asList(new Segment[]{Segment.B, Segment.C, Segment.D2, Segment.D1, Segment.E, Segment.F}));
        mapping.put(86, Arrays.asList(new Segment[]{Segment.E, Segment.F, Segment.I, Segment.N}));
        mapping.put(87, Arrays.asList(new Segment[]{Segment.B, Segment.C, Segment.E, Segment.F, Segment.L, Segment.N}));
        mapping.put(88, Arrays.asList(new Segment[]{Segment.G, Segment.I, Segment.L, Segment.N}));
        mapping.put(89, Arrays.asList(new Segment[]{Segment.G, Segment.I, Segment.M}));
        mapping.put(90, Arrays.asList(new Segment[]{Segment.A1, Segment.A2, Segment.D2, Segment.D1, Segment.I, Segment.N}));

        mapping.put(186, Arrays.asList(new Segment[] {Segment.A1, Segment.F, Segment.H, Segment.P}));
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
     *         A1A1A1 A2A2A2
     *        F G    H    I B
     *        F  G   H   I  B
     *        F   G  H  I   B
     *        F    G H I    B
     *         PPPPPP KKKKKK
     *        E    N M L    C
     *        E   N  M  L   C
     *        E  N   M   L  C
     *        E N    M    L C
     *         D1D1D1 D2D2D2
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
        return new SixteenSegmentSkin(this);
    }

    @Override public String getUserAgentStylesheet() {
        return getClass().getResource(getClass().getSimpleName().toLowerCase() + ".css").toExternalForm();
    }
}

