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



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        backendControllerProducts = new BackendControllerProducts();






        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                backendControllerProducts.shutDown();
            }
        }));


    }
}
