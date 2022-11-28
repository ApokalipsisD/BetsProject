package com.bets.betsproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    @Column(name = "bet", nullable = false)
    private BigDecimal bet;

    @ManyToOne
    @JoinColumn(name = "team")
    private Team team;

    @Column(name = "coefficient", nullable = false)
    private BigDecimal coefficient;

    @ManyToOne
    @JoinColumn(name = "bet_status_id")
    private BetStatus betStatus;

    @Column(name = "earnings")
    private BigDecimal earnings;

    public Bet(User user, Match match, BigDecimal bet, Team team, BigDecimal coefficient, BetStatus betStatus) {
        this.user = user;
        this.match = match;
        this.bet = bet;
        this.team = team;
        this.coefficient = coefficient;
        this.betStatus = betStatus;
    }

    public Bet(User user, Match match, BigDecimal bet, Team team, BigDecimal coefficient, BetStatus betStatus, BigDecimal earnings) {
        this.user = user;
        this.match = match;
        this.bet = bet;
        this.team = team;
        this.coefficient = coefficient;
        this.betStatus = betStatus;
        this.earnings = earnings;
    }

    public Bet(Integer id, User user, Match match, BigDecimal bet, Team team, BigDecimal coefficient, BetStatus betStatus, BigDecimal earnings) {
        this.id = id;
        this.user = user;
        this.match = match;
        this.bet = bet;
        this.team = team;
        this.coefficient = coefficient;
        this.betStatus = betStatus;
        this.earnings = earnings;
    }
}
