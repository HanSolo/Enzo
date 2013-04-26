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
import javafx.scene.Group;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
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


public class LcdSkin extends SkinBase<Lcd> implements Skin<Lcd> {
    private static final double        PREFERRED_WIDTH = 132;
    private static final double        PREFERRED_HEIGHT = 48;
    private static final double        MINIMUM_WIDTH     = 5;
    private static final double        MINIMUM_HEIGHT    = 5;
    private static final double        MAXIMUM_WIDTH     = 1024;
    private static final double        MAXIMUM_HEIGHT    = 1024;
    private static double              aspectRatio       = PREFERRED_HEIGHT / PREFERRED_WIDTH;
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
    private Group                      shadowGroup;


    // ******************** Constructors **************************************
    public LcdSkin(final Lcd CONTROL) {
        super(CONTROL);
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
    }

    private void initGraphics() {
        lcdFrame = new Region();
        lcdFrame.getStyleClass().setAll("frame");
        lcdFrame.setVisible(getSkinnable().isBackgroundVisible());

        lcdMain = new Region();
        lcdMain.getStyleClass().setAll("main");
        lcdMain.setVisible(getSkinnable().isBackgroundVisible());

        lcdMainInnerShadow0 = InnerShadowBuilder.create()
                                                .offsetX(0.0)
                                                .offsetY(0.0)
                                                .radius(3.0 / 132.0 * PREFERRED_WIDTH)
                                                .color(Color.web("0xffffff80"))
                                                .blurType(BlurType.GAUSSIAN)
                                                .build();
        lcdMainInnerShadow1 = InnerShadowBuilder.create()
                                                .offsetX(0.0)
                                                .offsetY(1.0)
                                                .radius(2.0 / 132.0 * PREFERRED_WIDTH)
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
        lcdCrystalOverlay.setVisible(getSkinnable().isCrystalOverlayVisible());

        lcdThreshold = new Region();
        lcdThreshold.getStyleClass().setAll("threshold");
        lcdThreshold.setVisible(getSkinnable().isThresholdVisible() && getSkinnable().isThresholdExceeded());

        lcdTrendDown = new Region();
        lcdTrendDown.getStyleClass().setAll("trend-down");
        lcdTrendDown.setVisible(getSkinnable().isTrendVisible() && Lcd.Trend.DOWN == getSkinnable().getTrend());

        lcdTrendFalling = new Region();
        lcdTrendFalling.getStyleClass().setAll("trend-falling");
        lcdTrendFalling.setVisible(getSkinnable().isTrendVisible() && Lcd.Trend.FALLING == getSkinnable().getTrend());

        lcdTrendSteady = new Region();
        lcdTrendSteady.getStyleClass().setAll("trend-steady");
        lcdTrendSteady.setVisible(getSkinnable().isTrendVisible() && Lcd.Trend.STEADY == getSkinnable().getTrend());

        lcdTrendRising = new Region();
        lcdTrendRising.getStyleClass().setAll("trend-rising");
        lcdTrendRising.setVisible(getSkinnable().isTrendVisible() && Lcd.Trend.RISING == getSkinnable().getTrend());

        lcdTrendUp = new Region();
        lcdTrendUp.getStyleClass().setAll("trend-up");
        lcdTrendUp.setVisible(getSkinnable().isTrendVisible() && Lcd.Trend.UP == getSkinnable().getTrend());

        lcdBattery = new Region();
        lcdBattery.getStyleClass().setAll("battery-empty");
        lcdBattery.setVisible(getSkinnable().isBatteryVisible());

        lcdAlarm = new Region();
        lcdAlarm.getStyleClass().setAll("alarm");
        lcdAlarm.setVisible(getSkinnable().isAlarmVisible());

        lcdBackgroundText = new Text(getSkinnable().isTextMode() ? getSkinnable().getText() : Double.toString(getSkinnable().getValue()));
        lcdBackgroundText.getStyleClass().setAll("fg-trsp");
        lcdBackgroundText.setVisible(Lcd.LcdFont.LCD == getSkinnable().getValueFont() || Lcd.LcdFont.ELEKTRA == getSkinnable().getValueFont());

        lcdText = new Text(getSkinnable().isTextMode() ? getSkinnable().getText() : Double.toString(getSkinnable().getValue()));
        lcdText.getStyleClass().setAll("fg");

        lcdUnitText = new Text(getSkinnable().getUnit());
        lcdUnitText.getStyleClass().setAll("fg");
        lcdUnitText.setVisible(getSkinnable().isUnitVisible());

        lcdTitle = new Text(getSkinnable().getTitle());
        lcdTitle.getStyleClass().setAll("fg");
        lcdTitle.setVisible(getSkinnable().isTitleVisible());

        lcdLowerRightText = getSkinnable().isNumberSystemVisible() ? new Text(getSkinnable().getNumberSystem().toString()) : new Text(getSkinnable().getLowerRightText());
        lcdLowerRightText.getStyleClass().setAll("fg");
        lcdLowerRightText.setVisible(getSkinnable().isLowerRightTextVisible());

        lcdUpperLeftText = getSkinnable().isMinMeasuredValueVisible() ? new Text(Double.toString(getSkinnable().getMaxValue())) : new Text(getSkinnable().getUpperLeftText());
        lcdUpperLeftText.getStyleClass().setAll("fg");
        lcdUpperLeftText.setVisible(getSkinnable().isMinMeasuredValueVisible());

        lcdUpperRightText = getSkinnable().isMaxMeasuredValueVisible() ? new Text(Double.toString(getSkinnable().getMinValue())) : new Text(getSkinnable().getUpperRightText());
        lcdUpperRightText.getStyleClass().setAll("fg");
        lcdUpperRightText.setVisible(getSkinnable().isMaxMeasuredValueVisible());

        lcdLowerCenterText = new Text(getSkinnable().isFormerValueVisible() ? Double.toString(getSkinnable().getFormerValue()) : getSkinnable().getLowerCenterText());
        lcdLowerCenterText.getStyleClass().setAll("fg");

        shadowGroup = new Group();
        shadowGroup.setEffect(getSkinnable().isForegroundShadowVisible() ? FOREGROUND_SHADOW : null);
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
        getSkinnable().widthProperty().addListener(observable -> { handleControlPropertyChanged("RESIZE"); });
        getSkinnable().heightProperty().addListener(observable -> { handleControlPropertyChanged("RESIZE"); });
        getSkinnable().titleProperty().addListener(observable -> { handleControlPropertyChanged("UPDATE"); });
        getSkinnable().unitProperty().addListener(observable -> { handleControlPropertyChanged("UPDATE"); });
        getSkinnable().lowerRightTextProperty().addListener(observable -> { handleControlPropertyChanged("UPDATE"); });
        getSkinnable().numberSystemProperty().addListener(observable -> { handleControlPropertyChanged("UPDATE"); });
        getSkinnable().textModeProperty().addListener(observable -> { handleControlPropertyChanged("UPDATE"); });
        getSkinnable().textProperty().addListener(observable -> { handleControlPropertyChanged("UPDATE"); });
        getSkinnable().currentValueProperty().addListener(observable -> { handleControlPropertyChanged("UPDATE"); });
        getSkinnable().lowerCenterTextProperty().addListener(observable -> { handleControlPropertyChanged("UPDATE"); });
        getSkinnable().minMeasuredValueProperty().addListener(observable -> { handleControlPropertyChanged("UPDATE"); });
        getSkinnable().maxMeasuredValueProperty().addListener(observable -> { handleControlPropertyChanged("UPDATE"); });
        getSkinnable().upperLeftTextProperty().addListener(observable -> { handleControlPropertyChanged("UPDATE"); });
        getSkinnable().upperRightTextProperty().addListener(observable -> { handleControlPropertyChanged("UPDATE"); });
        getSkinnable().batteryChargeProperty().addListener(observable -> { handleControlPropertyChanged("UPDATE"); });
        getSkinnable().prefWidthProperty().addListener(observable -> { handleControlPropertyChanged("PREF_SIZE"); });
        getSkinnable().prefHeightProperty().addListener(observable -> { handleControlPropertyChanged("PREF_SIZE"); });
        getSkinnable().valueFontProperty().addListener(observable -> { handleControlPropertyChanged("FONT"); });
        getSkinnable().numberSystemVisibleProperty().addListener(observable -> { handleControlPropertyChanged("NUMBER_SYSTEM_VISIBLE"); });
        getSkinnable().backgroundVisibleProperty().addListener(observable -> { handleControlPropertyChanged("BACKGROUND_VISIBLE"); });
        getSkinnable().crystalOverlayVisibleProperty().addListener(observable -> { handleControlPropertyChanged("CRYSTAL_OVERLAY_VISIBLE"); });
        getSkinnable().foregroundShadowVisibleProperty().addListener(observable -> { handleControlPropertyChanged("FOREGROUND_SHADOW_VISIBLE"); });
        getSkinnable().animationDurationProperty().addListener(observable -> { handleControlPropertyChanged("ANIMATION_DURATION"); });
        getSkinnable().thresholdExceededProperty().addListener(observable -> { handleControlPropertyChanged("THRESHOLD_EXCEEDED"); });
        getSkinnable().trendProperty().addListener(observable -> { handleControlPropertyChanged("TREND"); });
        getSkinnable().valueVisibleProperty().addListener(observable -> { handleControlPropertyChanged("VALUE_VISIBLE"); });
        getSkinnable().unitVisibleProperty().addListener(observable -> { handleControlPropertyChanged("UNIT_VISIBLE"); });
        getSkinnable().lowerCenterTextVisibleProperty().addListener(observable -> { handleControlPropertyChanged("LOWER_CENTER_VISIBLE"); });
        getSkinnable().lowerRightTextVisibleProperty().addListener(observable -> { handleControlPropertyChanged("LOWER_RIGHT_VISIBLE"); });
        getSkinnable().upperLeftTextVisibleProperty().addListener(observable -> { handleControlPropertyChanged("UPPER_LEFT_VISIBLE"); });
        getSkinnable().upperRightTextVisibleProperty().addListener(observable -> { handleControlPropertyChanged("UPPER_RIGHT_VISIBLE"); });
        getSkinnable().batteryVisibleProperty().addListener(observable -> { handleControlPropertyChanged("BATTERY_VISIBLE"); });
        getSkinnable().alarmVisibleProperty().addListener(observable -> { handleControlPropertyChanged("ALARM_VISIBLE"); });
        getSkinnable().formerValueVisibleProperty().addListener(observable -> { handleControlPropertyChanged("FORMER_VALUE_VISIBLE"); });
        getSkinnable().maxMeasuredValueVisibleProperty().addListener(observable -> { handleControlPropertyChanged("MAX_MEASURED_VISIBLE"); });
        getSkinnable().minMeasuredValueVisibleProperty().addListener(observable -> { handleControlPropertyChanged("MIN_MEASURED_VISIBLE"); });
        getSkinnable().getStyleClass().addListener(new ListChangeListener<String>() {
            @Override public void onChanged(Change<? extends String> change) {
                resize();
                updateLcd();
            }
        });
    }


