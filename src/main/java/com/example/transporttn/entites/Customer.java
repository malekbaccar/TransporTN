package com.example.transporttn.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Customer")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;


    @Column(name = "name")
    private String name;


    @Column(name = "lastname")
    private String lastname;

    @Column(name = "email")
    private String email;

    private String Longitude;
    private String lattitude;


    @OneToOne
    private Account account;

    @OneToMany(mappedBy = "customer")
    private Set<Booking> bookings;

}
