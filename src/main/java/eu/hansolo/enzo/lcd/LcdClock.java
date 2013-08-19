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

import eu.hansolo.enzo.lcd.skin.LcdClockSkin;
import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.BooleanPropertyBase;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LcdClock extends Control {
    public static final String STYLE_CLASS_BEIGE              = "lcd-beige";
    public static final String STYLE_CLASS_BLUE               = "lcd-blue";
    public static final String STYLE_CLASS_ORANGE             = "lcd-orange";
    public static final String STYLE_CLASS_RED                = "lcd-red";
    public static final String STYLE_CLASS_YELLOW             = "lcd-yellow";
    public static final String STYLE_CLASS_WHITE              = "lcd-white";
    public static final String STYLE_CLASS_GRAY               = "lcd-gray";
    public static final String STYLE_CLASS_BLACK              = "lcd-black";
    public static final String STYLE_CLASS_GREEN              = "lcd-green";
    public static final String STYLE_CLASS_GREEN_DARKGREEN    = "lcd-green-darkgreen";
    public static final String STYLE_CLASS_BLUE2              = "lcd-blue2";
    public static final String STYLE_CLASS_BLUE_BLACK         = "lcd-blue-black";
    public static final String STYLE_CLASS_BLUE_DARKBLUE      = "lcd-blue-darkblue";
    public static final String STYLE_CLASS_BLUE_LIGHTBLUE     = "lcd-blue-lightblue";
    public static final String STYLE_CLASS_BLUE_GRAY          = "lcd-blue-gray";
    public static final String STYLE_CLASS_STANDARD           = "lcd-standard";
    public static final String STYLE_CLASS_LIGHTGREEN         = "lcd-lightgreen";
    public static final String STYLE_CLASS_STANDARD_GREEN     = "lcd-standard-green";
    public static final String STYLE_CLASS_BLUE_BLUE          = "lcd-blue-blue";
    public static final String STYLE_CLASS_RED_DARKRED        = "lcd-red-darkred";
    public static final String STYLE_CLASS_DARKBLUE           = "lcd-darkblue";
    public static final String STYLE_CLASS_PURPLE             = "lcd-purple";
    public static final String STYLE_CLASS_BLACK_RED          = "lcd-black-red";
    public static final String STYLE_CLASS_DARKGREEN          = "lcd-darkgreen";
    public static final String STYLE_CLASS_AMBER              = "lcd-amber";
    public static final String STYLE_CLASS_LIGHTBLUE          = "lcd-lightblue";
    public static final String STYLE_CLASS_GREEN_BLACK        = "lcd-green-black";
    public static final String STYLE_CLASS_YELLOW_BLACK       = "lcd-yellow-black";
    public static final String STYLE_CLASS_BLACK_YELLOW       = "lcd-black-yellow";
    public static final String STYLE_CLASS_LIGHTGREEN_BLACK   = "lcd-lightgreen-black";
    public static final String STYLE_CLASS_DARKPURPLE         = "lcd-darkpurple";
    public static final String STYLE_CLASS_DARKAMBER          = "lcd-darkamber";
    public static final String STYLE_CLASS_BLUE_LIGHTBLUE2    = "lcd-blue-lightblue2";
    public static final String STYLE_CLASS_GRAY_PURPLE        = "lcd-gray-purple";
    public static final String STYLE_CLASS_SECTIONS           = "lcd-sections";
    public static final String STYLE_CLASS_YOCTOPUCE          = "lcd-yoctopuce";

    public static final String STYLE_CLASS_FLAT_TURQOISE      = "lcd-flat-turqoise";
    public static final String STYLE_CLASS_FLAT_GREEN_SEA     = "lcd-flat-green-sea";
    public static final String STYLE_CLASS_FLAT_EMERLAND      = "lcd-flat-emerland";
    public static final String STYLE_CLASS_FLAT_NEPHRITIS     = "lcd-flat-nephritis";
    public static final String STYLE_CLASS_FLAT_PETER_RIVER   = "lcd-flat-peter-river";
    public static final String STYLE_CLASS_FLAT_BELIZE_HOLE   = "lcd-flat-belize-hole";
    public static final String STYLE_CLASS_FLAT_AMETHYST      = "lcd-flat-amethyst";
    public static final String STYLE_CLASS_FLAT_WISTERIA      = "lcd-flat-wisteria";
    public static final String STYLE_CLASS_FLAT_SUNFLOWER     = "lcd-flat-sunflower";
    public static final String STYLE_CLASS_FLAT_ORANGE        = "lcd-flat-orange";
    public static final String STYLE_CLASS_FLAT_CARROT        = "lcd-flat-carrot";
    public static final String STYLE_CLASS_FLAT_PUMPKIN       = "lcd-flat-pumpkin";
    public static final String STYLE_CLASS_FLAT_ALIZARIN      = "lcd-flat-alizarin";
    public static final String STYLE_CLASS_FLAT_POMEGRANATE   = "lcd-flat-pomegranate";
    public static final String STYLE_CLASS_FLAT_CLOUDS        = "lcd-flat-clouds";
    public static final String STYLE_CLASS_FLAT_SILVER        = "lcd-flat-silver";
    public static final String STYLE_CLASS_FLAT_CONCRETE      = "lcd-flat-concrete";
    public static final String STYLE_CLASS_FLAT_ASBESTOS      = "lcd-flat-asbestos";
    public static final String STYLE_CLASS_FLAT_WET_ASPHALT   = "lcd-flat-wet-asphalt";
    public static final String STYLE_CLASS_FLAT_MIDNIGHT_BLUE = "lcd-flat-midnight-blue";
    public static enum LcdFont {
        STANDARD,
        LCD,
        DIGITAL,
        DIGITAL_BOLD,
        ELEKTRA
    }

    // CSS pseudo classes
    private static final PseudoClass      NO_FRAME_PSEUDO_CLASS = PseudoClass.getPseudoClass("no-frame");
    private BooleanProperty               noFrame;

    private boolean                       initialized;
    private boolean                       firstTime;
    private boolean                       keepAspect;
    private String                        _title = "";
    private StringProperty                title;
    private String                        _titleFont = "Open Sans";
    private StringProperty                titleFont;
    private LcdFont                       _timeFont = LcdFont.LCD;
    private ObjectProperty<LcdFont>       timeFont;
    private String                        _smallFont = "Open Sans";
    private StringProperty                smallFont;
    private boolean                       _backgroundVisible = true;
    private BooleanProperty               backgroundVisible;
    private boolean                       _crystalOverlayVisible = false;
    private BooleanProperty               crystalOverlayVisible;
    private boolean                       _mainInnerShadowVisible = false;
    private BooleanProperty               mainInnerShadowVisible;
    private boolean                       _foregroundShadowVisible = false;
    private BooleanProperty               foregroundShadowVisible;
    private ObservableList<Alarm>         alarms;
    private List<Alarm>                   alarmsToRemove;
    private ObjectProperty<Clock>         clock;
    private ObjectProperty<LocalDateTime> time;
    private long                          lastTimerCall;
    private AnimationTimer                timer;


    // ******************** Constructors **************************************
    public LcdClock() {
        getStyleClass().add("lcd-clock");
        clock          = new SimpleObjectProperty<>(this, "clock", Clock.systemDefaultZone());
        time           = new SimpleObjectProperty<>(this, "time", LocalDateTime.now(clock.get()));
        alarms         = FXCollections.observableArrayList();
        alarmsToRemove = new ArrayList<>();
        initialized    = false;
        firstTime      = true;
        keepAspect     = true;
        lastTimerCall  = System.nanoTime();
        timer          = new AnimationTimer() {
            @Override public void handle(final long NOW) {
                if (NOW > lastTimerCall + 1_000_000_000l) {
                    time.set(LocalDateTime.now(clock.get()));
                    lastTimerCall = NOW;
                }
            }
        };
        init();
        initialized = true;
        timer.start();
    }


    // ******************** Initialization ************************************
    private void init() {
        timeProperty().addListener(observable -> {
            alarmsToRemove.clear();
            for (Alarm alarm : alarms) {
                switch(alarm.getRepetition()) {
                    case ONCE:
                        if (getTime().isAfter(alarm.getTime())) {
                            if (alarm.isActive()) {
                                fireEvent(new Alarm.AlarmEvent(alarm, this, this, Alarm.AlarmEvent.ALARM));
                                alarm.executeCommand();
                            }
                            alarmsToRemove.add(alarm);
                        }
                        break;
                    case HOURLY:
                        if (alarm.getTime().getHour() == getTime().getMinute() &&
                            alarm.getTime().getMinute() == getTime().getSecond()) {
                            if (alarm.isActive()) {
                                fireEvent(new Alarm.AlarmEvent(alarm, this, this, Alarm.AlarmEvent.ALARM));
                                alarm.executeCommand();
                            }
                        }
                        break;
                    case DAILY:
                        if (alarm.getTime().getHour() == getTime().getHour() &&
                            alarm.getTime().getMinute() == getTime().getMinute() &&
                            alarm.getTime().getSecond() == getTime().getSecond()) {
                            if (alarm.isActive()) {
                                fireEvent(new Alarm.AlarmEvent(alarm, this, this, Alarm.AlarmEvent.ALARM));
                                alarm.executeCommand();
                            }
                        }
                        break;
                    case WEEKLY:
                        if (alarm.getTime().getDayOfWeek() == getTime().getDayOfWeek() &&
                            alarm.getTime().getHour() == getTime().getHour() &&
                            alarm.getTime().getMinute() == getTime().getMinute() &&
                            alarm.getTime().getSecond() == getTime().getSecond()) {
                            if (alarm.isActive()) {
                                fireEvent(new Alarm.AlarmEvent(alarm, this, this, Alarm.AlarmEvent.ALARM));
                                alarm.executeCommand();
                            }
                        }
                        break;
                }
            }
            for (Alarm alarm : alarmsToRemove) {
                removeAlarm(alarm);
            }
        });
    }


    // ******************** Methods *******************************************
    public final boolean isKeepAspect() {
        return keepAspect;
    }
    public final void setKeepAspect(final boolean KEEP_ASPECT) {
        keepAspect = KEEP_ASPECT;
    }

    @Override public boolean isResizable() {
        return true;
    }

    public final Clock getClock() {
        return clock.get();
    }
    public final void setClock(final Clock CLOCK) {
        clock.set(CLOCK);
    }
    public final ObjectProperty<Clock> clockProperty() {
        return clock;
    }

    public final LocalDateTime getTime() {
        return time.get();
    }
    public final ReadOnlyObjectProperty timeProperty() {
        return time;
    }

    public final String getTitle() {
        return null == title ? _title : title.get();
    }
    public final void setTitle(final String TITLE) {
        if (null == title) {
            _title = TITLE;
        } else {
            title.set(TITLE);
        }
    }
    public final StringProperty titleProperty() {
        if (null == title) {
            title = new SimpleStringProperty(this, "title", _title);
        }
        return title;
    }

    public final boolean isNoFrame() {
        return null == noFrame ? true : noFrame.get();
    }
    public final void setNoFrame(final boolean NO_FRAME) {
        noFrameProperty().set(NO_FRAME);
    }
    public final BooleanProperty noFrameProperty() {
        if (null == noFrame) {
            noFrame = new BooleanPropertyBase(false) {
                @Override protected void invalidated() {
                    pseudoClassStateChanged(NO_FRAME_PSEUDO_CLASS, get());
                }
                @Override public Object getBean() { return this; }
                @Override public String getName() { return "noFrame"; }
            };
        }
        return noFrame;
    }

    public final boolean isBackgroundVisible() {
        return null == backgroundVisible ? _backgroundVisible : backgroundVisible.get();
    }
    public final void setBackgroundVisible(final boolean BACKGROUND_VISIBLE) {
        if (null == backgroundVisible) {
            _backgroundVisible = BACKGROUND_VISIBLE;
        } else {
            backgroundVisible.set(BACKGROUND_VISIBLE);
        }
    }
    public final BooleanProperty backgroundVisibleProperty() {
        if (null == backgroundVisible) {
            backgroundVisible = new SimpleBooleanProperty(this, "backgroundVisible", _backgroundVisible);
        }
        return backgroundVisible;
    }

    public final boolean isCrystalOverlayVisible() {
        return null == crystalOverlayVisible ? _crystalOverlayVisible : crystalOverlayVisible.get();
    }
    public final void setCrystalOverlayVisible(final boolean CRYSTAL_OVERLAY_VISIBLE) {
        if (null == crystalOverlayVisible) {
            _crystalOverlayVisible = CRYSTAL_OVERLAY_VISIBLE;
        } else {
            crystalOverlayVisible.set(CRYSTAL_OVERLAY_VISIBLE);
        }
    }
    public final BooleanProperty crystalOverlayVisibleProperty() {
        if (null == crystalOverlayVisible) {
            crystalOverlayVisible = new SimpleBooleanProperty(this, "crystalOverlayVisible", _crystalOverlayVisible);
        }
        return crystalOverlayVisible;
    }

    public final boolean isMainInnerShadowVisible() {
        return null == mainInnerShadowVisible ? _mainInnerShadowVisible : mainInnerShadowVisible.get();
    }
    public final void setMainInnerShadowVisible(final boolean MAIN_INNER_SHADOW_VISIBLE) {
        if (null == mainInnerShadowVisible) {
            _mainInnerShadowVisible = MAIN_INNER_SHADOW_VISIBLE;
        } else {
            mainInnerShadowVisible.set(MAIN_INNER_SHADOW_VISIBLE);
        }
    }
    public final BooleanProperty mainInnerShadowVisibleProperty() {
        if (null == mainInnerShadowVisible) {
            mainInnerShadowVisible = new SimpleBooleanProperty(this, "mainInnerShadowVisible", _mainInnerShadowVisible);
        }
        return mainInnerShadowVisible;
    }

    public final boolean isForegroundShadowVisible() {
        return null == foregroundShadowVisible ? _foregroundShadowVisible : foregroundShadowVisible.get();
    }
    public final void setForegroundShadowVisible(final boolean FOREGROUND_SHADOW_VISIBLE) {
        if (null == foregroundShadowVisible) {
            _foregroundShadowVisible = FOREGROUND_SHADOW_VISIBLE;
        } else {
            foregroundShadowVisible.set(FOREGROUND_SHADOW_VISIBLE);
        }
    }
    public final BooleanProperty foregroundShadowVisibleProperty() {
        if (null == foregroundShadowVisible) {
            foregroundShadowVisible = new SimpleBooleanProperty(this, "foregroundShadowVisible", _foregroundShadowVisible);
        }
        return foregroundShadowVisible;
    }

    public final String getTitleFont() {
        return null == titleFont ? _titleFont : titleFont.get();
    }
    public final void setTitleFont(final String TITLE_FONT) {
        if (null == titleFont) {
            _titleFont = TITLE_FONT;
        } else {
            titleFont.set(TITLE_FONT);
        }
    }
    public final StringProperty titleFontProperty() {
        if (null == titleFont) {
            titleFont = new SimpleStringProperty(this, "titleFont", _titleFont);
        }
        return titleFont;
    }

    public final LcdFont getTimeFont() {
        return null == timeFont ? _timeFont : timeFont.get();
    }
    public final void setTimeFont(final LcdFont TIME_FONT) {
        if (null == timeFont) {
            _timeFont = TIME_FONT;
        } else {
            timeFont.set(TIME_FONT);
        }
    }
    public final ObjectProperty<LcdFont> timeFontProperty() {
        if (null == timeFont) {
            timeFont = new SimpleObjectProperty<>(this, "timeFont", _timeFont);
        }
        return timeFont;
    }

    public final String getSmallFont() {
        return null == smallFont ? _smallFont : smallFont.get();
    }
    public final void setSmallFont(final String SMALL_FONT) {
        if (null == smallFont) {
            _smallFont = SMALL_FONT;
        } else {
            smallFont.set(SMALL_FONT);
        }
    }
    public final StringProperty smallFontProperty() {
        if (null == smallFont) {
            smallFont = new SimpleStringProperty(this, "smallFont", _smallFont);
        }
        return smallFont;
    }

    public final ObservableList<Alarm> getAlarms() {
        return alarms;
    }
    public final void setAlarms(final List<Alarm> ALARMS) {
        alarms.setAll(ALARMS);
    }
    public final void setAlarms(final Alarm... ALARMS) {
        setAlarms(Arrays.asList(ALARMS));
    }
    public final void addAlarm(final Alarm ALARM) {
        if (!alarms.contains(ALARM)) alarms.add(ALARM);
    }
    public final void removeAlarm(final Alarm ALARM) {
        if (alarms.contains(ALARM)) alarms.remove(ALARM);
    }


    // ******************** Utility Methods ***********************************
    public static double clamp(final double MIN, final double MAX, final double VALUE) {
        if (VALUE < MIN) return MIN;
        if (VALUE > MAX) return MAX;
        return VALUE;
    }


    // ******************** Style related *************************************
    @Override protected Skin createDefaultSkin() {
        return new LcdClockSkin(this);
    }

    @Override protected String getUserAgentStylesheet() {
        return getClass().getResource(getClass().getSimpleName().toLowerCase() + ".css").toExternalForm();
    }
}
