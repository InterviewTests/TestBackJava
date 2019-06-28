package io.santander.gastos.service;

import io.santander.gastos.repository.ClientCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ClientCardService {

    private final ClientCardRepository clientCardRepository;

    public boolean verifiCardWoner(long client, long card) {
        return clientCardRepository.existsByClientAndCard(client, card);

    }
}
