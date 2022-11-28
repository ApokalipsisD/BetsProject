package com.bets.betsproject.service.impl;

import com.bets.betsproject.exception.ResourceNotFoundException;
import com.bets.betsproject.model.BetStatus;
import com.bets.betsproject.repository.BetStatusRepository;
import com.bets.betsproject.service.api.BetStatusService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BetStatusServiceImpl implements BetStatusService {

    private final BetStatusRepository betStatusRepository;

    public BetStatusServiceImpl(BetStatusRepository betStatusRepository) {
        this.betStatusRepository = betStatusRepository;
    }


    @Override
    public BetStatus saveBetStatus(BetStatus status) {
        return betStatusRepository.save(status);
    }

    @Override
    public List<BetStatus> getAllBetStatuses() {
        return betStatusRepository.findAll();
    }

    @Override
    public BetStatus getBetStatusById(Integer id) {
        return betStatusRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("BetStatus", "Id", id)
        );
    }

    @Override
    public BetStatus updateBetStatus(BetStatus game, Integer id) {
        BetStatus newBetStatus = betStatusRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("BetStatus", "Id", id));
        newBetStatus.setName(game.getName());
        betStatusRepository.save(newBetStatus);
        return newBetStatus;
    }

    @Override
    public void deleteBetStatus(Integer id) {
        betStatusRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("BetStatus", "Id", id));
        betStatusRepository.deleteById(id);

    }
}
