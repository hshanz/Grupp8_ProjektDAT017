package Project;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HistoryAccordion implements Initializable {

    @FXML
    public AnchorPane base;

    public FlowPane flow;
    public ScrollPane scroll;
    public GridPane grid;

    public Button expandButton;

    public boolean expanded = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Disable vertical scrolling
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        grid.setGridLinesVisible(true);

        //Remove the initial row
        grid.getRowConstraints().remove(0);


        //Set the gridlines' size (space between objects)
        grid.setHgap(10);
        grid.setVgap(10);


        addRows(21);

        //Add columns, each taking 25% of the available space
        for (int i = 0; i < 4; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(25);

            grid.getColumnConstraints().add(column);
        }

        //Add products
            /*grid.add(new TestItemController(), 0,0);
            grid.add(new TestItemController(), 1,0);*/


    }

    @FXML
    protected void expand(Event event){

        //Expand the scroll-pane
        if(!expanded){
            scroll.setPrefHeight(300);
            base.setPrefHeight(400);
            scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            expanded = true;
        }else{
            scroll.setPrefHeight(200);
            base.setPrefHeight(300);
            scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            scroll.setVvalue(0);
            expanded = false;
        }


    }

    //Add rows depending on the number of items to be displayed
    private void addRows(int numberOfItems){

        int nRows = (numberOfItems /4);

        //Add rows
        for (int row = 0; row < nRows; row++) {
            grid.getRowConstraints().add(new RowConstraints(125));
        }
    }


}
