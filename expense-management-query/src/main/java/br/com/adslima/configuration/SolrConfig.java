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
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

/**
 * @author andrews.silva
 *
 */

@ComponentScan
@EnableSolrRepositories("br.com.adslima.repository")
public class SolrConfig {

	private HttpSolrClient solr;

	//// HttpSolrServer solr = new
	// HttpSolrServer("http://localhost:8983/solr/collection1");
	// SolrQuery query = new SolrQuery();
	// query.setQuery("q");
	// QueryResponse response = solrServer.query(query);
	// SolrDocumentList results = response.getResults();
	//
	//

	@Bean
	public SolrClient solrServer() {
		String urlString = String.format("http://localhost:8983/solr");
		solr = new HttpSolrClient.Builder(urlString).build();
		solr.setParser(new XMLResponseParser());
		return solr;

	}

	@Bean
	public SolrTemplate solrTemplate(SolrClient client) throws Exception {
		return new SolrTemplate(client);
	}
}