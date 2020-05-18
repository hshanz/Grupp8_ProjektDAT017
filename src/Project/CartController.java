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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class CartController extends AnchorPane implements ShoppingCartListener,Initializable {



    //Add items to this one (it is inside a scroll pane)
    @FXML private VBox flowPane;
    @FXML private Label cartLabel;
    @FXML private Button checkoutButton;
    @FXML private Label priceLabel;

    private Button button = new Button("button");

    ParentController parentController;
    ShoppingCart shoppingCart;
    List <CartItemController> cartItemList = new ArrayList<>();

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        System.out.println("sadasdasdjjjjjj");
        System.out.println(cartEvent.isAddEvent());
        if (cartEvent.isAddEvent()){
            System.out.println("111");
            addItemTocart(cartEvent.getShoppingItem());
        }

    }

    private void addItemTocart(ShoppingItem sci){
        boolean itemAldredyInCart = false;
        for (ShoppingItem s: shoppingCart.getItems()) {
            if (sci.equals(s)){
                System.out.println("222");
                itemAldredyInCart = true;
                break;
            }
        }
        System.out.println("5555");
        if (itemAldredyInCart){
            sci.setAmount(sci.getAmount()+1);
        } else {
            System.out.println("llllllll");
           // cartItemList.add(new CartItemController(sci,this));
            //flowPane.getChildren().add(cartItemList.get(0));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        parentController = ParentController.getInstance();
        shoppingCart = parentController.backendControllerProducts.getShoppingCart();
        Product product = parentController.backendControllerProducts.getProduct(137);
        shoppingCart.addShoppingCartListener(this);

        ShoppingItem shoppingItem = new ShoppingItem(product);

        flowPane.getChildren().add(new CartItemController(shoppingItem,this));
        flowPane.getChildren().add(button);

        //flowPane.getChildren().add(new CartItemController(shoppingItem,this));


    }



    public void addItem(Product product){
        for (int i = 0; i < cartItemList.size(); i++){
            if (product.equals(cartItemList.get(i).product)){
                cartItemList.get(i).incCartItemCount();
                return;
            }
        }

    }

    }

