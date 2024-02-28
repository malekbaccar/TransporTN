package com.example.transporttn.entites;


import javax.persistence.*;

import com.example.transporttn.enumeration.VehicleType;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "Vehicule")
@Data
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String make;
    private String modelee;
    private int année;
    private String numeroSerie;

    @ManyToOne
    @JoinColumn(name = "companies", nullable = false)
    private Company company;
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;


}
