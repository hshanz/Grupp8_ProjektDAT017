package Project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import se.chalmers.cse.dat216.project.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.EventHandler;
import java.net.URL;
import java.text.DecimalFormat;
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
    private DecimalFormat df = new DecimalFormat("0.00");


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bckEndP = BackendControllerProducts.getInstance();
        parentController = ParentController.getInstance();
        shoppingCart =bckEndP.getShoppingCart();
        cartItemList = new CopyOnWriteArrayList<>();
        shoppingCart.addShoppingCartListener(this);

        checkSaveditems();
        //Set Gap between products
        flowPane.setVgap(10);


    }

    private void checkSaveditems(){
        if (shoppingCart.getItems().size() !=0){
            for (ShoppingItem sci:shoppingCart.getItems()) {
                shoppingCart.fireShoppingCartChanged(sci,true);
            }
        }
    }

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        if (cartEvent.isAddEvent()){
            cartItemList.add(new CartItemController(cartEvent.getShoppingItem()));
        } else if (cartItemList.size() > shoppingCart.getItems().size()){
            if (shoppingCart.getItems().size() == 0){
                for (CartItemController c : cartItemList) {
                    cartItemList.remove(c);
                }
            }else {
                for (CartItemController c : cartItemList) {
                    if (cartEvent.getShoppingItem().equals(c.getShoppingItem())) {
                        cartItemList.remove(c);
                    }
                }
            }
        }
        updateList();
    }

    private void updateList(){
        priceLabel.setText("= " + df.format(shoppingCart.getTotal()) + " kr");
        flowPane.getChildren().clear();
        for (int i = cartItemList.size()-1; i > -1; i--) {
            cartItemList.get(i).update();
            flowPane.getChildren().add(cartItemList.get(i));
        }
    }

    @FXML
    public void checkout(){
        if (shoppingCart.getItems().size() == 0)return;
        parentController.setCenterPage("Checkout");
    }
}

