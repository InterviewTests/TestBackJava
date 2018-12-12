package microservice.repositories;

import java.util.List;
import microservice.models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


public interface CategoryRepository extends MongoRepository<Category, String> {

    @Query("{ '_id': { '$regex': '^?0' } }")
    public List<Category> findBySimilarName(String name);

}
