package com.bets.betsproject.service.impl;

import com.bets.betsproject.exception.ResourceNotFoundException;
import com.bets.betsproject.model.Bet;
import com.bets.betsproject.repository.BetRepository;
import com.bets.betsproject.service.api.BetService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BetServiceImpl implements BetService {

    private final BetRepository betRepository;

    public BetServiceImpl(BetRepository betRepository) {
        this.betRepository = betRepository;
    }

    @Override
    public Bet saveBet(Bet bet) {
        return betRepository.save(bet);
    }

    @Override
    public List<Bet> getAllBets() {
        return betRepository.findAll();
    }

    @Override
    public Bet getBetById(Integer id) {
        return betRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Bet", "Id", id)
        );
    }

    @Override
    public Bet updateBet(Bet bet, Integer id) {
        Bet newBet = betRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bet", "Id", id));
        newBet.setUserId(bet.getUserId());
        newBet.setMatchId(bet.getMatchId());
        newBet.setBet(bet.getBet());
        newBet.setTeam(bet.getTeam());
        newBet.setCoefficient(bet.getCoefficient());
        newBet.setBetStatus(bet.getBetStatus());
        newBet.setEarnings(bet.getEarnings());

        betRepository.save(newBet);
        return newBet;
    }

    @Override
    public void deleteBet(Integer id) {
        betRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bet", "Id", id));
        betRepository.deleteById(id);
    }

    @Override
    public void deleteBetByUserAndMatchId(Integer userId, Integer matchId) {
        betRepository.deleteBetByUserAndMatchId(userId, matchId);
    }

//    @Override
//    public void deleteBetByUserId(Integer userId) {
//        betRepository.deleteBetByUserId(userId);
//    }
}
