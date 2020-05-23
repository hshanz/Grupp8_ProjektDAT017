package Project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingCartListener;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class CheckoutController implements Initializable, ShoppingCartListener {



    @FXML private AnchorPane wiz1_Check; // The first page of the checkout wizard.
    @FXML private AnchorPane wiz2_Date; // The second page of the checkout wizard.
    @FXML private AnchorPane wiz3_Payment; // The third page of the checkout wizard.
    @FXML private AnchorPane wiz4_Confirm; // The fourth page of the checkout wizard.
    @FXML private AnchorPane Finish; // Last page of the checkout wizard. This page is shown after the checkout is finished.

    @FXML private Text Cost_text_1; // These texts should be updated with the complete cost of the purchase + "kr".
    @FXML private FlowPane itemPane;
    @FXML private FlowPane dateFlowPane;
    @FXML private Button nextButton;
    @FXML private FlowPane smallItemPane;


    @FXML private Text Delivery_date_1; //These texts should be updated with the with the delivery date. The "Delivery_date_1" should also change colour to black when first updated with a date.
    @FXML private Text Delivery_date_2;
    @FXML private Text Delivery_date_3;

    @FXML private Button remove_goBack;


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
    @FXML private Text confirm_name; //Should display the full name of the buyer, (first name + surname)
    @FXML private Text confirm_adress; //should display the delivery address of the buyer
    @FXML private Text confirm_cardnumber; //Should display 8 stars and then the 4 last numbers of the credit card number. (ex: **** **** 1234)
    //endregion

    //region Finnish vars
    @FXML private Text wares_to_dest; //Should say the number of wares that is delivered to the destination (ex: 6st varor är påväg mot Kazakhstansgatan 13)
    @FXML private Text date_and_time; //should say the date and time the wares are delivered (ex: 10 mars kl 16:30)
    //endregion

    private List<AnchorPane> wizSteps;
    private List<CheckoutItemLarge> checkoutItemLargeList;
    private List<CheckoutItemSmall> checkoutItemSmallList;
    private List<DateTimeItemController> dateTimeItemList;

    private int currentStep;
    private ParentController parentController;
    private ShoppingCart shoppingCart;
    private BackendControllerProducts bckEndP;
    private BackendControllerUserInfo bckEndU;
    private String timeOfDelivery;
    private String dateOfDelivery;
    private Order order;
    private DecimalFormat df = new DecimalFormat("0.00");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bckEndP = BackendControllerProducts.getInstance();
        bckEndU = BackendControllerUserInfo.getInstance();
        parentController = ParentController.getInstance();
        parentController.setCheckoutController(this);

        shoppingCart = bckEndP.getShoppingCart();
        checkoutItemLargeList = new CopyOnWriteArrayList<>();
        checkoutItemSmallList = new CopyOnWriteArrayList<>();
        dateTimeItemList = new CopyOnWriteArrayList<>();
        shoppingCart.addShoppingCartListener(this);
        order = new Order();

        wizSteps = new ArrayList<>();
        wizSteps.add(wiz1_Check);
        wizSteps.add(wiz2_Date);
        wizSteps.add(wiz3_Payment);
        wizSteps.add(wiz4_Confirm);
        wizSteps.add(Finish);
        currentStep = 0;
        wizSteps.get(currentStep).toFront();
        fillDateList();

    }

    private void updateDateList(){
        dateFlowPane.getChildren().clear();
        for (DateTimeItemController dti:dateTimeItemList) {
            dateFlowPane.getChildren().add(dti);
        }
    }

    private void fillDateList(){
        for (int i = 1;i < 5 ; i++) {
            dateTimeItemList.add(new DateTimeItemController(i,this));
        }
        updateDateList();
    }

    private void updateList(){
        Cost_text_1.setText(String.valueOf(df.format(shoppingCart.getTotal())));
        itemPane.getChildren().clear();
        smallItemPane.getChildren().clear();
        for (CheckoutItemLarge cl: checkoutItemLargeList) {
            cl.update();
            itemPane.getChildren().add(cl);
        }
        for (CheckoutItemSmall cl: checkoutItemSmallList) {
            cl.update();
            smallItemPane.getChildren().add(cl);
        }
    }

    @FXML
    public void confirmButtonPressed(){
        order.setItems(shoppingCart.getItems());
        order.setDate(new Date());
        wares_to_dest.setText(order.getItems().size() + ":st varor är på väg till " + delivery_adress_field.getText() );

        shoppingCart.clear();
        Finish.toFront();
    }

    @FXML
    public void nextStep() {
        currentStep++;
        wizSteps.get(currentStep).toFront();
        remove_goBack.setText("Tillbaka");
        if (currentStep == 3) nextButton.setVisible(false);
    }

    @FXML
    public void goBackStep() {
        nextButton.setVisible(true);
        if (currentStep == 0) {
            shoppingCart.clear();
            return;
        }
        currentStep--;
        wizSteps.get(currentStep).toFront();
        if (currentStep == 0) remove_goBack.setText("Ta bort allt");

    }

    public void resetWizard(){
        Finish.toBack();
        currentStep=0;
        wizSteps.get(currentStep).toFront();
        nextButton.setVisible(true);
    }

    @FXML
    public void backToStore(){
        parentController.setCenterPage("StorePage");
    }



    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        if (cartEvent.isAddEvent()){
            checkoutItemLargeList.add(new CheckoutItemLarge(cartEvent.getShoppingItem()));
            checkoutItemSmallList.add(new CheckoutItemSmall(cartEvent.getShoppingItem()));
        } else if (checkoutItemLargeList.size() > shoppingCart.getItems().size()){
            if (shoppingCart.getItems().size() == 0){
                for (CheckoutItemLarge cl : checkoutItemLargeList) {
                    checkoutItemLargeList.remove(cl);
                }
                for (CheckoutItemSmall cl : checkoutItemSmallList) {
                    checkoutItemSmallList.remove(cl);
                }

            } else {
                for (CheckoutItemLarge cl : checkoutItemLargeList) {
                    if (cartEvent.getShoppingItem().equals(cl.getShoppingItem())) {
                        checkoutItemLargeList.remove(cl);
                    }
                }
                for (CheckoutItemSmall cl : checkoutItemSmallList) {
                    if (cartEvent.getShoppingItem().equals(cl.getShoppingItem())) {
                        checkoutItemSmallList.remove(cl);
                    }
                }
            }
        }
        updateList();
    }

    public void setDelivery(String timeOfDelivery,String dateOfDelivery) {
        this.timeOfDelivery = timeOfDelivery;
        this.dateOfDelivery = dateOfDelivery;
        System.out.println(dateOfDelivery);
        System.out.println(this.timeOfDelivery);

    }
}
    //endregion

