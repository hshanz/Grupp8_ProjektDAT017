package Project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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
    @FXML Text Cost_text_4;

    @FXML Text Delivery_date_1; //These texts should be updated with the with the delivery date. The "Delivery_date_1" should also change colour to black when first updated with a date.
    @FXML Text Delivery_date_2;
    @FXML Text Delivery_date_3;

    @FXML Text this_month_text; //Should say the current month (ex "Mars")
    @FXML Text next_month_text; //Should say next month (ex "April")
    @FXML Text after_next_month_text; //Should say the month after the next month (ex "Maj")

    @FXML AnchorPane this_month_pane;
    @FXML AnchorPane next_month_pane;
    @FXML AnchorPane after_next_month_pane;

    //Here is the payment information field. All information that we already have (from the profile) should be pre-written.
    @FXML TextField first_name_field;
    @FXML TextField surname_field;
    @FXML TextField email_field;
    @FXML TextField cardnumber_field;
    @FXML TextField expirationdate_mm_field;
    @FXML TextField expirationdate_yy_field;
    @FXML TextField cvc_field;
    @FXML TextField delivery_adress_field;
    @FXML TextField city_field;
    @FXML TextField postal_number_field;

    @FXML Text confirm_name; //Should display the full name of the buyer, (first name + surname)
    @FXML Text confirm_adress; //should display the delivery address of the buyer
    @FXML Text confirm_cardnumber; //Should display 8 stars and then the 4 last numbers of the credit card number. (ex: **** **** 1234)



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

    //region date_wiz_methods
    private void SetLarge(AnchorPane month_pane)
    {
        month_pane.setPrefHeight(53);
        month_pane.setLayoutY(132);
        month_pane.setStyle("-fx-background-color: #F0F0F0");
    }

    private void SetSmall(AnchorPane month_pane)
    {

        month_pane.setPrefHeight(38);
        month_pane.setLayoutY(146);
        month_pane.setStyle("-fx-background-color: #E6E6E6");
    }

    public void SetMonthThis () // changes the displayed month to this month.
    {
        SetLarge(this_month_pane);
        SetSmall(next_month_pane);
        SetSmall(after_next_month_pane);
    }

    public void SetMonthNext () //changes the displayed month to next month;
    {
        SetSmall(this_month_pane);
        SetLarge(next_month_pane);
        SetSmall(after_next_month_pane);
    }

    public void SetMonthAfterNext () //changes the displayed month to the month after the next month;
    {
        SetSmall(this_month_pane);
        SetSmall(next_month_pane);
        SetLarge(after_next_month_pane);
    }
    //endregion

    //region Confirm_wiz_methods
    public void ConfirmAndBuy () //the method that actually confirms the purchase
    {

        ToFinish();
    }
}
