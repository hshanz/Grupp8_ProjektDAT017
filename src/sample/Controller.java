package sample;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    BackendController backendController;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backendController = new BackendController();

    }
}
