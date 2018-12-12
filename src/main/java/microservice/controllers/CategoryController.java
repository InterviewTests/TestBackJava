package microservice.controllers;


import java.util.List;
import microservice.models.Category;
import javax.validation.constraints.Size;
import org.springframework.http.ResponseEntity;
import microservice.services.CategoryService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.springframework.http.MediaType;


@RestController
@Validated
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

 
    @RequestMapping(value = "/category/suggestions", 
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Category>> getSuggestedCategories(
        @Size(min=2, message="partial_name should contain at least 2 characters") 
        @RequestParam(value="partial_name") 
        String partialCategoryName) throws InterruptedException, ExecutionException {

            CompletableFuture<List<Category>> categoryPromisse = categoryService.listSimilarCategories(partialCategoryName);
            return ResponseEntity.ok(categoryPromisse.get());

    }

}
