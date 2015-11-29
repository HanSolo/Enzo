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

package eu.langhammer.enzo2.qlocktwo.skin;

import eu.langhammer.enzo2.common.BrushedMetalPaint;
import eu.langhammer.enzo2.qlocktwo.QlockTwo;
import eu.langhammer.enzo2.qlocktwo.QlockWord;
import javafx.animation.AnimationTimer;
import javafx.collections.ListChangeListener;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.time.LocalTime;


public class QlockTwoSkin extends SkinBase<QlockTwo> implements Skin<QlockTwo> {
    private static final double   PREFERRED_WIDTH  = 200;
    private static final double   PREFERRED_HEIGHT = 200;
    private static final double   MINIMUM_WIDTH    = 50;
    private static final double   MINIMUM_HEIGHT   = 50;
    private static final double   MAXIMUM_WIDTH    = 1024;
    private static final double   MAXIMUM_HEIGHT   = 1024;
    private static final double   ASPECT_RATIO     = 1.0;
    private double                size;
    private double                width;
    private double                height;
    private int                   hour;
    private int                   minute;
    private int                   second;
    private QlockTwo.SecondsLeft  secondLeft;
    private QlockTwo.SecondsRight secondRight;
    private int                   oldMinute;
    private int                   timeZoneOffsetHour;
    private int                   timeZoneOffsetMinute;
    private BrushedMetalPaint     texture;
    private Pane                  pane;
    private Region                background;
    private ImageView             stainlessBackground;
    private Region                p1;
    private Region                p2;
    private Region                p3;
    private Region                p4;
    private Label[][]             matrix;
    private Region                highlight;
    private Font                  font;
    private double                startX;
    private double                startY;
    private double                stepX;
    private double                stepY;
    private long                  lastTimerCall;
    private AnimationTimer        timer;


    // ******************** Constructors **************************************
    public QlockTwoSkin(final QlockTwo CONTROL) {
        super(CONTROL);
        hour                 = 0;
        minute               = 0;
        second               = 0;
        secondLeft           = QlockTwo.SecondsLeft.ZERO;
        secondRight          = QlockTwo.SecondsRight.ZERO;
        oldMinute            = 0;
        timeZoneOffsetHour   = 0;
        timeZoneOffsetMinute = 0;
        texture              = new BrushedMetalPaint(Color.web("#888888"));
        stainlessBackground  = new ImageView();
        pane                 = new Pane();
        lastTimerCall        = System.nanoTime();
        timer = new AnimationTimer() {
            @Override public void handle(long now) {
                if (now > lastTimerCall + 1_000_000_000l) {

                    // Hours
                    hour = LocalTime.now().getHour() - timeZoneOffsetHour;

                    // Minutes
                    minute = LocalTime.now().getMinute() + timeZoneOffsetMinute;

                    if (oldMinute < minute || (oldMinute == 59 && minute == 0)) {
                        updateClock();
                        oldMinute = minute;
                    }

                    // SecondsRight
                    if (getSkinnable().isSecondsMode()) {
                        second = LocalTime.now().getSecond();
                        if (second < 10) {
                            secondLeft  = QlockTwo.SecondsLeft.ZERO;
                            secondRight = QlockTwo.SecondsRight.values()[second];
                        } else {
                            secondLeft  = QlockTwo.SecondsLeft.values()[Integer.parseInt(Integer.toString(second).substring(0, 1))];
                            secondRight = QlockTwo.SecondsRight.values()[Integer.parseInt(Integer.toString(second).substring(1, 2))];
                        }
                        updateClock();
                    }

                    lastTimerCall = now;
                }
            }
        };
        init();
        initGraphics();
        registerListeners();
        timer.start();
    }


    // ******************** Initialization ************************************
    private void init() {
        if (Double.compare(getSkinnable().getPrefWidth(), 0.0) <= 0 || Double.compare(getSkinnable().getPrefHeight(), 0.0) <= 0 ||
            Double.compare(getSkinnable().getWidth(), 0.0) <= 0 || Double.compare(getSkinnable().getHeight(), 0.0) <= 0) {
            if (getSkinnable().getPrefWidth() > 0 && getSkinnable().getPrefHeight() > 0) {
                getSkinnable().setPrefSize(getSkinnable().getPrefWidth(), getSkinnable().getPrefHeight());
            } else {
                getSkinnable().setPrefSize(PREFERRED_WIDTH, PREFERRED_HEIGHT);
            }
        }

        if (Double.compare(getSkinnable().getMinWidth(), 0.0) <= 0 || Double.compare(getSkinnable().getMinHeight(), 0.0) <= 0) {
            getSkinnable().setMinSize(MINIMUM_WIDTH, MINIMUM_HEIGHT);
        }

        if (Double.compare(getSkinnable().getMaxWidth(), 0.0) <= 0 || Double.compare(getSkinnable().getMaxHeight(), 0.0) <= 0) {
            getSkinnable().setMaxSize(MAXIMUM_WIDTH, MAXIMUM_HEIGHT);
        }
    }

