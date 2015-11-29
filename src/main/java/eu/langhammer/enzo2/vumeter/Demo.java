package eu.langhammer.enzo2.vumeter;/**
 * User: hansolo
 * Date: 04.11.13
 * Time: 10:39
 */

import eu.langhammer.enzo2.common.SectionBuilder;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;


public class Demo extends Application {
    private static       int    noOfNodes = 0;
    private static final Random RND       = new Random();
    private VuMeter             control;
    private long                lastTimerCall;
    private AnimationTimer      timer;

    @Override public void init() {
        control = VuMeterBuilder.create()
                                .noOfLeds(25)
                                .peakValueVisible(true)
                                //.interactive(true)
                                .orientation(Orientation.HORIZONTAL)
                                .sections(SectionBuilder.create()
                                                        .start(0)
                                                        .stop(0.6)
                                                        .styleClass("led-section-0")
                                                        .build(),
                                          SectionBuilder.create()
                                                        .start(0.6)
                                                        .stop(0.8)
                                                        .styleClass("led-section-1")
                                                        .build(),
                                          SectionBuilder.create()
                                                        .start(0.8)
                                                        .stop(1.0)
                                                        .styleClass("led-section-2")
                                                        .build())
                                .build();        
        lastTimerCall = System.nanoTime();
        timer = new AnimationTimer() {
            @Override public void handle(final long NOW) {
                if (NOW > lastTimerCall + 100_000_000l) {
                    control.setValue(RND.nextDouble());                    
                    lastTimerCall = NOW;
                }
            }
        };
    }

    @Override public void start(Stage stage) {
        StackPane pane = new StackPane();
        pane.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.getChildren().add(control);

        Scene scene = new Scene(pane);

        stage.setTitle("VuMeter Demo");
        stage.setScene(scene);
        stage.show();

        timer.start();
        
        calcNoOfNodes(scene.getRoot());
        System.out.println(noOfNodes + " Nodes in SceneGraph");
    }

    @Override public void stop() {

    }

    public static void main(String[] args) {
        launch(args);
    }

    // ******************** Misc **********************************************
    private static void calcNoOfNodes(Node node) {
        if (node instanceof Parent) {
            if (((Parent) node).getChildrenUnmodifiable().size() != 0) {
                ObservableList<Node> tempChildren = ((Parent) node).getChildrenUnmodifiable();
                noOfNodes += tempChildren.size();
                for (Node n : tempChildren) {
                    calcNoOfNodes(n);
                    //System.out.println(n.getStyleClass().toString());
                }
            }
        }
    }
}
