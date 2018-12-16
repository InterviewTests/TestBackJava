package microservice.repositories;

import java.util.Date;
import java.util.List;
import microservice.models.Spend;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface SpendRepository extends MongoRepository<Spend, String> {
    
    @Query("{ 'date': { '$gte': ?0, '$lte': ?1 }, 'userCode': ?2 }")
    public List<Spend> findByStartAndEndDate(Date startDate, Date endDate, String userCode);

    public List<Spend> findByUserCode(String userCode);

    public List<Spend> findByDescription(String description);

}
