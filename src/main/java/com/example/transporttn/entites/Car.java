package com.example.transporttn.entites;


import com.example.transporttn.enumeration.VehicleType;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Car")
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
