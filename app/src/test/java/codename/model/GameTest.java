package codename.model;

import codename.Observer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    private Game game;

    @BeforeEach
    public void setUp() {
        List<String> words = Arrays.asList("word1", "word2", "word3", "word4", "word5", "word6", "word7", "word8", "word9", "word10",
                                           "word11", "word12", "word13", "word14", "word15", "word16", "word17", "word18", "word19", "word20",
                                           "word21", "word22", "word23", "word24", "word25");
        game = new Game(words);
    }

    @Test
    public void testSwitchTurn() {
        Team initialTurn = game.whosTurn();
        game.switchTurn();
        assertNotEquals(initialTurn, game.whosTurn());
    }

    @Test
    public void testSetGridSize() {
        game.setGridSize(6, 6);
        assertEquals(6, game.getBoard().getRows());
        assertEquals(6, game.getBoard().getColumns());
    }

    @Test
    public void testAddObserver() {
        Observer observer = new Observer() {
            @Override
            public void update() {
                // Do nothing
            }
        };
        game.add_observer(observer);
        assertTrue(game.getObservers().contains(observer));
    }

    @Test
    public void testNotifyObservers() {
        final boolean[] notified = {false};
        Observer observer = new Observer() {
            @Override
            public void update() {
                notified[0] = true;
            }
        };
        game.add_observer(observer);
        game.notify_observator();
        assertTrue(notified[0]);
    }
}