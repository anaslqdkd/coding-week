package codename.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Button;

public class SettingsController {

    @FXML
    private Slider gridLinesSlider;

    @FXML
    private Slider gridColumnsSlider;

    @FXML
    private TextField usernameField;

    @FXML
    private CheckBox darkModeCheckbox;

    @FXML
    private Button saveSettingsButton;

    @FXML
    private Button cancelSettingsButton;

    @FXML
    public void initialize() {
        // Force gridLinesSlider to move in integer steps
        gridLinesSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            gridLinesSlider.setValue(Math.round(newValue.doubleValue()));
        });

        // Force gridColumnsSlider to move in integer steps
        gridColumnsSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            gridColumnsSlider.setValue(Math.round(newValue.doubleValue()));
        });
    }

    @FXML
    private void saveSettings() {
        int gridLines = (int) gridLinesSlider.getValue();
        int gridColumns = (int) gridColumnsSlider.getValue();
        String username = usernameField.getText();
        boolean darkMode = darkModeCheckbox.isSelected();

        // Example of saving settings (replace with real logic)
        System.out.println("Settings saved:");
        System.out.println("Grid Lines: " + gridLines);
        System.out.println("Grid Columns: " + gridColumns);
        System.out.println("Username: " + username);
        System.out.println("Dark Mode: " + darkMode);
    }

    @FXML
    private void cancelSettings() {
        System.out.println("Settings changes canceled.");
        gridLinesSlider.setValue(5); // Reset to default
        gridColumnsSlider.setValue(5); // Reset to default
        usernameField.setText(""); // Clear the username field
        darkModeCheckbox.setSelected(false); // Uncheck dark mode
    }

}
