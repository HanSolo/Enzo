package eu.hansolo.enzo.led1;

import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Control;
import javafx.scene.paint.Color;


public class Led extends Control {
    public static enum LedColor {
        ALICEBLUE("led-aliceblue", Color.web("#f0f8ff")),
        ANTIQUEWHITE("led-antiquewhite", Color.web("#faebd7")),
        AQUA("led-aqua", Color.web("#00ffff")),
        AQUAMARINE("led-aquamarine", Color.web("#7fffd4")),
        AZURE("led-azure", Color.web("#f0ffff")),
        BEIGE("led-beige", Color.web("#f5f5dc")),
        BISQUE("led-bisque", Color.web("#ffe4c4")),
        BLACK("led-black", Color.web("#000000")),
        BLACNEDALMOND("led-blanchedalmond", Color.web("#ffebcd")),
        BLUE("led-blue", Color.web("#0000ff")),
        BLUE_VIOLETT("led-blueviolet", Color.web("#8a2be2")),
        BROWN("led-brown", Color.web("#a52a2a")),
        BURLYWOOD("led-burlywood", Color.web("#deb887")),
        CADETBLUE("led-cadetblue", Color.web("#5f9ea0")),
        CHARTREUSE("led-chartreuse", Color.web("#7fff00")),
        CHOCOLATE("led-chocolate", Color.web("#d2691e")),
        CORAL("led-coral", Color.web("#ff7f50")),
        CORNFLOWERBLUE("led-cornflowerblue", Color.web("#6495ed")),
        CORNSILK("led-cornsilk", Color.web("#fff8dc")),
        CRIMSON("led-crimson", Color.web("#dc143c")),
        CYAN("led-cyan", Color.web("#00ffff")),
        DARKBLUE("led-darkblue", Color.web("#00008b")),
        DARKCYAN("led-darkcyan", Color.web("#008b8b")),
        DARKGOLENROD("led-darkgoldenrod", Color.web("#b8860b")),
        DARKGRAY("led-darkgray", Color.web("#a9a9a9")),
        DARKGREEN("led-darkgreen", Color.web("#006400")),
        DARKGREY("led-darkgrey", Color.web("#a9a9a9")),
        DARKKHAKI("led-darkkhaki", Color.web("#bdb76b")),
        DARKMAGENTA("led-darkmagenta", Color.web("#8b008b")),
        DARKOLIVEGREEN("led-darkolivegreen", Color.web("#556b2f")),
        DARKORANGE("led-darkorange", Color.web("#ff8c00")),
        DARKORCHID("led-darkorchid", Color.web("#9932cc")),
        DARKRED("led-darkred", Color.web("#8b0000")),
        DARKSALMON("led-darksalmon", Color.web("#e9967a")),
        DARKSEAGREEN("led-darkseagreen", Color.web("#8fbc8f")),
        DARKSLATEBLUE("led-darkslateblue", Color.web("#483d8b")),
        DARKSLATEGRAY("led-darkslategray", Color.web("#2f4f4f")),
        DARKSLATEGREY("led-darkslategrey", Color.web("#2f4f4f")),
        DARKTURQUOISE("led-darkturquoise", Color.web("#00ced1")),
        DARKVIOLETT("led-darkviolet", Color.web("#9400d3")),
        DEEPPINK("led-deeppink", Color.web("#ff1493")),
        DEEPSKYBLUE("led-deepskyblue", Color.web("#00bfff")),
        DIMGRAY("led-dimgray", Color.web("#696969")),
        DIMGREY("led-dimgrey", Color.web("#696969")),
        DODGERBLUE("led-dodgerblue", Color.web("#1e90ff")),
        FIREBRICK("led-firebrick", Color.web("#b22222")),
        FLORALWHITE("led-floralwhite", Color.web("#fffaf0")),
        FORESTGREEN("led-forestgreen", Color.web("#228b22")),
        FUCHSIA("led-fuchsia", Color.web("#ff00ff")),
        GAINSBORO("led-gainsboro", Color.web("#dcdcdc")),
        GHOSTWHITE("led-ghostwhite", Color.web("#f8f8ff")),
        GOLD("led-gold", Color.web("#ffd700")),
        GOLDENROD("led-goldenrod", Color.web("#daa520")),
        GRAY("led-gray", Color.web("#808080")),
        GREEN("led-green", Color.web("#008000")),
        GREENYELLOW("led-greenyellow", Color.web("#adff2f")),
        GREY("led-grey", Color.web("#808080")),
        HONEYDEW("led-honeydew", Color.web("#f0fff0")),
        HOTPINK("led-hotpink", Color.web("#ff69b4")),
        INDIANRED("led-indianred", Color.web("#cd5c5c")),
        INDIGO("led-indigo", Color.web("#4b0082")),
        IVORY("led-ivory", Color.web("#fffff0")),
        KHAKI("led-khaki", Color.web("#f0e68c")),
        LAVENDER("led-lavender", Color.web("#e6e6fa")),
        LAVENDERBLUSH("led-lavenderblush", Color.web("#fff0f5")),
        LAWNGREEN("led-lawngreen", Color.web("#7cfc00")),
        LEMONCHIFFON("led-lemonchiffon", Color.web("#fffacd")),
        LIGHTBLUE("led-lightblue", Color.web("#add8e6")),
        LIGHTCORAL("led-lightcoral", Color.web("#f08080")),
        LIGHTCYAN("led-lightcyan", Color.web("#e0ffff")),
        LIGHTGOLDENRODYELLOW("led-lightgoldenrodyellow", Color.web("#fafad2")),
        LIGHTGRAY("led-lightgray", Color.web("#d3d3d3")),
        LIGHTGREEN("led-lightgreen", Color.web("#90ee90")),
        LIGHTGREY("led-lightgrey", Color.web("#d3d3d3")),
        LIGHTPINK("led-lightpink", Color.web("#ffb6c1")),
        LIGHTSALMON("led-lightsalmon", Color.web("#ffa07a")),
        LIGHTSEAGREEN("led-lightseagreen", Color.web("#20b2aa")),
        LIGHTSKYBLUE("led-lightskyblue", Color.web("#87cefa")),
        LIGHTSLATEGRAY("led-lightslategray", Color.web("#778899")),
        LIGHTSLATEGREY("led-lightslategrey", Color.web("#778899")),
        LIGHTSTEELBLUE("led-lightsteelblue", Color.web("#b0c4de")),
        LIGHTYELLOW("led-lightyellow", Color.web("#ffffe0")),
        LIME("led-lime", Color.web("#00ff00")),
        LIMEGREEN("led-limegreen", Color.web("#32cd32")),
        LINEN("led-linen", Color.web("#faf0e6")),
        MAGENTA("led-magenta", Color.web("#ff00ff")),
        MAROON("led-maroon", Color.web("#800000")),
        MEDIUMAQUAMARINE("led-mediumaquamarine", Color.web("#66cdaa")),
        MEDIUMBLUE("led-mediumblue", Color.web("#0000cd")),
        MEDIUMORCHID("led-mediumorchid", Color.web("#ba55d3")),
        MEDIUMPURPLE("led-mediumpurple", Color.web("#9370db")),
        MEDIUMSEAGREEN("led-mediumseagreen", Color.web("#3cb371")),
        MEDIUMSLATEBLUE("led-mediumslateblue", Color.web("#7b68ee")),
        MEDIUMSPRINGGREEN("led-mediumspringgreen", Color.web("#00fa9a")),
        MEDIUMTURQUOISE("led-mediumturquoise", Color.web("#48d1cc")),
        MEDIUMVIOLETRED("led-mediumvioletred", Color.web("#c71585")),
        MIDNIGHTBLUE("led-midnightblue", Color.web("#191970")),
        MINTCREAM("led-mintcream", Color.web("#f5fffa")),
        MISTYSROSE("led-mistyrose", Color.web("#ffe4e1")),
        MOCCASIN("led-moccasin", Color.web("#ffe4b5")),
        NAVAJOWHITE("led-navajowhite", Color.web("#ffdead")),
        NAVY("led-navy", Color.web("#000080")),
        OLDLACE("led-oldlace", Color.web("#fdf5e6")),
        OLIVE("led-olive", Color.web("#808000")),
        OLIVEDRAB("led-olivedrab", Color.web("#6b8e23")),
        ORANGE("led-orange", Color.web("#ffa500")),
        ORANGERED("led-orangered", Color.web("#ff4500")),
        ORCHID("led-orchid", Color.web("#da70d6")),
        PALEGOLDENROD("led-palegoldenrod", Color.web("#eee8aa")),
        PALEGREEN("led-palegreen", Color.web("#98fb98")),
        PALETURQUOISE("led-paleturquoise", Color.web("#afeeee")),
        PALEVIOLETRED("led-palevioletred", Color.web("#db7093")),
        PAPAYAWHIP("led-papayawhip", Color.web("#ffefd5")),
        PEACHPUFF("led-peachpuff", Color.web("#ffdab9")),
        PERU("led-peru", Color.web("#cd853f")),
        PINK("led-pink", Color.web("#ffc0cb")),
        PLUM("led-plum", Color.web("#dda0dd")),
        POWDERBLUE("led-powderblue", Color.web("#b0e0e6")),
        PURPLE("led-purple", Color.web("#800080")),
        RED("led-red", Color.web("#ff0000")),
        ROSYBROWN("led-rosybrown", Color.web("#bc8f8f")),
        ROYALBLUE("led-royalblue", Color.web("#4169e1")),
        SADDLEBROWN("led-saddlebrown", Color.web("#8b4513")),
        SALMON("led-salmon", Color.web("#fa8072")),
        SANDYBROWN("led-sandybrown", Color.web("#f4a460")),
        SEAGREEN("led-seagreen", Color.web("#2e8b57")),
        SEASHELL("led-seashell", Color.web("#fff5ee")),
        SIENNA("led-sienna", Color.web("#a0522d")),
        SILVER("led-silver", Color.web("#c0c0c0")),
        SKYBLUE("led-skyblue", Color.web("#87ceeb")),
        SLATEBLUE("led-slateblue", Color.web("#6a5acd")),
        SLATEGRAY("led-slategray", Color.web("#708090")),
        SLATEGREY("led-slategrey", Color.web("#708090")),
        SNOW("led-snow", Color.web("#fffafa")),
        SPRINGGREEN("led-springgreen", Color.web("#00ff7f")),
        STEELBLUE("led-steelblue", Color.web("#4682b4")),
        TAN("led-tan", Color.web("#d2b48c")),
        TEAL("led-teal", Color.web("#008080")),
        THISTLE("led-thistle", Color.web("#d8bfd8")),
        TOMATO("led-tomato", Color.web("#ff6347")),
        TURQUOISE("led-turquoise", Color.web("#40e0d0")),
        VIOLETT("led-violet", Color.web("#ee82ee")),
        WHEAT("led-wheat", Color.web("#f5deb3")),
        WHITE("led-white", Color.web("#ffffff")),
        WHITESMOKE("led-whitesmoke", Color.web("#f5f5f5")),
        YELLOW("led-yellow", Color.web("#ffff00")),
        YELLOWGREEN("led-yellowgreen", Color.web("#9acd32")),
        TRANSPARENT("led-transparent", Color.web("rgba(0,0,0,0)"));

