package santander.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import santander.api.domain.Category;
import santander.api.domain.repository.CategoryRepository;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/category")
public class CategoryController {


    @Autowired
    CategoryRepository categoryRepository;

    @PostMapping("/add")
    public Category addCategory(@RequestBody Category request){
        String id = String.valueOf(new Random().nextInt());
        Category category = new Category(id,request.getDescription());
        return categoryRepository.save(category);
    }


    @GetMapping(value = "/find/{searchCategory}", produces = "application/json; charset=utf-8")
    public ResponseEntity<List<Category>> searchCategoryByDescription(@PathVariable String searchCategory) {

        List<Category> categories = categoryRepository.findByDescription(searchCategory);
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

}
