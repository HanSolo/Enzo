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

package eu.hansolo.enzo.experimental.pushbutton.skin;

import eu.hansolo.enzo.experimental.pushbutton.PushButton;
import javafx.collections.ListChangeListener;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;


public class PushButtonSkin extends SkinBase<PushButton> implements Skin<PushButton> {
    private static final double MINIMUM_WIDTH    = 5;
    private static final double MINIMUM_HEIGHT   = 5;
    private static final double MAXIMUM_WIDTH    = 1024;
    private static final double MAXIMUM_HEIGHT   = 1024;
    private static final double PREFERRED_WIDTH  = 128;
    private static final double PREFERRED_HEIGHT = 128;
    private double              aspectRatio;
    private double              size;
    private double              width;
    private double              height;
    private Pane                pane;

    private Region              frame;
    private InnerShadow         frameInnerShadow0;
    private InnerShadow         frameInnerShadow1;

    private Region              deselected;
    private InnerShadow         deselectedInnerShadow0;
    private InnerShadow         deselectedInnerShadow1;
    private DropShadow          deselectedDropShadow;

    private Region              selected;
    private InnerShadow         selectedInnerShadow0;
    private InnerShadow         selectedInnerShadow1;
    private DropShadow          selectedDropShadow;

    private Region              icon;


    // ******************** Constructors **************************************
    public PushButtonSkin(final PushButton CONTROL) {
        super(CONTROL);
        aspectRatio  = PREFERRED_HEIGHT / PREFERRED_WIDTH;
        pane         = new Pane();
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
        frame = new Region();
        frame.getStyleClass().setAll("frame");

        frameInnerShadow0 = new InnerShadow();
        frameInnerShadow0.setOffsetX(0);
        frameInnerShadow0.setOffsetY(1);
        frameInnerShadow0.setRadius(0);
        frameInnerShadow0.setColor(Color.web("0x333333a6"));
        frameInnerShadow0.setBlurType(BlurType.TWO_PASS_BOX);
        frameInnerShadow1 = new InnerShadow();
        frameInnerShadow1.setOffsetX(0);
        frameInnerShadow1.setOffsetY(-1);
        frameInnerShadow1.setRadius(0);
        frameInnerShadow1.setColor(Color.web("0xeeeeeea6"));
        frameInnerShadow1.setBlurType(BlurType.TWO_PASS_BOX);
        frameInnerShadow1.setInput(frameInnerShadow0);
        frame.setEffect(frameInnerShadow1);

        deselected = new Region();
        deselected.getStyleClass().setAll("deselected");

        deselectedInnerShadow0 = new InnerShadow();
        deselectedInnerShadow0.setOffsetX(0);
        deselectedInnerShadow0.setOffsetY(-1);
        deselectedInnerShadow0.setRadius(0);
        deselectedInnerShadow0.setColor(Color.web("0x4b4e52a6"));
        deselectedInnerShadow0.setBlurType(BlurType.TWO_PASS_BOX);
        deselectedInnerShadow1 = new InnerShadow();
        deselectedInnerShadow1.setOffsetX(0);
        deselectedInnerShadow1.setOffsetY(1);
        deselectedInnerShadow1.setRadius(0);
        deselectedInnerShadow1.setColor(Color.web("0xeeeeeea6"));
        deselectedInnerShadow1.setBlurType(BlurType.TWO_PASS_BOX);
        deselectedInnerShadow1.setInput(deselectedInnerShadow0);
        deselectedDropShadow = new DropShadow();
        deselectedDropShadow.setOffsetX(0);
        deselectedDropShadow.setOffsetY(3);
        deselectedDropShadow.setRadius(3.0 / 128.0 * PREFERRED_WIDTH);
        deselectedDropShadow.setColor(Color.web("0x000000a6"));
        deselectedDropShadow.setBlurType(BlurType.TWO_PASS_BOX);
        deselectedDropShadow.setInput(deselectedInnerShadow1);
        deselected.setEffect(deselectedDropShadow);

        selected = new Region();
        selected.getStyleClass().setAll("selected");

        selectedInnerShadow0 = new InnerShadow();
        selectedInnerShadow0.setOffsetX(0);
        selectedInnerShadow0.setOffsetY(-1);
        selectedInnerShadow0.setRadius(0);
        selectedInnerShadow0.setColor(Color.web("0x4b4e52a6"));
        selectedInnerShadow0.setBlurType(BlurType.TWO_PASS_BOX);
        selectedInnerShadow1 = new InnerShadow();
        selectedInnerShadow1.setOffsetX(0);
        selectedInnerShadow1.setOffsetY(1);
        selectedInnerShadow1.setRadius(0);
        selectedInnerShadow1.setColor(Color.web("0xeeeeeea6"));
        selectedInnerShadow1.setBlurType(BlurType.TWO_PASS_BOX);
        selectedInnerShadow1.setInput(selectedInnerShadow0);
        selectedDropShadow = new DropShadow();
        selectedDropShadow.setOffsetX(0);
        selectedDropShadow.setOffsetY(0);
        selectedDropShadow.setRadius(2.0 / 128.0 * PREFERRED_WIDTH);
        selectedDropShadow.setColor(Color.web("0x000000a6"));
        selectedDropShadow.setBlurType(BlurType.TWO_PASS_BOX);
        selectedDropShadow.setInput(selectedInnerShadow1);
        selected.setEffect(selectedDropShadow);

        icon = new Region();
        icon.getStyleClass().setAll("icon");
        icon.setStyle("-icon-on: " + colorToCss(getSkinnable().getColor()));
        pane.getChildren().setAll(frame,
                                  deselected,
                                  selected,
                                  icon);

        // Adjust visibility dependent on settings
        updateStatus();

        getChildren().setAll(pane);
        resize();
    }

