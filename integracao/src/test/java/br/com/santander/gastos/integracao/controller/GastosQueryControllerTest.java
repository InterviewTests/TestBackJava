package br.com.santander.gastos.integracao.controller;

import br.com.santander.gastos.integracao.dto.GastosDTO;
import br.com.santander.gastos.integracao.mappers.GastosMapper;
import br.com.santander.gastos.integracao.service.GastosQueryService;
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
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GastosQueryController.class)
public class GastosQueryControllerTest {

    private GastosMapper gastosMapper = Mappers.getMapper(GastosMapper.class);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GastosQueryService gastosQueryService;

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
    public void deveConsultarGastosComSucesso() throws Exception {
        List<GastosDTO> dtoList = enhancedRandom.objects(GastosDTO.class, 10).collect(Collectors.toList());
        Page<GastosDTO> page = new PageImpl<>(dtoList);

        when(gastosQueryService.consultarUltimosGastos(Mockito.anyLong(), Mockito.any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/{id}/gastos", 1))
                .andDo(print())
                .andExpect(status().isOk());
    }
}