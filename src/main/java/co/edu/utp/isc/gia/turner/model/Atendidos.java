/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.turner.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

/**
 *
 * @author migue
 */
@Entity
@Table(name = "cfg_atendidos")
@Data
@AllArgsConstructor
//@RequiredArgsConstructor
@NoArgsConstructor
@Builder
public class Atendidos implements Serializable{
    @Id
    @GeneratedValue
    private Long registro;
    private User usuario;
    private Asesor asesor;
    private String descrip;
    private Long tiempoAtendido;
}
