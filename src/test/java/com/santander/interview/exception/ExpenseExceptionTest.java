package com.santander.interview.exception;

import static com.santander.interview.enums.ResponseMessageEnum.*;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;

public class ExpenseExceptionTest {
    private static final String MESSAGE = "teste";

    @Test
    public void expenseExceptionConstructor1Test() {
        ExpenseException ee = new ExpenseException();
        Assert.assertEquals(ee.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        Assert.assertEquals(ee.getResponseMessageEnum(), UNKNOWN_ERROR);
    }

    @Test
    public void expenseExceptionConstructor2Test() {
        ExpenseException ee = new ExpenseException(HttpStatus.OK, MESSAGE, EXPENSE_BADLY_FORMATTED_DATE);
        Assert.assertEquals(ee.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(ee.getResponseMessageEnum(), EXPENSE_BADLY_FORMATTED_DATE);
        Assert.assertEquals(ee.getMessage(), MESSAGE);
    }

    @Test
    public void expenseExceptionConstructor3Test() {
        ExpenseException ee = new ExpenseException(HttpStatus.OK, EXPENSE_BADLY_FORMATTED_DATE);
        Assert.assertEquals(ee.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(ee.getResponseMessageEnum(), EXPENSE_BADLY_FORMATTED_DATE);

        ee.setMessage(MESSAGE);
        ee.setResponseMessageEnum(UNKNOWN_ERROR);
        ee.setStatusCode(HttpStatus.BAD_GATEWAY);

        Assert.assertEquals(ee.getMessage(), MESSAGE);
        Assert.assertEquals(ee.getResponseMessageEnum(), UNKNOWN_ERROR);
        Assert.assertEquals(ee.getStatusCode(), HttpStatus.BAD_GATEWAY);
    }
}
