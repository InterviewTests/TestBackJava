package com.santander.interview.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ResponsePostTest {
    private static final long STATUS_CODE = 123;
    private static final String MESSAGE = "Teste";
    private static final Object DATA = new Object();

    @Test
    public void ResponseTest() {
        ResponsePost responsePost = new ResponsePost();
        responsePost.setData(DATA);
        responsePost.setUserMessage(MESSAGE);
        responsePost.setStatusCode(STATUS_CODE);

        Assert.assertEquals(responsePost.getData(), DATA);
        Assert.assertEquals(responsePost.getUserMessage(), MESSAGE);
        Assert.assertEquals(responsePost.getStatusCode(), STATUS_CODE);
    }

    @Test
    public void ResponseConstructorWithParamsTest() {
        ResponsePost responsePost = new ResponsePost(STATUS_CODE, MESSAGE, DATA);
        Assert.assertEquals(responsePost.getStatusCode(), STATUS_CODE);
        Assert.assertEquals(responsePost.getUserMessage(), MESSAGE);
        Assert.assertEquals(responsePost.getData(), DATA);
    }
}
