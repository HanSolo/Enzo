package eu.hansolo.enzo.led1.skin;

import com.sun.javafx.scene.control.skin.BehaviorSkinBase;
import eu.hansolo.enzo.led1.Led;
import eu.hansolo.enzo.led1.behavior.LedBehavior;
import javafx.collections.ListChangeListener;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.DropShadowBuilder;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.InnerShadowBuilder;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

//import javafx.scene.control.SkinBase;


public class LedSkin extends BehaviorSkinBase<Led, LedBehavior> {
    private static final double DEFAULT_WIDTH  = 16;
    private static final double DEFAULT_HEIGHT = 16;
    private static final double MINIMUM_WIDTH  = 5;
    private static final double MINIMUM_HEIGHT = 5;
    private static final double MAXIMUM_WIDTH  = 1024;
    private static final double MAXIMUM_HEIGHT = 1024;
    private Led           control;
    private double        aspectRatio;
    private double        size;
    private double        width;
    private double        height;
    private Pane          pane;
    private Region        frame;
    private Region        ledOff;
    private Region        ledOn;
    private InnerShadow   innerShadow;
    private DropShadow    dropShadow;
    private Region        highlight;


    // ******************** Constructors **************************************
    public LedSkin(final Led CONTROL) {
        super(CONTROL, new LedBehavior(CONTROL));
        control     = CONTROL;
        aspectRatio = DEFAULT_HEIGHT / DEFAULT_WIDTH;
        pane        = new Pane();
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
        frame = new Region();
        frame.getStyleClass().setAll("frame");
        frame.setVisible(control.isFrameVisible());

        ledOff = new Region();
        ledOff.getStyleClass().setAll("led", "led-off", control.getColor().STYLE_CLASS);

        ledOn = new Region();
        ledOn.getStyleClass().setAll("led", "led-on", control.getColor().STYLE_CLASS);
        ledOn.setVisible(control.isOn());

        innerShadow = InnerShadowBuilder.create()
                                        .offsetX(0.0)
                                        .offsetY(0.0)
                                        .radius(0.1 * DEFAULT_WIDTH)
                                        .color(Color.BLACK)
                                        .blurType(BlurType.GAUSSIAN)
                                        .build();
        dropShadow = DropShadowBuilder.create()
                                      .offsetX(0.0)
                                      .offsetY(0.0)
                                      .radius(25.0 / 100.0 * DEFAULT_WIDTH)
                                      .color(control.getColor().COLOR)
                                      .blurType(BlurType.GAUSSIAN)
                                      .input(innerShadow)
                                      .build();
        ledOn.setEffect(dropShadow);

        highlight = new Region();
        highlight.getStyleClass().setAll("highlight");

        pane.getChildren().setAll(frame,
                                  ledOff,
                                  ledOn,
                                  highlight);

        getChildren().setAll(pane);
        resize();
    }

    private void registerListeners() {
        registerChangeListener(control.widthProperty(), "RESIZE");
        registerChangeListener(control.heightProperty(), "RESIZE");
        registerChangeListener(control.prefWidthProperty(), "PREF_SIZE");
        registerChangeListener(control.prefHeightProperty(), "PREF_SIZE");
        registerChangeListener(control.onProperty(), "GLOWING");
        registerChangeListener(control.frameVisibleProperty(), "FRAME_VISIBLE");
        registerChangeListener(control.colorProperty(), "COLOR");

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
        } else if ("GLOWING".equals(PROPERTY)) {
            ledOn.setVisible(control.isOn());
            ledOff.setVisible(!control.isOn());
        } else if ("FRAME_VISIBLE".equals(PROPERTY)) {
            frame.setVisible(control.isFrameVisible());
        } else if ("COLOR".equals(PROPERTY)) {
            ledOff.getStyleClass().setAll("led", "led-off", control.getColor().STYLE_CLASS);
            ledOn.getStyleClass().setAll("led", "led-on", control.getColor().STYLE_CLASS);
            dropShadow.setColor(control.getColor().COLOR);
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


    // ******************** Resizing ******************************************
    private void resize() {
        boolean wasBlinking = control.isBlinking();
        if (wasBlinking) {
            control.setBlinking(false);
        }

        size    = control.getWidth() < control.getHeight() ? control.getWidth() : control.getHeight();
        width   = control.getWidth();
        height  = control.getHeight();
        if (control.isKeepAspect()) {
            if (aspectRatio * width > height) {
                width  = 1 / (aspectRatio / height);
            } else if (1 / (aspectRatio / height) > width) {
                height = aspectRatio * width;
            }
        }

        frame.setPrefSize(0.75 * width, 0.75 * height);
        frame.setTranslateX(0.125 * width);
        frame.setTranslateY(0.125 * height);

        ledOff.setPrefSize(0.625 * width, 0.625 * height);
        ledOff.setTranslateX(0.1875 * width);
        ledOff.setTranslateY(0.1875 * height);

        ledOn.setPrefSize(0.625 * width, 0.625 * height);
        ledOn.setTranslateX(0.1875 * width);
        ledOn.setTranslateY(0.1875 * height);
        innerShadow.setRadius(0.1 / 80.0 * size);
        dropShadow.setRadius(0.25 * size);

        highlight.setPrefSize(0.5 * width, 0.5 * height);
        highlight.setTranslateX(0.25 * width);
        highlight.setTranslateY(0.25 * height);

        control.setBlinking(wasBlinking);
    }
}
