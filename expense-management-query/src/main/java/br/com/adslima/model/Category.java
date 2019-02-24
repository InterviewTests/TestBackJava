package br.com.adslima.model;

import java.io.Serializable;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@SolrDocument(collection = "categories-core")
public class Category implements Serializable {


	public Category() {

	}

	/**
	 * @param categoryDescription
	 */
	public Category(ExpenseCategory categoryDescription) {
		this.categoryDescription = categoryDescription.getDescription();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Field
	@Indexed(name = "cid", type = "string")
	private String categoryId;

	@Indexed(name = "edesc", type = "string")
	private String expenseDescription;

	@Field("categories_txt")
	@Indexed(name = "cdesc", type = "string")
	private String categoryDescription;
	
	/**
	 * @param expenseDescription the expenseDescription to set
	 */
	@Field("expenseDescription")
	public void setExpenseDescription(String expenseDescription) {
		this.expenseDescription = expenseDescription;
	}

}
