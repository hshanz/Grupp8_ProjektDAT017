package Project;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.net.URL;
import java.util.ResourceBundle;

public class ParentController implements Initializable {
    BackendControllerProducts backendControllerProducts;
    PaneLoader paneLoader;
    BackendControllerUserInfo backendControllerUserInfo;
    static ParentController parentController;

    @FXML BorderPane mainPane;
    @FXML private AnchorPane checkoutPane;
    @FXML private AnchorPane checkoutPaneParent;

    private CheckoutController checkoutController;
    private HistoryPage historyPageController;
    private StorePageController storePageController;

    private Pane toolBar;
    private Pane categoryList;
    private Pane cart;
    private Pane storePage;
    private Pane userPage;
    private Pane historyPage;

    public static ParentController getInstance(){
        return parentController;
    }


    public void setCenterPage(String str){
        checkoutPaneParent.toBack();
        switch (str) {
            case "StorePage":
                mainPane.setCenter(storePage);
                mainPane.setLeft(categoryList);
                mainPane.setRight(cart);
                break;
            case "UserPage":
                mainPane.setCenter(userPage);
                mainPane.setLeft(null);
                mainPane.setRight(null);
                break;
            case "Checkout":
                checkoutController.resetWizard();
                checkoutPaneParent.toFront();
                break;
            case "HistoryPage":
                System.out.println("123123123");
                mainPane.setRight(cart);
                mainPane.setCenter(historyPage);
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
        storePage = paneLoader.LoadPane("StorePage.fxml");
        userPage = paneLoader.LoadPane("UserPage.fxml");
        checkoutPane.getChildren().add(paneLoader.LoadPane("Checkout.fxml"));
        historyPage = paneLoader.LoadPane("HistoryPage.fxml");
        cart = paneLoader.LoadPane("Cart.fxml");

        System.out.println(checkoutPane);

        mainPane.setCenter(storePage);
        mainPane.setTop(toolBar);
        mainPane.setLeft(categoryList);
        mainPane.setRight(cart);










        Runtime.getRuntime().addShutdownHook(new Thread(() -> backendControllerProducts.shutDown()));
    }

    public void setCheckoutController(CheckoutController checkoutController) {
        this.checkoutController = checkoutController;
    }

    public void setHistoryPageController(HistoryPage historyPageController) {
        this.historyPageController = historyPageController;
    }

    public void addNewOrder(Order order){
        historyPageController.addOrder(order);
    }

    public void loadCatergory(ProductCategory productCategory) {
        System.out.println(productCategory);
        storePageController.update(productCategory);
    }

    public void setStorePagecontroller(StorePageController storePageController) {
        this.storePageController = storePageController;
    }
}
