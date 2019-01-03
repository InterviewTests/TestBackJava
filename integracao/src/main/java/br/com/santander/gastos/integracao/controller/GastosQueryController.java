package br.com.santander.gastos.integracao.controller;

import br.com.santander.gastos.integracao.dto.GastosDTO;
import br.com.santander.gastos.integracao.service.GastosQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/v1")
public class GastosQueryController {

    private final GastosQueryService gastosQueryService;

    @Autowired
    public GastosQueryController(GastosQueryService gastosQueryService){
        this.gastosQueryService = gastosQueryService;
    }

    @GetMapping(value = "/{codigoUsuario}/gastos")
    public Page<GastosDTO> consultar(
            @PathVariable("codigoUsuario") Long codigoUsuario,
            @RequestParam(value = "data", required = false)
            @DateTimeFormat(pattern = "dd/MM/yyyy") Optional<LocalDate> data,
            Pageable pageable
    ){
        Page<GastosDTO> consulta = null;

        if(data.isPresent()){
             consulta = gastosQueryService.consultar(codigoUsuario, data.get(), pageable);
        } else {
            consulta = gastosQueryService.consultarUltimosGastos(codigoUsuario, pageable);
        }

        return consulta;
    }
}
