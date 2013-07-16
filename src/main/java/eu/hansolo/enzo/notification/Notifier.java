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
        lifetime = Duration.millis(5000);
        popups   = FXCollections.observableArrayList();
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
    public void stop() {
        popups.clear();
        stage.close();
    }

    /**
     * Returns the Duration that the notification will stay on screen before it
     * will fade out.
     * @return the Duration the popup notification will stay on screen
     */
    public Duration getPopupLifeTime() {
        return lifetime;
    }

    /**
     * Defines the Duration that the popup notification will stay on screen before it
     * will fade out. The parameter is limited to values between 2 and 20 seconds.
     * @param DURATION
     */
    public void setPopupLifetime(final Duration DURATION) {
        lifetime = Duration.millis(clamp(2000, 20000, DURATION.toMillis()));
    }

    /**
     * Show the given Notification on the screen
     * @param NOTIFICATION
     */
    public void notify(final Notification NOTIFICATION) {
        preOrder();
        showPopup(NOTIFICATION);
    }

    /**
     * Show a Notification with the given parameters on the screen
     * @param TITLE
     * @param MESSAGE
     * @param IMAGE
     */
    public void notify(final String TITLE, final String MESSAGE, final Image IMAGE) {
        notify(new Notification(TITLE, MESSAGE, IMAGE));
    }

    /**
     * Show a Notification with the given title and message and an Info icon
     * @param TITLE
     * @param MESSAGE
     */
    public void notifyInfo(final String TITLE, final String MESSAGE) {
        notify(new Notification(TITLE, MESSAGE, Notification.INFO_ICON));
    }

    /**
     * Show a Notification with the given title and message and a Warning icon
     * @param TITLE
     * @param MESSAGE
     */
    public void notifyWarning(final String TITLE, final String MESSAGE) {
        notify(new Notification(TITLE, MESSAGE, Notification.WARNING_ICON));
    }

    /**
     * Show a Notification with the given title and message and a Checkmark icon
     * @param TITLE
     * @param MESSAGE
     */
    public void notifySuccess(final String TITLE, final String MESSAGE) {
        notify(new Notification(TITLE, MESSAGE, Notification.SUCCESS_ICON));
    }

    /**
     * Show a Notification with the given title and message and an Error icon
     * @param TITLE
     * @param MESSAGE
     */
    public void notifyError(final String TITLE, final String MESSAGE) {
        notify(new Notification(TITLE, MESSAGE, Notification.ERROR_ICON));
    }

    /**
     * Makes sure that the given VALUE is within the range of MIN to MAX
     * @param MIN
     * @param MAX
     * @param VALUE
     * @return
     */
    private double clamp(final double MIN, final double MAX, final double VALUE) {
        if (VALUE < MIN) return MIN;
        if (VALUE > MAX) return MAX;
        return VALUE;
    }

    /**
     * Reorder the popup Notifications on screen so that the latest Notification will stay on top
     */
    private void preOrder() {
        if (popups.isEmpty()) return;
        for (int i = 0 ; i < popups.size() ; i++) {
            popups.get(i).setY(popups.get(i).getY() + HEIGHT+ SPACING_Y);
        }
    }

    /**
     * Creates and shows a popup with the data from the given Notification object
     * @param NOTIFICATION
     */
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

        final Popup POPUP = new Popup();
        POPUP.setX(Screen.getPrimary().getBounds().getWidth() - WIDTH - 10);
        POPUP.setY(OFFSET_Y);
        POPUP.getContent().add(popupPane);

        popups.add(POPUP);

        // Add a timeline for popup fade out
        KeyValue fadeOutBegin = new KeyValue(POPUP.opacityProperty(), 1.0);
        KeyValue fadeOutEnd   = new KeyValue(POPUP.opacityProperty(), 0.0);

        KeyFrame kfBegin = new KeyFrame(Duration.ZERO, fadeOutBegin);
        KeyFrame kfEnd   = new KeyFrame(Duration.millis(500), fadeOutEnd);

        Timeline timeline = new Timeline(kfBegin, kfEnd);
        timeline.setDelay(lifetime);
        timeline.setOnFinished(actionEvent -> {
            Platform.runLater(new Runnable() {
                @Override public void run() {
                    POPUP.hide();
                    popups.remove(POPUP);
                }
            });
        });

        // Move popup to the right during fade out
        //POPUP.opacityProperty().addListener((observableValue, oldOpacity, opacity) -> popup.setX(popup.getX() + (1.0 - opacity.doubleValue()) * popup.getWidth()) );

        POPUP.show(stage);
        timeline.play();
    }
}
