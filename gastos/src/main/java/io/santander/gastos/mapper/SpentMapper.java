package io.santander.gastos.mapper;

import commons.AbstractMapper;
import io.santander.gastos.domain.Spent;
import io.santander.gastos.dto.SpentDTO;

import java.util.List;

public class GastosMapper extends AbstractMapper<Spent, SpentDTO> {
    @Override
    public SpentDTO toDTO(Spent entity) {
        return super.toDTO(entity);
    }

    @Override
    public Spent toEntity(SpentDTO dto) {
        return super.toEntity(dto);
    }

    @Override
    public List<SpentDTO> toDTOList(List<Spent> entity) {
        return super.toDTOList(entity);
    }

    @Override
    public List<Spent> toEntityList(List<SpentDTO> dto) {
        return super.toEntityList(dto);
    }
}
