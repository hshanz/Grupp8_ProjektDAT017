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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class CategoryListController extends AnchorPane implements Initializable {

    //Documentation can be found here:
    //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/ListView.html

    //For styling options check ProjectStylesheet.css


    //Write all categories in this list
    ObservableList<String> categories = FXCollections.observableArrayList("Hela sortimentet","Frukt & Grönt","Pasta, Ris & Potatis"
            ,"Skafferi & Örter","Bröd","Mejeri","Kött, Kyckling & Fisk","Baljväxter","Frön & Nötter","Dryck & Godis");

    @FXML
    private ListView<String> list;

    private ParentController parentController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        parentController = ParentController.getInstance();
        list.setItems(categories);

        list.getSelectionModel().selectFirst();



        list.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            if ("[Hela sortimentet]".equals(String.valueOf(list.getSelectionModel().getSelectedItems()))){
                parentController.loadCatergory(Arrays.asList(ProductCategory.values()));
            }else parentController.loadCatergory(findCatergorys(list.getSelectionModel().getSelectedItem()));
        });


        
    }

    private List<ProductCategory> findCatergorys(String str){
        List<ProductCategory> categories = new ArrayList<>();
        switch (str){
            case "Frukt & Grönt":
                categories.add(ProductCategory.CITRUS_FRUIT);
                categories.add(ProductCategory.EXOTIC_FRUIT);
                categories.add(ProductCategory.BERRY);
                categories.add(ProductCategory.FRUIT);
                categories.add(ProductCategory.MELONS);
                categories.add(ProductCategory.CABBAGE);
                categories.add(ProductCategory.ROOT_VEGETABLE);
                categories.add(ProductCategory.VEGETABLE_FRUIT);
                return categories;
            case "Pasta, Ris & Potatis":
                categories.add(ProductCategory.POTATO_RICE);
                categories.add(ProductCategory.PASTA);
                return categories;
            case "Skafferi & Örter":
                categories.add(ProductCategory.FLOUR_SUGAR_SALT);
                categories.add(ProductCategory.HERB);
                return categories;
            case "Bröd":
                categories.add(ProductCategory.BREAD);
                return categories;
            case"Mejeri":
                categories.add(ProductCategory.DAIRIES);
                return categories;
            case "Kött, Kyckling & Fisk":
                categories.add(ProductCategory.MEAT);
                categories.add(ProductCategory.FISH);
                return categories;
            case "Baljväxter":
                categories.add(ProductCategory.POD);
                return categories;
            case "Frön & Nötter":
                categories.add(ProductCategory.NUTS_AND_SEEDS);
                return categories;
            case "Dryck & Godis":
                categories.add(ProductCategory.COLD_DRINKS);
                categories.add(ProductCategory.HOT_DRINKS);
                categories.add(ProductCategory.SWEET);
                return categories;
        }
        return null;
    }
}
