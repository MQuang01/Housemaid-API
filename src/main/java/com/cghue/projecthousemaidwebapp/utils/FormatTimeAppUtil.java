package com.cghue.projecthousemaidwebapp.utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class FormatTimeAppUtil {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    public static final DateTimeFormatter TO_LOCALTIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public static LocalTime TO_LOCALTIME(String timeStart) {
        String formattedTime = String.format("%02d:%02d",
                Integer.parseInt(timeStart.split(":")[0]),
                Integer.parseInt(timeStart.split(":")[1]));
        LocalTime localTime = LocalTime.parse(formattedTime, TO_LOCALTIME_FORMATTER);
        return localTime;
    }

    public static LocalTime FORMAT(String timeStart) {
        return LocalTime.parse(timeStart, FORMATTER);
    }
}
