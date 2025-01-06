package codename;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import codename.controllers.GameController;
import codename.controllers.GridController;
import codename.Observer;
import javafx.scene.layout.BorderPane;


public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/game.fxml"));
    BorderPane root = loader.load();

    GameController game_controller = loader.getController();


        Scene scene = new Scene(root);
        primaryStage.setTitle("CodeName");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
