package com.bets.betsproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

//@JsonDeserialize(as= Bet.class)

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "bet_status")
@NoArgsConstructor
public class BetStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "name", nullable = false)
    String name;

    public BetStatus(String name) {
        this.name = name;
    }

}
