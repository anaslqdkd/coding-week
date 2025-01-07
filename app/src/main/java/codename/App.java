package codename;

import codename.controller.GameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class App extends Application {
  @Override
  public void start(Stage primaryStage) throws Exception {
    // Charger menu.fxml pour démarrer l'application
    URL fxmlURL = getClass().getResource("/menu.fxml");
    if (fxmlURL == null) {
      System.err.println("Could not find menu.fxml");
      System.exit(1);
    }
    FXMLLoader loader = new FXMLLoader(fxmlURL);
    Parent root = loader.load();

    // Obtenir le contrôleur associé à menu.fxml
    GameController controller = loader.getController();

    // Créer la scène et l'afficher
    Scene scene = new Scene(root);

    primaryStage.setTitle("CodeName - Menu");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
