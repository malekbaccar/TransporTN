package com.example.transporttn.repositories;

import com.example.transporttn.entites.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("select a from Customer a where a.email like :email")
    Optional<Customer> findByEmail(String email);

}
