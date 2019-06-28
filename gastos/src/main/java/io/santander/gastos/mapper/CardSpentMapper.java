package io.santander.gastos.mapper;

import io.santander.gastos.commons.AbstractMapper;
import io.santander.gastos.domain.CardSpent;
import io.santander.gastos.dto.CardSpentDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CardSpentMapper extends AbstractMapper<CardSpent, CardSpentDTO> {
    @Override
    public CardSpentDTO toDTO(CardSpent entity) {
        return super.toDTO(entity);
    }

    @Override
    public CardSpent toEntity(CardSpentDTO dto) {
        return super.toEntity(dto);
    }

    @Override
    public List<CardSpentDTO> toDTOList(List<CardSpent> entity) {
        return super.toDTOList(entity);
    }

    @Override
    public List<CardSpent> toEntityList(List<CardSpentDTO> dto) {
        return super.toEntityList(dto);
    }
}
