package codename.controller;

import codename.Observer;
import codename.model.Card;
import codename.model.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GridSpyController implements Observer {
  private GridAgentController gridAgentController;

  private static final String FILE_NAME = "database.txt";
  @FXML GridPane gridSpy;
  private Game game;

  @FXML
  private void initialize() {
    this.game = Game.getInstance();
    game.add_observer(this);
    System.out.println(game);
    generate_grid_spy(gridSpy);
  }

  public void setGame(Game game) {
    this.game = game;
  }

  public void setGridAgentController(GridAgentController gridAgentController) {
    this.gridAgentController = gridAgentController;
  }

  public void generate_grid_spy(GridPane gridpane) {
    int rows = game.getBoard().getRows();
    int columns = game.getBoard().getColumns();
    Card[][] matrix = game.getBoard().getCards();

    Image imageNeutral = new Image(getClass().getResourceAsStream("/images/word_civ.png"));
    Image imageBlue = new Image(getClass().getResourceAsStream("/images/word_blue.png"));
    Image imageRed = new Image(getClass().getResourceAsStream("/images/word_red.png"));
    Image imageBlack = new Image(getClass().getResourceAsStream("/images/word_black.png"));

    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < columns; col++) {
        StackPane stackPane = new StackPane();

        Card card = matrix[row][col];
        Label label = new Label(card.getWord());
        label.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: black;");
        StackPane.setAlignment(label, javafx.geometry.Pos.BOTTOM_CENTER);
        label.setTranslateY(-12);

        // stackPane.setPrefSize(150, 150);
        final int currentRow = row;
        final int currentCol = col;

        stackPane.setMinSize(50, 50);
        stackPane.setMaxSize(200, 200);
        Rectangle rectangle = new Rectangle(150, 90);
        rectangle.setArcWidth(10);
        rectangle.setArcHeight(10);
        String color = card.getColor();
        ImageView imageView = new ImageView();
        if (color == "Red") {
          imageView = new ImageView(imageRed);
          imageView.setFitWidth(150);
          imageView.setFitHeight(90);
        }
        if (color == "Blue") {
          imageView = new ImageView(imageBlue);
          imageView.setFitWidth(150);
          imageView.setFitHeight(90);
        }
        if (color == "Assassin") {
          rectangle.setFill(Color.BLACK);
          imageView = new ImageView(imageBlack);
          imageView.setFitWidth(150);
          imageView.setFitHeight(90);
          label.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: white;");
        }
        if (color == "Neutral") {
          imageView = new ImageView(imageNeutral);
          imageView.setFitWidth(150);
          imageView.setFitHeight(90);
        }
        if (card.isRevealed()) {
          Image image = card.getImage();
          imageView = new ImageView(image);
          imageView.setFitWidth(150);
          imageView.setFitHeight(90);
        }

        stackPane.getChildren().add(imageView);
        if (!card.isRevealed()) {
          stackPane.getChildren().add(label);
        }

        gridpane.add(stackPane, col, row);
      }
    }
  }

  @Override
  public void update() {
    generate_grid_spy(gridSpy);
  }
}
