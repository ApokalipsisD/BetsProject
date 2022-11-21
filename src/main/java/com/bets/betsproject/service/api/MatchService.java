package com.bets.betsproject.service.api;

import com.bets.betsproject.model.Match;

import java.util.List;

public interface MatchService {
    Match saveMatch(Match match);

    List<Match> getAllMatches();

    Match getMatchById(Integer id);

    Match updateMatch(Match team, Integer id);

    void deleteMatch(Integer id);

    //todo ask for 1 method update for all entity and for field
}
