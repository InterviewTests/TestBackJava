/**
 * 
 */
package br.com.adslima.model;

import java.io.Serializable;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author andrews.silva
 *
 */
@Data
@EqualsAndHashCode
@Builder
@SolrDocument(collection = "solr")
public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Field
	@Indexed(name = "oid", type = "string")
	private String categoryId;
	@Field
	@Indexed(name = "odesc", type = "string")
	private String description;
	@Field("categories_txt")
	@Indexed(name = "ocat", type = "string")
	private ExpenseCategory category;

	public Category() {
	}

	/**
	 * 
	 * @param categoryId
	 * @param description
	 * @param category
	 */
	public Category(String categoryId, String description, ExpenseCategory category) {
		this.categoryId = categoryId;
		this.description = description;
		this.category = category;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", description=" + description + ", category=" + category + "]";
	}

}
