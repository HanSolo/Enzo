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

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;


/**
 * Created by
 * User: hansolo
 * Date: 17.04.13
 * Time: 14:56
 */
public class SimpleRow extends HBox {
    private LedSegment              leds;
    private SimpleTimeSegment       time;
    private SimpleCharacterSegment  destination;
    private SimpleCharacterSegment  track;
    private boolean                 isEmpty;


    // ******************** Constructors **************************************
    public SimpleRow() {
        this(false, "  ", "  ", "", "");
    }
    public SimpleRow(final String HOURS, final String MINUTES, final String DESTINATION, final String TRACK) {
        this(false, HOURS, MINUTES, DESTINATION, TRACK);
    }
    public SimpleRow(final boolean BLINKING, final String HOURS, final String MINUTES, final String DESTINATION, final String TRACK) {
        leds        = new LedSegment();
        time        = new SimpleTimeSegment();
        destination = new SimpleCharacterSegment(16);
        track       = new SimpleCharacterSegment(2);
        isEmpty     = DESTINATION.isEmpty() ? true : false;
        leds.setBlinking(BLINKING);
        time.setTime(HOURS, MINUTES);
        destination.setText(DESTINATION);
        track.setText(TRACK);

        setSpacing(11);
        setAlignment(Pos.CENTER_LEFT);
        setCenterShape(true);
        getChildren().addAll(leds,
                             time,
                             destination,
                             track);
    }


    // ******************** Methods *******************************************
    public final String getRowString() {
        return time.getTime() + " " + destination.getText() + " " + track.getText();
    }

    public final void setRow(final SimpleRow ROW) {
        setRow(ROW.isBlinking(), ROW.getHours(), ROW.getMinutes(), ROW.getDestination(), ROW.getTrack());
    }
    public final void setRow(final String HOURS, final String MINUTES, final String DESTINATION, final String TRACK) {
        setRow(false, HOURS, MINUTES, DESTINATION, TRACK);
    }
    public final void setRow(final boolean BLINKING, final String HOURS, final String MINUTES, final String DESTINATION, final String TRACK) {
        leds.setBlinking(BLINKING);
        time.setTime(HOURS, MINUTES);
        destination.setText(DESTINATION);
        track.setText(TRACK);
        isEmpty = DESTINATION.isEmpty() ? true : false;
    }

    public final boolean isBlinking() {
        return leds.isBlinking();
    }
    public final void setBlinking(final boolean BLINKING) {
        leds.setBlinking(BLINKING);
    }

    public final String getTime() {
        return time.getTime();
    }
    public final void setTime(final String HOURS, final String MINUTES) {
        time.setTime(HOURS, MINUTES);
    }

    public final String getHours() {
        return time.getHours();
    }
    public final void setHours(final String HOURS) {
        time.setHours(HOURS);
    }

    public final String getMinutes() {
        return time.getMinutes();
    }
    public final void setMinutes(final String MINUTES) {
        time.setMinutes(MINUTES);
    }

    public final String getDestination() {
        return destination.getText();
    }
    public final void setDestination(final String DESTINATION) {
        destination.setText(DESTINATION);
    }

    public final String getTrack() {
        return track.getText();
    }
    public final void setTrack(final String TRACK) {
        track.setText(TRACK);
    }

    public final void setLedColor(final Color COLOR) {
        leds.setColor(COLOR);
    }

    public final void reset() {
        setRow(false, "", "", "", "");
        isEmpty = true;
        setBlinking(false);
    }

    public final boolean isEmpty() {
        return isEmpty;
    }

    public final void toggleLeds() {
        leds.toggle();
    }

    public final void setLedsOff() {
        leds.reset();
    }

    public String toString() {
        return getRowString();
    }
}