package com.example.Clientes;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Data
@Entity
public class Clientes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer id;
    private String nombrecliente;
    private String telefonocliente;
    private String direccioncliente;

}
