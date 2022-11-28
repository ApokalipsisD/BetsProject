package com.bets.betsproject.service.api;

import com.bets.betsproject.model.BetStatus;

import java.util.List;

public interface BetStatusService {
    BetStatus saveBetStatus(BetStatus status);

    List<BetStatus> getAllBetStatuses();

    BetStatus getBetStatusById(Integer id);

    BetStatus updateBetStatus(BetStatus game, Integer id);

    void deleteBetStatus(Integer id);
}
