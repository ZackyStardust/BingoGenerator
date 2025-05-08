package com.zackystardust;

import com.zackystardust.model.BingoSelect;
import com.zackystardust.builder.PdfBuilder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

// Main.java - Launches the app
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // 1. Load FXML file
        Parent root = FXMLLoader.load(
                Objects.requireNonNull(getClass().getResource("/com/zackystardust/views/bingo-ui.fxml"))
        );

        // 2. Setup window
        primaryStage.setTitle("Bingo Generator");
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.setResizable(false); // Optional: Prevent resizing
        primaryStage.show();
    }
}

// Old PDF generator below
/*
public class Main {
    public static void main(String[] args) {
        int gridSize = 0;
        try {
            BingoSelect bingo = new BingoSelect();
            PdfBuilder pdfBuilder = new PdfBuilder();
            gridSize = 3;

            bingo.buildBingo(gridSize);
            pdfBuilder.build(bingo, "Car Bingo", 22, 16, 20, gridSize);

            System.out.println("Bingo card generated successfully!");

        } catch (IOException e) {
            System.err.println("File error: " + e.getMessage());
            System.err.println("Ensure JSON file exists in src/main/resources/data/ with valid data");

        } catch (IllegalArgumentException e) {
            System.err.println("Configuration error: " + e.getMessage());
            System.err.println("Please check: " + (e.getMessage().contains("names")
                    ? "JSON file must contain at least " + (gridSize * gridSize) + " names"
                    : "Grid size must be positive"));

        } catch (Exception e) {
            System.err.println("Unexpected error generating bingo card:");
            e.printStackTrace();
            System.err.println("Please report this issue with the stack trace");
        }
    }
}*/