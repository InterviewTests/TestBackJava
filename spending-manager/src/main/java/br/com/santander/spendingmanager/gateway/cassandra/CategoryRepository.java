package br.com.santander.spendingmanager.gateway.cassandra;

import br.com.santander.spendingmanager.domain.Category;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends CrudRepository<Category, UUID> {

    @AllowFiltering
    List<Category> findCategoriesByCategoryStartsWith(String categoryTextPart);

    boolean existsCategoryByCategory(String category);
}
