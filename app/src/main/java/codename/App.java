package codename;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Charger la fenêtre des agents
        URL agentsFXMLURL = getClass().getResource("/gameAgent.fxml");
        if (agentsFXMLURL == null) {
            System.err.println("Could not find agents.fxml");
            System.exit(1);
        }
        FXMLLoader agentsLoader = new FXMLLoader(agentsFXMLURL);
        Scene agentsScene = new Scene(agentsLoader.load());
        Stage agentsStage = new Stage();
        agentsStage.setTitle("CodeName - Agents");
        agentsStage.setScene(agentsScene);

        // Charger la fenêtre des espions
        URL spiesFXMLURL = getClass().getResource("/gameSpy.fxml");
        if (spiesFXMLURL == null) {
            System.err.println("Could not find spies.fxml");
            System.exit(1);
        }
        FXMLLoader spiesLoader = new FXMLLoader(spiesFXMLURL);
        Scene spiesScene = new Scene(spiesLoader.load());
        Stage spiesStage = new Stage();
        spiesStage.setTitle("CodeName - Espions");
        spiesStage.setScene(spiesScene);

        // Afficher les deux fenêtres
        agentsStage.show();
        spiesStage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}
