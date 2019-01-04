package br.com.santander.gastos.sugestaocategorias.mappers;

import br.com.santander.gastos.sugestaocategorias.dto.CategoriaDTO;
import br.com.santander.gastos.sugestaocategorias.dto.CategoriaDocument;
import br.com.santander.gastos.sugestaocategorias.dto.CategoriasCliente;
import br.com.santander.gastos.sugestaocategorias.dto.CategoriasClienteDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CategoriaMapper {

    CategoriaDTO entityToDTO(CategoriaDocument document);

    CategoriasClienteDTO entityToDTO(CategoriasCliente document);
}