    // ******************** Methods *******************************************
    protected void handleControlPropertyChanged(final String PROPERTY) {    
        if ("UPDATE".equals(PROPERTY)) {
            updateLcd();
        } else if ("RESIZE".equals(PROPERTY)) {
            resize();
            updateLcd();
        } else if ("PREF_SIZE".equals(PROPERTY)) {
            aspectRatio = getSkinnable().getPrefHeight() / getSkinnable().getPrefWidth();
        } else if ("BACKGROUND_VISIBLE".equals(PROPERTY)) {
            lcdMain.setVisible(getSkinnable().isBackgroundVisible());
            lcdCrystalOverlay.setVisible(getSkinnable().isBackgroundVisible());
            lcdFrame.setVisible(getSkinnable().isBackgroundVisible());
        } else if ("CRYSTAL_OVERLAY_VISIBLE".equals(PROPERTY)) {
            lcdCrystalOverlay.setVisible(getSkinnable().isCrystalOverlayVisible());
            resize();
        } else if ("FOREGROUND_SHADOW_VISIBLE".equals(PROPERTY)) {
            shadowGroup.setEffect(getSkinnable().isForegroundShadowVisible() ? FOREGROUND_SHADOW : null);
        } else if ("TREND".equals(PROPERTY)) {
            updateTrend();
        } else if ("THRESHOLD_EXCEEDED".equals(PROPERTY)) {
            if (getSkinnable().isThresholdVisible()) {
                lcdThreshold.setVisible(getSkinnable().isThresholdExceeded());
            }
        } else if ("FONT".equals(PROPERTY)) {
            updateFonts();
        } else if ("VALUE_VISIBLE".equals(PROPERTY)) {
            lcdText.setVisible(getSkinnable().isValueVisible());
        } else if ("UNIT_VISIBLE".equals(PROPERTY)) {
            lcdUnitText.setVisible(getSkinnable().isUnitVisible());
        } else if ("FORMER_VALUE_VISIBLE".equals(getSkinnable().isFormerValueVisible())) {
            lcdLowerCenterText.setVisible(getSkinnable().isFormerValueVisible());
        } else if ("MAX_MEASURED_VISIBLE".equals(PROPERTY)) {
            lcdUpperRightText.setVisible(getSkinnable().isMaxMeasuredValueVisible());
        } else if ("MIN_MEASURED_VISIBLE".equals(PROPERTY)) {
            lcdUpperLeftText.setVisible(getSkinnable().isMinMeasuredValueVisible());
        } else if ("NUMBER_SYSTEM_VISIBLE".equals(PROPERTY)) {
            updateLcd();
        } else if ("LOWER_RIGHT_VISIBLE".equals(PROPERTY)) {
            lcdLowerRightText.setVisible(getSkinnable().isLowerRightTextVisible());
        } else if ("UPPER_LEFT_VISIBLE".equals(PROPERTY)) {
            lcdUpperLeftText.setVisible(getSkinnable().isUpperLeftTextVisible());
        } else if ("UPPER_RIGHT_VISIBLE".equals(PROPERTY)) {
            lcdUpperRightText.setVisible(getSkinnable().isUpperRightTextVisible());
        } else if ("BATTERY_VISIBLE".equals(PROPERTY)) {
            lcdBattery.setVisible(getSkinnable().isBatteryVisible());
        } else if ("ALARM_VISIBLE".equals(PROPERTY)) {
            lcdAlarm.setVisible(getSkinnable().isAlarmVisible());
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
        int width  = WIDTH <= 0 ? (int) PREFERRED_WIDTH : (int) WIDTH;
        int height = HEIGHT <= 0 ? (int) PREFERRED_HEIGHT : (int) HEIGHT;
        double alphaVariationInPercent      = getSkinnable().clamp(0, 100, ALPHA_VARIATION_IN_PERCENT);
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
                noiseAlpha = getSkinnable().clamp(0, 1, ALPHA_START + ALPHA_RND.nextDouble() * ALPHA_VARIATION);
                PIXEL_WRITER.setColor(x, y, Color.color(noiseColor.getRed(), noiseColor.getGreen(), noiseColor.getBlue(), noiseAlpha));
            }
        }
        return IMAGE;
    }

    private void updateFonts() {
        lcdDigitalFontSizeFactor = 1.0;
        switch(getSkinnable().getValueFont()) {
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
                lcdValueFont = Font.font("Arial", FontWeight.NORMAL, (0.5 * height));
                break;
        }
        lcdBackgroundText.setFont(lcdValueFont);
        lcdBackgroundText.setVisible(Lcd.LcdFont.LCD == getSkinnable().getValueFont() || Lcd.LcdFont.ELEKTRA == getSkinnable().getValueFont());
        lcdText.setFont(lcdValueFont);
        lcdUnitFont  = Font.font(getSkinnable().getUnitFont(), FontWeight.NORMAL, (0.26 * height));
        lcdTitleFont = Font.font(getSkinnable().getTitleFont(), FontWeight.BOLD, (0.1666666667 * height));
        lcdSmallFont = Font.font("Arial", FontWeight.NORMAL, (0.1666666667 * height));
    }

    private void updateTrend() {
        if (getSkinnable().isTrendVisible()) {
            switch (getSkinnable().getTrend()) {
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
        if (Lcd.LcdFont.LCD == getSkinnable().getValueFont()) {
            oneSegment.setText("8");
        } else if (Lcd.LcdFont.ELEKTRA == getSkinnable().getValueFont()) {
            oneSegment.setText("_");
        }
        oneSegmentWidth = oneSegment.getLayoutBounds().getWidth();

        // Width of decimals
        widthOfDecimals = getSkinnable().getDecimals() == 0 ? 0 : getSkinnable().getDecimals() * oneSegmentWidth + oneSegmentWidth;

        // Available width
        availableWidth = width - 2 - lcdValueOffsetRight - widthOfDecimals;

        // Number of segments
        noOfSegments = (int) Math.floor(availableWidth / oneSegmentWidth);

        // Add segments to background text
        lcdBackgroundTextBuilder.setLength(0);
        for (int i = 0 ; i < getSkinnable().getDecimals() ; i++) {
            lcdBackgroundTextBuilder.append(oneSegment.getText());
        }
        if (getSkinnable().getDecimals() != 0) {
            lcdBackgroundTextBuilder.insert(0, ".");
        }

        for (int i = 0 ; i < noOfSegments ; i++) {
            lcdBackgroundTextBuilder.insert(0, oneSegment.getText());
        }
        lcdBackgroundText.setText(lcdBackgroundTextBuilder.toString());
    }

    private void updateLcd() {
        switch (getSkinnable().getNumberSystem()) {
            case HEXADECIMAL:
                lcdText.setText(Integer.toHexString((int) getSkinnable().getCurrentValue()).toUpperCase());
                break;
            case OCTAL:
                lcdText.setText(Integer.toOctalString((int) getSkinnable().getCurrentValue()).toUpperCase());
                break;
            case DECIMAL:
            default:
                lcdText.setText(formatLcdValue(getSkinnable().getCurrentValue(), getSkinnable().getDecimals()));
                break;
        }

        if (isNoOfDigitsInvalid()) {
            lcdText.setText("-E-");
        }

        updateBackgroundText();

        // Visualize the lcd semitransparent background text
        if (getSkinnable().isUnitVisible()) {
            lcdBackgroundText.setX(width - 2 - lcdBackgroundText.getLayoutBounds().getWidth() - lcdValueOffsetRight);
        } else {
            lcdBackgroundText.setX((width - lcdBackgroundText.getLayoutBounds().getWidth()) - lcdValueOffsetRight);
        }
        lcdBackgroundText.setY(height - (lcdBackgroundText.getLayoutBounds().getHeight() * lcdDigitalFontSizeFactor) * 0.5);

        if (getSkinnable().isUnitVisible()) {
            lcdText.setX((width - 2 - lcdText.getLayoutBounds().getWidth()) - lcdValueOffsetRight);
        } else {
            lcdText.setX((width - lcdText.getLayoutBounds().getWidth()) - lcdValueOffsetRight);
        }

        // Update the title
        lcdTitle.setText(getSkinnable().getTitle());
        lcdTitle.setX((width - lcdTitle.getLayoutBounds().getWidth()) * 0.5);

        // Update the upper left text
        lcdUpperLeftText.setText(getSkinnable().isMinMeasuredValueVisible() ? formatLcdValue(getSkinnable().getMinMeasuredValue(), getSkinnable().getMinMeasuredValueDecimals()) : getSkinnable().getUpperLeftText());
        if (lcdUpperLeftText.getX() + lcdUpperLeftText.getLayoutBounds().getWidth() > lcdTitle.getX()) {
            lcdUpperLeftText.setText("...");
        }

        // Update the upper right text
        lcdUpperRightText.setText(getSkinnable().isMaxMeasuredValueVisible() ? formatLcdValue(getSkinnable().getMaxMeasuredValue(), getSkinnable().getMaxMeasuredValueDecimals()) : getSkinnable().getUpperRightText());
        lcdUpperRightText.setX(width - lcdUpperRightText.getLayoutBounds().getWidth() - 0.0416666667 * height);
        if (lcdUpperRightText.getX() < lcdTitle.getX() + lcdTitle.getLayoutBounds().getWidth()) {
            lcdUpperRightText.setText("...");
            lcdUpperRightText.setX(width - lcdUpperRightText.getLayoutBounds().getWidth() - 0.0416666667 * height);
        }

        // Update the lower center text
        lcdLowerCenterText.setText(getSkinnable().isFormerValueVisible() ? formatLcdValue(getSkinnable().getFormerValue(), getSkinnable().getDecimals()) : getSkinnable().getLowerCenterText());
        lcdLowerCenterText.setX((width - lcdLowerCenterText.getLayoutBounds().getWidth()) * 0.5);

        // Update the lower right text
        lcdLowerRightText.setText(getSkinnable().isNumberSystemVisible() ? getSkinnable().getNumberSystem().toString() : getSkinnable().getLowerRightText());
        lcdLowerRightText.setX(width - lcdLowerRightText.getLayoutBounds().getWidth() - 0.0416666667 * height);
        lcdLowerRightText.setY(lcdMain.getLayoutY() + lcdMain.getLayoutBounds().getHeight() - 0.0416666667 * height);
        if (lcdLowerRightText.getX() < lcdLowerCenterText.getX() + lcdLowerCenterText.getLayoutBounds().getWidth()) {
            lcdLowerRightText.setText("...");
            lcdLowerRightText.setX(width - lcdLowerRightText.getLayoutBounds().getWidth() - 0.0416666667 * height);
        }

        // Update battery charge
        if (getSkinnable().getBatteryCharge() < 0.01) {
            lcdBattery.getStyleClass().setAll("battery-empty");
        } else if (getSkinnable().getBatteryCharge() < 0.06) {
            lcdBattery.getStyleClass().setAll("battery-almost-empty");
        } else if (getSkinnable().getBatteryCharge() < 0.26) {
            lcdBattery.getStyleClass().setAll("battery-25");
        } else if (getSkinnable().getBatteryCharge() < 0.51) {
            lcdBattery.getStyleClass().setAll("battery-50");
        } else if (getSkinnable().getBatteryCharge() < 0.76) {
            lcdBattery.getStyleClass().setAll("battery-75");
        } else if (getSkinnable().getBatteryCharge() < 0.96) {
            lcdBattery.getStyleClass().setAll("battery-almost-full");
        } else {
            lcdBattery.getStyleClass().setAll("battery-full");
        }
    }

    private void resize() {
        width       = getSkinnable().getWidth();
        height      = getSkinnable().getHeight();
        if (getSkinnable().isKeepAspect()) {
            if (aspectRatio * width > height) {
                width = 1 / (aspectRatio / height);
            } else if (1 / (aspectRatio / height) > width) {
                height = aspectRatio * width;
            }
        }

        if (width > 0 && height > 0) {
            lcdFrame.setPrefSize(width, height);

            lcdMain.setPrefSize(width - 2.0, height - 2.0);
            lcdMain.setTranslateX(1);
            lcdMain.setTranslateY(1);
            lcdMainInnerShadow0.setRadius(3.0 / 132.0 * height);
            lcdMainInnerShadow1.setRadius(2.0 / 132.0 * height);

            if (width > 0 && height > 0 && lcdCrystalOverlay.isVisible()) {
                lcdMainClip.setScaleX(width / (PREFERRED_WIDTH - 2.0));
                lcdMainClip.setScaleY(height / (PREFERRED_HEIGHT - 2.0));
                lcdMainClip.setTranslateX((width - PREFERRED_WIDTH - 2) * 0.5);
                lcdMainClip.setTranslateY((height - PREFERRED_HEIGHT - 2) * 0.5);
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

            lcdUnitText.setText(getSkinnable().getUnit());
            if (lcdUnitText.visibleProperty().isBound()) {
                lcdUnitText.visibleProperty().unbind();
            }
            lcdUnitText.visibleProperty().bind(getSkinnable().unitVisibleProperty());

            lcdValueOffsetLeft = height * 0.04;

            if (getSkinnable().isUnitVisible()) {
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

            if (getSkinnable().isUnitVisible()) {
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
            lcdTitle.setText(getSkinnable().getTitle());
            lcdTitle.setX((width - lcdTitle.getLayoutBounds().getWidth()) * 0.5);
            lcdTitle.setY(lcdMain.getLayoutY() + lcdTitle.getLayoutBounds().getHeight() + 0.04 * height);

            // Info Text
            lcdLowerRightText.setFont(lcdSmallFont);
            lcdLowerRightText.setTextOrigin(VPos.BASELINE);
            lcdLowerRightText.setTextAlignment(TextAlignment.RIGHT);
            lcdLowerRightText.setText(getSkinnable().getNumberSystem().toString());
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
}
