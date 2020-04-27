/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.turner.web.rest;

import co.edu.utp.isc.gia.turner.model.Llamado;
import co.edu.utp.isc.gia.turner.service.AsesorService;
import co.edu.utp.isc.gia.turner.web.dto.AsesorDeTurno;
import co.edu.utp.isc.gia.turner.web.dto.TurnoLlamado;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author migue
 */
@RunWith(SpringRunner.class)
public class AsesorControllerTest {
    @Mock
    private AsesorService asesorService;
    
    @InjectMocks
    private AsesorController asesorController;
    public AsesorControllerTest() {
    }

    @Test
    public void testLlamarSiguiente_AsesorNoExiste(){
        String asesor="c1";
        given(asesorService.llamarSiguiente(asesor)).willReturn(null);
        TurnoLlamado user = asesorController.llamarSiguiente(asesor);
        assertEquals(user,null);
    }
    
    @Test
    public void testTurnosLlamados(){
        given(asesorService.turnosLlamados()).willReturn(null);
        
        List<Llamado> listL = asesorController.turnosLlamados();
        assertEquals(listL, null);
    }
    
    @Test
    public void testTerminarTurno_Asesor_NoExiste(){
        given(asesorService.terminarTurno("c1", "Reporte")).willReturn(null);
        
        TurnoLlamado t = asesorController.terminarTurno("c1", "Reporte");
        assertEquals(t, null);
    }
    
    @Test
    public void testTerminarTurno_Asesor(){
        given(asesorService.terminarTurno("c", "Reporte")).willReturn(null);
        
        TurnoLlamado t = asesorController.terminarTurno("c", "Reporte");
        assertEquals(t, null);
    }
    
    
    /*
    @Test
    public void testTiempoPromedio(){
        given(asesorService.tiempoPromedio("A", "c12")).willReturn(null);
        
        Float tP = asesorController.tiempoPromedio("A", "c12");
        assertEquals(tP, null);
    }*/
    
    @Test
    public void testConsultarAsesor_Null(){
        String asesor = "c12" ;
        given(asesorService.consultarAsesor(asesor)).willReturn(null);
        
        AsesorDeTurno ases = asesorController.consultarAsesor(asesor);
        assertEquals(ases, null );
    }
    
    @Test
    public void testConsultarAsesor(){
        String asesor = "c12" ;
        given(asesorService.consultarAsesor(asesor)).willReturn(
                AsesorDeTurno.builder()
                .id("c12")
                .build()
        );
        
        AsesorDeTurno ases = asesorController.consultarAsesor(asesor);
        assertEquals(ases.getId(), "c12");
    }
    
}
