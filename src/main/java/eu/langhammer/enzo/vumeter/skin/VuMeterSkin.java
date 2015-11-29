package eu.langhammer.enzo.vumeter.skin;

import eu.langhammer.enzo.common.Section;
import eu.langhammer.enzo.vumeter.VuMeter;
import javafx.animation.AnimationTimer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.WeakEventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;


/**
 * User: hansolo
 * Date: 04.11.13
 * Time: 10:30
 */
public class VuMeterSkin extends SkinBase<VuMeter> implements Skin<VuMeter> {
    private static final double    PREFERRED_WIDTH  = 24;
    private static final double    PREFERRED_HEIGHT = 24;
    private static final double    MINIMUM_WIDTH    = 16;
    private static final double    MINIMUM_HEIGHT   = 16;
    private static final double    MAXIMUM_WIDTH    = 1024;
    private static final double    MAXIMUM_HEIGHT   = 1024;
    public static final long       PEAK_TIMEOUT     = 1_500_000_000l;
    private static boolean         active;
    private double                 size;    
    private ObservableList<Region> leds;
    private HBox                   hBox;
    private VBox                   vBox;
    private DoubleProperty         stepSize;
    private int                    peakLedIndex;
    private long                   lastTimerCall;    
    private AnimationTimer         timer;


    // ******************** Constructors **************************************
    public VuMeterSkin(final VuMeter CONTROL) {
        super(CONTROL);
        active        = false;
        lastTimerCall = 0l;
        stepSize      = new SimpleDoubleProperty((getSkinnable().getMaxValue() - getSkinnable().getMinValue()) / getSkinnable().getNoOfLeds());
        timer         = new AnimationTimer() {
            @Override public void handle(final long NOW) {
                if (NOW > lastTimerCall + PEAK_TIMEOUT) {
                    leds.get(peakLedIndex).getStyleClass().remove("led-peak");
                    peakLedIndex = Orientation.HORIZONTAL == getSkinnable().getOrientation() ? 0 : leds.size() - 1;
                    timer.stop();
                }
            }
        };
        init();
        initGraphics();
        registerListeners();
    }


    // ******************** Initialization ************************************
    private void init() {
        if (Double.compare(getSkinnable().getPrefWidth(), 0.0) <= 0 || Double.compare(getSkinnable().getPrefHeight(), 0.0) <= 0 ||
            Double.compare(getSkinnable().getWidth(), 0.0) <= 0 || Double.compare(getSkinnable().getHeight(), 0.0) <= 0) {
            if (Orientation.HORIZONTAL == getSkinnable().getOrientation()) {                
                getSkinnable().setPrefSize(getSkinnable().getNoOfLeds() * PREFERRED_WIDTH + (getSkinnable().getNoOfLeds() - 1) * getSkinnable().getLedSpacing(), PREFERRED_HEIGHT);                                                    
            } else {                
                getSkinnable().setPrefSize(PREFERRED_WIDTH, getSkinnable().getNoOfLeds() * PREFERRED_HEIGHT + (getSkinnable().getNoOfLeds() - 1) * getSkinnable().getLedSpacing());                
            }            
        }

        if (Double.compare(getSkinnable().getMinWidth(), 0.0) <= 0 || Double.compare(getSkinnable().getMinHeight(), 0.0) <= 0) {
            getSkinnable().setMinSize(MINIMUM_WIDTH, MINIMUM_HEIGHT);
        }

        if (Double.compare(getSkinnable().getMaxWidth(), 0.0) <= 0 || Double.compare(getSkinnable().getMaxHeight(), 0.0) <= 0) {
            getSkinnable().setMaxSize(MAXIMUM_WIDTH, MAXIMUM_WIDTH);
        }
    }

