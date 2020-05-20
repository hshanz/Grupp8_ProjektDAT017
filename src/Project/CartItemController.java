package Project;

import javafx.beans.property.Property;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CartItemController extends AnchorPane {

    @FXML private ImageView CartItemImage;
    @FXML private Label CartItemName;
    @FXML private Label CartItemPrice;
    @FXML private Label CartItemCount;
    @FXML private AnchorPane pane;

    private ShoppingCart shoppingCart;
    private ShoppingItem shoppingItem;
    private Product product;
    BackendControllerProducts bckEndP;
    public CartItemController(ShoppingItem shoppingItem) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CartItem.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        bckEndP = BackendControllerProducts.getInstance();
        shoppingCart = bckEndP.getShoppingCart();
        this.shoppingItem = shoppingItem;
        product = shoppingItem.getProduct();

        CartItemName.setText(product.getName());
        CartItemPrice.setText(String.valueOf(product.getPrice()));
        CartItemCount.setText(String.valueOf(shoppingItem.getAmount()));
        CartItemImage.setImage(bckEndP.getFXImage(product));
    }


    public void update(){
        CartItemCount.setText(String.valueOf(shoppingItem.getAmount()));
    }

    public ShoppingItem getShoppingItem() {
        return shoppingItem;
    }
}
