package Project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingCartListener;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.text.DecimalFormat;

public class CheckoutItemLarge extends AnchorPane {
    /* The checkout item large should populate the first step in the checkoutwizard.
     * It should be checkout item large for each type of ware in the cart.
     * The checkout item large should be placed in a list view on after the the other.
     * Reference image: https://www.figma.com/file/Y6tseIZ6XOMK9EmOdySxMK/IMat?node-id=0%3A1 Betalningswizard1*/

    @FXML private Text name_text; //Should show the name of the wares (ex "Köttfärslimpa")
    @FXML private Text cost_text; //Should show the cost of these wares (ex "120kr")
    @FXML private TextField number_of_wares; //Should show the number of wares (ex "3st"), This is can be edited
    private ShoppingItem shoppingItem;
    private ShoppingCart shoppingCart;
    private DecimalFormat df = new DecimalFormat("0.00");

    public CheckoutItemLarge(ShoppingItem sci) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Checkout_Item_Large.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        shoppingItem = sci;
        shoppingCart = BackendControllerProducts.getInstance().getShoppingCart();

        name_text.setText(shoppingItem.getProduct().getName());
        cost_text.setText(String.valueOf(shoppingItem.getProduct().getPrice()));
        number_of_wares.setText(String.valueOf(shoppingItem.getAmount()));

        number_of_wares.focusedProperty().addListener((observableValue, s, t1) -> {
            if (number_of_wares.getText().equals("")) {
                number_of_wares.setText(String.valueOf(shoppingItem.getAmount()));
            } else if (!number_of_wares.getText().matches("[0-9]+")) {
                number_of_wares.setText(String.valueOf(shoppingItem.getAmount()));
                System.out.println("Invalid number");
            } else if (number_of_wares.getText().equals("0")){
                shoppingCart.removeItem(shoppingItem);
            } else {
                shoppingItem.setAmount(Double.parseDouble(number_of_wares.getText()));
                shoppingCart.fireShoppingCartChanged(shoppingItem,false);
            }
        });
    }

    @FXML
    public void AddWare () {
        shoppingItem.setAmount(shoppingItem.getAmount()+1);
        shoppingCart.fireShoppingCartChanged(shoppingItem,false);
    } //Should add 1 ware from the cart

    @FXML
    public void RemoveWare () {
        shoppingItem.setAmount(shoppingItem.getAmount()-1);
        if (shoppingItem.getAmount() == 0){
            shoppingCart.removeItem(shoppingItem);
            return;
        }
        shoppingCart.fireShoppingCartChanged(shoppingItem,false);
    } //Should remove 1 ware from the cart

    public void update(){
        number_of_wares.setText(String.valueOf(shoppingItem.getAmount()));
        cost_text.setText(String.valueOf(df.format(shoppingItem.getAmount() * shoppingItem.getProduct().getPrice())));
    }

    public ShoppingItem getShoppingItem() {
        return shoppingItem;
    }
}
