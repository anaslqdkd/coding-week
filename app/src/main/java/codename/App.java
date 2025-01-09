package codename;

import java.net.URL;
import java.util.List;

import codename.model.Game;
import codename.model.WordList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    private Game game; 
    
  @Override
  public void start(Stage primaryStage) throws Exception {
    ; // Create a new game
    List<String> words = WordList.getWordList(25, "database/database.txt");
    this.game = Game.getInstance(words);
    // this.game.setGridSize(6, 6);


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
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

