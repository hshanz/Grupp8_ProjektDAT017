package Project;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.net.URL;
import java.util.ResourceBundle;

public class CategoryListController extends AnchorPane implements Initializable {

    //Documentation can be found here:
    //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/ListView.html

    //For styling options check ProjectStylesheet.css


    //Write all categories in this list
    ObservableList<ProductCategory> categories = FXCollections.observableArrayList(ProductCategory.values());

    @FXML
    private ListView<ProductCategory> list;

    private ParentController parentController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        parentController = ParentController.getInstance();
        list.setItems(categories);


        list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ProductCategory>() {
            @Override
            public void changed(ObservableValue<? extends ProductCategory> observableValue, ProductCategory productCategory, ProductCategory t1) {
                parentController.loadCatergory(list.getSelectionModel().getSelectedItem());
            }
        });


        
    }
}
