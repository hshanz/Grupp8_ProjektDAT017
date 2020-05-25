package Project;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
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
    ParentController parentController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bckEndP = BackendControllerProducts.getInstance();
        parentController = ParentController.getInstance();
        parentController.setStorePagecontroller(this);
        fillMap();
        update(Arrays.asList(ProductCategory.values()));


        searchField.textProperty().addListener((observableValue, s, t1) -> update(searchField.getText()));

        //Some properties regarding the flowpane
        flowPane.setVgap(10);
        flowPane.setHgap(10);
        flowPane.setPadding(new Insets(5));


    }

    public void update(List<ProductCategory> pcl){
        flowPane.getChildren().clear();
        for (ProductCategory pc:pcl) {
            for (Product p:bckEndP.getProducts(pc)) {
                flowPane.getChildren().add(productMap.get(p));
            }
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
