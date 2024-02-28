package com.example.transporttn.repositories;

import com.example.transporttn.entites.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Account, Long> {
    @Query("select a from Account a where a.email like :email")
    Account findByEmail(String email);


}
