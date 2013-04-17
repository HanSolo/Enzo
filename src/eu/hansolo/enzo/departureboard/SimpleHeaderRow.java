package eu.hansolo.enzo.departureboard;

import javafx.geometry.VPos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


/**
 * Created by
 * User: hansolo
 * Date: 17.04.13
 * Time: 15:33
 */
public class SimpleHeaderRow extends HBox {

    // ******************** Constructors **************************************
    public SimpleHeaderRow() {
        Font font = Font.font("sans-serif", 20);

        Rectangle spacer1 = new Rectangle(0, 0, 60, 10);
        spacer1.setOpacity(0.0);

        Text timeLabel = new Text("Zeit");
        timeLabel.setFill(Color.WHITE);
        timeLabel.setTextAlignment(TextAlignment.LEFT);
        timeLabel.setTextOrigin(VPos.BOTTOM);
        timeLabel.setFont(font);

        Rectangle spacer2 = new Rectangle(0, 0, 105, 10);
        spacer2.setOpacity(0.0);

        Text destinationLabel = new Text("Ziel");
        destinationLabel.setFill(Color.WHITE);
        destinationLabel.setTextAlignment(TextAlignment.LEFT);
        destinationLabel.setTextOrigin(VPos.BOTTOM);
        destinationLabel.setFont(font);

        Rectangle spacer3 = new Rectangle(0, 0, 583, 10);
        spacer3.setOpacity(0.0);

        Text trackLabel = new Text("Gleis");
        trackLabel.setFill(Color.WHITE);
        trackLabel.setTextAlignment(TextAlignment.LEFT);
        trackLabel.setTextOrigin(VPos.BOTTOM);
        trackLabel.setFont(font);

        getChildren().addAll(spacer1, timeLabel, spacer2, destinationLabel, spacer3, trackLabel);
    }
}