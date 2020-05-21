package Project;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CheckoutDate {
    /* Should populate the second step of the checkout wizard.
     * The color of the button should change depending of:
     * If the date has already been.
     * If the date is chosen as delivery date.
     * If the date is a red day.
     * The buttons should be distributed in a grid-like pattern with 7 columns and about 5 rows.
     * Se reference: https://www.figma.com/file/Y6tseIZ6XOMK9EmOdySxMK/IMat?node-id=0%3A1, Betalningswizard2 and Betalningswizard2chosen*/

    @FXML Button date_button; //Should show a date.

    private boolean hasBeen;

    public void ChoseThisDay () //Should set this day as the chosen delivery date.
    {
        if(!hasBeen)
        {
            date_button.setStyle("-fx-background-color: #3cdc3c");
            //TODO
        }
    }

    public void SetHasBeen() //Call this function if the date has already been.
    {
        hasBeen = true;
        date_button.setStyle("-fx-background-color: #888888");
    }

    public void SetIsRedDay() //Call this function if this day is a red day
    {
        date_button.setStyle("-fx-background-color: #ffd1d1");
    }

    public void DeselectThisDay () //call this function if this day was the chosen delivery day but now it's not
    {
        date_button.setStyle("-fx-background-color: #dddddd");
    }
}
