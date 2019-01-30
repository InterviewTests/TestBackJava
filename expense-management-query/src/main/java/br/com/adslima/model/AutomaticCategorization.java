
/**
	 * 
	 */
package br.com.adslima.model;

import java.io.Serializable;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

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
@Document
public class AutomaticCategorization implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String categoryId;
	@Field
	private String description;
	@Field
	private Category category;

	public AutomaticCategorization() {
	}

	/**
	 * @param id
	 * @param description
	 */
	public AutomaticCategorization(String categoryId, String description, Category category) {
		this.categoryId = categoryId;
		this.description = description;
		this.category = category;
	}

}
