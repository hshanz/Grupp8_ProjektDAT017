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

public class CartController extends AnchorPane implements Initializable {



    //Add items to this one (it is inside a scroll pane)
    @FXML private FlowPane flowPane;
    @FXML private Label cartLabel;
    @FXML private Button checkoutButton;
    @FXML private Label priceLabel;

    private Button button = new Button("button");

    ParentController parentController;
    ShoppingCart shoppingCart;
    List <CartItemController> cartItemList = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        parentController = ParentController.getInstance();
        Product product = parentController.backendControllerProducts.getProduct(137);

        ShoppingItem shoppingItem = new ShoppingItem(product);

        flowPane.getChildren().add(new CartItemController(shoppingItem,this));

        flowPane.getChildren().add(button);



    }



    }

