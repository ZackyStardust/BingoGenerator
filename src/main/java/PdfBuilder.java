package main.java;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.awt.*;
import java.io.*;

public class PdfBuilder {

    private PDDocument doc = new PDDocument();
    private PDPage page = new PDPage(PDRectangle.A4);

    public void writeTitle ( String title, int fontSize, int margin ) {
        try {
            PDType1Font font = new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN);

            float titleWidth = font.getStringWidth(title) / 1000 * fontSize;
            float titleHeight = font.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * fontSize ;

            PDPageContentStream stream = new PDPageContentStream(doc, page);

            stream.beginText();
            stream.setFont(font, fontSize);
            stream.newLineAtOffset((page.getMediaBox().getWidth() - titleWidth) / 2, page.getMediaBox().getHeight() - margin - titleHeight);
            stream.showText(title);
            stream.endText();

            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void drawBoxes ( BingoSelect bingo, int gridSize, int margin ) {

        float pageWidth = page.getMediaBox().getWidth();
        float pageHeight = page.getMediaBox().getHeight();
        float floatMargin = (float) margin;
        int longestLen = 0;

        try {
            for (int i = 0 ; i < gridSize ; i++ ) {
                for ( int ii = 0 ; ii < gridSize ; ii++ ) {
                    int temp = bingo.getContent(i, ii).length();
                    if ( temp > longestLen ) longestLen = temp;
                }
            }
            System.out.println(longestLen);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*try (PDPageContentStream stream = new PDPageContentStream(doc, page)) {


            stream.setStrokingColor(Color.BLACK);
            stream.setLineWidth(1f);

            float yPos = pageHeight/2;

            stream.moveTo(margin, 200);
            stream.lineTo(margin, 500);
            stream.stroke();

            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    public void build ( BingoSelect bingo, int gridSize ) {
        try {
            int margin = 25;

            doc.addPage(page);

            writeTitle("Bingo de carro", 16, 25);


            doc.save("teste.pdf");

            doc.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
