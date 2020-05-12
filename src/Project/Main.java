package Project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("Project/Resources/Project.properties");
        Properties appProp = new Properties();
        appProp.load(inputStream);

        Parent root = FXMLLoader.load(getClass().getResource("Project/Parent.fxml"));
        Scene scene= new Scene(root, 500, 500);
        stage.setTitle(appProp.getProperty("application.name"));
        stage.setScene(scene);
        stage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
