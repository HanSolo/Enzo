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

package eu.hansolo.enzo.imgsplitflap;

import javafx.animation.AnimationTimer;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.VPos;
import javafx.scene.CacheHint;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by
 * User: hansolo
 * Date: 18.04.13
 * Time: 07:46
 */
public class SplitFlap extends Region {
    public static final String[]   TIME_0_TO_5  = {"1", "2", "3", "4", "5", "0"};
    public static final String[]   TIME_0_TO_9  = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
    public static final String[]   NUMERIC      = {" ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
    public static final String[]   ALPHANUMERIC = {" ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
                                                   "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
                                                   "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                                                   "W", "X", "Y", "Z"};
    public static final String[]   ALPHA        = {" ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                                                   "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
                                                   "V", "W", "X", "Y", "Z"};
    public static final String[]   EXTENDED     = {" ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
                                                   "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
                                                   "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                                                   "W", "X", "Y", "Z", "-", "/", ":", ",", ".", ";", "@",
                                                   "#", "+", "?", "!", "%", "$", "=", "<", ">"};
    private static final Image     BACKGROUND     = new Image(SplitFlap.class.getResource("background.png").toExternalForm());
    private static final Image     FLAP           = new Image(SplitFlap.class.getResource("flap.png").toExternalForm());
    private static final double    DEFAULT_WIDTH  = 234;
    private static final double    DEFAULT_HEIGHT = 402;
    private static final double    MINIMUM_WIDTH  = 5;
    private static final double    MINIMUM_HEIGHT = 5;
    private static final double    MAXIMUM_WIDTH  = 1024;
    private static final double    MAXIMUM_HEIGHT = 1024;
    private static final double    MIN_FLIP_TIME  = 16_666_666.6666667; // 60 fps
    private String[]               selection;
    private ArrayList<String>      selectedSet;
    private int                    currentSelectionIndex;
    private int                    nextSelectionIndex;
    private int                    previousSelectionIndex;
    private String                 _text;
    private StringProperty         text;
    private Color                  _textColor;
    private ObjectProperty<Color>  textColor;
    private double                 width;
    private double                 height;
    private Pane                   pane;
    private ImageView              background;
    private ImageView              flap;
    private Font                   font;
    private Canvas                 upperBackgroundText;
    private GraphicsContext        ctxUpperBackgroundText;
    private Canvas                 lowerBackgroundText;
    private GraphicsContext        ctxLowerBackgroundText;
    private Canvas                 flapTextFront;
    private GraphicsContext        ctxTextFront;
    private Canvas                 flapTextBack;
    private GraphicsContext        ctxTextBack;
    private Rotate                 rotateFlap;
    private Duration               flipTime;
    private boolean                flipping;
    private double                 angleStep;
    private double                 currentAngle;
    private AnimationTimer         timer;


    // ******************** Constructors **************************************
    public SplitFlap() {
        this(EXTENDED, " ");
    }
    public SplitFlap(final String[] SELECTION, final String TEXT) {        
        selection              = SELECTION;
        selectedSet            = new ArrayList<>(64);
        selectedSet.addAll(Arrays.asList(selection));
        _text                  = TEXT;
        _textColor             = Color.WHITE;
        currentSelectionIndex  = 0;
        nextSelectionIndex     = 1;
        previousSelectionIndex = selectedSet.size() - 1;
        pane                   = new Pane();
        rotateFlap             = new Rotate();
        rotateFlap.setAxis(Rotate.X_AXIS);
        rotateFlap.setAngle(0);
        flipTime               = Duration.millis(100);
        flipping               = false;
        angleStep              = 180.0 / ((flipTime.toMillis() * 1_000_000) / (MIN_FLIP_TIME));
        timer                  = new AnimationTimer() {
            @Override public void handle(long now) {
                flip(angleStep);
            }
        };
        init();
        initGraphics();
        registerListeners();
    }


    // ******************** Initialization ************************************
    private void init() {
        if (Double.compare(getPrefWidth(), 0.0) <= 0 || Double.compare(getPrefHeight(), 0.0) <= 0 ||
            Double.compare(getWidth(), 0.0) <= 0 || Double.compare(getHeight(), 0.0) <= 0) {
            if (getPrefWidth() > 0 && getPrefHeight() > 0) {
                setPrefSize(getPrefWidth(), getPrefHeight());
            } else {
                setPrefSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
            }
        }

        if (Double.compare(getMinWidth(), 0.0) <= 0 || Double.compare(getMinHeight(), 0.0) <= 0) {
            setMinSize(MINIMUM_WIDTH, MINIMUM_HEIGHT);
        }

        if (Double.compare(getMaxWidth(), 0.0) <= 0 || Double.compare(getMaxHeight(), 0.0) <= 0) {
            setMaxSize(MAXIMUM_WIDTH, MAXIMUM_HEIGHT);
        }
    }

    private void initGraphics() {
        background = new ImageView(BACKGROUND);
        background.setFitWidth(DEFAULT_WIDTH);
        background.setFitHeight(DEFAULT_HEIGHT);
        background.setPreserveRatio(true);
        background.setSmooth(true);
        background.setCache(true);

        flap = new ImageView(FLAP);
        flap.setFitWidth(DEFAULT_WIDTH * 0.8461538462);
        flap.setFitHeight(DEFAULT_HEIGHT * 0.407960199);
        flap.setPreserveRatio(true);
        flap.setSmooth(true);
        flap.setTranslateX(DEFAULT_HEIGHT * 0.0447761194);
        flap.setTranslateY(DEFAULT_HEIGHT * 0.0447761194);
        flap.setCache(true);
        flap.setCacheHint(CacheHint.ROTATE);
        flap.getTransforms().add(rotateFlap);
        rotateFlap.setPivotY(DEFAULT_HEIGHT * 0.460199005);
        flap.setCache(true);
        flap.setCacheHint(CacheHint.SPEED);

        upperBackgroundText    = new Canvas();
        ctxUpperBackgroundText = upperBackgroundText.getGraphicsContext2D();
        ctxUpperBackgroundText.setTextBaseline(VPos.CENTER);
        ctxUpperBackgroundText.setTextAlign(TextAlignment.CENTER);

        lowerBackgroundText    = new Canvas();
        ctxLowerBackgroundText = lowerBackgroundText.getGraphicsContext2D();
        ctxLowerBackgroundText.setTextBaseline(VPos.CENTER);
        ctxLowerBackgroundText.setTextAlign(TextAlignment.CENTER);

        flapTextFront = new Canvas();
        flapTextFront.getTransforms().add(rotateFlap);
        ctxTextFront  = flapTextFront.getGraphicsContext2D();
        ctxTextFront.setTextBaseline(VPos.CENTER);
        ctxTextFront.setTextAlign(TextAlignment.CENTER);

        flapTextBack  = new Canvas();
        flapTextBack.getTransforms().add(rotateFlap);
        flapTextBack.setVisible(false);

        ctxTextBack   = flapTextBack.getGraphicsContext2D();
        ctxTextBack.setTextBaseline(VPos.CENTER);
        ctxTextBack.setTextAlign(TextAlignment.CENTER);

        pane.getChildren().setAll(background,
                                  upperBackgroundText,
                                  lowerBackgroundText,
                                  flap,
                                  flapTextFront,
                                  flapTextBack);

        getChildren().setAll(pane);
        resize();
    }

    private void registerListeners() {
        widthProperty().addListener(observable -> { resize(); });
        heightProperty().addListener(observable -> { resize(); });
    }


    // ******************** Methods *******************************************
    public final String[] getSelection() {
        return selection;
    }
    public final void setSelection(final String[] SELECTION) {
        selection = SELECTION;
        selectedSet.clear();
        selectedSet.addAll(Arrays.asList(selection));
    }

    public double getFlipTime() {
        return flipTime.toMillis();
    }
    public void setFlipTime(final double FLIP_TIME) {
        flipTime  = Duration.millis(FLIP_TIME);
        angleStep = 180.0 / ((flipTime.toMillis() * 1_000_000) / (MIN_FLIP_TIME));
    }

    public final String getText() {
        return null == text ? _text : text.get();
    }
    public final void setText(final char CHAR) {
        setText(Character.toString(CHAR));
    }
    public final void setText(final String TEXT) {
        if(!TEXT.isEmpty() && selectedSet.contains(TEXT)) {
            if (TEXT.equals(getText())) return;

            if (null == text) {
                _text = TEXT;
            } else {
                text.set(TEXT);
            }
            flipping = true;
            timer.start();
        } else {
            if (null == text) {
                _text = selectedSet.get(0);
            } else {
                text.set(selectedSet.get(0));
            }
            currentSelectionIndex = 0;
            nextSelectionIndex    = currentSelectionIndex + 1 > selectedSet.size() ? 0 : currentSelectionIndex + 1;
        }
    }
    public final StringProperty textProperty() {
        if (null == text) {
            text = new SimpleStringProperty(this, "text", _text);
        }
        return text;
    }
    public final String getNextText() {
        return selectedSet.get(nextSelectionIndex);
    }
    public final String getPreviousText() {
        return selectedSet.get(previousSelectionIndex);
    }

    public final Color getTextColor() {
        return null == textColor ? _textColor : textColor.get();
    }
    public final void setTextColor(final Color TEXT_COLOR) {
        if (null == textColor) {
            _textColor = TEXT_COLOR;
        } else {
            textColor.set(TEXT_COLOR);
        }
    }
    public final ObjectProperty<Color> textColorProperty() {
        if (null == textColor) {
            textColor = new SimpleObjectProperty<>(this, "textColor", _textColor);
        }
        return textColor;
    }
    
    @Override protected double computePrefWidth(final double PREF_HEIGHT) {
        double prefHeight = DEFAULT_HEIGHT;
        if (PREF_HEIGHT != -1) {
            prefHeight = Math.max(0, PREF_HEIGHT - getInsets().getTop() - getInsets().getBottom());
        }
        return super.computePrefWidth(prefHeight);
    }
    @Override protected double computePrefHeight(final double PREF_WIDTH) {
        double prefWidth = DEFAULT_WIDTH;
        if (PREF_WIDTH != -1) {
            prefWidth = Math.max(0, PREF_WIDTH - getInsets().getLeft() - getInsets().getRight());
        }
        return super.computePrefWidth(prefWidth);
    }

    @Override protected double computeMinWidth(final double MIN_HEIGHT) {
        return super.computeMinWidth(Math.max(MINIMUM_HEIGHT, MIN_HEIGHT - getInsets().getTop() - getInsets().getBottom()));
    }
    @Override protected double computeMinHeight(final double MIN_WIDTH) {
        return super.computeMinHeight(Math.max(MINIMUM_WIDTH, MIN_WIDTH - getInsets().getLeft() - getInsets().getRight()));
    }

    @Override protected double computeMaxWidth(final double MAX_HEIGHT) {
        return super.computeMaxWidth(Math.min(MAXIMUM_HEIGHT, MAX_HEIGHT - getInsets().getTop() - getInsets().getBottom()));
    }
    @Override protected double computeMaxHeight(final double MAX_WIDTH) {
        return super.computeMaxHeight(Math.min(MAXIMUM_WIDTH, MAX_WIDTH - getInsets().getLeft() - getInsets().getRight()));
    }

    private void flip(final double ANGLE_STEP) {
        currentAngle += ANGLE_STEP;
        if (Double.compare(currentAngle, 180) >= 0) {
            currentAngle = 0;
            flapTextBack.setVisible(false);
            flapTextFront.setVisible(true);
            currentSelectionIndex++;
            if (currentSelectionIndex >= selectedSet.size()) {
                currentSelectionIndex = 0;
            }
            nextSelectionIndex = currentSelectionIndex + 1;
            if (nextSelectionIndex >= selectedSet.size()) {
                nextSelectionIndex = 0;
            }
            if (selectedSet.get(currentSelectionIndex).equals(getText())) {
                timer.stop();
                flipping = false;
                rotateFlap.setAngle(currentAngle);
            }
            refreshTextCtx();
        }
        if (currentAngle > 90) {
            flapTextFront.setVisible(false);
            flapTextBack.setVisible(true);
        }
        if (flipping) {
            rotateFlap.setAngle(currentAngle);
        }
    }

    private void refreshTextCtx() {
        //double flapX      = flap.getTranslateX();
        //double flapY      = flap.getTranslateY();
        double flapWidth  = flap.getLayoutBounds().getWidth(); //flapTextFront.getWidth();
        double flapHeight = flap.getLayoutBounds().getHeight(); //flapTextFront.getHeight();
        
        // set the text on the upper background
        ctxUpperBackgroundText.clearRect(0, 0, flapWidth, flapHeight);
        ctxUpperBackgroundText.setFill(getTextColor());
        ctxUpperBackgroundText.fillText(selectedSet.get(nextSelectionIndex), width * 0.44, height * 0.43);

        // set the text on the lower background
        ctxLowerBackgroundText.clearRect(0, 0, flapWidth, flapHeight);
        ctxLowerBackgroundText.setFill(getTextColor());
        ctxLowerBackgroundText.fillText(selectedSet.get(currentSelectionIndex), width * 0.44, 0);

        // set the text on the flap front
        ctxTextFront.clearRect(0, 0, flapWidth, flapHeight);
        ctxTextFront.setFill(getTextColor());
        ctxTextFront.fillText(selectedSet.get(currentSelectionIndex), width * 0.44, height * 0.43);

        // set the text on the flap back
        ctxTextBack.clearRect(0, 0, flapWidth, flapHeight);
        ctxTextBack.setFill(getTextColor());
        ctxTextBack.save();
        ctxTextBack.scale(1,-1);
        ctxTextBack.fillText(selectedSet.get(nextSelectionIndex), width * 0.44, -height * 0.405);
        ctxTextBack.restore();
    }


    // ******************** Resizing ******************************************
    private void resize() {
        width  = getWidth();
        height = getHeight();

        if (width > 0 && height > 0) {
            background.setCache(false);
            flap.setCache(false);

            background.setFitWidth(width);
            background.setFitHeight(height);

            flap.setFitWidth(width * 0.8461538462);
            flap.setFitHeight(height * 0.407960199);
            if (width < height) {
                flap.setTranslateX(width * 0.0769230769);
                flap.setTranslateY(width * 0.0769230769);
                rotateFlap.setPivotY(width * 0.715);
            } else {
                flap.setTranslateX(height * 0.0447761194);
                flap.setTranslateY(height * 0.0447761194);
                rotateFlap.setPivotY(height * 0.460199005);
            }

            background.setCache(true);
            flap.setCache(true);
            flap.setCacheHint(CacheHint.ROTATE);

            font = Font.font("Droid Sans Mono", background.getLayoutBounds().getHeight() * 0.7);

            upperBackgroundText.setWidth(flap.getLayoutBounds().getWidth());
            upperBackgroundText.setHeight(flap.getLayoutBounds().getHeight());
            upperBackgroundText.setTranslateX(flap.getTranslateX());
            upperBackgroundText.setTranslateY(flap.getTranslateY());

            lowerBackgroundText.setWidth(flap.getLayoutBounds().getWidth());
            lowerBackgroundText.setHeight(flap.getLayoutBounds().getHeight());
            lowerBackgroundText.setTranslateX(flap.getTranslateX());
            lowerBackgroundText.setTranslateY(0.4701492537 * background.getLayoutBounds().getHeight());

            flapTextFront.setWidth(flap.getLayoutBounds().getWidth());
            flapTextFront.setHeight(flap.getLayoutBounds().getHeight());
            flapTextFront.setTranslateX(flap.getTranslateX());
            flapTextFront.setTranslateY(flap.getTranslateY());

            flapTextBack.setWidth(flap.getLayoutBounds().getWidth());
            flapTextBack.setHeight(flap.getLayoutBounds().getHeight());
            flapTextBack.setTranslateX(flap.getTranslateX());
            flapTextBack.setTranslateY(flap.getTranslateY());

            ctxUpperBackgroundText.setFont(font);
            ctxLowerBackgroundText.setFont(font);
            ctxTextFront.setFont(font);
            ctxTextBack.setFont(font);

            refreshTextCtx();
        }
    }
}
