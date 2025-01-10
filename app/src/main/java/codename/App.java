package codename;

import codename.model.Game;
import codename.model.WordList;
import java.net.URL;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {
  private Game game;

  @Override
  public void start(Stage primaryStage) throws Exception {

    List<String> words = WordList.getWordList(25, "database/database.txt");
    this.game = Game.getInstance(words);

    URL fxmlURL = getClass().getResource("/menu.fxml");
    if (fxmlURL == null) {
      System.err.println("Could not find menu.fxml");
      System.exit(1);
    }
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/menu.fxml"));
    Parent root = loader.load();

    Scene scene = new Scene(root);

    primaryStage.setTitle("CodeName");
    primaryStage.setScene(scene);
    // primaryStage.setResizable(false);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
