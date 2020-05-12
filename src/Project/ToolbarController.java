package Project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class ToolbarController implements Initializable {

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Test code

        /*escapeHatch.getChildren();
        shopButton.setText("1");
        historyButton.setText("2");
        helpButton.setText("3");
        userButton.setText("4");*/


    }
}
