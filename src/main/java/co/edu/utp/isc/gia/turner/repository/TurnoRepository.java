/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.turner.repository;

import co.edu.utp.isc.gia.turner.model.Turno;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author migue
 */
@RepositoryRestResource(path = "turnos")
public interface TurnoRepository extends JpaRepository<Turno, String>{

    public List<Turno> findByAsesor(String asesor);

    public List<Turno> findByAsesorAndEstado(String asesor, String estado);

    public List<Turno> findByAsesorAndEstadoAndCategory(String asesor, String estado, String category);

    public List<Turno> findByCategory(String category);

    public List<Turno> findByCategoryAndEstado(String category, String estado);

    public List<Turno> findByEstado(String estado);
    
}