package codename.controller;

import codename.Observer;
import codename.model.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class InfoController implements Observer {
  private Game game;
  @FXML VBox info;
  @FXML Label currentTeam;
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

  public void updateCurrentTeam() {
    if (game.whosTurn().getColor().equals("Red")) {
      currentTeam.setText("Rouge");
    } else {
      currentTeam.setText("Bleu");
    }
  }

  @Override
  public void update() {
    updateScores(redScore, blueScore);
    updateCurrentTeam();
  }
}
