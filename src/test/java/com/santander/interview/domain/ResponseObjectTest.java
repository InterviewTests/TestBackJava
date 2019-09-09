package com.santander.interview.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ResponseObjectTest {
    private static final long STATUS_CODE = 123;
    private static final String USER_MESSAGE = "userMessage";
    private static final String INTERNAL_MESSAGE = "internalMessage";
    private static final Object DATA = new Object();

    @Test
    public void responseEmptyConstTest() {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setData(DATA);
        responseObject.setUserMessage(USER_MESSAGE);
        responseObject.setInternalMessage(INTERNAL_MESSAGE);
        responseObject.setStatusCode(STATUS_CODE);

        Assert.assertEquals(responseObject.getData(), DATA);
        Assert.assertEquals(responseObject.getUserMessage(), USER_MESSAGE);
        Assert.assertEquals(responseObject.getInternalMessage(), INTERNAL_MESSAGE);
        Assert.assertEquals(responseObject.getStatusCode(), STATUS_CODE);
    }

    @Test
    public void responseConstructorWithParamsTest() {
        ResponseObject responseObject = new ResponseObject(STATUS_CODE, USER_MESSAGE, INTERNAL_MESSAGE, DATA);
        Assert.assertEquals(responseObject.getStatusCode(), STATUS_CODE);
        Assert.assertEquals(responseObject.getUserMessage(), USER_MESSAGE);
        Assert.assertEquals(responseObject.getInternalMessage(), INTERNAL_MESSAGE);
        Assert.assertEquals(responseObject.getData(), DATA);
    }
}
