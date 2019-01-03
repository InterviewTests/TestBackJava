package br.com.santander.gastos.integracao.controller;

import br.com.santander.gastos.integracao.dto.AdicionarGastoRequest;
import br.com.santander.gastos.integracao.dto.CategorizarGastoRequest;
import br.com.santander.gastos.integracao.dto.GastosDTO;
import br.com.santander.gastos.integracao.service.GastosCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path="/api/v1")
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

    @PutMapping(value = "{codigoUsuario}/gastos/{idGasto}")
    public GastosDTO categorizarGasto(
            @RequestBody @Valid CategorizarGastoRequest categorizarGastoRequest,
            @PathVariable("codigoUsuario") Long codigoUsuario,
            @PathVariable("idGasto") Long idGasto) {

        categorizarGastoRequest.setCodigoUsuario(codigoUsuario);
        categorizarGastoRequest.setId(idGasto);

        return gastosCommandService.categorizarGasto(categorizarGastoRequest);
    }

}
