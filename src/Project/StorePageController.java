package Project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StorePageController extends AnchorPane implements Initializable {


    //Add items to this panel
    @FXML
    public FlowPane flowPane;

    @FXML
    public TextField searchField;

    @FXML
    GridPane grid;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StorePage.fxml"));
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }


        grid.add(new MainShopItemController(),0,0);


        //flowPane.getChildren().add(new MainShopItemController());

        //Test Code

        /*flowPane.getChildren().add(new Button());
        searchField.setText("Hej hej!");*/
    }
}
