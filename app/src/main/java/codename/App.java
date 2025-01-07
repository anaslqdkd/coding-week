package codename;

import java.net.URL;

import codename.controller.MenuController;
import codename.controller.SettingsController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        showMenu();
    }

    public void showMenu() throws Exception {
        URL fxmlURL = getClass().getResource("/menu.fxml");
        if (fxmlURL == null) {
            System.err.println("Could not find menu.fxml");
            System.exit(1);
        }
        FXMLLoader loader = new FXMLLoader(fxmlURL);
        Parent root = loader.load();

        // Pass the application reference to the controller
        MenuController controller = loader.getController();
        controller.setApp(this);

        Scene scene = new Scene(root);

        primaryStage.setTitle("CodeName - Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showSettings() throws Exception {
        URL fxmlURL = getClass().getResource("/settings.fxml");
        if (fxmlURL == null) {
            System.err.println("Could not find settings.fxml");
            System.exit(1);
        }
        FXMLLoader loader = new FXMLLoader(fxmlURL);
        Parent root = loader.load();

        // Pass the application reference to the controller
        SettingsController controller = loader.getController();
        controller.setApp(this);

        Scene scene = new Scene(root);

        primaryStage.setTitle("CodeName - Paramètres");
        primaryStage.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
