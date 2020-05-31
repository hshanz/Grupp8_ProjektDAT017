package Project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Order;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

public class MonthHolder implements Initializable {

    @FXML
    private FlowPane historyMonthHolder;

    private HistoryPage historyPage;
    private ParentController parentController;
    private List<HistoryMonths> monthsList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        parentController = ParentController.getInstance();
        historyPage = parentController.getHistoryPageController();
        monthsList = new ArrayList<>();
        monthsList.add(new HistoryMonths(-1,this));
        addMonths();
        addThisMonth();
        update();

    }

    private void update() {
        historyMonthHolder.getChildren().clear();
        for (HistoryMonths h:monthsList) {
            historyMonthHolder.getChildren().add(h);
        }
    }

    public void addThisMonth(){
        Calendar calendar = Calendar.getInstance();
        if (monthsList.size() > 1 && calendar.get(Calendar.MONTH) != monthsList.get(1).getMonth()){
            monthsList.add(1,new HistoryMonths(calendar.get(Calendar.MONTH),this));
        }
    }


    public void loadMonth(int month){
        if (month == -1) {
            historyPage.updateList();
        }else historyPage.loadMonthView(month);
        for (HistoryMonths h:monthsList) {
            h.deselect(month);
        }
    }




    private void addMonths() {
        List<Order> orders = historyPage.getOrders();
        int month;
        int monthPrev = -1;
        Calendar calendar = Calendar.getInstance();
        for (int i = orders.size() -1; i >= 0 ; i--) {
            calendar.setTimeInMillis(orders.get(i).getDate().getTime());
            month = calendar.get(Calendar.MONTH);
            if (month != monthPrev) monthsList.add(new HistoryMonths(month,this));
            monthPrev = month;
        }
    }
}
