package com.santander.test.backend.bweninger.controller;

import com.santander.test.backend.bweninger.exception.UsuarioOuSenhaInvalidaException;
import com.santander.test.backend.bweninger.service.GastoService;
import com.santander.test.backend.bweninger.util.JwtUtil;
import com.santander.test.backend.bweninger.vo.CategoriaVO;
import com.santander.test.backend.bweninger.vo.GastoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME;

/**
 * Created by BWeninger on 10/01/2019.
 */
@RestController
public class GastoController {

    @Autowired
    private GastoService gastoService;

    @RequestMapping(value = "/api/gastos", method = RequestMethod.GET)
    public ResponseEntity<List<GastoVO>> getGastos(@RequestParam(value = "data", required = false)
                                                       @DateTimeFormat(iso = DATE_TIME) LocalDateTime data,
                                                   @RequestHeader(JwtUtil.TOKEN_HEADER) String token){
        if(data == null) {
            return new ResponseEntity<>(gastoService.listarMeusGastos(JwtUtil.getCpf(token)), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(gastoService.filtrarGastosPorData(JwtUtil.getCpf(token), data), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/api/gastos/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> cadastrarCategoria(@RequestBody CategoriaVO categoria, @PathVariable("id") UUID idGasto,
                                                   @RequestHeader(JwtUtil.TOKEN_HEADER) String token) {
        try {
            gastoService.cadastrarCategoria(idGasto, categoria, JwtUtil.getCpf(token));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UsuarioOuSenhaInvalidaException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/api/gastos", method = RequestMethod.POST)
    public ResponseEntity<Void> cadastrarGastos(@RequestBody List<GastoVO> gastos,
                                                          @RequestHeader(JwtUtil.TOKEN_HEADER) String token) {
        String cpf = JwtUtil.getCpf(token);
        gastoService.cadastrarGastos(gastos, cpf);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
