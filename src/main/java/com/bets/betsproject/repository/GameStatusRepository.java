package com.bets.betsproject.repository;

import com.bets.betsproject.model.GameStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameStatusRepository extends JpaRepository<GameStatus, Integer> {
}
