/**
 * 
 */
package br.com.adslima.repository;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.stereotype.Repository;

import br.com.adslima.model.Category;

/**
 * 
 * @author andrews.silva
 *
 */
@Repository
public class CustomCategoryRepository {

	@Resource
	private SolrTemplate solrTemplate;

	public List<Category> dynamicSearch(String searchTerm) {
		Criteria conditions = createConditions(searchTerm);
		SimpleQuery search = new SimpleQuery(conditions);

		search.addSort(sortByIdDesc());

		Page<Category> results = solrTemplate.queryForPage("Category", search, Category.class);
		return results.getContent();
	}

	private Criteria createConditions(String searchTerm) {
		Criteria conditions = null;

		for (String term : searchTerm.split(" ")) {
			if (conditions == null) {
				conditions = new Criteria("oid").contains(term).or(new Criteria("odesc").contains(term));
			} else {
				conditions = conditions.or(new Criteria("oid").contains(term)).or(new Criteria("odesc").contains(term));
			}
		}
		return conditions;
	}

	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "oid");
	}

}
