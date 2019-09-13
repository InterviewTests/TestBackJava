package com.santander.interview.controller;

import static com.santander.interview.enums.ResponseMessageEnum.*;

import com.santander.interview.domain.Category;
import com.santander.interview.domain.Response;
import com.santander.interview.domain.ResponseObject;
import com.santander.interview.service.CategoryService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;


public class CategoryControllerTest {
    private static final String CATEGORY_DETAIL = "Detail";
    private Category category;

    @InjectMocks
    CategoryController categoryController = new CategoryController();

    @Mock
    CategoryService categoryService;

    @Before
    public void init() {
        category = new Category(CATEGORY_DETAIL);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void suggestionCategoryTest() {
        String detailSubstring = "teste";
        List<Category> categories = new ArrayList<>();
        Mockito.when(categoryService.searchCategoryByDetailSubstring(detailSubstring)).thenReturn(categories);
        ResponseEntity<ResponseObject> response = categoryController.suggestionCategory(detailSubstring);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertNotNull(response.getBody().getData());
    }

    @Test
    public void addCategoryTest() {
        ResponseEntity<Response> response = categoryController.addCategory(category);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(response.getBody().getStatusCode(), HttpStatus.OK.value());
        Assert.assertEquals(response.getBody().getUserMessage(), ADD_CATEGORY_SUCCESS.getUserMessage());
        Assert.assertEquals(response.getBody().getInternalMessage(), ADD_CATEGORY_SUCCESS.getInternalMessage());
    }

}
