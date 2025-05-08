package com.zackystardust.model;

import org.json.JSONArray;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

public class BingoSelect {

    private ArrayList<ArrayList<String>> grid = new ArrayList<>();

    public void buildBingo (Integer size) {
        try {
            String content = new String (Files.readAllBytes(Paths.get("colors/colors.json")));
            JSONArray arr = new JSONArray(content);
            ArrayList<String> cores = new ArrayList<>();
            Random rand = new Random();

            for (int i = 0; i < arr.length(); i++) {
                cores.add(arr.getString(i));
            }

            for ( int i = 0 ; i < size ; i++) {
                ArrayList<String> temp = new ArrayList<>();
                for ( int ii = 0 ; ii < size ; ii++ ) {
                    int randomIndex = Math.floorMod( rand.nextInt() , cores.size() - 1);
                    temp.add( cores.get(randomIndex) );
                    cores.remove(randomIndex);
                }
                grid.add(temp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printGrid () {
        System.out.println(grid + "\n");
    }

    public String getContent (int x, int y) {
        return grid.get(y).get(x);
    }

}