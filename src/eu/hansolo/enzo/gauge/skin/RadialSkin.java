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

package eu.hansolo.enzo.gauge.skin;

import eu.hansolo.enzo.gauge.Radial;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.control.SkinBase;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadowBuilder;
import javafx.scene.effect.InnerShadowBuilder;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;


/**
 * Created by
 * User: hansolo
 * Date: 15.11.12
 * Time: 00:53
 */
public class RadialSkin extends SkinBase<Radial> {
    private static final double PREFERRED_SIZE = 200;
    private static final double MINIMUM_SIZE   = 50;
    private static final double MAXIMUM_SIZE   = 1024;
    private Radial   control;
    private double   size;
    private Pane     pane;
    private Region   background;
    private Region   ticks;
    private Region   needle;
    private Rotate   needleRotate;
    private double   angleStep;
    private Region   knob;
    private Region   knobHighlight;
    private Text     title;
    private Timeline timeline;


    // ******************** Constructors **************************************
    public RadialSkin(final Radial CONTROL) {
        super(CONTROL);
        control   = CONTROL;
        pane      = new Pane();
        angleStep = 0;
        timeline  = new Timeline();
        init();
        initGraphics();
        registerListeners();
    }

    private void init() {
        if (control.getPrefWidth() <= 0 || control.getPrefHeight() <= 0 ||
            control.getWidth() <= 0 || control.getHeight() <= 0) {
            control.setPrefSize(PREFERRED_SIZE, PREFERRED_SIZE);
        }

        if (control.getMinWidth() <= 0 || control.getMinHeight() <= 0) {
            control.setMinSize(MINIMUM_SIZE, MINIMUM_SIZE);
        }

        if (control.getMaxWidth() <= 0 || control.getMaxHeight() <= 0) {
            control.setMaxSize(MAXIMUM_SIZE, MAXIMUM_SIZE);
        }
    }

    private void initGraphics() {
        background = new Region();
        background.getStyleClass().setAll("background");

        ticks = new Region();
        ticks.getStyleClass().setAll("ticks");

        needle = new Region();
        changeNeedle();
        needleRotate = new Rotate(-control.getRotationOffset());
        needle.getTransforms().setAll(needleRotate);

        knob = new Region();
        knob.getStyleClass().setAll("knob");

        knobHighlight = new Region();
        knobHighlight.getStyleClass().setAll("knob-highlight");

        Group shadowGroup = new Group(needle, knob, knobHighlight);
        shadowGroup.setEffect(DropShadowBuilder.create()
                                               .color(Color.rgb(0, 0, 0, 0.45))
                                               .radius(8)
                                               .blurType(BlurType.GAUSSIAN)
                                               .offsetY(3)
                                               .input(InnerShadowBuilder.create()
                                                                        .color(Color.rgb(255, 255, 255, 0.4))
                                                                        .radius(1)
                                                                        .blurType(BlurType.GAUSSIAN)
                                                                        .offsetY(1)
                                                                        .input(InnerShadowBuilder.create()
                                                                                                 .color(Color.rgb(0, 0, 0, 0.4))
                                                                                                 .radius(1)
                                                                                                 .blurType(BlurType.GAUSSIAN)
                                                                                                 .offsetY(-1)
                                                                                                 .build())
                                                                        .build())
                                               .build());

        title = new Text(control.getTitle());
        title.getStyleClass().setAll("title");

        // Add all nodes
        pane.getChildren().setAll(background, ticks, title, shadowGroup);

        getChildren().setAll(pane);
    }

    private void registerListeners() {
        registerChangeListener(control.widthProperty(),      "RESIZE");
        registerChangeListener(control.heightProperty(),     "RESIZE");
        registerChangeListener(control.valueProperty(),      "VALUE");
        registerChangeListener(control.titleProperty(),      "TITLE");
        registerChangeListener(control.needleTypeProperty(), "NEEDLE");
    }


    // ******************** Methods *******************************************
    @Override protected void handleControlPropertyChanged(final String PROPERTY) {
        super.handleControlPropertyChanged(PROPERTY);
        if ("RESIZE".equals(PROPERTY)) {
            resize();
        } else if ("VALUE".equals(PROPERTY)) {
            rotateNeedle();
        } else if ("TITLE".equals(PROPERTY)) {
            title.setText(control.getTitle());
            resize();
        } else if ("NEEDLE".equals(PROPERTY)) {
            changeNeedle();
            resize();
        }
    }

