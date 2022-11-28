package com.bets.betsproject.repository;

import com.bets.betsproject.model.Bet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BetRepository extends JpaRepository<Bet, Integer> {
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM user_bet_on_match WHERE user_id=:userId and match_id=:matchId", nativeQuery = true)
    void deleteBetByUserAndMatchId(@Param("userId") Integer userId, @Param("matchId") Integer matchId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM user_bet_on_match WHERE user_id=:userId", nativeQuery = true)
    void deleteBetsByUserId(@Param("userId") Integer userId);

    @Query(value = "DELETE FROM user_bet_on_match WHERE user_id=:userId", nativeQuery = true)
    void deleteBetByUserId(@Param("userId") Integer userId);

    @Query(value = "SELECT id, user_id, match_id, bet, team, coefficient, bet_status_id, earnings FROM user_bet_on_match WHERE user_id=:userId and match_id=:matchId", nativeQuery = true)
    Bet findBetByUserIdAndMatchId(@Param("userId") Integer userId, @Param("matchId") Integer matchId);

    @Query(value = "SELECT id, user_id, match_id, bet, team, coefficient, bet_status_id, earnings FROM user_bet_on_match WHERE user_id=? order by bet_status_id desc", nativeQuery = true)
    List<Bet> findBetsByUserId(@Param("userId") Integer userId);

    @Query(value = "SELECT id, user_id, match_id, bet, team, coefficient, bet_status_id, earnings FROM user_bet_on_match WHERE match_id=? order by bet_status_id desc", nativeQuery = true)
    List<Bet> findBetsByMatchId(@Param("matchId") Integer matchId);

}
