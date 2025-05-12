package com.zackystardust.builder;

import com.zackystardust.model.BingoSelect;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.awt.*;
import java.io.IOException;


public class PdfBuilder {
    private final PDDocument doc = new PDDocument();
    private final PDPage page = new PDPage(PDRectangle.A4);
    private final PDType1Font font = new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN);

    private float writeTitle(String title, int titleSize, float margin) throws IOException {
        float pageWidth = page.getMediaBox().getWidth();
        float pageHeight = page.getMediaBox().getHeight();
        float titleWidth = font.getStringWidth(title) / 1000 * titleSize;
        float titleHeight = font.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * titleSize;

        try (PDPageContentStream stream = new PDPageContentStream(doc, page)) {
            stream.beginText();
            stream.setFont(font, titleSize);
            stream.newLineAtOffset((pageWidth - titleWidth) / 2, pageHeight - margin - titleHeight);
            stream.showText(title);
            stream.endText();
        }
        return pageHeight - margin - titleHeight - margin;
    }

    private String getLongestWord(BingoSelect bingo, int gridSize) {
        String longestWord = "";
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                String word = bingo.getContent(i, j);
                if (word != null && word.length() > longestWord.length()) {
                    longestWord = word;
                }
            }
        }
        return longestWord;
    }

    private void drawGridLines(PDPageContentStream stream, float gridStartX, float gridStartY,
                               float squareSize, int gridSize) throws IOException {
        stream.setStrokingColor(Color.BLACK);
        stream.setLineWidth(1f);

        // Vertical
        for (int col = 0; col <= gridSize; col++) {
            float x = gridStartX + (col * squareSize);
            stream.moveTo(x, gridStartY);
            stream.lineTo(x, gridStartY - (squareSize * gridSize));
        }

        // Horizontal
        for (int row = 0; row <= gridSize; row++) {
            float y = gridStartY - (row * squareSize);
            stream.moveTo(gridStartX, y);
            stream.lineTo(gridStartX + (squareSize * gridSize), y);
        }
        stream.stroke();
    }

    private void writeGrid(BingoSelect bingo, float gridStartY, int fontSize, float margin, int gridSize) throws IOException {
        String longestWord = getLongestWord(bingo, gridSize);
        if (longestWord.isEmpty()) return;

        float cellContentWidth = font.getStringWidth(longestWord) / 1000 * fontSize;
        float cellContentHeight = font.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * fontSize;
        float squareSize = Math.max(cellContentWidth, cellContentHeight) + (2 * margin);
        float gridStartX = (page.getMediaBox().getWidth() - (squareSize * gridSize)) / 2;

        try (PDPageContentStream stream = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.APPEND, true)) {
            drawGridLines(stream, gridStartX, gridStartY, squareSize, gridSize);

            // Fill
            for (int row = 0; row < gridSize; row++) {
                for (int col = 0; col < gridSize; col++) {
                    String word = bingo.getContent(row, col);
                    if (word != null) {
                        float wordWidth = font.getStringWidth(word) / 1000 * fontSize;
                        float cellCenterX = gridStartX + (col * squareSize) + (squareSize / 2) - (wordWidth / 2);
                        float cellCenterY = gridStartY - (row * squareSize) - (squareSize / 2) -
                                (font.getFontDescriptor().getFontBoundingBox().getHeight() / 2000 * fontSize);

                        stream.beginText();
                        stream.setFont(font, fontSize);
                        stream.newLineAtOffset(cellCenterX, cellCenterY);
                        stream.showText(word);
                        stream.endText();
                    }
                }
            }
        }
    }

    public void build(BingoSelect bingo, String title, int titleSize, int fontSize, float margin, int gridSize) throws IOException {
        doc.addPage(page);
        float gridStartY = writeTitle(title, titleSize, margin);
        writeGrid(bingo, gridStartY, fontSize, margin, gridSize);
        doc.save("bingo_card.pdf");
        doc.close();
    }
}