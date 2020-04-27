/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.turner.web.rest;

import co.edu.utp.isc.gia.turner.model.User;
import co.edu.utp.isc.gia.turner.service.UserService;
import co.edu.utp.isc.gia.turner.web.dto.UserTurn;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author migue
 */
public class UserControllerTest {
    @Mock
    private UserService userService;
    
    @InjectMocks
    private UserController userController;
    
    
    public UserControllerTest() {
    }

    @Test
    public void testInsertarUsuario_ParametroNull(){
        User userQ = new User("A2", "Embarazado", "En espera");
        
        given(userService.insertarUsuario(userQ)).willReturn(
                UserTurn.builder()
                        .turn(userQ.getNoTurn())
                        .build()
        );
        
        ResponseEntity<UserTurn> userR = userController.insertarUsuario(userQ);
        assertEquals(userR.getStatusCode(), HttpStatus.CREATED);
    }
    
}
