package com.example.transporttn.entites;

import com.example.transporttn.enumeration.ReservationStatus;

import javax.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
@Entity
@Table(name = "Réservation")
@Data
public class Réservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ramassageAdresse;
    private String livraisonAddresse;
    private LocalDateTime ramassageDateTime;
    private LocalDateTime estimationLivraisonTime;
    private Double price;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
}


