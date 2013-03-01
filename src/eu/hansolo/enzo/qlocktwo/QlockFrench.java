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
public class QlockFrench implements Qlock {
    private static final QlockTwo.Language LANGUAGE = QlockTwo.Language.FRENCH;
    private static final String[][] MATRIX = {
        {"I", "L", "N", "E", "S", "T", "O", "D", "E", "U", "X"},
        {"Q", "U", "A", "T", "R", "E", "T", "R", "O", "I", "S"},
        {"N", "E", "U", "F", "U", "N", "E", "S", "E", "P", "T"},
        {"H", "U", "I", "T", "S", "I", "X", "C", "I", "N", "Q"},
        {"M", "I", "D", "I", "X", "M", "I", "N", "U", "I", "T"},
        {"O", "N", "Z", "E", "R", "H", "E", "U", "R", "E", "S"},
        {"M", "O", "I", "N", "S", "O", "L", "E", "D", "I", "X"},
        {"E", "T", "R", "Q", "U", "A", "R", "T", "P", "M", "D"},
        {"V", "I", "N", "G", "T", "-", "C", "I", "N", "Q", "U"},
        {"E", "T", "S", "D", "E", "M", "I", "E", "P", "A", "M"}
    };
    private final ConcurrentHashMap<Integer, String> LOOKUP;
    private List<QlockWord> timeList;


    public QlockFrench() {
        LOOKUP = new ConcurrentHashMap<>();
        LOOKUP.putAll(QlockTwo.Language.FRENCH.getLookup());
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

        timeList.add(QlockLanguage.IL);
        timeList.add(QlockLanguage.EST);
        switch (minute) {
            case 0:
                timeList.add(QlockLanguage.valueOf(LOOKUP.get(hour)));
                timeList.add(hour == 1 ? QlockLanguage.HEURE : QlockLanguage.HEURES);
                break;
            case 5:
                timeList.add(QlockLanguage.valueOf(LOOKUP.get(hour)));
                timeList.add(hour == 1 ? QlockLanguage.HEURE : QlockLanguage.HEURES);
                timeList.add(QlockLanguage.CINQ1);
                break;
            case 10:
                timeList.add(QlockLanguage.valueOf(LOOKUP.get(hour)));
                timeList.add(hour == 1 ? QlockLanguage.HEURE : QlockLanguage.HEURES);
                timeList.add(QlockLanguage.DIX1);
                break;
            case 15:
                timeList.add(QlockLanguage.valueOf(LOOKUP.get(hour)));
                timeList.add(hour == 1 ? QlockLanguage.HEURE : QlockLanguage.HEURES);
                timeList.add(QlockLanguage.ET);
                timeList.add(QlockLanguage.QUART);
                break;
            case 20:
                timeList.add(QlockLanguage.valueOf(LOOKUP.get(hour)));
                timeList.add(hour == 1 ? QlockLanguage.HEURE : QlockLanguage.HEURES);
                timeList.add(QlockLanguage.VINGT);
                break;
            case 25:
                timeList.add(QlockLanguage.valueOf(LOOKUP.get(hour)));
                timeList.add(hour == 1 ? QlockLanguage.HEURE : QlockLanguage.HEURES);
                timeList.add(QlockLanguage.VINGT_CINQ);
                break;
            case 30:
                timeList.add(QlockLanguage.valueOf(LOOKUP.get(hour)));
                timeList.add(hour == 1 ? QlockLanguage.HEURE : QlockLanguage.HEURES);
                timeList.add(QlockLanguage.ET1);
                timeList.add(QlockLanguage.DEMIE);
                break;
            case 35:
                addHour(timeList, hour);
                if (hour != 11) {
                    timeList.add(hour == 1 ? QlockLanguage.HEURE : QlockLanguage.HEURES);
                }
                timeList.add(QlockLanguage.MOINS);
                timeList.add(QlockLanguage.VINGT_CINQ);
                break;
            case 40:
                addHour(timeList, hour);
                if (hour != 11) {
                    timeList.add(hour == 1 ? QlockLanguage.HEURE : QlockLanguage.HEURES);
                }
                timeList.add(QlockLanguage.MOINS);
                timeList.add(QlockLanguage.VINGT);
                break;
            case 45:
                addHour(timeList, hour);
                if (hour != 11) {
                    timeList.add(hour == 1 ? QlockLanguage.HEURE : QlockLanguage.HEURES);
                }
                timeList.add(QlockLanguage.MOINS);
                timeList.add(QlockLanguage.LE);
                timeList.add(QlockLanguage.QUART);
                break;
            case 50:
                addHour(timeList, hour);
                if (hour != 11) {
                    timeList.add(hour == 1 ? QlockLanguage.HEURE : QlockLanguage.HEURES);
                }
                timeList.add(QlockLanguage.MOINS);
                timeList.add(QlockLanguage.DIX1);
                break;
            case 55:
                addHour(timeList, hour);
                if (hour != 11) {
                    timeList.add(hour == 1 ? QlockLanguage.HEURE : QlockLanguage.HEURES);
                }
                timeList.add(QlockLanguage.MOINS);
                timeList.add(QlockLanguage.CINQ1);
                break;
        }

        return timeList;
    }

    @Override public QlockTwo.Language getLanguage() {
        return LANGUAGE;
    }

    private void addHour(List<QlockWord> timeList, final int HOUR) {
        if (HOUR == 12) {
            timeList.add(QlockLanguage.UNE);
        } else if (HOUR == 5) {
            timeList.add(QlockLanguage.CINQ1);
        } else if (HOUR == 11) {
            timeList.add(QlockLanguage.MIDI);
        } else {
            timeList.add(QlockLanguage.valueOf(LOOKUP.get(HOUR + 1)));
        }
    }

    private enum QlockLanguage implements QlockWord {
        UNE(2, 4, 6),
        DEUX(0, 7, 10),
        TROIS(1, 6, 10),
        QUATRE(1, 0, 5),
        CINQ(3, 7, 10),
        CINQ1(8, 6, 9),
        SIX(3, 4, 6),
        SEPT(2, 7, 10),
        HUIT(3, 0, 3),
        NEUF(2, 0, 3),
        DIX(4, 2, 4),
        DIX1(6, 8, 10),
        ONZE(5, 0, 3),
        IL(0, 0, 1),
        EST(0, 3, 5),
        ET(7, 0, 1),
        HEURE(5, 5, 9),
        HEURES(5, 5, 10),
        LE(6, 6, 7),
        QUART(7, 3, 7),
        VINGT(8, 0, 4),
        MIDI(4, 0, 3),
        VINGT_CINQ(8, 0, 9),
        MOINS(6, 0, 4),
        ET1(9, 0, 1),
        DEMI(9, 3, 6),
        DEMIE(9, 3, 7);

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