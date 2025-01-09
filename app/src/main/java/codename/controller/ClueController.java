package codename.controller;

import codename.Observer;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ClueController implements Observer {

    @FXML
    private TextField textField;

    @FXML
    private ChoiceBox<Integer> choiceBox;

    @FXML
    private Label labelIndice;

    @FXML
    public void initialize() {
        // Initialisation des valeurs de la ChoiceBox
        for (int i = 1; i <= 9; i++) {
            choiceBox.getItems().add(i);
        }

        // Ajout de listeners
        choiceBox.setOnAction(event -> updateLabel());
        textField.textProperty().addListener((observable, oldValue, newValue) -> updateLabel());
    }

    private void updateLabel() {
        String text = textField.getText();
        Integer number = choiceBox.getValue();

        if (text != null && number != null) {
            labelIndice.setText("Indice : " + text + " - " + number);
        } else if (text != null) {
            labelIndice.setText("Indice : " + text);
        } else if (number != null) {
            labelIndice.setText("Indice : - " + number);
        } else {
            labelIndice.setText("Indice :");
        }
    }

    @Override
    public void update() {
    }
}
