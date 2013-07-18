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

package eu.hansolo.enzo.lcd;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Dimension2D;

import java.util.HashMap;


/**
 * Created by
 * User: hansolo
 * Date: 14.03.12
 * Time: 15:34
 */
public class LcdBuilder<B extends LcdBuilder<B>> {
    private HashMap<String, Property> properties = new HashMap<>();


    // ******************** Constructors **************************************
    protected LcdBuilder() {}


    // ******************** Methods *******************************************
    public static final LcdBuilder create() {
        return new LcdBuilder();
    }

    public final LcdBuilder styleClass(final String STYLE_CLASS) {
        properties.put("styleClass", new SimpleStringProperty(STYLE_CLASS));
        return this;
    }

    public final LcdBuilder textMode(final boolean TEXT_MODE) {
        properties.put("textMode", new SimpleBooleanProperty(TEXT_MODE));
        return this;
    }

    public final LcdBuilder text(final String TEXT) {
        properties.put("text", new SimpleStringProperty(TEXT));
        return this;
    }

    public final LcdBuilder value(final double VALUE) {
        properties.put("value", new SimpleDoubleProperty(VALUE));
        return this;
    }

    public final LcdBuilder minValue(final double MIN_VALUE) {
        properties.put("minValue", new SimpleDoubleProperty(MIN_VALUE));
        return this;
    }

    public final LcdBuilder maxValue(final double MAX_VALUE) {
        properties.put("maxValue", new SimpleDoubleProperty(MAX_VALUE));
        return this;
    }

    public final LcdBuilder animated(final boolean ANIMATED) {
        properties.put("animated", new SimpleBooleanProperty(ANIMATED));
        return this;
    }

    public final LcdBuilder animationDurationInMs(final double ANIMATION_DURATION_IN_MS) {
        properties.put("animationDuration", new SimpleDoubleProperty(ANIMATION_DURATION_IN_MS));
        return this;
    }

    public final LcdBuilder threshold(final double THRESHOLD) {
        properties.put("threshold", new SimpleDoubleProperty(THRESHOLD));
        return this;
    }

    public final LcdBuilder decimals(final int DECIMALS) {
        properties.put("decimals", new SimpleIntegerProperty(DECIMALS));
        return this;
    }

    public final LcdBuilder keepAspect(final boolean KEEP_ASPECT) {
        properties.put("keepAspect", new SimpleBooleanProperty(KEEP_ASPECT));
        return this;
    }

    public final LcdBuilder backgroundVisible(final boolean BACKGROUND_VISIBLE) {
        properties.put("backgroundVisible", new SimpleBooleanProperty(BACKGROUND_VISIBLE));
        return this;
    }

    public final LcdBuilder crystalOverlayVisible(final boolean CRYSTAL_OVERLAY_VISIBLE) {
        properties.put("crystalOverlayVisible", new SimpleBooleanProperty(CRYSTAL_OVERLAY_VISIBLE));
        return this;
    }

    public final LcdBuilder foregroundShadowVisible(final boolean FOREGROUND_SHADOW_VISIBLE) {
        properties.put("foregroundShadowVisible", new SimpleBooleanProperty(FOREGROUND_SHADOW_VISIBLE));
        return this;
    }

    public final LcdBuilder minMeasuredValueVisible(final boolean MIN_MEASURED_VALUE_VISIBLE) {
        properties.put("minMeasuredValueVisible", new SimpleBooleanProperty(MIN_MEASURED_VALUE_VISIBLE));
        return this;
    }

    public final LcdBuilder minMeasuredValueDecimals(final int MIN_MEASURED_VALUE_DECIMALS) {
        properties.put("minMeasuredValueDecimals", new SimpleIntegerProperty(MIN_MEASURED_VALUE_DECIMALS));
        return this;
    }

    public final LcdBuilder maxMeasuredValueVisible(final boolean MAX_MEASURED_VALUE_VISIBLE) {
        properties.put("maxMeasuredValueVisible", new SimpleBooleanProperty(MAX_MEASURED_VALUE_VISIBLE));
        return this;
    }

