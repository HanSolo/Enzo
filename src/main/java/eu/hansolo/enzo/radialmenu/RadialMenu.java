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

package eu.hansolo.enzo.radialmenu;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * User: hansolo
 * Date: 21.09.12
 * Time: 13:25
 * To change this template use File | Settings | File Templates.
 */
public class RadialMenu extends Region {
    public static enum State {
        OPENED,
        CLOSED
    }

    private ObservableMap<Parent, MenuItem> items;
    private State                           defaultState;
    private ObjectProperty<State>           state;
    private double                          degrees;
    private int                             positions;
    private Timeline[]                      openTimeLines;
    private Timeline[]                      closeTimeLines;
    private Options                         options;
    private Group                           button;
    private Circle                          mainMenuButton;
    private Group                           cross;
    private boolean                         firstTime;
    private EventHandler<MouseEvent>        mouseHandler;


    // ******************** Constructors **************************************
    public RadialMenu(final Options OPTIONS, final MenuItem... ITEMS) {
        this(OPTIONS, Arrays.asList(ITEMS));
    }
    public RadialMenu(final Options OPTIONS, final List<MenuItem> ITEMS) {
        options               = OPTIONS;
        items                 = FXCollections.observableHashMap();
        getStylesheets().add(getClass().getResource("radialmenu.css").toExternalForm());
        getStyleClass().addAll("radial-menu");
        state                 = new SimpleObjectProperty<>(this, "state", State.CLOSED);
        degrees               = Math.max(Math.min(360, options.getDegrees()), 0);
        positions             = Double.compare(degrees, 360.0) == 0 ? ITEMS.size() : ITEMS.size() - 1;
        openTimeLines         = new Timeline[ITEMS.size()];
        closeTimeLines        = new Timeline[ITEMS.size()];
        button                = new Group();
        cross                 = new Group();
        firstTime             = true;
        initHandler();
        initMainButton();
        initMenuItems(ITEMS);
        initGraphics();
        registerListeners();
    }


