package br.com.santander.gastos.integracao.service;

import br.com.santander.gastos.integracao.dto.AdicionarGastoRequest;
import br.com.santander.gastos.integracao.dto.CategorizarGastoRequest;
import br.com.santander.gastos.integracao.dto.GastosDTO;

public interface GastosCommandService {
    GastosDTO adicionarGasto(AdicionarGastoRequest adicionarGastoRequest);

    GastosDTO categorizarGasto(CategorizarGastoRequest categorizarGastoRequest);
}
