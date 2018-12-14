package microservice.services;


import microservice.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import microservice.models.Category;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepo;

    @Async("ThreadPoolExecutor")
    public CompletableFuture<List<Category>> listSimilarCategories(String partialCategoryName) {
        return CompletableFuture.completedFuture(categoryRepo.findBySimilarName(partialCategoryName));
    }

}
