package br.com.santander.controller;

import br.com.santander.domain.Gasto;
import br.com.santander.service.GastoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/gastos")
public class GastosController {

    @Autowired
    private GastoService gastoService;

    @RequestMapping(value = "/incluir", method = RequestMethod.POST)
    public ResponseEntity<List<Gasto>> incluir(@Valid @RequestBody Gasto gasto) {
        gasto = gastoService.incluir(gasto);
        return listarGastos(gasto.getCodigoUsuario());
    }

    @RequestMapping(value = "/listar/{user}", method = RequestMethod.GET)
    public ResponseEntity<List<Gasto>> listarGastos(@PathVariable(value = "user") long user){
        return ResponseEntity.status(HttpStatus.OK).body(gastoService.listarGastos(user));
    }
}
