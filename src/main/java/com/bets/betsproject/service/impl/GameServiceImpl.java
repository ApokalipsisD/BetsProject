package com.bets.betsproject.service.impl;

import com.bets.betsproject.exception.ResourceNotFoundException;
import com.bets.betsproject.model.Game;
import com.bets.betsproject.repository.GameRepository;
import com.bets.betsproject.service.api.GameService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @Override
    public Game getGameById(Integer id) {
        return gameRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Game", "Id", id)
        );
    }

    @Override
    public Game updateGame(Game game, Integer id) {
        Game newGame = gameRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Game", "Id", id));
        newGame.setName(game.getName());
        gameRepository.save(newGame);
        return newGame;
    }

    @Override
    public void deleteGame(Integer id) {
        gameRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Game", "Id", id));
        gameRepository.deleteById(id);
    }
}
