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

import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by
 * User: hansolo
 * Date: 15.04.13
 * Time: 08:21
 */
public class DepartureBoard extends VBox{
    private static final String[] DESTINATIONS = {
        " ",
        "NEW YORK",
        "LONDON",
        "BASEL",
        "FRANKFURT",
        "SYDNEY",
        "PARIS",
        "SAN FRANCISCO",
        "BOSTON",
        "MUNICH",
        "BERLIN",
        "FRANKFURT",
        "BARCELONA",
        "BEIJING",
        "SHANGHAI",
        "HONG KONG",
        "TOKYO",
        "YOKOHAMA",
        "BRISBANE",
        "STOCKHOLM",
        "OSLO",
        "MOSCOW",
        "PRAGUE",
        "LION",
        "ZURICH",
        "MADRID",
        "LISBOA",
        "CHICAGO",
        "HELSINKI"
    };
    private static final Calendar CAL = Calendar.getInstance();
    private List<Row>           queue;
    private ObservableList<Row> rows;
    private List<Row>           activeRows;
    private AnimationTimer      timer;
    private long                lastTimerCall;
    private long                lastUpdateCall;
    private long                lastLedCall;
    private int                 queueCounter;
    private boolean             isUpdating;


    // ******************** Constructors **************************************
    public DepartureBoard() {
        queue          = new LinkedList<>();
        rows           = FXCollections.observableArrayList();
        lastTimerCall  = 10_000_000_000l;
        lastUpdateCall = 6_000_000_000l;
        lastLedCall    = 750_000_000l;
        for (int i = 0 ; i < 5 ; i++) {
            rows.add(new Row(DESTINATIONS));
        }
        setSpacing(5);
        for (Row row : rows) {
            getChildren().add(row);
        }
        activeRows = new LinkedList<>();
        timer = new AnimationTimer() {
            @Override public void handle(final long NOW) {
                if (NOW > lastTimerCall + 8_000_000_000l) {
                    // check the rows for their departure time
                    checkRows();
                    lastTimerCall = NOW;
                }
                if (NOW > lastUpdateCall + 5_000_000_000l) {
                    // update the next row
                    update();
                    lastUpdateCall = NOW;
                }
                if (NOW > lastLedCall + 750_000_000l) {
                    // set led's to blinking if needed
                    //final Calendar CAL = Calendar.getInstance();
                    final int HH       = CAL.get(Calendar.HOUR_OF_DAY);
                    final int MM       = CAL.get(Calendar.MINUTE);
                    for (Row row : activeRows) {
                        if (!row.isEmpty()) {
                            int hour = Integer.parseInt(row.getHours());
                            int min  = Integer.parseInt(row.getMinutes());
                            if (HH == hour) {
                                if (MM >= min || MM + 10 >= min) {
                                    row.toggleLeds();
                                } else if (HH + 1 == hour && (MM + 10) % 60 >= min) {
                                    row.toggleLeds();
                                } else {
                                    row.setLedsOff();
                                }
                            }
                        }
                    }
                    lastLedCall = NOW;
                }
            }
        };
        queueCounter = 0;
        isUpdating   = false;
    }


    // ******************** Methods *******************************************
    public final void addRow(final Row ROW) {
        queue.add(ROW);
    }
    public final void removeRow(final Row ROW) {
        if (queue.contains(ROW)) {
            queue.remove(ROW);
        }
    }

    public final void start() {
        timer.start();
    }
    public final void stop() {
        timer.stop();
    }

    private final void checkRows() {
        final Calendar CAL = Calendar.getInstance();
        final int      HH  = CAL.get(Calendar.HOUR_OF_DAY);
        final int      MM  = CAL.get(Calendar.MINUTE);
        boolean addRow;
        for (Row row : rows) {
            try {
                int hour = Integer.parseInt(row.getHours());
                int min  = Integer.parseInt(row.getMinutes());
                addRow   = false;
                if (HH == hour) {
                    if (MM >= min || MM + 10 >= min) {
                        addRow = true;
                    } else if (HH + 1 == hour && (MM + 10) % 60 >= min) {
                        addRow = true;
                    }
                }
                if (addRow) {
                    if (!activeRows.contains(row)) {
                        activeRows.add(row);
                    }
                }
            } catch (NumberFormatException exception) {}
        }
        removeOverdue();
    }

    private void removeOverdue() {
        final Calendar CAL = Calendar.getInstance();
        final int HH       = CAL.get(Calendar.HOUR_OF_DAY);
        final int MM       = CAL.get(Calendar.MINUTE);
        for (int i = 0 ; i < rows.size() ; i++) {
            try {
                int hh = Integer.parseInt(rows.get(i).getHours());
                int mm = Integer.parseInt(rows.get(i).getMinutes());
                if (HH > hh || (HH == hh && MM > mm)) {
                    rows.get(i).reset();
                    queue.remove(i);
                    queueCounter = 0;
                    activeRows.remove(0);
                }
            } catch (NumberFormatException exception) {

            }
        }
    }

    private void update() {
        if (!rows.isEmpty() && queueCounter < rows.size()) {
            rows.get(queueCounter).setRow(queue.get(queueCounter));
            rows.get(queueCounter).setLedsOff();
            isUpdating = true;
            queueCounter++;
            if (queueCounter >= queue.size()) {
                queueCounter = 0;
                isUpdating = false;
            }
            for (int i = queue.size() ; i < rows.size() ; i++) {
                rows.get(i).reset();
            }
        }
    }
}
