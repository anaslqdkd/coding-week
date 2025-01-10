package codename.model;

import codename.Observer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {
  private static Game instance;
  private Parameters parameters;
  private Board board;
  private final Team redTeam;
  private final Team blueTeam;
  private Team currentTurn;
  private Clue currentClue;
  private int maxClicks;
  private int clicksRemaining;
  private boolean isGameOver;
  private Team winner;
  private List<String> words;
  private ArrayList<Observer> observers = new ArrayList<>(10);
  private String filePath;

  public Game(List<String> words) {
    this.observers = new ArrayList<>();
    this.parameters = new Parameters();
    this.board = new Board(words);
    this.redTeam = new Team("Red");
    this.blueTeam = new Team("Blue");
    this.currentTurn = redTeam;
    this.currentClue = null;
    this.maxClicks = 0;
    this.words = words;
    this.clicksRemaining = 0;
    this.isGameOver = false;
    this.winner = null;
    this.filePath = getClass().getClassLoader().getResource("database/database.txt").getPath();
  }

  public void add_observer(Observer observer) {
    observers.add(observer);
  }

  public Parameters getParameters() {
    return parameters;
  }

  public void notify_observator() {
    for (Observer observers : this.observers) {
      observers.update();
    }
  }

  public static synchronized Game getInstance(List<String> words) {
    if (instance == null) {
      instance = new Game(words);
    }
    return instance;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public void setGridSize(int rows, int columns) {
    this.board.setGridSize(rows, columns);
    // this.board.updateWords
    int wordNumber = rows * columns;
    try {
      this.words = WordList.getWordListGlobal(wordNumber, this.filePath);
      this.getBoard().setWords(this.words);
      this.getBoard().regenerateBoard(rows, columns);
      System.out.println("RED SCORE : " + this.getBoard().getRedTeamCount());
      System.out.println("BLUE SCORE : " + this.getBoard().getBlueTeamCount());
      this.redTeam.setScore(this.getBoard().getRedTeamCount());
      this.blueTeam.setScore(this.getBoard().getBlueTeamCount());
      System.out.println("*************************");
    } catch (IOException e) {
      e.printStackTrace();
    }
    this.notify_observator();
    this.board.regenerateBoard(rows, columns);
    // this.board.update()
  }

  public void setWords(List<String> words) {
    this.words = words;
    this.board = new Board(words);
    this.notify_observator();
  }

  public int getSize() {
    return this.board.getRows() * this.board.getColumns();
  }

  public static Game getInstance() {
    if (instance == null) {
      throw new IllegalStateException(
          "Game not initialized. Call getInstance(List<String>) first.");
    }
    return instance;
  }

  // public void setBoard(Board board) {
  //     this.board = board;
  // }

  public Board getBoard() {
    return board;
  }

  public Team getRedTeam() {
    return redTeam;
  }

  public Team getBlueTeam() {
    return blueTeam;
  }

  public void addPlayerToRedTeam(Player player) {
    redTeam.addPlayer(player);
  }

  public void addPlayerToBlueTeam(Player player) {
    blueTeam.addPlayer(player);
  }

  public void clearTeams() {
    redTeam.clear();
    blueTeam.clear();
  }

  public void swicthPlayer(Player player) {
    if (redTeam.getPlayers().contains(player)) {
      redTeam.removePlayer(player);
      blueTeam.addPlayer(player);
    } else if (blueTeam.getPlayers().contains(player)) {
      blueTeam.removePlayer(player);
      redTeam.addPlayer(player);
    }
  }

  public int getMaxClicks() {
    return maxClicks;
  }

  public void decrementClicksRemaining() {
    clicksRemaining--;
  }

  public void proposeClue(Clue clue) {
    if (!isValidClue(clue.getText())) {
      throw new IllegalArgumentException("Le mot-clé est invalide.");
    }
    if (clue.getNumber() < 1 || clue.getNumber() > 25) {
      throw new IllegalArgumentException("Le nombre doit être compris entre 1 et 25.");
    }
    this.currentClue = clue;
    this.maxClicks = clue.getNumber() + 1;
    this.clicksRemaining = this.maxClicks;
  }

  private boolean isValidClue(String clue) {
    return clue != null && clue.matches("^[a-zA-Z\\-]+$");
  }

  public Clue getClue() {
    return this.currentClue;
  }

  public void revealCard(int row, int col) {
    Card card = board.getCards()[row][col]; // Access the card in the 2D array by row and col
    if (card.isRevealed()) {
      throw new IllegalArgumentException("Cette carte est déjà révélée.");
    }
    card.reveal();

    if (card.getColor().equalsIgnoreCase(currentTurn.getColor())) {
      currentTurn.incrementScore();
    }
  }

  public boolean isGameOver() {
    return this.isGameOver;
  }

  public void switchTurn() {
    currentTurn = (currentTurn == redTeam) ? blueTeam : redTeam;
    currentClue = null;
    maxClicks = 0;
    clicksRemaining = 0;
  }

  public Team whosTurn() {
    return currentTurn;
  }

  public Team getWinner() {
    return this.winner;
  }

  public void checkWinCondition() {
    if (redTeam.getScore() == 0) {
      System.out.println("L'équipe rouge a gagné !");
      this.winner = redTeam;
      this.isGameOver = true;
    } else if (blueTeam.getScore() == 0) {
      System.out.println("L'équipe bleue a gagné !");
      this.winner = blueTeam;
      this.isGameOver = true;
    } else if (this.board.isAssassinRevealed()) {
      System.out.println(
          "L'assassin a été révélé . L'équipe " + currentTurn.getColor() + " a gagné.");
      this.winner = currentTurn;
      this.isGameOver = true;
    } else {
      this.isGameOver = false;
    }

    System.out.println("Le jeu est fini : " + this.isGameOver);
  }
}
