/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.turner.repository;

import co.edu.utp.isc.gia.turner.model.Asesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author migue
 */
@RepositoryRestResource(path="asesores")
public interface AsesorRepository extends JpaRepository<Asesor, String>{
    
}