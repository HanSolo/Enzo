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

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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

    private static final double   WIDTH       = 300;
    private static final double   HEIGHT      = 80;
    private static final double   OFFSET_Y    = 25;
    private static final double   SPACING_Y   = 5;
    private static final double   ICON_WIDTH  = 24;
    private static final double   ICON_HEIGHT = 24;
    private Duration              lifetime;
    private Stage                 stage;
    private StackPane             pane;
    private Scene                 scene;
    private ObservableList<Popup> popups;


    // ******************** Constructor ***************************************
    private Notifier() {
        init();
        initGraphics();
    }


    // ******************** Initialization ************************************
    private void init() {
        lifetime        = Duration.millis(5000);
        popups          = FXCollections.observableArrayList();
    }

    private void initGraphics() {
        pane  = new StackPane();
        scene = new Scene(pane);
        scene.setFill(null);
        scene.getStylesheets().add(getClass().getResource("notifier.css").toExternalForm());

        stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }


    // ******************** Methods *******************************************
    public Duration getPopupLifeTime() {
        return lifetime;
    }
    public void setPopupLifetime(final Duration DURATION) {
        lifetime = DURATION;
    }

    // Custom notification
    public void notify(final Notification NOTIFICATION) {
        preOrder();
        showPopup(NOTIFICATION);
    }
    public void notify(final String TITLE, final String MESSAGE, final Image IMAGE) {
        notify(new Notification(TITLE, MESSAGE, IMAGE));
    }

    // Predefined notifications
    public void notifyInfo(final String MESSAGE) {
        notify(new Notification("Info", MESSAGE, Notification.INFO_ICON));
    }
    public void notifyWarning(final String MESSAGE) {
        notify(new Notification("Warning", MESSAGE, Notification.WARNING_ICON));
    }
    public void notifySuccess(final String MESSAGE) {
        notify(new Notification("Success", MESSAGE, Notification.SUCCESS_ICON));
    }
    public void notifyError(final String MESSAGE) {
        notify(new Notification("Error", MESSAGE, Notification.ERROR_ICON));
    }

    private void preOrder() {
        if (popups.isEmpty()) return;
        for (int i = 0 ; i < popups.size() ; i++) {
            popups.get(i).setY(popups.get(i).getY() + HEIGHT+ SPACING_Y);
        }
    }

    // Create and show a popup with the given Notification
    private void showPopup(final Notification NOTIFICATION) {
        Region body = new Region();
        body.getStyleClass().addAll("body");
        body.setPrefSize(WIDTH, HEIGHT);

        Label title = new Label(NOTIFICATION.TITLE);
        title.getStyleClass().add("title");

        ImageView icon = new ImageView(NOTIFICATION.IMAGE);
        icon.setFitWidth(ICON_WIDTH);
        icon.setFitHeight(ICON_HEIGHT);

        Label message = new Label(NOTIFICATION.MESSAGE, icon);
        message.getStyleClass().add("message");

        VBox popupLayout = new VBox();
        popupLayout.setSpacing(10);
        popupLayout.setPadding(new Insets(10, 10, 10, 10));
        popupLayout.getChildren().addAll(title, message);

        StackPane popupPane = new StackPane();
        popupPane.getStyleClass().add("notification");
        popupPane.getChildren().addAll(body, popupLayout);

        Popup popup = new Popup();
        popup.setX(Screen.getPrimary().getBounds().getWidth() - WIDTH - 10);
        popup.setY(OFFSET_Y);
        popup.getContent().add(popupPane);

        popups.add(popup);

        // Add a timeline for popup fade out
        KeyValue fadeOutBegin = new KeyValue(popup.opacityProperty(), 1.0);
        KeyValue fadeOutEnd   = new KeyValue(popup.opacityProperty(), 0.0);

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

        // Move popup to the right during fade out
        //popup.opacityProperty().addListener((observableValue, oldOpacity, opacity) -> popup.setX(popup.getX() + (1.0 - opacity.doubleValue()) * popup.getWidth()) );

        popup.show(stage);
        timeline.play();
    }
}
