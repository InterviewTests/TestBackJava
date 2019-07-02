package br.com.testesantanderway.controller;

import br.com.testesantanderway.controller.form.GastoForm;
import br.com.testesantanderway.dto.DetalheGastosDTO;
import br.com.testesantanderway.dto.GastosDTO;
import br.com.testesantanderway.modelo.Gasto;
import br.com.testesantanderway.repository.GastoRepository;
import br.com.testesantanderway.util.UtilDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/gastos")
public class GastosController {
    @Autowired
    private GastoRepository gastoRepository;

    @GetMapping
    @Cacheable(value = "gastoDeCliente")
    public Page<GastosDTO> dadosDosGastos(@RequestParam(required = false) String descricao,
                                          @PageableDefault(sort = "codigoUsuario",
                                                  direction = Sort.Direction.ASC) Pageable paginacao) {

        if (descricao == null || descricao.isEmpty()) {
            Page<Gasto> gastos = gastoRepository.findAll(paginacao);

            return GastosDTO.converter(gastos);
        } else {
            Page<Gasto> gastos = gastoRepository.findByDescricao(descricao, paginacao);

            return GastosDTO.converter(gastos);
        }
    }

    @PostMapping
    public ResponseEntity<GastosDTO> lancarGastos(@RequestBody GastoForm form, UriComponentsBuilder uriBuilder) {
        Gasto gastoCadastro = form.converter();
        gastoRepository.save(gastoCadastro);
        URI uri = uriBuilder.path("/{id}").buildAndExpand(gastoCadastro.getCodigoUsuario()).toUri();

        return ResponseEntity.created(uri).body(new GastosDTO(gastoCadastro));
    }

    @GetMapping("/{dataCriacao}")
    public ResponseEntity<DetalheGastosDTO> detalheGastosPorData(@PathVariable LocalDateTime dataCriacao) {
        //String utilDate = UtilDate.format(dataCriacao);
        Optional<Gasto> gasto = gastoRepository.findByDataCriacao(dataCriacao);
        if (gasto.isPresent()) {
            return ResponseEntity.ok(new DetalheGastosDTO(gasto.get()));
        }

        return ResponseEntity.notFound().build();
    }
}