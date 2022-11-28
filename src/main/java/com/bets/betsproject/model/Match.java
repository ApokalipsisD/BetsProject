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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@Entity
@Table(name = "match")
@NoArgsConstructor
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "team_1")
    private Team firstTeam;

    @ManyToOne
    @JoinColumn(name = "team_2")
    private Team secondTeam;

    @Column(name = "coefficient_1", nullable = false)
    private BigDecimal firstCoefficient;

    @Column(name = "coefficient_2", nullable = false)
    private BigDecimal secondCoefficient;

    @Column(name = "score_1")
    private Integer firstTeamScore;

    @Column(name = "score_2")
    private Integer secondTeamScore;

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp date;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private GameStatus status;


    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    public Match(Team firstTeam, Team secondTeam, BigDecimal firstCoefficient, BigDecimal secondCoefficient, Integer firstTeamScore, Integer secondTeamScore, Timestamp date, GameStatus status, Game game) {
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
        this.firstCoefficient = firstCoefficient;
        this.secondCoefficient = secondCoefficient;
        this.firstTeamScore = firstTeamScore;
        this.secondTeamScore = secondTeamScore;
        this.date = date;
        this.status = status;
        this.game = game;
    }

    public Match(Integer id, Team firstTeam, Team secondTeam, BigDecimal firstCoefficient, BigDecimal secondCoefficient, Integer firstTeamScore, Integer secondTeamScore, Timestamp date, GameStatus status, Game game) {
        this.id = id;
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
        this.firstCoefficient = firstCoefficient;
        this.secondCoefficient = secondCoefficient;
        this.firstTeamScore = firstTeamScore;
        this.secondTeamScore = secondTeamScore;
        this.date = date;
        this.status = status;
        this.game = game;
    }
}
