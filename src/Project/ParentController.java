package Project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ParentController implements Initializable {
    BackendControllerProducts backendControllerProducts;
    int i;

    @FXML Label usernameLabel;
    @FXML Button button;

    @FXML
    public void onClick(){
        i++;
        backendControllerProducts.setCustomer(String.valueOf(i));
        usernameLabel.setText(backendControllerProducts.getCustomer());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backendControllerProducts = new BackendControllerProducts();
        i= Integer.parseInt(backendControllerProducts.getCustomer());
        backendControllerProducts.setCustomer(String.valueOf(i));
        usernameLabel.setText(backendControllerProducts.getCustomer());

        Runtime.getRuntime().addShutdownHook(new Thread(() -> backendControllerProducts.iM.shutDown()));

    }
}
