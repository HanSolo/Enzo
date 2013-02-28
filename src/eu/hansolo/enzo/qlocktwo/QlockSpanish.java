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

package eu.hansolo.enzo.qlocktwo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by
 * User: hansolo
 * Date: 28.02.13
 * Time: 07:53
 */
public class QlockSpanish implements Qlock {
    private static final QlockTwo.Language LANGUAGE = QlockTwo.Language.SPANISH;
    private static final String[][] MATRIX = {
        {"E", "S", "O", "N", "E", "L", "A", "S", "U", "N", "A"},
        {"D", "O", "S", "I", "T", "R", "E", "S", "O", "R", "E"},
        {"C", "U", "A", "T", "R", "O", "C", "I", "N", "C", "O"},
        {"S", "E", "I", "S", "A", "S", "I", "E", "T", "E", "N"},
        {"O", "C", "H", "O", "N", "U", "E", "V", "E", "Y", "O"},
        {"L", "A", "D", "I", "E", "Z", "S", "O", "N", "C", "E"},
        {"D", "O", "C", "E", "L", "Y", "M", "E", "N", "O", "S"},
        {"O", "V", "E", "I", "N", "T", "E", "D", "I", "E", "Z"},
        {"V", "E", "I", "N", "T", "I", "C", "I", "N", "C", "O"},
        {"M", "E", "D", "I", "A", "C", "U", "A", "R", "T", "O"}
    };
    private boolean p1;
    private boolean p2;
    private boolean p3;
    private boolean p4;
    private final ConcurrentHashMap<Integer, String> LOOKUP;
    private List<QlockWord> timeList;


    public QlockSpanish() {
        LOOKUP = new ConcurrentHashMap<>();
        LOOKUP.putAll(QlockTwo.Language.SPANISH.getLookup());
        timeList = new ArrayList<>(10);
    }

    @Override public String[][] getMatrix() {
        return MATRIX;
    }

    @Override public List<QlockWord> getTime(int minute, int hour) {
        if (hour > 12) {
            hour -= 12;
        }
        if (hour <= 0) {
            hour += 12;
        }

        if (minute > 60) {
            minute -= 60;
            hour++;
        }
        if (minute < 0) {
            minute += 60;
            hour--;
        }

        if (minute %5 == 0) {
            p1 = false;
            p2 = false;
            p3 = false;
            p4 = false;
        }

        if (minute %10 == 1 || minute %10 == 6) {
            p1 = true;
        }

        if (minute %10 == 2 || minute %10 == 7) {
            p1 = true;
            p2 = true;
        }

        if (minute %10 == 3 || minute %10 == 8) {
            p1 = true;
            p2 = true;
            p3 = true;
        }

        if (minute %10 == 4 || minute %10 == 9) {
            p1 = true;
            p2 = true;
            p3 = true;
            p4 = true;
        }

        minute -= minute%5;

        timeList.clear();

        switch (minute)
        {
            case 0:
                timeList.add(hour == 1 ? QlockLanguage.ES : QlockLanguage.SON);
                timeList.add(hour == 1 ? QlockLanguage.LA : QlockLanguage.LAS);
                timeList.add(QlockLanguage.valueOf(LOOKUP.get(hour)));
                break;
            case 5:
                timeList.add(hour == 1 ? QlockLanguage.ES : QlockLanguage.SON);
                timeList.add(hour == 1 ? QlockLanguage.LA : QlockLanguage.LAS);
                timeList.add(QlockLanguage.valueOf(LOOKUP.get(hour)));
                timeList.add(QlockLanguage.Y);
                timeList.add(QlockLanguage.CINCO1);
                break;
            case 10:
                timeList.add(hour == 1 ? QlockLanguage.ES : QlockLanguage.SON);
                timeList.add(hour == 1 ? QlockLanguage.LA : QlockLanguage.LAS);
                timeList.add(QlockLanguage.valueOf(LOOKUP.get(hour)));
                timeList.add(QlockLanguage.Y);
                timeList.add(QlockLanguage.DIEZ1);
                break;
            case 15:
                timeList.add(hour == 1 ? QlockLanguage.ES : QlockLanguage.SON);
                timeList.add(hour == 1 ? QlockLanguage.LA : QlockLanguage.LAS);
                timeList.add(QlockLanguage.valueOf(LOOKUP.get(hour)));
                timeList.add(QlockLanguage.Y);
                timeList.add(QlockLanguage.CUARTO);
                break;
            case 20:
                timeList.add(hour == 1 ? QlockLanguage.ES : QlockLanguage.SON);
                timeList.add(hour == 1 ? QlockLanguage.LA : QlockLanguage.LAS);
                timeList.add(QlockLanguage.valueOf(LOOKUP.get(hour)));
                timeList.add(QlockLanguage.Y);
                timeList.add(QlockLanguage.VEINTE);
                break;
            case 25:
                timeList.add(hour == 1 ? QlockLanguage.ES : QlockLanguage.SON);
                timeList.add(hour == 1 ? QlockLanguage.LA : QlockLanguage.LAS);
                timeList.add(QlockLanguage.valueOf(LOOKUP.get(hour)));
                timeList.add(QlockLanguage.Y);
                timeList.add(QlockLanguage.VEINTICINCO);
                break;
            case 30:
                timeList.add(hour == 1 ? QlockLanguage.ES : QlockLanguage.SON);
                timeList.add(hour == 1 ? QlockLanguage.LA : QlockLanguage.LAS);
                timeList.add(QlockLanguage.valueOf(LOOKUP.get(hour)));
                timeList.add(QlockLanguage.Y);
                timeList.add(QlockLanguage.MEDIA);
                break;
            case 35:
                timeList.add(hour == 1 ? QlockLanguage.ES : QlockLanguage.SON);
                timeList.add(hour == 1 ? QlockLanguage.LA : QlockLanguage.LAS);
                timeList.add(QlockLanguage.MENOS);
                timeList.add(QlockLanguage.VEINTICINCO);
                addHour(timeList, hour);
                break;
            case 40:
                timeList.add(hour == 1 ? QlockLanguage.ES : QlockLanguage.SON);
                timeList.add(hour == 1 ? QlockLanguage.LA : QlockLanguage.LAS);
                timeList.add(QlockLanguage.MENOS);
                timeList.add(QlockLanguage.VEINTE);
                addHour(timeList, hour);
                break;
            case 45:
                timeList.add(QlockLanguage.SON);
                timeList.add(QlockLanguage.LAS);
                timeList.add(QlockLanguage.MENOS);
                timeList.add(QlockLanguage.CUARTO);
                addHour(timeList, hour);
                break;
            case 50:
                // ES LA UNA MENOS DIEZ
                timeList.add(QlockLanguage.SON);
                timeList.add(QlockLanguage.LAS);
                timeList.add(QlockLanguage.MENOS);
                timeList.add(QlockLanguage.DIEZ1);
                addHour(timeList, hour);
                break;
            case 55:
                timeList.add(QlockLanguage.SON);
                timeList.add(QlockLanguage.LAS);
                timeList.add(QlockLanguage.MENOS);
                timeList.add(QlockLanguage.CINCO1);
                addHour(timeList, hour);
                break;
        }

        return timeList;
    }

