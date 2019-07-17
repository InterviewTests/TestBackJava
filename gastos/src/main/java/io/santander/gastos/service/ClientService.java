package io.santander.gastos.service;

import io.santander.gastos.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ClientService {
    private final ClientRepository clientRepository;

    public boolean verifyUser(Long id){
        return clientRepository.existsById(id);
    }
}
