package Project;

import javafx.beans.binding.Bindings;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HistoryAccordion extends AnchorPane implements Initializable {


    //Labels
    public Label dateLabel;
    public Label priceLabel;


    public ScrollPane scroll;

    //Add Items to this one
    public FlowPane flowPane;

    @FXML public ImageView arrow;

    public Button expandButton;
    public boolean expanded = false;

    public HistoryAccordion() {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HistoryAccordion.fxml"));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);


        try {
            fxmlLoader.load();
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Disable vertical scrolling
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        //Set the gridlines' size (space between objects)
        flowPane.setHgap(10);
        flowPane.setVgap(10);

        flowPane.setPadding(new Insets(10));

        //Add products
        for (int i = 0; i < 10; i++) {
            flowPane.getChildren().add(new HistoryItemController());
        }

        setArrowProperties();

        expandButton.setOnAction(this::expand);

    }

    private void setArrowProperties(){
        //Create new Effect
        ColorAdjust increaseBrightness = new ColorAdjust();
        increaseBrightness.setBrightness(0.5);

        //Set Effect
        arrow.effectProperty().bind(Bindings.when(arrow.hoverProperty()).then((Effect)increaseBrightness).otherwise((Effect)null));
        arrow.setCache(true);

        arrow.setOnMouseClicked(this::expand);

    }


    @FXML
    protected void expand(Event event){

        //Expand the scroll-pane
        if(!expanded){
            scroll.setPrefHeight(300);
            setPrefHeight(400);
            scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            arrow.setRotate(180);
            expanded = true;
        }else{
            scroll.setPrefHeight(240);
            setPrefHeight(340);
            scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            scroll.setVvalue(0);
            arrow.setRotate(0);
            expanded = false;
        }



    }
}
