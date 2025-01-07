package codename.controller;

import codename.Observer;
import codename.model.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

// TODO: à supprimer info et infoSpy qui ne servent plus à rien
// TODO: pas clickable pour l'autre équipe si pas d'indice

public class InfoController implements Observer {
  private Game game;
  @FXML VBox info;
  @FXML Label redScore;
  @FXML Label blueScore;

  @FXML
  private void initialize() {
    System.out.println("-----------------------------------------");
    System.out.println("in initialise info controller");
    this.game = Game.getInstance();
    game.add_observer(this);
    System.out.println(game);
  }

  public void updateScores(Label redLabel, Label blueLabel) {
    if (game != null) {
      int red_score = game.getRedTeam().getScore();
      int blue_score = game.getBlueTeam().getScore();
      redLabel.setText("Equipe Rouge : " + red_score);
      blueLabel.setText("Equipe Bleue : " + blue_score);
    }
  }

  @Override
  public void update() {
    updateScores(redScore, blueScore);
  }
}
