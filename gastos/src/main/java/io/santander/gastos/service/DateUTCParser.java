package io.santander.gastos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class DateUTCParser extends SimpleDateFormat {

    private String pattern = "yyyy-MM-dd";

    final SimpleDateFormat sdf = new SimpleDateFormat(pattern);

    public Date toDate(String utcDate) {
        try {
            return sdf.parse(utcDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String toUtcDate(Date date) {
        return new SimpleDateFormat(pattern).format(date);
    }

}
