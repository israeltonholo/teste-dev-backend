package com.olisaude.israeltestedevbackend.domain.util;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class ClockUtils {

    public static OffsetDateTime brazilsDataFormatToParseOffsetDate(String brazilDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/uuuu");
        Date date = null;
        try {
            date = formatter.parse(brazilDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return OffsetDateTime.from(date.toInstant().atZone(ZoneId.systemDefault()));
    }

    public static Boolean isDateValid(String brazilDate) {
        String regex = "(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/(19|20)\\d{2}";
        return brazilDate.matches(regex);
    }
}
