package com.bets.betsproject.repository;

import com.bets.betsproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT u_a.id, u_a.login, u_a.password, u_a.name, u_a.surname, u_a.age, u_a.email, u_a.balance, u_a.role_id FROM user_account u_a where u_a.login=?1", nativeQuery = true)
    Optional<User> findByLogin(@Param("login") String login);

}
