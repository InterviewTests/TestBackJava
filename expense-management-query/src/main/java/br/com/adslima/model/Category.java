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
@SolrDocument(collection = "Santander-categories")
public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Field
	@Indexed(name = "cid", type = "string")
	private String categoryId;
	
	@Field
	@Indexed(name = "edesc", type = "string")
	private String expenseDescription;
	
	@Field("categories_txt")
	@Indexed(name = "cdesc", type = "string")
	private ExpenseCategory categoryDescription;

	public Category() {
	}

	/**
	 * 
	 * @param categoryId
	 * @param description
	 * @param category
	 */
	public Category(String categoryId, String expenseDescription, ExpenseCategory categoryDescription) {
		this.categoryId = categoryId;
		this.expenseDescription = expenseDescription;
		this.categoryDescription = categoryDescription;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", expenseDescription=" + expenseDescription + ", category="
				+ categoryDescription + "]";
	}

}
