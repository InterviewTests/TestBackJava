package br.com.zup.way.gasto;

import br.com.zup.way.db.solr.model.Gasto;
import br.com.zup.way.db.solr.model.dto.FiltroCategorizarGastoDTO;
import br.com.zup.way.db.solr.repository.GastoRepository;
import br.com.zup.way.service.impl.GastoServiceImpl;
import br.com.zup.way.util.DateUtil;
import br.com.zup.way.util.Exception.WayBusinessException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GastoApplicationTests {

    @Mock
    GastoRepository gastoRepositoryMock;

    @InjectMocks
    GastoServiceImpl gastoService;

    @Test(expected = WayBusinessException.class)
    public void categorizarGasto() throws WayBusinessException {
        LocalDateTime data = DateUtil.setZone(LocalDateTime.now());
        when(gastoRepositoryMock.findById("7bd92702-ddc2-4286-a2ec-1e362c5d77b3"))
                .thenReturn(returnGasto("7bd92702-ddc2-4286-a2ec-1e362c5d77b3", "Supermercado Extra", 200D, 2L, "SuperMercado", data));
        gastoService.categorizarGasto(returnFiltroCategorizarGastoDTO("Supermercado Extra", "7bd92702-ddc2-4286-a2ec-1e362c5d77b3"));
    }

    private FiltroCategorizarGastoDTO returnFiltroCategorizarGastoDTO(String categoria, String idGasto) {
        return new FiltroCategorizarGastoDTO(categoria, idGasto);
    }

    /**
     * Private Methods
     *
     * @return
     */
    private Optional<Gasto> returnGasto(String id, String descricao, Double valor, Long codigoUsuario, String categoria, LocalDateTime dataCadastro) {
        return Optional.of(new Gasto(id, descricao, valor, codigoUsuario, categoria, dataCadastro));
    }

}