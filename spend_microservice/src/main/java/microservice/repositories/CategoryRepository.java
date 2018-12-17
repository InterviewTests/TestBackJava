package microservice.repositories;

import java.util.List;
import microservice.models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


public interface CategoryRepository extends MongoRepository<Category, String> {

    @Query("{ 'category': { $regex: '^?0', $options: 'i' } }")
    public List<Category> findBySimilarName(String category);

}
