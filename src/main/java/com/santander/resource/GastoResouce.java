package com.santander.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.santander.model.Gasto;
import com.santander.model.dto.GastoDTO;
import com.santander.repository.GastoRepositoy;
import com.santander.repository.filter.GastoFilter;
import com.santander.service.GastoService;

@RestController
@RequestMapping("/")
public class GastoResouce {

	@Autowired
	private GastoService gastoService;
	@Autowired
	private GastoRepositoy gastoRepository;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Gasto> criarGasto(@Valid @RequestBody Gasto gasto) {

		Gasto gastoSalvo = gastoService.salvar(gasto);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/usuario/{codigoUsuario}")
				.buildAndExpand(gastoSalvo.getCodigoUsuario()).toUri();

		return ResponseEntity.created(uri).body(gastoSalvo);
	}

	@GetMapping("/{codigoUsuario}/listagemGasto")
	public ResponseEntity<List<Gasto>> buscarGastoPor(GastoFilter filter, @PathVariable int codigoUsuario) {
		List<Gasto> gasto = gastoService.filtrar(filter, codigoUsuario);
		return !gasto.isEmpty() ? ResponseEntity.ok(gasto) : ResponseEntity.notFound().build();
	}

	@PutMapping("/{codigo}/categoria")
	public void alterarCategoria(@PathVariable Long codigo, @RequestBody String categoria) {
		gastoService.alterarCategoria(codigo, categoria);
	}

	@GetMapping("/categoria/filtro")
	public @ResponseBody List<GastoDTO> pesquisarCategoria(String categoria) {
		return gastoRepository.buscarPorCategoria(categoria);
	}

}