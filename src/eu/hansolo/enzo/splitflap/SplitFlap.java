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
    public static final String[] TIME_0_TO_5  = {"1", "2", "3", "4", "5", "0"};
    public static final String[] TIME_0_TO_9  = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
    public static final String[] NUMERIC      = {" ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
    public static final String[] ALPHANUMERIC = {" ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
                                                 "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
                                                 "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                                                 "W", "X", "Y", "Z"};
    public static final String[] EXTENDED     = {" ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
                                                 "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
                                                 "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                                                 "W", "X", "Y", "Z", "-", "/", ":", ",", ".", ";", "@",
                                                 "#", "+", "?", "!", "%", "$", "=", "<", ">"};

    private final FlipEvent              FLIP_FORWARD = new FlipEvent(this, null, FlipEvent.FLIP_FORWARD);
    private final FlipEvent              FIP_BACKWARD = new FlipEvent(this, null, FlipEvent.FLIP_BACKWARD);
    private boolean                      keepAspect;
    private double                       defaultFlipTime = 500;
    private DoubleProperty               flipTime;
    private boolean                      defaultWordMode = false;
    private BooleanProperty              wordMode;
    private boolean                      defaultDarkFixture = false;
    private BooleanProperty              darkFixture;
    private boolean                      defaultSquareFlaps = false;
    private BooleanProperty              squareFlaps;
    private Color                        defaultTextColor = Color.WHITE;
    private ObjectProperty<Color>        textColor;
    private String                       defaultText = "";
    private StringProperty               text;
    private ArrayList<String>            selectedSet;
    private String[]                     selection;
    private int                          currentSelectionIndex;
    private int                          nextSelectionIndex;
    private int                          previousSelectionIndex;


    // ******************** Constructors **************************************
    public SplitFlap() {
        this(EXTENDED, " ");
    }
    public SplitFlap(final String[] SELECTION, final String TEXT) {
        getStyleClass().add("split-flap");
        keepAspect             = true;
        selectedSet            = new ArrayList<>(64);
        selection              = SELECTION;
        currentSelectionIndex  = 0;
        nextSelectionIndex     = 1;
        previousSelectionIndex = selection.length - 1;
        defaultText            = TEXT;
        selectedSet.addAll(Arrays.asList(selection));
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

    public final boolean isWordMode() {
        return null == wordMode ? defaultWordMode : wordMode.get();
    }
    public final void setWordMode(final boolean WORD_MODE) {
        if (null == wordMode) {
            defaultWordMode = WORD_MODE;
        } else {
            wordMode.set(WORD_MODE);
        }
    }
    public final BooleanProperty wordModeProperty() {
        if (null == wordMode) {
            wordMode = new SimpleBooleanProperty(this, "wordMode", defaultWordMode);
        }
        return wordMode;
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

    public final boolean isSquareFlaps() {
        return null == squareFlaps ? defaultSquareFlaps : squareFlaps.get();
    }
    public final void setSquareFlaps(final boolean SQUARE_FLAPS) {
        if (null == squareFlaps) {
            defaultSquareFlaps = SQUARE_FLAPS;
        } else {
            squareFlaps.set(SQUARE_FLAPS);
        }
    }
    public final BooleanProperty squareFlapsProperty() {
        if (null == squareFlaps) {
            squareFlaps = new SimpleBooleanProperty(this, "squareFlaps", defaultSquareFlaps);
        }
        return squareFlaps;
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
    public final void setText(final char CHAR) {
        setText(Character.toString(CHAR));
    }
    public final void setText(final String TEXT) {
        if(!TEXT.isEmpty() && selectedSet.contains(TEXT)) {
            if (null == text) {
                defaultText = TEXT;
            } else {
                text.set(TEXT);
            }
            currentSelectionIndex = selectedSet.indexOf(TEXT);
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

    public final String[] getSelection() {
        return selection;
    }
    public final void setSelection(final String[] SELECTION) {
        selection = SELECTION;
        selectedSet.clear();
        selectedSet.addAll(Arrays.asList(selection));
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

