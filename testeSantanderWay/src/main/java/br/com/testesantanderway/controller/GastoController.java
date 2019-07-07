package br.com.testesantanderway.controller;

import br.com.testesantanderway.config.security.AutenticacaoViaTokenFilter;
import br.com.testesantanderway.config.security.ServicoDeToken;
import br.com.testesantanderway.controller.form.GastoForm;
import br.com.testesantanderway.dto.GastoDTO;
import br.com.testesantanderway.modelo.Gasto;
import br.com.testesantanderway.service.GastoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("gasto")
public class GastoController {
    @Autowired
    private ServicoDeToken servicoDeToken;

    @Autowired
    private GastoService gastoService;

    //TODO permitir apenas USUARIO listar gastos
    @GetMapping
    public ResponseEntity<List<GastoDTO>> listar(HttpServletRequest request) {
        String codigoUsuario = servicoDeToken.getCodigo(AutenticacaoViaTokenFilter.recuperarToken(request));
        List<Gasto> gastos = gastoService.listarGastosMaisRecentes(codigoUsuario);
        return ResponseEntity.ok(GastoDTO.converter(gastos));
    }

    //TODO permitir apenas SISTEMA lançar gasto
    @PutMapping
    public ResponseEntity lancar(HttpServletRequest request, @RequestBody GastoForm form) {
        Gasto gasto = form.converter(servicoDeToken.getCodigo(AutenticacaoViaTokenFilter.recuperarToken(request)));
        gastoService.lancar(gasto);
        return ResponseEntity.ok().build();
    }

    //TODO permitir apenas USUARIO listar gastos
    @Cacheable("gastoUsuario")
    @GetMapping("/{dataCriacao}")
    public Page<GastoDTO> filtro(HttpServletRequest request,
                                 @PathVariable LocalDate dataCriacao,
                                 @PageableDefault(sort = "dataCriacao", direction = Sort.Direction.DESC) Pageable paginacao) {
        String codigoUsuario = servicoDeToken.getCodigo(AutenticacaoViaTokenFilter.recuperarToken(request));
        return GastoDTO.converter(gastoService.encontrarGastosDoDia(codigoUsuario, dataCriacao, paginacao));
    }

    //TODO permitir apenas USUARIO categorizar gasto
    @PutMapping("categorizar")
    public ResponseEntity categorizar(@RequestBody Gasto gasto) {
        gastoService.categorizarGasto(gasto);
        return ResponseEntity.ok().build();
    }

}