package io.santander.gastos.service;

import io.santander.gastos.dto.CreditCardDTO;
import io.santander.gastos.mapper.CardMapper;
import io.santander.gastos.repository.CreditCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CardService {
    private final CreditCardRepository creditCardRepository;
    private final CardMapper cardMapper;


    public CreditCardDTO findCard(String cardNumber) {
        return cardMapper.toDTO(creditCardRepository.findByCardNumber(cardNumber));
    }

}
