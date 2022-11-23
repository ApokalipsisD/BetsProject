package com.bets.betsproject.service.api;

import com.bets.betsproject.model.GameStatus;

import java.util.List;

public interface GameStatusService {
    GameStatus saveGameStatus(GameStatus status);

    List<GameStatus> getAllStatuses();

    GameStatus getGameStatusById(Integer id);

    GameStatus updateGameStatus(GameStatus status, Integer id);

    void deleteGameStatus(Integer id);
}
