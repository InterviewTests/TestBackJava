package io.santander.gastos.mapper;

import io.santander.gastos.commons.AbstractMapper;
import io.santander.gastos.domain.CreditCard;
import io.santander.gastos.dto.CreditCardDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CardMapper extends AbstractMapper<CreditCard, CreditCardDTO> {
    @Override
    public CreditCardDTO toDTO(CreditCard entity) {
        return super.toDTO(entity);
    }

    @Override
    public CreditCard toEntity(CreditCardDTO dto) {
        return super.toEntity(dto);
    }

    @Override
    public List<CreditCardDTO> toDTOList(List<CreditCard> entity) {
        return super.toDTOList(entity);
    }

    @Override
    public List<CreditCard> toEntityList(List<CreditCardDTO> dto) {
        return super.toEntityList(dto);
    }
}
