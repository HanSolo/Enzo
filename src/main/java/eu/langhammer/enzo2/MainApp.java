package eu.langhammer.enzo2;

import eu.langhammer.enzo2.led.Led;
import eu.langhammer.enzo2.ledbargraph.LedBargraph;
import eu.langhammer.enzo2.ledbargraph.LedBargraphBuilder;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.ObservableList;

import static javafx.application.Application.launch;

import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class MainApp extends Application {

    private static int noOfNodes  = 0;
    private static final Random RND = new Random();
    private LedBargraph         control;
    private long                lastTimerCall;
    private AnimationTimer      timer;
    
    @Override
    public void start(Stage stage) throws Exception {
        StackPane pane = new StackPane();

        pane.getChildren().setAll(control);

        Scene scene = new Scene(pane, 400, 400, Color.rgb(0, 0, 0, 0.8));

        stage.setTitle("Led Bargraph demo");
        stage.setScene(scene);
        stage.show();

        timer.start();

        calcNoOfNodes(scene.getRoot());
        System.out.println(noOfNodes + " Nodes in SceneGraph");
    }

    
    
    
    
    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
                control = LedBargraphBuilder.create()
                                    .ledType(Led.LedType.HORIZONTAL)
                                    .orientation(Orientation.VERTICAL)
                                    .peakValueVisible(true)
                                    .ledSize(32)
                                    .build();
        lastTimerCall = System.nanoTime();
        timer = new AnimationTimer() {
            @Override public void handle(final long NOW) {
                if (NOW > lastTimerCall + 100_000_000l) {
                    control.setValue(RND.nextDouble());
                    lastTimerCall = NOW;
                }
            }
        }; }

    
    
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
