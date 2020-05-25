package Project;

import javafx.beans.binding.Bindings;
import javafx.event.Event;
import javafx.event.EventHandler;
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
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;

public class HistoryAccordion extends AnchorPane {


    //Labels
    public Label dateLabel;
    public Label priceLabel;


    public ScrollPane scroll;

    //Add Items to this one
    public FlowPane flowPane;

    @FXML public ImageView arrow;

    public Button expandButton;
    public boolean expanded = false;

    private Order order;
    private List<HistoryItemController> itemList;
    private Calendar calendar = Calendar.getInstance();
    private String [] months ={"Januari","Februari","Mars","April","Maj","Juni","Juli","Augusti","September","Oktober","November","December"};



    public HistoryAccordion(Order order) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HistoryAccordion.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Disable vertical scrolling
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.addEventFilter(ScrollEvent.ANY, new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent scrollEvent) {
                scrollEvent.consume();
            }
        });


        //Set the gridlines' size (space between objects)
        flowPane.setHgap(10);
        flowPane.setVgap(10);
        flowPane.setPadding(new Insets(10));
        setArrowProperties();
        expandButton.setOnAction(this::expand);






        this.order = order;
        calendar.setTimeInMillis(order.getDate().getTime());
        dateLabel.setText(String.valueOf(calendar.get(Calendar.DATE))+ ": " + months[calendar.get(Calendar.MONTH)]);
        int temp = 0;
        for (ShoppingItem sci:order.getItems()) {
            temp += sci.getTotal();
        }
        priceLabel.setText(String.valueOf(temp) + " Kr");
        itemList = new CopyOnWriteArrayList<>();
        fillPane();
        updateList();
    }

    private void updateList() {
        flowPane.getChildren().clear();
        for (HistoryItemController i:itemList) {
            flowPane.getChildren().add(i);
        }
    }

    private void fillPane() {
        for (ShoppingItem sci:order.getItems()) {
            itemList.add(new HistoryItemController(sci));
        }
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


    private int calcSize(int numberOfObjects){

        int rows = numberOfObjects/4;
        /*if(rows > 0){
            rows--;
        }*/

        return rows * 200;

    }

    @FXML
    protected void expand(Event event){

        //Expand the scroll-pane
        if(!expanded){
            int expandSize = calcSize(flowPane.getChildren().size());
            scroll.setPrefHeight(240 + expandSize);
            setPrefHeight(scroll.getPrefHeight() + 100);

            //scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            arrow.setRotate(180);
            expanded = true;
        }else{

            setPrefHeight(340);
            scroll.setPrefHeight(240);
            scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            scroll.setVvalue(0);
            arrow.setRotate(0);
            expanded = false;
        }



    }
}
