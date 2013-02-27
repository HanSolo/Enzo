package eu.hansolo.enzo.qlocktwo;

import com.sun.javafx.scene.control.skin.BehaviorSkinBase;
import javafx.animation.AnimationTimer;
import javafx.collections.ListChangeListener;
import javafx.geometry.VPos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RegionBuilder;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;

import java.util.Calendar;

//import javafx.scene.control.SkinBase;


public class QlockTwoSkin extends BehaviorSkinBase<QlockTwo, QlockTwoBehavior> {
    private static final double DEFAULT_WIDTH  = 200;
    private static final double DEFAULT_HEIGHT = 200;
    private static final double MINIMUM_WIDTH  = 50;
    private static final double MINIMUM_HEIGHT = 50;
    private static final double MAXIMUM_WIDTH  = 1024;
    private static final double MAXIMUM_HEIGHT = 1024;
    private QlockTwo control;
    private static double  aspectRatio;
    private double         size;
    private double         width;
    private double         height;
    private int            hour;
    private int            minute;
    private int            oldMinute;
    private int            timeZoneOffsetHour;
    private int            timeZoneOffsetMinute;
    private Pane           pane;
    private Region         background;
    private Region         p1;
    private Region         p2;
    private Region         p3;
    private Region         p4;
    private Text[][]       matrix;
    private Region         innerBounds;
    private Region         highlight;
    private Font           font;
    private double         startX;
    private double         startY;
    private double         stepX;
    private double         stepY;
    private double         charX;
    private double         charY;
    private long           lastTimerCall;
    private AnimationTimer timer;


    // ******************** Constructors **************************************
    public QlockTwoSkin(final QlockTwo CONTROL) {
        super(CONTROL, new QlockTwoBehavior(CONTROL));
        control              = CONTROL;
        aspectRatio          = 1.0;
        hour                 = 0;
        minute               = 0;
        oldMinute            = 0;
        timeZoneOffsetHour   = 0;
        timeZoneOffsetMinute = 0;
        pane        = new Pane();
        lastTimerCall    = System.nanoTime();
        timer = new AnimationTimer() {
            @Override public void handle(long now) {
                if (now > lastTimerCall + 1_000_000_000l) {
                    // Hours
                    hour = Calendar.getInstance().get(Calendar.HOUR) - timeZoneOffsetHour;// + ((java.util.Calendar.getInstance().get(java.util.Calendar.DST_OFFSET)) / 3600000);

                    // Minutes
                    minute = Calendar.getInstance().get(Calendar.MINUTE) + timeZoneOffsetMinute;

                    if (oldMinute < minute || (oldMinute == 59 && minute == 0)) {
                        updateClock();
                        oldMinute = minute;
                    }
                    lastTimerCall = now;
                }
            }
        };
        init();
        initGraphics();
        registerListeners();
        timer.start();
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
        startX = DEFAULT_WIDTH * 0.124;
        startY = DEFAULT_WIDTH * 0.132;
        stepX  = DEFAULT_WIDTH * 0.072;
        stepY  = DEFAULT_WIDTH * 0.0805;
        font = Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, DEFAULT_WIDTH * 0.048);

        charX = 0;
        charY = 0;

        background = RegionBuilder.create().styleClass("background").build();

        p1 = RegionBuilder.create().styleClass("off").build();

        p2 = RegionBuilder.create().styleClass("off").build();

        p3 = RegionBuilder.create().styleClass("off").build();

        p4 = RegionBuilder.create().styleClass("off").build();

        innerBounds = RegionBuilder.create().styleClass("innerbounds").build();
        innerBounds.setVisible(false);

        highlight = RegionBuilder.create().styleClass("highlight").build();

        matrix = new Text[11][10];
        for (int y = 0 ; y < 10 ; y++) {
            for (int x = 0 ; x < 11 ; x++) {
                matrix[x][y] = TextBuilder.create().textOrigin(VPos.CENTER).text(control.getQlock().getMatrix()[y][x]).font(font).styleClass("off").build();
            }
        }

        pane.getChildren().setAll(background,
            p4,
            p3,
            p2,
            p1,
                                  innerBounds);
        for (int y = 0 ; y < 10 ; y++) {
            for (int x = 0 ; x < 11 ; x++) {
                pane.getChildren().add(matrix[x][y]);
            }
        }
        pane.getChildren().add(highlight);

