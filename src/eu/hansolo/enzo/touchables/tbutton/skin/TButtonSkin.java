package eu.hansolo.enzo.touchables.tbutton.skin;

import eu.hansolo.enzo.touchables.tbutton.TButton;
import javafx.geometry.VPos;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class TButtonSkin extends SkinBase<TButton> implements Skin<TButton> {
    private static final double MINIMUM_SIZE   = 25;    
    private static final double MAXIMUM_SIZE   = 1024;    
    private static final double PREFERRED_SIZE = 144;    
    private double              size;
    private double              width;
    private double              height;
    private Pane                pane;

    private Region              frame;
    private Region              off;
    private InnerShadow         offInnerShadow;
    private InnerShadow         offInnerShadow1;
    private DropShadow          offDropShadow;
    private Region              ledOff;
    private InnerShadow         ledOffInnerShadow;
    private InnerShadow         ledOffInnerShadow1;
    private Region              on;
    private InnerShadow         onInnerShadow;
    private InnerShadow         onInnerShadow1;
    private DropShadow          onDropShadow;
    private Region              ledOn;
    private InnerShadow         ledOnInnerShadow;
    private InnerShadow         ledOnInnerShadow1;
    private DropShadow          ledOnGlow;
    private Text                text;
    private Font                font;
    private InnerShadow         textInnerShadow;
    private InnerShadow         textInnerShadow1;


    // ******************** Constructors **************************************
    public TButtonSkin(final TButton CONTROL) {
        super(CONTROL);
        pane = new Pane();
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
                getSkinnable().setPrefSize(PREFERRED_SIZE, PREFERRED_SIZE);
            }
        }

        if (Double.compare(getSkinnable().getMinWidth(), 0.0) <= 0 || Double.compare(getSkinnable().getMinHeight(), 0.0) <= 0) {
            getSkinnable().setMinSize(MINIMUM_SIZE, MINIMUM_SIZE);
        }

        if (Double.compare(getSkinnable().getMaxWidth(), 0.0) <= 0 || Double.compare(getSkinnable().getMaxHeight(), 0.0) <= 0) {
            getSkinnable().setMaxSize(MAXIMUM_SIZE, MAXIMUM_SIZE);
        }
    }

    private void initGraphics() {
        frame = new Region();
        frame.getStyleClass().setAll("frame");

        off = new Region();
        off.getStyleClass().setAll("off");

        offInnerShadow = new InnerShadow();
        offInnerShadow.setOffsetX(0);
        offInnerShadow.setOffsetY(-5.0 / 144.0 * PREFERRED_SIZE);
        offInnerShadow.setRadius(2.0 / 144.0 * PREFERRED_SIZE);
        offInnerShadow.setColor(Color.web("0x00000080"));
        offInnerShadow.setBlurType(BlurType.TWO_PASS_BOX);
        offInnerShadow1 = new InnerShadow();
        offInnerShadow1.setOffsetX(0.0);
        offInnerShadow1.setOffsetY(0.0);
        offInnerShadow1.setRadius(3.0 / 144.0 * PREFERRED_SIZE);
        offInnerShadow1.setColor(Color.web("0x0000004d"));
        offInnerShadow1.setBlurType(BlurType.TWO_PASS_BOX);
        offInnerShadow1.setInput(offInnerShadow);
        offDropShadow = new DropShadow();
        offDropShadow.setOffsetX(0);
        offDropShadow.setOffsetY(10.0 / 144.0 * PREFERRED_SIZE);
        offDropShadow.setRadius(10.0 / 144.0 * PREFERRED_SIZE);
        offDropShadow.setColor(Color.web("0x000000bf"));
        offDropShadow.setBlurType(BlurType.TWO_PASS_BOX);
        offDropShadow.setInput(offInnerShadow1);
        off.setEffect(offDropShadow);

        ledOff = new Region();
        ledOff.getStyleClass().setAll("off-led");
        ledOff.setMouseTransparent(true);

        ledOffInnerShadow = new InnerShadow();
        ledOffInnerShadow.setOffsetX(0);
        ledOffInnerShadow.setOffsetY(2.0 / 144.0 * PREFERRED_SIZE);
        ledOffInnerShadow.setRadius(1.0 / 144.0 * PREFERRED_SIZE);
        ledOffInnerShadow.setColor(Color.web("0x0000004d"));
        ledOffInnerShadow.setBlurType(BlurType.TWO_PASS_BOX);
        ledOffInnerShadow1 = new InnerShadow();
        ledOffInnerShadow1.setOffsetX(0);
        ledOffInnerShadow1.setOffsetY(-2.0);
        ledOffInnerShadow1.setRadius(1.0 / 144.0 * PREFERRED_SIZE);
        ledOffInnerShadow1.setColor(Color.web("0xffffffa6"));
        ledOffInnerShadow1.setBlurType(BlurType.TWO_PASS_BOX);
        ledOffInnerShadow1.setInput(ledOffInnerShadow);
        ledOff.setEffect(ledOffInnerShadow1);

        on = new Region();
        on.getStyleClass().setAll("on");

        onInnerShadow = new InnerShadow();
        onInnerShadow.setOffsetX(0);
        onInnerShadow.setOffsetY(-2.0 / 144.0 * PREFERRED_SIZE);
        onInnerShadow.setRadius(2.0 / 144.0 * PREFERRED_SIZE);
        onInnerShadow.setColor(Color.web("0x00000080"));
        onInnerShadow.setBlurType(BlurType.TWO_PASS_BOX);
        onInnerShadow1 = new InnerShadow();
        onInnerShadow1.setOffsetX(0);
        onInnerShadow1.setOffsetY(5.0 / 144.0 * PREFERRED_SIZE);
        onInnerShadow1.setRadius(2.0 / 144.0 * PREFERRED_SIZE);
        onInnerShadow1.setColor(Color.web("0x90909080"));
        onInnerShadow1.setBlurType(BlurType.TWO_PASS_BOX);
        onInnerShadow1.setInput(onInnerShadow);
        onDropShadow = new DropShadow();
        onDropShadow.setOffsetX(0);
        onDropShadow.setOffsetY(1.0);
        onDropShadow.setRadius(1.0 / 144.0 * PREFERRED_SIZE);
        onDropShadow.setColor(Color.web("0x000000bf"));
        onDropShadow.setBlurType(BlurType.TWO_PASS_BOX);
        onDropShadow.setInput(onInnerShadow1);
        on.setEffect(onDropShadow);
        on.setVisible(getSkinnable().isSelected());

        ledOn = new Region();
        ledOn.getStyleClass().setAll("on-led");
        ledOn.setMouseTransparent(true);
        ledOn.setVisible(getSkinnable().isSelected());

        ledOnInnerShadow = new InnerShadow();
        ledOnInnerShadow.setOffsetX(1.4142135623730951);
        ledOnInnerShadow.setOffsetY(1.414213562373095);
        ledOnInnerShadow.setRadius(1.0 / 144.0 * PREFERRED_SIZE);
        ledOnInnerShadow.setColor(getSkinnable().getLedColor().darker().darker().darker());
        ledOnInnerShadow.setBlurType(BlurType.TWO_PASS_BOX);
        ledOnInnerShadow1 = new InnerShadow();
        ledOnInnerShadow1.setOffsetX(-2.457456132866976);
        ledOnInnerShadow1.setOffsetY(-1.7207293090531375);
        ledOnInnerShadow1.setRadius(2.0 / 144.0 * PREFERRED_SIZE);
        ledOnInnerShadow1.setColor(getSkinnable().getLedColor().darker());
        ledOnInnerShadow1.setBlurType(BlurType.TWO_PASS_BOX);
        ledOnInnerShadow1.setInput(ledOnInnerShadow);
        ledOnGlow = new DropShadow();
        ledOnGlow.setOffsetX(0.0);
        ledOnGlow.setOffsetY(0.0);
        ledOnGlow.setRadius(9.0 / 144.0 * PREFERRED_SIZE);
        ledOnGlow.setColor(getSkinnable().getLedColor());
        ledOnGlow.setBlurType(BlurType.TWO_PASS_BOX);
        ledOnGlow.setInput(ledOnInnerShadow1);
        ledOn.setEffect(ledOnGlow);

        font = Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 28.0 / 144.0 * PREFERRED_SIZE);
        text = new Text(getSkinnable().getText());
        text.setFont(font);
        text.setTextOrigin(VPos.TOP);
        text.getStyleClass().add("text");
        text.setMouseTransparent(true);
        textInnerShadow = new InnerShadow();
        textInnerShadow.setOffsetX(0);
        textInnerShadow.setOffsetY(-1.0 / 144.0 * PREFERRED_SIZE);
        textInnerShadow.setRadius(1.0 / 144.0 * PREFERRED_SIZE);
        textInnerShadow.setColor(Color.web("0x90909080"));
        textInnerShadow.setBlurType(BlurType.TWO_PASS_BOX);
        textInnerShadow1 = new InnerShadow();
        textInnerShadow1.setOffsetX(0);
        textInnerShadow1.setOffsetY(1.0 / 144.0 * PREFERRED_SIZE);
        textInnerShadow1.setRadius(1.0 / 144.0 * PREFERRED_SIZE);
        textInnerShadow1.setColor(Color.web("0x00000080"));
        textInnerShadow1.setBlurType(BlurType.TWO_PASS_BOX);
        textInnerShadow1.setInput(textInnerShadow);
        text.setEffect(textInnerShadow1);


        pane.getChildren().setAll(frame,
                                  off,
                                  ledOff,
                                  on,
                                  ledOn,
                                  text);

        getChildren().setAll(pane);
        resize();
    }

    private void registerListeners() {
        getSkinnable().widthProperty().addListener(observable -> { handleControlPropertyChanged("RESIZE"); });
        getSkinnable().heightProperty().addListener(observable -> { handleControlPropertyChanged("RESIZE"); });
        getSkinnable().selectedProperty().addListener(observable -> { handleControlPropertyChanged("SELECTED"); });
        getSkinnable().textProperty().addListener(observable -> { handleControlPropertyChanged("TEXT"); });
        getSkinnable().ledColorProperty().addListener(observable -> { handleControlPropertyChanged("LED_COLOR"); });
        if (getSkinnable().isTouchable()) {
            on.setOnTouchPressed(event -> { getSkinnable().setSelected(false); });
            off.setOnTouchPressed(event -> { getSkinnable().setSelected(true); });
        } else {
            on.setOnMousePressed(event -> { getSkinnable().setSelected(false); });
            off.setOnMousePressed(event -> { getSkinnable().setSelected(true); });
        }
    }


    // ******************** Methods *******************************************
    protected void handleControlPropertyChanged(final String PROPERTY) {
        if ("RESIZE".equals(PROPERTY)) {
            resize();
        } else if ("SELECTED".equals(PROPERTY)) {
            getSkinnable().fireSelectEvent(getSkinnable().isSelected() ? new TButton.SelectEvent(getSkinnable(), getSkinnable(), TButton.SelectEvent.SELECT) : new TButton.SelectEvent(getSkinnable(), getSkinnable(), TButton.SelectEvent.DESELECT));
            on.setVisible(getSkinnable().isSelected());
            ledOn.setVisible(getSkinnable().isSelected());

            off.setVisible(!getSkinnable().isSelected());
            ledOff.setVisible(!getSkinnable().isSelected());

            text.setTranslateY(getSkinnable().isSelected() ? (height - text.getLayoutBounds().getHeight()) * 0.49 + (3.0 / 144.0) * height : (height - text.getLayoutBounds().getHeight()) * 0.49);
        } else if ("TEXT".equals(PROPERTY)) {
            text.setText(getSkinnable().getText());
        } else if ("LED_COLOR".equals(PROPERTY)) {
            getSkinnable().setStyle("-led-color: " + colorToCss(getSkinnable().getLedColor()) + ";");
            ledOnGlow.setColor(getSkinnable().getLedColor());
            ledOnInnerShadow.setColor(getSkinnable().getLedColor().darker().darker().darker());
            ledOnInnerShadow1.setColor(getSkinnable().getLedColor().darker());
        }
    }

    @Override protected double computeMinWidth(final double HEIGHT, double TOP_INSET, double RIGHT_INSET, double BOTTOM_INSET, double LEFT_INSET) {
        return super.computeMinWidth(Math.max(MINIMUM_SIZE, HEIGHT - TOP_INSET - BOTTOM_INSET), TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET);
    }
    @Override protected double computeMinHeight(final double WIDTH, double TOP_INSET, double RIGHT_INSET, double BOTTOM_INSET, double LEFT_INSET) {
        return super.computeMinHeight(Math.max(MINIMUM_SIZE, WIDTH - LEFT_INSET - RIGHT_INSET), TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET);
    }

    @Override protected double computeMaxWidth(final double HEIGHT, double TOP_INSET, double RIGHT_INSET, double BOTTOM_INSET, double LEFT_INSET) {
        return super.computeMaxWidth(Math.min(MAXIMUM_SIZE, HEIGHT - TOP_INSET - BOTTOM_INSET), TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET);
    }
    @Override protected double computeMaxHeight(final double WIDTH, double TOP_INSET, double RIGHT_INSET, double BOTTOM_INSET, double LEFT_INSET) {
        return super.computeMaxHeight(Math.min(MAXIMUM_SIZE, WIDTH - LEFT_INSET - RIGHT_INSET), TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET);
    }

    @Override protected double computePrefWidth(final double HEIGHT, double TOP_INSET, double RIGHT_INSET, double BOTTOM_INSET, double LEFT_INSET) {
        double prefHeight = PREFERRED_SIZE;
        if (HEIGHT != -1) {
            prefHeight = Math.max(0, HEIGHT - TOP_INSET - BOTTOM_INSET);
        }
        return super.computePrefWidth(prefHeight, TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET);
    }
    @Override protected double computePrefHeight(final double WIDTH, double TOP_INSET, double RIGHT_INSET, double BOTTOM_INSET, double LEFT_INSET) {
        double prefWidth = PREFERRED_SIZE;
        if (WIDTH != -1) {
            prefWidth = Math.max(0, WIDTH - LEFT_INSET - RIGHT_INSET);
        }
        return super.computePrefHeight(prefWidth, TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET);
    }

    public static String colorToCss(final Color COLOR) {
        StringBuilder cssColor = new StringBuilder();
        cssColor.append("rgba(")
                .append((int) (COLOR.getRed() * 255)).append(", ")
                .append((int) (COLOR.getGreen() * 255)).append(", ")
                .append((int) (COLOR.getBlue() * 255)).append(", ")
                .append(COLOR.getOpacity()).append(");");
        return cssColor.toString();
    }


    // ******************** Resizing ******************************************
    private void resize() {
        size   = getSkinnable().getWidth() < getSkinnable().getHeight() ? getSkinnable().getWidth() : getSkinnable().getHeight();
        width  = size;
        height = size;

        if (width > 0 && height > 0) {
            frame.setPrefSize(width, height);

            off.setPrefSize(0.7916666666666666 * width, 0.7916666666666666 * height);
            off.setTranslateX(0.10416666666666667 * width);
            off.setTranslateY(0.10416666666666667 * height);
            offInnerShadow.setOffsetY(-5.0 / 144.0 * size);
            offInnerShadow.setRadius(2.0 / 144.0 * size);
            offInnerShadow1.setRadius(3.0 / 144.0 * size);
            offDropShadow.setOffsetY(10.0 / 144.0 * size);
            offDropShadow.setRadius(10.0 / 144.0 * size);

            ledOff.setPrefSize(0.08333333333333333 * width, 0.08333333333333333 * height);
            ledOff.setTranslateX(0.4583333333333333 * width);
            ledOff.setTranslateY(0.7291666666666666 * height);
            ledOffInnerShadow.setRadius(1.0 / 144.0 * size);
            ledOffInnerShadow1.setRadius(1.0 / 144.0 * size);

            on.setPrefSize(0.7916666666666666 * width, 0.7916666666666666 * height);
            on.setTranslateX(0.10416666666666667 * width);
            on.setTranslateY(0.10416666666666667 * height);
            onInnerShadow.setOffsetY(-2.0 / 144.0 * size);
            onInnerShadow.setRadius(2.0 / 144.0 * size);
            onInnerShadow1.setOffsetY(4.0 / 144.0 * size);
            onInnerShadow1.setRadius(2.0 / 144.0 * size);
            onDropShadow.setRadius(1.0 / 144.0 * size);

            ledOn.setPrefSize(0.08333333333333333 * width, 0.08333333333333333 * height);
            ledOn.setTranslateX(0.4583333333333333 * width);
            ledOn.setTranslateY(0.75 * height);
            ledOnInnerShadow.setRadius(1.0 / 144.0 * size);
            ledOnInnerShadow1.setRadius(2.0 / 144.0 * size);
            ledOnGlow.setRadius(9.0 / 144.0 * size);

            font = Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 28.0 / 144.0 * size);
            text.setFont(font);
            if (text.getLayoutBounds().getWidth() > 0.78 * width) {
                text.setText("...");
            }
            text.setTranslateX((width - text.getLayoutBounds().getWidth()) * 0.5);
            text.setTranslateY((height - text.getLayoutBounds().getHeight()) * 0.49);

            textInnerShadow.setOffsetY(-1.0 / 144.0 * size);
            textInnerShadow.setRadius(1.0 / 144.0 * size);
            textInnerShadow1.setOffsetY(1.0 / 144.0 * size);
            textInnerShadow1.setRadius(1.0 / 144.0 * size);
        }
    }
}
