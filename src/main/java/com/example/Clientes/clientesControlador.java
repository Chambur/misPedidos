package com.example.Clientes;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

@Service
public class clientesControlador {

    @Autowired
    private clientesRepository clientesRepositorio;

    @GetMapping("/clientes")
    public String clientes(Model model) {
        List<Clientes> misClientes = clientesRepositorio.findAll();
        model.addAttribute("clientes", misClientes);
        return "clientes";
    }

    @GetMapping("/clientes/nuevocliente")
    public String nuevocliente(Model model) {
        model.addAttribute("clientes", new Clientes());
        return "nuevocliente";
    }

    @PostMapping("/clientes/nuevocliente")
    public String nuevocliente(Clientes cliente) {
        clientesRepositorio.save(cliente);
        return "redirect:/clientes";
    }

    @PostMapping("/clientes/eliminar")
    public String eliminarRegistro(@RequestParam("id") Long id) {
        // Busca el registro con el ID especificado en la base de datos
        Optional<Clientes> optionalPedido = clientesRepositorio.findById(id.intValue());
    
        // Si el registro existe, elimínalo
        if (optionalPedido.isPresent()) {
            Clientes cliente = optionalPedido.get();
            clientesRepositorio.delete(cliente);
        }
    
        // Redirige al usuario a otra página una vez que se ha eliminado el registro
        return "redirect:/clientes";
    }



}
