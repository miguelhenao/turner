/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.turner.service;

import co.edu.utp.isc.gia.turner.model.*;
import co.edu.utp.isc.gia.turner.repository.*;
import co.edu.utp.isc.gia.turner.web.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author migue
 */
@Service
public class AsesorService {
    @Autowired
    AsesorRepository asesorRepository;
    @Autowired
    TurnoRepository turnoRepository;
    @Autowired
    LlamadosRepository llamadosRepository;
    @Autowired
    PendientesRepository pendientesRepository;
    @Autowired
    CategoriaRepository categoriaRepository;

    public AsesorDeTurno consultarAsesor(String asesor) {
        Asesor ases = asesorRepository.getOne(asesor);
        if(ases!=null){
            AsesorDeTurno adt = AsesorDeTurno.builder()
                    .id(ases.getNoAsesor())
                    .build();
            return adt;
        }
        return null;
    }
    
    
    public TurnoLlamado llamarSiguiente(String asesor) {
        if(!turnoRepository.findByAsesorAndEstado(asesor, "Atendiendo").isEmpty()){
            this.turnoPendiente(asesor);
        }/*
        List<Turno> listUser = turnoRepository.findAll();
        Turno userS = primerEspera(listUser);*/
        Turno userS = turnoPrioridad();
        if(userS!=null){
            userS.setEstado("Atendiendo");
            userS.setHoraInicio(new Date());
            userS.setAsesor(asesor);
            userS = turnoRepository.save(userS);
            if(userS != null){
                TurnoLlamado userTurn = TurnoLlamado.builder()
                        .turn(userS.getNoTurn())
                        .build();
                llamadosRepository.save(
                        Llamado.builder()
                        .turno(userTurn.getTurn())
                        .llamado(1)
                        .asesor(asesor)
                        .build());
                return userTurn;
            }
        }
        return null;
    }

    private Turno primerEspera(List<Turno> listUser) {
        for (Turno user: listUser) {
            if(user.getEstado().equals("En espera")){
                return user;
            }
        }
        return null;
    }

    public TurnoLlamado terminarTurno(String asesor, String reporte) {
        if(!turnoRepository.findByAsesorAndEstado(asesor, "Atendiendo").isEmpty()){
            Turno userEnd = turnoRepository.findByAsesorAndEstado(asesor, "Atendiendo").get(0);
            userEnd.setEstado("Terminado");
            userEnd.setHoraFinal(new Date());
            userEnd.setReporte(reporte);
            userEnd = turnoRepository.save(userEnd);
            llamadosRepository.deleteById(userEnd.getNoTurn());
            if(userEnd!=null){
                TurnoLlamado userT = TurnoLlamado.builder()
                        .turn(userEnd.getNoTurn())
                        .build();
                return userT;
            }
        }
        return null;
    }
    
    public TurnoLlamado turnoPendiente(String asesor) {
        if(!turnoRepository.findByAsesorAndEstado(asesor, "Atendiendo").isEmpty()){
            Turno userEnd = turnoRepository.findByAsesorAndEstado(asesor, "Atendiendo").get(0);
            userEnd.setAsesor(null);
            userEnd.setEstado("Pendiente");
            userEnd = turnoRepository.save(userEnd);
            if(userEnd!=null){
                TurnoLlamado userT = TurnoLlamado.builder()
                        .turn(userEnd.getNoTurn())
                        .build();
                pendientesRepository.save(
                        Pendiente.builder()
                        .turno(userT.getTurn())
                        .build());
                return userT;
            }
        }
        return null;
    }
    
    public TurnoLlamado rellamarPendiente(String asesor) {
        if(!turnoRepository.findByAsesorAndEstado(asesor, "Atendiendo").isEmpty()){
            this.turnoPendiente(asesor);
        } if(!turnoRepository.findByEstado("Pendiente").isEmpty()){
            Turno userR = turnoRepository.findByEstado("Pendiente").get(0);
            userR.setAsesor(asesor);
            userR.setEstado("Atendiendo");
            userR = turnoRepository.save(userR);
            pendientesRepository.deleteById(userR.getNoTurn());
            if(userR!=null){
                TurnoLlamado userT = TurnoLlamado.builder()
                        .turn(userR.getNoTurn())
                        .build();
                llamadosRepository.save(
                    Llamado.builder()
                    .turno(userT.getTurn())
                    .llamado(1)
                    .asesor(asesor)
                    .build());
                return userT;
            }
        }
        return null;
    }

    public List<Reporte> reporteAsesor(String asesor){
        List<Reporte> reporte = new ArrayList<>();
        List<Turno> listaTurnos = turnoRepository.findByAsesorAndEstado(asesor, "Terminado");
        reporte.add(new Reporte("Turnos gestionados", String.valueOf(listaTurnos.size())));
        Long timeG = tiempoPromedio(listaTurnos);
        reporte.add(new Reporte("Tiempo promedio", String.valueOf(timeG)));
        List<Categoria> listaCategorias = categoriaRepository.findAll();
        for(Categoria categoria: listaCategorias){
            List<Turno> listaTurnosC = turnoRepository.findByAsesorAndEstadoAndCategory(asesor, "Terminado", categoria.getLetra());
            reporte.add(new Reporte(categoria.getNombre(), String.valueOf(listaTurnosC.size())));
            Long time = tiempoPromedio(listaTurnosC);
            reporte.add(new Reporte("Tiempo promedio "+ categoria.getNombre(), String.valueOf(time)));
        }
        return reporte;
    }
    
    public Long tiempoPromedio(List<Turno> listT){
        long time = 0;
        for(Turno turno: listT){
            time=((turno.getHoraFinal().getTime()-turno.getHoraInicio().getTime())/60000)+time;
        }
        if(listT.size()>0){
            time= time/listT.size();
        }
        return time;
    }
    
    public Turno turnoPrioridad(){
        List<Categoria> listaCategorias = categoriaRepository.findByOrderByLetra();
        for(Categoria categoria: listaCategorias){
            int mlc = Integer.valueOf(categoria.getMlc());
            int lc = Integer.valueOf(categoria.getLc());
            List<Turno> listaTurno = turnoRepository.findByCategoryAndEstado(categoria.getLetra(), "En espera");
            if(!listaTurno.isEmpty()){
                if(mlc>lc){
                    categoria.setLc(String.valueOf(lc+1));
                    return listaTurno.get(0);
                }
                
            } 
            int last = listaCategorias.size()-1;
            if(categoria.equals(listaCategorias.get(last))){
                int mlc_ = Integer.valueOf(categoria.getMlc());
                int lc_ = Integer.valueOf(categoria.getLc());
                if(mlc_==lc_){
                    for(Categoria c: listaCategorias){
                        int mlc_c = Integer.valueOf(c.getMlc());
                        int lc_c = Integer.valueOf(c.getLc());
                        if(mlc_c==lc_c){
                            lc_c=0;
                            c.setLc(String.valueOf(lc_c));
                            categoriaRepository.save(c);
                        }
                    }   
                }
            }
        }
        return null;
    } 
    
    
    public List<Llamado> turnosLlamados() {
        List<Llamado> listTurnos = llamadosRepository.findAll();
        return listTurnos;
    }

    public List<Pendiente> turnosPendientes(){
        List<Pendiente> listP = pendientesRepository.findAll();
        return listP;
    }
}