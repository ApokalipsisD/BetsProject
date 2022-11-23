package com.bets.betsproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
//@JsonDeserialize(as=UserDetails.class)
@Data
@Entity(name = "user_account")
@Table(name = "user_account")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "login", nullable = false)
    @Pattern(regexp = "^[\\w.-]{3,20}[0-9a-zA-Z]$",
            message = "Incorrect login (login must be greater than 3 and less than 20 and must not contain inaccessible characters)")
    private String login;
    @Column(name = "password", nullable = false)
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}",
            message = "Incorrect password (password must contain at least one number, one lowercase and one uppercase letter, minimum password length 8)")
    private String password;
    @Column(name = "name")
    @Pattern(regexp = "^([А-Я][а-яё]{2,20}|[A-Z][a-z]{2,20})$",
            message = "Name must start with an uppercase letter and contain letters of only one language")
    private String name;
    @Column(name = "surname")
    @Pattern(regexp = "^([А-Я][а-яё]{2,20}|[A-Z][a-z]{2,20})$",
            message = "Name must start with an uppercase letter and contain letters of only one language")
    private String surname;
    @Column(name = "age")
    @Max(value = 150, message = "Incorrect age")
    private Integer age;
    @Column(name = "email")
    @Pattern(regexp = "^([a-zA-Z0-9_-]+\\.)*[a-zA-Z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$",
            message = "Incorrect email")
    private String email;
    @Column(name = "balance", nullable = false)
    @DecimalMin(value = "0.0")
    @Digits(integer = 10, fraction = 2, message = "Invalid balance")
    private BigDecimal balance;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;


    public User() {
    }

//    public User(String login, String password, BigDecimal balance, Integer roleId) {
//        this.login = login;
//        this.password = password;
//        this.balance = balance;
//        this.role = Role.getById(roleId);
//    }

//    public User(String login, String password, String name, String surname, Integer age, String email, BigDecimal balance, Integer roleId) {
//        this.login = login;
//        this.password = password;
//        this.name = name;
//        this.surname = surname;
//        this.age = age;
//        this.email = email;
//        this.balance = balance;
//        this.role = Role.getById(roleId);
//    }

//    public User(Integer id, String login, String password, String name, String surname, Integer age, String email, BigDecimal balance, Integer roleId) {
//        this.id = id;
//        this.login = login;
//        this.password = password;
//        this.name = name;
//        this.surname = surname;
//        this.age = age;
//        this.email = email;
//        this.balance = balance;
//        this.role = Role.getById(roleId);
//    }
    public User(Integer id, String login, String password, String name, String surname, Integer age, String email, BigDecimal balance, Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.balance = balance;
        this.role = role;
    }
    public User(String login, String password, String name, String surname, Integer age, String email, BigDecimal balance, Role role) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.balance = balance;
        this.role = role;
    }




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        return Collections.emptyList();
    }

    @Override
    public String getUsername() {
        return getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
