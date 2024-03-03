package com.example.transporttn.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Account")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private Role role;

//    @JoinColumn(name = "id", referencedColumnName = "id_account")
//    @OneToOne(mappedBy = "account")
//    private Company company;

//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "id", referencedColumnName = "id_account")
//    private Customer customer;


}
