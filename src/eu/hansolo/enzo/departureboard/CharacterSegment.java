package eu.hansolo.enzo.departureboard;

import eu.hansolo.enzo.splitflap.SplitFlap;
import eu.hansolo.enzo.splitflap.SplitFlapBuilder;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;


/**
 * Created by
 * User: hansolo
 * Date: 15.04.13
 * Time: 08:14
 */
public class CharacterSegment extends HBox {
    private int            noOfCharacters;
    private SplitFlap[]    splitFlaps;
    private String         defaultText;
    private StringProperty text;


    public CharacterSegment() {
        this(5, Color.WHITE);
    }
    public CharacterSegment(final int NO_OF_CHARACTERS) {
        this(NO_OF_CHARACTERS, Color.WHITE);
    }
    public CharacterSegment(final int NO_OF_CHARACTERS, final Color TEXT_COLOR) {
        this(NO_OF_CHARACTERS, TEXT_COLOR, 100);
    }
    public CharacterSegment(final int NO_OF_CHARACTERS, final Color TEXT_COLOR, final long FLIP_TIME_IN_MS) {
        noOfCharacters   = NO_OF_CHARACTERS;
        splitFlaps = new SplitFlap[noOfCharacters];
        for (int i = 0 ; i < noOfCharacters ; i++) {
            SplitFlap sf = SplitFlapBuilder.create()
                                           .selection(SplitFlap.ALPHANUMERIC)
                                           .flipTime(FLIP_TIME_IN_MS)
                                           .text("")
                                           .textColor(TEXT_COLOR)
                                           .squareFlaps(true)
                                           .build();
            sf.setPrefSize(36, 60);
            splitFlaps[i] = sf;
        }
        defaultText = "";
        setSpacing(2);
        getChildren().setAll(splitFlaps);
    }


    public final String getText() {
        return null == text ? defaultText : text.get();
    }
    public final void setText(final String TEXT) {
        if (null == text) {
            defaultText = TEXT;
        } else {
            text.set(TEXT);
        }
        int length = TEXT.length();
        if (length > noOfCharacters) {
            length = noOfCharacters;
        }
        for (int i = 0 ; i < noOfCharacters ; i++) {
            if (i < length) {
                splitFlaps[i].setText(TEXT.charAt(i));
            } else {
                splitFlaps[i].setText(" ");
            }
        }
    }
    public final StringProperty textProperty() {
        if (null == text) {
            text = new SimpleStringProperty(this, "text", defaultText);
        }
        return text;
    }
}
