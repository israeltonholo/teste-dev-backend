package com.olisaude.israeltestedevbackend.domain.util;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class ClockUtils {

    public static OffsetDateTime brazilsDataFormatToParseOffsetDate(String brazilDate) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        LocalDate date;
        date = LocalDate.parse(brazilDate, formatter);
        return date.atStartOfDay(ZoneId.of("GMT-3")).toOffsetDateTime();
    }

    public static Boolean isDateValid(String brazilDate) {
        String regex = "(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/(19|20)\\d{2}";
        return brazilDate.matches(regex);
    }
}
