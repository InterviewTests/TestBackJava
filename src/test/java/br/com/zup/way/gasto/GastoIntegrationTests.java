package br.com.zup.way.gasto;

import br.com.zup.way.controllers.GastoController;
import br.com.zup.way.db.solr.model.dto.FiltroCategorizarGastoDTO;
import br.com.zup.way.db.solr.model.dto.FiltroGastoDTO;
import br.com.zup.way.db.solr.model.dto.GastoDTO;
import br.com.zup.way.db.solr.model.dto.IntegrateGastoDTO;
import br.com.zup.way.util.DateUtil;
import br.com.zup.way.util.Exception.WayBusinessException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
@WithUserDetails
@Profile("test")
public class GastoIntegrationTests {

    @Autowired
    GastoController gastoController;
    private LocalDateTime dataCadastro = DateUtil.setZone(LocalDateTime.of(2019, 01, 01, 8, 00));

    @Test
    public void testIntegrateGastoWithouCategoria() throws WayBusinessException {
        GastoDTO gasto = gastoController.integrate(new IntegrateGastoDTO(2L, "SuperMercado Santana", 200.20D, dataCadastro));
        Assert.assertEquals(createGasto("SuperMercado Santana", 200.20D, null), gasto);
    }

    @Test
    public void testIntegrateGastoWithCategoria() throws WayBusinessException {
        GastoDTO gasto = gastoController.integrate(new IntegrateGastoDTO(2L, "TAM SITE", 400D, dataCadastro));
        Assert.assertEquals(createGasto("TAM SITE", 400D, "Viagem"), gasto);
    }

    @Test(expected = WayBusinessException.class)
    public void testIntegrateGastoWithoutValidUser() throws WayBusinessException {
        gastoController.integrate(new IntegrateGastoDTO(3L, "Inclusão Cliente Invalido", 200.20D, dataCadastro));
    }

    @Test
    public void testCategorizarGasto() throws WayBusinessException {
        GastoDTO gasto = gastoController.categorizarGasto(new FiltroCategorizarGastoDTO("Viagem", "513b08d9-b286-4da4-a09f-33d15687e972"));
        Assert.assertEquals(createGasto("TAM SITE", 300D, "Viagem"), gasto);
    }

    @Test
    public void testSuggestCategoria() {
        Set<String> categorias = gastoController.suggestCategoria("agem");
        Assert.assertEquals(new HashSet<>(Arrays.asList("Viagem")), categorias);
    }

    @Test
    public void testFind() {
        List<GastoDTO> gasto = gastoController.find(new FiltroGastoDTO(dataCadastro));
        Assert.assertEquals(
                Arrays.asList(
                        createGasto("TAM SITE", 300D, null),
                        createGasto("Padaria No Céu", 50D, "Restaurante"),
                        createGasto("Uber Do Brasil", 10D, "Transporte")
                ), gasto);

    }

    @Test
    public void testlistLast() {
        List<GastoDTO> gasto = gastoController.listLast();
        Assert.assertEquals(
                Arrays.asList(
                        createGasto("Uber Do Brasil", 10D, "Transporte"),
                        createGasto("Padaria No Céu", 50D, "Restaurante"),
                        createGasto("SuperMercado Santana", 200.2D, null),
                        createGasto("TAM SITE", 300D, "Viagem"),
                        createGasto("TAM SITE", 400D, "Viagem")
                ), gasto);
    }


    /**
     * Private Methods
     */

    private GastoDTO createGasto(String descricao, double valor, String categoria) {
        return new GastoDTO(null, descricao, valor, 2L, dataCadastro, categoria);
    }
}
