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
