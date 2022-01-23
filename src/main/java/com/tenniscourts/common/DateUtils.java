package com.tenniscourts.common;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateUtils {
    public static LocalDateTime localDateToLocalDateTime(LocalDate localDate, int hour, int min) {
        return LocalDateTime.of(localDate, LocalTime.of(hour, min));
    }
}
