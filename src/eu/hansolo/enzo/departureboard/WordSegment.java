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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;


/**
 * Created by
 * User: hansolo
 * Date: 15.04.13
 * Time: 07:41
 */
public class WordSegment extends HBox {
    private Color                  textColor;
    private SplitFlap              splitFlap;
    private String                 defaultText;
    private StringProperty         text;
    private ObservableList<String> selection;


    // ******************** Constructors **************************************
    public WordSegment() {
        this(5, Color.WHITE);
    }
    public WordSegment(final int NO_OF_CHARACTERS) {
        this(NO_OF_CHARACTERS, Color.WHITE);
    }
    public WordSegment(final int NO_OF_CHARACTERS, final String[] ITEMS) {
        this(NO_OF_CHARACTERS, Color.WHITE, ITEMS);
    }
    public WordSegment(final int NO_OF_CHARACTERS, final Color TEXT_COLOR) {
        this(NO_OF_CHARACTERS, TEXT_COLOR, new String[]{"0", "1", "2"});
    }
    public WordSegment(final int NO_OF_CHARACTERS, final Color TEXT_COLOR, final String[] SELECTION) {
        selection = FXCollections.observableArrayList(SELECTION);
        textColor = TEXT_COLOR;
        splitFlap = SplitFlapBuilder.create()
                                    .selection(SELECTION)
                                    .flipTime(100)
                                    .text(selection.get(0))
                                    .textColor(textColor)
                                    .keepAspect(false)
                                    .squareFlaps(true)
                                    .build();
        splitFlap.setPrefSize(NO_OF_CHARACTERS * 25, 60);
        setSpacing(0);
        getChildren().setAll(splitFlap);
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
        splitFlap.setText(TEXT);
    }
    public final StringProperty textProperty() {
        if (null == text) {
            text = new SimpleStringProperty(this, "text", defaultText);
        }
        return text;
    }

    public final String[] getSelection() {
        return (String[]) selection.toArray();
    }
    public final void setSelection(final String[] ITEMS) {
        selection.setAll(ITEMS);
    }
}
