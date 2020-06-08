package com.paulo.altran.gasto.controller;

import java.security.Principal;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paulo.altran.gasto.dto.GastoCadastroDTO;
import com.paulo.altran.gasto.dto.GastoFilterDTO;
import com.paulo.altran.gasto.dto.GastoRetornoDTO;
import com.paulo.altran.gasto.model.Gasto;
import com.paulo.altran.gasto.service.GastoService;

@RestController
public class GastoController {

	@Autowired
	private GastoService gastoService;

	@PreAuthorize("hasRole('ROLE_CADASTRAR_GASTO')")
	@PostMapping("/")
	public GastoRetornoDTO salvarGasto(@RequestBody @Valid GastoCadastroDTO dto) throws ParseException {
		Gasto gastoSalvo = gastoService.salvarGasto(dto.convertToGasto());
		GastoRetornoDTO retornoDTO = GastoRetornoDTO.convertToGastoRestornoDTO(gastoSalvo);
		return retornoDTO;
	}

	@PreAuthorize("hasRole('ROLE_ATRIBUIR_CATEGORIA')")
	@PatchMapping("/{id}/categoria")
	public GastoRetornoDTO atribuirCategoriaAoGasto(@PathVariable Long id, @RequestBody String categoria,
			Principal principal) {
		Gasto gasto = gastoService.atribuirCategoria(categoria, id, principal.getName());
		return GastoRetornoDTO.convertToGastoRestornoDTO(gasto);
	}

	@PreAuthorize("hasRole('ROLE_CONSULTAR_GASTO')")
	@GetMapping("/{id}")
	public GastoRetornoDTO obterGastoPorId(@PathVariable Long id, Principal principal) {
		Gasto gasto = gastoService.obterGastoPorId(id, principal.getName());
		return GastoRetornoDTO.convertToGastoRestornoDTO(gasto);
	}

	@PreAuthorize("hasRole('ROLE_CONSULTAR_GASTO')")
	@GetMapping
	public Page<GastoRetornoDTO> filtrarGastos(GastoFilterDTO filterDTO, Principal principal, Pageable pageable)
			throws ParseException {

		Gasto filter = Gasto.builder().descricao(filterDTO.getDescricao()).categoria(filterDTO.getCategoria())
				.valor(filterDTO.getValor()).build();
		Page<Gasto> results = gastoService.obterGastosPorCodigoUsuario(filter, principal.getName(), pageable);

		List<GastoRetornoDTO> gastos = results.getContent().stream()
				.map(x -> GastoRetornoDTO.convertToGastoRestornoDTO(x)).collect(Collectors.toList());
		return new PageImpl<GastoRetornoDTO>(gastos, pageable, results.getTotalElements());
	}

	@PreAuthorize("hasRole('ROLE_CONSULTAR_GASTO')")
	@GetMapping("/pesquisa/data")
	public Page<GastoRetornoDTO> filtrarGastosPorData(@RequestParam String data, Principal principal, Pageable pageable)
			throws ParseException {

		Page<Gasto> results = gastoService.obterGastosPorData(LocalDate.parse(data), principal.getName(), pageable);

		List<GastoRetornoDTO> gastos = results.getContent().stream()
				.map(x -> GastoRetornoDTO.convertToGastoRestornoDTO(x)).collect(Collectors.toList());
		return new PageImpl<GastoRetornoDTO>(gastos, pageable, results.getTotalElements());
	}

}
