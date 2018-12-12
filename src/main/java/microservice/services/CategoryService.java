package microservice.services;


import microservice.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import microservice.models.Category;
import java.util.List;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepo;

    public List<Category> listSimilarCategories(String partialCategoryName) {
        return categoryRepo.findBySimilarName(partialCategoryName);
    }

}
