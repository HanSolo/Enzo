package eu.hansolo.enzo.departureboard;

import eu.hansolo.enzo.imgsplitflap.SplitFlap;
import eu.hansolo.enzo.imgsplitflap.SplitFlapBuilder;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;


/**
 * Created by
 * User: hansolo
 * Date: 18.04.13
 * Time: 10:53
 */
public class SimpleCharacterSegment extends HBox {
    private int            noOfCharacters;
    private SplitFlap[]    splitFlaps;
    private String         defaultText;
    private StringProperty text;


    // ******************** Constructors **************************************
    public SimpleCharacterSegment() {
        this(5, Color.WHITE);
    }
    public SimpleCharacterSegment(final int NO_OF_CHARACTERS) {
        this(NO_OF_CHARACTERS, Color.WHITE);
    }
    public SimpleCharacterSegment(final int NO_OF_CHARACTERS, final Color TEXT_COLOR) {
        this(NO_OF_CHARACTERS, TEXT_COLOR, 100);
    }
    public SimpleCharacterSegment(final int NO_OF_CHARACTERS, final Color TEXT_COLOR, final long FLIP_TIME_IN_MS) {
        noOfCharacters   = NO_OF_CHARACTERS;
        splitFlaps = new SplitFlap[noOfCharacters];
        for (int i = 0 ; i < noOfCharacters ; i++) {
            SplitFlap sf = SplitFlapBuilder.create()
                                           .selection(SplitFlap.ALPHANUMERIC)
                                           .flipTime(FLIP_TIME_IN_MS)
                                           .text("")
                                           .textColor(TEXT_COLOR)
                                           .build();
            sf.setPrefSize(35, 60);
            splitFlaps[i] = sf;
        }
        defaultText = "";
        setSpacing(2);
        getChildren().setAll(splitFlaps);
    }


    // ******************** Methods *******************************************
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