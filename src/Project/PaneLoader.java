package Project;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;


public class PaneLoader {
    private  Pane pane;
    public Pane LoadPane(String str) {

        try {
            pane = FXMLLoader.load(getClass().getResource(str));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pane;
    }
}
