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

package eu.hansolo.enzo.splitflap;

import eu.hansolo.enzo.splitflap.skin.SplitFlapSkin;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.Control;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;


public class SplitFlap extends Control {
    public static enum CharacterSet {
        TIME_0_TO_5("0", "1", "2", "3", "4", "5"),
        TIME_0_TO_9("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"),
        NUMERIC(" ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"),
        ALPHANUMERIC(" ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
                     "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
                     "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                     "W", "X", "Y", "Z"),
        EXTENDED(" ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
                 "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
                 "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                 "W", "X", "Y", "Z", "-", "/", ":", ",", ".", ";", "@",
                 "#", "+", "?", "!", "%", "$", "=", "<", ">");

        public String[] selection;

        CharacterSet(final String... SELECTION) {
            selection = SELECTION;
        }

    }
    private final FlipEvent              FLIP_FORWARD = new FlipEvent(this, null, FlipEvent.FLIP_FORWARD);
    private final FlipEvent              FIP_BACKWARD = new FlipEvent(this, null, FlipEvent.FLIP_BACKWARD);
    private boolean                      keepAspect;
    private double                       defaultFlipTime = 500;
    private DoubleProperty               flipTime;
    private boolean                      defaultDarkFixture;
    private BooleanProperty              darkFixture;
    private Color                        defaultTextColor = Color.WHITE;
    private ObjectProperty<Color>        textColor;
    private String                       defaultText = "";
    private StringProperty               text;
    private ArrayList<String>            selectedSet;
    private ObjectProperty<CharacterSet> characterSet;
    private int                          currentSelectionIndex;
    private int                          nextSelectionIndex;
    private int                          previousSelectionIndex;


    // ******************** Constructors **************************************
    public SplitFlap() {
        this(CharacterSet.EXTENDED, " ");
    }

    public SplitFlap(final CharacterSet CHARACTER_SET, final String TEXT) {
        getStyleClass().add("split-flap");
        keepAspect             = true;
        selectedSet            = new ArrayList<>(64);
        characterSet           = new SimpleObjectProperty<>(CHARACTER_SET);
        selectedSet.addAll(Arrays.asList(characterSet.get().selection));
        currentSelectionIndex  = 0;
        nextSelectionIndex     = 1;
        previousSelectionIndex = characterSet.get().selection.length - 1;
        defaultText            = TEXT;
    }


    // ******************** Event handling ************************************
    public final ObjectProperty<EventHandler<FlipEvent>> onFlipForwardProperty() { return onFlipForward; }
    public final void setOnFlipForward(EventHandler<FlipEvent> value) { onFlipForwardProperty().set(value); }
    public final EventHandler<FlipEvent> getOnFlipForward() { return onFlipForwardProperty().get(); }
    private ObjectProperty<EventHandler<FlipEvent>> onFlipForward = new ObjectPropertyBase<EventHandler<FlipEvent>>() {
        @Override public Object getBean() { return this; }
        @Override public String getName() { return "onFlipForward";}
    };

    public final ObjectProperty<EventHandler<FlipEvent>> onFlipBackwardProperty() { return onFlipBackward; }
    public final void setOnFlipBackward(EventHandler<FlipEvent> value) { onFlipBackwardProperty().set(value); }
    public final EventHandler<FlipEvent> getOnFlipBackward() { return onFlipBackwardProperty().get(); }
    private ObjectProperty<EventHandler<FlipEvent>> onFlipBackward = new ObjectPropertyBase<EventHandler<FlipEvent>>() {
        @Override public Object getBean() { return this; }
        @Override public String getName() { return "onFlipBackward";}
    };

    public void fireFlipEvent(final FlipEvent EVENT) {
        final EventHandler<FlipEvent> HANDLER;
        final EventType               TYPE    = EVENT.getEventType();
        if (FlipEvent.FLIP_FORWARD == TYPE) {
            HANDLER = getOnFlipForward();
        } else if (FlipEvent.FLIP_BACKWARD == TYPE) {
            HANDLER = getOnFlipBackward();
        } else {
            HANDLER = null;
        }

        if (HANDLER != null) {
            HANDLER.handle(EVENT);
        }
    }


    // ******************** Methods *******************************************
    public final boolean isKeepAspect() {
        return keepAspect;
    }
    public final void setKeepAspect(final boolean KEEP_ASPECT) {
        keepAspect = KEEP_ASPECT;
    }

