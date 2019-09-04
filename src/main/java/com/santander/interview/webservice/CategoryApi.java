package com.santander.interview.webservice;

import com.santander.interview.domain.Category;
import com.santander.interview.domain.Response;
import com.santander.interview.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryApi {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/categories")
    public ResponseEntity<Response> addCategory(@RequestBody Category category) {
        categoryService.saveCategory(category);

        return new ResponseEntity<>(
                new Response(HttpStatus.OK.value(), "Categoria criada.", null),
                HttpStatus.OK
        );
    }

}
