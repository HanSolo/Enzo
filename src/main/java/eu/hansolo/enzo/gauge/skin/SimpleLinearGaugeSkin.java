package eu.hansolo.enzo.gauge.skin;

import eu.hansolo.enzo.gauge.SimpleLinearGauge;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ListChangeListener;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.util.Locale;


/**
 * Created by
 * User: hansolo
 * Date: 16.07.13
 * Time: 13:04
 */
public class SimpleLinearGaugeSkin extends SkinBase<SimpleLinearGauge> implements Skin<SimpleLinearGauge> {
    private static final double PREFERRED_WIDTH  = 200;
    private static final double PREFERRED_HEIGHT = 200;
    private static final double MINIMUM_WIDTH    = 50;
    private static final double MINIMUM_HEIGHT   = 50;
    private static final double MAXIMUM_WIDTH    = 1024;
    private static final double MAXIMUM_HEIGHT   = 1024;
    private double              size;
    private double              width;
    private double              height;
    private double              centerX;
    private double              centerY;
    private Orientation         orientation;
    private Pane                pane;
    private DoubleProperty      value;
    private Line                barBackground;
    private Line                bar;
    private Canvas              titleAndUnit;
    private GraphicsContext     ctx;
    private Label               valueLabel;
    private double              valueStep;
    private InnerShadow         innerShadow;
    private Timeline            timeline;


    // ******************** Constructors **************************************
    public SimpleLinearGaugeSkin(SimpleLinearGauge gauge) {
        super(gauge);
        orientation = Orientation.VERTICAL;
        valueStep   = gauge.getRange() / (gauge.getMaxValue() - gauge.getMinValue());
        value       = new SimpleDoubleProperty(this, "value", getSkinnable().getValue() * valueStep);
        timeline    = new Timeline();
        init();
        initGraphics();
        registerListeners();
        resize();
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
    }

    private void initGraphics() {
        Font.loadFont(getClass().getResourceAsStream("/eu/hansolo/enzo/fonts/opensans-semibold.ttf"), 12); // "OpenSans"

        innerShadow = new InnerShadow();
        innerShadow.setRadius(0.01 * PREFERRED_WIDTH);
        innerShadow.setBlurType(BlurType.TWO_PASS_BOX);
        innerShadow.setColor(Color.rgb(0, 0, 0, 0.65));

        barBackground = new Line();
        barBackground.setStrokeType(StrokeType.CENTERED);
        barBackground.getStyleClass().addAll("bar-background");
        barBackground.setEffect(innerShadow);

        bar = new Line();
        bar.setStrokeType(StrokeType.CENTERED);
        bar.getStyleClass().addAll("bar");
        bar.setEffect(innerShadow);

        titleAndUnit = new Canvas(PREFERRED_WIDTH, PREFERRED_HEIGHT);
        ctx          = titleAndUnit.getGraphicsContext2D();

        valueLabel = new Label(String.format(Locale.US, "%." + getSkinnable().getDecimals() + "f", value.get()));
        valueLabel.setMouseTransparent(true);
        valueLabel.setAlignment(Pos.TOP_CENTER);
        valueLabel.getStyleClass().addAll("valueLabel");

        // Add all nodes
        pane = new Pane();
        pane.getChildren().setAll(titleAndUnit,
                                  barBackground,
                                  bar,
                                  valueLabel);

        getChildren().setAll(pane);
    }

