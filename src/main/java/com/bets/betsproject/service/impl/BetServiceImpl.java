package com.bets.betsproject.service.impl;

import com.bets.betsproject.exception.ResourceNotFoundException;
import com.bets.betsproject.model.Bet;
import com.bets.betsproject.repository.BetRepository;
import com.bets.betsproject.service.api.BetService;
import com.bets.betsproject.service.api.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BetServiceImpl implements BetService {

    private final BetRepository betRepository;
    private final UserService userService;

    public BetServiceImpl(BetRepository betRepository, UserService userService) {
        this.betRepository = betRepository;
        this.userService = userService;
    }

    @Transactional
    @Override
    public Bet saveBet(Bet bet) {
        userService.updateUser(bet.getUser(), bet.getUser().getId());
        return betRepository.save(bet);
    }

    @Override
    public List<Bet> getAllBets() {
        return betRepository.findAll();
    }

    @Override
    public List<Bet> getBetsByUserId(Integer userId) {
        return betRepository.findBetsByUserId(userId);
    }

    @Override
    public List<Bet> getBetsByMatchId(Integer matchId) {
        return betRepository.findBetsByMatchId(matchId);
    }

    @Override
    public Bet getBetById(Integer id) {
        return betRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Bet", "Id", id)
        );
    }

    @Override
    public Bet getBetByUserAndMatchId(Integer userId, Integer matchId) {
        return betRepository.findBetByUserIdAndMatchId(userId, matchId);
    }

    @Transactional
    @Override
    public Bet updateBet(Bet bet, Integer id) {
        userService.updateUser(bet.getUser(), bet.getUser().getId());
        Bet newBet = betRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bet", "Id", id));
        newBet.setUser(bet.getUser());
        newBet.setMatch(bet.getMatch());
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
    public void deleteBetsByUserId(Integer userId) {
        betRepository.deleteBetsByUserId(userId);
    }

    @Override
    public void deleteBetByUserAndMatchId(Integer userId, Integer matchId) {
        betRepository.deleteBetByUserAndMatchId(userId, matchId);
    }

}
