package com.example.Clientes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component

public interface clientesRepository extends JpaRepository<Clientes, Integer> {
    
}

