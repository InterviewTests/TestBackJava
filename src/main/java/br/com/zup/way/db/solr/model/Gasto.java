package br.com.zup.way.db.solr.model;

import br.com.zup.way.db.solr.model.dto.GastoDTO;
import br.com.zup.way.db.solr.model.dto.IntegrateGastoDTO;
import br.com.zup.way.util.mapper.GastoMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SolrDocument(collection = "GastoSolr")
public class Gasto {

    @Id
    @Field
    private String id;

    @Field("descricao_text_pt")
    private String descricao;

    @Field
    private Double valor;

    @Field
    private Long codigoUsuario;

    @Field("categoria_text_pt")
    private String categoria;

    @Field("data_cadastro_dt")
    private LocalDateTime dataCadastro;

    public static Gasto convert(IntegrateGastoDTO gastoDTO)  {
        return GastoMapper.INSTANCE.gastoDTOtoGasto(gastoDTO);
    }

}
