/**
 * 
 */
package br.com.adslima.model;

import java.io.Serializable;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author andrews.silva
 *
 */
@Data
@EqualsAndHashCode
@ToString
@Builder
@SolrDocument(collection = "santander-solr")
public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Field
	private String categoryId;
	@Field
	private String description;
	@Field("categories_txt")
	private ExpenseCategory category;

	public Category() {
	}

	/**
	 * @param id
	 * @param description
	 */
	public Category(String categoryId, String description, ExpenseCategory category) {
		this.categoryId = categoryId;
		this.description = description;
		this.category = category;
	}

}
