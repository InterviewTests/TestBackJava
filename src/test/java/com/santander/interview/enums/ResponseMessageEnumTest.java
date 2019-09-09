package com.santander.interview.enums;

import org.junit.Assert;
import org.junit.Test;

import static com.santander.interview.enums.ResponseMessageEnum.*;

public class ResponseMessageEnumTest {
    @Test
    public void responseMessageEnumTest() {
        Assert.assertNotNull(UNKNOWN_ERROR.getUserMessage());
        Assert.assertNotNull(UNKNOWN_ERROR.getInternalMessage());
        Assert.assertNotNull(UNKNOWN_ERROR.getCode());
    }
}
