package com.zackystardust.model;

import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;

public class BingoSelect {

    private final ArrayList<ArrayList<String>> grid = new ArrayList<>();

    public void buildBingo(Integer size) throws IOException, IllegalArgumentException {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("data/colors.json")) {

            if (size <= 0) {
                throw new IllegalArgumentException("Grid size must be positive");
            }

            if (is == null) {
                throw new IOException("File not found in resources");
            }

            String content = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            ArrayList<String> colors = getStrings(size, content);

            Random rand = new Random();
            for (int row = 0; row < size; row++) {
                ArrayList<String> rowColors = new ArrayList<>();
                for (int col = 0; col < size; col++) {
                    int randomIndex = rand.nextInt(colors.size());
                    rowColors.add(colors.remove(randomIndex));
                }
                grid.add(rowColors);
            }

        } catch (Exception e) {
            grid.clear();
            throw e;
        }
    }

    private static ArrayList<String> getStrings(Integer size, String content) throws IOException {
        JSONArray arr = new JSONArray(content);
        if (arr.isEmpty()) {
            throw new IOException("JSON file is empty");
        }
        if (arr.length() < size * size) {
            throw new IllegalArgumentException(
                    String.format("Need at least %d names in JSON (found %d)",
                            size * size, arr.length())
            );
        }

        ArrayList<String> colors = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {
            colors.add(arr.getString(i));
        }
        return colors;
    }

    public String getContent(int x, int y) {
        return grid.get(y).get(x);
    }

    public void printGrid() {
        System.out.println(grid + "\n");
    }
}