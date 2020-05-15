package Project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{


        Parent root = FXMLLoader.load(getClass().getResource("Parent.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("Checkout.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
        System.out.println(System.getProperty("user.home"));



    }


    public static void main(String[] args) {
        launch(args);
    }
}
