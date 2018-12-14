package microservice.repositories;

import java.util.Date;
import java.util.List;
import microservice.models.Spend;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface SpendRepository extends MongoRepository<Spend, String> {
    
    @Query("{ 'date': { '$gte': ?0, '$lte': ?1 } }")
    public List<Spend> findByDate(Date startDate, Date endDate);

    public List<Spend> findByDescription(String description);

}