    @Override public boolean isP1() {
        return p1;
    }

    @Override public boolean isP2() {
        return p2;
    }

    @Override public boolean isP3() {
        return p3;
    }

    @Override public boolean isP4() {
        return p4;
    }

    @Override public QlockTwo.Language getLanguage() {
        return LANGUAGE;
    }

    private void addHour(List<QlockWord> timeList, final int HOUR) {
        if (HOUR == 12) {
            timeList.add(QlockLanguage.UNA);
        } else if (HOUR == 10) {
            timeList.add(QlockLanguage.DIEZ1);
        } else {
            timeList.add(QlockLanguage.valueOf(LOOKUP.get(HOUR + 1)));
        }
    }

    private enum QlockLanguage implements QlockWord {
        UNA(0, 8, 10),
        DOS(1, 0, 2),
        TRES(1, 4, 7),
        CUATRO(2, 0, 5),
        CINCO(2, 6, 10),
        CINCO1(8, 6, 10),
        SEIS(3, 0, 3),
        SIETE(3, 5, 9),
        OCHO(4, 0, 3),
        NUEVE(4, 4, 8),
        DIEZ(5, 2, 5),
        DIEZ1(7, 7, 10),
        ONCE(7, 5, 10),
        DOCE(6, 0, 3),
        SON(0, 1, 3),
        ES(0, 0, 1),
        LA(0, 5, 6),
        LAS(0, 5, 7),
        Y(6, 5, 5),
        MENOS(6, 6, 10),
        CUARTO(9, 5, 10),
        VEINTE(7, 1, 6),
        VEINTICINCO(8, 0, 10),
        MEDIA(9, 0, 4);

        private final int ROW;
        private final int START;
        private final int STOP;

        private QlockLanguage(final int ROW, final int START, final int STOP) {
            this.ROW = ROW;
            this.START = START;
            this.STOP = STOP;
        }

        @Override public int getRow() {
            return ROW;
        }

        @Override public int getStart() {
            return START;
        }

        @Override public int getStop() {
            return STOP;
        }
    }
}