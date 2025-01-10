package codename.controller;

import codename.Observer;
import codename.model.Clue;
import codename.model.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class ClueSpyController implements Observer {

  private ClueAgentController clueAgentController;
  private Game game;
  private GridAgentController gridAgentController;
  private boolean agentClick;

  @FXML private TextField textField;

  @FXML private ChoiceBox<Integer> choiceBox;

  @FXML private Label labelIndice;

  @FXML private Button validateButton;

  @FXML
  public void initialize() {
    this.game = Game.getInstance();
    this.game.add_observer(this);
    this.agentClick = false;
    // Interdire les espaces dans le TextField
    textField.textProperty().addListener((observable, oldValue, newValue) -> {
      textField.setText(newValue.replaceAll("[^a-zA-Z\\-]", ""));
    });
    
    validateButton.setOnAction(event ->{
      String text = textField.getText();
      Integer number = choiceBox.getValue();

      if (text != null && !text.isEmpty() && number != null) {
        // Vider et désactiver le champ texte
        textField.clear();
        textField.setDisable(true);
        validateButton.setDisable(true);
        setAgentClick(true);
        labelIndice.setText("Indice : " + text + " - " + number);
        clueAgentController.switchButton();
      }
      this.game.proposeClue(new Clue(text, number));
      clueAgentController.getClue();
    });

    // Transformer en majuscules automatiquement
    textField
        .textProperty()
        .addListener(
            (observable, oldValue, newValue) -> {
              if (!newValue.equals(newValue.toUpperCase())) {
                textField.setText(newValue.toUpperCase());
              }
            });

    // Initialisation des autres éléments
    for (int i = 9; i >= 1; i--) {
      choiceBox.getItems().add(i);
    }
    choiceBox.setOnAction(event -> updateLabel());
    textField.setOnKeyPressed(this::handleEnterKey);
  }

  public void setClueAgentController(ClueAgentController controller) {
    this.clueAgentController = controller;
  }

  public void setGridAgentController(GridAgentController gridAgentController) {
    this.gridAgentController = gridAgentController;
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

  public boolean agentClick() {
    return this.agentClick;
  }

  public void setAgentClick(boolean agentClick) {
    this.agentClick = agentClick;
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
          validateButton.setDisable(true);
          labelIndice.setText("Indice : " + text + " - " + number);
          clueAgentController.switchButton();
        }
        this.game.proposeClue(new Clue(text, number));
        clueAgentController.getClue();
        break;
      default:
        break;
    }
  }


  public void disableWin() {
    if ( this.game.isGameOver() ) {
      System.out.println("Partie terminée ClueSpyController");
      textField.setDisable(true);
      choiceBox.setDisable(true);
      validateButton.setDisable(true);
    }
  }


  public void reset() {
    textField.setDisable(false);
    textField.clear();
    choiceBox.setValue(null);
    validateButton.setDisable(false);
    setAgentClick(false);
    labelIndice.setText("Indice :");
    gridAgentController.resetClickCount();
  }

  @Override
  public void update() {
    this.disableWin();
  }
}