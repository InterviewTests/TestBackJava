package br.com.santander.gastos.sugestaocategorias.mappers;

import br.com.santander.gastos.sugestaocategorias.dto.CategoriaDTO;
import br.com.santander.gastos.sugestaocategorias.dto.CategoriasCliente;
import org.mapstruct.Mapper;

@Mapper
public interface CategoriaMapper {

    CategoriaDTO entityToDTO(CategoriasCliente gastoEntity);
}
