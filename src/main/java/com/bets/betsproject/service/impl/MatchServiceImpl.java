package com.bets.betsproject.service.impl;

import com.bets.betsproject.exception.ResourceNotFoundException;
import com.bets.betsproject.model.Match;
import com.bets.betsproject.repository.MatchRepository;
import com.bets.betsproject.service.api.MatchService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;

    public MatchServiceImpl(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public Match saveMatch(Match match) {
        return matchRepository.save(match);
    }

    @Override
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    @Override
    public Match getMatchById(Integer id) {
        return matchRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Match", "Id", id)
        );
    }

    @Override
    public Match updateMatch(Match team, Integer id) {
        Match newMatch = matchRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Match", "Id", id));
        newMatch.setFirstTeam(team.getFirstTeam());
        newMatch.setSecondTeam(team.getSecondTeam());
        newMatch.setFirstCoefficient(team.getFirstCoefficient());
        newMatch.setSecondCoefficient(team.getSecondCoefficient());
        newMatch.setFirstTeamScore(team.getFirstTeamScore());
        newMatch.setSecondTeamScore(newMatch.getSecondTeamScore());
        newMatch.setDate(team.getDate());
        newMatch.setStatusId(team.getStatusId());
        newMatch.setGameId(team.getGameId());
        return newMatch;
    }

    @Override
    public void deleteMatch(Integer id) {
        matchRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Match", "Id", id));
        matchRepository.deleteById(id);
    }
}
