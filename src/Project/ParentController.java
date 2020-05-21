package Project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.net.URL;
import java.util.ResourceBundle;

public class ParentController implements Initializable {
    BackendControllerProducts backendControllerProducts;
    PaneLoader paneLoader;
    BackendControllerUserInfo backendControllerUserInfo;
    static ParentController parentController;

    @FXML BorderPane mainPane;
    private Pane toolBar;
    private Pane categoryList;
    private Pane cart;
    private Pane checkout;
    private Pane storePage;
    private Pane userPage;

    public static ParentController getInstance(){
        return parentController;
    }


    public void setCenterPage(String str){
        switch (str) {
            case "StorePage":
                mainPane.setCenter(storePage);
                mainPane.setLeft(categoryList);
                mainPane.setRight(cart);
                break;
            case "UserPage":
                mainPane.setCenter(userPage);
                mainPane.setLeft(categoryList);
                mainPane.setRight(cart);
                break;
            case "Checkout":
                mainPane.setRight(null);
                mainPane.setLeft(null);
                mainPane.setCenter(checkout);
                break;
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        parentController = this;
        backendControllerProducts = new BackendControllerProducts();
        backendControllerUserInfo = new BackendControllerUserInfo();
        paneLoader = new PaneLoader();

        toolBar = paneLoader.LoadPane("Toolbar.fxml");
        categoryList = paneLoader.LoadPane("CategoryList.fxml");
        cart = paneLoader.LoadPane("Cart.fxml");
        storePage = paneLoader.LoadPane("StorePage.fxml");
        userPage = paneLoader.LoadPane("UserPage.fxml");
        checkout = paneLoader.LoadPane("Checkout.fxml");

        mainPane.setTop(toolBar);
        mainPane.setLeft(categoryList);
        mainPane.setRight(cart);
        mainPane.setCenter(storePage);









        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                backendControllerProducts.shutDown();
            }
        }));


    }
}