    @Override public final void dispose() {
        control = null;
    }

    @Override protected double computePrefWidth(final double PREF_HEIGHT) {
        double prefHeight = 100;
        if (PREF_HEIGHT != -1) {
            prefHeight = Math.max(0, PREF_HEIGHT - getInsets().getTop() - getInsets().getBottom());
        }
        return super.computePrefWidth(prefHeight);
    }

    @Override protected double computePrefHeight(final double PREF_WIDTH) {
        double prefWidth = 100;
        if (PREF_WIDTH != -1) {
            prefWidth = Math.max(0, PREF_WIDTH - getInsets().getLeft() - getInsets().getRight());
        }
        return super.computePrefWidth(prefWidth);
    }

    @Override protected double computeMinWidth(final double MIN_HEIGHT) {
        return super.computeMinWidth(Math.max(20, MIN_HEIGHT - getInsets().getTop() - getInsets().getBottom()));
    }

    @Override protected double computeMinHeight(final double MIN_WIDTH) {
        return super.computeMinHeight(Math.max(20, MIN_WIDTH - getInsets().getLeft() - getInsets().getRight()));
    }

    @Override protected double computeMaxWidth(final double MAX_HEIGHT) {
        return super.computeMaxWidth(Math.min(1024, MAX_HEIGHT - getInsets().getTop() - getInsets().getBottom()));
    }

    @Override protected double computeMaxHeight(final double MAX_WIDTH) {
        return super.computeMaxHeight(Math.min(1024, MAX_WIDTH - getInsets().getLeft() - getInsets().getRight()));
    }

