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

package eu.hansolo.enzo.qlocktwo.skin;

import com.sun.javafx.scene.control.skin.BehaviorSkinBase;
import eu.hansolo.enzo.qlocktwo.QlockTwo;
import eu.hansolo.enzo.qlocktwo.QlockWord;
import eu.hansolo.enzo.qlocktwo.behavior.QlockTwoBehavior;
import eu.hansolo.enzo.tools.BrushedMetalPaint;
import javafx.animation.AnimationTimer;
import javafx.collections.ListChangeListener;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RegionBuilder;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.util.Calendar;


public class QlockTwoSkin extends BehaviorSkinBase<QlockTwo, QlockTwoBehavior> {
    private static final double   DEFAULT_WIDTH  = 200;
    private static final double   DEFAULT_HEIGHT = 200;
    private static final double   MINIMUM_WIDTH  = 50;
    private static final double   MINIMUM_HEIGHT = 50;
    private static final double   MAXIMUM_WIDTH  = 1024;
    private static final double   MAXIMUM_HEIGHT = 1024;
    private static final double   ASPECT_RATIO   = 1.0;
    private QlockTwo              control;
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
        super(CONTROL, new QlockTwoBehavior(CONTROL));
        control              = CONTROL;
        hour                 = 0;
        minute               = 0;
        second               = 0;
        secondLeft           = QlockTwo.SecondsLeft.ZERO;
        secondRight          = QlockTwo.SecondsRight.ZERO;
        oldMinute            = 0;
        timeZoneOffsetHour   = 0;
        timeZoneOffsetMinute = 0;
        texture = new BrushedMetalPaint(Color.web("#888888"));
        stainlessBackground = new ImageView();
        pane                 = new Pane();
        lastTimerCall        = System.nanoTime();
        timer = new AnimationTimer() {
            @Override public void handle(long now) {
                if (now > lastTimerCall + 1_000_000_000l) {
                    Calendar cal = Calendar.getInstance();

                    // Hours
                    hour = cal.get(Calendar.HOUR) - timeZoneOffsetHour;// + ((java.util.Calendar.getInstance().get(java.util.Calendar.DST_OFFSET)) / 3600000);

                    // Minutes
                    minute = cal.get(Calendar.MINUTE) + timeZoneOffsetMinute;

                    if (oldMinute < minute || (oldMinute == 59 && minute == 0)) {
                        updateClock();
                        oldMinute = minute;
                    }

                    // SecondsRight
                    if (control.isSecondsMode()) {
                        second = cal.get(Calendar.SECOND);
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
        if (Double.compare(control.getPrefWidth(), 0.0) <= 0 || Double.compare(control.getPrefHeight(), 0.0) <= 0 ||
            Double.compare(control.getWidth(), 0.0) <= 0 || Double.compare(control.getHeight(), 0.0) <= 0) {
            if (control.getPrefWidth() > 0 && control.getPrefHeight() > 0) {
                control.setPrefSize(control.getPrefWidth(), control.getPrefHeight());
            } else {
                control.setPrefSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
            }
        }

        if (Double.compare(control.getMinWidth(), 0.0) <= 0 || Double.compare(control.getMinHeight(), 0.0) <= 0) {
            control.setMinSize(MINIMUM_WIDTH, MINIMUM_HEIGHT);
        }

        if (Double.compare(control.getMaxWidth(), 0.0) <= 0 || Double.compare(control.getMaxHeight(), 0.0) <= 0) {
            control.setMaxSize(MAXIMUM_WIDTH, MAXIMUM_HEIGHT);
        }
    }

    private void initGraphics() {
        startX     = DEFAULT_WIDTH * 0.114;
        startY     = DEFAULT_WIDTH * 0.112;
        stepX      = DEFAULT_WIDTH * 0.072;
        stepY      = DEFAULT_WIDTH * 0.08;
        font       = Font.loadFont(getClass().getResourceAsStream("/resources/din.otf"), DEFAULT_WIDTH * 0.048);
        background = RegionBuilder.create().styleClass("background", control.getColor().STYLE_CLASS).build();

        stainlessBackground.setImage(texture.getImage(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        stainlessBackground.setVisible(control.getColor() == QlockTwo.QlockColor.STAINLESS_STEEL);

        p1 = RegionBuilder.create().styleClass("dot-off").build();
        p2 = RegionBuilder.create().styleClass("dot-off").build();
        p3 = RegionBuilder.create().styleClass("dot-off").build();
        p4 = RegionBuilder.create().styleClass("dot-off").build();

        highlight = RegionBuilder.create().styleClass("highlight").build();

        matrix = new Label[11][10];
        for (int y = 0 ; y < 10 ; y++) {
            for (int x = 0 ; x < 11 ; x++) {
                matrix[x][y] = LabelBuilder.create()
                                          //.textOrigin(VPos.CENTER)
                                          .alignment(Pos.CENTER)
                                          .prefWidth(DEFAULT_WIDTH * 0.048)
                                          .prefHeight(DEFAULT_HEIGHT * 0.048)
                                          .text(control.getQlock().getMatrix()[y][x])
                                          .font(font)
                                          .styleClass("text-off")
                                          .build();
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
        registerChangeListener(control.widthProperty(), "RESIZE");
        registerChangeListener(control.heightProperty(), "RESIZE");
        registerChangeListener(control.prefWidthProperty(), "PREF_SIZE");
        registerChangeListener(control.prefHeightProperty(), "PREF_SIZE");
        registerChangeListener(control.colorProperty(), "COLOR");
        registerChangeListener(control.languageProperty(), "LANGUAGE");
        registerChangeListener(control.highlightVisibleProperty(), "HIGHLIGHT");

        control.getStyleClass().addListener(new ListChangeListener<String>() {
            @Override public void onChanged(Change<? extends String> change) {
                resize();
            }
        });
    }


    // ******************** Methods *******************************************
    @Override protected void handleControlPropertyChanged(final String PROPERTY) {
        super.handleControlPropertyChanged(PROPERTY);
        if ("RESIZE".equals(PROPERTY)) {
            resize();
        } else if ("COLOR".equals(PROPERTY)) {
            background.getStyleClass().setAll("background", control.getColor().STYLE_CLASS);
            stainlessBackground.setVisible(control.getColor() == QlockTwo.QlockColor.STAINLESS_STEEL);
            for (int y = 0 ; y < 10 ; y++) {
                for (int x = 0 ; x < 11 ; x++) {
                    matrix[x][y].getStyleClass().setAll("text-off", control.getColor().STYLE_CLASS);
                }
            }
            p1.getStyleClass().setAll("dot-off", control.getColor().STYLE_CLASS);
            p2.getStyleClass().setAll("dot-off", control.getColor().STYLE_CLASS);
            p3.getStyleClass().setAll("dot-off", control.getColor().STYLE_CLASS);
            p4.getStyleClass().setAll("dot-off", control.getColor().STYLE_CLASS);
        } else if ("LANGUAGE".equals(PROPERTY)) {
            for (int y = 0 ; y < 10 ; y++) {
                for (int x = 0 ; x < 11 ; x++) {
                    matrix[x][y].setText(control.getQlock().getMatrix()[y][x]);
                }
            }
        } else if ("HIGHLIGHT".equals(PROPERTY)) {
            highlight.setVisible(control.isHighlightVisible());
        }
    }

    @Override public final void dispose() {
        control = null;
    }

    @Override protected double computePrefWidth(final double PREF_HEIGHT) {
        double prefHeight = DEFAULT_HEIGHT;
        if (PREF_HEIGHT != -1) {
            prefHeight = Math.max(0, PREF_HEIGHT - control.getInsets().getTop() - control.getInsets().getBottom());
        }
        return super.computePrefWidth(prefHeight);
    }

    @Override protected double computePrefHeight(final double PREF_WIDTH) {
        double prefWidth = DEFAULT_WIDTH;
        if (PREF_WIDTH != -1) {
            prefWidth = Math.max(0, PREF_WIDTH - control.getInsets().getLeft() - control.getInsets().getRight());
        }
        return super.computePrefWidth(prefWidth);
    }

    @Override protected double computeMinWidth(final double MIN_HEIGHT) {
        return super.computeMinWidth(Math.max(MINIMUM_HEIGHT, MIN_HEIGHT - control.getInsets().getTop() - control.getInsets().getBottom()));
    }

    @Override protected double computeMinHeight(final double MIN_WIDTH) {
        return super.computeMinHeight(Math.max(MINIMUM_WIDTH, MIN_WIDTH - control.getInsets().getLeft() - control.getInsets().getRight()));
    }

    @Override protected double computeMaxWidth(final double MAX_HEIGHT) {
        return super.computeMaxWidth(Math.min(MAXIMUM_HEIGHT, MAX_HEIGHT - control.getInsets().getTop() - control.getInsets().getBottom()));
    }

    @Override protected double computeMaxHeight(final double MAX_WIDTH) {
        return super.computeMaxHeight(Math.min(MAXIMUM_WIDTH, MAX_WIDTH - control.getInsets().getLeft() - control.getInsets().getRight()));
    }


    // ******************** Update ********************************************
    private void updateClock() {
        if (control.isSecondsMode()) {
            for (int y = 0 ; y < 10 ; y++) {
                for (int x = 0 ; x < 11 ; x++) {
                    if (secondLeft.dots.containsKey(y) || secondRight.dots.containsKey(y)) {
                        if (secondLeft.dots.containsKey(y) && secondLeft.dots.get(y).contains(x)) {
                            matrix[x][y].getStyleClass().setAll("text-on", control.getColor().STYLE_CLASS);
                        } else if (secondRight.dots.containsKey(y) && secondRight.dots.get(y).contains(x)) {
                            matrix[x][y].getStyleClass().setAll("text-on", control.getColor().STYLE_CLASS);
                        } else {
                            matrix[x][y].getStyleClass().setAll("text-off", control.getColor().STYLE_CLASS);
                        }
                    } else {
                        matrix[x][y].getStyleClass().setAll("text-off", control.getColor().STYLE_CLASS);
                    }
                }
            }
        } else {
            for (int y = 0 ; y < 10 ; y++) {
                for (int x = 0 ; x < 11 ; x++) {
                    matrix[x][y].getStyleClass().setAll("text-off", control.getColor().STYLE_CLASS);
                }
            }

            for (QlockWord word : control.getQlock().getTime(minute, hour)) {
                for (int col = word.getStart() ; col <= word.getStop() ; col++) {
                    matrix[col][word.getRow()].getStyleClass().setAll("text-on", control.getColor().STYLE_CLASS);
                }
            }
        }
        int min = minute > 60 ? minute - 60 : (minute < 0 ? minute + 60 : minute);

        if (min %5 == 0) {
            p1.getStyleClass().setAll("dot-off", control.getColor().STYLE_CLASS);
            p2.getStyleClass().setAll("dot-off", control.getColor().STYLE_CLASS);
            p3.getStyleClass().setAll("dot-off", control.getColor().STYLE_CLASS);
            p4.getStyleClass().setAll("dot-off", control.getColor().STYLE_CLASS);
        } else if (min %10 == 1 || min %10 == 6) {
            p1.getStyleClass().setAll("dot-on", control.getColor().STYLE_CLASS);
        } else if (min %10 == 2 || min %10 == 7) {
            p1.getStyleClass().setAll("dot-on", control.getColor().STYLE_CLASS);
            p2.getStyleClass().setAll("dot-on", control.getColor().STYLE_CLASS);
        } else if (min %10 == 3 || min %10 == 8) {
            p1.getStyleClass().setAll("dot-on", control.getColor().STYLE_CLASS);
            p2.getStyleClass().setAll("dot-on", control.getColor().STYLE_CLASS);
            p3.getStyleClass().setAll("dot-on", control.getColor().STYLE_CLASS);
        } else if (min %10 == 4 || min %10 == 9) {
            p1.getStyleClass().setAll("dot-on", control.getColor().STYLE_CLASS);
            p2.getStyleClass().setAll("dot-on", control.getColor().STYLE_CLASS);
            p3.getStyleClass().setAll("dot-on", control.getColor().STYLE_CLASS);
            p4.getStyleClass().setAll("dot-on", control.getColor().STYLE_CLASS);
        }
    }


    // ******************** Resizing ******************************************
    private void resize() {
        size   = control.getWidth() < control.getHeight() ? control.getWidth() : control.getHeight();
        width  = control.getWidth();
        height = control.getHeight();

        if (width > height) {
            width  = 1 / (ASPECT_RATIO / height);
        } else if (1 / (ASPECT_RATIO / height) > width) {
            height = width;
        }

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

