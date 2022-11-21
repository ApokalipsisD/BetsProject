package com.bets.betsproject.service.api;

import com.bets.betsproject.model.Bet;

import java.util.List;

public interface BetService {
    Bet saveBet(Bet bet);

    List<Bet> getAllBets();

    Bet getBetById(Integer id);

    Bet updateBet(Bet bet, Integer id);

    void deleteBet(Integer id);

    void deleteBetByUserAndMatchId(Integer userId, Integer matchId);

//    void deleteBetByUserId(Integer userId);


}
