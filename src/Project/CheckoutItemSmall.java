package Project;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class CheckoutItemSmall {
    /* The checkout item small should populate the fourth step of the checkout wizard.
     * It should be checkout item large for each type of ware in the cart.
     * The checkout item small should be placed in a scroll pane where 2 items is besides each other and then it lists down. '
     * Reference image: https://www.figma.com/file/Y6tseIZ6XOMK9EmOdySxMK/IMat?node-id=0%3A1 Betalningswizard4*/

    @FXML Text name_text; //Should show the name of the wares
    @FXML Text cost_text; //Should show the cost of the wares
    @FXML TextField number_of_wares; //should show the number of these wares in the cart. This can also be edited
}