    private void initGraphics() {
        startX     = PREFERRED_WIDTH * 0.114;
        startY     = PREFERRED_WIDTH * 0.112;
        stepX      = PREFERRED_WIDTH * 0.072;
        stepY      = PREFERRED_WIDTH * 0.08;
        font       = Font.loadFont(getClass().getResourceAsStream("/eu/hansolo/enzo/fonts/din.otf"), PREFERRED_WIDTH * 0.048);
        background = new Region();
        background.getStyleClass().addAll("background", getSkinnable().getColor().STYLE_CLASS);

        stainlessBackground.setImage(texture.getImage(PREFERRED_WIDTH, PREFERRED_HEIGHT));
        stainlessBackground.setOpacity(getSkinnable().getColor() == QlockTwo.QlockColor.STAINLESS_STEEL ? 1 : 0);

        p1 = new Region();
        p1.getStyleClass().add("dot-off");
        p2 = new Region();
        p2.getStyleClass().add("dot-off");
        p3 = new Region();
        p3.getStyleClass().add("dot-off");
        p4 = new Region();
        p4.getStyleClass().add("dot-off");

        highlight = new Region();
        highlight.getStyleClass().add("highlight");

        matrix = new Label[11][10];
        for (int y = 0 ; y < 10 ; y++) {
            for (int x = 0 ; x < 11 ; x++) {
                matrix[x][y] = new Label();
                matrix[x][y].setAlignment(Pos.CENTER);
                matrix[x][y].setPrefWidth(PREFERRED_WIDTH * 0.048);
                matrix[x][y].setPrefHeight(PREFERRED_HEIGHT * 0.048);
                matrix[x][y].setText(getSkinnable().getQlock().getMatrix()[y][x]);
                matrix[x][y].setFont(font);
                matrix[x][y].getStyleClass().add("text-off");
            }
        }

        pane.getChildren().setAll(background,
                                  stainlessBackground,
                                  p4,
                                  p3,
                                  p2,
                                  p1);
        for (int y = 0 ; y < 10 ; y++) {
            for (int x = 0 ; x < 11 ; x++) {
                pane.getChildren().add(matrix[x][y]);
            }
        }
        pane.getChildren().add(highlight);

        getChildren().setAll(pane);
        resize();
    }

    private void registerListeners() {
        getSkinnable().widthProperty().addListener(observable -> handleControlPropertyChanged("RESIZE") );
        getSkinnable().heightProperty().addListener(observable -> handleControlPropertyChanged("RESIZE") );
        getSkinnable().prefWidthProperty().addListener(observable -> handleControlPropertyChanged("PREF_SIZE") );
        getSkinnable().prefHeightProperty().addListener(observable -> handleControlPropertyChanged("PREF_SIZE") );
        getSkinnable().colorProperty().addListener(observable -> handleControlPropertyChanged("COLOR") );
        getSkinnable().languageProperty().addListener(observable -> handleControlPropertyChanged("LANGUAGE") );
        getSkinnable().highlightVisibleProperty().addListener(observable -> handleControlPropertyChanged( "HIGHLIGHT") );

        getSkinnable().getStyleClass().addListener(new ListChangeListener<String>() {
            @Override public void onChanged(Change<? extends String> change) {
                resize();
            }
        });
    }


