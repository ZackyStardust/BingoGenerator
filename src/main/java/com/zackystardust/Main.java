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

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(
                Objects.requireNonNull(getClass().getResource("/com/zackystardust/views/bingo-ui.fxml"))
        );

        primaryStage.setTitle("Bingo Generator");
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.setResizable(false); // Optional: Prevent resizing
        primaryStage.show();
    }
}