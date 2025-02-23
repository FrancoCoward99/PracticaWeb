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

    /*@GetMapping("/crear-arbol")
    public String crearArbol(Model model) {
        model.addAttribute("arbol", new Arbol());
        return "operaciones/crear-arbol";
    }*/
    @PostMapping("/guardar")
    public String guardarArbol(Arbol arbol) {
        arbolService.save(arbol);
        return "redirect:listado";
    }

    /*@GetMapping("/eliminar/{id}")
    public String eliminarArbol(@PathVariable Long id) {
        arbolService.delete(id);
        return "redirect:/listado";
    }*/
 /*Version 2*/
    @GetMapping("/crear-arbol")
    public String crearArbol(@RequestParam(required = false) Long id, Model model) {
        Arbol arbol = (id != null) ? arbolService.getArbol(id) : new Arbol();
        model.addAttribute("arbol", arbol);
        return "operaciones/crear-arbol";
    }

    /*@GetMapping("/modificar-arbol")
    public String modificarArbol(@RequestParam(required = false) Long id, Model model) {
        Arbol arbol = (id != null) ? arbolService.getArbol(id) : new Arbol();
        model.addAttribute("arbol", arbol);
        return "operaciones/crear-arbol";
    }*/
    @GetMapping("/eliminar/{id}")
    public String eliminarArbol(@PathVariable Long id) {
        arbolService.delete(id);
        return "redirect:/listado";
    }

    /*  @GetMapping("/modificar-arbol")
public String modificarArbol(@RequestParam Long id, Model model) {
    Arbol arbol = arbolService.getArbol(id);
    if (arbol == null) {  // CORREGIDO: Se debe verificar si es `null`, no `!null`
        return "redirect:/error";  // Redirige a una página de error si el ID no existe
    }
    model.addAttribute("arbol", arbol);
    return "operaciones/modificar-arbol";  // Retorna la vista correcta
}*/
    @GetMapping("/modificar-arbol")
    public String modificarArbol(@RequestParam Long id, Model model) {
        Arbol arbol = arbolService.getArbol(id);

        if (arbol == null) {  // Si el árbol no existe, redirigir a una página de error o al listado
            return "redirect:/error";
        }

        model.addAttribute("arbol", arbol);
        return "operaciones/modificar"; // Vista para modificar el árbol
    }

}
