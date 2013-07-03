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

package eu.hansolo.enzo.gauge.skin;

import eu.hansolo.enzo.gauge.Gauge;
import eu.hansolo.enzo.gauge.GaugeEvent;
import eu.hansolo.enzo.gauge.Marker;
import eu.hansolo.enzo.gauge.Section;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableMap;
import javafx.geometry.Point2D;
import javafx.geometry.VPos;
import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.FillRule;
import javafx.scene.shape.Path;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;

import java.util.Locale;


/**
 * Created by
 * User: hansolo
 * Date: 01.04.13
 * Time: 17:18
 */
public class GaugeSkin extends SkinBase<Gauge> implements Skin<Gauge> {
    private static final double           PREFERRED_WIDTH  = 200;
    private static final double           PREFERRED_HEIGHT = 200;
    private static final double           MINIMUM_WIDTH    = 50;
    private static final double           MINIMUM_HEIGHT   = 50;
    private static final double           MAXIMUM_WIDTH    = 1024;
    private static final double           MAXIMUM_HEIGHT   = 1024;
    private Point2D                       clickPoint;
    private double                        size;
    private Pane                          pane;
    private Region                        background;
    private Canvas                        ticksAndSectionsCanvas;
    private GraphicsContext               ticksAndSections;
    private ObservableMap<Marker, Rotate> markers;
    private Region                        threshold;
    private Rotate                        thresholdRotate;
    private boolean                       thresholdExceeded;
    private Region                        needle;
    private Region                        needleHighlight;
    private Rotate                        needleRotate;
    private Region                        knob;
    private Group                         shadowGroup;
    private DropShadow                    dropShadow;
    private Text                          title;
    private Text                          unit;
    private Text                          value;
    private DropShadow                    valueBlendBottomShadow;
    private InnerShadow                   valueBlendTopShadow;
    private Blend                         valueBlend;
    private Text                          interactiveText;
    private Path                          histogram;
    private double                        angleStep;
    private Timeline                      timeline;


    // ******************** Constructors **************************************
    public GaugeSkin(Gauge gauge) {
        super(gauge);
        angleStep = gauge.getAngleRange() / (gauge.getMaxValue() - gauge.getMinValue());
        markers   = FXCollections.observableHashMap();
        timeline  = new Timeline();
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
    }

