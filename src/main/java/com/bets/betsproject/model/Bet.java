package com.bets.betsproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@Entity
@Table(name = "user_bet_on_match")
@NoArgsConstructor
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "match_id", nullable = false)
    private Integer matchId;

    @Column(name = "bet", nullable = false)
    private BigDecimal bet;

    @ManyToOne
    @JoinColumn(name = "team")
    private Team team;

    @Column(name = "coefficient", nullable = false)
    private BigDecimal coefficient;

    @Column(name = "bet_status_id", nullable = false)
    private Integer betStatus;

    @Column(name = "earnings")
    private BigDecimal earnings;

    public Bet(Integer userId, Integer matchId, BigDecimal bet, Team team, BigDecimal coefficient, Integer betStatus) {
        this.userId = userId;
        this.matchId = matchId;
        this.bet = bet;
        this.team = team;
        this.coefficient = coefficient;
        this.betStatus = betStatus;
    }

    public Bet(Integer userId, Integer matchId, BigDecimal bet, Team team, BigDecimal coefficient, Integer betStatus, BigDecimal earnings) {
        this.userId = userId;
        this.matchId = matchId;
        this.bet = bet;
        this.team = team;
        this.coefficient = coefficient;
        this.betStatus = betStatus;
        this.earnings = earnings;
    }

    public Bet(Integer id, Integer userId, Integer matchId, BigDecimal bet, Team team, BigDecimal coefficient, Integer betStatus, BigDecimal earnings) {
        this.id = id;
        this.userId = userId;
        this.matchId = matchId;
        this.bet = bet;
        this.team = team;
        this.coefficient = coefficient;
        this.betStatus = betStatus;
        this.earnings = earnings;
    }
}