    private void registerListeners() {
        getSkinnable().widthProperty().addListener(observable -> handleControlPropertyChanged("RESIZE") );
        getSkinnable().heightProperty().addListener(observable -> handleControlPropertyChanged("RESIZE") );
        getSkinnable().prefWidthProperty().addListener(observable -> handleControlPropertyChanged("PREF_SIZE") );
        getSkinnable().prefHeightProperty().addListener(observable -> handleControlPropertyChanged("PREF_SIZE") );
        getSkinnable().statusProperty().addListener(observable -> handleControlPropertyChanged("STATUS") );
        getSkinnable().colorProperty().addListener(observable -> handleControlPropertyChanged("COLOR") );
        getSkinnable().getStyleClass().addListener(new ListChangeListener<String>() {
            @Override public void onChanged(Change<? extends String> change) {
                resize();
            }
        });
    }


    // ******************** Methods *******************************************
    protected void handleControlPropertyChanged(final String PROPERTY) {
        if ("RESIZE".equals(PROPERTY)) {
            resize();
        } else if ("STATUS".equals(PROPERTY)) {
            updateStatus();
        } else if ("COLOR".equals(PROPERTY)) {
            icon.setStyle("-icon-on-color: " + colorToCss(getSkinnable().getColor()));
            resize();
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

    private String colorToCss(final Color COLOR) {
        StringBuilder cssColor = new StringBuilder();
        cssColor.append("rgba(")
                .append((int) (COLOR.getRed() * 255)).append(", ")
                .append((int) (COLOR.getGreen() * 255)).append(", ")
                .append((int) (COLOR.getBlue() * 255)).append(", ")
                .append(COLOR.getOpacity()).append(")");
        return cssColor.toString();
    }

    private void updateStatus() {
        switch (getSkinnable().getStatus()) {
            case DESELECTED:
                selected.setOpacity(0);
                deselected.setOpacity(1);
                icon.getStyleClass().setAll("icon");
                icon.setOpacity(1);
                break;
            case SELECTED:
                selected.setOpacity(1);
                deselected.setOpacity(0);
                icon.getStyleClass().setAll("icon", "icon-selected");
                icon.setOpacity(1);
                break;
            case EMPTY:
            default:
                selected.setOpacity(0);
                deselected.setOpacity(0);
                icon.setOpacity(0);
                break;
        }
    }


    // ******************** Resizing ******************************************
    private void resize() {
        size   = getSkinnable().getWidth() < getSkinnable().getHeight() ? getSkinnable().getWidth() : getSkinnable().getHeight();
        width  = getSkinnable().getWidth();
        height = getSkinnable().getHeight();
        if (getSkinnable().isKeepAspect()) {
            if (aspectRatio * width > height) {
                width  = 1 / (aspectRatio / height);
            } else if (1 / (aspectRatio / height) > width) {
                height = aspectRatio * width;
            }
        }
        if (width > 0 && height > 0) {
            frame.setPrefSize(width, height);

            deselected.setPrefSize(0.6875 * width, 0.6875 * height);
            deselected.setTranslateX(0.15625 * width);
            deselected.setTranslateY(0.15625 * height);
            deselectedDropShadow.setRadius(3.0 / 128.0 * size);

            selected.setPrefSize(0.6875 * width, 0.6796875 * height);
            selected.setTranslateX(0.15625 * width);
            selected.setTranslateY(0.15625 * height);
            selectedDropShadow.setRadius(2.0 / 128.0 * size);

            icon.setPrefSize(0.375 * width, 0.4375 * height);
            icon.setTranslateX(0.3125 * width);
            icon.setTranslateY(0.28125 * height);
        }
    }
}

