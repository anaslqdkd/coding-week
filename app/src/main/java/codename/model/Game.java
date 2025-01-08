package codename.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import codename.Observer;

public class Game {
    private static Game instance;
    private Parameters parameters;
    private Board board;
    private final Team redTeam;
    private final Team blueTeam;
    private Team currentTurn;
    private String currentClue;
    private int maxClicks;
    private int clicksRemaining;
    private ArrayList<Observer> observers = new ArrayList<>(10);

    public Game(List<String> words) {
        this.parameters = new Parameters();
        this.observers = new ArrayList<>();
        this.board = new Board(words);
        this.redTeam = new Team("Red");
        this.blueTeam = new Team("Blue");
        this.currentTurn = redTeam;
        this.currentClue = null;
        this.maxClicks = 0;
        this.clicksRemaining = 0;
    }

    public void add_observer(Observer observer) {
        observers.add(observer);
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

    public Parameters getParameters() {
        return parameters;
    }

    public void setBoard(Board board) {
        this.board = board;
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

    public void addPlayerToRedTeam(Player player) {
        redTeam.addPlayer(player);
    }

    public void addPlayerToBlueTeam(Player player) {
        blueTeam.addPlayer(player);
    }

    public Team getCurrentTurn() {
        return currentTurn;
    }

    public String getCurrentClue() {
        return currentClue;
    }

    public int getClicksRemaining() {
        return clicksRemaining;
    }

    public void proposeClue(String clue, int number) {
        if (!isValidClue(clue)) {
            throw new IllegalArgumentException("Le mot-clé est invalide.");
        }
        if (number < 1 || number > 25) {
            throw new IllegalArgumentException("Le nombre doit être compris entre 1 et 25.");
        }
        this.currentClue = clue;
        this.maxClicks = number + 1;
        this.clicksRemaining = this.maxClicks;
    }

    private boolean isValidClue(String clue) {
        return clue != null && clue.matches("^[a-zA-Z]+$");
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
        return checkWinCondition();
    }

    public void switchTurn() {
        currentTurn = (currentTurn == redTeam) ? blueTeam : redTeam;
        currentClue = null;
        maxClicks = 0;
        clicksRemaining = 0;
    }

    private boolean checkWinCondition() {
        long redLeft =
                Arrays.stream(board.getCards())
                        .flatMap(Arrays::stream) // Flatten the 2D array to a stream of cards
                        .filter(card -> card.getColor().equals("Red") && !card.isRevealed())
                        .count();

        long blueLeft =
                Arrays.stream(board.getCards())
                        .flatMap(Arrays::stream) // Flatten the 2D array to a stream of cards
                        .filter(card -> card.getColor().equals("Blue") && !card.isRevealed())
                        .count();

        return redLeft == 0 || blueLeft == 0;
    }
}

