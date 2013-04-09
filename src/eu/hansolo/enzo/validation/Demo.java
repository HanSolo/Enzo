package eu.hansolo.enzo.validation;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFieldBuilder;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleButtonBuilder;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.StackPaneBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * Created by
 * User: hansolo
 * Date: 09.04.13
 * Time: 19:15
 */
public class Demo extends Application {
    private ValidationPane            validationPane;
    private TextField                 field1;
    private TextField                 field2;
    private ToggleButton              button1;
    private ToggleButton              button2;
    private EventHandler<ActionEvent> handler;


    @Override public void init() {
        handler = actionEvent -> {
            ToggleButton SRC = (ToggleButton) actionEvent.getSource();
            if (SRC.equals(button1)) {
                if (button1.isSelected()) {
                    button1.setText("valid");
                    validationPane.setValid(field1, true);
                } else {
                    button1.setText("invalid");
                    validationPane.setValid(field1, false);
                }
            } else if (SRC.equals(button2)) {
                if (button2.isSelected()) {
                    button2.setText("valid");
                    validationPane.setValid(field2, true);
                } else {
                    button2.setText("invalid");
                    validationPane.setValid(field2, false);
                }
            }
        };

        field1  = TextFieldBuilder.create().promptText("text1").build();
        field2  = TextFieldBuilder.create().promptText("text2").build();

        button1 = ToggleButtonBuilder.create().text("invalid").onAction(handler).build();
        button2 = ToggleButtonBuilder.create().text("invalid").onAction(handler).build();

        validationPane = new ValidationPane();
        validationPane.add(field1);
        validationPane.add(field2);

        // React on Validation events fired by the ValidationPane
        validationPane.setOnValid(validationEvent -> {
            System.out.println(validationEvent.getNode() + " is now valid");
        });
        validationPane.setOnInvalid(validationEvent -> {
            System.out.println(validationEvent.getNode() + " is now invalid");
        });
    }

    @Override public void start(Stage stage) {
        HBox row1 = HBoxBuilder.create()
                               .spacing(10)
                               .children(new Label("Label 1"), field1, button1)
                               .build();
        HBox row2 = HBoxBuilder.create()
                               .spacing(10)
                               .children(new Label("Label 2"), field2, button2)
                               .build();

        VBox col = VBoxBuilder.create()
                              .spacing(20)
                              .padding(new Insets(10, 10, 10, 10))
                              .children(row1, row2)
                              .build();

        StackPane pane = StackPaneBuilder.create()
                                         .children(col, validationPane)
                                         .build();

        Scene scene = new Scene(pane, Color.DARKGRAY);

        stage.setTitle("Validation overlay");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
