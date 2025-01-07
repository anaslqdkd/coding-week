package codename;

import codename.controller.ClueAgentController;
import codename.controller.ClueSpyController;
import codename.controller.GameController;
import codename.controller.GridController;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
  @Override
  public void start(Stage primaryStage) {
    try {
      // Charger la fenêtre des agents (gameAgent.fxml)
      FXMLLoader agentsLoader = new FXMLLoader(getClass().getResource("/gameAgent.fxml"));
      Parent agentsRoot = agentsLoader.load();
      Scene agentsScene = new Scene(agentsRoot);
      Stage agentsStage = new Stage();
      agentsStage.setTitle("CodeName - Agents");
      agentsStage.setScene(agentsScene);

      // Charger la fenêtre des espions (gameSpy.fxml)
      FXMLLoader spiesLoader = new FXMLLoader(getClass().getResource("/gameSpy.fxml"));
      Parent spiesRoot = spiesLoader.load();
      Scene spiesScene = new Scene(spiesRoot);
      Stage spiesStage = new Stage();
      spiesStage.setTitle("CodeName - Espions");
      spiesStage.setScene(spiesScene);

      // Récupérer les GameController des deux scènes
      GameController gameAgentController = agentsLoader.getController();
      GameController gameSpyController = spiesLoader.getController();

      // Accéder aux sous-contrôleurs ClueAgentController et ClueSpyController
      ClueAgentController clueAgentController = gameAgentController.getClueAgentController();
      ClueSpyController clueSpyController = gameSpyController.getClueSpyController();

      // Connecter les sous-contrôleurs entre eux
      clueSpyController.setClueAgentController(clueAgentController);
      clueAgentController.setClueSpyController(clueSpyController);
      
      // Afficher les deux fenêtres
      agentsStage.show();
      spiesStage.show();
    } catch (Exception e) {
      e.printStackTrace(); // Afficher tous les détails de l'erreur
      System.exit(1);
    }
  }
  
  
  
    
  public static void main(String[] args) {
    launch(args);
  }
}
