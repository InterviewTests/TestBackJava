package dev.wellison.santander.controller;

import dev.wellison.santander.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import dev.wellison.santander.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {


    @Autowired
    CategoryService categoryService;

    @PostMapping("/add")
    public Category addCategory(@RequestBody Category request){
        return categoryService.addCategory(request);
    }


    @GetMapping(value = "/find/{searchCategory}", produces = "application/json; charset=utf-8")
    public ResponseEntity<List<Category>> searchCategoryByDescription(@PathVariable String searchCategory) {

        List<Category> categories = categoryService.searchCategoryByDescription(searchCategory);
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

}
