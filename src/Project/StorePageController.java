package Project;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class StorePageController extends AnchorPane implements Initializable {


    //Add items to this panel
    @FXML
    public FlowPane flowPane;

    @FXML
    public TextField searchField;

    BackendControllerProducts bckEndP;
    HashMap<Product,MainShopItemController> productMap = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bckEndP = BackendControllerProducts.getInstance();
        fillMap();
        update();


        searchField.textProperty().addListener((observableValue, s, t1) -> update(searchField.getText()));

    }

    public void update(ProductCategory pc){
        flowPane.getChildren().clear();
        for (Product p:bckEndP.getProducts(pc)) {
            flowPane.getChildren().add(productMap.get(p));
        }
    }

    public void update(String str){
        flowPane.getChildren().clear();
        for (Product p:bckEndP.getProducts(str)) {
            flowPane.getChildren().add(productMap.get(p));
        }
    }



    public void update(){
        flowPane.getChildren().clear();
        for (Product p:bckEndP.getProducts()) {
            flowPane.getChildren().add(productMap.get(p));
        }
    }

    private void fillMap(){
        for (Product p:bckEndP.getProducts()) {
            productMap.put(p,new MainShopItemController(p));
        }
    }
}
