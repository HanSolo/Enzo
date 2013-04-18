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
 * Date: 17.04.13
 * Time: 15:33
 */
public class SimpleHeaderRow extends HBox {

    // ******************** Constructors **************************************
    public SimpleHeaderRow() {
        Font font = Font.font("sans-serif", 20);

        Rectangle spacer1 = new Rectangle(0, 0, 60, 10);
        spacer1.setOpacity(0.0);

        Text timeLabel = new Text("Zeit");
        timeLabel.setFill(Color.WHITE);
        timeLabel.setTextAlignment(TextAlignment.LEFT);
        timeLabel.setTextOrigin(VPos.BOTTOM);
        timeLabel.setFont(font);

        Rectangle spacer2 = new Rectangle(0, 0, 105, 10);
        spacer2.setOpacity(0.0);

        Text destinationLabel = new Text("Ziel");
        destinationLabel.setFill(Color.WHITE);
        destinationLabel.setTextAlignment(TextAlignment.LEFT);
        destinationLabel.setTextOrigin(VPos.BOTTOM);
        destinationLabel.setFont(font);

        Rectangle spacer3 = new Rectangle(0, 0, 583, 10);
        spacer3.setOpacity(0.0);

        Text trackLabel = new Text("Gleis");
        trackLabel.setFill(Color.WHITE);
        trackLabel.setTextAlignment(TextAlignment.LEFT);
        trackLabel.setTextOrigin(VPos.BOTTOM);
        trackLabel.setFont(font);

        getChildren().addAll(spacer1, timeLabel, spacer2, destinationLabel, spacer3, trackLabel);
    }
}