package com.santander.interview.controller;

import static com.santander.interview.enums.ResponseMessageEnum.*;

import com.santander.interview.domain.Category;
import com.santander.interview.domain.Response;
import com.santander.interview.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense-management")
@Api(value = "Categoria")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @ApiOperation("Adicionar uma nova categoria")
    @PostMapping("/categories")
    public ResponseEntity<Response> addCategory(
            @ApiParam(value = "Nova categoria", required = true) @RequestBody Category category
    ) {
        categoryService.saveCategory(category);

        return new ResponseEntity<>(
                new Response(HttpStatus.OK.value(), ADD_CATEGORY_SUCCESS.getMessage(), null),
                HttpStatus.OK
        );
    }

    @ApiOperation("Sugestão de categoria")
    @GetMapping("/category/detail/{detailPrefix}")
    public ResponseEntity<Response> suggestionCategory(
            @ApiParam(value = "Prefixo da categoria", required = true) @PathVariable String detailPrefix
    ) {
        List<Category> categories = this.categoryService.searchCategoryByDetailPrefix(detailPrefix);

        return new ResponseEntity<>(
                new Response(HttpStatus.OK.value(), SUGGESTION_CATEGORY_SUCCESS.getMessage(), categories),
                HttpStatus.OK
        );
    }

}
