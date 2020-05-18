package Project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.net.URL;
import java.util.ResourceBundle;

public class ParentController implements Initializable {
    BackendControllerProducts backendControllerProducts;
    PaneLoader paneLoader;
    static ParentController parentController;

    @FXML BorderPane mainPane;

    public static ParentController getInstance(){
        return parentController;
    }


    public void setCenterPage(String str){
        mainPane.setCenter(paneLoader.LoadPane(str));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        parentController = this;
        backendControllerProducts = new BackendControllerProducts();
        paneLoader = new PaneLoader();
        mainPane.setTop(paneLoader.LoadPane("Toolbar.fxml"));
        mainPane.setLeft(paneLoader.LoadPane("CategoryList.fxml"));
        mainPane.setRight(paneLoader.LoadPane("Cart.fxml"));
        mainPane.setCenter(paneLoader.LoadPane("StorePage.fxml"));









        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                backendControllerProducts.shutDown();
            }
        }));


    }
}
