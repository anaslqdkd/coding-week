package codename.model;

public class Game {
    private Parameters parameters;
    private Board board;
    private final Team redTeam;
    private final Team blueTeam;
    private Team currentTurn;
    private String currentClue;
    private int maxClicks;
    private int clicksRemaining;

    public Game() {
        this.parameters = new Parameters();
        this.board = null;
        this.redTeam = new Team("Red");
        this.blueTeam = new Team("Blue");
        this.currentTurn = redTeam;
        this.currentClue = null;
        this.maxClicks = 0;
        this.clicksRemaining = 0;
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

    public void revealCard(int index) {
        Card card = board.getCards().get(index);
        if (card.isRevealed()) {
            throw new IllegalArgumentException("Cette carte est déjà révélée.");
        }
        card.reveal();

        // Mettre à jour les scores
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
        long redLeft = board.getCards().stream()
                .filter(card -> card.getColor().equals("Red") && !card.isRevealed())
                .count();
        long blueLeft = board.getCards().stream()
                .filter(card -> card.getColor().equals("Blue") && !card.isRevealed())
                .count();

        return redLeft == 0 || blueLeft == 0;
    }
}

