package br.com.testesantanderway.controller;

import br.com.testesantanderway.config.security.AutenticacaoViaTokenFilter;
import br.com.testesantanderway.config.security.ServicoDeToken;
import br.com.testesantanderway.controller.form.GastoForm;
import br.com.testesantanderway.dto.DetalheGastosDTO;
import br.com.testesantanderway.dto.GastosDTO;
import br.com.testesantanderway.modelo.Gasto;
import br.com.testesantanderway.repository.GastoRepository;
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
import java.util.Optional;

@RestController
@RequestMapping("/gastos")
public class GastoController {
    @Autowired
    private ServicoDeToken servicoDeToken;

    @Autowired
    private GastoRepository gastoRepository;

    @Autowired
    private GastoService gastoService;

    @GetMapping
    @Cacheable(value = "gastoDeCliente")
    public Page<GastosDTO> listagemDeGastos(@RequestParam(required = false) String descricao,
                                            @PageableDefault(sort = "codigoCliente",
                                                    direction = Sort.Direction.ASC) Pageable paginacao) {

        if (descricao == null || descricao.isEmpty()) {
            Page<Gasto> gastos = gastoRepository.findAll(paginacao);

            return GastosDTO.converter(gastos);
        } else {
            Page<Gasto> gastos = gastoRepository.findByCategoria(descricao, paginacao);

            return GastosDTO.converter(gastos);
        }
    }

    //TODO permitir apenas SISTEMA lançar gasto
    @PutMapping
    public ResponseEntity lancarGastosCartao(HttpServletRequest request, @RequestBody GastoForm form) {
        Gasto gasto = form.converter(servicoDeToken.getCodigo(AutenticacaoViaTokenFilter.recuperarToken(request)));
        gastoService.lancarGastosCartao(gasto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{dataCriacao}")
    public ResponseEntity<DetalheGastosDTO> listagemDeGastosPorData(@PathVariable String dataCriacao) {
        Optional<Gasto> gasto = gastoRepository.findByDataCriacao(dataCriacao);

        if (gasto.isPresent()) {
            return ResponseEntity.ok(new DetalheGastosDTO(gasto.get()));
        }

        return ResponseEntity.notFound().build();
    }

//    @PostMapping("/{categorizaGasto}")
//    public ResponseEntity<GastosDTO> categorizarGastos(@RequestBody CategoriaForm form, UriComponentsBuilder uriBuilder) {
//        Gasto categoriaCadastro = form.converter();
//        gastoRepository.save(categoriaCadastro);
//        URI uri = uriBuilder.path("/{id}").buildAndExpand(categoriaCadastro.getCodigo()).toUri();
//
//        return ResponseEntity.created(uri).body(new GastosDTO(categoriaCadastro));
//    }

}