package microservice.services;


import microservice.repositories.SpendRepository;
import microservice.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import microservice.models.Spend;
import microservice.models.Category;
import org.bson.types.ObjectId;


@Service
public class SpendService {

    @Autowired
    private SpendRepository spendRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    public Spend insertNewSpend(Spend spend) {
        spend.set_id(ObjectId.get());
        if (spend.getCategory() != null) {
            Category c = new Category(spend.getCategory());
            categoryRepo.save(c);
        }
        spendRepo.save(spend);
        return spend; 
    }

}
