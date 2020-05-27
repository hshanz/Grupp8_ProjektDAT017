package Project;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import se.chalmers.cse.dat216.project.CreditCard;
import se.chalmers.cse.dat216.project.Customer;

import java.net.URL;
import java.util.ResourceBundle;

public class UserPageController implements Initializable {


    @FXML public TextField firstNameField;
    @FXML public TextField lastNameField;
    @FXML public TextField emailField;

    @FXML public TextField addressField;
    @FXML public TextField cityField;
    @FXML public TextField zipCodeField;

    @FXML public TextField cardNumberField;
    @FXML public TextField monthField;
    @FXML public TextField yearField;
    @FXML public TextField cvcField;

    @FXML private RadioButton visaButton;
    @FXML private RadioButton masterCardButton;


    public Button saveButton;

    private BackendControllerUserInfo bckEndU;
    private Customer customer;
    private CreditCard creditCard;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bckEndU = BackendControllerUserInfo.getInstance();
        customer = bckEndU.getCustomer();
        creditCard = bckEndU.getCreditCard();

        ToggleGroup group = new ToggleGroup();

        visaButton.setToggleGroup(group);
        masterCardButton.setToggleGroup(group);

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

        firstNameField.textProperty().addListener((observableValue, s, t1) -> customer.setFirstName(firstNameField.getText()));
        lastNameField.textProperty().addListener((observableValue, s, t1) -> customer.setLastName(lastNameField.getText()));
        emailField.textProperty().addListener((observableValue, s, t1) -> customer.setEmail(emailField.getText()));
        addressField.textProperty().addListener((observableValue, s, t1) -> customer.setAddress(addressField.getText()));
        cityField.textProperty().addListener((observableValue, s, t1) -> customer.setPostAddress(cityField.getText()));
        zipCodeField.textProperty().addListener((observableValue, s, t1) -> customer.setPostCode(zipCodeField.getText()));
        cardNumberField.textProperty().addListener((observableValue, s, t1) -> creditCard.setCardNumber(cardNumberField.getText()));

        monthField.textProperty().addListener((observableValue, s, t1) -> {
            if (monthField.getText().equals("")) {
                creditCard.setValidMonth(0);
            } else if (!monthField.getText().matches("[0-9]+")) {
                System.out.println("Invalid number");
            } else creditCard.setValidMonth(Integer.parseInt(monthField.getText()));
        });
        yearField.textProperty().addListener((observableValue, s, t1) -> {
            if (yearField.getText().equals("")) {
                creditCard.setValidYear(0);
            } else if (!yearField.getText().matches("[0-9]+")) {
                System.out.println("Invalid number");
            } else creditCard.setValidYear(Integer.parseInt(yearField.getText()));
        });
        cvcField.textProperty().addListener((observableValue, s, t1) -> {
            if (cvcField.getText().equals("")) {
                creditCard.setVerificationCode(0);
            } else if (!cvcField.getText().matches("[0-9]+")) {
                System.out.println("Invalid number");
            } else creditCard.setVerificationCode(Integer.parseInt(cvcField.getText()));
        });

    }

    @FXML
    public void save(){
        System.out.println(customer.getFirstName());
        System.out.println(customer.getLastName());
        System.out.println(customer.getEmail());
        System.out.println(customer.getAddress());
        System.out.println(customer.getPostCode());
        System.out.println(creditCard.getCardNumber());
        System.out.println(creditCard.getValidMonth());
        System.out.println(creditCard.getValidYear());
        System.out.println(creditCard.getVerificationCode());
    }


}
