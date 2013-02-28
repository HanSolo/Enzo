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
 * Date: 27.02.13
 * Time: 15:44
 */
public class QlockGerman implements Qlock {
    private static final QlockTwo.Language LANGUAGE = QlockTwo.Language.GERMAN;
    private static final String[][] MATRIX = {
        {"E", "S", "K", "I", "S", "T", "A", "F", "Ü", "N", "F"},
        {"Z", "E", "H", "N", "Z", "W", "A", "N", "Z", "I", "G"},
        {"D", "R", "E", "I", "V", "I", "E", "R", "T", "E", "L"},
        {"V", "O", "R", "F", "U", "N", "K", "N", "A", "C", "H"},
        {"H", "A", "L", "B", "A", "E", "L", "F", "Ü", "N", "F"},
        {"E", "I", "N", "S", "X", "Ä", "M", "Z", "W", "E", "I"},
        {"D", "R", "E", "I", "A", "U", "J", "V", "I", "E", "R"},
        {"S", "E", "C", "H", "S", "N", "L", "A", "C", "H", "T"},
        {"S", "I", "E", "B", "E", "N", "Z", "W", "Ö", "L", "F"},
        {"Z", "E", "H", "N", "E", "U", "N", "K", "U", "H", "R"}
    };
    private boolean p1;
    private boolean p2;
    private boolean p3;
    private boolean p4;
    private final ConcurrentHashMap<Integer, String> LOOKUP;
    private List<QlockWord> timeList;


    public QlockGerman() {
        LOOKUP = new ConcurrentHashMap<>();
        LOOKUP.putAll(QlockTwo.Language.GERMAN.getLookup());
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

        timeList.add(QlockLanguage.ES);
        timeList.add(QlockLanguage.IST);
        switch (minute) {
            case 0:
                timeList.add(hour == 10 ? QlockLanguage.ZEHN1 : QlockLanguage.valueOf(LOOKUP.get(hour)));
                timeList.add(QlockLanguage.UHR);
                break;
            case 5:
                timeList.add(QlockLanguage.FÜNF1);
                timeList.add(QlockLanguage.NACH);
                timeList.add(hour == 10 ? QlockLanguage.ZEHN1 : QlockLanguage.valueOf(LOOKUP.get(hour)));
                break;
            case 10:
                timeList.add(QlockLanguage.ZEHN);
                timeList.add(QlockLanguage.NACH);
                timeList.add(hour == 10 ? QlockLanguage.ZEHN1 : QlockLanguage.valueOf(LOOKUP.get(hour)));
                break;
            case 15:
                timeList.add(QlockLanguage.VIERTEL);
                timeList.add(QlockLanguage.NACH);
                timeList.add(hour == 10 ? QlockLanguage.ZEHN1 : QlockLanguage.valueOf(LOOKUP.get(hour)));
                break;
            case 20:
                timeList.add(QlockLanguage.ZWANZIG);
                timeList.add(QlockLanguage.NACH);
                timeList.add(hour == 10 ? QlockLanguage.ZEHN1 : QlockLanguage.valueOf(LOOKUP.get(hour)));
                break;
            case 25:
                timeList.add(QlockLanguage.FÜNF1);
                timeList.add(QlockLanguage.VOR);
                timeList.add(hour == 10 ? QlockLanguage.ZEHN1 : QlockLanguage.HALB);
                addHour(timeList, hour);
                break;
            case 30:
                timeList.add(QlockLanguage.HALB);
                addHour(timeList, hour);
                break;
            case 35:
                timeList.add(QlockLanguage.FÜNF1);
                timeList.add(QlockLanguage.NACH);
                timeList.add(QlockLanguage.HALB);
                addHour(timeList, hour);
                break;
            case 40:
                timeList.add(QlockLanguage.ZWANZIG);
                timeList.add(QlockLanguage.VOR);
                addHour(timeList, hour);
                break;
            case 45:
                timeList.add(QlockLanguage.VIERTEL);
                timeList.add(QlockLanguage.VOR);
                addHour(timeList, hour);
                break;
            case 50:
                timeList.add(QlockLanguage.ZEHN);
                timeList.add(QlockLanguage.VOR);
                addHour(timeList, hour);
                break;
            case 55:
                timeList.add(QlockLanguage.FÜNF1);
                timeList.add(QlockLanguage.VOR);
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
            timeList.add(QlockLanguage.EINS);
        } else if (HOUR == 5) {
            timeList.add(QlockLanguage.FÜNF2);
        } else {
            if (HOUR + 1 == 5) {
                timeList.add(QlockLanguage.FÜNF2);
            } else if (HOUR + 1 == 10) {
                timeList.add(QlockLanguage.ZEHN1);
            } else {
                timeList.add(QlockLanguage.valueOf(LOOKUP.get(HOUR + 1)));
            }
        }
    }

    private enum QlockLanguage implements QlockWord {
        EINS(5, 0, 3),
        ZWEI(5, 7, 10),
        DREI(2, 0, 3),
        VIER(6, 7, 10),
        FÜNF(4, 7, 0),
        FÜNF1(0, 7, 10),
        FÜNF2(4, 7, 10),
        SECHS(7, 0, 4),
        SIEBEN(8, 0, 5),
        ACHT(7, 7, 10),
        NEUN(9, 3, 6),
        ZEHN(1, 0, 3),
        ZEHN1(9, 0, 3),
        ELF(4, 5, 7),
        ZWÖLF(8, 6, 10),
        ES(0, 0, 1),
        IST(0, 3, 5),
        VOR(3, 0, 2),
        NACH(3, 7, 10),
        VIERTEL(2, 4, 10),
        DREIVIERTEL(2, 0, 10),
        HALB(4, 0, 3),
        ZWANZIG(1, 4, 10),
        UHR(9, 8, 10);

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

