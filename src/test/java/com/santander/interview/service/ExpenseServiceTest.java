package com.santander.interview.service;

import com.santander.interview.domain.Category;
import com.santander.interview.domain.Expense;
import com.santander.interview.repository.CategoryRepository;
import com.santander.interview.repository.ExpenseRepository;
import com.santander.interview.service.impl.ExpenseServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ExpenseServiceTest {
    private static final long CODIGO_USUARIO = 1232;
    private static final Date DATA = new Date();
    private static final String DATA_STRING = "01102019";
    private static final double VALOR = 124.2;
    private static final String DESCRICAO = "Teste";
    private static final String DETAIL = "Detalhes";

    @InjectMocks
    ExpenseServiceImpl expenseService = new ExpenseServiceImpl();

    @Mock
    ExpenseRepository expenseRepository;

    @Mock
    CategoryRepository categoryRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addNewExpenseWithCategoryNullTest() {
        List<Expense> expenses = new ArrayList<>();
        Expense expense = new Expense();
        expense.setCodigoUsuario(CODIGO_USUARIO);
        expense.setData(DATA);
        expense.setValor(VALOR);
        expense.setDescricao(DESCRICAO);
        expenses.add(expense);

        Mockito.when(this.expenseRepository.findByCodigoUsuario(CODIGO_USUARIO))
                .thenReturn(expenses);

        this.expenseService.addNewExpense(expense);
        List<Expense> result = this.expenseService.findExpensesByCodigoUsuario(CODIGO_USUARIO);
        Assert.assertEquals(result, expenses);
    }

    @Test
    public void addNewExpenseWithCategoryTest() {
        boolean isOk = true;
        Expense expense = new Expense();
        Category category = new Category();
        List<Category> categories = new ArrayList<>();
        String uuid = UUID.randomUUID().toString();

        expense.setCodigoUsuario(CODIGO_USUARIO);
        expense.setData(DATA);
        expense.setValor(VALOR);
        expense.setDescricao(DESCRICAO);
        category.setDetail(DETAIL);
        expense.setCategory(category);
        categories.add(new Category(uuid, DETAIL));

        Mockito.when(categoryRepository.findByDetail(category.getDetail()))
                .thenReturn(categories);
        try {
            this.expenseService.addNewExpense(expense);
        } catch (Exception e) {
            isOk = false;
        }

        Assert.assertTrue(isOk);
    }

    @Test
    public void findExpensesByCodigoUsuarioAndDataTest() throws ParseException {
        List<Expense> expenses = new ArrayList<>();
        Expense expense = new Expense();
        expense.setCodigoUsuario(CODIGO_USUARIO);
        expense.setData(DATA);
        expense.setValor(VALOR);
        expense.setDescricao(DESCRICAO);
        expenses.add(expense);

        Assert.assertNotNull(this.expenseService.findExpensesByCodigoUsuarioAndData(CODIGO_USUARIO, DATA_STRING));
    }

    @Test
    public void updateExpenseTest() {
        boolean isOk = true;
        String uuid = UUID.randomUUID().toString();
        Expense expense = new Expense();
        try {
            this.expenseService.updateExpense(uuid, expense);
        } catch (Exception e) {
            isOk = false;
        }

        Assert.assertTrue(isOk);
    }


}
