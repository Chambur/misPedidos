package com.example.misPedidos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Clientes.Clientes;
import com.example.Clientes.clientesRepository;
import com.example.Coches.coche;
import com.example.Coches.cocheRepository;
import com.example.Repartidores.Repartidor;
import com.example.Repartidores.RepartidorRepository;

@Controller()
public class miControlador {

    @Autowired
    private PedidoRepository pedidorespository;
    @Autowired
    private clientesRepository clientesRepositorio;
    @Autowired
    private RepartidorRepository repartidoresRepositorio;
    @Autowired
    private cocheRepository cochesrepositorio;

    @GetMapping("/")
    public String index(Model model) {
        List<Pedido> pedidos = pedidorespository.findAll();
        List<Repartidor> misRepartidores = repartidoresRepositorio.findAll();
        model.addAttribute("repartidores", misRepartidores);
        model.addAttribute("pedidos", pedidos);
        return "index";
    }

    @GetMapping("pedidos/nuevopedido")
    public String nuevo(Model model) {
        model.addAttribute("pedido", new Pedido());
        List<Clientes> misClientes = clientesRepositorio.findAll();
        List<Repartidor> misRepartidores = repartidoresRepositorio.findAll();
        List<coche> misCoches = cochesrepositorio.findAll();

        model.addAttribute("repartidores", misRepartidores);
        model.addAttribute("clientes", misClientes);
        model.addAttribute("coches", misCoches);
        return "nuevoPedido";
    }

    @PostMapping("pedidos/nuevopedido")
    public String nuevo(Pedido pedido, @RequestParam("idRepartidor") Integer repartidorId,@RequestParam("idCoches") Integer idCoches) {
    Repartidor repartidor = repartidoresRepositorio.findById(repartidorId).orElseThrow(() -> new IllegalArgumentException("Repartidor no encontrado con id: " + repartidorId));
   
    coche coche = cochesrepositorio.findById(idCoches).orElseThrow(() -> new IllegalArgumentException("Coche no encontrado con id: " + idCoches));
    
    pedido.setRepartidor(repartidor);
    pedido.setCoche(coche);
    pedidorespository.save(pedido);
    return "redirect:/";
}

    // @PostMapping("pedidos/nuevopedido")
    // public String nuevo(Pedido pedido) {
    //     pedidorespository.save(pedido);
    //     return "redirect:/";
    // }

    @PostMapping("eliminar")
    public String eliminarRegistro(@RequestParam("id") Long id) {
        // Busca el registro con el ID especificado en la base de datos
        Optional<Pedido> optionalPedido = pedidorespository.findById(id.intValue());
    
        // Si el registro existe, elimínalo
        if (optionalPedido.isPresent()) {
            Pedido pedido = optionalPedido.get();
            pedidorespository.delete(pedido);
        }
        // Redirige al usuario a otra página una vez que se ha eliminado el registro
        return "redirect:/";
    }

    @PostMapping("modificar")
    public String modificarPedido(@RequestParam("id") Long id, Model model){
        // Busca el registro con el ID especificado en la base de datos
        Optional<Pedido> optionalPedido = pedidorespository.findById(id.intValue());
        List<Clientes> misClientes = clientesRepositorio.findAll();
        List<Repartidor> misRepartidores = repartidoresRepositorio.findAll();
        List<coche> misCoches = cochesrepositorio.findAll();

        //optionalPedido.ifPresent(pedido -> System.out.println("El pedido es: " + pedido));
            // Si el registro existe, elimínalo
        if (optionalPedido.isPresent()) {
            Pedido pedido = optionalPedido.get();
            //Recojo el registro y lo envío a la vista;
            model.addAttribute("registro", pedido);
            model.addAttribute("clientes", misClientes);
            model.addAttribute("repartidores", misRepartidores);
            model.addAttribute("coches",misCoches);
        }
        return "modificarPedido";
    }

    @PostMapping("/modificar/guardar")
    public String guardarModificacionPedido(Pedido miPedido){

        Optional<Pedido> buscoMiPedidoEnBD = pedidorespository.findById(miPedido.getId().intValue());
       
        if(buscoMiPedidoEnBD.isPresent()){
            Pedido pedidoantiguo = buscoMiPedidoEnBD.get();
            pedidoantiguo.setCantidad(miPedido.getCantidad());
            pedidoantiguo.setPeso(miPedido.getPeso());
            pedidoantiguo.setNombre(miPedido.getNombre());
            pedidoantiguo.setFechaPedido(miPedido.getFechaPedido());
            pedidoantiguo.setRepartidor(miPedido.getRepartidor());
            pedidoantiguo.setCoche(miPedido.getCoche());
            pedidorespository.save(pedidoantiguo);

        }        

        return "redirect:/";
    }

    
    

    
    

}
