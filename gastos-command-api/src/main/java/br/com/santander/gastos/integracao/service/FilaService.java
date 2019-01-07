package br.com.santander.gastos.integracao.service;

import br.com.santander.gastos.integracao.dto.GastosDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface FilaService {
    void enviarMensagem(GastosDTO gastosDTO) throws JsonProcessingException;
}
