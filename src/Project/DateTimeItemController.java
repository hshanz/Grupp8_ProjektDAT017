package Project;

import com.sun.tools.javac.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Order;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class DateTimeItemController extends AnchorPane {

    @FXML private Label dateLabel;
    @FXML private Button timeButton1;
    @FXML private Button timeButton2;
    @FXML private Button timeButton3;

    Order order;
    Calendar calendar;
    CheckoutController parent;
    String [] weekdays = {"Söndag ","Måndag ","Tisdag ","Onsdag ","Torsdag ","Fredag ","Lördag "};


    public DateTimeItemController(int dayIndex, CheckoutController parent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dateTimeItem.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.parent = parent;
        calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR,dayIndex);

        dateLabel.setText(weekdays[calendar.get(Calendar.DAY_OF_WEEK)-1] + calendar.get(Calendar.DATE)+ "/" + (calendar.get(Calendar.MONTH)+1));


        timeButton1.setOnAction(event -> {
            onButtonPressed((Button) event.getSource());
            timeButton1.getStyleClass().add("normal-button-selected");
        });
        timeButton2.setOnAction(event -> {
            onButtonPressed((Button) event.getSource());
            timeButton2.getStyleClass().add("normal-button-selected");
        });
        timeButton3.setOnAction(event -> {
            onButtonPressed((Button) event.getSource());
            timeButton3.getStyleClass().add("normal-button-selected");
        });
    }

    public void resetButtonStyles ()
    {
        timeButton1.getStyleClass().remove("normal-button-selected");
        timeButton2.getStyleClass().remove("normal-button-selected");
        timeButton3.getStyleClass().remove("normal-button-selected");
    }

    private void onButtonPressed(Button button){
        parent.setDelivery(button.getText(),dateLabel.getText());
    }
}