        public final Color  COLOR;
        public final String STYLE_CLASS;

        private LedColor(final String STYLE_CLASS, final Color COLOR) {
            this.COLOR       = COLOR;
            this.STYLE_CLASS = STYLE_CLASS;
        }
    }

    private BooleanProperty          on;
    private BooleanProperty          blinking;
    private boolean                  _frameVisible;
    private BooleanProperty          frameVisible;
    private ObjectProperty<LedColor> color;
    private boolean                  _keepAspect;
    private BooleanProperty          keepAspect;
    private long                     _interval;
    private LongProperty             interval;
    private long                     lastTimerCall;
    private boolean                  toggle;
    private AnimationTimer           timer;


    // ******************** Constructors **************************************
    public Led() {
        getStyleClass().add("led");
        on            = new SimpleBooleanProperty(false);
        blinking      = new SimpleBooleanProperty(false);
        _frameVisible = true;
        color         = new SimpleObjectProperty<>(LedColor.RED);
        _keepAspect   = true;
        _interval     = 500_000_000l;
        lastTimerCall = System.nanoTime();
        toggle        = false;
        timer         = new AnimationTimer() {
            @Override public void handle(final long NOW) {
                if (NOW > lastTimerCall + getInterval()) {
                    toggle ^= true;
                    setOn(toggle);
                    lastTimerCall = NOW;
                }
            }
        };

    }