    /*
    private void drawCircularTickmarks() {
        size = control.getWidth() < control.getHeight() ? control.getWidth() : control.getHeight();
        final double WIDTH  = size;
        final double HEIGHT = size;
        final Point2D CENTER = new Point2D(size * 0.5, size * 0.5);

        final Group TICKMARKS = new Group();

        final double RADIUS_FACTOR = 0.41;

        final double TEXT_DISTANCE_FACTOR;
        switch (control.getTickLabelOrientation()) {
            case TANGENT:
                TEXT_DISTANCE_FACTOR = 0.07;
                break;
            case HORIZONTAL:
                TEXT_DISTANCE_FACTOR = 0.08;
                break;
            case NORMAL:
            default:
                TEXT_DISTANCE_FACTOR = 0.09;
                break;
        }

        TICKMARKS.getTransforms().clear();
        TICKMARKS.getChildren().clear();
        TICKMARKS.getStyleClass().add("ticks");
        final Shape IBOUNDS = new Rectangle(0, 0, WIDTH, HEIGHT);
        IBOUNDS.setOpacity(0.0);
        TICKMARKS.getChildren().add(IBOUNDS);

        final Path MAJOR_TICK_MARKS_PATH = new Path();
        MAJOR_TICK_MARKS_PATH.setFillRule(FillRule.EVEN_ODD);
        MAJOR_TICK_MARKS_PATH.setSmooth(true);
        MAJOR_TICK_MARKS_PATH.setStrokeType(StrokeType.CENTERED);
        MAJOR_TICK_MARKS_PATH.setStrokeLineCap(StrokeLineCap.ROUND);
        MAJOR_TICK_MARKS_PATH.setStrokeLineJoin(StrokeLineJoin.BEVEL);
        if (WIDTH < 200) {
            MAJOR_TICK_MARKS_PATH.setStrokeWidth(1.0);
        } else {
            MAJOR_TICK_MARKS_PATH.setStrokeWidth(0.005 * WIDTH);
        }
        MAJOR_TICK_MARKS_PATH.getStyleClass().add("ticks");

        final Path MEDIUM_TICK_MARKS_PATH = new Path();
        MEDIUM_TICK_MARKS_PATH.setFillRule(FillRule.EVEN_ODD);
        MEDIUM_TICK_MARKS_PATH.setSmooth(true);
        MEDIUM_TICK_MARKS_PATH.setStrokeType(StrokeType.CENTERED);
        MEDIUM_TICK_MARKS_PATH.setStrokeLineCap(StrokeLineCap.ROUND);
        MEDIUM_TICK_MARKS_PATH.setStrokeLineJoin(StrokeLineJoin.BEVEL);
        if (WIDTH < 200) {
            MEDIUM_TICK_MARKS_PATH.setStrokeWidth(0.5);
        } else {
            MEDIUM_TICK_MARKS_PATH.setStrokeWidth(0.0025 * WIDTH);
        }
        MEDIUM_TICK_MARKS_PATH.getStyleClass().add("ticks");

        final Path MINOR_TICK_MARKS_PATH = new Path();
        MINOR_TICK_MARKS_PATH.setFillRule(FillRule.EVEN_ODD);
        MINOR_TICK_MARKS_PATH.setSmooth(true);
        MINOR_TICK_MARKS_PATH.setStrokeType(StrokeType.CENTERED);
        MINOR_TICK_MARKS_PATH.setStrokeLineCap(StrokeLineCap.ROUND);
        MINOR_TICK_MARKS_PATH.setStrokeLineJoin(StrokeLineJoin.BEVEL);
        if (WIDTH < 200) {
            MINOR_TICK_MARKS_PATH.setStrokeWidth(0.30);
        } else {
            MINOR_TICK_MARKS_PATH.setStrokeWidth(0.0015 * WIDTH);
        }
        MINOR_TICK_MARKS_PATH.getStyleClass().add("ticks");

        MAJOR_TICK_MARKS_PATH.setVisible(true);
        MEDIUM_TICK_MARKS_PATH.setVisible(true);
        MINOR_TICK_MARKS_PATH.setVisible(true);


        final ArrayList<Text> tickMarkLabel = new ArrayList<Text>();

        // Adjust the number format of the ticklabels
        final Radial.NumberFormat numberFormat;
        if (Radial.NumberFormat.AUTO == control.getNumberFormat()) {
            if (Math.abs(control.getMajorTickSpacing()) > 1000) {
                numberFormat = Radial.NumberFormat.SCIENTIFIC;
            } else if (control.getMajorTickSpacing() % 1.0 != 0) {
                numberFormat = Radial.NumberFormat.FRACTIONAL;
            } else {
                numberFormat = Radial.NumberFormat.STANDARD;
            }
        } else {
            numberFormat = control.getNumberFormat();
        }

        // Definitions
        final Font STD_FONT;
        if (WIDTH < 250) {
            STD_FONT = Font.font("Verdana", FontWeight.NORMAL, 8);
        } else {
            STD_FONT = Font.font("Verdana", FontWeight.NORMAL, (0.035 * WIDTH));
        }
        final double TEXT_DISTANCE           = TEXT_DISTANCE_FACTOR * WIDTH;
        final double ticklabelRotationOffset = 0;
        final double MINOR_TICK_LENGTH       = (0.0133333333 * WIDTH);
        final double MEDIUM_TICK_LENGTH      = (0.02 * WIDTH);
        final double MAJOR_TICK_LENGTH       = (0.03 * WIDTH);
        Point2D textPoint;
        Point2D innerPoint;
        Point2D outerPoint;

        // Set some default parameters for the graphics object
        if (control.getTickmarksOffset() != null) {
            TICKMARKS.translateXProperty().set(control.getTickmarksOffset().getX());
            TICKMARKS.translateYProperty().set(control.getTickmarksOffset().getY());
        }

        final double ROTATION_OFFSET = control.getRotationOffset(); // Depends on RadialRange
        final double RADIUS          = WIDTH * RADIUS_FACTOR;
        final double ANGLE_STEP      = (control.getAngleRange() / ((control.getMaxValue() - control.getMinValue()) / control.getMinorTickSpacing()));
        double valueCounter          = control.getMinValue();
        int majorTickCounter         = control.getMaxNoOfMinorTicks() - 1; // Indicator when to draw the major tickmark
        double sinValue;
        double cosValue;

        final Transform transform = Transform.rotate(ROTATION_OFFSET - 180, CENTER.getX(), CENTER.getY());
        TICKMARKS.getTransforms().add(transform);

        // ******************** Create the scale path in a loop ***************
        // recalculate the scaling
        final double LOWER_BOUND = control.getMinValue();
        final double UPPER_BOUND = control.getMaxValue();
        final double STEP_SIZE   = control.getMinorTickSpacing();

        for (double angle = 0, counter = LOWER_BOUND ; Double.compare(counter, UPPER_BOUND) <= 0 ; angle -= ANGLE_STEP, counter += STEP_SIZE) {
            sinValue = Math.sin(Math.toRadians(angle));
            cosValue = Math.cos(Math.toRadians(angle));

            majorTickCounter++;

            // Draw tickmark every major tickmark spacing
            if (majorTickCounter == control.getMaxNoOfMinorTicks()) {
                innerPoint = new Point2D(CENTER.getX() + (RADIUS - MAJOR_TICK_LENGTH) * sinValue, CENTER.getY() + (RADIUS - MAJOR_TICK_LENGTH) * cosValue);
                outerPoint = new Point2D(CENTER.getX() + RADIUS * sinValue, CENTER.getY() + RADIUS * cosValue);
                textPoint  = new Point2D(CENTER.getX() + (RADIUS - TEXT_DISTANCE) * sinValue, CENTER.getY() + (RADIUS - TEXT_DISTANCE) * cosValue);

                // Draw the major TICKMARKS
                drawRadialTicks(MAJOR_TICK_MARKS_PATH, innerPoint, outerPoint);

                // Draw the standard tickmark labels
                if (control.isTickLabelsVisible()) {
                    final Text tickLabel = new Text(numberFormat.format(valueCounter));
                    tickLabel.setFontSmoothingType(FontSmoothingType.LCD);
                    tickLabel.setTextOrigin(VPos.BOTTOM);
                    tickLabel.setBoundsType(TextBoundsType.LOGICAL);
                    tickLabel.getStyleClass().add("ticks");
                    tickLabel.setStroke(null);
                    tickLabel.setFont(STD_FONT);
                    tickLabel.setX(textPoint.getX() - tickLabel.getLayoutBounds().getWidth() / 2.0);
                    tickLabel.setY(textPoint.getY() + tickLabel.getLayoutBounds().getHeight() / 2.0);
                    switch (control.getTickLabelOrientation()) {
                        case NORMAL:
                            if (Double.compare(angle, 0) > 0) {
                                tickLabel.rotateProperty().set(-90 - angle);
                            } else {
                                tickLabel.rotateProperty().set(90 - angle);
                            }
                            break;
                        case HORIZONTAL:
                            tickLabel.rotateProperty().set(180 - control.getRotationOffset());
                            break;
                        case TANGENT:

                        default:
                            tickLabel.rotateProperty().set(180 - angle + ticklabelRotationOffset);
                            break;
                    }

                    // Check if current label is the last of the scale
                    if (Double.compare(valueCounter, UPPER_BOUND) != 0) {
                        tickMarkLabel.add(tickLabel);
                    } else {
                        tickMarkLabel.add(tickLabel);
                    }
                }

                valueCounter += control.getMajorTickSpacing();
                majorTickCounter = 0;
                continue;
            }

            // Draw tickmark every minor tickmark spacing
            innerPoint = new Point2D(CENTER.getX() + (RADIUS - MINOR_TICK_LENGTH) * sinValue, CENTER.getY() + (RADIUS - MINOR_TICK_LENGTH) * cosValue);
            outerPoint = new Point2D(CENTER.getX() + RADIUS * sinValue, CENTER.getY() + RADIUS * cosValue);
            if (control.getMaxNoOfMinorTicks() % 2 == 0 && majorTickCounter == (control.getMaxNoOfMinorTicks() / 2)) {
                // Draw the medium TICKMARKS
                innerPoint = new Point2D(CENTER.getX() + (RADIUS - MEDIUM_TICK_LENGTH) * sinValue,
                    CENTER.getY() + (RADIUS - MEDIUM_TICK_LENGTH) * cosValue);
                outerPoint = new Point2D(CENTER.getX() + RADIUS * sinValue, CENTER.getY() + RADIUS * cosValue);
                drawRadialTicks(MEDIUM_TICK_MARKS_PATH, innerPoint, outerPoint);
            } else if (control.isTickmarksVisible() && control.isMinorTicksVisible()) {
                // Draw the minor TICKMARKS
                drawRadialTicks(MINOR_TICK_MARKS_PATH, innerPoint, outerPoint);
            }
        }
        TICKMARKS.getChildren().addAll(MAJOR_TICK_MARKS_PATH, MEDIUM_TICK_MARKS_PATH, MINOR_TICK_MARKS_PATH);
        TICKMARKS.getChildren().addAll(tickMarkLabel);

        TICKMARKS.setCache(true);
    }

    private static void drawRadialTicks(final Path TICKMARKS_PATH, final Point2D INNER_POINT, final Point2D OUTER_POINT) {
        TICKMARKS_PATH.getElements().add(new MoveTo(INNER_POINT.getX(), INNER_POINT.getY()));
        TICKMARKS_PATH.getElements().add(new LineTo(OUTER_POINT.getX(), OUTER_POINT.getY()));
    }
    */

