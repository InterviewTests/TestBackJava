package br.com.santander.gastos.integracao.mappers;

import br.com.santander.gastos.integracao.dto.GastosDTO;
import br.com.santander.gastos.integracao.entity.GastoEntity;
import org.mapstruct.Mapper;

@Mapper
public interface GastosMapper {

    GastosDTO entityToDTO(GastoEntity gastoEntity);
}
