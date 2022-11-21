package com.bets.betsproject.repository;

import com.bets.betsproject.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<Match, Integer> {

    //todo delete with bet

//    Match updateMatchStatus(Match match);
}
