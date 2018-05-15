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
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/gastos")
public class GastosController {

    private static final Logger log = Logger.getLogger(GastosController.class.getName());

    @Autowired
    private GastoService gastoService;

    @RequestMapping(value = "/incluir", method = RequestMethod.POST)
    public ResponseEntity<List<Gasto>> incluir(@Valid @RequestBody Gasto gasto) {
        log.info("Cadastrando gasto.");
        gasto = gastoService.incluir(gasto);
        return listarGastos(gasto.getCodigoUsuario());
    }

    @RequestMapping(value = "/listar/{user}", method = RequestMethod.GET)
    public ResponseEntity<List<Gasto>> listarGastos(@PathVariable(value = "user") long user){
        List<Gasto> listaGastos = gastoService.listarGastos(user);
        log.info("Quantidade de gastos validos: " + listaGastos.size());
        return ResponseEntity.status(HttpStatus.OK).body(listaGastos);
    }

    @RequestMapping(value = "/listar/{user}/{data}", method = RequestMethod.GET)
    public ResponseEntity<List<Gasto>> listarGastosFiltrados(@PathVariable(value = "user") long user,
    @PathVariable(value = "data") String data){
        List<Gasto> result = null;

        try {
            result = gastoService.listarGastosFiltrados(user, data);
        } catch (ParseException e) {
            log.warning("Ocorreu um erro com a data informada: ".concat(data));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        log.info("Quantidade de gastos validos: " + result.size());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
