package codename.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TeamTest {

    private Team team;

    @BeforeEach
    public void setUp() {
        team = new Team("Red");
    }

    @Test
    public void testAddPlayer() {
        Player player = new Player("Player1", false);
        team.addPlayer(player);
        assertTrue(team.getPlayers().contains(player));
    }

    @Test
    public void testRemovePlayer() {
        Player player = new Player("Player1", false);
        team.addPlayer(player);
        team.removePlayer(player);
        assertFalse(team.getPlayers().contains(player));
    }

    @Test
    public void testIncrementScore() {
        int initialScore = team.getScore();
        team.incrementScore();
        assertEquals(initialScore + 1, team.getScore());
    }
}