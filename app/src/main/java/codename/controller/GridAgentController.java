package codename.controller;

import codename.Observer;
import codename.model.Card;
import codename.model.Clue;
import codename.model.Game;
import codename.model.Team;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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
    System.out.println("AgentGridController initialized");
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
    final int rows = 5;
    final int columns = 5;
    Card[][] matrix = this.game.getBoard().getCards();

    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < columns; col++) {
        StackPane stackPane = new StackPane();

        Card card = matrix[row][col];
        Label label = new Label(card.getWord());
        label.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-label-fill: black;");

        stackPane.setPrefSize(100, 100);
        final int currentRow = row;
        final int currentCol = col;

        stackPane.setMinSize(50, 50);
        stackPane.setMaxSize(200, 200);
        Rectangle rectangle = new Rectangle(100, 60); // Width: 100, Height: 60
        rectangle.setArcWidth(10); // Rounded corners
        rectangle.setArcHeight(10);
        String color = card.getColor();
        if (card.isRevealed()) {
          if (color == "Red") {
            rectangle.setFill(Color.RED);
          }
          if (color == "Blue") {
            rectangle.setFill(Color.BLUE);
          }
          if (color == "Assassin") {
            rectangle.setFill(Color.BLACK);
            label.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-label-fill: white;");
            // mourir xd
          }
          if (color == "Neutral") {
            rectangle.setFill(Color.WHEAT);
          }
        } else {
          rectangle.setFill(Color.BEIGE);
        }
        stackPane.setOnMouseClicked(
            event -> {
              handleCardClick(currentRow, currentCol);
            });

        stackPane.getChildren().add(0, rectangle);
        stackPane.getChildren().add(label);

        gridPane.add(stackPane, col, row);
      }
    }
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
          if (colorTeam == "Blue") {
            game.switchTurn();
          }
          int score = game.getRedTeam().getScore();
          game.getRedTeam().setScore(score - 1);
        } else if (color.equals("Blue")) {
          if (colorTeam == "Red") {
            game.switchTurn();
          }
          int score = game.getBlueTeam().getScore();
          game.getBlueTeam().setScore(score - 1);
        }

        clickedCard.reveal();
        System.out.println("carde revelée" + clickedCard.getWord());
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
  }
}
