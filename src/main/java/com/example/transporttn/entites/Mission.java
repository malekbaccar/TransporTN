package com.example.transporttn.entites;

import com.example.transporttn.enumeration.TransportStatus;
import javax.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "Transport")
@Data
public class Transport implements Serializable   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Company driver;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicule vehicle;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @Enumerated(EnumType.STRING)
    private TransportStatus status;

}
