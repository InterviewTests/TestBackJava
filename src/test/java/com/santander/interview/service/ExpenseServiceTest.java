package com.santander.interview.service;

import com.santander.interview.domain.Category;
import com.santander.interview.domain.Expense;
import com.santander.interview.enums.ResponseMessageEnum;
import com.santander.interview.exception.ExpenseException;
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
import org.springframework.http.HttpStatus;

import java.util.*;

import static com.santander.interview.enums.ResponseMessageEnum.EXPENSE_BADLY_FORMATTED_DATE;
import static com.santander.interview.enums.ResponseMessageEnum.EXPENSE_NOT_FOUND;

public class ExpenseServiceTest {
    private static final long USER_CODE = 1232;
    private static final Date DATE = new Date();
    private static final String DATE_STRING = "01102019";
    private static final String DATE_STRING_INVALID = "011";
    private static final double VALUE = 124.2;
    private static final String DESCRIPTION = "Teste";
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
        Expense expense = new Expense(DESCRIPTION, VALUE, USER_CODE, DATE);
        expenses.add(expense);

        Mockito.when(this.expenseRepository.findByUserCode(USER_CODE)).thenReturn(expenses);

        this.expenseService.addNewExpense(expense);
        List<Expense> result = this.expenseService.findExpensesByUserCode(USER_CODE);
        Assert.assertEquals(result, expenses);
    }

    @Test
    public void addNewExpenseWithCategoryTest() {
        boolean isOk = true;
        Expense expense = new Expense(DESCRIPTION, VALUE, USER_CODE, DATE);
        Category category = new Category();
        List<Category> categories = new ArrayList<>();
        category.setDetail(DETAIL);
        expense.setCategory(category);
        categories.add(new Category(DETAIL));

        Mockito.when(categoryRepository.findByDetail(category.getDetail())).thenReturn(categories);
        try {
            this.expenseService.addNewExpense(expense);
        } catch (Exception e) {
            isOk = false;
        }

        Assert.assertTrue(isOk);
    }

    @Test
    public void findExpensesByCodigoUsuarioAndDataTest() throws ExpenseException {
        List<Expense> expenses = new ArrayList<>();
        Expense expense = new Expense(DESCRIPTION, VALUE, USER_CODE, DATE);
        expenses.add(expense);

        Assert.assertNotNull(this.expenseService.findExpensesByUserCodeAndDate(USER_CODE, DATE_STRING));
    }

    @Test
    public void findExpensesByCodigoUsuarioAndData_InvalidDataTest() {
        List<Expense> expenses = new ArrayList<>();
        ResponseMessageEnum responseMessageEnumExpected = EXPENSE_BADLY_FORMATTED_DATE;
        HttpStatus httpStatusExpected = HttpStatus.BAD_REQUEST;
        Expense expense = new Expense(DESCRIPTION, VALUE, USER_CODE, DATE);
        expenses.add(expense);

        try {
            this.expenseService.findExpensesByUserCodeAndDate(USER_CODE, DATE_STRING_INVALID);
        } catch (ExpenseException ee) {
            ResponseMessageEnum responseMessageEnum = ee.getResponseMessageEnum();
            Assert.assertEquals(responseMessageEnum.getInternalMessage(), responseMessageEnumExpected.getInternalMessage());
            Assert.assertEquals(responseMessageEnum.getUserMessage(), responseMessageEnumExpected.getUserMessage());
            Assert.assertEquals(ee.getStatusCode(), httpStatusExpected);
        }
    }

    @Test
    public void updateExpense_FoundExpenseTest() throws ExpenseException {
        boolean isOk = true;
        String uuid = UUID.randomUUID().toString();
        Expense expense = new Expense();
        Optional<Expense> optionalExpense = Optional.of(expense);
        Mockito.when(this.expenseRepository.findById(uuid)).thenReturn(optionalExpense);

        try {
            this.expenseService.updateExpense(uuid, expense);
        } catch (Exception e) {
            isOk = false;
        }

        Assert.assertTrue(isOk);
    }

    @Test
    public void updateExpense_NotFoundExpenseTest() {
        HttpStatus httpStatusExpected = HttpStatus.NOT_FOUND;
        ResponseMessageEnum responseMessageEnum = EXPENSE_NOT_FOUND;
        String uuid = UUID.randomUUID().toString();
        Expense expense = new Expense();
        Optional<Expense> optionalExpense = Optional.empty();
        Mockito.when(this.expenseRepository.findById(uuid)).thenReturn(optionalExpense);

        try {
            this.expenseService.updateExpense(uuid, expense);
        } catch (ExpenseException ee) {
            Assert.assertEquals(ee.getStatusCode(), httpStatusExpected);
            Assert.assertEquals(ee.getResponseMessageEnum().getUserMessage(), responseMessageEnum.getUserMessage());
        }

    }

}
