package Project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class CartItemController extends AnchorPane {

    @FXML private ImageView CartItemImage;
    @FXML private Label CartItemName;
    @FXML private Label CartItemPrice;
    @FXML private Label CartItemCount;
    @FXML private AnchorPane pane;

    ShoppingItem shoppingItem;
    CartController parent;
    Product product;

    public CartItemController(ShoppingItem shoppingItem, CartController parent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CartItem.fxml"));
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }


        this.shoppingItem = shoppingItem;
        this.parent = parent;
        product = shoppingItem.getProduct();
        CartItemImage.setImage(this.parent.parentController.backendControllerProducts.getFXImage(product));
        CartItemName.setText("SSSSSSSSSSSS");
        CartItemPrice.setText("SSSSSSSSSSSSS");
        CartItemCount.setText("SSSSSSSSSSSSS");





    }


    public void incCartItemCount() {
        int temp = Integer.parseInt(CartItemCount.getText());
        temp++;
        CartItemCount.setText(String.valueOf(temp));
    }
}
