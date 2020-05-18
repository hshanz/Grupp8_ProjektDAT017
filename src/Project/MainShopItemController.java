package Project;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.net.URL;
import java.util.ResourceBundle;

public class MainShopItemController implements Initializable{

    @FXML private ImageView productImage;
    @FXML private Label productName;
    @FXML private Label productPrice;
    @FXML private Label productCount;

    ShoppingItem shoppingItem;
    Product p;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    private void addToCart(){
        ShoppingCart shoppingCart;

        if (shoppingItem == null){
            shoppingItem = new ShoppingItem(p);
        }
        shoppingItem.setAmount(shoppingItem.getAmount()+1);
    }

    private void removeFromCart(){
        shoppingItem.setAmount(shoppingItem.getAmount()-1);
    }

}
