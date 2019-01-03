package br.com.santander.gastos.sugestaocategorias.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface CategoriaQueryService {
    Page<String> consultarTodasCategorias(String nome, Pageable pageable);
}
