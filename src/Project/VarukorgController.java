package Project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class VarukorgController implements Initializable {



    //Add items to this one (it is inside a scroll pane)
    @FXML
    private FlowPane flowPane;

    @FXML
    private Label varukorgLabel;

    @FXML
    private Button kassaButton;

    @FXML
    private Label prisLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Test code

        /*flowPane.setPrefWidth(700);
        System.out.println(flowPane.getPrefWidth());
        varukorgLabel.setText("Hej hej");
        kassaButton.setText("då då");
        prisLabel.setText("10000 kr");*/

    }
}
