package br.com.testesantanderway.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UtilDate {
    private static final String PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    public static String format(LocalDateTime dataCriacao) {
        return dataCriacao.format(DateTimeFormatter.ofPattern(PATTERN));
    }
}