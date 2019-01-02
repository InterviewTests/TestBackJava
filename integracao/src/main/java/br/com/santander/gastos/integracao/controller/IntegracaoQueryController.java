package br.com.santander.gastos.integracao.controller;

import br.com.santander.gastos.integracao.dto.GastosDTO;
import br.com.santander.gastos.integracao.service.GastosQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public class IntegracaoQueryController {

    private final GastosQueryService gastosQueryService;

    @Autowired
    public IntegracaoQueryController(GastosQueryService gastosQueryService){
        this.gastosQueryService = gastosQueryService;
    }

    @GetMapping(value = "/{codigoUsuario}/gastos")
    public Page<GastosDTO> consultar(
            @PathVariable("codigoUsuario") Long codigoUsuario,
            Pageable pageable
    ){
        return gastosQueryService.consultarGastos(codigoUsuario, pageable);
    }
}
