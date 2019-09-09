package com.santander.interview.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class ExpenseTest {
    private static final String ID = "123";
    private static final String DESCRIPTION = "descricao";
    private static final double VALUE = 124.2;
    private static final long USER_CODE = 142;
    private static final Date DATE = new Date();
    private static final String CATEGORY_DETAIL = "teste";
    private static final Category CATEGORY = new Category(CATEGORY_DETAIL);

    @Test
    public void expenseEmptyConstructorTest() {
        String expectedToString = String.format(
                "Expense[id=%s, descricao=%s, valor=%f, codigoUsuario=%d, data=%s]",
                ID, DESCRIPTION, VALUE, USER_CODE, DATE
        );
        Expense expense = new Expense();
        expense.setId(ID);
        expense.setCategory(CATEGORY);
        expense.setDescription(DESCRIPTION);
        expense.setValue(VALUE);
        expense.setUserCode(USER_CODE);
        expense.setDate(DATE);

        Assert.assertEquals(expense.getId(), ID);
        Assert.assertEquals(expense.getCategory(), CATEGORY);
        Assert.assertEquals(expense.getUserCode(), USER_CODE);
        Assert.assertEquals(expense.getDate(), DATE);
        Assert.assertEquals(expense.getDescription(), DESCRIPTION);
        Assert.assertTrue(expense.getValue() == VALUE);
        Assert.assertEquals(expense.toString(), expectedToString);
    }

    @Test
    public void expenseConstrutorWithAllAttrTest() {
        Expense expense = new Expense(DESCRIPTION, VALUE, USER_CODE, DATE, CATEGORY);

        Assert.assertEquals(expense.getCategory(), CATEGORY);
        Assert.assertEquals(expense.getUserCode(), USER_CODE);
        Assert.assertEquals(expense.getDate(), DATE);
        Assert.assertEquals(expense.getDescription(), DESCRIPTION);
        Assert.assertTrue(expense.getValue() == VALUE);
    }

    @Test
    public void expenseConstrutorWithoutCategoryAttrTest() {
        Expense expense = new Expense(DESCRIPTION, VALUE, USER_CODE, DATE);

        Assert.assertEquals(expense.getUserCode(), USER_CODE);
        Assert.assertEquals(expense.getDate(), DATE);
        Assert.assertEquals(expense.getDescription(), DESCRIPTION);
        Assert.assertTrue(expense.getValue() == VALUE);
    }
}
