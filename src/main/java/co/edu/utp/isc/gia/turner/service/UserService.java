/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.turner.service;

import co.edu.utp.isc.gia.turner.model.Asesor;
import co.edu.utp.isc.gia.turner.model.Atendidos;
import co.edu.utp.isc.gia.turner.model.User;
import co.edu.utp.isc.gia.turner.repository.AsesorRepository;
import co.edu.utp.isc.gia.turner.repository.AtendidosRepository;
import co.edu.utp.isc.gia.turner.repository.UserRepository;
import co.edu.utp.isc.gia.turner.web.dto.AsesorDeTurno;
import co.edu.utp.isc.gia.turner.web.dto.UserTurn;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author migue
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AtendidosRepository atendidosRepository;
    @Autowired
    AsesorRepository asesorRepository;
    
    
    public UserTurn insertarUsuario(User user) {
        Optional<User> u = userRepository.findById(user.getNoTurn());
        if(u.isPresent()){
            return null;
        }
        
        User userNew = new User(user.getNoTurn(), user.getCategory(), "En espera");
        
        userNew = userRepository.save(userNew);
        if(userNew != null){
            UserTurn userTurn = UserTurn.builder()
                    .turn(userNew.getNoTurn())
                    .build();
            return userTurn;
        }
        return null;
    }
    
    public UserTurn llamarSiguiente(String idAsesor){
        List<User> listUser = userRepository.findAll();
        User userS = primerEspera(listUser);
        if(userS==null){
            return null;
        }
        User userUp = User.builder()
                .noTurn(userS.getNoTurn())
                .category(userS.getCategory())
                .estado("Atendiendo")
                .asesor(asesorRepository.getOne(idAsesor))
                .build();
        userUp = userRepository.save(userUp);
        if(userUp != null){
            UserTurn userTurn = UserTurn.builder()
                    .turn(userUp.getNoTurn())
                    .build();
            return userTurn;
        }
        return null;
    }
    
    private User primerEspera(List<User> listUser) {
        for (User user: listUser) {
            if(user.getEstado().equals("En espera")){
                return user;
            }
        }
        return null;
    }

    

    
    
    
}
