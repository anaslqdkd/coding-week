package codename.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import codename.Observer;
import javafx.fxml.FXML;

public class GridController implements Observer {

    private static final String FILE_NAME = "database.txt";

    @FXML
    private void initialize() {
        System.out.println("GridController initialized");
    }

    public static List<String> getWordList(int nb) throws IOException {
        InputStream inputStream = GridController.class.getClassLoader().getResourceAsStream(FILE_NAME);

        if (inputStream == null) {
            System.out.println("Le fichier " + FILE_NAME + " est introuvable dans les ressources !");
            return Collections.emptyList();
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            List<String> words = reader.lines().collect(Collectors.toList());
            Collections.shuffle(words, new Random());
            List<String> selectedWords =  words.stream().distinct().limit(nb).collect(Collectors.toList());

            for (String word : selectedWords) {
                System.out.println(word +"\n");
            }
            System.out.println("Liste de " + selectedWords.size() +" mots générée avec succès !");

            return selectedWords;
        }
    }

    @Override
    public void update() {
    }
}
