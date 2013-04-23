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

package eu.hansolo.enzo.splitflap.skin;

import eu.hansolo.enzo.splitflap.FlipEvent;
import eu.hansolo.enzo.splitflap.SplitFlap;
import eu.hansolo.enzo.tools.ShapeConverter;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.CacheHint;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.InnerShadowBuilder;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.FillRule;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.RotateBuilder;
import javafx.util.Duration;

import java.util.ArrayList;


public class SplitFlapSkin extends SkinBase<SplitFlap> implements Skin<SplitFlap> {
    private static final double PREFERRED_WIDTH  = 112;
    private static final double PREFERRED_HEIGHT = 189;
    private static final double MINIMUM_WIDTH    = 5;
    private static final double MINIMUM_HEIGHT   = 5;
    private static final double MAXIMUM_WIDTH    = 1024;
    private static final double MAXIMUM_HEIGHT   = 1024;
    private static double       aspectRatio;
    private final FlipEvent     FLIP_FINISHED;
    private ArrayList<String>   selectedSet;
    private int                 currentSelectionIndex;
    private int                 nextSelectionIndex;
    private double              width;
    private double              height;
    private double              flapHeight;
    private Pane                pane;
    private Region              fixtureRight;
    private Region              fixtureLeft;
    private InnerShadow         innerShadow;
    private InnerShadow         innerHighlight;
    private InnerShadow         reversedInnerShadow;
    private InnerShadow         reversedInnerHighlight;
    private Region              upperBackground;
    private Canvas              upperBackgroundText;
    private GraphicsContext     ctxUpperBackgroundText;
    private Region              lowerBackground;
    private Canvas              lowerBackgroundText;
    private GraphicsContext     ctxLowerBackgroundText;
    private Region              flap;
    private Canvas              flapTextFront;
    private GraphicsContext     ctxTextFront;
    private Canvas              flapTextBack;
    private GraphicsContext     ctxTextBack;
    private LinearGradient      upperTextFill;
    private LinearGradient      lowerTextFill;
    private Font                font;
    private Rotate              rotateFlap;
    private Timeline            timeline;
    private KeyFrame            keyFrame;
    private KeyValue            keyValueFlap;


    // ******************** Constructors **************************************
    public SplitFlapSkin(final SplitFlap CONTROL) {
        super(CONTROL);
        FLIP_FINISHED         = new FlipEvent(this, getSkinnable(), FlipEvent.FLIP_FINISHED);
        selectedSet           = getSkinnable().getSelectedSet();
        currentSelectionIndex = getSkinnable().getSelectedSet().indexOf(getSkinnable().getText());
        nextSelectionIndex    = currentSelectionIndex + 1 > getSkinnable().getSelectedSet().size() ? 0 : currentSelectionIndex + 1;
        aspectRatio           = PREFERRED_HEIGHT / PREFERRED_WIDTH;
        pane                  = new Pane();
        rotateFlap            = RotateBuilder.create().axis(Rotate.X_AXIS).angle(0).build();
        flapHeight            = 0.49206349206349204 * PREFERRED_HEIGHT;
        timeline              = new Timeline();
        init();
        initGraphics();
        registerListeners();
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

        if (getSkinnable().getPrefWidth() != PREFERRED_WIDTH || getSkinnable().getPrefHeight() != PREFERRED_HEIGHT) {
            aspectRatio = getSkinnable().getPrefHeight() / getSkinnable().getPrefWidth();
        }

        selectedSet.addAll(getSkinnable().getSelectedSet());
    }

