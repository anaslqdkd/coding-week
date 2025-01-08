package codename.controller;

import java.io.IOException;

import codename.Observer;
import codename.model.Game;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuController implements Observer{

    @FXML
    private Button playButton;

    private Game game;

    @FXML
    private void initialize() {

        System.out.println("initialize MenuController");
        this.game = Game.getInstance();
        game.add_observer(this);

        playButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Nb_joueurs.fxml"));
                Parent root = loader.load();

                Stage stage = (Stage) playButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void update() {
    }
}