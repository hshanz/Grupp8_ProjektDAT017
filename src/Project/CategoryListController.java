package Project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CategoryListController extends AnchorPane implements Initializable {

    //Documentation can be found here:
    //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/ListView.html

    //For styling options check ProjectStylesheet.css


    //Write all categories in this list
    ObservableList<String> categories = FXCollections.observableArrayList("Kött", "Frukt", "Glass", "Växter");

    @FXML
    private ListView<String> list;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        list.setItems(categories);
    }
}
