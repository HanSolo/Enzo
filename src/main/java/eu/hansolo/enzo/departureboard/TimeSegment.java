/*
 * Copyright (c) 2013. Gerrit Grunwald
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package eu.hansolo.enzo.departureboard;

import eu.hansolo.enzo.splitflap.SplitFlap;
import eu.hansolo.enzo.splitflap.SplitFlapBuilder;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.VPos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


/**
 * Created by
 * User: hansolo
 * Date: 15.04.13
 * Time: 07:42
 */
public class TimeSegment extends HBox {
    private SplitFlap      hourLeft;
    private SplitFlap      hourRight;
    private SplitFlap      minLeft;
    private SplitFlap      minRight;
    private String         defaultHours;
    private StringProperty hours;
    private String         defaultMinutes;
    private StringProperty minutes;


    // ******************** Constructors **************************************
    public TimeSegment() {
        this(Color.rgb(255, 240, 100));
    }
    public TimeSegment(final Color TEXT_COLOR) {
        hourLeft  = SplitFlapBuilder.create()
                                    .selection(SplitFlap.NUMERIC)
                                    .flipTime(100)
                                    .textColor(TEXT_COLOR)
                                    .squareFlaps(true)
                                    .build();
        hourLeft.setPrefSize(36, 60);
        hourRight = SplitFlapBuilder.create()
                                    .selection(SplitFlap.NUMERIC)
                                    .flipTime(100)
                                    .textColor(TEXT_COLOR)
                                    .squareFlaps(true)
                                    .build();
        hourRight.setPrefSize(36, 60);
        minLeft   = SplitFlapBuilder.create()
                                    .selection(SplitFlap.NUMERIC)
                                    .flipTime(100)
                                    .textColor(TEXT_COLOR)
                                    .squareFlaps(true)
                                    .build();
        minLeft.setPrefSize(36, 60);
        minRight  = SplitFlapBuilder.create()
                                    .selection(SplitFlap.NUMERIC)
                                    .flipTime(100)
                                    .textColor(TEXT_COLOR)
                                    .squareFlaps(true)
                                    .build();
        minRight.setPrefSize(36, 60);
        Text colon = new Text(":");
        colon.setFill(TEXT_COLOR);
        colon.setTextAlignment(TextAlignment.CENTER);
        colon.setTextOrigin(VPos.CENTER);
        colon.setFont(Font.font("sans serif", 36));

        setSpacing(0);
        getChildren().addAll(hourLeft,
                             hourRight,
                             colon,
                             minLeft,
                             minRight);
    }


    // ******************** Methods *******************************************
    public final String getTime() {
        return (hours.get() + ":" + minutes.get());
    }
    public final void setTime(final String HOURS, final String MINUTES) {
        setHours(HOURS);
        setMinutes(MINUTES);
    }

    public final String getHours() {
        return null == hours ? defaultHours : hours.get();
    }
    public final void setHours(final String HOURS) {
        if (null == hours) {
            defaultHours = HOURS;
        } else {
            hours.set(HOURS);
        }

        if (HOURS.isEmpty()) {
            hourLeft.setText(" ");
            hourRight.setText(" ");
        } else {
            if (HOURS.length() > 1) {
                hourLeft.setText(HOURS.substring(0, 1));
                hourRight.setText(HOURS.substring(1, 2));
            } else {
                hourLeft.setText("0");
                hourRight.setText(HOURS.substring(0, 1));
            }
        }
    }
    public final StringProperty hoursProperty() {
        if (null == hours) {
            hours = new SimpleStringProperty(this, "hours", defaultHours);
        }
        return hours;
    }

    public final String getMinutes() {
        return null == minutes ? defaultMinutes : minutes.get();
    }
    public final void setMinutes(final String MINUTES) {
        if (null == minutes) {
            defaultMinutes = MINUTES;
        } else {
            minutes.set(MINUTES);
        }

        if (MINUTES.isEmpty()) {
            minLeft.setText(" ");
            minRight.setText(" ");
        } else {
            if (MINUTES.length() > 1) {
                minLeft.setText(MINUTES.substring(0, 1));
                minRight.setText(MINUTES.substring(1, 2));
            } else {
                minLeft.setText("0");
                minRight.setText(MINUTES.substring(0, 1));
            }
        }
    }
    public final StringProperty minutesProperty() {
        if (null == minutes) {
            minutes = new SimpleStringProperty(this, "minutes", defaultMinutes);
        }
        return minutes;
    }
}