    private void rotateNeedle() {
        double range          = (control.getMaxValue() - control.getMinValue());
        double angleRange     = control.getAngleRange();
        double rotationOffset = control.getRotationOffset();
        angleStep             = angleRange / range;
        double targetAngle    = control.getValue() * angleStep - rotationOffset;

        if (control.isAnimated()) {
            timeline.stop();
            final KeyValue KEY_VALUE = new KeyValue(needleRotate.angleProperty(), targetAngle, Interpolator.SPLINE(0.5, 0.4, 0.4, 1.0));
            final KeyFrame KEY_FRAME = new KeyFrame(Duration.millis(800), KEY_VALUE);
            timeline.getKeyFrames().setAll(KEY_FRAME);
            timeline.getKeyFrames().add(KEY_FRAME);
            timeline.play();
        } else {
            needleRotate.setAngle(targetAngle);
        }
    }

    private void changeNeedle() {
        switch(control.getNeedleType()) {
            case ARROW:
                needle.getStyleClass().setAll(Radial.STYLE_CLASS_NEEDLE_ARROW);
                break;
            case SCIENTIFIC:
                needle.getStyleClass().setAll(Radial.STYLE_CLASS_NEEDLE_SCIENTIFIC);
                break;
            case BIG:
                needle.getStyleClass().setAll(Radial.STYLE_CLASS_NEEDLE_BIG);
                break;
            default:
                needle.getStyleClass().setAll(Radial.STYLE_CLASS_NEEDLE_STANDARD);
        }
    }

