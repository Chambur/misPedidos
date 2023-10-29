package com.example.Repartidores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Coches.coche;
import com.example.Coches.cocheRepository;
import com.example.misPedidos.Pedido;
import com.example.misPedidos.PedidoRepository;

import ch.qos.logback.core.joran.conditional.IfAction;

@Controller
@Service

public class RepartidorControlador {

    @Autowired
    private RepartidorRepository repartidorreporsitorio;
    @Autowired
    private cocheRepository misCochesrepositorio;
    @Autowired
    private PedidoRepository miPedidoRepositorio;


    @GetMapping("repartidores")
    public String misRepartidores(Model model) {
        List<Repartidor> misRepartidores = repartidorreporsitorio.findAll();
        model.addAttribute("repartidores", misRepartidores);
        return "repartidores";
    }

    @GetMapping("repartidores/nuevo")
    public String verRepartidores(Model model) {
        List<coche> misCoches = misCochesrepositorio.findAll();
        model.addAttribute("coches", misCoches);
        return "nuevoRepartidor";
    }

    @PostMapping("repartidores/nuevo")
    public String nuevoRepartidor(Repartidor nuevoRepartidor) {
        repartidorreporsitorio.save(nuevoRepartidor);
        return "redirect:/repartidores";
    }

    @PostMapping("/repartidores/eliminar")
public String eliminarRegistro(@RequestParam("id") Long id) {
    // Busca el registro con el ID especificado en la base de datos
    Optional<Repartidor> optionalRepartidor = repartidorreporsitorio.findById(id.intValue());
    List<Pedido> todoslosPedidos = miPedidoRepositorio.findAll();
    for (Pedido pedido : todoslosPedidos) {
        // Hacer algo con cada objeto Pedido en la lista
        if (pedido.getRepartidor() != null && pedido.getRepartidor().getIdRepartidor() == optionalRepartidor.orElse(null).getIdRepartidor()) {
            // Hacer algo si el pedido está asignado a este repartidor
            pedido.setRepartidor(null);
            miPedidoRepositorio.save(pedido);
        }
    }
    // Si el registro existe, elimínalo
    if (optionalRepartidor.isPresent()) {
        Repartidor miRepartidor = optionalRepartidor.get();
        repartidorreporsitorio.delete(miRepartidor);
    }
    // Redirige al usuario a otra página una vez que se ha eliminado el registro
    return "redirect:/repartidores";
}




}
