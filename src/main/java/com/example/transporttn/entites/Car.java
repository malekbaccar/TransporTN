package com.example.transporttn.entites;


import javax.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "Vehicule")
@Data
public class Vehicule   implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String make;
    private String modelee;
    private int année;
    private String numeroSerie;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Company user; // Utilisateur auquel appartient ce véhicule
}
