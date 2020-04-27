/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.turner.model;

import javax.persistence.*;

import lombok.*;

/**
 *
 * @author migue
 */
@Entity 
@Table(name = "cfg_categoria")
@Data 
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Categoria {
    @Id
    String letra;
    String nombre;
    String consecutivo;
    //Mayor número de llamados en consecutivo
    String mlc;
    //Llamados consecutivos
    String lc;
}
