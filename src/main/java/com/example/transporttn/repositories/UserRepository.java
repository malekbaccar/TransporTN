package com.example.transporttn.repositories;

import com.example.transporttn.entites.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Account, Long> {
    @Query("select a from Account a where a.email like :email")
    Optional<Account> findByEmail(String email);

    @Query("select a from Account a where a.email like :email")
    Account findByEmail2(String email);

    @Query("select a.password from Account a where a.password = :password")
    String findPassword(String password);
//    @Query("SELECT u FROM Account u WHERE u.password = :password")
//    boolean existsByPassword(String password);

//    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Account u WHERE u.password = :password")
//    boolean existsAccountByPassword(String password);


}
