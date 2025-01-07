package codename;

import java.net.URL;

import codename.controller.MenuController;
import codename.model.Game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    private Game game; 

    @Override
    public void start(Stage primaryStage) throws Exception {
        game = new Game(); // Create a new game

        URL fxmlURL = getClass().getResource("/menu.fxml");
        if (fxmlURL == null) {
            System.err.println("Could not find menu.fxml");
            System.exit(1);
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/menu.fxml"));
        Parent root = loader.load();

        MenuController menuController = loader.getController();
        menuController.setGame(game);

        Scene scene = new Scene(root);

        primaryStage.setTitle("CodeName");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

