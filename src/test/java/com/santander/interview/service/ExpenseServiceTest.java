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

import java.text.ParseException;
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
    List<Expense> expensesResult;

    @InjectMocks
    ExpenseServiceImpl expenseService = new ExpenseServiceImpl();

    @Mock
    Expense expenseDomain;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        this.expensesResult = new ArrayList<>();
        this.expensesResult.add(new Expense());
    }

    @Test
    public void searchExpensesByUserCodeAndDate_InvalidDataTest() throws ParseException {
        List<Expense> expenses = new ArrayList<>();
        ResponseMessageEnum responseMessageEnumExpected = EXPENSE_BADLY_FORMATTED_DATE;
        HttpStatus httpStatusExpected = HttpStatus.BAD_REQUEST;
        Expense expense = new Expense(DESCRIPTION, VALUE, USER_CODE, DATE);
        expenses.add(expense);
        Mockito.when(this.expenseDomain.searchByUserCodeAndDate(USER_CODE, DATE_STRING_INVALID))
                .thenThrow(ParseException.class);

        try {
            this.expenseService.searchExpensesByUserCodeAndDate(USER_CODE, DATE_STRING_INVALID);
        } catch (ExpenseException ee) {
            ResponseMessageEnum responseMessageEnum = ee.getResponseMessageEnum();
            Assert.assertEquals(responseMessageEnum.getInternalMessage(), responseMessageEnumExpected.getInternalMessage());
            Assert.assertEquals(responseMessageEnum.getUserMessage(), responseMessageEnumExpected.getUserMessage());
            Assert.assertEquals(ee.getStatusCode(), httpStatusExpected);
        }
    }

    @Test
    public void searchExpensesByUserCodeTest() {
        Mockito.when(this.expenseDomain.searchByUserCode(USER_CODE)).thenReturn(this.expensesResult);
        Assert.assertEquals(
                this.expenseService.searchExpensesByUserCode(USER_CODE),
                this.expensesResult
        );
    }


    @Test
    public void updateExpenseTest() {
        boolean isOk = true;
        String uuid = UUID.randomUUID().toString();
        Expense expense = new Expense();
        Mockito.when(this.expenseDomain.update(uuid, expense)).thenReturn(true);

        try {
            this.expenseService.updateExpense(uuid, expense);
        } catch (ExpenseException e) {
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
        Mockito.when(this.expenseDomain.update(uuid, expense)).thenReturn(false);

        try {
            this.expenseService.updateExpense(uuid, expense);
        } catch (ExpenseException ee) {
            Assert.assertEquals(ee.getStatusCode(), httpStatusExpected);
            Assert.assertEquals(ee.getResponseMessageEnum().getUserMessage(), responseMessageEnum.getUserMessage());
        }

    }

}
