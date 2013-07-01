/*
 * Copyright (c) 2013 by Gerrit Grunwald
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.hansolo.enzo.notification;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


/**
 * Created by
 * User: hansolo
 * Date: 01.07.13
 * Time: 07:10
 */
public enum Notifier {
    INSTANCE;

    private static final double   WIDTH     = 300;
    private static final double   HEIGHT    = 80;
    private static final double   OFFSET_Y  = 25;
    private static final double   SPACING_Y = 5;
    private Duration              lifetime;
    private Color                 popupBackground;
    private Color                 popupForeground;
    private Stage                 stage;
    private StackPane             pane;
    private Scene                 scene;
    private InnerShadow           innerShadow;
    private ObservableList<Popup> popups;


    // ******************** Constructor ***************************************
    private Notifier() {
        init();
        initGraphics();
    }


    // ******************** Initialization ************************************
    private void init() {
        lifetime        = Duration.millis(5000);
        popupBackground = Color.rgb(30, 30, 30, 0.8);
        popupForeground = Color.WHITE;
        popups          = FXCollections.observableArrayList();
    }

    private void initGraphics() {
        pane  = new StackPane();
        scene = new Scene(pane);
        scene.setFill(null);

        stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();

        innerShadow = new InnerShadow();
        innerShadow.setRadius(4);
        innerShadow.setBlurType(BlurType.GAUSSIAN);
        innerShadow.setColor(Color.rgb(255, 255, 255, 0.6));
        innerShadow.setOffsetX(0);
        innerShadow.setOffsetY(0);
    }


    // ******************** Methods *******************************************
    public Duration getPopupLifeTime() {
        return lifetime;
    }
    public void setPopupLifetime(final Duration DURATION) {
        lifetime = DURATION;
    }

    public Color getPopupBackground() {
        return popupBackground;
    }
    public void setPopupBackground(final Color POPUP_BACKGROUND) {
        popupBackground = POPUP_BACKGROUND;
    }

    public Color getPopupForeground() {
        return popupForeground;
    }
    public void setPopupForeground(final Color POPUP_FOREGROUND) {
        popupForeground = POPUP_FOREGROUND;
    }

    // Custom notification
    public void fire(final Notification NOTIFICATION) {
        preOrder();
        showPopup(NOTIFICATION);
    }
    public void fire(final String TITLE, final String MESSAGE, final Image IMAGE) {
        fire(new Notification(TITLE, MESSAGE, IMAGE));
    }

    // Predefined notifications
    public void fireInfo(final String MESSAGE) {
        fire(new Notification("Info", MESSAGE, Notification.INFO_ICON));
    }
    public void fireWarning(final String MESSAGE) {
        fire(new Notification("Warning", MESSAGE, Notification.WARNING_ICON));
    }
    public void fireSuccess(final String MESSAGE) {
        fire(new Notification("Success", MESSAGE, Notification.SUCCESS_ICON));
    }
    public void fireError(final String MESSAGE) {
        fire(new Notification("Error", MESSAGE, Notification.ERROR_ICON));
    }

    private void preOrder() {
        if (popups.isEmpty()) return;
        for (int i = 0 ; i < popups.size() ; i++) {
            popups.get(i).setY(popups.get(i).getY() + HEIGHT+ SPACING_Y);
        }
    }

    // Create and show a popup with the given Notification
    private void showPopup(final Notification NOTIFICATION) {
        Rectangle shape = new Rectangle(WIDTH, HEIGHT, popupBackground);
        shape.setArcWidth(10);
        shape.setArcHeight(10);
        shape.setEffect(innerShadow);

        Text title = new Text(NOTIFICATION.TITLE);
        title.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 13));
        title.setFill(popupForeground);

        ImageView image = new ImageView(NOTIFICATION.IMAGE);
        image.setFitWidth(24);
        image.setFitHeight(24);

        Label message = new Label(NOTIFICATION.MESSAGE, image);
        message.setGraphicTextGap(10);
        message.setContentDisplay(ContentDisplay.LEFT);
        message.setTextFill(popupForeground);
        message.setFont(Font.font("Verdana", FontWeight.NORMAL, FontPosture.REGULAR, 12));

        VBox popupLayout = new VBox();
        popupLayout.setSpacing(10);
        popupLayout.setPadding(new Insets(10, 10, 10, 10));
        popupLayout.getChildren().addAll(title, message);

        StackPane popupPane = new StackPane();
        popupPane.getChildren().addAll(shape, popupLayout);

        Popup popup = new Popup();
        popup.setX(Screen.getPrimary().getBounds().getWidth() - WIDTH - 10);
        popup.setY(OFFSET_Y);
        popup.getContent().add(popupPane);

        popups.add(popup);

        KeyValue fadeOutBegin = new KeyValue(popup.opacityProperty(), 1.0);
        KeyValue fadeOutEnd   = new KeyValue(popup.opacityProperty(), 0.0, Interpolator.EASE_OUT);

        KeyFrame kfBegin = new KeyFrame(Duration.ZERO, fadeOutBegin);
        KeyFrame kfEnd   = new KeyFrame(Duration.millis(500), fadeOutEnd);

        Timeline timeline = new Timeline(kfBegin, kfEnd);
        timeline.setDelay(lifetime);
        timeline.setOnFinished(actionEvent -> {
            Platform.runLater(new Runnable() {
                @Override public void run() {
                    popup.hide();
                    popups.remove(popup);
                }
            });
        });
        popup.show(stage);
        timeline.play();
    }
}
