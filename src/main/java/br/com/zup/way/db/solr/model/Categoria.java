package br.com.zup.way.db.solr.model;

import br.com.zup.way.db.solr.model.dto.CategoriaDTO;
import br.com.zup.way.util.mapper.CategoriaMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SolrDocument(collection = "CategoriaSolr")
public class Categoria {

    @Id
    @Field
    private String id;

    @Field("nome_text_pt")
    private String nome;

    @Field
    private Long codigoUsuario;

    public static Categoria convert(CategoriaDTO categoriaDTO) {
        return CategoriaMapper.INSTANCE.categoriaDTOtoCategoria(categoriaDTO);
    }
}
