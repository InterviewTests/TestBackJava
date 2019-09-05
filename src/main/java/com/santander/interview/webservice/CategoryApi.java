package com.santander.interview.webservice;

import static com.santander.interview.enums.ResponseMessageEnum.*;

import com.santander.interview.domain.Category;
import com.santander.interview.domain.Response;
import com.santander.interview.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryApi {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/categories")
    public ResponseEntity<Response> addCategory(@RequestBody Category category) {
        categoryService.saveCategory(category);

        return new ResponseEntity<>(
                new Response(HttpStatus.OK.value(), ADD_CATEGORY_SUCCESS.getMessage(), null),
                HttpStatus.OK
        );
    }

    @GetMapping("/category/detail/{detailPrefix}")
    public ResponseEntity<Response> suggestionCategory(@PathVariable String detailPrefix) {
        List<Category> categories = this.categoryService.searchCategoryByDetailPrefix(detailPrefix);

        return new ResponseEntity<>(
                new Response(HttpStatus.OK.value(), SUGGESTION_CATEGORY_SUCCESS.getMessage(), categories),
                HttpStatus.OK
        );
    }

}
