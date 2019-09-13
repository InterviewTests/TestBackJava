package com.santander.interview.service;

import com.santander.interview.domain.Category;
import com.santander.interview.service.impl.CategoryServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {
    private static final String DETAIL = "teste";

    @InjectMocks
    CategoryServiceImpl categoryService = new CategoryServiceImpl();

    @Mock
    Category categoryDomain;

    Category categoryObject;
    List<Category> categoriesResult;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.categoryObject = new Category(DETAIL);
        this.categoriesResult = new ArrayList<>();
        this.categoriesResult.add(this.categoryObject);
    }

    @Test
    public void saveCategoryTest() {
        boolean isOk = true;
        try {
            this.categoryService.saveCategory(categoryObject);
        } catch (Exception e) {
            isOk = false;
        }
        Assert.assertTrue(isOk);
    }

    @Test
    public void searchCategoryByDetailSubstringTest() {
        Mockito.when(categoryDomain.searchByDetailSubstring(DETAIL))
                .thenReturn(this.categoriesResult);

        List<Category> categories = this.categoryService.searchCategoryByDetailSubstring(DETAIL);
        Assert.assertEquals(categories, this.categoriesResult);
    }

}
