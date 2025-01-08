package codename.controller;

import codename.Observer;
import codename.model.Card;
import codename.model.Game;
import java.util.Arrays;
import java.util.List;
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
        label.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: black;");

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
            label.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: white;");
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
    String label = clueAgentController.getClueLabel();
    System.out.println(label);

    if (label.equals("En attente...")) {
      System.out.println("Card click ignored: Label is 'En attente...'");
      return;
    }
    Card[][] cards = game.getBoard().getCards();
    Card clickedCard = cards[row][col];
    if (!clickedCard.isRevealed()) {
      String color = clickedCard.getColor();
      if (color == "Red") {
        int score = game.getRedTeam().getScore();
        game.getRedTeam().setScore(score - 1);
      }
      if (color == "Blue") {
        int score = game.getBlueTeam().getScore();
        game.getBlueTeam().setScore(score - 1);
      }
      clickedCard.reveal();
      System.out.println("Card revealed: " + clickedCard.getWord());
    } else {
      System.out.println("Card already revealed: " + clickedCard.getWord());
    }

    game.notify_observator();
  }

  @Override
  public void update() {
    generate_grid_agent(gridAgent);
  }
}
