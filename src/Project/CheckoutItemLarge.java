package Project;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class CheckoutItemLarge {
    @FXML Text name_text; //Should show the name of the wares (ex "Köttfärslimpa")
    @FXML Text cost_text; //Should show the cost of these wares (ex "120kr")
    @FXML TextField number_of_wares; //Should show the number of wares (ex "3st"), This is can be edited

    public void AddWare () {} //Should add 1 ware from the cart
    public void RemoveWare () {} //Should remove 1 ware from the cart
}
