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
    private Label cartLabel;

    @FXML
    private Button checkoutButton;

    @FXML
    private Label priceLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Test code

        /*flowPane.setPrefWidth(700);
        System.out.println(flowPane.getPrefWidth());
        cartLabel.setText("Hej hej");
        checkoutButton.setText("då då");
        priceLabel.setText("10000 kr");*/

    }
}
