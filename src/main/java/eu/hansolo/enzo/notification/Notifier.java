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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
 * 
 * Modified by
 * User: jugen
 * Date: 17.07.13
 * Added the ability to set the location of the Notification 
 * relative to either the Screen or a Stage. See setLocation( Stage, Pos )
 * Also added setters for various attributes.
 */
public enum Notifier {
    INSTANCE;

	private static double WIDTH = 300;
	private static double HEIGHT = 80;
	private static double OFFSET_X = 0;
	private static double OFFSET_Y = 25;
	private static double SPACING_Y = 5;
    private static final double   ICON_WIDTH  = 24;
    private static final double   ICON_HEIGHT = 24;
	private static Pos LOCATION = Pos.TOP_RIGHT;
	private static Stage STAGE_REF = null;
    private Duration              lifetime;
    private Stage                 stage;
    private StackPane             pane;
    private Scene                 scene;
    private ObservableList<Popup> popups;

	/**
	 * @param newWidth  The default is 300 px.
	 */
	public static void setWidth( double newWidth )
	{
		WIDTH = newWidth;
	}

	/**
	 * @param newOffset  The horizontal shift required.
	 * <br> The default is 0 px.
	 */
	public static void setOffset_X( double newOffset )
	{
		OFFSET_X = newOffset;
	}

	/**
	 * @param newHeight  The default is 80 px.
	 */
	public static void setHeight( double newHeight )
	{
		HEIGHT = newHeight;
	}

	/**
	 * @param newOffset  The vertical shift required.
	 * <br> The default is 25 px.
	 */
	public static void setOffset_Y( double newOffset )
	{
		OFFSET_Y = newOffset;
	}

	/**
	 * @param newSpacing  The spacing between multiple Notifications.
	 * <br> The default is 5 px.
	 */
	public static void setSpacing_Y( double newSpacing )
	{
		SPACING_Y = newSpacing;
	}

	/**
	 * @param stageRef  The Notification will be positioned relative to the given Stage.<br>
	 * 					If null then the Notification will be positioned relative to the primary Screen.
	 * @param position  The default is TOP_RIGHT of primary Screen.
	 */
	public static void setLocation( Stage stageRef, Pos position )
	{
		if ( stageRef != null )
		{	
			INSTANCE.stage.initOwner( stageRef );
			STAGE_REF = stageRef;
		}	
		LOCATION = position;
	}

	/**
	 * Sets the Notification's owner stage so that when the owner
	 * stage is closed Notifications will be shut down as well.<br>
	 * This is only needed if <code>setLocation</code> is called
	 * <u>without</u> a stage reference.  
	 * @param owner
	 */
	public static void setNotificationOwner( Stage owner )
	{
		INSTANCE.stage.initOwner( owner );
	}

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
			switch ( LOCATION )
			{
				case TOP_LEFT : case TOP_CENTER : case TOP_RIGHT :
				{
					popups.get( i ).setY( popups.get( i ).getY() + HEIGHT + SPACING_Y );
					break;
				}
				default :
				{
					popups.get( i ).setY( popups.get( i ).getY() - HEIGHT - SPACING_Y );
				}
			}
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
		POPUP.setX( getX() );
		POPUP.setY( getY() );
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

		if ( ! stage.isShowing() )  stage.show();
		else  stage.toFront();

        POPUP.show(stage);
        timeline.play();
    }


	private double getX()
	{
		if ( STAGE_REF != null ) return calcX( STAGE_REF.getX(), STAGE_REF.getWidth() );

		return calcX( 0.0, Screen.getPrimary().getBounds().getWidth() );
	}

	private double calcX( double left, double totalWidth )
	{
		switch ( LOCATION )
		{
			case TOP_LEFT : case CENTER_LEFT : case BOTTOM_LEFT :
			{
				return left + OFFSET_X;
			}
			case TOP_CENTER : case CENTER : case BOTTOM_CENTER :
			{
				return left + (totalWidth-WIDTH)/2 - OFFSET_X;
			}
			case TOP_RIGHT : case CENTER_RIGHT : case BOTTOM_RIGHT :
			{
				return left + totalWidth - WIDTH - OFFSET_X;
			}
			default : return 0.0;
		}
	}


	private double getY()
	{
		if ( STAGE_REF != null ) return calcY( STAGE_REF.getY(), STAGE_REF.getHeight() );

		return calcY( 0.0, Screen.getPrimary().getBounds().getHeight() );
	}

	private double calcY( double top, double totalHeight )
	{
		switch ( LOCATION )
		{
			case TOP_LEFT : case TOP_CENTER : case TOP_RIGHT :
			{
				return top + OFFSET_Y;
			}
			case CENTER_LEFT : case CENTER : case CENTER_RIGHT :
			{
				return top + (totalHeight-HEIGHT)/2 - OFFSET_Y;
			}
			case BOTTOM_LEFT : case BOTTOM_CENTER : case BOTTOM_RIGHT :
			{
				return top + totalHeight - HEIGHT - OFFSET_Y;
			}
			default : return 0.0;
		}
	}

}
