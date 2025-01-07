package codename.model;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private final String color; // Rouge ou Bleu
    private final List<Player> players;
    private int score;

    public Team(String color) {
        this.color = color;
        this.players = new ArrayList<>();
        this.score = 0;
    }

    public String getColor() {
        return color;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getScore() {
        return score;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void incrementScore() {
        score++;
    }

    public void clear() {
        players.clear();
    }

    @Override
    public String toString() {
        return "Team{" +
                "color='" + color + '\'' +
                ", players=" + players +
                ", score=" + score +
                '}';
    }
}

