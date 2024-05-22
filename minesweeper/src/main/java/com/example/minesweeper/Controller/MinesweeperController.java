package com.example.minesweeper.Controller;

import com.example.minesweeper.Modules.GameInfoResponse;
import com.example.minesweeper.Modules.GameTurnRequest;
import com.example.minesweeper.Modules.NewGameRequest;
import com.example.minesweeper.Service.MinesweeperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MinesweeperController {

    @Autowired
    private MinesweeperService service;

    @PostMapping("/new")
    public ResponseEntity<?> startNewGame(@RequestBody NewGameRequest request) {
        try {
            GameInfoResponse response = service.startNewGame(request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CustomErrorResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new CustomErrorResponse("Ошибка при создании игры"));
        }
    }

    @PostMapping("/turn")
    public ResponseEntity<?> makeMove(@RequestBody GameTurnRequest request) {
        try {
            GameInfoResponse response = service.makeMove(request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CustomErrorResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new CustomErrorResponse("Ошибка при выполнении хода"));
        }
    }
}