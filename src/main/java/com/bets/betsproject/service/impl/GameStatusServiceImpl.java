package com.bets.betsproject.service.impl;

import com.bets.betsproject.exception.ResourceNotFoundException;
import com.bets.betsproject.model.GameStatus;
import com.bets.betsproject.repository.GameStatusRepository;
import com.bets.betsproject.service.api.GameStatusService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameStatusServiceImpl implements GameStatusService {
    private final GameStatusRepository statusRepository;

    public GameStatusServiceImpl(GameStatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public GameStatus saveGameStatus(GameStatus status) {
        return statusRepository.save(status);
    }

    @Override
    public List<GameStatus> getAllStatuses() {
        return statusRepository.findAll();
    }

    @Override
    public GameStatus getGameStatusById(Integer id) {
        return statusRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Status", "Id", id)
        );
    }

    @Override
    public GameStatus updateGameStatus(GameStatus status, Integer id) {
        GameStatus newStatus = statusRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Status", "Id", id));
        newStatus.setName(status.getName());
        statusRepository.save(newStatus);
        return newStatus;
    }

    @Override
    public void deleteGameStatus(Integer id) {
        statusRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Status", "Id", id));
        statusRepository.deleteById(id);
    }
}
