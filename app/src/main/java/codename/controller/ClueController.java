package codename.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import codename.model.Clue;

public class ClueController {
    private Clue clue;

    @FXML
    private TextField textField;

    @FXML
    private ChoiceBox<Integer> choiceBox;

    @FXML
    private Label labelIndice;

    @FXML
    public void initialize() {
        // Interdire les espaces dans le TextField
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.contains(" ")) {
                textField.setText(newValue.replaceAll(" ", ""));
            }
        });
    
        // Transformer en majuscules automatiquement
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equals(newValue.toUpperCase())) {
                textField.setText(newValue.toUpperCase());
            }
        });
    
        // Initialisation des autres éléments
        for (int i = 1; i <= 9; i++) {
            choiceBox.getItems().add(i);
        }
        choiceBox.setOnAction(event -> updateLabel());
        textField.setOnKeyPressed(this::handleEnterKey);
    }
    

    private void updateLabel() {
        String text = textField.getText();
        Integer number = choiceBox.getValue();

        if (text != null && !text.isEmpty() && number != null) {
            labelIndice.setText("Indice : " + text + " - " + number);
        } else if (text != null && !text.isEmpty()) {
            labelIndice.setText("Indice : " + text);
        } else {
            labelIndice.setText("Indice :");
        }
    }

    private void handleEnterKey(KeyEvent event) {
        switch (event.getCode()) {
            case ENTER:
                // Vérifier si un indice est saisi et un nombre est sélectionné
                String text = textField.getText();
                Integer number = choiceBox.getValue();

                if (text != null && !text.isEmpty() && number != null) {
                    // Vider et désactiver le champ texte
                    textField.clear();
                    textField.setDisable(true);
                    labelIndice.setText("Indice : " + text + " - " + number);
                }
                clue = new Clue(text, number);
                break;
            default:
                break;
        }
    }
}
