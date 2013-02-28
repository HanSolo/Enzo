package eu.hansolo.enzo.qlocktwo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Demo extends Application {
    private QlockTwo german;
    private QlockTwo english;
    private QlockTwo french;
    private QlockTwo spanish;
    private QlockTwo dutch;


    @Override public void init() {
        german  = QlockTwoBuilder.create().prefWidth(300).prefHeight(300).language(QlockTwo.Language.GERMAN).color(QlockTwo.QlockColor.BLACK_ICE_TEA).build();
        english = QlockTwoBuilder.create().prefWidth(300).prefHeight(300).language(QlockTwo.Language.ENGLISH).color(QlockTwo.QlockColor.BLUE_CANDY).build();
        french  = QlockTwoBuilder.create().prefWidth(300).prefHeight(300).language(QlockTwo.Language.FRENCH).color(QlockTwo.QlockColor.CHERRY_CAKE).build();
        spanish = QlockTwoBuilder.create().prefWidth(300).prefHeight(300).language(QlockTwo.Language.SPANISH).color(QlockTwo.QlockColor.FROZEN_BLACKBERRY).build();
        dutch   = QlockTwoBuilder.create().prefWidth(300).prefHeight(300).language(QlockTwo.Language.DUTCH).color(QlockTwo.QlockColor.LIME_JUICE).build();
    }

    @Override public void start(Stage stage) {
        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);
        pane.add(german, 0, 0);
        pane.add(english, 1, 0);
        pane.add(french, 2, 0);
        pane.add(spanish, 0, 1);
        pane.add(dutch, 1, 1);

        Scene scene = new Scene(pane, Color.DARKGRAY);

        stage.setTitle("JavaFX QlockTwo");
        stage.setScene(scene);
        stage.show();
    }

    @Override public void stop() {
        Platform.exit();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}


