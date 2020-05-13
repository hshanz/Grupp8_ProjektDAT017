package Project;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainShopItemController implements Initializable{

    @FXML private ImageView productImage;

    @FXML private Label productName;

    @FXML private Label productPrice;

    @FXML private Label productCount;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
