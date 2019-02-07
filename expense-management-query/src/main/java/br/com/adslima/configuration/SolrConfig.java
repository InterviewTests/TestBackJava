/**
 * 
 */
package br.com.adslima.configuration;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

/**
 * @author andrews.silva
 *
 */
@Configuration
@EnableSolrRepositories(basePackages = { "br.com.adslima.repository" })
public class SolrConfig {

	static final String SOLR_HOST = String.format("http://localhost:8983/solr");

	private HttpSolrClient solr;

	@Bean
	public SolrClient solrServer() {

		solr = new HttpSolrClient.Builder(SOLR_HOST).build();
		solr.setParser(new XMLResponseParser());
		return solr;

	}

	@Bean
	public SolrTemplate solrTemplate(SolrClient client) throws Exception {
		return new SolrTemplate(client);
	}

}