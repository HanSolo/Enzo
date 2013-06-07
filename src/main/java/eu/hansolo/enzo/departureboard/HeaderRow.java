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

import javafx.geometry.VPos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


/**
 * Created by
 * User: hansolo
 * Date: 15.04.13
 * Time: 08:24
 */
public class HeaderRow extends HBox {

    // ******************** Constructors **************************************
    public HeaderRow() {
        Font font = Font.font("sans-serif", 20);

        Rectangle spacer1 = new Rectangle(0, 0, 60, 10);
        spacer1.setOpacity(0.0);

        Text flightLabel = new Text("Flight");
        flightLabel.setFill(Color.WHITE);
        flightLabel.setTextAlignment(TextAlignment.LEFT);
        flightLabel.setTextOrigin(VPos.BOTTOM);
        flightLabel.setFont(font);

        Rectangle spacer2 = new Rectangle(0, 0, 188, 10);
        spacer2.setOpacity(0.0);

        Text destinationLabel = new Text("Destination");
        destinationLabel.setFill(Color.WHITE);
        destinationLabel.setTextAlignment(TextAlignment.LEFT);
        destinationLabel.setTextOrigin(VPos.BOTTOM);
        destinationLabel.setFont(font);

        Rectangle spacer3 = new Rectangle(0, 0, 260, 10);
        spacer3.setOpacity(0.0);

        Text timeLabel = new Text("Time");
        timeLabel.setFill(Color.WHITE);
        timeLabel.setTextAlignment(TextAlignment.LEFT);
        timeLabel.setTextOrigin(VPos.BOTTOM);
        timeLabel.setFont(font);

        Rectangle spacer4 = new Rectangle(0, 0, 125, 10);
        spacer4.setOpacity(0.0);

        Text gateLabel = new Text("Gate");
        gateLabel.setFill(Color.WHITE);
        gateLabel.setTextAlignment(TextAlignment.LEFT);
        gateLabel.setTextOrigin(VPos.BOTTOM);
        gateLabel.setFont(font);

        getChildren().addAll(spacer1, flightLabel, spacer2, destinationLabel, spacer3, timeLabel, spacer4, gateLabel);
    }
}
