package Project;

import javafx.beans.binding.Bindings;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class HistoryAccordion extends AnchorPane implements Comparable {


    //Labels

    @FXML private Label dateLabel;
    @FXML private Label priceLabel;


    @FXML private ScrollPane scroll;
    private ScrollPane parentScroll;

    //Add Items to this one
    @FXML private FlowPane flowPane;

    @FXML private ImageView arrow;

    @FXML private Button expandButton;
    @FXML private boolean expanded = false;

    private Order order;
    private List<HistoryItemController> itemList;
    private Calendar calendar = Calendar.getInstance();
    private String [] months ={"Januari","Februari","Mars","April","Maj","Juni","Juli","Augusti","September","Oktober","November","December"};



    public HistoryAccordion(Order order, ScrollPane parentScroll) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HistoryAccordion.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.parentScroll = parentScroll;

        //Disable vertical scrolling
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.addEventFilter(ScrollEvent.SCROLL, parentScroll::fireEvent);




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


        if(flowPane.getChildren().size() <= 4){
            expandButton.setVisible(false);
            arrow.setVisible(false);
        }

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
            setPrefHeight(scroll.getPrefHeight() + 105);
            expandButton.setText("Se mindre:");
            expandButton.setPrefWidth(120);
            arrow.setRotate(180);
        }else{
            setPrefHeight(345);
            scroll.setPrefHeight(240);
            expandButton.setText("Se mer:");
            expandButton.setPrefWidth(100);
            arrow.setRotate(0);
        }
        expanded = !expanded;


    }


    public Order getOrder() {
        return order;
    }

    @Override
    public int compareTo(Object o) {
        long orderDate = ((HistoryAccordion) o).getOrder().getDate().getTime();
        return (int) (this.order.getDate().getTime()-orderDate);
    }
}