    private void initGraphics() {                
        hBox = new HBox();        
        hBox.getStyleClass().setAll("vu-meter");
        hBox.setSpacing(getSkinnable().getLedSpacing());        
        
        vBox = new VBox();
        vBox.getStyleClass().setAll("vu-meter");
        vBox.setSpacing(getSkinnable().getLedSpacing());        

        leds = FXCollections.observableArrayList();
        for (int i = 0 ; i < getSkinnable().getNoOfLeds() ; i++) {
            Region led = new Region();                    
            led.setOnMouseClicked(new WeakEventHandler<>(event -> active = !active));
            led.setOnMouseEntered(new WeakEventHandler<>(event -> handleMouseEvent(event)));                                    
            if (getSkinnable().getSections().isEmpty()) {
                led.getStyleClass().setAll("led");
            } else {                
                for (Section section : getSkinnable().getSections()) {
                    if (section.contains(i * stepSize.doubleValue())) {
                        led.getStyleClass().setAll("led", section.getStyleClass());
                    }
                }                
            }            
            leds.add(led);
        }
        
        if (Orientation.HORIZONTAL == getSkinnable().getOrientation()) {
            vBox.setManaged(false);            
            vBox.setVisible(false);
            hBox.getChildren().setAll(leds);
            peakLedIndex = 0;
        } else {
            hBox.setManaged(false);
            hBox.setVisible(false); 
            FXCollections.reverse(leds);
            vBox.getChildren().setAll(leds);
            peakLedIndex = leds.size() - 1;
        }
                                        
        // Add all nodes
        getChildren().setAll(hBox, vBox);        
    }

    private void registerListeners() {
        getSkinnable().widthProperty().addListener(observable -> handleControlPropertyChanged("RESIZE") );
        getSkinnable().heightProperty().addListener(observable -> handleControlPropertyChanged("RESIZE") );                
        getSkinnable().valueProperty().addListener(observable -> handleControlPropertyChanged("VALUE"));        
        getSkinnable().orientationProperty().addListener((ov, oldOrientation, newOrientation) -> {
            if (Orientation.HORIZONTAL == oldOrientation && Orientation.VERTICAL == newOrientation) {
                FXCollections.reverse(leds);    
            } else if (Orientation.VERTICAL == oldOrientation && Orientation.HORIZONTAL == newOrientation) {
                FXCollections.reverse(leds);
            }
            handleControlPropertyChanged("ORIENTATION");
        });
        getSkinnable().noOfLedsProperty().addListener(observable -> handleControlPropertyChanged("NO_OF_LEDS"));
        getSkinnable().getSections().addListener((ListChangeListener<Section>) change -> handleControlPropertyChanged("SECTIONS"));        
    }


