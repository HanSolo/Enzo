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
public class QlockEnglish implements Qlock {
    private static final QlockTwo.Language LANGUAGE = QlockTwo.Language.ENGLISH;
    private static final String[][] MATRIX = {
        {"I", "T", "L", "I", "S", "A", "S", "T", "I", "M", "E"},
        {"A", "C", "Q", "U", "A", "R", "T", "E", "R", "D", "C"},
        {"T", "W", "E", "N", "T", "Y", "F", "I", "V", "E", "X"},
        {"H", "A", "L", "F", "B", "T", "E", "N", "F", "T", "O"},
        {"P", "A", "S", "T", "E", "R", "U", "N", "I", "N", "E"},
        {"O", "N", "E", "S", "I", "X", "T", "H", "R", "E", "E"},
        {"F", "O", "U", "R", "F", "I", "V", "E", "T", "W", "O"},
        {"E", "I", "G", "H", "T", "E", "L", "E", "V", "E", "N"},
        {"S", "E", "V", "E", "N", "T", "W", "E", "L", "V", "E"},
        {"T", "E", "N", "S", "E", "O'", "C", "L", "O", "C", "K"}
    };
    private final ConcurrentHashMap<Integer, String> LOOKUP;
    private List<QlockWord> timeList;


    public QlockEnglish() {
        LOOKUP = new ConcurrentHashMap<>();
        LOOKUP.putAll(QlockTwo.Language.ENGLISH.getLookup());
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

        minute -= minute%5;

        timeList.clear();

        timeList.add(QlockLanguage.IT);
        timeList.add(QlockLanguage.IS);
        switch (minute)
        {
            case 0:
                timeList.add(QlockLanguage.valueOf(LOOKUP.get(hour)));
                timeList.add(QlockLanguage.OCLOCK);
                break;
            case 5:
                timeList.add(QlockLanguage.FIVE1);
                timeList.add(QlockLanguage.PAST);
                timeList.add(QlockLanguage.valueOf(LOOKUP.get(hour)));
                break;
            case 10:
                timeList.add(QlockLanguage.TEN1);
                timeList.add(QlockLanguage.PAST);
                timeList.add(QlockLanguage.valueOf(LOOKUP.get(hour)));
                break;
            case 15:
                timeList.add(QlockLanguage.A);
                timeList.add(QlockLanguage.QUARTER);
                timeList.add(QlockLanguage.PAST);
                timeList.add(QlockLanguage.valueOf(LOOKUP.get(hour)));
                break;
            case 20:
                timeList.add(QlockLanguage.TWENTY);
                timeList.add(QlockLanguage.PAST);
                timeList.add(QlockLanguage.valueOf(LOOKUP.get(hour)));
                break;
            case 25:
                timeList.add(QlockLanguage.TWENTYFIVE);
                timeList.add(QlockLanguage.PAST);
                timeList.add(QlockLanguage.valueOf(LOOKUP.get(hour)));
                break;
            case 30:
                timeList.add(QlockLanguage.HALF);
                timeList.add(QlockLanguage.PAST);
                timeList.add(QlockLanguage.valueOf(LOOKUP.get(hour)));
                break;
            case 35:
                timeList.add(QlockLanguage.TWENTYFIVE);
                timeList.add(QlockLanguage.TO);
                addHour(timeList, hour);
                break;
            case 40:
                timeList.add(QlockLanguage.TWENTY);
                timeList.add(QlockLanguage.TO);
                addHour(timeList, hour);
                break;
            case 45:
                timeList.add(QlockLanguage.A);
                timeList.add(QlockLanguage.QUARTER);
                timeList.add(QlockLanguage.TO);
                addHour(timeList, hour);
                break;
            case 50:
                timeList.add(QlockLanguage.TEN1);
                timeList.add(QlockLanguage.TO);
                addHour(timeList, hour);
                break;
            case 55:
                timeList.add(QlockLanguage.FIVE1);
                timeList.add(QlockLanguage.TO);
                addHour(timeList, hour);
                break;
        }

        return timeList;
    }

    @Override public QlockTwo.Language getLanguage() {
        return LANGUAGE;
    }

    private void addHour(List<QlockWord> timeList, final int HOUR) {
        if (HOUR == 12) {
            timeList.add(QlockLanguage.ONE);
        } else if (HOUR == 10) {
            timeList.add(QlockLanguage.TEN2);
        } else {
            timeList.add(QlockLanguage.valueOf(LOOKUP.get(HOUR + 1)));
        }
    }

    private enum QlockLanguage implements QlockWord {
        ONE(5, 0, 2),
        TWO(6, 8, 10),
        THREE(5, 6, 10),
        FOUR(6, 0, 3),
        FIVE(6, 4, 7),
        FIVE1(2, 6, 9),
        SIX(5, 3, 5),
        SEVEN(8, 0, 4),
        EIGHT(7, 0, 4),
        NINE(4, 7, 10),
        TEN(9, 0, 2),
        TEN1(3, 5, 7),
        TEN2(9, 0, 2),
        ELEVEN(7, 5, 10),
        TWELVE(8, 5, 10),
        IT(0, 0, 1),
        IS(0, 3, 4),
        A(1,0,0),
        TO(3, 9, 10),
        PAST(4, 0, 3),
        QUARTER(1, 2, 8),
        HALF(3, 0, 3),
        TWENTY(2, 0, 5),
        TWENTYFIVE(2, 0, 9),
        OCLOCK(9, 5, 10);

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