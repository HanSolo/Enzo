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

package eu.hansolo.enzo.lcd.skin;

import eu.hansolo.enzo.lcd.Lcd;
import javafx.collections.ListChangeListener;
import javafx.geometry.VPos;
import javafx.scene.control.SkinBase;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.InnerShadowBuilder;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;


public class LcdSkin extends SkinBase<Lcd> {
    private static final double        DEFAULT_WIDTH     = 132;
    private static final double        DEFAULT_HEIGHT    = 48;
    private static final double        MINIMUM_WIDTH     = 5;
    private static final double        MINIMUM_HEIGHT    = 5;
    private static final double        MAXIMUM_WIDTH     = 1024;
    private static final double        MAXIMUM_HEIGHT    = 1024;
    private static final double        ASPECT_RATIO      = DEFAULT_HEIGHT / DEFAULT_WIDTH;
    private static final Text          ONE_SEGMENT       = new Text("8");
    private static final DecimalFormat DEC_FORMAT        = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.US));
    private static final boolean       SCIFI_FORMAT      = false;
    private Lcd                        control;
    private double                     width;
    private double                     height;
    private Pane                       pane;
    private Region                     lcdFrame;
    private Region                     lcdMain;
    private InnerShadow                lcdMainInnerShadow0;
    private InnerShadow                lcdMainInnerShadow1;
    private Region                     lcdThreshold;
    private Region                     lcdTrendDown;
    private Region                     lcdTrendFalling;
    private Region                     lcdTrendSteady;
    private Region                     lcdTrendRising;
    private Region                     lcdTrendUp;
    private Text                       lcdValueString;
    private Text                       lcdValueBackgroundString;
    private Text                       lcdUnitString;
    private Text                       lcdTitle;
    private Text                       lcdNumberSystem;
    private Text                       lcdMinMeasuredValue;
    private Text                       lcdMaxMeasuredValue;
    private Text                       lcdFormerValue;
    private double                     lcdValueOffsetLeft;
    private double                     lcdValueOffsetRight;
    private double                     lcdDigitalFontSizeFactor;
    private Font                       lcdValueFont;
    private Font                       lcdUnitFont;
    private Font                       lcdTitleFont;
    private Font                       lcdSmallFont;
    private double                     oneSegmentWidth;
    private double                     widthOfDecimals;
    private double                     availableWidth;
    private int                        noOfSegments;
    private StringBuilder              lcdBackgroundText;
    private StringBuilder              decBuffer;


    // ******************** Constructors **************************************
    public LcdSkin(final Lcd CONTROL) {
        super(CONTROL);
        control                  = CONTROL;
        pane                     = new Pane();
        lcdValueOffsetLeft       = 0.0;
        lcdValueOffsetRight      = 0.0;
        lcdDigitalFontSizeFactor = 1.0;
        lcdBackgroundText        = new StringBuilder();
        decBuffer                = new StringBuilder(16);
        init();
        initGraphics();
        registerListeners();
    }


    // ******************** Initialization ************************************
    private void init() {
        if (control.getPrefWidth() <= 0 || control.getPrefHeight() <= 0 ||
            control.getPrefWidth() <= 0 || control.getHeight() <= 0) {
            if (control.getPrefWidth() > 0 && control.getPrefHeight() > 0) {
                control.setPrefSize(control.getPrefWidth(), control.getPrefHeight());
            } else {
                control.setPrefSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
            }
        }

        if (control.getMinWidth() <= 0 || control.getMinHeight() <= 0 ||
            control.getPrefWidth() <= 0 || getHeight() <= 0) {
            control.setMinSize(MINIMUM_WIDTH, MINIMUM_HEIGHT);
        }

        if (control.getMaxWidth() <= 0 || control.getMaxHeight() <= 0 ||
            control.getPrefWidth() <= 0 || getHeight() <= 0) {
            control.setMaxSize(MAXIMUM_WIDTH, MAXIMUM_HEIGHT);
        }
    }

    private void initGraphics() {
        lcdFrame = new Region();
        lcdFrame.getStyleClass().setAll("lcd-frame");
        lcdFrame.setVisible(control.isBackgroundVisible());

        lcdMain = new Region();
        lcdMain.getStyleClass().setAll("lcd-main");
        lcdMain.setVisible(control.isBackgroundVisible());

        lcdMainInnerShadow0 = InnerShadowBuilder.create()
                                                .offsetX(0.0)
                                                .offsetY(0.0)
                                                .radius(3.0 / 132.0 * DEFAULT_WIDTH)
                                                .color(Color.web("0xffffff80"))
                                                .blurType(BlurType.GAUSSIAN)
                                                .build();
        lcdMainInnerShadow1 = InnerShadowBuilder.create()
                                                .offsetX(0.0)
                                                .offsetY(1.0)
                                                .radius(2.0 / 132.0 * DEFAULT_WIDTH)
                                                .color(Color.web("0x000000a6"))
                                                .blurType(BlurType.GAUSSIAN)
                                                .input(lcdMainInnerShadow0)
                                                .build();
        lcdMain.setEffect(lcdMainInnerShadow1);

        lcdThreshold = new Region();
        lcdThreshold.getStyleClass().setAll("lcd-threshold");
        lcdThreshold.setVisible(control.isThresholdVisible() && control.isThresholdExceeded());

        lcdTrendDown = new Region();
        lcdTrendDown.getStyleClass().setAll("lcd-trend-down");
        lcdTrendDown.setVisible(control.isTrendVisible() && Lcd.Trend.DOWN == control.getTrend());

        lcdTrendFalling = new Region();
        lcdTrendFalling.getStyleClass().setAll("lcd-trend-falling");
        lcdTrendFalling.setVisible(control.isTrendVisible() && Lcd.Trend.FALLING == control.getTrend());

        lcdTrendSteady = new Region();
        lcdTrendSteady.getStyleClass().setAll("lcd-trend-steady");
        lcdTrendSteady.setVisible(control.isTrendVisible() && Lcd.Trend.STEADY == control.getTrend());

        lcdTrendRising = new Region();
        lcdTrendRising.getStyleClass().setAll("lcd-trend-rising");
        lcdTrendRising.setVisible(control.isTrendVisible() && Lcd.Trend.RISING == control.getTrend());

        lcdTrendUp = new Region();
        lcdTrendUp.getStyleClass().setAll("lcd-trend-up");
        lcdTrendUp.setVisible(control.isTrendVisible() && Lcd.Trend.UP == control.getTrend());

        lcdValueBackgroundString = new Text("0.00");
        lcdValueBackgroundString.getStyleClass().setAll("lcd-fg-trsp");
        lcdValueBackgroundString.setVisible(Lcd.LcdFont.LCD == control.getValueFont());

        lcdValueString = new Text("0.00");
        lcdValueString.getStyleClass().setAll("lcd-fg");
        lcdValueBackgroundString.setVisible(Lcd.LcdFont.LCD == control.getValueFont());

        lcdUnitString = new Text(control.getUnit());
        lcdUnitString.getStyleClass().setAll("lcd-fg");
        lcdUnitString.setVisible(control.isUnitVisible());

        lcdTitle = new Text(control.getTitle());
        lcdTitle.getStyleClass().setAll("lcd-fg");
        lcdTitle.setVisible(control.isTitleVisible());

        lcdNumberSystem = new Text(control.getNumberSystem().toString());
        lcdNumberSystem.getStyleClass().setAll("lcd-fg");
        lcdNumberSystem.setVisible(control.isNumberSystemVisible());

        lcdMinMeasuredValue = new Text(Double.toString(control.getMaxValue()));
        lcdMinMeasuredValue.getStyleClass().setAll("lcd-fg");
        lcdMinMeasuredValue.setVisible(control.isMinMeasuredValueVisible());

        lcdMaxMeasuredValue = new Text(Double.toString(control.getMinValue()));
        lcdMaxMeasuredValue.getStyleClass().setAll("lcd-fg");
        lcdMaxMeasuredValue.setVisible(control.isMaxMeasuredValueVisible());

        lcdFormerValue = new Text(Double.toString(control.getFormerValue()));
        lcdFormerValue.getStyleClass().setAll("lcd-fg");

        pane.getChildren().setAll(lcdFrame,
                                  lcdMain,
                                  lcdThreshold,
                                  lcdTrendDown,
                                  lcdTrendFalling,
                                  lcdTrendSteady,
                                  lcdTrendRising,
                                  lcdTrendUp,
                                  lcdValueBackgroundString,
                                  lcdValueString,
                                  lcdUnitString,
                                  lcdTitle,
                                  lcdNumberSystem,
                                  lcdMinMeasuredValue,
                                  lcdMaxMeasuredValue,
                                  lcdFormerValue);

        getChildren().setAll(pane);

        resize();
        updateLcd();
    }

    private void registerListeners() {
        registerChangeListener(control.widthProperty(), "RESIZE");
        registerChangeListener(control.heightProperty(), "RESIZE");
        registerChangeListener(control.titleProperty(), "UPDATE");
        registerChangeListener(control.numberSystemProperty(), "UPDATE");
        registerChangeListener(control.currentValueProperty(), "UPDATE");
        registerChangeListener(control.valueFontProperty(), "FONT");
        registerChangeListener(control.numberSystemVisibleProperty(), "NUMBER_SYSTEM_VISIBLE");
        registerChangeListener(control.backgroundVisibleProperty(), "BACKGROUND_VISIBLE");
        registerChangeListener(control.animationDurationProperty(), "ANIMATION_DURATION");
        registerChangeListener(control.thresholdExceededProperty(), "THRESHOLD_EXCEEDED");
        registerChangeListener(control.minMeasuredValueProperty(), "MIN_MEASURED_VALUE");
        registerChangeListener(control.maxMeasuredValueProperty(), "MAX_MEASURED_VALUE");
        registerChangeListener(control.trendProperty(), "TREND");
        registerChangeListener(control.thresholdExceededProperty(), "THRESHOLD_EXCEEDED");
        registerChangeListener(control.valueVisibleProperty(), "VALUE_VISIBLE");
        registerChangeListener(control.unitVisibleProperty(), "UNIT_VISIBLE");
        registerChangeListener(control.formerValueVisibleProperty(), "FORMER_VALUE_VISIBLE");
        registerChangeListener(control.maxMeasuredValueVisibleProperty(), "MAX_MEASURED_VISIBLE");
        registerChangeListener(control.minMeasuredValueVisibleProperty(), "MIN_MEASURED_VISIBLE");
        control.getStyleClass().addListener(new ListChangeListener<String>() {
            @Override public void onChanged(Change<? extends String> change) {
                resize();
                updateLcd();
            }
        });
    }


    // ******************** Methods *******************************************
    @Override protected void handleControlPropertyChanged(final String PROPERTY) {
        super.handleControlPropertyChanged(PROPERTY);
        if ("UPDATE".equals(PROPERTY)) {
            updateLcd();
        } else if ("RESIZE".equals(PROPERTY)) {
            resize();
            updateLcd();
        } else if ("BACKGROUND_VISIBLE".equals(PROPERTY)) {
            lcdMain.setVisible(control.isBackgroundVisible());
            lcdFrame.setVisible(control.isBackgroundVisible());
        } else if ("TREND".equals(PROPERTY)) {
            updateTrend();
        } else if ("THRESHOLD_EXCEEDED".equals(PROPERTY)) {
            if (control.isThresholdVisible()) {
                lcdThreshold.setVisible(control.isThresholdExceeded());
            }
        } else if ("FONT".equals(PROPERTY)) {
            updateFonts();
        } else if ("VALUE_VISIBLE".equals(PROPERTY)) {
            lcdValueString.setVisible(control.isValueVisible());
        } else if ("UNIT_VISIBLE".equals(PROPERTY)) {
            lcdUnitString.setVisible(control.isUnitVisible());
        } else if ("FORMER_VALUE_VISIBLE".equals(control.isFormerValueVisible())) {
            lcdFormerValue.setVisible(control.isFormerValueVisible());
        } else if ("MAX_MEASURED_VISIBLE".equals(PROPERTY)) {
            lcdMaxMeasuredValue.setVisible(control.isMaxMeasuredValueVisible());
        } else if ("MIN_MEASURED_VISIBLE".equals(PROPERTY)) {
            lcdMinMeasuredValue.setVisible(control.isMinMeasuredValueVisible());
        } else if ("NUMBER_SYSTEM_VISIBLE".equals(PROPERTY)) {
            lcdNumberSystem.setVisible(control.isNumberSystemVisible());
        }
    }

    @Override public final void dispose() {
        control = null;
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


    // ******************** Private Methods ***********************************
    private boolean isNoOfDigitsInvalid() {
        final double AVAILABLE_WIDTH = width - 2 - lcdValueOffsetLeft - lcdValueOffsetRight;
        final double NEEDED_WIDTH    = lcdValueString.getLayoutBounds().getWidth();

        return Double.compare(AVAILABLE_WIDTH, NEEDED_WIDTH) < 0;
    }

    private String formatLcdValue(final double VALUE, final int DECIMALS) {
        decBuffer.setLength(0);
        decBuffer.append("0");

        if (DECIMALS > 0) {
            decBuffer.append(".");
        }

        for (int i = 0; i < DECIMALS; i++) {
            decBuffer.append("0");
        }

        if (SCIFI_FORMAT) {
            decBuffer.append("E0");
        }
        decBuffer.trimToSize();

        DEC_FORMAT.applyPattern(decBuffer.toString());
        return DEC_FORMAT.format(VALUE);
    }

    private void updateFonts() {
        lcdDigitalFontSizeFactor = 1.0;
        switch(control.getValueFont()) {
            case BUS:
                lcdValueFont = Font.loadFont(getClass().getResourceAsStream("/resources/bus.otf"), (0.4583333333 * height));
                break;
            case LCD:
                lcdValueFont = Font.loadFont(getClass().getResourceAsStream("/resources/digital.ttf"), (0.5833333333 * height));
                //lcdDigitalFontSizeFactor = 1.9098073909;
                lcdDigitalFontSizeFactor = 1.3;
                break;
            case PIXEL:
                lcdValueFont = Font.loadFont(getClass().getResourceAsStream("/resources/pixel.ttf"), (0.5208333333 * height));
                break;
            case PHONE_LCD:
                lcdValueFont = Font.loadFont(getClass().getResourceAsStream("/resources/phonelcd.ttf"), (0.4583333333 * height));
                break;
            case STANDARD:
            default:
                lcdValueFont = Font.font("Verdana", FontWeight.NORMAL, (0.5 * height));
                break;
        }
        lcdValueBackgroundString.setFont(lcdValueFont);
        lcdValueBackgroundString.setVisible(Lcd.LcdFont.LCD == control.getValueFont());
        lcdValueString.setFont(lcdValueFont);
        lcdUnitFont  = Font.font(control.getUnitFont(), FontWeight.NORMAL, (0.26 * height));
        lcdTitleFont = Font.font(control.getTitleFont(), FontWeight.BOLD, (0.1666666667 * height));
        lcdSmallFont = Font.font("Verdana", FontWeight.NORMAL, (0.1666666667 * height));
    }

    private void updateTrend() {
        if (control.isTrendVisible()) {
            switch (control.getTrend()) {
                case UP:
                    lcdTrendUp.setVisible(true);
                    lcdTrendRising.setVisible(false);
                    lcdTrendSteady.setVisible(false);
                    lcdTrendFalling.setVisible(false);
                    lcdTrendDown.setVisible(false);
                    break;
                case RISING:
                    lcdTrendUp.setVisible(false);
                    lcdTrendRising.setVisible(true);
                    lcdTrendSteady.setVisible(false);
                    lcdTrendFalling.setVisible(false);
                    lcdTrendDown.setVisible(false);
                    break;
                case STEADY:
                    lcdTrendUp.setVisible(false);
                    lcdTrendRising.setVisible(false);
                    lcdTrendSteady.setVisible(true);
                    lcdTrendFalling.setVisible(false);
                    lcdTrendDown.setVisible(false);
                    break;
                case FALLING:
                    lcdTrendUp.setVisible(false);
                    lcdTrendRising.setVisible(false);
                    lcdTrendSteady.setVisible(false);
                    lcdTrendFalling.setVisible(true);
                    lcdTrendDown.setVisible(false);
                    break;
                case DOWN:
                    lcdTrendUp.setVisible(false);
                    lcdTrendRising.setVisible(false);
                    lcdTrendSteady.setVisible(false);
                    lcdTrendFalling.setVisible(false);
                    lcdTrendDown.setVisible(true);
                    break;
                default:
                    lcdTrendUp.setVisible(false);
                    lcdTrendRising.setVisible(false);
                    lcdTrendSteady.setVisible(false);
                    lcdTrendFalling.setVisible(false);
                    lcdTrendDown.setVisible(false);
                    break;
            }
        }
    }

    private void updateBackgroundText() {
        // Setup the semitransparent background text
        lcdValueBackgroundString.setTextOrigin(VPos.BASELINE);
        lcdValueBackgroundString.setTextAlignment(TextAlignment.RIGHT);

        // Setup the semitransparent background text
        // Width of one segment
        ONE_SEGMENT.setFont(lcdValueFont);
        oneSegmentWidth = ONE_SEGMENT.getLayoutBounds().getWidth();

        // Width of decimals
        widthOfDecimals = control.getDecimals() == 0 ? 0 : control.getDecimals() * oneSegmentWidth + oneSegmentWidth;

        // Available width
        availableWidth = width - 2 - lcdValueOffsetRight - widthOfDecimals;

        // Number of segments
        noOfSegments = (int) Math.floor(availableWidth / oneSegmentWidth);

        // Add segments to background text
        lcdBackgroundText.setLength(0);
        for (int i = 0 ; i < control.getDecimals() ; i++) {
            lcdBackgroundText.append("8");
        }
        if (control.getDecimals() != 0) {
            lcdBackgroundText.insert(0, ".");
        }

        for (int i = 0 ; i < noOfSegments ; i++) {
            lcdBackgroundText.insert(0, "8");
        }
        lcdValueBackgroundString.setText(lcdBackgroundText.toString());
    }

    private void updateLcd() {
        switch (control.getNumberSystem()) {
            case HEXADECIMAL:
                lcdValueString.setText(Integer.toHexString((int) control.getCurrentValue()).toUpperCase());
                break;
            case OCTAL:
                lcdValueString.setText(Integer.toOctalString((int) control.getCurrentValue()).toUpperCase());
                break;
            case DECIMAL:
            default:
                lcdValueString.setText(formatLcdValue(control.getCurrentValue(), control.getDecimals()));
                break;
        }
        lcdNumberSystem.setText(control.getNumberSystem().toString());
        lcdNumberSystem.setX(width - lcdNumberSystem.getLayoutBounds().getWidth() - 0.0416666667 * height);
        lcdNumberSystem.setY(lcdMain.getLayoutY() + lcdMain.getLayoutBounds().getHeight() - 0.0416666667 * height);

        if (isNoOfDigitsInvalid()) {
            lcdValueString.setText("-E-");
        }

        updateBackgroundText();

        // Visualize the lcd semitransparent background text
        if (control.isUnitVisible()) {
            lcdValueBackgroundString.setX(width - 2 - lcdValueBackgroundString.getLayoutBounds().getWidth() - lcdValueOffsetRight);
        } else {
            lcdValueBackgroundString.setX((width - lcdValueBackgroundString.getLayoutBounds().getWidth()) - lcdValueOffsetRight);
        }
        lcdValueBackgroundString.setY(height - (lcdValueBackgroundString.getLayoutBounds().getHeight() * lcdDigitalFontSizeFactor) * 0.5);

        if (control.isUnitVisible()) {
            lcdValueString.setX((width - 2 - lcdValueString.getLayoutBounds().getWidth()) - lcdValueOffsetRight);
        } else {
            lcdValueString.setX((width - lcdValueString.getLayoutBounds().getWidth()) - lcdValueOffsetRight);
        }

        // Update the title
        lcdTitle.setText(control.getTitle());
        lcdTitle.setX((width - lcdTitle.getLayoutBounds().getWidth()) * 0.5);

        // Update the min measured value
        lcdMinMeasuredValue.setText(formatLcdValue(control.getMinMeasuredValue(), control.getMinMeasuredValueDecimals()));

        // Update the max measured value
        lcdMaxMeasuredValue.setText(formatLcdValue(control.getMaxMeasuredValue(), control.getMaxMeasuredValueDecimals()));
        lcdMaxMeasuredValue.setX(width - lcdMaxMeasuredValue.getLayoutBounds().getWidth() - 0.0416666667 * height);

        // Update the former lcd value
        lcdFormerValue.setText(formatLcdValue(control.getFormerValue(), control.getDecimals()));
        lcdFormerValue.setX((width - lcdFormerValue.getLayoutBounds().getWidth()) * 0.5);
    }

    private void resize() {
        width  = control.getWidth();
        height = control.getHeight();
        if (control.isKeepAspect()) {
            if (ASPECT_RATIO * width > height) {
                width  = 1 / (ASPECT_RATIO / height);
            } else if (1 / (ASPECT_RATIO / height) > width) {
                height = ASPECT_RATIO * width;
            }
        }

        lcdFrame.setPrefSize(width, height);

        lcdMain.setPrefSize(width - 2.0, height - 2);
        lcdMain.setTranslateX(1);
        lcdMain.setTranslateY(1);
        lcdMainInnerShadow0.setRadius(3.0 / 132.0 * height);
        lcdMainInnerShadow1.setRadius(2.0 / 132.0 * height);

        lcdThreshold.setPrefSize(0.0955911838647091 * width, 0.26287567615509033 * height);
        lcdThreshold.setTranslateX(0.027961994662429348 * width);
        lcdThreshold.setTranslateY(0.69 * height);

        lcdTrendDown.setPrefSize(0.06718573425755356 * width, 0.1333622932434082 * height);
        lcdTrendDown.setTranslateX(0.1785283377676299 * width);
        lcdTrendDown.setTranslateY(0.8125 * height);

        lcdTrendFalling.setPrefSize(0.06982171896732214 * width, 0.13879903157552084 * height);
        lcdTrendFalling.setTranslateX(0.17915284994876746 * width);
        lcdTrendFalling.setTranslateY(0.8061291376749674 * height);

        lcdTrendSteady.setPrefSize(0.0676060878869259 * width, 0.1342292626698812 * height);
        lcdTrendSteady.setTranslateX(0.18181818181818182 * width);
        lcdTrendSteady.setTranslateY(0.8078853289286295 * height);

        lcdTrendRising.setPrefSize(0.06982171896732214 * width, 0.13879903157552084 * height);
        lcdTrendRising.setTranslateX(0.17915284994876746 * width);
        lcdTrendRising.setTranslateY(0.8050718307495117 * height);

        lcdTrendUp.setPrefSize(0.06718573425755356 * width, 0.1333622932434082 * height);
        lcdTrendUp.setTranslateX(0.1785283377676299 * width);
        lcdTrendUp.setTranslateY(0.8041377067565918 * height);

        updateFonts();

        // Setup the lcd unit
        lcdUnitString.setFont(lcdUnitFont);
        lcdUnitString.setTextOrigin(VPos.BASELINE);
        lcdUnitString.setTextAlignment(TextAlignment.RIGHT);

        lcdUnitString.setText(control.getUnit());
        if (lcdUnitString.visibleProperty().isBound()) {
            lcdUnitString.visibleProperty().unbind();
        }
        lcdUnitString.visibleProperty().bind(control.unitVisibleProperty());

        lcdValueOffsetLeft = height * 0.04;

        if (control.isUnitVisible()) {
            lcdUnitString.setX((width - lcdUnitString.getLayoutBounds().getWidth()) - height * 0.04);
            lcdUnitString.setY(height - (lcdValueString.getLayoutBounds().getHeight() * lcdDigitalFontSizeFactor) * 0.5);
            lcdValueOffsetRight = (lcdUnitString.getLayoutBounds().getWidth() + height * 0.0833333333); // distance between value and unit
            lcdValueString.setX(width - 2 - lcdValueString.getLayoutBounds().getWidth() - lcdValueOffsetRight);
        } else {
            lcdValueOffsetRight = height * 0.0833333333;
            lcdValueString.setX((width - lcdValueString.getLayoutBounds().getWidth()) - lcdValueOffsetRight);
        }
        lcdValueString.setY(height - (lcdValueString.getLayoutBounds().getHeight() * lcdDigitalFontSizeFactor) * 0.5);

        updateBackgroundText();

        // Visualize the lcd semitransparent background text
        if (control.isUnitVisible()) {
            lcdValueBackgroundString.setX(width - 2 - lcdValueBackgroundString.getLayoutBounds().getWidth() - lcdValueOffsetRight);
        } else {
            lcdValueBackgroundString.setX((width - lcdValueBackgroundString.getLayoutBounds().getWidth()) - lcdValueOffsetRight);
        }
        lcdValueBackgroundString.setY(height - (lcdValueBackgroundString.getLayoutBounds().getHeight() * lcdDigitalFontSizeFactor) * 0.5);

        // Setup the font for the lcd title, number system, min measured, max measure and former value
        // Title
        lcdTitle.setFont(lcdTitleFont);
        lcdTitle.setTextOrigin(VPos.BASELINE);
        lcdTitle.setTextAlignment(TextAlignment.CENTER);
        lcdTitle.setText(control.getTitle());
        lcdTitle.setX((width - lcdTitle.getLayoutBounds().getWidth()) * 0.5);
        lcdTitle.setY(lcdMain.getLayoutY() + lcdTitle.getLayoutBounds().getHeight() + 0.04 * height);

        // NumberSystem
        lcdNumberSystem.setFont(lcdSmallFont);
        lcdNumberSystem.setTextOrigin(VPos.BASELINE);
        lcdNumberSystem.setTextAlignment(TextAlignment.RIGHT);
        lcdNumberSystem.setText(control.getNumberSystem().toString());
        lcdNumberSystem.setX(lcdMain.getLayoutX() + (lcdMain.getLayoutBounds().getWidth() - lcdTitle.getLayoutBounds().getWidth()) * 0.5);
        lcdNumberSystem.setY(lcdMain.getLayoutY() + height - 1 - 0.0416666667 * height);

        // Min measured value
        lcdMinMeasuredValue.setFont(lcdSmallFont);
        lcdMinMeasuredValue.setTextOrigin(VPos.BASELINE);
        lcdMinMeasuredValue.setTextAlignment(TextAlignment.RIGHT);
        lcdMinMeasuredValue.setX(lcdMain.getLayoutX() + 0.0416666667 * height);
        lcdMinMeasuredValue.setY(lcdMain.getLayoutY() + lcdMinMeasuredValue.getLayoutBounds().getHeight() + 0.04 * height);

        // Max measured value
        lcdMaxMeasuredValue.setFont(lcdSmallFont);
        lcdMaxMeasuredValue.setTextOrigin(VPos.BASELINE);
        lcdMaxMeasuredValue.setTextAlignment(TextAlignment.RIGHT);
        lcdMaxMeasuredValue.setY(lcdMain.getLayoutY() + lcdMinMeasuredValue.getLayoutBounds().getHeight() + 0.04 * height);

        // Former value
        lcdFormerValue.setFont(lcdSmallFont);
        lcdFormerValue.setTextOrigin(VPos.BASELINE);
        lcdFormerValue.setTextAlignment(TextAlignment.CENTER);
        lcdFormerValue.setX((width - lcdFormerValue.getLayoutBounds().getWidth()) * 0.5);
        lcdFormerValue.setY(lcdMain.getLayoutY() + height - 1 - 0.0416666667 * height);
    }
}
