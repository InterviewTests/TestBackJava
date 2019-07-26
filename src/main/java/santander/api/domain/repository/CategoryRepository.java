package santander.api.domain.repository;

import org.springframework.data.repository.CrudRepository;
import santander.api.domain.Category;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, String> {
    List<Category> findByDescription(String description);
}