    public final double getFlipTime() {
        return null == flipTime ? defaultFlipTime : flipTime.get();
    }
    public final void setFlipTime(final double FLIP_TIME) {
        if (null == flipTime) {
            defaultFlipTime = FLIP_TIME;
        } else {
            flipTime.set(FLIP_TIME);
        }
    }
    public final DoubleProperty flipTimeProperty() {
        if (null == flipTime) {
            flipTime = new SimpleDoubleProperty(this, "flipTime", defaultFlipTime);
        }
        return flipTime;
    }

    public final boolean isDarkFixture() {
        return null == darkFixture ? defaultDarkFixture : darkFixture.get();
    }
    public final void setDarkFixture(final boolean DARK_FIXTURE) {
        if (null == darkFixture) {
            defaultDarkFixture = DARK_FIXTURE;
        } else {
            darkFixture.set(DARK_FIXTURE);
        }
    }
    public final BooleanProperty darkFixtureProperty() {
        if (null == darkFixture) {
            darkFixture = new SimpleBooleanProperty(this, "darkFixture", defaultDarkFixture);
        }
        return darkFixture;
    }

    public final Color getTextColor() {
        return null == textColor ? defaultTextColor : textColor.get();
    }
    public final void setTextColor(final Color TEXT_COLOR) {
        if (null == textColor) {
            defaultTextColor = TEXT_COLOR;
        } else {
            textColor.set(TEXT_COLOR);
        }
    }
    public final ObjectProperty<Color> textColorProperty() {
        if (null == textColor) {
            textColor = new SimpleObjectProperty<>(this, "textColor", defaultTextColor);
        }
        return textColor;
    }

    public final String getText() {
        return null == text ? defaultText : text.get();
    }
    public final void setText(final String TEXT) {
        if(!TEXT.isEmpty() || selectedSet.contains(TEXT.substring(0,1))) {
            if (null == text) {
                defaultText = TEXT.substring(0, 1);
            } else {
                text.set(TEXT.substring(0,1));
            }
            currentSelectionIndex = selectedSet.indexOf(TEXT.substring(0,1));
            nextSelectionIndex    = currentSelectionIndex + 1 > selectedSet.size() ? 0 : currentSelectionIndex + 1;
        } else {
            if (null == text) {
                defaultText = selectedSet.get(0);
            } else {
                text.set(selectedSet.get(0));
            }
            currentSelectionIndex = 0;
            nextSelectionIndex    = currentSelectionIndex + 1 > selectedSet.size() ? 0 : currentSelectionIndex + 1;
        }
    }
    public final StringProperty textProperty() {
        if (null == text) {
            text = new SimpleStringProperty(this, "text", defaultText);
        }
        return text;
    }
    public final String getNextText() {
        return selectedSet.get(nextSelectionIndex);
    }
    public final String getPreviousText() {
        return selectedSet.get(previousSelectionIndex);
    }

    public final CharacterSet getCharacterSet() {
        return characterSet.get();
    }
    public final void setCharacterSet(final CharacterSet CHARACTER_SET) {
        characterSet.set(CHARACTER_SET);
        selectedSet.clear();
        selectedSet.addAll(Arrays.asList(characterSet.get().selection));
    }
    public final ObjectProperty<CharacterSet> characterSetProperty() {
        return characterSet;
    }

    public final ArrayList<String> getSelectedSet() {
        return selectedSet;
    }

    public final void flipForward() {
        previousSelectionIndex = currentSelectionIndex;
        currentSelectionIndex++;
        if (currentSelectionIndex >= selectedSet.size()) {
            currentSelectionIndex = 0;
        }
        nextSelectionIndex = currentSelectionIndex + 1;
        if (nextSelectionIndex >= selectedSet.size()) {
            nextSelectionIndex = 0;
        }
        setText(selectedSet.get(currentSelectionIndex));
        fireFlipEvent(FLIP_FORWARD);
    }
    public final void flipBackward() {
        fireFlipEvent(FIP_BACKWARD);
        ((SplitFlapSkin) getSkin()).flipBackward();
    }

    @Override public boolean isResizable() {
        return true;
    }
    

    // ******************** Style related *************************************
    @Override protected String getUserAgentStylesheet() {
        return getClass().getResource(getClass().getSimpleName().toLowerCase() + ".css").toExternalForm();
    }
}

