/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.turner.web.rest;

import co.edu.utp.isc.gia.turner.model.Categoria;
import co.edu.utp.isc.gia.turner.repository.CategoriaRepository;
import co.edu.utp.isc.gia.turner.service.CategoriaService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
public class CategoriaControllerTest {
    @Mock
    private CategoriaService categoriaService;
    
    @InjectMocks 
    private CategoriaController categoriaController;
    
    public CategoriaControllerTest() {
    }

    @Test
    public void testListarCategorias() {
        given(categoriaService.listarCategorias()).willReturn(Arrays.asList(
                Categoria.builder()
                .letra("A")
                .consecutivo("1")
                .nombre("Categoria 1")
                .build()
        ));
        
        List<Categoria> listC = categoriaController.categorias();
        assertNotNull(listC);
        assertEquals(listC.get(0).getLetra(), "A");
        assertEquals(listC.get(0).getNombre(), "Categoria 1");
        assertEquals(listC.get(0).getConsecutivo(), "1");
    }
}
