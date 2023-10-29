package com.example.Coches;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity

public class coche {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer idCoches;
    private String nombreCoche;
    private int kilometrosCoche;
    private int gasolinaCoche;
    private String matriculaCoche;


}
