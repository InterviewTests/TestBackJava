package com.santander.interview.webservice;

import com.santander.interview.domain.Category;
import com.santander.interview.domain.Response;
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


public class CategoryApiTest {
    private static final String CATEGORY_ID = "12asd";
    private static final String CATEGORY_DETAIL = "Detail";
    private Category category;

    @InjectMocks
    CategoryApi categoryApi = new CategoryApi();

    @Mock
    CategoryService categoryService;

    @Before
    public void init() {
        category = new Category(CATEGORY_ID, CATEGORY_DETAIL);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void suggestionCategoryTest() {
        String detailPrefix = "teste";
        Mockito.when(categoryService.searchCategoryByDetailPrefix(detailPrefix)).thenReturn(null);
        ResponseEntity<Response> response = categoryApi.suggestionCategory(detailPrefix);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertNull(response.getBody().getData());
    }

    @Test
    public void addCategoryTest() {
        ResponseEntity<Response> response = categoryApi.addCategory(category);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertNull(response.getBody().getData());
    }

}
