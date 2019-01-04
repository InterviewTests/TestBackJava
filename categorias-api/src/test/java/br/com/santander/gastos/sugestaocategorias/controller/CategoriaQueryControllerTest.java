package br.com.santander.gastos.sugestaocategorias.controller;

import br.com.santander.gastos.sugestaocategorias.service.CategoriaQueryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CategoriaQueryController.class)
public class CategoriaQueryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoriaQueryService categoriaQueryService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void deveConsultarCategoriasComSucesso() throws Exception {

        final List<String> categorias = Arrays.asList("categoria a", "categoria b");

        Page<String> retornoService = new PageImpl<>(categorias);
        when(categoriaQueryService.consultarTodasCategorias(anyString(), any(Pageable.class))).thenReturn(retornoService);

        mockMvc.perform(get("/categorias").param("nome", "nome da categoria")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(categorias.size()));

    }

    @Test
    public void deveRetornarListaVaziaSeQuantidadeDeCaracteresDaPesquisaForMenorQueMinimo() throws Exception {

        final List<String> categorias = Arrays.asList("categoria a", "categoria b");

        Page<String> retornoService = new PageImpl<>(categorias);
        when(categoriaQueryService.consultarTodasCategorias(anyString(), any(Pageable.class))).thenReturn(retornoService);

        mockMvc.perform(get("/categorias")
                .param("nome", "")
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.totalElements").value(0));

    }

    @Test
    public void deveRetornarErroSeNaoForFornecidoTextoDePesquisa() throws Exception {

        final List<String> categorias = Arrays.asList("categoria a", "categoria b");

        Page<String> retornoService = new PageImpl<>(categorias);
        when(categoriaQueryService.consultarTodasCategorias(anyString(), any(Pageable.class))).thenReturn(retornoService);

        mockMvc.perform(get("/categorias")
            )
            .andDo(print())
            .andExpect(status().is4xxClientError());

    }
}