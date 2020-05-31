package Project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Order;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;

public class HistoryPage implements Initializable {

    @FXML private FlowPane accordionFlowPane;
    @FXML private ScrollPane mainScroll;


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
        accordions.add(new HistoryAccordion(order,mainScroll));
        updateList();
    }

    private void updateList() {
        accordionFlowPane.getChildren().clear();
        for (int i = accordions.size()-1; i > -1 ; i--) {
            accordionFlowPane.getChildren().add(accordions.get(i));
        }
    }

    private void fillAccordionList(){
        for (Order o:orders) {
            accordions.add(new HistoryAccordion(o, mainScroll));
        }
        updateList();
    }

    public List<Order> getOrders() {
        return orders;
    }

}