    private void initGraphics() {
        fixtureRight = new Region();
        fixtureRight.getStyleClass().setAll(getSkinnable().isDarkFixture() ? "fixture-dark" : "fixture");

        fixtureLeft = new Region();
        fixtureLeft.getStyleClass().setAll(getSkinnable().isDarkFixture() ? "fixture-dark" : "fixture");

        innerShadow = InnerShadowBuilder.create()
                                        .offsetY(-0.01 * flapHeight)
                                        .radius(0.01 * flapHeight)
                                        .color(Color.rgb(0, 0, 0, 0.65))
                                        .blurType(BlurType.TWO_PASS_BOX)
                                        .build();
        innerHighlight = InnerShadowBuilder.create()
                                           .offsetY(0.01 * flapHeight)
                                           .radius(0.01 * flapHeight)
                                           .color(Color.rgb(255, 255, 255, 0.65))
                                           .blurType(BlurType.TWO_PASS_BOX)
                                           .input(innerShadow)
                                           .build();

        reversedInnerShadow = InnerShadowBuilder.create()
                                                .offsetY(-0.01 * 0.4920634921 * height)
                                                .radius(0.01 * 0.4920634921 * height)
                                                .color(Color.rgb(0, 0, 0, 0.65))
                                                .blurType(BlurType.TWO_PASS_BOX)
                                                .build();
        reversedInnerHighlight = InnerShadowBuilder.create()
                                                   .offsetY(0.01 * 0.4920634921 * height)
                                                   .radius(0.01 * 0.4920634921 * height)
                                                   .color(Color.rgb(255, 255, 255, 0.65))
                                                   .blurType(BlurType.TWO_PASS_BOX)
                                                   .input(innerShadow)
                                                   .build();

        upperBackground = new Region();
        upperBackground.getStyleClass().setAll(getSkinnable().isSquareFlaps() ? "upper-square" : "upper");
        upperBackground.setEffect(innerHighlight);

        //font = Font.font("Bebas Neue", PREFERRED_HEIGHT);
        //font = Font.loadFont(getClass().getResourceAsStream("/resources/bebasneue.otf"), PREFERRED_HEIGHT);
        font = Font.loadFont(getClass().getResourceAsStream("/resources/droidsansmono.ttf"), PREFERRED_HEIGHT);

        upperTextFill = new LinearGradient(0, 0,
                                           0, flapHeight,
                                           false, CycleMethod.NO_CYCLE,
                                           new Stop(0.0, getSkinnable().getTextColor()),
                                           new Stop(1.0, getSkinnable().getTextColor().darker()));
        upperBackgroundText    = new Canvas();
        upperBackgroundText.setEffect(innerShadow);
        ctxUpperBackgroundText = upperBackgroundText.getGraphicsContext2D();
        ctxUpperBackgroundText.setTextBaseline(VPos.CENTER);
        ctxUpperBackgroundText.setTextAlign(TextAlignment.CENTER);

        lowerBackground = new Region();
        lowerBackground.getStyleClass().setAll(getSkinnable().isSquareFlaps() ? "lower-square" : "lower");
        lowerBackground.setEffect(innerHighlight);

        lowerTextFill = new LinearGradient(0, 0.5079365079365079 * PREFERRED_HEIGHT,
                                           0, 0.5079365079365079 * PREFERRED_HEIGHT + flapHeight,
                                           false, CycleMethod.NO_CYCLE,
                                           new Stop(0.0, getSkinnable().getTextColor().darker()),
                                           new Stop(1.0, getSkinnable().getTextColor()));
        lowerBackgroundText    = new Canvas();
        lowerBackgroundText.setEffect(innerHighlight);
        ctxLowerBackgroundText = lowerBackgroundText.getGraphicsContext2D();
        ctxLowerBackgroundText.setTextBaseline(VPos.CENTER);
        ctxLowerBackgroundText.setTextAlign(TextAlignment.CENTER);

        flap = new Region();
        flap.getStyleClass().setAll(getSkinnable().isSquareFlaps() ? "upper-square" : "upper");
        flap.setEffect(innerHighlight);
        flap.getTransforms().add(rotateFlap);

        flapTextFront = new Canvas();
        flapTextFront.setEffect(innerShadow);
        flapTextFront.getTransforms().add(rotateFlap);
        ctxTextFront  = flapTextFront.getGraphicsContext2D();
        ctxTextFront.setTextBaseline(VPos.CENTER);
        ctxTextFront.setTextAlign(TextAlignment.CENTER);

        flapTextBack  = new Canvas();
        flapTextBack.setEffect(reversedInnerHighlight);
        flapTextBack.getTransforms().add(rotateFlap);
        flapTextBack.setVisible(false);
        ctxTextBack   = flapTextBack.getGraphicsContext2D();
        ctxTextBack.setTextBaseline(VPos.CENTER);
        ctxTextBack.setTextAlign(TextAlignment.CENTER);

        pane.getChildren().setAll(fixtureRight,
                                  fixtureLeft,
                                  upperBackground,
                                  lowerBackground,
                                  upperBackgroundText,
                                  lowerBackgroundText,
                                  flap,
                                  flapTextFront,
                                  flapTextBack);

        getChildren().setAll(pane);
        resize();
    }

