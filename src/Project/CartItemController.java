package Project;

import javafx.beans.property.Property;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    //@FXML private Label CartItemCount;
    @FXML private AnchorPane pane;

    @FXML private Button plusButton;
    @FXML private Button minusButton;
    @FXML private TextField productCounter;

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
        CartItemPrice.setText(String.valueOf(product.getPrice()) + " kr/"+product.getUnitSuffix());
        // CartItemCount.setText(String.valueOf(shoppingItem.getAmount()));
        productCounter.setText(String.valueOf(shoppingItem.getAmount()));
        CartItemImage.setImage(bckEndP.getFXImage(product));

        productCounter.focusedProperty().addListener((observableValue, s, t1) -> {
            if (productCounter.getText().equals("")) {
                productCounter.setText(String.valueOf(shoppingItem.getAmount()));
            } else if (!productCounter.getText().matches("[0-9]+")) {
                productCounter.setText(String.valueOf(shoppingItem.getAmount()));
                System.out.println("Invalid number");
            } else if (productCounter.getText().equals("0")){
                shoppingItem.setAmount(1);
                removeItem();
            } else {
                shoppingItem.setAmount(Double.parseDouble(productCounter.getText()));
                shoppingCart.fireShoppingCartChanged(shoppingItem,false);

            }
        });

        productCounter.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)){
                    productCounter.getParent().requestFocus();
                }
            }
        });
    }
    @FXML
    public void decItem(){
        if (shoppingItem.getAmount() == 1) {
            removeItem();
            return;
        }
        shoppingItem.setAmount(shoppingItem.getAmount()-1);
        shoppingCart.fireShoppingCartChanged(shoppingItem,false);
    }

    private void removeItem(){
        shoppingItem.setAmount(0);
        shoppingCart.removeItem(shoppingItem);
    }

    @FXML
    public void addItem(){
        shoppingItem.setAmount(shoppingItem.getAmount()+1);
        shoppingCart.fireShoppingCartChanged(shoppingItem,false);
    }

    public void update(){
        //CartItemCount.setText(String.valueOf(shoppingItem.getAmount()) + " " + shoppingItem.getProduct().getUnitSuffix());
        productCounter.setText(String.valueOf(shoppingItem.getAmount()));
    }

    public ShoppingItem getShoppingItem() {
        return shoppingItem;
    }

    @FXML
    public void DeleteItem() {
        removeItem();
    }
}
