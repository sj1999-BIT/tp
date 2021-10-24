package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import seedu.address.logic.Logic;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ModelInfo extends UiPart<Region> {
    @FXML
    VBox mainContainer;

    private static final String FXML = "ModelInfo.fxml";

    public ModelInfo(int val, String info) {
        super(FXML);
        mainContainer.setBackground(new Background(
                new BackgroundFill(Color.web("#383838"),  CornerRadii.EMPTY, Insets.EMPTY)));
        mainContainer.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0.5))));

        Text value = new Text(Integer.toString(val));
        value.setFont(Font.font(40));
        value.setFill(Color.color(0.3,1,0.6));
        value.setStyle("-fx-font-weight: bold");
        value.setTextAlignment(TextAlignment.CENTER);

        Text infoText = new Text(info);
        infoText.setFont(Font.font(15));
        infoText.setFill(Color.color(0,0.5,1));
        infoText.setTextAlignment(TextAlignment.CENTER);

        if (val < 0) {
            value.setText("Invalid");
            infoText.setText("Data");
        }

        mainContainer.getChildren().addAll(value, infoText);
    }
}
