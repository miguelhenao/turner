/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.turner.web.rest;

import co.edu.utp.isc.gia.turner.model.Categoria;
import co.edu.utp.isc.gia.turner.service.CategoriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author migue
 */
@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    @Autowired
    CategoriaService categoriaService;
    
    @GetMapping("/categorias")
    public List<Categoria> categorias(){
        List<Categoria> listC = categoriaService.listarCategorias();
        return listC;
    }
}
