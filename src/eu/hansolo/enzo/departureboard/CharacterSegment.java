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


    // ******************** Constructors **************************************
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
