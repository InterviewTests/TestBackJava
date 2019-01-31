/**
 * 
 */
package br.com.adslima.configuration;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
//import org.apache.solr.client.solrj.SolrServer;
//import org.apache.solr.client.solrj.impl.HttpSolrServer;
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

	@Bean
	public SolrClient solrServer() {
		String solrURL = String.format("http://localhost:8983/solr");
		solr = new HttpSolrClient.Builder(solrURL).build();
		solr.setParser(new XMLResponseParser());
		return solr;

	}

	@Bean
	public SolrTemplate solrTemplate(SolrClient client) throws Exception {
		return new SolrTemplate(client);
	}
}