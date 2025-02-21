package com.practica.controller;

import com.practica.domain.Arbol;
import com.practica.service.ArbolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ArbolController {

    @Autowired
    private ArbolService arbolService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var arboles = arbolService.getArboles();
        model.addAttribute("arboles", arboles);
        return "operaciones/listado";
    }

    @GetMapping("/crear-arbol")
    public String crearArbol(Model model) {
        model.addAttribute("arbol", new Arbol());
        return "operaciones/crear-arbol";
    }

    @PostMapping("/guardar")
    public String guardarArbol(Arbol arbol) {
        arbolService.save(arbol);
        return "redirect:listado";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarArbol(@PathVariable Long id) {
        arbolService.delete(id);
        return "redirect:/listado";
    }


}
