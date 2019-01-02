package br.com.santander.gastos.integracao.service;

import br.com.santander.gastos.integracao.dto.GastosDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GastosQueryService {
    Page<GastosDTO> consultarGastos(Long idCliente, Pageable pageable);
}
