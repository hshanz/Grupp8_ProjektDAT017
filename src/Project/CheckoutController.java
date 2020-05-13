package Project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class CheckoutController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML StackPane wiz1_Check; // The first page of the checkout wizard.
    @FXML StackPane wiz2_Date; // The second page of the checkout wizard.
    @FXML StackPane wiz3_Payment; // The third page of the checkout wizard.
    @FXML StackPane wiz4_Confirm; // The fourth page of the checkout wizard.
    @FXML StackPane Finish; // Last page of the checkout wizard. This page is shown after the checkout is finished.

    @FXML Text Cost_text_1; // These texts should be updated with the complete cost of the purchase + "kr".
    @FXML Text Cost_text_2;
    @FXML Text Cost_text_3;

    @FXML Text Delivery_date_1; //These texts should be updated with the with the delivery date. The "Delivery_date_1" should also change colour to black when first updated with a date.
    @FXML Text Delivery_date_2;

    @FXML Text this_month_text; //Should say the current month (ex "Mars")
    @FXML Text next_month_text; //Should say next month (ex "April")
    @FXML Text after_next_month_text; //Should say the month after the next month (ex "Maj")

    //region Change stackpane methods. (might need improvement)
    public void ToWiz1()
    {
        wiz1_Check.toFront();
    }

    public void ToWiz2()
    {
        wiz2_Date.toFront();
    }
    public void ToWiz3()
    {
        wiz3_Payment.toFront();
    }
    public void ToWiz4()
    {
        wiz4_Confirm.toFront();
    }
    public void ToFinish()
    {
        Finish.toFront();
    }
    //endregion

    public void EscapeCheckout() //an escape hatch for the checkout.
    {}

    public void Remove_all_wares () //removes all wares from the cart
    {}
}
