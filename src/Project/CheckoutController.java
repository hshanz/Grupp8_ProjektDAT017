package Project;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.*;

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

    @FXML
    private AnchorPane cancelPopUp;

    @FXML private Text Cost_text_1; // These texts should be updated with the complete cost of the purchase + "kr".
    @FXML private FlowPane itemPane;
    @FXML private FlowPane dateFlowPane;
    @FXML private Button nextButton;
    @FXML private FlowPane smallItemPane;


    @FXML private Text Delivery_date_1; //These texts should be updated with the with the delivery date. The "Delivery_date_1" should also change colour to black when first updated with a date.
    @FXML private Text Delivery;

    @FXML private Button remove_goBack;

    @FXML private ScrollPane scrollPane1;


    //region payment_wiz vars
    //Here is the payment information field. All information that we already have (from the profile) should be pre-written.
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField emailField;
    @FXML private TextField cardNumberField;
    @FXML private TextField monthField;
    @FXML private TextField yearField;
    @FXML private TextField cvcField;
    @FXML private TextField addressField;
    @FXML private TextField cityField;
    @FXML private TextField zipCodeField;

    @FXML private RadioButton visaButton;
    @FXML private RadioButton masterCardButton;

    @FXML private Text save_text;

    //endregion

    //region confirm_wiz vars
    @FXML private Text confirm_name; //Should display the full name of the buyer, (first name + surname)
    @FXML private Text confirm_adress; //should display the delivery address of the buyer
    @FXML private Text confirm_cardnumber; //Should display 8 stars and then the 4 last numbers of the credit card number. (ex: **** **** 1234)
    @FXML private ScrollPane checkout_cart_confirm_pane;
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
    private CreditCard creditCard;
    private Customer customer;
    private DecimalFormat df = new DecimalFormat("0.00");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bckEndP = BackendControllerProducts.getInstance();
        bckEndU = BackendControllerUserInfo.getInstance();
        parentController = ParentController.getInstance();
        parentController.setCheckoutController(this);
        creditCard = bckEndU.getCreditCard();
        customer = bckEndU.getCustomer();

        shoppingCart = bckEndP.getShoppingCart();
        checkoutItemLargeList = new CopyOnWriteArrayList<>();
        checkoutItemSmallList = new CopyOnWriteArrayList<>();
        dateTimeItemList = new CopyOnWriteArrayList<>();
        shoppingCart.addShoppingCartListener(this);
        DeselectSaveText();

        wizSteps = new ArrayList<>();
        wizSteps.add(wiz1_Check);
        wizSteps.add(wiz2_Date);
        wizSteps.add(wiz3_Payment);
        wizSteps.add(wiz4_Confirm);
        wizSteps.add(Finish);
        fillDateList();
        resetWizard();

        ToggleGroup group = new ToggleGroup();

        visaButton.setToggleGroup(group);
        masterCardButton.setToggleGroup(group);
        getSelectedCardType();

        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                if (group.getSelectedToggle() != null){
                    RadioButton button = (RadioButton) group.getSelectedToggle();
                    if (button.equals(visaButton)){
                        creditCard.setCardType("Visa");
                    }else {
                        creditCard.setCardType("MasterCard");
                    }
                }
            }
        });

        updateTextFeilds();

        firstNameField.textProperty().addListener((observableValue, s, t1) -> {
            customer.setFirstName(firstNameField.getText());
            updateButton();
        });
        lastNameField.textProperty().addListener((observableValue, s, t1) -> {
            customer.setLastName(lastNameField.getText());
            updateButton();
        });
        emailField.textProperty().addListener((observableValue, s, t1) -> {
            customer.setEmail(emailField.getText());
            updateButton();
        });
        addressField.textProperty().addListener((observableValue, s, t1) -> {
            customer.setAddress(addressField.getText());
            updateButton();
        });
        cityField.textProperty().addListener((observableValue, s, t1) -> {
            customer.setPostAddress(cityField.getText());
            updateButton();
        });
        zipCodeField.textProperty().addListener((observableValue, s, t1) -> {
            customer.setPostCode(zipCodeField.getText());
            updateButton();
        });
        cardNumberField.textProperty().addListener((observableValue, s, t1) -> {
            creditCard.setCardNumber(cardNumberField.getText());
            updateButton();
        });

        monthField.textProperty().addListener((observableValue, s, t1) -> {
            if (monthField.getText().equals("")) {
                creditCard.setValidMonth(0);
            } else if (!monthField.getText().matches("[0-9]+")) {
                System.out.println("Invalid number");
                monthField.setText(String.valueOf(0));
            } else creditCard.setValidMonth(Integer.parseInt(monthField.getText()));
            updateButton();
        });
        yearField.textProperty().addListener((observableValue, s, t1) -> {
            if (yearField.getText().equals("")) {
                creditCard.setValidYear(0);
            } else if (!yearField.getText().matches("[0-9]+")) {
                System.out.println("Invalid number");
                yearField.setText(String.valueOf(0));
            } else creditCard.setValidYear(Integer.parseInt(yearField.getText()));
            updateButton();
        });
        cvcField.textProperty().addListener((observableValue, s, t1) -> {
            if (cvcField.getText().equals("")) {
                creditCard.setVerificationCode(0);
            } else if (!cvcField.getText().matches("[0-9]+")) {
                System.out.println("Invalid number");
                cvcField.setText(String.valueOf(0));
            } else creditCard.setVerificationCode(Integer.parseInt(cvcField.getText()));
            updateButton();
        });

        firstNameField.getParent().getParent().getParent().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)){
                    firstNameField.getParent().getParent().getParent().requestFocus();
                }
                updateButton();
            }
        });

        scrollPane1.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        checkout_cart_confirm_pane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }

    private void updateButton() {
        if (infoCheck()){
            nextButton.getStyleClass().remove("important-button-disabled");
        } else nextButton.getStyleClass().add("important-button-disabled");
    }

    private void updateDateList(){
        dateFlowPane.getChildren().clear();
        for (DateTimeItemController dti:dateTimeItemList) {
            dateFlowPane.getChildren().add(dti);
        }
    }

    private void updateTextFeilds(){
        firstNameField.setText(customer.getFirstName());
        lastNameField.setText(customer.getLastName());
        emailField.setText(customer.getEmail());
        addressField.setText(customer.getAddress());
        zipCodeField.setText(customer.getPostCode());
        cityField.setText(customer.getPostCode());
        cardNumberField.setText(creditCard.getCardNumber());
        monthField.setText(String.valueOf(creditCard.getValidMonth()));
        yearField.setText(String.valueOf(creditCard.getValidYear()));
        cvcField.setText(String.valueOf(creditCard.getVerificationCode()));
        getSelectedCardType();
    }

    private void fillDateList(){
        for (int i = 1;i < 5 ; i++) {
            dateTimeItemList.add(new DateTimeItemController(i,this));
        }
        updateDateList();
    }

    private void updateList(){
        Cost_text_1.setText(String.valueOf(df.format(shoppingCart.getTotal()) + " kr"));
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

    private boolean infoCheck(){
       return bckEndU.isCustomerComplete() && creditCardCheck();
    }

    private boolean creditCardCheck(){
        if (creditCard.getCardType().equals(""))return false;
        if (creditCard.getCardNumber().equals(""))return false;
        if (creditCard.getValidMonth() == 0 || String.valueOf(creditCard.getValidMonth()).toCharArray().length > 2) return false;
        if (creditCard.getValidYear() == 0 || String.valueOf(creditCard.getValidYear()).toCharArray().length > 2) return false;
        if (creditCard.getVerificationCode() == 0 || String.valueOf(creditCard.getVerificationCode()).toCharArray().length > 3) return false;
        return true;
    }

    @FXML
    public void confirmButtonPressed(){
        Order order = bckEndP.placeOrder(false);
        makeCopys(order);
        System.out.println(order.getItems().get(0).getAmount());
        order.setDate(new Date());
        order.setOrderNumber(bckEndP.getOrders().size()+1);
        wares_to_dest.setText(order.getItems().size() + ":st varor är på väg till " + addressField.getText() );
        date_and_time.setText(dateOfDelivery +" kl: "+timeOfDelivery);
        parentController.addNewOrder(order);

        for (ShoppingItem sci:shoppingCart.getItems()) {
            sci.setAmount(0);
            shoppingCart.fireShoppingCartChanged(sci,false);
        }
        System.out.println(order.getItems().get(0).getAmount());

        shoppingCart.clear();
        Finish.toFront();
        System.out.println(order.getItems().get(0).getAmount());
    }

    public void makeCopys(Order order){
        List<ShoppingItem> orderList = new ArrayList<>();
        for (ShoppingItem sci: shoppingCart.getItems()){
            orderList.add(new ShoppingItem(sci.getProduct(),sci.getAmount()));
        }
        order.setItems(orderList);
    }



    @FXML
    public void nextStep() {
        DeselectSaveText();
        if (currentStep == 2 && !infoCheck())return;
        if (currentStep == 1 && dateOfDelivery == null) return;
        currentStep++;
        wizSteps.get(currentStep).toFront();
        remove_goBack.setText("Tillbaka");
        sanityChecks();
    }

    private void sanityChecks(){

        if (currentStep == 1 && dateOfDelivery == null){
            Delivery_date_1.setVisible(true);
            Delivery.setVisible(true);
            nextButton.getStyleClass().add("important-button-disabled");
        }
        if (currentStep == 2 && !infoCheck())
        {
            nextButton.getStyleClass().add("important-button-disabled");
        }
        if (currentStep == 3) {
            nextButton.setVisible(false);
            confirm_adress.setText(customer.getAddress());
            confirm_cardnumber.setText(creditCard.getCardNumber());
            confirm_name.setText(customer.getFirstName() + " " + customer.getLastName());
        }
    }

    @FXML
    void cancel(){
        cancelPopUp.toFront();
    }

    @FXML
    void No_dont_cancel() {
       cancelPopUp.toBack();
    }

    @FXML
    void Yes_cancel() {
        cancelPopUp.toBack();
        backToStore();
    }

    @FXML
    public void goBackStep() {
        DeselectSaveText();
        if (currentStep == 0) {
            cancel();
            return;
        }
        nextButton.setVisible(true);
        currentStep--;
        wizSteps.get(currentStep).toFront();
        nextButton.getStyleClass().remove("important-button-disabled");
    }

    public void resetWizard(){
        DeselectSaveText();
        updateTextFeilds();
        for(DateTimeItemController d:dateTimeItemList)
        {
            d.resetButtonStyles();
        }
        Finish.toBack();
        Delivery_date_1.setFill(Color.RED);
        Delivery_date_1.setText("Ej valt");
        Delivery_date_1.setVisible(false);
        Delivery.setVisible(false);
        timeOfDelivery = null;
        dateOfDelivery = null;
        currentStep=0;
        wizSteps.get(currentStep).toFront();
        nextButton.setVisible(true);
        nextButton.getStyleClass().remove("important-button-disabled");
    }


    @FXML
    public void backToStore(){
        DeselectSaveText();
        parentController.toolbarController.onClickShop();
    }


    @FXML
    public void toHistory () {
        DeselectSaveText();
        parentController.toolbarController.onClickHistory();
    }

    private void getSelectedCardType() {
        if ("Visa".equals(creditCard.getCardType())){
            visaButton.setSelected(true);
        } else if (creditCard.getCardType().equals("MasterCard")){
            masterCardButton.setSelected(true);
        }
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

        Delivery_date_1.setText(this.dateOfDelivery + "\n kl:" + this.timeOfDelivery );
        Delivery_date_1.setFill(Color.BLACK);
        //nextButton.setVisible(true);
        nextButton.getStyleClass().remove("important-button-disabled");

        for(DateTimeItemController d:dateTimeItemList) {
            d.resetButtonStyles();
        }
    }

    @FXML
    public void SaveUserInfo ()
    {
        save_text.setOpacity(1);
    }

    public void DeselectSaveText ()
    {
        save_text.setOpacity(0);
    }
}
    //endregion

