package Project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HistoryItemController extends AnchorPane {

    @FXML private ImageView productImage;
    @FXML private Label productName;
    @FXML private Label historyCount;
    @FXML private Label historyPrice;
    @FXML private Label singleItem;
    @FXML private Label currentPrice;
    @FXML private Button addButton;

    private ShoppingItem shoppingItem;
    private BackendControllerProducts bckEndP;
    private ShoppingCart shoppingCart;

    public HistoryItemController(ShoppingItem shoppingItem) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HistoryItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }bckEndP = BackendControllerProducts.getInstance();
        shoppingCart = bckEndP.getShoppingCart();
        this.shoppingItem = shoppingItem;


        productImage.setImage(bckEndP.getFXImage(shoppingItem.getProduct()));
        productName.setText(shoppingItem.getProduct().getName());
        historyCount.setText(String.valueOf(shoppingItem.getAmount()) + " " + shoppingItem.getProduct().getUnitSuffix());
        historyPrice.setText(String.valueOf(shoppingItem.getTotal() + " kr"));
        singleItem.setText("1 " + shoppingItem.getProduct().getUnitSuffix());
        currentPrice.setText(String.valueOf(shoppingItem.getProduct().getPrice()) + " kr");
    }

    @FXML
    public void addItem(){

        System.out.println("444");
        ShoppingItem shoppingItemCopy = new ShoppingItem(shoppingItem.getProduct());

        for (ShoppingItem sci:shoppingCart.getItems()) {
            if (shoppingItemCopy.getProduct().equals(sci.getProduct())){
                sci.setAmount(sci.getAmount()+1);
                shoppingCart.fireShoppingCartChanged(sci,false);
                return;
            }
        }
        System.out.println("3333");
        shoppingItemCopy.setAmount(1);
        shoppingCart.addItem(shoppingItemCopy);

    }
}
