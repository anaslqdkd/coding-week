package codename;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

  @Override
  public void start(Stage primaryStage) {
    Label label = new Label("Hello, JavaFX!");

    StackPane root = new StackPane(label);

    Scene scene = new Scene(root, 400, 300);

    primaryStage.setTitle("Basic JavaFX App");
    primaryStage.setScene(scene);
    primaryStage.show();
    // commentaires
  }

  public static void main(String[] args) {
    launch(args);
  }
}
