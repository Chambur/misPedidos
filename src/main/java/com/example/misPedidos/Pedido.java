package com.example.misPedidos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.Coches.coche;
import com.example.Repartidores.Repartidor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Data
@Entity

public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer id;
    private String nombre;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaPedido;
    //private String repartidor;
    private int cantidad;
    private String peso;

    @ManyToOne
    private Repartidor repartidor;

    @ManyToOne
    private coche coche;
    
    @PrePersist
    private void asignarFechaRegistro(){
        fechaRegistro = LocalDateTime.now();
    }
}
