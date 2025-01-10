package codename.controller;

import java.io.IOException;

import codename.Observer;
import codename.model.Game;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class WriteDataBaseController implements Observer {

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

    private Game game;

    @FXML
    private void initialize() {
        System.out.println("initialize WriteDataBaseController");
        this.game = Game.getInstance();
        game.add_observer(this);

        cancelButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/settings.fxml"));
                Parent root = loader.load();

                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }
}

