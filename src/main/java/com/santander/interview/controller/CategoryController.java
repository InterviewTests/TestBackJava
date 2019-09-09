package com.santander.interview.controller;

import static com.santander.interview.enums.ResponseMessageEnum.*;

import com.santander.interview.domain.Category;
import com.santander.interview.domain.ResponseObject;
import com.santander.interview.enums.ResponseMessageEnum;
import com.santander.interview.service.CategoryService;
import com.santander.interview.utils.ExpenseManagementUtils;
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
    public ResponseEntity<?> addCategory(
            @ApiParam(value = "Nova categoria", required = true) @RequestBody Category category
    ) {
        categoryService.saveCategory(category);

        return ExpenseManagementUtils.responseWithoutData(ADD_CATEGORY_SUCCESS, HttpStatus.OK);
    }

    @ApiOperation("Sugestão de categoria")
    @GetMapping("/category/detail/{detailSubstring}")
    public ResponseEntity<ResponseObject> suggestionCategory(
            @ApiParam(value = "Substring da categoria", required = true) @PathVariable String detailSubstring
    ) {
        List<Category> categories = this.categoryService.searchCategoryByDetailSubstring(detailSubstring);

        return ExpenseManagementUtils.responseWithData(SUGGESTION_CATEGORY_SUCCESS, HttpStatus.OK, categories);
    }

}
