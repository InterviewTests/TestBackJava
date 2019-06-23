package io.santander.gastos.web.controller;

import io.santander.gastos.service.GastosService;
import io.santander.gastos.vo.GastosVO;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(GastosController.GASTOS_ENDPOINT)
@Validated
public class GastosController {

    public static final String GASTOS_ENDPOINT = "/gastos";

    private GastosService gastosService;

    @GetMapping("/{codigoUsuario}")
    PageImpl<GastosVO> buscaTodosOsGastoPorCliente(@Valid @PathVariable final Long codigoUsuario, final GastosVO vo, Pageable pageable){
        return gastosService.buscaTodosOsGastoPorCliente(codigoUsuario, vo, pageable);
    }
}
