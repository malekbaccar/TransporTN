package com.example.transporttn.entites;

import javax.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "vehiculeDisponible")
@Data
public class vehiculeDisponible implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String make;
    private String modelee;
    private int année;
    private String numeroSerie;
}
