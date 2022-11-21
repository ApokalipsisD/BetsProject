package com.bets.betsproject.model;

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

@Data
@Entity
@Table(name = "match")
@NoArgsConstructor
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @Column(name = "team_1", nullable = false)
    @ManyToOne
    @JoinColumn(name = "team_1")
    private Team firstTeam;

//    @Column(name = "team_2", nullable = false)
    @ManyToOne
    @JoinColumn(name = "team_2")
    private Team secondTeam;

    @Column(name = "coefficient_1", nullable = false)
    private BigDecimal firstCoefficient;

    @Column(name = "coefficient_2", nullable = false)
    private BigDecimal secondCoefficient;

    @Column(name = "score_1", nullable = false)
    private Integer firstTeamScore;

    @Column(name = "score_2", nullable = false)
    private Integer secondTeamScore;

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp date;

    @Column(name = "status_id", nullable = false)
    private Integer statusId;

//    @Column(name = "game_id", nullable = false)
//    @Enumerated(EnumType.ORDINAL)
    @Column(name="game_id")
    private Integer gameId;

//    public Match(Integer firstTeam, Integer secondTeam, BigDecimal firstCoefficient, BigDecimal secondCoefficient, Integer firstTeamScore, Integer secondTeamScore, Timestamp date, Integer statusId, Integer gameId) {
//        this.firstTeam = firstTeam;
//        this.secondTeam = secondTeam;
//        this.firstCoefficient = firstCoefficient;
//        this.secondCoefficient = secondCoefficient;
//        this.firstTeamScore = firstTeamScore;
//        this.secondTeamScore = secondTeamScore;
//        this.date = date;
//        this.statusId = statusId;
//        this.gameId = gameId;
//    }


    public Match(Team firstTeam, Team secondTeam, BigDecimal firstCoefficient, BigDecimal secondCoefficient, Integer firstTeamScore, Integer secondTeamScore, Timestamp date, Integer statusId, Integer gameId) {
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
        this.firstCoefficient = firstCoefficient;
        this.secondCoefficient = secondCoefficient;
        this.firstTeamScore = firstTeamScore;
        this.secondTeamScore = secondTeamScore;
        this.date = date;
        this.statusId = statusId;
        this.gameId = gameId;
    }
}
