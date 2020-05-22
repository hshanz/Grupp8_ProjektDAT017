package Project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import se.chalmers.cse.dat216.project.*;

import java.awt.*;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CartController extends AnchorPane implements Initializable,ShoppingCartListener {



    //Add items to this one (it is inside a scroll pane)
    @FXML private FlowPane flowPane;
    @FXML private Label cartLabel;
    @FXML private Button checkoutButton;
    @FXML private Label priceLabel;



    private BackendControllerProducts bckEndP;
    private ParentController parentController;
    ShoppingCart shoppingCart;
    List <CartItemController> cartItemList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bckEndP = BackendControllerProducts.getInstance();
        parentController = ParentController.getInstance();
        shoppingCart =bckEndP.getShoppingCart();
        cartItemList = new CopyOnWriteArrayList<>();
        shoppingCart.addShoppingCartListener(this);

        //Set Gap between products
        flowPane.setVgap(10);


    }

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        if (cartEvent.isAddEvent()){
            cartItemList.add(new CartItemController(cartEvent.getShoppingItem()));
        } else if (cartItemList.size() > shoppingCart.getItems().size()){
            for (CartItemController c : cartItemList) {
                if (cartEvent.getShoppingItem().equals(c.getShoppingItem())) {
                    cartItemList.remove(c);
                }
            }
        }
        updateList();
    }

    private void updateList(){
        priceLabel.setText("= " + String.valueOf(shoppingCart.getTotal()) + " kr");
        flowPane.getChildren().clear();
        for (CartItemController i:cartItemList) {
            i.update();
            flowPane.getChildren().add(i);
        }
    }

    @FXML
    public void checkout(){
        parentController.setCenterPage("Checkout");
    }
}

