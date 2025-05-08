package com.zackystardust;

import com.zackystardust.model.BingoSelect;
import com.zackystardust.builder.PdfBuilder;
import java.io.IOException;

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
}