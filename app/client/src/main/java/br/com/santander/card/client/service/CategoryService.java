package br.com.santander.card.client.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.santander.card.client.http.dto.Category;
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
	
	public Set<Category> findAllByStartCategory(final String cat) {
		Set<Category> categories = new HashSet<>(0);
		SolrQuery query = new SolrQuery();
		query.set("q", String.format("category:%s*", cat));
		QueryResponse response;
		try {
			response = solr.query(query);
			SolrDocumentList docs = response.getResults();
			categories = docs.stream().map(doc->{
				Object category = doc.getFieldValue("category");
				Object desc = doc.getFieldValue("description");
				Category c = null;
				if(Objects.nonNull(category) && Objects.nonNull(desc)) {
					c = new Category(category.toString(), desc.toString());					
				}
				return c;
			}).collect(Collectors.toSet());
			
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return categories;
	}

	public void createCategory(Category category) {
		/* TODO Achar uma solução melhor, para nao ficar buscar no solr antes de inserir*/
		if(findAllByStartCategory(category.getCategory()).isEmpty()) {		
			SolrInputDocument document = new SolrInputDocument();
			document.setField("description", category.getDescription());
			document.setField("category", category.getCategory());
			try {
				solr.add(document);
				solr.commit();
			} catch (SolrServerException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
