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
