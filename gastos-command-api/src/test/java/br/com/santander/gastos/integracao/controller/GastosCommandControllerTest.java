package br.com.santander.gastos.integracao.controller;

import br.com.santander.gastos.integracao.dto.AdicionarGastoRequest;
import br.com.santander.gastos.integracao.dto.CategorizarGastoRequest;
import br.com.santander.gastos.integracao.dto.GastosDTO;
import br.com.santander.gastos.integracao.infra.handler.ErrorDetail;
import br.com.santander.gastos.integracao.mappers.GastosMapper;
import br.com.santander.gastos.integracao.service.GastosCommandService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.FieldDefinitionBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import io.github.benas.randombeans.randomizers.SentenceRandomizer;
import io.github.benas.randombeans.randomizers.misc.NullRandomizer;
import io.github.benas.randombeans.randomizers.number.DoubleRandomizer;
import io.github.benas.randombeans.randomizers.range.LongRangeRandomizer;
import io.github.benas.randombeans.randomizers.time.LocalDateTimeRandomizer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GastosCommandController.class)
public class GastosCommandControllerTest {

    private GastosMapper gastosMapper = Mappers.getMapper(GastosMapper.class);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GastosCommandService gastosCommandService;

    private ObjectMapper objectMapper = new ObjectMapper();

    private EnhancedRandom enhancedRandom = EnhancedRandomBuilder
            .aNewEnhancedRandomBuilder()
            .randomize(FieldDefinitionBuilder.field().named("id").ofType(Long.class).get(), new LongRangeRandomizer(1L, Long.MAX_VALUE))
            .randomize(FieldDefinitionBuilder.field().named("valor").ofType(Double.class).get(), new DoubleRandomizer())
            .randomize(FieldDefinitionBuilder.field().named("codigoUsuario").ofType(Long.class).get(), new LongRangeRandomizer(1L, Long.MAX_VALUE))
            .randomize(FieldDefinitionBuilder.field().named("data").ofType(LocalDateTime.class).get(), new LocalDateTimeRandomizer())
            .randomize(FieldDefinitionBuilder.field().named("descricao").ofType(String.class).get(), new SentenceRandomizer())
            .randomize(FieldDefinitionBuilder.field().named("categoria").ofType(String.class).get(), new NullRandomizer())
            .build();

    @Before
    public void setup(){
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Test
    public void deveAdicionarUmGastoComSucesso() throws Exception {
        AdicionarGastoRequest request = enhancedRandom.nextObject(AdicionarGastoRequest.class);

        final GastosDTO dto = gastosMapper.requestToDTO(request);

        when(gastosCommandService.adicionarGasto(any(AdicionarGastoRequest.class))).thenReturn(dto);

        final String contentAsString = mockMvc.perform(post("/gastos/v1/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();

        GastosDTO retorno = objectMapper.readValue(contentAsString, GastosDTO.class);

        assertThat(retorno).isEqualToComparingFieldByField(dto);

    }

    @Test
    public void naoDevePermitirCamposInvalidos() throws Exception {
        when(gastosCommandService.adicionarGasto(any(AdicionarGastoRequest.class))).thenReturn(new GastosDTO());

        AdicionarGastoRequest request = new AdicionarGastoRequest();

        final String contentAsString = mockMvc.perform(post("/gastos/v1/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andReturn()
                .getResponse().getContentAsString();

        ErrorDetail erro = objectMapper.readValue(contentAsString, ErrorDetail.class);

        assertThat(erro.getErrors()).containsKeys("descricao", "valor", "data", "codigoUsuario");
    }

    @Test
    public void deveCategorizarUmGastoComSucesso() throws Exception {
        final GastosDTO gasto = enhancedRandom.nextObject(GastosDTO.class);
        gasto.setCategoria("Categoria");

        when(gastosCommandService.categorizarGasto(any(CategorizarGastoRequest.class))).thenReturn(gasto);

        CategorizarGastoRequest request = new CategorizarGastoRequest();
        request.setCategoria("Categoria");

        final String contentAsString = mockMvc.perform(put("/gastos/v1/{idGasto}", 1L, 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

        GastosDTO retorno = objectMapper.readValue(contentAsString, GastosDTO.class);

        assertThat(retorno.getCategoria()).isEqualTo(gasto.getCategoria());
    }

    @Test
    public void naoDevePermitirCategoriaVazia() throws Exception {

        CategorizarGastoRequest request = new CategorizarGastoRequest();

        final String contentAsString = mockMvc.perform(put("/gastos/v1/{idGasto}", 1L, 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ErrorDetail erro = objectMapper.readValue(contentAsString, ErrorDetail.class);

        assertThat(erro.getErrors()).containsKeys("categoria");
    }

}