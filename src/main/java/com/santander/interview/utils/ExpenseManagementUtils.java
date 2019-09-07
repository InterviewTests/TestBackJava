package com.santander.interview.utils;

public class ExpenseManagementUtils {

    public static int expenseManagementUtils(int defaultValue, String value) {
        try {
            return Integer.valueOf(value);
        } catch (Exception e) {
            return defaultValue;
        }
    }

}
