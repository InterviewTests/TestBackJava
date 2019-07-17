package io.santander.gastos.service;

import io.santander.gastos.repository.ClientCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ClientCardService {

    private final ClientCardRepository clientCardRepository;

    public boolean verifyCardHolder(long client, long card) {
        return clientCardRepository.existsByClientAndCard(client, card);

    }

    public List<Long> getClientsCard(long client, String card) {
        return clientCardRepository.findByClientAndCard(client, card);
    }

    public List<Long> getCardsByClient(Long userCode) {
        return clientCardRepository.findAllCardByClient(userCode);
    }
}
