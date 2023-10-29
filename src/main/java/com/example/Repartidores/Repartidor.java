package com.example.Repartidores;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity

public class Repartidor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer idRepartidor;
    private String nombreRepartidor;
    private String dniRepartidor;
    private int telefonoRepartidor;
    private String cocheRepartidor;
    private String nombre;

    public Repartidor(String nombre) {
        this.nombre = nombre;
    }
    public Repartidor() {}

    

}
