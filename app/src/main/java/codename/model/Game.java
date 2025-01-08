package codename.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import codename.Observer;

public class Game {
  private static Game instance;
  private Parameters parameters;
  private final Board board;
  private final Team redTeam;
  private final Team blueTeam;
  private Team currentTurn;
  private Clue currentClue;
  private int maxClicks;
  private int clicksRemaining;
  private boolean isGameOver;
  private Team winner;
  private ArrayList<Observer> observers = new ArrayList<>(10);

  public Game(List<String> words) {
    this.observers = new ArrayList<>();
    this.parameters = new Parameters();
    this.board = new Board(words);
    this.redTeam = new Team("Red");
    this.blueTeam = new Team("Blue");
    this.currentTurn = redTeam;
    this.currentClue = null;
    this.maxClicks = 0;
    this.clicksRemaining = 0;
    this.isGameOver = false;
    this.winner = null;
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

  public static Game getInstance() {
    if (instance == null) {
      throw new IllegalStateException(
          "Game not initialized. Call getInstance(List<String>) first.");
    }
    return instance;
  }

  public Board getBoard() {
    return board;
  }

  public Team getRedTeam() {
    return redTeam;
  }

  public Team getBlueTeam() {
    return blueTeam;
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
      System.out.println("L'assassin a été révélé . L'équipe " + currentTurn.getColor() + " a gagné.");
      this.winner = currentTurn;
      this.isGameOver = true;
    } else {
      this.isGameOver = false;
    }
    
    System.out.println("Le jeu est fini : " + this.isGameOver);
  }
}