    public final LcdBuilder maxMeasuredValueDecimals(final int MAX_MEASURED_VALUE_DECIMALS) {
        properties.put("maxMeasuredValueDecimals", new SimpleIntegerProperty(MAX_MEASURED_VALUE_DECIMALS));
        return this;
    }

    public final LcdBuilder lowerCenterText(final String LOWER_CENTER_TEXT) {
        properties.put("lowerCenterText", new SimpleStringProperty(LOWER_CENTER_TEXT));
        return this;
    }

    public final LcdBuilder lowerCenterTextVisible(final boolean LOWER_CENTER_TEXT_VISIBLE) {
        properties.put("lowerCenterTextVisible", new SimpleBooleanProperty(LOWER_CENTER_TEXT_VISIBLE));
        return this;
    }

    public final LcdBuilder formerValueVisible(final boolean FORMER_VALUE_VISIBLE) {
        properties.put("formerValueVisible", new SimpleBooleanProperty(FORMER_VALUE_VISIBLE));
        return this;
    }

    public final LcdBuilder title(final String TITLE) {
        properties.put("title", new SimpleStringProperty(TITLE));
        return this;
    }

    public final LcdBuilder titleVisible(final boolean TITLE_VISIBLE) {
        properties.put("titleVisible", new SimpleBooleanProperty(TITLE_VISIBLE));
        return this;
    }

    public final LcdBuilder unit(final String UNIT) {
        properties.put("unit", new SimpleStringProperty(UNIT));
        return this;
    }

    public final LcdBuilder unitVisible(final boolean UNIT_VISIBLE) {
        properties.put("unitVisible", new SimpleBooleanProperty(UNIT_VISIBLE));
        return this;
    }

    public final LcdBuilder lowerRightText(final String LOWER_RIGHT_TEXT) {
        properties.put("lowerRightText", new SimpleStringProperty(LOWER_RIGHT_TEXT));
        return this;
    }

    public final LcdBuilder lowerRightTextVisible(final boolean LOWER_RIGHT_TEXT_VISIBLE) {
        properties.put("lowerRightTextVisible", new SimpleBooleanProperty(LOWER_RIGHT_TEXT_VISIBLE));
        return this;
    }

    public final LcdBuilder upperLeftText(final String UPPER_LEFT_TEXT) {
        properties.put("upperLeftText", new SimpleStringProperty(UPPER_LEFT_TEXT));
        return this;
    }

    public final LcdBuilder upperLeftTextVisible(final boolean UPPER_LEFT_TEXT_VISIBLE) {
        properties.put("upperLeftTextVisible", new SimpleBooleanProperty(UPPER_LEFT_TEXT_VISIBLE));
        return this;
    }

    public final LcdBuilder upperRightText(final String UPPER_RIGHT_TEXT) {
        properties.put("upperRightText", new SimpleStringProperty(UPPER_RIGHT_TEXT));
        return this;
    }

    public final LcdBuilder upperRightTextVisible(final boolean UPPER_RIGHT_TEXT_VISIBLE) {
        properties.put("upperRightTextVisible", new SimpleBooleanProperty(UPPER_RIGHT_TEXT_VISIBLE));
        return this;
    }

    public final LcdBuilder trendVisible(final boolean TREND_VISIBLE) {
        properties.put("trendVisible", new SimpleBooleanProperty(TREND_VISIBLE));
        return this;
    }

    public final LcdBuilder trend(final Lcd.Trend TREND) {
        properties.put("trend", new SimpleObjectProperty<>(TREND));
        return this;
    }

    public final LcdBuilder batteryCharge(final double BATTERY_CHARGE) {
        properties.put("batteryCharge", new SimpleDoubleProperty(BATTERY_CHARGE));
        return this;
    }

    public final LcdBuilder batteryVisible(final boolean BATTERY_VISIBLE) {
        properties.put("batteryVisible", new SimpleBooleanProperty(BATTERY_VISIBLE));
        return this;
    }

    public final LcdBuilder signalStrength(final double SIGNAL_STRENGTH) {
        properties.put("signalStrength", new SimpleDoubleProperty(SIGNAL_STRENGTH));
        return this;
    }

