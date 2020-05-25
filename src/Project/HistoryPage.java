package Project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Order;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;

public class HistoryPage implements Initializable {

    @FXML
    private FlowPane accordionFlowPane;


    private BackendControllerProducts bckEndP;
    private List<Order> orders;
    private List<HistoryAccordion> accordions;
    private ParentController parentController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bckEndP = BackendControllerProducts.getInstance();
        parentController = ParentController.getInstance();
        parentController.setHistoryPageController(this);

        orders = new CopyOnWriteArrayList<>();
        orders = bckEndP.getOrders();
        accordions = new CopyOnWriteArrayList<>();
        accordionFlowPane.setVgap(10);
        if (orders.size() !=0) {
            fillAccordionList();
        }


    }

    public void addOrder(Order order){
        accordions.add(new HistoryAccordion(order));
        updateList();
    }

    private void updateList() {
        accordionFlowPane.getChildren().clear();
        for (HistoryAccordion h:accordions) {
            accordionFlowPane.getChildren().add(h);
        }
    }

    private void fillAccordionList(){
        for (int i = orders.size()-1; i >= 0 ; i--) {
            accordions.add(new HistoryAccordion(orders.get(i)));
        }

        updateList();
    }

    public List<Order> getOrders() {
        return orders;
    }

}
