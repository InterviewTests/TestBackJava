package com.santander.test.backend.bweninger.service;

import com.santander.test.backend.bweninger.model.Categoria;
import com.santander.test.backend.bweninger.repository.CategoriaRepository;
import com.santander.test.backend.bweninger.vo.CategoriaVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeNotNull;
import static org.mockito.Mockito.when;

/**
 * Created by BWeninger on 11/01/2019.
 */
@RunWith(MockitoJUnitRunner.class)
public class CategoriaServiceImplTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaServiceImpl service;

    @Test
    public void testSugerirCategorias(){
        Categoria c = new Categoria();
        c.setDescricao("Alimentação");

        Categoria c2 = new Categoria();
        c2.setDescricao("Educação");

        Categoria c3 = new Categoria();
        c3.setDescricao("Outros");


        List<Categoria> resultSet = Arrays.asList(c, c2, c3);
        when(categoriaRepository.findAll()).thenReturn(resultSet);

        Set<CategoriaVO> result = service.sugerirCategorias("Alim");

        assertTrue(result.stream().anyMatch(cat -> cat.getDescricao().equals(c.getDescricao())));
        assertFalse(result.stream().anyMatch(cat -> cat.getDescricao().equals("Outros")));
        assertFalse(result.stream().anyMatch(cat -> cat.getDescricao().equals("Educação")));
    }

}