    public final LcdBuilder signalVisible(final boolean SIGNAL_VISIBLE) {
        properties.put("signalVisible", new SimpleBooleanProperty(SIGNAL_VISIBLE));
        return this;
    }

    public final LcdBuilder alarmVisible(final boolean ALARM_VISIBLE) {
        properties.put("alarmVisible", new SimpleBooleanProperty(ALARM_VISIBLE));
        return this;
    }

    public final LcdBuilder thresholdVisible(final boolean THRESHOLD_VISIBLE) {
        properties.put("thresholdVisible", new SimpleBooleanProperty(THRESHOLD_VISIBLE));
        return this;
    }

    public final LcdBuilder thresholdBehaviorInverted(final boolean THRESHOLD_BEHAVIOR_INVERTED) {
        properties.put("thresholdBehaviorInverted", new SimpleBooleanProperty(THRESHOLD_BEHAVIOR_INVERTED));
        return this;
    }

    public final LcdBuilder numberSystem(final Lcd.NumberSystem NUMBER_SYSTEM) {
        properties.put("numberSystem", new SimpleObjectProperty<Lcd.NumberSystem>(NUMBER_SYSTEM));
        return this;
    }

    public final LcdBuilder numberSystemVisible(final boolean NUMBER_SYSTEM_VISIBLE) {
        properties.put("numberSystemVisible", new SimpleBooleanProperty(NUMBER_SYSTEM_VISIBLE));
        return this;
    }

    public final LcdBuilder titleFont(final String TITLE_FONT) {
        properties.put("titleFont", new SimpleStringProperty(TITLE_FONT));
        return this;
    }

    public final LcdBuilder unitFont(final String UNIT_FONT) {
        properties.put("unitFont", new SimpleStringProperty(UNIT_FONT));
        return this;
    }

    public final LcdBuilder valueFont(final Lcd.LcdFont VALUE_FONT) {
        properties.put("valueFont", new SimpleObjectProperty<Lcd.LcdFont>(VALUE_FONT));
        return this;
    }

    public final LcdBuilder smallFont(final String SMALL_FONT) {
        properties.put("smallFont", new SimpleStringProperty(SMALL_FONT));
        return this;
    }

    public final B prefSize(final double WIDTH, final double HEIGHT) {
        properties.put("prefSize", new SimpleObjectProperty<>(new Dimension2D(WIDTH, HEIGHT)));
        return (B)this;
    }

    public final B prefWidth(final double PREF_WIDTH) {
        properties.put("prefWidth", new SimpleDoubleProperty(PREF_WIDTH));
        return (B)this;
    }
    public final B prefHeight(final double PREF_HEIGHT) {
        properties.put("prefHeight", new SimpleDoubleProperty(PREF_HEIGHT));
        return (B)this;
    }

    public final B minWidth(final double MIN_WIDTH) {
        properties.put("minWidth", new SimpleDoubleProperty(MIN_WIDTH));
        return (B)this;
    }
    public final B minHeight(final double MIN_HEIGHT) {
        properties.put("minHeight", new SimpleDoubleProperty(MIN_HEIGHT));
        return (B)this;
    }

    public final B maxWidth(final double MAX_WIDTH) {
        properties.put("maxWidth", new SimpleDoubleProperty(MAX_WIDTH));
        return (B)this;
    }
    public final B maxHeight(final double MAX_HEIGHT) {
        properties.put("maxHeight", new SimpleDoubleProperty(MAX_HEIGHT));
        return (B)this;
    }

    public final B scaleX(final double SCALE_X) {
        properties.put("scaleX", new SimpleDoubleProperty(SCALE_X));
        return (B)this;
    }
    public final B scaleY(final double SCALE_Y) {
        properties.put("scaleY", new SimpleDoubleProperty(SCALE_Y));
        return (B)this;
    }

    public final B layoutX(final double LAYOUT_X) {
        properties.put("layoutX", new SimpleDoubleProperty(LAYOUT_X));
        return (B)this;
    }
    public final B layoutY(final double LAYOUT_Y) {
        properties.put("layoutY", new SimpleDoubleProperty(LAYOUT_Y));
        return (B)this;
    }