    private void initGraphics() {
        valueBlendBottomShadow = new DropShadow();
        valueBlendBottomShadow.setBlurType(BlurType.TWO_PASS_BOX);
        valueBlendBottomShadow.setColor(Color.rgb(255, 255, 255, 0.5));
        valueBlendBottomShadow.setOffsetX(0);
        valueBlendBottomShadow.setOffsetY(0.005 * PREFERRED_WIDTH);
        valueBlendBottomShadow.setRadius(0);

        valueBlendTopShadow = new InnerShadow();
        valueBlendTopShadow.setBlurType(BlurType.TWO_PASS_BOX);
        valueBlendTopShadow.setColor(Color.rgb(0, 0, 0, 0.7));
        valueBlendTopShadow.setOffsetX(0);
        valueBlendTopShadow.setOffsetY(0.005 * PREFERRED_WIDTH);
        valueBlendTopShadow.setRadius(0.005 * PREFERRED_WIDTH);

        valueBlend = new Blend();
        valueBlend.setMode(BlendMode.MULTIPLY);
        valueBlend.setBottomInput(valueBlendBottomShadow);
        valueBlend.setTopInput(valueBlendTopShadow);

        background = new Region();
        background.getStyleClass().setAll("background");

        ticksAndSectionsCanvas = new Canvas(PREFERRED_WIDTH, PREFERRED_HEIGHT);
        ticksAndSections = ticksAndSectionsCanvas.getGraphicsContext2D();

        histogram = new Path();
        histogram.setFillRule(FillRule.NON_ZERO);
        histogram.getStyleClass().add("histogram-fill");

        threshold = new Region();
        threshold.getStyleClass().setAll("threshold");
        thresholdRotate = new Rotate(180 - getSkinnable().getStartAngle());
        threshold.getTransforms().setAll(thresholdRotate);
        thresholdExceeded = false;

        needle = new Region();
        needle.getStyleClass().setAll(Gauge.STYLE_CLASS_NEEDLE_STANDARD);
        needleRotate = new Rotate(180 - getSkinnable().getStartAngle());
        needle.getTransforms().setAll(needleRotate);

        needleHighlight = new Region();
        needleHighlight.setMouseTransparent(true);
        needleHighlight.getStyleClass().setAll("needle-highlight");
        needleHighlight.getTransforms().setAll(needleRotate);

        knob = new Region();
        knob.getStyleClass().setAll("knob");

        dropShadow = new DropShadow();
        dropShadow.setColor(Color.rgb(0, 0, 0, 0.25));
        dropShadow.setBlurType(BlurType.TWO_PASS_BOX);
        dropShadow.setRadius(0.015 * PREFERRED_WIDTH);
        dropShadow.setOffsetY(0.015 * PREFERRED_WIDTH);

        shadowGroup = new Group(needle, needleHighlight);//, knob);
        shadowGroup.setEffect(getSkinnable().isDropShadowEnabled() ? dropShadow : null);

        title = new Text(getSkinnable().getTitle());
        title.setTextOrigin(VPos.CENTER);
        title.getStyleClass().setAll("title");

        unit = new Text(getSkinnable().getUnit());
        unit.setMouseTransparent(true);
        unit.setTextOrigin(VPos.CENTER);
        unit.getStyleClass().setAll("unit");
        unit.setVisible(getSkinnable().isInteractive());

        value = new Text(String.format(Locale.US, "%.1f", (needleRotate.getAngle() + getSkinnable().getStartAngle() - 180) / angleStep));
        value.setMouseTransparent(true);
        value.setTextOrigin(VPos.CENTER);
        value.getStyleClass().setAll("value");
        value.setEffect(getSkinnable().isPlainValue() ? null : valueBlend);
        value.setVisible(!getSkinnable().isInteractive());

        interactiveText = new Text(getSkinnable().getInteractiveText());
        interactiveText.setMouseTransparent(true);
        interactiveText.setTextOrigin(VPos.CENTER);
        interactiveText.getStyleClass().setAll("value");
        interactiveText.setEffect(getSkinnable().isPlainValue() ? null : valueBlend);
        interactiveText.setVisible(getSkinnable().isInteractive());

        // Add all nodes
        pane = new Pane();
        pane.getChildren().setAll(background,
                                  histogram,
                                  ticksAndSectionsCanvas,
                                  threshold,
                                  title,
                                  shadowGroup,
                                  knob,
                                  unit,
                                  value,
                                  interactiveText);

        initMarkers();
        pane.getChildren().addAll(markers.keySet());

        getChildren().setAll(pane);
    }

    private void initMarkers() {
        if (getSkinnable().getMarkers().isEmpty()) return;
        int markerCounter = 0;
        for (Marker marker : getSkinnable().getMarkers()) {
            marker.getStyleClass().add("marker" + markerCounter);
            Rotate markerRotate = new Rotate(180 - getSkinnable().getStartAngle());
            marker.getTransforms().setAll(markerRotate);
            markers.put(marker, markerRotate);
            markerCounter++;
        }
    }

