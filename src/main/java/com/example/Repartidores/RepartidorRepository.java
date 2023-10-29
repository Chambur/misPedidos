package com.example.Repartidores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component

public interface RepartidorRepository extends JpaRepository<Repartidor, Integer>{
    
}
