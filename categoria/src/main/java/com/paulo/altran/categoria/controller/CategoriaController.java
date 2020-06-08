package com.paulo.altran.categoria.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paulo.altran.categoria.dto.CategoriaCadastroDTO;
import com.paulo.altran.categoria.dto.CategoriaRetornoDTO;
import com.paulo.altran.categoria.model.Categoria;
import com.paulo.altran.categoria.service.CategoriaService;

@RestController
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@PreAuthorize("hasRole('ROLE_CADASTRAR_CATEGORIA')")
	@PostMapping
	public CategoriaRetornoDTO salvarCategoria(@RequestBody @Valid CategoriaCadastroDTO dto) {
		Categoria categoria = dto.convertToCategoria();
		Categoria categoriaSalva = categoriaService.salvarCategoria(categoria);
		CategoriaRetornoDTO retornoDTO = CategoriaRetornoDTO.convertToCategoriaRetornoDTO(categoriaSalva);
		return retornoDTO;
	}

	@PreAuthorize("hasRole('ROLE_CONSULTAR_CATEGORIA')")
	@GetMapping("/{id}")
	public CategoriaRetornoDTO buscarPeloId(@PathVariable Long id) {
		Categoria categoriaSalva = categoriaService.buscarPeloId(id);
		CategoriaRetornoDTO retornoDTO = CategoriaRetornoDTO.convertToCategoriaRetornoDTO(categoriaSalva);
		return retornoDTO;
	}

	@PreAuthorize("hasRole('ROLE_CONSULTAR_CATEGORIA')")
	@GetMapping
	public Page<CategoriaRetornoDTO> buscarPeloNome(@RequestParam(name = "nome", required = false) String nome,
			Pageable pageable) {
		Page<Categoria> categorias = categoriaService.buscarCategoria(nome, pageable);
		List<CategoriaRetornoDTO> lista = categorias.getContent().stream()
				.map(cat -> CategoriaRetornoDTO.convertToCategoriaRetornoDTO(cat)).collect(Collectors.toList());
		return new PageImpl<CategoriaRetornoDTO>(lista, pageable, categorias.getTotalElements());
	}

	@PreAuthorize("hasRole('ROLE_CONSULTAR_CATEGORIA')")
	@GetMapping("/pesquisa/nome")
	public CategoriaRetornoDTO pesquisarPeloNomeExato(@RequestParam String termo) {
		Categoria categoria = categoriaService.buscarCategoriaPeloNomeExato(termo);
		CategoriaRetornoDTO retornoDTO = CategoriaRetornoDTO.convertToCategoriaRetornoDTO(categoria);
		return retornoDTO;
	}

}
