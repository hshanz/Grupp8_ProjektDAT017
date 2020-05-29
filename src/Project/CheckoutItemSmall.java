package Project;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.text.DecimalFormat;

public class CheckoutItemSmall extends AnchorPane {
    /* The checkout item small should populate the fourth step of the checkout wizard.
     * It should be checkout item large for each type of ware in the cart.
     * The checkout item small should be placed in a scroll pane where 2 items is besides each other and then it lists down. '
     * Reference image: https://www.figma.com/file/Y6tseIZ6XOMK9EmOdySxMK/IMat?node-id=0%3A1 Betalningswizard4*/

    @FXML private Text name_text; //Should show the name of the wares
    @FXML private Text cost_text; //Should show the cost of the wares
    @FXML private Text number_of_wares; //should show the number of these wares in the cart. This can also be edited
    private DecimalFormat df = new DecimalFormat("0.00");


    private ShoppingItem shoppingItem;
    private ShoppingCart shoppingCart;
    private BackendControllerProducts bckEndp;

    public CheckoutItemSmall(ShoppingItem shoppingItem) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CheckoutItemSmall.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        bckEndp = BackendControllerProducts.getInstance();
        shoppingCart = bckEndp.getShoppingCart();


        this.shoppingItem = shoppingItem;
        name_text.setText(shoppingItem.getProduct().getName());

    }

    public ShoppingItem getShoppingItem() {
        return shoppingItem;
    }

    public void update(){
        number_of_wares.setText(String.valueOf(shoppingItem.getAmount()));
        cost_text.setText(String.valueOf(df.format(shoppingItem.getAmount() * shoppingItem.getProduct().getPrice())));
    }
}
