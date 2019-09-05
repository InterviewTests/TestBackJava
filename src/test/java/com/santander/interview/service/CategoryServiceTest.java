package com.santander.interview.service;

import com.santander.interview.domain.Category;
import com.santander.interview.repository.CategoryRepository;
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
    @InjectMocks
    CategoryServiceImpl categoryService = new CategoryServiceImpl();

    @Mock
    CategoryRepository categoryRepository;

    Category category;
    List<Category> categoriesResult;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.category = new Category("123", "Detalhe");
        this.categoriesResult = new ArrayList<>();
        this.categoriesResult.add(this.category);
    }

    @Test
    public void saveCategoryTest() {
        boolean isOk = true;
        try {
            this.categoryService.saveCategory(this.category);
        } catch (Exception e) {
            isOk = false;
        }
        Assert.assertTrue(isOk);
    }

    @Test
    public void searchCategoryByDetailPrefixTest() {
        Mockito.when(categoryRepository.findByDetailLike(this.category.getDetail())).thenReturn(this.categoriesResult);

        String prefix = this.category.getDetail();
        List<Category> result = this.categoryService.searchCategoryByDetailPrefix(prefix);
        Assert.assertEquals(result, categoriesResult);
    }
}
