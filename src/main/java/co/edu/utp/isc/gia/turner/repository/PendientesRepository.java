/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.turner.repository;

import co.edu.utp.isc.gia.turner.model.Pendiente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author migue
 */
@RepositoryRestResource(path="pendientes")
public interface PendientesRepository extends JpaRepository<Pendiente, String>{
    
}