package eu.hansolo.enzo.radialmenu;

import javafx.animation.FadeTransition;
import javafx.animation.FadeTransitionBuilder;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.RotateTransitionBuilder;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.animation.Transition;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
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
public class RadialMenu extends Pane {
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
    private Group                           cross;
    private Circle                          mainMenuMouseCatcher;
    private boolean                         isDirty;
    private EventHandler<MouseEvent>        mouseHandler;


    // ******************** Constructors **************************************
    public RadialMenu(final Options OPTIONS, final MenuItem... ITEMS) {
        this(OPTIONS, Arrays.asList(ITEMS));
    }
    public RadialMenu(final Options OPTIONS, final List<MenuItem> ITEMS) {
        options               = OPTIONS;
        items                 = javafx.collections.FXCollections.observableHashMap();
        state                 = new SimpleObjectProperty<State>(State.CLOSED);
        degrees               = Math.max(Math.min(360, options.getDegrees()), 0);
        positions             = Double.compare(degrees, 360.0) == 0 ? ITEMS.size() : ITEMS.size() - 1;
        openTimeLines         = new Timeline[ITEMS.size()];
        closeTimeLines        = new Timeline[ITEMS.size()];
        button                = new Group();
        cross                 = new Group();
        isDirty               = true;
        mouseHandler          = new EventHandler<MouseEvent>() {
            @Override public void handle(final MouseEvent EVENT) {
                final Object SOURCE = EVENT.getSource();
                if (MouseEvent.MOUSE_CLICKED == EVENT.getEventType()) {
                    if(EVENT.getSource().equals(mainMenuMouseCatcher)) {
                        if (State.CLOSED == getState()) {
                            open();
                        } else {
                            close();
                        }
                    }
                } else if (MouseEvent.MOUSE_PRESSED == EVENT.getEventType()) {
                    if (SOURCE.equals(mainMenuMouseCatcher)) {
                        mainMenuMouseCatcher.setFill(Color.rgb(0, 0, 0, 0.5));
                    } else {
                        select(items.get(SOURCE));
                        fireItemEvent(new ItemEvent(items.get(SOURCE), this, null, ItemEvent.ITEM_SELECTED));
                    }
                } else if (MouseEvent.MOUSE_RELEASED == EVENT.getEventType()) {
                    if (EVENT.getSource().equals(mainMenuMouseCatcher)) {
                        mainMenuMouseCatcher.setFill(Color.TRANSPARENT);
                    }
                } else if (MouseEvent.MOUSE_ENTERED == EVENT.getEventType()) {
                    if (EVENT.getSource().equals(mainMenuMouseCatcher)) {

                    } else {

                    }

                }
            }
        };
        initMainButton();
        initMenuItems(ITEMS);
        initGraphics();
        registerListeners();
    }


