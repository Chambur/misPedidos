package com.example.misPedidos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    
    
}
