package br.com.santander.gastos.integracao.controller;

import br.com.santander.gastos.integracao.dto.AdicionarGastoRequest;
import br.com.santander.gastos.integracao.dto.GastoAdicionadoResponse;
import br.com.santander.gastos.integracao.service.GastosCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class IntegracaoCommandController {

    private final GastosCommandService adicionarGastosService;

    @Autowired
    public IntegracaoCommandController(GastosCommandService adicionarGastosService){
        this.adicionarGastosService = adicionarGastosService;
    }

    @PutMapping(value = "/gastos")
    public GastoAdicionadoResponse adicionarGastos(
            @RequestBody @Valid AdicionarGastoRequest adicionarGastoRequest
    ){

        adicionarGastosService.adicionarGasto(adicionarGastoRequest);

        return null;
    }
}
