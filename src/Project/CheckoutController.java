package Project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingCartListener;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;

public class CheckoutController implements Initializable, ShoppingCartListener {



    @FXML private AnchorPane wiz1_Check; // The first page of the checkout wizard.
    @FXML private AnchorPane wiz2_Date; // The second page of the checkout wizard.
    @FXML private AnchorPane wiz3_Payment; // The third page of the checkout wizard.
    @FXML private AnchorPane wiz4_Confirm; // The fourth page of the checkout wizard.
    @FXML private AnchorPane Finish; // Last page of the checkout wizard. This page is shown after the checkout is finished.

    @FXML private Text Cost_text_1; // These texts should be updated with the complete cost of the purchase + "kr".
    @FXML private FlowPane itemPane;


    @FXML private Text Delivery_date_1; //These texts should be updated with the with the delivery date. The "Delivery_date_1" should also change colour to black when first updated with a date.
    @FXML private Text Delivery_date_2;
    @FXML private Text Delivery_date_3;


    //region date_wiz vars
    @FXML private Text this_month_text; //Should say the current month (ex "Mars")
    @FXML private Text next_month_text; //Should say next month (ex "April")
    @FXML private Text after_next_month_text; //Should say the month after the next month (ex "Maj")
    @FXML private Button remove_goBack;

    @FXML private AnchorPane this_month_pane;
    @FXML private AnchorPane next_month_pane;
    @FXML private AnchorPane after_next_month_pane;

    @FXML private ScrollPane calendar_pane; //Should be populated with CheckoutDate

    @FXML private StackPane choose_time_popup; //The popup where you choose the time of the delivery
    @FXML private ListView time_list; //This list should be populated with CheckoutTime.
    @FXML private Text day_of_times; //shows the current day where you are choosing the time the delivery, (ex: Mars 9)
    //endregion

    //region payment_wiz vars
    //Here is the payment information field. All information that we already have (from the profile) should be pre-written.
    @FXML private TextField first_name_field;
    @FXML private TextField surname_field;
    @FXML private TextField email_field;
    @FXML private TextField cardnumber_field;
    @FXML private TextField expirationdate_mm_field;
    @FXML private TextField expirationdate_yy_field;
    @FXML private TextField cvc_field;
    @FXML private TextField delivery_adress_field;
    @FXML private TextField city_field;
    @FXML private TextField postal_number_field;
    //endregion

    //region confirm_wiz vars
    @FXML private ScrollPane checkout_cart_confirm_pane; //Should be populated with CheckoutItemSmall
    @FXML private Text confirm_name; //Should display the full name of the buyer, (first name + surname)
    @FXML private Text confirm_adress; //should display the delivery address of the buyer
    @FXML private Text confirm_cardnumber; //Should display 8 stars and then the 4 last numbers of the credit card number. (ex: **** **** 1234)
    //endregion

    //region Finnish vars
    @FXML private Text wares_to_dest; //Should say the number of wares that is delivered to the destination (ex: 6st varor är påväg mot Kazakhstansgatan 13)
    @FXML private Text date_and_time; //should say the date and time the wares are delivered (ex: 10 mars kl 16:30)
    //endregion

    private List<AnchorPane> wizSteps;
    private List<CheckoutItemLarge> checkoutItemLarges;

    private int currentStep;
    private ParentController parentController;
    private ShoppingCart shoppingCart;
    private BackendControllerProducts bckEndP;
    private BackendControllerUserInfo bckEndU;
    private DecimalFormat df = new DecimalFormat("0.00");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bckEndP = BackendControllerProducts.getInstance();
        bckEndU = BackendControllerUserInfo.getInstance();
        parentController = ParentController.getInstance();

        shoppingCart = bckEndP.getShoppingCart();
        checkoutItemLarges = new CopyOnWriteArrayList<>();
        shoppingCart.addShoppingCartListener(this);

        wizSteps = new ArrayList<>();
        wizSteps.add(wiz1_Check);
        wizSteps.add(wiz2_Date);
        wizSteps.add(wiz3_Payment);
        wizSteps.add(wiz4_Confirm);
        wizSteps.add(Finish);
        currentStep = 0;
        wizSteps.get(currentStep).toFront();



    }

    private void updateList(){
        Cost_text_1.setText(String.valueOf(df.format(shoppingCart.getTotal())));
        itemPane.getChildren().clear();
        for (CheckoutItemLarge cl: checkoutItemLarges) {
            cl.update();
            itemPane.getChildren().add(cl);
        }
    }

    @FXML
    public void nextStep() {
        currentStep++;
        wizSteps.get(currentStep).toFront();
        remove_goBack.setText("Tillbaka");
    }

    @FXML
    public void goBackStep() {
        if (currentStep == 0 || currentStep == 4) {
            parentController.setCenterPage("StorePage");
            remove_goBack.setText("Avbryt");
            return;
        }
        currentStep--;
        wizSteps.get(currentStep).toFront();
        if (currentStep == 0) remove_goBack.setText("Avbryt");

    }

    @FXML
    public void backToStore(){
        parentController.setCenterPage("StorePage");
    }



    public void Remove_all_wares() //removes all wares from the cart
    {
    }

    //region date_wiz_methods
    private void SetLarge(AnchorPane month_pane) {
        month_pane.setPrefHeight(53);
        month_pane.setLayoutY(132);
        month_pane.setStyle("-fx-background-color: #EFEFEF");
    }

    private void SetSmall(AnchorPane month_pane) {

        month_pane.setPrefHeight(38);
        month_pane.setLayoutY(146);
        month_pane.setStyle("-fx-background-color: #FFD1D1");
    }

    public void SetMonthThis() // changes the displayed month to this month.
    {
        SetLarge(this_month_pane);
        SetSmall(next_month_pane);
        SetSmall(after_next_month_pane);
    }

    public void SetMonthNext() //changes the displayed month to next month;
    {
        SetSmall(this_month_pane);
        SetLarge(next_month_pane);
        SetSmall(after_next_month_pane);
    }

    public void SetMonthAfterNext() //changes the displayed month to the month after the next month;
    {
        SetSmall(this_month_pane);
        SetSmall(next_month_pane);
        SetLarge(after_next_month_pane);
    }

    //region choose_time_popup methods
    public void Change_day() {
        choose_time_popup.toBack();
    }

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        if (cartEvent.isAddEvent()){
            checkoutItemLarges.add(new CheckoutItemLarge(cartEvent.getShoppingItem()));
        } else if (checkoutItemLarges.size() > shoppingCart.getItems().size()){
            for (CheckoutItemLarge cl: checkoutItemLarges) {
                if (cartEvent.getShoppingItem().equals(cl.getShoppingItem())) {
                    checkoutItemLarges.remove(cl);
                }
            }
        }
        updateList();
    }
}
    //endregion

