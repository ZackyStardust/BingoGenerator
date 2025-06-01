package com.zackystardust.controller;

import com.zackystardust.service.BingoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/bingo")
public class BingoController {
    private final BingoService bingoService;
    private static final Integer MAX_GRID_SIZE = 5;

    public BingoController(BingoService bingoService) {
        this.bingoService = bingoService;
    }

    @GetMapping
    public ResponseEntity<?> getBingoGrid(@RequestParam(required = false) Integer gridSize) throws IOException {
        if (gridSize == null || gridSize == 0)
            gridSize = 3;
        if (gridSize >= MAX_GRID_SIZE) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Grid size cannot exceed " + MAX_GRID_SIZE);
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(response);
        }
        return ResponseEntity.ok(bingoService.getBingoGrid(gridSize));
    }

}
