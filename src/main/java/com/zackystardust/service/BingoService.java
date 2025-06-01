package com.zackystardust.service;

import com.zackystardust.model.BingoSelect;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class BingoService {

    private final BingoSelect bingoSelect;

    public BingoService(BingoSelect bingoSelect) {
        this.bingoSelect = bingoSelect;
    }

    public ArrayList<ArrayList<String>> getBingoGrid(int gridSize) throws IOException {
        bingoSelect.buildBingo(gridSize);
        return bingoSelect.GetArray();
    }

}
