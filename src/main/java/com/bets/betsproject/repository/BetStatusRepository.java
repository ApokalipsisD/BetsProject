package com.bets.betsproject.repository;

import com.bets.betsproject.model.BetStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BetStatusRepository extends JpaRepository<BetStatus, Integer> {
}
