package Project;


import com.sun.media.jfxmediaimpl.platform.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import se.chalmers.cse.dat216.project.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainShopItemController extends AnchorPane implements ShoppingCartListener {

    @FXML private ImageView productImage;
    @FXML private Label productName;
    @FXML private Label productPrice;
    @FXML private TextField productCounter;
    @FXML private Label priceUnit;


    private ShoppingItem shoppingItem;
    private ShoppingCart shoppingCart;
    private Product p;
    private BackendControllerProducts bckEndP;

    public MainShopItemController(Product p) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainShopItem.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        bckEndP = BackendControllerProducts.getInstance();
        shoppingCart = bckEndP.getShoppingCart();
        shoppingCart.addShoppingCartListener(this);
        this.p = p;
        shoppingItem = new ShoppingItem(p,0);
        checkSaveditems();

        productName.setText(p.getName());
        productImage.setImage(bckEndP.getFXImage(p));
        productPrice.setText(String.valueOf(p.getPrice()) + " kr/" + shoppingItem.getProduct().getUnitSuffix());
        productCounter.setText("0.0");
        priceUnit.setText(shoppingItem.getProduct().getUnitSuffix() + ":");

        productCounter.focusedProperty().addListener((observableValue, s, t1) -> {
            if (productCounter.getText().equals("")) {
                productCounter.setText(String.valueOf(shoppingItem.getAmount()));
            } else if (!productCounter.getText().matches("[0-9]+")) {
                productCounter.setText(String.valueOf(shoppingItem.getAmount()));
                System.out.println("Invalid number");
            } else if (productCounter.getText().equals("0")){
                shoppingItem.setAmount(1);
                removeFromCart();
            } else {
                shoppingItem.setAmount(Double.parseDouble(productCounter.getText()));
                if (!isInCart(shoppingItem)){
                    shoppingCart.addItem(shoppingItem);
                }
                shoppingCart.fireShoppingCartChanged(shoppingItem,false);

            }
        });

    }

    private void checkSaveditems(){
        for (ShoppingItem sci:shoppingCart.getItems()) {
                if (sci.getProduct().equals(p)){
                    shoppingItem = sci;
                    productCounter.setText(String.valueOf(shoppingItem.getAmount()));
                }
        }

    }

    @FXML
    public void addToCart(){
        if (!isInCart(shoppingItem) ){
            shoppingItem.setAmount(1);
            shoppingCart.addItem(shoppingItem);
        }else {
            shoppingItem.setAmount(shoppingItem.getAmount() + 1);
            shoppingCart.fireShoppingCartChanged(null, false);
        }
        productCounter.setText(String.valueOf(shoppingItem.getAmount()));
    }

    @FXML
    public void removeFromCart(){
        if (shoppingItem.getAmount() == 0) return;
        shoppingItem.setAmount(shoppingItem.getAmount()-1);
        if (shoppingItem.getAmount() == 0){
            shoppingCart.removeItem(shoppingItem);
        }
        shoppingCart.fireShoppingCartChanged(null, false);
        productCounter.setText(String.valueOf(shoppingItem.getAmount()));
    }

    private boolean isInCart(ShoppingItem sci){
        for (ShoppingItem i:shoppingCart.getItems()) {
            if (i.equals(sci))return true;
        }
        return false;
    }

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        if (cartEvent.isAddEvent() && cartEvent.getShoppingItem().getProduct().equals(p)){
            shoppingItem = cartEvent.getShoppingItem();
        }
        productCounter.setText(String.valueOf(shoppingItem.getAmount()) + " " + shoppingItem.getProduct().getUnitSuffix());
    }
}
