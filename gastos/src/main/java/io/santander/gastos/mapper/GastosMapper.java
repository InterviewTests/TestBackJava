package io.santander.gastos.mapper;

import commons.AbstractMapper;
import io.santander.gastos.domain.Gasto;
import io.santander.gastos.dto.GastoDTO;

import java.util.List;

public class GastosMapper extends AbstractMapper<Gasto, GastoDTO> {
    @Override
    public GastoDTO toDTO(Gasto entity) {
        return super.toDTO(entity);
    }

    @Override
    public Gasto toEntity(GastoDTO dto) {
        return super.toEntity(dto);
    }

    @Override
    public List<GastoDTO> toDTOList(List<Gasto> entity) {
        return super.toDTOList(entity);
    }

    @Override
    public List<Gasto> toEntityList(List<GastoDTO> dto) {
        return super.toEntityList(dto);
    }
}
