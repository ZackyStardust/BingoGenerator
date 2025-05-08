package com.zackystardust;

import com.zackystardust.model.BingoSelect;
import com.zackystardust.builder.PdfBuilder;

public class Main {
    public static void main (String[] args) {
        try {
            BingoSelect newBingo = new BingoSelect();
            PdfBuilder newPdf = new PdfBuilder();
            int gridSize = 4;

            newBingo.buildBingo(gridSize);

            newPdf.build(newBingo, "Car Bingo", 22, 16, 20, gridSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}