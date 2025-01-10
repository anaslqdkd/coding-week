package codename.model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardTest {

    private Board board;

    @BeforeEach
    public void setUp() {
        List<String> words = Arrays.asList("word1", "word2", "word3", "word4", "word5", "word6", "word7", "word8", "word9", "word10",
                                           "word11", "word12", "word13", "word14", "word15", "word16", "word17", "word18", "word19", "word20",
                                           "word21", "word22", "word23", "word24", "word25");
        board = new Board(words);
    }

    @Test
    public void testSetGridSize() {
        board.setGridSize(6, 6);
        assertEquals(6, board.getRows());
        assertEquals(6, board.getColumns());
    }

    @Test
    public void testSetWords() {
        List<String> newWords = Arrays.asList("newWord1", "newWord2", "newWord3", "newWord4", "newWord5", "newWord6", "newWord7", "newWord8", "newWord9", "newWord10",
                                              "newWord11", "newWord12", "newWord13", "newWord14", "newWord15", "newWord16", "newWord17", "newWord18", "newWord19", "newWord20",
                                              "newWord21", "newWord22", "newWord23", "newWord24", "newWord25");
        board.setWords(newWords);
        assertEquals(newWords, board.getWords());
    }

    @Test
    public void testRevealCard() {
        board.revealCard(0, 0, 5, 5);
        assertTrue(board.getCards()[0][0].isRevealed());
    }
}