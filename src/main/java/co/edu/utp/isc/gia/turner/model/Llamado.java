/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.turner.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author migue
 */
@Entity
@Table(name = "cfg_llamados")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Llamado {
    @Id
    String turno;
    Integer llamado;
    String asesor;
}
