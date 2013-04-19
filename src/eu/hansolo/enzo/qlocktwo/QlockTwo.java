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

package eu.hansolo.enzo.qlocktwo;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Control;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class QlockTwo extends Control {
    public enum Language {
        GERMAN(new String[]{"NULL", "EINS", "ZWEI", "DREI", "VIER", "FÜNF", "SECHS", "SIEBEN", "ACHT", "NEUN", "ZEHN", "ELF", "ZWÖLF"}),
        ENGLISH(new String[]{"ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN", "ELEVEN", "TWELVE"}),
        DUTCH(new String[]{"NUL", "EEN", "TWEE", "DRIE", "VIER", "VIJF", "ZES", "ZEVEN", "ACHT", "NEGEN", "TIEN", "ELF", "TWAALF"}),
        FRENCH(new String[]{"ZERO", "UNE", "DEUX", "TROIS", "QUATRE", "CINQ", "SIX", "SEPT", "HUIT", "NEUF", "DIX", "ONZE", "DOUZE"}),
        SPANISH(new String[]{"CERO", "UNA", "DOS", "TRES", "CUATRO", "CINCO", "SEIS", "SIETE", "OCHO", "NUEVE", "DIEZ", "ONCE", "DOCE"});


        private final Map<Integer, String> LOOKUP = new HashMap<>();

        private Language(final String[] NUMBERS) {
            int count = 0;
            for(String number : NUMBERS) {
                LOOKUP.put(count, number);
                count++;
            }
        }

        public Map<Integer, String> getLookup() {
            return LOOKUP;
        }
    }
    public enum QlockColor {
        BLACK_ICE_TEA("black-ice-tea"),
        CHERRY_CAKE("cherry-cake"),
        VANILLA_SUGAR("vanilla-sugar"),
        FROZEN_BLACKBERRY("frozen-blackberry"),
        LIME_JUICE("lime-juice"),
        DARK_CHOCOLATE("dark-chocolate"),
        BLUE_CANDY("blue-candy"),
        STAINLESS_STEEL("stainless-steel");

        public final String STYLE_CLASS;

        private QlockColor(final String STYLE_CLASS) {
            this.STYLE_CLASS = STYLE_CLASS;
        }
    }
    public enum SecondsLeft {
        ZERO(new HashMap<Integer, List<Integer>>() {
            {
                put(2, Arrays.asList(  1,2,3));
                put(3, Arrays.asList(0,      4));
                put(4, Arrays.asList(0,      4));
                put(5, Arrays.asList(0,      4));
                put(6, Arrays.asList(0,      4));
                put(7, Arrays.asList(0,      4));
                put(8, Arrays.asList(  1,2,3));
            };
        }),
        ONE(new HashMap<Integer, List<Integer>>() {
            {
                put(2, Arrays.asList(  2));
                put(3, Arrays.asList(1,2));
                put(4, Arrays.asList(  2));
                put(5, Arrays.asList(  2));
                put(6, Arrays.asList(  2));
                put(7, Arrays.asList(  2));
                put(8, Arrays.asList(1,2,3));
            };
        }),
        TWO(new HashMap<Integer, List<Integer>>() {
            {
                put(2, Arrays.asList(  1,2,3));
                put(3, Arrays.asList(0,      4));
                put(4, Arrays.asList(        4));
                put(5, Arrays.asList(      3));
                put(6, Arrays.asList(    2));
                put(7, Arrays.asList(  1));
                put(8, Arrays.asList(0,1,2,3,4));
            };
        }),
        THREE(new HashMap<Integer, List<Integer>>() {
            {
                put(2, Arrays.asList(0,1,2,3,4));
                put(3, Arrays.asList(      3));
                put(4, Arrays.asList(    2));
                put(5, Arrays.asList(      3));
                put(6, Arrays.asList(        4));
                put(7, Arrays.asList(0,      4));
                put(8, Arrays.asList(  1,2,3));
            };
        }),
        FOUR(new HashMap<Integer, List<Integer>>() {
            {
                put(2, Arrays.asList(      3));
                put(3, Arrays.asList(    2,3));
                put(4, Arrays.asList(  1,  3));
                put(5, Arrays.asList(0,    3));
                put(6, Arrays.asList(0,1,2,3,4));
                put(7, Arrays.asList(      3));
                put(8, Arrays.asList(      3));
            };
        }),
        FIVE(new HashMap<Integer, List<Integer>>() {
            {
                put(2, Arrays.asList(0,1,2,3,4));
                put(3, Arrays.asList(0));
                put(4, Arrays.asList(0,1,2,3));
                put(5, Arrays.asList(        4));
                put(6, Arrays.asList(        4));
                put(7, Arrays.asList(0,      4));
                put(8, Arrays.asList(  1,2,3));
            };
        }),
        SIX(new HashMap<Integer, List<Integer>>() {
            {
                put(2, Arrays.asList(    2,3));
                put(3, Arrays.asList(  1));
                put(4, Arrays.asList(0));
                put(5, Arrays.asList(0,1,2,3));
                put(6, Arrays.asList(0,      4));
                put(7, Arrays.asList(0,      4));
                put(8, Arrays.asList(  1,2,3));
            };
        }),
        SEVEN(new HashMap<Integer, List<Integer>>() {
            {
                put(2, Arrays.asList(0,1,2,3,4));
                put(3, Arrays.asList(        4));
                put(4, Arrays.asList(      3));
                put(5, Arrays.asList(    2));
                put(6, Arrays.asList(  1));
                put(7, Arrays.asList(  1));
                put(8, Arrays.asList(  1));
            };
        }),
        EIGHT(new HashMap<Integer, List<Integer>>() {
            {
                put(2, Arrays.asList(  1,2,3));
                put(3, Arrays.asList(0,      4));
                put(4, Arrays.asList(0,      4));
                put(5, Arrays.asList(  1,2,3));
                put(6, Arrays.asList(0,      4));
                put(7, Arrays.asList(0,      4));
                put(8, Arrays.asList(  1,2,3));
            };
        }),
        NINE(new HashMap<Integer, List<Integer>>() {
            {
                put(2, Arrays.asList(  1,2,3));
                put(3, Arrays.asList(0,      4));
                put(4, Arrays.asList(0,      4));
                put(5, Arrays.asList(  1,2,3,4));
                put(6, Arrays.asList(        4));
                put(7, Arrays.asList(      3));
                put(8, Arrays.asList(  1,2));
            };
        });

        public Map<Integer, List<Integer>> dots;

        private SecondsLeft(final HashMap<Integer, List<Integer>> DOTS) {
            dots = DOTS;
        }
    }
    public enum SecondsRight {
        ZERO(new HashMap<Integer,List<Integer>>() {
            {
                put(2, Arrays.asList(  7,8,9));
                put(3, Arrays.asList(6,      10));
                put(4, Arrays.asList(6,      10));
                put(5, Arrays.asList(6,      10));
                put(6, Arrays.asList(6,      10));
                put(7, Arrays.asList(6,      10));
                put(8, Arrays.asList(  7,8,9));
            };
        }),
        ONE(new HashMap<Integer,List<Integer>>() {
            {
                put(2, Arrays.asList(  8));
                put(3, Arrays.asList(7,8));
                put(4, Arrays.asList(  8));
                put(5, Arrays.asList(  8));
                put(6, Arrays.asList(  8));
                put(7, Arrays.asList(  8));
                put(8, Arrays.asList(7,8,9));
            };
        }),
        TWO(new HashMap<Integer, List<Integer>>() {
            {
                put(2, Arrays.asList(  7,8,9));
                put(3, Arrays.asList(6,      10));
                put(4, Arrays.asList(        10));
                put(5, Arrays.asList(      9));
                put(6, Arrays.asList(    8));
                put(7, Arrays.asList(  7));
                put(8, Arrays.asList(6,7,8,9,10));
            };
        }),
        THREE(new HashMap<Integer, List<Integer>>() {
            {
                put(2, Arrays.asList(6,7,8,9,10));
                put(3, Arrays.asList(      9));
                put(4, Arrays.asList(    8));
                put(5, Arrays.asList(      9));
                put(6, Arrays.asList(        10));
                put(7, Arrays.asList(6,      10));
                put(8, Arrays.asList(  7,8,9));
            };
        }),
        FOUR(new HashMap<Integer, List<Integer>>() {
            {
                put(2, Arrays.asList(      9));
                put(3, Arrays.asList(    8,9));
                put(4, Arrays.asList(  7,  9));
                put(5, Arrays.asList(6,    9));
                put(6, Arrays.asList(6,7,8,9,10));
                put(7, Arrays.asList(      9));
                put(8, Arrays.asList(      9));
            };
        }),
        FIVE(new HashMap<Integer, List<Integer>>() {
            {
                put(2, Arrays.asList(6,7,8,9,10));
                put(3, Arrays.asList(6));
                put(4, Arrays.asList(6,7,8,9));
                put(5, Arrays.asList(        10));
                put(6, Arrays.asList(        10));
                put(7, Arrays.asList(6,      10));
                put(8, Arrays.asList(  7,8,9));
            };
        }),
        SIX(new HashMap<Integer, List<Integer>>() {
            {
                put(2, Arrays.asList(    8,9));
                put(3, Arrays.asList(  7));
                put(4, Arrays.asList(6));
                put(5, Arrays.asList(6,7,8,9));
                put(6, Arrays.asList(6,      10));
                put(7, Arrays.asList(6,      10));
                put(8, Arrays.asList(  7,8,9));
            };
        }),
        SEVEN(new HashMap<Integer, List<Integer>>() {
            {
                put(2, Arrays.asList(6,7,8,9,10));
                put(3, Arrays.asList(        10));
                put(4, Arrays.asList(      9));
                put(5, Arrays.asList(    8));
                put(6, Arrays.asList(  7));
                put(7, Arrays.asList(  7));
                put(8, Arrays.asList(  7));
            };
        }),
        EIGHT(new HashMap<Integer, List<Integer>>() {
            {
                put(2, Arrays.asList(  7,8,9));
                put(3, Arrays.asList(6,      10));
                put(4, Arrays.asList(6,      10));
                put(5, Arrays.asList(  7,8,9));
                put(6, Arrays.asList(6,      10));
                put(7, Arrays.asList(6,      10));
                put(8, Arrays.asList(  7,8,9));
            };
        }),
        NINE(new HashMap<Integer, List<Integer>>() {
            {
                put(2, Arrays.asList(  7,8,9));
                put(3, Arrays.asList(6,      10));
                put(4, Arrays.asList(6,      10));
                put(5, Arrays.asList(  7,8,9,10));
                put(6, Arrays.asList(        10));
                put(7, Arrays.asList(      9));
                put(8, Arrays.asList(  7,8));
            };
        });

        public Map<Integer, List<Integer>> dots;

        private SecondsRight(final HashMap<Integer, List<Integer>> DOTS) {
            dots = DOTS;
        }
    }
    private QlockColor                 _color;
    private ObjectProperty<QlockColor> color;
    private Language                   _language;
    private ObjectProperty<Language>   language;
    private Qlock                      qlock;
    private boolean                    _secondsMode;
    private BooleanProperty            secondsMode;
    private boolean                    _highlightVisible;
    private BooleanProperty            highlightVisible;


    // ******************** Constructors **************************************
    public QlockTwo() {
        getStyleClass().add("qlocktwo");
        _color            = QlockColor.BLACK_ICE_TEA;
        qlock             = new QlockGerman();
        _language         = qlock.getLanguage();
        _secondsMode      = false;
        _highlightVisible = true;
    }


    // ******************** Methods *******************************************
    @Override public boolean isResizable() {
        return true;
    }

    public final Qlock getQlock() {
        return qlock;
    }

    public final QlockColor getColor() {
        return null == color ? _color : color.get();
    }
    public final void setColor(final QlockColor COLOR) {
        if (null == color) {
            _color = COLOR;
        } else {
            color.set(COLOR);
        }
    }
    public final ObjectProperty<QlockColor> colorProperty() {
        if (null == color) {
            color = new SimpleObjectProperty<>(this, "color", _color);
        }
        return color;
    }

    public final Language getLanguage() {
        return null == language ? _language : language.get();
    }
    public final void setLanguage(final Language LANGUAGE) {
        switch(LANGUAGE) {
            case GERMAN:
                qlock = new QlockGerman();
                break;
            case ENGLISH:
                qlock = new QlockEnglish();
                break;
            case FRENCH:
                qlock = new QlockFrench();
                break;
            case DUTCH:
                qlock = new QlockDutch();
                break;
            case SPANISH:
                qlock = new QlockSpanish();
                break;
        }
        if (null == language) {
            _language = LANGUAGE;
        } else {
            language.set(LANGUAGE);
        }
    }
    public final ObjectProperty<Language> languageProperty() {
        if (null == language) {
            language = new SimpleObjectProperty<>(this, "language", _language);
        }
        return language;
    }

    public final boolean isSecondsMode() {
        return null == secondsMode ? _secondsMode : secondsMode.get();
    }
    public final void setSecondsMode(final boolean SECONDS_MODE) {
        if (null == secondsMode) {
            _secondsMode = SECONDS_MODE;
        } else {
            secondsMode.set(SECONDS_MODE);
        }
    }
    public final BooleanProperty secondsModeProperty() {
        if (null == secondsMode) {
            secondsMode = new SimpleBooleanProperty(this, "secondsMode", _secondsMode);
        }
        return secondsMode;
    }

    public final boolean isHighlightVisible() {
        return null == highlightVisible ? _highlightVisible : highlightVisible.get();
    }
    public final void setHighlightVisible(final boolean HIGHLIGHT_VISIBLE) {
        if (null == highlightVisible) {
            _highlightVisible = HIGHLIGHT_VISIBLE;
        } else {
            highlightVisible.set(HIGHLIGHT_VISIBLE);
        }
    }
    public final BooleanProperty highlightVisibleProperty() {
        if (null == highlightVisible) {
            highlightVisible = new SimpleBooleanProperty(this, "highlightVisible", _highlightVisible);
        }
        return highlightVisible;
    }


    // ******************** Style related *************************************
    @Override protected String getUserAgentStylesheet() {
        return getClass().getResource(getClass().getSimpleName().toLowerCase() + ".css").toExternalForm();
    }
}
