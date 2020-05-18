package Project;


import com.sun.media.jfxmediaimpl.platform.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainShopItemController extends AnchorPane {

    @FXML private ImageView productImage;
    @FXML private Label productName;
    @FXML private Label productPrice;
    @FXML private Label productCount;

    ShoppingItem shoppingItem;
    Product p;
    ParentController parentController = ParentController.getInstance();

    public MainShopItemController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainShopItem.fxml"));
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }



        productName.setText("asddddd");

        p = parentController.backendControllerProducts.getProduct(137);
       productImage.setImage(parentController.backendControllerProducts.getFXImage(p));

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
