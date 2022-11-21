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
@Table(name = "role")
@NoArgsConstructor
public class Role {
//    USER(1), ADMIN(2);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    public Role(String name) {
        this.name = name;
    }

    //    public static Role getById(Integer id) {
//        return Arrays.stream(Role.values())
//                .filter(role -> Objects.equals(role.getId(), id))
//                .findFirst()
//                .orElse(null);
//    }
//    public static List<Role> valuesAsList() {
//        return Arrays.asList(values());
//    }
//
//    public static Role of(Integer roleId) {
//        return Stream.of(Role.values())
//                .filter(p -> Objects.equals(p.getId(), roleId))
//                .findFirst()
//                .orElseThrow(IllegalArgumentException::new);
//    }
}
