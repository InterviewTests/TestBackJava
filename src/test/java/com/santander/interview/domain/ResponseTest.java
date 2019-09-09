package com.santander.interview.domain;

import org.junit.Assert;
import org.junit.Test;

public class ResponseTest {
    private static final long STATUS_CODE = 12;
    private static final String USER_MESSAGE = "userMessage";
    private static final String INTERNAL_MESSAGE = "internalMessage";

    @Test
    public void responseEmptyConstructorTest() {
        Response response = new Response();
        response.setStatusCode(STATUS_CODE);
        response.setInternalMessage(INTERNAL_MESSAGE);
        response.setUserMessage(USER_MESSAGE);

        Assert.assertEquals(response.getStatusCode(), STATUS_CODE);
        Assert.assertEquals(response.getUserMessage(), USER_MESSAGE);
        Assert.assertEquals(response.getInternalMessage(), INTERNAL_MESSAGE);
    }

    @Test
    public void responseWithAllConstructorAttrTest() {
        Response response = new Response(STATUS_CODE, USER_MESSAGE, INTERNAL_MESSAGE);

        Assert.assertEquals(response.getStatusCode(), STATUS_CODE);
        Assert.assertEquals(response.getUserMessage(), USER_MESSAGE);
        Assert.assertEquals(response.getInternalMessage(), INTERNAL_MESSAGE);
    }
}
