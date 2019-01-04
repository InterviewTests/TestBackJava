package br.com.santander.gastos.sugestaocategorias.service;

import br.com.santander.gastos.sugestaocategorias.dto.AdicionarCategoriaRequest;
import br.com.santander.gastos.sugestaocategorias.dto.CategoriaDTO;

public interface CategoriaCommandService {

    CategoriaDTO adicionarCategoria(AdicionarCategoriaRequest adicionarGastoRequest);

}
