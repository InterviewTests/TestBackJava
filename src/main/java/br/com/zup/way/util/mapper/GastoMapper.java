package br.com.zup.way.util.mapper;

import br.com.zup.way.db.solr.model.Gasto;
import br.com.zup.way.db.solr.model.dto.GastoDTO;
import br.com.zup.way.db.solr.model.dto.IntegrateGastoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GastoMapper {

    GastoMapper INSTANCE = Mappers.getMapper(GastoMapper.class);

    GastoDTO gastoToGastoDTO(Gasto gasto);

    List<GastoDTO> gastoToGastoDTO(List<Gasto> gasto);

    Gasto gastoDTOtoGasto(GastoDTO gasto);

    Gasto gastoDTOtoGasto(IntegrateGastoDTO gastoDTO);
}
