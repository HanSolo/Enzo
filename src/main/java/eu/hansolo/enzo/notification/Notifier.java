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

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
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

    private static final Duration              LIFE_TIME = Duration.millis(5000);
    private static final Image                 INFO      = null;
    private static final Image                 WARNING   = null;
    private static final Image                 SUCCESS   = null;
    private static final Image                 ERROR     = null;
    private ObservableMap<Notification, Popup> notifications;


    // ******************** Constructor ***************************************
    private Notifier() {
        notifications = FXCollections.observableHashMap();
    }


    // ******************** Initialization ************************************
    private void registerListeners() {
        notifications.addListener((MapChangeListener<Notification, Popup>) change -> reOrder() );
    }


    // ******************** Methods *******************************************
    // Custom notification
    public void fire(final Notification NOTIFICATION) {
        notifications.put(NOTIFICATION, createPopup(NOTIFICATION));

    }
    public void fire(final String TITLE, final String MESSAGE, final Image IMAGE) {
        fire(new Notification(TITLE, MESSAGE, IMAGE));
    }

    // Predefined notifications
    public void fireInfo(final String MESSAGE) {
        fire(new Notification("Info", MESSAGE, INFO));
    }
    public void fireWarning(final String MESSAGE) {
        fire(new Notification("Warning", MESSAGE, WARNING));
    }
    public void fireSuccess(final String MESSAGE) {
        fire(new Notification("Success", MESSAGE, SUCCESS));
    }
    public void fireError(final String MESSAGE) {
        fire(new Notification("Error", MESSAGE, ERROR));
    }

    private Popup createPopup(final Notification NOTIFICATION) {
        final Popup POPUP = new Popup();

        return POPUP;
    }

    private void reOrder() {
        // make sure that the latest notification stays on top
    }

    public void showPopup(final Notification NOTIFICATION) {
        final Stage popupStage = createPopupStage(NOTIFICATION, notifications.size());

        final FadeTransition popupFadeOut = new FadeTransition();
        popupFadeOut.setDuration(Duration.millis(500));
        popupFadeOut.setDelay(LIFE_TIME);
        popupFadeOut.setNode(popupStage.getScene().getRoot());
        popupFadeOut.setFromValue(0.8);
        popupFadeOut.setToValue(0.0);
        popupFadeOut.setOnFinished(actionEvent -> {
            notifications.remove(NOTIFICATION);
            popupStage.close();
        });

        popupStage.show();
        popupFadeOut.play();
    }

    public Stage createPopupStage(final Notification NOTIFICATION, final int NO_OF_POPUPS) {
        final Stage popup = new Stage();
        popup.setResizable(false);
        popup.setX(Screen.getPrimary().getBounds().getMaxX() - 310);
        popup.setY(25 + (75 * NO_OF_POPUPS));
        popup.initStyle(StageStyle.TRANSPARENT);

        Rectangle shape = new Rectangle(300, 70, Color.rgb(30, 30, 30, 0.8));
        shape.setArcWidth(10);
        shape.setArcHeight(10);

        InnerShadow innerShadow = new InnerShadow();
        innerShadow.setRadius(4);
        innerShadow.setBlurType(BlurType.GAUSSIAN);
        innerShadow.setColor(Color.rgb(255, 255, 255, 0.6));
        innerShadow.setOffsetX(0);
        innerShadow.setOffsetY(0);

        shape.setEffect(innerShadow);

        Text title = new Text(NOTIFICATION.TITLE);
        title.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 13));
        title.setFill(Color.WHITE);

        ImageView image = new ImageView(NOTIFICATION.IMAGE);
        image.setFitWidth(24);
        image.setFitHeight(24);

        Label message = new Label(NOTIFICATION.MESSAGE, image);
        message.setContentDisplay(ContentDisplay.LEFT);
        message.setTextFill(Color.WHITE);
        message.setFont(Font.font("Verdana", FontWeight.NORMAL, FontPosture.REGULAR, 12));

        VBox popupLayout = new VBox();
        popupLayout.setSpacing(10);
        popupLayout.setPadding(new Insets(10, 10, 10, 10));
        popupLayout.getChildren().addAll(title, message);

        StackPane popupPane = new StackPane();
        popupPane.getChildren().addAll(shape, popupLayout);

        Scene scene = new Scene(popupPane, 300, 70, null);

        popup.setScene(scene);

        return popup;
    }
}
