package br.com.testesantanderway.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UtilDate {
    private static String data;

    public static String format(LocalDateTime dataCriacao) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
       // LocalDateTime dateTime = LocalDateTime(data, format);

        return "";
    }
}