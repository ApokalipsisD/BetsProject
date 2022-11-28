package com.bets.betsproject.controller;

import com.bets.betsproject.exception.ResourceNotFoundException;
import com.bets.betsproject.model.Bet;
import com.bets.betsproject.model.GameStatus;
import com.bets.betsproject.model.Match;
import com.bets.betsproject.model.User;
import com.bets.betsproject.service.api.BetService;
import com.bets.betsproject.service.api.GameStatusService;
import com.bets.betsproject.service.api.MatchService;
import com.bets.betsproject.service.api.UserService;
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

import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/matches")
public class MatchController {
    private final MatchService matchService;
    private final GameStatusService gameStatusService;
    private final BetService betService;
    private final UserService userService;
    private static final Integer startScore = 0;

    public MatchController(MatchService matchService, GameStatusService gameStatusService, BetService betService, UserService userService) {
        this.matchService = matchService;
        this.gameStatusService = gameStatusService;
        this.betService = betService;
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<Match> saveMatch(@RequestBody Match match) {
        Timestamp currentTime = new Timestamp(new Date().getTime());

        if (match.getFirstTeam().getName().equals(match.getSecondTeam().getName())) {
            throw new ResourceNotFoundException("Teams are the same");
        }

        GameStatus gameStatus = match.getDate().before(currentTime)
                ? gameStatusService.getGameStatusById(3) : match.getDate().after(currentTime)
                ? gameStatusService.getGameStatusById(1) : gameStatusService.getGameStatusById(2);

        Match newMatch = new Match();
        newMatch.setFirstTeam(match.getFirstTeam());
        newMatch.setSecondTeam(match.getSecondTeam());
        newMatch.setFirstCoefficient(match.getFirstCoefficient());
        newMatch.setSecondCoefficient(match.getSecondCoefficient());
        newMatch.setFirstTeamScore(startScore);
        newMatch.setSecondTeamScore(startScore);
        newMatch.setDate(match.getDate());
        newMatch.setStatus(gameStatus);
        newMatch.setGame(match.getGame());

        return new ResponseEntity<>(matchService.saveMatch(newMatch), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Match> getAllMatches() {
        List<Match> list = matchService.getAllMatches();
        Timestamp currentTime = new Timestamp(new Date().getTime());

        for (Match match : list) {
            if (match.getDate().after(currentTime) && !Objects.equals(match.getStatus().getName(), gameStatusService.getGameStatusById(1).getName())) {
                match.setStatus(gameStatusService.getGameStatusById(1));
                matchService.updateMatch(match, match.getId());
            }
            if (match.getDate().before(currentTime) && !Objects.equals(match.getStatus().getName(), gameStatusService.getGameStatusById(3).getName())) {
                match.setStatus(gameStatusService.getGameStatusById(3));
                if (((Math.random() <= 0.5) ? 1 : 2) == 1) {
                    match.setFirstTeamScore(16);
                    match.setSecondTeamScore((int) (Math.random() * 14));
                } else {
                    match.setFirstTeamScore((int) (Math.random() * 14));
                    match.setSecondTeamScore(16);
                }
                matchService.updateMatch(match, match.getId());
            }
        }
        return list;
    }

    @GetMapping("{id}")
    public ResponseEntity<Match> getMatchById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(matchService.getMatchById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Match> updateMatch(@PathVariable("id") Integer id,
                                             @RequestBody Match match) {
        return new ResponseEntity<>(matchService.updateMatch(match, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Map<String, Boolean>> deleteMatch(@PathVariable("id") Integer id) {

        List<Bet> betsByMatchIdList = betService.getBetsByMatchId(id);

        for (Bet bet : betsByMatchIdList) {
            if (Objects.isNull(bet.getEarnings())) {
                User user = bet.getUser();
                user.setBalance((user.getBalance().add(bet.getBet())).setScale(2, RoundingMode.HALF_UP));
                userService.updateUser(user, user.getId());
            }
            betService.deleteBet(bet.getId());
        }

        matchService.deleteMatch(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
