package dev.wellison.santander.repository;

import dev.wellison.santander.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, String> {
    List<Category> findByDescription(String description);
}
