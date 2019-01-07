package br.com.santander.gastos.sugestaocategorias.service;

import br.com.santander.gastos.sugestaocategorias.dto.AdicionarCategoriaRequest;
import br.com.santander.gastos.sugestaocategorias.dto.CategoriasClienteDTO;

public interface CategoriaCommandService {

    CategoriasClienteDTO adicionarCategoria(AdicionarCategoriaRequest adicionarGastoRequest);

}
