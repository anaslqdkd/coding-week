package codename.controllers;

import codename.Observer;
import javafx.fxml.FXML;
import codename.controllers.GridController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import java.io.IOException;



public class GameController implements Observer {
  private GridController gridController;

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