    private void registerListeners() {
        getSkinnable().widthProperty().addListener(observable -> { handleControlPropertyChanged("RESIZE"); });
        getSkinnable().heightProperty().addListener(observable -> { handleControlPropertyChanged("RESIZE"); });
        getSkinnable().prefWidthProperty().addListener(observable -> { handleControlPropertyChanged("PREF_SIZE"); });
        getSkinnable().prefHeightProperty().addListener(observable -> { handleControlPropertyChanged("PREF_SIZE"); });
        getSkinnable().textProperty().addListener(observable -> { handleControlPropertyChanged("TEXT"); });
        getSkinnable().textColorProperty().addListener(observable -> { handleControlPropertyChanged("TEXT_COLOR"); });
        getSkinnable().darkFixtureProperty().addListener(observable -> { handleControlPropertyChanged("DARK_FIXTURE"); });
        getSkinnable().squareFlapsProperty().addListener(observable -> { handleControlPropertyChanged("SQUARE_FLAPS"); });

        getSkinnable().getStyleClass().addListener(new ListChangeListener<String>() {
            @Override public void onChanged(Change<? extends String> change) {
                resize();
            }
        });

        rotateFlap.angleProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> ov, Number oldAngle, Number newAngle) {
                if (newAngle.doubleValue() > 90) {
                    flapTextFront.setVisible(false);
                    flapTextBack.setVisible(true);
                    flap.setEffect(reversedInnerHighlight);
                }
                if (newAngle.doubleValue() < 90) {
                    // frontside text visible = true
                }
            }
        });

        timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override public void handle(final ActionEvent EVENT) {
                getSkinnable().fireEvent(FLIP_FINISHED);
                flap.setCache(false);
                flap.setCacheShape(false);
                if (Double.compare(rotateFlap.getAngle(), 180) == 0) {
                    flap.setEffect(innerHighlight);
                    rotateFlap.setAngle(0);
                    flapTextBack.setVisible(false);
                    flapTextFront.setVisible(true);
                    refreshTextCtx();
                    if (!getSkinnable().getText().equals(selectedSet.get(currentSelectionIndex))) {
                        flipForward();
                    }
                } else if(Double.compare(rotateFlap.getAngle(), 0) == 0) {
                    rotateFlap.setAngle(180);
                }
            }
        });
    }


    // ******************** Methods *******************************************
    protected void handleControlPropertyChanged(final String PROPERTY) {
        if ("RESIZE".equals(PROPERTY)) {
            resize();
        } else if ("PREF_SIZE".equals(PROPERTY)) {
            aspectRatio = getSkinnable().getPrefHeight() / getSkinnable().getPrefWidth();
        } else if ("TEXT".equals(PROPERTY)) {
            flipForward();
        } else if ("TEXT_COLOR".equals(PROPERTY)) {
            refreshTextCtx();
        } else if ("CHARACTER_SET".equals(PROPERTY)) {
            selectedSet.clear();
            for (String text : getSkinnable().getSelectedSet()) {
                selectedSet.add(text);
            }
        } else if ("DARK_FIXTURE".equals(PROPERTY)) {
            fixtureLeft.getStyleClass().setAll(getSkinnable().isDarkFixture() ? "fixture-left-dark" : "fixture-left");
            fixtureRight.getStyleClass().setAll(getSkinnable().isDarkFixture() ? "fixture-right-dark" : "fixture-right");
        } else if ("SQUARE_FLAPS".equals(PROPERTY)) {
            upperBackground.getStyleClass().setAll(getSkinnable().isSquareFlaps() ? "upper-square" : "upper");
            lowerBackground.getStyleClass().setAll(getSkinnable().isSquareFlaps() ? "lower-square" : "lower");
            flap.getStyleClass().setAll(getSkinnable().isSquareFlaps() ? "upper-square" : "upper");
        }
    }

    @Override protected double computeMinWidth(final double HEIGHT, int TOP_INSET, int RIGHT_INSET, int BOTTOM_INSET, int LEFT_INSET) {
        return super.computeMinWidth(Math.max(MINIMUM_HEIGHT, HEIGHT - TOP_INSET - BOTTOM_INSET), TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET);
    }
    @Override protected double computeMinHeight(final double WIDTH, int TOP_INSET, int RIGHT_INSET, int BOTTOM_INSET, int LEFT_INSET) {
        return super.computeMinHeight(Math.max(MINIMUM_WIDTH, WIDTH - LEFT_INSET - RIGHT_INSET), TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET);
    }

    @Override protected double computeMaxWidth(final double HEIGHT, int TOP_INSET, int RIGHT_INSET, int BOTTOM_INSET, int LEFT_INSET) {
        return super.computeMaxWidth(Math.min(MAXIMUM_HEIGHT, HEIGHT - TOP_INSET - BOTTOM_INSET), TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET);
    }
    @Override protected double computeMaxHeight(final double WIDTH, int TOP_INSET, int RIGHT_INSET, int BOTTOM_INSET, int LEFT_INSET) {
        return super.computeMaxHeight(Math.min(MAXIMUM_WIDTH, WIDTH - LEFT_INSET - RIGHT_INSET), TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET);
    }

    @Override protected double computePrefWidth(final double HEIGHT, int TOP_INSET, int RIGHT_INSET, int BOTTOM_INSET, int LEFT_INSET) {
        double prefHeight = PREFERRED_HEIGHT;
        if (HEIGHT != -1) {
            prefHeight = Math.max(0, HEIGHT - TOP_INSET - BOTTOM_INSET);
        }
        return super.computePrefWidth(prefHeight, TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET);
    }
    @Override protected double computePrefHeight(final double WIDTH, int TOP_INSET, int RIGHT_INSET, int BOTTOM_INSET, int LEFT_INSET) {
        double prefWidth = PREFERRED_WIDTH;
        if (WIDTH != -1) {
            prefWidth = Math.max(0, WIDTH - LEFT_INSET - RIGHT_INSET);
        }
        return super.computePrefHeight(prefWidth, TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET);
    }

    public void flipForward() {
        timeline.stop();
        flap.setCacheShape(true);
        flap.setCache(true);
        flap.setCacheHint(CacheHint.SPEED);
        currentSelectionIndex++;
        if (currentSelectionIndex >= selectedSet.size()) {
            currentSelectionIndex = 0;
        }
        nextSelectionIndex = currentSelectionIndex + 1;
        if (nextSelectionIndex >= selectedSet.size()) {
            nextSelectionIndex = 0;
        }
        //keyValueFlap = new KeyValue(rotateFlap.angleProperty(), 180, Interpolator.SPLINE(0.5, 0.4, 0.4, 1.0));
        keyValueFlap = new KeyValue(rotateFlap.angleProperty(), 180, Interpolator.EASE_IN);
        keyFrame     = new KeyFrame(Duration.millis(getSkinnable().getFlipTime()), keyValueFlap);
        timeline.getKeyFrames().setAll(keyFrame);
        timeline.play();
    }
    public void flipBackward() {
        timeline.stop();
        //keyValueFlap = new KeyValue(rotateFlap.angleProperty(), -180, Interpolator.SPLINE(0.5, 0.4, 0.4, 1.0));
        keyValueFlap = new KeyValue(rotateFlap.angleProperty(), -180, Interpolator.EASE_IN);
        keyFrame     = new KeyFrame(Duration.millis(getSkinnable().getFlipTime()), keyValueFlap);
        timeline.getKeyFrames().setAll(keyFrame);
        timeline.play();
    }

    private void refreshTextCtx() {
        double flapWidth  = flapTextFront.getWidth();
        double flapHeight = flapTextFront.getHeight();

        upperTextFill = new LinearGradient(0, 0,
                                           0, flapHeight,
                                           false, CycleMethod.NO_CYCLE,
                                           new Stop(0.0, getSkinnable().getTextColor().deriveColor(0.0, 1.0, 0.75, 1.0)),
                                           new Stop(0.97, getSkinnable().getTextColor()),
                                           new Stop(1.0, getSkinnable().getTextColor().deriveColor(0.0, 1.0, 0.65, 1.0)));

        lowerTextFill = new LinearGradient(0, 0,
                                           0, flapHeight,
                                           false, CycleMethod.NO_CYCLE,
                                           new Stop(0.0, getSkinnable().getTextColor().deriveColor(0.0, 1.0, 0.65, 1.0)),
                                           new Stop(0.03, getSkinnable().getTextColor()),
                                           new Stop(1.0, getSkinnable().getTextColor()));

        // set the text on the upper background
        ctxUpperBackgroundText.clearRect(0, 0, flapWidth, flapHeight);
        ctxUpperBackgroundText.setFill(upperTextFill);
        //ctxUpperBackgroundText.fillText(selectedSet.get(nextSelectionIndex), width * 0.5, height * 0.55);
        ctxUpperBackgroundText.fillText(selectedSet.get(nextSelectionIndex), width * 0.5, height * 0.5);

        // set the text on the lower background
        ctxLowerBackgroundText.clearRect(0, 0, flapWidth, flapHeight);
        ctxLowerBackgroundText.setFill(lowerTextFill);
        //ctxLowerBackgroundText.fillText(selectedSet.get(currentSelectionIndex), width * 0.5, height * 0.041);
        ctxLowerBackgroundText.fillText(selectedSet.get(currentSelectionIndex), width * 0.5, 0);

        // set the text on the flap front
        ctxTextFront.clearRect(0, 0, flapWidth, flapHeight);
        ctxTextFront.setFill(upperTextFill);
        //ctxTextFront.fillText(selectedSet.get(currentSelectionIndex), width * 0.5, height * 0.55);
        ctxTextFront.fillText(selectedSet.get(currentSelectionIndex), width * 0.5, height * 0.5);

        // set the text on the flap back
        ctxTextBack.clearRect(0, 0, flapWidth, flapHeight);
        ctxTextBack.setFill(new LinearGradient(0, 0,
                                               0, -flapHeight,
                                               false, CycleMethod.NO_CYCLE,
                                               new Stop(0.0, getSkinnable().getTextColor()),
                                               new Stop(0.97, getSkinnable().getTextColor()),
                                               new Stop(1.0, getSkinnable().getTextColor().deriveColor(0.0, 1.0, 0.65, 1.0))));
        ctxTextBack.save();
        ctxTextBack.scale(1,-1);
        //ctxTextBack.fillText(selectedSet.get(nextSelectionIndex), width * 0.5, -height * 0.45);
        ctxTextBack.fillText(selectedSet.get(nextSelectionIndex), width * 0.5, -height * 0.5);
        ctxTextBack.restore();
    }


    // ******************** Resizing ******************************************
    private void resize() {
        width  = getSkinnable().getWidth();
        height = getSkinnable().getHeight();
        if (getSkinnable().isKeepAspect()) {
            if (aspectRatio * width > height) {
                width  = 1 / (aspectRatio / height);
            } else if (1 / (aspectRatio / height) > width) {
                height = aspectRatio * width;
            }
        }

        if (width > 0 && height > 0) {
            // Autocenter the control
            //getSkinnable().setTranslateX((getSkinnable().getWidth() - width) * 0.5);
            //getSkinnable().setTranslateY((getSkinnable().getHeight() - height) * 0.5);

            flapHeight = 0.49206349206349204 * height;

            fixtureRight.setPrefSize(0.0476190476 * height, 0.164021164021164 * height);
            fixtureRight.setTranslateX(width - 0.0476190476 * height);
            fixtureRight.setTranslateY(0.41798941798941797 * height);

            fixtureLeft.setPrefSize(0.0476190476 * height, 0.164021164021164 * height);
            fixtureLeft.setTranslateY(0.41798941798941797 * height);

            if (width > height && width > 0 && height > 0) {
                final Path UPPER = new Path();
                UPPER.setFillRule(FillRule.EVEN_ODD);
                UPPER.getElements().add(new MoveTo(width, 0.0));
                UPPER.getElements().add(new LineTo(width, 0.4074074074074074 * height));
                UPPER.getElements().add(new LineTo(width - 0.0582010582 * height, 0.4074074074074074 * height));
                UPPER.getElements().add(new LineTo(width - 0.0582010582 * height, 0.49206349206349204 * height));
                UPPER.getElements().add(new LineTo(0.0582010582 * height, 0.49206349206349204 * height));
                UPPER.getElements().add(new LineTo(0.0582010582 * height, 0.4074074074074074 * height));
                UPPER.getElements().add(new LineTo(0.0, 0.4074074074074074 * height));
                UPPER.getElements().add(new LineTo(0.0, 0.0));
                UPPER.getElements().add(new LineTo(width, 0.0));
                UPPER.getElements().add(new ClosePath());
                final String UPPER_SVG = ShapeConverter.shapeToSvgString(UPPER);

                final Path LOWER = new Path();
                LOWER.setFillRule(FillRule.EVEN_ODD);
                LOWER.getElements().add(new MoveTo(width, height));
                LOWER.getElements().add(new LineTo(width, 0.5925925925925926 * height));
                LOWER.getElements().add(new LineTo(width - 0.0582010582 * height, 0.5925925925925926 * height));
                LOWER.getElements().add(new LineTo(width - 0.0582010582 * height, 0.5079365079365079 * height));
                LOWER.getElements().add(new LineTo(0.0582010582 * height, 0.5079365079365079 * height));
                LOWER.getElements().add(new LineTo(0.0582010582 * height, 0.5925925925925926 * height));
                LOWER.getElements().add(new LineTo(0.0, 0.5925925925925926 * height));
                LOWER.getElements().add(new LineTo(0.0, height));
                LOWER.getElements().add(new LineTo(width, height));
                LOWER.getElements().add(new ClosePath());
                final String LOWER_SVG = ShapeConverter.shapeToSvgString(LOWER);

                upperBackground.setStyle(new StringBuilder("-fx-shape:").append("\"").append(UPPER_SVG).append("\";").toString());
                lowerBackground.setStyle(new StringBuilder("-fx-shape:").append("\"").append(LOWER_SVG).append("\";").toString());
                flap.setStyle(new StringBuilder("-fx-shape:").append("\"").append(UPPER_SVG).append("\";").toString());
            }

            upperBackground.setPrefSize(width, flapHeight);
            lowerBackground.setPrefSize(width, flapHeight);
            lowerBackground.setTranslateY(0.5079365079365079 * height);

            //font = Font.font("Bebas Neue", height * 0.9);
            font = Font.font("Droid Sans Mono", height * 0.75);

            upperBackgroundText.setWidth(width);
            upperBackgroundText.setHeight(flapHeight);
            lowerBackgroundText.setWidth(width);
            lowerBackgroundText.setHeight(flapHeight);
            //lowerBackgroundText.setTranslateY(0.5079365079365079 * height);
            lowerBackgroundText.setTranslateY(0.5 * height);

            flap.setPrefSize(width, flapHeight);
            rotateFlap.setPivotY(height * 0.5);

            flapTextFront.setWidth(width);
            flapTextFront.setHeight(flapHeight);
            flapTextBack.setWidth(width);
            flapTextBack.setHeight(flapHeight);

            ctxUpperBackgroundText.setFont(font);
            ctxLowerBackgroundText.setFont(font);
            ctxTextFront.setFont(font);
            ctxTextBack.setFont(font);

            refreshTextCtx();

            innerShadow.setOffsetY(-0.01 * flapHeight);
            innerShadow.setRadius(0.01 * flapHeight);
            innerHighlight.setOffsetY(0.01 * flapHeight);
            innerHighlight.setRadius(0.01 * flapHeight);

            reversedInnerShadow.setOffsetY(-0.01 * 0.4920634921 * height);
            reversedInnerShadow.setRadius(0.01 * 0.4920634921 * height);
            reversedInnerHighlight.setOffsetY(0.01 * 0.4920634921 * height);
            reversedInnerHighlight.setRadius(0.01 * 0.4920634921 * height);
        }
    }
}
