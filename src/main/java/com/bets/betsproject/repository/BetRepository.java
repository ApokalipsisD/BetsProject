package com.bets.betsproject.repository;

import com.bets.betsproject.model.Bet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BetRepository extends JpaRepository<Bet, Integer> {
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM user_bet_on_match WHERE user_id=:userId and match_id=:matchId", nativeQuery = true)
    void deleteBetByUserAndMatchId(@Param("userId") Integer userId, @Param("matchId") Integer matchId);


    //todo delete by match id


    @Query(value = "DELETE FROM user_bet_on_match WHERE user_id=:userId", nativeQuery = true)
    void deleteBetByUserId(@Param("userId") Integer userId);

    //todo find bets by user id

}
