package com.santander.interview.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CategoryTest {
    private static final String ID = "123";
    private static final String DETAIL = "teste";

    @Test
    public void CategoryTest() {
        Category category = new Category();
        category.setId(ID);
        category.setDetail(DETAIL);

        Assert.assertEquals(category.getId(), ID);
        Assert.assertEquals(category.getDetail(), DETAIL);
    }

    @Test
    public void CategoryConstructorWithParamsTest() {
        Category category = new Category(ID, DETAIL);
        Assert.assertEquals(category.getId(), ID);
        Assert.assertEquals(category.getDetail(), DETAIL);
    }
}
