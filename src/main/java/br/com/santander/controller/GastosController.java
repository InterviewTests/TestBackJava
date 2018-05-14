package br.com.santander.controller;

import br.com.santander.ServletInitializer;
import br.com.santander.domain.Gasto;
import br.com.santander.service.GastoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/gastos")
public class GastosController {

    @Autowired
    private GastoService gastoService;

    public ResponseEntity<Void> incluir(Gasto gasto) {
        gasto = gastoService.incluir(gasto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(gasto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
