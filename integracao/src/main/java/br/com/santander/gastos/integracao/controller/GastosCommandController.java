package br.com.santander.gastos.integracao.controller;

import br.com.santander.gastos.integracao.dto.AdicionarGastoRequest;
import br.com.santander.gastos.integracao.dto.GastosDTO;
import br.com.santander.gastos.integracao.service.GastosCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class GastosCommandController {

    private final GastosCommandService gastosCommandService;

    @Autowired
    public GastosCommandController(GastosCommandService adicionarGastosService) {
        this.gastosCommandService = adicionarGastosService;
    }

    @PostMapping(value = "/gastos")
    public GastosDTO adicionarGastos(
            @RequestBody @Valid AdicionarGastoRequest adicionarGastoRequest
    ) {
        return gastosCommandService.adicionarGasto(adicionarGastoRequest);
    }

}
