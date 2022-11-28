package com.bets.betsproject.service.api;

import com.bets.betsproject.model.Bet;

import java.util.List;

public interface BetService {
    Bet saveBet(Bet bet);

    List<Bet> getAllBets();

    List<Bet> getBetsByUserId(Integer userId);

    List<Bet> getBetsByMatchId(Integer matchId);

    Bet getBetById(Integer id);

    Bet getBetByUserAndMatchId(Integer userId, Integer matchId);

    Bet updateBet(Bet bet, Integer id);

    void deleteBet(Integer id);

    void deleteBetsByUserId(Integer userId);

    void deleteBetByUserAndMatchId(Integer userId, Integer matchId);

}
