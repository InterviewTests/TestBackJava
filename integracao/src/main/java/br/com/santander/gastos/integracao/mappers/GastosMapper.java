package br.com.santander.gastos.integracao.mappers;

import br.com.santander.gastos.integracao.dto.AdicionarGastoRequest;
import br.com.santander.gastos.integracao.dto.GastosDTO;
import br.com.santander.gastos.integracao.entity.GastoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface GastosMapper {

    GastosDTO entityToDTO(GastoEntity gastoEntity);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "categoria", ignore = true)
    })
    GastosDTO requestToDTO(AdicionarGastoRequest request);
}
