package Project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;

public class StorePageController implements Initializable {


    //Add items to this panel
    @FXML
    public FlowPane flowPane;

    @FXML
    public TextField searchField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Test Code

        /*flowPane.getChildren().add(new Button());
        searchField.setText("Hej hej!");*/

    }
}
