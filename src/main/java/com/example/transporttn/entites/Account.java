package com.example.transporttn.entites;

import com.example.transporttn.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Account")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;


    @OneToOne(mappedBy = "account")
    private Company company;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Account_Customer_id", referencedColumnName = "id")
    private Customer customer;


}
