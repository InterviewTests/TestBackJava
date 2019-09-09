package com.santander.interview.domain;

import org.junit.Assert;
import org.junit.Test;

public class ResponseErrorTest {
    private static final long STATUS_CODE = 12;
    private static final String USER_MESSAGE = "userMessage";
    private static final String INTERNAL_MESSAGE = "internalMessage";
    private static final int INTERNAL_CODE = 1;
    private static final int INTERNAL_CODE_2 = 3;

    @Test
    public void responseErrorTest(){
        ResponseError responseError = new ResponseError(STATUS_CODE, USER_MESSAGE, INTERNAL_MESSAGE, INTERNAL_CODE);
        Assert.assertEquals(responseError.getStatusCode(), STATUS_CODE);
        Assert.assertEquals(responseError.getUserMessage(), USER_MESSAGE);
        Assert.assertEquals(responseError.getInternalMessage(), INTERNAL_MESSAGE);
        Assert.assertEquals(responseError.getInternalCode(), INTERNAL_CODE);

        responseError.setInternalCode(INTERNAL_CODE_2);
        Assert.assertEquals(responseError.getInternalCode(), INTERNAL_CODE_2);
    }
}
