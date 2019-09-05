package com.santander.interview.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class ExpenseTest {
    private static final String ID = "123";
    private static final String DESCRICAO = "descricao";
    private static final double VALOR = 124.2;
    private static final long CODIGO_USUARIO = 142;
    private static final Date DATA = new Date();
    private static final String CATEGORY_ID = "32";
    private static final String CATEGORY_DETAIL = "teste";
    private static final Category CATEGORY = new Category(CATEGORY_ID, CATEGORY_DETAIL);

    @Test
    public void ExpenseTest() {
        String expectedToString = String.format(
                "Expense[id=%s, descricao=%s, valor=%f, codigoUsuario=%d, data=%s]",
                ID, DESCRICAO, VALOR, CODIGO_USUARIO, DATA
        );
        Expense expense = new Expense();
        expense.setId(ID);
        expense.setCategory(CATEGORY);
        expense.setDescricao(DESCRICAO);
        expense.setValor(VALOR);
        expense.setCodigoUsuario(CODIGO_USUARIO);
        expense.setData(DATA);

        Assert.assertEquals(expense.getId(), ID);
        Assert.assertEquals(expense.getCategory(), CATEGORY);
        Assert.assertEquals(expense.getCodigoUsuario(), CODIGO_USUARIO);
        Assert.assertEquals(expense.getData(), DATA);
        Assert.assertEquals(expense.getDescricao(), DESCRICAO);
        Assert.assertTrue(expense.getValor() == VALOR);
        Assert.assertEquals(expense.toString(), expectedToString);
    }
}
