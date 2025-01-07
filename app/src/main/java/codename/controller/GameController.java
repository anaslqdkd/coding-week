package codename.controller;

import codename.Observer;
import codename.model.Game;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;



public class GameController implements Observer {
  private GridController gridController;

  private Game game;

  @FXML
  public void setGame(Game game) {
      this.game = game;
  }

  @FXML
  private void initialize() {
    try{
      FXMLLoader gridLoader = new FXMLLoader(getClass().getResource("/grid.fxml"));
              GridPane grid = gridLoader.load();
              gridController = gridLoader.getController();

    }catch (IOException e) {
            e.printStackTrace();
        }
  }

  public void update() {}
}
