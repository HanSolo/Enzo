package eu.hansolo.enzo.splitflap;

import eu.hansolo.enzo.splitflap.skin.SplitFlapSkin;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
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
    private static final FlipEvent       FLIP_FORWARD = new FlipEvent(FlipEvent.FLIP_FORWARD);
    private static final FlipEvent       FIP_BACKWARD = new FlipEvent(FlipEvent.FLIP_BACKWARD);
    private boolean                      keepAspect;
    private double                       defaultFlipTime = 500;
    private DoubleProperty               flipTime;
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
    public final void setOnFlipForward(EventHandler<FlipEvent> value) { addEventHandler(FlipEvent.FLIP_FORWARD, value); }
    public final void setOnFlipBackward(EventHandler<FlipEvent> value) { addEventHandler(FlipEvent.FLIP_BACKWARD, value); }
    public final void setOnFlipFinished(EventHandler<FlipEvent> value) { addEventHandler(FlipEvent.FLIP_FINISHED, value); }


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
        fireEvent(FLIP_FORWARD);
    }
    public final void flipBackward() {
        fireEvent(FIP_BACKWARD);
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

