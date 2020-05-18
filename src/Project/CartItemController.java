package Project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.net.URL;
import java.util.ResourceBundle;

public class CartItemController extends AnchorPane {

    @FXML private ImageView CartItemImage;
    @FXML private Label CartItemName;
    @FXML private Label CartItemPrice;
    @FXML private Label CartItemCount;

    ShoppingItem shoppingItem;
    CartController parent;

    public CartItemController(ShoppingItem shoppingItem, CartController parent) {
        this.shoppingItem = shoppingItem;
        this.parent = parent;




    }
}
