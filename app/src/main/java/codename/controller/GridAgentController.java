package codename.controller;

import codename.Observer;
import codename.model.Card;
import codename.model.Clue;
import codename.model.Game;
import codename.model.Team;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class GridAgentController implements Observer {

  private static final String FILE_NAME = "database.txt";
  private ClueAgentController clueAgentController;
  private GridSpyController gridSpyController;
  @FXML GridPane gridAgent;
  private String clue;
  private Game game;
  private int clickCount = 0;

  @FXML
  private void initialize() {
    this.game = Game.getInstance();
    game.add_observer(this);
    generate_grid_agent(gridAgent);
  }

  public void setClueAgentController(ClueAgentController clueAgentController) {
    this.clueAgentController = clueAgentController;
  }

  public void setGridSpyController(GridSpyController gridSpyController) {
    this.gridSpyController = gridSpyController;
  }

  public void setGame(Game game) {
    System.out.println("in setGame in agent grid controller");
  }

  public void generate_grid_agent(GridPane gridPane) {
    int rows = game.getBoard().getRows();
    int columns = game.getBoard().getColumns();

    Image imageCiv = new Image(getClass().getResourceAsStream("/images/word_civ.png"));
    Card[][] matrix = this.game.getBoard().getCards();

    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < columns; col++) {
        ImageView imageView = new ImageView(imageCiv);
        imageView.setFitWidth(150);
        imageView.setFitHeight(90);

        Card card = matrix[row][col];
        Image image = card.getImage();
        Label label = new Label(card.getWord());
        label.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-label-fill: black;");
        StackPane.setAlignment(label, javafx.geometry.Pos.BOTTOM_CENTER);
        label.setTranslateY(-12);

        StackPane stackPane = new StackPane(imageView);
        stackPane.getChildren().add(label);

        if (card.isRevealed()) {
          stackPane.getChildren().clear();

          ImageView revealedImageView = new ImageView(image);
          revealedImageView.setFitWidth(150);
          revealedImageView.setFitHeight(90);
          stackPane.getChildren().add(revealedImageView);
          stackPane.getChildren().add(label);
        }

        stackPane.setOnMouseEntered(
            e -> {
              ScaleTransition popIn = new ScaleTransition(Duration.millis(200), stackPane);
              popIn.setToX(1.2);
              popIn.setToY(1.2);
              popIn.playFromStart();
            });

        stackPane.setOnMouseExited(
            e -> {
              ScaleTransition popOut = new ScaleTransition(Duration.millis(200), stackPane);
              popOut.setToX(1.0);
              popOut.setToY(1.0);
              popOut.playFromStart();
            });

        final int currentRow = row;
        final int currentCol = col;
        stackPane.setOnMouseClicked(
            event -> {
              handleCardClick(currentRow, currentCol);
              imageView.setFitWidth(150);
              imageView.setFitHeight(90);
            });

        gridPane.add(stackPane, col, row);
      }
    }
  }

  private StackPane createPopEffectStackPane(ImageView imageView) {
    StackPane stackPane = new StackPane();
    stackPane.setPrefSize(150, 150);

    stackPane.getChildren().add(imageView);

    ScaleTransition popIn = new ScaleTransition(Duration.millis(200), stackPane);
    popIn.setToX(1.2);
    popIn.setToY(1.2);

    ScaleTransition popOut = new ScaleTransition(Duration.millis(200), stackPane);
    popOut.setToX(1.0);
    popOut.setToY(1.0);

    stackPane.setOnMouseEntered(e -> popIn.playFromStart());
    stackPane.setOnMouseExited(e -> popOut.playFromStart());

    return stackPane;
  }

  public void handleCardClick(int row, int col) {
    Clue clue = game.getClue();
    if (clue == null) {
      System.out.println("Pas de clue");
      return;
    }

    int maxClicks = clue.getNumber() + 1;

    if (this.clickCount < maxClicks) {
      String label = clue.getText();
      System.out.println("Label: " + label);
      System.out.println("Max Clicks: " + maxClicks);

      Card[][] cards = game.getBoard().getCards();
      Card clickedCard = cards[row][col];
      Team currentTeam = game.whosTurn();
      String colorTeam = currentTeam.getColor();

      if (!clickedCard.isRevealed()) {
        String color = clickedCard.getColor();

        if (color.equals("Red")) {
          if (colorTeam != "Red") {
            System.out.println("Switching turn");
            game.switchTurn();
          }
          int score = game.getRedTeam().getScore();
          game.getRedTeam().setScore(score - 1);
        } else if (color.equals("Blue")) {
          if (colorTeam != "Blue") {
            System.out.println("Switching turn");
            game.switchTurn();
          }
          int score = game.getBlueTeam().getScore();
          game.getBlueTeam().setScore(score - 1);
        } else if (color.equals("Neutral")) {
          System.out.println("Carte neutre");
          game.switchTurn();
        } else if (color.equals("Assassin")) {
          System.out.println("Assassin");
          game.switchTurn();
        }

        clickedCard.reveal();
        System.out.println("Couleur revelée : " + color);
        System.out.println("Team: " + colorTeam);
        this.clickCount++;

      } else {
        System.out.println("carte déjà revelée: " + clickedCard.getWord());
      }
    } else {
      System.out.println("nb max de clicks(" + maxClicks + ").");
    }
    game.notify_observator();
  }

  public void resetClickCount() {
    this.clickCount = 0;
  }

  @Override
  public void update() {
    generate_grid_agent(gridAgent);
    this.game.checkWinCondition();
  }
}