    private void registerListeners() {
        getSkinnable().widthProperty().addListener(observable -> handleControlPropertyChanged("RESIZE"));
        getSkinnable().heightProperty().addListener(observable -> handleControlPropertyChanged("RESIZE"));
        getSkinnable().valueProperty().addListener(observable -> handleControlPropertyChanged("VALUE"));
        getSkinnable().minValueProperty().addListener(observable -> handleControlPropertyChanged("RESIZE"));
        getSkinnable().maxValueProperty().addListener(observable -> handleControlPropertyChanged("RESIZE"));

        getSkinnable().animatedProperty().addListener(observable -> handleControlPropertyChanged("ANIMATED"));
        getSkinnable().decimalsProperty().addListener(observable -> handleControlPropertyChanged("RESIZE"));

        getSkinnable().valueColorProperty().addListener(observable -> handleControlPropertyChanged("REDRAW_CANVAS"));
        getSkinnable().labelColorProperty().addListener(observable -> handleControlPropertyChanged("REDRAW_CANVAS"));

        getSkinnable().getStyleClass().addListener((ListChangeListener<String>) change -> handleControlPropertyChanged("STYLE"));

        value.addListener(observable -> handleControlPropertyChanged("CURRENT_VALUE"));
    }


    // ******************** Methods *******************************************
    protected void handleControlPropertyChanged(final String PROPERTY) {
        if ("RESIZE".equals(PROPERTY)) {
            resize();
        } else if ("VALUE".equals(PROPERTY)) {
            resize();
            setBar();
        } else if ("CURRENT_VALUE".equals(PROPERTY)) {
            double currentValue = value.get();

            valueLabel.setText(String.format(Locale.US, "%." + getSkinnable().getDecimals() + "f", currentValue));
            valueLabel.setTranslateX((width - valueLabel.getLayoutBounds().getWidth()) * 0.5);

            switch (orientation) {
                case VERTICAL:
                    bar.setStartX(barBackground.getStartX());
                    bar.setStartY(barBackground.getStartY());
                    bar.setEndX(barBackground.getEndX());
                    bar.setEndY(barBackground.getStartY() - (currentValue - getSkinnable().getMinValue()) * valueStep);
                    break;
                case HORIZONTAL:
                    bar.setStartX(barBackground.getStartX());
                    bar.setStartY(barBackground.getStartY());
                    bar.setEndX(getSkinnable().getBarWidth() + (currentValue) * valueStep);
                    bar.setEndY(barBackground.getEndY());
                    break;
            }

            for (int i = 0 ; i < getSkinnable().getSections().size() ; i++) {
                if (getSkinnable().getSections().get(i).contains(currentValue)) {
                    if (bar.getStyleClass().contains("section" + i)) continue;
                    bar.getStyleClass().add("section" + i);
                } else {
                    bar.getStyleClass().remove("section" + i);
                }
            }
        } else if ("STYLE".equals(PROPERTY)) {
            for (String style : getSkinnable().getStyleClass()) {
                if (bar.getStyleClass().contains(style)) continue;
                bar.getStyleClass().add(style);
            }
        } else if ("REDRAW_CANVAS".equals(PROPERTY)) {
            drawTitleAndUnit();
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
    private void drawTitleAndUnit() {
        ctx.clearRect(0, 0, width, height);
        ctx.setFill(getSkinnable().getLabelColor());
        ctx.setTextBaseline(VPos.CENTER);
        ctx.setTextAlign(TextAlignment.CENTER);

        // title
        if (Orientation.VERTICAL == orientation) {
            ctx.setFont(Font.font("Open Sans", FontWeight.NORMAL, 9 / PREFERRED_WIDTH * width));
            ctx.save();
            ctx.translate(width * 0.5 + getSkinnable().getBarWidth(), height * 0.5);
            ctx.rotate(90);
            ctx.fillText(getSkinnable().getTitle(), 0, 0);
            ctx.restore();
        } else {
            ctx.setFont(Font.font("Open Sans", FontWeight.NORMAL, 9 / PREFERRED_HEIGHT * height));
            ctx.fillText(getSkinnable().getTitle(), width * 0.5, height * 0.5 + 1.5 * getSkinnable().getBarWidth());
        }

        // unit
        if (Orientation.VERTICAL == orientation) {
            ctx.fillText(getSkinnable().getUnit(), width * 0.5, valueLabel.getLayoutBounds().getMaxY() + 10);
        } else {
            ctx.fillText(getSkinnable().getUnit(), width * 0.5, valueLabel.getLayoutBounds().getMaxY() + 10);
        }
    }

    private void setBar() {
        if (getSkinnable().isAnimated()) {
            timeline.stop();
            final KeyValue KEY_VALUE = new KeyValue(value, getSkinnable().getValue(), Interpolator.SPLINE(0.5, 0.4, 0.4, 1.0));
            final KeyFrame KEY_FRAME = new KeyFrame(Duration.millis(getSkinnable().getAnimationDuration()), KEY_VALUE);
            timeline.getKeyFrames().setAll(KEY_FRAME);
            timeline.play();
        } else {
            value.set(getSkinnable().getValue());
        }
    }

    private void resize() {
        size        = getSkinnable().getWidth() < getSkinnable().getHeight() ? getSkinnable().getWidth() : getSkinnable().getHeight();
        width       = getSkinnable().getWidth();
        height      = getSkinnable().getHeight();
        orientation = getSkinnable().getWidth() < getSkinnable().getHeight() ? Orientation.VERTICAL : Orientation.HORIZONTAL;
        centerX     = width * 0.5;
        centerY     = height * 0.5;

        if (width > 0 && height > 0) {

            innerShadow.setRadius(0.01 * size);

            valueLabel.setFont(Font.font("Open Sans", FontWeight.BOLD, (size - (getSkinnable().getBarWidth() * 2)) * 0.3));
            valueLabel.setText(String.format(Locale.US, "%." + getSkinnable().getDecimals() + "f", getSkinnable().getValue()));
            valueLabel.setPrefWidth(width * 0.8);
            valueLabel.setTranslateX((width - valueLabel.getLayoutBounds().getWidth()) * 0.5);
            valueLabel.setTranslateY(5);

            switch (orientation) {
                case VERTICAL:
                    valueStep = Math.abs((height - (2 * getSkinnable().getBarWidth()) - valueLabel.getLayoutBounds().getHeight() - getSkinnable().getBarWidth() * 0.5) / getSkinnable().getRange());
                    break;
                case HORIZONTAL:
                    valueStep = Math.abs((width - (2 * getSkinnable().getBarWidth())) / getSkinnable().getRange());
                    break;
            }

            switch(orientation) {
                case VERTICAL:
                    barBackground.setStartX(centerX);
                    barBackground.setStartY(height - getSkinnable().getBarWidth());
                    barBackground.setEndX(centerX);
                    barBackground.setEndY(getSkinnable().getBarWidth() + valueLabel.getLayoutBounds().getHeight() + getSkinnable().getBarWidth() * 0.5);
                    break;
                case HORIZONTAL:
                    barBackground.setStartX(getSkinnable().getBarWidth());
                    barBackground.setStartY(centerY);
                    barBackground.setEndX(width - getSkinnable().getBarWidth());
                    barBackground.setEndY(centerY);
                    break;
            }

            switch (orientation) {
                case VERTICAL:
                    bar.setStartX(barBackground.getStartX());
                    bar.setStartY(barBackground.getStartY());
                    bar.setEndX(barBackground.getEndX());
                    bar.setEndY(barBackground.getStartY() - ((getSkinnable().getValue() - getSkinnable().getMinValue()) * valueStep));
                    break;
                case HORIZONTAL:
                    bar.setStartX(barBackground.getStartX());
                    bar.setStartY(barBackground.getStartY());
                    bar.setEndX(getSkinnable().getBarWidth() + ((getSkinnable().getValue() - getSkinnable().getMinValue()) * valueStep));
                    bar.setEndY(barBackground.getEndY());
                    break;
            }

            titleAndUnit.setWidth(width);
            titleAndUnit.setHeight(height);
            drawTitleAndUnit();
            titleAndUnit.setVisible(Double.compare(size, 100) >= 0);
        }
    }
}
