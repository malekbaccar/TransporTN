package com.example.transporttn.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Entity
@Table(name = "Company")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Company implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "email")
    private String email;

    @Column(name = "numTEL")
    private String numTEL;

    @Column(name = "address")
    private String address;

    @Column(name = "password")
    private String password;

    @OneToOne
    private Account account;
    @OneToMany(mappedBy = "company")
    private Set<Driver> drivers;
    @OneToMany(mappedBy = "company")
    private Set<Car> cars;
    @OneToMany(mappedBy = "company")
    private Set<Mission> missions;


}