    // ******************** Initialization ************************************
    private void initMainButton() {
        // Define main menu button
        final Point2D CENTER     = new Point2D(getPrefWidth() * 0.5, getPrefHeight() * 0.5);
        final Circle MAIN_BUTTON = new Circle(CENTER.getX(), CENTER.getY(), options.getButtonSize() * 0.5);
        MAIN_BUTTON.setFill(new LinearGradient(0, MAIN_BUTTON.getLayoutBounds().getMinY(),
                                               0, MAIN_BUTTON.getLayoutBounds().getMaxY(),
                                               false, CycleMethod.NO_CYCLE,
                                               new Stop(0.0, Color.hsb(options.getButtonInnerColor().getHue(), 0.80, 0.90)),
                                               new Stop(0.5, Color.hsb(options.getButtonInnerColor().getHue(), 0.75, 0.90)),
                                               new Stop(0.5, Color.hsb(options.getButtonInnerColor().getHue(), 0.85, 0.90)),
                                               new Stop(1.0, Color.hsb(options.getButtonInnerColor().getHue(), 0.90, 0.80))));
        MAIN_BUTTON.setStroke(options.getButtonFrameColor());
        MAIN_BUTTON.setStrokeWidth(0.0681818182 * options.getButtonSize());

        cross.getChildren().clear();
        final Line HOR = new Line(CENTER.getX() - (0.1818181818 * options.getButtonSize()), CENTER.getY(), CENTER.getX() + (0.1818181818 * options.getButtonSize()), CENTER.getY());
        final Line VER = new Line(CENTER.getX(), CENTER.getY() - (0.1818181818 * options.getButtonSize()), CENTER.getX(), CENTER.getY() + (0.1818181818 * options.getButtonSize()));
        HOR.setStrokeWidth(0.0909090909 * options.getButtonSize());
        VER.setStrokeWidth(0.0909090909 * options.getButtonSize());
        HOR.setStroke(Color.WHITE);
        VER.setStroke(Color.WHITE);
        cross.getChildren().addAll(HOR, VER);

        mainMenuMouseCatcher = new Circle(CENTER.getX(), CENTER.getY(), options.getButtonSize() * 0.5);
        mainMenuMouseCatcher.setFill(Color.TRANSPARENT);

        mainMenuMouseCatcher.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseHandler);
        mainMenuMouseCatcher.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseHandler);
        mainMenuMouseCatcher.addEventHandler(MouseEvent.MOUSE_RELEASED, mouseHandler);
        mainMenuMouseCatcher.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseHandler);

        final DropShadow SHADOW = new DropShadow();
        SHADOW.setRadius(0.1590909091 * options.getButtonSize());
        SHADOW.setColor(Color.rgb(0, 0, 0, 0.6));
        SHADOW.setBlurType(BlurType.GAUSSIAN);
        button.setEffect(SHADOW);

        button.setOpacity(options.isButtonVisible() ? options.getButtonAlpha() : 0.0);

        button.getChildren().setAll(MAIN_BUTTON, cross, mainMenuMouseCatcher);
    }

    private void initMenuItems(final List<MenuItem> ITEMS) {
        Map<Parent, MenuItem> itemMap = new HashMap<>(ITEMS.size());

        final DropShadow SHADOW = new DropShadow();
        SHADOW.setRadius(0.1590909091 * options.getButtonSize());
        SHADOW.setColor(Color.rgb(0, 0, 0, 0.6));
        SHADOW.setBlurType(BlurType.GAUSSIAN);

        for (int i = 0 ; i < ITEMS.size() ; i++) {
            MenuItem item = ITEMS.get(i);

            // Create graphical representation of each menu item
            final StackPane NODE = new StackPane();

            NODE.getChildren().add(createItemShape(item, SHADOW));

            if (Symbol.Type.NONE == item.getSymbol() && item.getThumbnailImageName().isEmpty()) {
                Text text = new Text(Integer.toString(i));
                text.setFont(Font.font("Verdana", FontWeight.BOLD, item.getSize() * 0.5));
                text.setFill(item.getForegroundColor());
                NODE.getChildren().add(text);
            } else if (!item.getThumbnailImageName().isEmpty()) {
                try {
                    NODE.getChildren().add(createCanvasThumbnail(item));
                } catch (IllegalArgumentException exception) {
                    Text text = new Text(Integer.toString(i));
                    text.setFont(Font.font("Verdana", FontWeight.BOLD, item.getSize() * 0.5));
                    text.setFill(item.getForegroundColor());
                    NODE.getChildren().add(text);
                }
            } else {
                Canvas symbol = SymbolCanvas.getSymbol(item.getSymbol(), 0.7 * item.getSize(), Color.WHITE);
                NODE.getChildren().add(symbol);
            }

            Circle itemMouseCatcher = new Circle(item.getSize() * 0.5);
            itemMouseCatcher.setFill(Color.TRANSPARENT);
            itemMouseCatcher.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseHandler);
            NODE.getChildren().add(itemMouseCatcher);
            NODE.setOpacity(0.0);

            // Add animations for each menu item
            double  degree    = (((degrees / positions) * i) + options.getOffset()) % 360;
            Point2D position  = new Point2D(Math.cos(Math.toRadians(degree)), Math.sin(Math.toRadians(degree)));
            double x          = Math.round(position.getX() * options.getRadius());
            double y          = Math.round(position.getY() * options.getRadius());
            double delay      = (200 / ITEMS.size()) * i;

            openTimeLines[i]  = createItemOpenTimeLine(NODE, x, y, delay);
            closeTimeLines[i] = createItemCloseTimeLine(NODE, x, y, delay);

            // Add mouse event handler to each item
            //NODE.setOnMouseEntered(mouseHandler);
            //NODE.setOnMouseClicked(mouseHandler);
            NODE.setOnMousePressed(mouseHandler);
            NODE.setOnMouseReleased(mouseHandler);

            // Add items and nodes to map
            itemMap.put(NODE, item);
        }
        items.putAll(itemMap);
    }

    private void initGraphics() {
        getChildren().setAll(items.keySet());
        getChildren().add(button);
    }

    private void registerListeners() {
        widthProperty().addListener((ov, oldWidth, newWidth) -> {
            if (oldWidth.doubleValue() != newWidth.doubleValue()) isDirty = true;
        });
        heightProperty().addListener((ov, oldHeight, newHeight) -> {
            if (oldHeight.doubleValue() != newHeight.doubleValue()) isDirty = true;
        });
    }


    // ******************** Methods *******************************************
    @Override public void layoutChildren() {
        super.layoutChildren();
        if (isDirty) {
            resize();
            isDirty = false;
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
        List<MenuItem> tmpList = new ArrayList<MenuItem>(items.size());
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
        RotateTransition rotate = RotateTransitionBuilder.create().node(cross).toAngle(45).duration(Duration.millis(200)).interpolator(Interpolator.EASE_BOTH).build();
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
        if (State.CLOSED == getState()) {
            return;
        }
        setState(State.CLOSED);
        RotateTransition rotate = RotateTransitionBuilder.create().node(cross).toAngle(0).duration(Duration.millis(200)).interpolator(Interpolator.EASE_BOTH).build();
        rotate.play();
        closeTimeLines[closeTimeLines.length - 1].setOnFinished(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent actionEvent) {
                FadeTransition buttonFadeOut = FadeTransitionBuilder.create().node(button).duration(Duration.millis(100)).toValue(options.getButtonAlpha()).build();
                buttonFadeOut.play();
                fireMenuEvent(new MenuEvent(this, null, MenuEvent.MENU_CLOSE_FINISHED));
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

            FadeTransition buttonFadeIn = FadeTransitionBuilder.create().node(button).duration(Duration.millis(200)).toValue(options.getButtonAlpha()).build();
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

    public void select(final MenuItem SELECTED_ITEM) {
        List<Transition> transitions = new ArrayList<Transition>(items.size() * 2);
        for (Parent node : items.keySet()) {
            if (items.get(node).equals(SELECTED_ITEM)) {
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
            RotateTransition rotateBackMainButton = RotateTransitionBuilder.create().node(cross).toAngle(0).duration(Duration.millis(200)).interpolator(Interpolator.EASE_BOTH).build();
            transitions.add(rotateBackMainButton);
            FadeTransition mainButtonFadeOut = FadeTransitionBuilder.create().node(button).duration(Duration.millis(100)).toValue(options.getButtonAlpha()).build();
            transitions.add(mainButtonFadeOut);
        }

        // Play all transitions in parallel
        ParallelTransition selectTransition = new ParallelTransition();
        selectTransition.getChildren().addAll(transitions);
        selectTransition.play();

        // Set menu state back to closed
        setState(State.CLOSED);
    }

    private Circle createItemShape(final MenuItem ITEM, final Effect EFFECT) {
        Circle circle = new Circle(ITEM.getSize() * 0.5);
        circle.setFill(ITEM.getInnerColor());
        circle.setStroke(ITEM.getFrameColor());
        circle.setStrokeWidth(0.09375 * ITEM.getSize());
        circle.setStrokeType(StrokeType.CENTERED);
        circle.setEffect(EFFECT);
        return circle;
    }

    private Canvas createCanvasThumbnail(final MenuItem ITEM) {
        final Image  THUMBNAIL = new Image(ITEM.getThumbnailImageName());
        final double SIZE      = THUMBNAIL.getWidth() > THUMBNAIL.getHeight() ? THUMBNAIL.getWidth() : THUMBNAIL.getHeight();
        final double SCALE     = (0.7 * ITEM.getSize()) / SIZE;

        Canvas canvasThumbnail = new Canvas(0.7 * ITEM.getSize(), 0.7 * ITEM.getSize());
        GraphicsContext ctx    = canvasThumbnail.getGraphicsContext2D();

        ctx.scale(SCALE, SCALE);
        ctx.drawImage(THUMBNAIL, 0, 0);

        return canvasThumbnail;
    }

    private Timeline createItemOpenTimeLine(final StackPane NODE, final double X, final double Y, final double DELAY) {
        KeyValue kvX1     = new KeyValue(NODE.translateXProperty(), 0, Interpolator.EASE_OUT);
        KeyValue kvY1     = new KeyValue(NODE.translateYProperty(), 0, Interpolator.EASE_OUT);
        KeyValue kvR1     = new KeyValue(NODE.rotateProperty(), 0, Interpolator.EASE_OUT);
        KeyValue kvO1     = new KeyValue(NODE.opacityProperty(), 0, Interpolator.EASE_OUT);

        KeyValue kvX2     = new KeyValue(NODE.translateXProperty(), 0.0);
        KeyValue kvY2     = new KeyValue(NODE.translateYProperty(), 0.0);

        KeyValue kvX3     = new KeyValue(NODE.translateXProperty(), 1.1 * X, Interpolator.EASE_IN);
        KeyValue kvY3     = new KeyValue(NODE.translateYProperty(), 1.1 * Y, Interpolator.EASE_IN);

        KeyValue kvX4     = new KeyValue(NODE.translateXProperty(), 0.95 * X, Interpolator.EASE_OUT);
        KeyValue kvY4     = new KeyValue(NODE.translateYProperty(), 0.95 * Y, Interpolator.EASE_OUT);
        KeyValue kvRO4    = new KeyValue(NODE.rotateProperty(), 360);
        KeyValue kvO4     = new KeyValue(NODE.opacityProperty(), 1.0, Interpolator.EASE_OUT);

        KeyValue kvX5     = new KeyValue(NODE.translateXProperty(), X);
        KeyValue kvY5     = new KeyValue(NODE.translateYProperty(), Y);

        KeyFrame kfO1     = new KeyFrame(Duration.millis(0), kvX1, kvY1, kvR1, kvO1);
        KeyFrame kfO2     = new KeyFrame(Duration.millis(50 + DELAY), kvX2, kvY2);
        KeyFrame kfO3     = new KeyFrame(Duration.millis(250 + DELAY), kvX3, kvY3);
        KeyFrame kfO4     = new KeyFrame(Duration.millis(400 + DELAY), kvX4, kvY4, kvRO4, kvO4);
        KeyFrame kfO5     = new KeyFrame(Duration.millis(600 + DELAY), kvX5, kvY5);

        return TimelineBuilder.create().keyFrames(kfO1, kfO2, kfO3, kfO4, kfO5).build();
    }
    private Timeline createItemCloseTimeLine(final StackPane NODE, final double X, final double Y, final double DELAY) {
        KeyValue kvX1     = new KeyValue(NODE.translateXProperty(), 0, Interpolator.EASE_OUT);
        KeyValue kvY1     = new KeyValue(NODE.translateYProperty(), 0, Interpolator.EASE_OUT);
        KeyValue kvR1     = new KeyValue(NODE.rotateProperty(), 0, Interpolator.EASE_OUT);
        KeyValue kvO1     = new KeyValue(NODE.opacityProperty(), 0, Interpolator.EASE_OUT);

        KeyValue kvX2     = new KeyValue(NODE.translateXProperty(), 0.0);
        KeyValue kvY2     = new KeyValue(NODE.translateYProperty(), 0.0);

        KeyValue kvX3     = new KeyValue(NODE.translateXProperty(), 1.1 * X, Interpolator.EASE_IN);
        KeyValue kvY3     = new KeyValue(NODE.translateYProperty(), 1.1 * Y, Interpolator.EASE_IN);

        KeyValue kvX4     = new KeyValue(NODE.translateXProperty(), 0.95 * X, Interpolator.EASE_OUT);
        KeyValue kvY4     = new KeyValue(NODE.translateYProperty(), 0.95 * Y, Interpolator.EASE_OUT);
        KeyValue kvRC4    = new KeyValue(NODE.rotateProperty(), 720);
        KeyValue kvO4     = new KeyValue(NODE.opacityProperty(), 1.0, Interpolator.EASE_OUT);

        KeyValue kvX5     = new KeyValue(NODE.translateXProperty(), X);
        KeyValue kvY5     = new KeyValue(NODE.translateYProperty(), Y);

        KeyFrame kfC1     = new KeyFrame(Duration.millis(0), kvX5, kvY5);
        KeyFrame kfC2     = new KeyFrame(Duration.millis(50 + DELAY), kvX4, kvY4, kvRC4, kvO4);
        KeyFrame kfC3     = new KeyFrame(Duration.millis(250 + DELAY), kvX3, kvY3);
        KeyFrame kfC4     = new KeyFrame(Duration.millis(400 + DELAY), kvX2, kvY2);
        KeyFrame kfC5     = new KeyFrame(Duration.millis(600 + DELAY), kvX1, kvY1, kvR1, kvO1);

        return TimelineBuilder.create().keyFrames(kfC1, kfC2, kfC3, kfC4, kfC5).build();
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
    public final ObjectProperty<EventHandler<ItemEvent>> onItemSelectedProperty() { return onItemSelected; }
    public final void setOnItemSelected(EventHandler<ItemEvent> value) { onItemSelectedProperty().set(value); }
    public final EventHandler<ItemEvent> getOnItemSelected() { return onItemSelectedProperty().get(); }
    private ObjectProperty<EventHandler<ItemEvent>> onItemSelected = new ObjectPropertyBase<EventHandler<ItemEvent>>() {
        @Override public Object getBean() { return this; }
        @Override public String getName() { return "onItemSelected";}
    };

    public void fireItemEvent(final ItemEvent EVENT) {
        final EventHandler<ItemEvent> HANDLER = getOnItemSelected();

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
        final EventType               TYPE    = EVENT.getEventType();
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
        public static final EventType<ItemEvent> ITEM_SELECTED = new EventType(ANY, "itemSelected");

        private MenuItem item;


        // ******************** Constructors **********************************
        public ItemEvent(final MenuItem ITEM, final Object SOURCE, final EventTarget TARGET, final EventType<ItemEvent> EVENT_TYPE) {
            super(SOURCE, TARGET, EVENT_TYPE);
            item = ITEM;
        }


        // ******************** Methods ***************************************
        public final MenuItem getItem() {
            return item;
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
