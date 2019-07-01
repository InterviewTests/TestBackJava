package br.com.zup.way;

import br.com.zup.way.util.DateUtil;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration
@EnableTransactionManagement
@EnableSolrRepositories(basePackages = "br.com.zup.way.db.solr.repository")
@ComponentScan
@Profile("test")
public class WaySolrDbConfig {

    static final String CONFIGSET_DIR = "src/test/resources/configsets";

    private LocalDateTime dataCadastro = DateUtil.setZone(LocalDateTime.of(2019, 01, 01, 8, 00));

    @Bean
    public SolrClient solrClient() {
        return getEmbeddedSolr();
    }

    private SolrClient getEmbeddedSolr() {
        String targetLocation = EmbeddedWaySolrServerFactory.class
                .getProtectionDomain().getCodeSource().getLocation().getFile() + "/..";

        String solrHome = targetLocation + "/solr";

        try {
            SolrClient solrClient = EmbeddedWaySolrServerFactory.create(solrHome, CONFIGSET_DIR, "GastoSolr");

            LocalDateTime dataCadastro = DateUtil.setZone(LocalDateTime.of(2019, 01, 01, 8, 00));

            SolrInputDocument doc1 = new SolrInputDocument();

            doc1.setField("id", "513b08d9-b286-4da4-a09f-33d15687e972");
            doc1.addField("descricao_text_pt", "TAM SITE");
            doc1.addField("valor", 300);
            doc1.addField("codigoUsuario", 2);
            doc1.addField("data_cadastro_dt", DateUtil.format(dataCadastro));

            SolrInputDocument doc2 = new SolrInputDocument();

            doc2.addField("descricao_text_pt", "Padaria No Céu");
            doc2.addField("valor", 50D);
            doc2.addField("codigoUsuario", 2);
            doc2.addField("categoria_text_pt", "Restaurante");
            doc2.addField("data_cadastro_dt", DateUtil.format(dataCadastro));

            SolrInputDocument doc3 = new SolrInputDocument();

            doc3.addField("descricao_text_pt", "Uber Do Brasil");
            doc3.addField("valor", 10D);
            doc3.addField("codigoUsuario", 2);
            doc3.addField("categoria_text_pt", "Transporte");
            doc3.addField("data_cadastro_dt", DateUtil.format(dataCadastro));

            solrClient.add(Arrays.asList(doc1, doc2, doc3));
            solrClient.commit();
            return solrClient;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Bean
    public SolrTemplate solrTemplate(SolrClient client) {
        return new SolrTemplate(client);
    }
}