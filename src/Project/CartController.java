package Project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.*;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CartController implements ShoppingCartListener {



    //Add items to this one (it is inside a scroll pane)
    @FXML private FlowPane flowPane;
    @FXML private Label cartLabel;
    @FXML private Button checkoutButton;
    @FXML private Label priceLabel;

    ParentController parentController;
    ShoppingCart shoppingCart;
    List <CartItemController> cartItemList = new ArrayList<>();

    public CartController() {
        parentController = ParentController.getInstance();
        shoppingCart = parentController.backendControllerProducts.getShoppingCart();

    }


    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        if (cartEvent.isAddEvent()){
            addItemTocart(cartEvent.getShoppingItem());
        }

    }

    private void addItemTocart(ShoppingItem sci){
        boolean itemAldredyInCart = false;
        for (ShoppingItem s: shoppingCart.getItems()) {
            if (sci.equals(s)){
                itemAldredyInCart = true;
                break;
            }
        }
        if (itemAldredyInCart){
            sci.setAmount(sci.getAmount()+1);
        } else {
            cartItemList.add(new CartItemController(sci,this));
        }
    }
}
