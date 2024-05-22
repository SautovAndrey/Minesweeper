package com.example.minesweeper.Service;

import com.example.minesweeper.GameLogic.Game;
import com.example.minesweeper.Modules.GameInfoResponse;
import com.example.minesweeper.Modules.GameTurnRequest;
import com.example.minesweeper.Modules.NewGameRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
@Service
public class MinesweeperService {

    private Map<String, Game> games = new HashMap<>();

    public GameInfoResponse startNewGame(NewGameRequest request) {
        if (request.getWidth() <= 0 || request.getHeight() <= 0 || request.getMinesCount() <= 0 || request.getWidth() > 30 || request.getHeight() > 30 || request.getMinesCount() >= request.getWidth() * request.getHeight()) {
            throw new IllegalArgumentException("Некорректные параметры игры");
        }

        Game game = new Game(request.getWidth(), request.getHeight(), request.getMinesCount());
        String gameId = UUID.randomUUID().toString();
        games.put(gameId, game);

        return createGameInfoResponse(gameId, game);
    }

    public GameInfoResponse makeMove(GameTurnRequest request) {
        Game game = games.get(request.getGameId());
        if (game == null) {
            throw new IllegalArgumentException("Игра не найдена");
        }

        game.makeMove(request.getRow(), request.getCol());

        return createGameInfoResponse(request.getGameId(), game);
    }

    private GameInfoResponse createGameInfoResponse(String gameId, Game game) {
        GameInfoResponse response = new GameInfoResponse();
        response.setGameId(gameId);
        response.setWidth(game.getWidth());
        response.setHeight(game.getHeight());
        response.setMinesCount(game.getMinesCount());
        response.setCompleted(game.isCompleted());
        response.setField(game.getField());

        return response;
    }
}