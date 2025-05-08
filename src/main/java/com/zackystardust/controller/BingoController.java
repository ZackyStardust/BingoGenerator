package com.zackystardust.controller;

import com.zackystardust.model.BingoSelect;
import com.zackystardust.builder.PdfBuilder;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class BingoController {
    @FXML private ChoiceBox<String> jsonDropdown;
    @FXML private TextField titleField;
    @FXML private Spinner<Integer> sizeSpinner;

    @FXML
    private void initialize() {
        File themesDir = new File("themes");
        File[] jsonFiles = themesDir.listFiles((dir, name) -> name.endsWith(".json"));

        if (!themesDir.exists()) errorExit("Themes folder not found. Please create a 'themes' folder with JSON files.");
        if (jsonFiles == null || jsonFiles.length == 0) {
            errorExit("No JSON theme files found in 'themes' folder.");
        }

        jsonDropdown.getItems().setAll(
                Arrays.stream(Objects.requireNonNull(themesDir.listFiles()))
                        .filter(f -> f.getName().endsWith(".json"))
                        .map(f -> f.getName().replace(".json", ""))
                        .collect(Collectors.toList())
        );

        // Set default selection
        if (!jsonDropdown.getItems().isEmpty()) {
            jsonDropdown.getSelectionModel().selectFirst();
        }

        // Set default grid size (3x3)
        sizeSpinner.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(3, 5, 3)
        );
    }

    @FXML
    private void handleGenerate() {
        try {
            BingoSelect bingo = new BingoSelect();
            PdfBuilder pdfBuilder = new PdfBuilder();

            // Get user inputs
            String title = titleField.getText();
            int size = sizeSpinner.getValue();
            String jsonFile = jsonDropdown.getValue();

            // Generate PDF
            bingo.buildBingo(size, jsonFile ); // Modified method
            pdfBuilder.build(bingo, title, 22, 16, 20, size);

            showAlert("Success", "Bingo card generated!");
        } catch (Exception e) {
            showAlert("Error", e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void errorExit(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Critical Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
        Platform.exit(); // Close JavaFX
        System.exit(1);  // Force exit (status code 1 = error)
    }
}