    // ******************** Methods *******************************************
    protected void handleControlPropertyChanged(final String PROPERTY) {
        if ("RESIZE".equals(PROPERTY)) {
            resize();
        } else if ("COLOR".equals(PROPERTY)) {
            background.getStyleClass().setAll("background", getSkinnable().getColor().STYLE_CLASS);
            stainlessBackground.setOpacity(getSkinnable().getColor() == QlockTwo.QlockColor.STAINLESS_STEEL ? 1 : 0);
            for (int y = 0 ; y < 10 ; y++) {
                for (int x = 0 ; x < 11 ; x++) {
                    matrix[x][y].getStyleClass().setAll("text-off", getSkinnable().getColor().STYLE_CLASS);
                }
            }
            p1.getStyleClass().setAll("dot-off", getSkinnable().getColor().STYLE_CLASS);
            p2.getStyleClass().setAll("dot-off", getSkinnable().getColor().STYLE_CLASS);
            p3.getStyleClass().setAll("dot-off", getSkinnable().getColor().STYLE_CLASS);
            p4.getStyleClass().setAll("dot-off", getSkinnable().getColor().STYLE_CLASS);
        } else if ("LANGUAGE".equals(PROPERTY)) {
            for (int y = 0 ; y < 10 ; y++) {
                for (int x = 0 ; x < 11 ; x++) {
                    matrix[x][y].setText(getSkinnable().getQlock().getMatrix()[y][x]);
                }
            }
        } else if ("HIGHLIGHT".equals(PROPERTY)) {
            highlight.setOpacity(getSkinnable().isHighlightVisible() ? 1 : 0);
        }
    }

    @Override protected double computeMinWidth(final double HEIGHT, double TOP_INSET, double RIGHT_INSET, double BOTTOM_INSET, double LEFT_INSET) {
        return super.computeMinWidth(Math.max(MINIMUM_HEIGHT, HEIGHT - TOP_INSET - BOTTOM_INSET), TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET);
    }
    @Override protected double computeMinHeight(final double WIDTH, double TOP_INSET, double RIGHT_INSET, double BOTTOM_INSET, double LEFT_INSET) {
        return super.computeMinHeight(Math.max(MINIMUM_WIDTH, WIDTH - LEFT_INSET - RIGHT_INSET), TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET);
    }

    @Override protected double computeMaxWidth(final double HEIGHT, double TOP_INSET, double RIGHT_INSET, double BOTTOM_INSET, double LEFT_INSET) {
        return super.computeMaxWidth(Math.min(MAXIMUM_HEIGHT, HEIGHT - TOP_INSET - BOTTOM_INSET), TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET);
    }
    @Override protected double computeMaxHeight(final double WIDTH, double TOP_INSET, double RIGHT_INSET, double BOTTOM_INSET, double LEFT_INSET) {
        return super.computeMaxHeight(Math.min(MAXIMUM_WIDTH, WIDTH - LEFT_INSET - RIGHT_INSET), TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET);
    }

    @Override protected double computePrefWidth(final double HEIGHT, double TOP_INSET, double RIGHT_INSET, double BOTTOM_INSET, double LEFT_INSET) {
        double prefHeight = PREFERRED_HEIGHT;
        if (HEIGHT != -1) {
            prefHeight = Math.max(0, HEIGHT - TOP_INSET - BOTTOM_INSET);
        }
        return super.computePrefWidth(prefHeight, TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET);
    }
    @Override protected double computePrefHeight(final double WIDTH, double TOP_INSET, double RIGHT_INSET, double BOTTOM_INSET, double LEFT_INSET) {
        double prefWidth = PREFERRED_WIDTH;
        if (WIDTH != -1) {
            prefWidth = Math.max(0, WIDTH - LEFT_INSET - RIGHT_INSET);
        }
        return super.computePrefHeight(prefWidth, TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET);
    }


