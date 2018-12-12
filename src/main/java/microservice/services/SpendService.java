package microservice.services;


import microservice.repositories.SpendRepository;
import microservice.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import microservice.models.Spend;
import microservice.models.Category;
import org.bson.types.ObjectId;
import java.util.concurrent.CompletableFuture;


@Service
public class SpendService {

    @Autowired
    private SpendRepository spendRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    @Async("ThreadPoolExecutor")
    public CompletableFuture<Spend> insertNewSpend(Spend spend) {
        spend.set_id(ObjectId.get());
        if (spend.getCategory() != null) {
            Category c = new Category(spend.getCategory());
            categoryRepo.save(c);
        }
        return CompletableFuture.completedFuture(spendRepo.save(spend));  
    }

}
