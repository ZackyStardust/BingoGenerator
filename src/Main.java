import main.java.BingoSelect;
import main.java.PdfBuilder;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import java.io.*;

public class Main {
    public static void main (String[] args) {
        try {
            BingoSelect newBingo = new BingoSelect();
            PdfBuilder newPdf = new PdfBuilder();
            int gridSize = 3;

            newBingo.buildBingo(gridSize);

            newPdf.drawBoxes(newBingo, gridSize, 25);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}