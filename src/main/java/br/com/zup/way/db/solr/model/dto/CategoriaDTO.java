package br.com.zup.way.db.solr.model.dto;

import br.com.zup.way.db.solr.model.Categoria;
import br.com.zup.way.util.mapper.CategoriaMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO {

    private String nome;

    private Long codigoUsuario;

    public static List<CategoriaDTO> convert(List<Categoria> categorias) {
        return CategoriaMapper.INSTANCE.categoriaToCategoriaDTO(categorias);
    }

    public static CategoriaDTO convert(Categoria categoria) {
        return CategoriaMapper.INSTANCE.categoriaToCategoriaDTO(categoria);
    }
}
