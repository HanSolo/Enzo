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

import com.sun.javafx.scene.control.skin.BehaviorSkinBase;
import eu.hansolo.enzo.lcd.Lcd;
import eu.hansolo.enzo.lcd.behavior.LcdBehavior;
import javafx.collections.ListChangeListener;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.DropShadowBuilder;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.InnerShadowBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.SVGPathBuilder;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Random;


public class LcdSkin extends BehaviorSkinBase<Lcd, LcdBehavior> {
    private static final double        DEFAULT_WIDTH     = 132;
    private static final double        DEFAULT_HEIGHT    = 48;
    private static final double        MINIMUM_WIDTH     = 5;
    private static final double        MINIMUM_HEIGHT    = 5;
    private static final double        MAXIMUM_WIDTH     = 1024;
    private static final double        MAXIMUM_HEIGHT    = 1024;
    private static double              aspectRatio       = DEFAULT_HEIGHT / DEFAULT_WIDTH;
    private static Text                oneSegment        = new Text("8");
    private static final DecimalFormat DEC_FORMAT        = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.US));
    private static final boolean       SCIFI_FORMAT      = false;
    private static final Color         DARK_NOISE_COLOR   = Color.rgb(100, 100, 100, 0.10);
    private static final Color         BRIGHT_NOISE_COLOR = Color.rgb(200, 200, 200, 0.05);
    private static final DropShadow    FOREGROUND_SHADOW  = DropShadowBuilder.create()
                                                                             .offsetX(0).offsetY(1)
                                                                             .color(Color.rgb(0, 0, 0, 0.5))
                                                                             .blurType(BlurType.GAUSSIAN)
                                                                             .radius(2)
                                                                             .build();
    private Lcd                        control;
    private double                     width;
    private double                     height;
    private Pane                       pane;
    private Region                     lcdFrame;
    private Region                     lcdMain;
    private ImageView                  lcdCrystalOverlay;
    private Image                      crystalImage;
    private SVGPath                    lcdMainClip;
    private InnerShadow                lcdMainInnerShadow0;
    private InnerShadow                lcdMainInnerShadow1;
    private Region                     lcdThreshold;
    private Region                     lcdTrendDown;
    private Region                     lcdTrendFalling;
    private Region                     lcdTrendSteady;
    private Region                     lcdTrendRising;
    private Region                     lcdTrendUp;
    private Region                     lcdBattery;
    private Region                     lcdAlarm;
    private Text                       lcdText;
    private Text                       lcdBackgroundText;
    private Text                       lcdUnitText;
    private Text                       lcdTitle;
    private Text                       lcdLowerRightText;
    private Text                       lcdUpperLeftText;
    private Text                       lcdUpperRightText;
    private Text                       lcdLowerCenterText;
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
    private StringBuilder              lcdBackgroundTextBuilder;
    private StringBuilder              decBuffer;
    private Group shadowGroup;


    // ******************** Constructors **************************************
    public LcdSkin(final Lcd CONTROL) {
        super(CONTROL, new LcdBehavior(CONTROL));
        control                  = CONTROL;
        pane                     = new Pane();
        lcdValueOffsetLeft       = 0.0;
        lcdValueOffsetRight      = 0.0;
        lcdDigitalFontSizeFactor = 1.0;
        lcdBackgroundTextBuilder = new StringBuilder();
        decBuffer                = new StringBuilder(16);
        init();
        initGraphics();
        registerListeners();
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

        if (control.getPrefWidth() != DEFAULT_WIDTH || control.getPrefHeight() != DEFAULT_HEIGHT) {
            aspectRatio = control.getPrefHeight() / control.getPrefWidth();
        }
    }

    private void initGraphics() {
        lcdFrame = new Region();
        lcdFrame.getStyleClass().setAll("frame");
        lcdFrame.setVisible(control.isBackgroundVisible());

        lcdMain = new Region();
        lcdMain.getStyleClass().setAll("main");
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

        lcdMainClip = SVGPathBuilder.create()
                                    .content("M 1 5 C 1 3 3 1 5 1 C 5 1 127 1 127 1 C 129 1 131 3 131 5 " +
                                             "C 131 5 131 43 131 43 C 131 45 129 47 127 47 C 127 47 5 47 5 47 " +
                                             "C 3 47 1 45 1 43 C 1 43 1 5 1 5 Z")
                                    .build();
        crystalImage      = createNoiseImage(132, 48, DARK_NOISE_COLOR, BRIGHT_NOISE_COLOR, 8);
        lcdCrystalOverlay = new ImageView(crystalImage);
        lcdCrystalOverlay.setClip(lcdMainClip);
        lcdCrystalOverlay.setVisible(control.isCrystalOverlayVisible());

        lcdThreshold = new Region();
        lcdThreshold.getStyleClass().setAll("threshold");
        lcdThreshold.setVisible(control.isThresholdVisible() && control.isThresholdExceeded());

        lcdTrendDown = new Region();
        lcdTrendDown.getStyleClass().setAll("trend-down");
        lcdTrendDown.setVisible(control.isTrendVisible() && Lcd.Trend.DOWN == control.getTrend());

        lcdTrendFalling = new Region();
        lcdTrendFalling.getStyleClass().setAll("trend-falling");
        lcdTrendFalling.setVisible(control.isTrendVisible() && Lcd.Trend.FALLING == control.getTrend());

        lcdTrendSteady = new Region();
        lcdTrendSteady.getStyleClass().setAll("trend-steady");
        lcdTrendSteady.setVisible(control.isTrendVisible() && Lcd.Trend.STEADY == control.getTrend());

        lcdTrendRising = new Region();
        lcdTrendRising.getStyleClass().setAll("trend-rising");
        lcdTrendRising.setVisible(control.isTrendVisible() && Lcd.Trend.RISING == control.getTrend());

        lcdTrendUp = new Region();
        lcdTrendUp.getStyleClass().setAll("trend-up");
        lcdTrendUp.setVisible(control.isTrendVisible() && Lcd.Trend.UP == control.getTrend());

        lcdBattery = new Region();
        lcdBattery.getStyleClass().setAll("battery-empty");
        lcdBattery.setVisible(control.isBatteryVisible());

        lcdAlarm = new Region();
        lcdAlarm.getStyleClass().setAll("alarm");
        lcdAlarm.setVisible(control.isAlarmVisible());

        lcdBackgroundText = new Text(control.isTextMode() ? control.getText() : Double.toString(control.getValue()));
        lcdBackgroundText.getStyleClass().setAll("fg-trsp");
        lcdBackgroundText.setVisible(Lcd.LcdFont.LCD == control.getValueFont() || Lcd.LcdFont.ELEKTRA == control.getValueFont());

        lcdText = new Text(control.isTextMode() ? control.getText() : Double.toString(control.getValue()));
        lcdText.getStyleClass().setAll("fg");

        lcdUnitText = new Text(control.getUnit());
        lcdUnitText.getStyleClass().setAll("fg");
        lcdUnitText.setVisible(control.isUnitVisible());

        lcdTitle = new Text(control.getTitle());
        lcdTitle.getStyleClass().setAll("fg");
        lcdTitle.setVisible(control.isTitleVisible());

        lcdLowerRightText = control.isNumberSystemVisible() ? new Text(control.getNumberSystem().toString()) : new Text(control.getLowerRightText());
        lcdLowerRightText.getStyleClass().setAll("fg");
        lcdLowerRightText.setVisible(control.isLowerRightTextVisible());

        lcdUpperLeftText = control.isMinMeasuredValueVisible() ? new Text(Double.toString(control.getMaxValue())) : new Text(control.getUpperLeftText());
        lcdUpperLeftText.getStyleClass().setAll("fg");
        lcdUpperLeftText.setVisible(control.isMinMeasuredValueVisible());

        lcdUpperRightText = control.isMaxMeasuredValueVisible() ? new Text(Double.toString(control.getMinValue())) : new Text(control.getUpperRightText());
        lcdUpperRightText.getStyleClass().setAll("fg");
        lcdUpperRightText.setVisible(control.isMaxMeasuredValueVisible());

        lcdLowerCenterText = new Text(control.isFormerValueVisible() ? Double.toString(control.getFormerValue()) : control.getLowerCenterText());
        lcdLowerCenterText.getStyleClass().setAll("fg");

        shadowGroup = new Group();
        shadowGroup.setEffect(control.isForegroundShadowVisible() ? FOREGROUND_SHADOW : null);
        shadowGroup.getChildren().setAll(lcdThreshold,
                                         lcdTrendDown,
                                         lcdTrendFalling,
                                         lcdTrendSteady,
                                         lcdTrendRising,
                                         lcdTrendUp,
                                         lcdBattery,
                                         lcdAlarm,
                                         lcdText,
                                         lcdUnitText,
                                         lcdTitle,
                                         lcdLowerRightText,
                                         lcdUpperLeftText,
                                         lcdUpperRightText,
                                         lcdLowerCenterText);

        pane.getChildren().setAll(lcdFrame,
                                  lcdMain,
                                  lcdCrystalOverlay,
                                  lcdBackgroundText,
                                  shadowGroup);                                  

        getChildren().setAll(pane);

        resize();
        updateLcd();
    }

    private void registerListeners() {
        registerChangeListener(control.widthProperty(), "RESIZE");
        registerChangeListener(control.heightProperty(), "RESIZE");
        registerChangeListener(control.keepAspectProperty(), "RESIZE");
        registerChangeListener(control.titleProperty(), "UPDATE");
        registerChangeListener(control.unitProperty(), "UPDATE");
        registerChangeListener(control.lowerRightTextProperty(), "UPDATE");
        registerChangeListener(control.numberSystemProperty(), "UPDATE");
        registerChangeListener(control.textModeProperty(), "UPDATE");
        registerChangeListener(control.textProperty(), "UPDATE");
        registerChangeListener(control.currentValueProperty(), "UPDATE");
        registerChangeListener(control.lowerCenterTextProperty(), "UPDATE");
        registerChangeListener(control.minMeasuredValueProperty(), "UPDATE");
        registerChangeListener(control.maxMeasuredValueProperty(), "UPDATE");
        registerChangeListener(control.upperLeftTextProperty(), "UPDATE");
        registerChangeListener(control.upperRightTextProperty(), "UPDATE");
        registerChangeListener(control.batteryChargeProperty(), "UPDATE");
        registerChangeListener(control.prefWidthProperty(), "PREF_SIZE");
        registerChangeListener(control.prefHeightProperty(), "PREF_SIZE");
        registerChangeListener(control.valueFontProperty(), "FONT");
        registerChangeListener(control.numberSystemVisibleProperty(), "NUMBER_SYSTEM_VISIBLE");
        registerChangeListener(control.backgroundVisibleProperty(), "BACKGROUND_VISIBLE");
        registerChangeListener(control.crystalOverlayVisibleProperty(), "CRYSTAL_OVERLAY_VISIBLE");
        registerChangeListener(control.foregroundShadowVisibleProperty(), "FOREGROUND_SHADOW_VISIBLE");
        registerChangeListener(control.animationDurationProperty(), "ANIMATION_DURATION");
        registerChangeListener(control.thresholdExceededProperty(), "THRESHOLD_EXCEEDED");
        registerChangeListener(control.trendProperty(), "TREND");
        registerChangeListener(control.thresholdExceededProperty(), "THRESHOLD_EXCEEDED");
        registerChangeListener(control.valueVisibleProperty(), "VALUE_VISIBLE");
        registerChangeListener(control.unitVisibleProperty(), "UNIT_VISIBLE");
        registerChangeListener(control.lowerCenterTextVisibleProperty(), "LOWER_CENTER_VISIBLE");
        registerChangeListener(control.lowerRightTextVisibleProperty(), "LOWER_RIGHT_VISIBLE");
        registerChangeListener(control.upperLeftTextVisibleProperty(), "UPPER_LEFT_VISIBLE");
        registerChangeListener(control.upperRightTextVisibleProperty(), "UPPER_RIGHT_VISIBLE");
        registerChangeListener(control.batteryVisibleProperty(), "BATTERY_VISIBLE");
        registerChangeListener(control.alarmVisibleProperty(), "ALARM_VISIBLE");
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
        } else if ("PREF_SIZE".equals(PROPERTY)) {
            aspectRatio = control.getPrefHeight() / control.getPrefWidth();
        } else if ("BACKGROUND_VISIBLE".equals(PROPERTY)) {
            lcdMain.setVisible(control.isBackgroundVisible());
            lcdCrystalOverlay.setVisible(control.isBackgroundVisible());
            lcdFrame.setVisible(control.isBackgroundVisible());
        } else if ("CRYSTAL_OVERLAY_VISIBLE".equals(PROPERTY)) {
            lcdCrystalOverlay.setVisible(control.isCrystalOverlayVisible());
            resize();
        } else if ("FOREGROUND_SHADOW_VISIBLE".equals(PROPERTY)) {
            shadowGroup.setEffect(control.isForegroundShadowVisible() ? FOREGROUND_SHADOW : null);
        } else if ("TREND".equals(PROPERTY)) {
            updateTrend();
        } else if ("THRESHOLD_EXCEEDED".equals(PROPERTY)) {
            if (control.isThresholdVisible()) {
                lcdThreshold.setVisible(control.isThresholdExceeded());
            }
        } else if ("FONT".equals(PROPERTY)) {
            updateFonts();
        } else if ("VALUE_VISIBLE".equals(PROPERTY)) {
            lcdText.setVisible(control.isValueVisible());
        } else if ("UNIT_VISIBLE".equals(PROPERTY)) {
            lcdUnitText.setVisible(control.isUnitVisible());
        } else if ("FORMER_VALUE_VISIBLE".equals(control.isFormerValueVisible())) {
            lcdLowerCenterText.setVisible(control.isFormerValueVisible());
        } else if ("MAX_MEASURED_VISIBLE".equals(PROPERTY)) {
            lcdUpperRightText.setVisible(control.isMaxMeasuredValueVisible());
        } else if ("MIN_MEASURED_VISIBLE".equals(PROPERTY)) {
            lcdUpperLeftText.setVisible(control.isMinMeasuredValueVisible());
        } else if ("NUMBER_SYSTEM_VISIBLE".equals(PROPERTY)) {
            updateLcd();
        } else if ("LOWER_RIGHT_VISIBLE".equals(PROPERTY)) {
            lcdLowerRightText.setVisible(control.isLowerRightTextVisible());
        } else if ("UPPER_LEFT_VISIBLE".equals(PROPERTY)) {
            lcdUpperLeftText.setVisible(control.isUpperLeftTextVisible());
        } else if ("UPPER_RIGHT_VISIBLE".equals(PROPERTY)) {
            lcdUpperRightText.setVisible(control.isUpperRightTextVisible());
        } else if ("BATTERY_VISIBLE".equals(PROPERTY)) {
            lcdBattery.setVisible(control.isBatteryVisible());
        } else if ("ALARM_VISIBLE".equals(PROPERTY)) {
            lcdAlarm.setVisible(control.isAlarmVisible());
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


    // ******************** Private Methods ***********************************
    private boolean isNoOfDigitsInvalid() {
        final double AVAILABLE_WIDTH = width - 2 - lcdValueOffsetLeft - lcdValueOffsetRight;
        final double NEEDED_WIDTH    = lcdText.getLayoutBounds().getWidth();

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

    private Image createNoiseImage(final double WIDTH, final double HEIGHT, final Color DARK_COLOR, final Color BRIGHT_COLOR, final double ALPHA_VARIATION_IN_PERCENT) {
        int width  = WIDTH <= 0 ? (int) DEFAULT_WIDTH : (int) WIDTH;
        int height = HEIGHT <= 0 ? (int) DEFAULT_HEIGHT : (int) HEIGHT;
        double alphaVariationInPercent      = control.clamp(0, 100, ALPHA_VARIATION_IN_PERCENT);
        final WritableImage IMAGE           = new WritableImage(width, height);
        final PixelWriter   PIXEL_WRITER    = IMAGE.getPixelWriter();
        final Random        BW_RND          = new Random();
        final Random        ALPHA_RND       = new Random();
        final double        ALPHA_START     = alphaVariationInPercent / 100 / 2;
        final double        ALPHA_VARIATION = alphaVariationInPercent / 100;
        Color noiseColor;
        double noiseAlpha;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                noiseColor = BW_RND.nextBoolean() == true ? BRIGHT_COLOR : DARK_COLOR;
                noiseAlpha = control.clamp(0, 1, ALPHA_START + ALPHA_RND.nextDouble() * ALPHA_VARIATION);
                PIXEL_WRITER.setColor(x, y, Color.color(noiseColor.getRed(), noiseColor.getGreen(), noiseColor.getBlue(), noiseAlpha));
            }
        }
        return IMAGE;
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
            case ELEKTRA:
                lcdValueFont = Font.loadFont(getClass().getResourceAsStream("/resources/elektra.ttf"), (0.58333333 * height));
                break;
            case STANDARD:
            default:
                lcdValueFont = Font.font("Verdana", FontWeight.NORMAL, (0.5 * height));
                break;
        }
        lcdBackgroundText.setFont(lcdValueFont);
        lcdBackgroundText.setVisible(Lcd.LcdFont.LCD == control.getValueFont() || Lcd.LcdFont.ELEKTRA == control.getValueFont());
        lcdText.setFont(lcdValueFont);
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
        lcdBackgroundText.setTextOrigin(VPos.BASELINE);
        lcdBackgroundText.setTextAlignment(TextAlignment.RIGHT);

        // Setup the semitransparent background text
        // Width of one segment
        oneSegment.setFont(lcdValueFont);
        if (Lcd.LcdFont.LCD == control.getValueFont()) {
            oneSegment.setText("8");
        } else if (Lcd.LcdFont.ELEKTRA == control.getValueFont()) {
            oneSegment.setText("_");
        }
        oneSegmentWidth = oneSegment.getLayoutBounds().getWidth();

        // Width of decimals
        widthOfDecimals = control.getDecimals() == 0 ? 0 : control.getDecimals() * oneSegmentWidth + oneSegmentWidth;

        // Available width
        availableWidth = width - 2 - lcdValueOffsetRight - widthOfDecimals;

        // Number of segments
        noOfSegments = (int) Math.floor(availableWidth / oneSegmentWidth);

        // Add segments to background text
        lcdBackgroundTextBuilder.setLength(0);
        for (int i = 0 ; i < control.getDecimals() ; i++) {
            lcdBackgroundTextBuilder.append(oneSegment.getText());
        }
        if (control.getDecimals() != 0) {
            lcdBackgroundTextBuilder.insert(0, ".");
        }

        for (int i = 0 ; i < noOfSegments ; i++) {
            lcdBackgroundTextBuilder.insert(0, oneSegment.getText());
        }
        lcdBackgroundText.setText(lcdBackgroundTextBuilder.toString());
    }

    private void updateLcd() {
        switch (control.getNumberSystem()) {
            case HEXADECIMAL:
                lcdText.setText(Integer.toHexString((int) control.getCurrentValue()).toUpperCase());
                break;
            case OCTAL:
                lcdText.setText(Integer.toOctalString((int) control.getCurrentValue()).toUpperCase());
                break;
            case DECIMAL:
            default:
                lcdText.setText(formatLcdValue(control.getCurrentValue(), control.getDecimals()));
                break;
        }

        if (isNoOfDigitsInvalid()) {
            lcdText.setText("-E-");
        }

        updateBackgroundText();

        // Visualize the lcd semitransparent background text
        if (control.isUnitVisible()) {
            lcdBackgroundText.setX(width - 2 - lcdBackgroundText.getLayoutBounds().getWidth() - lcdValueOffsetRight);
        } else {
            lcdBackgroundText.setX((width - lcdBackgroundText.getLayoutBounds().getWidth()) - lcdValueOffsetRight);
        }
        lcdBackgroundText.setY(height - (lcdBackgroundText.getLayoutBounds().getHeight() * lcdDigitalFontSizeFactor) * 0.5);

        if (control.isUnitVisible()) {
            lcdText.setX((width - 2 - lcdText.getLayoutBounds().getWidth()) - lcdValueOffsetRight);
        } else {
            lcdText.setX((width - lcdText.getLayoutBounds().getWidth()) - lcdValueOffsetRight);
        }

        // Update the title
        lcdTitle.setText(control.getTitle());
        lcdTitle.setX((width - lcdTitle.getLayoutBounds().getWidth()) * 0.5);

        // Update the upper left text
        lcdUpperLeftText.setText(control.isMinMeasuredValueVisible() ? formatLcdValue(control.getMinMeasuredValue(), control.getMinMeasuredValueDecimals()) : control.getUpperLeftText());
        if (lcdUpperLeftText.getX() + lcdUpperLeftText.getLayoutBounds().getWidth() > lcdTitle.getX()) {
            lcdUpperLeftText.setText("...");
        }

        // Update the upper right text
        lcdUpperRightText.setText(control.isMaxMeasuredValueVisible() ? formatLcdValue(control.getMaxMeasuredValue(), control.getMaxMeasuredValueDecimals()) : control.getUpperRightText());
        lcdUpperRightText.setX(width - lcdUpperRightText.getLayoutBounds().getWidth() - 0.0416666667 * height);
        if (lcdUpperRightText.getX() < lcdTitle.getX() + lcdTitle.getLayoutBounds().getWidth()) {
            lcdUpperRightText.setText("...");
            lcdUpperRightText.setX(width - lcdUpperRightText.getLayoutBounds().getWidth() - 0.0416666667 * height);
        }

        // Update the lower center text
        lcdLowerCenterText.setText(control.isFormerValueVisible() ? formatLcdValue(control.getFormerValue(), control.getDecimals()) : control.getLowerCenterText());
        lcdLowerCenterText.setX((width - lcdLowerCenterText.getLayoutBounds().getWidth()) * 0.5);

        // Update the lower right text
        lcdLowerRightText.setText(control.isNumberSystemVisible() ? control.getNumberSystem().toString() : control.getLowerRightText());
        lcdLowerRightText.setX(width - lcdLowerRightText.getLayoutBounds().getWidth() - 0.0416666667 * height);
        lcdLowerRightText.setY(lcdMain.getLayoutY() + lcdMain.getLayoutBounds().getHeight() - 0.0416666667 * height);
        if (lcdLowerRightText.getX() < lcdLowerCenterText.getX() + lcdLowerCenterText.getLayoutBounds().getWidth()) {
            lcdLowerRightText.setText("...");
            lcdLowerRightText.setX(width - lcdLowerRightText.getLayoutBounds().getWidth() - 0.0416666667 * height);
        }

        // Update battery charge
        if (control.getBatteryCharge() < 0.01) {
            lcdBattery.getStyleClass().setAll("battery-empty");
        } else if (control.getBatteryCharge() < 0.06) {
            lcdBattery.getStyleClass().setAll("battery-almost-empty");
        } else if (control.getBatteryCharge() < 0.26) {
            lcdBattery.getStyleClass().setAll("battery-25");
        } else if (control.getBatteryCharge() < 0.51) {
            lcdBattery.getStyleClass().setAll("battery-50");
        } else if (control.getBatteryCharge() < 0.76) {
            lcdBattery.getStyleClass().setAll("battery-75");
        } else if (control.getBatteryCharge() < 0.96) {
            lcdBattery.getStyleClass().setAll("battery-almost-full");
        } else {
            lcdBattery.getStyleClass().setAll("battery-full");
        }
    }

    private void resize() {
        width       = control.getWidth();
        height      = control.getHeight();
        if (control.isKeepAspect()) {
            if (aspectRatio * width > height) {
                width  = 1 / (aspectRatio / height);
            } else if (1 / (aspectRatio / height) > width) {
                height = aspectRatio * width;
            }
        }

        lcdFrame.setPrefSize(width, height);

        lcdMain.setPrefSize(width - 2.0, height - 2.0);
        lcdMain.setTranslateX(1);
        lcdMain.setTranslateY(1);
        lcdMainInnerShadow0.setRadius(3.0 / 132.0 * height);
        lcdMainInnerShadow1.setRadius(2.0 / 132.0 * height);

        if (width > 0 && height > 0 && lcdCrystalOverlay.isVisible()) {
            lcdMainClip.setScaleX(width / (DEFAULT_WIDTH - 2.0));
            lcdMainClip.setScaleY(height / (DEFAULT_HEIGHT - 2.0));
            lcdMainClip.setTranslateX((width - DEFAULT_WIDTH - 2) * 0.5);
            lcdMainClip.setTranslateY((height - DEFAULT_HEIGHT - 2) * 0.5);
            lcdCrystalOverlay.setImage(createNoiseImage(width, height, DARK_NOISE_COLOR, BRIGHT_NOISE_COLOR, 8));
            lcdCrystalOverlay.setCache(true);
        }

        lcdThreshold.setPrefSize(0.20 * height, 0.20 * height);
        lcdThreshold.setTranslateX(0.027961994662429348 * width);
        lcdThreshold.setTranslateY(0.75 * height);

        lcdTrendDown.setPrefSize(0.06718573425755356 * width, 0.1333622932434082 * height);
        lcdTrendDown.setTranslateX(0.1439393939 * width);
        lcdTrendDown.setTranslateY(0.8125 * height);

        lcdTrendFalling.setPrefSize(0.06982171896732214 * width, 0.13879903157552084 * height);
        lcdTrendFalling.setTranslateX(0.1439393939 * width);
        lcdTrendFalling.setTranslateY(0.8061291376749674 * height);

        lcdTrendSteady.setPrefSize(0.0676060878869259 * width, 0.1342292626698812 * height);
        lcdTrendSteady.setTranslateX(0.1439393939 * width);
        lcdTrendSteady.setTranslateY(0.8078853289286295 * height);

        lcdTrendRising.setPrefSize(0.06982171896732214 * width, 0.13879903157552084 * height);
        lcdTrendRising.setTranslateX(0.1439393939 * width);
        lcdTrendRising.setTranslateY(0.8050718307495117 * height);

        lcdTrendUp.setPrefSize(0.06718573425755356 * width, 0.1333622932434082 * height);
        lcdTrendUp.setTranslateX(0.1439393939 * width);
        lcdTrendUp.setTranslateY(0.8041377067565918 * height);

        lcdBattery.setPrefSize(0.0833333333 * width, 0.1458333333 * height);
        lcdBattery.setTranslateX(0.6439393939 * width);
        lcdBattery.setTranslateY(0.81 * height);

        lcdAlarm.setPrefSize(0.1666666667 * height, 0.1666666667 * height);
        lcdAlarm.setTranslateX(0.2651515152 * width);
        lcdAlarm.setTranslateY(0.7916666667 * height);

        updateFonts();

        // Setup the lcd unit
        lcdUnitText.setFont(lcdUnitFont);
        lcdUnitText.setTextOrigin(VPos.BASELINE);
        lcdUnitText.setTextAlignment(TextAlignment.RIGHT);

        lcdUnitText.setText(control.getUnit());
        if (lcdUnitText.visibleProperty().isBound()) {
            lcdUnitText.visibleProperty().unbind();
        }
        lcdUnitText.visibleProperty().bind(control.unitVisibleProperty());

        lcdValueOffsetLeft = height * 0.04;

        if (control.isUnitVisible()) {
            lcdUnitText.setX((width - lcdUnitText.getLayoutBounds().getWidth()) - height * 0.04);
            lcdUnitText.setY(height - (lcdText.getLayoutBounds().getHeight() * lcdDigitalFontSizeFactor) * 0.5);
            lcdValueOffsetRight = (lcdUnitText.getLayoutBounds().getWidth() + height * 0.0833333333); // distance between value and unit
            lcdText.setX(width - 2 - lcdText.getLayoutBounds().getWidth() - lcdValueOffsetRight);
        } else {
            lcdValueOffsetRight = height * 0.0833333333;
            lcdText.setX((width - lcdText.getLayoutBounds().getWidth()) - lcdValueOffsetRight);
        }
        lcdText.setY(height - (lcdText.getLayoutBounds().getHeight() * lcdDigitalFontSizeFactor) * 0.5);

        // Visualize the lcd semitransparent background text
        updateBackgroundText();

        if (control.isUnitVisible()) {
            lcdBackgroundText.setX(width - 2 - lcdBackgroundText.getLayoutBounds().getWidth() - lcdValueOffsetRight);
        } else {
            lcdBackgroundText.setX((width - lcdBackgroundText.getLayoutBounds().getWidth()) - lcdValueOffsetRight);
        }
        lcdBackgroundText.setY(height - (lcdBackgroundText.getLayoutBounds().getHeight() * lcdDigitalFontSizeFactor) * 0.5);

        // Setup the font for the lcd title, number system, min measured, max measure and former value
        // Title
        lcdTitle.setFont(lcdTitleFont);
        lcdTitle.setTextOrigin(VPos.BASELINE);
        lcdTitle.setTextAlignment(TextAlignment.CENTER);
        lcdTitle.setText(control.getTitle());
        lcdTitle.setX((width - lcdTitle.getLayoutBounds().getWidth()) * 0.5);
        lcdTitle.setY(lcdMain.getLayoutY() + lcdTitle.getLayoutBounds().getHeight() + 0.04 * height);

        // Info Text
        lcdLowerRightText.setFont(lcdSmallFont);
        lcdLowerRightText.setTextOrigin(VPos.BASELINE);
        lcdLowerRightText.setTextAlignment(TextAlignment.RIGHT);
        lcdLowerRightText.setText(control.getNumberSystem().toString());
        lcdLowerRightText.setX(lcdMain.getLayoutX() + (lcdMain.getLayoutBounds().getWidth() - lcdTitle.getLayoutBounds().getWidth()) * 0.5);
        lcdLowerRightText.setY(lcdMain.getLayoutY() + height - 1 - 0.0416666667 * height);

        // Min measured value
        lcdUpperLeftText.setFont(lcdSmallFont);
        lcdUpperLeftText.setTextOrigin(VPos.BASELINE);
        lcdUpperLeftText.setTextAlignment(TextAlignment.RIGHT);
        lcdUpperLeftText.setX(lcdMain.getLayoutX() + 0.0416666667 * height);
        lcdUpperLeftText.setY(lcdMain.getLayoutY() + lcdUpperLeftText.getLayoutBounds().getHeight() + 0.04 * height);

        // Max measured value
        lcdUpperRightText.setFont(lcdSmallFont);
        lcdUpperRightText.setTextOrigin(VPos.BASELINE);
        lcdUpperRightText.setTextAlignment(TextAlignment.RIGHT);
        lcdUpperRightText.setY(lcdMain.getLayoutY() + lcdUpperLeftText.getLayoutBounds().getHeight() + 0.04 * height);

        // Former value
        lcdLowerCenterText.setFont(lcdSmallFont);
        lcdLowerCenterText.setTextOrigin(VPos.BASELINE);
        lcdLowerCenterText.setTextAlignment(TextAlignment.CENTER);
        lcdLowerCenterText.setX((width - lcdLowerCenterText.getLayoutBounds().getWidth()) * 0.5);
        lcdLowerCenterText.setY(lcdMain.getLayoutY() + height - 1 - 0.0416666667 * height);
    }
}
