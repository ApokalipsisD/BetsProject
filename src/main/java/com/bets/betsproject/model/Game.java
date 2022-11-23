package com.bets.betsproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "game")
@NoArgsConstructor
public class Game {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "name", nullable = false)
    String name;

    public Game(String name) {
        this.name = name;
    }


//    CSGO(1), Valorant(2);

//    private final Integer id;
//
//    Game(Integer id) {
//        this.id = id;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public static Game getById(Integer id) {
//        return Arrays.stream(Game.values())
//                .filter(role -> Objects.equals(role.getId(), id))
//                .findFirst()
//                .orElse(null);
//    }
}
