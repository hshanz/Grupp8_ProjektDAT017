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

    public FlowPane baseFlowPane;
    public ScrollPane scroll;
    public FlowPane flowPane;

    public Button expandButton;

    public boolean expanded = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Disable vertical scrolling
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);




        //Set the gridlines' size (space between objects)
        flowPane.setHgap(10);
        flowPane.setVgap(10);

        //Add products
        flowPane.getChildren().add(new TestItemController());
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
}
