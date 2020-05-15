package Project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class CartItemController implements Initializable{

    @FXML private ImageView CartItemImage;

    @FXML private Label CartItemName;

    @FXML private Label CartItemPrice;

    @FXML private Label CartItemCount;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
