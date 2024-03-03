package com.example.transporttn.repositories;

import com.example.transporttn.entites.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query("select a from Company a where a.email like :email")
    Optional<Company> findByEmail(String email);

    @Query("select c from Company c where c.account.id = :accountId")
    Optional<Company> findByAccountId(@Param("accountId") Long accountId);
}
