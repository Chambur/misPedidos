package com.example.Coches;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@Service

public class cocheControlador {
    
    @Autowired
    private cocheRepository miscoches;

    @GetMapping("/coches")
    public String coches (Model model){
        List<coche> miListadecoches = miscoches.findAll();
        model.addAttribute("coches", miListadecoches);
        return "coches";
    }

    @GetMapping("/coches/nuevo")
    public String nuevoCoche(Model model){
        model.addAttribute("coches", new coche());
        return "nuevoCoche";
    }

    @PostMapping("/coches/nuevo")
    public String nuevoCoche(coche miNuevoCoche){
        miscoches.save(miNuevoCoche);
        return "redirect:/coches";
    }

}
