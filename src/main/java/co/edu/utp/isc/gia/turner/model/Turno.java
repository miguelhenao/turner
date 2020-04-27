/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.turner.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author migue
 */

@Entity
@Table(name = "cfg_usuario")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Turno implements Serializable {
    @Id
    private String noTurn;
    private String category;
    private String estado;
    private Date horaInicio;
    private Date horaFinal;
    private String reporte;
    private String asesor;
}
