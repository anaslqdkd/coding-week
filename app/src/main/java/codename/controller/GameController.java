package codename.controller;

import java.io.IOException;

import codename.Observer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;



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

  @Override
  public void update() {}
}
