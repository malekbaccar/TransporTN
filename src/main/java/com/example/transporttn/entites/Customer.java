package com.example.transporttn.entites;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "_user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "firstname")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "email")
    private String email;
    @Column(name = "longitude")
    private String Longitude;
    @Column(name = "lattitude")
    private String lattitude;
    @OneToOne(mappedBy = "customer")
    private Account account;
    @OneToMany(mappedBy="customer")
    private Set<Booking> bookings;

}
