package com.example.transporttn.entites;

import com.example.transporttn.enumeration.BookingStatus;

import javax.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "booking")
@Data
public class Booking implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pickupadress;
    private String deliveryAddresse;
    private LocalDateTime pickupDateTime;
    private LocalDateTime estimationDeliveryTime;
    private Double price;
    @ManyToOne
    @JoinColumn(name="customer_id", nullable=false)
    private Customer customer;
    @OneToMany(mappedBy="booking")
    private Set<Mission> missions;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;
}