    // ******************** Update ********************************************
    private void updateClock() {
        if (getSkinnable().isSecondsMode()) {
            for (int y = 0 ; y < 10 ; y++) {
                for (int x = 0 ; x < 11 ; x++) {
                    if (secondLeft.dots.containsKey(y) || secondRight.dots.containsKey(y)) {
                        if (secondLeft.dots.containsKey(y) && secondLeft.dots.get(y).contains(x)) {
                            matrix[x][y].getStyleClass().setAll("text-on", getSkinnable().getColor().STYLE_CLASS);
                        } else if (secondRight.dots.containsKey(y) && secondRight.dots.get(y).contains(x)) {
                            matrix[x][y].getStyleClass().setAll("text-on", getSkinnable().getColor().STYLE_CLASS);
                        } else {
                            matrix[x][y].getStyleClass().setAll("text-off", getSkinnable().getColor().STYLE_CLASS);
                        }
                    } else {
                        matrix[x][y].getStyleClass().setAll("text-off", getSkinnable().getColor().STYLE_CLASS);
                    }
                }
            }
        } else {
            for (int y = 0 ; y < 10 ; y++) {
                for (int x = 0 ; x < 11 ; x++) {
                    matrix[x][y].getStyleClass().setAll("text-off", getSkinnable().getColor().STYLE_CLASS);
                }
            }

            for (QlockWord word : getSkinnable().getQlock().getTime(minute, hour)) {
                for (int col = word.getStart() ; col <= word.getStop() ; col++) {
                    matrix[col][word.getRow()].getStyleClass().setAll("text-on", getSkinnable().getColor().STYLE_CLASS);
                }
            }
        }
        int min = minute > 60 ? minute - 60 : (minute < 0 ? minute + 60 : minute);

        if (min %5 == 0) {
            p1.getStyleClass().setAll("dot-off", getSkinnable().getColor().STYLE_CLASS);
            p2.getStyleClass().setAll("dot-off", getSkinnable().getColor().STYLE_CLASS);
            p3.getStyleClass().setAll("dot-off", getSkinnable().getColor().STYLE_CLASS);
            p4.getStyleClass().setAll("dot-off", getSkinnable().getColor().STYLE_CLASS);
        } else if (min %10 == 1 || min %10 == 6) {
            p1.getStyleClass().setAll("dot-on", getSkinnable().getColor().STYLE_CLASS);
        } else if (min %10 == 2 || min %10 == 7) {
            p1.getStyleClass().setAll("dot-on", getSkinnable().getColor().STYLE_CLASS);
            p2.getStyleClass().setAll("dot-on", getSkinnable().getColor().STYLE_CLASS);
        } else if (min %10 == 3 || min %10 == 8) {
            p1.getStyleClass().setAll("dot-on", getSkinnable().getColor().STYLE_CLASS);
            p2.getStyleClass().setAll("dot-on", getSkinnable().getColor().STYLE_CLASS);
            p3.getStyleClass().setAll("dot-on", getSkinnable().getColor().STYLE_CLASS);
        } else if (min %10 == 4 || min %10 == 9) {
            p1.getStyleClass().setAll("dot-on", getSkinnable().getColor().STYLE_CLASS);
            p2.getStyleClass().setAll("dot-on", getSkinnable().getColor().STYLE_CLASS);
            p3.getStyleClass().setAll("dot-on", getSkinnable().getColor().STYLE_CLASS);
            p4.getStyleClass().setAll("dot-on", getSkinnable().getColor().STYLE_CLASS);
        }
    }


    // ******************** Resizing ******************************************
    private void resize() {
        size   = getSkinnable().getWidth() < getSkinnable().getHeight() ? getSkinnable().getWidth() : getSkinnable().getHeight();
        width  = getSkinnable().getWidth();
        height = getSkinnable().getHeight();

        if (width > height) {
            width  = 1 / (ASPECT_RATIO / height);
        } else if (1 / (ASPECT_RATIO / height) > width) {
            height = width;
        }

        if (width > 0 && height > 0) {
            background.setPrefSize(width, height);
            if (width != 0 && height != 0) {
                stainlessBackground.setImage(texture.getImage(width, height));
            }

            p4.setPrefSize(0.012 * width, 0.012 * height);
            p4.setTranslateX(0.044 * width);
            p4.setTranslateY(0.944 * height);

            p3.setPrefSize(0.012 * width, 0.012 * height);
            p3.setTranslateX(0.944 * width);
            p3.setTranslateY(0.944 * height);

            p2.setPrefSize(0.012 * width, 0.012 * height);
            p2.setTranslateX(0.944 * width);
            p2.setTranslateY(0.044 * height);

            p1.setPrefSize(0.012 * width, 0.012 * height);
            p1.setTranslateX(0.044 * width);
            p1.setTranslateY(0.044 * height);

            startX = size * 0.114;
            startY = size * 0.112;
            stepX  = size * 0.072;
            stepY  = size * 0.08;
            font = Font.font("DINfun Pro", FontWeight.NORMAL, FontPosture.REGULAR, size * 0.048);
            for (int y = 0 ; y < 10 ; y++) {
                for (int x = 0 ; x < 11 ; x++) {
                    matrix[x][y].setFont(font);
                    matrix[x][y].setPrefSize(size * 0.048, size * 0.048);
                    matrix[x][y].setTranslateY(startY + y * stepY);
                    matrix[x][y].setTranslateX(startX + x * stepX);
                    matrix[x][y].setTranslateY(startY + y * stepY);
                }
            }

            highlight.setPrefSize(0.8572706909179687 * width, 0.7135147094726563 * height);
            highlight.setTranslateX(0.14224906921386718 * width);
            highlight.setTranslateY(0.28614569091796876 * height);
        }
    }
}

