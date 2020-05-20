package Project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.net.URL;
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

    @FXML
    public void onClickUserPage() {
        parentController.setCenterPage("UserPage");
    }

    @FXML
    public void onClickShop(){
        parentController.setCenterPage("StorePage");
    }

    public void onClickHistory(){
        parentController.setCenterPage(".fxml");
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        parentController = ParentController.getInstance();
    }
}
