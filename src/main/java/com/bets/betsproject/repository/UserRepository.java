package com.bets.betsproject.repository;

import com.bets.betsproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
//    @Transactional
    @Query(value = "SELECT u_a.id, u_a.login, u_a.password, u_a.name, u_a.surname, u_a.age, u_a.email, u_a.balance, u_a.role_id FROM user_account u_a where u_a.login=?1", nativeQuery = true)
    Optional<User> findByLogin(@Param("login") String login);

//    @Query(value = "SELECT u_a.id, u_a.login, u_a.password, u_a.name, u_a.surname, u_a.age, u_a.email, u_a.balance, u_a.role_id FROM user_account u_a WHERE u_a.id=:id", nativeQuery = true)
//    User findByUserId(@Param("id") Integer id);

//    @Query("SELECT id, login, password, name, surname, age, email, balance, role_id FROM user_account where user_account.login=:login")
//    User findByLogin(String login);

//    @Transactional
//    @Modifying
//    @Query(value = "UPDATE Users u set EMAIL_VERIFICATION_STATUS =:emailVerificationStatus where u.USER_ID = :userId",
//            nativeQuery = true)
//    void updateUser(@Param("emailVerificationStatus") boolean emailVerificationStatus, @Param("userId") String userId);

}
