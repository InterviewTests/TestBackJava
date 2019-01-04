package br.com.santander.gastos.query.mappers;

import br.com.santander.gastos.query.dto.GastosDTO;
import br.com.santander.gastos.query.entity.GastoEntity;
import org.mapstruct.Mapper;

@Mapper
public interface GastosMapper {

    GastosDTO entityToDTO(GastoEntity gastoEntity);

}
