package com.company.gestaogastos.services;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.company.gestaogastos.domain.dto.CategoriaDTO;

public interface CategoriaService {

	public Page<CategoriaDTO> retrieveCategorias(Map<String, String> allRequestParams);

	public CategoriaDTO retrieveCategoria(long id);
	
	public void deleteCategoria(long id);

	public CategoriaDTO createCategoria(CategoriaDTO categoria);
	
	public CategoriaDTO updateCategoria(CategoriaDTO categoria, long id);
}