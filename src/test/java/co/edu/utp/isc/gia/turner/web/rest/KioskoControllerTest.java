/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.turner.web.rest;

import co.edu.utp.isc.gia.turner.service.KioskoService;
import co.edu.utp.isc.gia.turner.web.dto.TurnoLlamado;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author migue
 */
@RunWith(SpringRunner.class)
public class KioskoControllerTest {
    @Mock
    private KioskoService kioskoService;
    
    @InjectMocks
    private KioskoController kioskoController;
    
    public KioskoControllerTest() {
    }

    @Test
    public void testInsertarTurno(){
        given(kioskoService.insertarTurno("A")).willReturn(TurnoLlamado.builder()
                .turn("A1")
                .build()        
        );
        
        TurnoLlamado userT = kioskoController.insertarTurno("A");
        TurnoLlamado user = TurnoLlamado.builder()
                .turn("A1")
                .build();
        assertEquals(userT.getTurn(), user.getTurn());
    }
/*
    @Test
    public void testCategorias(){
        given(kioskoService.listarCategorias()).willReturn(
                Arrays.asList(
                new Categoria("A", "Categoria 1", "2"),
                new Categoria("B", "Categoria 2", "1"),
                new Categoria("C", "Categoria 3", "1"),
                new Categoria("D", "Categoria 4", "1")
        ));
        
        List<Categoria> categ = kioskoController.categorias();
        
        assertEquals(categ.get(0).getLetra(), "A");
        assertEquals(categ.get(1).getLetra(), "B");
    }
    */

}
