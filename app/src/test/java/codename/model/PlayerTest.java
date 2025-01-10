package codename.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    public void testPlayerCreation() {
        Player player = new Player("Player1", false);
        assertEquals("Player1", player.getName());
        assertFalse(player.isSpymaster());
    }

    @Test
    public void testSetSpymaster() {
        Player player = new Player("Player1", false);
        player.setSpymaster(true);
        assertTrue(player.isSpymaster());
    }
}