    private void registerListeners() {
        getSkinnable().widthProperty().addListener(observable -> handleControlPropertyChanged("RESIZE") );
        getSkinnable().heightProperty().addListener(observable -> handleControlPropertyChanged("RESIZE") );
        getSkinnable().valueProperty().addListener(observable -> handleControlPropertyChanged("VALUE") );
        getSkinnable().minValueProperty().addListener(observable -> handleControlPropertyChanged("RECALC") );
        getSkinnable().maxValueProperty().addListener(observable -> handleControlPropertyChanged("RECALC") );
        getSkinnable().minMeasuredValueProperty().addListener(observable -> handleControlPropertyChanged("MIN_MEASURED_VALUE") );
        getSkinnable().maxMeasuredValueProperty().addListener(observable -> handleControlPropertyChanged("MAX_MEASURED_VALUE") );
        getSkinnable().tickLabelOrientationProperty().addListener(observable -> handleControlPropertyChanged("RESIZE") );
        getSkinnable().needleTypeProperty().addListener(observable -> handleControlPropertyChanged("NEEDLE_TYPE") );
        getSkinnable().needleColorProperty().addListener(observable -> handleControlPropertyChanged("NEEDLE_COLOR") );
        getSkinnable().animatedProperty().addListener(observable -> handleControlPropertyChanged("ANIMATED") );
        getSkinnable().thresholdProperty().addListener(observable -> handleControlPropertyChanged("THRESHOLD"));
        getSkinnable().angleRangeProperty().addListener(observable -> handleControlPropertyChanged("ANGLE_RANGE") );
        getSkinnable().numberFormatProperty().addListener(observable -> handleControlPropertyChanged("RECALC") );
        getSkinnable().plainValueProperty().addListener(observable -> handleControlPropertyChanged("PLAIN_VALUE") );
        getSkinnable().histogramEnabledProperty().addListener(observable -> handleControlPropertyChanged("HISTOGRAM") );
        getSkinnable().dropShadowEnabledProperty().addListener(observable -> handleControlPropertyChanged("DROP_SHADOW") );
        getSkinnable().interactiveProperty().addListener(observable -> handleControlPropertyChanged("INTERACTIVE") );
        getSkinnable().getSections().addListener((ListChangeListener<Section>) change -> handleControlPropertyChanged("CANVAS_REFRESH"));
        getSkinnable().getMarkers().addListener((ListChangeListener<Marker>) change -> handleControlPropertyChanged("MARKER"));

        needleRotate.angleProperty().addListener(observable -> handleControlPropertyChanged("ANGLE") );
        knob.setOnMousePressed(event -> getSkinnable().setInteractive(!getSkinnable().isInteractive()) );
        needle.setOnMousePressed(event -> moveNeedleByHand(event) );
    }


