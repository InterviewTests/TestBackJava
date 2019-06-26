package io.santander.gastos.commons;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public abstract class DateParser extends SimpleDateFormat {

    final SimpleDateFormat sdf = new SimpleDateFormat("${spent.date-format}");

    public Date toDate(String utcDate) throws ParseException {
        return sdf.parse(utcDate);
    }

    public String toUtcDate(Date date) throws ParseException {
        return new SimpleDateFormat("${spent.date-format}").format(date);
    }

}
