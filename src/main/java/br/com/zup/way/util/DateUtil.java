package br.com.zup.way.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private static final String PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    public static LocalDateTime getStartDate(LocalDateTime dateToConvert) {
        return setZone((dateToConvert.toLocalDate()).atStartOfDay());
    }

    public static LocalDateTime getEndDate(LocalDateTime dateToConvert) {
        return setZone((dateToConvert.toLocalDate()).atTime(23, 59, 59));
    }

    public static LocalDateTime setZone(LocalDateTime localDateTime) {
        return localDateTime
                .atZone(ZoneId.of("UTC"))
                .toLocalDateTime();
    }

    public static String format(LocalDateTime dateTime) {
        return ZonedDateTime.of(dateTime, ZoneId.of("UTC")).format(DateTimeFormatter.ofPattern(PATTERN));
    }
}
