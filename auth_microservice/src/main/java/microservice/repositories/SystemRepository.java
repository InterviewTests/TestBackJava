package microservice.repositories;


import microservice.models.System;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface SystemRepository extends MongoRepository<System, String> {

    public System findByUsername(String username);

}
