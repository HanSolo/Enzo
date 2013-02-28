package eu.hansolo.enzo.qlocktwo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Demo extends Application {
    private QlockTwo german;
    private QlockTwo english;
    private QlockTwo french;
    private QlockTwo spanish;
    private QlockTwo dutch;


    @Override public void init() {
        german  = QlockTwoBuilder.create().language(QlockTwo.Language.GERMAN).color(QlockTwo.QlockColor.BLACK_ICE_TEA).build();
        english = QlockTwoBuilder.create().language(QlockTwo.Language.ENGLISH).color(QlockTwo.QlockColor.BLUE_CANDY).build();
        french  = QlockTwoBuilder.create().language(QlockTwo.Language.FRENCH).color(QlockTwo.QlockColor.CHERRY_CAKE).build();
        spanish = QlockTwoBuilder.create().language(QlockTwo.Language.SPANISH).color(QlockTwo.QlockColor.FROZEN_BLACKBERRY).build();
        dutch   = QlockTwoBuilder.create().language(QlockTwo.Language.DUTCH).color(QlockTwo.QlockColor.LIME_JUICE).build();
    }

    @Override public void start(Stage stage) {
        HBox pane = new HBox();
        pane.setSpacing(10);
        pane.getChildren().setAll(german, english, french, spanish, dutch);

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


