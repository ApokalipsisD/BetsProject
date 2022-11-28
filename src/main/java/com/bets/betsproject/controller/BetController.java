package com.bets.betsproject.controller;

import com.bets.betsproject.exception.ResourceNotFoundException;
import com.bets.betsproject.model.Bet;
import com.bets.betsproject.model.Match;
import com.bets.betsproject.service.api.BetService;
import com.bets.betsproject.service.api.BetStatusService;
import com.bets.betsproject.service.api.GameStatusService;
import com.bets.betsproject.service.api.MatchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/bet")
public class BetController {

    private final BetService betService;
    private final BetStatusService betStatusService;

    private final GameStatusService gameStatusService;
    private final MatchService matchService;

    public BetController(BetService betService, BetStatusService betStatusService, GameStatusService gameStatusService, MatchService matchService) {
        this.betService = betService;
        this.betStatusService = betStatusService;
        this.gameStatusService = gameStatusService;
        this.matchService = matchService;
    }

    @PostMapping()
    public ResponseEntity<Bet> saveBet(@RequestBody(required = false) Bet bet) {

        bet.setBetStatus(betStatusService.getBetStatusById(3));

        if (bet.getBet().compareTo(bet.getUser().getBalance()) > 0) {
            throw new ResourceNotFoundException("You don't have enough money");
        }
        if (Objects.nonNull(betService.getBetByUserAndMatchId(bet.getUser().getId(), bet.getMatch().getId()))) {
            throw new ResourceNotFoundException("You have already bet on this match");
        }

        if (!Objects.equals(bet.getMatch().getStatus().getName(), gameStatusService.getGameStatusById(1).getName())) {
            throw new ResourceNotFoundException("You can no longer bet on this match");
        }

        bet.getUser().setBalance(bet.getUser().getBalance().subtract(bet.getBet()));

        Bet newBet = new Bet(bet.getUser(), bet.getMatch(), bet.getBet(), bet.getTeam(), bet.getCoefficient(), betStatusService.getBetStatusById(3));

        return new ResponseEntity<>(betService.saveBet(newBet), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Bet> getAllBets() {
        return betService.getAllBets();
    }

    @GetMapping("myBets/{id}")
    public List<Bet> getBetsByUserId(@PathVariable("id") Integer userId) {
        List<Bet> list = betService.getBetsByUserId(userId);

        for (Bet bet : list) {
            Match match = matchService.getMatchById(bet.getMatch().getId());
            Integer winner;

            if (match.getStatus().getName().equals(gameStatusService.getGameStatusById(3).getName())
                    && Objects.isNull(bet.getEarnings())) {
                winner = match.getFirstTeamScore().equals(16)
                        ? match.getFirstTeam().getId()
                        : match.getSecondTeam().getId();

                if (winner.equals(bet.getTeam().getId())) {
                    bet.setEarnings(bet.getBet().multiply(bet.getCoefficient()).setScale(2, RoundingMode.HALF_UP));
                    bet.setBetStatus(betStatusService.getBetStatusById(1));
                } else {
                    bet.setEarnings(BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_UP));
                    bet.setBetStatus(betStatusService.getBetStatusById(2));
                }
                if (bet.getBetStatus().getName().equals(betStatusService.getBetStatusById(1).getName())) {
                    bet.getUser().setBalance(bet.getUser().getBalance().add(bet.getEarnings()).setScale(2, RoundingMode.HALF_UP));

                }
                betService.updateBet(bet, bet.getId());
            }
        }

        return list;
    }

    @GetMapping("{id}")
    public ResponseEntity<Bet> getBetById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(betService.getBetById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Bet> updateBet(@PathVariable("id") Integer id,
                                         @RequestBody Bet bet) {
        return new ResponseEntity<>(betService.updateBet(bet, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBet(@PathVariable("id") Integer id) {
        betService.deleteBet(id);
        return new ResponseEntity<>("Bet deleted successfully!", HttpStatus.OK);
    }
}
