package com.practica.service;

import com.practica.domain.Arbol;
import java.util.List;

public interface ArbolService {
    List<Arbol> getArboles();
    Arbol getArbol(Long id);
    void save(Arbol arbol);
    void delete(Long id);
}
