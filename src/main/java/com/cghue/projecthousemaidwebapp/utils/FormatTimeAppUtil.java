package com.cghue.projecthousemaidwebapp.utils;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class FormatTimeAppUtil {
    public static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    public static final DateTimeFormatter TO_LOCALTIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public static LocalTime TO_LOCALTIME(String timeStart) {
        String formattedTime = String.format("%02d:%02d",
                Integer.parseInt(timeStart.split(":")[0]),
                Integer.parseInt(timeStart.split(":")[1]));
        LocalTime localTime = LocalTime.parse(formattedTime, TO_LOCALTIME_FORMATTER);
        return localTime;
    }

//    public static String getCodeOrder() {
//        LocalTime currentTime = LocalTime.now();
//        return String.format(AppConstant.get(), FORMATTER.format(currentTime));
//    }

    public static String getStringFormatTime(){
        return FORMATTER.format(new Date());
    }

}
