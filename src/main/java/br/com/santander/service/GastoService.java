package br.com.santander.service;

import br.com.santander.domain.Gasto;
import br.com.santander.repository.GastosRepository;
import org.springframework.stereotype.Service;

@Service
public class GastoService {

    private GastosRepository repository;

    public Gasto incluir(Gasto gasto) {
        return repository.save(gasto);
    }
}
