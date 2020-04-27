/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.turner.web.rest;

import co.edu.utp.isc.gia.turner.service.KioskoService;
import co.edu.utp.isc.gia.turner.web.dto.TurnoLlamado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author migue
 */
@RestController
@RequestMapping("/api/kiosko")
public class KioskoController {
    @Autowired
    KioskoService kioskoService;
    
    @PostMapping("/{categoria}")
    public TurnoLlamado insertarTurno(@PathVariable("categoria") String categoria){
        TurnoLlamado turno = kioskoService.insertarTurno(categoria);
        return turno;
    }
}
