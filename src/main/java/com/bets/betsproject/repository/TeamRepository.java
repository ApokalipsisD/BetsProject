package com.bets.betsproject.repository;

import com.bets.betsproject.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
    @Query(value = "SELECT id, name FROM team where name=:teamName", nativeQuery = true)
    Team findByTeamName(@Param("teamName") String teamName);

}
