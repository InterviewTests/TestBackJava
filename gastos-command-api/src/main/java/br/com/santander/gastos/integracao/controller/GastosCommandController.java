package br.com.santander.gastos.integracao.controller;

import br.com.santander.gastos.integracao.dto.AdicionarGastoRequest;
import br.com.santander.gastos.integracao.dto.CategorizarGastoRequest;
import br.com.santander.gastos.integracao.dto.GastosDTO;
import br.com.santander.gastos.integracao.service.FilaServiceImpl;
import br.com.santander.gastos.integracao.service.GastosCommandService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class GastosCommandController {

    private final GastosCommandService gastosCommandService;

    private final FilaServiceImpl filaService;

    @Autowired
    public GastosCommandController(GastosCommandService adicionarGastosService, FilaServiceImpl filaService) {
        this.gastosCommandService = adicionarGastosService;
        this.filaService = filaService;
    }

    @PostMapping(value = "/gastos/v1/gastos/")
    public GastosDTO adicionarGastos(
            @RequestBody @Valid AdicionarGastoRequest adicionarGastoRequest
    ) throws JsonProcessingException {
        final GastosDTO gastosDTO = gastosCommandService.adicionarGasto(adicionarGastoRequest);

        filaService.enviarMensagem(gastosDTO);

        return gastosDTO;
    }

    @PutMapping(value = "/gastos/v1/gastos/{idGasto}")
    public GastosDTO categorizarGasto(
            @RequestBody @Valid CategorizarGastoRequest categorizarGastoRequest,
            @PathVariable("idGasto") Long idGasto) throws JsonProcessingException {

        categorizarGastoRequest.setId(idGasto);

        final GastosDTO gastosDTO = gastosCommandService.categorizarGasto(categorizarGastoRequest);

        filaService.enviarMensagem(gastosDTO);

        return gastosDTO;
    }

}