        getChildren().setAll(pane);
        resize();
    }

    private void registerListeners() {
        registerChangeListener(control.widthProperty(), "RESIZE");
        registerChangeListener(control.heightProperty(), "RESIZE");
        registerChangeListener(control.prefWidthProperty(), "PREF_SIZE");
        registerChangeListener(control.prefHeightProperty(), "PREF_SIZE");
        registerChangeListener(control.colorProperty(), "COLOR");
        registerChangeListener(control.languageProperty(), "LANGUAGE");

        control.getStyleClass().addListener(new ListChangeListener<String>() {
            @Override public void onChanged(Change<? extends String> change) {
                resize();
            }
        });
    }


    // ******************** Methods *******************************************
    @Override protected void handleControlPropertyChanged(final String PROPERTY) {
        super.handleControlPropertyChanged(PROPERTY);
        if ("RESIZE".equals(PROPERTY)) {
            resize();
        } else if ("PREF_SIZE".equals(PROPERTY)) {
            aspectRatio = control.getPrefHeight() / control.getPrefWidth();
        } else if ("COLOR".equals(PROPERTY)) {
            background.getStyleClass().setAll("background", control.getColor().STYLE_CLASS);
            p1.getStyleClass().setAll(control.getQlock().isP1() ? "on" : "off", control.getColor().STYLE_CLASS);
            p2.getStyleClass().setAll(control.getQlock().isP2() ? "on" : "off", control.getColor().STYLE_CLASS);
            p3.getStyleClass().setAll(control.getQlock().isP3() ? "on" : "off", control.getColor().STYLE_CLASS);
            p4.getStyleClass().setAll(control.getQlock().isP4() ? "on" : "off", control.getColor().STYLE_CLASS);
        } else if ("LANGUAGE".equals(PROPERTY)) {
            for (int y = 0 ; y < 10 ; y++) {
                for (int x = 0 ; x < 11 ; x++) {
                    matrix[x][y].setText(control.getQlock().getMatrix()[y][x]);
                }
            }
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


    // ******************** Utility methods ***********************************
    private String colorToCss(final Color COLOR) {
        StringBuilder cssColor = new StringBuilder();
        cssColor.append("rgba(")
                .append((int) (COLOR.getRed() * 255)).append(", ")
                .append((int) (COLOR.getGreen() * 255)).append(", ")
                .append((int) (COLOR.getBlue() * 255)).append(", ")
                .append(COLOR.getOpacity()).append(")");
        return cssColor.toString();
    }


    // ******************** Update ********************************************
    private void updateClock() {
        for (int y = 0 ; y < 10 ; y++) {
            for (int x = 0 ; x < 11 ; x++) {
                matrix[x][y].getStyleClass().setAll("off", control.getColor().STYLE_CLASS);
            }
        }

        for (QlockWord word : control.getQlock().getTime(minute, hour)) {
            for (int col = word.getStart() ; col <= word.getStop() ; col++) {
                matrix[col][word.getRow()].getStyleClass().setAll("on", control.getColor().STYLE_CLASS);
            }
        }
        p1.getStyleClass().setAll(control.getQlock().isP1() ? "on" : "off");
        p2.getStyleClass().setAll(control.getQlock().isP2() ? "on" : "off");
        p3.getStyleClass().setAll(control.getQlock().isP3() ? "on" : "off");
        p4.getStyleClass().setAll(control.getQlock().isP4() ? "on" : "off");
    }


    // ******************** Resizing ******************************************
    private void resize() {
        size   = control.getWidth() < control.getHeight() ? control.getWidth() : control.getHeight();
        width  = control.getWidth();
        height = control.getHeight();

        if (aspectRatio * width > height) {
            width  = 1 / (aspectRatio / height);
        } else if (1 / (aspectRatio / height) > width) {
            height = aspectRatio * width;
        }

        background.setPrefSize(1.0 * width, 1.0 * height);
        background.setTranslateX(0.0 * width);
        background.setTranslateY(0.0 * height);

        p4.setPrefSize(0.012 * width, 0.012 * height);
        p4.setTranslateX(0.044 * width);
        p4.setTranslateY(0.944 * height);

        p3.setPrefSize(0.012 * width, 0.012 * height);
        p3.setTranslateX(0.944 * width);
        p3.setTranslateY(0.944 * height);

        p2.setPrefSize(0.012 * width, 0.012 * height);
        p2.setTranslateX(0.944 * width);
        p2.setTranslateY(0.044 * height);

        p1.setPrefSize(0.012 * width, 0.012 * height);
        p1.setTranslateX(0.044 * width);
        p1.setTranslateY(0.044 * height);

        innerBounds.setPrefSize(0.752 * width, 0.752 * height);
        innerBounds.setTranslateX(0.124 * width);
        innerBounds.setTranslateY(0.12 * height);

        startX = size * 0.124;
        startY = size * 0.132;
        stepX  = size * 0.072;
        stepY  = size * 0.0805;
        font = Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, size * 0.048);
        for (int y = 0 ; y < 10 ; y++) {
            for (int x = 0 ; x < 11 ; x++) {
                matrix[x][y].setFont(font);
                matrix[x][y].setX(startX + x * stepX);
                matrix[x][y].setY(startY + y * stepY);
            }
        }

        highlight.setPrefSize(0.8572706909179687 * width, 0.7135147094726563 * height);
        highlight.setTranslateX(0.14224906921386718 * width);
        highlight.setTranslateY(0.28614569091796876 * height);

    }


}

