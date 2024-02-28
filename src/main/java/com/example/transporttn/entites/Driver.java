package com.example.transporttn.entites;

import javax.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "driver")
@Data
public class Driver implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private String numerotlf;
    @ManyToOne
    @JoinColumn(name="company_id", nullable=false)
    private Company company;
}