    // ******************** Methods *******************************************
    public final boolean isKeepAspect() {
        return keepAspect != null ? keepAspect.get() : _keepAspect;
    }
    public final void setKeepAspect(final boolean KEEP_ASPECT) {
        if (keepAspect != null) {
            keepAspect.set(KEEP_ASPECT);
        } else {
            _keepAspect = KEEP_ASPECT;
        }
    }
    public final BooleanProperty keepAspectProperty() {
        if (keepAspect == null) {
            keepAspect = new SimpleBooleanProperty(this, "keepAspect", _keepAspect);
        }
        return keepAspect;
    }

    @Override public boolean isResizable() {
        return true;
    }
    
    public final boolean isOn() {
        return on.get();
    }
    public final void setOn(final boolean ON) {
        on.set(ON);
    }
    public final BooleanProperty onProperty() {
        return on;
    }

    public final boolean isBlinking() {
        return blinking.get();
    }
    public final void setBlinking(final boolean BLINKING) {
        blinking.set(BLINKING);
        if (BLINKING) {
            timer.start();
        } else {
            timer.stop();
        }
    }
    public final BooleanProperty blinkingProperty() {
        return blinking;
    }

    public final long getInterval() {
        return interval != null ? interval.get() : _interval;
    }
    public final void setInterval(final long INTERVAL) {
        if (interval != null) {
            interval.set(clamp(50_000_000l, 5_000_000_000l, INTERVAL));
        } else {
            _interval = clamp(50_000_000l, 5_000_000_000l, INTERVAL);
        }
    }
    public final LongProperty intervalProperty() {
        if (interval == null) {
            interval = new SimpleLongProperty(this, "interval", _interval);
        }
        return interval;
    }

    public final boolean isFrameVisible() {
        return frameVisible != null ? frameVisible.get() : _frameVisible;
    }
    public final void setFrameVisible(final boolean FRAME_VISIBLE) {
        if (frameVisible != null) {
            frameVisible.set(FRAME_VISIBLE);
        } else {
            _frameVisible = FRAME_VISIBLE;
        }
    }
    public final BooleanProperty frameVisibleProperty() {
        if (frameVisible == null) {
            frameVisible = new SimpleBooleanProperty(this, "frameVisible", _frameVisible);
        }
        return frameVisible;
    }

    public final LedColor getColor() {
        return color.get();
    }
    public final void setColor(final LedColor LED_COLOR) {
        color.set(LED_COLOR);
    }
    public final ObjectProperty<LedColor> colorProperty() {
        return color;
    }


    // ******************** Utility Methods ***********************************
    public static long clamp(final long MIN, final long MAX, final long VALUE) {
        if (VALUE < MIN) return MIN;
        if (VALUE > MAX) return MAX;
        return VALUE;
    }


    // ******************** Style related *************************************
    @Override protected String getUserAgentStylesheet() {
        return getClass().getResource(getClass().getSimpleName().toLowerCase() + ".css").toExternalForm();
    }
}

