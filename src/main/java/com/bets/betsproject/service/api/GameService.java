package com.bets.betsproject.service.api;

import com.bets.betsproject.model.Game;

import java.util.List;

public interface GameService {
    Game saveGame(Game game);

    List<Game> getAllGames();

    Game getGameById(Integer id);

    Game updateGame(Game game, Integer id);

    void deleteGame(Integer id);
}
