package Project;

import javafx.fxml.FXMLLoader;

import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class TestItemController extends AnchorPane {

    public TestItemController() {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TestItem.fxml"));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.setLocation(getClass().getResource("TestItem.fxml"));

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            //e.printStackTrace();
        }

    }
}
