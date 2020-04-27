/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.turner.service;

import co.edu.utp.isc.gia.turner.model.Categoria;
import co.edu.utp.isc.gia.turner.repository.CategoriaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author migue
 */
@Service
public class CategoriaService {
    @Autowired
    CategoriaRepository categoriaRepository;
    
    public List<Categoria> listarCategorias() {
        List<Categoria> listC = categoriaRepository.findByOrderByLetra();
        return listC;
    }
}
