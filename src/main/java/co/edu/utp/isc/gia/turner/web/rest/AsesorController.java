/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.turner.web.rest;

import co.edu.utp.isc.gia.turner.model.Categoria;
import co.edu.utp.isc.gia.turner.model.Llamado;
import co.edu.utp.isc.gia.turner.model.Pendiente;
import co.edu.utp.isc.gia.turner.service.AsesorService;
import co.edu.utp.isc.gia.turner.service.CategoriaService;
import co.edu.utp.isc.gia.turner.service.KioskoService;
import co.edu.utp.isc.gia.turner.web.dto.AsesorDeTurno;
import co.edu.utp.isc.gia.turner.web.dto.Reporte;
import co.edu.utp.isc.gia.turner.web.dto.TurnoLlamado;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author migue
 */
@RestController
@RequestMapping("/api/asesor")
public class AsesorController {
    @Autowired
    KioskoService kioskoService;
    
    @Autowired
    AsesorService asesorService;
    
    @Autowired
    CategoriaService categoriaService;
    
    
    @GetMapping("/{asesor}")
    public TurnoLlamado llamarSiguiente(@PathVariable("asesor") String asesor){
        AsesorDeTurno ases = asesorService.consultarAsesor(asesor);
        if(ases!= null){
            TurnoLlamado userSiguiente = asesorService.llamarSiguiente(ases.getId());
            if(userSiguiente==null){
                return null;
            }
            return userSiguiente;
        }
        return null;
    }
    
    @PutMapping("/{asesor}")
    public TurnoLlamado terminarTurno(@PathVariable("asesor") String asesor, @RequestBody String reporte){
        
        AsesorDeTurno ases = asesorService.consultarAsesor(asesor);
        if(ases!=null){
            TurnoLlamado turnEnd = asesorService.terminarTurno(asesor, reporte);
            if(turnEnd==null){
                return null;
            }
            return turnEnd;
        }
        return null;
    }
    
    @GetMapping("/{asesor}/p")
    public TurnoLlamado turnoPendiente(@PathVariable("asesor") String asesor){
        AsesorDeTurno ases = asesorService.consultarAsesor(asesor);
        if(ases!=null){
            TurnoLlamado turnoP = asesorService.turnoPendiente(asesor);
            if(turnoP!=null){
                return turnoP;
            }
        }
        return null;
    }
    
    @GetMapping("/{asesor}/rp")
    public TurnoLlamado rellamarPendiente(@PathVariable("asesor") String asesor){
        AsesorDeTurno ases = asesorService.consultarAsesor(asesor);
        if(ases!=null){
            TurnoLlamado turnoR = asesorService.rellamarPendiente(asesor);
            if(turnoR!=null){
                return turnoR;
            }
        }
        return null;
    }
    
    @GetMapping("/llamados")
    public List<Llamado> turnosLlamados(){
        List<Llamado> llamados = asesorService.turnosLlamados();
        return llamados;
    }
    
    @GetMapping("/pendientes")
    public List<Pendiente> turnosPendientes(){
        List<Pendiente> pendientes = asesorService.turnosPendientes();
        return pendientes;
    }
    
    @GetMapping("/{asesor}/reporte")
    public List<Reporte> reporteAsesor(@PathVariable("asesor") String asesor){
        AsesorDeTurno ases = asesorService.consultarAsesor(asesor);
        if(ases!=null){
            List<Reporte> reporte = asesorService.reporteAsesor(asesor);
            if(!reporte.isEmpty()){
                return reporte;
            }
        }
        return null;
    }
    
    public AsesorDeTurno consultarAsesor(String asesor){
        AsesorDeTurno ases = asesorService.consultarAsesor(asesor);
        if(ases!=null){
            return ases;
        }
        return null;
    }
}
