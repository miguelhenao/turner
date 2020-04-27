/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.turner.service;

import co.edu.utp.isc.gia.turner.model.Categoria;
import co.edu.utp.isc.gia.turner.model.Turno;
import co.edu.utp.isc.gia.turner.repository.CategoriaRepository;
import co.edu.utp.isc.gia.turner.web.dto.TurnoLlamado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.utp.isc.gia.turner.repository.TurnoRepository;

/**
 *
 * @author migue
 */
@Service
public class KioskoService {
    @Autowired
    TurnoRepository turnoRepository;
    @Autowired
    CategoriaService categoriaService;
    @Autowired
    CategoriaRepository categoriaRepository;
    
    public TurnoLlamado insertarTurno(String categoria) {
        Categoria cateTurno = categoriaRepository.findByLetra(categoria).get(0);
        int consecutivo = Integer.parseInt(cateTurno.getConsecutivo());
        Turno turno = Turno.builder()
                .noTurn(categoria+consecutivo)
                .category(categoria)
                .estado("En espera")
                .build();
        turno = turnoRepository.save(turno);
        
        Categoria cateUp = Categoria.builder()
                .letra(cateTurno.getLetra())
                .nombre(cateTurno.getNombre())
                .consecutivo(String.valueOf(consecutivo+1))
                .mlc(cateTurno.getMlc())
                .lc(cateTurno.getLc())
                .build();
        categoriaRepository.save(cateUp);
        
        if(turno!=null){
            TurnoLlamado userT = TurnoLlamado.builder()
                    .turn(turno.getNoTurn())
                    .build();
            return userT;
        }
        
        return null;
    }
}
