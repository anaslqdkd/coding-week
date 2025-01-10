package codename.model;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class WordListTest {

    @Test
    public void testGetWordList() throws IOException {
        List<String> words = WordList.getWordList(25, "database/database.txt");
        assertEquals(25, words.size());
    }

    @Test
    public void testGetDefaultList() throws IOException {
        List<String> words = WordList.getDefaultList(25);
        assertEquals(25, words.size());
    }
}