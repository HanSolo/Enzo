package eu.hansolo.enzo.gauge;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;


/**
 * Created by
 * User: hansolo
 * Date: 17.07.13
 * Time: 08:27
 */

public class DemoRadialBargraph extends Application {
    private static final Random RND       = new Random();
    private static int          noOfNodes = 0;
    private RadialBargraph      control;
    private long                lastTimerCall;
    private AnimationTimer      timer;


    @Override public void init() {
        control = RadialBargraphBuilder.create()
                                       .title("Enzo")
                                       .unit("°C")
                                       .markers(new Marker(40))
                                       .sections(new Section(60, 80))
                                       .thresholdVisible(true)
                                       .build();

        lastTimerCall = System.nanoTime() + 2_000_000_000l;
        timer = new AnimationTimer() {
            @Override public void handle(long now) {
                if (now > lastTimerCall + 5_000_000_000l) {
                    control.setValue(RND.nextDouble() * 100);
                    lastTimerCall = now;
                }
            }
        };
    }

    @Override public void start(Stage stage) throws Exception {
        StackPane pane = new StackPane();
        pane.setBackground(null);
        pane.setPadding(new Insets(5, 5, 5, 5));
        pane.getChildren().addAll(control);

        Scene scene = new Scene(pane, Color.WHITE);

        //scene.setFullScreen(true);

        stage.setTitle("Title");
        stage.setScene(scene);
        stage.show();

        timer.start();

        calcNoOfNodes(scene.getRoot());
        System.out.println(noOfNodes + " Nodes in SceneGraph");
    }

    @Override public void stop() {

    }

    public static void main(final String[] args) {
        Application.launch(args);
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