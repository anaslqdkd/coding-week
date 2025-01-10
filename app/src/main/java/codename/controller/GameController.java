package codename.controller;

import codename.Observer;
import codename.model.Game;
import codename.model.Team;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;

public class GameController implements Observer {
  @FXML private ClueAgentController clueAgentController; // Injecté automatiquement via fx:include

  @FXML private ClueSpyController clueSpyController; // Injecté automatiquement via fx:include
  @FXML private GridAgentController gridAgentController; // Injecté automatiquement via fx:include

  @FXML private GridSpyController gridSpyController; // Injecté automatiquement via fx:include

  @FXML private InfoController infoController;
  @FXML private Button startButton;
  @FXML private Button stopButton;
  @FXML private BorderPane borderPaneSpy;
  @FXML private BorderPane borderPaneAgent;
  private Game game;

  @FXML private Label timerLabel;

  public ClueAgentController getClueAgentController() {
    return clueAgentController;
  }

  public ClueSpyController getClueSpyController() {
    return clueSpyController;
  }

  public GridAgentController getGridAgentController() {
    return gridAgentController;
  }

  public GridSpyController getGridSpyController() {
    return gridSpyController;
  }

  public InfoController getInfoController() {
    return infoController;
  }

  @FXML
  private void initialize() {

    this.game = Game.getInstance();
    this.game.add_observer(this);
    if (borderPaneAgent != null) {
      setBackground(borderPaneAgent);
    }
    if (borderPaneSpy != null) {
      setBackground(borderPaneSpy);
    }
  }

  private void setBackground(BorderPane pane) {
    Team team = game.whosTurn();
    Image image;

    if (team.getColor() == "Red") {
      image = new Image(getClass().getResourceAsStream("/images/background_red.png"));
    } else image = new Image(getClass().getResourceAsStream("/images/background_blue.png"));
    BackgroundImage backgroundImage =
        new BackgroundImage(
            image,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            BackgroundSize.DEFAULT);
    pane.setBackground(new Background(backgroundImage));
  }

  @Override
  public void update() {
    if (borderPaneAgent != null) {
      setBackground(borderPaneAgent);
    }
    if (borderPaneSpy != null) {
      setBackground(borderPaneSpy);
    }
  }
}
