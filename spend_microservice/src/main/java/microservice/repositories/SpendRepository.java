package microservice.repositories;


import java.util.Date;
import java.util.List;
import microservice.models.Spend;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;


public interface SpendRepository extends MongoRepository<Spend, String> {
    
    @Query("{ 'date': { '$gte': ?0, '$lte': ?1 }, 'userCode': ?2 }")
    public List<Spend> findByStartAndEndDate(Date startDate, Date endDate, String userCode);

    public Spend findBy_id(ObjectId _id);

    public Page<Spend> findByDescriptionAndUserCodeAndCategoryNotNull(String description, String userCode, Pageable pageable);

}
