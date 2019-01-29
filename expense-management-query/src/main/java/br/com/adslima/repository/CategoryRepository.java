/**
 * 
 */
package br.com.adslima.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.data.solr.repository.Facet;
import org.springframework.data.solr.repository.Highlight;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import br.com.adslima.model.Category;

/**
 * @author andrews.silva
 *
 */
public interface CategoryRepository extends SolrCrudRepository<Category, String> {

	List<Category> findByDescription(String description);

	// Page<Category> findByNameOrDescription(@Boost(2) String name, String
	// description, Pageable pageable);

	@Query("category:?0")
	@Facet(fields = { "categories_txt" }, limit = 5)
	FacetPage<Category> findByDescriptionAndFacetOnCategories(String description, Pageable page);

	@Highlight(prefix = "<highlight>", postfix = "</highlight>")
	HighlightPage<Category> findByDescription(String description, Pageable pageable);

	// ExpenseManagement save(ExpenseManagement expenseManagement);

}