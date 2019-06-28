package io.santander.gastos.service;

import io.santander.gastos.dto.CardSpentDTO;
import io.santander.gastos.mapper.CardSpentMapper;
import io.santander.gastos.repository.CardSpentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CardSpentService {
    private final CardSpentRepository cardSpentRepository;
    private final CardSpentMapper cardSpentMapper;

    public void save(CardSpentDTO cardSpentDTO){
        cardSpentRepository.save(cardSpentMapper.toEntity(cardSpentDTO));
    }

}
