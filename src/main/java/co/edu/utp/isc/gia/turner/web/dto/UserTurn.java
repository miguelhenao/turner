/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.turner.web.dto;

import lombok.Builder;
import lombok.Getter;

/**
 *
 * @author migue
 */
@Builder
@Getter
public class UserTurn {
    String turn;

    public UserTurn(String turn) {
        this.turn = turn;
    }

}
