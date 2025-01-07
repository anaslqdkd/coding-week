package codename.controller;

import codename.Observer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class InfoController implements Observer {
  @FXML VBox info;
  @FXML Label redScore;
  @FXML Label blueScore;

  @Override
  public void update() {}
}
