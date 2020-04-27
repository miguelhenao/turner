/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.turner.web.rest;

import co.edu.utp.isc.gia.turner.model.Asesor;
import co.edu.utp.isc.gia.turner.model.User;
import co.edu.utp.isc.gia.turner.service.AsesorService;
import co.edu.utp.isc.gia.turner.service.UserService;
import co.edu.utp.isc.gia.turner.web.dto.AsesorDeTurno;
import co.edu.utp.isc.gia.turner.web.dto.UserTurn;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author migue
 */
@RestController
@RequestMapping("turnos")
public class UserController {
    @Autowired
    UserService userService;
    
    @Autowired
    AsesorService asesorService;

    @PostMapping()
    @RequestMapping("iu")
    public ResponseEntity<UserTurn> insertarUsuario( @RequestBody 
            User user){
        if(user == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        UserTurn userNew = userService.insertarUsuario(user);
        if(userNew == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userNew);
    }
    
    @PostMapping()
    @RequestMapping("ia")
    public ResponseEntity<Asesor> insertarAsesor(@RequestBody 
            Asesor asesor){
        if(asesor == null){
            return ResponseEntity.badRequest().build();
        }
        Asesor asesorT = asesorService.insertarAsesor(asesor);
        if(asesorT == null){
            return ResponseEntity.status(HttpStatus.FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(asesorT);
    }
    
    
    @RequestMapping("llamar")
    @GetMapping("/{asesor}")
    public ResponseEntity<UserTurn> llamarSigueinte(@PathVariable("asesor") String asesor){
        AsesorDeTurno ases = asesorService.consultarAsesor(asesor);
        if(ases!= null){/*
            UserTurn userSiguiente = userService.llamarSiguiente(ases.getId());
            if(userSiguiente==null){
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }*/
            return ResponseEntity.status(HttpStatus.FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    
    @RequestMapping("consultar")
    @GetMapping("/{asesor}")
    public ResponseEntity<List<UserTurn>> consultarTurnosAsesor(@PathVariable("asesor") 
            String asesor){
        AsesorDeTurno ases = asesorService.consultarAsesor(asesor);
        if(ases == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<UserTurn> listUsu = asesorService.consultarTurnosAsesor(ases);
        if(listUsu!=null){
            return ResponseEntity.ok(listUsu);
        }
        return ResponseEntity.badRequest().build();
    }
    
    //@RequestMapping("tiempo")
    @GetMapping("/{categoria}/{asesor}")
    public ResponseEntity<Long> tiempoXCategoria(@PathVariable("asesor") 
            String asesor, @PathVariable("categoria") String categoria){
        
        AsesorDeTurno ases =asesorService.consultarAsesor(asesor);
        long times = asesorService.consultarTiempos(categoria, asesor);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(times);
    }
}
