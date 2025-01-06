package codename.controller;

import codename.Observer;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class GridController implements Observer {

  @FXML
  private void initialize() {
    System.out.println("here");
  }

  public void update() {}
}

