package com.santander.interview;

import org.junit.Test;

public class ExpenseManagementTest {

    @Test(expected = Exception.class)
    public void mainStartDispatcherTest() {
        String[] args = new String[]{};
        ExpenseManagement.main(args);
    }
}
