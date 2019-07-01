package br.com.zup.way.util.mapper;

import br.com.zup.way.db.solr.model.Categoria;
import br.com.zup.way.db.solr.model.dto.CategoriaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoriaMapper {

    CategoriaMapper INSTANCE = Mappers.getMapper(CategoriaMapper.class);

    CategoriaDTO categoriaToCategoriaDTO(Categoria categoria);

    List<CategoriaDTO> categoriaToCategoriaDTO(List<Categoria> categoria);

    @Mapping(target = "id", ignore = true)
    Categoria categoriaDTOtoCategoria(CategoriaDTO categoria);
}
