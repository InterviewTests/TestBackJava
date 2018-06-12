package br.com.santander.card.sale;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import lombok.extern.java.Log;

@Log
@Service
@Scope("prototype")
public class CategoryService {
	
	private HttpSolrClient solr;
	
	@Value("${sorl.ip-adress}")
	private String solrIp;
	
	@PostConstruct
	private void init() {
		String urlString = String.format("http://%s:8983/solr/santander-core", solrIp);
		solr = new HttpSolrClient.Builder(urlString).build();
		solr.setParser(new XMLResponseParser());
	}

	@Cacheable(value="categoryByDescription", key="#description")
	public String findFirstByDescription(final String description) {
		SolrQuery query = new SolrQuery();
		query.set("q", String.format("description:%s*", description));
		QueryResponse response;
		try {
			response = solr.query(query);
			SolrDocumentList doc = response.getResults();
			if(!doc.isEmpty()) {
				return doc.get(0).getFieldValue("category").toString();
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
