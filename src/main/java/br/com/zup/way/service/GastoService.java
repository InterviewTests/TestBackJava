package br.com.zup.way.service;

import br.com.zup.way.db.solr.model.dto.FiltroCategorizarGastoDTO;
import br.com.zup.way.db.solr.model.dto.FiltroGastoDTO;
import br.com.zup.way.db.solr.model.dto.GastoDTO;
import br.com.zup.way.db.solr.model.dto.IntegrateGastoDTO;
import br.com.zup.way.util.Exception.WayBusinessException;

import java.util.List;
import java.util.Set;

public interface GastoService {

    GastoDTO categorizarGasto(FiltroCategorizarGastoDTO filtro) throws WayBusinessException;

    GastoDTO integrateGasto(IntegrateGastoDTO gastoDTO) throws WayBusinessException;

    List<GastoDTO> findLastGastos(Long codigoUsuario);

    List<GastoDTO> findGastosByDate(Long codigoUsuario, FiltroGastoDTO filtroData);

    Set<String> findCategoria(String nomeCategoria);

}
