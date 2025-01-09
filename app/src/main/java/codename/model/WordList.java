package codename.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class WordList {
  private List<String> words;

  public WordList() {
    this.words = null;
  }

  public List<String> getWords() {
    return this.words;
  }

  public void setWords(List<String> words) {
    this.words = words;
  }

  public static List<String> getDefaultList(int nb) throws IOException {
    return getWordList(nb, "database.txt");
  }

  public static List<String> getWordList(int nb, String FILE_NAME) throws IOException {
    InputStream inputStream = WordList.class.getClassLoader().getResourceAsStream(FILE_NAME);

    if (inputStream == null) {
      System.out.println("Le fichier " + FILE_NAME + " est introuvable dans les ressources !");
      return Collections.emptyList();
    }

    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
      List<String> words = reader.lines().collect(Collectors.toList());
      Collections.shuffle(words, new Random());
      List<String> selectedWords = words.stream().distinct().limit(nb).collect(Collectors.toList());

      return selectedWords;
    }
  }

  public static List<String> getWordListGlobal(int nb, String FILE_NAME) throws IOException {
    // InputStream inputStream = WordList.class.getClassLoader().getResourceAsStream(FILE_NAME);

    // if (inputStream == null) {
    //  System.out.println("Le fichier " + FILE_NAME + " est introuvable dans les ressources !");
    //  return Collections.emptyList();
    // }

    try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
      List<String> words = reader.lines().collect(Collectors.toList());
      Collections.shuffle(words, new Random());
      List<String> selectedWords = words.stream().distinct().limit(nb).collect(Collectors.toList());

      // System.out.println(selectedWords);
      return selectedWords;
    }
  }
}
