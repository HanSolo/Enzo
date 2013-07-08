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

package eu.hansolo.enzo.lcd.skin;

import eu.hansolo.enzo.lcd.Lcd;
import javafx.collections.ListChangeListener;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Random;


public class LcdSkin extends SkinBase<Lcd> implements Skin<Lcd> {
    private static final double        PREFERRED_WIDTH    = 132;
    private static final double        PREFERRED_HEIGHT   = 48;
    private static final double        MINIMUM_WIDTH      = 5;
    private static final double        MINIMUM_HEIGHT     = 5;
    private static final double        MAXIMUM_WIDTH      = 1024;
    private static final double        MAXIMUM_HEIGHT     = 1024;
    private static double              aspectRatio        = PREFERRED_HEIGHT / PREFERRED_WIDTH;
    private static Text                oneSegment         = new Text("8");
    private static final DecimalFormat DEC_FORMAT         = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.US));
    private static final boolean       SCIFI_FORMAT       = false;
    private static final Color         DARK_NOISE_COLOR   = Color.rgb(100, 100, 100, 0.10);
    private static final Color         BRIGHT_NOISE_COLOR = Color.rgb(200, 200, 200, 0.05);
    private static final DropShadow    FOREGROUND_SHADOW  = new DropShadow();
    private double                     width;
    private double                     height;
    private Pane                       pane;
    private Region                     frame;
    private Region                     main;
    private ImageView                  crystalOverlay;
    private Image                      crystalImage;
    private SVGPath                    mainClip;
    private InnerShadow                mainInnerShadow0;
    private InnerShadow                mainInnerShadow1;
    private Region                     threshold;
    private Region                     trendDown;
    private Region                     trendFalling;
    private Region                     trendSteady;
    private Region                     trendRising;
    private Region                     trendUp;
    private Region                     battery;
    private Region                     signal;
    private Region                     alarm;
    private Text                       text;
    private Text                       backgroundText;
    private Text                       unitText;
    private Text                       title;
    private Text                       lowerRightText;
    private Text                       upperLeftText;
    private Text                       upperRightText;
    private Text                       lowerCenterText;
    private double                     valueOffsetLeft;
    private double                     valueOffsetRight;
    private double                     digitalFontSizeFactor;
    private Font                       valueFont;
    private Font                       unitFont;
    private Font                       titleFont;
    private Font                       smallFont;
    private double                     oneSegmentWidth;
    private double                     widthOfDecimals;
    private double                     availableWidth;
    private int                        noOfSegments;
    private StringBuilder              backgroundTextBuilder;
    private StringBuilder              decBuffer;
    private Group                      shadowGroup;


    // ******************** Constructors **************************************
    public LcdSkin(final Lcd CONTROL) {
        super(CONTROL);
        pane                  = new Pane();
        valueOffsetLeft       = 0.0;
        valueOffsetRight      = 0.0;
        digitalFontSizeFactor = 1.0;
        backgroundTextBuilder = new StringBuilder();
        decBuffer             = new StringBuilder(16);
        FOREGROUND_SHADOW.setOffsetX(0);
        FOREGROUND_SHADOW.setOffsetY(1);
        FOREGROUND_SHADOW.setColor(Color.rgb(0, 0, 0, 0.5));
        FOREGROUND_SHADOW.setBlurType(BlurType.TWO_PASS_BOX);
        FOREGROUND_SHADOW.setRadius(2);
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
        // load the fonts
        Font.loadFont(getClass().getResourceAsStream("/eu/hansolo/enzo/fonts/bus.otf"), (0.4583333333 * PREFERRED_HEIGHT));             // "Bus-Regular"
        Font.loadFont(getClass().getResourceAsStream("/eu/hansolo/enzo/fonts/digital.ttf"), (0.5833333333 * PREFERRED_HEIGHT));         // "Digital-7"
        Font.loadFont(getClass().getResourceAsStream("/eu/hansolo/enzo/fonts/digitalreadout.ttf"), (0.5833333333 * PREFERRED_HEIGHT));  // "Digital Readout Upright"
        Font.loadFont(getClass().getResourceAsStream("/eu/hansolo/enzo/fonts/digitalreadoutb.ttf"), (0.5833333333 * PREFERRED_HEIGHT)); // "Digital Readout Thick Upright"
        Font.loadFont(getClass().getResourceAsStream("/eu/hansolo/enzo/fonts/pixel.ttf"), (0.5208333333 * PREFERRED_HEIGHT));           // "Electronic Highway Sign"
        Font.loadFont(getClass().getResourceAsStream("/eu/hansolo/enzo/fonts/phonelcd.ttf"), (0.4583333333 * PREFERRED_HEIGHT));        // "Nokian"
        Font.loadFont(getClass().getResourceAsStream("/eu/hansolo/enzo/fonts/elektra.ttf"), (0.58333333 * PREFERRED_HEIGHT));           // "Elektra"

        frame = new Region();
        frame.getStyleClass().setAll("frame");
        frame.setOpacity(getSkinnable().isBackgroundVisible() ? 1.0 : 0.0);

        main = new Region();
        main.getStyleClass().setAll("main");
        main.setOpacity(getSkinnable().isBackgroundVisible() ? 1 : 0);

        mainInnerShadow0 = new InnerShadow();
        mainInnerShadow0.setOffsetX(0.0);
        mainInnerShadow0.setOffsetY(0.0);
        mainInnerShadow0.setRadius(3.0 / 132.0 * PREFERRED_WIDTH);
        mainInnerShadow0.setColor(Color.web("0xffffff80"));
        mainInnerShadow0.setBlurType(BlurType.TWO_PASS_BOX);

        mainInnerShadow1 = new InnerShadow();
        mainInnerShadow1.setOffsetX(0.0);
        mainInnerShadow1.setOffsetY(1.0);
        mainInnerShadow1.setRadius(2.0 / 132.0 * PREFERRED_WIDTH);
        mainInnerShadow1.setColor(Color.web("0x000000a6"));
        mainInnerShadow1.setBlurType(BlurType.TWO_PASS_BOX);
        mainInnerShadow1.setInput(mainInnerShadow0);

        main.setEffect(mainInnerShadow1);

        mainClip = new SVGPath();
        mainClip.setContent("M 1 5 C 1 3 3 1 5 1 C 5 1 127 1 127 1 C 129 1 131 3 131 5 " +
                            "C 131 5 131 43 131 43 C 131 45 129 47 127 47 C 127 47 5 47 5 47 " +
                            "C 3 47 1 45 1 43 C 1 43 1 5 1 5 Z");

        crystalImage = createNoiseImage(132, 48, DARK_NOISE_COLOR, BRIGHT_NOISE_COLOR, 8);
        crystalOverlay = new ImageView(crystalImage);
        crystalOverlay.setClip(mainClip);
        crystalOverlay.setOpacity(getSkinnable().isCrystalOverlayVisible() ? 1 : 0);

        threshold = new Region();
        threshold.getStyleClass().setAll("threshold");
        threshold.setOpacity((getSkinnable().isThresholdVisible() && getSkinnable().isThresholdExceeded()) ? 1 : 0);

        trendDown = new Region();
        trendDown.getStyleClass().setAll("trend-down");
        trendDown.setOpacity((getSkinnable().isTrendVisible() && Lcd.Trend.DOWN == getSkinnable().getTrend()) ? 1 : 0);

        trendFalling = new Region();
        trendFalling.getStyleClass().setAll("trend-falling");
        trendFalling.setOpacity((getSkinnable().isTrendVisible() && Lcd.Trend.FALLING == getSkinnable().getTrend()) ? 1 : 0);

        trendSteady = new Region();
        trendSteady.getStyleClass().setAll("trend-steady");
        trendSteady.setOpacity((getSkinnable().isTrendVisible() && Lcd.Trend.STEADY == getSkinnable().getTrend()) ? 1 : 0);

        trendRising = new Region();
        trendRising.getStyleClass().setAll("trend-rising");
        trendRising.setOpacity((getSkinnable().isTrendVisible() && Lcd.Trend.RISING == getSkinnable().getTrend()) ? 1 : 0);

        trendUp = new Region();
        trendUp.getStyleClass().setAll("trend-up");
        trendUp.setOpacity((getSkinnable().isTrendVisible() && Lcd.Trend.UP == getSkinnable().getTrend()) ? 1 : 0);

        battery = new Region();
        battery.getStyleClass().setAll("battery-empty");
        battery.setOpacity(getSkinnable().isBatteryVisible() ? 1 : 0);

        signal = new Region();
        signal.getStyleClass().setAll("signal");
        signal.setOpacity(getSkinnable().isSignalVisible() ? 1 : 0);

        alarm = new Region();
        alarm.getStyleClass().setAll("alarm");
        alarm.setOpacity(getSkinnable().isAlarmVisible() ? 1 : 0);

        backgroundText = new Text(getSkinnable().isTextMode() ? getSkinnable().getText() : Double.toString(getSkinnable().getValue()));
        backgroundText.getStyleClass().setAll("fg-trsp");
        backgroundText.setOpacity((Lcd.LcdFont.LCD == getSkinnable().getValueFont() || Lcd.LcdFont.ELEKTRA == getSkinnable().getValueFont()) ? 1 : 0);

        text = new Text(getSkinnable().isTextMode() ? getSkinnable().getText() : Double.toString(getSkinnable().getValue()));
        text.getStyleClass().setAll("fg");

        unitText = new Text(getSkinnable().getUnit());
        unitText.getStyleClass().setAll("fg");
        unitText.setOpacity(getSkinnable().isUnitVisible() ? 1 : 0);

        title = new Text(getSkinnable().getTitle());
        title.getStyleClass().setAll("fg");
        title.setOpacity(getSkinnable().isTitleVisible() ? 1 : 0);

        lowerRightText = getSkinnable().isNumberSystemVisible() ? new Text(getSkinnable().getNumberSystem().toString()) : new Text(getSkinnable().getLowerRightText());
        lowerRightText.getStyleClass().setAll("fg");
        lowerRightText.setOpacity(getSkinnable().isLowerRightTextVisible() ? 1 : 0);

        upperLeftText = getSkinnable().isMinMeasuredValueVisible() ? new Text(Double.toString(getSkinnable().getMaxValue())) : new Text(getSkinnable().getUpperLeftText());
        upperLeftText.getStyleClass().setAll("fg");
        upperLeftText.setOpacity(getSkinnable().isMinMeasuredValueVisible() ? 1 : 0);

        upperRightText = getSkinnable().isMaxMeasuredValueVisible() ? new Text(Double.toString(getSkinnable().getMinValue())) : new Text(getSkinnable().getUpperRightText());
        upperRightText.getStyleClass().setAll("fg");
        upperRightText.setOpacity(getSkinnable().isMaxMeasuredValueVisible() ? 1 : 0);

        lowerCenterText = new Text(getSkinnable().isFormerValueVisible() ? Double.toString(getSkinnable().getFormerValue()) : getSkinnable().getLowerCenterText());
        lowerCenterText.getStyleClass().setAll("fg");

        shadowGroup = new Group();
        shadowGroup.setEffect(getSkinnable().isForegroundShadowVisible() ? FOREGROUND_SHADOW : null);
        shadowGroup.getChildren().setAll(threshold,
                                         trendDown,
                                         trendFalling,
                                         trendSteady,
                                         trendRising,
                                         trendUp,
                                         battery,
                                         signal,
                                         alarm,
                                         text,
                                         unitText,
                                         title,
                                         lowerRightText,
                                         upperLeftText,
                                         upperRightText,
                                         lowerCenterText);

        pane.getChildren().setAll(frame,
                                  main,
                                  crystalOverlay,
                                  backgroundText,
                                  shadowGroup);                                  

        getChildren().setAll(pane);

        resize();
        updateLcd();
    }

    private void registerListeners() {
        getSkinnable().widthProperty().addListener(observable -> handleControlPropertyChanged("RESIZE") );
        getSkinnable().heightProperty().addListener(observable -> handleControlPropertyChanged("RESIZE") );
        getSkinnable().titleProperty().addListener(observable -> handleControlPropertyChanged("UPDATE") );
        getSkinnable().unitProperty().addListener(observable -> handleControlPropertyChanged("UPDATE") );
        getSkinnable().lowerRightTextProperty().addListener(observable -> handleControlPropertyChanged("UPDATE") );
        getSkinnable().numberSystemProperty().addListener(observable -> handleControlPropertyChanged("UPDATE") );
        getSkinnable().textModeProperty().addListener(observable -> handleControlPropertyChanged("UPDATE") );
        getSkinnable().textProperty().addListener(observable -> handleControlPropertyChanged("UPDATE") );
        getSkinnable().currentValueProperty().addListener(observable -> handleControlPropertyChanged("UPDATE") );
        getSkinnable().lowerCenterTextProperty().addListener(observable -> handleControlPropertyChanged("UPDATE") );
        getSkinnable().minMeasuredValueProperty().addListener(observable -> handleControlPropertyChanged("UPDATE") );
        getSkinnable().maxMeasuredValueProperty().addListener(observable -> handleControlPropertyChanged("UPDATE") );
        getSkinnable().upperLeftTextProperty().addListener(observable -> handleControlPropertyChanged("UPDATE") );
        getSkinnable().upperRightTextProperty().addListener(observable -> handleControlPropertyChanged("UPDATE") );
        getSkinnable().batteryChargeProperty().addListener(observable -> handleControlPropertyChanged("UPDATE") );
        getSkinnable().signalStrengthProperty().addListener(observable -> handleControlPropertyChanged("UPDATE"));
        getSkinnable().prefWidthProperty().addListener(observable -> handleControlPropertyChanged("PREF_SIZE") );
        getSkinnable().prefHeightProperty().addListener(observable -> handleControlPropertyChanged("PREF_SIZE") );
        getSkinnable().valueFontProperty().addListener(observable -> handleControlPropertyChanged("FONT") );
        getSkinnable().smallFontProperty().addListener(observable -> handleControlPropertyChanged("FONT") );
        getSkinnable().unitFontProperty().addListener(observable -> handleControlPropertyChanged("FONT") );
        getSkinnable().numberSystemVisibleProperty().addListener(observable -> handleControlPropertyChanged("NUMBER_SYSTEM_VISIBLE") );
        getSkinnable().backgroundVisibleProperty().addListener(observable -> handleControlPropertyChanged("BACKGROUND_VISIBLE") );
        getSkinnable().crystalOverlayVisibleProperty().addListener(observable -> handleControlPropertyChanged("CRYSTAL_OVERLAY_VISIBLE") );
        getSkinnable().foregroundShadowVisibleProperty().addListener(observable -> handleControlPropertyChanged("FOREGROUND_SHADOW_VISIBLE") );
        getSkinnable().animationDurationProperty().addListener(observable -> handleControlPropertyChanged("ANIMATION_DURATION") );
        getSkinnable().thresholdExceededProperty().addListener(observable -> handleControlPropertyChanged("THRESHOLD_EXCEEDED") );
        getSkinnable().trendProperty().addListener(observable -> handleControlPropertyChanged("TREND") );
        getSkinnable().valueVisibleProperty().addListener(observable -> handleControlPropertyChanged("VALUE_VISIBLE") );
        getSkinnable().unitVisibleProperty().addListener(observable -> handleControlPropertyChanged("UNIT_VISIBLE") );
        getSkinnable().lowerCenterTextVisibleProperty().addListener(observable -> handleControlPropertyChanged("LOWER_CENTER_VISIBLE") );
        getSkinnable().lowerRightTextVisibleProperty().addListener(observable -> handleControlPropertyChanged("LOWER_RIGHT_VISIBLE") );
        getSkinnable().upperLeftTextVisibleProperty().addListener(observable -> handleControlPropertyChanged("UPPER_LEFT_VISIBLE") );
        getSkinnable().upperRightTextVisibleProperty().addListener(observable -> handleControlPropertyChanged("UPPER_RIGHT_VISIBLE") );
        getSkinnable().batteryVisibleProperty().addListener(observable -> handleControlPropertyChanged("BATTERY_VISIBLE") );
        getSkinnable().signalVisibleProperty().addListener(observable -> handleControlPropertyChanged("SIGNAL_VISIBLE"));
        getSkinnable().alarmVisibleProperty().addListener(observable -> handleControlPropertyChanged("ALARM_VISIBLE") );
        getSkinnable().formerValueVisibleProperty().addListener(observable -> handleControlPropertyChanged("FORMER_VALUE_VISIBLE") );
        getSkinnable().maxMeasuredValueVisibleProperty().addListener(observable -> handleControlPropertyChanged("MAX_MEASURED_VISIBLE") );
        getSkinnable().minMeasuredValueVisibleProperty().addListener(observable -> handleControlPropertyChanged("MIN_MEASURED_VISIBLE") );
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
            main.setOpacity(getSkinnable().isBackgroundVisible() ? 1 : 0);
            crystalOverlay.setOpacity(getSkinnable().isBackgroundVisible() ? 1 : 0);
            frame.setOpacity(getSkinnable().isBackgroundVisible() ? 1 : 0);
        } else if ("CRYSTAL_OVERLAY_VISIBLE".equals(PROPERTY)) {
            crystalOverlay.setOpacity(getSkinnable().isCrystalOverlayVisible() ? 1 : 0);
            resize();
        } else if ("FOREGROUND_SHADOW_VISIBLE".equals(PROPERTY)) {
            shadowGroup.setEffect(getSkinnable().isForegroundShadowVisible() ? FOREGROUND_SHADOW : null);
        } else if ("TREND".equals(PROPERTY)) {
            updateTrend();
        } else if ("THRESHOLD_EXCEEDED".equals(PROPERTY)) {
            if (getSkinnable().isThresholdVisible()) {
                threshold.setOpacity(getSkinnable().isThresholdExceeded() ? 1 : 0);
            }
        } else if ("FONT".equals(PROPERTY)) {
            updateFonts();
        } else if ("VALUE_VISIBLE".equals(PROPERTY)) {
            text.setOpacity(getSkinnable().isValueVisible() ? 1 : 0);
        } else if ("UNIT_VISIBLE".equals(PROPERTY)) {
            unitText.setOpacity(getSkinnable().isUnitVisible() ? 1 : 0);
        } else if ("FORMER_VALUE_VISIBLE".equals(PROPERTY)) {
            lowerCenterText.setOpacity(getSkinnable().isFormerValueVisible() ? 1 : 0);
        } else if ("MAX_MEASURED_VISIBLE".equals(PROPERTY)) {
            upperRightText.setOpacity(getSkinnable().isMaxMeasuredValueVisible() ? 1 : 0);
        } else if ("MIN_MEASURED_VISIBLE".equals(PROPERTY)) {
            upperLeftText.setOpacity(getSkinnable().isMinMeasuredValueVisible() ? 1 : 0);
        } else if ("NUMBER_SYSTEM_VISIBLE".equals(PROPERTY)) {
            updateLcd();
        } else if ("LOWER_RIGHT_VISIBLE".equals(PROPERTY)) {
            lowerRightText.setOpacity(getSkinnable().isLowerRightTextVisible() ? 1 : 0);
        } else if ("UPPER_LEFT_VISIBLE".equals(PROPERTY)) {
            upperLeftText.setOpacity(getSkinnable().isUpperLeftTextVisible() ? 1 : 0);
        } else if ("UPPER_RIGHT_VISIBLE".equals(PROPERTY)) {
            upperRightText.setOpacity(getSkinnable().isUpperRightTextVisible() ? 1 : 0);
        } else if ("BATTERY_VISIBLE".equals(PROPERTY)) {
            battery.setOpacity(getSkinnable().isBatteryVisible() ? 1 : 0);
        } else if ("SIGNAL_VISIBLE".equals(PROPERTY)) {
            signal.setOpacity(getSkinnable().isSignalVisible() ? 1 : 0);
        } else if ("ALARM_VISIBLE".equals(PROPERTY)) {
            alarm.setOpacity(getSkinnable().isAlarmVisible() ? 1 : 0);
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
        final double AVAILABLE_WIDTH = width - 2 - valueOffsetLeft - valueOffsetRight;
        final double NEEDED_WIDTH    = text.getLayoutBounds().getWidth();

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
        digitalFontSizeFactor = 1.0;
        switch(getSkinnable().getValueFont()) {
            case BUS:
                //valueFont = Font.loadFont(getClass().getResourceAsStream("/eu/hansolo/enzo/fonts/bus.otf"), (0.4583333333 * height));
                valueFont = Font.font("Bus-Regular", (0.4583333333 * height));
                break;
            case LCD:
                //valueFont = Font.loadFont(getClass().getResourceAsStream("/eu/hansolo/enzo/fonts/digital.ttf"), (0.5833333333 * height));
                valueFont = Font.font("Digital-7", (0.5833333333 * height));
                //digitalFontSizeFactor = 1.9098073909;
                digitalFontSizeFactor = 1.3;
                break;
            case DIGITAL:
                //valueFont = Font.loadFont(getClass().getResourceAsStream("/eu/hansolo/enzo/fonts/digitalreadout.ttf"), (0.5833333333 * height));
                valueFont = Font.font("Digital Readout Upright", (0.5833333333 * height));
                break;
            case DIGITAL_BOLD:
                //valueFont = Font.loadFont(getClass().getResourceAsStream("/eu/hansolo/enzo/fonts/digitalreadoutb.ttf"), (0.5833333333 * height));
                valueFont = Font.font("Digital Readout Thick Upright", (0.5833333333 * height));
                break;
            case PIXEL:
                //valueFont = Font.loadFont(getClass().getResourceAsStream("/eu/hansolo/enzo/fonts/pixel.ttf"), (0.5208333333 * height));
                valueFont = Font.font("Electronic Highway Sign", (0.5208333333 * height));
                break;
            case PHONE_LCD:
                //valueFont = Font.loadFont(getClass().getResourceAsStream("/eu/hansolo/enzo/fonts/phonelcd.ttf"), (0.4583333333 * height));
                valueFont = Font.font("Nokian", (0.4583333333 * height));
                break;
            case ELEKTRA:
                //valueFont = Font.loadFont(getClass().getResourceAsStream("/eu/hansolo/enzo/fonts/elektra.ttf"), (0.58333333 * height));
                valueFont = Font.font("Elektra", (0.58333333 * height));
                break;
            case STANDARD:
            default:
                valueFont = Font.font("Arial", FontWeight.NORMAL, (0.5 * height));
                break;
        }
        backgroundText.setFont(valueFont);
        backgroundText.setOpacity((Lcd.LcdFont.LCD == getSkinnable().getValueFont() ||
                                   Lcd.LcdFont.DIGITAL == getSkinnable().getValueFont() ||
                                   Lcd.LcdFont.DIGITAL_BOLD == getSkinnable().getValueFont() ||
                                   Lcd.LcdFont.ELEKTRA == getSkinnable().getValueFont()) ? 1 : 0);
        text.setFont(valueFont);
        unitFont  = Font.font(getSkinnable().getUnitFont(), FontWeight.NORMAL, (0.26 * height));
        titleFont = Font.font(getSkinnable().getTitleFont(), FontWeight.BOLD, (0.1666666667 * height));
        smallFont = Font.font(getSkinnable().getSmallFont(), FontWeight.NORMAL, (0.1666666667 * height));
    }

    private void updateTrend() {
        if (getSkinnable().isTrendVisible()) {
            switch (getSkinnable().getTrend()) {
                case UP:
                    trendUp.setOpacity(1);
                    trendRising.setOpacity(0);
                    trendSteady.setOpacity(0);
                    trendFalling.setOpacity(0);
                    trendDown.setOpacity(0);
                    break;
                case RISING:
                    trendUp.setOpacity(0);
                    trendRising.setOpacity(1);
                    trendSteady.setOpacity(0);
                    trendFalling.setOpacity(0);
                    trendDown.setOpacity(0);
                    break;
                case STEADY:
                    trendUp.setOpacity(0);
                    trendRising.setOpacity(0);
                    trendSteady.setOpacity(1);
                    trendFalling.setOpacity(0);
                    trendDown.setOpacity(0);
                    break;
                case FALLING:
                    trendUp.setOpacity(0);
                    trendRising.setOpacity(0);
                    trendSteady.setOpacity(0);
                    trendFalling.setOpacity(1);
                    trendDown.setOpacity(0);
                    break;
                case DOWN:
                    trendUp.setOpacity(0);
                    trendRising.setOpacity(0);
                    trendSteady.setOpacity(0);
                    trendFalling.setOpacity(0);
                    trendDown.setOpacity(1);
                    break;
                default:
                    trendUp.setOpacity(0);
                    trendRising.setOpacity(0);
                    trendSteady.setOpacity(0);
                    trendFalling.setOpacity(0);
                    trendDown.setOpacity(0);
                    break;
            }
        }
    }

    private void updateBackgroundText() {
        // Setup the semitransparent background text
        backgroundText.setTextOrigin(VPos.BASELINE);
        backgroundText.setTextAlignment(TextAlignment.RIGHT);

        // Setup the semitransparent background text
        // Width of one segment
        oneSegment.setFont(valueFont);
        if (Lcd.LcdFont.LCD == getSkinnable().getValueFont()) {
            oneSegment.setText("8");
        } else if (Lcd.LcdFont.DIGITAL == getSkinnable().getValueFont()) {
            oneSegment.setText("_");
        } else if (Lcd.LcdFont.DIGITAL_BOLD == getSkinnable().getValueFont()) {
            oneSegment.setText("_");
        } else if (Lcd.LcdFont.ELEKTRA == getSkinnable().getValueFont()) {
            oneSegment.setText("_");
        }
        oneSegmentWidth = oneSegment.getLayoutBounds().getWidth();

        // Width of decimals
        widthOfDecimals = getSkinnable().getDecimals() == 0 ? 0 : getSkinnable().getDecimals() * oneSegmentWidth + oneSegmentWidth;

        // Available width
        availableWidth = width - 2 - valueOffsetRight - widthOfDecimals;

        // Number of segments
        noOfSegments = (int) Math.floor(availableWidth / oneSegmentWidth);

        // Add segments to background text
        backgroundTextBuilder.setLength(0);
        for (int i = 0 ; i < getSkinnable().getDecimals() ; i++) {
            backgroundTextBuilder.append(oneSegment.getText());
        }
        if (getSkinnable().getDecimals() != 0) {
            backgroundTextBuilder.insert(0, ".");
        }

        for (int i = 0 ; i < noOfSegments ; i++) {
            backgroundTextBuilder.insert(0, oneSegment.getText());
        }
        backgroundText.setText(backgroundTextBuilder.toString());
    }

    private void updateLcd() {
        switch (getSkinnable().getNumberSystem()) {
            case HEXADECIMAL:
                text.setText(Integer.toHexString((int) getSkinnable().getCurrentValue()).toUpperCase());
                break;
            case OCTAL:
                text.setText(Integer.toOctalString((int) getSkinnable().getCurrentValue()).toUpperCase());
                break;
            case DECIMAL:
            default:
                text.setText(formatLcdValue(getSkinnable().getCurrentValue(), getSkinnable().getDecimals()));
                break;
        }

        if (isNoOfDigitsInvalid()) {
            text.setText("-E-");
        }

        updateBackgroundText();

        // Visualize the lcd semitransparent background text
        if (getSkinnable().isUnitVisible()) {
            backgroundText.setX(width - 2 - backgroundText.getLayoutBounds().getWidth() - valueOffsetRight);
        } else {
            backgroundText.setX((width - backgroundText.getLayoutBounds().getWidth()) - valueOffsetRight);
        }
        backgroundText.setY(height - (backgroundText.getLayoutBounds().getHeight() * digitalFontSizeFactor) * 0.5);

        if (getSkinnable().isUnitVisible()) {
            text.setX((width - 2 - text.getLayoutBounds().getWidth()) - valueOffsetRight);
        } else {
            text.setX((width - text.getLayoutBounds().getWidth()) - valueOffsetRight);
        }

        // Update the title
        title.setText(getSkinnable().getTitle());
        title.setX((width - title.getLayoutBounds().getWidth()) * 0.5);

        // Update the upper left text
        upperLeftText.setText(getSkinnable().isMinMeasuredValueVisible() ? formatLcdValue(getSkinnable().getMinMeasuredValue(), getSkinnable().getMinMeasuredValueDecimals()) : getSkinnable().getUpperLeftText());
        if (upperLeftText.getX() + upperLeftText.getLayoutBounds().getWidth() > title.getX()) {
            upperLeftText.setText("...");
        }

        // Update the upper right text
        upperRightText.setText(getSkinnable().isMaxMeasuredValueVisible() ? formatLcdValue(getSkinnable().getMaxMeasuredValue(), getSkinnable().getMaxMeasuredValueDecimals()) : getSkinnable().getUpperRightText());
        upperRightText.setX(width - upperRightText.getLayoutBounds().getWidth() - 0.0416666667 * height);
        if (upperRightText.getX() < title.getX() + title.getLayoutBounds().getWidth()) {
            upperRightText.setText("...");
            upperRightText.setX(width - upperRightText.getLayoutBounds().getWidth() - 0.0416666667 * height);
        }

        // Update the lower center text
        lowerCenterText.setText(getSkinnable().isFormerValueVisible() ? formatLcdValue(getSkinnable().getFormerValue(), getSkinnable().getDecimals()) : getSkinnable().getLowerCenterText());
        lowerCenterText.setX((width - lowerCenterText.getLayoutBounds().getWidth()) * 0.5);

        // Update the lower right text
        lowerRightText.setText(getSkinnable().isNumberSystemVisible() ? getSkinnable().getNumberSystem().toString() : getSkinnable().getLowerRightText());
        lowerRightText.setX(width - lowerRightText.getLayoutBounds().getWidth() - 0.0416666667 * height);
        lowerRightText.setY(main.getLayoutY() + height - 1 - 0.0416666667 * height);
        if (lowerRightText.getX() < lowerCenterText.getX() + lowerCenterText.getLayoutBounds().getWidth()) {
            lowerRightText.setText("...");
            lowerRightText.setX(width - lowerRightText.getLayoutBounds().getWidth() - 0.0416666667 * height);
        }

        // Update battery charge
        if (getSkinnable().getBatteryCharge() < 0.01) {
            battery.getStyleClass().setAll("battery-empty");
        } else if (getSkinnable().getBatteryCharge() < 0.06) {
            battery.getStyleClass().setAll("battery-almost-empty");
        } else if (getSkinnable().getBatteryCharge() < 0.26) {
            battery.getStyleClass().setAll("battery-25");
        } else if (getSkinnable().getBatteryCharge() < 0.51) {
            battery.getStyleClass().setAll("battery-50");
        } else if (getSkinnable().getBatteryCharge() < 0.76) {
            battery.getStyleClass().setAll("battery-75");
        } else if (getSkinnable().getBatteryCharge() < 0.96) {
            battery.getStyleClass().setAll("battery-almost-full");
        } else {
            battery.getStyleClass().setAll("battery-full");
        }

        // Update signal strength
        if (getSkinnable().getSignalStrength() < 0.06) {
            signal.getStyleClass().setAll("signal", "signal-0");
        } else if (getSkinnable().getSignalStrength() < 0.26) {
            signal.getStyleClass().setAll("signal", "signal-25");
        } else if (getSkinnable().getSignalStrength() < 0.51) {
            signal.getStyleClass().setAll("signal", "signal-50");
        } else if (getSkinnable().getSignalStrength() < 0.85) {
            signal.getStyleClass().setAll("signal", "signal-75");
        } else {
            signal.getStyleClass().setAll("signal", "signal-100");
        }
    }

    private void resize() {
        width  = getSkinnable().getWidth();
        height = getSkinnable().getHeight();
        if (getSkinnable().isKeepAspect()) {
            if (aspectRatio * width > height) {
                width = 1 / (aspectRatio / height);
            } else if (1 / (aspectRatio / height) > width) {
                height = aspectRatio * width;
            }
        }

        if (width > 0 && height > 0) {
            frame.setPrefSize(width, height);

            main.setPrefSize(width - 2.0, height - 2.0);
            main.setTranslateX(1);
            main.setTranslateY(1);
            mainInnerShadow0.setRadius(3.0 / 132.0 * height);
            mainInnerShadow1.setRadius(2.0 / 132.0 * height);

            if (crystalOverlay.isVisible()) {
                mainClip.setScaleX(width / (PREFERRED_WIDTH - 2.0));
                mainClip.setScaleY(height / (PREFERRED_HEIGHT - 2.0));
                mainClip.setTranslateX((width - PREFERRED_WIDTH - 2) * 0.5);
                mainClip.setTranslateY((height - PREFERRED_HEIGHT - 2) * 0.5);
                crystalOverlay.setImage(createNoiseImage(width, height, DARK_NOISE_COLOR, BRIGHT_NOISE_COLOR, 8));
                crystalOverlay.setCache(true);
            }

            threshold.setPrefSize(0.20 * height, 0.20 * height);
            threshold.setTranslateX(0.027961994662429348 * width);
            threshold.setTranslateY(0.75 * height);

            trendDown.setPrefSize(0.06718573425755356 * width, 0.1333622932434082 * height);
            trendDown.setTranslateX(0.1439393939 * width);
            trendDown.setTranslateY(0.8125 * height);

            trendFalling.setPrefSize(0.06982171896732214 * width, 0.13879903157552084 * height);
            trendFalling.setTranslateX(0.1439393939 * width);
            trendFalling.setTranslateY(0.8061291376749674 * height);

            trendSteady.setPrefSize(0.0676060878869259 * width, 0.1342292626698812 * height);
            trendSteady.setTranslateX(0.1439393939 * width);
            trendSteady.setTranslateY(0.8078853289286295 * height);

            trendRising.setPrefSize(0.06982171896732214 * width, 0.13879903157552084 * height);
            trendRising.setTranslateX(0.1439393939 * width);
            trendRising.setTranslateY(0.8050718307495117 * height);

            trendUp.setPrefSize(0.06718573425755356 * width, 0.1333622932434082 * height);
            trendUp.setTranslateX(0.1439393939 * width);
            trendUp.setTranslateY(0.8041377067565918 * height);

            battery.setPrefSize(0.0833333333 * width, 0.1458333333 * height);
            battery.setTranslateX(0.6439393939 * width);
            battery.setTranslateY(0.81 * height);

            signal.setPrefSize(0.0416666667 * height, 0.5 * height);
            signal.setTranslateX(0.0151515152 * width);
            signal.setTranslateY(0.28 * height);

            alarm.setPrefSize(0.1666666667 * height, 0.1666666667 * height);
            alarm.setTranslateX(0.2651515152 * width);
            alarm.setTranslateY(0.7916666667 * height);

            updateFonts();

            // Setup the lcd unit
            unitText.setFont(unitFont);
            unitText.setTextOrigin(VPos.BASELINE);
            unitText.setTextAlignment(TextAlignment.RIGHT);

            unitText.setText(getSkinnable().getUnit());
            if (unitText.visibleProperty().isBound()) {
                unitText.visibleProperty().unbind();
            }
            unitText.visibleProperty().bind(getSkinnable().unitVisibleProperty());

            valueOffsetLeft = height * 0.04;

            if (getSkinnable().isUnitVisible()) {
                unitText.setX((width - unitText.getLayoutBounds().getWidth()) - height * 0.04);
                unitText.setY(height - (text.getLayoutBounds().getHeight() * digitalFontSizeFactor) * 0.5);
                valueOffsetRight = (unitText.getLayoutBounds().getWidth() + height * 0.0833333333); // distance between value and unit
                text.setX(width - 2 - text.getLayoutBounds().getWidth() - valueOffsetRight);
            } else {
                valueOffsetRight = height * 0.0833333333;
                text.setX((width - text.getLayoutBounds().getWidth()) - valueOffsetRight);
            }
            text.setY(height - (text.getLayoutBounds().getHeight() * digitalFontSizeFactor) * 0.5);

            // Visualize the lcd semitransparent background text
            updateBackgroundText();

            if (getSkinnable().isUnitVisible()) {
                backgroundText.setX(width - 2 - backgroundText.getLayoutBounds().getWidth() - valueOffsetRight);
            } else {
                backgroundText.setX((width - backgroundText.getLayoutBounds().getWidth()) - valueOffsetRight);
            }
            backgroundText.setY(height - (backgroundText.getLayoutBounds().getHeight() * digitalFontSizeFactor) * 0.5);

            // Setup the font for the lcd title, number system, min measured, max measure and former value
            // Title
            title.setFont(titleFont);
            title.setTextOrigin(VPos.BASELINE);
            title.setTextAlignment(TextAlignment.CENTER);
            title.setText(getSkinnable().getTitle());
            title.setX((width - title.getLayoutBounds().getWidth()) * 0.5);
            title.setY(main.getLayoutY() + title.getLayoutBounds().getHeight() + 0.04 * height);

            // Info Text
            lowerRightText.setFont(smallFont);
            lowerRightText.setTextOrigin(VPos.BASELINE);
            lowerRightText.setTextAlignment(TextAlignment.RIGHT);
            lowerRightText.setText(getSkinnable().getNumberSystem().toString());
            lowerRightText.setX(main.getLayoutX() + (main.getLayoutBounds().getWidth() - lowerRightText.getLayoutBounds().getWidth()) * 0.5);
            lowerRightText.setY(main.getLayoutY() + height - 1 - 0.0416666667 * height);

            // Min measured value
            upperLeftText.setFont(smallFont);
            upperLeftText.setTextOrigin(VPos.BASELINE);
            upperLeftText.setTextAlignment(TextAlignment.RIGHT);
            upperLeftText.setX(main.getLayoutX() + 0.0416666667 * height);
            upperLeftText.setY(main.getLayoutY() + upperLeftText.getLayoutBounds().getHeight() + 0.04 * height);

            // Max measured value
            upperRightText.setFont(smallFont);
            upperRightText.setTextOrigin(VPos.BASELINE);
            upperRightText.setTextAlignment(TextAlignment.RIGHT);
            upperRightText.setY(main.getLayoutY() + upperRightText.getLayoutBounds().getHeight() + 0.04 * height);

            // Former value
            lowerCenterText.setFont(smallFont);
            lowerCenterText.setTextOrigin(VPos.BASELINE);
            lowerCenterText.setTextAlignment(TextAlignment.CENTER);
            lowerCenterText.setX((width - lowerCenterText.getLayoutBounds().getWidth()) * 0.5);
            lowerCenterText.setY(main.getLayoutY() + height - 1 - 0.0416666667 * height);
        }
    }
}
