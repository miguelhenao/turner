/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.turner.web.dto;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author migue
 */
@Data
@NoArgsConstructor
public class Reporte implements Serializable{
    String nombre;
    String valor;

    public Reporte(String nombre, String valor) {
        this.nombre = nombre;
        this.valor = valor;
    }
}