    private void resize() {
        size = control.getWidth() < control.getHeight() ? control.getWidth() : control.getHeight();

        background.setPrefSize(size, size);

        switch (control.getNeedleType()) {
            case ARROW:
                needle.setPrefSize(size * 0.07, size * 0.45);
                break;
            case SCIENTIFIC:
                needle.setPrefSize(size * 0.025, size * 0.45);
                break;
            case BIG:
                needle.setPrefSize(size * 0.08, size * 0.45);
                break;
            default:
                needle.setPrefSize(size * 0.04, size * 0.45);
        }
        needle.setTranslateX((size - needle.getPrefWidth()) * 0.5);
        needle.setTranslateY(size * 0.5 - needle.getPrefHeight());
        needleRotate.setPivotX((needle.getPrefWidth()) * 0.5);
        needleRotate.setPivotY(needle.getPrefHeight());

        knob.setPrefSize(size * 0.085, size * 0.085);
        knob.setTranslateX((size - knob.getPrefWidth()) * 0.5);
        knob.setTranslateY((size - knob.getPrefHeight()) * 0.5);

        knobHighlight.setPrefSize(size * 0.08, size * 0.065);
        knobHighlight.setTranslateX((size - knobHighlight.getPrefWidth()) * 0.5);
        knobHighlight.setTranslateY(size * 0.462);

        title.setFont(Font.font("Verdana", FontWeight.NORMAL, size * 0.06));
        title.setTranslateX((size - title.getLayoutBounds().getWidth()) * 0.5);
        title.setTranslateY(size * 0.3);
    }
}