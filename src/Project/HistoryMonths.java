package Project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HistoryMonths extends AnchorPane {
    @FXML Button month_button; //The text should represent a month.
    private String [] months ={"Januari","Februari","Mars","April","Maj","Juni","Juli","Augusti","September","Oktober","November","December"};
    MonthHolder parent;
    private int month;

    public HistoryMonths(int month, MonthHolder parent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HistoryMonths.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.parent = parent;
        this.month = month;
        if (this.month == -1){
            month_button.setText("Alla köp");
        }else month_button.setText(months[this.month]);
    }

    @FXML
    public void Pressed (){
        parent.loadMonth(month);
    }

    public void deselect(int month){
        if (month == this.month && !month_button.getStyleClass().contains("month-button-selected")){
            month_button.getStyleClass().remove("month-button");
            month_button.getStyleClass().add("month-button-selected");
        } else if (month_button.getStyleClass().contains("month-button-selected")){
            month_button.getStyleClass().remove("month-button-selected");
            month_button.getStyleClass().add("month-button");
        }
    }

    public int getMonth() {
        return month;
    }
}
