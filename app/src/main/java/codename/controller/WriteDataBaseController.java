package codename.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class WriteDataBaseController {

    @FXML
    private TextArea databaseTextArea;

    @FXML
    private Button cancelButton;

    @FXML
    private MenuButton themeButton;

    @FXML
    private TextField titleField;

    @FXML
    private Button saveButton;

    @FXML
    private void initialize() {
        // Initialisation des composants si nécessaire
    }

    // @FXML
    // private void handleSaveButtonAction() {
    //     // Logique pour enregistrer la base de données
    //     String title = titleField.getText();
    //     String content = databaseTextArea.getText();
    //     System.out.println("Enregistrer la base de données : " + title);
    //     System.out.println("Contenu : " + content);
    // }

    // @FXML
    // private void handleCancelButtonAction() {
    //     // Logique pour annuler et fermer la fenêtre
    //     Stage stage = (Stage) cancelButton.getScene().getWindow();
    //     stage.close();
    // }
}