    // ******************** Methods *******************************************
    protected void handleControlPropertyChanged(final String PROPERTY) {
        if ("RESIZE".equals(PROPERTY)) {
            resize();
        } else if ("VALUE".equals(PROPERTY)) {
            rotateNeedle();
        } else if ("RECALC".equals(PROPERTY)) {
            angleStep = getSkinnable().getAngleRange() / (getSkinnable().getMaxValue() - getSkinnable().getMinValue());
            resize();
        } else if ("ANGLE".equals(PROPERTY)) {
            if (!getSkinnable().isInteractive()) {
                double currentValue = (needleRotate.getAngle() + getSkinnable().getStartAngle() - 180) / angleStep;
                value.setText(String.format(Locale.US, "%.1f", currentValue));
                value.setTranslateX((size - value.getLayoutBounds().getWidth()) * 0.5);
                if (thresholdExceeded) {
                    if (currentValue < getSkinnable().getThreshold()) {
                        getSkinnable().fireGaugeEvent(new GaugeEvent(this, null, GaugeEvent.THRESHOLD_UNDERRUN));
                        thresholdExceeded = false;
                    }
                } else {
                    if (currentValue > getSkinnable().getThreshold()) {
                        getSkinnable().fireGaugeEvent(new GaugeEvent(this, null, GaugeEvent.THRESHOLD_EXCEEDED));
                        thresholdExceeded = true;
                    }
                }
                for (Marker marker : markers.keySet()) {
                    if (marker.isExceeded()) {
                        if (currentValue < marker.getValue()) {
                            marker.fireMarkerEvent(new Marker.MarkerEvent(this, null, Marker.MarkerEvent.MARKER_UNDERRUN));
                            marker.setExceeded(false);
                        }
                    } else {
                        if (currentValue > marker.getValue()) {
                            marker.fireMarkerEvent(new Marker.MarkerEvent(this, null, Marker.MarkerEvent.MARKER_EXCEEDED));
                            marker.setExceeded(true);
                        }
                    }
                }
            }
        } else if ("PLAIN_VALUE".equals(PROPERTY)) {
            value.setEffect(getSkinnable().isPlainValue() ? null : valueBlend);
        } else if ("HISTOGRAM".equals(PROPERTY)) {
            histogram.setVisible(getSkinnable().isHistogramEnabled());
            histogram.setManaged(getSkinnable().isHistogramEnabled());
        } else if ("DROP_SHADOW".equals(PROPERTY)) {
            shadowGroup.setEffect(getSkinnable().isDropShadowEnabled() ? dropShadow : null);
        } else if ("INTERACTIVE".equals(PROPERTY)) {
            value.setVisible(!getSkinnable().isInteractive());
            unit.setVisible(!getSkinnable().isInteractive());
            interactiveText.setText(getSkinnable().getInteractiveText());
            interactiveText.setVisible(getSkinnable().isInteractive());
        } else if ("CANVAS_REFRESH".equals(PROPERTY)) {
            ticksAndSections.clearRect(0, 0, size, size);
            drawSections(ticksAndSections);
            drawTickMarks(ticksAndSections);
        } else if ("THRESHOLD".equals(PROPERTY)) {
            thresholdRotate.setAngle(getSkinnable().getThreshold() * angleStep - 180 - getSkinnable().getStartAngle());
        } else if ("MARKER".equals(PROPERTY)) {
            int markerCounter = 0;
            for (Marker marker : getSkinnable().getMarkers()) {
                if (markers.containsKey(marker)) {
                    markerCounter++;
                    continue;
                }
                marker.getStyleClass().add("marker" + markerCounter);
                Rotate markerRotate = new Rotate(180 - getSkinnable().getStartAngle());
                marker.getTransforms().setAll(markerRotate);
                markers.put(marker, markerRotate);
                pane.getChildren().add(marker);
                markerCounter++;
            }

            drawMarkers();
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


    // ******************** Private Methods ***********************************
    private void rotateNeedle() {
        double range       = (getSkinnable().getMaxValue() - getSkinnable().getMinValue());
        double angleRange  = getSkinnable().getAngleRange();
        angleStep          = angleRange / range;
        double targetAngle = needleRotate.getAngle() + (getSkinnable().getValue() - getSkinnable().getOldValue()) * angleStep;

        if (getSkinnable().isAnimated()) {
            timeline.stop();
            final KeyValue KEY_VALUE      = new KeyValue(needleRotate.angleProperty(), targetAngle, Interpolator.SPLINE(0.5, 0.4, 0.4, 1.0));
            final KeyFrame KEY_FRAME      = new KeyFrame(getSkinnable().getAnimationTime(), KEY_VALUE);
            timeline.getKeyFrames().setAll(KEY_FRAME);
            timeline.play();
        } else {
            needleRotate.setAngle(targetAngle);
        }
    }

    private void moveNeedleByHand(final MouseEvent EVENT) {
        if (getSkinnable().isInteractive()) {
            clickPoint = new Point2D(EVENT.getX(), EVENT.getY());
            //TODO: move the needle manually here
        }
    }

    private void changeNeedle() {
        switch(getSkinnable().getNeedleType()) {
            default:
                needle.getStyleClass().setAll(Gauge.STYLE_CLASS_NEEDLE_STANDARD);
        }
    }

    private void drawTickMarks(final GraphicsContext CTX) {
        if (getSkinnable().isHistogramEnabled()) {
            double xy;
            double wh;
            double step         = 0;
            double OFFSET       = 90 - getSkinnable().getStartAngle();
            double ANGLE_EXTEND = (getSkinnable().getMaxValue()) * angleStep;
            CTX.setStroke(Color.rgb(200, 200, 200));
            CTX.setLineWidth(size * 0.001);
            CTX.setLineCap(StrokeLineCap.BUTT);
            for (int i = 0 ; i < 5 ; i++) {
                xy = (size - (0.435 + step) * size) / 2;
                wh = size * (0.435 + step);
                CTX.strokeArc(xy, xy, wh, wh, -OFFSET, -ANGLE_EXTEND, ArcType.OPEN);
                step += 0.075;
            }
        }

        double sinValue;
        double cosValue;
        double offset = getSkinnable().getStartAngle();
        Point2D center = new Point2D(size * 0.5, size * 0.5);
        for (double angle = 0, counter = getSkinnable().getMinValue() ; Double.compare(counter, getSkinnable().getMaxValue()) <= 0 ; angle -= angleStep, counter++) {
            sinValue = Math.sin(Math.toRadians(angle + offset));
            cosValue = Math.cos(Math.toRadians(angle + offset));

            Point2D innerMainPoint   = new Point2D(center.getX() + size * 0.368 * sinValue, center.getY() + size * 0.368 * cosValue);
            Point2D innerMediumPoint = new Point2D(center.getX() + size * 0.388 * sinValue, center.getY() + size * 0.388 * cosValue);
            Point2D innerMinorPoint  = new Point2D(center.getX() + size * 0.3975 * sinValue, center.getY() + size * 0.3975 * cosValue);
            Point2D outerPoint       = new Point2D(center.getX() + size * 0.432 * sinValue, center.getY() + size * 0.432 * cosValue);
            Point2D textPoint        = new Point2D(center.getX() + size * 0.31 * sinValue, center.getY() + size * 0.31 * cosValue);

            CTX.setStroke(getSkinnable().getTickMarkFill());
            if (counter % getSkinnable().getMajorTickSpace() == 0) {
                // Draw major tickmark
                CTX.setLineWidth(size * 0.0055);
                CTX.strokeLine(innerMainPoint.getX(), innerMainPoint.getY(), outerPoint.getX(), outerPoint.getY());

                // Draw text
                CTX.save();
                CTX.setFont(Font.font("Verdana", FontWeight.NORMAL, 0.045 * size));
                CTX.setTextAlign(TextAlignment.CENTER);
                CTX.setTextBaseline(VPos.CENTER);
                CTX.setFill(getSkinnable().getTickLabelFill());
                CTX.fillText(Integer.toString((int) counter), textPoint.getX(), textPoint.getY());
                CTX.restore();
            } else if (getSkinnable().getMinorTickSpace() % 2 != 0 && counter % 5 == 0) {
                CTX.setLineWidth(size * 0.0035);
                CTX.strokeLine(innerMediumPoint.getX(), innerMediumPoint.getY(), outerPoint.getX(), outerPoint.getY());
            } else if (counter % getSkinnable().getMinorTickSpace() == 0) {
                CTX.setLineWidth(size * 0.00225);
                CTX.strokeLine(innerMinorPoint.getX(), innerMinorPoint.getY(), outerPoint.getX(), outerPoint.getY());
            }
        }
    }

    private final void drawSections(final GraphicsContext CTX) {
        final double xy        = (size - 0.83 * size) / 2;
        final double wh        = size * 0.83;
        final double MIN_VALUE = getSkinnable().getMinValue();
        final double OFFSET = 90 - getSkinnable().getStartAngle();
        for (int i = 0 ; i < getSkinnable().getSections().size() ; i++) {
            final Section SECTION      = getSkinnable().getSections().get(i);
            final double  ANGLE_START  = (SECTION.getStart() - MIN_VALUE) * angleStep;
            final double  ANGLE_EXTEND = (SECTION.getStop() - SECTION.getStart()) * angleStep;
            CTX.save();
            switch(i) {
                case 0: CTX.setStroke(getSkinnable().getSection0Fill()); break;
                case 1: CTX.setStroke(getSkinnable().getSection1Fill()); break;
                case 2: CTX.setStroke(getSkinnable().getSection2Fill()); break;
                case 3: CTX.setStroke(getSkinnable().getSection3Fill()); break;
                case 4: CTX.setStroke(getSkinnable().getSection4Fill()); break;
                case 5: CTX.setStroke(getSkinnable().getSection5Fill()); break;
                case 6: CTX.setStroke(getSkinnable().getSection6Fill()); break;
                case 7: CTX.setStroke(getSkinnable().getSection7Fill()); break;
                case 8: CTX.setStroke(getSkinnable().getSection8Fill()); break;
                case 9: CTX.setStroke(getSkinnable().getSection9Fill()); break;
            }
            CTX.setLineWidth(size * 0.037);
            CTX.setLineCap(StrokeLineCap.BUTT);
            CTX.strokeArc(xy, xy, wh, wh, -(OFFSET + ANGLE_START), -ANGLE_EXTEND, ArcType.OPEN);
            CTX.restore();
        }
    }

    private final void drawMarkers() {
        for (Marker marker : getSkinnable().getMarkers()) {
            marker.setPrefSize(0.0375 * size, 0.05 * size);
            marker.relocate((size - marker.getPrefWidth()) * 0.5, size * 0.04);
            markers.get(marker).setPivotX(marker.getPrefWidth() * 0.5);
            markers.get(marker).setPivotY(size * 0.46);
            markers.get(marker).setAngle(marker.getValue() * angleStep - 180 - getSkinnable().getStartAngle());
        }
    }

    private void resize() {
        size = getSkinnable().getWidth() < getSkinnable().getHeight() ? getSkinnable().getWidth() : getSkinnable().getHeight();

        valueBlendBottomShadow.setOffsetY(0.005 * size);

        valueBlendTopShadow.setOffsetY(0.005 * size);
        valueBlendTopShadow.setRadius(0.005 * size);

        dropShadow.setRadius(0.015 * size);
        dropShadow.setOffsetY(0.015 * size);

        background.setPrefSize(size, size);

        ticksAndSectionsCanvas.setWidth(size);
        ticksAndSectionsCanvas.setHeight(size);
        ticksAndSections.clearRect(0, 0, size, size);
        drawSections(ticksAndSections);
        drawTickMarks(ticksAndSections);
        ticksAndSectionsCanvas.setCache(true);
        ticksAndSectionsCanvas.setCacheHint(CacheHint.QUALITY);

        drawMarkers();

        threshold.setPrefSize(0.05 * size, 0.0425 * size);
        threshold.relocate((size - threshold.getPrefWidth()) * 0.5, size * 0.11);
        thresholdRotate.setPivotX(threshold.getPrefWidth() * 0.5);
        thresholdRotate.setPivotY(size * 0.39);
        thresholdRotate.setAngle(getSkinnable().getThreshold() * angleStep - 180 - getSkinnable().getStartAngle());

        value.setText(String.format(Locale.US, "%.1f", (needleRotate.getAngle() + getSkinnable().getStartAngle() - 180) / angleStep));

        switch (getSkinnable().getNeedleType()) {
            default:
                needle.setPrefSize(size * 0.04, size * 0.425);
        }
        needle.relocate((size - needle.getPrefWidth()) * 0.5, size * 0.5 - needle.getPrefHeight());
        needleRotate.setPivotX(needle.getPrefWidth() * 0.5);
        needleRotate.setPivotY(needle.getPrefHeight());

        needleHighlight.setPrefSize(size * 0.04, size * 0.425);
        needleHighlight.setTranslateX((size - needle.getPrefWidth()) * 0.5);
        needleHighlight.setTranslateY(size * 0.5 - needle.getPrefHeight());

        knob.setPrefSize(size * 0.35, size * 0.35);
        knob.setTranslateX((size - knob.getPrefWidth()) * 0.5);
        knob.setTranslateY((size - knob.getPrefHeight()) * 0.5);

        title.setFont(Font.font("Arial", FontWeight.NORMAL, size * 0.06));
        title.setTranslateX((size - title.getLayoutBounds().getWidth()) * 0.5);
        title.setTranslateY(size * 0.85);

        unit.setFont(Font.font("Arial", FontWeight.NORMAL, size * 0.05));
        unit.setTranslateX((size - unit.getLayoutBounds().getWidth()) * 0.5);
        unit.setTranslateY(size * 0.41);

        value.setFont(Font.font("Arial", FontWeight.BOLD, size * 0.1));
        value.setTranslateX((size - value.getLayoutBounds().getWidth()) * 0.5);
        value.setTranslateY(size * 0.51);

        interactiveText.setFont(Font.font("Arial", FontWeight.BOLD, size * 0.04));
        interactiveText.setTranslateX((size - interactiveText.getLayoutBounds().getWidth()) * 0.5);
        interactiveText.setTranslateY(size * 0.51);
    }
}
