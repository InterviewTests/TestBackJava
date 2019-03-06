/**
 * 
 */
package br.com.adslima.configuration;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.springframework.beans.factory.annotation.Value;
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

	private HttpSolrClient solr;

	@Value("${spring.data.solr.host}")
	private String solrHost;

	@Bean
	public SolrClient solrServer() {
		solr = new HttpSolrClient.Builder(solrHost).build();
		solr.setParser(new XMLResponseParser());
		return solr;

	}

	@Bean
	public SolrTemplate solrTemplate(SolrClient client) throws Exception {
		return new SolrTemplate(client);
	}

}