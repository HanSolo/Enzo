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

package eu.langhammer.enzo2.splitflap;

import eu.langhammer.enzo2.splitflap.skin.SplitFlapSkin;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;


public class SplitFlap extends Control {
    public static final String[] TIME_0_TO_5  = {"1", "2", "3", "4", "5", "0"};
    public static final String[] TIME_0_TO_9  = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
    public static final String[] NUMERIC      = {" ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
    public static final String[] ALPHANUMERIC = {" ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
        "A", "B", "C", "D", "E", "F", "G", "H", "I", "I", "K",
        "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
        "W", "X", "Y", "Z"};
    public static final String[] ALPHA        = {" ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "I",
        "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
        "V", "W", "X", "Y", "Z"};
    public static final String[] EXTENDED     = {" ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
        "A", "B", "C", "D", "E", "F", "G", "H", "I", "I", "K",
        "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
        "W", "X", "Y", "Z", "-", "/", ":", ",", "", ";", "@",
        "#", "+", "?", "!", "%", "$", "=", "<", ">"};

    private final FlipEvent FLIP_FORWARD = new FlipEvent(this, null, FlipEvent.FLIP_FORWARD);
    private final FlipEvent FLIP_BACKWARD = new FlipEvent(this, null, FlipEvent.FLIP_BACKWARD);
    private boolean keepAspect;
    private double _flipTime = 500;
    private DoubleProperty flipTime;
    private boolean _wordMode = false;
    private BooleanProperty wordMode;
    private boolean _withFixture = true;
    private BooleanProperty withFixture;
    private boolean _darkFixture = false;
    private BooleanProperty darkFixture;
    private boolean _squareFlaps = false;
    private BooleanProperty squareFlaps;
    private Color _flapColor = Color.rgb(59, 58, 53);
    private ObjectProperty<Color> flapColor;
    private Color _textColor = Color.WHITE;
    private ObjectProperty<Color> textColor;
    private String _text = "";
    private StringProperty    text;
    private ArrayList<String> selectedSet;
    private String[]          selection;
    private int               currentSelectionIndex;
    private int               nextSelectionIndex;
    private int               previousSelectionIndex;


    // ******************** Constructors **************************************
    public SplitFlap() {
        this(EXTENDED, " ");
    }

    public SplitFlap(final String[] SELECTION, final String TEXT) {
        getStyleClass().add("split-flap");
        keepAspect = true;
        selectedSet = new ArrayList<>(64);
        selection = SELECTION;
        currentSelectionIndex = 0;
        nextSelectionIndex = 1;
        previousSelectionIndex = selection.length - 1;
        _text = TEXT;
        selectedSet.addAll(Arrays.asList(selection));
    }


    // ******************** Methods *******************************************
    public final boolean isKeepAspect() {
        return keepAspect;
    }

    public final void setKeepAspect(final boolean KEEP_ASPECT) {
        keepAspect = KEEP_ASPECT;
    }

    public final double getFlipTime() {
        return null == flipTime ? _flipTime : flipTime.get();
    }

    public final void setFlipTime(final double FLIP_TIME) {
        if (null == flipTime) {
            _flipTime = FLIP_TIME;
        } else {
            flipTime.set(FLIP_TIME);
        }
    }

    public final DoubleProperty flipTimeProperty() {
        if (null == flipTime) {
            flipTime = new SimpleDoubleProperty(this, "flipTime", _flipTime);
        }
        return flipTime;
    }

    public final boolean isWordMode() {
        return null == wordMode ? _wordMode : wordMode.get();
    }
    public final void setWordMode(final boolean WORD_MODE) {
        if (null == wordMode) {
            _wordMode = WORD_MODE;
        } else {
            wordMode.set(WORD_MODE);
        }
    }
    public final BooleanProperty wordModeProperty() {
        if (null == wordMode) {
            wordMode = new SimpleBooleanProperty(this, "wordMode", _wordMode);
        }
        return wordMode;
    }

    public final boolean isWithFixture() {
        return null == withFixture ? _withFixture : withFixture.get();
    }
    public final void setWithFixture(final boolean WITH_FIXTURE) {
        if (null == withFixture) {
            _withFixture = WITH_FIXTURE;
        } else {
            withFixture.set(WITH_FIXTURE);
        }
    }
    public final BooleanProperty withFixtureProperty() {
        if (null == withFixture) {
            withFixture = new SimpleBooleanProperty(this, "withFixture", _withFixture);
        }
        return withFixture;
    }

    public final boolean isDarkFixture() {
        return null == darkFixture ? _darkFixture : darkFixture.get();
    }
    public final void setDarkFixture(final boolean DARK_FIXTURE) {
        if (null == darkFixture) {
            _darkFixture = DARK_FIXTURE;
        } else {
            darkFixture.set(DARK_FIXTURE);
        }
    }
    public final BooleanProperty darkFixtureProperty() {
        if (null == darkFixture) {
            darkFixture = new SimpleBooleanProperty(this, "darkFixture", _darkFixture);
        }
        return darkFixture;
    }

    public final boolean isSquareFlaps() {
        return null == squareFlaps ? _squareFlaps : squareFlaps.get();
    }
    public final void setSquareFlaps(final boolean SQUARE_FLAPS) {
        if (null == squareFlaps) {
            _squareFlaps = SQUARE_FLAPS;
        } else {
            squareFlaps.set(SQUARE_FLAPS);
        }
    }
    public final BooleanProperty squareFlapsProperty() {
        if (null == squareFlaps) {
            squareFlaps = new SimpleBooleanProperty(this, "squareFlaps", _squareFlaps);
        }
        return squareFlaps;
    }

    public final Color getFlapColor() {
        return null == flapColor ? _flapColor : flapColor.get();
    }
    public final void setFlapColor(final Color FLAP_COLOR) {
        if (null == flapColor) {
            _flapColor = FLAP_COLOR;
        } else {
            flapColor.set(FLAP_COLOR);
        }
    }
    public final ObjectProperty<Color> flapColorProperty() {
        if (null == flapColor) {
            flapColor = new SimpleObjectProperty<>(this, "flapColor", _flapColor);
        }
        return flapColor;
    }

    public final Color getTextColor() {
        return null == textColor ? _textColor : textColor.get();
    }
    public final void setTextColor(final Color TEXT_COLOR) {
        if (null == textColor) {
            _textColor = TEXT_COLOR;
        } else {
            textColor.set(TEXT_COLOR);
        }
    }
    public final ObjectProperty<Color> textColorProperty() {
        if (null == textColor) {
            textColor = new SimpleObjectProperty<>(this, "textColor", _textColor);
        }
        return textColor;
    }

    public final String getText() {
        return null == text ? _text : text.get();
    }
    public final void setText(final char CHAR) {
        setText(Character.toString(CHAR));
    }
    public final void setText(final String TEXT) {
        if(!TEXT.isEmpty() && selectedSet.contains(TEXT)) {
            if (null == text) {
                _text = TEXT;
            } else {
                text.set(TEXT);
            }
            currentSelectionIndex = selectedSet.indexOf(TEXT);
            nextSelectionIndex    = currentSelectionIndex + 1 > selectedSet.size() ? 0 : currentSelectionIndex + 1;
        } else {
            if (null == text) {
                _text = selectedSet.get(0);
            } else {
                text.set(selectedSet.get(0));
            }
            currentSelectionIndex = 0;
            nextSelectionIndex    = currentSelectionIndex + 1 > selectedSet.size() ? 0 : currentSelectionIndex + 1;
        }
    }
    public final StringProperty textProperty() {
        if (null == text) {
            text = new SimpleStringProperty(this, "text", _text);
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
        fireEvent(FLIP_FORWARD);
    }
    public final void flipBackward() {
        fireEvent(FLIP_BACKWARD);
        ((SplitFlapSkin) getSkin()).flipBackward();
    }

    @Override public boolean isResizable() {
        return true;
    }
    

    // ******************** Style related *************************************
    @Override protected Skin createDefaultSkin() {
        return new SplitFlapSkin(this);
    }

    @Override public String getUserAgentStylesheet() {
        return getClass().getResource(getClass().getSimpleName().toLowerCase() + ".css").toExternalForm();
    }
}
