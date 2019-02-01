/**
 * 
 */
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
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author andrews.silva
 *
 */
@Data
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
	private String categoryDescription;

}
