package com.santander.interview.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ResponseTest {
    private static final long STATUS_CODE = 123;
    private static final String MESSAGE = "Teste";
    private static final Object DATA = new Object();

    @Test
    public void ResponseTest() {
        Response response = new Response();
        response.setData(DATA);
        response.setMessage(MESSAGE);
        response.setStatusCode(STATUS_CODE);

        Assert.assertEquals(response.getData(), DATA);
        Assert.assertEquals(response.getMessage(), MESSAGE);
        Assert.assertEquals(response.getStatusCode(), STATUS_CODE);
    }

    @Test
    public void ResponseConstructorWithParamsTest() {
        Response response = new Response(STATUS_CODE, MESSAGE, DATA);
        Assert.assertEquals(response.getStatusCode(), STATUS_CODE);
        Assert.assertEquals(response.getMessage(), MESSAGE);
        Assert.assertEquals(response.getData(), DATA);
    }
}