    public final B translateX(final double TRANSLATE_X) {
        properties.put("translateX", new SimpleDoubleProperty(TRANSLATE_X));
        return (B)this;
    }
    public final B translateY(final double TRANSLATE_Y) {
        properties.put("translateY", new SimpleDoubleProperty(TRANSLATE_Y));
        return (B)this;
    }

    public final Lcd build() {
        final Lcd CONTROL = new Lcd();
        properties.forEach((key, property) -> {
            if ("prefSize".equals(key)) {
                Dimension2D dim = ((ObjectProperty<Dimension2D>) property).get();
                CONTROL.setPrefSize(dim.getWidth(), dim.getHeight());
            } else if("prefWidth".equals(key)) {
                CONTROL.setPrefWidth(((DoubleProperty) property).get());
            } else if("prefHeight".equals(key)) {
                CONTROL.setPrefHeight(((DoubleProperty) property).get());
            } else if("minWidth".equals(key)) {
                CONTROL.setMinWidth(((DoubleProperty) property).get());
            } else if("minHeight".equals(key)) {
                CONTROL.setMinHeight(((DoubleProperty) property).get());
            } else if("maxWidth".equals(key)) {
                CONTROL.setMaxWidth(((DoubleProperty) property).get());
            } else if("maxHeight".equals(key)) {
                CONTROL.setMaxHeight(((DoubleProperty) property).get());
            } else if("scaleX".equals(key)) {
                CONTROL.setScaleX(((DoubleProperty) property).get());
            } else if("scaleY".equals(key)) {
                CONTROL.setScaleY(((DoubleProperty) property).get());
            } else if ("layoutX".equals(key)) {
                CONTROL.setLayoutX(((DoubleProperty) property).get());
            } else if ("layoutY".equals(key)) {
                CONTROL.setLayoutY(((DoubleProperty) property).get());
            } else if ("translateX".equals(key)) {
                CONTROL.setTranslateX(((DoubleProperty) property).get());
            } else if ("translateY".equals(key)) {
                CONTROL.setTranslateY(((DoubleProperty) property).get());
            } else if("styleClass".equals(key)) {
                CONTROL.getStyleClass().setAll("lcd", ((StringProperty) property).get());
            } else if("textMode".equals(key)) {
                CONTROL.setTextMode(((BooleanProperty) property).get());
            } else if("text".equals(key)) {
                CONTROL.setText(((StringProperty) property).get());
            } else if("value".equals(key)) {
                CONTROL.setValue(((DoubleProperty) property).get());
            } else if("minValue".equals(key)) {
                CONTROL.setMinValue(((DoubleProperty) property).get());
            } else if("maxValue".equals(key)) {
                CONTROL.setMaxValue(((DoubleProperty) property).get());
            } else if("animated".equals(key)) {
                CONTROL.setAnimated(((BooleanProperty) property).get());
            } else if ("animationDuration".equals(key)) {
                CONTROL.setAnimationDuration(((DoubleProperty) property).get());
            } else if("threshold".equals(key)) {
                CONTROL.setThreshold(((DoubleProperty) property).get());
            } else if("decimals".equals(key)) {
                CONTROL.setDecimals(((IntegerProperty) property).get());
            } else if ("keepAspect".equals(key)) {
                CONTROL.setKeepAspect(((BooleanProperty) property).get());
            } else if ("backgroundVisible".equals(key)) {
                CONTROL.setBackgroundVisible(((BooleanProperty) property).get());
            } else if ("crystalOverlayVisible".equals(key)) {
                CONTROL.setCrystalOverlayVisible(((BooleanProperty) property).get());
            } else if ("foregroundShadowVisible".equals(key)) {
                CONTROL.setForegroundShadowVisible(((BooleanProperty) property).get());
            } else if("minMeasuredValueVisible".equals(key)) {
                CONTROL.setMinMeasuredValueVisible(((BooleanProperty) property).get());
            } else if("minMeasuredValueDecimals".equals(key)) {
                CONTROL.setMinMeasuredValueDecimals(((IntegerProperty) property).get());
            } else if ("maxMeasuredValueVisible".equals(key)) {
                CONTROL.setMaxMeasuredValueVisible(((BooleanProperty) property).get());
            } else if ("maxMeasuredValueDecimals".equals(key)) {
                CONTROL.setMaxMeasuredValueDecimals(((IntegerProperty) property).get());
            } else if ("formerValueVisible".equals(key)) {
                CONTROL.setFormerValueVisible(((BooleanProperty) property).get());
            } else if ("title".equals(key)) {
                CONTROL.setTitle(((StringProperty) property).get());
            } else if ("titleVisible".equals(key)) {
                CONTROL.setTitleVisible(((BooleanProperty) property).get());
            } else if ("unit".equals(key)) {
                CONTROL.setUnit(((StringProperty) property).get());
            } else if ("unitVisible".equals(key)) {
                CONTROL.setUnitVisible(((BooleanProperty) property).get());
            } else if("lowerCenterText".equals(key)) {
                CONTROL.setLowerCenterText(((StringProperty) property).get());
            } else if("lowerCenterTextVisible".equals(key)) {
                CONTROL.setLowerCenterTextVisible(((BooleanProperty) property).get());
            } else if ("lowerRightText".equals(key)) {
                CONTROL.setLowerRightText(((StringProperty) property).get());
            } else if ("lowerRightTextVisible".equals(key)) {
                CONTROL.setLowerRightTextVisible(((BooleanProperty) property).get());
            } else if ("upperLeftText".equals(key)) {
                CONTROL.setUpperLeftText(((StringProperty) property).get());
            } else if ("upperLeftTextVisible".equals(key)) {
                CONTROL.setUpperLeftTextVisible(((BooleanProperty) property).get());
            } else if ("upperRightText".equals(key)) {
                CONTROL.setUpperRightText(((StringProperty) property).get());
            } else if ("upperRightTextVisible".equals(key)) {
                CONTROL.setUpperRightTextVisible(((BooleanProperty) property).get());
            } else if ("trendVisible".equals(key)) {
                CONTROL.setTrendVisible(((BooleanProperty) property).get());
            } else if ("trend".equals(key)) {
                CONTROL.setTrend(((ObjectProperty<Lcd.Trend>) property).get());
            } else if ("batteryCharge".equals(key)) {
                CONTROL.setBatteryCharge(((DoubleProperty) property).get());
            } else if ("batteryVisible".equals(key)) {
                CONTROL.setBatteryVisible(((BooleanProperty) property).get());
            } else if ("signalStrength".equals(key)) {
                CONTROL.setSignalStrength(((DoubleProperty) property).get());
            } else if ("signalVisible".equals(key)) {
                CONTROL.setSignalVisible(((BooleanProperty) property).get());
            } else if ("alarmVisible".equals(key)) {
                CONTROL.setAlarmVisible(((BooleanProperty) property).get());
            } else if ("thresholdVisible".equals(key)) {
                CONTROL.setThresholdVisible(((BooleanProperty) property).get());
            } else if ("thresholdBehaviorInverted".equals(key)) {
                CONTROL.setThresholdBehaviorInverted(((BooleanProperty) property).get());
            } else if("numberSystem".equals(key)) {
                CONTROL.setNumberSystem(((ObjectProperty<Lcd.NumberSystem>) property).get());
            } else if ("numberSystemVisible".equals(key)) {
                CONTROL.setNumberSystemVisible(((BooleanProperty) property).get());
            } else if ("unitFont".equals(key)) {
                CONTROL.setUnitFont(((StringProperty) property).get());
            } else if ("titleFont".equals(key)) {
                CONTROL.setTitleFont(((StringProperty) property).get());
            } else if ("valueFont".equals(key)) {
                CONTROL.setValueFont(((ObjectProperty<Lcd.LcdFont>) property).get());
            } else if ("smallFont".equals(key)) {
                CONTROL.setSmallFont(((StringProperty) property).get());
            }
        });
        return CONTROL;
    }
}
