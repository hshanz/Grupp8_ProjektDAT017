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
        month_button.setText(months[month]);
    }

    public void deselect()
    {
        month_button.getStyleClass().remove("list-cell-selected");
    }

    public void Pressed () //This month is chosen
    {
        parent.deselectMonths();
        month_button.getStyleClass().add("list-cell-selected");

    }
}
