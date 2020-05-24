package Project;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ToolbarController implements Initializable {

    private ParentController parentController;

    @FXML
    private Pane escapeHatch;

    @FXML
    private Button shopButton;

    @FXML
    private Button historyButton;

    @FXML
    private Button helpButton;

    @FXML
    private Button userButton;

    //List to hold all buttons
    List<Button> buttonList;





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        parentController = ParentController.getInstance();

        buttonList = new ArrayList<>();
        buttonList.add(shopButton);
        buttonList.add(historyButton);
        buttonList.add(helpButton);
        buttonList.add(userButton);

    }

    @FXML
    public void onClickUserPage() {
        parentController.setCenterPage("UserPage");
        changeStylesheet(userButton);
    }

    @FXML
    public void onClickShop(){
        parentController.setCenterPage("StorePage");
        changeStylesheet(shopButton);
    }

    @FXML
    public void onClickHistory(){
        parentController.setCenterPage("HistoryPage");
        changeStylesheet(historyButton);
    }


    //Changes the pressed button's style, and resets the other buttons' style
    private void changeStylesheet(Button pressedButton){
        if(buttonList.contains(pressedButton)){
            for(Button b : buttonList){
                b.getStyleClass().clear();
                if(b.equals(pressedButton)){
                    b.getStyleClass().add("toolbar-button-selected");
                }else{
                    b.getStyleClass().add("toolbar-button");
                }
            }
        }
    }
}
