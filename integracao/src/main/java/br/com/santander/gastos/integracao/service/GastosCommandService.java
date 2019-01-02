package br.com.santander.gastos.integracao.service;

import br.com.santander.gastos.integracao.dto.AdicionarGastoRequest;

public interface GastosCommandService {
    void adicionarGasto(AdicionarGastoRequest adicionarGastoRequest);
}
