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
    private Button handlaButton;

    @FXML
    private Button historikButton;

    @FXML
    private Button hjalpButton;

    @FXML
    private Button anvandareButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        escapeHatch.getChildren();
        handlaButton.setText("1");
        historikButton.setText("2");
        hjalpButton.setText("3");
        anvandareButton.setText("4");


    }
}
