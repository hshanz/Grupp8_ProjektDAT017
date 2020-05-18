package Project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.net.URL;
import java.util.ResourceBundle;

public class HistoryItemController {

    @FXML private ImageView productImage;
    @FXML private Label productName;
    @FXML private Label historyCount;
    @FXML private Label historyPrice;
    @FXML private Label singleItem;
    @FXML private Label currentPrice;
    @FXML private Button addButton;
}