    // ******************** Initialization ************************************
    private void initMainButton() {
        // Define main menu button
        /*
        mainMenuButton = new Region();
        mainMenuButton.getStyleClass().add("main-menu-button");
         */

        final Point2D CENTER     = new Point2D(getPrefWidth() * 0.5, getPrefHeight() * 0.5);
        mainMenuButton = new Circle(CENTER.getX(), CENTER.getY(), options.getButtonSize() * 0.5);
        /*
        mainMenuButton.setFill(new LinearGradient(0, mainMenuButton.getLayoutBounds().getMinY(),
                                               0, mainMenuButton.getLayoutBounds().getMaxY(),
                                               false, CycleMethod.NO_CYCLE,
                                               new Stop(0.0, Color.hsb(options.getButtonFillColor().getHue(), 0.80, 0.90)),
                                               new Stop(0.5, Color.hsb(options.getButtonFillColor().getHue(), 0.75, 0.90)),
                                               new Stop(0.5, Color.hsb(options.getButtonFillColor().getHue(), 0.85, 0.90)),
                                               new Stop(1.0, Color.hsb(options.getButtonFillColor().getHue(), 0.90, 0.80))));
        */
        mainMenuButton.setFill(options.getButtonFillColor());
        mainMenuButton.setStroke(options.getButtonStrokeColor());
        mainMenuButton.setStrokeWidth(0.0681818182 * options.getButtonSize());

        cross.getChildren().clear();
        final Line HOR = new Line(CENTER.getX() - (0.1818181818 * options.getButtonSize()), CENTER.getY(), CENTER.getX() + (0.1818181818 * options.getButtonSize()), CENTER.getY());
        final Line VER = new Line(CENTER.getX(), CENTER.getY() - (0.1818181818 * options.getButtonSize()), CENTER.getX(), CENTER.getY() + (0.1818181818 * options.getButtonSize()));
        HOR.setStrokeWidth(0.0909090909 * options.getButtonSize());
        VER.setStrokeWidth(0.0909090909 * options.getButtonSize());
        HOR.setStroke(Color.WHITE);
        VER.setStroke(Color.WHITE);
        cross.getChildren().addAll(HOR, VER);
        cross.setMouseTransparent(true);

        mainMenuButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseHandler);
        mainMenuButton.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseHandler);
        mainMenuButton.addEventHandler(MouseEvent.MOUSE_RELEASED, mouseHandler);
        mainMenuButton.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseHandler);

        final DropShadow SHADOW = new DropShadow();
        SHADOW.setRadius(0.1590909091 * options.getButtonSize());
        SHADOW.setColor(Color.rgb(0, 0, 0, 0.6));
        SHADOW.setBlurType(BlurType.TWO_PASS_BOX);
        button.setEffect(SHADOW);

        button.setOpacity(options.isButtonVisible() ? options.getButtonAlpha() : 0.0);

        button.getChildren().setAll(mainMenuButton, cross);
    }

    private void initMenuItems(final List<MenuItem> ITEMS) {
        Map<Parent, MenuItem> itemMap = new HashMap<>(ITEMS.size());

        final DropShadow SHADOW = new DropShadow();
        SHADOW.setRadius(0.1590909091 * options.getButtonSize());
        SHADOW.setColor(Color.rgb(0, 0, 0, 0.6));
        SHADOW.setBlurType(BlurType.TWO_PASS_BOX);

        for (int i = 0 ; i < ITEMS.size() ; i++) {
            MenuItem item = ITEMS.get(i);

            // Create graphical representation of each menu item
            final StackPane ITEM_CONTAINER = new StackPane();

            ITEM_CONTAINER.getChildren().add(createItemShape(item, SHADOW));

            if (SymbolType.NONE == item.getSymbolType() && item.getThumbnailImageName().isEmpty()) {
                Text text = new Text(item.getText());
                text.setFont(Font.font("Open Sans", FontWeight.BOLD, item.getSize() * 0.5));
                text.setFill(item.getForegroundColor());
                text.setMouseTransparent(true);
                ITEM_CONTAINER.getChildren().add(text);
            } else if (!item.getThumbnailImageName().isEmpty()) {
                try {
                    ITEM_CONTAINER.getChildren().add(createThumbnail(item));
                } catch (IllegalArgumentException exception) {
                    Text text = new Text(Integer.toString(i));
                    text.setFont(Font.font("Open Sans", FontWeight.BOLD, item.getSize() * 0.5));
                    text.setFill(item.getForegroundColor());
                    text.setMouseTransparent(true);
                    ITEM_CONTAINER.getChildren().add(text);
                }
            } else {
                Symbol symbol = new Symbol(item.getSymbolType(), 0.7 * item.getSize(), Color.WHITE);
                symbol.setMouseTransparent(true);
                ITEM_CONTAINER.getChildren().add(symbol);
            }

            ITEM_CONTAINER.setPickOnBounds(false);
            ITEM_CONTAINER.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseHandler);
            ITEM_CONTAINER.setOpacity(0.0);

            // Add animations for each menu item
            double  degree    = (((degrees / positions) * i) + options.getOffset()) % 360;
            Point2D position  = new Point2D(Math.cos(Math.toRadians(degree)), Math.sin(Math.toRadians(degree)));
            double x          = Math.round(position.getX() * options.getRadius());
            double y          = Math.round(position.getY() * options.getRadius());
            double delay      = (200 / ITEMS.size()) * i;

            openTimeLines[i]  = createItemOpenTimeLine(ITEM_CONTAINER, x, y, delay);
            closeTimeLines[i] = createItemCloseTimeLine(ITEM_CONTAINER, x, y, delay);

            // Add mouse event handler to each item
            ITEM_CONTAINER.setOnMousePressed(mouseHandler);
            ITEM_CONTAINER.setOnMouseReleased(mouseHandler);

            // Add items and nodes to map
            itemMap.put(ITEM_CONTAINER, item);
        }
        items.putAll(itemMap);
    }

    private void initGraphics() {
        getChildren().setAll(items.keySet());
        getChildren().add(button);
    }

    private void initHandler() {
        mouseHandler          = mouseEvent -> {
            final Object SOURCE = mouseEvent.getSource();
            if (MouseEvent.MOUSE_CLICKED == mouseEvent.getEventType()) {
                if(mouseEvent.getSource().equals(mainMenuButton) && button.getOpacity() > 0) {
                    if (State.CLOSED == getState()) {
                        open();
                    } else {
                        close();
                    }
                }
            } else if (MouseEvent.MOUSE_PRESSED == mouseEvent.getEventType()) {
                if (SOURCE.equals(mainMenuButton)) {

                } else {
                    MenuItem item = items.get(SOURCE);
                    if (item.isSelectable()) {
                        item.setSelected(!item.isSelected());
                        select(item);
                        fireItemEvent(new ItemEvent(item, this, null, ItemEvent.ITEM_SELECTED));
                    } else {
                        click(item);
                        fireItemEvent(new ItemEvent(item, this, null, ItemEvent.ITEM_CLICKED));
                    }
                }
            } else if (MouseEvent.MOUSE_RELEASED == mouseEvent.getEventType()) {
                if (mouseEvent.getSource().equals(mainMenuButton)) {

                }
            } else if (MouseEvent.MOUSE_ENTERED == mouseEvent.getEventType()) {
                if (mouseEvent.getSource().equals(mainMenuButton)) {

                } else {

                }

            }
        };
    }

    private void registerListeners() {
        widthProperty().addListener(observable -> resize());
        heightProperty().addListener(observable -> resize());
    }


    // ******************** Methods *******************************************
    @Override public void layoutChildren() {
        super.layoutChildren();
        if (firstTime) {
            resize();
            firstTime = false;
        }
    }

    public final State getState() {
        return null == state ? defaultState : state.get();
    }
    private final void setState(final State STATE) {
        if (null == state) {
            defaultState = STATE;
        } else {
            state.set(STATE);
        }
    }
    public final ReadOnlyObjectProperty<State> stateProperty() {
        if (null == state) {
            state = new SimpleObjectProperty<>(this, "state", defaultState);
        }
        return state;
    }

    public MenuItem getItem(final int INDEX) {
        if (INDEX < 0 || INDEX > items.size()) {
            throw new IndexOutOfBoundsException();
        }
        return getItems().get(INDEX);
    }
    public void addItem(final MenuItem ITEM) {
        List<MenuItem> tmpItems = (List<MenuItem>) items.values();
        tmpItems.add(ITEM);
        initMenuItems(tmpItems);
        initGraphics();
    }
    public void removeItem(final MenuItem ITEM) {
        if (!items.values().contains(ITEM)) {
            return;
        }
        List<MenuItem> tmpItems = (List<MenuItem>) items.values();
        tmpItems.remove(ITEM);
        initMenuItems(tmpItems);
        initGraphics();
    }
    public List<MenuItem> getItems() {
        List<MenuItem> tmpList = new ArrayList<>(items.size());
        for (MenuItem item : items.values()) {
            tmpList.add(item);
        }
        return tmpList;
    }

    public void open() {
        if (!options.isButtonHideOnSelect()) {
            show();
        }

        if (State.OPENED == getState()) {
            return;
        }
        setState(State.OPENED);
        button.setOpacity(1.0);
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(cross);
        rotate.setToAngle(45);
        rotate.setDuration(Duration.millis(200));
        rotate.setInterpolator(Interpolator.EASE_BOTH);
        rotate.play();
        openTimeLines[openTimeLines.length - 1].setOnFinished(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent actionEvent) {
                fireMenuEvent(new MenuEvent(this, null, MenuEvent.MENU_OPEN_FINISHED));
            }
        });
        for (int i = 0 ; i < openTimeLines.length ; i++) {
            openTimeLines[i].play();
        }
        fireMenuEvent(new MenuEvent(this, null, MenuEvent.MENU_OPEN_STARTED));
    }
    public void close() {
        if (State.CLOSED == getState()) return;

        setState(State.CLOSED);
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(cross);
        rotate.setToAngle(0);
        rotate.setDuration(Duration.millis(200));
        rotate.setInterpolator(Interpolator.EASE_BOTH);
        rotate.play();
        closeTimeLines[closeTimeLines.length - 1].setOnFinished(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent actionEvent) {
                FadeTransition buttonFadeOut = new FadeTransition();
                buttonFadeOut.setNode(button);
                buttonFadeOut.setDuration(Duration.millis(100));
                buttonFadeOut.setToValue(options.getButtonAlpha());
                buttonFadeOut.play();
                buttonFadeOut.setOnFinished(event -> {
                    if (options.isButtonHideOnClose()) hide();
                    fireMenuEvent(new MenuEvent(this, null, MenuEvent.MENU_CLOSE_FINISHED));
                });
            }
        });
        for (int i = 0 ; i < closeTimeLines.length ; i++) {
            closeTimeLines[i].play();
        }
        fireMenuEvent(new MenuEvent(this, null, MenuEvent.MENU_CLOSE_STARTED));
    }

    public void show() {
        if (options.isButtonHideOnSelect() && button.getOpacity() > 0) {
            return;
        }

        if (options.isButtonHideOnSelect() || button.getOpacity() == 0) {
            button.setScaleX(1.0);
            button.setScaleY(1.0);
            cross.setRotate(0);
            button.setRotate(0);

            FadeTransition buttonFadeIn = new FadeTransition();
            buttonFadeIn.setNode(button);
            buttonFadeIn.setDuration(Duration.millis(200));
            buttonFadeIn.setToValue(options.getButtonAlpha());
            buttonFadeIn.play();
        }
        for (Parent node : items.keySet()) {
            node.setScaleX(1.0);
            node.setScaleY(1.0);
            node.setTranslateX(0);
            node.setTranslateY(0);
            node.setRotate(0);
        }
    }
    public void hide() {
        setState(State.CLOSED);
        button.setOpacity(0.0);
        for (Parent node : items.keySet()) {
            node.setOpacity(0);
        }
    }

    public void click(final MenuItem CLICKED_ITEM) {
        List<Transition> transitions = new ArrayList<>(items.size() * 2);
        for (Parent node : items.keySet()) {
            if (items.get(node).equals(CLICKED_ITEM)) {
                // Add enlarge transition to selected item
                ScaleTransition enlargeItem = new ScaleTransition(Duration.millis(300), node);
                enlargeItem.setToX(5.0);
                enlargeItem.setToY(5.0);
                transitions.add(enlargeItem);
            } else {
                // Add shrink transition to all other items
                ScaleTransition shrinkItem = new ScaleTransition(Duration.millis(300), node);
                shrinkItem.setToX(0.0);
                shrinkItem.setToY(0.0);
                transitions.add(shrinkItem);
            }
            // Add fade out transition to every node
            FadeTransition fadeOutItem = new FadeTransition(Duration.millis(300), node);
            fadeOutItem.setToValue(0.0);
            transitions.add(fadeOutItem);
        }

        // Add rotate and fade transition to main menu button
        if (options.isButtonHideOnSelect()) {
            RotateTransition rotateMainButton = new RotateTransition(Duration.millis(300), button);
            rotateMainButton.setToAngle(225);
            transitions.add(rotateMainButton);
            FadeTransition fadeOutMainButton = new FadeTransition(Duration.millis(300), button);
            fadeOutMainButton.setToValue(0.0);
            transitions.add(fadeOutMainButton);
            ScaleTransition shrinkMainButton = new ScaleTransition(Duration.millis(300), button);
            shrinkMainButton.setToX(0.0);
            shrinkMainButton.setToY(0.0);
            transitions.add(shrinkMainButton);
        } else {
            RotateTransition rotateBackMainButton = new RotateTransition();
            rotateBackMainButton.setNode(cross);
            rotateBackMainButton.setToAngle(0);
            rotateBackMainButton.setDuration(Duration.millis(200));
            rotateBackMainButton.setInterpolator(Interpolator.EASE_BOTH);
            transitions.add(rotateBackMainButton);
            FadeTransition mainButtonFadeOut = new FadeTransition();
            mainButtonFadeOut.setNode(button);
            mainButtonFadeOut.setDuration(Duration.millis(100));
            mainButtonFadeOut.setToValue(options.getButtonAlpha());
            transitions.add(mainButtonFadeOut);
        }

        // Play all transitions in parallel
        ParallelTransition selectTransition = new ParallelTransition();
        selectTransition.getChildren().addAll(transitions);
        selectTransition.play();

        // Set menu state back to closed
        setState(State.CLOSED);
    }

    public void select(final MenuItem SELECTED_ITEM) {
        for (Parent node : items.keySet()) {
            if (items.get(node).equals(SELECTED_ITEM)) {
                for (Node child : node.getChildrenUnmodifiable()) {
                    if (child instanceof Circle) {
                        ((Circle) child).setFill(SELECTED_ITEM.isSelected() ? SELECTED_ITEM.getSelectedColor() : SELECTED_ITEM.getFillColor());
                    }
                }
            }
        }
    }

    private Circle createItemShape(final MenuItem ITEM, final Effect EFFECT) {
        Circle circle = new Circle(ITEM.getSize() * 0.5);
        //Region circle = new Region();
        //circle.setPrefSize(ITEM.getSize() * 0.5);
        //circle.getStyleClass().add("item");

        circle.setFill((ITEM.isSelected() && ITEM.isSelectable()) ? ITEM.getSelectedColor() : ITEM.getFillColor());
        circle.setStroke(ITEM.getStrokeColor());
        circle.setStrokeWidth(0.09375 * ITEM.getSize());
        circle.setStrokeType(StrokeType.CENTERED);
        circle.setEffect(EFFECT);
        return circle;
    }

    private ImageView createThumbnail(final MenuItem ITEM) {
        ImageView imageItem = new ImageView(new Image(ITEM.getThumbnailImageName()));
        imageItem.setFitWidth(ITEM.getSize() * 0.7);
        imageItem.setFitHeight(ITEM.getSize() * 0.7);
        imageItem.setMouseTransparent(true);

        return imageItem;
    }

    private Timeline createItemOpenTimeLine(final StackPane NODE, final double X, final double Y, final double DELAY) {
        KeyValue kvX1  = new KeyValue(NODE.translateXProperty(), 0, Interpolator.EASE_OUT);
        KeyValue kvY1  = new KeyValue(NODE.translateYProperty(), 0, Interpolator.EASE_OUT);
        KeyValue kvR1  = new KeyValue(NODE.rotateProperty(), 0, Interpolator.EASE_OUT);
        KeyValue kvO1  = new KeyValue(NODE.opacityProperty(), 0, Interpolator.EASE_OUT);

        KeyValue kvX2  = new KeyValue(NODE.translateXProperty(), 0.0);
        KeyValue kvY2  = new KeyValue(NODE.translateYProperty(), 0.0);

        KeyValue kvX3  = new KeyValue(NODE.translateXProperty(), 1.1 * X, Interpolator.EASE_IN);
        KeyValue kvY3  = new KeyValue(NODE.translateYProperty(), 1.1 * Y, Interpolator.EASE_IN);

        KeyValue kvX4  = new KeyValue(NODE.translateXProperty(), 0.95 * X, Interpolator.EASE_OUT);
        KeyValue kvY4  = new KeyValue(NODE.translateYProperty(), 0.95 * Y, Interpolator.EASE_OUT);
        KeyValue kvRO4 = new KeyValue(NODE.rotateProperty(), 360);
        KeyValue kvO4  = new KeyValue(NODE.opacityProperty(), 1.0, Interpolator.EASE_OUT);

        KeyValue kvX5  = new KeyValue(NODE.translateXProperty(), X);
        KeyValue kvY5  = new KeyValue(NODE.translateYProperty(), Y);

        KeyFrame kfO1  = new KeyFrame(Duration.millis(0), kvX1, kvY1, kvR1, kvO1);
        KeyFrame kfO2  = new KeyFrame(Duration.millis(50 + DELAY), kvX2, kvY2);
        KeyFrame kfO3  = new KeyFrame(Duration.millis(250 + DELAY), kvX3, kvY3);
        KeyFrame kfO4  = new KeyFrame(Duration.millis(400 + DELAY), kvX4, kvY4, kvRO4, kvO4);
        KeyFrame kfO5  = new KeyFrame(Duration.millis(600 + DELAY), kvX5, kvY5);

        return new Timeline(kfO1, kfO2, kfO3, kfO4, kfO5);
    }
    private Timeline createItemCloseTimeLine(final StackPane NODE, final double X, final double Y, final double DELAY) {
        KeyValue kvX1  = new KeyValue(NODE.translateXProperty(), 0, Interpolator.EASE_OUT);
        KeyValue kvY1  = new KeyValue(NODE.translateYProperty(), 0, Interpolator.EASE_OUT);
        KeyValue kvR1  = new KeyValue(NODE.rotateProperty(), 0, Interpolator.EASE_OUT);
        KeyValue kvO1  = new KeyValue(NODE.opacityProperty(), 0, Interpolator.EASE_OUT);

        KeyValue kvX2  = new KeyValue(NODE.translateXProperty(), 0.0);
        KeyValue kvY2  = new KeyValue(NODE.translateYProperty(), 0.0);

        KeyValue kvX3  = new KeyValue(NODE.translateXProperty(), 1.1 * X, Interpolator.EASE_IN);
        KeyValue kvY3  = new KeyValue(NODE.translateYProperty(), 1.1 * Y, Interpolator.EASE_IN);

        KeyValue kvX4  = new KeyValue(NODE.translateXProperty(), 0.95 * X, Interpolator.EASE_OUT);
        KeyValue kvY4  = new KeyValue(NODE.translateYProperty(), 0.95 * Y, Interpolator.EASE_OUT);
        KeyValue kvRC4 = new KeyValue(NODE.rotateProperty(), 720);
        KeyValue kvO4  = new KeyValue(NODE.opacityProperty(), 1.0, Interpolator.EASE_OUT);

        KeyValue kvX5  = new KeyValue(NODE.translateXProperty(), X);
        KeyValue kvY5  = new KeyValue(NODE.translateYProperty(), Y);

        KeyFrame kfC1  = new KeyFrame(Duration.millis(0), kvX5, kvY5);
        KeyFrame kfC2  = new KeyFrame(Duration.millis(50 + DELAY), kvX4, kvY4, kvRC4, kvO4);
        KeyFrame kfC3  = new KeyFrame(Duration.millis(250 + DELAY), kvX3, kvY3);
        KeyFrame kfC4  = new KeyFrame(Duration.millis(400 + DELAY), kvX2, kvY2);
        KeyFrame kfC5  = new KeyFrame(Duration.millis(600 + DELAY), kvX1, kvY1, kvR1, kvO1);

        return new Timeline(kfC1, kfC2, kfC3, kfC4, kfC5);
    }

    private void resize() {
        button.setLayoutX((getPrefWidth()) * 0.5);
        button.setLayoutY((getPrefHeight()) * 0.5);
        for (Parent node : items.keySet()) {
            node.setLayoutX((getPrefWidth() - node.getLayoutBounds().getWidth()) * 0.5);
            node.setLayoutY((getPrefHeight() - node.getLayoutBounds().getHeight()) * 0.5);
        }
    }


    // ******************** Event handling ************************************
    public final ObjectProperty<EventHandler<ItemEvent>> onItemClickedProperty() { return onItemClicked; }
    public final void setOnItemClicked(EventHandler<ItemEvent> value) { onItemClickedProperty().set(value); }
    public final EventHandler<ItemEvent> getOnItemClicked() { return onItemClickedProperty().get(); }
    private ObjectProperty<EventHandler<ItemEvent>> onItemClicked = new ObjectPropertyBase<EventHandler<ItemEvent>>() {
        @Override public Object getBean() { return this; }
        @Override public String getName() { return "onItemClicked";}
    };

    public final ObjectProperty<EventHandler<ItemEvent>> onItemSelectedProperty() { return onItemSelected; }
    public final void setOnItemSelected(EventHandler<ItemEvent> value) { onItemSelectedProperty().set(value); }
    public final EventHandler<ItemEvent> getOnItemSelected() { return onItemSelectedProperty().get(); }
    private ObjectProperty<EventHandler<ItemEvent>> onItemSelected = new ObjectPropertyBase<EventHandler<ItemEvent>>() {
        @Override public Object getBean() { return this; }
        @Override public String getName() { return "onItemSelected";}
    };

    public void fireItemEvent(final ItemEvent EVENT) {
        fireEvent(EVENT);

        final EventType               TYPE = EVENT.getEventType();
        final EventHandler<ItemEvent> HANDLER;

        if (ItemEvent.ITEM_CLICKED == TYPE) {
            HANDLER = getOnItemClicked();
        } else if (ItemEvent.ITEM_SELECTED == TYPE) {
            HANDLER = getOnItemSelected();
        } else {
            HANDLER = null;
        }

        if (HANDLER != null) {
            HANDLER.handle(EVENT);
        }
    }

    public final ObjectProperty<EventHandler<MenuEvent>> onMenuOpenStartedProperty() { return onMenuOpenStarted; }
    public final void setOnMenuOpenStarted(EventHandler<MenuEvent> value) { onMenuOpenStartedProperty().set(value); }
    public final EventHandler<MenuEvent> getOnMenuOpenStarted() { return onMenuOpenStartedProperty().get(); }
    private ObjectProperty<EventHandler<MenuEvent>> onMenuOpenStarted = new ObjectPropertyBase<EventHandler<MenuEvent>>() {
        @Override public Object getBean() { return this; }
        @Override public String getName() { return "onMenuOpenStarted";}
    };

    public final ObjectProperty<EventHandler<MenuEvent>> onMenuOpenFinishedProperty() { return onMenuOpenFinished; }
    public final void setOnMenuOpenFinished(EventHandler<MenuEvent> value) { onMenuOpenFinishedProperty().set(value); }
    public final EventHandler<MenuEvent> getOnMenuOpenFinished() { return onMenuOpenFinishedProperty().get(); }
    private ObjectProperty<EventHandler<MenuEvent>> onMenuOpenFinished = new ObjectPropertyBase<EventHandler<MenuEvent>>() {
        @Override public Object getBean() { return this; }
        @Override public String getName() { return "onMenuOpenFinished";}
    };

    public final ObjectProperty<EventHandler<MenuEvent>> onMenuCloseStartedProperty() { return onMenuCloseStarted; }
    public final void setOnMenuCloseStarted(EventHandler<MenuEvent> value) { onMenuCloseStartedProperty().set(value); }
    public final EventHandler<MenuEvent> getOnMenuCloseStarted() { return onMenuCloseStartedProperty().get(); }
    private ObjectProperty<EventHandler<MenuEvent>> onMenuCloseStarted = new ObjectPropertyBase<EventHandler<MenuEvent>>() {
        @Override public Object getBean() { return this; }
        @Override public String getName() { return "onMenuCloseStarted";}
    };

    public final ObjectProperty<EventHandler<MenuEvent>> onMenuCloseFinishedProperty() { return onMenuCloseFinished; }
    public final void setOnMenuCloseFinished(EventHandler<MenuEvent> value) { onMenuCloseFinishedProperty().set(value); }
    public final EventHandler<MenuEvent> getOnMenuCloseFinished() { return onMenuCloseFinishedProperty().get(); }
    private ObjectProperty<EventHandler<MenuEvent>> onMenuCloseFinished = new ObjectPropertyBase<EventHandler<MenuEvent>>() {
        @Override public Object getBean() { return this; }
        @Override public String getName() { return "onMenuCloseFinished";}
    };

    public void fireMenuEvent(final MenuEvent EVENT) {
        fireEvent(EVENT);

        final EventType               TYPE = EVENT.getEventType();
        final EventHandler<MenuEvent> HANDLER;

        if (MenuEvent.MENU_OPEN_STARTED == TYPE) {
            HANDLER = getOnMenuOpenStarted();
        } else if (MenuEvent.MENU_OPEN_FINISHED == TYPE) {
            HANDLER = getOnMenuOpenFinished();
        } else if (MenuEvent.MENU_CLOSE_STARTED == TYPE) {
            HANDLER = getOnMenuCloseStarted();
        } else if (MenuEvent.MENU_CLOSE_FINISHED == TYPE) {
            HANDLER = getOnMenuCloseFinished();
        } else {
            HANDLER = null;
        }

        if (HANDLER != null) {
            HANDLER.handle(EVENT);
        }
    }


    // ******************** Inner classes *************************************
    public static class ItemEvent extends Event {
        public static final EventType<ItemEvent> ITEM_CLICKED  = new EventType(ANY, "itemClicked");
        public static final EventType<ItemEvent> ITEM_SELECTED = new EventType(ANY, "itemSelected");

        public MenuItem item;


        // ******************** Constructors **********************************
        public ItemEvent(final MenuItem ITEM, final Object SOURCE, final EventTarget TARGET, final EventType<ItemEvent> EVENT_TYPE) {
            super(SOURCE, TARGET, EVENT_TYPE);
            item = ITEM;
        }

    }

    public static class MenuEvent extends Event {
        public static final EventType<MenuEvent> MENU_OPEN_STARTED   = new EventType(ANY, "menuOpenStarted");
        public static final EventType<MenuEvent> MENU_OPEN_FINISHED  = new EventType(ANY, "menuOpenFinished");
        public static final EventType<MenuEvent> MENU_CLOSE_STARTED  = new EventType(ANY, "menuCloseStarted");
        public static final EventType<MenuEvent> MENU_CLOSE_FINISHED = new EventType(ANY, "menuCloseFinished");

        // ******************** Constructors **********************************
        public MenuEvent(final Object SOURCE, final EventTarget TARGET, final EventType<MenuEvent> EVENT_TYPE) {
            super(SOURCE, TARGET, EVENT_TYPE);
        }
    }
}
