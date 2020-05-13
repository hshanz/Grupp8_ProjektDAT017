package Project;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class UserPageController implements Initializable {


    public TextField firstNameField;
    public TextField lastNameField;
    public TextField emailField;

    public TextField addressField;
    public TextField cityField;
    public TextField zipCodeField;

    public TextField cardNumberField;
    public TextField monthField;
    public TextField yearField;
    public TextField cvcField;


    public Button saveButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Test code

        /*firstNameField.setText("Förnamn");
        lastNameField.setText("Efternamn");
        emailField.setText("E-Post");

        addressField.setText("Adress");
        cityField.setText("Ort");
        zipCodeField.setText("Postnummer");

        cardNumberField.setText("Kortnummer");
        monthField.setText("03");
        yearField.setText("20");
        cvcField.setText("543");*/

    }
}