    // ******************** Methods *******************************************
    protected void handleControlPropertyChanged(final String PROPERTY) {
        if ("RESIZE".equals(PROPERTY)) {
            resize();
        } else if ("STYLE".equals(PROPERTY)) {
            resize();            
        } else if ("VALUE".equals(PROPERTY)) {
            int currentLedPeakIndex;
            if (Orientation.HORIZONTAL == getSkinnable().getOrientation()) {                
                // HORIZONTAL
                currentLedPeakIndex = 0;
                for (int i = 0 ; i < leds.size() ; i++) {
                    leds.get(i).getStyleClass().remove("led-on");
                    if (Double.compare(i * stepSize.doubleValue(), getSkinnable().getValue()) <= 0) {
                        leds.get(i).getStyleClass().add("led-on");                    
                        currentLedPeakIndex = i;
                    }
                }
                if (getSkinnable().isPeakValueVisible()) {
                    if (currentLedPeakIndex > peakLedIndex) {
                        timer.stop();
                        leds.get(peakLedIndex).getStyleClass().remove("led-peak");
                        leds.get(currentLedPeakIndex).getStyleClass().add("led-peak");
                        peakLedIndex  = currentLedPeakIndex;
                        lastTimerCall = System.nanoTime();
                        timer.start();
                    }
                }
            } else {    
                // VERTICAL
                currentLedPeakIndex = leds.size() - 1;
                for (int i = 0 ; i < leds.size() ; i++) {
                    if (i != peakLedIndex) leds.get(i).getStyleClass().remove("led-on");
                    if (Double.compare((leds.size() - i - 1) * stepSize.doubleValue(), getSkinnable().getValue()) <= 0) {
                        leds.get(i).getStyleClass().add("led-on");                                                
                    } else {
                        currentLedPeakIndex = i;
                    }
                }                
                if (getSkinnable().isPeakValueVisible()) {
                    if (currentLedPeakIndex < peakLedIndex) {
                        timer.stop();
                        leds.get(peakLedIndex).getStyleClass().remove("led-peak");
                        leds.get(currentLedPeakIndex).getStyleClass().add("led-peak");
                        peakLedIndex  = currentLedPeakIndex;
                        lastTimerCall = System.nanoTime();
                        timer.start();
                    }
                }
            }                        
        } else if ("ORIENTATION".equals(PROPERTY)) {
            hBox.getChildren().clear();
            vBox.getChildren().clear();            
            if (Orientation.HORIZONTAL == getSkinnable().getOrientation()) {
                vBox.setManaged(false);
                vBox.setVisible(false);
                hBox.getChildren().setAll(leds);
            } else {
                hBox.setManaged(false);
                hBox.setVisible(false);                
                vBox.getChildren().setAll(leds);
            }    
        } else if ("NO_OF_LEDS".equals(PROPERTY)) {
            leds.clear();
            hBox.getChildren().clear();
            vBox.getChildren().clear();
            for (int i = 0 ; i < getSkinnable().getNoOfLeds() ; i++) {
                Region led = new Region();
                led.setPrefSize(10, 20);
                if (getSkinnable().getSections().isEmpty()) {
                    led.getStyleClass().setAll("led");
                } else {
                    for (Section section : getSkinnable().getSections()) {
                        if (section.contains(i * stepSize.doubleValue())) {
                            led.getStyleClass().setAll("led", section.getStyleClass());
                        }
                    }
                }
                leds.add(led);
            }

            if (Orientation.HORIZONTAL == getSkinnable().getOrientation()) {                
                hBox.getChildren().setAll(leds);
            } else {   
                vBox.getChildren().setAll(leds);
            }    
        } else if ("SECTIONS".equals(PROPERTY)) {
            for (int i = 0 ; i < getSkinnable().getNoOfLeds() ; i++) {
                Region led = new Region();
                led.setPrefSize(10, 20);
                if (getSkinnable().getSections().isEmpty()) {
                    led.getStyleClass().setAll("led");
                } else {
                    for (Section section : getSkinnable().getSections()) {
                        if (section.contains(i * stepSize.doubleValue())) {
                            led.getStyleClass().setAll("led", section.getStyleClass());
                        }
                    }
                }
                leds.add(led);
            }    
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

    private void handleMouseEvent(final MouseEvent EVENT) {        
        if (active && MouseEvent.MOUSE_ENTERED == EVENT.getEventType()) {
            final Region SRC = (Region) EVENT.getSource();            
            if (Orientation.HORIZONTAL == getSkinnable().getOrientation()) {
                for (int i = 0 ; i < leds.size() ; i++) {
                    leds.get(i).getStyleClass().remove("led-on");
                    if (i <= leds.indexOf(SRC)) {
                        leds.get(i).getStyleClass().add("led-on");
                    }                
                }            
            } else {
                for (int i = 0 ; i < leds.size() ; i++) {
                    leds.get(i).getStyleClass().remove("led-on");
                    if (i >= leds.indexOf(SRC)) {
                        leds.get(i).getStyleClass().add("led-on");
                    }
                }
            }
        }
    }
    

    // ******************** Private Methods ***********************************    
    private void resize() {
        size = getSkinnable().getWidth() < getSkinnable().getHeight() ? getSkinnable().getWidth() : getSkinnable().getHeight();                       
        if (size > 0) {                        
            for (Region led : leds) {
                led.setPrefSize(size, size);
            }                                        
        }
    }        
}
