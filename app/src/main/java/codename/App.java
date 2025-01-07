package codename;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {
  @Override
  public void start(Stage primaryStage) throws Exception {
    URL fxmlURL = getClass().getResource("/menu.fxml");
    if (fxmlURL == null) {
      System.err.println("Could not find game.fxml");
      System.exit(1);
    }
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/menu.fxml"));
    Parent root = loader.load();

    // GameController game_controller = loader.getController();
    // GridController grid_controller = new GridController();
    // grid_controller.getWordList(25);

    Scene scene = new Scene(root);

    primaryStage.setTitle("CodeName");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
